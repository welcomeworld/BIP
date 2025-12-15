import 'package:bip/bloc/search_bloc.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'search_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<Database>(), MockSpec<MediaManager>()])
void main() {
  group('SearchBloc', () {
    late SearchBloc searchBloc;
    late MockDatabase mockDatabase;
    late MockMediaManager mockMediaManager;

    setUp(() {
      mockDatabase = MockDatabase();
      mockMediaManager = MockMediaManager();
      when(mockMediaManager.requestSearchHot())
          .thenAnswer((_) async => SourceApiResult(['hot1', 'hot2']));
      when(mockDatabase.searchHistoryStream)
          .thenAnswer((_) => Stream.value([]));
      searchBloc = SearchBloc(mockDatabase, mockMediaManager);
    });

    tearDown(() {
      searchBloc.dispose();
    });

    test('onClearSearch should clear text and hide results', () {
      searchBloc.searchTextController.text = 'test';
      searchBloc.showResultSubject.add(true);

      searchBloc.onClearSearch();

      expect(searchBloc.searchTextController.text, isEmpty);
      expect(searchBloc.showResultSubject.value, isFalse);
    });

    test('onClearHistory should call database to clear histories', () {
      searchBloc.onClearHistory();

      verify(mockDatabase.clearSearchHistories()).called(1);
    });

    test('onSearchHot should set search text and perform search', () {
      const hotKey = 'hot_search';
      when(mockMediaManager.requestSearch(hotKey, 1))
          .thenAnswer((_) => Stream.value([]));

      searchBloc.onSearchHot(hotKey);

      expect(searchBloc.searchTextController.text, hotKey);
      verify(mockMediaManager.requestSearch(hotKey, 1)).called(1);
      verify(mockDatabase.saveSearchHistory(any)).called(1);
      expect(searchBloc.showResultSubject.value, isTrue);
    });

    test('onSearchHistory should set search text and perform search', () {
      const historyKey = 'history_search';
      when(mockMediaManager.requestSearch(historyKey, 1))
          .thenAnswer((_) => Stream.value([]));

      searchBloc.onSearchHistory(historyKey);

      expect(searchBloc.searchTextController.text, historyKey);
      verify(mockMediaManager.requestSearch(historyKey, 1)).called(1);
      verify(mockDatabase.saveSearchHistory(any)).called(1);
      expect(searchBloc.showResultSubject.value, isTrue);
    });

    test('onSearch should save history and show results', () async {
      const searchKey = 'search_key';
      final results = [MediaPagePreview()];
      when(mockMediaManager.requestSearch(searchKey, 1))
          .thenAnswer((_) => Stream.value(results));

      await searchBloc.onSearch(searchKey);

      verify(mockDatabase.saveSearchHistory(any)).called(1);
      verify(mockMediaManager.requestSearch(searchKey, 1)).called(1);
      expect(searchBloc.showResultSubject.value, isTrue);
      expect(searchBloc.searchResultSubject.value, results);
    });

    test('onSearchMore should load more results', () async {
      const searchKey = 'search_key';
      final initialResults = [MediaPagePreview()];
      final moreResults = [MediaPagePreview()];
      when(mockMediaManager.requestSearch(searchKey, 1))
          .thenAnswer((_) => Stream.value(initialResults));
      when(mockMediaManager.requestSearch(searchKey, 2))
          .thenAnswer((_) => Stream.value(moreResults));

      await searchBloc.onSearch(searchKey);
      await searchBloc.onSearchMore();
      verify(mockMediaManager.requestSearch(searchKey, 1)).called(1);
      verify(mockMediaManager.requestSearch(searchKey, 2)).called(1);
      expect(searchBloc.searchResultSubject.value.length, 2);
    });
  });
}
