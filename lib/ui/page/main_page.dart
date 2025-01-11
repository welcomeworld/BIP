import 'package:bip/bloc/main_bloc.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/gen_auto_import.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:bip/utils/common_util.dart';
import 'package:bip/utils/page_info.dart';
import 'package:bip/utils/screen_util.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

import '../../bloc/bloc_state.dart';

class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends BlocState<MainPage, MainBloc> {
  _MainPageState() : super(MainBloc());
  final ScrollController _scrollController = ScrollController();

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(() {
      if (_scrollController.position.pixels >=
          _scrollController.position.maxScrollExtent - 256) {
        bloc.explore();
      }
    });
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return ScreenUtil.isExpandDisplay(context) ? _expandBody() : _compactBody();
  }

  Widget _compactBody() {
    return Scaffold(
      body: SafeArea(
        child: IndexedStack(
          index: bloc.tabIndex,
          children: [
            _previewList(),
            _bangumiList(),
            _mineContent(),
          ],
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: [
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_home_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                Theme.of(context).colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_home_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                Theme.of(context).colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: AppLocale.of(context)!.home,
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_bangumi_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                Theme.of(context).colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_bangumi_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                Theme.of(context).colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: AppLocale.of(context)!.bangumi,
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_mine_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                Theme.of(context).colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_mine_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                Theme.of(context).colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: AppLocale.of(context)!.mine,
          ),
        ],
        elevation: 0,
        currentIndex: bloc.tabIndex,
        onTap: _changeTab,
      ),
    );
  }

  void _changeTab(int newIndex) {
    setState(() {
      bloc.tabIndex = newIndex;
    });
  }

  Widget _previewList() {
    return RefreshIndicator(
      onRefresh: bloc.refresh,
      child: CustomScrollView(
        controller: _scrollController,
        slivers: [
          SliverAppBar(
            surfaceTintColor: Colors.transparent,
            backgroundColor: Theme.of(context).colorScheme.surface,
            elevation: 0,
            title: FilledButton.tonal(
              style: FilledButton.styleFrom(
                padding: const EdgeInsets.all(16),
                backgroundColor:
                    Theme.of(context).colorScheme.surfaceContainerHigh,
                fixedSize: const Size(double.maxFinite, 56),
              ),
              onPressed: () => BipRouter.of(context).push(PageNames.search),
              child: Row(
                children: [
                  SvgPicture.asset(
                    "assets/img/ic_search.svg",
                    width: 24,
                    height: 24,
                    colorFilter: ColorFilter.mode(
                        Theme.of(context).colorScheme.onSurface,
                        BlendMode.srcIn),
                  ),
                  const SizedBox(width: 16),
                  Text(
                    AppLocale.of(context)!.searchHint,
                    style: TextStyle(
                      fontSize: 16,
                      color: Theme.of(context).colorScheme.onSurfaceVariant,
                    ),
                  )
                ],
              ),
            ),
            floating: true,
            snap: true,
          ),
          SliverToBoxAdapter(
            child: StreamBuilder(
                stream: bloc.homeExploreList,
                builder: (context, snap) {
                  var exploreList = snap.data;
                  if (exploreList?.isNotEmpty == true) {
                    return ListView.builder(
                      physics: const NeverScrollableScrollPhysics(),
                      shrinkWrap: true,
                      itemCount: exploreList!.length,
                      itemBuilder: (context, index) {
                        var previewDetail = exploreList[index];
                        return Padding(
                          padding: const EdgeInsets.only(
                            left: 16,
                            right: 16,
                            top: 8,
                            bottom: 8,
                          ),
                          child: _previewCard(previewDetail),
                        );
                      },
                    );
                  }
                  return const SizedBox.shrink();
                }),
          ),
          SliverPersistentHeader(delegate: LoadingIndicatorDelegate())
        ],
      ),
    );
  }

  Widget _bangumiList() {
    return const Center(
      child: Text("施工中"),
    );
  }

  Widget _mineContent() {
    return const Center(
      child: Text("施工中"),
    );
  }

  Widget _previewCard(MediaPagePreview previewDetail) {
    return AspectRatio(
      aspectRatio: 3,
      child: FilledButton.tonal(
        onPressed: () => BipRouter.of(context).pushPageInfo(
          PageInfo(
            PageNames.mediaPageDetail,
            extras: {"data": previewDetail},
          ),
        ),
        style: FilledButton.styleFrom(
          padding: EdgeInsets.zero,
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
          elevation: 0,
          backgroundColor:
              Theme.of(context).colorScheme.surfaceContainerHighest,
        ),
        child: Row(
          children: [
            Expanded(
              flex: 8,
              child: Stack(
                children: [
                  SizedBox.expand(
                    child: ClipRRect(
                      borderRadius: const BorderRadius.only(
                        topLeft: Radius.circular(8),
                        bottomLeft: Radius.circular(8),
                      ),
                      child: Image.network(
                        previewDetail.cover,
                        fit: BoxFit.fill,
                      ),
                    ),
                  ),
                  Positioned.fill(
                    child: DecoratedBox(
                      decoration: BoxDecoration(
                        borderRadius: const BorderRadius.only(
                          bottomLeft: Radius.circular(8),
                        ),
                        gradient: LinearGradient(
                          begin: Alignment.bottomCenter,
                          end: Alignment.topCenter,
                          colors: [
                            Colors.black.withOpacity(0.6),
                            Colors.transparent
                          ],
                          stops: const [0.0, 0.3], // 从底部到1/3位置
                        ),
                      ),
                    ),
                  ),
                  Positioned(
                    right: 8.0,
                    bottom: 4,
                    child: Text(
                      CommonUtil.formatDurationShow(previewDetail.duration),
                      style:
                          const TextStyle(fontSize: 12.0, color: Colors.white),
                    ),
                  ),
                ],
              ),
            ),
            Expanded(
              flex: 7,
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 4, vertical: 4),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      previewDetail.title,
                      maxLines: 3,
                      overflow: TextOverflow.ellipsis,
                      style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.w500,
                          color:
                              Theme.of(context).colorScheme.onSurfaceVariant),
                    ),
                    Column(
                      children: [
                        Row(
                          children: [
                            if (previewDetail.tags.isNotEmpty)
                              DecoratedBox(
                                decoration: BoxDecoration(
                                    color:
                                        Theme.of(context).colorScheme.secondary,
                                    borderRadius: const BorderRadius.all(
                                        Radius.circular(2))),
                                child: Padding(
                                  padding:
                                      const EdgeInsets.symmetric(horizontal: 2),
                                  child: Text(
                                    previewDetail.tags.firstOrNull ?? "",
                                    style: TextStyle(
                                      color: Theme.of(context)
                                          .colorScheme
                                          .onSecondary,
                                    ),
                                  ),
                                ),
                              ),
                            if (!previewDetail.tags.isNotEmpty)
                              SvgPicture.asset(
                                "assets/img/ic_upper.svg",
                                height: 18,
                                width: 18,
                                colorFilter: ColorFilter.mode(
                                    Theme.of(context)
                                        .colorScheme
                                        .onSurfaceVariant,
                                    BlendMode.srcIn),
                              ),
                            const SizedBox(width: 2),
                            Expanded(
                              child: Text(
                                previewDetail.owner.name,
                                maxLines: 1,
                                overflow: TextOverflow.ellipsis,
                                style: TextStyle(
                                  color: Theme.of(context)
                                      .colorScheme
                                      .onSurfaceVariant,
                                ),
                              ),
                            ),
                          ],
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            SvgPicture.asset(
                              "assets/img/ic_play_count.svg",
                              height: 16,
                              width: 16,
                              colorFilter: ColorFilter.mode(
                                  Theme.of(context)
                                      .colorScheme
                                      .onSurfaceVariant,
                                  BlendMode.srcIn),
                            ),
                            const SizedBox(width: 2),
                            Expanded(
                              child: Text(
                                CommonUtil.formatPlayCount(
                                    previewDetail.playCount),
                                maxLines: 1,
                                overflow: TextOverflow.ellipsis,
                                style: TextStyle(
                                  fontSize: 12.0,
                                  color: Theme.of(context)
                                      .colorScheme
                                      .onSurfaceVariant,
                                ),
                              ),
                            ),
                            const SizedBox(
                              width: 8,
                            ),
                            Text(
                              CommonUtil.formatDateShow(
                                DateTime.fromMillisecondsSinceEpoch(
                                  previewDetail.pageTime,
                                ),
                              ),
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis,
                              style: TextStyle(
                                fontSize: 12.0,
                                color: Theme.of(context)
                                    .colorScheme
                                    .onSurfaceVariant,
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _expandBody() {
    return Row();
  }
}

class LoadingIndicatorDelegate extends SliverPersistentHeaderDelegate {
  @override
  double get minExtent => 56; // 进度条的最小高度
  @override
  double get maxExtent => 56; // 进度条的最大高度

  @override
  Widget build(
      BuildContext context, double shrinkOffset, bool overlapsContent) {
    return const Center(
      child: CircularProgressIndicator(
        strokeWidth: 2,
      ),
    );
  }

  @override
  bool shouldRebuild(SliverPersistentHeaderDelegate oldDelegate) {
    return false;
  }
}
