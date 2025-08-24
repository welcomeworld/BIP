// Top-level response class
class BiliReplyResponse {
  final int code;
  final String message;
  final int ttl;
  final BiliReplyData data;

  const BiliReplyResponse({
    required this.code,
    required this.message,
    required this.ttl,
    required this.data,
  });

  factory BiliReplyResponse.fromJson(Map<String, dynamic> json) {
    return BiliReplyResponse(
      code: json['code'] as int? ?? 0,
      message: json['message'] as String? ?? '',
      ttl: json['ttl'] as int? ?? 0,
      data: BiliReplyData.fromJson(json['data'] as Map<String, dynamic>? ?? {}),
    );
  }
}

// Data class containing page, replies, and other fields
class BiliReplyData {
  final Cursor cursor;
  final List<ReplyResponse> replies;
  final List<ReplyResponse> hots;
  final List<ReplyResponse> topReplies;
  final Upper upper;
  final dynamic top;
  final dynamic notice;
  final int vote;
  final int blacklist;
  final int assist;
  final int mode;
  final List<int> supportMode;
  final dynamic lotteryCard;
  final bool showBvid;
  final Control control;

  const BiliReplyData({
    required this.cursor,
    required this.replies,
    required this.hots,
    required this.topReplies,
    required this.upper,
    this.top,
    this.notice,
    required this.vote,
    required this.blacklist,
    required this.assist,
    required this.mode,
    required this.supportMode,
    this.lotteryCard,
    required this.showBvid,
    required this.control,
  });

  factory BiliReplyData.fromJson(Map<String, dynamic> json) {
    return BiliReplyData(
      cursor: Cursor.fromJson(json['cursor'] as Map<String, dynamic>? ?? {}),
      replies: (json['replies'] as List<dynamic>?)
              ?.map((e) => ReplyResponse.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      hots: (json['hots'] as List<dynamic>?)
              ?.map((e) => ReplyResponse.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      topReplies: (json['top_replies'] as List<dynamic>?)
              ?.map((e) => ReplyResponse.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      // Nullable
      upper: Upper.fromJson(json['upper'] as Map<String, dynamic>? ?? {}),
      top: json['top'],
      // Nullable
      notice: json['notice'],
      // Nullable
      vote: json['vote'] as int? ?? 0,
      blacklist: json['blacklist'] as int? ?? 0,
      assist: json['assist'] as int? ?? 0,
      mode: json['mode'] as int? ?? 0,
      supportMode: (json['support_mode'] as List<dynamic>?)
              ?.map((e) => e as int)
              .toList() ??
          [],
      lotteryCard: json['lottery_card'],
      // Nullable
      showBvid: json['show_bvid'] as bool? ?? false,
      control: Control.fromJson(json['control'] as Map<String, dynamic>? ?? {}),
    );
  }
}

// Page class
class Cursor {
  final int allCount;
  final bool isEnd;
  final String nextOffset;

  const Cursor({
    required this.allCount,
    required this.isEnd,
    required this.nextOffset,
  });

  factory Cursor.fromJson(Map<String, dynamic> json) {
    return Cursor(
      allCount: json['all_count'] as int? ?? 0,
      isEnd: json['is_end'] as bool? ?? false,
      nextOffset:
          (json['pagination_reply'] as Map<String, dynamic>?)?['next_offset'] ??
              "",
    );
  }
}

// Reply class
class ReplyResponse {
  final int rpid;
  final int oid;
  final int type;
  final int mid;
  final int root;
  final int parent;
  final int dialog;
  final int count;
  final int rcount;
  final int state;
  final int fansgrade;
  final int attr;
  final int ctime;
  final String rpidStr;
  final String rootStr;
  final String parentStr;
  final int like;
  final int action;
  final Member member;
  final Content content;
  final List<ReplyResponse> replies;
  final int assist;
  final UpAction upAction;
  final bool showFollow;
  final bool invisible;
  final ReplyControl replyControl;

  const ReplyResponse({
    required this.rpid,
    required this.oid,
    required this.type,
    required this.mid,
    required this.root,
    required this.parent,
    required this.dialog,
    required this.count,
    required this.rcount,
    required this.state,
    required this.fansgrade,
    required this.attr,
    required this.ctime,
    required this.rpidStr,
    required this.rootStr,
    required this.parentStr,
    required this.like,
    required this.action,
    required this.member,
    required this.content,
    required this.replies,
    required this.assist,
    required this.upAction,
    required this.showFollow,
    required this.invisible,
    required this.replyControl,
  });

  factory ReplyResponse.fromJson(Map<String, dynamic> json) {
    return ReplyResponse(
      rpid: json['rpid'] as int? ?? 0,
      oid: json['oid'] as int? ?? 0,
      type: json['type'] as int? ?? 0,
      mid: json['mid'] as int? ?? 0,
      root: json['root'] as int? ?? 0,
      parent: json['parent'] as int? ?? 0,
      dialog: json['dialog'] as int? ?? 0,
      count: json['count'] as int? ?? 0,
      rcount: json['rcount'] as int? ?? 0,
      state: json['state'] as int? ?? 0,
      fansgrade: json['fansgrade'] as int? ?? 0,
      attr: json['attr'] as int? ?? 0,
      ctime: json['ctime'] as int? ?? 0,
      rpidStr: json['rpid_str'] as String? ?? '',
      rootStr: json['root_str'] as String? ?? '',
      parentStr: json['parent_str'] as String? ?? '',
      like: json['like'] as int? ?? 0,
      action: json['action'] as int? ?? 0,
      member: Member.fromJson(json['member'] as Map<String, dynamic>? ?? {}),
      content: Content.fromJson(json['content'] as Map<String, dynamic>? ?? {}),
      replies: (json['replies'] as List<dynamic>?)
              ?.map((e) => ReplyResponse.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      assist: json['assist'] as int? ?? 0,
      upAction:
          UpAction.fromJson(json['up_action'] as Map<String, dynamic>? ?? {}),
      showFollow: json['show_follow'] as bool? ?? false,
      invisible: json['invisible'] as bool? ?? false,
      replyControl: ReplyControl.fromJson(
          json['reply_control'] as Map<String, dynamic>? ?? {}),
    );
  }
}

// Member class
class Member {
  final String mid;
  final String uname;
  final String sex;
  final String sign;
  final String avatar;
  final String rank;
  final String displayRank;
  final int faceNftNew;
  final int isSeniorMember;
  final LevelInfo levelInfo;
  final Pendant pendant;
  final Nameplate nameplate;
  final OfficialVerify officialVerify;
  final Vip vip;
  final dynamic fansDetail;
  final int following;
  final int isFollowed;
  final UserSailing userSailing;
  final bool isContractor;
  final String contractDesc;

  const Member({
    required this.mid,
    required this.uname,
    required this.sex,
    required this.sign,
    required this.avatar,
    required this.rank,
    required this.displayRank,
    required this.faceNftNew,
    required this.isSeniorMember,
    required this.levelInfo,
    required this.pendant,
    required this.nameplate,
    required this.officialVerify,
    required this.vip,
    this.fansDetail,
    required this.following,
    required this.isFollowed,
    required this.userSailing,
    required this.isContractor,
    required this.contractDesc,
  });

  factory Member.fromJson(Map<String, dynamic> json) {
    return Member(
      mid: json['mid'] as String? ?? '',
      uname: json['uname'] as String? ?? '',
      sex: json['sex'] as String? ?? '',
      sign: json['sign'] as String? ?? '',
      avatar: json['avatar'] as String? ?? '',
      rank: json['rank'] as String? ?? '',
      displayRank: json['DisplayRank'] as String? ?? '',
      faceNftNew: json['face_nft_new'] as int? ?? 0,
      isSeniorMember: json['is_senior_member'] as int? ?? 0,
      levelInfo:
          LevelInfo.fromJson(json['level_info'] as Map<String, dynamic>? ?? {}),
      pendant: Pendant.fromJson(json['pendant'] as Map<String, dynamic>? ?? {}),
      nameplate:
          Nameplate.fromJson(json['nameplate'] as Map<String, dynamic>? ?? {}),
      officialVerify: OfficialVerify.fromJson(
          json['official_verify'] as Map<String, dynamic>? ?? {}),
      vip: Vip.fromJson(json['vip'] as Map<String, dynamic>? ?? {}),
      fansDetail: json['fans_detail'],
      // Nullable
      following: json['following'] as int? ?? 0,
      isFollowed: json['is_followed'] as int? ?? 0,
      userSailing: UserSailing.fromJson(
          json['user_sailing'] as Map<String, dynamic>? ?? {}),
      isContractor: json['is_contractor'] as bool? ?? false,
      contractDesc: json['contract_desc'] as String? ?? '',
    );
  }
}

// LevelInfo class
class LevelInfo {
  final int currentLevel;
  final int currentMin;
  final int currentExp;
  final int nextExp;

  const LevelInfo({
    required this.currentLevel,
    required this.currentMin,
    required this.currentExp,
    required this.nextExp,
  });

  factory LevelInfo.fromJson(Map<String, dynamic> json) {
    return LevelInfo(
      currentLevel: json['current_level'] as int? ?? 0,
      currentMin: json['current_min'] as int? ?? 0,
      currentExp: json['current_exp'] as int? ?? 0,
      nextExp: json['next_exp'] as int? ?? 0,
    );
  }
}

// Pendant class
class Pendant {
  final int pid;
  final String name;
  final String image;
  final int expire;
  final String imageEnhance;
  final String imageEnhanceFrame;

  const Pendant({
    required this.pid,
    required this.name,
    required this.image,
    required this.expire,
    required this.imageEnhance,
    required this.imageEnhanceFrame,
  });

  factory Pendant.fromJson(Map<String, dynamic> json) {
    return Pendant(
      pid: json['pid'] as int? ?? 0,
      name: json['name'] as String? ?? '',
      image: json['image'] as String? ?? '',
      expire: json['expire'] as int? ?? 0,
      imageEnhance: json['image_enhance'] as String? ?? '',
      imageEnhanceFrame: json['image_enhance_frame'] as String? ?? '',
    );
  }
}

// Nameplate class
class Nameplate {
  final int nid;
  final String name;
  final String image;
  final String imageSmall;
  final String level;
  final String condition;

  const Nameplate({
    required this.nid,
    required this.name,
    required this.image,
    required this.imageSmall,
    required this.level,
    required this.condition,
  });

  factory Nameplate.fromJson(Map<String, dynamic> json) {
    return Nameplate(
      nid: json['nid'] as int? ?? 0,
      name: json['name'] as String? ?? '',
      image: json['image'] as String? ?? '',
      imageSmall: json['image_small'] as String? ?? '',
      level: json['level'] as String? ?? '',
      condition: json['condition'] as String? ?? '',
    );
  }
}

// OfficialVerify class
class OfficialVerify {
  final int type;
  final String desc;

  const OfficialVerify({
    required this.type,
    required this.desc,
  });

  factory OfficialVerify.fromJson(Map<String, dynamic> json) {
    return OfficialVerify(
      type: json['type'] as int? ?? -1,
      desc: json['desc'] as String? ?? '',
    );
  }
}

// Vip class
class Vip {
  final int vipType;
  final int vipDueDate;
  final String dueRemark;
  final int accessStatus;
  final int vipStatus;
  final String vipStatusWarn;
  final int themeType;
  final Label label;
  final int avatarSubscript;
  final String avatarSubscriptUrl;
  final String nicknameColor;

  const Vip({
    required this.vipType,
    required this.vipDueDate,
    required this.dueRemark,
    required this.accessStatus,
    required this.vipStatus,
    required this.vipStatusWarn,
    required this.themeType,
    required this.label,
    required this.avatarSubscript,
    required this.avatarSubscriptUrl,
    required this.nicknameColor,
  });

  factory Vip.fromJson(Map<String, dynamic> json) {
    return Vip(
      vipType: json['vipType'] as int? ?? 0,
      vipDueDate: json['vipDueDate'] as int? ?? 0,
      dueRemark: json['dueRemark'] as String? ?? '',
      accessStatus: json['accessStatus'] as int? ?? 0,
      vipStatus: json['vipStatus'] as int? ?? 0,
      vipStatusWarn: json['vipStatusWarn'] as String? ?? '',
      themeType: json['themeType'] as int? ?? 0,
      label: Label.fromJson(json['label'] as Map<String, dynamic>? ?? {}),
      avatarSubscript: json['avatar_subscript'] as int? ?? 0,
      avatarSubscriptUrl: json['avatar_subscript_url'] as String? ?? '',
      nicknameColor: json['nickname_color'] as String? ?? '',
    );
  }
}

// Label class
class Label {
  final String path;
  final String text;
  final String labelTheme;
  final String textColor;
  final int bgStyle;
  final String bgColor;
  final String borderColor;

  const Label({
    required this.path,
    required this.text,
    required this.labelTheme,
    required this.textColor,
    required this.bgStyle,
    required this.bgColor,
    required this.borderColor,
  });

  factory Label.fromJson(Map<String, dynamic> json) {
    return Label(
      path: json['path'] as String? ?? '',
      text: json['text'] as String? ?? '',
      labelTheme: json['label_theme'] as String? ?? '',
      textColor: json['text_color'] as String? ?? '',
      bgStyle: json['bg_style'] as int? ?? 0,
      bgColor: json['bg_color'] as String? ?? '',
      borderColor: json['border_color'] as String? ?? '',
    );
  }
}

// UserSailing class
class UserSailing {
  final dynamic pendant;
  final dynamic cardbg;
  final dynamic cardbgWithFocus;

  const UserSailing({
    this.pendant,
    this.cardbg,
    this.cardbgWithFocus,
  });

  factory UserSailing.fromJson(Map<String, dynamic> json) {
    return UserSailing(
      pendant: json['pendant'], // Nullable
      cardbg: json['cardbg'], // Nullable
      cardbgWithFocus: json['cardbg_with_focus'], // Nullable
    );
  }
}

// Content class
class Content {
  final String message;
  final int plat;
  final String device;
  final List<Member> members;
  final Map<String, Emote> emote;
  final Map<String, dynamic> jumpUrl;
  final int maxLine;
  final List<Picture> pictures;

  const Content({
    required this.message,
    required this.plat,
    required this.device,
    required this.members,
    required this.emote,
    required this.jumpUrl,
    required this.maxLine,
    required this.pictures,
  });

  factory Content.fromJson(Map<String, dynamic> json) {
    final emoteJson = json['emote'] as Map<String, dynamic>? ?? {};
    final emotes = emoteJson.map(
      (key, value) =>
          MapEntry(key, Emote.fromJson(value as Map<String, dynamic>)),
    );

    return Content(
      message: json['message'] as String? ?? '',
      plat: json['plat'] as int? ?? 0,
      device: json['device'] as String? ?? '',
      members: (json['members'] as List<dynamic>?)
              ?.map((member) => Member.fromJson(member))
              .toList() ??
          [],
      emote: emotes,
      jumpUrl: json['jump_url'] as Map<String, dynamic>? ?? {},
      maxLine: json['max_line'] as int? ?? 0,
      pictures: (json['pictures'] as List<dynamic>?)
              ?.map((e) => Picture.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
    );
  }
}

// Emote class
class Emote {
  final int id;
  final int packageId;
  final int state;
  final int type;
  final int attr;
  final String text;
  final String url;
  final Meta meta;
  final int mtime;
  final String jumpTitle;

  const Emote({
    required this.id,
    required this.packageId,
    required this.state,
    required this.type,
    required this.attr,
    required this.text,
    required this.url,
    required this.meta,
    required this.mtime,
    required this.jumpTitle,
  });

  factory Emote.fromJson(Map<String, dynamic> json) {
    return Emote(
      id: json['id'] as int? ?? 0,
      packageId: json['package_id'] as int? ?? 0,
      state: json['state'] as int? ?? 0,
      type: json['type'] as int? ?? 0,
      attr: json['attr'] as int? ?? 0,
      text: json['text'] as String? ?? '',
      url: json['url'] as String? ?? '',
      meta: Meta.fromJson(json['meta'] as Map<String, dynamic>? ?? {}),
      mtime: json['mtime'] as int? ?? 0,
      jumpTitle: json['jump_title'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'package_id': packageId,
      'state': state,
      'type': type,
      'attr': attr,
      'text': text,
      'url': url,
      'meta': meta.toJson(),
      'mtime': mtime,
      'jump_title': jumpTitle,
    };
  }
}

// Meta class
class Meta {
  final int size;

  const Meta({
    required this.size,
  });

  factory Meta.fromJson(Map<String, dynamic> json) {
    return Meta(
      size: json['size'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'size': size,
    };
  }
}

class Picture {
  final String imgSrc;
  final int imgWidth;
  final int imgHeight;

  const Picture({
    required this.imgSrc,
    required this.imgWidth,
    required this.imgHeight,
  });

  factory Picture.fromJson(Map<String, dynamic> json) {
    return Picture(
      imgSrc: json['img_src'] as String? ?? '',
      imgWidth: json['img_width'] as int? ?? 0,
      imgHeight: json['img_height'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'img_src': imgSrc,
      'img_width': imgWidth,
      'img_height': imgHeight,
    };
  }
}

// UpAction class
class UpAction {
  final bool like;
  final bool reply;

  const UpAction({
    required this.like,
    required this.reply,
  });

  factory UpAction.fromJson(Map<String, dynamic> json) {
    return UpAction(
      like: json['like'] as bool? ?? false,
      reply: json['reply'] as bool? ?? false,
    );
  }
}

// ReplyControl class
class ReplyControl {
  final String timeDesc;
  final String location;

  const ReplyControl({
    required this.timeDesc,
    required this.location,
  });

  factory ReplyControl.fromJson(Map<String, dynamic> json) {
    return ReplyControl(
      timeDesc: json['time_desc'] as String? ?? '',
      location: json['location'] as String? ?? '',
    );
  }
}

// Upper class
class Upper {
  final int mid;
  final dynamic top;
  final dynamic vote;

  const Upper({
    required this.mid,
    this.top,
    this.vote,
  });

  factory Upper.fromJson(Map<String, dynamic> json) {
    return Upper(
      mid: json['mid'] as int? ?? 0,
      top: json['top'], // Nullable
      vote: json['vote'], // Nullable
    );
  }
}

// Control class
class Control {
  final bool inputDisable;
  final String rootInputText;
  final String childInputText;
  final String giveupInputText;
  final String bgText;
  final bool webSelection;
  final String answerGuideText;
  final String answerGuideIconUrl;
  final String answerGuideIosUrl;
  final String answerGuideAndroidUrl;
  final int showType;
  final String showText;
  final bool disableJumpEmote;

  const Control({
    required this.inputDisable,
    required this.rootInputText,
    required this.childInputText,
    required this.giveupInputText,
    required this.bgText,
    required this.webSelection,
    required this.answerGuideText,
    required this.answerGuideIconUrl,
    required this.answerGuideIosUrl,
    required this.answerGuideAndroidUrl,
    required this.showType,
    required this.showText,
    required this.disableJumpEmote,
  });

  factory Control.fromJson(Map<String, dynamic> json) {
    return Control(
      inputDisable: json['input_disable'] as bool? ?? false,
      rootInputText: json['root_input_text'] as String? ?? '',
      childInputText: json['child_input_text'] as String? ?? '',
      giveupInputText: json['giveup_input_text'] as String? ?? '',
      bgText: json['bg_text'] as String? ?? '',
      webSelection: json['web_selection'] as bool? ?? false,
      answerGuideText: json['answer_guide_text'] as String? ?? '',
      answerGuideIconUrl: json['answer_guide_icon_url'] as String? ?? '',
      answerGuideIosUrl: json['answer_guide_ios_url'] as String? ?? '',
      answerGuideAndroidUrl: json['answer_guide_android_url'] as String? ?? '',
      showType: json['show_type'] as int? ?? 0,
      showText: json['show_text'] as String? ?? '',
      disableJumpEmote: json['disable_jump_emote'] as bool? ?? false,
    );
  }
}
