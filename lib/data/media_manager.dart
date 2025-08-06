import 'dart:async';

import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/model/user_info.dart';
import 'package:bip/data/source/bilibili/bili_source.dart';
import 'package:bip/data/source/bimi/bimi_source.dart';
import 'package:bip/data/source/gugufan/gugufan_source.dart';
import 'package:bip/data/source/source.dart';
import 'package:rxdart/rxdart.dart';

import 'model/media_info.dart';
import 'model/media_page_detail.dart';
import 'model/reply.dart';

class MediaManager {
  static MediaManager? _ins;

  MediaManager._({Source? mainSource, Map<String, Source>? sources}) {
    _ins = this;
    _mainSource = mainSource ?? BiliSource();
    var gugufanSource = GugufanSource();
    var bimiSource = BimiSource();
    _sources = sources ??
        {
          _mainSource.sourceName: _mainSource,
          gugufanSource.sourceName: gugufanSource,
          bimiSource.sourceName: bimiSource
        };
    _refreshAccount();
  }

  factory MediaManager({Source? mainSource, Map<String, Source>? sources}) =>
      _ins ?? MediaManager._(mainSource: mainSource, sources: sources);

  ///  usually only use for test
  static void reset() {
    _ins = null;
  }

  late final Source _mainSource;
  late final Map<String, Source> _sources;
  int _explorePageNumber = 1;

  final List<MediaPagePreview> _homeExploreList = [];
  BehaviorSubject<List<MediaPagePreview>> homeExploreList = BehaviorSubject();
  BehaviorSubject<Map<String, UserInfo?>> accounts = BehaviorSubject();

  Future<void> explore() async {
    var result = await _mainSource.explore(_explorePageNumber++);
    if (_explorePageNumber == 2) {
      _homeExploreList.clear();
    }
    _homeExploreList.addAll(result);
    homeExploreList.add(_homeExploreList);
  }

  Future<void> refreshExplore() async {
    _explorePageNumber = 1;
    await explore();
  }

  Stream<List<MediaPagePreview>> requestSearch(String keyword, int pageNumber) {
    final controller = StreamController<List<MediaPagePreview>>();
    final List<Future<void>> searchFutures = [];

    for (final source in _sources.values) {
      final searchFuture = source.search(keyword, pageNumber).then((result) {
        controller.add(result.result);
      }).catchError((error) {
        controller.addError(error);
      });
      searchFutures.add(searchFuture);
    }

    Future.wait(searchFutures).then((_) => controller.close());
    return controller.stream;
  }

  Future<SourceApiResult<List<String>>> requestSearchHot() async {
    return await _mainSource.requestSearchHot();
  }

  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview) async {
    return await _sources[preview.sourceName]?.requestDetail(preview) ??
        SourceApiResult(
          MediaPageDetail.fromPreview(preview),
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }

  Future<SourceApiResult<MediaInfo>> requestMediaInfo(
      MediaInfo mediaInfo) async {
    return await _sources[mediaInfo.sourceName]?.requestMediaInfo(mediaInfo) ??
        SourceApiResult(
          mediaInfo,
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }

  void _refreshAccount() {
    final accountMap = Map.fromEntries(
      _sources.entries
          .where((entry) => entry.value.hasAccount)
          .map((entry) => MapEntry(entry.key, entry.value.accountInfo)),
    );
    accounts.add(accountMap);
  }

  Future<String> requestLoginQr(String sourceName) async {
    return await _sources[sourceName]?.requestLoginQr() ?? "";
  }

  Future<SourceLoginResult> validateLoginQr(String sourceName) async {
    final loginResult = await _sources[sourceName]?.validateLoginQr() ??
        SourceLoginResult.failed;
    if (loginResult == SourceLoginResult.success) {
      _refreshAccount();
    }
    return loginResult;
  }

  Future<SourceApiResult<List<Reply>>> requestReplies(
      MediaPageDetail page, int pageNumber) async {
    return await _sources[page.sourceName]?.requestReplies(page, pageNumber) ??
        SourceApiResult(
          [],
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }

  Future<SourceApiResult<List<Reply>>> requestSubReplies(
      Reply reply, int pageNumber) async {
    return await _sources[reply.sourceName]
            ?.requestSubReplies(reply, pageNumber) ??
        SourceApiResult(
          [],
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }
}
