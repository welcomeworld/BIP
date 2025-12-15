import 'dart:ui';

import 'package:bip/bloc/theme_settings_bloc.dart';
import 'package:bip/ui/theme/theme_notifier.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'theme_settings_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<ThemeNotifier>()])
void main() {
  group('ThemeSettingsBloc', () {
    late ThemeSettingsBloc themeSettingsBloc;
    late MockThemeNotifier mockThemeNotifier;

    setUp(() {
      mockThemeNotifier = MockThemeNotifier();
      themeSettingsBloc = ThemeSettingsBloc(mockThemeNotifier);
    });

    tearDown(() {
      themeSettingsBloc.dispose();
    });

    test('isDynamic returns correct value from themeNotifier', () {
      when(mockThemeNotifier.isDynamicTheme).thenReturn(true);
      expect(themeSettingsBloc.isDynamic(), isTrue);

      when(mockThemeNotifier.isDynamicTheme).thenReturn(false);
      expect(themeSettingsBloc.isDynamic(), isFalse);
    });

    test('dynamicColor returns correct value from themeNotifier', () {
      const color = Color(0xFF0000FF);
      when(mockThemeNotifier.getDynamicColor()).thenReturn(color);

      expect(themeSettingsBloc.dynamicColor, color);
    });

    test('enableDynamic calls setDynamicTheme on themeNotifier', () async {
      when(mockThemeNotifier.setDynamicTheme(true)).thenAnswer((_) async {});

      await themeSettingsBloc.enableDynamic();

      verify(mockThemeNotifier.setDynamicTheme(true)).called(1);
    });

    test('isCustomColor returns correct value', () {
      when(mockThemeNotifier.isDynamicTheme).thenReturn(false);
      when(mockThemeNotifier.value).thenReturn(const Color(0xFFFF0000));
      when(mockThemeNotifier.isPresetColor(any)).thenReturn(false);

      expect(themeSettingsBloc.isCustomColor(), isTrue);
    });

    test('currentColor returns correct value from themeNotifier', () {
      const color = Color(0xFF00FF00);
      when(mockThemeNotifier.value).thenReturn(color);

      expect(themeSettingsBloc.currentColor, color);
    });

    test('setStaticColor calls setThemeColor on themeNotifier', () async {
      const color = Color(0xFFFFFF00);
      when(mockThemeNotifier.setThemeColor(color, dynamic: false))
          .thenAnswer((_) async {});

      await themeSettingsBloc.setStaticColor(color);

      verify(mockThemeNotifier.setThemeColor(color, dynamic: false)).called(1);
    });
  });
}
