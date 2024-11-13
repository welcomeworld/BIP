import 'package:flutter/cupertino.dart';

abstract class Bloc {
  void initState(BuildContext context);

  void dispose();
}
