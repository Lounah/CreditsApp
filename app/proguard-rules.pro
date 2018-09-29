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
-keepattributes Exceptions
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3. { *; }
-keep interface okhttp3. { *; }
-dontwarn okhttp3.
-dontnote okhttp3.
-dontwarn okhttp3.internal.platform.*
-keep class com.lounah.creditsapp.data.entity. { *; }

# Okio
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream. { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn javax.annotation.concurrent.GuardedBy

-dontwarn com.google.errorprone.annotations.**

-keep class com.lounah.creditsapp.data.entity.** { *; }
-keep class com.lounah.creditsapp.data.network.** { *; }

-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}