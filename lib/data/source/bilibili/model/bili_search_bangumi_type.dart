import 'package:bip/utils/logger.dart';

class BiliSearchBangumiType {
  final String? type;
  final int? ssid;
  final String? author;
  final String? title;
  final String? pic;
  final String? typename;
  final String? styles;
  final String? angleTitle;
  final int? pubtime;
  final double score;
  final String indexShow;

  BiliSearchBangumiType({
    this.type,
    this.ssid,
    this.author,
    this.title,
    this.pic,
    this.typename,
    this.styles,
    this.angleTitle,
    this.pubtime,
    this.score = 0.0,
    this.indexShow = "",
  });

  factory BiliSearchBangumiType.fromJson(Map<String, dynamic> json) {
    var cover = json['cover'] ?? "";
    if (!cover.startsWith("http")) {
      cover = "https:$cover";
    }
    return BiliSearchBangumiType(
      type: json['type'] as String?,
      ssid: json['season_id'] as int?,
      author: "哔哩哔哩动画",
      title: json['title'] as String?,
      pic: cover,
      typename: json['season_type_name'] as String? ?? "番剧",
      styles: json['styles'] as String?,
      angleTitle: json['angle_title'] as String?,
      pubtime: json['pubtime'] as int?,
      score: (json['media_score']['score']?? 0.0).toDouble(),
      indexShow: json['index_show'] as String? ?? "",
    );
  }

  Map<String, dynamic> toJson() => {
        'type': type,
        'season_id': ssid,
        'author': author,
        'title': title,
        'cover': pic,
        'season_type_name': typename,
        'styles': styles,
        'angle_title': angleTitle,
        'pubtime': pubtime,
        'media_score': {
          'score': score,
        },
        'index_show': indexShow,
      };
}
