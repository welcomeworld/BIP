import '../model/media_page_preview.dart';

abstract class Source {
  String get sourceName;

  Future<List<MediaPagePreview>> explore(int pageNumber) async {
    return List.empty();
  }
}
