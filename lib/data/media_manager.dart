import 'dart:async';

import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/model/user_info.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/data/source_manager.dart';
import 'package:rxdart/rxdart.dart';

import 'model/media_info.dart';
import 'model/index_configuration.dart';
import 'model/media_page_detail.dart';
import 'model/reply.dart';

class MediaManager {
  static MediaManager? _ins;

  MediaManager._({SourceManager? sourceManager})
      : _sourceManager = sourceManager ?? SourceManager() {
    _ins = this;
    _refreshAccount();
  }

  factory MediaManager({SourceManager? sourceManager}) =>
      _ins ?? MediaManager._(sourceManager: sourceManager);

  ///  usually only use for test
  static void reset() {
    _ins = null;
  }

  SourceManager _sourceManager;
  int _explorePageNumber = 1;

  final List<MediaPagePreview> _homeExploreList = [];
  BehaviorSubject<List<MediaPagePreview>> homeExploreList = BehaviorSubject();
  BehaviorSubject<Map<String, UserInfo?>> accounts = BehaviorSubject();

  Future<void> explore() async {
    var result = await _sourceManager.mainSource.explore(_explorePageNumber++);
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

    for (final source in _sourceManager.activeSources.values) {
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
    return await _sourceManager.mainSource.requestSearchHot();
  }

  // bangumi index
  Future<IndexConfiguration> requestBangumiIndexConfiguration() async {
    return await _sourceManager.mainSource.requestBangumiIndexConfiguration();
  }

  Future<SourceApiResult<List<MediaPagePreview>>> requestBangumiIndex(
      IndexConfiguration configuration, int pageNumber) async {
    return await _sourceManager.mainSource
        .requestBangumiIndex(configuration, pageNumber);
  }

  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview) async {
    return await _sourceManager.activeSources[preview.sourceName]
            ?.requestDetail(preview) ??
        SourceApiResult(
          MediaPageDetail.fromPreview(preview),
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }

  Future<SourceApiResult<MediaInfo>> requestMediaInfo(
      MediaInfo mediaInfo) async {
    return await _sourceManager.activeSources[mediaInfo.sourceName]
            ?.requestMediaInfo(mediaInfo) ??
        SourceApiResult(
          mediaInfo,
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }

  void _refreshAccount() {
    final accountMap = Map.fromEntries(
      _sourceManager.activeSources.entries
          .where((entry) => entry.value.hasAccount)
          .map((entry) => MapEntry(entry.key, entry.value.accountInfo)),
    );
    accounts.add(accountMap);
  }

  Future<String> requestLoginQr(String sourceName) async {
    return await _sourceManager.activeSources[sourceName]?.requestLoginQr() ??
        "";
  }

  Future<SourceLoginResult> validateLoginQr(String sourceName) async {
    final loginResult =
        await _sourceManager.activeSources[sourceName]?.validateLoginQr() ??
            SourceLoginResult.failed;
    if (loginResult == SourceLoginResult.success) {
      _refreshAccount();
    }
    return loginResult;
  }

  Future<SourceApiResult<List<Reply>>> requestReplies(
      MediaPageDetail page, int pageNumber) async {
    return await _sourceManager.activeSources[page.sourceName]
            ?.requestReplies(page, pageNumber) ??
        SourceApiResult(
          [],
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }

  Future<SourceApiResult<List<Reply>>> requestSubReplies(
      Reply reply, int pageNumber) async {
    return await _sourceManager.activeSources[reply.sourceName]
            ?.requestSubReplies(reply, pageNumber) ??
        SourceApiResult(
          [],
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }
}
