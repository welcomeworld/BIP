class BiliBangumiRecommendResponse {
  final int code;
  final Data data;
  final String message;

  BiliBangumiRecommendResponse({
    required this.code,
    required this.data,
    required this.message,
  });

  factory BiliBangumiRecommendResponse.fromJson(Map<String, dynamic> json) {
    return BiliBangumiRecommendResponse(
      code: json['code'] as int? ?? 0,
      data: Data.fromJson(json['data'] as Map<String, dynamic>? ?? {}),
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

class Data {
  final List<Relate> relates;
  final List<Season> season;

  Data({
    required this.relates,
    required this.season,
  });

  factory Data.fromJson(Map<String, dynamic> json) {
    return Data(
      relates: (json['relates'] as List<dynamic>? ?? [])
          .map((e) => Relate.fromJson(e as Map<String, dynamic>? ?? {}))
          .toList(),
      season: (json['season'] as List<dynamic>? ?? [])
          .map((e) => Season.fromJson(e as Map<String, dynamic>? ?? {}))
          .toList(),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'relates': relates.map((e) => e.toJson()).toList(),
      'season': season.map((e) => e.toJson()).toList(),
    };
  }
}

class Relate {
  final String desc1;
  final String desc2;
  final int itemId;
  final String pic;
  final String title;
  final String typeName;
  final String url;

  Relate({
    required this.desc1,
    required this.desc2,
    required this.itemId,
    required this.pic,
    required this.title,
    required this.typeName,
    required this.url,
  });

  factory Relate.fromJson(Map<String, dynamic> json) {
    return Relate(
      desc1: json['desc1'] as String? ?? '',
      desc2: json['desc2'] as String? ?? '',
      itemId: json['item_id'] as int? ?? 0,
      pic: json['pic'] as String? ?? '',
      title: json['title'] as String? ?? '',
      typeName: json['type_name'] as String? ?? '',
      url: json['url'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'desc1': desc1,
      'desc2': desc2,
      'item_id': itemId,
      'pic': pic,
      'title': title,
      'type_name': typeName,
      'url': url,
    };
  }
}

class Season {
  final String actor;
  final String badge;
  final String cover;
  final NewEp newEp;
  final Rating rating;
  final String rcmdReason;
  final int seasonId;
  final Stat stat;
  final List<Style> styles;
  final String title;
  final String url;
  final UserStatus userStatus;

  Season({
    required this.actor,
    required this.badge,
    required this.cover,
    required this.newEp,
    required this.rating,
    required this.rcmdReason,
    required this.seasonId,
    required this.stat,
    required this.styles,
    required this.title,
    required this.url,
    required this.userStatus,
  });

  factory Season.fromJson(Map<String, dynamic> json) {
    return Season(
      actor: json['actor'] as String? ?? '',
      badge: json['badge'] as String? ?? '',
      cover: json['cover'] as String? ?? '',
      newEp: NewEp.fromJson(json['new_ep'] as Map<String, dynamic>? ?? {}),
      rating: Rating.fromJson(json['rating'] as Map<String, dynamic>? ?? {}),
      rcmdReason: json['rcmd_reason'] as String? ?? '',
      seasonId: json['season_id'] as int? ?? 0,
      stat: Stat.fromJson(json['stat'] as Map<String, dynamic>? ?? {}),
      styles: (json['styles'] as List<dynamic>? ?? [])
          .map((e) => Style.fromJson(e as Map<String, dynamic>? ?? {}))
          .toList(),
      title: json['title'] as String? ?? '',
      url: json['url'] as String? ?? '',
      userStatus: UserStatus.fromJson(json['user_status'] as Map<String, dynamic>? ?? {}),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'actor': actor,
      'badge': badge,
      'cover': cover,
      'new_ep': newEp.toJson(),
      'rating': rating.toJson(),
      'rcmd_reason': rcmdReason,
      'season_id': seasonId,
      'stat': stat.toJson(),
      'styles': styles.map((e) => e.toJson()).toList(),
      'title': title,
      'url': url,
      'user_status': userStatus.toJson(),
    };
  }
}

class NewEp {
  final String? cover;
  final String indexShow;

  NewEp({
    this.cover,
    required this.indexShow,
  });

  factory NewEp.fromJson(Map<String, dynamic> json) {
    return NewEp(
      cover: json['cover'] as String?,
      indexShow: json['index_show'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      if (cover != null) 'cover': cover,
      'index_show': indexShow,
    };
  }
}

class Rating {
  final int count;
  final double score;

  Rating({
    required this.count,
    required this.score,
  });

  factory Rating.fromJson(Map<String, dynamic> json) {
    return Rating(
      count: json['count'] as int? ?? 0,
      score: (json['score'] as num?)?.toDouble() ?? 0.0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'count': count,
      'score': score,
    };
  }
}

class Stat {
  final int danmaku;
  final int follow;
  final int view;
  final int? vt;
  final int? vtForUnity;

  Stat({
    required this.danmaku,
    required this.follow,
    required this.view,
    this.vt,
    this.vtForUnity,
  });

  factory Stat.fromJson(Map<String, dynamic> json) {
    return Stat(
      danmaku: json['danmaku'] as int? ?? 0,
      follow: json['follow'] as int? ?? 0,
      view: json['view'] as int? ?? 0,
      vt: json['vt'] as int?,
      vtForUnity: json['vtForUnity'] as int?,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'danmaku': danmaku,
      'follow': follow,
      'view': view,
      if (vt != null) 'vt': vt,
      if (vtForUnity != null) 'vtForUnity': vtForUnity,
    };
  }
}

class Style {
  final int? id;
  final String name;

  Style({
    this.id,
    required this.name,
  });

  factory Style.fromJson(Map<String, dynamic> json) {
    return Style(
      id: json['id'] as int?,
      name: json['name'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      if (id != null) 'id': id,
      'name': name,
    };
  }
}

class UserStatus {
  final int follow;

  UserStatus({
    required this.follow,
  });

  factory UserStatus.fromJson(Map<String, dynamic> json) {
    return UserStatus(
      follow: json['follow'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'follow': follow,
    };
  }
}