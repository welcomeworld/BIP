import 'package:bip/domain/model/user_info.dart';
import 'package:bip/ui/widgets/simple_svg.dart';
import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/main_mine_sub_bloc.dart';
import '../../utils/bip_router.dart';
import '../../utils/page_info.dart';

class MainMineSubPage extends StatefulWidget {
  const MainMineSubPage({super.key});

  @override
  State<MainMineSubPage> createState() => _MainMineSubPageState();
}

class _MainMineSubPageState
    extends BlocState<MainMineSubPage, MainMineSubBloc> {
  _MainMineSubPageState() : super(MainMineSubBloc());

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        StreamBuilder(
            stream: bloc.accounts,
            builder: (context, snapshot) {
              final accounts = snapshot.data;
              if (accounts == null) {
                return const SizedBox.shrink();
              }
              return Column(
                mainAxisSize: MainAxisSize.min,
                children: accounts.entries
                    .map((entry) => _userInfo(entry.key, entry.value))
                    .toList(),
              );
            }),
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 24),
          child: SizedBox(
            height: 72,
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                _topIconButton(
                  "assets/img/ic_download.svg",
                  localeString.download,
                  () {},
                ),
                _topIconButton(
                  "assets/img/ic_favorite_outline.svg",
                  localeString.favorite,
                  () {
                    BipRouter.of(context).push(PageNames.collections);
                  },
                ),
                _topIconButton(
                  "assets/img/ic_history_outline.svg",
                  localeString.history,
                  () {
                    BipRouter.of(context).push(PageNames.history);
                  },
                ),
                _topIconButton(
                  "assets/img/ic_message.svg",
                  localeString.message,
                  () {},
                ),
              ],
            ),
          ),
        ),
        _listTileItem(
          "assets/img/ic_theme.svg",
          localeString.theme,
          () {
            BipRouter.of(context).push(PageNames.themes);
          },
        ),
        _listTileItem(
          "assets/img/ic_settings.svg",
          localeString.settings,
          () {
            BipRouter.of(context).push(PageNames.settings);
          },
        ),
        const SizedBox(height: 24),
      ],
    );
  }

  Widget _topIconButton(
    String iconAsset,
    String label,
    VoidCallback? onPressed,
  ) {
    return IconButton(
      style: IconButton.styleFrom(
          shape:
              RoundedRectangleBorder(borderRadius: BorderRadius.circular(16))),
      onPressed: onPressed,
      icon: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        mainAxisSize: MainAxisSize.min,
        children: [
          SimpleSvg(
            iconAsset,
            size: 30,
            color: colorScheme.secondary,
          ),
          const SizedBox(
            height: 4,
          ),
          Text(
            label,
            style: TextStyle(color: colorScheme.onSurface),
          ),
        ],
      ),
    );
  }

  Widget _userInfo(String sourceName, UserInfo? userInfo) {
    final isLogin = userInfo != null;
    return IconButton(
      onPressed: () {
        if (isLogin) {
          BipRouter.of(context).pushPageInfo(
            PageInfo(
              PageNames.userDetail,
              extras: {"data": userInfo},
            ),
          );
        } else {
          bloc.login(sourceName);
        }
      },
      style: IconButton.styleFrom(
          minimumSize: const Size(0, 80),
          padding: const EdgeInsets.only(left: 16, right: 24)),
      icon: Row(children: [
        isLogin
            ? CircleAvatar(
                radius: 32,
                backgroundImage: NetworkImage(userInfo.avatar),
              )
            : Ink(
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: colorScheme.primary,
                ),
                width: 64,
                height: 64,
                child: Center(
                  child: Text(
                    textAlign: TextAlign.center,
                    localeString.login,
                    style: TextStyle(
                      color: colorScheme.onPrimary,
                      fontSize: 16,
                    ),
                  ),
                ),
              ),
        const SizedBox(width: 16),
        Expanded(
          child: Text(
            isLogin ? userInfo.name : sourceName,
            style: TextStyle(
              color: colorScheme.onSurface,
              fontWeight: FontWeight.w500,
              fontSize: 18,
            ),
          ),
        ),
        SimpleSvg(
          "assets/img/ic_right_arrow.svg",
          size: 16,
          color: colorScheme.onSurface.withOpacity(0.6),
        ),
      ]),
    );
  }

  Widget _listTileItem(
    String iconAsset,
    String label,
    VoidCallback? onPressed,
  ) {
    return ListTile(
      leading: SimpleSvg(
        iconAsset,
        size: 24,
        color: colorScheme.tertiary,
      ),
      onTap: onPressed,
      title: Text(
        label,
        style: TextStyle(color: colorScheme.onSurface),
      ),
      trailing: SimpleSvg(
        "assets/img/ic_right_arrow.svg",
        size: 16,
        color: colorScheme.onSurface.withOpacity(0.6),
      ),
    );
  }
}
