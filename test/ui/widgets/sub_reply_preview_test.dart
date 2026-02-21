import 'package:bip/domain/model/reply.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:bip/ui/widgets/sub_reply_preview.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/ui_test_util.dart';

void main() {
  testWidgets('SubReplyPreview renders sub-reply correctly', (WidgetTester tester) async {
    final reply = Reply(
      'sub1',
      owner: UserInfo(sourceName: 'test', name: 'SubUser'),
      content: ReplyContent()..message = 'SubContent',
    );

    await UiTestUtil.pumpWithContext(tester, child: SubReplyPreview(reply: reply));

    expect(find.text('SubUser:SubContent', findRichText: true), findsOneWidget);
  });
}
