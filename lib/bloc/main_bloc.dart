import 'dart:async';

import 'package:bip/bloc/bloc.dart';
import 'package:bip/data/media_manager.dart';
import 'package:flutter/material.dart';

import '../data/model/media_page_preview.dart';

class MainBloc extends Bloc {
  Stream<List<MediaPagePreview>> get homeExploreList =>
      MediaManager().homeExploreList;
  int tabIndex = 0;

  @override
  void dispose() {}

  @override
  void initState(BuildContext context) {}

  void explore() {
    MediaManager().explore();
  }

  void refresh() {
    MediaManager().refreshExplore();
  }
}
