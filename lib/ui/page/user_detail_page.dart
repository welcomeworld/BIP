import 'package:bip/data/model/user_info.dart';
import 'package:flutter/material.dart';

import '../../bloc/bloc_state.dart';
import '../../bloc/user_detail_bloc.dart';

class UserDetailPage extends StatefulWidget {
  const UserDetailPage(this.userInfo, {super.key});

  final UserInfo userInfo;

  @override
  State<UserDetailPage> createState() => _UserDetailPageState();
}

class _UserDetailPageState extends BlocState<UserDetailPage, UserDetailBloc> {
  _UserDetailPageState() : super(UserDetailBloc());

  @override
  void initState() {
    super.initState();
    bloc.setUserInfo(widget.userInfo);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          onPressed: () {
            Navigator.of(context).maybePop();
          },
          padding: EdgeInsets.zero,
          icon: const Icon(Icons.arrow_back),
        ),
      ),
      body: StreamBuilder<UserInfo>(
        stream: bloc.userInfo,
        builder: (context, snapshot) {
          return Column(
            children: [
              FilledButton(
                onPressed: () {
                  bloc.logout();
                  Navigator.of(context).maybePop();
                },
                style: FilledButton.styleFrom(
                  padding: const EdgeInsets.symmetric(horizontal: 8),
                  backgroundColor: colorScheme.secondary,
                  overlayColor: colorScheme.onSurfaceVariant,
                  minimumSize: const Size(120, 40),
                  tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(8),
                  ),
                ),
                child: Text(
                  localeString.logout,
                  style: TextStyle(
                    color: colorScheme.onSecondary,
                    fontSize: 14,
                    overflow: TextOverflow.ellipsis,
                  ),
                  maxLines: 1,
                ),
              ),
            ],
          );
        },
      ),
    );
  }
}
