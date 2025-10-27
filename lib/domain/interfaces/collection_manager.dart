import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/domain/model/media_page_preview.dart';

/// CollectionManager的抽象接口，用于依赖注入和测试
abstract class CollectionManager {
  /// 请求媒体收藏列表
  Stream<List<MediaCollection>> requestMediaCollections({String? sourceName});

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

  /// 从所有媒体收藏中移除
  Future<bool> removeFromAllMediaCollection(MediaPagePreview preview);

  /// 请求媒体收藏详情
  Future<List<MediaPagePreview>> requestMediaCollectionDetail(
    MediaCollection collection, {
    String key = "",
    int pageNumber = 1,
  });
}
