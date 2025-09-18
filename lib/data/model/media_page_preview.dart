import 'package:bip/data/model/media_type.dart';
import 'package:bip/data/model/user_info.dart';

class MediaPagePreview {
  String sourceName = "";
  String mediaPageId = "";
  String get uniqueId => sourceName + mediaPageId;
  Map<String, dynamic> extras = {};
  MediaType mediaType = MediaType.video;

  late UserInfo owner;
  String title = "";

  //seconds
  int recordPosition = 0;

  //seconds
  int duration = 0;
  int pageTime = 0;
  String cover = "";
  bool coverPortrait = false;
  List<String> tags = [];
  String topDec = "";
  int playCount = 0;

  // bangumi only
  double score = 0.0;
  String indexShow = "";

  MediaPagePreview({
    this.sourceName = "",
    this.mediaPageId = "",
    Map<String, dynamic>? extras,
    this.mediaType = MediaType.video,
    UserInfo? owner,
    this.title = "",
    this.recordPosition = 0,
    this.duration = 0,
    this.pageTime = 0,
    this.cover = "",
    this.coverPortrait = false,
    List<String>? tags,
    this.topDec = "",
    this.playCount = 0,
    this.score = 0.0,
    this.indexShow = "",
  })  : extras = extras ?? {},
        tags = tags ?? [],
        owner = owner ?? UserInfo();

  factory MediaPagePreview.fromJson(Map<String, dynamic> json) {
    return MediaPagePreview(
      sourceName: json['sourceName'] as String? ?? "",
      mediaPageId: json['mediaPageId'] as String? ?? "",
      extras: json['extras'] != null
          ? Map<String, dynamic>.from(json['extras'])
          : {},
      mediaType: MediaType.fromString(json['mediaType'] as String? ?? "video"),
      owner: UserInfo.fromJson(json['owner'] as Map<String, dynamic>? ?? {}),
      title: json['title'] as String? ?? "",
      recordPosition: json['recordPosition'] as int? ?? 0,
      duration: json['duration'] as int? ?? 0,
      pageTime: json['pageTime'] as int? ?? 0,
      cover: json['cover'] as String? ?? "",
      coverPortrait: json['coverPortrait'] as bool? ?? false,
      tags: (json['tags'] as List<dynamic>?)?.cast<String>() ?? [],
      topDec: json['topDec'] as String? ?? "",
      playCount: json['playCount'] as int? ?? 0,
      score: (json['score'] as num?)?.toDouble() ?? 0.0,
      indexShow: json['indexShow'] as String? ?? "",
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'sourceName': sourceName,
      'mediaPageId': mediaPageId,
      'extras': extras,
      'mediaType': mediaType.typeString,
      'owner': owner.toJson(),
      'title': title,
      'recordPosition': recordPosition,
      'duration': duration,
      'pageTime': pageTime,
      'cover': cover,
      'coverPortrait': coverPortrait,
      'tags': tags,
      'topDec': topDec,
      'playCount': playCount,
      'score': score,
      'indexShow': indexShow,
    };
  }
}
