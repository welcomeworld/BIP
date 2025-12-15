import 'package:bip/bloc/main_bloc.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  group('MainBloc', () {
    late MainBloc mainBloc;

    setUp(() {
      mainBloc = MainBloc();
    });

    test('initial tabIndex is 0', () {
      expect(mainBloc.tabIndex, 0);
    });
  });
}
