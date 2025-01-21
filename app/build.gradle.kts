import com.google.protobuf.gradle.GenerateProtoTask
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //kotlin序列化(serialisation)
    kotlin("plugin.serialization") version "1.9.23"

    //依赖注入(dependency injection)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

    //protobuf插件
    alias(libs.plugins.protobuf)
}

    android {
    namespace = "com.example.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.app"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true

        //开启buildConfig
        buildConfig = true
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.navigation.compose)

    //kotlin序列化(serialisation)
    //https://kotlinlang.org/docs/serialization.html
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    //region 网络框架
    //https://github.com/square/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //网络框架日志框架
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //类型安全网络框架
    //https://github.com/square/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //让Retrofit支持Kotlinx Serialization
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    //endregion

    //图片加载框架
    //https://github.com/coil-kt/coil
    implementation("io.coil-kt:coil-compose:2.6.0")
    testImplementation(libs.androidx.espresso.core)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    //region 依赖注入
    //https://developer.android.google.cn/training/dependency-injection/hilt-android?hl=zh-cn
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    //endregion

    compileOnly(libs.ksp.gradlePlugin)

    //自定义toast提示框架
    //https://github.com/tfaki/ComposableSweetToast
    implementation("com.github.tfaki:ComposableSweetToast:1.0.1")

    //数据存储
    //https://developer.android.google.cn/topic/libraries/architecture/datastore?hl=zh-cn#prefs-vs-proto
    implementation("androidx.datastore:datastore:1.0.0")

    implementation(libs.protobuf.kotlin.lite)

    val androidx_media3_version = "1.2.1"
    implementation("androidx.media3:media3-exoplayer:$androidx_media3_version")
    implementation("androidx.media3:media3-datasource:$androidx_media3_version")
    implementation("androidx.media3:media3-ui:$androidx_media3_version")
    implementation("androidx.media3:media3-session:$androidx_media3_version")
    implementation("androidx.media3:media3-cast:$androidx_media3_version")

    //browser?.getChildren()?.await()
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.7.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Mockito 核心库
    testImplementation("org.mockito:mockito-core:5.5.0")

    // Mockito-Kotlin 支持库
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")

    // JUnit 5 测试框架
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    // Android 测试支持库（可选）
    testImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    testImplementation("org.robolectric:robolectric:4.10.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.1")
    testImplementation("junit:junit:4.13.2")

}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}
//https://github.com/google/dagger/issues/4097#issuecomment-1763781846
androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val protoTask =
                project.tasks.getByName("generate" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Proto") as GenerateProtoTask

            project.tasks.getByName("ksp" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Kotlin") {
                dependsOn(protoTask)
                (this as org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool<*>).setSource(
                    protoTask.outputBaseDir
                )
            }
        }
    }
}