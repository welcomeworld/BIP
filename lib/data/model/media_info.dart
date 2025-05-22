import 'media_type.dart';

class MediaInfo {
  String sourceName = "";
  MediaType mediaType = MediaType.video;
  Map<String, dynamic> extras = {};
  Map<String, String> headers = {};
  String title = "";
  String cover = "";
  String barrageUrl = "";
  int recordPosition = 0;
  int duration = 0;
  String topDec = "";
  String mediaPath = "";
  Map<String, String> mediaQualities = {};
  String qualityKey = "";
  Map<String, String> additionSubtitles = {};
  String additionSubtitleKey = "";
  Map<String, String> additionAudios = {};
  String additionAudioKey = "";
}
