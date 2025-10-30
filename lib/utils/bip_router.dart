import 'package:bip/ui/page/about_page.dart';
import 'package:bip/ui/page/collections_page.dart';
import 'package:bip/ui/page/history_page.dart';
import 'package:bip/ui/page/login_page.dart';
import 'package:bip/ui/page/main_page.dart';
import 'package:bip/ui/page/media_page_detail_page.dart';
import 'package:bip/ui/page/search_page.dart';
import 'package:bip/ui/page/settings_page.dart';
import 'package:bip/ui/page/user_detail_page.dart';
import 'package:bip/utils/page_info.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import '../ui/page/collection_detail_page.dart';
import '../ui/page/theme_settings_page.dart';

class BipRouter extends RouterDelegate<String>
    with PopNavigatorRouterDelegateMixin, ChangeNotifier {
  static final rootRouter = BipRouter();
  final _stack = <PageInfo>[PageInfo("/")];

  List<PageInfo> get stack => List.unmodifiable(_stack);
  @override
  GlobalKey<NavigatorState> navigatorKey = GlobalKey();

  static BipRouter of(BuildContext context) {
    return Router.of(context).routerDelegate as BipRouter;
  }

  @override
  Future<void> setNewRoutePath(String configuration) {
    for (var page in _stack) {
      page.didComplete(null);
    }
    _stack
      ..clear()
      ..add(PageInfo(configuration));
    return SynchronousFuture<void>(null);
  }

  @override
  Widget build(BuildContext context) {
    return Navigator(
      onDidRemovePage: _onDidRemovePage,
      key: navigatorKey,
      pages: [for (var page in _stack) createPage(page)],
    );
  }

  PageInfo? getTopPageInfo() {
    if (_stack.isEmpty) {
      return null;
    }
    return _stack.last;
  }

  void _onDidRemovePage(Page page) {
    if (_stack.isNotEmpty) {
      var top = getTopPageInfo();
      if (top?.pageName == page.name) {
        _stack.removeLast();
        notifyListeners();
      }
    }
  }

  bool pop<T extends Object?>([T? result]) {
    if (_stack.isNotEmpty) {
      _stack.removeLast().didComplete(result);
      notifyListeners();
      return true;
    }
    return false;
  }

  Future<bool> maybePop<T extends Object?>([T? result]) async {
    return await navigatorKey.currentState?.maybePop(result) ?? false;
  }

  Page createPage(PageInfo page) {
    final pageChild = switch (page.pageName) {
      PageNames.home => const MainPage(),
      PageNames.search => const SearchPage(),
      PageNames.mediaPageDetail => MediaPageDetailPage(page.extras["data"]!),
      PageNames.login => LoginPage(page.extras["data"]!),
      PageNames.history => const HistoryPage(),
      PageNames.collections => const CollectionsPage(),
      PageNames.collectionDetail => CollectionDetailPage(
          page.extras["data"],
        ),
      PageNames.themes => const ThemeSettingsPage(),
      PageNames.settings => const SettingsPage(),
      PageNames.about => const AboutPage(),
      PageNames.userDetail => UserDetailPage(
          page.extras["data"],
        ),
      String() => const SizedBox.shrink(),
    };
    return MaterialPage(
      name: page.pageName,
      onPopInvoked: (didPop, result) {
        if (didPop) {
          page.didComplete(result);
        }
      },
      child: pageChild,
    );
  }

  Future<dynamic> push(String newRoute) {
    return pushPageInfo(PageInfo(newRoute));
  }

  Future<dynamic> pushPageInfo(PageInfo pageInfo) {
    _stack.add(pageInfo);
    notifyListeners();
    return pageInfo.popped;
  }
}
