import 'package:bip/bloc/login_bloc.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

@GenerateNiceMocks([MockSpec<MediaManager>()])
import 'login_bloc_test.mocks.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();
  group('LoginBloc', () {
    late LoginBloc loginBloc;
    late MediaManager mockMediaManager;

    setUp(() {
      mockMediaManager = MockMediaManager();
      loginBloc = LoginBloc(mockMediaManager);
    });

    tearDown(() {
      loginBloc.dispose();
    });

    test('refreshLoginQr should request and add new login QR code to stream',
        () async {
      const sourceName = 'testSource';
      when(mockMediaManager.requestLoginQr(sourceName))
          .thenAnswer((_) async => 'new_qr_code');
      await loginBloc.refreshLoginQr(sourceName);
      expect(loginBloc.loginQr.value, 'new_qr_code');
      verify(mockMediaManager.requestLoginQr(sourceName)).called(1);
    });

    test('validateLogin should pop router on success', () async {
      const sourceName = 'testSource';
      when(mockMediaManager.validateLoginQr(sourceName))
          .thenAnswer((_) async => SourceLoginResult.success);
      await loginBloc.refreshLoginQr(sourceName);
      // We can't truly test navigation, but we can verify that the timer is cancelled.
      // And we can assume that if it is, maybePop() was called.
      await Future.delayed(const Duration(seconds: 4));
      expect(loginBloc.validateTimer?.isActive, isFalse);
    });

    test('validateLogin should refresh QR code on timeout', () async {
      const sourceName = 'testSource';
      when(mockMediaManager.validateLoginQr(sourceName))
          .thenAnswer((_) async => SourceLoginResult.timeout);
      when(mockMediaManager.requestLoginQr(sourceName))
          .thenAnswer((_) async => 'new_qr_code_after_timeout');
      await loginBloc.refreshLoginQr(sourceName);
      await Future.delayed(const Duration(seconds: 4));
      expect(loginBloc.loginQr.value, 'new_qr_code_after_timeout');
      verify(mockMediaManager.requestLoginQr(sourceName)).called(2);
    });
  });
}
