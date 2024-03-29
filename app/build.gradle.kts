plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id ("io.realm.kotlin")
}

android {
    namespace = "com.example.traker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.traker"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")


    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //lotile
    implementation("com.airbnb.android:lottie-compose:6.1.0")
    // Extended Icons
    implementation("androidx.compose.material:material-icons-extended:1.6.2")

    // system UI Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    //pagerIndicator
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")

    //realm
    implementation ("io.realm.kotlin:library-base:1.11.0")
    implementation ("androidx.work:work-runtime-ktx:2.8.1")

    implementation ("com.google.accompanist:accompanist-permissions:0.23.1")
    implementation ("com.google.android.gms:play-services-location:21.1.0")

    implementation("com.google.android.gms:play-services-maps:17.0.1")
    implementation ("androidx.activity:activity-compose:1.4.0")

}