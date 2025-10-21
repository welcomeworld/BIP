import 'package:bip/data/model/user_info.dart';
import 'package:rxdart/rxdart.dart';

import '../data/source_manager.dart';
import 'bloc.dart';

class UserDetailBloc extends Bloc {
  UserDetailBloc({SourceManager? sourceManager}) {
    _sourceManager = sourceManager ?? SourceManager();
  }

  late final SourceManager _sourceManager;

  String _sourceName = "";

  BehaviorSubject<UserInfo> userInfo = BehaviorSubject();

  @override
  void dispose() {
    userInfo.close();
    super.dispose();
  }

  void setUserInfo(UserInfo userInfo) {
    _sourceName = userInfo.sourceName;
    this.userInfo.add(userInfo);
    _refreshUserInfo();
  }

  void _refreshUserInfo() async {
    // final source = _sourceManager.activeSources[_sourceName];
    // if (source == null) return;
    // final result = await source.requestUserInfo();
    // if (result.isSuccess && result.data != null) {
    //   userInfo.add(result.data!);
    // }
  }

  void logout() async {
    await _sourceManager.activeSources[_sourceName]?.logout();
    // Implement logout functionality here
  }
}
