import '../data/media_manager.dart';
import '../data/model/user_info.dart';
import 'bloc.dart';

class MainMineSubBloc extends Bloc {
  MainMineSubBloc({MediaManager? mediaManager}) {
    _mediaManager = mediaManager ?? MediaManager();
  }

  late final MediaManager _mediaManager;
  Stream<Map<String, UserInfo?>> get accounts => _mediaManager.accounts.stream;
}
