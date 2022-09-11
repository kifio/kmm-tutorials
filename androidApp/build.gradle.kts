plugins {
    id(androidApp)
    kotlin(androidPlugin)
}

android {
    compileSdk = Versions.compile_sdk
    defaultConfig {
        applicationId = "me.kifio.findtime.android"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget="11"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared-ui"))

    implementation(Deps.napier)
    implementation(Deps.material)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.uiUtil)
    implementation(Deps.Compose.tooling)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material_icons)
    implementation(Deps.Compose.runtime)
    implementation(Deps.Compose.compiler)
    implementation(Deps.Compose.runtime_livedata)
    implementation(Deps.Compose.foundationLayout)
    implementation(Deps.Compose.activity)
}