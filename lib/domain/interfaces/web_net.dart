import 'package:dio/dio.dart';

/// WebNet的抽象接口，用于依赖注入和测试
abstract class WebNet {
  /// GET请求
  Future<Response<T>> get<T>(
    String path, {
    Map<String, dynamic>? queryParameters,
    Options? options,
  });

  /// POST请求
  Future<Response<T>> post<T>(
    String path, {
    Map<String, dynamic>? queryParameters,
    Options? options,
  });
}
