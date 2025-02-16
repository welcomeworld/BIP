import 'package:bip/bloc/bloc.dart';
import 'package:bip/data/media_manager.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';

import '../data/model/media_page_preview.dart';
import '../data/model/search_history.dart';

class SearchBloc extends Bloc {
  BehaviorSubject<bool> showResultSubject = BehaviorSubject();
  TextEditingController searchTextController = TextEditingController();
  final ScrollController scrollController = ScrollController();
  BehaviorSubject<List<String>> searchHotSubject = BehaviorSubject();
  BehaviorSubject<List<SearchHistory>> searchHistorySubject = BehaviorSubject();
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
    searchHistorySubject.add([]);
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
    searchHistorySubject.add([SearchHistory()..searchKey = searchKey]);
    showResultSubject.add(true);
    searchResultSubject.add([]);
    final resultStream = MediaManager().requestSearch(searchKey, _searchPage++);
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
    final resultStream = MediaManager().requestSearch(searchKey, _searchPage++);
    await for (final result in resultStream) {
      if (searchKey == _searchKey) {
        final preResult = searchResultSubject.valueOrNull ?? [];
        searchResultSubject.add(preResult..addAll(result));
      }
    }
    _isLoading = false;
  }

  @override
  void initState(BuildContext context) {
    super.initState(context);
    _requestSearchHot();
    scrollController.addListener(() {
      if (scrollController.position.pixels >=
          scrollController.position.maxScrollExtent - 256) {
        onSearchMore();
      }
    });
  }

  void _requestSearchHot() {
    MediaManager().requestSearchHot().then((value) {
      searchHotSubject.add(value.result);
    });
  }

  @override
  void dispose() {
    searchHotSubject.close();
    searchHistorySubject.close();
    scrollController.dispose();
    showResultSubject.close();
    searchResultSubject.close();
    searchFocusNode.dispose();
    super.dispose();
  }
}
