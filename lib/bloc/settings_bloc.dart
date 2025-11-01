import 'package:bip/bloc/bloc.dart';
import 'package:bip/domain/interfaces/settings_manager.dart';
import 'package:bip/domain/model/setting_item.dart';
import 'package:rxdart/rxdart.dart';

class SettingsBloc extends Bloc {
  SettingsBloc(this._settingsManager);

  final SettingsManager _settingsManager;
  final BehaviorSubject<Map<AppSetting, dynamic>> _settingItemsController =
      BehaviorSubject<Map<AppSetting, dynamic>>.seeded({});

  Stream<Map<AppSetting, dynamic>> get settingItems =>
      _settingItemsController.stream;

  T getValue<T>(AppSetting setting) {
    return _settingsManager.getValue<T>(setting);
  }

  Future<void> setValue<T>(AppSetting setting, T value) async {
    await _settingsManager.setValue<T>(setting, value);
    _settingItemsController.add({});
  }
}
