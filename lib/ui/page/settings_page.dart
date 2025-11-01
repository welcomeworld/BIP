import 'package:bip/bloc/bloc_state.dart';
import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/settings_manager.dart';
import 'package:bip/domain/model/setting_item.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/page_info.dart';
import 'package:flutter/material.dart';

import '../../bloc/settings_bloc.dart';

class SettingsPage extends StatefulWidget {
  const SettingsPage({super.key});

  @override
  State createState() => _SettingsPageState();
}

class _SettingsPageState extends BlocState<SettingsPage, SettingsBloc> {
  _SettingsPageState() : super(SettingsBloc(getIt<SettingsManager>()));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          onPressed: () {
            Navigator.of(context).maybePop();
          },
          padding: EdgeInsets.zero,
          icon: const Icon(Icons.arrow_back),
        ),
        title: Text(localeString.settings),
      ),
      body: StreamBuilder<Map<AppSetting, dynamic>>(
          stream: bloc.settingItems,
          builder: (context, snapshot) {
            final items = snapshot.data ?? {};
            return ListView(
              children: [
                for (var setting in AppSetting.values)
                  _buildSettingWidget(setting, items[setting]),
              ],
            );
          }),
    );
  }

  Widget _buildSettingWidget(AppSetting setting, dynamic value) {
    if (setting == AppSetting.about) {
      return _buildActionSetting(setting, () {
        BipRouter.rootRouter.push(PageNames.about);
      });
    }
    switch (setting.type) {
      case SettingType.boolToggle:
        return _buildToggleSetting(setting);
      case SettingType.selection:
        return _buildSelectionSetting(setting);
      case SettingType.slider:
        return _buildSliderSetting(setting);
      case SettingType.action:
        return _buildActionSetting(setting, null);
      default:
        return const SizedBox();
    }
  }

  Widget _buildToggleSetting(AppSetting setting) {
    final description = _getSettingDescription(setting);
    return SwitchListTile(
      title: Text(_getSettingTitle(setting)),
      subtitle:
          description.isNotEmpty ? Text(_getSettingDescription(setting)) : null,
      value: bloc.getValue(setting),
      onChanged: (newValue) {
        bloc.setValue<bool>(setting, newValue);
      },
    );
  }

  Widget _buildSliderSetting(AppSetting setting) {
    return const SizedBox();
  }

  Widget _buildSelectionSetting(AppSetting setting) {
    return const SizedBox();
  }

  Widget _buildActionSetting(AppSetting setting, GestureTapCallback? onTap) {
    final description = _getSettingDescription(setting);
    return ListTile(
      title: Text(_getSettingTitle(setting)),
      subtitle:
          description.isNotEmpty ? Text(_getSettingDescription(setting)) : null,
      onTap: onTap,
    );
  }

  String _getSettingTitle(AppSetting setting) {
    switch (setting) {
      case AppSetting.enterFull:
        return localeString.enterFull;
      case AppSetting.bestMedia:
        return localeString.bestMedia;
      case AppSetting.about:
        return localeString.about;
    }
  }

  String _getSettingDescription(AppSetting setting) {
    switch (setting) {
      case AppSetting.enterFull:
        return "";
      case AppSetting.bestMedia:
        return "";
      case AppSetting.about:
        return "";
    }
  }
}
