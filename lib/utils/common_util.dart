import 'dart:convert';

class CommonUtil {
  CommonUtil._();

  static String formatDateShow(DateTime date) {
    var now = DateTime.now();
    var difference = now.difference(date);

    return switch (difference) {
      Duration(inDays: 0, inHours: 0, inMinutes: 0) => '刚刚',
      Duration(inDays: 0, inHours: 0) => '${difference.inMinutes}分钟前',
      Duration(inDays: 0) => '${difference.inHours}小时前',
      Duration(inDays: 1) => '昨天',
      _ => date.year != now.year
          ? '${date.year}年${date.month}月${date.day}日'
          : '${date.month}月${date.day}日',
    };
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

  static String encode64(String source) {
    List<int> bytes = utf8.encode(source);
    return base64Encode(bytes);
  }

  static String decode64(String source) {
    List<int> decodedBytes = base64Decode(source);
    return utf8.decode(decodedBytes);
  }
}
