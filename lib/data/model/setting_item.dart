import '../../utils/constant.dart';

enum AppSetting {
  enterFull(Constant.kvSettingsEnterFull, SettingType.boolToggle, true),
  bestMedia(Constant.kvSettingsBestMedia, SettingType.boolToggle, true),
  about(Constant.kvSettingsAbout, SettingType.action, "");

  final String key;
  final SettingType type;
  final dynamic defaultValue;

  const AppSetting(this.key, this.type, this.defaultValue);

  // 类型安全的方法
  T getDefaultValue<T>() => defaultValue as T;
}

enum SettingType {
  boolToggle,
  selection,
  slider,
  action,
  undefined,
}
