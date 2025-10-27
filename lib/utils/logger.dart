import 'package:bip/domain/interfaces/logger.dart';
import 'package:flutter/foundation.dart';

Logger get appLogger => LoggerImpl();

class LoggerImpl implements Logger {
  LoggerImpl._();

  factory LoggerImpl() => LoggerImpl._();

  @override
  void debug(String message) {
    if (kDebugMode) {
      debugPrint("DEBUG: $message", wrapWidth: 100);
    }
  }

  @override
  void info(String message) {
    if (kDebugMode) {
      debugPrint("INFO: $message", wrapWidth: 100);
    }
  }

  @override
  void warning(String message) {
    if (kDebugMode) {
      debugPrint("WARNING: $message", wrapWidth: 100);
    }
  }

  @override
  void error(String message, [dynamic error, StackTrace? stackTrace]) {
    if (kDebugMode) {
      debugPrint("ERROR: $message", wrapWidth: 100);
      if (error != null) {
        debugPrint("Error details: $error", wrapWidth: 100);
      }
      if (stackTrace != null) {
        debugPrint("Stack trace: $stackTrace", wrapWidth: 100);
      }
    }
  }

  @override
  void fatal(String message, [dynamic error, StackTrace? stackTrace]) {
    if (kDebugMode) {
      debugPrint("FATAL: $message", wrapWidth: 100);
      if (error != null) {
        debugPrint("Error details: $error", wrapWidth: 100);
      }
      if (stackTrace != null) {
        debugPrint("Stack trace: $stackTrace", wrapWidth: 100);
      }
    }
  }

  // 保持向后兼容的静态方法
  static void logConsole(String log) {
    if (kDebugMode) {
      debugPrint("Local Logger:$log", wrapWidth: 100);
    }
  }
}
