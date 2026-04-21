import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test history recording', (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);

    // Wait for home load
    final scrollFinder = find.byType(CustomScrollView);
    await NavigationHelper.waitForWidget(tester, scrollFinder);

    // Tap the first video/image
    final images = find.byType(Image);
    if (images.evaluate().isNotEmpty) {
      await tester.tap(images.first);
      await NavigationHelper.safePump(tester);

      // Go back
      await NavigationHelper.pageBack(tester);
      await NavigationHelper.safePump(tester);

      // Navigate to History
      await NavigationHelper.navigateToHistory(tester);

      // Verify history items exist
      expect(find.byType(Image), findsWidgets);
    }
  });
}
