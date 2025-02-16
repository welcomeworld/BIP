import 'package:flutter/material.dart';

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
              fontWeight: FontWeight.bold,
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
