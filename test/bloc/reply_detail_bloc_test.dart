import 'package:bip/bloc/reply_detail_bloc.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/source.dart';
import 'package:bip/domain/model/reply.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';

import 'reply_detail_bloc_test.mocks.dart';

@GenerateNiceMocks([MockSpec<MediaManager>(), MockSpec<FocusNode>()])
void main() {
  group('ReplyDetailBloc', () {
    late ReplyDetailBloc replyDetailBloc;
    late MockMediaManager mockMediaManager;

    setUp(() {
      mockMediaManager = MockMediaManager();
      replyDetailBloc = ReplyDetailBloc(mockMediaManager);
      final reply =
          Reply('id', owner: UserInfo(sourceName: ''), content: ReplyContent());
      replyDetailBloc.reply = reply;

      when(mockMediaManager.requestSubReplies(reply, any))
          .thenAnswer((_) async => SourceApiResult([]));
    });

    tearDown(() {
      replyDetailBloc.dispose();
    });

    test('refresh should reset page number and reload data', () async {
      await replyDetailBloc.refresh();

      verify(mockMediaManager.requestSubReplies(replyDetailBloc.reply, 1))
          .called(1);
    });

    test('loadMore should not do anything if it is already loading', () async {
      replyDetailBloc.loadMore(); // First call to set _isLoading to true
      replyDetailBloc.loadMore(); // Second call should return early

      verify(mockMediaManager.requestSubReplies(replyDetailBloc.reply, any))
          .called(1);
    });

    test('setReply should update reply and request new replies', () async {
      final newReply = Reply('new_reply',
          owner: UserInfo(sourceName: ''), content: ReplyContent());
      when(mockMediaManager.requestSubReplies(newReply, 1))
          .thenAnswer((_) async => SourceApiResult([]));

      await replyDetailBloc.setReply(newReply);

      expect(replyDetailBloc.reply, newReply);
      verify(mockMediaManager.requestSubReplies(newReply, 1)).called(1);
    });

    test('onSubmitReply should clear text and unfocus', () {
      replyDetailBloc.textController.text = 'test reply';
      // To test focus, we need a focus node attached to a widget.
      // For a unit test, we can just verify the methods are called.
      final mockFocusNode = MockFocusNode();
      replyDetailBloc.focusNode = mockFocusNode;

      replyDetailBloc.onSubmitReply('test reply');

      expect(replyDetailBloc.textController.text, isEmpty);
      verify(mockFocusNode.unfocus()).called(1);
    });
  });
}
