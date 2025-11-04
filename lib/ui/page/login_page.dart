import 'package:barcode_widget/barcode_widget.dart';
import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/login_bloc.dart';

class LoginPage extends StatefulWidget {
  const LoginPage(this.sourceName, {super.key});

  final String sourceName;

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends BlocState<LoginPage, LoginBloc> {
  _LoginPageState() : super(LoginBloc(getIt<MediaManager>()));

  @override
  void initState() {
    super.initState();
    bloc.refreshLoginQr(widget.sourceName);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          onPressed: () {
            Navigator.of(context).maybePop();
          },
          padding: EdgeInsets.zero,
          icon: const Icon(Icons.arrow_back),
        ),
        title: Text(localeString.login),
      ),
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
