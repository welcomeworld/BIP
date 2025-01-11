import '../model/media_page_detail.dart';
import '../model/media_page_preview.dart';

abstract class Source {
  String get sourceName;

  Future<List<MediaPagePreview>> explore(int pageNumber) async {
    return List.empty();
  }

  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview);
}

class SourceApiResult<T> {
  static const resultSuccess = 0;
  static const resultInnerFailed = -1;
  static const resultNetworkFailed = -2;
  static const resultSourceEmpty = -3;
  T result;
  int resultCode;

  SourceApiResult(this.result, {this.resultCode = 0});
}
