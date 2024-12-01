import 'package:bip/utils/constant.dart';
import 'package:flutter/cupertino.dart';

class ScreenUtil {
  ScreenUtil._();

  static bool isExpandDisplay(BuildContext context) {
    return getScreenWidth(context) >= Constant.maxMediumSize;
  }

  static int getScreenWidth(BuildContext context) {
    return MediaQuery.sizeOf(context).width.toInt();
  }

  static int getHomeListColumn(BuildContext context) {
    if (getScreenWidth(context) < Constant.maxCompactSize) {
      return 1;
    } else if (isExpandDisplay(context)) {
      return 3;
    } else {
      return 2;
    }
  }
}
