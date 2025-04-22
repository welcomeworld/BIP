import 'package:bip/player/bip_controls.dart';
import 'package:bip/ui/theme/theme_colors.dart';
import 'package:flutter/material.dart';
import 'package:media_kit_video/media_kit_video.dart';

class BipVideo extends StatelessWidget {
  const BipVideo({super.key, required this.controller});

  final VideoController controller;

  @override
  Widget build(BuildContext context) {
    return MaterialVideoControlsTheme(
        normal: _normalTheme(context),
        fullscreen: _fullTheme(context),
        child: Video(controller: controller));
  }

  MaterialVideoControlsThemeData _normalTheme(BuildContext context) {
    return MaterialVideoControlsThemeData(
      displaySeekBar: false,
      automaticallyImplySkipNextButton: true,
      automaticallyImplySkipPreviousButton: true,
      volumeGesture: false,
      brightnessGesture: false,
      seekGesture: false,
      gesturesEnabledWhileControlsVisible: true,
      seekOnDoubleTap: false,
      seekOnDoubleTapEnabledWhileControlsVisible: true,
      seekOnDoubleTapLayoutTapsRatios: [1, 1, 1],
      seekOnDoubleTapLayoutWidgetRatios: [1, 1, 1],
      visibleOnMount: false,
      speedUpOnLongPress: false,
      speedUpFactor: 2.0,
      verticalGestureSensitivity: 100,
      horizontalGestureSensitivity: 1000,
      backdropColor: ThemeColors.playerBackdrop,
      controlsHoverDuration: const Duration(seconds: 4),
      controlsTransitionDuration: const Duration(milliseconds: 300),
      // bufferingIndicatorBuilder,
      // volumeIndicatorBuilder,
      // brightnessIndicatorBuilder,
      // seekIndicatorBuilder,
      // speedUpIndicatorBuilder,
      primaryButtonBar: [],
      topButtonBar: [],
      topButtonBarMargin: const EdgeInsets.symmetric(horizontal: 16.0),
      bottomButtonBar: const [
        MaterialPlayOrPauseButton(iconSize: 32.0),
        Expanded(child: MaterialSeekBar()),
        SizedBox(width: 8),
        MaterialPositionIndicator(),
        MaterialFullscreenButton(),
      ],
      bottomButtonBarMargin: const EdgeInsets.only(left: 4, right: 4),
      buttonBarHeight: 56.0,
      buttonBarButtonSize: 24.0,
      buttonBarButtonColor: ThemeColors.playerContent,
      seekBarMargin: EdgeInsets.zero,
      seekBarHeight: 2.4,
      seekBarContainerHeight: 36.0,
      seekBarColor: ThemeColors.playerSeekbar,
      seekBarPositionColor: Theme.of(context).colorScheme.primary,
      seekBarBufferColor: ThemeColors.playerSeekbarBuffer,
      seekBarThumbSize: 12.8,
      seekBarThumbColor: Theme.of(context).colorScheme.primary,
      seekBarAlignment: Alignment.center,
      shiftSubtitlesOnControlsVisibilityChange: false,
    );
  }

  MaterialVideoControlsThemeData _fullTheme(BuildContext context) {
    return MaterialVideoControlsThemeData(
      displaySeekBar: false,
      automaticallyImplySkipNextButton: true,
      automaticallyImplySkipPreviousButton: true,
      volumeGesture: true,
      brightnessGesture: true,
      seekGesture: true,
      gesturesEnabledWhileControlsVisible: false,
      seekOnDoubleTap: false,
      seekOnDoubleTapEnabledWhileControlsVisible: true,
      seekOnDoubleTapLayoutTapsRatios: [1, 1, 1],
      seekOnDoubleTapLayoutWidgetRatios: [1, 1, 1],
      visibleOnMount: false,
      speedUpOnLongPress: true,
      speedUpFactor: 2.0,
      verticalGestureSensitivity: 100,
      horizontalGestureSensitivity: 1000,
      backdropColor: ThemeColors.playerBackdrop,
      controlsHoverDuration: const Duration(seconds: 4),
      controlsTransitionDuration: const Duration(milliseconds: 300),
      // bufferingIndicatorBuilder,
      // volumeIndicatorBuilder,
      // brightnessIndicatorBuilder,
      // seekIndicatorBuilder,
      // speedUpIndicatorBuilder,
      primaryButtonBar: [],
      topButtonBar: const [
        Expanded(flex: 1, child: TitleView()),
        Expanded(
          flex: 1,
          child: SizedBox.shrink(),
        )
      ],
      topButtonBarMargin: const EdgeInsets.symmetric(horizontal: 8.0),
      bottomButtonBar: const [
        Expanded(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            mainAxisSize: MainAxisSize.max,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                padding: EdgeInsets.only(left: 16, right: 16),
                child: MaterialPositionIndicator(),
              ),
              Padding(
                padding: EdgeInsets.only(left: 16, right: 16),
                child: MaterialSeekBar(),
              ),
              SizedBox(
                height: 56,
                child: Row(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    MaterialPlayOrPauseButton(iconSize: 42.0),
                    Spacer(),
                    SpeedButton(),
                    ResolutionButton(),
                    AudioButton(),
                    MaterialFullscreenButton(
                      iconSize: 28,
                    ),
                  ],
                ),
              )
            ],
          ),
        ),
      ],
      bottomButtonBarMargin: const EdgeInsets.only(left: 4.0, right: 4),
      buttonBarHeight: 110,
      buttonBarButtonSize: 24.0,
      buttonBarButtonColor: ThemeColors.playerContent,
      seekBarMargin: EdgeInsets.zero,
      seekBarHeight: 2.4,
      seekBarContainerHeight: 36.0,
      seekBarColor: ThemeColors.playerSeekbar,
      seekBarPositionColor: Theme.of(context).colorScheme.primary,
      seekBarBufferColor: ThemeColors.playerSeekbarBuffer,
      seekBarThumbSize: 12.8,
      seekBarThumbColor: Theme.of(context).colorScheme.primary,
      seekBarAlignment: Alignment.center,
      shiftSubtitlesOnControlsVisibilityChange: false,
    );
  }
}
