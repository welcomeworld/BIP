import 'package:bip/domain/model/media_collection.dart';
import 'package:bip/domain/model/user_info.dart';
import 'package:bip/ui/widgets/collection_card.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../utils/ui_test_util.dart';

void main() {
  testWidgets("collection card should show given collection info",
      (tester) async {
    final collection = MediaCollection(
        sourceName: "biliTest",
        title: "collection title",
        itemCount: 2,
        owner: UserInfo(sourceName: "", name: "test owner"));
    await UiTestUtil.pumpWithContext(tester,
        child: CollectionListItem(
          collection: collection,
        ));
    final countFinder = find.text("2");
    final ownerFinder = find.text("test owner");
    final titleFinder = find.text("collection title",findRichText: true);

    expect(titleFinder, findsOneWidget);
    expect(countFinder, findsOneWidget);
    expect(ownerFinder, findsOneWidget);
  });
}
