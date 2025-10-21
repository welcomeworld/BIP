import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:intl/intl.dart' as intl;

import 'app_locale_zh.dart';

// ignore_for_file: type=lint

/// Callers can lookup localized strings with an instance of AppLocale
/// returned by `AppLocale.of(context)`.
///
/// Applications need to include `AppLocale.delegate()` in their app's
/// `localizationDelegates` list, and the locales they support in the app's
/// `supportedLocales` list. For example:
///
/// ```dart
/// import 'l10n/app_locale.dart';
///
/// return MaterialApp(
///   localizationsDelegates: AppLocale.localizationsDelegates,
///   supportedLocales: AppLocale.supportedLocales,
///   home: MyApplicationHome(),
/// );
/// ```
///
/// ## Update pubspec.yaml
///
/// Please make sure to update your pubspec.yaml to include the following
/// packages:
///
/// ```yaml
/// dependencies:
///   # Internationalization support.
///   flutter_localizations:
///     sdk: flutter
///   intl: any # Use the pinned version from flutter_localizations
///
///   # Rest of dependencies
/// ```
///
/// ## iOS Applications
///
/// iOS applications define key application metadata, including supported
/// locales, in an Info.plist file that is built into the application bundle.
/// To configure the locales supported by your app, you’ll need to edit this
/// file.
///
/// First, open your project’s ios/Runner.xcworkspace Xcode workspace file.
/// Then, in the Project Navigator, open the Info.plist file under the Runner
/// project’s Runner folder.
///
/// Next, select the Information Property List item, select Add Item from the
/// Editor menu, then select Localizations from the pop-up menu.
///
/// Select and expand the newly-created Localizations item then, for each
/// locale your application supports, add a new item and select the locale
/// you wish to add from the pop-up menu in the Value field. This list should
/// be consistent with the languages listed in the AppLocale.supportedLocales
/// property.
abstract class AppLocale {
  AppLocale(String locale) : localeName = intl.Intl.canonicalizedLocale(locale.toString());

  final String localeName;

  static AppLocale? of(BuildContext context) {
    return Localizations.of<AppLocale>(context, AppLocale);
  }

  static const LocalizationsDelegate<AppLocale> delegate = _AppLocaleDelegate();

  /// A list of this localizations delegate along with the default localizations
  /// delegates.
  ///
  /// Returns a list of localizations delegates containing this delegate along with
  /// GlobalMaterialLocalizations.delegate, GlobalCupertinoLocalizations.delegate,
  /// and GlobalWidgetsLocalizations.delegate.
  ///
  /// Additional delegates can be added by appending to this list in
  /// MaterialApp. This list does not have to be used at all if a custom list
  /// of delegates is preferred or required.
  static const List<LocalizationsDelegate<dynamic>> localizationsDelegates = <LocalizationsDelegate<dynamic>>[
    delegate,
    GlobalMaterialLocalizations.delegate,
    GlobalCupertinoLocalizations.delegate,
    GlobalWidgetsLocalizations.delegate,
  ];

  /// A list of this localizations delegate's supported locales.
  static const List<Locale> supportedLocales = <Locale>[
    Locale('zh')
  ];

  /// No description provided for @appName.
  ///
  /// In zh, this message translates to:
  /// **'BIP'**
  String get appName;

  /// No description provided for @home.
  ///
  /// In zh, this message translates to:
  /// **'首页'**
  String get home;

  /// No description provided for @bangumi.
  ///
  /// In zh, this message translates to:
  /// **'番剧'**
  String get bangumi;

  /// No description provided for @mine.
  ///
  /// In zh, this message translates to:
  /// **'我的'**
  String get mine;

  /// No description provided for @searchHint.
  ///
  /// In zh, this message translates to:
  /// **'随便搜点什么吧'**
  String get searchHint;

  /// No description provided for @searchHistory.
  ///
  /// In zh, this message translates to:
  /// **'搜索历史'**
  String get searchHistory;

  /// No description provided for @searchHot.
  ///
  /// In zh, this message translates to:
  /// **'热门搜索'**
  String get searchHot;

  /// No description provided for @favorite.
  ///
  /// In zh, this message translates to:
  /// **'收藏'**
  String get favorite;

  /// No description provided for @history.
  ///
  /// In zh, this message translates to:
  /// **'历史记录'**
  String get history;

  /// No description provided for @download.
  ///
  /// In zh, this message translates to:
  /// **'离线缓存'**
  String get download;

  /// No description provided for @message.
  ///
  /// In zh, this message translates to:
  /// **'聊天私信'**
  String get message;

  /// No description provided for @settings.
  ///
  /// In zh, this message translates to:
  /// **'设置'**
  String get settings;

  /// No description provided for @theme.
  ///
  /// In zh, this message translates to:
  /// **'主题'**
  String get theme;

  /// No description provided for @login.
  ///
  /// In zh, this message translates to:
  /// **'登录'**
  String get login;

  /// No description provided for @appreciate.
  ///
  /// In zh, this message translates to:
  /// **'点赞'**
  String get appreciate;

  /// No description provided for @coin.
  ///
  /// In zh, this message translates to:
  /// **'投币'**
  String get coin;

  /// No description provided for @comment.
  ///
  /// In zh, this message translates to:
  /// **'评论'**
  String get comment;

  /// No description provided for @download2.
  ///
  /// In zh, this message translates to:
  /// **'下载'**
  String get download2;

  /// No description provided for @commentHint.
  ///
  /// In zh, this message translates to:
  /// **'发表评论，请遵守社区规范'**
  String get commentHint;

  /// No description provided for @replyHint.
  ///
  /// In zh, this message translates to:
  /// **'回复评论'**
  String get replyHint;

  /// No description provided for @replyDetail.
  ///
  /// In zh, this message translates to:
  /// **'评论详情'**
  String get replyDetail;

  /// No description provided for @noMoreData.
  ///
  /// In zh, this message translates to:
  /// **'再怎么找也没有啦'**
  String get noMoreData;

  /// No description provided for @collection.
  ///
  /// In zh, this message translates to:
  /// **'收藏夹'**
  String get collection;

  /// No description provided for @createCollection.
  ///
  /// In zh, this message translates to:
  /// **'新建收藏夹'**
  String get createCollection;

  /// No description provided for @title.
  ///
  /// In zh, this message translates to:
  /// **'标题'**
  String get title;

  /// No description provided for @description.
  ///
  /// In zh, this message translates to:
  /// **'简介'**
  String get description;

  /// No description provided for @localCollection.
  ///
  /// In zh, this message translates to:
  /// **'本地收藏夹'**
  String get localCollection;

  /// No description provided for @public.
  ///
  /// In zh, this message translates to:
  /// **'公开'**
  String get public;

  /// No description provided for @cancel.
  ///
  /// In zh, this message translates to:
  /// **'取消'**
  String get cancel;

  /// No description provided for @confirm.
  ///
  /// In zh, this message translates to:
  /// **'确认'**
  String get confirm;

  /// No description provided for @titleHint.
  ///
  /// In zh, this message translates to:
  /// **'请输入标题'**
  String get titleHint;

  /// 删除确认对话框消息
  ///
  /// In zh, this message translates to:
  /// **'您确定要删除 \"{title}\" 吗？此操作无法撤销，所有内容将被永久删除。'**
  String deleteConfirmation(String title);

  /// No description provided for @dynamicTheme.
  ///
  /// In zh, this message translates to:
  /// **'系统动态主题'**
  String get dynamicTheme;

  /// No description provided for @customTheme.
  ///
  /// In zh, this message translates to:
  /// **'自定义颜色'**
  String get customTheme;

  /// No description provided for @bestMedia.
  ///
  /// In zh, this message translates to:
  /// **'优先最佳画质'**
  String get bestMedia;

  /// No description provided for @enterFull.
  ///
  /// In zh, this message translates to:
  /// **'播放页面默认全屏'**
  String get enterFull;

  /// No description provided for @about.
  ///
  /// In zh, this message translates to:
  /// **'关于'**
  String get about;

  /// No description provided for @license.
  ///
  /// In zh, this message translates to:
  /// **'许可'**
  String get license;

  /// No description provided for @feedback.
  ///
  /// In zh, this message translates to:
  /// **'反馈/建议'**
  String get feedback;

  /// No description provided for @version.
  ///
  /// In zh, this message translates to:
  /// **'版本'**
  String get version;

  /// No description provided for @logout.
  ///
  /// In zh, this message translates to:
  /// **'退出登录'**
  String get logout;
}

class _AppLocaleDelegate extends LocalizationsDelegate<AppLocale> {
  const _AppLocaleDelegate();

  @override
  Future<AppLocale> load(Locale locale) {
    return SynchronousFuture<AppLocale>(lookupAppLocale(locale));
  }

  @override
  bool isSupported(Locale locale) => <String>['zh'].contains(locale.languageCode);

  @override
  bool shouldReload(_AppLocaleDelegate old) => false;
}

AppLocale lookupAppLocale(Locale locale) {


  // Lookup logic when only language code is specified.
  switch (locale.languageCode) {
    case 'zh': return AppLocaleZh();
  }

  throw FlutterError(
    'AppLocale.delegate failed to load unsupported locale "$locale". This is likely '
    'an issue with the localizations generation tool. Please file an issue '
    'on GitHub with a reproducible sample app and the gen-l10n configuration '
    'that was used.'
  );
}
