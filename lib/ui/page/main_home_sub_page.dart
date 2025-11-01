import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/main_home_sub_bloc.dart';
import '../../utils/bip_router.dart';
import '../../utils/page_info.dart';
import '../widgets/media_preview_card.dart';
import '../widgets/simple_svg.dart';

class MainHomeSubPage extends StatefulWidget {
  const MainHomeSubPage({super.key});

  @override
  State<MainHomeSubPage> createState() => _MainHomeSubPageState();
}

class _MainHomeSubPageState
    extends BlocState<MainHomeSubPage, MainHomeSubBloc> {
  _MainHomeSubPageState() : super(MainHomeSubBloc(getIt<MediaManager>()));

  @override
  Widget build(BuildContext context) {
    return RefreshIndicator(
      onRefresh: bloc.refresh,
      child: CustomScrollView(
        controller: bloc.scrollController,
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
                  SimpleSvg(
                    "assets/img/ic_search.svg",
                    size: 24,
                    color: colorScheme.onSurface,
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
