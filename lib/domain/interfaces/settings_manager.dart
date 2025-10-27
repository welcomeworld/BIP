import 'package:bip/domain/model/setting_item.dart';

/// SettingsManager的抽象接口，用于依赖注入和测试
abstract class SettingsManager {
  /// 获取设置值
  T getValue<T>(AppSetting setting);

  /// 设置值
  Future<void> setValue<T>(AppSetting setting, T value);
}
