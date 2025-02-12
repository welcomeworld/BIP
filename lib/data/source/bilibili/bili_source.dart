import 'package:bip/data/model/media_page_detail.dart';
import 'package:bip/data/model/user_info.dart';
import 'package:bip/data/net/web_net.dart';
import 'package:bip/data/source/bilibili/bili_explore_response.dart';
import 'package:bip/data/source/bilibili/model/bili_av_media_info_response.dart';
import 'package:bip/data/source/bilibili/model/bili_search_hot_response.dart';
import 'package:bip/data/source/bilibili/wbi_net.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/utils/common_util.dart';
import 'package:bip/utils/logger.dart';
import 'package:dio/dio.dart';

import '../../model/media_info.dart';
import '../../model/media_page_preview.dart';
import 'model/bili_av_detail_response.dart';

class BiliSource extends Source {
  @override
  String get sourceName => "哔哩哔哩";
  static const String _apiUrl = "https://api.bilibili.com/";
  static const String _homeUrl = "https://www.bilibili.com/";
  static const List<String> _typeWhiteList = ["av", "ogv", "bangumi"];
  static final Map<String, dynamic> _dmQueries = {
    "dm_img_str": "V2ViR0wgMS4wIChPcGVuR0wgRVMgMi4wIENocm9taXVtKQ",
    "dm_cover_img_str":
        "QU5HTEUgKEludGVsLCBNZXNhIEludGVsKFIpIEdyYXBoaWNzIChBREwgR1QyKSwgT3BlbkdMIDQuNilHb29nbGUgSW5jLiAoSW50ZW",
    "dm_img_inter":
        """{"ds":[{"t":2,"c":"YnB4LXBsYXllci12aWRlby1pbnB1dGJhci13cm","p":[1896,50,1927],"s":[135,1085,1330]},{"t":2,"c":"YnB4LXBsYXllci1kbS1idG4tc2VuZCBidWkgYnVpLWJ1dHRvbiBidWktZGlzYWJsZW","p":[2349,3,1583],"s":[86,331,292]}],"wh":[2217,914,3],"of":[374,748,374]}""",
    "dm_img_list": "[]",
  };

  static const String _explorePath =
      "${_apiUrl}x/web-interface/wbi/index/top/feed/rcmd";
  static final Map<String, dynamic> _exploreConstQueries = {
    "fresh_type": 4,
    "feed_version": "V8",
    "homepage_ver": "1",
    "y_num": "4",
    "ps": "16",
    "web_location": "1430650",
  };

  static const String _searchHotPath =
      "${_apiUrl}x/web-interface/wbi/search/square";
  static final Map<String, dynamic> _searchHotConstQueries = {
    "limit": 10,
    "platform": "web",
  };

  static const _avDetailPagePrefix = "${_homeUrl}video/";
  static const _avDetailPath = "${_apiUrl}x/web-interface/wbi/view/detail";
  final List<String> _exploreLastShow = [];

  static const _avMediaInfoPath = "${_apiUrl}x/player/wbi/playurl";
  static final Map<String, dynamic> _avMediaInfoConstQueries = {
    "qn": 0,
    "fnver": 0,
    "fourk": 1,
    "fnval": 4048,
    "from_client": "BROWSER",
    "is_main_page": true,
    "web_location": "1315873",
  };

  Future<void> _initHome() async {
    await WebNet().get(_homeUrl);
  }

  @override
  Future<List<MediaPagePreview>> explore(int pageNumber) async {
    List<MediaPagePreview> exploreResult = [];
    try {
      String lastShow = "";
      if (pageNumber == 1) {
        _exploreLastShow.clear();
        _initHome();
      } else {
        lastShow = _getLastShowQuery();
      }
      Map<String, dynamic> extraParameters = {};
      extraParameters.addAll(_exploreConstQueries);
      if (lastShow.isNotEmpty) {
        extraParameters["last_showlist"] = lastShow;
      }
      extraParameters["fresh_idx_1h"] = pageNumber;
      extraParameters["fetch_row"] = pageNumber * 3 + 1;
      extraParameters["fresh_idx"] = pageNumber;
      extraParameters["brush"] = pageNumber;
      var response =
          await WbiNet().get(_explorePath, queryParameters: extraParameters);
      BiliExploreResponse exploreResponse =
          BiliExploreResponse.fromJson(response.data);
      exploreResponse.data?.item.where((avItem) {
        if (avItem.id == 0) {
          _addLastShow("${avItem.gotoX}_n_${avItem.businessInfo?.srcId}");
        } else {
          _addLastShow("${avItem.gotoX}_n_${avItem.id}");
        }
        return _typeWhiteList.contains(avItem.gotoX);
      }).forEach((avItem) {
        MediaPagePreview pagePreview = MediaPagePreview();
        pagePreview.sourceName = sourceName;
        pagePreview.title = avItem.title;
        UserInfo owner = UserInfo();
        owner.name = avItem.owner?.name ?? "";
        pagePreview.owner = owner;
        pagePreview.coverPortrait = false;
        pagePreview.cover = "${avItem.pic}@640w_400h_1e_1c.webp";
        pagePreview.extras["videoType"] = avItem.gotoX;
        pagePreview.extras["bvid"] = avItem.bvid;
        pagePreview.extras["cid"] = avItem.cid;
        pagePreview.pageTime = avItem.pubDate * 1000;
        pagePreview.duration = avItem.duration;
        if (avItem.rcmdReason?.content.isNotEmpty == true) {
          pagePreview.tags.add(avItem.rcmdReason?.content ?? "");
        }
        if (avItem.isFollowed) {
          pagePreview.tags.add("已关注");
        }
        pagePreview.playCount = avItem.stat?.view ?? 0;
        exploreResult.add(pagePreview);
      });
    } catch (message) {
      Logger.logConsole("bili explore err:$message");
    }
    return exploreResult;
  }

  String _getLastShowQuery() {
    StringBuffer result = StringBuffer("");
    var index = 0;
    for (var av in _exploreLastShow) {
      if (index++ != 0) {
        result.write(",");
      }
      result.write(av);
    }
    return result.toString();
  }

  _addLastShow(String showId) {
    _exploreLastShow.add(showId);
    if (_exploreLastShow.length > 64) {
      _exploreLastShow.removeAt(0);
    }
  }

  @override
  Future<SourceApiResult<List<String>>> requestSearchHot() async {
    try {
      var response = await WbiNet()
          .get(_searchHotPath, queryParameters: _searchHotConstQueries);
      if (response.data == null || response.statusCode != 200) {
        return SourceApiResult(
          List.empty(),
          resultCode: SourceApiResult.resultNetworkFailed,
        );
      }
      final searchHotData = BiliSearchHotResponse.fromJson(response.data);
      var searchHot = searchHotData.data?.trending?.hotSearch?.map((hotSearch) {
        return hotSearch.keyword;
      }).toList();
      if (searchHot?.isNotEmpty == true) {
        return SourceApiResult(searchHot!);
      }
    } catch (message) {
      Logger.logConsole(message.toString());
      return SourceApiResult(
        List.empty(),
        resultCode: SourceApiResult.resultInnerFailed,
      );
    }
    return SourceApiResult(
      List.empty(),
      resultCode: SourceApiResult.resultSourceEmpty,
    );
  }

  @override
  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview) async {
    if (preview.extras["videoType"] == "bangumi") {
      return await _requestBangumiDetail(preview);
    } else {
      return await _requestAvDetail(preview);
    }
  }

  Future<SourceApiResult<MediaPageDetail>> _requestAvDetail(
      MediaPagePreview preview) async {
    MediaPageDetail result = MediaPageDetail.fromPreview(preview);

    final extraData = preview.extras;
    int? aid = extraData['aid'];
    String? bvid = extraData['bvid'];
    final pageUrl = "$_avDetailPagePrefix${bvid ?? "av$aid"}";
    // await WebNet().get(Uri.parse(pageUrl)); //maybe need when api denied by risk management.
    Map<String, dynamic> extraParameters = {
      "web_location": 1315873,
      "isGaiaAvoided": false,
      "need_view": 1,
    };
    if (aid != null) {
      extraParameters["aid"] = aid;
    } else {
      extraParameters["bvid"] = bvid;
    }
    extraParameters.addAll(_dmQueries);
    Options options = Options(headers: {
      "Referer": pageUrl,
      "Origin": "https://www.bilibili.com/",
    });
    try {
      var response = await WbiNet().get(_avDetailPath,
          queryParameters: extraParameters, options: options);
      if (response.data == null || response.statusCode != 200) {
        return SourceApiResult(
          result,
          resultCode: SourceApiResult.resultNetworkFailed,
        );
      }
      BiliAvDetailResponse detailResponse =
          BiliAvDetailResponse.fromJson(response.data);
      if (detailResponse.code != 0) {
        return SourceApiResult(result, resultCode: detailResponse.code);
      }
      var tagData = detailResponse.data.tags;
      var relatedData = detailResponse.data.related;
      var pageData = detailResponse.data.view;
      // map pageData to MediaPageDetail
      result.owner.name = pageData.owner.name;
      result.owner.avatar = pageData.owner.face;
      result.title = pageData.title;
      result.desc = pageData.descV2.map((v2Desc) {
        if (v2Desc.type == 1) {
          return v2Desc.rawText;
        } else {
          return "@${v2Desc.rawText}";
        }
      }).join("\n");
      result.pageTime = pageData.pubdate * 1000;
      result.cover = pageData.pic;
      result.coverPortrait = false;
      result.tags = tagData.map((tag) => tag.tagName).toList();
      result.playCount = pageData.stat.view;
      // map relatedData to MediaPagePreview
      for (var related in relatedData) {
        MediaPagePreview relatedPreview = MediaPagePreview();
        relatedPreview.extras['aid'] = related.aid;
        relatedPreview.extras['bvid'] = related.bvid;
        relatedPreview.extras["videoType"] = "av";
        relatedPreview.sourceName = sourceName;
        relatedPreview.title = related.title;
        UserInfo owner = UserInfo();
        owner.name = related.owner.name;
        owner.avatar = related.owner.face;
        relatedPreview.owner = owner;
        relatedPreview.coverPortrait = false;
        relatedPreview.cover = "${related.pic}@640w_400h_1e_1c.webp";
        relatedPreview.pageTime = related.pubdate * 1000;
        relatedPreview.duration = related.duration;
        relatedPreview.topDec = related.isUpowerExclusive ? "充电专属" : "";
        relatedPreview.playCount = related.stat.view;
        result.relatedMediaList.add(relatedPreview);
      }
      // map pages to MediaInfo
      for (var media in pageData.pages) {
        MediaInfo mediaInfo = MediaInfo();
        mediaInfo.sourceName = sourceName;
        mediaInfo.headers["User-Agent"] =
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36";
        mediaInfo.headers["Referer"] = _homeUrl;
        mediaInfo.extras["bvid"] = bvid;
        mediaInfo.extras["cid"] = media.cid;
        mediaInfo.extras["videoType"] = "av";
        mediaInfo.title = media.part;
        mediaInfo.cover = pageData.pic;
        mediaInfo.barrageUrl = "http://comment.bilibili.com/${media.cid}.xml";
        mediaInfo.duration = media.duration;
        result.playlists.putIfAbsent("default", () => []).add(mediaInfo);
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

  Future<SourceApiResult<MediaPageDetail>> _requestBangumiDetail(
      MediaPagePreview preview) async {
    throw UnimplementedError();
  }

  @override
  Future<SourceApiResult<MediaInfo>> requestMediaInfo(
      MediaInfo mediaInfo) async {
    if (mediaInfo.extras["videoType"] == "bangumi") {
      return await _requestBangumiMediaInfo(mediaInfo);
    } else {
      return await _requestAvMediaInfo(mediaInfo);
    }
  }

  String _getResolutionDesc(int resolutionId) {
    switch (resolutionId) {
      case 0:
        return "自动";
      case 16:
        return "360P";
      case 32:
        return "480P";
      case 64:
        return "720P";
      case 74:
        return "720P60";
      case 80:
        return "1080P";
      case 112:
        return "1080P+";
      case 116:
        return "1080P60";
      case 120:
        return "4K";
      case 125:
        return "HDR";
      case 126:
        return "杜比视界";
      case 127:
        return "8K";
      default:
        return "未知";
    }
  }

  String _getAudioDesc(int qualityId) {
    switch (qualityId) {
      case 30216:
        return "64K";
      case 30232:
        return "132K";
      case 30280:
        return "192K";
      case 30250:
        return "杜比全景声";
      case 30251:
        return "Hi-Res无损";
      default:
        return "未知";
    }
  }

  Future<SourceApiResult<MediaInfo>> _requestAvMediaInfo(
      MediaInfo mediaInfo) async {
    final extraData = mediaInfo.extras;
    int? aid = extraData['aid'];
    String? bvid = extraData['bvid'];
    int cid = extraData['cid'];
    final pageUrl = "$_avDetailPagePrefix${bvid ?? "av$aid"}";
    Map<String, dynamic> extraParameters = {
      "cid": cid,
    };
    if (aid != null) {
      extraParameters["avid"] = aid;
    } else {
      extraParameters["bvid"] = bvid;
    }
    extraParameters.addAll(_avMediaInfoConstQueries);
    extraParameters.addAll(_dmQueries);
    Options options = Options(headers: {
      "Referer": pageUrl,
      "Origin": "https://www.bilibili.com/",
    });
    try {
      var response = await WbiNet().get(_avMediaInfoPath,
          queryParameters: extraParameters, options: options);
      if (response.data == null || response.statusCode != 200) {
        return SourceApiResult(
          mediaInfo,
          resultCode: SourceApiResult.resultNetworkFailed,
        );
      }
      BiliAvMediaInfoResponse mediaInfoResponse =
          BiliAvMediaInfoResponse.fromJson(response.data);
      if (mediaInfoResponse.code != 0) {
        return SourceApiResult(mediaInfo, resultCode: mediaInfoResponse.code);
      }
      // map videoUrl
      for (var media in mediaInfoResponse.data.dash.video) {
        Logger.logConsole("parseVideoMedia: ${media.id} ${media.baseUrl}");
        mediaInfo.mediaQualities[_getResolutionDesc(media.id)] =
            _wrapMediaUrl(media.baseUrl);
      }
      // map audioUrl
      for (var media in mediaInfoResponse.data.dash.audio) {
        Logger.logConsole("parseAudioMedia: ${media.id} ${media.baseUrl}");
        mediaInfo.additionAudios[_getAudioDesc(media.id)] =
            _wrapMediaUrl(media.baseUrl);
      }
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

  Future<SourceApiResult<MediaInfo>> _requestBangumiMediaInfo(
      MediaInfo mediaInfo) async {
    throw UnimplementedError();
  }

  String _wrapMediaUrl(String mediaUrl) {
    return "http://localhost:8080/?url=${CommonUtil.encode64(mediaUrl)}";
  }
}
