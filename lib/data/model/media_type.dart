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
}
