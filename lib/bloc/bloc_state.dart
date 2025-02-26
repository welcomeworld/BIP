import 'package:bip/gen_auto_import.dart';
import 'package:flutter/material.dart';

import 'bloc.dart';

abstract class BlocState<T extends StatefulWidget, B extends Bloc>
    extends State<T> {
  BlocState(this.bloc);

  final B bloc;
  late ColorScheme colorScheme;
  late AppLocale localeString;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    colorScheme = Theme.of(context).colorScheme;
    localeString = AppLocale.of(context)!;
  }

  @override
  void initState() {
    super.initState();
    bloc.initState(context);
  }

  @override
  void dispose() {
    bloc.dispose();
    super.dispose();
  }
}
