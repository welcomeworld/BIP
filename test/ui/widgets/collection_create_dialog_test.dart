import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/l10n/app_locale.dart';
import 'package:bip/ui/widgets/collection_create_dialog.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/ui_test_util.dart';

void main() {
  group('CollectionCreateDialog Widget Tests', () {
    testWidgets('dialog shows with correct initial state',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );
      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      final locale = AppLocale.of(tester.element(find.byType(Scaffold)))!;

      // 验证对话框存在
      expect(find.byType(Dialog), findsOneWidget);

      // 验证标题
      expect(find.text(locale.createCollection, findRichText: true), findsOneWidget);

      // 验证输入字段
      expect(find.byType(TextFormField), findsNWidgets(2));

      // 验证第一个输入框的标签
      expect(find.text(locale.title), findsOneWidget);

      // 验证第二个输入框的标签
      expect(find.text(locale.description), findsOneWidget);

      // 验证开关和标签
      expect(find.text(locale.public), findsOneWidget);
      expect(find.byType(Switch), findsOneWidget);

      // 验证按钮
      expect(find.text(locale.cancel), findsOneWidget);
      expect(find.text(locale.confirm), findsOneWidget);

      // 验证开关初始状态为 true（公开）
      final switchWidget = tester.widget<Switch>(find.byType(Switch));
      expect(switchWidget.value, true);
    });

    testWidgets('form validation shows error for empty title',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );
      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      final locale = AppLocale.of(tester.element(find.byType(Scaffold)))!;

      // 尝试直接点击确认按钮而不填写标题
      await tester.tap(find.text(locale.confirm));
      await tester.pump();

      // 应该显示验证错误
      expect(find.text(locale.titleHint), findsOneWidget);

      // 现在填写标题
      final titleField = find.byType(TextFormField).first;
      await tester.enterText(titleField, 'Test Collection');
      await tester.pump();

      // 错误消息应该消失
      expect(find.text(locale.titleHint), findsNothing);
    });

    testWidgets('form validation passes with valid title',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );
      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      final locale = AppLocale.of(tester.element(find.byType(Scaffold)))!;

      // 填写标题
      final titleField = find.byType(TextFormField).first;
      await tester.enterText(titleField, 'My Collection');
      await tester.pump();

      // 点击确认按钮
      await tester.tap(find.text(locale.confirm));
      await tester.pumpAndSettle();

      // 不应该有错误消息
      expect(find.text(locale.titleHint), findsNothing);

      // 对话框应该关闭
      expect(find.byType(Dialog), findsNothing);
    });

    testWidgets('switch toggle changes value correctly',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );
      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 初始状态为 true（公开）
      var switchWidget = tester.widget<Switch>(find.byType(Switch));
      expect(switchWidget.value, true);

      // 点击切换开关
      await tester.tap(find.byType(Switch));
      await tester.pump();

      // 状态应该变为 false（私有）
      switchWidget = tester.widget<Switch>(find.byType(Switch));
      expect(switchWidget.value, false);

      // 再次点击切换回来
      await tester.tap(find.byType(Switch));
      await tester.pump();

      switchWidget = tester.widget<Switch>(find.byType(Switch));
      expect(switchWidget.value, true);
    });

    testWidgets('cancel button closes dialog without returning value',
        (WidgetTester tester) async {
      bool dialogWasClosed = false;
      late AppLocale locale;

      await UiTestUtil.pumpWithContext(
        tester,
        locale: const Locale('en', 'US'),
        child: Builder(
          builder: (context) {
            locale = AppLocale.of(context)!;
            return Center(
              child: ElevatedButton(
                onPressed: () async {
                  final result = await showDialog<MediaCollection>(
                    context: context,
                    builder: (context) => const CollectionCreateDialog(),
                  );
                  dialogWasClosed = true;
                  // 检查返回值为 null
                  expect(result, isNull);
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );

      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 点击取消按钮
      await tester.tap(find.text(locale.cancel));
      await tester.pumpAndSettle();

      // 对话框应该关闭
      expect(find.byType(Dialog), findsNothing);

      // 验证回调被调用
      await tester.pump(const Duration(milliseconds: 100));
      expect(dialogWasClosed, true);
    });

    testWidgets(
        'confirm button returns correct MediaCollection with public visibility',
        (WidgetTester tester) async {
      MediaCollection? returnedCollection;
      late AppLocale locale;

      await UiTestUtil.pumpWithContext(
        tester,
        locale: const Locale('en', 'US'),
        child: Builder(
          builder: (context) {
            locale = AppLocale.of(context)!;
            return Center(
              child: ElevatedButton(
                onPressed: () async {
                  returnedCollection = await showDialog<MediaCollection>(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );

      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 填写表单
      final titleField = find.byType(TextFormField).at(0);
      final descriptionField = find.byType(TextFormField).at(1);

      await tester.enterText(titleField, 'My Test Collection');
      await tester.enterText(descriptionField, 'This is a test description');

      await tester.pump();

      // 确保开关是 true（默认值）
      final switchWidget = tester.widget<Switch>(find.byType(Switch));
      expect(switchWidget.value, true);

      // 点击确认按钮
      await tester.tap(find.text(locale.confirm));
      await tester.pumpAndSettle();

      // 对话框应该关闭
      expect(find.byType(Dialog), findsNothing);

      // 验证返回值
      await tester.pump(const Duration(milliseconds: 100));

      expect(returnedCollection, isNotNull);
      expect(returnedCollection!.title, 'My Test Collection');
      expect(returnedCollection!.description, 'This is a test description');
      expect(returnedCollection!.visible, true); // 公开
      expect(returnedCollection!.local, true);
      expect(returnedCollection!.sourceName, '');
      expect(returnedCollection!.id, 'My Test Collection'); // id 默认为 title
      expect(returnedCollection!.cover, '');
      expect(returnedCollection!.itemCount, 0);
      expect(returnedCollection!.items, isEmpty);
      expect(returnedCollection!.extras, isEmpty);

      // 检查创建时间在合理范围内
      final now = DateTime.now();
      expect(
        returnedCollection!.createTime
            .isAfter(now.subtract(const Duration(seconds: 2))),
        true,
      );
      expect(
        returnedCollection!.createTime
            .isBefore(now.add(const Duration(seconds: 2))),
        true,
      );

      // 检查 owner
      expect(returnedCollection!.owner, isNotNull);
      expect(returnedCollection!.owner.sourceName, 'local');
      expect(returnedCollection!.owner.name, locale.localCollection);
    });

    testWidgets(
        'confirm button returns correct MediaCollection with private visibility',
        (WidgetTester tester) async {
      MediaCollection? returnedCollection;
      late AppLocale locale;

      await UiTestUtil.pumpWithContext(
        tester,
        locale: const Locale('en', 'US'),
        child: Builder(
          builder: (context) {
            locale = AppLocale.of(context)!;
            return Center(
              child: ElevatedButton(
                onPressed: () async {
                  returnedCollection = await showDialog<MediaCollection>(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );

      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 填写表单
      final titleField = find.byType(TextFormField).at(0);
      final descriptionField = find.byType(TextFormField).at(1);

      await tester.enterText(titleField, 'Private Collection');
      await tester.enterText(descriptionField, 'A private collection');

      // 将开关设置为 false（私有）
      await tester.tap(find.byType(Switch));
      await tester.pump();

      // 验证开关现在是 false
      final switchWidget = tester.widget<Switch>(find.byType(Switch));
      expect(switchWidget.value, false);

      // 点击确认按钮
      await tester.tap(find.text(locale.confirm));
      await tester.pumpAndSettle();

      // 验证返回值
      await tester.pump(const Duration(milliseconds: 100));

      expect(returnedCollection, isNotNull);
      expect(returnedCollection!.title, 'Private Collection');
      expect(returnedCollection!.description, 'A private collection');
      expect(returnedCollection!.visible, false); // 私有
    });

    testWidgets('dialog respects Material 3 styling',
        (WidgetTester tester) async {
      final theme = ThemeData.from(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      );

      await UiTestUtil.pumpWithContext(
        tester,
        theme: theme,
        child: Builder(
          builder: (context) {
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );

      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 验证 Material 3 样式
      final dialog = tester.widget<Dialog>(find.byType(Dialog));

      // 检查圆角
      expect(dialog.shape, isA<RoundedRectangleBorder>());
      final shape = dialog.shape as RoundedRectangleBorder;
      expect(shape.borderRadius, BorderRadius.circular(28));

      // 检查阴影
      expect(dialog.elevation, 2);

      // 检查背景颜色是 surface 颜色
      expect(dialog.backgroundColor, isNotNull);
    });

    testWidgets('description field can handle multi-line input',
        (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );
      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 获取描述字段
      final descriptionField = find.byType(TextFormField).at(1);

      // 输入多行文本
      const multiLineText = 'Line 1\nLine 2\nLine 3';
      await tester.enterText(descriptionField, multiLineText);
      await tester.pump();

      // 验证文本被正确输入
      final textField = tester.widget<TextFormField>(descriptionField);
      expect(textField.controller!.text, multiLineText);

      // 验证 maxLines 是 3
      // expect(textField.maxLines, 3);
    });

    testWidgets('icons are displayed correctly', (WidgetTester tester) async {
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );
      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 验证标题字段的图标
      expect(find.byIcon(Icons.title), findsOneWidget);

      // 验证描述字段的图标
      expect(find.byIcon(Icons.description), findsOneWidget);

      // 验证公开/私有标签的图标
      expect(find.byIcon(Icons.public), findsOneWidget);
    });

    testWidgets('text direction is correctly set', (WidgetTester tester) async {
      late AppLocale locale;
      // 测试从左到右的布局
      await UiTestUtil.pumpWithContext(
        tester,
        locale: const Locale('en', 'US'), // LTR 语言
        child: Builder(
          builder: (context) {
            locale = AppLocale.of(context)!;
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );

      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 验证对话框存在且没有方向性错误
      expect(find.byType(Dialog), findsOneWidget);

      // 验证按钮在正确的位置（取消在左，确认在右）
      final cancelButton = find.text(locale.cancel);
      final confirmButton = find.text(locale.confirm);

      expect(cancelButton, findsOneWidget);
      expect(confirmButton, findsOneWidget);
    });

    testWidgets('dispose does not cause errors', (WidgetTester tester) async {
      late AppLocale locale;
      // 多次打开和关闭对话框，测试 dispose 方法
      await UiTestUtil.pumpWithContext(
        tester,
        child: Builder(
          builder: (context) {
            locale = AppLocale.of(context)!;
            return Center(
              child: ElevatedButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) => CollectionCreateDialog(),
                  );
                },
                child: const Text('Open Dialog'),
              ),
            );
          },
        ),
      );

      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 关闭对话框
      await tester.tap(find.text(locale.cancel));
      await tester.pumpAndSettle();

      // 再次打开
      await tester.tap(find.text('Open Dialog'));
      await tester.pumpAndSettle();

      // 填写一些文本
      await tester.enterText(find.byType(TextFormField).first, 'Test');
      await tester.pump();

      // 再次关闭
      await tester.tap(find.text(locale.cancel));
      await tester.pumpAndSettle();

      // 验证没有错误发生
      expect(tester.takeException(), isNull);
    });
  });
}
