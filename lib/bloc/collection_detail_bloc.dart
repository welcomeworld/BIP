import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';
import 'package:rxdart/subjects.dart';

import 'bloc.dart';

class CollectionDetailBloc extends Bloc {
  CollectionDetailBloc({CollectionManager? collectionManager})
      : _collectionManager = collectionManager ?? getIt<CollectionManager>();

  final CollectionManager _collectionManager;

  final ScrollController scrollController = ScrollController();
  final List<MediaPagePreview> _listData = [];
  final List<MediaPagePreview> _cacheList = [];
  BehaviorSubject<MediaCollection> collectionSubject = BehaviorSubject();
  BehaviorSubject<List<MediaPagePreview>> listSubject = BehaviorSubject();
  BehaviorSubject<bool> listEnd = BehaviorSubject();
  TextEditingController searchTextController = TextEditingController();
  BehaviorSubject<bool> searchOpen = BehaviorSubject.seeded(false);
  bool _isRefreshing = false;
  bool _isLoading = false;
  int _pageNumber = 1;
  int _cachePage = 1;
  double _cacheOffset = 0;
  String _searchKey = "";

  Future<void> setCollection(MediaCollection collection) async {
    collectionSubject.add(collection);
    refresh();
  }

  @override
  void dispose() {
    super.dispose();
    scrollController.dispose();
    listSubject.close();
    listEnd.close();
    searchOpen.close();
  }

  void checkLoadMore() {
    if (!scrollController.hasClients) {
      return;
    }
    final position = scrollController.position;
    if (position.pixels >= position.maxScrollExtent - 256) {
      loadMore();
    }
  }

  void hideBottom(double bottomHigh) async {
    if (!scrollController.hasClients) {
      return;
    }
    final position = scrollController.position;
    if (position.pixels >= position.maxScrollExtent - bottomHigh) {
      // 向上滚动刚好隐藏进度条
      scrollController.animateTo(
        position.maxScrollExtent - bottomHigh,
        duration: const Duration(milliseconds: 250),
        curve: Curves.easeIn,
      );
    }
  }

  void loadMore() async {
    if (_isLoading) return;
    if (listEnd.valueOrNull == true) return;
    _isLoading = true;
    await requestCollectionDetail();
    _isLoading = false;
  }

  Future<void> refresh() async {
    if (_isRefreshing) return;
    _isRefreshing = true;
    _pageNumber = 1;
    listEnd.add(false);
    await requestCollectionDetail();
    _isRefreshing = false;
  }

  Future<void> requestCollectionDetail() async {
    if (!collectionSubject.hasValue) {
      return;
    }
    final collection = collectionSubject.value;
    final result = await _collectionManager.requestMediaCollectionDetail(
        collection,
        key: _searchKey,
        pageNumber: _pageNumber++);
    if (result.isEmpty) {
      listEnd.add(true);
    } else {
      if (_pageNumber == 2) {
        _listData.clear();
      }
      _listData.addAll(result);
      listSubject.add(_listData);
    }
  }

  Future<void> handleSearchClick() async {
    if (searchOpen.value) {
      searchTextController.clear();
      return;
    }
    searchOpen.add(true);
    _cacheList.clear();
    _cacheList.addAll(_listData);
    _listData.clear();
    listSubject.add(_listData);
    _cachePage = _pageNumber;
    _cacheOffset = scrollController.offset;
    listEnd.add(true);
    scrollController.jumpTo(0);
  }

  Future<void> closeSearch() async {
    _searchKey = "";
    searchOpen.add(false);
    searchTextController.clear();
    _listData.clear();
    _listData.addAll(_cacheList);
    _cacheList.clear();
    _pageNumber = _cachePage;
    listEnd.add(false);
    listSubject.add(_listData);
    scrollController.jumpTo(_cacheOffset);
  }

  Future<void> onSearch(String key) async {
    _searchKey = key;
    _pageNumber = 1;
    listEnd.add(false);
    await requestCollectionDetail();
  }
}
