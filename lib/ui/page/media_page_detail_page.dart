import 'package:bip/bloc/bloc_state.dart';
import 'package:bip/bloc/media_page_detail_bloc.dart';
import 'package:bip/data/model/media_page_detail.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:media_kit_video/media_kit_video.dart';

import '../../utils/bip_router.dart';
import '../../utils/common_util.dart';
import '../../utils/page_info.dart';
import '../theme/theme_colors.dart';

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
                    child: SingleChildScrollView(
                      child: Column(
                        children: [
                          _ownerCard(pageInfo),
                          _descContent(pageInfo),
                          _relatedList(pageInfo.relatedMediaList),
                        ],
                      ),
                    ),
                  )
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
            return Video(controller: bloc.controller);
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
            color: Theme.of(context).colorScheme.primary,
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
          child: SelectableText(
            pageInfo.title,
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.w600,
              color: Theme.of(context).colorScheme.onSurface,
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
              SvgPicture.asset(
                "assets/img/ic_play_count.svg",
                height: 16,
                width: 16,
                colorFilter: ColorFilter.mode(
                    ThemeColors.onSurfaceLow(context), BlendMode.srcIn),
              ),
              const SizedBox(width: 2),
              Text(
                CommonUtil.formatPlayCount(pageInfo.playCount),
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                  fontSize: 12.0,
                  color: ThemeColors.onSurfaceLow(context),
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
                  color: ThemeColors.onSurfaceLow(context),
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
              color: ThemeColors.onSurfaceLow(context),
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(
            top: 4,
            left: 16,
            right: 16,
          ),
          child: Wrap(
            spacing: 8,
            runSpacing: 8,
            children: pageInfo.tags
                .map(
                  (tag) => DecoratedBox(
                    decoration: BoxDecoration(
                      color: Theme.of(context).colorScheme.secondary,
                      borderRadius: const BorderRadius.all(Radius.circular(2)),
                    ),
                    child: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 4),
                      child: Text(
                        tag,
                        style: TextStyle(
                          color: Theme.of(context).colorScheme.onSecondary,
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
            child: _previewCard(previewDetail),
          );
        },
      );

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
}
