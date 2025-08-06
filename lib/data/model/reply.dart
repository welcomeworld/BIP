import 'package:bip/data/model/user_info.dart';

class Reply {
  String sourceName = "";
  Map<String, dynamic> extras = {};
  String replyId; // unique identifier for the reply
  String replyParentId = "";
  String rootReplyId = ""; // the root reply id, if this is a sub-reply
  late UserInfo owner;
  UserInfo? replyTo; // the user being replied to, if applicable
  String content = "";
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

  Reply(this.replyId);
}
