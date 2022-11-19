import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.7.21"
val slf4jVersion = "2.0.4"
val log4jSlf4j2Version = "2.19.0"
val junitVersion = "5.9.1"

val awsSdkVersion = "2.18.20"
val kafkaVersion = "3.3.1"
val kodeinVersion = "7.16.0"
val mapstructVersion = "1.5.3.Final"
val mskIamVersion = "1.1.5"

plugins {
    id("java")
    kotlin("jvm") version "1.7.21"
    id("org.sonarqube") version "3.5.0.2730"
    kotlin("plugin.spring") version "1.7.21" apply (false)
    id("org.springframework.boot") version "2.7.5" apply (false)
    id("io.spring.dependency-management") version "1.1.0" apply (false)
}

allprojects {
    group = "com.ivyxjc"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.apache.logging.log4j:log4j-slf4j2-impl:$log4jSlf4j2Version")

        testImplementation(platform("org.junit:junit-bom:$junitVersion"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }


    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    sonarqube {
        properties {
            property("sonar.projectKey", "ivyxjc_jvm-lang-tutorial")
            property("sonar.organization", "ivyxjc")
            property("sonar.host.url", "https://sonarcloud.io")
            property(
                "sonar.coverage.exclusions",
                "**/*Model.java,**/*Entity.java"
            )
        }
    }
}

project(":kafka:demo") {
    dependencies {
        implementation("org.apache.logging.log4j:log4j-slf4j2-impl:$log4jSlf4j2Version")

        implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
        implementation("software.amazon.msk:aws-msk-iam-auth:$mskIamVersion")
    }
}
