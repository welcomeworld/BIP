import 'package:bip/app_initialzer.dart';
import 'package:bip/di/get_it.dart';
import 'package:bip/main.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:media_kit/media_kit.dart';

class NavigationHelper {
  static const defaultSettleTimeoutDuration = Duration(seconds: 2);

  static Future<void> launchApp(WidgetTester tester) async {
    try {
      await getIt.reset();
    } catch (e) {
      debugPrint("GetIt reset failed: $e");
    }
    MediaKit.ensureInitialized();
    await AppInitializer.initialize();
    BipRouter.rootRouter.setNewRoutePath("/");
    await tester.pumpWidget(const MyApp());
    await safePump(tester);
  }

  static Future<void> navigateToHome(WidgetTester tester) async {
    await tester.tap(find.text('首页'));
    await safePump(tester);
  }

  static Future<void> navigateToBangumi(WidgetTester tester) async {
    await tester.tap(find.text('番剧'));
    await safePump(tester);
  }

  static Future<void> navigateToMine(WidgetTester tester) async {
    await tester.tap(find.text('我的'));
    await safePump(tester);
  }

  static Future<void> navigateToSearch(WidgetTester tester) async {
    await tester.tap(find.text('随便搜点什么吧'));
    await safePump(tester);
  }

  static Future<void> navigateToSettings(WidgetTester tester) async {
    await navigateToMine(tester);
    await tester.tap(find.text('设置'));
    await safePump(tester);
  }

  static Future<void> navigateToThemeSettings(WidgetTester tester) async {
    await navigateToMine(tester);
    await tester.tap(find.text('主题'));
    await safePump(tester);
  }

  static Future<void> navigateToHistory(WidgetTester tester) async {
    await navigateToMine(tester);
    await tester.tap(find.text('历史记录'));
    await safePump(tester);
  }

  static Future<void> navigateToCollections(WidgetTester tester) async {
    await navigateToMine(tester);
    await tester.tap(find.text('我的收藏'));
    await safePump(tester);
  }

  static Future<void> pageBack(WidgetTester tester,
      {bool forcePageBack = false}) async {
    return TestAsyncUtils.guard<void>(() async {
      if (!forcePageBack) {
        Finder backButton = find.byTooltip('Back');
        if (backButton.allCandidates.length == 1) {
          await tester.tap(backButton);
          return;
        }
      }
      BipRouter.rootRouter.maybePop();
    });
  }

  static Future<void> safePump(
    WidgetTester tester, {
    Duration duration = const Duration(milliseconds: 100),
    Duration timeout = defaultSettleTimeoutDuration,
  }) async {
    try {
      await tester.pumpAndSettle(
          duration, EnginePhase.sendSemanticsUpdate, timeout);
    } catch (timeout) {
      // ignore
    }
  }

  static Future<void> waitForWidget(
    WidgetTester tester,
    Finder finder, {
    Duration timeout = const Duration(seconds: 8),
    Duration interval = const Duration(milliseconds: 100),
  }) async {
    final endTime = tester.binding.clock.fromNowBy(timeout);
    while (tester.binding.clock.now().isBefore(endTime)) {
      await tester.pump(interval);
      if (finder.evaluate().isNotEmpty) {
        return;
      }
    }
  }
}
