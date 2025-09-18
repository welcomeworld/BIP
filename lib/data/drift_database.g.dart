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

class $DatabaseMediaCollectionsTable extends DatabaseMediaCollections
    with TableInfo<$DatabaseMediaCollectionsTable, DatabaseMediaCollection> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $DatabaseMediaCollectionsTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _sourceNameMeta =
      const VerificationMeta('sourceName');
  @override
  late final GeneratedColumn<String> sourceName = GeneratedColumn<String>(
      'source_name', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _idMeta = const VerificationMeta('id');
  @override
  late final GeneratedColumn<String> id = GeneratedColumn<String>(
      'id', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _extrasMeta = const VerificationMeta('extras');
  @override
  late final GeneratedColumnWithTypeConverter<Map<String, dynamic>, String>
      extras = GeneratedColumn<String>('extras', aliasedName, false,
              type: DriftSqlType.string, requiredDuringInsert: true)
          .withConverter<Map<String, dynamic>>(
              $DatabaseMediaCollectionsTable.$converterextras);
  static const VerificationMeta _ownerMeta = const VerificationMeta('owner');
  @override
  late final GeneratedColumnWithTypeConverter<UserInfo, String> owner =
      GeneratedColumn<String>('owner', aliasedName, false,
              type: DriftSqlType.string, requiredDuringInsert: true)
          .withConverter<UserInfo>(
              $DatabaseMediaCollectionsTable.$converterowner);
  static const VerificationMeta _createTimeMeta =
      const VerificationMeta('createTime');
  @override
  late final GeneratedColumn<DateTime> createTime = GeneratedColumn<DateTime>(
      'create_time', aliasedName, false,
      type: DriftSqlType.dateTime, requiredDuringInsert: true);
  static const VerificationMeta _itemCountMeta =
      const VerificationMeta('itemCount');
  @override
  late final GeneratedColumn<int> itemCount = GeneratedColumn<int>(
      'item_count', aliasedName, false,
      type: DriftSqlType.int, requiredDuringInsert: true);
  static const VerificationMeta _titleMeta = const VerificationMeta('title');
  @override
  late final GeneratedColumn<String> title = GeneratedColumn<String>(
      'title', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _coverMeta = const VerificationMeta('cover');
  @override
  late final GeneratedColumn<String> cover = GeneratedColumn<String>(
      'cover', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _descriptionMeta =
      const VerificationMeta('description');
  @override
  late final GeneratedColumn<String> description = GeneratedColumn<String>(
      'description', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _visibleMeta =
      const VerificationMeta('visible');
  @override
  late final GeneratedColumn<bool> visible = GeneratedColumn<bool>(
      'visible', aliasedName, false,
      type: DriftSqlType.bool,
      requiredDuringInsert: true,
      defaultConstraints:
          GeneratedColumn.constraintIsAlways('CHECK ("visible" IN (0, 1))'));
  static const VerificationMeta _localMeta = const VerificationMeta('local');
  @override
  late final GeneratedColumn<bool> local = GeneratedColumn<bool>(
      'local', aliasedName, false,
      type: DriftSqlType.bool,
      requiredDuringInsert: true,
      defaultConstraints:
          GeneratedColumn.constraintIsAlways('CHECK ("local" IN (0, 1))'));
  @override
  List<GeneratedColumn> get $columns => [
        sourceName,
        id,
        extras,
        owner,
        createTime,
        itemCount,
        title,
        cover,
        description,
        visible,
        local
      ];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'database_media_collections';
  @override
  VerificationContext validateIntegrity(
      Insertable<DatabaseMediaCollection> instance,
      {bool isInserting = false}) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('source_name')) {
      context.handle(
          _sourceNameMeta,
          sourceName.isAcceptableOrUnknown(
              data['source_name']!, _sourceNameMeta));
    } else if (isInserting) {
      context.missing(_sourceNameMeta);
    }
    if (data.containsKey('id')) {
      context.handle(_idMeta, id.isAcceptableOrUnknown(data['id']!, _idMeta));
    } else if (isInserting) {
      context.missing(_idMeta);
    }
    context.handle(_extrasMeta, const VerificationResult.success());
    context.handle(_ownerMeta, const VerificationResult.success());
    if (data.containsKey('create_time')) {
      context.handle(
          _createTimeMeta,
          createTime.isAcceptableOrUnknown(
              data['create_time']!, _createTimeMeta));
    } else if (isInserting) {
      context.missing(_createTimeMeta);
    }
    if (data.containsKey('item_count')) {
      context.handle(_itemCountMeta,
          itemCount.isAcceptableOrUnknown(data['item_count']!, _itemCountMeta));
    } else if (isInserting) {
      context.missing(_itemCountMeta);
    }
    if (data.containsKey('title')) {
      context.handle(
          _titleMeta, title.isAcceptableOrUnknown(data['title']!, _titleMeta));
    } else if (isInserting) {
      context.missing(_titleMeta);
    }
    if (data.containsKey('cover')) {
      context.handle(
          _coverMeta, cover.isAcceptableOrUnknown(data['cover']!, _coverMeta));
    } else if (isInserting) {
      context.missing(_coverMeta);
    }
    if (data.containsKey('description')) {
      context.handle(
          _descriptionMeta,
          description.isAcceptableOrUnknown(
              data['description']!, _descriptionMeta));
    } else if (isInserting) {
      context.missing(_descriptionMeta);
    }
    if (data.containsKey('visible')) {
      context.handle(_visibleMeta,
          visible.isAcceptableOrUnknown(data['visible']!, _visibleMeta));
    } else if (isInserting) {
      context.missing(_visibleMeta);
    }
    if (data.containsKey('local')) {
      context.handle(
          _localMeta, local.isAcceptableOrUnknown(data['local']!, _localMeta));
    } else if (isInserting) {
      context.missing(_localMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => {id};
  @override
  DatabaseMediaCollection map(Map<String, dynamic> data,
      {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return DatabaseMediaCollection(
      sourceName: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}source_name'])!,
      id: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}id'])!,
      extras: $DatabaseMediaCollectionsTable.$converterextras.fromSql(
          attachedDatabase.typeMapping
              .read(DriftSqlType.string, data['${effectivePrefix}extras'])!),
      owner: $DatabaseMediaCollectionsTable.$converterowner.fromSql(
          attachedDatabase.typeMapping
              .read(DriftSqlType.string, data['${effectivePrefix}owner'])!),
      createTime: attachedDatabase.typeMapping
          .read(DriftSqlType.dateTime, data['${effectivePrefix}create_time'])!,
      itemCount: attachedDatabase.typeMapping
          .read(DriftSqlType.int, data['${effectivePrefix}item_count'])!,
      title: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}title'])!,
      cover: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}cover'])!,
      description: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}description'])!,
      visible: attachedDatabase.typeMapping
          .read(DriftSqlType.bool, data['${effectivePrefix}visible'])!,
      local: attachedDatabase.typeMapping
          .read(DriftSqlType.bool, data['${effectivePrefix}local'])!,
    );
  }

  @override
  $DatabaseMediaCollectionsTable createAlias(String alias) {
    return $DatabaseMediaCollectionsTable(attachedDatabase, alias);
  }

  static TypeConverter<Map<String, dynamic>, String> $converterextras =
      const MapConverter();
  static TypeConverter<UserInfo, String> $converterowner =
      const UserInfoConverter();
}

class DatabaseMediaCollection extends DataClass
    implements Insertable<DatabaseMediaCollection> {
  final String sourceName;
  final String id;
  final Map<String, dynamic> extras;
  final UserInfo owner;
  final DateTime createTime;
  final int itemCount;
  final String title;
  final String cover;
  final String description;
  final bool visible;
  final bool local;
  const DatabaseMediaCollection(
      {required this.sourceName,
      required this.id,
      required this.extras,
      required this.owner,
      required this.createTime,
      required this.itemCount,
      required this.title,
      required this.cover,
      required this.description,
      required this.visible,
      required this.local});
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['source_name'] = Variable<String>(sourceName);
    map['id'] = Variable<String>(id);
    {
      map['extras'] = Variable<String>(
          $DatabaseMediaCollectionsTable.$converterextras.toSql(extras));
    }
    {
      map['owner'] = Variable<String>(
          $DatabaseMediaCollectionsTable.$converterowner.toSql(owner));
    }
    map['create_time'] = Variable<DateTime>(createTime);
    map['item_count'] = Variable<int>(itemCount);
    map['title'] = Variable<String>(title);
    map['cover'] = Variable<String>(cover);
    map['description'] = Variable<String>(description);
    map['visible'] = Variable<bool>(visible);
    map['local'] = Variable<bool>(local);
    return map;
  }

  DatabaseMediaCollectionsCompanion toCompanion(bool nullToAbsent) {
    return DatabaseMediaCollectionsCompanion(
      sourceName: Value(sourceName),
      id: Value(id),
      extras: Value(extras),
      owner: Value(owner),
      createTime: Value(createTime),
      itemCount: Value(itemCount),
      title: Value(title),
      cover: Value(cover),
      description: Value(description),
      visible: Value(visible),
      local: Value(local),
    );
  }

  factory DatabaseMediaCollection.fromJson(Map<String, dynamic> json,
      {ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return DatabaseMediaCollection(
      sourceName: serializer.fromJson<String>(json['sourceName']),
      id: serializer.fromJson<String>(json['id']),
      extras: serializer.fromJson<Map<String, dynamic>>(json['extras']),
      owner: serializer.fromJson<UserInfo>(json['owner']),
      createTime: serializer.fromJson<DateTime>(json['createTime']),
      itemCount: serializer.fromJson<int>(json['itemCount']),
      title: serializer.fromJson<String>(json['title']),
      cover: serializer.fromJson<String>(json['cover']),
      description: serializer.fromJson<String>(json['description']),
      visible: serializer.fromJson<bool>(json['visible']),
      local: serializer.fromJson<bool>(json['local']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'sourceName': serializer.toJson<String>(sourceName),
      'id': serializer.toJson<String>(id),
      'extras': serializer.toJson<Map<String, dynamic>>(extras),
      'owner': serializer.toJson<UserInfo>(owner),
      'createTime': serializer.toJson<DateTime>(createTime),
      'itemCount': serializer.toJson<int>(itemCount),
      'title': serializer.toJson<String>(title),
      'cover': serializer.toJson<String>(cover),
      'description': serializer.toJson<String>(description),
      'visible': serializer.toJson<bool>(visible),
      'local': serializer.toJson<bool>(local),
    };
  }

  DatabaseMediaCollection copyWith(
          {String? sourceName,
          String? id,
          Map<String, dynamic>? extras,
          UserInfo? owner,
          DateTime? createTime,
          int? itemCount,
          String? title,
          String? cover,
          String? description,
          bool? visible,
          bool? local}) =>
      DatabaseMediaCollection(
        sourceName: sourceName ?? this.sourceName,
        id: id ?? this.id,
        extras: extras ?? this.extras,
        owner: owner ?? this.owner,
        createTime: createTime ?? this.createTime,
        itemCount: itemCount ?? this.itemCount,
        title: title ?? this.title,
        cover: cover ?? this.cover,
        description: description ?? this.description,
        visible: visible ?? this.visible,
        local: local ?? this.local,
      );
  DatabaseMediaCollection copyWithCompanion(
      DatabaseMediaCollectionsCompanion data) {
    return DatabaseMediaCollection(
      sourceName:
          data.sourceName.present ? data.sourceName.value : this.sourceName,
      id: data.id.present ? data.id.value : this.id,
      extras: data.extras.present ? data.extras.value : this.extras,
      owner: data.owner.present ? data.owner.value : this.owner,
      createTime:
          data.createTime.present ? data.createTime.value : this.createTime,
      itemCount: data.itemCount.present ? data.itemCount.value : this.itemCount,
      title: data.title.present ? data.title.value : this.title,
      cover: data.cover.present ? data.cover.value : this.cover,
      description:
          data.description.present ? data.description.value : this.description,
      visible: data.visible.present ? data.visible.value : this.visible,
      local: data.local.present ? data.local.value : this.local,
    );
  }

  @override
  String toString() {
    return (StringBuffer('DatabaseMediaCollection(')
          ..write('sourceName: $sourceName, ')
          ..write('id: $id, ')
          ..write('extras: $extras, ')
          ..write('owner: $owner, ')
          ..write('createTime: $createTime, ')
          ..write('itemCount: $itemCount, ')
          ..write('title: $title, ')
          ..write('cover: $cover, ')
          ..write('description: $description, ')
          ..write('visible: $visible, ')
          ..write('local: $local')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(sourceName, id, extras, owner, createTime,
      itemCount, title, cover, description, visible, local);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is DatabaseMediaCollection &&
          other.sourceName == this.sourceName &&
          other.id == this.id &&
          other.extras == this.extras &&
          other.owner == this.owner &&
          other.createTime == this.createTime &&
          other.itemCount == this.itemCount &&
          other.title == this.title &&
          other.cover == this.cover &&
          other.description == this.description &&
          other.visible == this.visible &&
          other.local == this.local);
}

class DatabaseMediaCollectionsCompanion
    extends UpdateCompanion<DatabaseMediaCollection> {
  final Value<String> sourceName;
  final Value<String> id;
  final Value<Map<String, dynamic>> extras;
  final Value<UserInfo> owner;
  final Value<DateTime> createTime;
  final Value<int> itemCount;
  final Value<String> title;
  final Value<String> cover;
  final Value<String> description;
  final Value<bool> visible;
  final Value<bool> local;
  final Value<int> rowid;
  const DatabaseMediaCollectionsCompanion({
    this.sourceName = const Value.absent(),
    this.id = const Value.absent(),
    this.extras = const Value.absent(),
    this.owner = const Value.absent(),
    this.createTime = const Value.absent(),
    this.itemCount = const Value.absent(),
    this.title = const Value.absent(),
    this.cover = const Value.absent(),
    this.description = const Value.absent(),
    this.visible = const Value.absent(),
    this.local = const Value.absent(),
    this.rowid = const Value.absent(),
  });
  DatabaseMediaCollectionsCompanion.insert({
    required String sourceName,
    required String id,
    required Map<String, dynamic> extras,
    required UserInfo owner,
    required DateTime createTime,
    required int itemCount,
    required String title,
    required String cover,
    required String description,
    required bool visible,
    required bool local,
    this.rowid = const Value.absent(),
  })  : sourceName = Value(sourceName),
        id = Value(id),
        extras = Value(extras),
        owner = Value(owner),
        createTime = Value(createTime),
        itemCount = Value(itemCount),
        title = Value(title),
        cover = Value(cover),
        description = Value(description),
        visible = Value(visible),
        local = Value(local);
  static Insertable<DatabaseMediaCollection> custom({
    Expression<String>? sourceName,
    Expression<String>? id,
    Expression<String>? extras,
    Expression<String>? owner,
    Expression<DateTime>? createTime,
    Expression<int>? itemCount,
    Expression<String>? title,
    Expression<String>? cover,
    Expression<String>? description,
    Expression<bool>? visible,
    Expression<bool>? local,
    Expression<int>? rowid,
  }) {
    return RawValuesInsertable({
      if (sourceName != null) 'source_name': sourceName,
      if (id != null) 'id': id,
      if (extras != null) 'extras': extras,
      if (owner != null) 'owner': owner,
      if (createTime != null) 'create_time': createTime,
      if (itemCount != null) 'item_count': itemCount,
      if (title != null) 'title': title,
      if (cover != null) 'cover': cover,
      if (description != null) 'description': description,
      if (visible != null) 'visible': visible,
      if (local != null) 'local': local,
      if (rowid != null) 'rowid': rowid,
    });
  }

  DatabaseMediaCollectionsCompanion copyWith(
      {Value<String>? sourceName,
      Value<String>? id,
      Value<Map<String, dynamic>>? extras,
      Value<UserInfo>? owner,
      Value<DateTime>? createTime,
      Value<int>? itemCount,
      Value<String>? title,
      Value<String>? cover,
      Value<String>? description,
      Value<bool>? visible,
      Value<bool>? local,
      Value<int>? rowid}) {
    return DatabaseMediaCollectionsCompanion(
      sourceName: sourceName ?? this.sourceName,
      id: id ?? this.id,
      extras: extras ?? this.extras,
      owner: owner ?? this.owner,
      createTime: createTime ?? this.createTime,
      itemCount: itemCount ?? this.itemCount,
      title: title ?? this.title,
      cover: cover ?? this.cover,
      description: description ?? this.description,
      visible: visible ?? this.visible,
      local: local ?? this.local,
      rowid: rowid ?? this.rowid,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (sourceName.present) {
      map['source_name'] = Variable<String>(sourceName.value);
    }
    if (id.present) {
      map['id'] = Variable<String>(id.value);
    }
    if (extras.present) {
      map['extras'] = Variable<String>(
          $DatabaseMediaCollectionsTable.$converterextras.toSql(extras.value));
    }
    if (owner.present) {
      map['owner'] = Variable<String>(
          $DatabaseMediaCollectionsTable.$converterowner.toSql(owner.value));
    }
    if (createTime.present) {
      map['create_time'] = Variable<DateTime>(createTime.value);
    }
    if (itemCount.present) {
      map['item_count'] = Variable<int>(itemCount.value);
    }
    if (title.present) {
      map['title'] = Variable<String>(title.value);
    }
    if (cover.present) {
      map['cover'] = Variable<String>(cover.value);
    }
    if (description.present) {
      map['description'] = Variable<String>(description.value);
    }
    if (visible.present) {
      map['visible'] = Variable<bool>(visible.value);
    }
    if (local.present) {
      map['local'] = Variable<bool>(local.value);
    }
    if (rowid.present) {
      map['rowid'] = Variable<int>(rowid.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('DatabaseMediaCollectionsCompanion(')
          ..write('sourceName: $sourceName, ')
          ..write('id: $id, ')
          ..write('extras: $extras, ')
          ..write('owner: $owner, ')
          ..write('createTime: $createTime, ')
          ..write('itemCount: $itemCount, ')
          ..write('title: $title, ')
          ..write('cover: $cover, ')
          ..write('description: $description, ')
          ..write('visible: $visible, ')
          ..write('local: $local, ')
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
  late final $DatabaseMediaCollectionsTable databaseMediaCollections =
      $DatabaseMediaCollectionsTable(this);
  @override
  Iterable<TableInfo<Table, Object?>> get allTables =>
      allSchemaEntities.whereType<TableInfo<Table, Object?>>();
  @override
  List<DatabaseSchemaEntity> get allSchemaEntities => [
        databaseSearchHistories,
        databaseMediaPageHistories,
        databaseMediaCollections
      ];
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
typedef $$DatabaseMediaCollectionsTableCreateCompanionBuilder
    = DatabaseMediaCollectionsCompanion Function({
  required String sourceName,
  required String id,
  required Map<String, dynamic> extras,
  required UserInfo owner,
  required DateTime createTime,
  required int itemCount,
  required String title,
  required String cover,
  required String description,
  required bool visible,
  required bool local,
  Value<int> rowid,
});
typedef $$DatabaseMediaCollectionsTableUpdateCompanionBuilder
    = DatabaseMediaCollectionsCompanion Function({
  Value<String> sourceName,
  Value<String> id,
  Value<Map<String, dynamic>> extras,
  Value<UserInfo> owner,
  Value<DateTime> createTime,
  Value<int> itemCount,
  Value<String> title,
  Value<String> cover,
  Value<String> description,
  Value<bool> visible,
  Value<bool> local,
  Value<int> rowid,
});

class $$DatabaseMediaCollectionsTableFilterComposer
    extends Composer<_$BipDatabase, $DatabaseMediaCollectionsTable> {
  $$DatabaseMediaCollectionsTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<String> get sourceName => $composableBuilder(
      column: $table.sourceName, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get id => $composableBuilder(
      column: $table.id, builder: (column) => ColumnFilters(column));

  ColumnWithTypeConverterFilters<Map<String, dynamic>, Map<String, dynamic>,
          String>
      get extras => $composableBuilder(
          column: $table.extras,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnWithTypeConverterFilters<UserInfo, UserInfo, String> get owner =>
      $composableBuilder(
          column: $table.owner,
          builder: (column) => ColumnWithTypeConverterFilters(column));

  ColumnFilters<DateTime> get createTime => $composableBuilder(
      column: $table.createTime, builder: (column) => ColumnFilters(column));

  ColumnFilters<int> get itemCount => $composableBuilder(
      column: $table.itemCount, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get title => $composableBuilder(
      column: $table.title, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get cover => $composableBuilder(
      column: $table.cover, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get description => $composableBuilder(
      column: $table.description, builder: (column) => ColumnFilters(column));

  ColumnFilters<bool> get visible => $composableBuilder(
      column: $table.visible, builder: (column) => ColumnFilters(column));

  ColumnFilters<bool> get local => $composableBuilder(
      column: $table.local, builder: (column) => ColumnFilters(column));
}

class $$DatabaseMediaCollectionsTableOrderingComposer
    extends Composer<_$BipDatabase, $DatabaseMediaCollectionsTable> {
  $$DatabaseMediaCollectionsTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<String> get sourceName => $composableBuilder(
      column: $table.sourceName, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get id => $composableBuilder(
      column: $table.id, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get extras => $composableBuilder(
      column: $table.extras, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get owner => $composableBuilder(
      column: $table.owner, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<DateTime> get createTime => $composableBuilder(
      column: $table.createTime, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<int> get itemCount => $composableBuilder(
      column: $table.itemCount, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get title => $composableBuilder(
      column: $table.title, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get cover => $composableBuilder(
      column: $table.cover, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get description => $composableBuilder(
      column: $table.description, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<bool> get visible => $composableBuilder(
      column: $table.visible, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<bool> get local => $composableBuilder(
      column: $table.local, builder: (column) => ColumnOrderings(column));
}

class $$DatabaseMediaCollectionsTableAnnotationComposer
    extends Composer<_$BipDatabase, $DatabaseMediaCollectionsTable> {
  $$DatabaseMediaCollectionsTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumn<String> get sourceName => $composableBuilder(
      column: $table.sourceName, builder: (column) => column);

  GeneratedColumn<String> get id =>
      $composableBuilder(column: $table.id, builder: (column) => column);

  GeneratedColumnWithTypeConverter<Map<String, dynamic>, String> get extras =>
      $composableBuilder(column: $table.extras, builder: (column) => column);

  GeneratedColumnWithTypeConverter<UserInfo, String> get owner =>
      $composableBuilder(column: $table.owner, builder: (column) => column);

  GeneratedColumn<DateTime> get createTime => $composableBuilder(
      column: $table.createTime, builder: (column) => column);

  GeneratedColumn<int> get itemCount =>
      $composableBuilder(column: $table.itemCount, builder: (column) => column);

  GeneratedColumn<String> get title =>
      $composableBuilder(column: $table.title, builder: (column) => column);

  GeneratedColumn<String> get cover =>
      $composableBuilder(column: $table.cover, builder: (column) => column);

  GeneratedColumn<String> get description => $composableBuilder(
      column: $table.description, builder: (column) => column);

  GeneratedColumn<bool> get visible =>
      $composableBuilder(column: $table.visible, builder: (column) => column);

  GeneratedColumn<bool> get local =>
      $composableBuilder(column: $table.local, builder: (column) => column);
}

class $$DatabaseMediaCollectionsTableTableManager extends RootTableManager<
    _$BipDatabase,
    $DatabaseMediaCollectionsTable,
    DatabaseMediaCollection,
    $$DatabaseMediaCollectionsTableFilterComposer,
    $$DatabaseMediaCollectionsTableOrderingComposer,
    $$DatabaseMediaCollectionsTableAnnotationComposer,
    $$DatabaseMediaCollectionsTableCreateCompanionBuilder,
    $$DatabaseMediaCollectionsTableUpdateCompanionBuilder,
    (
      DatabaseMediaCollection,
      BaseReferences<_$BipDatabase, $DatabaseMediaCollectionsTable,
          DatabaseMediaCollection>
    ),
    DatabaseMediaCollection,
    PrefetchHooks Function()> {
  $$DatabaseMediaCollectionsTableTableManager(
      _$BipDatabase db, $DatabaseMediaCollectionsTable table)
      : super(TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$DatabaseMediaCollectionsTableFilterComposer(
                  $db: db, $table: table),
          createOrderingComposer: () =>
              $$DatabaseMediaCollectionsTableOrderingComposer(
                  $db: db, $table: table),
          createComputedFieldComposer: () =>
              $$DatabaseMediaCollectionsTableAnnotationComposer(
                  $db: db, $table: table),
          updateCompanionCallback: ({
            Value<String> sourceName = const Value.absent(),
            Value<String> id = const Value.absent(),
            Value<Map<String, dynamic>> extras = const Value.absent(),
            Value<UserInfo> owner = const Value.absent(),
            Value<DateTime> createTime = const Value.absent(),
            Value<int> itemCount = const Value.absent(),
            Value<String> title = const Value.absent(),
            Value<String> cover = const Value.absent(),
            Value<String> description = const Value.absent(),
            Value<bool> visible = const Value.absent(),
            Value<bool> local = const Value.absent(),
            Value<int> rowid = const Value.absent(),
          }) =>
              DatabaseMediaCollectionsCompanion(
            sourceName: sourceName,
            id: id,
            extras: extras,
            owner: owner,
            createTime: createTime,
            itemCount: itemCount,
            title: title,
            cover: cover,
            description: description,
            visible: visible,
            local: local,
            rowid: rowid,
          ),
          createCompanionCallback: ({
            required String sourceName,
            required String id,
            required Map<String, dynamic> extras,
            required UserInfo owner,
            required DateTime createTime,
            required int itemCount,
            required String title,
            required String cover,
            required String description,
            required bool visible,
            required bool local,
            Value<int> rowid = const Value.absent(),
          }) =>
              DatabaseMediaCollectionsCompanion.insert(
            sourceName: sourceName,
            id: id,
            extras: extras,
            owner: owner,
            createTime: createTime,
            itemCount: itemCount,
            title: title,
            cover: cover,
            description: description,
            visible: visible,
            local: local,
            rowid: rowid,
          ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ));
}

typedef $$DatabaseMediaCollectionsTableProcessedTableManager
    = ProcessedTableManager<
        _$BipDatabase,
        $DatabaseMediaCollectionsTable,
        DatabaseMediaCollection,
        $$DatabaseMediaCollectionsTableFilterComposer,
        $$DatabaseMediaCollectionsTableOrderingComposer,
        $$DatabaseMediaCollectionsTableAnnotationComposer,
        $$DatabaseMediaCollectionsTableCreateCompanionBuilder,
        $$DatabaseMediaCollectionsTableUpdateCompanionBuilder,
        (
          DatabaseMediaCollection,
          BaseReferences<_$BipDatabase, $DatabaseMediaCollectionsTable,
              DatabaseMediaCollection>
        ),
        DatabaseMediaCollection,
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
  $$DatabaseMediaCollectionsTableTableManager get databaseMediaCollections =>
      $$DatabaseMediaCollectionsTableTableManager(
          _db, _db.databaseMediaCollections);
}
