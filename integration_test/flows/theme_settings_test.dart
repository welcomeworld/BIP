import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test theme settings', (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);
    await NavigationHelper.navigateToThemeSettings(tester);

    // Check for theme options
    expect(find.text('系统动态主题'), findsOneWidget);
    expect(find.text('自定义颜色'), findsOneWidget);

    // Toggle switch for dynamic theme
    final switchFinder = find.byType(Switch);
    if (switchFinder.evaluate().isNotEmpty) {
      await tester.tap(switchFinder.first);
      await tester.pumpAndSettle();
    }
  });
}
