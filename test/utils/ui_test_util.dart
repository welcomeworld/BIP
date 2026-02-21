import 'package:bip/l10n/app_locale.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

class UiTestUtil {
  // 创建一个可重用的测试设置函数,它只提供上下文
  static Future<void> pumpWithContext(
    WidgetTester tester, {
    required Widget child,
    ThemeData? theme,
    Locale? locale,
  }) async {
    await tester.pumpWidget(
      MaterialApp(
        theme: theme,
        locale: locale ?? const Locale('zh', 'CN'),
        localizationsDelegates: AppLocale.localizationsDelegates,
        supportedLocales: AppLocale.supportedLocales,
        home: Scaffold(body: child),
      ),
    );
  }
}
