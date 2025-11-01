import 'package:bip/di/get_it.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/model/media_collection.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/collections_bloc.dart';
import '../../gen_auto_import.dart';
import '../../utils/bip_router.dart';
import '../../utils/page_info.dart';
import '../widgets/collection_card.dart';
import '../widgets/collection_create_dialog.dart';

class CollectionsPage extends StatefulWidget {
  const CollectionsPage({super.key});

  @override
  State<CollectionsPage> createState() => _CollectionsPageState();
}

class _CollectionsPageState
    extends BlocState<CollectionsPage, CollectionsBloc> {
  _CollectionsPageState() : super(CollectionsBloc(getIt<CollectionManager>()));

  @override
  Widget build(BuildContext context) {
    return Material(
      child: SafeArea(
        child: RefreshIndicator(
          onRefresh: bloc.refresh,
          child: NotificationListener<ScrollNotification>(
            onNotification: (notification) {
              if (notification is UserScrollNotification) {
                if (notification.direction == ScrollDirection.idle) {
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
                      BipRouter.of(context).maybePop();
                    },
                    padding: EdgeInsets.zero,
                    icon: const Icon(Icons.arrow_back),
                  ),
                  title: Text(localeString.collection),
                  surfaceTintColor: Colors.transparent,
                  backgroundColor: colorScheme.surface,
                  elevation: 0,
                  actions: [
                    Padding(
                      padding: const EdgeInsets.only(right: 8),
                      child: IconButton(
                        icon: Icon(
                          Icons.add,
                          size: 24,
                          color: colorScheme.onSurface,
                        ),
                        onPressed: () {
                          // directly push a dialog to input collection name and visibility
                          showDialog(
                            context: context,
                            builder: (BuildContext context) {
                              return const CollectionCreateDialog();
                            },
                          ).then((value) {
                            if (value != null) {
                              bloc.addCollection(value);
                              bloc.refresh();
                            }
                          });
                        },
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
                            physics: const NeverScrollableScrollPhysics(),
                            shrinkWrap: true,
                            itemCount: list!.length,
                            itemBuilder: (context, index) {
                              var collection = list[index];
                              return Dismissible(
                                key: Key(collection.id.toString()),
                                // 确保每个项有唯一key
                                direction: DismissDirection.endToStart,
                                // 仅允许从左向右滑动
                                confirmDismiss: (direction) async {
                                  // 显示确认对话框
                                  return await _showDeleteDialog(collection);
                                },
                                onDismissed: (direction) {
                                  bloc.deleteCollection(collection);
                                },
                                child: Padding(
                                  padding: const EdgeInsets.only(
                                    left: 0,
                                    right: 0,
                                    top: 4,
                                    bottom: 4,
                                  ),
                                  child: CollectionListItem(
                                    collection: collection,
                                    onTap: () {
                                      BipRouter.of(context).pushPageInfo(
                                        PageInfo(
                                          PageNames.collectionDetail,
                                          extras: {"data": collection},
                                        ),
                                      );
                                    },
                                  ),
                                ),
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
                                  color:
                                      Theme.of(context).colorScheme.onSurface,
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
    );
  }

  Future<bool?> _showDeleteDialog(MediaCollection item) {
    return showDialog<bool>(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(
            '删除收藏夹',
            style: TextStyle(
              fontWeight: FontWeight.w500,
              color: Theme.of(context).colorScheme.onSurface,
            ),
          ),
          content: Text(
            localeString.deleteConfirmation(item.title),
            style: TextStyle(
              color: Theme.of(context).colorScheme.onSurface.withAlpha(178),
            ),
          ),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop(false);
              },
              child: Text(
                localeString.cancel,
                style: TextStyle(
                  color: Theme.of(context).colorScheme.primary,
                ),
              ),
            ),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Theme.of(context).colorScheme.error,
              ),
              onPressed: () {
                Navigator.of(context).pop(true);
              },
              child: Text(localeString.confirm,
                  style: const TextStyle(color: Colors.white)),
            ),
          ],
        );
      },
    );
  }
}
