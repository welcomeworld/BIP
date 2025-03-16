class BiliLoginQrRequestResponse {
  int? code;
  String? message;
  int? ttl;
  BiliLoginQrRequestData? data;

  BiliLoginQrRequestResponse({this.code, this.message, this.ttl, this.data});

  factory BiliLoginQrRequestResponse.fromJson(Map<String, dynamic> json) {
    return BiliLoginQrRequestResponse(
      code: json['code'] as int?,
      message: json['message'] as String?,
      ttl: json['ttl'] as int?,
      data: json['data'] != null
          ? BiliLoginQrRequestData.fromJson(json['data'])
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

class BiliLoginQrRequestData {
  String? url;
  String? qrcodeKey;

  BiliLoginQrRequestData({this.url, this.qrcodeKey});

  factory BiliLoginQrRequestData.fromJson(Map<String, dynamic> json) {
    return BiliLoginQrRequestData(
      url: json['url'] as String?,
      qrcodeKey: json['qrcode_key'] as String?,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'url': url,
      'qrcode_key': qrcodeKey,
    };
  }
}
