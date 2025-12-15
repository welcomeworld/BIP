import 'dart:async';

import 'package:bip/bloc/media_page_detail_bloc.dart';
import 'package:bip/domain/interfaces/bip_player.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/domain/model/media_page_detail.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:media_kit/media_kit.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'media_page_detail_bloc_test.mocks.dart';

@GenerateNiceMocks([
  MockSpec<MediaManager>(),
  MockSpec<BipPlayer>(),
  MockSpec<Database>(),
  MockSpec<CollectionManager>()
])
class FakeStream extends Fake implements PlayerStream {
  @override
  Stream<PlayerLog> get log => const Stream.empty();
}

void main() {
  group('MediaPageDetailBloc', () {
    late MediaPageDetailBloc mediaPageDetailBloc;
    late MockMediaManager mockMediaManager;
    late MockBipPlayer mockPlayer;
    late MockDatabase mockDatabase;
    late MockCollectionManager mockCollectionManager;

    setUp(() {
      mockMediaManager = MockMediaManager();
      mockPlayer = MockBipPlayer();
      mockDatabase = MockDatabase();
      mockCollectionManager = MockCollectionManager();

      when(mockMediaManager.requestDetail(any)).thenAnswer((_) async =>
          SourceApiResult(MediaPageDetail.fromPreview(MediaPagePreview())));
      when(mockCollectionManager.isInMediaCollection(any))
          .thenAnswer((_) async => false);
      when(mockCollectionManager.requestMediaCollections())
          .thenAnswer((_) => Stream.value([]));
      final fakeStream = FakeStream();
      when(mockPlayer.stream).thenAnswer((_) => fakeStream);

      mediaPageDetailBloc = MediaPageDetailBloc(
        mockMediaManager,
        mockPlayer,
        mockDatabase,
        mockCollectionManager,
      );
    });

    tearDown(() {
      mediaPageDetailBloc.dispose();
    });

    test(
        'setPreview saves history, fetches detail, and checks collection status',
        () async {
      final preview = MediaPagePreview();
      await mediaPageDetailBloc.setPreview(preview);

      verify(mockDatabase.saveMediaPageHistory(any)).called(1);
      verify(mockMediaManager.requestDetail(preview)).called(1);
      verify(mockCollectionManager.isInMediaCollection(preview)).called(1);
      expect(mediaPageDetailBloc.inCollection.value, false);
    });

    test('addToCollections adds to selected collections', () async {
      final preview = MediaPagePreview();
      final collection = MediaCollection(sourceName: '', title: '');
      mediaPageDetailBloc.selectedCollections.add({collection});

      when(mockCollectionManager.addToMediaCollection(collection, preview))
          .thenAnswer((_) async => true);

      final result = await mediaPageDetailBloc.addToCollections(preview);

      expect(result, isTrue);
      expect(mediaPageDetailBloc.inCollection.value, isTrue);
    });

    test('removeFromCollections removes from all collections', () async {
      final preview = MediaPagePreview();
      mediaPageDetailBloc.inCollection.add(true);
      when(mockCollectionManager.removeFromAllMediaCollection(preview))
          .thenAnswer((_) async => true);

      final result = await mediaPageDetailBloc.removeFromCollections(preview);

      expect(result, isTrue);
      verify(mockCollectionManager.removeFromAllMediaCollection(preview))
          .called(1);
      expect(mediaPageDetailBloc.inCollection.value, false);
    });
  });
}
