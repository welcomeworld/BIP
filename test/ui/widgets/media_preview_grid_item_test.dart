import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/media_type.dart';
import 'package:bip/ui/widgets/media_preview_grid_item.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/mock_network_image.dart';
import '../../utils/ui_test_util.dart';

void main() {
  testWidgets('MediaPreviewGridItem renders correctly',
      (WidgetTester tester) async {
    final preview = MediaPagePreview(
      title: 'Grid Video',
      cover: 'http://example.com/cover.png',
      topDec: 'New',
      score: 9.8,
      indexShow: 'Ep 1',
      mediaType: MediaType.bangumi,
    );

    await runWithMockNetworkImages(() async {
      await UiTestUtil.pumpWithContext(tester,
          child: Center(
              child: SizedBox(
                  height: 400,
                  width: 200,
                  child: MediaPreviewGridItem(preview))));

      expect(find.text('Grid Video'), findsOneWidget);
      expect(find.text('New'), findsOneWidget);
      expect(find.text('Ep 1'), findsOneWidget);
      expect(find.text('9.8'), findsOneWidget);
      expect(find.byType(Image), findsOneWidget);
    });
  });
}
