plugins {
    id(depedencies.Plugins.androidApplication)
    id(depedencies.Plugins.kotlinAndroid)
    id(depedencies.Plugins.navigationSafeArgs)
}

android {
    compileSdk = depedencies.Project.compileSdk

    defaultConfig {
        applicationId = depedencies.Project.applicationId
        minSdk = depedencies.Project.minSdk
        targetSdk = depedencies.Project.targetSdk
        versionCode = depedencies.Project.versionCode
        versionName = depedencies.Project.versionName

        testInstrumentationRunner = depedencies.Project.testInstrumentationRunner
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
}

dependencies {
    //Modules
    implementation(project(depedencies.Dependencies.Modules.coreModule))
    implementation(project(depedencies.Dependencies.Modules.dashboardModule))

    //Material
    implementation(depedencies.Dependencies.Material.googleMaterial)

    //AndroidX
    implementation(depedencies.Dependencies.AndroidX.appCompat)
    implementation(depedencies.Dependencies.AndroidX.constrainLayout)
    implementation(depedencies.Dependencies.AndroidX.serializationCore)
    implementation(depedencies.Dependencies.AndroidX.coroutinesAndroid)
    implementation(depedencies.Dependencies.AndroidX.splashScreen)
    implementation(depedencies.Dependencies.AndroidX.fragment)

    //Lifecycle
    implementation(depedencies.Dependencies.Lifecycle.androidLifeCycle)

    //Navigation
    implementation(depedencies.Dependencies.NavigationComponent.navUI)
    implementation(depedencies.Dependencies.NavigationComponent.navFragment)

    //Test
    implementation(depedencies.Dependencies.Test.androidJunit)
    implementation(depedencies.Dependencies.Test.androidJunitEspresso)
    implementation(depedencies.Dependencies.Test.androidEspressoCore)
    implementation(depedencies.Dependencies.NavigationComponent.navTestingNavigation)
}