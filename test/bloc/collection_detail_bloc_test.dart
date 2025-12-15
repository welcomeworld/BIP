import 'package:bip/bloc/collection_detail_bloc.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'collection_detail_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<CollectionManager>()])
void main() {
  group('CollectionDetailBloc', () {
    late CollectionDetailBloc collectionDetailBloc;
    late MockCollectionManager mockCollectionManager;

    setUp(() async {
      mockCollectionManager = MockCollectionManager();
      collectionDetailBloc = CollectionDetailBloc(mockCollectionManager);
      when(mockCollectionManager.requestMediaCollectionDetail(any,
              key: anyNamed('key'), pageNumber: anyNamed('pageNumber')))
          .thenAnswer((_) async => []);
      final collection = MediaCollection(sourceName: '', title: '');
      await collectionDetailBloc.setCollection(collection);
    });

    setUp(() {
      clearInteractions(mockCollectionManager);
    });

    tearDown(() {
      collectionDetailBloc.dispose();
    });

    test('setCollection should update collection and refresh', () async {
      final collection = MediaCollection(sourceName: '', title: '');
      await collectionDetailBloc.setCollection(collection);
      expect(collectionDetailBloc.collectionSubject.value, collection);
      //todo: check why real object can call verify
      verify(collectionDetailBloc.refresh()).called(1);
    });

    test('refresh should reset page number and request details', () async {
      await collectionDetailBloc.refresh();
      verify(mockCollectionManager.requestMediaCollectionDetail(any,
              key: anyNamed('key'), pageNumber: 1))
          .called(1);
    });

    test('refresh should not do anything if it is already refresh', () async {
      collectionDetailBloc.refresh();
      await collectionDetailBloc.refresh();
      verify(mockCollectionManager.requestMediaCollectionDetail(any,
              key: anyNamed('key'), pageNumber: anyNamed('pageNumber')))
          .called(1);
    });

    test('loadMore should not do anything if it is already loading', () async {
      collectionDetailBloc.listEnd.add(false);
      collectionDetailBloc.loadMore();
      await collectionDetailBloc.loadMore();
      verify(mockCollectionManager.requestMediaCollectionDetail(any,
              key: anyNamed('key'), pageNumber: anyNamed('pageNumber')))
          .called(1);
    });

    test('onSearch should set search key and request details', () async {
      const searchKey = 'test_key';
      await collectionDetailBloc.onSearch(searchKey);
      verify(mockCollectionManager.requestMediaCollectionDetail(any,
              key: searchKey, pageNumber: 1))
          .called(1);
    });
  });
}
