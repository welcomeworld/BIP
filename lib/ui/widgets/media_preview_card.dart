import 'package:bip/ui/widgets/simple_rich_text.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

import '../../data/model/media_page_preview.dart';
import '../../utils/bip_router.dart';
import '../../utils/common_util.dart';
import '../../utils/page_info.dart';

Widget mediaPreviewCard(BuildContext context, MediaPagePreview previewDetail) {
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
        backgroundColor: Theme.of(context).colorScheme.surfaceContainerHighest,
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
                if (previewDetail.duration != 0)
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
                  RichText(
                    text: buildTextSpans(
                      previewDetail.title,
                      context,
                      style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.w500,
                          color:
                              Theme.of(context).colorScheme.onSurfaceVariant),
                    ),
                    maxLines: 3,
                    overflow: TextOverflow.ellipsis,
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
                                Theme.of(context).colorScheme.onSurfaceVariant,
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
