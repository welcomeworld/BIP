import 'package:bip/data/model/user_info.dart';

class MediaPagePreview {
  String sourceName = "";
  Map<String, dynamic> extras = {};
  late UserInfo owner;
  String title = "";
  //seconds
  int recordPosition = 0;
  //seconds
  int duration = 0;
  int pageTime = 0;
  String cover = "";
  bool coverPortrait = false;
  List<String> tags = [];
  String topDec = "";
  int playCount = 0;
}
