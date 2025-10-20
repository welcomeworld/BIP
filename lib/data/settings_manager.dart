import 'package:bip/data/persistence/kv_store.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'model/setting_item.dart';

class SettingsManager {
  static final SettingsManager _instance = SettingsManager._internal();

  factory SettingsManager() => _instance;

  SettingsManager._internal();

  final Map<AppSetting, dynamic> _settingsCache = {};
  SharedPreferences? _prefs;

  Future<void> init() async {
    _prefs = KvStore.getSp();
    for (var setting in AppSetting.values) {
      _loadSetting(setting);
    }
  }

  T getValue<T>(AppSetting setting) {
    if (_settingsCache.containsKey(setting)) {
      return _settingsCache[setting] as T;
    }
    return setting.getDefaultValue<T>();
  }

  Future<void> setValue<T>(AppSetting setting, T value) async {
    _settingsCache[setting] = value;

    if (_prefs != null) {
      if (value is bool) {
        await _prefs!.setBool(setting.key, value);
      } else if (value is String) {
        await _prefs!.setString(setting.key, value);
      } else if (value is double) {
        await _prefs!.setDouble(setting.key, value);
      } else if (value is int) {
        await _prefs!.setInt(setting.key, value);
      }
    }
  }

  void _loadSetting(AppSetting setting) {
    if (_prefs == null) return;

    final storedValue = _prefs!.get(setting.key);
    if (storedValue != null) {
      _settingsCache[setting] = storedValue;
    } else {
      _settingsCache[setting] = setting.defaultValue;
    }
  }
}
