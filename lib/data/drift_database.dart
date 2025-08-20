import 'package:bip/data/drift_tables.dart';
import 'package:drift/drift.dart';
import 'package:drift_flutter/drift_flutter.dart';

import 'drift_converter.dart';
import 'model/media_page_history.dart';
import 'model/media_page_preview.dart';
import 'model/search_history.dart';

part 'drift_database.g.dart';

@DriftDatabase(tables: [DatabaseSearchHistories, DatabaseMediaPageHistories])
class BipDatabase extends _$BipDatabase {
  static BipDatabase? _ins;

  BipDatabase._() : super(_openConnection()) {
    _ins = this;
  }

  factory BipDatabase() => _ins ?? BipDatabase._();

  @override
  int get schemaVersion => 2;

  @override
  MigrationStrategy get migration => MigrationStrategy(
        onUpgrade: (Migrator m, int from, int to) async {
          if (from == 1 && to == 2) {
            // 新增 DatabaseMediaPageHistories 表
            await m.createTable(databaseMediaPageHistories);
          }
        },
      );

  static QueryExecutor _openConnection() {
    return driftDatabase(
      name: 'bip_database',
    );
  }

  Future<void> saveSearchHistory(SearchHistory history) async {
    await into(databaseSearchHistories).insertOnConflictUpdate(
      DatabaseSearchHistoriesCompanion(
        searchKey: Value(history.searchKey),
        searchTime: Value(history.searchTime),
      ),
    );
  }

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

  Future<void> clearSearchHistories() async {
    await delete(databaseSearchHistories).go();
  }

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
}
