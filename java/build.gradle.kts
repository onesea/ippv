val gradle_version = "6.8-rc-5"

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
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
	mavenCentral()
}

dependencies {
	//implementation("com.google.guava:guava:+")
	//implementation("org.bouncycastle:bcprov-jdk15on:+")
	//implementation("org.bouncycastle:bcpkix-jdk15on:+")
}

application {
	mainClassName = "com.my.ippv.Main"
}

tasks.wrapper {
  gradleVersion = gradle_version
  distributionType = Wrapper.DistributionType.ALL
}
