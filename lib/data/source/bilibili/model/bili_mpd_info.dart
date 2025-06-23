class BiliMpdInfo {
  String url;
  String id;
  int bandwidth;
  final String mimeType;
  final String codecs;
  final int width;
  final int height;
  final String frameRate;
  final String sar;
  final int startWithSap;
  final MpdSegmentBase segmentBase;

  BiliMpdInfo(
      this.url,
      this.id,
      this.bandwidth,
      this.mimeType,
      this.codecs,
      this.width,
      this.height,
      this.frameRate,
      this.sar,
      this.startWithSap,
      this.segmentBase);
}

class MpdSegmentBase {
  final String initialization;
  final String indexRange;

  MpdSegmentBase._(this.initialization, this.indexRange);

  factory MpdSegmentBase.fromJson(Map<String, dynamic> json) {
    return MpdSegmentBase._(
      json['Initialization'] ?? "",
      json['indexRange'] ?? "",
    );
  }
}
