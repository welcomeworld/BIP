import 'package:flutter/material.dart';

class LevelBadge extends StatelessWidget {
  final int level;

  const LevelBadge({super.key, required this.level});

  @override
  Widget build(BuildContext context) {
    if (level == -1) {
      return const SizedBox.shrink();
    }
    // 纯色背景，根据等级动态调整（可选）
    Color backgroundColor = level <= 50 ? Colors.blueAccent : Colors.redAccent;
    const baseFontSize = 8.0;
    return Container(
      width: 22,
      height: 14,
      decoration: BoxDecoration(
        color: backgroundColor, // 纯色背景
        borderRadius: BorderRadius.circular(2), // 圆角矩形
      ),
      child: Center(
        child: RichText(
          text: TextSpan(
            children: [
              const TextSpan(
                text: 'LV',
                style: TextStyle(
                  letterSpacing: 1.1,
                  color: Colors.white, // 基色（会被渐变覆盖）
                  fontSize: baseFontSize, // 前缀字体大小
                  fontWeight: FontWeight.w500,
                ),
              ),
              TextSpan(
                text: '$level',
                style: const TextStyle(
                  letterSpacing: 1.1,
                  color: Colors.white, // 基色（会被渐变覆盖）
                  fontSize: baseFontSize + 3, // 等级数字比前缀大2号
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
