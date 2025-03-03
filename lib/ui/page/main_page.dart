import 'package:bip/bloc/main_bloc.dart';
import 'package:bip/ui/page/main_home_sub_page.dart';
import 'package:bip/ui/page/main_mine_sub_page.dart';
import 'package:bip/utils/screen_util.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

import '../../bloc/bloc_state.dart';
import 'main_bangumi_sub_page.dart';

class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends BlocState<MainPage, MainBloc> {
  _MainPageState() : super(MainBloc());

  @override
  Widget build(BuildContext context) {
    return ScreenUtil.isExpandDisplay(context) ? _expandBody() : _compactBody();
  }

  Widget _compactBody() {
    return Scaffold(
      body: SafeArea(
        child: IndexedStack(
          index: bloc.tabIndex,
          children: const [
            MainHomeSubPage(),
            MainBangumiSubPage(),
            MainMineSubPage(),
          ],
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: [
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_home_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_home_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: localeString.home,
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_bangumi_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_bangumi_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: localeString.bangumi,
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              "assets/img/ic_mine_outline.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.onSurfaceVariant,
                BlendMode.srcIn,
              ),
            ),
            activeIcon: SvgPicture.asset(
              "assets/img/ic_mine_filled.svg",
              width: 24,
              height: 24,
              colorFilter: ColorFilter.mode(
                colorScheme.primary,
                BlendMode.srcIn,
              ),
            ),
            label: localeString.mine,
          ),
        ],
        elevation: 0,
        currentIndex: bloc.tabIndex,
        onTap: _changeTab,
      ),
    );
  }

  void _changeTab(int newIndex) {
    setState(() {
      bloc.tabIndex = newIndex;
    });
  }

  Widget _expandBody() {
    return const Row();
  }
}
