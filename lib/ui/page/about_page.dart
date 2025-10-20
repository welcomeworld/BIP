import 'package:bip/utils/constant.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher_string.dart';

import '../../l10n/app_locale.dart';

class AboutPage extends StatelessWidget {
  const AboutPage({super.key});

  @override
  Widget build(BuildContext context) {
    final localeString = AppLocale.of(context)!;
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          onPressed: () {
            Navigator.of(context).maybePop();
          },
          padding: EdgeInsets.zero,
          icon: const Icon(Icons.arrow_back),
        ),
        title: Text(localeString.about),
      ),
      body: ListView(
        children: [
          Padding(
            padding:
                const EdgeInsets.only(top: 48, bottom: 48, right: 16, left: 16),
            child: Column(
              children: [
                Image.asset(
                  "assets/img/ic_launcher_round.png",
                  width: 120,
                  height: 120,
                ),
                const SizedBox(height: 4),
                Text(
                  localeString.appName,
                  style: const TextStyle(
                    fontSize: 22,
                    fontWeight: FontWeight.w500,
                  ),
                ),
              ],
            ),
          ),
          ListTile(
            title: Text(localeString.license),
            onTap: () {
              showLicensePage(context: context);
            },
          ),
          ListTile(
            title: Text(localeString.feedback),
            onTap: () {
              launchUrlString(Constant.feedbackUrl);
            },
          ),
          ListTile(
            title: Text(localeString.version),
            trailing: const Text(
              Constant.appVersion,
              style: TextStyle(fontSize: 12),
            ),
          ),
        ],
      ),
    );
  }
}
