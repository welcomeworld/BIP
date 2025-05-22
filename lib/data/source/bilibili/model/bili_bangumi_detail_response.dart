class BiliBangumiDetailResponse {
  final int code;
  final String message;
  final Result result;

  BiliBangumiDetailResponse({
    required this.code,
    required this.message,
    required this.result,
  });

  factory BiliBangumiDetailResponse.fromJson(Map<String, dynamic> json) {
    return BiliBangumiDetailResponse(
      code: json['code'] as int? ?? 0,
      message: json['message'] as String? ?? '',
      result: Result.fromJson(json['result'] as Map<String, dynamic>? ?? {}),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'code': code,
      'message': message,
      'result': result.toJson(),
    };
  }
}

class Result {
  final String actors;
  final String cover;
  final List<Episode> episodes;
  final String evaluate;
  final int pubTime;
  final NewEp newEp;
  final Rating rating;
  final int seasonId;
  final String seasonTitle;
  final List<Season> seasons;
  final List<Section> section;
  final String staff;
  final Stat stat;
  final List<String> styles;
  final String subtitle;
  final String title;
  final int type;
  final UpInfo upInfo;

  Result({
    required this.actors,
    required this.cover,
    required this.episodes,
    required this.evaluate,
    required this.pubTime,
    required this.newEp,
    required this.rating,
    required this.seasonId,
    required this.seasonTitle,
    required this.seasons,
    required this.section,
    required this.staff,
    required this.stat,
    required this.styles,
    required this.subtitle,
    required this.title,
    required this.type,
    required this.upInfo,
  });

  factory Result.fromJson(Map<String, dynamic> json) {
    return Result(
      actors: json['actors'] as String? ?? '',
      cover: json['cover'] as String? ?? '',
      episodes: (json['episodes'] as List<dynamic>? ?? [])
          .map((e) => Episode.fromJson(e as Map<String, dynamic>? ?? {}))
          .toList(),
      evaluate: json['evaluate'] as String? ?? '',
      pubTime: _dateTimeToTimestampSimple(
          json['publish']['pub_time'] as String? ?? ''),
      newEp: NewEp.fromJson(json['new_ep'] as Map<String, dynamic>? ?? {}),
      rating: Rating.fromJson(json['rating'] as Map<String, dynamic>? ?? {}),
      seasonId: json['season_id'] as int? ?? 0,
      seasonTitle: json['season_title'] as String? ?? '',
      seasons: (json['seasons'] as List<dynamic>? ?? [])
          .map((e) => Season.fromJson(e as Map<String, dynamic>? ?? {}))
          .toList(),
      section: (json['section'] as List<dynamic>? ?? [])
          .map((e) => Section.fromJson(e as Map<String, dynamic>? ?? {}))
          .toList(),
      staff: json['staff'] as String? ?? '',
      stat: Stat.fromJson(json['stat'] as Map<String, dynamic>? ?? {}),
      styles: (json['styles'] as List<dynamic>? ?? [])
          .map((e) => e as String)
          .toList(),
      subtitle: json['subtitle'] as String? ?? '',
      title: json['title'] as String? ?? '',
      type: json['type'] as int? ?? 0,
      upInfo: UpInfo.fromJson(json['up_info'] as Map<String, dynamic>? ?? {}),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'actors': actors,
      'cover': cover,
      'episodes': episodes.map((e) => e.toJson()).toList(),
      'evaluate': evaluate,
      'publish': {
        'pub_time': _timestampToDateTimeSimple(pubTime),
      },
      'new_ep': newEp.toJson(),
      'rating': rating.toJson(),
      'season_id': seasonId,
      'season_title': seasonTitle,
      'seasons': seasons.map((e) => e.toJson()).toList(),
      'section': section.map((e) => e.toJson()).toList(),
      'staff': staff,
      'stat': stat.toJson(),
      'styles': styles,
      'subtitle': subtitle,
      'title': title,
      'type': type,
      'up_info': upInfo.toJson(),
    };
  }

  static String _timestampToDateTimeSimple(int timestamp) {
    final date = DateTime.fromMillisecondsSinceEpoch(timestamp * 1000);
    return '${date.year}-${date.month.toString().padLeft(2, '0')}-'
        '${date.day.toString().padLeft(2, '0')} '
        '${date.hour.toString().padLeft(2, '0')}:'
        '${date.minute.toString().padLeft(2, '0')}:'
        '${date.second.toString().padLeft(2, '0')}';
  }

  static int _dateTimeToTimestampSimple(String dateTimeString) {
    try {
      DateTime dateTime = DateTime.parse(dateTimeString);
      return (dateTime.millisecondsSinceEpoch / 1000).floor();
    } catch (e) {
      return 0;
    }
  }
}

class Episode {
  final int aid;
  final String badge;
  final String bvid;
  final int cid;
  final String cover;
  final int duration;
  final int epId;
  final String from;
  final int id;
  final String link;
  final String longTitle;
  final int pubTime;
  final String showTitle;
  final Skip skip;
  final String subtitle;

  Episode({
    required this.aid,
    required this.badge,
    required this.bvid,
    required this.cid,
    required this.cover,
    required this.duration,
    required this.epId,
    required this.from,
    required this.id,
    required this.link,
    required this.longTitle,
    required this.pubTime,
    required this.showTitle,
    required this.skip,
    required this.subtitle,
  });

  factory Episode.fromJson(Map<String, dynamic> json) {
    return Episode(
      aid: json['aid'] as int? ?? 0,
      badge: json['badge'] as String? ?? '',
      bvid: json['bvid'] as String? ?? '',
      cid: json['cid'] as int? ?? 0,
      cover: json['cover'] as String? ?? '',
      duration: json['duration'] as int? ?? 0,
      epId: json['ep_id'] as int? ?? 0,
      from: json['from'] as String? ?? '',
      id: json['id'] as int? ?? 0,
      link: json['link'] as String? ?? '',
      longTitle: json['long_title'] as String? ?? '',
      pubTime: json['pub_time'] as int? ?? 0,
      showTitle: json['show_title'] as String? ?? '',
      skip: Skip.fromJson(json['skip'] as Map<String, dynamic>? ?? {}),
      subtitle: json['subtitle'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'aid': aid,
      'badge': badge,
      'bvid': bvid,
      'cid': cid,
      'cover': cover,
      'duration': duration,
      'ep_id': epId,
      'from': from,
      'id': id,
      'link': link,
      'long_title': longTitle,
      'pub_time': pubTime,
      'show_title': showTitle,
      'skip': skip.toJson(),
      'subtitle': subtitle,
    };
  }
}

class Skip {
  final Ed ed;
  final Op op;

  Skip({
    required this.ed,
    required this.op,
  });

  factory Skip.fromJson(Map<String, dynamic> json) {
    return Skip(
      ed: Ed.fromJson(json['ed'] as Map<String, dynamic>? ?? {}),
      op: Op.fromJson(json['op'] as Map<String, dynamic>? ?? {}),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'ed': ed.toJson(),
      'op': op.toJson(),
    };
  }
}

class Ed {
  final int end;
  final int start;

  Ed({
    required this.end,
    required this.start,
  });

  factory Ed.fromJson(Map<String, dynamic> json) {
    return Ed(
      end: json['end'] as int? ?? 0,
      start: json['start'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'end': end,
      'start': start,
    };
  }
}

class Op {
  final int end;
  final int start;

  Op({
    required this.end,
    required this.start,
  });

  factory Op.fromJson(Map<String, dynamic> json) {
    return Op(
      end: json['end'] as int? ?? 0,
      start: json['start'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'end': end,
      'start': start,
    };
  }
}

class NewEp {
  final String desc;

  NewEp({
    required this.desc,
  });

  factory NewEp.fromJson(Map<String, dynamic> json) {
    return NewEp(
      desc: json['desc'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'desc': desc,
    };
  }
}

class Rating {
  final double score;

  Rating({
    required this.score,
  });

  factory Rating.fromJson(Map<String, dynamic> json) {
    return Rating(
      score: (json['score'] as num?)?.toDouble() ?? 0.0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'score': score,
    };
  }
}

class Season {
  final String cover;
  final NewEp newEp;
  final int seasonId;
  final String seasonTitle;
  final Stat stat;

  Season({
    required this.cover,
    required this.newEp,
    required this.seasonId,
    required this.seasonTitle,
    required this.stat,
  });

  factory Season.fromJson(Map<String, dynamic> json) {
    return Season(
      cover: json['cover'] as String? ?? '',
      newEp: NewEp.fromJson(json['new_ep'] as Map<String, dynamic>? ?? {}),
      seasonId: json['season_id'] as int? ?? 0,
      seasonTitle: json['season_title'] as String? ?? '',
      stat: Stat.fromJson(json['stat'] as Map<String, dynamic>? ?? {}),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'cover': cover,
      'new_ep': newEp.toJson(),
      'season_id': seasonId,
      'season_title': seasonTitle,
      'stat': stat.toJson(),
    };
  }
}

class Section {
  final List<Episode> episodes;
  final int id;
  final String title;
  final int type;
  final int type2;

  Section({
    required this.episodes,
    required this.id,
    required this.title,
    required this.type,
    required this.type2,
  });

  factory Section.fromJson(Map<String, dynamic> json) {
    return Section(
      episodes: (json['episodes'] as List<dynamic>? ?? [])
          .map((e) => Episode.fromJson(e as Map<String, dynamic>? ?? {}))
          .toList(),
      id: json['id'] as int? ?? 0,
      title: json['title'] as String? ?? '',
      type: json['type'] as int? ?? 0,
      type2: json['type2'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'episodes': episodes.map((e) => e.toJson()).toList(),
      'id': id,
      'title': title,
      'type': type,
      'type2': type2,
    };
  }
}

class Stat {
  final int coins;
  final int danmakus;
  final int favorite;
  final int favorites;
  final String followText;
  final int likes;
  final int reply;
  final int share;
  final int views;

  Stat({
    required this.coins,
    required this.danmakus,
    required this.favorite,
    required this.favorites,
    required this.followText,
    required this.likes,
    required this.reply,
    required this.share,
    required this.views,
  });

  factory Stat.fromJson(Map<String, dynamic> json) {
    return Stat(
      coins: json['coins'] as int? ?? 0,
      danmakus: json['danmakus'] as int? ?? 0,
      favorite: json['favorite'] as int? ?? 0,
      favorites: json['favorites'] as int? ?? 0,
      followText: json['follow_text'] as String? ?? '',
      likes: json['likes'] as int? ?? 0,
      reply: json['reply'] as int? ?? 0,
      share: json['share'] as int? ?? 0,
      views: json['views'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'coins': coins,
      'danmakus': danmakus,
      'favorite': favorite,
      'favorites': favorites,
      'follow_text': followText,
      'likes': likes,
      'reply': reply,
      'share': share,
      'views': views,
    };
  }
}

class UpInfo {
  final String avatar;
  final int follower;
  final int isFollow;
  final int mid;
  final String uname;
  final int verifyType;
  final int vipStatus;

  UpInfo({
    required this.avatar,
    required this.follower,
    required this.isFollow,
    required this.mid,
    required this.uname,
    required this.verifyType,
    required this.vipStatus,
  });

  factory UpInfo.fromJson(Map<String, dynamic> json) {
    return UpInfo(
      avatar: json['avatar'] as String? ?? 'https://i1.hdslb.com/bfs/face/040528a1ec634b9907ba3a9ba957819bb07def06.jpg',
      follower: json['follower'] as int? ?? 0,
      isFollow: json['is_follow'] as int? ?? 0,
      mid: json['mid'] as int? ?? 928123,
      uname: json['uname'] as String? ?? '哔哩哔哩番剧',
      verifyType: json['verify_type'] as int? ?? 0,
      vipStatus: json['vip_status'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'avatar': avatar,
      'follower': follower,
      'is_follow': isFollow,
      'mid': mid,
      'uname': uname,
      'verify_type': verifyType,
      'vip_status': vipStatus,
    };
  }
}
