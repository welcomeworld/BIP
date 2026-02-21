import 'dart:async';
import 'dart:io';

import 'package:flutter_test/flutter_test.dart';

/// Run a callback with network image mocking enabled.
R runWithMockNetworkImages<R>(R Function() body) {
  return HttpOverrides.runZoned(
    body,
    createHttpClient: (_) => _createMockImageHttpClient(),
  );
}

HttpClient _createMockImageHttpClient() {
  return _MockHttpClient();
}

class RealTodoRepository {          // 具体类，没有 abstract
  Future<List<int>> getAll() async { return []; }
  Future<void> add(int t) async {  }
// 还有 10 个其他方法...
}

class FakeTodoRepository extends Fake implements RealTodoRepository {
  @override
  Future<List<int>> getAll() async => [];   // 只实现这一个
// 其他方法故意不写
}

class _MockHttpClient extends Fake implements HttpClient {
  @override
  bool autoUncompress = true;

  @override
  Future<HttpClientRequest> getUrl(Uri url) async {
    return _MockHttpClientRequest();
  }
}

class _MockHttpClientRequest extends Fake implements HttpClientRequest {
  @override
  HttpHeaders get headers => _MockHttpHeaders();

  @override
  Future<HttpClientResponse> close() async {
    return _MockHttpClientResponse();
  }
}

class _MockHttpClientResponse extends Fake implements HttpClientResponse {
  @override
  int get contentLength => _transparentImage.length;

  @override
  int get statusCode => 200;

  @override
  HttpClientResponseCompressionState get compressionState =>
      HttpClientResponseCompressionState.notCompressed;

  @override
  StreamSubscription<List<int>> listen(void Function(List<int> event)? onData,
      {Function? onError, void Function()? onDone, bool? cancelOnError}) {
    return Stream.value(_transparentImage).listen(onData,
        onError: onError, onDone: onDone, cancelOnError: cancelOnError);
  }
}

class _MockHttpHeaders extends Fake implements HttpHeaders {}

// A 1x1 transparent PNG.
const List<int> _transparentImage = [
  0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A, // Header
  0x00, 0x00, 0x00, 0x0D, // IHDR length
  0x49, 0x48, 0x44, 0x52, // IHDR
  0x00, 0x00, 0x00, 0x01, // width
  0x00, 0x00, 0x00, 0x01, // height
  0x08, // bit depth
  0x06, // color type (truecolor with alpha)
  0x00, // compression method
  0x00, // filter method
  0x00, // interlace method
  0x1F, 0x15, 0xC4, 0x89, // IHDR CRC
  0x00, 0x00, 0x00, 0x0A, // IDAT length
  0x49, 0x44, 0x41, 0x54, // IDAT
  0x78, 0x9C, 0x63, 0x00, 0x01, 0x00, 0x00, 0x05, 0x00, 0x01, // IDAT data
  0x0D, 0x0A, 0x2D, 0xB4, // IDAT CRC
  0x00, 0x00, 0x00, 0x00, // IEND length
  0x49, 0x45, 0x4E, 0x44, // IEND
  0xAE, 0x42, 0x60, 0x82, // IEND CRC
];
