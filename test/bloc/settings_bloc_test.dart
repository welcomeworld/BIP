import 'package:bip/bloc/settings_bloc.dart';
import 'package:bip/domain/interfaces/settings_manager.dart';
import 'package:bip/domain/model/setting_item.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'settings_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<SettingsManager>()])
void main() {
  group('SettingsBloc', () {
    late SettingsBloc settingsBloc;
    late MockSettingsManager mockSettingsManager;

    setUp(() {
      mockSettingsManager = MockSettingsManager();
      settingsBloc = SettingsBloc(mockSettingsManager);
    });
    tearDown(() {
      settingsBloc.dispose();
    });

    test('getValue should return value from settings manager', () {
      const setting = AppSetting.bestMedia;
      const value = true;
      when(mockSettingsManager.getValue<bool>(setting)).thenReturn(value);

      final result = settingsBloc.getValue<bool>(setting);

      expect(result, value);
      verify(mockSettingsManager.getValue(setting)).called(1);
    });

    test('setValue should call settings manager and notify listeners',
        () async {
      const setting = AppSetting.bestMedia;
      const value = true;
      when(mockSettingsManager.setValue<bool>(setting, value))
          .thenAnswer((_) async {});

      await settingsBloc.setValue<bool>(setting, value);

      verify(mockSettingsManager.setValue<bool>(setting, value)).called(1);
      expect(settingsBloc.settingItems, emits({}));
    });
  });
}
