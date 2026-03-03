plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.lab6"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.lab6"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// Enable JUnit Platform for unit tests
tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    // Android core dependencies
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // JUnit 5 for unit tests
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")

    // Add this line - JUnit Platform Launcher
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.9.2")

    // Android test dependencies
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(files("/Users/apple/Library/Android/sdk/platforms/android-36/android.jar"))

}