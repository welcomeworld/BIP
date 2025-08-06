import 'package:flutter/material.dart';

class BipNavigator extends StatefulWidget {
  const BipNavigator({
    super.key,
    required this.child,
    this.canPop = true,
    this.onPopInvoked,
  });

  final Widget child;
  final PopInvokedCallback? onPopInvoked;
  final bool canPop;

  @override
  State<BipNavigator> createState() => _BipNavigatorState();
}

class _BipNavigatorState extends State<BipNavigator> {
  final GlobalKey<NavigatorState> _navigatorKey = GlobalKey<NavigatorState>();

  @override
  Widget build(BuildContext context) {
    return PopScope(
      canPop: widget.canPop && !(_navigatorKey.currentState?.canPop() ?? false),
      onPopInvoked: (didPop) async {
        if (didPop) {
          return;
        }
        final result = await _navigatorKey.currentState?.maybePop() ?? false;
        if (result) {
          return;
        }
        widget.onPopInvoked?.call(didPop);
      },
      child: Navigator(
        key: _navigatorKey,
        onGenerateRoute: (settings) {
          return MaterialPageRoute(
            builder: (context) => widget.child,
          );
        },
      ),
    );
  }
}
