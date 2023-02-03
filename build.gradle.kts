buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(depedencies.ClassPath.buildGradle)
        classpath(depedencies.ClassPath.kotlinSerialization)
        classpath(depedencies.ClassPath.navSafeArgs)
        classpath(depedencies.ClassPath.navSafeArgs)
        classpath(depedencies.ClassPath.kotlinGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}