import 'package:integration_test/integration_test.dart';
import 'flows/home_test.dart' as home_test;
import 'flows/bangumi_test.dart' as bangumi_test;
import 'flows/mine_test.dart' as mine_test;
import 'flows/settings_test.dart' as settings_test;
import 'flows/theme_settings_test.dart' as theme_settings_test;
import 'flows/collections_test.dart' as collections_test;
import 'flows/history_test.dart' as history_test;
import 'flows/search_test.dart' as search_test;

// 暂时没有在MacOS上找到让不同测试文件隔离测试的方法，所以需要注意手动清理可能对其他测试造成影响的状态
void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();
  home_test.main();
  bangumi_test.main();
  mine_test.main();
  settings_test.main();
  theme_settings_test.main();
  collections_test.main();
  history_test.main();
  search_test.main();
}
