class Constant {
  Constant._();

  static const String kvKeyWbi = "kv_key_wbi";
  static const String kvKeyUpdateTime = 'kv_key_update_time';
  static const String kvKeyBiliUser = "kv_key_bili_user";
  static late String cookiePath;
  static const String kvKeyThemeColor = 'kv_key_selected_theme_color';
  static const String kvKeyDynamicTheme = 'kv_key_is_dynamic_theme';

  static const int maxCompactSize = 600;
  static const int maxMediumSize = 840;

  // settings keys
  static const String kvSettingsMediaQuality = "kv_settings_media_quality";
}

class ReplyAction {
  ReplyAction._();

  static const int like = 1;
  static const int dislike = 2;
  static const int report = 3;
  static const int reply = 4;
  static const int delete = 5;
}
