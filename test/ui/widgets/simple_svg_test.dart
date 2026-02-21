import 'package:bip/ui/widgets/simple_svg.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/ui_test_util.dart';

void main() {
  testWidgets('SimpleSvg renders correctly', (WidgetTester tester) async {
    const assetName = 'assets/img/test_icon.svg';
    const color = Colors.red;
    const size = 30.0;

    await UiTestUtil.pumpWithContext(
      tester,
      child: const SimpleSvg(
        assetName,
        color: color,
        size: size,
      ),
    );

    final svgFinder = find.byType(SvgPicture);
    expect(svgFinder, findsOneWidget);

    final svgWidget = tester.widget<SvgPicture>(svgFinder);
    // Verify properties if possible, though SvgPicture implementation details might make deep inspection tricky without casting to specific internal types.
    // However, we can verify the widget was built.
    // Since we don't have the actual asset, SvgPicture might throw or log error if it tries to load, 
    // but in test environment without running the loop it might be fine or we might need to mock defaultBundle.
    // For now, let's just check existence.
    
    expect(svgWidget.width, size);
    expect(svgWidget.height, size);
    expect(svgWidget.colorFilter, const ColorFilter.mode(color, BlendMode.srcIn));
  });
}
