import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

//plugins {
//    id("org.jetbrains.kotlin.android")
//}
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.kusitms.hdmedi.ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":feature:medicine:domain"))
    implementation(project(":feature:medicine:data"))

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.calendar)


    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.navigation)
    implementation(libs.navigation.ui)
    implementation(libs.okhttp.logging)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.calendar)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}