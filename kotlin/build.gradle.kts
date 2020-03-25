import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradle_version = "6.3"

buildscript {
	extra.apply {
		set("kotlinVersion", "1.3.70")
	}

	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/snapshot") }
		maven { url = uri("https://repo.spring.io/milestone") }
	}

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("kotlinVersion")}")
	}
}

plugins {
	id("java")
	id("application")
	kotlin("jvm") version("${property("kotlinVersion")}")
	id("io.spring.dependency-management") version("1.0.9.RELEASE")
}

dependencyManagement {
     imports {
          mavenBom("io.projectreactor:reactor-bom:Bismuth-RELEASE")
     }
}


group = "com.my"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_14

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

application {
	mainClassName = "com.my.ippv.MainKt"
}

dependencies {
	//implementation("com.google.guava:guava:+")
	//implementation("io.projectreactor:reactor-core")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:${property("kotlinVersion")}")
}

tasks.withType<KotlinCompile> {
        kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "13"
        }
}

tasks.wrapper {
	gradleVersion = gradle_version
	distributionType = Wrapper.DistributionType.ALL
}
