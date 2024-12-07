import 'package:bip/data/source/bilibili/wbi_sort_and_sign_interceptor.dart';
import 'package:dio/dio.dart';
import 'package:cookie_jar/cookie_jar.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';

import '../../../utils/constant.dart';

class WbiNet {
  static WbiNet? _ins;

  WbiNet._() {
    _ins = this;
    _dio.interceptors.add(CookieManager(PersistCookieJar(
      ignoreExpires: true,
      storage: FileStorage(Constant.cookiePath),
    )));
    _dio.interceptors.add(WbiSortAndSignInterceptor());
    _dio.interceptors.add(LogInterceptor(responseBody: true));
  }

  factory WbiNet() => _ins ?? WbiNet._();

  final _dio = Dio();

  Future<Response<T>> get<T>(String path,
      {Map<String, dynamic>? queryParameters}) async {
    return await _dio.get(path, queryParameters: queryParameters);
  }
}
