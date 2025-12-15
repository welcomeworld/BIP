import 'package:bip/bloc/media_page_detail_reply_bloc.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/model/media_page_detail.dart';
import 'package:bip/domain/model/media_page_preview.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'media_page_detail_reply_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<MediaManager>(), MockSpec<FocusNode>()])
void main() {
  group('MediaPageDetailReplyBloc', () {
    late MediaPageDetailReplyBloc mediaPageDetailReplyBloc;
    late MockMediaManager mockMediaManager;

    setUp(() {
      mockMediaManager = MockMediaManager();
      mediaPageDetailReplyBloc = MediaPageDetailReplyBloc(mockMediaManager);
      final pageDetail = MediaPageDetail.fromPreview(MediaPagePreview());
      mediaPageDetailReplyBloc.pageDetail = pageDetail;

      when(mockMediaManager.requestReplies(pageDetail, any))
          .thenAnswer((_) async => SourceApiResult([]));
    });

    tearDown(() {
      mediaPageDetailReplyBloc.dispose();
    });

    test('setPageDetail should update pageDetail and request replies',
        () async {
      final newPageDetail = MediaPageDetail.fromPreview(MediaPagePreview());
      when(mockMediaManager.requestReplies(newPageDetail, 1))
          .thenAnswer((_) async => SourceApiResult([]));

      await mediaPageDetailReplyBloc.setPageDetail(newPageDetail);

      expect(mediaPageDetailReplyBloc.pageDetail, newPageDetail);
      verify(mockMediaManager.requestReplies(newPageDetail, 1)).called(1);
    });

    test('refresh should reset page number and reload data', () async {
      await mediaPageDetailReplyBloc.refresh();

      verify(mockMediaManager.requestReplies(
              mediaPageDetailReplyBloc.pageDetail, 1))
          .called(1);
      verifyNever(mockMediaManager.requestReplies(
          mediaPageDetailReplyBloc.pageDetail, any));
    });

    test('loadMore should not do anything if it is already loading', () async {
      mediaPageDetailReplyBloc
          .loadMore(); // First call to set _isLoading to true
      mediaPageDetailReplyBloc.loadMore(); // Second call should return early
      verify(mockMediaManager.requestReplies(
              mediaPageDetailReplyBloc.pageDetail, 1))
          .called(1);
      verifyNever(mockMediaManager.requestReplies(
          mediaPageDetailReplyBloc.pageDetail, any));
    });

    test('onSubmitReply should clear text and unfocus', () {
      mediaPageDetailReplyBloc.textController.text = 'test reply';
      final mockFocusNode = MockFocusNode();
      mediaPageDetailReplyBloc.focusNode = mockFocusNode;

      mediaPageDetailReplyBloc.onSubmitReply('test reply');

      expect(mediaPageDetailReplyBloc.textController.text, isEmpty);
      verify(mockFocusNode.unfocus()).called(1);
    });
  });
}
