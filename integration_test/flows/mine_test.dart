import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import '../utils/navigation_helper.dart';

void main() {
  testWidgets('Test mine page entries', (WidgetTester tester) async {
    await NavigationHelper.launchApp(tester);
    await NavigationHelper.navigateToMine(tester);

    expect(find.text('离线缓存'), findsOneWidget);
    expect(find.text('我的收藏'), findsOneWidget);
    expect(find.text('历史记录'), findsOneWidget);
    expect(find.text('聊天私信'), findsOneWidget);
    expect(find.text('主题'), findsOneWidget);
    expect(find.text('设置'), findsOneWidget);
  });
}
