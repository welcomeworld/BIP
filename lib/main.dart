import 'package:bip/ui/theme/theme_colors.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:dynamic_color/dynamic_color.dart';
import 'package:flutter/material.dart';
import 'package:media_kit/media_kit.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  MediaKit.ensureInitialized();
  runApp(const MyApp());
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
        routerDelegate: BipRouter(),
      );
    });
  }
}
