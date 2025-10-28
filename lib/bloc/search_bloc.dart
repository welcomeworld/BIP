import 'package:bip/bloc/bloc.dart';
import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:bip/domain/model/search_history.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';

class SearchBloc extends Bloc {
  SearchBloc({Database? database, MediaManager? mediaManager}) {
    _database = database ?? getIt<Database>();
    _mediaManager = mediaManager ?? getIt<MediaManager>();
    _requestSearchHot();
    scrollController.addListener(() {
      if (scrollController.position.pixels >=
          scrollController.position.maxScrollExtent - 256) {
        onSearchMore();
      }
    });
  }

  late final Database _database;
  late final MediaManager _mediaManager;
  BehaviorSubject<bool> showResultSubject = BehaviorSubject();
  TextEditingController searchTextController = TextEditingController();
  final ScrollController scrollController = ScrollController();
  BehaviorSubject<List<String>> searchHotSubject = BehaviorSubject();

  Stream<List<SearchHistory>> get searchHistorySubject =>
      _database.searchHistoryStream;
  BehaviorSubject<List<MediaPagePreview>> searchResultSubject =
      BehaviorSubject();
  FocusNode searchFocusNode = FocusNode();
  bool _isRefreshing = false;
  bool _isLoading = false;
  String _searchKey = "";
  int _searchPage = 1;

  void onClearSearch() {
    searchTextController.clear();
    showResultSubject.add(false);
  }

  void onClearHistory() {
    _database.clearSearchHistories();
  }

  void onSearchHot(String searchKey) {
    searchTextController.text = searchKey;
    onSearch(searchKey);
  }

  void onSearchHistory(String searchKey) {
    searchTextController.text = searchKey;
    onSearch(searchKey);
  }

  Future<void> onSearch(String searchKey) async {
    searchFocusNode.unfocus();
    _searchPage = 1;
    _searchKey = searchKey;
    _database.saveSearchHistory(
      SearchHistory(searchKey: searchKey, searchTime: DateTime.now()),
    );
    showResultSubject.add(true);
    searchResultSubject.add([]);
    final resultStream = _mediaManager.requestSearch(searchKey, _searchPage++);
    await for (final result in resultStream) {
      if (searchKey == _searchKey) {
        final preResult = searchResultSubject.valueOrNull ?? [];
        searchResultSubject.add(preResult..addAll(result));
      }
    }
  }

  Future<void> onRefresh() async {
    if (_isRefreshing) return;
    _isRefreshing = true;
    await onSearch(_searchKey);
    _isRefreshing = false;
  }

  Future<void> onSearchMore() async {
    final searchKey = _searchKey;
    if (_isLoading) return;
    _isLoading = true;
    final resultStream = _mediaManager.requestSearch(searchKey, _searchPage++);
    await for (final result in resultStream) {
      if (searchKey == _searchKey) {
        final preResult = searchResultSubject.valueOrNull ?? [];
        searchResultSubject.add(preResult..addAll(result));
      }
    }
    _isLoading = false;
  }

  void _requestSearchHot() {
    _mediaManager.requestSearchHot().then((value) {
      searchHotSubject.add(value.result);
    });
  }

  @override
  void dispose() {
    searchHotSubject.close();
    scrollController.dispose();
    showResultSubject.close();
    searchResultSubject.close();
    searchFocusNode.dispose();
    super.dispose();
  }
}
