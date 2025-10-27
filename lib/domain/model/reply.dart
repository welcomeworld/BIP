import 'package:bip/domain/model/user_info.dart';

class Reply {
  String sourceName = "";
  Map<String, dynamic> extras = {};
  String replyId; // unique identifier for the reply
  String replyParentId = "";
  String rootReplyId = ""; // the root reply id, if this is a sub-reply
  UserInfo owner;
  UserInfo? replyTo; // the user being replied to, if applicable
  ReplyContent content;
  int replyTime = 0; // seconds since epoch
  int likeCount = 0;
  bool isLiked = false; // whether the current user has liked this reply
  bool isHated = false; // whether the current user has hated this reply
  int subReplyCount = 0; // total number of sub-replies
  List<Reply> subReplies = [];
  bool subReplyExpanded = false;
  String device = ""; // device used to post the reply
  String replyIp = "";
  String replyDec = "";

  Reply(this.replyId, {required this.owner, required this.content});
}

class ReplyContent {
  String message = "";
  Map<String, ContentEmote> emote = {};
  List<ContentPicture> pictures = [];
  List<UserInfo> members = [];
}

class ContentEmote {
  final int id;
  final int packageId;
  final String text;
  final String url;
  int size;

  ContentEmote({
    required this.id,
    required this.packageId,
    required this.text,
    required this.url,
    required this.size,
  });

  factory ContentEmote.fromJson(Map<String, dynamic> json) {
    return ContentEmote(
      id: json['id'] as int? ?? 0,
      packageId: json['package_id'] as int? ?? 0,
      text: json['text'] as String? ?? '',
      url: json['url'] as String? ?? '',
      size: json['size'] as int? ?? 0,
    );
  }
}

class ContentPicture {
  final String imgSrc;
  final int imgWidth;
  final int imgHeight;

  const ContentPicture({
    required this.imgSrc,
    required this.imgWidth,
    required this.imgHeight,
  });

  factory ContentPicture.fromJson(Map<String, dynamic> json) {
    return ContentPicture(
      imgSrc: json['img_src'] as String? ?? '',
      imgWidth: json['img_width'] as int? ?? 0,
      imgHeight: json['img_height'] as int? ?? 0,
    );
  }
}
