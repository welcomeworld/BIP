import 'package:bip/bloc/main_bloc.dart';
import 'package:bip/ui/widgets/media_preview_card.dart';
import 'package:bip/utils/bip_router.dart';
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
                colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_home_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: localeString.home,
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_bangumi_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_bangumi_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: localeString.bangumi,
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_mine_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_mine_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: localeString.mine,
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
            backgroundColor: colorScheme.surface,
            elevation: 0,
            title: FilledButton.tonal(
              style: FilledButton.styleFrom(
                padding: const EdgeInsets.all(16),
                backgroundColor: colorScheme.surfaceContainerHigh,
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
                        colorScheme.onSurface, BlendMode.srcIn),
                  ),
                  const SizedBox(width: 16),
                  Text(
                    localeString.searchHint,
                    style: TextStyle(
                      fontSize: 16,
                      color: colorScheme.onSurfaceVariant,
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
                          child: mediaPreviewCard(context, previewDetail),
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
    return Column(
      children: [
        StreamBuilder(
            stream: bloc.accounts,
            builder: (context, snapshot) {
              final accounts = snapshot.data;
              if (accounts == null) {
                return const SizedBox.shrink();
              }
              final accountInfo = accounts.entries.map((entry) {
                final MapEntry(key: sourceName, value: userInfo) = entry;
                if (userInfo == null) {
                  return SizedBox(
                    height: 80,
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 16),
                          child: FilledButton(
                            onPressed: () {},
                            style: FilledButton.styleFrom(
                              shape: const CircleBorder(),
                              fixedSize: const Size(60, 60),
                              padding: EdgeInsets.zero,
                            ),
                            child: Text(
                              localeString.login,
                              style: TextStyle(
                                color: colorScheme.onPrimary,
                                fontSize: 16,
                              ),
                            ),
                          ),
                        ),
                        Text(
                          sourceName,
                          style: TextStyle(
                            color: colorScheme.onSurface,
                            fontWeight: FontWeight.w500,
                            fontSize: 18,
                          ),
                        )
                      ],
                    ),
                  );
                } else {
                  return SizedBox(
                    height: 80,
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 16),
                          child: FilledButton(
                            onPressed: () {},
                            style: FilledButton.styleFrom(
                              shape: const CircleBorder(),
                              fixedSize: const Size(60, 60),
                              padding: EdgeInsets.zero,
                            ),
                            child: CircleAvatar(
                              radius: 26,
                              backgroundImage: userInfo.avatar.isNotEmpty
                                  ? NetworkImage(userInfo.avatar)
                                  : const AssetImage(
                                      "assets/img/ic_default_avatar.png",
                                    ),
                            ),
                          ),
                        ),
                        Text(
                          userInfo.name,
                          style: TextStyle(
                            color: colorScheme.onSurface,
                            fontWeight: FontWeight.w500,
                            fontSize: 18,
                          ),
                        )
                      ],
                    ),
                  );
                }
              }).toList();
              return Column(
                mainAxisSize: MainAxisSize.min,
                children: accountInfo,
              );
            }),
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 24),
          child: SizedBox(
            height: 72,
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_download.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.download,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_favorite_outline.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.favorite,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_history_outline.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.history,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_message.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.message,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
        ListTile(
          leading: SvgPicture.asset(
            "assets/img/ic_theme.svg",
            width: 24,
            height: 24,
            colorFilter: ColorFilter.mode(
              colorScheme.tertiary,
              BlendMode.srcIn,
            ),
          ),
          trailing: SvgPicture.asset(
            "assets/img/ic_right_arrow.svg",
            width: 16,
            height: 16,
            colorFilter: ColorFilter.mode(
              colorScheme.onSurface.withOpacity(0.6),
              BlendMode.srcIn,
            ),
          ),
          onTap: () {},
          title: Text(
            localeString.theme,
            style: TextStyle(color: colorScheme.onSurface),
          ),
        ),
        ListTile(
          leading: SvgPicture.asset(
            "assets/img/ic_settings.svg",
            width: 24,
            height: 24,
            colorFilter: ColorFilter.mode(
              colorScheme.tertiary,
              BlendMode.srcIn,
            ),
          ),
          onTap: () {},
          title: Text(
            localeString.settings,
            style: TextStyle(color: colorScheme.onSurface),
          ),
          trailing: SvgPicture.asset(
            "assets/img/ic_right_arrow.svg",
            width: 16,
            height: 16,
            colorFilter: ColorFilter.mode(
              colorScheme.onSurface.withOpacity(0.6),
              BlendMode.srcIn,
            ),
          ),
        ),
        const SizedBox(height: 24),
      ],
    );
  }

  Widget _expandBody() {
    return const Row();
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
