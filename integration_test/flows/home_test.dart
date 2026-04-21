import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test home page scroll and load more',
      (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);

    // Verify home page is displayed
    expect(find.text('首页'), findsOneWidget);

    // Wait for initial load

    // Find the CustomScrollView
    final scrollFinder = find.byType(CustomScrollView);
    await NavigationHelper.waitForWidget(tester, scrollFinder);
    expect(scrollFinder, findsOneWidget);

    // Count initial items (Image widgets are good proxies for media cards)
    final initialCount = tester.widgetList(find.byType(Image)).length;

    // Scroll down to trigger load more
    await tester.drag(scrollFinder, const Offset(0, -1000));
    await NavigationHelper.safePump(tester);

    // Verify items count
    final newCount = tester.widgetList(find.byType(Image)).length;
    expect(newCount, greaterThanOrEqualTo(initialCount));
  });
}
