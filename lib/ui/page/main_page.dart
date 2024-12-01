import 'package:bip/bloc/main_bloc.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/gen_auto_import.dart';
import 'package:bip/utils/common_util.dart';
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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 0,
        backgroundColor: Theme.of(context).colorScheme.surface,
      ),
      body:
          ScreenUtil.isExpandDisplay(context) ? _expandBody() : _compactBody(),
    );
  }

  Widget _compactBody() {
    return Column(
      children: [
        Expanded(
          child: IndexedStack(
            index: bloc.tabIndex,
            children: [
              _previewList(),
              _bangumiList(),
              _mineContent(),
            ],
          ),
        ),
        BottomNavigationBar(
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
      ],
    );
  }

  void _changeTab(int newIndex) {
    setState(() {
      bloc.tabIndex = newIndex;
    });
  }

  Widget _previewList() {
    return StreamBuilder(
        stream: bloc.homeExploreList,
        builder: (context, snap) {
          var exploreList = snap.data;
          if (exploreList?.isNotEmpty == true) {
            return ListView.builder(
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
          return const Center(
            child: CircularProgressIndicator(
              strokeWidth: 2,
            ),
          );
        });
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
      child: Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
        ),
        elevation: 0,
        margin: EdgeInsets.zero,
        color: Theme.of(context).colorScheme.surfaceContainerHighest,
        child: InkWell(
          borderRadius: BorderRadius.circular(8),
          onTap: () {},
          highlightColor:
              Theme.of(context).colorScheme.primary.withOpacity(0.5),
          splashColor: Theme.of(context).colorScheme.primary.withOpacity(0.5),
          child: Row(
            children: [
              Expanded(
                flex: 8,
                child: Stack(
                  children: [
                    ClipRRect(
                      borderRadius: const BorderRadius.only(
                        topLeft: Radius.circular(8),
                        bottomLeft: Radius.circular(8),
                      ),
                      child: Image.network(
                        previewDetail.cover,
                        fit: BoxFit.fill,
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
                        style: const TextStyle(
                            fontSize: 12.0, color: Colors.white),
                      ),
                    ),
                  ],
                ),
              ),
              Expanded(
                flex: 7,
                child: Padding(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 4, vertical: 4),
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
                                      color: Theme.of(context)
                                          .colorScheme
                                          .secondary,
                                      borderRadius: const BorderRadius.all(
                                          Radius.circular(2))),
                                  child: Padding(
                                    padding: const EdgeInsets.symmetric(
                                        horizontal: 2),
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
      ),
    );
  }

  Widget _expandBody() {
    return Row();
  }
}
