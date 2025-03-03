import 'package:flutter/cupertino.dart';
import 'package:flutter_svg/svg.dart';

class SimpleSvg extends StatelessWidget {
  const SimpleSvg(
    this.assetName, {
    super.key,
    required this.color,
    this.size = 24,
  });

  final Color color;
  final double size;
  final String assetName;

  @override
  Widget build(BuildContext context) {
    return SvgPicture.asset(
      assetName,
      width: size,
      height: size,
      colorFilter: ColorFilter.mode(
        color,
        BlendMode.srcIn,
      ),
    );
  }
}
