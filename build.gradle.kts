import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("io.realm.kotlin") version "1.8.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.compose.material3:material3-desktop:1.5.0-dev1043")
                implementation("com.marosseleng.android:compose-material3-datetime-pickers:0.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.realm.kotlin:library-base:1.8.0")
                implementation("io.realm.kotlin:library-sync:1.8.0")
                implementation(kotlin("reflect"))
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "AeroportProb1"
            packageVersion = "1.0.0"
        }
    }
}
