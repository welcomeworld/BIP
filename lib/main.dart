import 'package:bip/data/media_manager.dart';
import 'package:bip/data/persistence/kv_store.dart';
import 'package:bip/gen_auto_import.dart';
import 'package:bip/ui/theme/theme_notifier.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/constant.dart';
import 'package:bip/utils/proxy_server.dart';
import 'package:dynamic_color/dynamic_color.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:media_kit/media_kit.dart';
import 'package:path_provider/path_provider.dart';

final defaultIsolateConfig = <String, dynamic>{};

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  MediaKit.ensureInitialized();
  await _initMainIsolate();
  await initThemeNotifier();
  runApp(const MyApp());
  SystemChrome.setEnabledSystemUIMode(SystemUiMode.edgeToEdge);
  SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.light.copyWith(
    systemNavigationBarColor: Colors.transparent,
    statusBarColor: Colors.transparent,
    systemStatusBarContrastEnforced: false,
    statusBarIconBrightness: Brightness.dark,
  ));
}

Future<void> initChildIsolate(Map<String, dynamic> config) async {
  BackgroundIsolateBinaryMessenger.ensureInitialized(config["rootToken"]);
  Constant.cookiePath = (await getApplicationCacheDirectory()).path;
  await KvStore.init();
}

Future<void> _initMainIsolate() async {
  defaultIsolateConfig["rootToken"] = ServicesBinding.rootIsolateToken;
  Constant.cookiePath = (await getApplicationCacheDirectory()).path;
  await KvStore.init();
  MediaManager().refreshExplore();
  startProxyIso();
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ValueListenableBuilder<Color>(
      valueListenable: themeNotifier,
      builder: (context, themeColor, _) {
        return DynamicColorBuilder(
          builder: (ColorScheme? lightDynamic, ColorScheme? darkDynamic) {
            themeNotifier.setDynamicColor(lightDynamic?.primary);
            final useDynamic = themeNotifier.isDynamicTheme;
            ColorScheme lightColorScheme = (useDynamic && lightDynamic != null)
                ? lightDynamic
                : ColorScheme.fromSeed(
                    brightness: Brightness.light,
                    seedColor: themeColor,
                    primary: themeColor,
                  ).harmonized();
            ColorScheme darkColorScheme = (useDynamic && darkDynamic != null)
                ? darkDynamic
                : ColorScheme.fromSeed(
                    brightness: Brightness.dark,
                    seedColor: themeColor,
                    primary: themeColor,
                  ).harmonized();
            return MaterialApp.router(
              title: 'BIP',
              debugShowCheckedModeBanner: false,
              theme: ThemeData(
                colorScheme: lightColorScheme,
                useMaterial3: true,
              ),
              darkTheme: ThemeData(
                colorScheme: darkColorScheme,
                useMaterial3: true,
              ),
              localizationsDelegates: AppLocale.localizationsDelegates,
              supportedLocales: AppLocale.supportedLocales,
              routerDelegate: BipRouter.rootRouter,
            );
          },
        );
      },
    );
  }
}
