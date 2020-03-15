buildscript {
    repositories {
        google {
            content {
                includeGroupByRegex("^android\\..+")
                includeGroupByRegex("^androidx\\..+")
                includeGroupByRegex("^com\\.android\\.?.*\$")
                includeGroupByRegex("^com\\.google\\.?.*\$")
                includeGroupByRegex("^io\\.fabric\\.?.*\$")
                includeGroupByRegex("^com\\.crashlytics\\.?.*\$")
            }
        }

        jcenter()
    }

    dependencies {
        classpath(Plugins.android)
        classpath(Plugins.atomicfu)
        classpath(Plugins.dokka)
        classpath(Plugins.kotlin)
        classpath(Plugins.kotlinSerialization)
    }
}

group = "dev.scottpierce.firebase"
val versionString = "0.0.1"
version = versionString

subprojects {
    version = versionString

    repositories {
        maven {
            url = uri("https://artifacts.werally.in/artifactory/mobile-release/")
            val artifactoryCredentials = ArtifactoryCredential.load()
            credentials {
                username = artifactoryCredentials.user
                password = artifactoryCredentials.password
            }
            content {
                // Includes all
                includeGroupByRegex("^com\\.rallyhealth\\.\\S+\$")
            }
        }

        google {
            content {
                includeGroupByRegex("^android\\..+")
                includeGroupByRegex("^androidx\\..+")
                includeGroupByRegex("^com\\.android\\.?.*\$")
                includeGroupByRegex("^com\\.google\\.?.*\$")
                includeGroupByRegex("^io\\.fabric\\.?.*\$")
                includeGroupByRegex("^com\\.crashlytics\\.?.*\$")
            }
        }

        jcenter()
    }
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

//buildscript {
//    ext.kotlin_version = '1.3.50'
//
//    ext.versions = [
//        'minSdk': 9,
//    'compileSdk': 28,
//
//    'kotlin': '1.3.50',
//
//    'androidPlugin': '3.4.1',
//    'androidTools': '29.0.1',
//
//    'firebaseCoreIos': '6.0.2',
//    'firebaseFirestoreIos': '1.3.2',
//    'firebaseCoreAndroid': '17.0.1',
//    'firebaseFirestoreAndroid': '20.1.0',
//
//    'stately': '0.9.3',
//    'coroutines': '1.3.0'
//
//    ]
//
//    repositories {
//        mavenLocal()
//        maven { url 'https://dl.bintray.com/kotlin/kotlin-eap' }
//        google()
//        jcenter()
//        mavenCentral()
//    }
//    dependencies {
//        classpath "com.android.tools.build:gradle:${versions.androidPlugin}"
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
//        classpath 'com.google.gms:google-services:4.3.0'
//        classpath 'org.jetbrains.dokka:dokka-gradle-plugin:0.9.17'
//
//        // NOTE: Do not place your application dependencies here; they belong
//        // in the individual module build.gradle files
//    }
//}
//
//allprojects {
//    repositories {
//        mavenLocal()
//        maven { url 'https://dl.bintray.com/kotlin/kotlin-eap' }
//        google()
//        jcenter()
//        mavenCentral()
//    }
//}
//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
