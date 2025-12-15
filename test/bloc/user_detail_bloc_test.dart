import 'package:bip/bloc/user_detail_bloc.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/interfaces/source_manager.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'user_detail_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<SourceManager>(), MockSpec<Source>()])
void main() {
  group('UserDetailBloc', () {
    late UserDetailBloc userDetailBloc;
    late MockSourceManager mockSourceManager;

    setUp(() {
      mockSourceManager = MockSourceManager();
      userDetailBloc = UserDetailBloc(mockSourceManager);
    });

    tearDown(() {
      userDetailBloc.dispose();
    });

    test('setUserInfo should update userInfo stream', () {
      final userInfo = UserInfo(sourceName: 'test');

      userDetailBloc.setUserInfo(userInfo);

      expect(userDetailBloc.userInfo.value, userInfo);
    });

    test('logout should call logout on the correct source', () async {
      const sourceName = 'testSource';
      final userInfo = UserInfo(sourceName: sourceName);
      final mockSource = MockSource();
      when(mockSourceManager.activeSources)
          .thenReturn({sourceName: mockSource});
      when(mockSource.logout()).thenAnswer((_) async {
        return SourceApiResult(false);
      });

      userDetailBloc.setUserInfo(userInfo);
      await userDetailBloc.logout();

      verify(mockSource.logout()).called(1);
    });
  });
}
