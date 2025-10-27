/// 键值存储接口 - 抽象SharedPreferences
abstract class KvStore {
  /// 获取字符串值
  String? getString(String key);

  /// 设置字符串值
  Future<bool> setString(String key, String value);

  /// 获取整数值
  int? getInt(String key);

  /// 设置整数值
  Future<bool> setInt(String key, int value);

  /// 获取布尔值
  bool? getBool(String key);

  /// 设置布尔值
  Future<bool> setBool(String key, bool value);

  /// 获取双精度浮点值
  double? getDouble(String key);

  /// 设置双精度浮点值
  Future<bool> setDouble(String key, double value);

  /// 获取字符串列表
  List<String>? getStringList(String key);

  /// 设置字符串列表
  Future<bool> setStringList(String key, List<String> value);

  /// 移除指定键
  Future<bool> remove(String key);

  /// 清空所有数据
  Future<bool> clear();

  /// 检查是否包含指定键
  bool containsKey(String key);

  /// 获取所有键
  Set<String> getKeys();

  Object? get(String key);
}
