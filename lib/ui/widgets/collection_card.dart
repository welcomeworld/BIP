import 'package:bip/ui/widgets/broken_image.dart';
import 'package:bip/ui/widgets/simple_rich_text.dart';
import 'package:flutter/material.dart';

import '../../domain/model/media_collection.dart';

class CollectionListItem extends StatelessWidget {
  final MediaCollection collection;
  final VoidCallback? onTap;

  const CollectionListItem({
    super.key,
    required this.collection,
    this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    final colorScheme = Theme.of(context).colorScheme;
    return AspectRatio(
      aspectRatio: 3.5,
      child: FilledButton.tonal(
        onPressed: () {
          if (onTap != null) {
            onTap!();
            return;
          }
        },
        style: FilledButton.styleFrom(
          padding: EdgeInsets.zero,
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(1)),
          elevation: 0,
          backgroundColor: Colors.transparent,
        ),
        child: Row(
          children: [
            Expanded(
              flex: 6,
              child: Stack(
                children: [
                  Positioned(
                    top: 4,
                    left: 40,
                    right: 32,
                    child: SizedBox(
                      height: 6,
                      width: double.infinity,
                      child: Container(
                        decoration: const BoxDecoration(
                          color: Colors.black12,
                          borderRadius: BorderRadius.only(
                              topLeft: Radius.circular(8),
                              topRight: Radius.circular(8)),
                        ),
                      ),
                    ),
                  ),
                  Positioned(
                    top: 10,
                    left: 28,
                    right: 20,
                    child: SizedBox(
                      height: 6,
                      width: double.infinity,
                      child: Container(
                        decoration: const BoxDecoration(
                          color: Colors.black26,
                          borderRadius: BorderRadius.only(
                              topLeft: Radius.circular(8),
                              topRight: Radius.circular(8)),
                        ),
                      ),
                    ),
                  ),
                  SizedBox.expand(
                    child: Padding(
                      padding: const EdgeInsets.only(
                          left: 16, right: 8, top: 16, bottom: 16),
                      child: ClipRRect(
                        borderRadius: const BorderRadius.all(
                          Radius.circular(8),
                        ),
                        child: Image.network(
                          collection.cover,
                          fit: BoxFit.cover,
                          loadingBuilder: (context, child, progress) {
                            if (progress == null) {
                              return child;
                            }
                            return Container(
                              decoration:
                                  const BoxDecoration(color: Colors.black38),
                              child: const Center(
                                child: Icon(
                                  Icons.collections,
                                  color: Colors.black45,
                                  size: 32,
                                ),
                              ),
                            );
                          },
                          errorBuilder: (context, error, trace) =>
                              brokenImage(32),
                        ),
                      ),
                    ),
                  ),
                  Positioned(
                    bottom: 20,
                    right: 12,
                    child: Container(
                      padding: const EdgeInsets.symmetric(
                          horizontal: 6, vertical: 2),
                      decoration: BoxDecoration(
                        color: Colors.black54,
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Text(
                        '${collection.itemCount}',
                        style: const TextStyle(
                          color: Colors.white,
                          fontSize: 12,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Expanded(
              flex: 7,
              child: Padding(
                padding:
                    const EdgeInsets.symmetric(horizontal: 4, vertical: 16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    RichText(
                      text: buildTextSpans(
                        collection.title,
                        context,
                        style: TextStyle(
                          fontSize: 16,
                          fontWeight: FontWeight.w500,
                          color: colorScheme.onSurface,
                        ),
                      ),
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                    ),
                    Column(
                      children: [
                        Row(
                          children: [
                            const Icon(Icons.person_outline,
                                size: 14, color: Colors.grey),
                            const SizedBox(width: 4),
                            Text(
                              collection.owner.name,
                              style: const TextStyle(
                                  fontSize: 13, color: Colors.grey),
                            ),
                          ],
                        ),
                        Row(
                          children: [
                            Icon(_getVisibilityIcon(collection.visible),
                                size: 14, color: Colors.grey),
                            const SizedBox(width: 4),
                            Text(
                              _getVisibilityText(collection.visible),
                              style: const TextStyle(
                                  fontSize: 13, color: Colors.grey),
                            ),
                            const Spacer(),
                            if (!collection.local)
                              Container(
                                padding: const EdgeInsets.symmetric(
                                    horizontal: 4, vertical: 2),
                                decoration: BoxDecoration(
                                  color: Colors.blue[50],
                                  borderRadius: BorderRadius.circular(4),
                                ),
                                child: Text(
                                  collection.sourceName,
                                  style: TextStyle(
                                      fontSize: 10, color: Colors.blue[700]),
                                ),
                              ),
                            const SizedBox(width: 8),
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

  IconData _getVisibilityIcon(bool visible) {
    return visible ? Icons.public : Icons.lock;
  }

  String _getVisibilityText(bool visible) {
    return visible ? '公开' : '私密';
  }
}
