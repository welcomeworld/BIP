import 'package:bip/data/persistence/kv_store.dart';
import 'package:bip/ui/theme/theme_colors.dart';
import 'package:bip/utils/constant.dart';
import 'package:flutter/material.dart';

class ThemeNotifier extends ValueNotifier<Color> {
  static const List<Map<String, dynamic>> presetColors = [
    {'name': '少女粉', 'color': Color(0xFFFB7299)},
    {'name': '高能红', 'color': Color(0xFFFF3333)},
    {'name': '咸蛋黄', 'color': Color(0xFFFFD54F)},
    {'name': '早苗绿', 'color': Color(0xFF6dc781)},
    {'name': '宝石蓝', 'color': Color(0xFF29B6F6)},
    {'name': '罗兰紫', 'color': Color(0xFF9B59B6)},
  ];

  bool _isDynamicTheme = false;

  bool get isDynamicTheme => _isDynamicTheme;

  Color? _dynamicColor;

  void setDynamicColor(Color? color) {
    _dynamicColor = color;
  }

  Color getDynamicColor() {
    return _dynamicColor ?? ThemeColors.brandColor;
  }

  bool isPresetColor(Color color) {
    // 判断是否是预设颜色
    for (var item in presetColors) {
      if ((item['color'] as Color) == color) {
        return true;
      }
    }
    return false;
  }

  ThemeNotifier(super.value, {bool isDynamic = true}) {
    _isDynamicTheme = isDynamic;
  }

  static Future<ThemeNotifier> load() async {
    final sp = KvStore.getSp();
    final isDynamic = sp.getBool(Constant.kvKeyDynamicTheme) ?? true;
    if (isDynamic) {
      // 动态主题色，value 可用默认色
      return ThemeNotifier(presetColors[0]['color'] as Color, isDynamic: true);
    } else {
      int? colorValue = sp.getInt(Constant.kvKeyThemeColor);
      return ThemeNotifier(
        colorValue != null
            ? Color(colorValue)
            : presetColors[0]['color'] as Color,
        isDynamic: false,
      );
    }
  }

  Future<void> setThemeColor(Color color, {bool dynamic = false}) async {
    final sp = KvStore.getSp();
    await sp.setBool(Constant.kvKeyDynamicTheme, dynamic);
    if (!dynamic) {
      await sp.setInt(Constant.kvKeyThemeColor, color.toARGB32());
      value = color;
    }
    _isDynamicTheme = dynamic;
    notifyListeners();
  }

  Future<void> setDynamicTheme(bool enable) async {
    final sp = KvStore.getSp();
    await sp.setBool(Constant.kvKeyDynamicTheme, enable);
    _isDynamicTheme = enable;
    notifyListeners();
  }
}

// 全局单例
late final ThemeNotifier themeNotifier;

Future<void> initThemeNotifier() async {
  themeNotifier = await ThemeNotifier.load();
}
