import 'package:bip/data/source/bilibili/bili_source.dart';
import 'package:bip/data/source/bimi/bimi_source.dart';
import 'package:bip/data/source/gugufan/gugufan_source.dart';
import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/kv_store.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/interfaces/source_manager.dart';
import 'package:bip/domain/interfaces/web_net.dart';

class SourceManagerImpl implements SourceManager {
  late final Source _mainSource;
  late final Map<String, Source> _sources;

  static SourceManagerImpl? _ins;

  SourceManagerImpl._({Source? mainSource, Map<String, Source>? sources}) {
    _ins = this;
    final WebNet webNet = getIt<WebNet>();
    _mainSource = mainSource ?? BiliSource(getIt<KvStore>(), webNet);
    var gugufanSource = GugufanSource(webNet);
    var bimiSource = BimiSource(webNet);
    _sources = sources ??
        {
          _mainSource.sourceName: _mainSource,
          gugufanSource.sourceName: gugufanSource,
          bimiSource.sourceName: bimiSource
        };
  }

  factory SourceManagerImpl(
          {Source? mainSource, Map<String, Source>? sources}) =>
      _ins ?? SourceManagerImpl._(mainSource: mainSource, sources: sources);

  @override
  Source get mainSource => _mainSource;

  @override
  Map<String, Source> get activeSources => _sources;
}
