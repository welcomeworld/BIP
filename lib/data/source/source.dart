import 'package:bip/data/model/media_collection.dart';
import 'package:bip/data/model/media_info.dart';
import 'package:bip/data/model/media_type.dart';
import 'package:bip/data/model/reply.dart';
import 'package:bip/data/model/user_info.dart';

import '../../utils/constant.dart';
import '../model/index_configuration.dart';
import '../model/media_page_detail.dart';
import '../model/media_page_preview.dart';

abstract class Source {
  String get sourceName;

  Future<List<MediaPagePreview>> explore(int pageNumber) async {
    return List.empty();
  }

  Future<SourceApiResult<List<MediaPagePreview>>> search(
      String keyword, int pageNumber,
      {MediaType searchType = MediaType.video});

  Future<SourceApiResult<List<String>>> requestSearchHot() async {
    return SourceApiResult(
      List.empty(),
      resultCode: SourceApiResult.resultSourceEmpty,
    );
  }

  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview);

  Future<SourceApiResult<MediaInfo>> requestMediaInfo(MediaInfo mediaInfo);

  Future<String> requestLoginQr() async {
    return "";
  }

  Future<SourceLoginResult> validateLoginQr() async {
    return SourceLoginResult.failed;
  }

  Future<SourceApiResult<bool>> logout() async {
    return SourceApiResult(false);
  }

  Future<SourceApiResult<List<Reply>>> requestReplies(
      MediaPageDetail page, int pageNumber) async {
    return SourceApiResult(
      List.empty(),
      resultCode: SourceApiResult.resultSourceEmpty,
    );
  }

  Future<SourceApiResult<List<Reply>>> requestSubReplies(
      Reply parentReply, int pageNumber) async {
    return SourceApiResult(
      List.empty(),
      resultCode: SourceApiResult.resultSourceEmpty,
    );
  }

  Future<SourceApiResult<Reply>> replyAction(
      Reply reply, ReplyAction action) async {
    return SourceApiResult(
      reply,
      resultCode: SourceApiResult.resultSuccess,
    );
  }

  Future<SourceApiResult<List<MediaCollection>>>
      requestMediaCollections() async {
    return SourceApiResult(
      List.empty(),
      resultCode: SourceApiResult.resultSourceEmpty,
    );
  }

  Future<bool> saveMediaCollection(MediaCollection collection) async {
    return true;
  }

  Future<bool> deleteMediaCollection(MediaCollection collection) async {
    return true;
  }

  Future<bool> addToMediaCollection(
      MediaCollection collection, MediaPagePreview preview) async {
    return true;
  }

  Future<bool> removeFromMediaCollection(
      MediaCollection collection, MediaPagePreview preview) async {
    return true;
  }

  Future<bool> removeFromAllMediaCollection(MediaPagePreview preview) async {
    return true;
  }

  Future<bool> isInMediaCollection(MediaPagePreview preview) async {
    return false;
  }

  Future<SourceApiResult<List<MediaPagePreview>>> requestMediaCollectionDetail(
      MediaCollection collection,
      {String key = "",
      int pageNumber = 1}) async {
    return SourceApiResult([], resultCode: SourceApiResult.resultSourceEmpty);
  }

  bool get hasAccount => false;

  UserInfo? get accountInfo => null;

  // bangumi index default implementations
  Future<IndexConfiguration> requestBangumiIndexConfiguration() async {
    return IndexConfiguration(availableCategories: []);
  }

  Future<SourceApiResult<List<MediaPagePreview>>> requestBangumiIndex(
      IndexConfiguration configuration, int pageNumber) async {
    return SourceApiResult([], resultCode: SourceApiResult.resultSourceEmpty);
  }
}

class SourceApiResult<T> {
  static const resultSuccess = 0;
  static const resultInnerFailed = -1;
  static const resultNetworkFailed = -2;
  static const resultSourceEmpty = -3;
  T result;
  int resultCode;
  String resultMsg;

  SourceApiResult(this.result, {this.resultCode = 0, this.resultMsg = ""});
}

enum SourceLoginResult {
  success,
  continueWait,
  timeout,
  failed,
}
