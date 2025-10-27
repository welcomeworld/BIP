import 'dart:convert';

import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:drift/drift.dart';

class MediaPagePreviewConverter
    extends TypeConverter<MediaPagePreview, String> {
  const MediaPagePreviewConverter();

  @override
  MediaPagePreview fromSql(String fromDb) {
    return MediaPagePreview.fromJson(
        jsonDecode(fromDb) as Map<String, dynamic>);
  }

  @override
  String toSql(MediaPagePreview value) {
    return jsonEncode(value.toJson());
  }
}

class MapConverter extends TypeConverter<Map<String, dynamic>, String> {
  const MapConverter();

  @override
  Map<String, dynamic> fromSql(String fromDb) {
    return jsonDecode(fromDb) as Map<String, dynamic>;
  }

  @override
  String toSql(Map<String, dynamic> value) {
    return jsonEncode(value);
  }
}

class UserInfoConverter extends TypeConverter<UserInfo, String> {
  const UserInfoConverter();

  @override
  UserInfo fromSql(String fromDb) {
    return UserInfo.fromJson(jsonDecode(fromDb) as Map<String, dynamic>);
  }

  @override
  String toSql(UserInfo value) {
    return jsonEncode(value.toJson());
  }
}
