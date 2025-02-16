import 'bili_search_video_type.dart';

class BiliTypeSearchResponse {
  final int? code;
  final String? message;
  final Data? data;

  BiliTypeSearchResponse({
    this.code,
    this.message,
    this.data,
  });

  factory BiliTypeSearchResponse.fromJson(Map<String, dynamic> json) {
    return BiliTypeSearchResponse(
      code: json['code'] as int?,
      message: json['message'] as String?,
      data: json['data'] != null
          ? Data.fromJson(json['data'] as Map<String, dynamic>)
          : null,
    );
  }

  Map<String, dynamic> toJson() => {
        'code': code,
        'message': message,
        'data': data?.toJson(),
      };
}

class Data {
  final String? seid;
  final int? page;
  final int? pagesize;
  final int? numResults;
  final int? numPages;
  final List<BiliSearchVideoType>? result;

  Data({
    this.seid,
    this.page,
    this.pagesize,
    this.numResults,
    this.numPages,
    this.result,
  });

  factory Data.fromJson(Map<String, dynamic> json) {
    return Data(
      seid: json['seid'] as String?,
      page: json['page'] as int?,
      pagesize: json['pagesize'] as int?,
      numResults: json['numResults'] as int?,
      numPages: json['numPages'] as int?,
      result: json['result'] != null
          ? (json['result'] as List)
              .map((e) => BiliSearchVideoType.fromJson(e))
              .toList()
          : null,
    );
  }

  Map<String, dynamic> toJson() => {
        'seid': seid,
        'page': page,
        'pagesize': pagesize,
        'numResults': numResults,
        'numPages': numPages,
        'result': result?.map((e) => e.toJson()).toList(),
      };
}
