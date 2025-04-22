import 'dart:math';

import 'package:bip/player/bip_constant.dart';
import 'package:bip/ui/theme/theme_colors.dart';
import 'package:flutter/material.dart';
import 'package:media_kit/media_kit.dart';
import 'package:media_kit_video/media_kit_video_controls/src/controls/methods/video_state.dart';

class ResolutionButton extends StatefulWidget {
  const ResolutionButton({super.key});

  @override
  State<ResolutionButton> createState() => _ResolutionButtonState();
}

class _ResolutionButtonState extends State<ResolutionButton> {
  late final _player = controller(context).player;

  @override
  Widget build(BuildContext context) {
    var tracks = _player.state.tracks.video;

    var selectedTrack = _player.state.track.video;
    if (tracks.length <= 1) {
      return const SizedBox.shrink();
    }
    return TextButton(
        onPressed: () {
          _showItemListDialog(
            context,
            tracks.map((track) => _getResolutionName(track)).toList(),
            tracks.indexOf(selectedTrack),
            (index) {
              _player.setVideoTrack(tracks[index]);
            },
          );
        },
        child: Text(
          _getResolutionName(selectedTrack),
          style: const TextStyle(
              color: ThemeColors.playerContent, fontWeight: FontWeight.w600),
        ));
  }

  String _getResolutionName(VideoTrack video) {
    if (video.title != null) {
      return video.title!;
    }
    if (video.id == "auto") {
      return "自动分辨率";
    }
    if (video.id == "no") {
      return "禁用视频";
    }
    final fpsSuffix = (video.fps ?? 0) >= 59 ? "60帧" : "";
    final minResolution = min<int>(video.w ?? 0, video.h ?? 0);
    final resolutionPrefix = minResolution >= 2160 ? "4K" : "${minResolution}P";
    if (video.codec != null) {
      return "$resolutionPrefix(${video.codec}) $fpsSuffix";
    } else {
      return "$resolutionPrefix $fpsSuffix";
    }
  }
}

class AudioButton extends StatefulWidget {
  const AudioButton({super.key});

  @override
  State<AudioButton> createState() => _AudioButtonState();
}

class _AudioButtonState extends State<AudioButton> {
  late final _player = controller(context).player;

  @override
  Widget build(BuildContext context) {
    var tracks = _player.state.tracks.audio;

    var selectedTrack = _player.state.track.audio;
    if (tracks.length <= 1) {
      return const SizedBox.shrink();
    }
    var media = _player.state.playlist.medias.firstOrNull;
    return TextButton(
        onPressed: () {
          _showItemListDialog(
            context,
            tracks
                .map((track) => _getAudioQualityName(track, media?.extras))
                .toList(),
            tracks.indexOf(selectedTrack),
            (index) {
              _player.setAudioTrack(tracks[index]);
            },
          );
        },
        child: Text(
          _getAudioQualityName(selectedTrack, media?.extras),
          style: const TextStyle(
              color: ThemeColors.playerContent, fontWeight: FontWeight.w600),
        ));
  }

  String _getAudioQualityName(AudioTrack audio, Map<String, dynamic>? extras) {
    if (audio.title != null) {
      return audio.title!;
    }
    if (audio.id == "auto") {
      return "自动音轨";
    }
    if (audio.id == "no") {
      return "禁用音频";
    }
    return extras?[audio.id] ?? "${(audio.bitrate ?? 0) / 1000}bps";
  }
}

class SpeedButton extends StatefulWidget {
  const SpeedButton({super.key});

  @override
  State<SpeedButton> createState() => _SpeedButtonState();
}

class _SpeedButtonState extends State<SpeedButton> {
  late final _player = controller(context).player;

  @override
  Widget build(BuildContext context) {
    var currentSpeed = _player.state.rate;
    return TextButton(
        onPressed: () {
          final speedList = [
            2.0,
            1.5,
            1.25,
            1.0,
            0.75,
            0.5,
          ];
          _showItemListDialog(
            context,
            speedList.map((speed) => _formatSpeedName(speed)).toList(),
            speedList.indexOf(currentSpeed),
            (index) {
              _player.setRate(speedList[index]);
            },
          );
        },
        child: Text(
          currentSpeed == 1 ? "倍速" : _formatSpeedName(currentSpeed),
          style: const TextStyle(
              color: ThemeColors.playerContent, fontWeight: FontWeight.w600),
        ));
  }

  String _formatSpeedName(double speed) {
    final speedString = speed.toStringAsFixed(2);
    if (speedString.endsWith("0")) {
      return "${speedString.substring(0, speedString.length - 1)}X";
    }
    return "${speedString}X";
  }
}

class TitleView extends StatelessWidget {
  const TitleView({super.key});

  @override
  Widget build(BuildContext context) {
    var media = controller(context).player.state.playlist.medias.firstOrNull;
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      child: Text(
        media?.extras?[PlayerConstant.titleExtraKey] ?? "",
        style: const TextStyle(
          color: ThemeColors.playerContent,
          fontWeight: FontWeight.w600,
          fontSize: 18,
        ),
        maxLines: 1,
        softWrap: false,
        overflow: TextOverflow.fade,
      ),
    );
  }
}

void _showItemListDialog(
  BuildContext context,
  List<String> titleList,
  int selectedIndex,
  Function(int index) itemClick,
) {
  showDialog(
    context: context,
    barrierDismissible: true,
    barrierColor: Colors.transparent,
    builder: (context) {
      final colorScheme = Theme.of(context).colorScheme;
      return Align(
        alignment: Alignment.topRight,
        child: SizedBox(
          width: 268,
          height: MediaQuery.of(context).size.height,
          child: Material(
            color: ThemeColors.playerPanelBackground,
            child: Center(
              child: ListView(
                shrinkWrap: true,
                children: titleList.indexed.map((titlePair) {
                  final index = titlePair.$1;
                  final title = titlePair.$2;
                  return Padding(
                    padding:
                        const EdgeInsets.symmetric(vertical: 4, horizontal: 16),
                    child: FilledButton(
                        style: FilledButton.styleFrom(
                            backgroundColor:
                                ThemeColors.playerPanelContainer,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(8),
                            ),
                            minimumSize: const Size(0, 56)),
                        onPressed: () {
                          Navigator.of(context).pop();
                          itemClick(index);
                        },
                        child: Text(
                          title,
                          style: TextStyle(
                            fontSize: 16,
                            color: index == selectedIndex
                                ? colorScheme.primary
                                : ThemeColors.playerContent,
                          ),
                        )),
                  );
                }).toList(),
              ),
            ),
          ),
        ),
      );
    },
  );
}
