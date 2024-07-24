import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.springframework.boot") version "3.1.2" // Обновленная версия Spring Boot
    id("io.spring.dependency-management") version "1.1.0" // Обновленная версия
    kotlin("jvm") version "1.8.22" // Обновленная версия Kotlin
    kotlin("plugin.spring") version "1.8.22"
    application
    id("com.github.ben-manes.versions") version "0.48.0"
}

group = "exercise"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("exercise.Application")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        showStandardStreams = true
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17" // Настройка целевой версии JVM для Kotlin
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Настройка версии JVM для Java
    }
}
