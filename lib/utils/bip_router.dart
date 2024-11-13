import 'package:bip/ui/page/main_page.dart';
import 'package:bip/utils/page_info.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

class BipRouter extends RouterDelegate<String>
    with PopNavigatorRouterDelegateMixin, ChangeNotifier {
  final _stack = <PageInfo>[PageInfo("/")];

  List<PageInfo> get stack => List.unmodifiable(_stack);
  @override
  GlobalKey<NavigatorState> navigatorKey = GlobalKey();

  static BipRouter of(BuildContext context) {
    return Router.of(context).routerDelegate as BipRouter;
  }

  @override
  Future<void> setNewRoutePath(String configuration) {
    _stack
      ..clear()
      ..add(PageInfo(configuration));
    return SynchronousFuture<void>(null);
  }

  @override
  Widget build(BuildContext context) {
    return Navigator(
      onPopPage: _onPopPage,
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

  bool _onPopPage(Route<dynamic> route, dynamic result) {
    if (_stack.length > 1) {
      var top = getTopPageInfo();
      if (top?.pageName == route.settings.name) {
        _stack.removeLast();
        notifyListeners();
      }
    }
    return route.didPop(result);
  }

  bool pop() {
    if (_stack.length > 1) {
      _stack.removeLast();
      notifyListeners();
      return true;
    }
    return false;
  }

  Page createPage(PageInfo page) {
    switch (page.pageName) {
      case PageNames.home:
        return const MaterialPage(
          name: PageNames.home,
          child: MainPage(),
        );
    }
    return const MaterialPage(child: SizedBox.shrink());
  }

  void push(String newRoute) {
    pushPageInfo(PageInfo(newRoute));
  }

  void pushPageInfo(PageInfo pageInfo) {
    _stack.add(pageInfo);
    notifyListeners();
  }
}
