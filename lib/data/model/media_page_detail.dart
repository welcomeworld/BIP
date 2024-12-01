import 'package:bip/data/model/media_info.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/model/user_info.dart';

class MediaPageDetail {
  String sourceName = "";
  Map<String, dynamic> extras = {};
  late UserInfo owner;
  String title = "";
  String desc = "";
  int pageTime = 0;
  String cover = "";
  List<String> tags = [];
  Map<String, List<MediaInfo>> playlists = {};
  Map<String, List<MediaInfo>> additionPlaylists = {};
  List<MediaPagePreview> relatedMediaList = [];
  List<MediaPagePreview> seasonMediaList = [];
}
