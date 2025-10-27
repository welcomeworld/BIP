import 'package:bip/data/source/bilibili/wbi_manager.dart';
import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/kv_store.dart';
import 'package:dio/dio.dart';

class WbiSortAndSignInterceptor extends Interceptor {
  @override
  Future<dynamic> onRequest(
      RequestOptions options, RequestInterceptorHandler handler) async {
    final method = options.method;
    final url = options.uri;

    List<String> namesAndValues = [];

    if (method.toUpperCase() == 'GET') {
      final queryParameters = url.queryParametersAll;
      final sortedKeys = queryParameters.keys.toList()..sort();
      for (final key in sortedKeys) {
        for (final value in queryParameters[key]!) {
          namesAndValues
              .add('${Uri.encodeComponent(key)}=${Uri.encodeComponent(value)}');
        }
      }
    } else if (method.toUpperCase() == 'POST') {
      if (options.data is Map<String, dynamic>) {
        final formData = options.data as Map<String, dynamic>;
        final sortedKeys = formData.keys.toList()..sort();
        for (final key in sortedKeys) {
          final value = formData[key];
          if (value != null) {
            namesAndValues.add(
                '${Uri.encodeComponent(key)}=${Uri.encodeComponent(value.toString())}');
          }
        }
      }
    }

    String queryString = namesAndValues.join('&');
    queryString = await WbiManager(getIt<KvStore>()).signWithWbi(queryString);

    if (method.toUpperCase() == 'POST') {
      options.data = queryString;
      options.contentType = Headers.formUrlEncodedContentType;
      options.queryParameters = {};
    } else {
      options.queryParameters = Uri.splitQueryString(queryString);
    }

    handler.next(options);
  }
}
