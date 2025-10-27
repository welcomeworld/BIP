import 'package:bip/data/drift_tables.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/domain/model/media_page_history.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/search_history.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:drift/drift.dart';
import 'package:drift_flutter/drift_flutter.dart';

import 'drift_converter.dart';

part 'drift_database.g.dart';

@DriftDatabase(tables: [
  DatabaseSearchHistories,
  DatabaseMediaPageHistories,
  DatabaseMediaCollections,
])
class BipDatabase extends _$BipDatabase implements Database {
  static BipDatabase? _ins;

  BipDatabase._() : super(_openConnection()) {
    _ins = this;
  }

  factory BipDatabase() => _ins ?? BipDatabase._();

  @override
  int get schemaVersion => 3;

  @override
  MigrationStrategy get migration => MigrationStrategy(
        onUpgrade: (Migrator m, int from, int to) async {
          if (from < 2) {
            // 新增 DatabaseMediaPageHistories 表
            await m.createTable(databaseMediaPageHistories);
          }
          if (from < 3) {
            await m.createTable(databaseMediaCollections);
          }
        },
      );

  static QueryExecutor _openConnection() {
    return driftDatabase(
      name: 'bip_database',
    );
  }

  @override
  Future<void> saveSearchHistory(SearchHistory history) async {
    await into(databaseSearchHistories).insertOnConflictUpdate(
      DatabaseSearchHistoriesCompanion(
        searchKey: Value(history.searchKey),
        searchTime: Value(history.searchTime),
      ),
    );
  }

  @override
  Stream<List<SearchHistory>> get searchHistoryStream =>
      (select(databaseSearchHistories)
            ..orderBy([(history) => OrderingTerm.desc(history.searchTime)])
            ..limit(10))
          .map((databaseModel) {
        return SearchHistory(
          searchKey: databaseModel.searchKey,
          searchTime: databaseModel.searchTime,
        );
      }).watch();

  @override
  Future<void> clearSearchHistories() async {
    await delete(databaseSearchHistories).go();
  }

  @override
  Future<List<MediaPageHistory>> queryMediaPageHistory(
      {String key = "", int pageNumber = 1}) async {
    const pageSize = 20;
    final offset = (pageNumber - 1) * pageSize;
    return (select(databaseMediaPageHistories)
          ..where((history) => history.title.like("%$key%"))
          ..orderBy([(history) => OrderingTerm.desc(history.viewTime)])
          ..limit(pageSize, offset: offset))
        .map((databaseModel) {
      return MediaPageHistory(
        databaseModel.mediaPagePreview,
        viewTime: databaseModel.viewTime,
      );
    }).get();
  }

  @override
  Future<void> saveMediaPageHistory(MediaPageHistory history) async {
    await into(databaseMediaPageHistories).insertOnConflictUpdate(
      DatabaseMediaPageHistoriesCompanion(
        id: Value(history.id),
        viewTime: Value(history.viewTime),
        mediaPagePreview: Value(history.mediaPagePreview),
        title: Value(history.title),
      ),
    );
  }

  Future<MediaCollection?> findMediaCollection(String id) async {
    return (select(databaseMediaCollections)
          ..where((collection) => collection.id.equals(id)))
        .map((databaseModel) {
      return MediaCollection(
        sourceName: databaseModel.sourceName,
        id: databaseModel.id,
        extras: databaseModel.extras,
        owner: databaseModel.owner,
        createTime: databaseModel.createTime,
        itemCount: databaseModel.itemCount,
        title: databaseModel.title,
        cover: databaseModel.cover,
        description: databaseModel.description,
        visible: databaseModel.visible,
        local: databaseModel.local,
      );
    }).getSingleOrNull();
  }

  @override
  Future<List<MediaCollection>> queryMediaCollections({String key = ""}) async {
    return (select(databaseMediaCollections)
          ..where((collection) => collection.title.like("%$key%"))
          ..orderBy([(collection) => OrderingTerm.asc(collection.createTime)]))
        .map((databaseModel) {
      return MediaCollection(
        sourceName: databaseModel.sourceName,
        id: databaseModel.id,
        extras: databaseModel.extras,
        owner: databaseModel.owner,
        createTime: databaseModel.createTime,
        itemCount: databaseModel.itemCount,
        title: databaseModel.title,
        cover: databaseModel.cover,
        description: databaseModel.description,
        visible: databaseModel.visible,
        local: databaseModel.local,
      );
    }).get();
  }

  @override
  Future<bool> saveMediaCollection(MediaCollection collection) async {
    final result = await into(databaseMediaCollections).insertOnConflictUpdate(
      DatabaseMediaCollectionsCompanion(
        sourceName: Value(collection.sourceName),
        id: Value(collection.id),
        extras: Value(collection.extras),
        owner: Value(collection.owner),
        createTime: Value(collection.createTime),
        itemCount: Value(collection.itemCount),
        title: Value(collection.title),
        cover: Value(collection.cover),
        description: Value(collection.description),
        visible: Value(collection.visible),
        local: Value(collection.local),
      ),
    );
    return result > 0;
  }

  @override
  Future<bool> deleteMediaCollection(MediaCollection collection) async {
    final result = await delete(databaseMediaCollections)
        .delete(DatabaseMediaCollectionsCompanion(
      id: Value(collection.id),
    ));
    return result > 0;
  }

  @override
  Future<bool> addToMediaCollection(
      MediaCollection collection, MediaPagePreview preview) async {
    if (collection.extras.containsKey(MediaCollection.localIdsKey)) {
      if (collection.extras[MediaCollection.localIdsKey]
          .any((item) => item == preview.uniqueId)) {
        return false;
      } else {
        collection.extras[MediaCollection.localIdsKey].add(preview.uniqueId);
      }
    } else {
      collection.extras[MediaCollection.localIdsKey] = <String>[
        preview.uniqueId
      ];
    }
    collection.itemCount =
        collection.extras[MediaCollection.localIdsKey].length;
    collection.cover = preview.cover;
    return await saveMediaCollection(collection);
  }

  @override
  Future<bool> removeFromMediaCollection(
      MediaCollection collection, MediaPagePreview preview) async {
    if (collection.extras.containsKey(MediaCollection.localIdsKey)) {
      if (collection.extras[MediaCollection.localIdsKey]
          .any((item) => item == preview.uniqueId)) {
        collection.extras[MediaCollection.localIdsKey].remove(preview.uniqueId);
        collection.itemCount =
            collection.extras[MediaCollection.localIdsKey].length;
        if (collection.itemCount == 0) {
          collection.cover = "";
        }
        return await saveMediaCollection(collection);
      }
    }
    return false;
  }

  @override
  Future<bool> isInMediaCollection(MediaPagePreview preview) async {
    final collections = await queryMediaCollections();
    for (final collection in collections) {
      if (collection.extras[MediaCollection.localIdsKey]
          .any((item) => item == preview.uniqueId)) {
        return true;
      }
    }
    return false;
  }

  @override
  Future<List<MediaPagePreview>> queryMediaCollectionDetail(
      MediaCollection collection,
      {String key = "",
      int pageNumber = 1}) async {
    // according the ids in extras to query the histories get MediaPagePreview list
    const pageSize = 20;
    final offset = (pageNumber - 1) * pageSize;
    final realCollection =
        pageNumber == 1 ? await findMediaCollection(collection.id) : collection;
    if (realCollection == null) {
      return [];
    }
    if (realCollection.extras.containsKey(MediaCollection.localIdsKey)) {
      final ids =
          realCollection.extras[MediaCollection.localIdsKey] as List<dynamic>;
      final histories = await (select(databaseMediaPageHistories)
            ..where((history) =>
                history.id.isIn(ids.whereType<String>()) &
                history.title.like("%$key%"))
            ..orderBy([(history) => OrderingTerm.desc(history.viewTime)])
            ..limit(pageSize, offset: offset))
          .get();
      return histories.map((data) => data.mediaPagePreview).toList();
    }
    return [];
  }
}
