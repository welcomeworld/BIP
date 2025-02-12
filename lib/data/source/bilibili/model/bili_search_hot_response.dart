class BiliSearchHotResponse {
  final int code;
  final String message;
  final Data? data;

  BiliSearchHotResponse({
    required this.code,
    required this.message,
    this.data,
  });

  factory BiliSearchHotResponse.fromJson(Map<String, dynamic> json) {
    return BiliSearchHotResponse(
      code: json['code'] ?? 0,
      message: json['message'] ?? '',
      data: json['data'] != null ? Data.fromJson(json['data']) : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'code': code,
      'message': message,
      'data': data?.toJson(),
    };
  }
}

class Data {
  final Trending? trending;

  Data._({this.trending});

  factory Data.fromJson(Map<String, dynamic> json) {
    return Data._(
      trending: json['trending'] != null ? Trending.fromJson(json['trending']) : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'trending': trending?.toJson(),
    };
  }
}

class Trending {
  final String title;
  final List<SearchHotInnerData>? hotSearch;

  Trending._({
    required this.title,
    this.hotSearch,
  });

  factory Trending.fromJson(Map<String, dynamic> json) {
    return Trending._(
      title: json['title'] ?? '',
      hotSearch: (json['list'] as List?)?.map((e) => SearchHotInnerData.fromJson(e)).toList(),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'title': title,
      'list': hotSearch?.map((e) => e.toJson()).toList(),
    };
  }
}

class SearchHotInnerData {
  final String keyword;
  final String icon;

  SearchHotInnerData._({
    required this.keyword,
    required this.icon,
  });

  factory SearchHotInnerData.fromJson(Map<String, dynamic> json) {
    return SearchHotInnerData._(
      keyword: json['keyword'] ?? '',
      icon: json['icon'] ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'keyword': keyword,
      'icon': icon,
    };
  }
}