import 'package:bip/bloc/history_bloc.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:bip/domain/model/media_page_history.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'history_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<Database>()])
void main() {
  group('HistoryBloc', () {
    late HistoryBloc historyBloc;
    late MockDatabase mockDatabase;

    setUp(() async {
      mockDatabase = MockDatabase();
      when(mockDatabase.queryMediaPageHistory(
              key: anyNamed('key'), pageNumber: anyNamed('pageNumber')))
          .thenAnswer((_) async {
        print("before call queryMediaPageHistory");
        return [];
      });
      historyBloc = HistoryBloc(mockDatabase);
      await untilCalled(mockDatabase.queryMediaPageHistory(
          key: anyNamed('key'), pageNumber: anyNamed('pageNumber')));
      clearInteractions(mockDatabase);
    });

    tearDown(() {
      historyBloc.dispose();
    });

    test('refresh should reset page number and reload data', () async {
      when(mockDatabase.queryMediaPageHistory(key: '', pageNumber: 1))
          .thenAnswer((_) async => [MediaPageHistory(MediaPagePreview())]);

      await historyBloc.refresh();

      expect(historyBloc.listSubject.value.length, 1);
      verify(mockDatabase.queryMediaPageHistory(key: '', pageNumber: 1))
          .called(1);
    });

    test('loadMore should not do anything if it is already loading', () async {
      historyBloc.listEnd.add(false);
      historyBloc.loadMore(); // First call to set _isLoading to true
      await historyBloc.loadMore(); // Second call should return early
      await Future.delayed(const Duration(milliseconds: 500));
      verify(mockDatabase.queryMediaPageHistory(
              key: '', pageNumber: anyNamed('pageNumber')))
          .called(1);
    });

    test('handleSearchClick should open search or clear search text', () async {
      historyBloc.listSubject.add([MediaPageHistory(MediaPagePreview())]);
      historyBloc.searchOpen.add(false);

      await historyBloc.handleSearchClick();
      expect(historyBloc.searchOpen.value, isTrue);
      expect(historyBloc.listSubject.value, isEmpty);
      expect(historyBloc.listEnd.value, isTrue);

      historyBloc.searchTextController.text = "test search";

      await historyBloc.handleSearchClick();

      expect(historyBloc.searchTextController.text, '');
    });

    test('closeSearch should close search and restore cached state', () async {
      // First open search to cache the state
      final showingList = [MediaPageHistory(MediaPagePreview())];
      when(mockDatabase.queryMediaPageHistory(
              key: '', pageNumber: anyNamed('pageNumber')))
          .thenAnswer((_) async => showingList);
      await historyBloc.requestHistory();
      await historyBloc.handleSearchClick();

      expect(historyBloc.listSubject.value, isEmpty);
      // Now close it
      await historyBloc.closeSearch();

      expect(historyBloc.searchOpen.value, isFalse);
      expect(historyBloc.listSubject.value, showingList);
    });

    test('onSearch should perform search and update list', () async {
      const searchKey = 'test_key';
      final searchResult = [MediaPageHistory(MediaPagePreview())];
      when(mockDatabase.queryMediaPageHistory(key: searchKey, pageNumber: 1))
          .thenAnswer((_) async => searchResult);

      await historyBloc.onSearch(searchKey);

      expect(historyBloc.listSubject.value, searchResult);
    });
  });
}
