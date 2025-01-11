import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/source/bilibili/bili_source.dart';
import 'package:bip/data/source/source.dart';
import 'package:rxdart/rxdart.dart';

import 'model/media_page_detail.dart';

class MediaManager {
  static MediaManager? _ins;

  MediaManager._() {
    _ins = this;
    _sources[_biliSource.sourceName] = _biliSource;
  }

  factory MediaManager() => _ins ?? MediaManager._();

  final BiliSource _biliSource = BiliSource();
  final Map<String, Source> _sources = {};
  int _explorePageNumber = 1;

  final List<MediaPagePreview> _homeExploreList = [];
  BehaviorSubject<List<MediaPagePreview>> homeExploreList = BehaviorSubject();

  Future<void> explore() async {
    var result = await _biliSource.explore(_explorePageNumber++);
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

  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview) async {
    return await _sources[preview.sourceName]?.requestDetail(preview) ??
        SourceApiResult(
          MediaPageDetail.fromPreview(preview),
          resultCode: SourceApiResult.resultSourceEmpty,
        );
  }
}
