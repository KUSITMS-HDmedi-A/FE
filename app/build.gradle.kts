import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

//plugins {
//    id("org.jetbrains.kotlin.android")
//}
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.com.google.gms.google.services)
}

android {
    namespace = "com.kusitms.hdmedi"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.kusitms.hdmedi"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(":core:navigation"))
    implementation(project(":feature:signin:ui"))
    implementation(project(":feature:alarm:ui"))
    implementation(project(":feature:home:ui"))
    implementation(project(":feature:medicine:ui"))
    implementation(project(":feature:selfcheck:ui"))
    implementation(project(":feature:search:ui"))
    implementation(project(":feature:mypage:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))


    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation)
    implementation(libs.navigation.ui)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.kakao.login)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}