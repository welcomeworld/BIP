import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/main_mine_sub_bloc.dart';

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
              final accountInfo = accounts.entries.map((entry) {
                final MapEntry(key: sourceName, value: userInfo) = entry;
                if (userInfo == null) {
                  return SizedBox(
                    height: 80,
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 16),
                          child: FilledButton(
                            onPressed: () {},
                            style: FilledButton.styleFrom(
                              shape: const CircleBorder(),
                              fixedSize: const Size(60, 60),
                              padding: EdgeInsets.zero,
                            ),
                            child: Text(
                              localeString.login,
                              style: TextStyle(
                                color: colorScheme.onPrimary,
                                fontSize: 16,
                              ),
                            ),
                          ),
                        ),
                        Text(
                          sourceName,
                          style: TextStyle(
                            color: colorScheme.onSurface,
                            fontWeight: FontWeight.w500,
                            fontSize: 18,
                          ),
                        )
                      ],
                    ),
                  );
                } else {
                  return SizedBox(
                    height: 80,
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 16),
                          child: FilledButton(
                            onPressed: () {},
                            style: FilledButton.styleFrom(
                              shape: const CircleBorder(),
                              fixedSize: const Size(60, 60),
                              padding: EdgeInsets.zero,
                            ),
                            child: CircleAvatar(
                              radius: 26,
                              backgroundImage: userInfo.avatar.isNotEmpty
                                  ? NetworkImage(userInfo.avatar)
                                  : const AssetImage(
                                "assets/img/ic_default_avatar.png",
                              ),
                            ),
                          ),
                        ),
                        Text(
                          userInfo.name,
                          style: TextStyle(
                            color: colorScheme.onSurface,
                            fontWeight: FontWeight.w500,
                            fontSize: 18,
                          ),
                        )
                      ],
                    ),
                  );
                }
              }).toList();
              return Column(
                mainAxisSize: MainAxisSize.min,
                children: accountInfo,
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
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_download.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.download,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_favorite_outline.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.favorite,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_history_outline.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.history,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
                IconButton(
                  style: IconButton.styleFrom(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(16))),
                  onPressed: () {},
                  icon: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SvgPicture.asset(
                        "assets/img/ic_message.svg",
                        width: 30,
                        height: 30,
                        colorFilter: ColorFilter.mode(
                          colorScheme.secondary,
                          BlendMode.srcIn,
                        ),
                      ),
                      const SizedBox(
                        height: 4,
                      ),
                      Text(
                        localeString.message,
                        style: TextStyle(color: colorScheme.onSurface),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
        ListTile(
          leading: SvgPicture.asset(
            "assets/img/ic_theme.svg",
            width: 24,
            height: 24,
            colorFilter: ColorFilter.mode(
              colorScheme.tertiary,
              BlendMode.srcIn,
            ),
          ),
          trailing: SvgPicture.asset(
            "assets/img/ic_right_arrow.svg",
            width: 16,
            height: 16,
            colorFilter: ColorFilter.mode(
              colorScheme.onSurface.withOpacity(0.6),
              BlendMode.srcIn,
            ),
          ),
          onTap: () {},
          title: Text(
            localeString.theme,
            style: TextStyle(color: colorScheme.onSurface),
          ),
        ),
        ListTile(
          leading: SvgPicture.asset(
            "assets/img/ic_settings.svg",
            width: 24,
            height: 24,
            colorFilter: ColorFilter.mode(
              colorScheme.tertiary,
              BlendMode.srcIn,
            ),
          ),
          onTap: () {},
          title: Text(
            localeString.settings,
            style: TextStyle(color: colorScheme.onSurface),
          ),
          trailing: SvgPicture.asset(
            "assets/img/ic_right_arrow.svg",
            width: 16,
            height: 16,
            colorFilter: ColorFilter.mode(
              colorScheme.onSurface.withOpacity(0.6),
              BlendMode.srcIn,
            ),
          ),
        ),
        const SizedBox(height: 24),
      ],
    );
  }
}
