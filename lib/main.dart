import 'package:bip/data/media_manager.dart';
import 'package:bip/data/persistence/kv_store.dart';
import 'package:bip/gen_auto_import.dart';
import 'package:bip/ui/theme/theme_colors.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/constant.dart';
import 'package:dynamic_color/dynamic_color.dart';
import 'package:flutter/material.dart';
import 'package:media_kit/media_kit.dart';
import 'package:path_provider/path_provider.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  MediaKit.ensureInitialized();
  await preInitApp();
  runApp(const MyApp());
}

Future<void> preInitApp() async {
  Constant.cookiePath = (await getApplicationCacheDirectory()).path;
  await KvStore.init();
  MediaManager().refreshExplore();
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return DynamicColorBuilder(
        builder: (ColorScheme? lightDynamic, ColorScheme? darkDynamic) {
      ColorScheme lightColorScheme;
      ColorScheme darkColorScheme;
      if (lightDynamic != null && darkDynamic != null) {
        lightColorScheme = lightDynamic.harmonized();

        // Repeat for the dark color scheme.
        darkColorScheme = darkDynamic.harmonized();
      } else {
        // Otherwise, use fallback schemes.
        lightColorScheme = ColorScheme.fromSeed(
          seedColor: ThemeColors.brandColor,
        );
        darkColorScheme = ColorScheme.fromSeed(
          seedColor: ThemeColors.brandColor,
          brightness: Brightness.dark,
        );
      }
      return MaterialApp.router(
        title: 'BIP',
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
          colorScheme: lightColorScheme,
        ),
        darkTheme: ThemeData(
          colorScheme: darkColorScheme,
        ),
        localizationsDelegates: AppLocale.localizationsDelegates,
        supportedLocales: AppLocale.supportedLocales,
        routerDelegate: BipRouter(),
      );
    });
  }
}
