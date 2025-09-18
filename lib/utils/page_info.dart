import 'dart:async';

import 'package:flutter/material.dart';

class PageInfo {
  final String pageName;
  final Map<String, dynamic> extras;

  Future<dynamic> get popped => _popCompleter.future;
  final Completer<dynamic> _popCompleter = Completer<dynamic>();

  PageInfo(this.pageName, {this.extras = const {}});

  @mustCallSuper
  void didComplete<T>(T? result) {
    _popCompleter.complete(result);
  }
}

class PageNames {
  PageNames._();

  static const String home = "/";
  static const String search = "/search";
  static const String mediaPageDetail = "/media/page/detail";
  static const String login = "/login";
  static const String history = "/history";
  static const String collections = "/collections";
  static const String collectionDetail = "/collection/detail";
}
