package depedencies

object ClassPath {
    const val buildGradle = "com.android.tools.build:gradle:${ClassPathVersions.buildGradleVersion}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${ClassPathVersions.kotlinVersion}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${ClassPathVersions.kotlinVersion}"
    const val navSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${ClassPathVersions.safeArgsVersion}"
}

object ClassPathVersions{
    const val buildGradleVersion = "7.2.2"
    const val kotlinVersion = "1.7.10"
    const val safeArgsVersion = "2.5.3"
}
