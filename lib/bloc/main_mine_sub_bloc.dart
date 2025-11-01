import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/page_info.dart';

import '../domain/model/user_info.dart';
import 'bloc.dart';

class MainMineSubBloc extends Bloc {
  MainMineSubBloc(this._mediaManager);

  final MediaManager _mediaManager;

  Stream<Map<String, UserInfo?>> get accounts => _mediaManager.accounts.stream;

  void login(String sourceName) {
    BipRouter.rootRouter.pushPageInfo(
      PageInfo(PageNames.login, extras: {"data": sourceName}),
    );
  }
}
