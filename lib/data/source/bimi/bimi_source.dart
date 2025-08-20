import 'dart:convert';

import 'package:bip/data/model/media_info.dart';
import 'package:bip/data/model/media_page_detail.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/source/source.dart';
import 'package:html/dom.dart';
import 'package:html/parser.dart';

import '../../../utils/logger.dart';
import '../../model/media_type.dart';
import '../../model/user_info.dart';
import '../../net/web_net.dart';
import '../source_extra_key.dart';

class BimiSource extends Source {
  @override
  String get sourceName => "哔咪动漫";
  static const String publishUrl = "https://www.bimiacg.icu";
  static String _homeUrl = "https://www.bimiacg14.net";

  BimiSource() {
    _refreshHomeUrl();
  }

  Future<void> _refreshHomeUrl() async {
    try {
      final htmlResponse = await WebNet().get(publishUrl);
      if (htmlResponse.data != null && htmlResponse.statusCode == 200) {
        final document = parse(htmlResponse.data);
        var anchorElements = document.querySelectorAll('ul > li > a');

        for (var anchor in anchorElements) {
          if (anchor.attributes["href"]?.startsWith("https") == true) {
            _homeUrl = anchor.attributes["href"]!;
            return;
          }
        }
      }
    } catch (e) {
      Logger.logConsole("Failed to refresh home URL: $e");
    }
  }

  @override
  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview) async {
    MediaPageDetail result = MediaPageDetail.fromPreview(preview);

    final extraData = preview.extras;
    final pageUrl = extraData[SourceExtraKey.url];
    try {
      final htmlResponse = await WebNet().get(pageUrl);
      if (htmlResponse.data == null || htmlResponse.statusCode != 200) {
        return SourceApiResult(
          result,
          resultCode: SourceApiResult.resultNetworkFailed,
        );
      }
      Document detailPageItem = parse(htmlResponse.data);
      // map BiliBangumiDetailResponse to MediaPageDetail

      // map owner
      result.owner.name = sourceName;
      result.owner.avatar =
          "https://i1.hdslb.com/bfs/face/040528a1ec634b9907ba3a9ba957819bb07def06.jpg";

      // map basic info
      result.title =
          detailPageItem.querySelector("div.v_path.clearfix a.current")?.text ??
              "";
      result.desc =
          detailPageItem.querySelector("div.vod-jianjie > p")?.text ?? "";
      result.cover = _fixCover(detailPageItem
              .querySelector("div.v_pic img.lazy")
              ?.attributes["data-original"] ??
          "");
      result.coverPortrait = true;
      result.tags = detailPageItem
          .querySelectorAll("li.clearfix.fn-left > a")
          .where((element) {
        final href = element.attributes["href"]!;
        if (href.contains("search/class")) {
          return true;
        }
        return false;
      }).map((element) {
        return element.text;
      }).toList();

      final pageTimeList = detailPageItem
          .querySelectorAll("li.clearfix.fn-left")
          .where((element) {
            if (element.text.contains("开播")) {
              return true;
            }
            return false;
          })
          .map((element) {
            var originalText = element.text;
            var startIndex = originalText.indexOf("：");
            if (startIndex != -1) {
              originalText = originalText.substring(startIndex + 1);
            }
            var endIndex = originalText.indexOf("(");
            if (endIndex != -1) {
              originalText = originalText.substring(0, endIndex);
            }
            return originalText;
          })
          .first
          .split("-")
          .map((time) {
            return int.parse(time);
          })
          .toList();

      result.pageTime =
          DateTime(pageTimeList[0], pageTimeList[1], pageTimeList[2])
              .millisecondsSinceEpoch;

      result.indexShow =
          detailPageItem.querySelector("div.tit > p.p_txt em.em_num")?.text ??
              "";

      final titleList = detailPageItem.querySelectorAll("div#tab a");
      final playlists =
          detailPageItem.querySelectorAll("div.play_box ul.player_list");
      // map pages to MediaInfo
      for (var titleIndex in titleList.indexed) {
        int index = titleIndex.$1;
        String title = titleList[index].text;
        final mediaList = playlists[index].querySelectorAll("a");
        for (var media in mediaList) {
          MediaInfo mediaInfo = MediaInfo();
          mediaInfo.sourceName = sourceName;
          mediaInfo.mediaType = MediaType.bangumi;
          mediaInfo.mediaId = media.attributes["href"] ?? "";
          mediaInfo.headers["User-Agent"] =
              "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36";
          mediaInfo.headers["Referer"] = _homeUrl;
          mediaInfo.extras[SourceExtraKey.url] =
              "$_homeUrl${media.attributes["href"] ?? ""}";
          mediaInfo.title = media.text;
          mediaInfo.cover = "";
          mediaInfo.barrageUrl = "";
          mediaInfo.duration = 0;
          result.playlists.putIfAbsent(title, () => []).add(mediaInfo);
        }
      }

      // map recommend to MediaPagePreview
      for (var related
          in detailPageItem.querySelectorAll("div.love-det ul li.item")) {
        MediaPagePreview relatedPreview = MediaPagePreview();
        relatedPreview.sourceName = sourceName;
        relatedPreview.mediaType = MediaType.bangumi;
        relatedPreview.extras[SourceExtraKey.url] =
            "$_homeUrl${related.querySelector("a.img")?.attributes["href"] ?? ""}";
        relatedPreview.title =
            related.querySelector("a.img")?.attributes["title"] ?? "";
        relatedPreview.mediaPageId = relatedPreview.title;
        UserInfo owner = UserInfo();
        owner.name = sourceName;
        relatedPreview.owner = owner;
        relatedPreview.coverPortrait = true;
        relatedPreview.cover = _fixCover(
            related.querySelector("a.img img")?.attributes["data-original"] ??
                "");
        relatedPreview.score = 0;
        relatedPreview.indexShow =
            related.querySelector("div.info p span.fl")?.text ?? "";

        relatedPreview.playCount = 0;
        result.relatedMediaList.add(relatedPreview);
      }
    } catch (e, stack) {
      Logger.logConsole(stack.toString());
      Logger.logConsole(e.toString());
      return SourceApiResult(
        result,
        resultCode: SourceApiResult.resultInnerFailed,
      );
    }
    return SourceApiResult(result);
  }

  @override
  Future<SourceApiResult<MediaInfo>> requestMediaInfo(
      MediaInfo mediaInfo) async {
    final extraData = mediaInfo.extras;
    final pageUrl = extraData[SourceExtraKey.url];
    try {
      final htmlResponse = await WebNet().get(pageUrl);
      if (htmlResponse.data == null || htmlResponse.statusCode != 200) {
        return SourceApiResult(
          mediaInfo,
          resultCode: SourceApiResult.resultNetworkFailed,
        );
      }
      final htmlString = htmlResponse.data;
      final startIndex = htmlString.indexOf("player_aaaa");
      final startBracketIndex = htmlString.indexOf('{', startIndex + 1);
      int endIndex = -1;
      int bracketCount = 0;

      for (int i = startBracketIndex + 1; i < htmlString.length; i++) {
        final char = htmlString[i];
        if (char == '{') {
          bracketCount++;
        } else if (char == '}') {
          if (bracketCount == 0) {
            endIndex = i;
            break;
          }
          bracketCount--;
        }
      }

      if (endIndex <= startIndex) {
        return SourceApiResult(
          mediaInfo,
          resultCode: SourceApiResult.resultInnerFailed,
        );
      }

      final configString =
          htmlString.substring(startBracketIndex, endIndex + 1);
      final configMap = jsonDecode(configString) as Map<String, dynamic>;

      final playerUrl = _genPath(configMap["from"], configMap["url"], pageUrl);
      final playerResponse = await WebNet().get(playerUrl);
      final playerDoc = parse(playerResponse.data);
      var mediaUrl =
          playerDoc.querySelector("video#video source")?.attributes["src"] ??
              "";
      if (!mediaUrl.startsWith("http") && mediaUrl.contains("m3u8")) {
        mediaUrl =
            mediaUrl.replaceFirst("./m3u8", "$_homeUrl/static/danmu/m3u8");
      }
      mediaInfo.mediaQualities["默认分辨率"] = mediaUrl;
      mediaInfo.qualityKey = "默认分辨率";
      mediaInfo.headers["User-Agent"] =
          "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36";
      mediaInfo.headers["Referer"] = _homeUrl;
    } catch (e, stack) {
      Logger.logConsole(stack.toString());
      Logger.logConsole(e.toString());
      return SourceApiResult(
        mediaInfo,
        resultCode: SourceApiResult.resultInnerFailed,
      );
    }
    return SourceApiResult(mediaInfo);
  }

  @override
  Future<SourceApiResult<List<MediaPagePreview>>> search(
      String keyword, int pageNumber,
      {MediaType searchType = MediaType.video}) async {
    final bool typeMatch =
        searchType == MediaType.video || searchType == MediaType.bangumi;
    if (!typeMatch) {
      return SourceApiResult([], resultCode: SourceApiResult.resultSourceEmpty);
    }
    List<MediaPagePreview> exploreResult = [];
    try {
      final htmlResponse = await WebNet().get(
          "$_homeUrl/index.php/vod/search/page/$pageNumber/wd/$keyword.html");
      Logger.logConsole("$sourceName search content:${htmlResponse.data}");
      final searchItemList = parse(htmlResponse.data)
          .querySelectorAll("div.main div.v_tb ul li.item");
      if (searchItemList.isEmpty) {
        Logger.logConsole("$sourceName search empty");
        return SourceApiResult([],
            resultCode: SourceApiResult.resultSourceEmpty);
      }
      Logger.logConsole("$sourceName search ${searchItemList.length}");

      for (var avItem in searchItemList) {
        exploreResult.add(_mapBangumiItem(avItem));
      }
    } catch (message) {
      Logger.logConsole("$sourceName search err:$message");
    }
    return SourceApiResult(exploreResult);
  }

  // https://www.bimiacg14.net/static/player/${fromKey}.js?2023918
  String _genPath(String fromKey, String urlKey, String pageUrl) {
    String fromResult = "play";

    switch (fromKey) {
      case "pic":
      case "miui":
      case "danmakk":
        fromResult = "pic";
        break;
      case "alos":
      case "bimi":
        if (urlKey.contains(".m3u8")) {
          fromResult = "pic";
        }
        break;
      case "special":
      case "zhilian":
      case "ckplayer":
        if (urlKey.contains(".m3u8")) {
          fromResult = "m3u8";
        }
        break;
      case "kzyun":
        fromResult = "kzyun";
        break;
      case "renrenmi":
        fromResult = "rrm";
        break;
      case "dplayer":
      case "zkm3u8":
      case "qq":
      case "qiyi":
        if (urlKey.contains(".mp4")) {
          fromResult = "dm";
        } else if (urlKey.contains(".m3u8")) {
          fromResult = "m3u8";
        } else {
          fromResult = "dm";
        }
        break;
      case "cqyunm3u8":
        if (urlKey.contains(".m3u8")) {
          fromResult = "m3u8";
        } else {
          fromResult = "dm";
        }
        break;
      case "youku":
        fromResult = "qy";
        break;
      case "jdym3u8":
        if (urlKey.contains(".mp4")) {
          fromResult = "dm";
        } else {
          fromResult = "m3u8";
        }
        break;
      case "piaoquan":
        fromResult = "piaoquan";
        break;
      case "189Cloud":
        fromResult = "dm";
        break;
      case "baidu":
      case "wei":
      case "copyright":
      case "qihoo":
      case "qzone":
      case "aliplay":
        break;
    }

    return "$_homeUrl/static/danmu/$fromResult.php?url=$urlKey&myurl=$pageUrl";
  }

  MediaPagePreview _mapBangumiItem(Element item) {
    MediaPagePreview pagePreview = MediaPagePreview();
    pagePreview.mediaType = MediaType.bangumi;
    pagePreview.sourceName = sourceName;
    pagePreview.title = item.querySelector("div.info a")?.text ?? "";
    pagePreview.mediaPageId = pagePreview.title;
    UserInfo owner = UserInfo();
    owner.name = sourceName;
    pagePreview.owner = owner;
    pagePreview.coverPortrait = true;
    pagePreview.cover = _fixCover(
        item.querySelector("a.img img")?.attributes["data-original"] ?? "");
    pagePreview.extras[SourceExtraKey.url] =
        "$_homeUrl${item.querySelector("a.img")?.attributes["href"] ?? ""}";
    pagePreview.indexShow = item.querySelector("div.info p span")?.text ?? "";
    return pagePreview;
  }

  String _fixCover(String cover) {
    if (!cover.startsWith("http")) {
      return "$_homeUrl$cover";
    }
    return cover;
  }
}
