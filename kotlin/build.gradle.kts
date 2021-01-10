import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradle_version = "6.8"

java.sourceCompatibility = JavaVersion.VERSION_15

buildscript {
	extra.apply {
		set("kotlinVersion", "1.4.21")
	}

	repositories {
		mavenCentral()
		gradlePluginPortal()
		maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
	}

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("kotlinVersion")}")
	}
}

plugins {
	id("java")
	id("application")
	kotlin("jvm") version("${property("kotlinVersion")}")
	id("io.spring.dependency-management") version("1.0.10.RELEASE")
}

group = "com.my"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
}

application {
	mainClassName = "com.my.ippv.MainKt"
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:${property("kotlinVersion")}")
}

tasks.withType<KotlinCompile> {
        kotlinOptions {
                jvmTarget = "15"
                freeCompilerArgs = listOf("-Xjsr305=strict")
        }
}

tasks.wrapper {
	gradleVersion = gradle_version
	distributionType = Wrapper.DistributionType.ALL
}
