import 'package:flutter/foundation.dart';

class Logger {
  Logger._();

  static void logConsole(String log) {
    if (kDebugMode) {
      print(log);
    }
  }
}
