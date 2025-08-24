import 'package:bip/data/model/reply.dart';
import 'package:bip/data/model/user_info.dart';
import 'package:bip/ui/page/reply_detail_page.dart';
import 'package:bip/ui/theme/theme_colors.dart';
import 'package:bip/ui/widgets/level_badge.dart';
import 'package:bip/ui/widgets/simple_rich_text.dart';
import 'package:bip/ui/widgets/simple_svg.dart';
import 'package:bip/ui/widgets/sub_reply_preview.dart';
import 'package:flutter/material.dart';

import '../../utils/common_util.dart';

class ReplyCard extends StatelessWidget {
  const ReplyCard({super.key, required this.reply, this.showSub = true});

  final Reply reply;
  final bool showSub;

  Future<void> pushPop(BuildContext context) async {
    await Navigator.of(context).push(
      PageRouteBuilder(
        opaque: false, // 背景透明
        pageBuilder: (context, _, __) => ReplyDetailPage(reply),
        transitionsBuilder: (context, animation, secondaryAnimation, child) {
          // 动画效果：从底部滑入
          const begin = Offset(0.0, 1.0);
          const end = Offset.zero;
          const curve = Curves.easeInOut;
          var tween =
              Tween(begin: begin, end: end).chain(CurveTween(curve: curve));
          var offsetAnimation = animation.drive(tween);
          return SlideTransition(
            position: offsetAnimation,
            child: child,
          );
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    UserInfo owner = reply.owner;
    final colorScheme = Theme.of(context).colorScheme;

    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        CircleAvatar(
          radius: 20.0,
          backgroundImage: owner.avatar.isNotEmpty
              ? NetworkImage(
                  owner.avatar,
                )
              : const AssetImage(
                  "assets/img/ic_default_avatar.png",
                ),
        ),

        // Text content
        Expanded(
          child: Padding(
            padding: const EdgeInsets.only(left: 8.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisSize: MainAxisSize.min,
              children: [
                SizedBox(
                  height: 40,
                  child: Align(
                    alignment: Alignment.centerLeft,
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Text(
                              owner.name, // Placeholder text
                              style: TextStyle(
                                fontSize: 14,
                                color: owner.isVip
                                    ? colorScheme.vipGold
                                    : colorScheme
                                        .onSurface, // Approximating colorAccent
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(left: 4),
                              child: LevelBadge(level: owner.level),
                            ),
                          ],
                        ),
                        // Time
                        //0xFF575F75
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Text(
                              CommonUtil.formatDateShow(
                                DateTime.fromMillisecondsSinceEpoch(
                                  reply.replyTime,
                                ),
                              ),
                              style: TextStyle(
                                fontSize: 11.0,
                                color: colorScheme.onSurfaceVariant,
                              ),
                            ),
                            if (reply.replyIp.isNotEmpty)
                              Padding(
                                padding: const EdgeInsets.only(left: 8.0),
                                child: Text(
                                  reply.replyIp,
                                  style: TextStyle(
                                    fontSize: 11.0,
                                    color: colorScheme.onSurfaceVariant,
                                  ),
                                ),
                              ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 4, bottom: 4),
                  child: RichText(
                    text: buildReplySpans(
                      reply,
                      context,
                      style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.w500,
                        color: colorScheme.onSurfaceVariant,
                      ),
                    ),
                  ),
                ),
                Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    IconButton(
                      onPressed: () {},
                      icon: Row(
                        mainAxisSize: MainAxisSize.min,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          SimpleSvg(
                            "assets/img/ic_appreciate_outline.svg",
                            size: 18,
                            color: colorScheme.tertiary.withOpacity(0.8),
                          ),
                          if (reply.likeCount > 0)
                            Padding(
                              padding: const EdgeInsets.only(left: 2),
                              child: Text(
                                "${reply.likeCount}",
                                style: TextStyle(
                                    color:
                                        colorScheme.tertiary.withOpacity(0.8)),
                              ),
                            ),
                        ],
                      ),
                      style: IconButton.styleFrom(
                        padding: EdgeInsets.zero,
                        minimumSize: const Size(26, 26),
                        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                      ),
                    ),
                    const SizedBox(
                      width: 16,
                    ),
                    IconButton(
                      onPressed: () {},
                      icon: SimpleSvg(
                        "assets/img/ic_oppose_outline.svg",
                        size: 18,
                        color: colorScheme.tertiary.withOpacity(0.8),
                      ),
                      style: IconButton.styleFrom(
                        padding: EdgeInsets.zero,
                        minimumSize: const Size(26, 26),
                        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                      ),
                    ),
                    const SizedBox(
                      width: 16,
                    ),
                    IconButton(
                      onPressed: () {},
                      icon: SimpleSvg(
                        "assets/img/ic_warn_outline.svg",
                        size: 18,
                        color: colorScheme.tertiary.withOpacity(0.8),
                      ),
                      style: IconButton.styleFrom(
                        padding: EdgeInsets.zero,
                        minimumSize: const Size(26, 26),
                        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                      ),
                    ),
                    const SizedBox(
                      width: 16,
                    ),
                    IconButton(
                      onPressed: () {
                        //todo focus on reply input
                      },
                      icon: SimpleSvg(
                        "assets/img/ic_comment_outline.svg",
                        size: 18,
                        color: colorScheme.tertiary.withOpacity(0.8),
                      ),
                      style: IconButton.styleFrom(
                        padding: EdgeInsets.zero,
                        minimumSize: const Size(26, 26),
                        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                      ),
                    ),
                  ],
                ),
                if (reply.replyDec.isNotEmpty)
                  Padding(
                    padding: const EdgeInsets.only(top: 4, bottom: 6),
                    child: DecoratedBox(
                      decoration: BoxDecoration(
                        color: colorScheme.surfaceContainerLow,
                        borderRadius:
                            const BorderRadius.all(Radius.circular(4)),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.all(4),
                        child: Text(
                          reply.replyDec,
                          style: TextStyle(
                              fontSize: 12,
                              color: colorScheme.onSurface,
                              fontWeight: FontWeight.w500),
                        ),
                      ),
                    ),
                  ),
                if (showSub && reply.subReplyCount > 0)
                  Padding(
                    padding: const EdgeInsets.only(top: 4),
                    child: FilledButton(
                      onPressed: () {
                        pushPop(context);
                      },
                      style: FilledButton.styleFrom(
                        padding: EdgeInsets.zero,
                        minimumSize: const Size(0, 0),
                        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(4),
                        ),
                        backgroundColor: colorScheme.surfaceContainerLow,
                      ),
                      child: SizedBox(
                        width: double.infinity,
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              if (reply.subReplies.isNotEmpty)
                                SubReplyPreview(reply: reply.subReplies[0]),
                              if (reply.subReplies.length > 1)
                                SubReplyPreview(
                                  reply: reply.subReplies[1],
                                ),
                              if (reply.subReplyCount > reply.subReplies.length)
                                TextButton(
                                  onPressed: () {
                                    pushPop(context);
                                  },
                                  style: TextButton.styleFrom(
                                    padding: const EdgeInsets.only(
                                        top: 2, bottom: 2),
                                    minimumSize: const Size(0, 0),
                                    tapTargetSize:
                                        MaterialTapTargetSize.shrinkWrap,
                                  ),
                                  child: Text(
                                    "共${reply.subReplyCount}条回复",
                                    style: TextStyle(
                                      fontSize: 14,
                                      color: colorScheme.primary,
                                    ),
                                  ),
                                ),
                            ],
                          ),
                        ),
                      ),
                    ),
                  ),
              ],
            ),
          ),
        ),
      ],
    );
  }
}
