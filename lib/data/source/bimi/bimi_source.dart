import 'package:bip/data/model/media_info.dart';
import 'package:bip/data/model/media_page_detail.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/source/source.dart';

class BimiSource extends Source {
  @override
  String get sourceName => "哔咪动漫";

  @override
  Future<SourceApiResult<MediaPageDetail>> requestDetail(
      MediaPagePreview preview) async {
    // TODO: implement requestDetail
    throw UnimplementedError();
  }

  @override
  Future<SourceApiResult<MediaInfo>> requestMediaInfo(MediaInfo mediaInfo) {
    // TODO: implement requestMediaInfo
    throw UnimplementedError();
  }

  @override
  Future<SourceApiResult<List<MediaPagePreview>>> search(String keyword, int pageNumber) {
    // TODO: implement search
    throw UnimplementedError();
  }
}
