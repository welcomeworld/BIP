// ignore: unused_import
import 'package:intl/intl.dart' as intl;

import 'app_locale.dart';

// ignore_for_file: type=lint

/// The translations for Chinese (`zh`).
class AppLocaleZh extends AppLocale {
  AppLocaleZh([String locale = 'zh']) : super(locale);

  @override
  String get appName => 'BIP';

  @override
  String get home => '首页';

  @override
  String get bangumi => '番剧';

  @override
  String get mine => '我的';

  @override
  String get searchHint => '随便搜点什么吧';

  @override
  String get searchHistory => '搜索历史';

  @override
  String get searchHot => '热门搜索';

  @override
  String get favorite => '收藏';

  @override
  String get history => '历史记录';

  @override
  String get download => '离线缓存';

  @override
  String get message => '聊天私信';

  @override
  String get settings => '设置';

  @override
  String get theme => '主题';

  @override
  String get login => '登录';

  @override
  String get appreciate => '点赞';

  @override
  String get coin => '投币';

  @override
  String get comment => '评论';

  @override
  String get download2 => '下载';

  @override
  String get commentHint => '发表评论，请遵守社区规范';

  @override
  String get replyHint => '回复评论';

  @override
  String get replyDetail => '评论详情';

  @override
  String get noMoreData => '再怎么找也没有啦';

  @override
  String get collection => '收藏夹';

  @override
  String get createCollection => '新建收藏夹';

  @override
  String get title => '标题';

  @override
  String get description => '简介';

  @override
  String get localCollection => '本地收藏夹';

  @override
  String get public => '公开';

  @override
  String get cancel => '取消';

  @override
  String get confirm => '确认';

  @override
  String get titleHint => '请输入标题';

  @override
  String deleteConfirmation(String title) {
    return '您确定要删除 \"$title\" 吗？此操作无法撤销，所有内容将被永久删除。';
  }

  @override
  String get dynamicTheme => '系统动态主题';

  @override
  String get customTheme => '自定义颜色';
}
