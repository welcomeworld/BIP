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

  TextColumn get mediaPagePreview =>
      text().map(const MediaPagePreviewConverter())();

  @override
  Set<Column> get primaryKey => {id};
}

class DatabaseMediaCollections extends Table {
  TextColumn get sourceName => text()();

  TextColumn get id => text()();

  TextColumn get extras => text().map(const MapConverter())();

  TextColumn get owner => text().map(const UserInfoConverter())();

  DateTimeColumn get createTime => dateTime()();

  IntColumn get itemCount => integer()();

  TextColumn get title => text()();

  TextColumn get cover => text()();

  TextColumn get description => text()();

  BoolColumn get visible => boolean()();

  BoolColumn get local => boolean()();

  @override
  Set<Column> get primaryKey => {id};
}
