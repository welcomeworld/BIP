import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/page_info.dart';

import '../data/media_manager.dart';
import '../data/model/user_info.dart';
import 'bloc.dart';

class MainMineSubBloc extends Bloc {
  MainMineSubBloc({MediaManager? mediaManager}) {
    _mediaManager = mediaManager ?? MediaManager();
  }

  late final MediaManager _mediaManager;

  Stream<Map<String, UserInfo?>> get accounts => _mediaManager.accounts.stream;

  void login(String sourceName) {
    BipRouter.rootRouter.pushPageInfo(
      PageInfo(PageNames.login, extras: {"data": sourceName}),
    );
  }
}
