# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Keep JSch classes
-keep class com.jcraft.jsch.** { *; }

# Keep Apache Commons Net
-keep class org.apache.commons.net.** { *; }

# Keep Room entities
-keep class com.ftplink.data.local.** { *; }
