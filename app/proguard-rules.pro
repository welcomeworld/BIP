# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# And if you use AsyncExecutor:
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#gson 开始
# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
#gson 结束

#keep 数据类开始
-keep class com.github.welcomeworld.bangumi.instrumentality.project.model.**{*;}
-keep class com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.**{*;}
-keep class com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi.databean.**{*;}
-keep class com.github.welcomeworld.bangumi.instrumentality.project.source.agefans.PlayUrlBean
#keep 数据类结束


#keep class which has native method
-keepclasseswithmembers class * {
    native <methods>;
}

#keep viewbinding
-keep class * extends androidx.viewbinding.ViewBinding

#bugly 开始
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#bugly 结束