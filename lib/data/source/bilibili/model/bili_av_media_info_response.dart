class BiliAvMediaInfoResponse {
  int code;
  String message;
  int ttl;
  VideoData data;

  BiliAvMediaInfoResponse._(this.code, this.message, this.ttl, this.data);

  factory BiliAvMediaInfoResponse.fromJson(Map<String, dynamic> json) {
    return BiliAvMediaInfoResponse._(
      json['code'] ?? 0,
      json['message'] ?? "",
      json['ttl'] ?? 0,
      VideoData.fromJson(json['data']),
    );
  }
}

class VideoData {
  final int videoCodecId;
  final Dash dash;

  VideoData._(this.videoCodecId, this.dash);

  factory VideoData.fromJson(Map<String, dynamic> json) {
    return VideoData._(
      json['video_codecid'] ?? 0,
      Dash.fromJson(json['dash'] ?? {}),
    );
  }
}

class Dash {
  final int duration;
  final List<Media> video;
  final List<Media> audio;
  final DolbyAudio? dolby;
  final FlacAudio? flac;

  Dash._(this.duration, this.video, this.audio, this.dolby, this.flac);

  factory Dash.fromJson(Map<String, dynamic> json) {
    return Dash._(
      json['duration'] ?? 0,
      (json['video'] as List?)?.map((v) => Media.fromJson(v)).toList() ?? [],
      (json['audio'] as List?)?.map((a) => Media.fromJson(a)).toList() ?? [],
      json['dolby'] == null ? null : DolbyAudio.fromJson(json['dolby']),
      json['flac'] == null ? null : FlacAudio.fromJson(json['flac']),
    );
  }
}

class Media {
  final int id;
  final String baseUrl;
  final List<String>? backupUrl;
  final int bandwidth;
  final String mimeType;
  final String codecs;
  final int width;
  final int height;
  final String frameRate;
  final String sar;
  final int startWithSap;
  final SegmentBase segmentBase;
  final int codecid;

  Media._(
      this.id,
      this.baseUrl,
      this.backupUrl,
      this.bandwidth,
      this.mimeType,
      this.codecs,
      this.width,
      this.height,
      this.frameRate,
      this.sar,
      this.startWithSap,
      this.segmentBase,
      this.codecid);

  factory Media.fromJson(Map<String, dynamic> json) {
    return Media._(
      json['id'] ?? 0,
      json['baseUrl'] ?? "",
      (json['backupUrl'] as List?)?.map((url) => url.toString()).toList(),
      json['bandwidth'] ?? 0,
      json['mimeType'] ?? "",
      json['codecs'] ?? "",
      json['width'] ?? 0,
      json['height'] ?? 0,
      json['frameRate'] ?? "0",
      json['sar'] ?? "1:1",
      json['startWithSap'] ?? 0,
      SegmentBase.fromJson(json['SegmentBase'] ?? {}),
      json['codecid'] ?? 0,
    );
  }
}

class DolbyAudio {
  final Media audio;

  DolbyAudio._(this.audio);

  factory DolbyAudio.fromJson(Map<String, dynamic> json) {
    return DolbyAudio._(Media.fromJson(json['audio'] ?? {}));
  }
}

class FlacAudio {
  final Media audio;

  FlacAudio._(this.audio);

  factory FlacAudio.fromJson(Map<String, dynamic> json) {
    return FlacAudio._(Media.fromJson(json['audio'] ?? {}));
  }
}

class SegmentBase {
  final String initialization;
  final String indexRange;

  SegmentBase._(this.initialization, this.indexRange);

  factory SegmentBase.fromJson(Map<String, dynamic> json) {
    return SegmentBase._(
      json['Initialization'] ?? "",
      json['indexRange'] ?? "",
    );
  }
}
