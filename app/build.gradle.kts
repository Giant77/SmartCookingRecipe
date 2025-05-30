import java.util.Properties

val properties = Properties()
val localPropertiesFile = File(rootProject.rootDir, "local.properties")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "1.9.24"
}

if (localPropertiesFile.exists()) {
    properties.load(localPropertiesFile.inputStream())
}
android {
    namespace = "com.example.smartcookingrecipe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.smartcookingrecipe"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "SUPABASE_URL", "\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR2bmtpcmh3dGF6d292Y29pb291Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg1MDgxMDcsImV4cCI6MjA2NDA4NDEwN30.hd5MbnI-WTzLMm3UOgpYdGJc1r-1fe6FTY29G-yDUT4\"")
        buildConfigField("String", "SUPABASE_ANON_KEY", "\"https://tvnkirhwtazwovcoioou.supabase.co\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.circleindicator)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.camera.view)
    implementation(libs.core.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val ktorVersion = "3.1.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.1.0")
    implementation("io.github.jan-tennert.supabase:storage-kt:3.1.0")
    implementation("io.github.jan-tennert.supabase:auth-kt:3.1.0")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-utils:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

//    implementation("io.coil-kt:coil:2.5.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
}

