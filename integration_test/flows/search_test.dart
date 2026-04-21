import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test search functionality', (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);
    await NavigationHelper.navigateToSearch(tester);

    // Verify search input exists
    expect(find.byType(TextField), findsOneWidget);

    // Enter search text
    const searchText = 'Test Search Query';
    await tester.enterText(find.byType(TextField), searchText);
    await tester.testTextInput.receiveAction(TextInputAction.search);
    await NavigationHelper.safePump(tester);

    await tester.tap(find.byIcon(Icons.close));
    await tester.pumpAndSettle();

    // Verify history
    expect(find.text('搜索历史'), findsOneWidget);
    expect(find.text(searchText), findsOneWidget);
  });
}
