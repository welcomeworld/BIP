import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/model/index_configuration.dart';
import 'package:bip/domain/model/media_info.dart';
import 'package:bip/domain/model/media_page_detail.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/reply.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:rxdart/rxdart.dart';

/// MediaManager的抽象接口，用于依赖注入和测试
abstract class MediaManager {
  /// 获取首页探索内容
  Future<void> explore();

  /// 刷新首页探索内容
  Future<void> refreshExplore();

  /// 首页探索内容流
  BehaviorSubject<List<MediaPagePreview>> get homeExploreList;

  /// 账户信息流
  BehaviorSubject<Map<String, UserInfo?>> get accounts;

  /// 搜索请求
  Stream<List<MediaPagePreview>> requestSearch(String keyword, int pageNumber);

  /// 获取搜索热词
  Future<SourceApiResult<List<String>>> requestSearchHot();

  /// 获取番剧索引配置
  Future<IndexConfiguration> requestBangumiIndexConfiguration();

  /// 获取番剧索引内容
  Future<SourceApiResult<List<MediaPagePreview>>> requestBangumiIndex(
    IndexConfiguration configuration,
    int pageNumber,
  );

  /// 获取详情
  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview);

  /// 获取媒体信息
  Future<SourceApiResult<MediaInfo>> requestMediaInfo(MediaInfo mediaInfo);

  /// 请求登录二维码
  Future<String> requestLoginQr(String sourceName);

  /// 验证登录二维码
  Future<SourceLoginResult> validateLoginQr(String sourceName);

  /// 获取回复列表
  Future<SourceApiResult<List<Reply>>> requestReplies(
    MediaPageDetail page,
    int pageNumber,
  );

  /// 获取子回复列表
  Future<SourceApiResult<List<Reply>>> requestSubReplies(
    Reply reply,
    int pageNumber,
  );
}
