import 'package:bip/data/source/bilibili/bili_source.dart';
import 'package:bip/data/source/bimi/bimi_source.dart';
import 'package:bip/data/source/gugufan/gugufan_source.dart';
import 'package:bip/data/source/source.dart';

class SourceManager {
  late final Source _mainSource;
  late final Map<String, Source> _sources;

  static SourceManager? _ins;

  SourceManager._({Source? mainSource, Map<String, Source>? sources}) {
    _ins = this;
    _mainSource = mainSource ?? BiliSource();
    var gugufanSource = GugufanSource();
    var bimiSource = BimiSource();
    _sources = sources ??
        {
          _mainSource.sourceName: _mainSource,
          gugufanSource.sourceName: gugufanSource,
          bimiSource.sourceName: bimiSource
        };
  }

  factory SourceManager({Source? mainSource, Map<String, Source>? sources}) =>
      _ins ?? SourceManager._(mainSource: mainSource, sources: sources);

  Source get mainSource => _mainSource;

  Map<String, Source> get activeSources => _sources;
}
