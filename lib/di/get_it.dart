import 'package:bip/data/collection_manager_impl.dart';
import 'package:bip/data/drift_database.dart';
import 'package:bip/data/media_manager_impl.dart';
import 'package:bip/data/net/web_net.dart';
import 'package:bip/data/persistence/kv_store_impl.dart';
import 'package:bip/data/settings_manager_impl.dart';
import 'package:bip/data/source_manager_impl.dart';
import 'package:bip/domain/interfaces/collection_manager.dart';
import 'package:bip/domain/interfaces/database.dart';
import 'package:bip/domain/interfaces/kv_store.dart';
import 'package:bip/domain/interfaces/media_manager.dart';
import 'package:bip/domain/interfaces/settings_manager.dart';
import 'package:bip/domain/interfaces/source_manager.dart';
import 'package:bip/domain/interfaces/web_net.dart';
import 'package:bip/ui/theme/theme_notifier.dart';
import 'package:get_it/get_it.dart';

GetIt getIt = GetIt.instance;

Future<void> setupLocator() async {
  getIt.registerSingleton<KvStore>(await KvStoreImpl.create());
  getIt.registerSingleton<WebNet>(WebNetImpl());
  getIt.registerSingleton<ThemeNotifier>(ThemeNotifier.load(getIt<KvStore>()));
  getIt.registerSingleton<SettingsManager>(
      SettingsManagerImpl(getIt<KvStore>()));
  getIt.registerSingleton<SourceManager>(SourceManagerImpl());
  getIt.registerSingleton<MediaManager>(MediaManagerImpl());
  getIt.registerSingleton<Database>(BipDatabase());
  getIt.registerSingleton<CollectionManager>(CollectionManagerImpl());
}
