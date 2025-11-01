import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/model/reply.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';

import '../domain/interfaces/source.dart';
import '../domain/model/media_page_detail.dart';
import '../utils/bip_router.dart';
import 'bloc.dart';

class MediaPageDetailReplyBloc extends Bloc {
  MediaPageDetailReplyBloc(this._mediaManager);

  final MediaManager _mediaManager;

  final ScrollController scrollController = ScrollController();

  late MediaPageDetail pageDetail;
  int _replyPageNumber = 1;

  final List<Reply> _replies = [];
  BehaviorSubject<List<Reply>> repliesSubject = BehaviorSubject();
  BehaviorSubject<bool> replyEnd = BehaviorSubject();
  bool _isRefreshing = false;
  bool _isLoading = false;

  TextEditingController textController = TextEditingController();
  FocusNode focusNode = FocusNode();

  @override
  void dispose() {
    super.dispose();
    scrollController.dispose();
    replyEnd.close();
    repliesSubject.close();
    textController.dispose();
    focusNode.dispose();
  }

  void checkLoadMore() {
    if (!scrollController.hasClients) {
      return;
    }
    final position = scrollController.position;
    if (position.pixels >= position.maxScrollExtent - 256) {
      loadMore();
    }
  }

  void hideBottom(double bottomHigh) async {
    if (!scrollController.hasClients) {
      return;
    }
    final position = scrollController.position;
    if (position.pixels >= position.maxScrollExtent - bottomHigh) {
      // 向上滚动刚好隐藏进度条
      scrollController.animateTo(
        position.maxScrollExtent - bottomHigh,
        duration: const Duration(milliseconds: 250),
        curve: Curves.easeIn,
      );
    }
  }

  void loadMore() async {
    if (_isLoading) return;
    if (replyEnd.valueOrNull == true) return;
    _isLoading = true;
    await requestReplies();
    _isLoading = false;
  }

  Future<void> refresh() async {
    if (_isRefreshing) return;
    _isRefreshing = true;
    _replyPageNumber = 1;
    replyEnd.add(false);
    await requestReplies();
    _isRefreshing = false;
  }

  Future<void> requestReplies() async {
    final repliesResult =
        await _mediaManager.requestReplies(pageDetail, _replyPageNumber++);
    switch (repliesResult.resultCode) {
      case SourceApiResult.resultSuccess:
        if (_replyPageNumber == 2) {
          _replies.clear();
        }
        _replies.addAll(repliesResult.result);
        repliesSubject.add(_replies);
        break;
      case SourceApiResult.resultSourceEmpty:
        replyEnd.add(true);
        break;
      default:
        ScaffoldMessenger.of(BipRouter.rootRouter.navigatorKey.currentContext!)
            .showSnackBar(
          SnackBar(
            content: Text(
                "requestReplies with error code: ${repliesResult.resultCode}"),
          ),
        );
        break;
    }
  }

  Future<void> setPageDetail(MediaPageDetail pageDetail) async {
    this.pageDetail = pageDetail;
    await requestReplies();
  }

  void onEmojiClick() {}

  Future<void> onSubmitReply(String replyContent) async {
    textController.clear();
    focusNode.unfocus();
  }
}
