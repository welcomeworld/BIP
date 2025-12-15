import 'package:bip/bloc/main_mine_sub_bloc.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:rxdart/rxdart.dart';

import 'main_mine_sub_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<MediaManager>()])
void main() {
  group('MainMineSubBloc', () {
    late MainMineSubBloc mainMineSubBloc;
    late MockMediaManager mockMediaManager;

    setUp(() {
      mockMediaManager = MockMediaManager();
      // It's important to mock the stream even if it's not directly used in the login method
      mainMineSubBloc = MainMineSubBloc(mockMediaManager);
    });

    test('accounts stream should be exposed from media manager', () async {
      Map<String, UserInfo?> accounts = {};
      // This test ensures that the bloc correctly exposes the stream from the media manager.
      when(mockMediaManager.accounts).thenAnswer(
          (_) => BehaviorSubject<Map<String, UserInfo?>>.seeded(accounts));
      // just call
      final realAccount = await mainMineSubBloc.accounts.first;
      expect(realAccount, same(accounts));
    });
  });
}
