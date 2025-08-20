import 'package:drift/drift.dart';

import 'drift_converter.dart';

class DatabaseSearchHistories extends Table {
  TextColumn get searchKey => text()();

  DateTimeColumn get searchTime =>
      dateTime().clientDefault(() => DateTime.now())();

  @override
  Set<Column<Object>>? get primaryKey => {searchKey};
}

// 定义 Drift 表
class DatabaseMediaPageHistories extends Table {
  TextColumn get id => text()();
  TextColumn get title => text()();
  DateTimeColumn get viewTime => dateTime()();
  TextColumn get mediaPagePreview => text().map(const MediaPagePreviewConverter())();

  @override
  Set<Column> get primaryKey => {id};
}
