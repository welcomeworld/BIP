import 'package:bip/data/drift_tables.dart';
import 'package:drift/drift.dart';
import 'package:drift_flutter/drift_flutter.dart';

import 'model/search_history.dart';

part 'drift_database.g.dart';

@DriftDatabase(tables: [DatabaseSearchHistories])
class BipDatabase extends _$BipDatabase {
  BipDatabase() : super(_openConnection());

  @override
  int get schemaVersion => 1;

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
}
