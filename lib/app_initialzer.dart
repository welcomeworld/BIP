import 'package:bip/di/get_it.dart';
import 'package:bip/utils/constant.dart';
import 'package:path_provider/path_provider.dart';

class AppInitializer {
  AppInitializer._();

  static Future<void> initialize() async {
    Constant.cookiePath = (await getApplicationCacheDirectory()).path;
    await setupLocator();
  }
}
