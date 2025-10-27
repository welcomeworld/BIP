import 'package:bip/domain/interfaces/kv_store.dart';
import 'package:bip/domain/interfaces/settings_manager.dart';
import 'package:bip/domain/model/setting_item.dart';

class SettingsManagerImpl implements SettingsManager {
  SettingsManagerImpl(this._kvStore) {
    for (var setting in AppSetting.values) {
      _loadSetting(setting);
    }
  }

  final Map<AppSetting, dynamic> _settingsCache = {};
  final KvStore _kvStore;

  @override
  T getValue<T>(AppSetting setting) {
    if (_settingsCache.containsKey(setting)) {
      return _settingsCache[setting] as T;
    }
    return setting.getDefaultValue<T>();
  }

  @override
  Future<void> setValue<T>(AppSetting setting, T value) async {
    _settingsCache[setting] = value;

    if (value is bool) {
      await _kvStore.setBool(setting.key, value);
    } else if (value is String) {
      await _kvStore.setString(setting.key, value);
    } else if (value is double) {
      await _kvStore.setDouble(setting.key, value);
    } else if (value is int) {
      await _kvStore.setInt(setting.key, value);
    }
  }

  void _loadSetting(AppSetting setting) {
    final storedValue = _kvStore.get(setting.key);
    if (storedValue != null) {
      _settingsCache[setting] = storedValue;
    } else {
      _settingsCache[setting] = setting.defaultValue;
    }
  }
}
