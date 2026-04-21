import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test settings items', (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);
    await NavigationHelper.navigateToSettings(tester);

    expect(find.text('播放页面默认全屏'), findsOneWidget);
    expect(find.text('优先最佳画质'), findsOneWidget);
    expect(find.text('关于'), findsOneWidget);
  });
}
