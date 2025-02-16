class BiliSearchVideoType {
  final String? type;
  final int? id;
  final String? author;
  final int? mid;
  final String? typeid;
  final String? typename;
  final String? arcurl;
  final int? aid;
  final String? bvid;
  final String? title;
  final String? pic;
  final int? play;
  final int? videoReview;
  final int? favorites;
  final String? tag;
  final int? review;
  final int? pubdate;
  final int? senddate;
  final String? duration;
  final int? isUnionVideo;
  final int? like;
  final String? upic;
  final int? danmaku;
  final int? isChargeVideo;

  BiliSearchVideoType({
    this.type,
    this.id,
    this.author,
    this.mid,
    this.typeid,
    this.typename,
    this.arcurl,
    this.aid,
    this.bvid,
    this.title,
    this.pic,
    this.play,
    this.videoReview,
    this.favorites,
    this.tag,
    this.review,
    this.pubdate,
    this.senddate,
    this.duration,
    this.isUnionVideo,
    this.like,
    this.upic,
    this.danmaku,
    this.isChargeVideo,
  });

  factory BiliSearchVideoType.fromJson(Map<String, dynamic> json) {
    var cover = json['pic'] ?? "";
    if (!cover.startsWith("http")) {
      cover = "https:$cover";
    }
    return BiliSearchVideoType(
      type: json['type'] as String?,
      id: json['id'] as int?,
      author: json['author'] as String?,
      mid: json['mid'] as int?,
      typeid: json['typeid'] as String?,
      typename: json['typename'] as String?,
      arcurl: json['arcurl'] as String?,
      aid: json['aid'] as int?,
      bvid: json['bvid'] as String?,
      title: json['title'] as String?,
      pic: cover,
      play: json['play'] as int?,
      videoReview: json['video_review'] as int?,
      favorites: json['favorites'] as int?,
      tag: json['tag'] as String?,
      review: json['review'] as int?,
      pubdate: json['pubdate'] as int?,
      senddate: json['senddate'] as int?,
      duration: json['duration'] as String?,
      isUnionVideo: json['is_union_video'] as int?,
      like: json['like'] as int?,
      upic: json['upic'] as String?,
      danmaku: json['danmaku'] as int?,
      isChargeVideo: json['is_charge_video'] as int?,
    );
  }

  Map<String, dynamic> toJson() => {
        'type': type,
        'id': id,
        'author': author,
        'mid': mid,
        'typeid': typeid,
        'typename': typename,
        'arcurl': arcurl,
        'aid': aid,
        'bvid': bvid,
        'title': title,
        'pic': pic,
        'play': play,
        'video_review': videoReview,
        'favorites': favorites,
        'tag': tag,
        'review': review,
        'pubdate': pubdate,
        'senddate': senddate,
        'duration': duration,
        'is_union_video': isUnionVideo,
        'like': like,
        'upic': upic,
        'danmaku': danmaku,
        'is_charge_video': isChargeVideo,
      };
}
