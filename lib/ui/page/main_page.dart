import 'package:bip/bloc/main_bloc.dart';
import 'package:bip/gen_auto_import.dart';
import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';

class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends BlocState<MainPage, MainBloc> {
  _MainPageState() : super(MainBloc());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(AppLocale.of(context)!.appName),
      ),
      body: const SizedBox.shrink(),
    );
  }
}
