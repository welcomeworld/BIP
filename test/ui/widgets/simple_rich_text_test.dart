import 'package:bip/domain/model/reply.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:bip/ui/widgets/simple_rich_text.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/mock_network_image.dart';
import '../../utils/ui_test_util.dart';

void main() {
  group('SimpleRichText', () {
    testWidgets('buildTextSpans renders text with <em> tags correctly',
        (WidgetTester tester) async {
      const text = 'Hello <em>World</em>!';

      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            final span = buildTextSpans(text, context);
            return RichText(text: span);
          },
        ),
      );

      expect(find.text('Hello World!', findRichText: true), findsOneWidget);

      // To verify styling, we'd still need to inspect the TextSpan structure
      final richText = tester.widget<RichText>(find.byType(RichText));
      final textSpan = richText.text as TextSpan;
      final children = textSpan.children!;
      final emSpan = children
          .firstWhere((span) => (span as TextSpan).text == 'World') as TextSpan;
      expect(emSpan.style?.fontWeight, FontWeight.bold);
    });

    testWidgets('buildReplySpans renders text, emojis and images',
        (WidgetTester tester) async {
      final reply = Reply(
        '1',
        owner: UserInfo(sourceName: 'bili'),
        content: ReplyContent()
          ..message = 'Hello [smile] World'
          ..emote = {
            '[smile]': ContentEmote(
              id: 1,
              packageId: 1,
              text: 'smile',
              url: 'http://example.com/smile.png',
              size: 20,
            )
          }
          ..pictures = [
            const ContentPicture(
                imgSrc: 'http://example.com/pic.png',
                imgWidth: 100,
                imgHeight: 100)
          ],
      );

      await runWithMockNetworkImages(() async {
        await UiTestUtil.pumpWithContext(
          tester,
          child: Builder(
            builder: (context) {
              final span = buildReplySpans(reply, context);
              return RichText(text: span);
            },
          ),
        );
        expect(find.text('Hello ￼ World\n￼', findRichText: true),
            findsOneWidget); // Emoji is a space

        // Check for Image widgets (from WidgetSpan)
        expect(find.byType(Image), findsNWidgets(2)); // 1 emoji + 1 picture
      });
    });

    testWidgets('buildReplySpans renders sub-reply format',
        (WidgetTester tester) async {
      final reply = Reply(
        '1',
        owner: UserInfo(sourceName: 'bili', name: 'UserA'),
        content: ReplyContent()..message = 'Hi',
      );

      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            final span = buildReplySpans(reply, context, subReply: true);
            return RichText(text: span);
          },
        ),
      );

      expect(find.text('UserA:Hi', findRichText: true), findsOneWidget);
    });
  });
}
