import 'dart:ui';

import 'package:bip/bloc/bloc.dart';

import '../ui/theme/theme_notifier.dart';

class ThemeSettingsBloc extends Bloc {
  bool isDynamic() {
    return themeNotifier.isDynamicTheme;
  }

  Color get dynamicColor => themeNotifier.getDynamicColor();

  Future<void> enableDynamic() async {
    await themeNotifier.setDynamicTheme(true);
  }

  bool isCustomColor() {
    return !isDynamic() && !themeNotifier.isPresetColor(currentColor);
  }

  Color get currentColor {
    return themeNotifier.value;
  }

  Future<void> setStaticColor(Color color) async {
    await themeNotifier.setThemeColor(color, dynamic: false);
  }
}
