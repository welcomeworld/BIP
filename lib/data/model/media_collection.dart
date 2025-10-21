import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/model/user_info.dart';

class MediaCollection {
  static const String localIdsKey = "local_ids";
  String sourceName;
  String id;
  Map<String, dynamic> extras;
  UserInfo owner;
  DateTime createTime;
  int itemCount;
  List<MediaPagePreview> items;
  String title;
  String cover;
  String description;
  bool visible;
  bool local;

  MediaCollection(
      {required this.sourceName,
      required this.title,
      String? id,
      Map<String, dynamic>? extras,
      UserInfo? owner,
      this.itemCount = 0,
      List<MediaPagePreview>? items,
      this.cover = "",
      this.description = "",
      this.visible = true,
      DateTime? createTime,
      this.local = false})
      : id = id ?? title,
        extras = extras ?? {},
        createTime = createTime ?? DateTime.now(),
        items = items ?? [],
        owner = owner ?? UserInfo(sourceName: sourceName);
}
