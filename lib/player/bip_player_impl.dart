import 'dart:io';

import 'package:bip/domain/interfaces/bip_player.dart';
import 'package:media_kit/media_kit.dart';

import 'bip_constant.dart';

class BipPlayerImpl extends BipPlayer {
  @override
  Future<void> open(
    Playable playable, {
    bool play = true,
  }) async {
    if (playable is Media) {
      final extras = playable.extras;
      if (extras != null) {
        final audioTrackList = extras[PlayerConstant.audioTrackListExtraKey];
        if (audioTrackList is Map<String, String>) {
          final selectedAudioTrack =
              extras[PlayerConstant.selectedAudioTrackExtraKey];
          final audioUri = audioTrackList[selectedAudioTrack] ?? "";
          var nativePlayer = platform as NativePlayer;
          // 解除倍速限制
          await nativePlayer.setProperty("af", "scaletempo2=max-speed=8");
          //  音量不一致
          if (Platform.isAndroid) {
            await nativePlayer.setProperty("volume-max", "100");
          }

          if (selectedAudioTrack == PlayerConstant.disableAudioKey) {
            // 禁用音轨
            await setAudioTrack(AudioTrack.no());
          } else {
            await setAudioTrack(
              AudioTrack.auto(),
            );
          }

          // 音轨
          await nativePlayer.setProperty(
            'audio-files',
            Platform.isWindows
                ? audioUri.replaceAll(';', '\\;')
                : audioUri.replaceAll(':', '\\:'),
          );
        }
      }
    }
    await super.open(
      playable,
      play: play,
    );
  }
}
