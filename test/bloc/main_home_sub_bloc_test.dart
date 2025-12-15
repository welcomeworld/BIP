import 'package:bip/bloc/main_home_sub_bloc.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:rxdart/rxdart.dart';

import 'main_home_sub_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<MediaManager>()])
void main() {
  group('MainHomeSubBloc', () {
    late MainHomeSubBloc mainHomeSubBloc;
    late MockMediaManager mockMediaManager;

    setUp(() async {
      mockMediaManager = MockMediaManager();
      when(mockMediaManager.refreshExplore()).thenAnswer((_) async {});
      when(mockMediaManager.homeExploreList)
          .thenAnswer((_) => BehaviorSubject().value([]));
      mainHomeSubBloc = MainHomeSubBloc(mockMediaManager);
      await Future.delayed(const Duration(milliseconds: 60));
      clearInteractions(mockMediaManager);
    });

    tearDown(() {
      mainHomeSubBloc.dispose();
    });

    test('refresh should call refreshExplore on media manager', () async {
      await mainHomeSubBloc.refresh();

      verify(mockMediaManager.refreshExplore()).called(1);
    });

    test('explore should call explore on media manager', () {
      mainHomeSubBloc.explore();

      verify(mockMediaManager.explore()).called(1);
    });

    test('explore should not call explore on media manager if already loading',
        () async {
      mainHomeSubBloc.explore(); // First call, _isLoading becomes true
      await mainHomeSubBloc.explore(); // Second call, should return early

      verify(mockMediaManager.explore()).called(1);
    });
  });
}
