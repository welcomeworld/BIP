class BiliExploreResponse {
  final int code;
  final String message;
  final Data? data;

  BiliExploreResponse({required this.code, required this.message, this.data});

  factory BiliExploreResponse.fromJson(Map<String, dynamic> json) {
    return BiliExploreResponse(
      code: json['code'] ?? 0,
      message: json['message'] ?? '',
      data: json['data'] != null ? Data.fromJson(json['data']) : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'code': code,
      'message': message,
      'data': data?.toJson(),
    };
  }
}

class Data {
  final List<Item> item;

  Data({required this.item});

  factory Data.fromJson(Map<String, dynamic> json) {
    return Data(
      item: List<Item>.from(json['item'].map((x) => Item.fromJson(x))),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'item': item.map((x) => x.toJson()).toList(),
    };
  }
}

class Item {
  final int id;
  final String bvid;
  final int cid;
  final String gotoX;
  final String uri;
  final String pic;
  final String title;
  final int duration;
  final Owner? owner;
  final BusinessInfo? businessInfo;
  final int pubDate;
  final RcmdReason? rcmdReason;
  final Stat? stat;
  final bool isFollowed;

  Item({
    required this.id,
    required this.bvid,
    required this.cid,
    required this.gotoX,
    required this.uri,
    required this.pic,
    required this.title,
    required this.duration,
    this.owner,
    this.businessInfo,
    required this.pubDate,
    this.rcmdReason,
    this.stat,
    required this.isFollowed,
  });

  factory Item.fromJson(Map<String, dynamic> json) {
    return Item(
      id: json['id'] ?? 0,
      bvid: json['bvid'] ?? '',
      cid: json['cid'] ?? 0,
      gotoX: json['goto'] ?? '',
      uri: json['uri'] ?? '',
      pic: json['pic'] ?? '',
      title: json['title'] ?? '',
      duration: json['duration'] ?? 0,
      owner: json['owner'] != null ? Owner.fromJson(json['owner']) : null,
      businessInfo: json['business_info'] != null
          ? BusinessInfo.fromJson(json['business_info'])
          : null,
      pubDate: json['pubdate'] ?? 0,
      rcmdReason: json['rcmd_reason'] != null
          ? RcmdReason.fromJson(json['rcmd_reason'])
          : null,
      stat: json['stat'] != null ? Stat.fromJson(json['stat']) : null,
      isFollowed: (json['is_followed'] ?? 0) == 1 ? true : false,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'bvid': bvid,
      'cid': cid,
      'goto': gotoX,
      'uri': uri,
      'pic': pic,
      'title': title,
      'duration': duration,
      'owner': owner?.toJson(),
      'business_info': businessInfo?.toJson(),
      'pubdate': pubDate,
      'rcmd_reason': rcmdReason?.toJson(),
      'stat': stat?.toJson(),
      'is_followed': isFollowed ? 1 : 0,
    };
  }
}

class Owner {
  final String name;

  Owner({required this.name});

  factory Owner.fromJson(Map<String, dynamic> json) {
    return Owner(
      name: json['name'] ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'name': name,
    };
  }
}

class BusinessInfo {
  final int srcId;

  BusinessInfo({required this.srcId});

  factory BusinessInfo.fromJson(Map<String, dynamic> json) {
    return BusinessInfo(
      srcId: json['src_id'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'src_id': srcId,
    };
  }
}

class RcmdReason {
  final String content;

  RcmdReason({required this.content});

  factory RcmdReason.fromJson(Map<String, dynamic> json) {
    return RcmdReason(
      content: json['content'] ?? "",
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'content': content,
    };
  }
}

class Stat {
  final int view;

  Stat({required this.view});

  factory Stat.fromJson(Map<String, dynamic> json) {
    return Stat(
      view: json['view'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'view': view,
    };
  }
}
