import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.openinapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.openinapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            properties.load(localPropertiesFile.inputStream())
        }

        /**
         * Retrieves the value of BEARER_TOKEN from LOCAL.PROPERTIES file.
         */

        val bearerToken: String = properties.getProperty("BEARER_TOKEN")

        /**
         * Defines a Build Config field.
         * @param type  :type of attribute
         * @param name  : The name of the build config when called (ex : BuildConfig.name)
         * @param value : The value of the build config of the name
         */
        buildConfigField("String", "BEARER_TOKEN", "\"$bearerToken\"")
    }

    buildFeatures{
        viewBinding = true
        buildConfig = true
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Networking -- GSON, Retrofit, Convertor, Interceptor
    implementation (libs.google.code.gson)
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.convertor)
    implementation(libs.squareup.okhttp3.logging.interceptor)
}