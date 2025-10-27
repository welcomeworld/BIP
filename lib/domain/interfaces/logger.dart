/// Logger接口 - 日志记录抽象
abstract class Logger {
  /// 记录调试信息
  void debug(String message);

  /// 记录信息
  void info(String message);

  /// 记录警告
  void warning(String message);

  /// 记录错误
  void error(String message, [dynamic error, StackTrace? stackTrace]);

  /// 记录致命错误
  void fatal(String message, [dynamic error, StackTrace? stackTrace]);
}
