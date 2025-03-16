class UserInfo {
  Map<String, dynamic> extras = {};
  String name = "";
  String avatar = "";
  int videoCount = 0;
  int fansCount = 0;

  // Constructor
  UserInfo({
    this.extras = const {},
    this.name = "",
    this.avatar = "",
    this.videoCount = 0,
    this.fansCount = 0,
  });

  // Method to create a UserInfo object from a map (JSON)
  factory UserInfo.fromJson(Map<String, dynamic> json) {
    return UserInfo(
      extras: json['extras'] != null
          ? Map<String, dynamic>.from(json['extras'])
          : {},
      name: json['name'] as String? ?? "",
      avatar: json['avatar'] as String? ?? "",
      videoCount: json['videoCount'] as int? ?? 0,
      fansCount: json['fansCount'] as int? ?? 0,
    );
  }

  // Method to convert a UserInfo object to a map (JSON)
  Map<String, dynamic> toJson() {
    return {
      'extras': extras,
      'name': name,
      'avatar': avatar,
      'videoCount': videoCount,
      'fansCount': fansCount,
    };
  }
}
