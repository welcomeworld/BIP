import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/history_bloc.dart';
import '../../gen_auto_import.dart';
import '../../utils/bip_router.dart';
import '../widgets/media_preview_card.dart';
import '../widgets/simple_svg.dart';

class HistoryPage extends StatefulWidget {
  const HistoryPage({super.key});

  @override
  State<HistoryPage> createState() => _HistoryPageState();
}

class _HistoryPageState extends BlocState<HistoryPage, HistoryBloc> {
  _HistoryPageState() : super(HistoryBloc(getIt<Database>()));

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<bool>(
        stream: bloc.searchOpen.stream,
        builder: (context, snapshot) {
          final searchOpen = snapshot.data ?? false;
          return PopScope(
            canPop: !searchOpen,
            onPopInvokedWithResult: (didPop, result) {
              if (didPop) {
                return;
              }
              if (searchOpen) {
                bloc.closeSearch();
                return;
              }
            },
            child: Material(
              child: SafeArea(
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
                        SliverAppBar(
                          leading: IconButton(
                            onPressed: () {
                              if (searchOpen) {
                                bloc.closeSearch();
                                return;
                              }
                              BipRouter.of(context).maybePop();
                            },
                            padding: EdgeInsets.zero,
                            icon: const Icon(Icons.arrow_back),
                          ),
                          title: searchOpen
                              ? TextField(
                                  controller: bloc.searchTextController,
                                  decoration: InputDecoration(
                                    hintText: localeString.searchHint,
                                  ),
                                  autofocus: true,
                                  // 展开时自动聚焦
                                  textInputAction: TextInputAction.search,
                                  onSubmitted: bloc.onSearch,
                                )
                              : Text(localeString.history),
                          surfaceTintColor: Colors.transparent,
                          backgroundColor: colorScheme.surface,
                          elevation: 0,
                          actions: [
                            Padding(
                              padding: const EdgeInsets.only(right: 8),
                              child: IconButton(
                                icon: searchOpen
                                    ? Icon(
                                        Icons.close,
                                        size: 24,
                                        color: colorScheme.onSurface,
                                      )
                                    : SimpleSvg(
                                        "assets/img/ic_search.svg",
                                        size: 24,
                                        color: colorScheme.onSurface,
                                      ),
                                onPressed: bloc.handleSearchClick,
                              ),
                            )
                          ],
                          floating: false,
                          pinned: true,
                        ),
                        SliverToBoxAdapter(
                          child: StreamBuilder(
                              stream: bloc.listSubject.stream,
                              builder: (context, snap) {
                                var list = snap.data;
                                if (list?.isNotEmpty == true) {
                                  return ListView.builder(
                                    physics:
                                        const NeverScrollableScrollPhysics(),
                                    shrinkWrap: true,
                                    itemCount: list!.length,
                                    itemBuilder: (context, index) {
                                      var previewDetail = list[index];
                                      return Padding(
                                        padding: const EdgeInsets.only(
                                          left: 16,
                                          right: 16,
                                          top: 8,
                                          bottom: 8,
                                        ),
                                        child: mediaPreviewCard(context,
                                            previewDetail.mediaPagePreview),
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
                                  stream: bloc.listEnd.stream,
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
            ),
          );
        });
  }
}
