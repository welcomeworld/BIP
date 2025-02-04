import 'dart:async';
import 'dart:io';
import 'dart:isolate';

import 'package:bip/data/net/web_net.dart';
import 'package:bip/utils/common_util.dart';
import 'package:bip/utils/logger.dart';
import 'package:dio/dio.dart' as dio;
import 'package:shelf/shelf.dart' as shelf;
import 'package:shelf/shelf_io.dart' as shelf_io;

bool _isProxyIsoRunning = false;

void startProxyIso() {
  if (_isProxyIsoRunning) {
    return;
  }
  _isProxyIsoRunning = true;
  ReceivePort receivePort = ReceivePort();
  Isolate.spawn(startProxyServer, receivePort.sendPort);
  receivePort.close();
}

Future<void> startProxyServer(SendPort sendPort) async {
  HttpServer httpServer = await shelf_io.serve(
      shelf.logRequests().addHandler(_echoRequest),
      InternetAddress.anyIPv4,
      8080);
  // Enable content compression
  httpServer.autoCompress = true;
  Logger.logConsole(
      'Serving at http://${httpServer.address.host}:${httpServer.port}');
}

Future<shelf.Response> _echoRequest(shelf.Request request) async {
  var requestHeader = Map<String, String>.from(request.headers)
    ..remove("host")
    ..["Referer"] = "https://www.bilibili.com/";
  dio.Options options = dio.Options(
    headers: requestHeader,
    responseType: dio.ResponseType.stream,
  );
  var realUrl = request.url.queryParameters["url"];
  if (realUrl == null) {
    return shelf.Response.ok("Proxy url should not be null");
  }
  try {
    final response = await WebNet().get<dio.ResponseBody>(
      CommonUtil.decode64(realUrl),
      options: options,
    );
    var responseHeaders = Map<String, dynamic>.from(response.headers.map)
      ..remove("content-encoding")
      ..remove("transfer-encoding");
    var responseBody = response.data;
    return shelf.Response(response.statusCode ?? 200,
        body: responseBody!.stream.transform(StreamTransformer.fromHandlers(
          handleData: (data, sink) {
            // 将接收到的数据块转发到Shelf响应
            sink.add(data);
          },
          handleError: (e, __, sink) {
            Logger.logConsole("Proxy Server Stream Error: $e");
            sink.close();
          },
          handleDone: (sink) {
            sink.close();
          },
        )),
        headers: responseHeaders.cast<String, Object>());
  } catch (e) {
    Logger.logConsole("Proxy Server Error: $e");
    return shelf.Response.internalServerError();
  }
}
