import 'package:bip/domain/interfaces/kv_store.dart';
import 'package:shared_preferences/shared_preferences.dart';

class KvStoreImpl implements KvStore {
  KvStoreImpl._(this._pref);

  SharedPreferences _pref;

  static Future<KvStoreImpl> create() async {
    final pref = await SharedPreferences.getInstance();
    return KvStoreImpl._(pref);
  }

  @override
  String? getString(String key) => _pref.getString(key);

  @override
  Future<bool> setString(String key, String value) =>
      _pref.setString(key, value);

  @override
  int? getInt(String key) => _pref.getInt(key);

  @override
  Future<bool> setInt(String key, int value) => _pref.setInt(key, value);

  @override
  bool? getBool(String key) => _pref.getBool(key);

  @override
  Future<bool> setBool(String key, bool value) => _pref.setBool(key, value);

  @override
  double? getDouble(String key) => _pref.getDouble(key);

  @override
  Future<bool> setDouble(String key, double value) =>
      _pref.setDouble(key, value);

  @override
  List<String>? getStringList(String key) => _pref.getStringList(key);

  @override
  Future<bool> setStringList(String key, List<String> value) =>
      _pref.setStringList(key, value);

  @override
  Future<bool> remove(String key) => _pref.remove(key);

  @override
  Future<bool> clear() => _pref.clear();

  @override
  bool containsKey(String key) => _pref.containsKey(key);

  @override
  Set<String> getKeys() => _pref.getKeys();

  @override
  Object? get(String key) => _pref.get(key);
}
