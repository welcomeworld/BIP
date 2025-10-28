import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';
import 'package:rxdart/subjects.dart';

import 'bloc.dart';

class CollectionsBloc extends Bloc {
  CollectionsBloc({CollectionManager? collectionManager})
      : _collectionManager = collectionManager ?? getIt<CollectionManager>() {
    refresh();
  }

  final CollectionManager _collectionManager;

  final ScrollController scrollController = ScrollController();
  final List<MediaCollection> _listData = [];
  BehaviorSubject<List<MediaCollection>> listSubject = BehaviorSubject();
  BehaviorSubject<bool> listEnd = BehaviorSubject();
  bool _isRefreshing = false;

  @override
  void dispose() {
    super.dispose();
    scrollController.dispose();
    listSubject.close();
    listEnd.close();
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

  Future<void> refresh() async {
    if (_isRefreshing) return;
    _isRefreshing = true;
    listEnd.add(false);
    await requestCollections();
    _isRefreshing = false;
  }

  Future<void> requestCollections() async {
    final resultStream = _collectionManager.requestMediaCollections();
    _listData.clear();
    await for (final result in resultStream) {
      listSubject.add(_listData..addAll(result));
    }
    listEnd.add(true);
  }

  Future<void> addCollection(MediaCollection collection) async {
    _collectionManager.saveMediaCollection(collection);
  }

  Future<bool> deleteCollection(MediaCollection collection) async {
    final result = await _collectionManager.deleteMediaCollection(collection);
    if (result) {
      _listData.remove(collection);
      listSubject.add(_listData);
    }
    return result;
  }
}
