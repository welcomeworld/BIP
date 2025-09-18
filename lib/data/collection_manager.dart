import 'dart:async';

import 'package:bip/data/model/media_collection.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/source_manager.dart';

import 'drift_database.dart';

class CollectionManager {
  SourceManager _sourceManager;
  final BipDatabase _database;

  static CollectionManager? _ins;

  CollectionManager._({SourceManager? sourceManager, BipDatabase? database})
      : _sourceManager = sourceManager ?? SourceManager(),
        _database = database ?? BipDatabase() {
    _ins = this;
  }

  factory CollectionManager({SourceManager? sourceManager}) =>
      _ins ?? CollectionManager._(sourceManager: sourceManager);

  Stream<List<MediaCollection>> requestMediaCollections({String? sourceName}) {
    final controller = StreamController<List<MediaCollection>>();
    final List<Future<void>> futures = [];
    futures.add(
      _localMediaCollections().then((result) {
        controller.add(result);
      }).catchError((error) {
        controller.addError(error);
      }),
    );
    for (final source in _sourceManager.activeSources.values) {
      if (sourceName != null && source.sourceName != sourceName) {
        continue;
      }
      final sourceFuture = source.requestMediaCollections().then((result) {
        controller.add(result.result);
      }).catchError((error) {
        controller.addError(error);
      });
      futures.add(sourceFuture);
    }
    Future.wait(futures).then((_) => controller.close());
    return controller.stream;
  }

  Future<List<MediaCollection>> _localMediaCollections() async {
    return await _database.queryMediaCollections();
  }

  Future<bool> saveMediaCollection(MediaCollection collection) async {
    if (collection.local) {
      return await _database.saveMediaCollection(collection);
    } else {
      final source = _sourceManager.activeSources[collection.sourceName];
      if (source != null) {
        return await source.saveMediaCollection(collection);
      } else {
        return false;
      }
    }
  }

  Future<bool> deleteMediaCollection(MediaCollection collection) async {
    if (collection.local) {
      return await _database.deleteMediaCollection(collection);
    } else {
      final source = _sourceManager.activeSources[collection.sourceName];
      if (source != null) {
        return await source.deleteMediaCollection(collection);
      } else {
        return false;
      }
    }
  }

  Future<bool> addToMediaCollection(
      MediaCollection collection, MediaPagePreview preview) async {
    if (collection.local) {
      return await _database.addToMediaCollection(collection, preview);
    } else {
      final source = _sourceManager.activeSources[collection.sourceName];
      if (source != null) {
        return await source.addToMediaCollection(collection, preview);
      } else {
        return false;
      }
    }
  }

  Future<bool> removeFromMediaCollection(
      MediaCollection collection, MediaPagePreview preview) async {
    if (collection.local) {
      return await _database.removeFromMediaCollection(collection, preview);
    } else {
      final source = _sourceManager.activeSources[collection.sourceName];
      if (source != null) {
        return await source.removeFromMediaCollection(collection, preview);
      } else {
        return false;
      }
    }
  }

  Future<bool> isInMediaCollection(MediaPagePreview preview) async {
    final localResult = await _database.isInMediaCollection(preview);
    if (localResult) {
      return true;
    }
    final source = _sourceManager.activeSources[preview.sourceName];
    if (source != null) {
      return await source.isInMediaCollection(preview);
    } else {
      return false;
    }
  }

  Future<bool> removeFromAllMediaCollection(MediaPagePreview preview) async {
    bool success = true;
    final collections = await _localMediaCollections();
    for (final collection in collections) {
      final ids =
          collection.extras[MediaCollection.localIdsKey] as List<dynamic>? ??
              [];
      if (ids.contains(preview.uniqueId)) {
        var result =
            await _database.removeFromMediaCollection(collection, preview);
        if (!result) {
          success = false;
        }
      }
    }

    final sourceName = preview.sourceName;
    for (final source in _sourceManager.activeSources.values) {
      if (source.sourceName == sourceName) {
        return success && await source.removeFromAllMediaCollection(preview);
      }
    }
    return success;
  }

  Future<List<MediaPagePreview>> requestMediaCollectionDetail(
      MediaCollection collection,
      {String key = "",
      int pageNumber = 1}) async {
    if (collection.local) {
      return await _database.queryMediaCollectionDetail(collection,
          key: key, pageNumber: pageNumber);
    } else {
      final sourceName = collection.sourceName;
      for (final source in _sourceManager.activeSources.values) {
        if (source.sourceName == sourceName) {
          return (await source.requestMediaCollectionDetail(collection,
                  key: key, pageNumber: pageNumber))
              .result;
        }
      }
    }
    return [];
  }
}
