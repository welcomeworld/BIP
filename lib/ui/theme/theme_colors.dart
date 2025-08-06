import 'package:flutter/material.dart';

class ThemeColors {
  ThemeColors._();

  static const Color brandColor = Color(0xFF29B6F6);
  static const Color playerBackdrop = Color(0x5F000000);
  static const Color playerContent = Color(0xFFFFFFFF);
  static const Color playerSeekbar = Color(0x4DFFFFFF);
  static const Color playerSeekbarBuffer = Color(0x6FFFFFFF);
  static const Color playerPanelBackground = Color(0xC0000000);
  static const Color playerPanelContainer = Color(0xB34F4F4F);
}

extension ColorSchemeExt on ColorScheme {
  Color get onSurfaceLow => onSurface.withOpacity(0.8);
  Color get vipGold => const Color(0xFFFFD700);
}
