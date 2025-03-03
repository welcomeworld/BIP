import 'package:flutter/material.dart';

class ThemeColors {
  ThemeColors._();

  static const Color brandColor = Color(0xFF29B6F6);
}

extension ColorSchemeExt on ColorScheme {
  Color get onSurfaceLow => onSurface.withOpacity(0.8);
}
