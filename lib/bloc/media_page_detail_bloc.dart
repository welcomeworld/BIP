import 'package:bip/bloc/bloc.dart';
import 'package:bip/data/media_manager.dart';
import 'package:bip/data/model/media_info.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/logger.dart';
import 'package:flutter/material.dart';
import 'package:media_kit/media_kit.dart';
import 'package:media_kit_video/media_kit_video.dart';
import 'package:rxdart/rxdart.dart';
import 'package:rxdart/subjects.dart';

import '../data/model/media_page_detail.dart';

class MediaPageDetailBloc extends Bloc {
  BehaviorSubject<MediaPageDetail> detailSubject = BehaviorSubject();
  BehaviorSubject<MediaInfo> mediaInfoSubject = BehaviorSubject();
  late final player = Player();
  late final controller = VideoController(player);

  @override
  void initState(BuildContext context) {
    super.initState(context);
    player.stream.log.listen((log) {
      Logger.logConsole("Player log:$log");
    });
  }

  Future<void> setPreview(MediaPagePreview preview) async {
    detailSubject.add(MediaPageDetail.fromPreview(preview));
    final detailResult = await MediaManager().requestDetail(preview);
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
    final mediaResult = await MediaManager().requestMediaInfo(mediaInfo);
    if (mediaResult.resultCode != SourceApiResult.resultSuccess) {
      ScaffoldMessenger.of(BipRouter.rootRouter.navigatorKey.currentContext!)
          .showSnackBar(
        SnackBar(
          content: Text(
              "requestMediaInfo with error code: ${mediaResult.resultCode}"),
        ),
      );
    } else {
      mediaInfoSubject.add(mediaResult.result);
      //todo select different resolution and audio
      await player.open(
          Media(
            mediaResult.result.mediaQualities.values.first,
            httpHeaders: mediaResult.result.headers,
          ),
          play: false);
      var audioEntry = mediaResult.result.additionAudios.entries.first;
      await player.setAudioTrack(
        AudioTrack.uri(audioEntry.value, title: "Dash Audio"),
      );
      player.play();
    }
  }

  Future<void> onMediaInfoClick(MediaInfo mediaInfo) async {
    await player.stop();
    queryMediaInfo(mediaInfo);
  }

  @override
  void dispose() async {
    detailSubject.close();
    mediaInfoSubject.close();
    await player.dispose();
    super.dispose();
  }
}
