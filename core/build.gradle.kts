plugins {
    id(depedencies.Plugins.androidLibrary)
    id(depedencies.Plugins.kotlinAndroid)
    id(depedencies.Plugins.kotlinSerialization)
}

android {
    compileSdk = depedencies.Project.compileSdk

    defaultConfig {
        minSdk = depedencies.Project.minSdk
        targetSdk = depedencies.Project.compileSdk

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = depedencies.Project.jvmTarget
    }
}

dependencies {
    //AndroidX
    api(depedencies.Dependencies.AndroidX.coreKTX)
    implementation(depedencies.Dependencies.AndroidX.serializationCore)

    //ktor
    implementation(depedencies.Dependencies.Ktor.clientCore)
    implementation(depedencies.Dependencies.Ktor.cio)
    implementation(depedencies.Dependencies.Ktor.clientSerializationJSON)
    implementation(depedencies.Dependencies.Ktor.clientSerialization)
    implementation(depedencies.Dependencies.Ktor.clientLogging)
    implementation(depedencies.Dependencies.Ktor.contentNegotiation)


    //Koin
    api(depedencies.Dependencies.Koin.koinAndroid)

    api(depedencies.Dependencies.Test.jUnit)
}