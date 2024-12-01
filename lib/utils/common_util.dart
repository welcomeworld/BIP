class CommonUtil {
  CommonUtil._();

  static String formatDateShow(DateTime date) {
    var now = DateTime.now();
    var difference = now.difference(date);
    if (difference.inDays == 0) {
      if (difference.inHours == 0) {
        return "${difference.inMinutes}分钟前";
      } else {
        return "${difference.inHours}小时前";
      }
    } else if (difference.inDays == 1) {
      return "昨天";
    } else {
      var year = date.year != now.year ? "${date.year}年" : "";
      return "$year${date.month}月${date.day}日";
    }
  }

  static String formatDurationShow(int seconds) {
    if (seconds <= 0) return "00:00:00";
    int hour = seconds >= 3600 ? seconds ~/ 3600 : 0;
    seconds %= 3600;
    int min = seconds >= 60 ? seconds ~/ 60 : 0;
    int sec = seconds % 60;
    String hourString = hour == 0 ? "" : "${hour.toString().padLeft(2, '0')}:";
    return "$hourString${min.toString().padLeft(2, '0')}:${sec.toString().padLeft(2, '0')}";
  }

  static String formatPlayCount(int count) {
    if (count > 10000) {
      return "${(count / 10000).toStringAsFixed(1).replaceAll(RegExp(r'(\.0+|0+$)'), '')}万";
    }
    return "$count";
  }
}
