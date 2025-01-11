import 'package:bip/bloc/bloc.dart';
import 'package:bip/data/media_manager.dart';
import 'package:bip/data/model/media_page_preview.dart';
import 'package:bip/data/source/source.dart';
import 'package:bip/utils/bip_router.dart';
import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';

import '../data/model/media_page_detail.dart';

class MediaPageDetailBloc extends Bloc {
  BehaviorSubject<MediaPageDetail> detailSubject = BehaviorSubject();

  Future<void> setPreview(MediaPagePreview preview) async {
    detailSubject.add(MediaPageDetail.fromPreview(preview));
    final detailResult = await MediaManager().requestDetail(preview);
    detailSubject.add(detailResult.result);
    if (detailResult.resultCode != SourceApiResult.resultSuccess) {
      ScaffoldMessenger.of(BipRouter.rootRouter.navigatorKey.currentContext!)
          .showSnackBar(
        SnackBar(
          content:
              Text("requestDetail with error code: ${detailResult.resultCode}"),
        ),
      );
    }
  }
}
