import 'package:bip/domain/model/media_info.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/user_info.dart';

import 'media_type.dart';

class MediaPageDetail {
  String sourceName = "";
  String mediaPageId = "";
  Map<String, dynamic> extras = {};
  MediaType mediaType = MediaType.video;
  late UserInfo owner;
  String title = "";
  String desc = "";
  int pageTime = 0;
  String cover = "";
  bool coverPortrait = false;
  List<String> tags = [];
  int playCount = 0;
  int replyCount = 0;
  Map<String, List<MediaInfo>> playlists = {};
  Map<String, List<MediaInfo>> additionPlaylists = {};
  List<MediaPagePreview> relatedMediaList = [];
  List<MediaPagePreview> seasonMediaList = [];

  // bangumi only
  double score = 0.0;
  String indexShow = "";

  MediaPageDetail();

  MediaPageDetail.fromPreview(MediaPagePreview preview) {
    sourceName = preview.sourceName;
    mediaPageId = preview.mediaPageId;
    extras = preview.extras;
    owner = preview.owner;
    title = preview.title;
    pageTime = preview.pageTime;
    cover = preview.cover;
    coverPortrait = preview.coverPortrait;
    tags = preview.tags;
    playCount = preview.playCount;
    mediaType = preview.mediaType;
    score = preview.score;
    indexShow = preview.indexShow;
  }
}
