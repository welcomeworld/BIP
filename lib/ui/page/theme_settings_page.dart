import 'package:bip/ui/theme/theme_notifier.dart';
import 'package:flutter/material.dart';
import 'package:flutter_colorpicker/flutter_colorpicker.dart';
import 'package:flutter_svg/svg.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/theme_settings_bloc.dart';
import '../../utils/bip_router.dart';

class ThemeSettingsPage extends StatefulWidget {
  const ThemeSettingsPage({super.key});

  @override
  State<ThemeSettingsPage> createState() => _ThemeSettingsPageState();
}

class _ThemeSettingsPageState
    extends BlocState<ThemeSettingsPage, ThemeSettingsBloc> {
  _ThemeSettingsPageState() : super(ThemeSettingsBloc());

  @override
  Widget build(BuildContext context) {
    final isDynamic = bloc.isDynamic();
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          onPressed: () {
            BipRouter.of(context).maybePop();
          },
          padding: EdgeInsets.zero,
          icon: const Icon(Icons.arrow_back),
        ),
        title: Text(localeString.theme),
      ),
      body: ListView(
        children: [
          ListTile(
            leading: Icon(
              Icons.auto_awesome_rounded,
              color: bloc.dynamicColor,
              size: 24,
            ),
            title: Text(localeString.dynamicTheme),
            trailing: isDynamic
                ? Icon(Icons.check, color: colorScheme.primary)
                : null,
            onTap: bloc.enableDynamic,
          ),
          ...ThemeNotifier.presetColors.map((item) => ListTile(
                leading: CircleAvatar(
                  backgroundColor: item['color'],
                  radius: 12,
                ),
                title: Text(item['name']),
                trailing:
                    !isDynamic && bloc.currentColor == (item['color'] as Color)
                        ? Icon(Icons.check, color: colorScheme.primary)
                        : null,
                onTap: () async {
                  await bloc.setStaticColor(item['color'] as Color);
                },
              )),
          ListTile(
            leading: SvgPicture.asset(
              "assets/img/ic_theme_custom.svg",
              width: 24,
              height: 24,
            ),
            title: Text(localeString.customTheme),
            trailing: bloc.isCustomColor()
                ? Icon(Icons.check, color: colorScheme.primary)
                : null,
            onTap: () async {
              final resultColor = await _showColorPicker(bloc.currentColor);
              if (resultColor != null) {
                await bloc.setStaticColor(resultColor);
              }
            },
          ),
        ],
      ),
    );
  }

  Future<Color?> _showColorPicker(Color initColor) async {
    Color tempColor = initColor;
    return showDialog<Color?>(
      context: context,
      builder: (context) {
        return AlertDialog(
          content: SingleChildScrollView(
            child: ColorPicker(
              pickerColor: tempColor,
              onColorChanged: (color) {
                tempColor = color;
              },
              enableAlpha: false,
              labelTypes: const [],
              paletteType: PaletteType.hueWheel,
              hexInputBar: true,
            ),
          ),
          actions: [
            TextButton(
              child: Text(localeString.cancel),
              onPressed: () => Navigator.of(context).pop(),
            ),
            TextButton(
              child: Text(localeString.confirm),
              onPressed: () async {
                Navigator.of(context).pop(tempColor);
              },
            ),
          ],
        );
      },
    );
  }
}
