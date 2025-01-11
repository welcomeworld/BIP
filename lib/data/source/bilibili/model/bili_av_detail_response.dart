class BiliAvDetailResponse {
  final int code;
  final String message;
  final int ttl;
  final VideoPageData data;

  BiliAvDetailResponse({
    required this.code,
    required this.message,
    required this.ttl,
    required this.data,
  });

  factory BiliAvDetailResponse.fromJson(Map<String, dynamic> json) {
    return BiliAvDetailResponse(
      code: json['code'],
      message: json['message'] ?? '',
      ttl: json['ttl'] ?? 1,
      data: json['data'] != null
          ? VideoPageData.fromJson(json['data'])
          : VideoPageData.empty(),
    );
  }

  Map<String, dynamic> toJson() => {
        'code': code,
        'message': message,
        'ttl': ttl,
        'data': data.toJson(),
      };
}

class VideoPageData {
  final List<TagInfo> tags;
  final VideoData view;
  final List<VideoData> related;

  VideoPageData({
    required this.tags,
    required this.view,
    required this.related,
  });

  factory VideoPageData.fromJson(Map<String, dynamic> json) {
    return VideoPageData(
      tags: (json['Tags'] as List?)?.map((e) => TagInfo.fromJson(e)).toList() ??
          [],
      view: json['View'] != null
          ? VideoData.fromJson(json['View'])
          : VideoData.empty(),
      related: (json['Related'] as List?)
              ?.map((e) => VideoData.fromJson(e))
              .toList() ??
          [],
    );
  }

  Map<String, dynamic> toJson() => {
        'Tags': tags.map((e) => e.toJson()).toList(),
        'View': view.toJson(),
        'Related': related.map((e) => e.toJson()).toList(),
      };

  static VideoPageData empty() {
    return VideoPageData(
        tags: List.empty(), view: VideoData.empty(), related: List.empty());
  }
}

class TagInfo {
  final int tagId;
  final String tagName;
  final String cover;
  final String headCover;
  final String content;
  final String shortContent;
  final int type;
  final int state;
  final int ctime;
  final Count count;
  final int isAtten;
  final int likes;
  final int hates;
  final int attribute;
  final int liked;
  final int hated;
  final int extraAttr;
  final String musicId;
  final String tagType;
  final bool isActivity;
  final String color;
  final int alpha;
  final bool isSeason;
  final int subscribedCount;
  final String archiveCount;
  final int featuredCount;
  final String jumpUrl;

  // Private constructor
  TagInfo._({
    required this.tagId,
    required this.tagName,
    required this.cover,
    required this.headCover,
    required this.content,
    required this.shortContent,
    required this.type,
    required this.state,
    required this.ctime,
    required this.count,
    required this.isAtten,
    required this.likes,
    required this.hates,
    required this.attribute,
    required this.liked,
    required this.hated,
    required this.extraAttr,
    required this.musicId,
    required this.tagType,
    required this.isActivity,
    required this.color,
    required this.alpha,
    required this.isSeason,
    required this.subscribedCount,
    required this.archiveCount,
    required this.featuredCount,
    required this.jumpUrl,
  });

  // Factory constructor from JSON
  factory TagInfo.fromJson(Map<String, dynamic> json) {
    return TagInfo._(
      tagId: json['tag_id'] ?? 0,
      tagName: json['tag_name'] ?? "",
      cover: json['cover'] ?? "",
      headCover: json['head_cover'] ?? "",
      content: json['content'] ?? "",
      shortContent: json['short_content'] ?? "",
      type: json['type'] ?? 0,
      state: json['state'] ?? 0,
      ctime: json['ctime'] ?? 0,
      count: Count.fromJson(json['count'] ?? {}),
      isAtten: json['is_atten'] ?? 0,
      likes: json['likes'] ?? 0,
      hates: json['hates'] ?? 0,
      attribute: json['attribute'] ?? 0,
      liked: json['liked'] ?? 0,
      hated: json['hated'] ?? 0,
      extraAttr: json['extra_attr'] ?? 0,
      musicId: json['music_id'] ?? "",
      tagType: json['tag_type'] ?? "",
      isActivity: json['is_activity'] ?? false,
      color: json['color'] ?? "",
      alpha: json['alpha'] ?? 0,
      isSeason: json['is_season'] ?? false,
      subscribedCount: json['subscribed_count'] ?? 0,
      archiveCount: json['archive_count'] ?? "",
      featuredCount: json['featured_count'] ?? 0,
      jumpUrl: json['jump_url'] ?? "",
    );
  }

  // Method to convert the object to a JSON map
  Map<String, dynamic> toJson() {
    return {
      'tag_id': tagId,
      'tag_name': tagName,
      'cover': cover,
      'head_cover': headCover,
      'content': content,
      'short_content': shortContent,
      'type': type,
      'state': state,
      'ctime': ctime,
      'count': count.toJson(),
      'is_atten': isAtten,
      'likes': likes,
      'hates': hates,
      'attribute': attribute,
      'liked': liked,
      'hated': hated,
      'extra_attr': extraAttr,
      'music_id': musicId,
      'tag_type': tagType,
      'is_activity': isActivity,
      'color': color,
      'alpha': alpha,
      'is_season': isSeason,
      'subscribed_count': subscribedCount,
      'archive_count': archiveCount,
      'featured_count': featuredCount,
      'jump_url': jumpUrl,
    };
  }
}

class Count {
  final int view;
  final int use;
  final int atten;

  // Private constructor
  Count._({
    required this.view,
    required this.use,
    required this.atten,
  });

  // Factory constructor from JSON
  factory Count.fromJson(Map<String, dynamic> json) {
    return Count._(
      view: json['view'] ?? 0,
      use: json['use'] ?? 0,
      atten: json['atten'] ?? 0,
    );
  }

  // Method to convert the object to a JSON map
  Map<String, dynamic> toJson() {
    return {
      'view': view,
      'use': use,
      'atten': atten,
    };
  }
}

class RelatedData {}

class VideoData {
  final String bvid;
  final int aid;
  final int videos;
  final int tid;
  final int tidV2;
  final String tname;
  final String tnameV2;
  final int copyright;
  final String pic;
  final String title;
  final int pubdate;
  final int ctime;
  final String desc;
  final List<DescV2> descV2;
  final int state;
  final int duration;
  final int missionId;
  final Rights rights;
  final Owner owner;
  final Stat stat;
  final ArgueInfo argueInfo;
  final int cid; // 新增字段
  final Dimension dimension; // 新增字段
  final bool? premiere; // 新增字段
  final int teenageMode; // 新增字段
  final bool isChargeableSeason; // 新增字段
  final bool isStory; // 新增字段
  final bool isUpowerExclusive; // 新增字段
  final bool isUpowerPlay; // 新增字段
  final bool isUpowerPreview; // 新增字段
  final int enableVt; // 新增字段
  final String vtDisplay; // 新增字段
  final bool noCache; // 新增字段
  final List<Page> pages; // 新增字段
  final Subtitle subtitle; // 新增字段
  final List<Staff> staff; // 新增字段
  final bool isSeasonDisplay; // 新增字段
  final UserGarb userGarb; // 新增字段
  final HonorReply honorReply; // 新增字段
  final String likeIcon; // 新增字段
  final bool needJumpBv; // 新增字段
  final bool disableShowUpInfo; // 新增字段
  final int isStoryPlay; // 新增字段
  final bool isViewSelf; // 新增字段

  VideoData({
    required this.bvid,
    required this.aid,
    required this.videos,
    required this.tid,
    required this.tidV2,
    required this.tname,
    required this.tnameV2,
    required this.copyright,
    required this.pic,
    required this.title,
    required this.pubdate,
    required this.ctime,
    required this.desc,
    required this.descV2,
    required this.state,
    required this.duration,
    required this.missionId,
    required this.rights,
    required this.owner,
    required this.stat,
    required this.argueInfo,
    required this.cid,
    required this.dimension,
    required this.premiere,
    required this.teenageMode,
    required this.isChargeableSeason,
    required this.isStory,
    required this.isUpowerExclusive,
    required this.isUpowerPlay,
    required this.isUpowerPreview,
    required this.enableVt,
    required this.vtDisplay,
    required this.noCache,
    required this.pages,
    required this.subtitle,
    required this.staff,
    required this.isSeasonDisplay,
    required this.userGarb,
    required this.honorReply,
    required this.likeIcon,
    required this.needJumpBv,
    required this.disableShowUpInfo,
    required this.isStoryPlay,
    required this.isViewSelf,
  });

  factory VideoData.fromJson(Map<String, dynamic> json) {
    return VideoData(
      bvid: json['bvid'] ?? '',
      aid: json['aid'] ?? 0,
      videos: json['videos'] ?? 0,
      tid: json['tid'] ?? 0,
      tidV2: json['tid_v2'] ?? 0,
      tname: json['tname'] ?? '',
      tnameV2: json['tname_v2'] ?? '',
      copyright: json['copyright'] ?? 0,
      pic: json['pic'] ?? '',
      title: json['title'] ?? '',
      pubdate: json['pubdate'] ?? 0,
      ctime: json['ctime'] ?? 0,
      desc: json['desc'] ?? '',
      descV2:
          (json['desc_v2'] as List?)?.map((e) => DescV2.fromJson(e)).toList() ??
              [],
      state: json['state'] ?? 0,
      duration: json['duration'] ?? 0,
      missionId: json['mission_id'] ?? 0,
      rights: json['rights'] != null
          ? Rights.fromJson(json['rights'])
          : Rights.empty(),
      owner:
          json['owner'] != null ? Owner.fromJson(json['owner']) : Owner.empty(),
      stat: json['stat'] != null ? Stat.fromJson(json['stat']) : Stat.empty(),
      argueInfo: json['argue_info'] != null
          ? ArgueInfo.fromJson(json['argue_info'])
          : ArgueInfo.empty(),
      cid: json['cid'] ?? 0,
      dimension: json['dimension'] != null
          ? Dimension.fromJson(json['dimension'])
          : Dimension.empty(),
      premiere: json['premiere'],
      teenageMode: json['teenage_mode'] ?? 0,
      isChargeableSeason: json['is_chargeable_season'] ?? false,
      isStory: json['is_story'] ?? false,
      isUpowerExclusive: json['is_upower_exclusive'] ?? false,
      isUpowerPlay: json['is_upower_play'] ?? false,
      isUpowerPreview: json['is_upower_preview'] ?? false,
      enableVt: json['enable_vt'] ?? 0,
      vtDisplay: json['vt_display'] ?? '',
      noCache: json['no_cache'] ?? false,
      pages:
          (json['pages'] as List?)?.map((e) => Page.fromJson(e)).toList() ?? [],
      subtitle: json['subtitle'] != null
          ? Subtitle.fromJson(json['subtitle'])
          : Subtitle.empty(),
      staff: (json['staff'] as List?)?.map((e) => Staff.fromJson(e)).toList() ??
          [],
      isSeasonDisplay: json['is_season_display'] ?? false,
      userGarb: json['user_garb'] != null
          ? UserGarb.fromJson(json['user_garb'])
          : UserGarb.empty(),
      honorReply: json['honor_reply'] != null
          ? HonorReply.fromJson(json['honor_reply'])
          : HonorReply.empty(),
      likeIcon: json['like_icon'] ?? '',
      needJumpBv: json['need_jump_bv'] ?? false,
      disableShowUpInfo: json['disable_show_up_info'] ?? false,
      isStoryPlay: json['is_story_play'] ?? 0,
      isViewSelf: json['is_view_self'] ?? false,
    );
  }

  Map<String, dynamic> toJson() => {
        'bvid': bvid,
        'aid': aid,
        'videos': videos,
        'tid': tid,
        'tid_v2': tidV2,
        'tname': tname,
        'tname_v2': tnameV2,
        'copyright': copyright,
        'pic': pic,
        'title': title,
        'pubdate': pubdate,
        'ctime': ctime,
        'desc': desc,
        'desc_v2': descV2.map((e) => e.toJson()).toList(),
        'state': state,
        'duration': duration,
        'mission_id': missionId,
        'rights': rights.toJson(),
        'owner': owner.toJson(),
        'stat': stat.toJson(),
        'argue_info': argueInfo.toJson(),
        'cid': cid,
        'dimension': dimension.toJson(),
        'premiere': premiere,
        'teenage_mode': teenageMode,
        'is_chargeable_season': isChargeableSeason,
        'is_story': isStory,
        'is_upower_exclusive': isUpowerExclusive,
        'is_upower_play': isUpowerPlay,
        'is_upower_preview': isUpowerPreview,
        'enable_vt': enableVt,
        'vt_display': vtDisplay,
        'no_cache': noCache,
        'pages': pages.map((e) => e.toJson()).toList(),
        'subtitle': subtitle.toJson(),
        'staff': staff.map((e) => e.toJson()).toList(),
        'is_season_display': isSeasonDisplay,
        'user_garb': userGarb.toJson(),
        'honor_reply': honorReply.toJson(),
        'like_icon': likeIcon,
        'need_jump_bv': needJumpBv,
        'disable_show_up_info': disableShowUpInfo,
        'is_story_play': isStoryPlay,
        'is_view_self': isViewSelf,
      };

  static VideoData empty() {
    return VideoData(
      bvid: '',
      aid: 0,
      videos: 0,
      tid: 0,
      tidV2: 0,
      tname: '',
      tnameV2: '',
      copyright: 0,
      pic: '',
      title: '',
      pubdate: 0,
      ctime: 0,
      desc: '',
      descV2: [],
      state: 0,
      duration: 0,
      missionId: 0,
      rights: Rights.empty(),
      owner: Owner.empty(),
      stat: Stat.empty(),
      argueInfo: ArgueInfo.empty(),
      cid: 0,
      dimension: Dimension.empty(),
      premiere: null,
      teenageMode: 0,
      isChargeableSeason: false,
      isStory: false,
      isUpowerExclusive: false,
      isUpowerPlay: false,
      isUpowerPreview: false,
      enableVt: 0,
      vtDisplay: '',
      noCache: false,
      pages: [],
      subtitle: Subtitle.empty(),
      staff: [],
      isSeasonDisplay: false,
      userGarb: UserGarb.empty(),
      honorReply: HonorReply.empty(),
      likeIcon: '',
      needJumpBv: false,
      disableShowUpInfo: false,
      isStoryPlay: 0,
      isViewSelf: false,
    );
  }
}

class DescV2 {
  final String rawText;
  final int type;
  final int bizId;

  DescV2({
    required this.rawText,
    required this.type,
    required this.bizId,
  });

  factory DescV2.fromJson(Map<String, dynamic> json) {
    return DescV2(
      rawText: json['raw_text'] ?? '',
      type: json['type'] ?? 0,
      bizId: json['biz_id'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() => {
        'raw_text': rawText,
        'type': type,
        'biz_id': bizId,
      };
}

class Rights {
  final int bp;
  final int elec;
  final int download;
  final int movie;
  final int pay;
  final int hd5;
  final int noReprint;
  final int autoplay;
  final int ugcPay;
  final int isCooperation;
  final int ugcPayPreview;
  final int noBackground;
  final int cleanMode;
  final int isSteinGate;
  final int is360;
  final int noShare;
  final int arcPay;
  final int freeWatch;

  Rights({
    required this.bp,
    required this.elec,
    required this.download,
    required this.movie,
    required this.pay,
    required this.hd5,
    required this.noReprint,
    required this.autoplay,
    required this.ugcPay,
    required this.isCooperation,
    required this.ugcPayPreview,
    required this.noBackground,
    required this.cleanMode,
    required this.isSteinGate,
    required this.is360,
    required this.noShare,
    required this.arcPay,
    required this.freeWatch,
  });

  factory Rights.fromJson(Map<String, dynamic> json) {
    return Rights(
      bp: json['bp'] ?? 0,
      elec: json['elec'] ?? 0,
      download: json['download'] ?? 0,
      movie: json['movie'] ?? 0,
      pay: json['pay'] ?? 0,
      hd5: json['hd5'] ?? 0,
      noReprint: json['no_reprint'] ?? 0,
      autoplay: json['autoplay'] ?? 0,
      ugcPay: json['ugc_pay'] ?? 0,
      isCooperation: json['is_cooperation'] ?? 0,
      ugcPayPreview: json['ugc_pay_preview'] ?? 0,
      noBackground: json['no_background'] ?? 0,
      cleanMode: json['clean_mode'] ?? 0,
      isSteinGate: json['is_stein_gate'] ?? 0,
      is360: json['is_360'] ?? 0,
      noShare: json['no_share'] ?? 0,
      arcPay: json['arc_pay'] ?? 0,
      freeWatch: json['free_watch'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() => {
        'bp': bp,
        'elec': elec,
        'download': download,
        'movie': movie,
        'pay': pay,
        'hd5': hd5,
        'no_reprint': noReprint,
        'autoplay': autoplay,
        'ugc_pay': ugcPay,
        'is_cooperation': isCooperation,
        'ugc_pay_preview': ugcPayPreview,
        'no_background': noBackground,
        'clean_mode': cleanMode,
        'is_stein_gate': isSteinGate,
        'is_360': is360,
        'no_share': noShare,
        'arc_pay': arcPay,
        'free_watch': freeWatch,
      };

  static Rights empty() {
    return Rights(
      bp: 0,
      elec: 0,
      download: 0,
      movie: 0,
      pay: 0,
      hd5: 0,
      noReprint: 0,
      autoplay: 0,
      ugcPay: 0,
      isCooperation: 0,
      ugcPayPreview: 0,
      noBackground: 0,
      cleanMode: 0,
      isSteinGate: 0,
      is360: 0,
      noShare: 0,
      arcPay: 0,
      freeWatch: 0,
    );
  }
}

class Owner {
  final int mid;
  final String name;
  final String face;

  Owner({
    required this.mid,
    required this.name,
    required this.face,
  });

  factory Owner.fromJson(Map<String, dynamic> json) {
    return Owner(
      mid: json['mid'] ?? 0,
      name: json['name'] ?? '',
      face: json['face'] ?? '',
    );
  }

  Map<String, dynamic> toJson() => {
        'mid': mid,
        'name': name,
        'face': face,
      };

  static Owner empty() {
    return Owner(mid: 0, name: '', face: '');
  }
}

class Stat {
  final int aid;
  final int view;
  final int danmaku;
  final int reply;
  final int favorite;
  final int coin;
  final int share;
  final int nowRank;
  final int hisRank;
  final int like;
  final int dislike;
  final String evaluation;
  final int vt;

  Stat({
    required this.aid,
    required this.view,
    required this.danmaku,
    required this.reply,
    required this.favorite,
    required this.coin,
    required this.share,
    required this.nowRank,
    required this.hisRank,
    required this.like,
    required this.dislike,
    required this.evaluation,
    required this.vt,
  });

  factory Stat.fromJson(Map<String, dynamic> json) {
    return Stat(
      aid: json['aid'] ?? 0,
      view: json['view'] ?? 0,
      danmaku: json['danmaku'] ?? 0,
      reply: json['reply'] ?? 0,
      favorite: json['favorite'] ?? 0,
      coin: json['coin'] ?? 0,
      share: json['share'] ?? 0,
      nowRank: json['now_rank'] ?? 0,
      hisRank: json['his_rank'] ?? 0,
      like: json['like'] ?? 0,
      dislike: json['dislike'] ?? 0,
      evaluation: json['evaluation'] ?? '',
      vt: json['vt'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() => {
        'aid': aid,
        'view': view,
        'danmaku': danmaku,
        'reply': reply,
        'favorite': favorite,
        'coin': coin,
        'share': share,
        'now_rank': nowRank,
        'his_rank': hisRank,
        'like': like,
        'dislike': dislike,
        'evaluation': evaluation,
        'vt': vt,
      };

  static Stat empty() {
    return Stat(
      aid: 0,
      view: 0,
      danmaku: 0,
      reply: 0,
      favorite: 0,
      coin: 0,
      share: 0,
      nowRank: 0,
      hisRank: 0,
      like: 0,
      dislike: 0,
      evaluation: '',
      vt: 0,
    );
  }
}

class ArgueInfo {
  final String argueMsg;
  final int argueType;
  final String argueLink;

  ArgueInfo({
    required this.argueMsg,
    required this.argueType,
    required this.argueLink,
  });

  factory ArgueInfo.fromJson(Map<String, dynamic> json) {
    return ArgueInfo(
      argueMsg: json['argue_msg'] ?? '',
      argueType: json['argue_type'] ?? 0,
      argueLink: json['argue_link'] ?? '',
    );
  }

  Map<String, dynamic> toJson() => {
        'argue_msg': argueMsg,
        'argue_type': argueType,
        'argue_link': argueLink,
      };

  static ArgueInfo empty() {
    return ArgueInfo(argueMsg: '', argueType: 0, argueLink: '');
  }
}

class Dimension {
  final int width;
  final int height;
  final int rotate;

  Dimension({
    required this.width,
    required this.height,
    required this.rotate,
  });

  factory Dimension.fromJson(Map<String, dynamic> json) {
    return Dimension(
      width: json['width'] ?? 0,
      height: json['height'] ?? 0,
      rotate: json['rotate'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() => {
        'width': width,
        'height': height,
        'rotate': rotate,
      };

  static Dimension empty() {
    return Dimension(width: 0, height: 0, rotate: 0);
  }
}

class Page {
  final int cid;
  final int page;
  final String from;
  final String part;
  final int duration;
  final String vid;
  final String weblink;
  final Dimension dimension;

  Page({
    required this.cid,
    required this.page,
    required this.from,
    required this.part,
    required this.duration,
    required this.vid,
    required this.weblink,
    required this.dimension,
  });

  factory Page.fromJson(Map<String, dynamic> json) {
    return Page(
      cid: json['cid'] ?? 0,
      page: json['page'] ?? 0,
      from: json['from'] ?? '',
      part: json['part'] ?? '',
      duration: json['duration'] ?? 0,
      vid: json['vid'] ?? '',
      weblink: json['weblink'] ?? '',
      dimension: json['dimension'] != null
          ? Dimension.fromJson(json['dimension'])
          : Dimension.empty(),
    );
  }

  Map<String, dynamic> toJson() => {
        'cid': cid,
        'page': page,
        'from': from,
        'part': part,
        'duration': duration,
        'vid': vid,
        'weblink': weblink,
        'dimension': dimension.toJson(),
      };
}

class Subtitle {
  final bool allowSubmit;
  final List<dynamic> list;

  Subtitle({
    required this.allowSubmit,
    required this.list,
  });

  factory Subtitle.fromJson(Map<String, dynamic> json) {
    return Subtitle(
      allowSubmit: json['allow_submit'] ?? false,
      list: json['list'] ?? [],
    );
  }

  Map<String, dynamic> toJson() => {
        'allow_submit': allowSubmit,
        'list': list,
      };

  static Subtitle empty() {
    return Subtitle(allowSubmit: false, list: []);
  }
}

class Staff {
  final int mid;
  final String title;
  final String name;
  final String face;
  final Vip vip;
  final Official official;
  final int follower;
  final int labelStyle;

  Staff({
    required this.mid,
    required this.title,
    required this.name,
    required this.face,
    required this.vip,
    required this.official,
    required this.follower,
    required this.labelStyle,
  });

  factory Staff.fromJson(Map<String, dynamic> json) {
    return Staff(
      mid: json['mid'] ?? 0,
      title: json['title'] ?? '',
      name: json['name'] ?? '',
      face: json['face'] ?? '',
      vip: json['vip'] != null ? Vip.fromJson(json['vip']) : Vip.empty(),
      official: json['official'] != null
          ? Official.fromJson(json['official'])
          : Official.empty(),
      follower: json['follower'] ?? 0,
      labelStyle: json['label_style'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() => {
        'mid': mid,
        'title': title,
        'name': name,
        'face': face,
        'vip': vip.toJson(),
        'official': official.toJson(),
        'follower': follower,
        'label_style': labelStyle,
      };
}

class Vip {
  final int type;
  final int status;
  final int dueDate;
  final int vipPayType;
  final int themeType;
  final Label label;
  final int avatarSubscript;
  final String nicknameColor;
  final int role;
  final String avatarSubscriptUrl;
  final int tvVipStatus;
  final int tvVipPayType;
  final int tvDueDate;
  final AvatarIcon avatarIcon;

  Vip({
    required this.type,
    required this.status,
    required this.dueDate,
    required this.vipPayType,
    required this.themeType,
    required this.label,
    required this.avatarSubscript,
    required this.nicknameColor,
    required this.role,
    required this.avatarSubscriptUrl,
    required this.tvVipStatus,
    required this.tvVipPayType,
    required this.tvDueDate,
    required this.avatarIcon,
  });

  factory Vip.fromJson(Map<String, dynamic> json) {
    return Vip(
      type: json['type'] ?? 0,
      status: json['status'] ?? 0,
      dueDate: json['due_date'] ?? 0,
      vipPayType: json['vip_pay_type'] ?? 0,
      themeType: json['theme_type'] ?? 0,
      label:
          json['label'] != null ? Label.fromJson(json['label']) : Label.empty(),
      avatarSubscript: json['avatar_subscript'] ?? 0,
      nicknameColor: json['nickname_color'] ?? '',
      role: json['role'] ?? 0,
      avatarSubscriptUrl: json['avatar_subscript_url'] ?? '',
      tvVipStatus: json['tv_vip_status'] ?? 0,
      tvVipPayType: json['tv_vip_pay_type'] ?? 0,
      tvDueDate: json['tv_due_date'] ?? 0,
      avatarIcon: json['avatar_icon'] != null
          ? AvatarIcon.fromJson(json['avatar_icon'])
          : AvatarIcon.empty(),
    );
  }

  Map<String, dynamic> toJson() => {
        'type': type,
        'status': status,
        'due_date': dueDate,
        'vip_pay_type': vipPayType,
        'theme_type': themeType,
        'label': label.toJson(),
        'avatar_subscript': avatarSubscript,
        'nickname_color': nicknameColor,
        'role': role,
        'avatar_subscript_url': avatarSubscriptUrl,
        'tv_vip_status': tvVipStatus,
        'tv_vip_pay_type': tvVipPayType,
        'tv_due_date': tvDueDate,
        'avatar_icon': avatarIcon.toJson(),
      };

  static Vip empty() {
    return Vip(
      type: 0,
      status: 0,
      dueDate: 0,
      vipPayType: 0,
      themeType: 0,
      label: Label.empty(),
      avatarSubscript: 0,
      nicknameColor: '',
      role: 0,
      avatarSubscriptUrl: '',
      tvVipStatus: 0,
      tvVipPayType: 0,
      tvDueDate: 0,
      avatarIcon: AvatarIcon.empty(),
    );
  }
}

class Label {
  final String path;
  final String text;
  final String labelTheme;
  final String textColor;
  final int bgStyle;
  final String bgColor;
  final String borderColor;
  final bool useImgLabel;
  final String imgLabelUriHans;
  final String imgLabelUriHant;
  final String imgLabelUriHansStatic;
  final String imgLabelUriHantStatic;

  Label({
    required this.path,
    required this.text,
    required this.labelTheme,
    required this.textColor,
    required this.bgStyle,
    required this.bgColor,
    required this.borderColor,
    required this.useImgLabel,
    required this.imgLabelUriHans,
    required this.imgLabelUriHant,
    required this.imgLabelUriHansStatic,
    required this.imgLabelUriHantStatic,
  });

  factory Label.fromJson(Map<String, dynamic> json) {
    return Label(
      path: json['path'] ?? '',
      text: json['text'] ?? '',
      labelTheme: json['label_theme'] ?? '',
      textColor: json['text_color'] ?? '',
      bgStyle: json['bg_style'] ?? 0,
      bgColor: json['bg_color'] ?? '',
      borderColor: json['border_color'] ?? '',
      useImgLabel: json['use_img_label'] ?? false,
      imgLabelUriHans: json['img_label_uri_hans'] ?? '',
      imgLabelUriHant: json['img_label_uri_hant'] ?? '',
      imgLabelUriHansStatic: json['img_label_uri_hans_static'] ?? '',
      imgLabelUriHantStatic: json['img_label_uri_hant_static'] ?? '',
    );
  }

  Map<String, dynamic> toJson() => {
        'path': path,
        'text': text,
        'label_theme': labelTheme,
        'text_color': textColor,
        'bg_style': bgStyle,
        'bg_color': bgColor,
        'border_color': borderColor,
        'use_img_label': useImgLabel,
        'img_label_uri_hans': imgLabelUriHans,
        'img_label_uri_hant': imgLabelUriHant,
        'img_label_uri_hans_static': imgLabelUriHansStatic,
        'img_label_uri_hant_static': imgLabelUriHantStatic,
      };

  static Label empty() {
    return Label(
      path: '',
      text: '',
      labelTheme: '',
      textColor: '',
      bgStyle: 0,
      bgColor: '',
      borderColor: '',
      useImgLabel: false,
      imgLabelUriHans: '',
      imgLabelUriHant: '',
      imgLabelUriHansStatic: '',
      imgLabelUriHantStatic: '',
    );
  }
}

class AvatarIcon {
  final int iconType;
  final IconResource iconResource;

  AvatarIcon({
    required this.iconType,
    required this.iconResource,
  });

  factory AvatarIcon.fromJson(Map<String, dynamic> json) {
    return AvatarIcon(
      iconType: json['icon_type'] ?? 0,
      iconResource: json['icon_resource'] != null
          ? IconResource.fromJson(json['icon_resource'])
          : IconResource.empty(),
    );
  }

  Map<String, dynamic> toJson() => {
        'icon_type': iconType,
        'icon_resource': iconResource.toJson(),
      };

  static AvatarIcon empty() {
    return AvatarIcon(iconType: 0, iconResource: IconResource.empty());
  }
}

class IconResource {
  // 如果需要进一步细化 IconResource 类，请提供更多信息
  IconResource();

  factory IconResource.fromJson(Map<String, dynamic> json) {
    return IconResource();
  }

  Map<String, dynamic> toJson() => {};

  static IconResource empty() {
    return IconResource();
  }
}

class Official {
  final int role;
  final String title;
  final String desc;
  final int type;

  Official({
    required this.role,
    required this.title,
    required this.desc,
    required this.type,
  });

  factory Official.fromJson(Map<String, dynamic> json) {
    return Official(
      role: json['role'] ?? 0,
      title: json['title'] ?? '',
      desc: json['desc'] ?? '',
      type: json['type'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() => {
        'role': role,
        'title': title,
        'desc': desc,
        'type': type,
      };

  static Official empty() {
    return Official(role: 0, title: '', desc: '', type: 0);
  }
}

class UserGarb {
  final String urlImageAniCut;

  UserGarb({
    required this.urlImageAniCut,
  });

  factory UserGarb.fromJson(Map<String, dynamic> json) {
    return UserGarb(
      urlImageAniCut: json['url_image_ani_cut'] ?? '',
    );
  }

  Map<String, dynamic> toJson() => {
        'url_image_ani_cut': urlImageAniCut,
      };

  static UserGarb empty() {
    return UserGarb(urlImageAniCut: '');
  }
}

class HonorReply {
  final List<Honor> honor;

  HonorReply({
    required this.honor,
  });

  factory HonorReply.fromJson(Map<String, dynamic> json) {
    return HonorReply(
      honor: (json['honor'] as List?)?.map((e) => Honor.fromJson(e)).toList() ??
          [],
    );
  }

  Map<String, dynamic> toJson() => {
        'honor': honor.map((e) => e.toJson()).toList(),
      };

  static HonorReply empty() {
    return HonorReply(honor: []);
  }
}

class Honor {
  final int aid;
  final int type;
  final String desc;
  final int weeklyRecommendNum;

  Honor({
    required this.aid,
    required this.type,
    required this.desc,
    required this.weeklyRecommendNum,
  });

  factory Honor.fromJson(Map<String, dynamic> json) {
    return Honor(
      aid: json['aid'] ?? 0,
      type: json['type'] ?? 0,
      desc: json['desc'] ?? '',
      weeklyRecommendNum: json['weekly_recommend_num'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() => {
        'aid': aid,
        'type': type,
        'desc': desc,
        'weekly_recommend_num': weeklyRecommendNum,
      };
}
