import '../data/media_manager.dart';
import 'bloc.dart';

class MainBangumiSubBloc extends Bloc {
  MainBangumiSubBloc({MediaManager? mediaManager}) {
    _mediaManager = mediaManager ?? MediaManager();
  }

  late final MediaManager _mediaManager;
}
