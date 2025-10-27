import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/domain/model/media_page_history.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/search_history.dart';

/// BipDatabase的抽象接口，用于依赖注入和测试
abstract class Database {
  /// 保存搜索历史
  Future<void> saveSearchHistory(SearchHistory history);

  /// 搜索历史流
  Stream<List<SearchHistory>> get searchHistoryStream;

  /// 清除搜索历史
  Future<void> clearSearchHistories();

  /// 查询媒体页面历史
  Future<List<MediaPageHistory>> queryMediaPageHistory({
    String key = "",
    int pageNumber = 1,
  });

  /// 保存媒体页面历史
  Future<void> saveMediaPageHistory(MediaPageHistory history);

  /// 查询媒体收藏
  Future<List<MediaCollection>> queryMediaCollections();

  /// 保存媒体收藏
  Future<bool> saveMediaCollection(MediaCollection collection);

  /// 删除媒体收藏
  Future<bool> deleteMediaCollection(MediaCollection collection);

  /// 添加到媒体收藏
  Future<bool> addToMediaCollection(
    MediaCollection collection,
    MediaPagePreview preview,
  );

  /// 从媒体收藏中移除
  Future<bool> removeFromMediaCollection(
    MediaCollection collection,
    MediaPagePreview preview,
  );

  /// 检查是否在媒体收藏中
  Future<bool> isInMediaCollection(MediaPagePreview preview);

  /// 查询媒体收藏详情
  Future<List<MediaPagePreview>> queryMediaCollectionDetail(
    MediaCollection collection, {
    String key = "",
    int pageNumber = 1,
  });
}
