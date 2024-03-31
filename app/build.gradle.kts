fun kapt(s: String) {

}

plugins {
    id("com.android.application")
}

android {
    namespace = "algonquin.cst2335.final_project_w24"
    compileSdk = 34

    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        applicationId = "algonquin.cst2335.final_project_w24"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Volley Library
    implementation("com.android.volley:volley:1.2.1")

    // Retrofit and Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Room components
    implementation("androidx.room:room-runtime:2.2.5")
    kapt("androidx.room:room-compiler:2.2.5")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.1.0")

    // Material Design Components
    implementation("com.google.android.material:material:1.2.0")

    // Lifecycle extensions
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    dependencies {
        // ViewModel and LiveData
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

        // Retrofit & Gson
        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

        val room_version = "2.4.2"
        implementation("androidx.room:room-runtime:$room_version")
        annotationProcessor("androidx.room:room-compiler:$room_version") // For Java use
        kapt("androidx.room:room-compiler:$room_version") // For Kotlin use
        // Kotlin Extensions and Coroutines support for Room
        implementation("androidx.room:room-ktx:$room_version")
        dependencies {
            // Make sure these versions are up-to-date
            implementation ("com.squareup.okhttp3:okhttp:4.9.0")
            implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
            implementation ("com.squareup.retrofit2:retrofit:2.9.0")
            implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
            implementation ("com.squareup.picasso:picasso:2.71828")
        }
    }
    }

