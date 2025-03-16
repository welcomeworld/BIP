import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/login_bloc.dart';
import 'package:barcode_widget/barcode_widget.dart';

class LoginPage extends StatefulWidget {
  const LoginPage(this.sourceName, {super.key});

  final String sourceName;

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends BlocState<LoginPage, LoginBloc> {
  _LoginPageState() : super(LoginBloc());

  @override
  void initState() {
    super.initState();
    bloc.setSourceName(widget.sourceName);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Center(
          child: StreamBuilder(
            stream: bloc.loginQr,
            builder: (context, snapshot) {
              return BarcodeWidget(
                data: snapshot.data ?? "",
                barcode: Barcode.qrCode(),
                width: 200,
                height: 200,
              );
            },
          ),
        ),
      ),
    );
  }
}
