import 'package:bip/data/net/chrome_header_interceptor.dart';
import 'package:bip/utils/constant.dart';
import 'package:dio/dio.dart';
import 'package:cookie_jar/cookie_jar.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';

class WebNet {
  static WebNet? _ins;

  WebNet._() {
    _ins = this;
    _dio.interceptors.add(
      CookieManager(
        PersistCookieJar(
          ignoreExpires: true,
          storage: FileStorage(Constant.cookiePath),
        ),
      ),
    );
    _dio.interceptors.add(ChromeHeaderInterceptor());
    _dio.interceptors.add(LogInterceptor());
  }

  factory WebNet() => _ins ?? WebNet._();

  final _dio = Dio();

  Future<Response<T>> get<T>(String path,
      {Map<String, dynamic>? queryParameters, Options? options}) async {
    return await _dio.get(path,
        queryParameters: queryParameters, options: options);
  }
}
