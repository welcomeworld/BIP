class BiliIndexResponse {
  final int code;
  final ResponseData data;
  final String message;

  BiliIndexResponse({
    required this.code,
    required this.data,
    required this.message,
  });

  factory BiliIndexResponse.fromJson(Map<String, dynamic> json) {
    return BiliIndexResponse(
      code: json['code'] as int? ?? 0,
      data: json['data'] != null
          ? ResponseData.fromJson(json['data'] as Map<String, dynamic>)
          : ResponseData(hasNext: 0, list: [], num: 0, size: 0, total: 0),
      message: json['message'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'code': code,
      'data': data.toJson(),
      'message': message,
    };
  }
}

class ResponseData {
  final int hasNext;
  final List<BangumiItem> list;
  final int num;
  final int size;
  final int total;

  ResponseData({
    required this.hasNext,
    required this.list,
    required this.num,
    required this.size,
    required this.total,
  });

  factory ResponseData.fromJson(Map<String, dynamic> json) {
    return ResponseData(
      hasNext: json['has_next'] as int? ?? 0,
      list: json['list'] != null
          ? (json['list'] as List)
          .map((item) => BangumiItem.fromJson(item as Map<String, dynamic>))
          .toList()
          : [],
      num: json['num'] as int? ?? 0,
      size: json['size'] as int? ?? 0,
      total: json['total'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'has_next': hasNext,
      'list': list.map((item) => item.toJson()).toList(),
      'num': num,
      'size': size,
      'total': total,
    };
  }
}

class BangumiItem {
  final String badge;
  final BadgeInfo badgeInfo;
  final int badgeType;
  final String cover;
  final FirstEp firstEp;
  final String indexShow;
  final int isFinish;
  final String link;
  final int mediaId;
  final String order;
  final String orderType;
  final String score;
  final int seasonId;
  final int seasonStatus;
  final int seasonType;
  final String subTitle;
  final String title;
  final String titleIcon;

  BangumiItem({
    required this.badge,
    required this.badgeInfo,
    required this.badgeType,
    required this.cover,
    required this.firstEp,
    required this.indexShow,
    required this.isFinish,
    required this.link,
    required this.mediaId,
    required this.order,
    required this.orderType,
    required this.score,
    required this.seasonId,
    required this.seasonStatus,
    required this.seasonType,
    required this.subTitle,
    required this.title,
    required this.titleIcon,
  });

  factory BangumiItem.fromJson(Map<String, dynamic> json) {
    return BangumiItem(
      badge: json['badge'] as String? ?? '',
      badgeInfo: json['badge_info'] != null
          ? BadgeInfo.fromJson(json['badge_info'] as Map<String, dynamic>)
          : BadgeInfo(bgColor: '', bgColorNight: '', text: ''),
      badgeType: json['badge_type'] as int? ?? 0,
      cover: json['cover'] as String? ?? '',
      firstEp: json['first_ep'] != null
          ? FirstEp.fromJson(json['first_ep'] as Map<String, dynamic>)
          : FirstEp(cover: '', epId: 0),
      indexShow: json['index_show'] as String? ?? '',
      isFinish: json['is_finish'] as int? ?? 0,
      link: json['link'] as String? ?? '',
      mediaId: json['media_id'] as int? ?? 0,
      order: json['order'] as String? ?? '',
      orderType: json['order_type'] as String? ?? '',
      score: json['score'] as String? ?? '',
      seasonId: json['season_id'] as int? ?? 0,
      seasonStatus: json['season_status'] as int? ?? 0,
      seasonType: json['season_type'] as int? ?? 0,
      subTitle: json['subTitle'] as String? ?? '',
      title: json['title'] as String? ?? '',
      titleIcon: json['title_icon'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'badge': badge,
      'badge_info': badgeInfo.toJson(),
      'badge_type': badgeType,
      'cover': cover,
      'first_ep': firstEp.toJson(),
      'index_show': indexShow,
      'is_finish': isFinish,
      'link': link,
      'media_id': mediaId,
      'order': order,
      'order_type': orderType,
      'score': score,
      'season_id': seasonId,
      'season_status': seasonStatus,
      'season_type': seasonType,
      'subTitle': subTitle,
      'title': title,
      'title_icon': titleIcon,
    };
  }
}

class BadgeInfo {
  final String bgColor;
  final String bgColorNight;
  final String text;

  BadgeInfo({
    required this.bgColor,
    required this.bgColorNight,
    required this.text,
  });

  factory BadgeInfo.fromJson(Map<String, dynamic> json) {
    return BadgeInfo(
      bgColor: json['bg_color'] as String? ?? '',
      bgColorNight: json['bg_color_night'] as String? ?? '',
      text: json['text'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'bg_color': bgColor,
      'bg_color_night': bgColorNight,
      'text': text,
    };
  }
}

class FirstEp {
  final String cover;
  final int epId;

  FirstEp({
    required this.cover,
    required this.epId,
  });

  factory FirstEp.fromJson(Map<String, dynamic> json) {
    return FirstEp(
      cover: json['cover'] as String? ?? '',
      epId: json['ep_id'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'cover': cover,
      'ep_id': epId,
    };
  }
}