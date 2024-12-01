import 'package:shared_preferences/shared_preferences.dart';

class KvStore {
  KvStore._();

  static late SharedPreferences _pref;

  static Future<void> init() async {
    _pref = await SharedPreferences.getInstance();
  }

  static SharedPreferences getSp() {
    return _pref;
  }
}
