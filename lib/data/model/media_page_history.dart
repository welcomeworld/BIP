import 'package:bip/data/model/media_page_preview.dart';

class MediaPageHistory {
  final MediaPagePreview mediaPagePreview;
  DateTime viewTime = DateTime.now();

  String get id => mediaPagePreview.sourceName + mediaPagePreview.mediaPageId;
  String get title => mediaPagePreview.title;

  MediaPageHistory(this.mediaPagePreview, {DateTime? viewTime}) {
    if (viewTime != null) {
      this.viewTime = viewTime;
    }
  }
}
