import 'package:bip/ui/widgets/top_icon_button.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/ui_test_util.dart';

void main() {
  testWidgets('topIconButton renders correctly and handles tap', (WidgetTester tester) async {
    bool tapped = false;
    
    await UiTestUtil.pumpWithContext(
      tester,
      child: topIconButton(
        const Icon(Icons.add),
        const Text('Add'),
        null,
        () {
          tapped = true;
        },
      ),
    );

    expect(find.byIcon(Icons.add), findsOneWidget);
    expect(find.text('Add'), findsOneWidget);
    expect(find.byType(IconButton), findsOneWidget);

    await tester.tap(find.byType(IconButton));
    expect(tapped, true);
  });
}
