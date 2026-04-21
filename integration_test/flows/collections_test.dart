import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test collections creation and deletion',
      (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);
    await NavigationHelper.navigateToCollections(tester);

    // Create collection
    await tester.tap(find.byIcon(Icons.add));
    await NavigationHelper.safePump(tester);

    const collectionName = 'Test Collection';
    // Find text field by label '标题'
    await tester.enterText(
        find.widgetWithText(TextFormField, '标题'), collectionName);

    // Tap confirm '确认'
    await tester.tap(find.text('确认'));
    await NavigationHelper.safePump(tester);

    // Verify collection exists
    expect(find.text(collectionName, findRichText: true), findsOneWidget);

    // Enter collection
    await tester.tap(find.text(collectionName, findRichText: true));
    await NavigationHelper.safePump(tester);

    // Verify detail page
    expect(find.text(collectionName, findRichText: true), findsOneWidget);

    // Go back
    await tester.tap(find.byIcon(Icons.arrow_back));
    await NavigationHelper.safePump(tester);

    // Delete collection (Swipe left)
    final itemFinder = find.text(collectionName, findRichText: true);
    await tester.drag(itemFinder, const Offset(-500, 0));
    await NavigationHelper.safePump(tester);

    // Confirm delete dialog
    await tester.tap(find.text('确认'));
    await NavigationHelper.safePump(tester);

    // Verify collection is gone
    expect(find.text(collectionName), findsNothing);
  });
}
