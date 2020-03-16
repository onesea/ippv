buildscript {
	repositories {
		mavenCentral()
	}
}

plugins {
	id("java")
	id("application")
}


group = "com.my"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_14

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.guava:guava:+")
	implementation("org.bouncycastle:bcprov-jdk15on:+")
	implementation("org.bouncycastle:bcpkix-jdk15on:+")
}

application {
	mainClassName = "com.my.ippv.Main"
}

tasks.wrapper {
  gradleVersion = "6.3-rc-1"
  distributionType = Wrapper.DistributionType.ALL
}

