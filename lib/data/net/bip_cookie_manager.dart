import 'package:cookie_jar/cookie_jar.dart';

import '../../utils/constant.dart';

class BipCookieManager {
  BipCookieManager._();

  static PersistCookieJar cookieJar = PersistCookieJar(
    ignoreExpires: true,
    storage: FileStorage(Constant.cookiePath),
  );

  static Future<void> saveCookie({
    required String name,
    required String value,
    required String domain,
    int maxAge = 365 * 24 * 60 * 60,
  }) async {
    final cookie = Cookie(name, value)
      ..domain = domain
      ..path = '/'
      ..maxAge = maxAge;

    await cookieJar.saveFromResponse(
      Uri.parse('https://$domain'),
      [cookie],
    );
  }

  static Future<String> getCookie(String domain, String name) async {
    final cookies =
        await cookieJar.loadForRequest(Uri.parse('https://$domain'));
    final cookie = cookies.firstWhere(
      (cookie) => cookie.name == name,
      orElse: () => Cookie('', ''),
    );
    return cookie.value;
  }

  static Future<void> clearCookies() async {
    await cookieJar.deleteAll();
  }
}
