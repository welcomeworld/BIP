import 'dart:convert';

import 'package:bip/data/model/media_info.dart';
import 'package:bip/data/model/media_page_detail.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/model/media_type.dart';
import 'package:bip/data/net/web_net.dart';
import 'package:bip/data/source/source.dart';
import 'package:html/dom.dart';
import 'package:html/parser.dart';

import '../../../utils/logger.dart';
import '../../model/user_info.dart';
import '../source_extra_key.dart';

class GugufanSource extends Source {
  @override
  String get sourceName => "咕咕番";
  static const String publishUrl = "https://www.gugufan.xyz/";
  static String _homeUrl = "https://www.gugu3.com";

  GugufanSource() {
    _refreshHomeUrl();
  }

  Future<void> _refreshHomeUrl() async {
    try {
      final htmlResponse = await WebNet().get(publishUrl);
      if (htmlResponse.data != null && htmlResponse.statusCode == 200) {
        final document = parse(htmlResponse.data);
        var anchorElements = document.querySelectorAll('a.modal__button');

        // 查找文本内容为“进入咕咕网页”的 <a> 标签
        for (var anchor in anchorElements) {
          if (anchor.text == '进入咕咕网页' && anchor.attributes["href"] != null) {
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
          detailPageItem.querySelector(".slide-info-title.hide")?.text ?? "";
      result.desc = detailPageItem.querySelector("#height_limit")?.text ?? "";
      result.cover = _fixCover(detailPageItem
              .querySelector("div.detail-pic img")
              ?.attributes["data-src"] ??
          "");
      result.coverPortrait = true;
      result.playCount = 0;
      result.score = double.parse(
          detailPageItem.querySelector("div.play-score > div.fraction")?.text ??
              "0.0");
      result.tags = detailPageItem
          .querySelectorAll("div.slide-info.hide > a")
          .where((element) {
        final href = element.attributes["href"]!;
        if (href.contains("search/class")) {
          return true;
        }
        return false;
      }).map((element) {
        return element.text;
      }).toList();
      int year = int.parse(detailPageItem
              .querySelector(
                  "div.slide-info.hide > span.slide-info-remarks > a")
              ?.text ??
          "0");
      result.pageTime = DateTime(year, 1, 1).millisecondsSinceEpoch;

      result.indexShow = detailPageItem
              .querySelector("div.slide-info.hide > span.slide-info-remarks")
              ?.text ??
          "";

      final titleList = detailPageItem.querySelectorAll(
          "div.anthology.wow.fadeInUp.animated > div.anthology-tab a");
      final playlists = detailPageItem.querySelectorAll(
          "div.anthology.wow.fadeInUp.animated > div.anthology-list > div.anthology-list-box");
      // map pages to MediaInfo
      for (var titleIndex in titleList.indexed) {
        int index = titleIndex.$1;
        String title = titleList[index].text;
        final mediaList = playlists[index].querySelectorAll("a");
        for (var media in mediaList) {
          MediaInfo mediaInfo = MediaInfo();
          mediaInfo.sourceName = sourceName;
          mediaInfo.mediaType = MediaType.bangumi;
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
      for (var related in detailPageItem.querySelectorAll(
          "div.box-width > div.flex.wrap > div.public-list-box div.public-list-div > a.public-list-exp")) {
        MediaPagePreview relatedPreview = MediaPagePreview();
        relatedPreview.sourceName = sourceName;
        relatedPreview.mediaType = MediaType.bangumi;
        relatedPreview.extras[SourceExtraKey.url] =
            "$_homeUrl${related.attributes["href"] ?? ""}";
        relatedPreview.title = related.attributes["title"] ?? "";
        UserInfo owner = UserInfo();
        owner.name = sourceName;
        relatedPreview.owner = owner;
        relatedPreview.coverPortrait = true;
        relatedPreview.cover = _fixCover(
            related.querySelector("img")?.attributes["data-src"] ?? "");

        relatedPreview.topDec = "";
        relatedPreview.tags
            .add(related.querySelector("span.public-prt.hide")?.text ?? "");
        relatedPreview.score = 0;
        relatedPreview.indexShow =
            related.querySelector("span.public-list-prb.hide")?.text ?? "";

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

      final playerUrl =
          "$_homeUrl/addons/dp/player/dp.php?key=0&id=${configMap["id"]}&url=${configMap["url"]}";
      final playerResponse = await WebNet().get(playerUrl);
      String playerString = playerResponse.data;
      final urlStartIndex = playerString.indexOf("url\":") + 7;
      final urlEndIndex = playerString.indexOf("\"", urlStartIndex);
      final mediaUrl = playerString.substring(urlStartIndex, urlEndIndex);
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
      final searchItemList =
          parse(htmlResponse.data).querySelectorAll("div.public-list-box");
      if (searchItemList.isEmpty) {
        return SourceApiResult([],
            resultCode: SourceApiResult.resultSourceEmpty);
      }
      for (var avItem in searchItemList) {
        exploreResult.add(_mapBangumiItem(avItem));
      }
    } catch (message) {
      Logger.logConsole("$sourceName search err:$message");
    }
    return SourceApiResult(exploreResult);
  }

  MediaPagePreview _mapBangumiItem(Element item) {
    MediaPagePreview pagePreview = MediaPagePreview();
    pagePreview.mediaType = MediaType.bangumi;
    pagePreview.sourceName = sourceName;
    pagePreview.title =
        item.querySelector("div.right div.thumb-content div.thumb-txt")?.text ??
            "";
    UserInfo owner = UserInfo();
    owner.name = sourceName;
    pagePreview.owner = owner;
    pagePreview.coverPortrait = true;
    pagePreview.cover = _fixCover(item
            .querySelector("div.left a.public-list-exp img")
            ?.attributes["data-src"] ??
        "");
    pagePreview.extras[SourceExtraKey.url] =
        "$_homeUrl${item.querySelector("div.left a.public-list-exp")?.attributes["href"] ?? ""}";
    pagePreview.topDec = "";
    final tagJoin = item
        .querySelectorAll("div.right div.thumb-content div.thumb-else a")
        .where((element) {
          final href = element.attributes["href"]!;
          if (href.contains("search/year")) {
            int year = int.parse(element.text);
            pagePreview.pageTime = DateTime(year, 1, 1).millisecondsSinceEpoch;
          } else if (href.contains("search/class")) {
            return true;
          }
          return false;
        })
        .map((element) {
          return element.text;
        })
        .take(3)
        .join("/");
    pagePreview.tags.add(tagJoin);
    pagePreview.score = 0;
    pagePreview.indexShow = item
            .querySelector("div.left a.public-list-exp span.public-list-prb")
            ?.text ??
        "";
    return pagePreview;
  }

  String _fixCover(String cover) {
    if (!cover.startsWith("http")) {
      return "$_homeUrl$cover";
    }
    return cover;
  }
}
