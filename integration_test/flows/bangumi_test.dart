import 'package:bip/ui/widgets/media_preview_grid_item.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test bangumi index and list update',
      (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);
    await NavigationHelper.navigateToBangumi(tester);

    // Check filters
    expect(find.byType(ChoiceChip), findsWidgets);

    expect(find.byType(MediaPreviewGridItem), findsWidgets);

    // Tap a ChoiceChip that is not selected.
    // We try to find the second chip (index 1) assuming index 0 is selected by default or just exists.
    final chips = find.byType(ChoiceChip);
    if (chips.evaluate().length > 1) {
      await tester.tap(chips.at(1));
      await NavigationHelper.safePump(tester);

      // Verify list still exists (update happens)
      expect(find.byType(MediaPreviewGridItem), findsWidgets);
    }
  });
}
