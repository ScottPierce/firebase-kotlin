@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    // id("org.jetbrains.dokka")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        publishAllLibraryVariants()
    }
    iosX64()
    iosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.kotlin.stdlib.common)
                implementation(Deps.kotlinx.coroutines.common)
            }
        }

        val commonTest by getting {
            dependencies {
                for (lib in Deps.kotlin.test.common) {
                    implementation(lib)
                }
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Deps.kotlin.stdlib.jvm)
                implementation(Deps.kotlinx.coroutines.jvm)
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(Deps.kotlin.test.jvm)
            }
        }

        val iosX64Main by getting
        val iosX64Test by getting
        val iosArm64Main by getting {
            dependsOn(iosX64Main)
        }
        val iosArm64Test by getting

        configure(
            listOf(
                iosArm64Main,
                iosX64Main
            )
        ) {
            dependencies {
                implementation(Deps.kotlinx.coroutines.ios)
            }
        }

        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }


    cocoapods {
        summary = "Working with AFNetworking from Kotlin/Native using CocoaPods"
        homepage = "https://github.com/JetBrains/kotlin-native"

        pod("FirebaseCore", "~> 6.6")
        pod("FirebaseFirestore", "~> 1.11")
    }
}

//dokka {
//    impliedPlatforms = ["Common"] // This will force platform tags for all non-common sources e.g. "JVM"
//    kotlinTasks {
//        // dokka fails to retrieve sources from MPP-tasks so they must be set empty to avoid exception
//        // use sourceRoot instead (see below)
//        []
//    }
//    /*packageOptions {
//        prefix = "com.squareup.sqldelight.internal"
//        suppress = true
//    }*/
//    sourceRoot {
//        // assuming there is only a single source dir...
//        path = kotlin.sourceSets.commonMain.kotlin.srcDirs[0]
//        platforms = ["Common"]
//    }
//    /*if (kotlin.sourceSets.getNames().contains("jvmMain")) {
//        sourceRoot {
//            // assuming there is only a single source dir...
//            path = kotlin.sourceSets.jvmMain.kotlin.srcDirs[0]
//            platforms = ["JVM"]
//        }
//    }
//    if (kotlin.sourceSets.getNames().contains("jsMain")) {
//        sourceRoot {
//            // assuming there is only a single source dir...
//            path = kotlin.sourceSets.jsMain.kotlin.srcDirs[0]
//            platforms = ["js"]
//        }
//    }
//    if (kotlin.sourceSets.getNames().contains("nativeMain")) {
//        sourceRoot {
//            // assuming there is only a single source dir...
//            path = kotlin.sourceSets.nativeMain.kotlin.srcDirs[0]
//            platforms = ["native"]
//        }
//    }*/
//}
