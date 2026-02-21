import 'package:bip/domain/model/reply.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:bip/ui/widgets/reply_card.dart';
import 'package:bip/ui/widgets/sub_reply_preview.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/mock_network_image.dart';
import '../../utils/ui_test_util.dart';

void main() {
  testWidgets('ReplyCard renders reply info correctly',
      (WidgetTester tester) async {
    final reply = Reply(
      '1',
      owner: UserInfo(
        sourceName: 'test',
        name: 'ReplyUser',
        avatar: 'http://example.com/avatar.png',
        level: 5,
      ),
      content: ReplyContent()..message = 'ReplyContent',
    )..likeCount = 10;

    // Set reply time to a fixed value
    reply.replyTime = DateTime.now().millisecondsSinceEpoch ~/ 1000;

    await runWithMockNetworkImages(() async {
      await UiTestUtil.pumpWithContext(tester, child: ReplyCard(reply: reply));

      expect(find.text('ReplyUser'), findsOneWidget);
      expect(find.text('ReplyContent', findRichText: true), findsOneWidget);
      expect(find.text('10'), findsOneWidget); // like count
      expect(find.byType(CircleAvatar), findsOneWidget);
      // LevelBadge inside
      expect(find.text('LV5', findRichText: true), findsOneWidget);
    });
  });

  testWidgets('ReplyCard renders sub-replies if showSub is true',
      (WidgetTester tester) async {
    final subReply1 = Reply(
      'sub1',
      owner: UserInfo(sourceName: 'test', name: 'SubUser1'),
      content: ReplyContent()..message = 'SubContent1',
    );
    final subReply2 = Reply(
      'sub2',
      owner: UserInfo(sourceName: 'test', name: 'SubUser2'),
      content: ReplyContent()..message = 'SubContent2',
    );

    final reply = Reply(
      '1',
      owner: UserInfo(
        sourceName: 'test',
        name: 'ReplyUser',
      ),
      content: ReplyContent()..message = 'ReplyContent',
    )
      ..subReplyCount = 3
      ..subReplies = [subReply1, subReply2];

    await runWithMockNetworkImages(() async {
      await UiTestUtil.pumpWithContext(tester,
          child: ReplyCard(reply: reply, showSub: true));

      // Check sub-replies (rendered as RichText in SubReplyPreview)
      // There might be multiple RichText widgets (main reply + sub replies)
      // ReplyCard main content is RichText too likely.
      // SubReplyPreview uses RichText.

      // Let's verify we can find the sub-reply text content within the widget tree
      expect(find.text('SubUser1:SubContent1', findRichText: true),
          findsOneWidget);
      expect(find.text('SubUser2:SubContent2', findRichText: true),
          findsOneWidget);

      expect(find.text('共3条回复'), findsOneWidget);
    });
  });
}
