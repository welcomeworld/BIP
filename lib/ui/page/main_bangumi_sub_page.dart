import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/main_bangumi_sub_bloc.dart';

class MainBangumiSubPage extends StatefulWidget {
  const MainBangumiSubPage({super.key});

  @override
  State<MainBangumiSubPage> createState() => _MainBangumiSubPageState();
}

class _MainBangumiSubPageState
    extends BlocState<MainBangumiSubPage, MainBangumiSubBloc> {
  _MainBangumiSubPageState() : super(MainBangumiSubBloc());

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text("施工中"),
    );
  }
}
