import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/media_type.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:bip/ui/widgets/media_preview_card.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/mock_network_image.dart';
import '../../utils/ui_test_util.dart';

void main() {
  testWidgets('mediaPreviewCard renders video info correctly',
      (WidgetTester tester) async {
    final preview = MediaPagePreview(
      title: 'Test Video',
      cover: 'http://example.com/cover.png',
      duration: 120,
      // 2:00
      owner: UserInfo(name: 'TestUser', sourceName: 'test'),
      playCount: 1000,
      mediaType: MediaType.video,
    );

    bool pressed = false;

    await runWithMockNetworkImages(() async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return mediaPreviewCard(
              context,
              preview,
              onPressed: (p) {
                pressed = true;
                expect(p, preview);
              },
            );
          },
        ),
      );

      expect(find.text('Test Video', findRichText: true), findsOneWidget);
      expect(find.text('TestUser'), findsOneWidget);
      expect(find.text('02:00'), findsOneWidget);
      expect(find.byType(Image), findsOneWidget);

      await tester.tap(find.byType(FilledButton));
      expect(pressed, true);
    });
  });

  testWidgets('mediaPreviewCard renders top decoration if present',
      (WidgetTester tester) async {
    final preview = MediaPagePreview(
      title: 'Top Video',
      topDec: 'Top 1',
      mediaType: MediaType.video,
    );

    await runWithMockNetworkImages(() async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return mediaPreviewCard(context, preview);
          },
        ),
      );

      expect(find.text('Top 1'), findsOneWidget);
    });
  });
}
