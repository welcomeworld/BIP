import 'package:bip/data/model/media_collection.dart';
import 'package:bip/data/model/user_info.dart';
import 'package:flutter/material.dart';

import '../../l10n/app_locale.dart';

class CollectionCreateDialog extends StatefulWidget {
  const CollectionCreateDialog({super.key});

  @override
  State<CollectionCreateDialog> createState() => _CollectionCreateDialogState();
}

class _CollectionCreateDialogState extends State<CollectionCreateDialog> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _descriptionController = TextEditingController();
  bool _isPublic = true;

  @override
  void dispose() {
    _nameController.dispose();
    _descriptionController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final localeString = AppLocale.of(context)!;
    final theme = Theme.of(context);

    return Dialog(
      elevation: 2, // MD3 建议使用微妙的阴影
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(28), // MD3 标准弹窗圆角
      ),
      backgroundColor: theme.colorScheme.surface, // 使用 MD3 表面颜色
      child: Padding(
        padding: const EdgeInsets.all(24.0), // MD3 推荐更大的内边距
        child: Form(
          key: _formKey,
          child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                localeString.createCollection,
                style: theme.textTheme.headlineSmall?.copyWith(
                  fontWeight: FontWeight.w600, // MD3 标题样式
                  color: theme.colorScheme.onSurface,
                ),
              ),
              const SizedBox(height: 24), // 增加间距以符合 MD3
              TextFormField(
                controller: _nameController,
                decoration: InputDecoration(
                  labelText: localeString.title,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12), // MD3 输入框圆角
                  ),
                  filled: true, // MD3 推荐填充背景
                  fillColor: theme.colorScheme.surfaceContainerHighest, // MD3 容器颜色
                  prefixIcon: Icon(
                    Icons.title,
                    color: theme.colorScheme.onSurfaceVariant,
                  ),
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return localeString.titleHint;
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),
              TextFormField(
                controller: _descriptionController,
                decoration: InputDecoration(
                  labelText: localeString.description,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                  filled: true,
                  fillColor: theme.colorScheme.surfaceContainerHighest,
                  prefixIcon: Icon(
                    Icons.description,
                    color: theme.colorScheme.onSurfaceVariant,
                  ),
                ),
                maxLines: 3,
              ),
              const SizedBox(height: 16),
              Row(
                children: [
                  Icon(
                    Icons.public,
                    size: 20,
                    color: theme.colorScheme.onSurfaceVariant,
                  ),
                  const SizedBox(width: 8),
                  Text(
                    localeString.public,
                    style: theme.textTheme.bodyLarge?.copyWith(
                      color: theme.colorScheme.onSurface,
                    ),
                  ),
                  const Spacer(),
                  Switch(
                    value: _isPublic,
                    onChanged: (value) {
                      setState(() {
                        _isPublic = value;
                      });
                    },
                    activeColor: theme.colorScheme.primary, // MD3 主色
                    inactiveTrackColor: theme.colorScheme.surfaceContainerHighest,
                  ),
                ],
              ),
              const SizedBox(height: 24),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  TextButton(
                    onPressed: () {
                      Navigator.of(context).pop();
                    },
                    style: TextButton.styleFrom(
                      foregroundColor: theme.colorScheme.onSurface, // MD3 按钮颜色
                      padding: const EdgeInsets.symmetric(
                        horizontal: 16,
                        vertical: 8,
                      ),
                    ),
                    child: Text(localeString.cancel),
                  ),
                  const SizedBox(width: 8),
                  FilledButton( // 使用 FilledButton 替换 ElevatedButton，符合 MD3
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        Navigator.of(context).pop(
                          MediaCollection(
                            title: _nameController.text,
                            description: _descriptionController.text,
                            visible: _isPublic,
                            sourceName: '',
                            local: true,
                            createTime: DateTime.now(),
                            owner: UserInfo(name: localeString.localCollection),
                          ),
                        );
                      }
                    },
                    style: FilledButton.styleFrom(
                      padding: const EdgeInsets.symmetric(
                        horizontal: 16,
                        vertical: 8,
                      ),
                      backgroundColor: theme.colorScheme.primary,
                      foregroundColor: theme.colorScheme.onPrimary,
                    ),
                    child: Text(localeString.confirm),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}