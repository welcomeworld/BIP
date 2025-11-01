import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/ui/theme/theme_colors.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/reply_detail_bloc.dart';
import '../../domain/model/reply.dart';
import '../../gen_auto_import.dart';
import '../../utils/bip_router.dart';
import '../widgets/reply_card.dart';
import '../widgets/simple_svg.dart';

class ReplyDetailPage extends StatefulWidget {
  const ReplyDetailPage(this.reply, {super.key});

  final Reply reply;

  @override
  State<ReplyDetailPage> createState() => _ReplyDetailPageState();
}

class _ReplyDetailPageState
    extends BlocState<ReplyDetailPage, ReplyDetailBloc> {
  _ReplyDetailPageState() : super(ReplyDetailBloc(getIt<MediaManager>()));

  @override
  void initState() {
    super.initState();
    bloc.setReply(widget.reply);
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Column(
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
                            var exploreList = snap.data ?? [];
                            return ListView.builder(
                              physics: const NeverScrollableScrollPhysics(),
                              shrinkWrap: true,
                              itemCount: exploreList.length + 1,
                              itemBuilder: (context, index) {
                                if (index == 0) {
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
                                        child: ReplyCard(
                                          reply: bloc.reply,
                                          showSub: false,
                                        ),
                                      ),
                                      Divider(
                                        height: 8,
                                        // 分隔线高度
                                        thickness: 12,
                                        // 分隔线厚度
                                        color: Colors.grey[300],
                                        // 分隔线颜色
                                        indent: 0,
                                        // 左侧缩进
                                        endIndent: 0,
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.only(
                                            left: 16, top: 8, bottom: 16),
                                        child: Text(
                                          "相关回复共${exploreList.length}条",
                                          style: TextStyle(
                                            color: Theme.of(context)
                                                .colorScheme
                                                .onSurfaceLow,
                                            fontSize: 13,
                                            fontWeight: FontWeight.w500,
                                          ),
                                        ),
                                      ),
                                    ],
                                  );
                                }
                                var previewDetail = exploreList[index - 1];
                                return Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  mainAxisSize: MainAxisSize.min,
                                  children: [
                                    Padding(
                                      padding: const EdgeInsets.only(
                                        left: 16,
                                        right: 16,
                                        top: 8,
                                        bottom: 8,
                                      ),
                                      child: ReplyCard(
                                        reply: previewDetail,
                                        showSub: false,
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
                                    ),
                                  ],
                                );
                              },
                            );
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
                              hintText: localeString.replyHint,
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
                    AppLocale.of(context)!.replyDetail,
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
