import 'package:dio/dio.dart';

class ChromeHeaderInterceptor extends Interceptor {
  @override
  Future<dynamic> onRequest(
      RequestOptions options, RequestInterceptorHandler handler) async {
    options.headers["User-Agent"] = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36";
    options.headers["Accept-Language"] = "en-GB,en;q=0.9";
    handler.next(options);
  }
}
