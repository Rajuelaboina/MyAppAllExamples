plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.phycaresolutions.mymap"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.phycaresolutions.mymap"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    flavorDimensions += listOf("free")
    productFlavors {
        create("free") {
            dimension = "free"
        }
    }


    /* productFlavors {


         create("phy") {
             dimension = "phycare"
             applicationId = "phyApplicationId"
             applicationIdSuffix = ".phy"
             versionCode = 1
             versionName = "phy"
         }
     }
     flavorDimensions += listOf("phycare")*/

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation.compose)
    implementation(libs.work.runtime)
    implementation(libs.play.services.ads)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //implementation("com.github.bkhezry:MapDrawingTools:1.1.3")
    //implementation("com.github.AmosKorir:Patterner-googlemap-patterns:Tag")
    implementation("com.google.maps.android:android-maps-utils:3.9.0")
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.squareup.okhttp3:logging-interceptor:3.4.1")
 //  implementation("com.squareup.okhttp3:okhttps:3.4.1")
    implementation("com.google.code.gson:gson:2.11.0:")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //implementation (files('libs/loginwithlinkedin-release.aar'))
  //  implementation("com.github.Mindinventory:LinkedInLogin:*.*.*")

    // amazon
  //  implementation("com.eftimoff:android-patternview:1.0.6@aar")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

        //implementation 'com.google.android.gms:play-services-maps:17.0.1' // or the latest version
    implementation("com.google.maps:google-maps-services:0.17.0")  // For Directions API

    implementation("com.squareup.okhttp3:okhttp:4.12.0")

   implementation("com.android.volley:volley:1.2.1")

    // Room data Base
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

 /*   implementation("com.google.firebase:firebase-admin:9.1.1")
    implementation("com.google.auth:google-auth-library-credentials:0.24.0")*/
    //implementation("com.google.firebase:firebase-admin:9.2.0")
    //implementation("org.apache.httpcomponents:httpclient:4.5.4")


}