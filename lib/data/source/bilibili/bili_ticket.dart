import 'dart:convert';

import 'package:bip/domain/interfaces/web_net.dart';
import 'package:crypto/crypto.dart';
import 'package:dio/dio.dart';

class BiliTicket {
  /// Convert a byte array to a hex string.
  static String _bytesToHex(List<int> bytes) {
    StringBuffer sb = StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      String hex = bytes[i].toRadixString(16);
      if (hex.length == 1) {
        sb.write('0');
      }
      sb.write(hex);
    }
    return sb.toString();
  }

  /// Generate a HMAC-SHA256 hash of the given message string using the given key string.
  static String _hmacSha256(String key, String message) {
    List<int> keyBytes = utf8.encode(key);
    List<int> messageBytes = utf8.encode(message);

    Hmac hmac = Hmac(sha256, keyBytes);
    Digest digest = hmac.convert(messageBytes);

    return _bytesToHex(digest.bytes);
  }

  /// Get a Bilibili web ticket for the given CSRF token.
  ///
  /// [csrf] The CSRF token to use for the web ticket, can be `null` or empty.
  /// Returns The Bilibili web ticket raw response for the given CSRF token.
  ///
  /// See: https://github.com/SocialSisterYi/bilibili-API-collect/blob/master/docs/misc/sign/bili_ticket.md
  static Future<String> getBiliTicket(WebNet webNet, String csrf) async {
    // params
    int ts = DateTime.now().millisecondsSinceEpoch ~/ 1000;
    String hexSign = _hmacSha256("XgwSnGZ1p", "ts" + ts.toString());

    String url =
        "https://api.bilibili.com/bapis/bilibili.api.ticket.v1.Ticket/GenWebTicket";

    Map<String, dynamic> extraParameters = {};
    extraParameters["key_id"] = "ec02";
    extraParameters["hexsign"] = hexSign;
    extraParameters["context[ts]"] = ts.toString();
    extraParameters["csrf"] = csrf;

    Options options = Options(headers: {
      "Referer": "https://www.bilibili.com/",
      "Origin": "https://www.bilibili.com/",
    });

    try {
      // request
      final response = (await webNet.post(url,
              options: options, queryParameters: extraParameters))
          .data as Map<String, dynamic>;
      return response['data']['ticket'];
    } catch (e) {
      return '';
    }
  }
}
