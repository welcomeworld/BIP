import 'package:bip/ui/widgets/simple_rich_text.dart';
import 'package:flutter/material.dart';

import '../../domain/model/reply.dart';

class SubReplyPreview extends StatelessWidget {
  const SubReplyPreview({super.key, required this.reply});

  final Reply reply;

  @override
  Widget build(BuildContext context) {
    final colorScheme = Theme.of(context).colorScheme;

    return RichText(
      text: buildReplySpans(
        reply,
        context,
        style: TextStyle(
          fontSize: 15,
          fontWeight: FontWeight.w500,
          color: colorScheme.onSurfaceVariant,
        ),
        subReply: true
      ),
      maxLines: 2,
      overflow: TextOverflow.ellipsis,
    );
  }
}
