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
  bool coverPortrait = false;
  List<String> tags = [];
  int playCount = 0;
  Map<String, List<MediaInfo>> playlists = {};
  Map<String, List<MediaInfo>> additionPlaylists = {};
  List<MediaPagePreview> relatedMediaList = [];
  List<MediaPagePreview> seasonMediaList = [];

  MediaPageDetail();

  MediaPageDetail.fromPreview(MediaPagePreview preview) {
    sourceName = preview.sourceName;
    extras = preview.extras;
    owner = preview.owner;
    title = preview.title;
    pageTime = preview.pageTime;
    cover = preview.cover;
    coverPortrait = preview.coverPortrait;
    tags = preview.tags;
    playCount = preview.playCount;
  }
}
