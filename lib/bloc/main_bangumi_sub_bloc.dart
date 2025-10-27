import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/model/index_configuration.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';

import 'bloc.dart';

class MainBangumiSubBloc extends Bloc {
  MainBangumiSubBloc({MediaManager? mediaManager}) {
    _mediaManager = mediaManager ?? getIt<MediaManager>();
  }

  late final MediaManager _mediaManager;

  final ScrollController scrollController = ScrollController();

  BehaviorSubject<IndexConfiguration> configurationSubject = BehaviorSubject();
  BehaviorSubject<List<MediaPagePreview>> listSubject = BehaviorSubject();
  BehaviorSubject<bool> listEnd = BehaviorSubject.seeded(false);

  bool _isRefreshing = false;
  bool _isLoading = false;
  int _pageNumber = 1;

  @override
  void initState(BuildContext context) {
    super.initState(context);
    _loadConfiguration();
    scrollController.addListener(() {
      if (scrollController.position.pixels >=
          scrollController.position.maxScrollExtent - 256) {
        loadMore();
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    scrollController.dispose();
    configurationSubject.close();
    listSubject.close();
    listEnd.close();
  }

  Future<void> _loadConfiguration() async {
    final config = await _mediaManager.requestBangumiIndexConfiguration();
    configurationSubject.add(config);
    refresh();
  }

  Future<void> onSelect(String paramKey, String paramValue) async {
    final current = configurationSubject.valueOrNull;
    if (current == null) return;
    configurationSubject.add(current.selectValue(paramKey, paramValue));
    await refresh();
  }

  Future<void> refresh() async {
    if (_isRefreshing) return;
    _isRefreshing = true;
    _pageNumber = 1;
    listEnd.add(false);
    await _requestList();
    _isRefreshing = false;
  }

  Future<void> loadMore() async {
    if (_isLoading) return;
    if (listEnd.valueOrNull == true) return;
    _isLoading = true;
    await _requestList();
    _isLoading = false;
  }

  Future<void> _requestList() async {
    final config = configurationSubject.valueOrNull;
    if (config == null) return;
    final result =
        await _mediaManager.requestBangumiIndex(config, _pageNumber++);
    final list = result.result;
    if (_pageNumber == 2) {
      listSubject.add([]);
    }
    if (list.isEmpty) {
      listEnd.add(true);
      return;
    }
    final pre = listSubject.valueOrNull ?? [];
    listSubject.add(pre..addAll(list));
  }
}
