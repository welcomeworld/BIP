import 'package:bip/bloc/bloc_state.dart';
import 'package:bip/bloc/media_page_detail_bloc.dart';
import 'package:bip/data/model/media_page_detail.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/ui/page/media_page_detail_reply_page.dart';
import 'package:bip/ui/widgets/media_preview_card.dart';
import 'package:bip/ui/widgets/simple_rich_text.dart';
import 'package:bip/utils/bip_navigator.dart';
import 'package:flutter/material.dart';

import '../../data/model/media_info.dart';
import '../../player/bip_video.dart';
import '../../utils/bip_router.dart';
import '../../utils/common_util.dart';
import '../../utils/page_info.dart';
import '../theme/theme_colors.dart';
import '../widgets/simple_svg.dart';
import '../widgets/top_icon_button.dart';

class MediaPageDetailPage extends StatefulWidget {
  const MediaPageDetailPage(this.preview, {super.key});

  final MediaPagePreview preview;

  @override
  State<MediaPageDetailPage> createState() => _MediaPageDetailPageState();
}

class _MediaPageDetailPageState
    extends BlocState<MediaPageDetailPage, MediaPageDetailBloc> {
  _MediaPageDetailPageState() : super(MediaPageDetailBloc());

  @override
  void initState() {
    super.initState();
    bloc.setPreview(widget.preview);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: StreamBuilder<MediaPageDetail>(
            stream: bloc.detailSubject.stream,
            builder: (context, snapshot) {
              var pageInfo = snapshot.data;
              if (pageInfo == null) {
                return const SizedBox.shrink();
              }
              return Column(
                children: [
                  _mediaView(pageInfo.cover),
                  Expanded(
                    child: StreamBuilder<bool>(
                      stream: bloc.showingReply.stream,
                      builder: (context, snapshot) {
                        var showingReply = snapshot.data ?? false;
                        return BipNavigator(
                          canPop: !showingReply,
                          onPopInvoked: (result) {
                            if (result) {
                              return;
                            }
                            bloc.showingReply.add(false);
                          },
                          child: Stack(
                            children: [
                              SingleChildScrollView(
                                child: Column(
                                  children: [
                                    _ownerCard(pageInfo),
                                    _descContent(pageInfo),
                                    _actionBar(pageInfo),
                                    _mediaPlayLists(pageInfo.playlists),
                                    _mediaPlayLists(pageInfo.additionPlaylists),
                                    _relatedList(pageInfo.relatedMediaList),
                                  ],
                                ),
                              ),
                              Offstage(
                                offstage: !showingReply,
                                child: MediaPageDetailReplyPage(pageInfo),
                              ),
                            ],
                          ),
                        );
                      },
                    ),
                  ),
                ],
              );
            }),
      ),
    );
  }

  Widget _mediaView(String cover) {
    return AspectRatio(
      aspectRatio: 16 / 9,
      child: StreamBuilder(
          stream: bloc.mediaInfoSubject.stream,
          builder: (context, snapshot) {
            if (snapshot.data == null) {
              return Image.network(
                cover,
                fit: BoxFit.cover,
              );
            }
            return BipVideo(controller: bloc.controller);
          }),
    );
  }

  Widget _ownerCard(MediaPageDetail pageInfo) {
    return Row(
      children: [
        Padding(
          padding: const EdgeInsets.only(
            left: 16,
            right: 8,
            top: 8,
            bottom: 8,
          ),
          child: CircleAvatar(
            radius: 26,
            backgroundImage: pageInfo.owner.avatar.isNotEmpty
                ? NetworkImage(
                    pageInfo.owner.avatar,
                  )
                : const AssetImage(
                    "assets/img/ic_default_avatar.png",
                  ),
          ),
        ),
        Text(
          pageInfo.owner.name,
          style: TextStyle(
            fontSize: 16,
            fontWeight: FontWeight.w500,
            color: colorScheme.primary,
          ),
        ),
      ],
    );
  }

  Widget _descContent(MediaPageDetail pageInfo) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: const EdgeInsets.only(
            left: 16,
            right: 16,
            top: 8,
            bottom: 8,
          ),
          child: SelectableText.rich(
            buildTextSpans(pageInfo.title, context),
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.w600,
              color: colorScheme.onSurface,
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(
            left: 16,
            right: 16,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              SimpleSvg(
                "assets/img/ic_play_count.svg",
                size: 16,
                color: colorScheme.onSurfaceLow,
              ),
              const SizedBox(width: 2),
              Text(
                CommonUtil.formatPlayCount(pageInfo.playCount),
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                  fontSize: 12.0,
                  color: colorScheme.onSurfaceLow,
                ),
              ),
              const SizedBox(
                width: 8,
              ),
              Text(
                CommonUtil.formatDateShow(
                  DateTime.fromMillisecondsSinceEpoch(
                    pageInfo.pageTime,
                  ),
                ),
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                  fontSize: 12.0,
                  color: colorScheme.onSurfaceLow,
                ),
              ),
            ],
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(
            top: 4,
            left: 16,
            right: 16,
          ),
          child: SelectableText(
            pageInfo.desc,
            style: TextStyle(
              fontSize: 14,
              fontWeight: FontWeight.w400,
              color: colorScheme.onSurfaceLow,
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(
            top: 4,
            left: 16,
            right: 16,
            bottom: 8,
          ),
          child: Wrap(
            spacing: 8,
            runSpacing: 8,
            children: pageInfo.tags
                .map(
                  (tag) => DecoratedBox(
                    decoration: BoxDecoration(
                      color: colorScheme.secondary,
                      borderRadius: const BorderRadius.all(Radius.circular(2)),
                    ),
                    child: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 4),
                      child: Text(
                        tag,
                        style: TextStyle(
                          color: colorScheme.onSecondary,
                        ),
                      ),
                    ),
                  ),
                )
                .toList(),
          ),
        ),
      ],
    );
  }

  Widget _actionBar(MediaPageDetail pageInfo) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
      child: SizedBox(
        height: 72,
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            topIconButton(
              SimpleSvg(
                "assets/img/ic_appreciate_filled.svg",
                size: 30,
                color: colorScheme.tertiary,
              ),
              Text(
                localeString.appreciate,
                style: TextStyle(color: colorScheme.onSurface),
              ),
              IconButton.styleFrom(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(16)),
                minimumSize: const Size(72, 72),
              ),
              () {},
            ),
            topIconButton(
              SimpleSvg(
                "assets/img/ic_favorite_filled.svg",
                size: 30,
                color: colorScheme.tertiary,
              ),
              Text(
                localeString.favorite,
                style: TextStyle(color: colorScheme.onSurface),
              ),
              IconButton.styleFrom(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(16)),
                minimumSize: const Size(72, 72),
              ),
              () {},
            ),
            topIconButton(
              SimpleSvg(
                "assets/img/ic_coin_filled.svg",
                size: 30,
                color: colorScheme.tertiary,
              ),
              Text(
                localeString.coin,
                style: TextStyle(color: colorScheme.onSurface),
              ),
              IconButton.styleFrom(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(16)),
                minimumSize: const Size(72, 72),
              ),
              () {},
            ),
            topIconButton(
              SimpleSvg(
                "assets/img/ic_download_filled.svg",
                size: 30,
                color: colorScheme.tertiary,
              ),
              Text(
                localeString.download2,
                style: TextStyle(color: colorScheme.onSurface),
              ),
              IconButton.styleFrom(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(16)),
                minimumSize: const Size(72, 72),
              ),
              () {},
            ),
            topIconButton(
              SimpleSvg(
                "assets/img/ic_comment_filled.svg",
                size: 30,
                color: colorScheme.tertiary,
              ),
              Text(
                localeString.comment,
                style: TextStyle(color: colorScheme.onSurface),
              ),
              IconButton.styleFrom(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(16)),
                minimumSize: const Size(72, 72),
              ),
              () {
                bloc.showingReply.add(true);
              },
            ),
          ],
        ),
      ),
    );
  }

  Widget _mediaPlayLists(Map<String, List<MediaInfo>> playlists) {
    if (playlists.isEmpty ||
        (playlists.length == 1 && playlists.values.first.length == 1)) {
      return const SizedBox.shrink();
    }
    return StreamBuilder(
        stream: bloc.mediaInfoSubject.stream,
        builder: (context, snapshot) {
          final selectedMedia = snapshot.data;
          final playlistWidgets = playlists.entries.map((entry) {
            var MapEntry(key: key, value: value) = entry;
            return Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.only(left: 16, top: 8),
                  child: Text(
                    key,
                    style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.w600,
                      color: colorScheme.onSurface,
                    ),
                  ),
                ),
                SizedBox(
                  height: 72,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    padding: const EdgeInsets.only(
                      left: 8,
                      right: 8,
                      top: 8,
                      bottom: 8,
                    ),
                    itemCount: value.length,
                    itemBuilder: (context, index) {
                      var mediaInfo = value[index];
                      final isSelected = mediaInfo == selectedMedia;
                      return Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 8),
                        child: FilledButton(
                          onPressed: () => bloc.onMediaInfoClick(mediaInfo),
                          style: FilledButton.styleFrom(
                            padding: const EdgeInsets.symmetric(
                              horizontal: 8,
                            ),
                            backgroundColor: colorScheme.surfaceContainerLow,
                            overlayColor: colorScheme.onSurfaceVariant,
                            fixedSize: const Size(120, 56),
                            tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(8),
                            ),
                          ),
                          child: Text(
                            mediaInfo.title,
                            style: TextStyle(
                              color: isSelected
                                  ? colorScheme.primary
                                  : colorScheme.onSurfaceVariant,
                              fontSize: 13,
                              fontWeight: isSelected
                                  ? FontWeight.w500
                                  : FontWeight.normal,
                              overflow: TextOverflow.ellipsis,
                            ),
                            maxLines: 2,
                          ),
                        ),
                      );
                    },
                  ),
                ),
              ],
            );
          }).toList();
          return Column(
            mainAxisSize: MainAxisSize.min,
            children: playlistWidgets,
          );
        });
  }

  Widget _relatedList(List<MediaPagePreview> previewList) => ListView.builder(
        padding: EdgeInsets.zero,
        shrinkWrap: true,
        physics: const NeverScrollableScrollPhysics(),
        itemCount: previewList.length,
        itemBuilder: (context, index) {
          var previewDetail = previewList[index];
          return Padding(
            padding: const EdgeInsets.only(
              left: 16,
              right: 16,
              top: 8,
              bottom: 8,
            ),
            child: mediaPreviewCard(context, previewDetail,
                onPressed: (preview) async {
              bloc.controller.player.pause();
              await BipRouter.rootRouter.pushPageInfo(
                PageInfo(
                  PageNames.mediaPageDetail,
                  extras: {"data": previewDetail},
                ),
              );
              bloc.controller.player.play();
            }),
          );
        },
      );
}
