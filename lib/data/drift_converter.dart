import 'dart:convert';

import 'package:drift/drift.dart';

import 'model/media_page_preview.dart';

class MediaPagePreviewConverter extends TypeConverter<MediaPagePreview, String> {
  const MediaPagePreviewConverter();

  @override
  MediaPagePreview fromSql(String fromDb) {
    return MediaPagePreview.fromJson(jsonDecode(fromDb) as Map<String, dynamic>);
  }

  @override
  String toSql(MediaPagePreview value) {
    return jsonEncode(value.toJson());
  }
}