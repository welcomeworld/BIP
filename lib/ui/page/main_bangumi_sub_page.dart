import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/main_bangumi_sub_bloc.dart';
import '../../domain/model/index_configuration.dart';
import '../widgets/media_preview_grid_item.dart';

class MainBangumiSubPage extends StatefulWidget {
  const MainBangumiSubPage({super.key});

  @override
  State<MainBangumiSubPage> createState() => _MainBangumiSubPageState();
}

class _MainBangumiSubPageState
    extends BlocState<MainBangumiSubPage, MainBangumiSubBloc> {
  _MainBangumiSubPageState() : super(MainBangumiSubBloc(getIt<MediaManager>()));

  @override
  Widget build(BuildContext context) {
    return RefreshIndicator(
      onRefresh: bloc.refresh,
      child: CustomScrollView(
        controller: bloc.scrollController,
        slivers: [
          SliverToBoxAdapter(
            child: StreamBuilder(
                stream: bloc.configurationSubject,
                builder: (context, snap) {
                  final config = snap.data;
                  if (config == null) return const SizedBox.shrink();
                  return _buildFilters(config);
                }),
          ),
          SliverPadding(
            padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
            sliver: StreamBuilder(
                stream: bloc.listSubject,
                builder: (context, snap) {
                  final list = snap.data ?? [];
                  return SliverGrid(
                    delegate: SliverChildBuilderDelegate((context, index) {
                      return MediaPreviewGridItem(list[index]);
                    }, childCount: list.length),
                    gridDelegate:
                        const SliverGridDelegateWithMaxCrossAxisExtent(
                      maxCrossAxisExtent: 150,
                      mainAxisSpacing: 8,
                      crossAxisSpacing: 8,
                      childAspectRatio: 1 / 1.9,
                    ),
                  );
                }),
          ),
          const SliverToBoxAdapter(
            child: SizedBox(
                height: 56,
                child:
                    Center(child: CircularProgressIndicator(strokeWidth: 2))),
          )
        ],
      ),
    );
  }

  Widget _buildFilters(IndexConfiguration config) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: config.availableCategories.map((cat) {
        final selected = config.getSelectedValueForCategory(cat.paramKey);
        return Padding(
          padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 6),
          child: SizedBox(
            height: 36,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: cat.options.length,
              itemBuilder: (context, idx) {
                final opt = cat.options[idx];
                final isSelected = selected == opt.paramValue;
                return Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 8.0),
                  child: ChoiceChip(
                    label: Text(opt.displayText),
                    selected: isSelected,
                    onSelected: (val) =>
                        bloc.onSelect(cat.paramKey, opt.paramValue),
                    showCheckmark: false,
                  ),
                );
              },
            ),
          ),
        );
      }).toList(),
    );
  }
}
