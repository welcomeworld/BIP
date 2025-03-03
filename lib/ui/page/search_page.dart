import 'package:bip/bloc/search_bloc.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../widgets/media_preview_card.dart';
import '../widgets/simple_svg.dart';

class SearchPage extends StatefulWidget {
  const SearchPage({super.key});

  @override
  State<SearchPage> createState() => _SearchPageState();
}

class _SearchPageState extends BlocState<SearchPage, SearchBloc> {
  _SearchPageState() : super(SearchBloc());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            TextField(
              controller: bloc.searchTextController,
              focusNode: bloc.searchFocusNode,
              textInputAction: TextInputAction.search,
              style: TextStyle(
                color: colorScheme.onSurface,
                fontWeight: FontWeight.w500,
                fontSize: 17,
              ),
              decoration: InputDecoration(
                contentPadding:
                    const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                prefixIcon: IconButton(
                  onPressed: () {
                    BipRouter.of(context).maybePop();
                  },
                  padding: EdgeInsets.zero,
                  icon: const Icon(Icons.arrow_back),
                ),
                hintText: localeString.searchHint,
                suffixIcon: IconButton(
                  onPressed: bloc.onClearSearch,
                  padding: EdgeInsets.zero,
                  icon: const Icon(Icons.close),
                ),
              ),
              onSubmitted: bloc.onSearch,
              autofocus: true,
            ),
            StreamBuilder(
                stream: bloc.showResultSubject,
                builder: (context, snap) {
                  return snap.data == true
                      ? _searchResultPage()
                      : _searchStartPage();
                })
          ],
        ),
      ),
    );
  }

  Widget _searchStartPage() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisSize: MainAxisSize.min,
      children: [
        StreamBuilder(
            stream: bloc.searchHistorySubject,
            builder: (context, snapshot) {
              var historyData = snapshot.data ?? [];
              if (historyData.isEmpty) {
                return const SizedBox.shrink();
              }
              return Column(
                mainAxisSize: MainAxisSize.min,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(
                          top: 24,
                          bottom: 8.0,
                          left: 16,
                        ),
                        child: Text(
                          localeString.searchHistory,
                          style: TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.w600,
                            color: colorScheme.onSurface,
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(right: 6),
                        child: IconButton(
                          style: IconButton.styleFrom(
                            padding: const EdgeInsets.all(10),
                            fixedSize: const Size(32, 32),
                            tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                          ),
                          onPressed: bloc.onClearHistory,
                          icon: SimpleSvg(
                            "assets/img/ic_delete.svg",
                            size: 24,
                            color: colorScheme.onSurface,
                          ),
                        ),
                      )
                    ],
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 16,
                      vertical: 8,
                    ),
                    child: Wrap(
                      runSpacing: 8,
                      spacing: 16,
                      children: [
                        for (var item in historyData)
                          FilledButton(
                            onPressed: () =>
                                bloc.onSearchHistory(item.searchKey),
                            style: FilledButton.styleFrom(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 8),
                              backgroundColor: colorScheme.surfaceContainerHigh,
                              overlayColor: colorScheme.onSurfaceVariant,
                              minimumSize: const Size(0, 36),
                              tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(8),
                              ),
                            ),
                            child: Text(
                              item.searchKey,
                              style: TextStyle(
                                color: colorScheme.onSurfaceVariant,
                                fontSize: 14,
                                overflow: TextOverflow.ellipsis,
                              ),
                              maxLines: 1,
                            ),
                          ),
                      ],
                    ),
                  ),
                ],
              );
            }),
        StreamBuilder(
            stream: bloc.searchHotSubject.stream,
            builder: (context, snapshot) {
              var hotData = snapshot.data ?? [];
              if (hotData.isEmpty) {
                return const SizedBox.shrink();
              }
              return Column(
                mainAxisSize: MainAxisSize.min,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(
                      top: 24,
                      bottom: 8.0,
                      left: 16,
                    ),
                    child: Text(
                      localeString.searchHot,
                      style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.w600,
                        color: colorScheme.onSurface,
                      ),
                    ),
                  ),
                  GridView.builder(
                    shrinkWrap: true,
                    gridDelegate:
                        const SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 2, // 每行显示的项目数
                      mainAxisSpacing: 4,
                      mainAxisExtent: 40,
                    ),
                    itemCount: hotData.length,
                    itemBuilder: (context, index) {
                      return TextButton(
                        onPressed: () => bloc.onSearchHot(hotData[index]),
                        style: TextButton.styleFrom(
                          alignment: Alignment.centerLeft,
                        ),
                        child: Text(
                          hotData[index],
                          maxLines: 1,
                          softWrap: true,
                          overflow: TextOverflow.ellipsis,
                          style: TextStyle(
                            color: colorScheme.onSurface,
                            fontSize: 16,
                          ),
                        ),
                      );
                    },
                  ),
                ],
              );
            }),
      ],
    );
  }

  Widget _searchResultPage() {
    return PopScope(
      canPop: false,
      onPopInvoked: (hasInvoked) {
        if (hasInvoked) {
          return;
        }
        bloc.showResultSubject.add(false);
      },
      child: Expanded(
        child: StreamBuilder(
            stream: bloc.searchResultSubject,
            builder: (context, snap) {
              var searchResultList = snap.data ?? [];
              return RefreshIndicator(
                onRefresh: bloc.onRefresh,
                child: searchResultList.isNotEmpty
                    ? ListView.builder(
                        controller: bloc.scrollController,
                        itemCount: searchResultList.length + 1,
                        itemBuilder: (context, index) {
                          if (index == searchResultList.length) {
                            return const SizedBox(
                              height: 56,
                              child: Center(
                                child: CircularProgressIndicator(
                                  strokeWidth: 2,
                                ),
                              ),
                            );
                          }
                          var previewDetail = searchResultList[index];
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
                      )
                    : const SizedBox.expand(),
              );
            }),
      ),
    );
  }
}
