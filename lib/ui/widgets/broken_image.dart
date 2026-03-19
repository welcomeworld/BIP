import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

Widget brokenImage(double? iconSize) {
  return Container(
    decoration: const BoxDecoration(color: Colors.black38),
    child: Center(
      child: Icon(
        Icons.broken_image_rounded,
        color: Colors.black45,
        size: iconSize,
      ),
    ),
  );
}
