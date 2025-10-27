import 'package:bip/data/net/chrome_header_interceptor.dart';
import 'package:bip/domain/interfaces/web_net.dart';
import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';

import 'bip_cookie_manager.dart';

class WebNetImpl implements WebNet {
  static WebNetImpl? _ins;

  WebNetImpl._() {
    _ins = this;
    _dio.interceptors.add(
      CookieManager(BipCookieManager.cookieJar),
    );
    _dio.interceptors.add(ChromeHeaderInterceptor());
    _dio.interceptors.add(LogInterceptor());
  }

  factory WebNetImpl() => _ins ?? WebNetImpl._();

  final _dio = Dio();

  @override
  Future<Response<T>> get<T>(String path,
      {Map<String, dynamic>? queryParameters, Options? options}) async {
    return await _dio.get(path,
        queryParameters: queryParameters, options: options);
  }

  @override
  Future<Response<T>> post<T>(String path,
      {Map<String, dynamic>? queryParameters, Options? options}) async {
    return await _dio.post(path,
        queryParameters: queryParameters, options: options);
  }
}
