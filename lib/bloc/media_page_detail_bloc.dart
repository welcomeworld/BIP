import 'package:bip/bloc/bloc.dart';
import 'package:bip/data/media_manager.dart';
import 'package:bip/data/model/media_info.dart';
import 'package:bip/data/model/media_page_history.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/logger.dart';
import 'package:flutter/material.dart';
import 'package:media_kit/media_kit.dart';
import 'package:media_kit_video/media_kit_video.dart';
import 'package:rxdart/rxdart.dart';
import 'package:rxdart/subjects.dart';

import '../data/drift_database.dart';
import '../data/model/media_page_detail.dart';
import '../player/bip_constant.dart';
import '../player/bip_player.dart';

class MediaPageDetailBloc extends Bloc {
  MediaPageDetailBloc({
    MediaManager? mediaManager,
    Player? player,
    BipDatabase? database,
  }) {
    _mediaManager = mediaManager ?? MediaManager();
    _player = player ?? BipPlayer();
    controller = VideoController(_player);
    _database = database ?? BipDatabase();
  }

  late final MediaManager _mediaManager;
  late final Player _player;
  late final BipDatabase _database;
  late final VideoController controller;

  BehaviorSubject<MediaPageDetail> detailSubject = BehaviorSubject();
  BehaviorSubject<MediaInfo> mediaInfoSubject = BehaviorSubject();
  BehaviorSubject<bool> showingReply = BehaviorSubject();

  @override
  void initState(BuildContext context) {
    super.initState(context);
    _player.stream.log.listen((log) {
      Logger.logConsole("Player log:$log");
    });
  }

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

  @override
  void dispose() async {
    detailSubject.close();
    mediaInfoSubject.close();
    showingReply.close();
    await _player.dispose();
    super.dispose();
  }
}
