plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.c24bank"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.c24bank"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "BASE_URL", "\"http://app.check24.de/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    //Compose
    implementation(Libraries.AndroidxLifecycleLivedataKtx)
    implementation(Libraries.AndroidxLifecycleRuntimeCompose)
    implementation(Libraries.AndroidxLifecycleViewModelCompose)
    implementation(Libraries.ComposeActivity)
    implementation(Libraries.ComposeUi)
    implementation(Libraries.ComposeUiGraphic)
    implementation(Libraries.ComposeTooling)
    implementation(Libraries.ComposeToolingPreview)
    implementation(Libraries.ComposeMaterial)
    implementation(Libraries.ComposeNavigation)
    implementation(Libraries.AndroidxComposeMaterial3)
    implementation(Libraries.AndroidxComposeMaterial3WindowSizeClass)
    implementation("androidx.compose.material:material:1.4.3")

    //Hilt
    implementation(Libraries.Hilt)
    implementation(Libraries.HiltNavigation)
    kapt(Libraries.HiltCompiler)

    //Retrofit
    implementation(Libraries.Retrofit)
    implementation(Libraries.RetrofitKotlinSerialization)
    implementation(Libraries.OkhttpLogging)

    //Room
    implementation(Libraries.RoomKtx)
    implementation(Libraries.RoomRuntime)
    implementation(Libraries.Serialization)
    kapt(Libraries.RoomCompiler)

    //coil
    implementation(Libraries.Coil)
    implementation(Libraries.CoilCompose)
    implementation(Libraries.CoilSvg)
}