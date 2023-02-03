package depedencies

object Dependencies {

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideAnnotationProcessor =
            "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object Material {
        const val googleMaterial =
            "com.google.android.material:material:${Versions.google_material}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidX_appCompat}"

        const val constrainLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.androidX_constrainLayout}"

        const val coreKTX = "androidx.core:core-ktx:${Versions.androidX_coreKTX}"

        const val serializationCore =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serializationCoreVersion}"

        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

        const val splashScreen =
            "androidx.core:core-splashscreen:${Versions.androidX_splashScreenVersion}"

        const val fragment = "androidx.fragment:fragment:${Versions.androidX_fragmentVersion}"

        const val cardView = "androidx.cardview:cardview:${Versions.androidX_cardViewVersion}"
    }

    object Lifecycle {
        const val androidLifeCycle =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifeCycleVersion}"

    }

    object Ktor {
        const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor_client}"
        const val cio = "io.ktor:ktor-client-cio:${Versions.ktor_client}"
        const val clientSerializationJSON =
            "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor_client}"
        const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor_client}"
        const val clientLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor_client}"
        const val contentNegotiation =
            "io.ktor:ktor-client-content-negotiation:${Versions.ktor_client}"
    }


    object Koin {
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
    }

    object Test {

        const val jUnit = "junit:junit:${Versions.jUnitVersion}"
        const val androidJunit = "androidx.test.ext:junit:${Versions.androidTestVersion}"
        const val androidJunitEspresso = "junit:junit:${Versions.espressoVersion}"
        const val androidEspressoCore =
            "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    }

    object Modules {
        const val coreModule = ":core"
        const val dashboardModule = ":features:recipe_dashboard"
    }

    object NavigationComponent {
        const val navFragment = "androidx.navigation:navigation-fragment:${Versions.navVersion}"
        const val navUI = "androidx.navigation:navigation-ui:${Versions.navVersion}"
        const val navTestingNavigation =
            "androidx.navigation:navigation-testing:${Versions.navVersion}"
    }

    object GooglePlayServices {
        const val location =
            "com.google.android.gms:play-services-location:${Versions.googlePlayServices_location}"
        const val maps =
            "com.google.android.gms:play-services-maps:${Versions.googlePlayServices_maps}"

    }
}

object Versions {
    const val google_material = "1.8.0"

    const val androidX_appCompat = "1.6.0"
    const val androidX_constrainLayout = "2.1.4"
    const val androidX_coreKTX = "1.9.0"
    const val androidX_fragmentVersion = "1.6.0-alpha04"
    const val androidX_splashScreenVersion = "1.0.0"
    const val androidX_cardViewVersion = "1.0.0"

    const val androidLifeCycleVersion = "2.5.1"

    const val ktor_client = "2.2.2"

    const val koinVersion = "3.3.2"

    const val serializationCoreVersion = "1.4.1"

    const val coroutinesVersion = "1.6.4"

    const val jUnitVersion = "4.13.2"
    const val androidTestVersion = "1.1.5"
    const val espressoVersion = "3.5.1"

    const val navVersion = "2.5.3"

    const val googlePlayServices_location = "21.0.1"
    const val googlePlayServices_maps = "18.1.0"

    const val glideVersion = "4.14.2"
}

object Project {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"
    const val getDefaultProguardFile = "proguard-android-optimize.txt"
    const val proguardRule = "proguard-rules.pro"
    const val jvmTarget = "1.8"
    const val applicationId = "com.carlosquezada.recetas"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val androidLibrary = "com.android.library"
    const val kotlinSerialization = "androidx.navigation.safeargs.kotlin"

}
