import 'dart:async';

import 'package:bip/data/source/source.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:rxdart/rxdart.dart';

import '../data/media_manager.dart';
import 'bloc.dart';

class LoginBloc extends Bloc {
  LoginBloc({MediaManager? mediaManager}) {
    _mediaManager = mediaManager ?? MediaManager();
  }

  late final MediaManager _mediaManager;

  String _sourceName = "";

  BehaviorSubject<String> loginQr = BehaviorSubject();
  Timer? validateTimer;

  @override
  void dispose() {
    loginQr.close();
    validateTimer?.cancel();
    super.dispose();
  }

  void setSourceName(String sourceName) {
    _sourceName = sourceName;
    refreshLoginQr();
  }

  void refreshLoginQr() async {
    loginQr.add(await _mediaManager.requestLoginQr(_sourceName));
    validateLogin();
  }

  void validateLogin() {
    validateTimer?.cancel();
    validateTimer = Timer.periodic(const Duration(seconds: 3), (timber) async {
      final loginResult = await _mediaManager.validateLoginQr(_sourceName);
      if (loginResult == SourceLoginResult.success) {
        validateTimer?.cancel();
        await BipRouter.rootRouter.maybePop();
      } else if (loginResult == SourceLoginResult.timeout) {
        refreshLoginQr();
      }
    });
  }
}
