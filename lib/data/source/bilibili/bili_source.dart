import 'dart:convert';
import 'dart:math';

import 'package:bip/data/model/media_page_detail.dart';
import 'package:bip/data/model/media_type.dart';
import 'package:bip/data/model/user_info.dart';
import 'package:bip/data/net/web_net.dart';
import 'package:bip/data/persistence/kv_store.dart';
import 'package:bip/data/source/bilibili/bili_explore_response.dart';
import 'package:bip/data/source/bilibili/model/bili_av_media_info_response.dart';
import 'package:bip/data/source/bilibili/model/bili_bangumi_detail_response.dart';
import 'package:bip/data/source/bilibili/model/bili_login_qr_request_response.dart';
import 'package:bip/data/source/bilibili/model/bili_mpd_info.dart';
import 'package:bip/data/source/bilibili/model/bili_search_hot_response.dart';
import 'package:bip/data/source/bilibili/model/bili_user_nav_info_response.dart';
import 'package:bip/data/source/bilibili/mpd_util.dart';
import 'package:bip/data/source/bilibili/wbi_net.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/data/source/source_extra_key.dart';
import 'package:bip/utils/common_util.dart';
import 'package:bip/utils/constant.dart';
import 'package:bip/utils/logger.dart';
import 'package:dio/dio.dart';
import 'package:path_provider/path_provider.dart';

import '../../model/media_info.dart';
import '../../model/media_page_preview.dart';
import 'model/bili_av_detail_response.dart';
import 'model/bili_bangumi_recommend_response.dart';
import 'model/bili_login_qr_validate_response.dart';
import 'model/bili_search_bangumi_type.dart';
import 'model/bili_search_video_type.dart';
import 'model/bili_type_search_response.dart';

class BiliSource extends Source {
  @override
  String get sourceName => "哔哩哔哩";
  static const String _apiUrl = "https://api.bilibili.com/";
  static const String _passportUrl = "https://passport.bilibili.com/";
  static const String _homeUrl = "https://www.bilibili.com/";
  static const String _typeAv = "av";
  static const String _typeBangumi = "bangumi";
  static const String _typeOgv = "ogv"; //边栏？
  static const String _typeKeTang = "ketang";
  static const List<String> _typeWhiteList = [_typeAv, _typeOgv, _typeBangumi];
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

  BiliSource() {
    final userCache = KvStore.getSp().getString(Constant.kvKeyBiliUser);
    if (userCache != null) {
      _userInfo = UserInfo.fromJson(jsonDecode(userCache));
    }
  }

  String _searchQvId = "";
  static const String _searchAllPath =
      "${_apiUrl}x/web-interface/wbi/search/all/v2";
  static const String _searchTypePath =
      "${_apiUrl}x/web-interface/wbi/search/type";

  static const _avDetailPagePrefix = "${_homeUrl}video/";
  static const _avDetailPath = "${_apiUrl}x/web-interface/wbi/view/detail";
  final List<String> _exploreLastShow = [];

  static const _bangumiPagePrefix = "${_homeUrl}bangumi/play/ss";
  static const _bangumiDetailPath = "${_apiUrl}pgc/view/web/season";
  static const _bangumiRecommendPath =
      "${_apiUrl}pgc/season/web/related/recommend";

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

  static const _loginQrRequestPath =
      "${_passportUrl}x/passport-login/web/qrcode/generate";
  String _qrLoginTempAuth = "";
  static const _loginQrValidatePath =
      "${_passportUrl}x/passport-login/web/qrcode/poll";
  static const _userNavInfoPath = "${_apiUrl}x/web-interface/nav";

  UserInfo? _userInfo;

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
        pagePreview.mediaType = _mapMediaType(avItem.gotoX);
        pagePreview.title = avItem.title;
        UserInfo owner = UserInfo();
        owner.name = avItem.owner?.name ?? "";
        pagePreview.owner = owner;
        pagePreview.coverPortrait = false;
        pagePreview.cover = "${avItem.pic}@640w_400h_1e_1c.webp";
        pagePreview.extras[SourceExtraKey.bvid] = avItem.bvid;
        pagePreview.extras[SourceExtraKey.cid] = avItem.cid;
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

  @override
  bool get hasAccount => true;

  @override
  UserInfo? get accountInfo {
    return _userInfo;
  }

  @override
  Future<String> requestLoginQr() async {
    var response = await WebNet().get(_loginQrRequestPath);
    BiliLoginQrRequestResponse loginQrRequestResponse =
        BiliLoginQrRequestResponse.fromJson(response.data);
    _qrLoginTempAuth = loginQrRequestResponse.data?.qrcodeKey ?? "";
    return loginQrRequestResponse.data?.url ?? "";
  }

  @override
  Future<SourceLoginResult> validateLoginQr() async {
    Map<String, dynamic> extraParameters = {};
    extraParameters["qrcode_key"] = _qrLoginTempAuth;
    var response = await WebNet()
        .get(_loginQrValidatePath, queryParameters: extraParameters);

    BiliLoginQrValidateResponse loginQrValidateResponse =
        BiliLoginQrValidateResponse.fromJson(response.data);
    final loginCode = loginQrValidateResponse.data?.code ?? -1;
    final loginResult = switch (loginCode) {
      0 => SourceLoginResult.success,
      86101 => SourceLoginResult.continueWait,
      86090 => SourceLoginResult.continueWait,
      86038 => SourceLoginResult.timeout,
      int() => SourceLoginResult.failed,
    };
    if (loginResult == SourceLoginResult.success) {
      var navResponse = await WebNet().get(_userNavInfoPath);
      BiliUserNavInfoResponse biliUserNavInfoResponse =
          BiliUserNavInfoResponse.fromJson(navResponse.data);
      if (biliUserNavInfoResponse.data != null) {
        final userData = biliUserNavInfoResponse.data!;
        _userInfo = UserInfo();
        _userInfo?.name = userData.uname!;
        _userInfo?.avatar = userData.face!;
        KvStore.getSp()
            .setString(Constant.kvKeyBiliUser, jsonEncode(_userInfo));
      }
    }
    return loginResult;
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
  Future<SourceApiResult<List<MediaPagePreview>>> search(
      String keyword, int pageNumber,
      {MediaType searchType = MediaType.video}) async {
    List<MediaPagePreview> exploreResult = [];
    final bool searchAll = searchType == MediaType.video && pageNumber == 1;
    try {
      Map<String, dynamic> extraParameters = {};
      if (searchAll) {
        _searchQvId = _randomId(length: 32);
      } else {
        extraParameters["search_type"] = searchType.typeString;
        extraParameters["page"] = pageNumber;
        extraParameters["dynamic_offset"] = (pageNumber - 1) * 20;
      }
      extraParameters["keyword"] = keyword;
      extraParameters["qv_id"] = _searchQvId;

      Options options = Options(headers: {
        "Referer": "https://www.bilibili.com/",
        "Origin": "https://www.bilibili.com/",
      });
      var response = await WbiNet().get(
          searchAll ? _searchAllPath : _searchTypePath,
          queryParameters: extraParameters,
          options: options);
      BiliTypeSearchResponse searchResponse =
          BiliTypeSearchResponse.fromJson(response.data);
      if (searchAll) {
        searchResponse.data?.result?.forEach((allItem) {
          BiliSearchAllType allTypeSearchResponse =
              BiliSearchAllType.fromJson(allItem);
          if (allTypeSearchResponse.resultType == MediaType.video.typeString) {
            allTypeSearchResponse.data
                ?.map((item) => BiliSearchVideoType.fromJson(item))
                .forEach((avItem) {
              exploreResult.add(_mapVideoItem(avItem));
            });
          } else if (allTypeSearchResponse.resultType ==
              MediaType.bangumi.typeString) {
            allTypeSearchResponse.data
                ?.map((item) => BiliSearchBangumiType.fromJson(item))
                .forEach((avItem) {
              exploreResult.add(_mapBangumiItem(avItem));
            });
          }
        });
      } else {
        if (searchType == MediaType.video) {
          searchResponse.data?.result
              ?.map((item) => BiliSearchVideoType.fromJson(item))
              .forEach((avItem) {
            exploreResult.add(_mapVideoItem(avItem));
          });
        } else if (searchType == MediaType.bangumi) {
          searchResponse.data?.result
              ?.map((item) => BiliSearchBangumiType.fromJson(item))
              .forEach((avItem) {
            exploreResult.add(_mapBangumiItem(avItem));
          });
        }
      }
    } catch (message) {
      Logger.logConsole("bili search err:$message");
    }
    return SourceApiResult(exploreResult);
  }

  MediaPagePreview _mapVideoItem(BiliSearchVideoType item) {
    MediaPagePreview pagePreview = MediaPagePreview();
    pagePreview.sourceName = sourceName;
    pagePreview.mediaType = MediaType.video;
    pagePreview.title = item.title!;
    UserInfo owner = UserInfo();
    owner.name = item.author ?? "";
    pagePreview.owner = owner;
    pagePreview.coverPortrait = false;
    pagePreview.cover = "${item.pic}@640w_400h_1e_1c.webp";
    pagePreview.extras[SourceExtraKey.bvid] = item.bvid;
    pagePreview.pageTime = item.pubdate! * 1000;
    pagePreview.topDec = item.isChargeVideo == 1 ? "充电专属" : "";
    pagePreview.duration = _parseDuration(
        item.duration?.isNotEmpty == true ? item.duration! : "0");
    if (item.typename?.isNotEmpty == true) {
      pagePreview.tags.add(item.typename ?? "");
    }
    if (item.type == _typeKeTang) {
      pagePreview.tags.add("课堂");
    }
    pagePreview.playCount = item.play ?? 0;
    return pagePreview;
  }

  MediaPagePreview _mapBangumiItem(BiliSearchBangumiType item) {
    MediaPagePreview pagePreview = MediaPagePreview();
    pagePreview.mediaType = MediaType.bangumi;
    pagePreview.sourceName = sourceName;
    pagePreview.title = item.title!;
    UserInfo owner = UserInfo();
    owner.name = item.author ?? "";
    pagePreview.owner = owner;
    pagePreview.coverPortrait = true;
    pagePreview.cover = "${item.pic}@480w_640h_1e_1c.webp";
    pagePreview.extras[SourceExtraKey.ssid] = item.ssid;
    pagePreview.pageTime = (item.pubtime ?? 0) * 1000;
    pagePreview.topDec = item.angleTitle ?? item.typename ?? "";
    if (item.styles?.isNotEmpty == true) {
      pagePreview.tags.add(item.styles ?? "");
    }
    pagePreview.score = item.score;
    pagePreview.indexShow = item.indexShow;
    return pagePreview;
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
    if (preview.mediaType == MediaType.bangumi) {
      return await _requestBangumiDetail(preview);
    } else {
      return await _requestAvDetail(preview);
    }
  }

  Future<SourceApiResult<MediaPageDetail>> _requestAvDetail(
      MediaPagePreview preview) async {
    MediaPageDetail result = MediaPageDetail.fromPreview(preview);

    final extraData = preview.extras;
    int? aid = extraData[SourceExtraKey.aid];
    String? bvid = extraData[SourceExtraKey.bvid];
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
        relatedPreview.extras[SourceExtraKey.aid] = related.aid;
        relatedPreview.extras[SourceExtraKey.bvid] = related.bvid;
        relatedPreview.mediaType = MediaType.video;
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
        mediaInfo.mediaType = MediaType.video;
        mediaInfo.headers["User-Agent"] =
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36";
        mediaInfo.headers["Referer"] = _homeUrl;
        mediaInfo.extras[SourceExtraKey.bvid] = bvid;
        mediaInfo.extras[SourceExtraKey.cid] = media.cid;
        mediaInfo.title = media.part;
        mediaInfo.cover = pageData.pic;
        mediaInfo.barrageUrl = "http://comment.bilibili.com/${media.cid}.xml";
        mediaInfo.duration = media.duration;
        result.playlists.putIfAbsent("选集", () => []).add(mediaInfo);
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
    MediaPageDetail result = MediaPageDetail.fromPreview(preview);

    final extraData = preview.extras;
    int? ssId = extraData[SourceExtraKey.ssid];
    final pageUrl = "$_bangumiPagePrefix$ssId";
    // await WebNet().get(Uri.parse(pageUrl)); //maybe need when api denied by risk management.
    Map<String, dynamic> extraParameters = {};
    extraParameters["season_id"] = ssId;
    Options options = Options(headers: {
      "Referer": pageUrl,
      "Origin": "https://www.bilibili.com/",
    });
    try {
      var response = await WbiNet().get(_bangumiDetailPath,
          queryParameters: extraParameters, options: options);
      if (response.data == null || response.statusCode != 200) {
        return SourceApiResult(
          result,
          resultCode: SourceApiResult.resultNetworkFailed,
        );
      }
      BiliBangumiDetailResponse detailResponse =
          BiliBangumiDetailResponse.fromJson(response.data);
      if (detailResponse.code != 0) {
        return SourceApiResult(result, resultCode: detailResponse.code);
      }

      // map BiliBangumiDetailResponse to MediaPageDetail
      var bangumiData = detailResponse.result;

      // map owner
      result.owner.name = bangumiData.upInfo.uname;
      result.owner.avatar = bangumiData.upInfo.avatar;

      // map basic info
      result.title = bangumiData.title;
      result.desc = bangumiData.evaluate;
      result.pageTime = bangumiData.pubTime * 1000;
      result.cover = bangumiData.cover;
      result.coverPortrait = true;
      result.tags = bangumiData.styles;
      result.playCount = bangumiData.stat.views;
      result.score = bangumiData.rating.score;
      result.indexShow = bangumiData.newEp.desc;

      // map pages to MediaInfo
      for (var media in bangumiData.episodes) {
        MediaInfo mediaInfo = MediaInfo();
        mediaInfo.sourceName = sourceName;
        mediaInfo.mediaType = MediaType.bangumi;
        mediaInfo.headers["User-Agent"] =
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36";
        mediaInfo.headers["Referer"] = _homeUrl;
        mediaInfo.extras[SourceExtraKey.bvid] = media.bvid;
        mediaInfo.extras[SourceExtraKey.cid] = media.cid;
        mediaInfo.title = media.showTitle;
        mediaInfo.cover = media.cover;
        mediaInfo.barrageUrl = "http://comment.bilibili.com/${media.cid}.xml";
        mediaInfo.duration = media.duration;
        result.playlists.putIfAbsent("选集", () => []).add(mediaInfo);
      }

      // map sections to MediaInfo
      for (var section in bangumiData.section) {
        final sectionTitle = section.title;
        for (var media in section.episodes) {
          MediaInfo mediaInfo = MediaInfo();
          mediaInfo.sourceName = sourceName;
          mediaInfo.mediaType = MediaType.bangumi;
          mediaInfo.headers["User-Agent"] =
              "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36";
          mediaInfo.headers["Referer"] = _homeUrl;
          mediaInfo.extras[SourceExtraKey.bvid] = media.bvid;
          mediaInfo.extras[SourceExtraKey.cid] = media.cid;
          mediaInfo.title = media.showTitle;
          mediaInfo.cover = media.cover;
          mediaInfo.barrageUrl = "http://comment.bilibili.com/${media.cid}.xml";
          mediaInfo.duration = media.duration;
          result.additionPlaylists
              .putIfAbsent(sectionTitle, () => [])
              .add(mediaInfo);
        }
      }

      // map recommend to MediaPagePreview
      var recommendNetResponse = await WbiNet().get(_bangumiRecommendPath,
          queryParameters: extraParameters, options: options);
      if (recommendNetResponse.data == null || recommendNetResponse.statusCode != 200) {
        return SourceApiResult(
          result,
          resultCode: SourceApiResult.resultNetworkFailed,
        );
      }
      BiliBangumiRecommendResponse recommendResponse =
      BiliBangumiRecommendResponse.fromJson(recommendNetResponse.data);
      if (detailResponse.code != 0) {
        return SourceApiResult(result, resultCode: detailResponse.code);
      }
      for (var related in recommendResponse.data.season) {
        MediaPagePreview relatedPreview = MediaPagePreview();
        relatedPreview.sourceName = sourceName;
        relatedPreview.mediaType = MediaType.bangumi;
        relatedPreview.extras[SourceExtraKey.ssid] = related.seasonId;
        relatedPreview.title = related.title;
        UserInfo owner = UserInfo();
        owner.name = "哔哩哔哩番剧";
        relatedPreview.owner = owner;
        relatedPreview.coverPortrait = true;
        relatedPreview.cover = "${related.cover}@480w_640h_1e_1c.webp";

        relatedPreview.topDec = related.badge;
        relatedPreview.tags = related.styles.map((tag) => tag.name).toList();
        relatedPreview.score = related.rating.score;
        relatedPreview.indexShow = related.newEp.indexShow;

        relatedPreview.playCount = related.stat.view;
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
    // if (mediaInfo.mediaType == MediaType.bangumi) {
    //   return await _requestBangumiMediaInfo(mediaInfo);
    // } else {
    return await _requestAvMediaInfo(mediaInfo);
    // }
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

  int _getRecommendedBandwidth(int resolutionId) {
    switch (resolutionId) {
      case 0: // 自动
        return 0; // 自动模式不固定带宽
      case 16: // 360P
        return 500000; // 0.5 Mbps
      case 32: // 480P
        return 1000000; // 1 Mbps
      case 64: // 720P
        return 2500000; // 2.5 Mbps
      case 74: // 720P60
        return 3500000; // 3.5 Mbps
      case 80: // 1080P
        return 4500000; // 4.5 Mbps
      case 112: // 1080P+
        return 6000000; // 6 Mbps
      case 116: // 1080P60
        return 7000000; // 7 Mbps
      case 120: // 4K
        return 15000000; // 15 Mbps
      case 125: // HDR
        return 20000000; // 20 Mbps
      case 126: // 杜比视界
        return 25000000; // 25 Mbps
      case 127: // 8K
        return 50000000; // 50 Mbps
      default: // 未知分辨率
        return 0; // 返回负数表示未知分辨率
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

  int _getRecommendedAudioBandwidth(int qualityId) {
    switch (qualityId) {
      case 30216: // 64K
        return 64000; // 64 kbps
      case 30232: // 132K
        return 132000; // 132 kbps
      case 30280: // 192K
        return 192000; // 192 kbps
      case 30250: // 杜比全景声
        return 768000; // 假设杜比全景声的平均比特率为 768 kbps
      case 30251: // Hi-Res无损
        return 1411000; // 假设 Hi-Res 无损音频的平均比特率为 1411 kbps（CD 音质）
      default: // 未知
        return 0;
    }
  }

  Future<SourceApiResult<MediaInfo>> _requestAvMediaInfo(
      MediaInfo mediaInfo) async {
    final extraData = mediaInfo.extras;
    int? aid = extraData[SourceExtraKey.aid];
    String? bvid = extraData[SourceExtraKey.bvid];
    int cid = extraData[SourceExtraKey.cid];
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
      var tempDir = (await getTemporaryDirectory()).path;
      final mpdPath = "$tempDir/bili/$cid.mpd";
      // map videoUrl
      final videoList =
          mediaInfoResponse.data.dash.video.indexed.map((mediaEntry) {
        final index = mediaEntry.$1;
        final media = mediaEntry.$2;
        mediaInfo.mediaQualities["${index + 1}"] = _getResolutionDesc(media.id);
        return BiliMpdInfo(_wrapMediaUrl(media.baseUrl),
            _getResolutionDesc(media.id), _getRecommendedBandwidth(media.id));
      }).toList();

      // map audioUrl
      final audioList =
          mediaInfoResponse.data.dash.audio.indexed.map((mediaEntry) {
        final index = mediaEntry.$1;
        final media = mediaEntry.$2;
        mediaInfo.additionAudios["${index + 1}"] = _getAudioDesc(media.id);
        return BiliMpdInfo(_wrapMediaUrl(media.baseUrl),
            _getAudioDesc(media.id), _getRecommendedAudioBandwidth(media.id));
      }).toList();
      final duration = mediaInfoResponse.data.dash.duration;
      await createMpdFile(mpdPath, videoList, audioList, duration);
      mediaInfo.mediaPath = "file://$mpdPath";
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

  int _parseDuration(String duration) {
    if (duration.isEmpty) {
      return 0;
    }
    var durationList = duration.split(":");
    if (durationList.length == 3) {
      return int.parse(durationList[0]) * 3600 +
          int.parse(durationList[1]) * 60 +
          int.parse(durationList[2]);
    } else if (durationList.length == 2) {
      return int.parse(durationList[0]) * 60 + int.parse(durationList[1]);
    } else {
      return int.parse(durationList[0]);
    }
  }

  String _randomId(
      {int length = 32,
      String charset =
          '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'}) {
    final random = Random();
    return List.generate(length, (_) => charset[random.nextInt(charset.length)])
        .join();
  }

  MediaType _mapMediaType(String type) {
    switch (type) {
      case _typeBangumi:
        return MediaType.bangumi;
      case _typeAv:
        return MediaType.video;
      case _typeOgv:
        return MediaType.article;
      default:
        return MediaType.video;
    }
  }
}
