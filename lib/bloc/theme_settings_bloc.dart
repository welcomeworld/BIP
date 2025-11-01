import 'dart:ui';

import 'package:bip/bloc/bloc.dart';

import '../ui/theme/theme_notifier.dart';

class ThemeSettingsBloc extends Bloc {
  ThemeSettingsBloc(this._themeNotifier);

  final ThemeNotifier _themeNotifier;

  bool isDynamic() {
    return _themeNotifier.isDynamicTheme;
  }

  Color get dynamicColor => _themeNotifier.getDynamicColor();

  Future<void> enableDynamic() async {
    await _themeNotifier.setDynamicTheme(true);
  }

  bool isCustomColor() {
    return !isDynamic() && !_themeNotifier.isPresetColor(currentColor);
  }

  Color get currentColor {
    return _themeNotifier.value;
  }

  Future<void> setStaticColor(Color color) async {
    await _themeNotifier.setThemeColor(color, dynamic: false);
  }
}
