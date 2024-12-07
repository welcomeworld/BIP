import 'package:bip/data/model/user_info.dart';
import 'package:bip/data/source/bilibili/bili_explore_response.dart';
import 'package:bip/data/source/bilibili/wbi_net.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/utils/logger.dart';

import '../../model/media_page_preview.dart';

class BiliSource extends Source {
  @override
  String get sourceName => "哔哩哔哩";
  static const String _apiUrl = "https://api.bilibili.com/";
  static const String _passportUrl = "https://passport.bilibili.com/";
  static const List<String> _typeWhiteList = ["av", "ogv", "bangumi"];

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
  final List<String> _exploreLastShow = [];

  @override
  Future<List<MediaPagePreview>> explore(int pageNumber) async {
    List<MediaPagePreview> exploreResult = [];
    try {
      String lastShow = "";
      if (pageNumber == 1) {
        _exploreLastShow.clear();
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
}
