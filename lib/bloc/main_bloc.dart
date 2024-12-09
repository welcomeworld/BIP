import 'dart:async';

import 'package:bip/bloc/bloc.dart';
import 'package:bip/data/media_manager.dart';
import 'package:flutter/material.dart';

import '../data/model/media_page_preview.dart';

class MainBloc extends Bloc {
  Stream<List<MediaPagePreview>> get homeExploreList =>
      MediaManager().homeExploreList;
  int tabIndex = 0;
  bool _isRefreshing = false;
  bool _isLoading = false;

  @override
  void dispose() {}

  @override
  void initState(BuildContext context) {}

  void explore() async {
    if (_isLoading) return;
    _isLoading = true;
    await MediaManager().explore();
    _isLoading = false;
  }

  Future<void> refresh() async {
    if (_isRefreshing) return;
    _isRefreshing = true;
    await MediaManager().refreshExplore();
    _isRefreshing = false;
  }
}
