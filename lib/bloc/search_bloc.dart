import 'package:bip/bloc/bloc.dart';
import 'package:bip/data/media_manager.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';

import '../data/model/search_history.dart';

class SearchBloc extends Bloc {
  TextEditingController searchTextController = TextEditingController();
  BehaviorSubject<List<String>> searchHotSubject = BehaviorSubject();
  BehaviorSubject<List<SearchHistory>> searchHistorySubject = BehaviorSubject();

  void onClearSearch() {
    searchTextController.clear();
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

  void onSearch(String searchKey) {
    searchHistorySubject.add([SearchHistory()..searchKey = searchKey]);
  }

  @override
  void initState(BuildContext context) {
    super.initState(context);
    _requestSearchHot();
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
    super.dispose();
  }
}
