import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	extra.apply {
		set("kotlinVersion", "1.3.61")
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
	id("io.spring.dependency-management") version("1.0.5.RELEASE")
}

dependencyManagement {
     imports {
          mavenBom("io.projectreactor:reactor-bom:Bismuth-RELEASE")
     }
}


group = "com.my"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_13

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

application {
	mainClassName = "com.my.ippv.MainKt"
}

dependencies {
	implementation("com.google.guava:guava:+")
	implementation("io.projectreactor:reactor-core")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${property("kotlinVersion")}")
}

tasks.withType<KotlinCompile> {
        kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "12"
        }
}

tasks.wrapper {
	gradleVersion = "6.2.2"
	distributionType = Wrapper.DistributionType.ALL
}
