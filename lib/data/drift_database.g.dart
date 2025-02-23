// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'drift_database.dart';

// ignore_for_file: type=lint
class $DatabaseSearchHistoriesTable extends DatabaseSearchHistories
    with TableInfo<$DatabaseSearchHistoriesTable, DatabaseSearchHistory> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $DatabaseSearchHistoriesTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _searchKeyMeta =
      const VerificationMeta('searchKey');
  @override
  late final GeneratedColumn<String> searchKey = GeneratedColumn<String>(
      'search_key', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _searchTimeMeta =
      const VerificationMeta('searchTime');
  @override
  late final GeneratedColumn<DateTime> searchTime = GeneratedColumn<DateTime>(
      'search_time', aliasedName, false,
      type: DriftSqlType.dateTime,
      requiredDuringInsert: false,
      clientDefault: () => DateTime.now());
  @override
  List<GeneratedColumn> get $columns => [searchKey, searchTime];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'database_search_histories';
  @override
  VerificationContext validateIntegrity(
      Insertable<DatabaseSearchHistory> instance,
      {bool isInserting = false}) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('search_key')) {
      context.handle(_searchKeyMeta,
          searchKey.isAcceptableOrUnknown(data['search_key']!, _searchKeyMeta));
    } else if (isInserting) {
      context.missing(_searchKeyMeta);
    }
    if (data.containsKey('search_time')) {
      context.handle(
          _searchTimeMeta,
          searchTime.isAcceptableOrUnknown(
              data['search_time']!, _searchTimeMeta));
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {searchKey};
  @override
  DatabaseSearchHistory map(Map<String, dynamic> data, {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return DatabaseSearchHistory(
      searchKey: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}search_key'])!,
      searchTime: attachedDatabase.typeMapping
          .read(DriftSqlType.dateTime, data['${effectivePrefix}search_time'])!,
    );
  }

  @override
  $DatabaseSearchHistoriesTable createAlias(String alias) {
    return $DatabaseSearchHistoriesTable(attachedDatabase, alias);
  }
}

class DatabaseSearchHistory extends DataClass
    implements Insertable<DatabaseSearchHistory> {
  final String searchKey;
  final DateTime searchTime;
  const DatabaseSearchHistory(
      {required this.searchKey, required this.searchTime});
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['search_key'] = Variable<String>(searchKey);
    map['search_time'] = Variable<DateTime>(searchTime);
    return map;
  }

  DatabaseSearchHistoriesCompanion toCompanion(bool nullToAbsent) {
    return DatabaseSearchHistoriesCompanion(
      searchKey: Value(searchKey),
      searchTime: Value(searchTime),
    );
  }

  factory DatabaseSearchHistory.fromJson(Map<String, dynamic> json,
      {ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return DatabaseSearchHistory(
      searchKey: serializer.fromJson<String>(json['searchKey']),
      searchTime: serializer.fromJson<DateTime>(json['searchTime']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'searchKey': serializer.toJson<String>(searchKey),
      'searchTime': serializer.toJson<DateTime>(searchTime),
    };
  }

  DatabaseSearchHistory copyWith({String? searchKey, DateTime? searchTime}) =>
      DatabaseSearchHistory(
        searchKey: searchKey ?? this.searchKey,
        searchTime: searchTime ?? this.searchTime,
      );
  DatabaseSearchHistory copyWithCompanion(
      DatabaseSearchHistoriesCompanion data) {
    return DatabaseSearchHistory(
      searchKey: data.searchKey.present ? data.searchKey.value : this.searchKey,
      searchTime:
          data.searchTime.present ? data.searchTime.value : this.searchTime,
    );
  }

  @override
  String toString() {
    return (StringBuffer('DatabaseSearchHistory(')
          ..write('searchKey: $searchKey, ')
          ..write('searchTime: $searchTime')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(searchKey, searchTime);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is DatabaseSearchHistory &&
          other.searchKey == this.searchKey &&
          other.searchTime == this.searchTime);
}

class DatabaseSearchHistoriesCompanion
    extends UpdateCompanion<DatabaseSearchHistory> {
  final Value<String> searchKey;
  final Value<DateTime> searchTime;
  final Value<int> rowid;
  const DatabaseSearchHistoriesCompanion({
    this.searchKey = const Value.absent(),
    this.searchTime = const Value.absent(),
    this.rowid = const Value.absent(),
  });
  DatabaseSearchHistoriesCompanion.insert({
    required String searchKey,
    this.searchTime = const Value.absent(),
    this.rowid = const Value.absent(),
  }) : searchKey = Value(searchKey);
  static Insertable<DatabaseSearchHistory> custom({
    Expression<String>? searchKey,
    Expression<DateTime>? searchTime,
    Expression<int>? rowid,
  }) {
    return RawValuesInsertable({
      if (searchKey != null) 'search_key': searchKey,
      if (searchTime != null) 'search_time': searchTime,
      if (rowid != null) 'rowid': rowid,
    });
  }

  DatabaseSearchHistoriesCompanion copyWith(
      {Value<String>? searchKey,
      Value<DateTime>? searchTime,
      Value<int>? rowid}) {
    return DatabaseSearchHistoriesCompanion(
      searchKey: searchKey ?? this.searchKey,
      searchTime: searchTime ?? this.searchTime,
      rowid: rowid ?? this.rowid,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (searchKey.present) {
      map['search_key'] = Variable<String>(searchKey.value);
    }
    if (searchTime.present) {
      map['search_time'] = Variable<DateTime>(searchTime.value);
    }
    if (rowid.present) {
      map['rowid'] = Variable<int>(rowid.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('DatabaseSearchHistoriesCompanion(')
          ..write('searchKey: $searchKey, ')
          ..write('searchTime: $searchTime, ')
          ..write('rowid: $rowid')
          ..write(')'))
        .toString();
  }
}

abstract class _$BipDatabase extends GeneratedDatabase {
  _$BipDatabase(QueryExecutor e) : super(e);
  $BipDatabaseManager get managers => $BipDatabaseManager(this);
  late final $DatabaseSearchHistoriesTable databaseSearchHistories =
      $DatabaseSearchHistoriesTable(this);
  @override
  Iterable<TableInfo<Table, Object?>> get allTables =>
      allSchemaEntities.whereType<TableInfo<Table, Object?>>();
  @override
  List<DatabaseSchemaEntity> get allSchemaEntities => [databaseSearchHistories];
}

typedef $$DatabaseSearchHistoriesTableCreateCompanionBuilder
    = DatabaseSearchHistoriesCompanion Function({
  required String searchKey,
  Value<DateTime> searchTime,
  Value<int> rowid,
});
typedef $$DatabaseSearchHistoriesTableUpdateCompanionBuilder
    = DatabaseSearchHistoriesCompanion Function({
  Value<String> searchKey,
  Value<DateTime> searchTime,
  Value<int> rowid,
});

class $$DatabaseSearchHistoriesTableFilterComposer
    extends Composer<_$BipDatabase, $DatabaseSearchHistoriesTable> {
  $$DatabaseSearchHistoriesTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<String> get searchKey => $composableBuilder(
      column: $table.searchKey, builder: (column) => ColumnFilters(column));

  ColumnFilters<DateTime> get searchTime => $composableBuilder(
      column: $table.searchTime, builder: (column) => ColumnFilters(column));
}

class $$DatabaseSearchHistoriesTableOrderingComposer
    extends Composer<_$BipDatabase, $DatabaseSearchHistoriesTable> {
  $$DatabaseSearchHistoriesTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<String> get searchKey => $composableBuilder(
      column: $table.searchKey, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get searchTime => $composableBuilder(
      column: $table.searchTime, builder: (column) => ColumnOrderings(column));
}

class $$DatabaseSearchHistoriesTableAnnotationComposer
    extends Composer<_$BipDatabase, $DatabaseSearchHistoriesTable> {
  $$DatabaseSearchHistoriesTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumn<String> get searchKey =>
      $composableBuilder(column: $table.searchKey, builder: (column) => column);

  GeneratedColumn<DateTime> get searchTime => $composableBuilder(
      column: $table.searchTime, builder: (column) => column);
}

class $$DatabaseSearchHistoriesTableTableManager extends RootTableManager<
    _$BipDatabase,
    $DatabaseSearchHistoriesTable,
    DatabaseSearchHistory,
    $$DatabaseSearchHistoriesTableFilterComposer,
    $$DatabaseSearchHistoriesTableOrderingComposer,
    $$DatabaseSearchHistoriesTableAnnotationComposer,
    $$DatabaseSearchHistoriesTableCreateCompanionBuilder,
    $$DatabaseSearchHistoriesTableUpdateCompanionBuilder,
    (
      DatabaseSearchHistory,
      BaseReferences<_$BipDatabase, $DatabaseSearchHistoriesTable,
          DatabaseSearchHistory>
    ),
    DatabaseSearchHistory,
    PrefetchHooks Function()> {
  $$DatabaseSearchHistoriesTableTableManager(
      _$BipDatabase db, $DatabaseSearchHistoriesTable table)
      : super(TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$DatabaseSearchHistoriesTableFilterComposer(
                  $db: db, $table: table),
          createOrderingComposer: () =>
              $$DatabaseSearchHistoriesTableOrderingComposer(
                  $db: db, $table: table),
          createComputedFieldComposer: () =>
              $$DatabaseSearchHistoriesTableAnnotationComposer(
                  $db: db, $table: table),
          updateCompanionCallback: ({
            Value<String> searchKey = const Value.absent(),
            Value<DateTime> searchTime = const Value.absent(),
            Value<int> rowid = const Value.absent(),
          }) =>
              DatabaseSearchHistoriesCompanion(
            searchKey: searchKey,
            searchTime: searchTime,
            rowid: rowid,
          ),
          createCompanionCallback: ({
            required String searchKey,
            Value<DateTime> searchTime = const Value.absent(),
            Value<int> rowid = const Value.absent(),
          }) =>
              DatabaseSearchHistoriesCompanion.insert(
            searchKey: searchKey,
            searchTime: searchTime,
            rowid: rowid,
          ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ));
}

typedef $$DatabaseSearchHistoriesTableProcessedTableManager
    = ProcessedTableManager<
        _$BipDatabase,
        $DatabaseSearchHistoriesTable,
        DatabaseSearchHistory,
        $$DatabaseSearchHistoriesTableFilterComposer,
        $$DatabaseSearchHistoriesTableOrderingComposer,
        $$DatabaseSearchHistoriesTableAnnotationComposer,
        $$DatabaseSearchHistoriesTableCreateCompanionBuilder,
        $$DatabaseSearchHistoriesTableUpdateCompanionBuilder,
        (
          DatabaseSearchHistory,
          BaseReferences<_$BipDatabase, $DatabaseSearchHistoriesTable,
              DatabaseSearchHistory>
        ),
        DatabaseSearchHistory,
        PrefetchHooks Function()>;

class $BipDatabaseManager {
  final _$BipDatabase _db;
  $BipDatabaseManager(this._db);
  $$DatabaseSearchHistoriesTableTableManager get databaseSearchHistories =>
      $$DatabaseSearchHistoriesTableTableManager(
          _db, _db.databaseSearchHistories);
}
