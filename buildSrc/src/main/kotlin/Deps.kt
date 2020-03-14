object AndroidConfig {
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val minSdkVersion = 21
}

object Versions {
    const val androidx = "1.1.0"
    const val atomicfu = "0.14.2"
    const val kotlin = "1.3.70"
    const val kinject = "0.1.4"
    const val kotlinxCoroutines = "1.3.4"
    const val kotlinxSerialization = "0.20.0"
    const val ktor = "1.3.2"
    const val store = "0.1.9"
}

object Plugins {
    val android = "com.android.tools.build:gradle:3.6.0"
    val atomicfu = "org.jetbrains.kotlinx:atomicfu-gradle-plugin:${Versions.atomicfu}"
    val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:0.10.1"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
}

object Deps {
    val android = AndroidDeps()
    val androidx = AndroidXDeps()
    val kotlin = KotlinDeps()
    val kotlinx = KotlinXDeps()
    val ktor = KtorDeps()
    val okhttp = "com.squareup.okhttp3:okhttp:4.3.1"
    val rally = RallyDeps()
}

class AndroidDeps internal constructor() {
    val robolectric = "org.robolectric:robolectric:4.3"
}

class AndroidXDeps internal constructor() {
    val appcompat = "androidx.appcompat:appcompat:${Versions.androidx}"
    val annotation = "androidx.annotation:annotation:${Versions.androidx}"
    val browser = "androidx.browser:browser:1.2.0"
}

class KotlinDeps internal constructor() {
    val stdlib = KotlinStdLibDeps()
    val test = KotlinTestDeps()
}

class KotlinStdLibDeps internal constructor() {
    val common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
    val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val js = "org.jetbrains.kotlin:kotlin-stdlib-js:${Versions.kotlin}"
}

class KotlinTestDeps internal constructor() {
    val common: List<String> = listOf(
        "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}",
        "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
    )
    val jvm = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val js = "org.jetbrains.kotlin:kotlin-test-js:${Versions.kotlin}"
}

class KotlinXDeps internal constructor() {
    val atomicfu = KotlinXAtomicFuDeps()
    val coroutines = KotlinXCoroutinesDeps()
    val serialization = KotlinXSerializationDeps()
}

class KotlinXCoroutinesDeps internal constructor() {
    val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
    val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.kotlinxCoroutines}"
    val ios = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.kotlinxCoroutines}"
    val jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    val js = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.kotlinxCoroutines}"
}

class KotlinXAtomicFuDeps internal constructor() {
    val common = "org.jetbrains.kotlinx:atomicfu-common:${Versions.atomicfu}"
}

class KotlinXSerializationDeps internal constructor() {
    val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.kotlinxSerialization}"
    val jvm = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerialization}"
    val native = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native${Versions.kotlinxSerialization}"
    val js = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.kotlinxSerialization}"
}

class KtorDeps internal constructor() {
    val client = KtorClientDeps()
    val feature = KtorFeatureDeps()
}

class KtorClientDeps internal constructor() {
    val common = "io.ktor:ktor-client:${Versions.ktor}"
    val jvm = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    val js = "io.ktor:ktor-client-js:${Versions.ktor}"
    val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
}

class KtorFeatureDeps internal constructor() {
    val json = KtorFeatureJsonDeps()
    val serialization = KtorFeatureSeralizationDeps()
    val logging = KtorFeatureLoggingDeps()
    val auth = KtorFeatureAuthDeps()
}

class KtorFeatureJsonDeps internal constructor() {
    val jvm = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
    val ios = "io.ktor:ktor-client-json-ios:${Versions.ktor}"
    val common = "io.ktor:ktor-client-json:${Versions.ktor}"
}

class KtorFeatureSeralizationDeps internal constructor() {
    val common = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    val jvm = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    val js = "io.ktor:ktor-client-serialization-js:${Versions.ktor}"
    val native = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
}

class KtorFeatureLoggingDeps internal constructor() {
    val common = "io.ktor:ktor-client-logging:${Versions.ktor}"
    val jvm = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
    val native = "io.ktor:ktor-client-logging-native:${Versions.ktor}"
}

class KtorFeatureAuthDeps internal constructor() {
    val common = "io.ktor:ktor-client-auth:${Versions.ktor}"
    val jvm = "io.ktor:ktor-client-auth-jvm:${Versions.ktor}"
    val native = "io.ktor:ktor-client-auth-native:${Versions.ktor}"
}

class RallyDeps internal constructor() {
    val kinject = KinjectDeps()
    val store = "com.rallyhealth.store:store:${Versions.store}"
    val storeCoroutines = "com.rallyhealth.store:store-coroutines:${Versions.store}"
    val storeMemory = "com.rallyhealth.store:store-memory:${Versions.store}"
}

class KinjectDeps internal constructor() {
    val common = "com.rallyhealth.kinject:kinject:${Versions.kinject}"
    val jvm = "com.rallyhealth.kinject:kinject-jvm:${Versions.kinject}"
}
