import 'package:bip/ui/widgets/level_badge.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/ui_test_util.dart';

void main() {
  group('LevelBadge', () {
    testWidgets('renders correctly with level <= 50',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(tester,
          child: const LevelBadge(level: 10));

      expect(find.text('LV10', findRichText: true), findsOneWidget);

      final container = tester.widget<Container>(find.byType(Container));
      final decoration = container.decoration as BoxDecoration;
      expect(decoration.color, Colors.blueAccent);
    });

    testWidgets('renders correctly with level > 50',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(tester,
          child: const LevelBadge(level: 60));

      expect(find.text('LV60', findRichText: true), findsOneWidget);

      final container = tester.widget<Container>(find.byType(Container));
      final decoration = container.decoration as BoxDecoration;
      expect(decoration.color, Colors.redAccent);
    });

    testWidgets('renders nothing when level is -1',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(tester,
          child: const LevelBadge(level: -1));

      expect(find.byType(Container), findsNothing);
      expect(find.byType(SizedBox), findsOneWidget);
    });
  });
}
