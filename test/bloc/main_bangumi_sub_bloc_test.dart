import 'package:bip/bloc/main_bangumi_sub_bloc.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/model/index_configuration.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'main_bangumi_sub_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<MediaManager>()])
void main() {
  group('MainBangumiSubBloc', () {
    late MainBangumiSubBloc mainBangumiSubBloc;
    late MockMediaManager mockMediaManager;

    setUp(() async {
      mockMediaManager = MockMediaManager();
      when(mockMediaManager.requestBangumiIndexConfiguration())
          .thenAnswer((_) async => IndexConfiguration(availableCategories: []));
      when(mockMediaManager.requestBangumiIndex(any, any))
          .thenAnswer((_) async => SourceApiResult([]));
      mainBangumiSubBloc = MainBangumiSubBloc(mockMediaManager);
      await Future.delayed(const Duration(milliseconds: 60));
      clearInteractions(mockMediaManager);
    });

    tearDown(() {
      mainBangumiSubBloc.dispose();
    });

    test('onSelect updates configuration and refreshes', () async {
      final initialConfig = IndexConfiguration(availableCategories: [
        IndexCategory(
            paramKey: 'key',
            displayName: 'Key',
            options: [IndexOption(displayText: 'V1', paramValue: 'v1')])
      ]);
      mainBangumiSubBloc.configurationSubject.add(initialConfig);

      await mainBangumiSubBloc.onSelect('key', 'v1');

      expect(
          mainBangumiSubBloc.configurationSubject.value
              .getSelectedValueForCategory('key'),
          'v1');
      verify(mockMediaManager.requestBangumiIndex(any, 1)).called(1);
    });

    test('refresh should reset page number and request list', () async {
      await mainBangumiSubBloc.refresh();

      verify(mockMediaManager.requestBangumiIndex(any, 1)).called(1);
    });

    test('loadMore should not do anything if it is already loading', () async {
      mainBangumiSubBloc.listEnd.add(false);
      mainBangumiSubBloc.loadMore(); // First call
      await mainBangumiSubBloc.loadMore(); // Second call should return early

      verify(mockMediaManager.requestBangumiIndex(any, any)).called(1);
    });
  });
}
