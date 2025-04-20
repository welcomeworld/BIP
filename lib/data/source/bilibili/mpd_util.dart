import 'dart:io';

import 'package:bip/data/source/bilibili/model/bili_mpd_info.dart';

String _generateMpdContent(
    List<BiliMpdInfo> videoInfos, List<BiliMpdInfo> audioInfos, int duration,
    {int minBufferTime = 1}) {
  String videoRepresentations = '';
  for (int i = 0; i < videoInfos.length; i++) {
    videoRepresentations += '''
      <Representation id="${videoInfos[i].id}" bandwidth="${videoInfos[i].bandwidth}">
        <BaseURL>${_escapeXml(videoInfos[i].url)}</BaseURL>
      </Representation>
    ''';
  }

  String audioRepresentations = '';
  for (int i = 0; i < audioInfos.length; i++) {
    audioRepresentations += '''
      <Representation id="${audioInfos[i].id}" bandwidth="${audioInfos[i].bandwidth}">
        <BaseURL>${_escapeXml(audioInfos[i].url)}</BaseURL>
      </Representation>
    ''';
  }

  return '''<?xml version="1.0" encoding="utf-8"?>
<MPD xmlns="urn:mpeg:dash:schema:mpd:2011" type="static" mediaPresentationDuration="PT${duration}S" minBufferTime="PT${minBufferTime}S" profiles="urn:mpeg:dash:profile:isoff-on-demand:2011">
  <Period duration="PT${duration}S">
    <AdaptationSet mimeType="video/mp4" segmentAlignment="true" startWithSAP="1">
      $videoRepresentations
    </AdaptationSet>
    
    <AdaptationSet mimeType="audio/mp4" segmentAlignment="true" startWithSAP="1">
      $audioRepresentations
    </AdaptationSet>
  </Period>
</MPD>''';
}

Future<void> _writeMpdToFile(String mpdContent, String filePath) async {
  final file = File(filePath);
  await file.create(recursive: true);
  await file.writeAsString(mpdContent);
}

Future<void> createMpdFile(String filePath, List<BiliMpdInfo> videoInfos,
    List<BiliMpdInfo> audioInfos, int duration,
    {int minBufferTime = 1}) async {
  await _writeMpdToFile(
      _generateMpdContent(videoInfos, audioInfos, duration), filePath);
}

String _escapeXml(String input) {
  return input
      .replaceAll('&', '&amp;') // 替换 & 为 &amp;
      .replaceAll('<', '&lt;') // 替换 < 为 &lt;
      .replaceAll('>', '&gt;') // 替换 > 为 &gt;
      .replaceAll('"', '&quot;') // 替换 " 为 &quot;
      .replaceAll("'", '&apos;'); // 替换 ' 为 &apos;
}
