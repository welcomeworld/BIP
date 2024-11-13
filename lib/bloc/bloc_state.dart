import 'package:flutter/material.dart';

import 'bloc.dart';

abstract class BlocState<T extends StatefulWidget, B extends Bloc>
    extends State<T> {
  BlocState(this.bloc);

  final B bloc;

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
