import 'package:bip/ui/widgets/simple_svg.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/media_page_detail_reply_bloc.dart';
import '../../data/model/media_page_detail.dart';
import '../../gen_auto_import.dart';
import '../widgets/reply_card.dart';

class MediaPageDetailReplyPage extends StatefulWidget {
  const MediaPageDetailReplyPage(this.pageDetail, {super.key});

  final MediaPageDetail pageDetail;

  @override
  State<MediaPageDetailReplyPage> createState() =>
      _MediaPageDetailReplyPageState();
}

class _MediaPageDetailReplyPageState
    extends BlocState<MediaPageDetailReplyPage, MediaPageDetailReplyBloc> {
  _MediaPageDetailReplyPageState() : super(MediaPageDetailReplyBloc());

  @override
  void initState() {
    super.initState();
    bloc.setPageDetail(widget.pageDetail);
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Column(
        mainAxisSize: MainAxisSize.max,
        children: [
          Expanded(
            child: RefreshIndicator(
              onRefresh: bloc.refresh,
              child: NotificationListener<ScrollNotification>(
                onNotification: (notification) {
                  if (notification is UserScrollNotification) {
                    if (notification.direction == ScrollDirection.idle) {
                      bloc.checkLoadMore();
                      bloc.hideBottom(56);
                    }
                  }
                  return false;
                },
                child: CustomScrollView(
                  controller: bloc.scrollController,
                  slivers: [
                    SliverPersistentHeader(
                      delegate: ReplyListDelegate(),
                      pinned: true,
                    ),
                    SliverToBoxAdapter(
                      child: StreamBuilder(
                          stream: bloc.repliesSubject.stream,
                          builder: (context, snap) {
                            var exploreList = snap.data;
                            if (exploreList?.isNotEmpty == true) {
                              return ListView.builder(
                                physics: const NeverScrollableScrollPhysics(),
                                shrinkWrap: true,
                                itemCount: exploreList!.length,
                                itemBuilder: (context, index) {
                                  var previewDetail = exploreList[index];
                                  return Column(
                                    crossAxisAlignment:
                                        CrossAxisAlignment.start,
                                    mainAxisSize: MainAxisSize.min,
                                    children: [
                                      Padding(
                                        padding: const EdgeInsets.only(
                                          left: 16,
                                          right: 16,
                                          top: 8,
                                          bottom: 8,
                                        ),
                                        child: ReplyCard(reply: previewDetail),
                                      ),
                                      Divider(
                                        height: 1,
                                        // 分隔线高度
                                        thickness: 1,
                                        // 分隔线厚度
                                        color: Colors.grey[300],
                                        // 分隔线颜色
                                        indent: 0,
                                        // 左侧缩进
                                        endIndent: 0,
                                      ),
                                    ],
                                  );
                                },
                              );
                            }
                            return const SizedBox.shrink();
                          }),
                    ),
                    const SliverFillRemaining(
                      hasScrollBody: false,
                      child: SizedBox.shrink(),
                    ),
                    SliverToBoxAdapter(
                      child: SizedBox(
                        height: 56,
                        child: Center(
                          child: StreamBuilder<bool>(
                              stream: bloc.replyEnd.stream,
                              builder: (context, snapshot) {
                                if (snapshot.hasData && snapshot.data!) {
                                  return Text(
                                    AppLocale.of(context)!.noMoreData,
                                    style: TextStyle(
                                      color: Theme.of(context)
                                          .colorScheme
                                          .onSurface,
                                    ),
                                  );
                                }
                                return const CircularProgressIndicator(
                                  strokeWidth: 2,
                                );
                              }),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          SizedBox(
            height: 52,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              mainAxisSize: MainAxisSize.max,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Divider(
                  height: 1,
                  // 分隔线高度
                  thickness: 1,
                  // 分隔线厚度
                  color: Colors.grey[300],
                  // 分隔线颜色
                  indent: 0,
                  // 左侧缩进
                  endIndent: 0,
                ),
                Expanded(
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Expanded(
                        child: Padding(
                          padding: const EdgeInsets.only(
                              left: 8.0, right: 4, top: 8, bottom: 8),
                          child: TextField(
                            controller: bloc.textController,
                            focusNode: bloc.focusNode,
                            textInputAction: TextInputAction.send,
                            style: TextStyle(
                              color: colorScheme.onSurface,
                              fontWeight: FontWeight.w500,
                              fontSize: 15,
                            ),
                            decoration: InputDecoration(
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(20),
                                  borderSide: BorderSide.none),
                              filled: true,
                              fillColor: colorScheme.surfaceContainer,
                              contentPadding:
                                  const EdgeInsets.fromLTRB(12, 0, 12, 0),
                              hintText: localeString.commentHint,
                            ),
                            onSubmitted: bloc.onSubmitReply,
                            autofocus: false,
                          ),
                        ),
                      ),
                      IconButton(
                        padding: EdgeInsets.zero,
                        onPressed: bloc.onEmojiClick,
                        icon: SimpleSvg(
                          "assets/img/ic_emoji.svg",
                          color: colorScheme.onSurface,
                          size: 32,
                        ),
                        iconSize: 32,
                      )
                    ],
                  ),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}

class ReplyListDelegate extends SliverPersistentHeaderDelegate {
  @override
  double get minExtent => 40; // 进度条的最小高度
  @override
  double get maxExtent => 40; // 进度条的最大高度

  @override
  Widget build(
      BuildContext context, double shrinkOffset, bool overlapsContent) {
    final colorScheme = Theme.of(context).colorScheme;
    return DecoratedBox(
      decoration: BoxDecoration(color: colorScheme.surface),
      child: Column(
        children: [
          Expanded(
            child: Row(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Padding(
                  padding: const EdgeInsets.only(left: 16),
                  child: Text(
                    AppLocale.of(context)!.comment,
                    style: TextStyle(
                      color: colorScheme.onSurface,
                      fontWeight: FontWeight.w500,
                      fontSize: 16,
                    ),
                  ),
                ),
                IconButton(
                  onPressed: () {
                    BipRouter.rootRouter.maybePop();
                  },
                  padding: EdgeInsets.zero,
                  icon: const Icon(Icons.close),
                )
              ],
            ),
          ),
          Divider(
            height: 1,
            // 分隔线高度
            thickness: 1,
            // 分隔线厚度
            color: Colors.grey[300],
            // 分隔线颜色
            indent: 0,
            // 左侧缩进
            endIndent: 0,
          )
        ],
      ),
    );
  }

  @override
  bool shouldRebuild(SliverPersistentHeaderDelegate oldDelegate) {
    return false;
  }
}
