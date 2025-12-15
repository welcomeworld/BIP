import 'package:bip/bloc/collections_bloc.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'collections_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<CollectionManager>()])
void main() {
  group('CollectionsBloc', () {
    late CollectionsBloc collectionsBloc;
    late MockCollectionManager mockCollectionManager;

    setUp(() async {
      mockCollectionManager = MockCollectionManager();
      when(mockCollectionManager.requestMediaCollections())
          .thenAnswer((_) => Stream.value([]));
      collectionsBloc = CollectionsBloc(mockCollectionManager);
      await untilCalled(mockCollectionManager.requestMediaCollections());
      clearInteractions(mockCollectionManager);
    });

    tearDown(() {
      collectionsBloc.dispose();
    });

    test('refresh should request collections and update the list', () async {
      final collections = [MediaCollection(sourceName: '', title: '')];
      when(mockCollectionManager.requestMediaCollections())
          .thenAnswer((_) => Stream.value(collections));
      await Future.delayed(const Duration(milliseconds: 50));
      await collectionsBloc.refresh();

      expect(collectionsBloc.listSubject.value, collections);
      expect(collectionsBloc.listEnd.value, isTrue);
    });

    test('addCollection should save the collection', () async {
      final collection = MediaCollection(sourceName: '', title: '');
      when(mockCollectionManager.saveMediaCollection(collection))
          .thenAnswer((_) async {
        return true;
      });

      await collectionsBloc.addCollection(collection);

      verify(mockCollectionManager.saveMediaCollection(collection)).called(1);
    });

    test('deleteCollection should delete and remove from list on success',
        () async {
      final collection = MediaCollection(sourceName: '', title: '');
      collectionsBloc.listSubject.add([collection]);
      when(mockCollectionManager.deleteMediaCollection(collection))
          .thenAnswer((_) async => true);

      final result = await collectionsBloc.deleteCollection(collection);

      expect(result, isTrue);
      expect(collectionsBloc.listSubject.value, isEmpty);
      verify(mockCollectionManager.deleteMediaCollection(collection)).called(1);
    });

    test('deleteCollection should not remove from list on failure', () async {
      final collection = MediaCollection(sourceName: '', title: '');
      collectionsBloc.listSubject.add([collection]);
      when(mockCollectionManager.deleteMediaCollection(collection))
          .thenAnswer((_) async => false);

      final result = await collectionsBloc.deleteCollection(collection);

      expect(result, isFalse);
      expect(collectionsBloc.listSubject.value, contains(collection));
      verify(mockCollectionManager.deleteMediaCollection(collection)).called(1);
    });
  });
}
