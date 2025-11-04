import 'dart:async';

import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:rxdart/rxdart.dart';

import 'bloc.dart';

class LoginBloc extends Bloc {
  LoginBloc(this._mediaManager);

  final MediaManager _mediaManager;

  BehaviorSubject<String> loginQr = BehaviorSubject();
  Timer? validateTimer;

  @override
  void dispose() {
    loginQr.close();
    validateTimer?.cancel();
    super.dispose();
  }

  Future<void> refreshLoginQr(String sourceName) async {
    loginQr.add(await _mediaManager.requestLoginQr(sourceName));
    validateLogin(sourceName);
  }

  void validateLogin(String sourceName) {
    validateTimer?.cancel();
    validateTimer = Timer.periodic(const Duration(seconds: 3), (timber) async {
      final loginResult = await _mediaManager.validateLoginQr(sourceName);
      if (loginResult == SourceLoginResult.success) {
        validateTimer?.cancel();
        await BipRouter.rootRouter.maybePop();
      } else if (loginResult == SourceLoginResult.timeout) {
        refreshLoginQr(sourceName);
      }
    });
  }
}
