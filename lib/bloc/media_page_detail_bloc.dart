import 'package:bip/bloc/bloc.dart';
import 'package:bip/domain/interfaces/bip_player.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/domain/model/media_info.dart';
import 'package:bip/domain/model/media_page_detail.dart';
import 'package:bip/domain/model/media_page_history.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/logger.dart';
import 'package:flutter/material.dart';
import 'package:media_kit/media_kit.dart';
import 'package:media_kit_video/media_kit_video.dart';
import 'package:rxdart/rxdart.dart';
import 'package:rxdart/subjects.dart';

import '../player/bip_constant.dart';

class MediaPageDetailBloc extends Bloc {
  MediaPageDetailBloc(
    this._mediaManager,
    this._player,
    this._database,
    this._collectionManager,
  ) {
    controller = VideoController(_player);
    _player.stream.log.listen((log) {
      appLogger.debug("Player log:$log");
    });
  }

  final MediaManager _mediaManager;
  final BipPlayer _player;
  final Database _database;
  late final VideoController controller;
  final CollectionManager _collectionManager;

  BehaviorSubject<MediaPageDetail> detailSubject = BehaviorSubject();
  BehaviorSubject<MediaInfo> mediaInfoSubject = BehaviorSubject();
  BehaviorSubject<bool> showingReply = BehaviorSubject();
  BehaviorSubject<bool> inCollection = BehaviorSubject.seeded(false);
  List<MediaCollection> mediaCollections = [];
  BehaviorSubject<Set<MediaCollection>> selectedCollections =
      BehaviorSubject.seeded({});

  Future<void> setPreview(MediaPagePreview preview) async {
    _database.saveMediaPageHistory(MediaPageHistory(preview));
    detailSubject.add(MediaPageDetail.fromPreview(preview));
    final detailResult = await _mediaManager.requestDetail(preview);
    detailSubject.add(detailResult.result);
    if (detailResult.resultCode != SourceApiResult.resultSuccess) {
      ScaffoldMessenger.of(BipRouter.rootRouter.navigatorKey.currentContext!)
          .showSnackBar(
        SnackBar(
          content:
              Text("requestDetail with error code: ${detailResult.resultCode}"),
        ),
      );
    } else {
      //todo select playlist and list item
      queryMediaInfo(detailResult.result.playlists.values.first.first);
    }
    _checkInCollection(preview);
    _requestCollections();
  }

  Future<void> _checkInCollection(MediaPagePreview preview) async {
    final result = await _collectionManager.isInMediaCollection(preview);
    inCollection.add(result);
  }

  Future<void> queryMediaInfo(MediaInfo mediaInfo) async {
    mediaInfoSubject.add(mediaInfo);
    final mediaResult = await _mediaManager.requestMediaInfo(mediaInfo);
    if (mediaResult.resultCode != SourceApiResult.resultSuccess) {
      ScaffoldMessenger.of(BipRouter.rootRouter.navigatorKey.currentContext!)
          .showSnackBar(
        SnackBar(
          content: Text(
              "requestMediaInfo with error code: ${mediaResult.resultCode}"),
        ),
      );
    } else {
      var resultInfo = mediaResult.result;
      mediaInfoSubject.add(resultInfo);
      await _player.open(
        Media(
          resultInfo.mediaQualities[resultInfo.qualityKey]!,
          httpHeaders: resultInfo.headers,
          extras: {
            PlayerConstant.titleExtraKey: resultInfo.title,
            PlayerConstant.audioTrackListExtraKey: resultInfo.additionAudios,
            PlayerConstant.videoTrackListExtraKey: resultInfo.mediaQualities,
            PlayerConstant.selectedAudioTrackExtraKey:
                resultInfo.additionAudioKey,
            PlayerConstant.selectedVideoTrackExtraKey: resultInfo.qualityKey,
          },
        ),
        play: true,
      );
    }
  }

  Future<void> onMediaInfoClick(MediaInfo mediaInfo) async {
    await _player.stop();
    queryMediaInfo(mediaInfo);
  }

  Future<void> _requestCollections() async {
    final resultStream = _collectionManager.requestMediaCollections();
    mediaCollections.clear();
    await for (final result in resultStream) {
      mediaCollections.addAll(result);
    }
  }

  void clearSelectedCollections() {
    selectedCollections.add({});
  }

  @override
  void dispose() async {
    detailSubject.close();
    mediaInfoSubject.close();
    showingReply.close();
    selectedCollections.close();
    await _player.dispose();
    super.dispose();
  }

  void onCollectionChecked(MediaCollection collection, bool bool) {
    final selectedCollections = this.selectedCollections.value;
    if (bool) {
      selectedCollections.add(collection);
    } else {
      selectedCollections.remove(collection);
    }
    this.selectedCollections.add(Set.from(selectedCollections));
  }

  Future<bool> addToCollections(MediaPagePreview preview) async {
    if (selectedCollections.valueOrNull?.isEmpty ?? true) {
      return false;
    }
    bool allSuccess = true;
    for (final collection in selectedCollections.value) {
      final success =
          await _collectionManager.addToMediaCollection(collection, preview);
      if (!success) {
        allSuccess = false;
      }
    }
    inCollection.add(allSuccess);
    return allSuccess;
  }

  Future<bool> removeFromCollections(MediaPagePreview preview) async {
    final result =
        await _collectionManager.removeFromAllMediaCollection(preview);
    if (result) {
      inCollection.add(false);
    }
    return result;
  }
}
