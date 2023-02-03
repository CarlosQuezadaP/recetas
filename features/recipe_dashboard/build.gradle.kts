import depedencies.Dependencies.Modules.coreModule

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    compileSdk = depedencies.Project.compileSdk

    defaultConfig {
        minSdk = depedencies.Project.minSdk
        targetSdk = depedencies.Project.targetSdk

        testInstrumentationRunner = depedencies.Project.testInstrumentationRunner
        consumerProguardFiles(depedencies.Project.consumerProguardFiles)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(depedencies.Project.getDefaultProguardFile),
                depedencies.Project.proguardRule
            )
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = depedencies.Project.jvmTarget
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src/main/assets")
            }
        }
    }
}

dependencies {

    //Modules
    implementation(project(coreModule))

    //androidX
    implementation(depedencies.Dependencies.AndroidX.appCompat)
    implementation(depedencies.Dependencies.AndroidX.constrainLayout)
    implementation(depedencies.Dependencies.AndroidX.serializationCore)
    implementation(depedencies.Dependencies.AndroidX.coroutinesAndroid)
    implementation(depedencies.Dependencies.AndroidX.cardView)


    //Material
    implementation(depedencies.Dependencies.Material.googleMaterial)

    //ktor
    implementation(depedencies.Dependencies.Ktor.clientCore)
    implementation(depedencies.Dependencies.Koin.koinAndroid)

    //Navigation
    implementation(depedencies.Dependencies.NavigationComponent.navFragment)
    implementation(depedencies.Dependencies.NavigationComponent.navUI)

    //Google play services
    implementation(depedencies.Dependencies.GooglePlayServices.location)
    implementation(depedencies.Dependencies.GooglePlayServices.maps)

    implementation("com.airbnb.android:lottie:3.0.7")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")


    //Glide
    implementation(depedencies.Dependencies.Glide.glide)
    annotationProcessor(depedencies.Dependencies.Glide.glideAnnotationProcessor)

    //Test
    implementation(depedencies.Dependencies.Test.androidJunit)
    implementation(depedencies.Dependencies.Test.androidJunitEspresso)
}