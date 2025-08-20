enum MediaType {
  video,
  bangumi,
  movie,
  live,
  article,
  user;

  String get typeString {
    switch (this) {
      case MediaType.video:
        return "video";
      case MediaType.bangumi:
        return "media_bangumi";
      case MediaType.movie:
        return "media_ft";
      case MediaType.live:
        return "live";
      case MediaType.article:
        return "article";
      case MediaType.user:
        return "bili_user";
    }
  }

  static MediaType fromString(String value) {
    switch (value) {
      case "video":
        return MediaType.video;
      case "media_bangumi":
        return MediaType.bangumi;
      case "media_ft":
        return MediaType.movie;
      case "live":
        return MediaType.live;
      case "article":
        return MediaType.article;
      case "bili_user":
        return MediaType.user;
      default:
        throw Exception("Unknown MediaType: $value");
    }
  }
}
