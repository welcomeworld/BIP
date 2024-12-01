import 'dart:convert';
import 'package:bip/data/net/web_net.dart';
import 'package:crypto/crypto.dart';
import 'package:bip/data/persistence/kv_store.dart';
import 'package:bip/utils/constant.dart';

class WbiManager {
  static const List<int> _mixinKey = [
    46,
    47,
    18,
    2,
    53,
    8,
    23,
    32,
    15,
    50,
    10,
    31,
    58,
    3,
    45,
    35,
    27,
    43,
    5,
    49,
    33,
    9,
    42,
    19,
    29,
    28,
    14,
    39,
    12,
    38,
    41,
    13,
    37,
    48,
    7,
    16,
    24,
    55,
    40,
    61,
    26,
    17,
    0,
    1,
    60,
    51,
    30,
    4,
    22,
    25,
    54,
    21,
    56,
    59,
    6,
    63,
    57,
    62,
    11,
    36,
    20,
    34,
    44,
    52
  ];
  static const String _wbiUrl =
      'https://api.bilibili.com/x/web-interface/nav';

  static const int _timeOut = 1000 * 60 * 60 * 12; // 12 hours

  WbiManager._();

  static final WbiManager _instance = WbiManager._();

  factory WbiManager() => _instance;

  Future<void> _saveWbiKey(String wbi) async {
    KvStore.getSp().setString(Constant.kvKeyWbi, wbi);
  }

  Future<String> _getWbiKey() async {
    await _checkWbiKey();
    return KvStore.getSp().getString(Constant.kvKeyWbi) ?? '';
  }

  Future<void> _saveUpdateTime() async {
    KvStore.getSp().setInt(
        Constant.kvKeyUpdateTime, DateTime.now().millisecondsSinceEpoch);
  }

  Future<int> _getUpdateTime() async {
    return KvStore.getSp().getInt(Constant.kvKeyUpdateTime) ?? 0;
  }

  Future<void> _checkWbiKey() async {
    if (await _isWbiAvailable()) {
      return;
    }
    final response = await WebNet().get(Uri.parse(_wbiUrl));

    if (response.statusCode == 200) {
      Map<String, dynamic> json = response.data;
      var data = json['data'] as Map<String, dynamic>;
      var wbiImg = data['wbi_img'] as Map<String, dynamic>;
      String imgUrl = wbiImg['img_url'] as String;
      String subUrl = wbiImg['sub_url'] as String;
      String imgKey =
          imgUrl.substring(imgUrl.indexOf('wbi/') + 4, imgUrl.indexOf('.png'));
      String subKey =
          subUrl.substring(subUrl.indexOf('wbi/') + 4, subUrl.indexOf('.png'));
      await _saveWbiKey(_generateWbiKey('$imgKey$subKey'));
      await _saveUpdateTime();
    }
  }

  Future<bool> _isWbiAvailable() async {
    int current = DateTime.now().millisecondsSinceEpoch;
    int lastUpdate = await _getUpdateTime();
    return current - lastUpdate < _timeOut &&
        DateTime.now().dayOfYear ==
            DateTime.fromMillisecondsSinceEpoch(lastUpdate).dayOfYear;
  }

  String _generateWbiKey(String wbiSeed) {
    StringBuffer wbiBuilder = StringBuffer();
    if (wbiSeed.length >= 32) {
      for (int i = 0; i < 32; i++) {
        wbiBuilder.write(wbiSeed[_mixinKey[i]]);
      }
    }
    return wbiBuilder.toString();
  }

  String signWithWbi(String queryString) {
    String wts = '&wts=${DateTime.now().millisecondsSinceEpoch ~/ 1000}';
    String result = '$queryString$wts';
    String hash = _md5('$result${_getWbiKey()}');
    // 确保md5字符串长度为32位
    String paddedHash = hash.padLeft(32, '0');
    return '$queryString&w_rid=$paddedHash$wts';
  }

  String _md5(String input) {
    var bytes = utf8.encode(input); // 转换为字节列表
    var digest = md5.convert(bytes);
    return digest.toString();
  }
}

extension on DateTime {
  int get dayOfYear {
    final date = this;
    final firstDayOfYear = DateTime(date.year, 1, 1);
    return date.difference(firstDayOfYear).inDays + 1;
  }
}
