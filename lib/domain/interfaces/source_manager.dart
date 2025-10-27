import 'package:bip/domain/interfaces/source.dart';

/// SourceManager接口 - 源管理器抽象
abstract class SourceManager {
  /// 获取主源
  Source get mainSource;

  /// 获取所有活跃源
  Map<String, Source> get activeSources;
}
