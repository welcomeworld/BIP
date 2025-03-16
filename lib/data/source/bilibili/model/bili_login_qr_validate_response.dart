class BiliLoginQrValidateResponse {
  int? code;
  String? message;
  int? ttl;
  BiliLoginQrValidateData? data;

  BiliLoginQrValidateResponse({this.code, this.message, this.ttl, this.data});

  factory BiliLoginQrValidateResponse.fromJson(Map<String, dynamic> json) {
    return BiliLoginQrValidateResponse(
      code: json['code'] as int?,
      message: json['message'] as String?,
      ttl: json['ttl'] as int?,
      data: json['data'] != null
          ? BiliLoginQrValidateData.fromJson(json['data'])
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'code': code,
      'message': message,
      'ttl': ttl,
      'data': data?.toJson(),
    };
  }
}

class BiliLoginQrValidateData {
  String? url;
  String? refreshToken;
  int? timestamp;
  int? code;
  String? message;

  BiliLoginQrValidateData({
    this.url,
    this.refreshToken,
    this.timestamp,
    this.code,
    this.message,
  });

  factory BiliLoginQrValidateData.fromJson(Map<String, dynamic> json) {
    return BiliLoginQrValidateData(
      url: json['url'] as String?,
      refreshToken: json['refresh_token'] as String?,
      timestamp: json['timestamp'] as int?,
      code: json['code'] as int?,
      message: json['message'] as String?,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'url': url,
      'refresh_token': refreshToken,
      'timestamp': timestamp,
      'code': code,
      'message': message,
    };
  }
}
