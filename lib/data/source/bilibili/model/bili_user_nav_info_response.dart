class BiliUserNavInfoResponse {
  int? code;
  String? message;
  int? ttl;
  UserData? data;

  BiliUserNavInfoResponse({this.code, this.message, this.ttl, this.data});

  factory BiliUserNavInfoResponse.fromJson(Map<String, dynamic> json) {
    return BiliUserNavInfoResponse(
      code: json['code'] as int?,
      message: json['message'] as String?,
      ttl: json['ttl'] as int?,
      data: json['data'] != null ? UserData.fromJson(json['data']) : null,
    );
  }

  Map<String, dynamic> toJson() => {
        'code': code,
        'message': message,
        'ttl': ttl,
        'data': data?.toJson(),
      };
}

class UserData {
  bool? isLogin;
  int? emailVerified;
  String? face;
  int? faceNft;
  int? faceNftType;
  LevelInfo? levelInfo;
  int? mid;
  int? mobileVerified;
  double? money;
  int? moral;
  Official? official;
  OfficialVerify? officialVerify;
  Pendant? pendant;
  int? scores;
  String? uname;
  int? vipDueDate;
  int? vipStatus;
  int? vipType;
  int? vipPayType;
  int? vipThemeType;
  VipLabel? vipLabel;
  int? vipAvatarSubscript;
  String? vipNicknameColor;
  Vip? vip;
  Wallet? wallet;
  bool? hasShop;
  String? shopUrl;
  int? answerStatus;
  int? isSeniorMember;
  WbiImg? wbiImg;
  bool? isJury;
  dynamic nameRender;

  UserData({
    this.isLogin,
    this.emailVerified,
    this.face,
    this.faceNft,
    this.faceNftType,
    this.levelInfo,
    this.mid,
    this.mobileVerified,
    this.money,
    this.moral,
    this.official,
    this.officialVerify,
    this.pendant,
    this.scores,
    this.uname,
    this.vipDueDate,
    this.vipStatus,
    this.vipType,
    this.vipPayType,
    this.vipThemeType,
    this.vipLabel,
    this.vipAvatarSubscript,
    this.vipNicknameColor,
    this.vip,
    this.wallet,
    this.hasShop,
    this.shopUrl,
    this.answerStatus,
    this.isSeniorMember,
    this.wbiImg,
    this.isJury,
    this.nameRender,
  });

  factory UserData.fromJson(Map<String, dynamic> json) {
    return UserData(
      isLogin: json['isLogin'] as bool?,
      emailVerified: json['email_verified'] as int?,
      face: json['face'] as String?,
      faceNft: json['face_nft'] as int?,
      faceNftType: json['face_nft_type'] as int?,
      levelInfo: json['level_info'] != null
          ? LevelInfo.fromJson(json['level_info'])
          : null,
      mid: json['mid'] as int?,
      mobileVerified: json['mobile_verified'] as int?,
      money: (json['money'] as num?)?.toDouble(),
      moral: json['moral'] as int?,
      official:
          json['official'] != null ? Official.fromJson(json['official']) : null,
      officialVerify: json['officialVerify'] != null
          ? OfficialVerify.fromJson(json['officialVerify'])
          : null,
      pendant:
          json['pendant'] != null ? Pendant.fromJson(json['pendant']) : null,
      scores: json['scores'] as int?,
      uname: json['uname'] as String?,
      vipDueDate: json['vipDueDate'] as int?,
      vipStatus: json['vipStatus'] as int?,
      vipType: json['vipType'] as int?,
      vipPayType: json['vip_pay_type'] as int?,
      vipThemeType: json['vip_theme_type'] as int?,
      vipLabel: json['vip_label'] != null
          ? VipLabel.fromJson(json['vip_label'])
          : null,
      vipAvatarSubscript: json['vip_avatar_subscript'] as int?,
      vipNicknameColor: json['vip_nickname_color'] as String?,
      vip: json['vip'] != null ? Vip.fromJson(json['vip']) : null,
      wallet: json['wallet'] != null ? Wallet.fromJson(json['wallet']) : null,
      hasShop: json['has_shop'] as bool?,
      shopUrl: json['shop_url'] as String?,
      answerStatus: json['answer_status'] as int?,
      isSeniorMember: json['is_senior_member'] as int?,
      wbiImg: json['wbi_img'] != null ? WbiImg.fromJson(json['wbi_img']) : null,
      isJury: json['is_jury'] as bool?,
      nameRender: json['name_render'],
    );
  }

  Map<String, dynamic> toJson() => {
        'isLogin': isLogin,
        'email_verified': emailVerified,
        'face': face,
        'face_nft': faceNft,
        'face_nft_type': faceNftType,
        'level_info': levelInfo?.toJson(),
        'mid': mid,
        'mobile_verified': mobileVerified,
        'money': money,
        'moral': moral,
        'official': official?.toJson(),
        'officialVerify': officialVerify?.toJson(),
        'pendant': pendant?.toJson(),
        'scores': scores,
        'uname': uname,
        'vipDueDate': vipDueDate,
        'vipStatus': vipStatus,
        'vipType': vipType,
        'vip_pay_type': vipPayType,
        'vip_theme_type': vipThemeType,
        'vip_label': vipLabel?.toJson(),
        'vip_avatar_subscript': vipAvatarSubscript,
        'vip_nickname_color': vipNicknameColor,
        'vip': vip?.toJson(),
        'wallet': wallet?.toJson(),
        'has_shop': hasShop,
        'shop_url': shopUrl,
        'answer_status': answerStatus,
        'is_senior_member': isSeniorMember,
        'wbi_img': wbiImg?.toJson(),
        'is_jury': isJury,
        'name_render': nameRender,
      };
}

class LevelInfo {
  int? currentLevel;
  int? currentMin;
  int? currentExp;
  String? nextExp;

  LevelInfo(
      {this.currentLevel, this.currentMin, this.currentExp, this.nextExp});

  factory LevelInfo.fromJson(Map<String, dynamic> json) => LevelInfo(
        currentLevel: json['current_level'] as int?,
        currentMin: json['current_min'] as int?,
        currentExp: json['current_exp'] as int?,
        nextExp: json['next_exp'] as String?,
      );

  Map<String, dynamic> toJson() => {
        'current_level': currentLevel,
        'current_min': currentMin,
        'current_exp': currentExp,
        'next_exp': nextExp,
      };
}

class Official {
  int? role;
  String? title;
  String? desc;
  int? type;

  Official({this.role, this.title, this.desc, this.type});

  factory Official.fromJson(Map<String, dynamic> json) => Official(
        role: json['role'] as int?,
        title: json['title'] as String?,
        desc: json['desc'] as String?,
        type: json['type'] as int?,
      );

  Map<String, dynamic> toJson() => {
        'role': role,
        'title': title,
        'desc': desc,
        'type': type,
      };
}

class OfficialVerify {
  int? type;
  String? desc;

  OfficialVerify({this.type, this.desc});

  factory OfficialVerify.fromJson(Map<String, dynamic> json) => OfficialVerify(
        type: json['type'] as int?,
        desc: json['desc'] as String?,
      );

  Map<String, dynamic> toJson() => {
        'type': type,
        'desc': desc,
      };
}

class Pendant {
  int? pid;
  String? name;
  String? image;
  int? expire;
  String? imageEnhance;
  String? imageEnhanceFrame;
  int? nPid;

  Pendant({
    this.pid,
    this.name,
    this.image,
    this.expire,
    this.imageEnhance,
    this.imageEnhanceFrame,
    this.nPid,
  });

  factory Pendant.fromJson(Map<String, dynamic> json) => Pendant(
        pid: json['pid'] as int?,
        name: json['name'] as String?,
        image: json['image'] as String?,
        expire: json['expire'] as int?,
        imageEnhance: json['image_enhance'] as String?,
        imageEnhanceFrame: json['image_enhance_frame'] as String?,
        nPid: json['n_pid'] as int?,
      );

  Map<String, dynamic> toJson() => {
        'pid': pid,
        'name': name,
        'image': image,
        'expire': expire,
        'image_enhance': imageEnhance,
        'image_enhance_frame': imageEnhanceFrame,
        'n_pid': nPid,
      };
}

class Vip {
  int? type;
  int? status;
  int? dueDate;
  int? vipPayType;
  int? themeType;
  VipLabel? label;
  int? avatarSubscript;
  String? nicknameColor;
  int? role;
  String? avatarSubscriptUrl;
  int? tvVipStatus;
  int? tvVipPayType;
  int? tvDueDate;
  AvatarIcon? avatarIcon;

  Vip({
    this.type,
    this.status,
    this.dueDate,
    this.vipPayType,
    this.themeType,
    this.label,
    this.avatarSubscript,
    this.nicknameColor,
    this.role,
    this.avatarSubscriptUrl,
    this.tvVipStatus,
    this.tvVipPayType,
    this.tvDueDate,
    this.avatarIcon,
  });

  factory Vip.fromJson(Map<String, dynamic> json) => Vip(
        type: json['type'] as int?,
        status: json['status'] as int?,
        dueDate: json['due_date'] as int?,
        vipPayType: json['vip_pay_type'] as int?,
        themeType: json['theme_type'] as int?,
        label: json['label'] != null ? VipLabel.fromJson(json['label']) : null,
        avatarSubscript: json['avatar_subscript'] as int?,
        nicknameColor: json['nickname_color'] as String?,
        role: json['role'] as int?,
        avatarSubscriptUrl: json['avatar_subscript_url'] as String?,
        tvVipStatus: json['tv_vip_status'] as int?,
        tvVipPayType: json['tv_vip_pay_type'] as int?,
        tvDueDate: json['tv_due_date'] as int?,
        avatarIcon: json['avatar_icon'] != null
            ? AvatarIcon.fromJson(json['avatar_icon'])
            : null,
      );

  Map<String, dynamic> toJson() => {
        'type': type,
        'status': status,
        'due_date': dueDate,
        'vip_pay_type': vipPayType,
        'theme_type': themeType,
        'label': label?.toJson(),
        'avatar_subscript': avatarSubscript,
        'nickname_color': nicknameColor,
        'role': role,
        'avatar_subscript_url': avatarSubscriptUrl,
        'tv_vip_status': tvVipStatus,
        'tv_vip_pay_type': tvVipPayType,
        'tv_due_date': tvDueDate,
        'avatar_icon': avatarIcon?.toJson(),
      };
}

class Wallet {
  int? mid;
  int? bcoinBalance;
  int? couponBalance;
  int? couponDueTime;

  Wallet({
    this.mid,
    this.bcoinBalance,
    this.couponBalance,
    this.couponDueTime,
  });

  factory Wallet.fromJson(Map<String, dynamic> json) => Wallet(
        mid: json['mid'] as int?,
        bcoinBalance: json['bcoin_balance'] as int?,
        couponBalance: json['coupon_balance'] as int?,
        couponDueTime: json['coupon_due_time'] as int?,
      );

  Map<String, dynamic> toJson() => {
        'mid': mid,
        'bcoin_balance': bcoinBalance,
        'coupon_balance': couponBalance,
        'coupon_due_time': couponDueTime,
      };
}

class WbiImg {
  String? imgUrl;
  String? subUrl;

  WbiImg({this.imgUrl, this.subUrl});

  factory WbiImg.fromJson(Map<String, dynamic> json) => WbiImg(
        imgUrl: json['img_url'] as String?,
        subUrl: json['sub_url'] as String?,
      );

  Map<String, dynamic> toJson() => {
        'img_url': imgUrl,
        'sub_url': subUrl,
      };
}

class AvatarIcon {
  int? iconType;
  Map<String, dynamic>? iconResource;

  AvatarIcon({this.iconType, this.iconResource});

  factory AvatarIcon.fromJson(Map<String, dynamic> json) => AvatarIcon(
        iconType: json['icon_type'] as int?,
        iconResource: json['icon_resource'] as Map<String, dynamic>?,
      );

  Map<String, dynamic> toJson() => {
        'icon_type': iconType,
        'icon_resource': iconResource,
      };
}

class VipLabel {
  String? path;
  String? text;
  String? labelTheme;
  String? textColor;
  int? bgStyle;
  String? bgColor;
  String? borderColor;
  bool? useImgLabel;
  String? imgLabelUriHans;
  String? imgLabelUriHant;
  String? imgLabelUriHansStatic;
  String? imgLabelUriHantStatic;

  VipLabel({
    this.path,
    this.text,
    this.labelTheme,
    this.textColor,
    this.bgStyle,
    this.bgColor,
    this.borderColor,
    this.useImgLabel,
    this.imgLabelUriHans,
    this.imgLabelUriHant,
    this.imgLabelUriHansStatic,
    this.imgLabelUriHantStatic,
  });

  factory VipLabel.fromJson(Map<String, dynamic> json) => VipLabel(
        path: json['path'] as String?,
        text: json['text'] as String?,
        labelTheme: json['label_theme'] as String?,
        textColor: json['text_color'] as String?,
        bgStyle: json['bg_style'] as int?,
        bgColor: json['bg_color'] as String?,
        borderColor: json['border_color'] as String?,
        useImgLabel: json['use_img_label'] as bool?,
        imgLabelUriHans: json['img_label_uri_hans'] as String?,
        imgLabelUriHant: json['img_label_uri_hant'] as String?,
        imgLabelUriHansStatic: json['img_label_uri_hans_static'] as String?,
        imgLabelUriHantStatic: json['img_label_uri_hant_static'] as String?,
      );

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
}
