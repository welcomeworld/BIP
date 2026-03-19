import 'package:bip/ui/widgets/broken_image.dart';
import 'package:flutter/material.dart';

import '../../domain/model/reply.dart';

TextSpan buildTextSpans(String text, BuildContext context, {TextStyle? style}) {
  final List<TextSpan> spans = [];
  final RegExp emRegExp = RegExp(r'<em[^>]*>(.*?)</em>');
  int lastIndex = 0;
  for (final match in emRegExp.allMatches(text)) {
    if (match.start > lastIndex) {
      spans.add(TextSpan(text: text.substring(lastIndex, match.start)));
    }
    spans.add(
      TextSpan(
        text: match.group(1),
        style: style?.copyWith(
              color: Theme.of(context).colorScheme.primary,
            ) ??
            TextStyle(
              color: Theme.of(context).colorScheme.primary,
              fontWeight: FontWeight.bold,
            ),
      ),
    );

    lastIndex = match.end;
  }
  if (lastIndex < text.length) {
    spans.add(TextSpan(text: text.substring(lastIndex)));
  }
  return TextSpan(style: style, children: spans);
}

TextSpan buildReplySpans(Reply reply, BuildContext context,
    {TextStyle? style, bool subReply = false}) {
  final List<InlineSpan> spans = [];
  final RegExp emRegExp = RegExp(r'<em[^>]*>(.*?)</em>');
  final emoRegExp = RegExp(r"\[(.*?)\]");
  final patterns = [emRegExp.pattern, emoRegExp.pattern];
  final allRegExp = RegExp(patterns.join('|'), dotAll: true);
  int lastIndex = 0;

  final message = subReply
      ? "<em>${reply.owner.name}</em>:${reply.content.message}"
      : reply.content.message;
  for (final match in allRegExp.allMatches(message)) {
    if (match.start > lastIndex) {
      spans.add(TextSpan(text: message.substring(lastIndex, match.start)));
    }
    final matchText = match.group(0)!;
    if (emRegExp.hasMatch(matchText)) {
      // 处理 <em> 标签
      spans.add(
        TextSpan(
          text: match.group(1),
          style: style?.copyWith(
                color: Theme.of(context).colorScheme.primary,
              ) ??
              TextStyle(
                color: Theme.of(context).colorScheme.primary,
                fontWeight: FontWeight.bold,
              ),
        ),
      );
    } else if (emoRegExp.hasMatch(matchText)) {
      // 处理表情
      final emote = reply.content.emote[matchText];
      if (emote != null) {
        spans.add(
          WidgetSpan(
              child: Padding(
                padding: const EdgeInsets.only(left: 4.0), // 表情间距
                child: SizedBox(
                  width: emote.size.toDouble(),
                  height: emote.size.toDouble(),
                  child: Image.network(
                    emote.url,
                    fit: BoxFit.contain,
                    errorBuilder: (context, error, trace) =>
                        brokenImage(emote.size.toDouble()),
                  ),
                ),
              ),
              alignment: PlaceholderAlignment.bottom,
              baseline: TextBaseline.ideographic),
        );
      } else {
        spans.add(TextSpan(text: matchText)); // 未找到表情时显示原文本
      }
    }
    lastIndex = match.end;
  }
  if (lastIndex < message.length) {
    spans.add(TextSpan(text: message.substring(lastIndex)));
  }

  // 如果有图片，添加图片的WidgetSpan
  if (reply.content.pictures.isNotEmpty) {
    spans.add(const TextSpan(text: "\n"));
    for (var picture in reply.content.pictures) {
      int originalWidth = picture.imgWidth;
      int originalHeight = picture.imgHeight;
      if (originalHeight == 0) originalHeight = 1; // 避免除零
      double aspectRatio = originalWidth / originalHeight;
      double maxRatio = 1.3;

      Widget imageWidget;
      double imageHeight = 176;
      double scaledWidth;
      if (aspectRatio >= 1 / maxRatio && aspectRatio <= maxRatio) {
        // 保持原始比例，等比缩放到高度176
        double scaledHeight = imageHeight;
        scaledWidth = imageHeight * aspectRatio;
        imageWidget = SizedBox(
          width: scaledWidth,
          height: scaledHeight,
          child: Image.network(
            picture.imgSrc,
            fit: BoxFit.contain,
            errorBuilder: (context, error, trace) =>
                brokenImage(scaledWidth / 2),
          ),
        );
      } else {
        if (aspectRatio > maxRatio) {
          // 宽图：目标aspect = maxRatio
          scaledWidth = imageHeight * maxRatio;
        } else {
          // 高图：目标aspect = 1 / maxRatio
          scaledWidth = imageHeight * (1 / maxRatio);
        }
        imageWidget = SizedBox(
          width: scaledWidth,
          height: imageHeight,
          child: Image.network(
            picture.imgSrc,
            fit: BoxFit.cover, // cover实现居中剪切
            errorBuilder: (context, error, trace) =>
                brokenImage(scaledWidth / 2),
          ),
        );
      }
      spans.add(
        WidgetSpan(
          child: Padding(
            padding: const EdgeInsets.only(top: 4.0), // 图片间距
            child: ClipRRect(
              borderRadius: const BorderRadius.only(
                topLeft: Radius.circular(4),
                bottomLeft: Radius.circular(4),
                topRight: Radius.circular(4),
                bottomRight: Radius.circular(4),
              ),
              child: imageWidget,
            ),
          ),
          alignment: PlaceholderAlignment.middle,
        ),
      );
    }
  }

  return TextSpan(style: style, children: spans);
}
