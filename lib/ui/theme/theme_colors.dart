import 'package:flutter/material.dart';

class ThemeColors {
  ThemeColors._();

  static const Color brandColor = Color(0xFF29B6F6);

  static Color onSurfaceLow(BuildContext context) =>
      Theme.of(context).colorScheme.onSurface.withOpacity(0.8);
}
