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
import 'package:bip/data/source/bilibili/model/bili_reply_response.dart';
import 'package:bip/data/source/bilibili/model/bili_search_hot_response.dart';
import 'package:bip/data/source/bilibili/model/bili_user_nav_info_response.dart';
import 'package:bip/data/source/bilibili/wbi_net.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/data/source/source_extra_key.dart';
import 'package:bip/utils/common_util.dart';
import 'package:bip/utils/constant.dart';
import 'package:bip/utils/logger.dart';
import 'package:dio/dio.dart';

import '../../model/media_info.dart';
import '../../model/media_page_preview.dart';
import '../../model/reply.dart';
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
    "qn": 116,
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

  static const _replyPath = "${_apiUrl}x/v2/reply";
  static const _subReplyPath = "${_apiUrl}x/v2/reply/reply";

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
      if (recommendNetResponse.data == null ||
          recommendNetResponse.statusCode != 200) {
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

  @override
  Future<SourceApiResult<List<Reply>>> requestReplies(
      MediaPageDetail page, int pageNumber) async {
    List<Reply> result = [];
    final replyType = _mapReplyType(page.mediaType);
    final oid = page.extras[SourceExtraKey.aid] ??
        page.extras[SourceExtraKey.bvid] ??
        page.extras[SourceExtraKey.ssid] ??
        "";
    try {
      Map<String, dynamic> extraParameters = {};
      extraParameters["type"] = replyType;
      extraParameters["pn"] = pageNumber;
      extraParameters["oid"] = oid;

      Options options = Options(headers: {
        "Referer": "https://www.bilibili.com/",
        "Origin": "https://www.bilibili.com/",
      });
      var response = await WebNet()
          .get(_replyPath, queryParameters: extraParameters, options: options);
      BiliReplyResponse biliResponse =
          BiliReplyResponse.fromJson(response.data);
      for (var item in biliResponse.data.hots) {
        result.add(_mapReplyItem(item));
      }
      for (var item in biliResponse.data.replies) {
        result.add(_mapReplyItem(item));
      }
      if (result.isEmpty) {
        return SourceApiResult(result,
            resultCode: SourceApiResult.resultSourceEmpty);
      }
    } catch (message) {
      Logger.logConsole("bili requestReplies err:$message");
      return SourceApiResult(
          resultCode: SourceApiResult.resultInnerFailed, result);
    }
    return SourceApiResult(result);
  }

  @override
  Future<SourceApiResult<List<Reply>>> requestSubReplies(
      Reply parentReply, int pageNumber) async {
    List<Reply> result = [];
    final replyType = parentReply.extras[SourceExtraKey.type];
    final oid = parentReply.extras[SourceExtraKey.oid] ?? "";
    try {
      Map<String, dynamic> extraParameters = {};
      extraParameters["type"] = replyType;
      extraParameters["pn"] = pageNumber;
      extraParameters["oid"] = oid;
      extraParameters["root"] = parentReply.replyId;

      Options options = Options(headers: {
        "Referer": "https://www.bilibili.com/",
        "Origin": "https://www.bilibili.com/",
      });
      var response = await WebNet().get(_subReplyPath,
          queryParameters: extraParameters, options: options);
      BiliReplyResponse biliResponse =
          BiliReplyResponse.fromJson(response.data);
      for (var item in biliResponse.data.hots) {
        result.add(_mapReplyItem(item));
      }
      for (var item in biliResponse.data.replies) {
        result.add(_mapReplyItem(item));
      }
      if (result.isEmpty) {
        return SourceApiResult(result,
            resultCode: SourceApiResult.resultSourceEmpty);
      }
    } catch (message) {
      Logger.logConsole("bili requestSubReplies err:$message");
      return SourceApiResult(
          resultCode: SourceApiResult.resultInnerFailed, result);
    }
    return SourceApiResult(result);
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
      var dash = mediaInfoResponse.data.dash;
      if (dash == null) {
        //todo map durl list into mpd file
        var selectedVideoKey =
            _getResolutionDesc(mediaInfoResponse.data.quality);
        mediaInfo.qualityKey = selectedVideoKey;

        mediaInfo.mediaQualities[selectedVideoKey] =
            mediaInfoResponse.data.durl![0].url;
      } else {
        var selectedVideoKey = "";
        var mediaId = 0;
        var needBestMedia = _needBestMedia;
        // map videoUrl
        for (var media in dash.video) {
          mediaInfo.mediaQualities[_getResolutionDesc(media.id)] =
              media.baseUrl;
          if (mediaId == 0 ||
              (needBestMedia && mediaId < media.id) ||
              (!needBestMedia && mediaId > media.id)) {
            mediaId = media.id;
            selectedVideoKey = _getResolutionDesc(media.id);
          }
        }
        mediaInfo.qualityKey = selectedVideoKey;

        // map audioUrl
        mediaId = 0;
        for (var media in dash.audio) {
          mediaInfo.additionAudios[_getAudioDesc(media.id)] = media.baseUrl;
          if (mediaId == 0 ||
              (needBestMedia && mediaId < media.id) ||
              (!needBestMedia && mediaId > media.id)) {
            mediaId = media.id;
            selectedVideoKey = _getAudioDesc(media.id);
          }
        }
        mediaInfo.additionAudioKey = selectedVideoKey;
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

  int _mapReplyType(MediaType type) {
    switch (type) {
      case MediaType.video:
        return 1; // 视频评论
      case MediaType.bangumi:
        return 1; // 番剧评论
      case MediaType.movie:
        return 1;
      case MediaType.user:
        return 1;
      case MediaType.live:
        return 8;
      case MediaType.article:
        return 12; // 专栏评论
      default:
        return 1; // 默认视频评论
    }
  }

  Reply _mapReplyItem(ReplyResponse item) {
    var result = Reply(item.rpidStr);
    result.extras[SourceExtraKey.type] = item.type;
    result.extras[SourceExtraKey.oid] = item.oid;
    result.sourceName = sourceName;
    result.rootReplyId = item.rootStr;
    result.replyParentId = item.parentStr;
    result.content = item.content.message;
    result.likeCount = item.like;
    result.replyTime = item.ctime * 1000;
    result.replyIp = item.replyControl.location;
    result.device = item.content.device;

    result.isLiked = item.action == 1;
    result.isHated = item.action == 2;
    result.subReplyCount = item.rcount;
    result.subReplies =
        (item.replies).map((subReply) => _mapReplyItem(subReply)).toList();
    result.replyDec = item.upAction.like
        ? "UP主觉得很赞"
        : (item.upAction.reply
            ? "UP主回复"
            : ""); // No direct mapping, set to empty
    result.owner = UserInfo(
      name: item.member.uname,
      avatar: item.member.avatar,
      level: item.member.levelInfo.currentLevel,
      isVip: item.member.vip.vipType != 0,
    );
    return result;
  }

  bool get _needBestMedia {
    var setting = KvStore.getSp().getInt(Constant.kvSettingsMediaQuality) ?? 0;
    if (setting == 0) {
      return true; // 0表示自动
    } else if (setting == 1) {
      return true; // 1表示最佳画质
    } else {
      return false; // 其他值表示不需要最佳画质
    }
  }
}
