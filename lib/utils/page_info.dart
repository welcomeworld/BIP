class PageInfo {
  final String pageName;
  final Map<String, dynamic> extras;

  PageInfo(this.pageName, {this.extras = const {}});
}

class PageNames {
  PageNames._();

  static const String home = "/";
  static const String search = "/search";
  static const String mediaPageDetail = "/media/page/detail";
  static const String login = "/login";
  static const String history = "/history";
}
