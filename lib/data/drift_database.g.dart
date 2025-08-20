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

class $DatabaseMediaPageHistoriesTable extends DatabaseMediaPageHistories
    with TableInfo<$DatabaseMediaPageHistoriesTable, DatabaseMediaPageHistory> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $DatabaseMediaPageHistoriesTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _idMeta = const VerificationMeta('id');
  @override
  late final GeneratedColumn<String> id = GeneratedColumn<String>(
      'id', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _titleMeta = const VerificationMeta('title');
  @override
  late final GeneratedColumn<String> title = GeneratedColumn<String>(
      'title', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _viewTimeMeta =
      const VerificationMeta('viewTime');
  @override
  late final GeneratedColumn<DateTime> viewTime = GeneratedColumn<DateTime>(
      'view_time', aliasedName, false,
      type: DriftSqlType.dateTime, requiredDuringInsert: true);
  static const VerificationMeta _mediaPagePreviewMeta =
      const VerificationMeta('mediaPagePreview');
  @override
  late final GeneratedColumnWithTypeConverter<MediaPagePreview, String>
      mediaPagePreview = GeneratedColumn<String>(
              'media_page_preview', aliasedName, false,
              type: DriftSqlType.string, requiredDuringInsert: true)
          .withConverter<MediaPagePreview>(
              $DatabaseMediaPageHistoriesTable.$convertermediaPagePreview);
  @override
  List<GeneratedColumn> get $columns => [id, title, viewTime, mediaPagePreview];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'database_media_page_histories';
  @override
  VerificationContext validateIntegrity(
      Insertable<DatabaseMediaPageHistory> instance,
      {bool isInserting = false}) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('id')) {
      context.handle(_idMeta, id.isAcceptableOrUnknown(data['id']!, _idMeta));
    } else if (isInserting) {
      context.missing(_idMeta);
    }
    if (data.containsKey('title')) {
      context.handle(
          _titleMeta, title.isAcceptableOrUnknown(data['title']!, _titleMeta));
    } else if (isInserting) {
      context.missing(_titleMeta);
    }
    if (data.containsKey('view_time')) {
      context.handle(_viewTimeMeta,
          viewTime.isAcceptableOrUnknown(data['view_time']!, _viewTimeMeta));
    } else if (isInserting) {
      context.missing(_viewTimeMeta);
    }
    context.handle(_mediaPagePreviewMeta, const VerificationResult.success());
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {id};
  @override
  DatabaseMediaPageHistory map(Map<String, dynamic> data,
      {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return DatabaseMediaPageHistory(
      id: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}id'])!,
      title: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}title'])!,
      viewTime: attachedDatabase.typeMapping
          .read(DriftSqlType.dateTime, data['${effectivePrefix}view_time'])!,
      mediaPagePreview: $DatabaseMediaPageHistoriesTable
          .$convertermediaPagePreview
          .fromSql(attachedDatabase.typeMapping.read(DriftSqlType.string,
              data['${effectivePrefix}media_page_preview'])!),
    );
  }

  @override
  $DatabaseMediaPageHistoriesTable createAlias(String alias) {
    return $DatabaseMediaPageHistoriesTable(attachedDatabase, alias);
  }

  static TypeConverter<MediaPagePreview, String> $convertermediaPagePreview =
      const MediaPagePreviewConverter();
}

class DatabaseMediaPageHistory extends DataClass
    implements Insertable<DatabaseMediaPageHistory> {
  final String id;
  final String title;
  final DateTime viewTime;
  final MediaPagePreview mediaPagePreview;
  const DatabaseMediaPageHistory(
      {required this.id,
      required this.title,
      required this.viewTime,
      required this.mediaPagePreview});
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['id'] = Variable<String>(id);
    map['title'] = Variable<String>(title);
    map['view_time'] = Variable<DateTime>(viewTime);
    {
      map['media_page_preview'] = Variable<String>(
          $DatabaseMediaPageHistoriesTable.$convertermediaPagePreview
              .toSql(mediaPagePreview));
    }
    return map;
  }

  DatabaseMediaPageHistoriesCompanion toCompanion(bool nullToAbsent) {
    return DatabaseMediaPageHistoriesCompanion(
      id: Value(id),
      title: Value(title),
      viewTime: Value(viewTime),
      mediaPagePreview: Value(mediaPagePreview),
    );
  }

  factory DatabaseMediaPageHistory.fromJson(Map<String, dynamic> json,
      {ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return DatabaseMediaPageHistory(
      id: serializer.fromJson<String>(json['id']),
      title: serializer.fromJson<String>(json['title']),
      viewTime: serializer.fromJson<DateTime>(json['viewTime']),
      mediaPagePreview:
          serializer.fromJson<MediaPagePreview>(json['mediaPagePreview']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'id': serializer.toJson<String>(id),
      'title': serializer.toJson<String>(title),
      'viewTime': serializer.toJson<DateTime>(viewTime),
      'mediaPagePreview': serializer.toJson<MediaPagePreview>(mediaPagePreview),
    };
  }

  DatabaseMediaPageHistory copyWith(
          {String? id,
          String? title,
          DateTime? viewTime,
          MediaPagePreview? mediaPagePreview}) =>
      DatabaseMediaPageHistory(
        id: id ?? this.id,
        title: title ?? this.title,
        viewTime: viewTime ?? this.viewTime,
        mediaPagePreview: mediaPagePreview ?? this.mediaPagePreview,
      );
  DatabaseMediaPageHistory copyWithCompanion(
      DatabaseMediaPageHistoriesCompanion data) {
    return DatabaseMediaPageHistory(
      id: data.id.present ? data.id.value : this.id,
      title: data.title.present ? data.title.value : this.title,
      viewTime: data.viewTime.present ? data.viewTime.value : this.viewTime,
      mediaPagePreview: data.mediaPagePreview.present
          ? data.mediaPagePreview.value
          : this.mediaPagePreview,
    );
  }

  @override
  String toString() {
    return (StringBuffer('DatabaseMediaPageHistory(')
          ..write('id: $id, ')
          ..write('title: $title, ')
          ..write('viewTime: $viewTime, ')
          ..write('mediaPagePreview: $mediaPagePreview')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(id, title, viewTime, mediaPagePreview);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is DatabaseMediaPageHistory &&
          other.id == this.id &&
          other.title == this.title &&
          other.viewTime == this.viewTime &&
          other.mediaPagePreview == this.mediaPagePreview);
}

class DatabaseMediaPageHistoriesCompanion
    extends UpdateCompanion<DatabaseMediaPageHistory> {
  final Value<String> id;
  final Value<String> title;
  final Value<DateTime> viewTime;
  final Value<MediaPagePreview> mediaPagePreview;
  final Value<int> rowid;
  const DatabaseMediaPageHistoriesCompanion({
    this.id = const Value.absent(),
    this.title = const Value.absent(),
    this.viewTime = const Value.absent(),
    this.mediaPagePreview = const Value.absent(),
    this.rowid = const Value.absent(),
  });
  DatabaseMediaPageHistoriesCompanion.insert({
    required String id,
    required String title,
    required DateTime viewTime,
    required MediaPagePreview mediaPagePreview,
    this.rowid = const Value.absent(),
  })  : id = Value(id),
        title = Value(title),
        viewTime = Value(viewTime),
        mediaPagePreview = Value(mediaPagePreview);
  static Insertable<DatabaseMediaPageHistory> custom({
    Expression<String>? id,
    Expression<String>? title,
    Expression<DateTime>? viewTime,
    Expression<String>? mediaPagePreview,
    Expression<int>? rowid,
  }) {
    return RawValuesInsertable({
      if (id != null) 'id': id,
      if (title != null) 'title': title,
      if (viewTime != null) 'view_time': viewTime,
      if (mediaPagePreview != null) 'media_page_preview': mediaPagePreview,
      if (rowid != null) 'rowid': rowid,
    });
  }

  DatabaseMediaPageHistoriesCompanion copyWith(
      {Value<String>? id,
      Value<String>? title,
      Value<DateTime>? viewTime,
      Value<MediaPagePreview>? mediaPagePreview,
      Value<int>? rowid}) {
    return DatabaseMediaPageHistoriesCompanion(
      id: id ?? this.id,
      title: title ?? this.title,
      viewTime: viewTime ?? this.viewTime,
      mediaPagePreview: mediaPagePreview ?? this.mediaPagePreview,
      rowid: rowid ?? this.rowid,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (id.present) {
      map['id'] = Variable<String>(id.value);
    }
    if (title.present) {
      map['title'] = Variable<String>(title.value);
    }
    if (viewTime.present) {
      map['view_time'] = Variable<DateTime>(viewTime.value);
    }
    if (mediaPagePreview.present) {
      map['media_page_preview'] = Variable<String>(
          $DatabaseMediaPageHistoriesTable.$convertermediaPagePreview
              .toSql(mediaPagePreview.value));
    }
    if (rowid.present) {
      map['rowid'] = Variable<int>(rowid.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('DatabaseMediaPageHistoriesCompanion(')
          ..write('id: $id, ')
          ..write('title: $title, ')
          ..write('viewTime: $viewTime, ')
          ..write('mediaPagePreview: $mediaPagePreview, ')
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
  late final $DatabaseMediaPageHistoriesTable databaseMediaPageHistories =
      $DatabaseMediaPageHistoriesTable(this);
  @override
  Iterable<TableInfo<Table, Object?>> get allTables =>
      allSchemaEntities.whereType<TableInfo<Table, Object?>>();
  @override
  List<DatabaseSchemaEntity> get allSchemaEntities =>
      [databaseSearchHistories, databaseMediaPageHistories];
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
typedef $$DatabaseMediaPageHistoriesTableCreateCompanionBuilder
    = DatabaseMediaPageHistoriesCompanion Function({
  required String id,
  required String title,
  required DateTime viewTime,
  required MediaPagePreview mediaPagePreview,
  Value<int> rowid,
});
typedef $$DatabaseMediaPageHistoriesTableUpdateCompanionBuilder
    = DatabaseMediaPageHistoriesCompanion Function({
  Value<String> id,
  Value<String> title,
  Value<DateTime> viewTime,
  Value<MediaPagePreview> mediaPagePreview,
  Value<int> rowid,
});

class $$DatabaseMediaPageHistoriesTableFilterComposer
    extends Composer<_$BipDatabase, $DatabaseMediaPageHistoriesTable> {
  $$DatabaseMediaPageHistoriesTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<String> get id => $composableBuilder(
      column: $table.id, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get title => $composableBuilder(
      column: $table.title, builder: (column) => ColumnFilters(column));

  ColumnFilters<DateTime> get viewTime => $composableBuilder(
      column: $table.viewTime, builder: (column) => ColumnFilters(column));

  ColumnWithTypeConverterFilters<MediaPagePreview, MediaPagePreview, String>
      get mediaPagePreview => $composableBuilder(
          column: $table.mediaPagePreview,
          builder: (column) => ColumnWithTypeConverterFilters(column));
}

class $$DatabaseMediaPageHistoriesTableOrderingComposer
    extends Composer<_$BipDatabase, $DatabaseMediaPageHistoriesTable> {
  $$DatabaseMediaPageHistoriesTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<String> get id => $composableBuilder(
      column: $table.id, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get title => $composableBuilder(
      column: $table.title, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get viewTime => $composableBuilder(
      column: $table.viewTime, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get mediaPagePreview => $composableBuilder(
      column: $table.mediaPagePreview,
      builder: (column) => ColumnOrderings(column));
}

class $$DatabaseMediaPageHistoriesTableAnnotationComposer
    extends Composer<_$BipDatabase, $DatabaseMediaPageHistoriesTable> {
  $$DatabaseMediaPageHistoriesTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumn<String> get id =>
      $composableBuilder(column: $table.id, builder: (column) => column);

  GeneratedColumn<String> get title =>
      $composableBuilder(column: $table.title, builder: (column) => column);

  GeneratedColumn<DateTime> get viewTime =>
      $composableBuilder(column: $table.viewTime, builder: (column) => column);

  GeneratedColumnWithTypeConverter<MediaPagePreview, String>
      get mediaPagePreview => $composableBuilder(
          column: $table.mediaPagePreview, builder: (column) => column);
}

class $$DatabaseMediaPageHistoriesTableTableManager extends RootTableManager<
    _$BipDatabase,
    $DatabaseMediaPageHistoriesTable,
    DatabaseMediaPageHistory,
    $$DatabaseMediaPageHistoriesTableFilterComposer,
    $$DatabaseMediaPageHistoriesTableOrderingComposer,
    $$DatabaseMediaPageHistoriesTableAnnotationComposer,
    $$DatabaseMediaPageHistoriesTableCreateCompanionBuilder,
    $$DatabaseMediaPageHistoriesTableUpdateCompanionBuilder,
    (
      DatabaseMediaPageHistory,
      BaseReferences<_$BipDatabase, $DatabaseMediaPageHistoriesTable,
          DatabaseMediaPageHistory>
    ),
    DatabaseMediaPageHistory,
    PrefetchHooks Function()> {
  $$DatabaseMediaPageHistoriesTableTableManager(
      _$BipDatabase db, $DatabaseMediaPageHistoriesTable table)
      : super(TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$DatabaseMediaPageHistoriesTableFilterComposer(
                  $db: db, $table: table),
          createOrderingComposer: () =>
              $$DatabaseMediaPageHistoriesTableOrderingComposer(
                  $db: db, $table: table),
          createComputedFieldComposer: () =>
              $$DatabaseMediaPageHistoriesTableAnnotationComposer(
                  $db: db, $table: table),
          updateCompanionCallback: ({
            Value<String> id = const Value.absent(),
            Value<String> title = const Value.absent(),
            Value<DateTime> viewTime = const Value.absent(),
            Value<MediaPagePreview> mediaPagePreview = const Value.absent(),
            Value<int> rowid = const Value.absent(),
          }) =>
              DatabaseMediaPageHistoriesCompanion(
            id: id,
            title: title,
            viewTime: viewTime,
            mediaPagePreview: mediaPagePreview,
            rowid: rowid,
          ),
          createCompanionCallback: ({
            required String id,
            required String title,
            required DateTime viewTime,
            required MediaPagePreview mediaPagePreview,
            Value<int> rowid = const Value.absent(),
          }) =>
              DatabaseMediaPageHistoriesCompanion.insert(
            id: id,
            title: title,
            viewTime: viewTime,
            mediaPagePreview: mediaPagePreview,
            rowid: rowid,
          ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ));
}

typedef $$DatabaseMediaPageHistoriesTableProcessedTableManager
    = ProcessedTableManager<
        _$BipDatabase,
        $DatabaseMediaPageHistoriesTable,
        DatabaseMediaPageHistory,
        $$DatabaseMediaPageHistoriesTableFilterComposer,
        $$DatabaseMediaPageHistoriesTableOrderingComposer,
        $$DatabaseMediaPageHistoriesTableAnnotationComposer,
        $$DatabaseMediaPageHistoriesTableCreateCompanionBuilder,
        $$DatabaseMediaPageHistoriesTableUpdateCompanionBuilder,
        (
          DatabaseMediaPageHistory,
          BaseReferences<_$BipDatabase, $DatabaseMediaPageHistoriesTable,
              DatabaseMediaPageHistory>
        ),
        DatabaseMediaPageHistory,
        PrefetchHooks Function()>;

class $BipDatabaseManager {
  final _$BipDatabase _db;
  $BipDatabaseManager(this._db);
  $$DatabaseSearchHistoriesTableTableManager get databaseSearchHistories =>
      $$DatabaseSearchHistoriesTableTableManager(
          _db, _db.databaseSearchHistories);
  $$DatabaseMediaPageHistoriesTableTableManager
      get databaseMediaPageHistories =>
          $$DatabaseMediaPageHistoriesTableTableManager(
              _db, _db.databaseMediaPageHistories);
}
