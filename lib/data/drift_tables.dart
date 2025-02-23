import 'package:drift/drift.dart';

class DatabaseSearchHistories extends Table {
  TextColumn get searchKey => text()();

  DateTimeColumn get searchTime =>
      dateTime().clientDefault(() => DateTime.now())();

  @override
  Set<Column<Object>>? get primaryKey => {searchKey};
}
