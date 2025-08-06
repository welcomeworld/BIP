import 'package:flutter/material.dart';

Widget topIconButton(
  Widget icon,
  Widget label,
  ButtonStyle? style,
  VoidCallback? onPressed,
) {
  style ??= IconButton.styleFrom(
    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
    minimumSize: const Size(48, 48),
  );
  return IconButton(
    style: style,
    onPressed: onPressed,
    icon: Column(
      mainAxisAlignment: MainAxisAlignment.center,
      mainAxisSize: MainAxisSize.min,
      children: [
        icon,
        const SizedBox(
          height: 4,
        ),
        label,
      ],
    ),
  );
}
