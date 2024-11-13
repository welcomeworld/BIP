class PageInfo {
  final String pageName;
  final Map<String, dynamic> extras;

  PageInfo(this.pageName, {this.extras = const {}});
}

class PageNames{
  PageNames._();
  static const String home = "/";
}
