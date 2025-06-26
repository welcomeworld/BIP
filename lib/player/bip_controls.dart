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
    var media = _player.state.playlist.medias.firstOrNull;
    if (media == null) {
      return const SizedBox.shrink();
    }
    var extras = media.extras;
    final trackList = (extras?[PlayerConstant.videoTrackListExtraKey]
            as Map<String, String>?) ??
        <String, String>{};
    final trackKeyList = [PlayerConstant.disableVideoKey, ...trackList.keys];
    final selectedTrackKey = extras?[PlayerConstant.selectedVideoTrackExtraKey];

    return TextButton(
        onPressed: () {
          _showItemListDialog(
            context,
            trackKeyList,
            trackKeyList.indexOf(selectedTrackKey),
            (index) async {
              final key = trackKeyList[index];
              if (key == PlayerConstant.disableVideoKey) {
                extras?[PlayerConstant.selectedVideoTrackExtraKey] =
                    PlayerConstant.disableVideoKey;
                _player.setVideoTrack(VideoTrack.no());
                return;
              } else if (key == selectedTrackKey) {
                return;
              } else {
                final trackUri = trackList[key];
                if (trackUri == null) {
                  return;
                }
                extras?[PlayerConstant.selectedVideoTrackExtraKey] = key;
                _player.open(
                    media.copyWith(
                      uri: trackUri,
                      start: _player.state.position,
                    ),
                    play: true);
              }
            },
          );
        },
        child: Text(
          selectedTrackKey,
          style: const TextStyle(
              color: ThemeColors.playerContent, fontWeight: FontWeight.w600),
        ));
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
    var media = _player.state.playlist.medias.firstOrNull;

    var extras = media?.extras;
    final trackList = (extras?[PlayerConstant.audioTrackListExtraKey]
            as Map<String, String>?) ??
        <String, String>{};
    final trackKeyList = [PlayerConstant.disableAudioKey, ...trackList.keys];
    final selectedTrackKey = extras?[PlayerConstant.selectedAudioTrackExtraKey];
    if (media == null || selectedTrackKey == null) {
      return const SizedBox.shrink();
    }
    return TextButton(
        onPressed: () {
          _showItemListDialog(
            context,
            trackKeyList,
            trackKeyList.indexOf(selectedTrackKey),
            (index) {
              final key = trackKeyList[index];
              if (key == PlayerConstant.disableAudioKey) {
                extras?[PlayerConstant.selectedAudioTrackExtraKey] =
                    PlayerConstant.disableAudioKey;
                _player.setAudioTrack(AudioTrack.no());
                return;
              } else if (key == selectedTrackKey) {
                return;
              } else {
                final trackUri = trackList[key];
                if (trackUri == null) {
                  return;
                }
                extras?[PlayerConstant.selectedAudioTrackExtraKey] = key;
                _player.setAudioTrack(AudioTrack.uri(trackUri));
              }
            },
          );
        },
        child: Text(
          selectedTrackKey,
          style: const TextStyle(
              color: ThemeColors.playerContent, fontWeight: FontWeight.w600),
        ));
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
                            backgroundColor: ThemeColors.playerPanelContainer,
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
