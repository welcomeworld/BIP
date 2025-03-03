import 'package:flutter/material.dart';

import '../data/media_manager.dart';
import '../data/model/media_page_preview.dart';
import 'bloc.dart';

class MainHomeSubBloc extends Bloc {
  MainHomeSubBloc({MediaManager? mediaManager}) {
    _mediaManager = mediaManager ?? MediaManager();
  }

  late final MediaManager _mediaManager;

  final ScrollController scrollController = ScrollController();

  Stream<List<MediaPagePreview>> get homeExploreList =>
      _mediaManager.homeExploreList;
  bool _isRefreshing = false;
  bool _isLoading = false;

  @override
  void initState(BuildContext context) {
    super.initState(context);
    scrollController.addListener(() {
      if (scrollController.position.pixels >=
          scrollController.position.maxScrollExtent - 256) {
        explore();
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    scrollController.dispose();
  }

  void explore() async {
    if (_isLoading) return;
    _isLoading = true;
    await _mediaManager.explore();
    _isLoading = false;
  }

  Future<void> refresh() async {
    if (_isRefreshing) return;
    _isRefreshing = true;
    await _mediaManager.refreshExplore();
    _isRefreshing = false;
  }
}
