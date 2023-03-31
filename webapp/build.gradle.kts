import java.util.Properties

plugins {
    id("java")
    id("org.springframework.boot") version "2.7.8"
    id("org.liquibase.gradle") version "2.2.0"
}

apply(plugin = "io.spring.dependency-management")

group = "com.chemaev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")
    implementation("org.thymeleaf:thymeleaf-spring5:3.0.15.RELEASE")

    //db
    implementation("org.postgresql:postgresql:42.5.3")

    //lombok
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    //webkjars
    implementation("org.webjars:jquery:3.6.0")
    implementation("org.webjars:bootstrap:4.6.0")
    implementation("org.webjars:webjars-locator-core:0.46")

    //test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("javax.mail:javax.mail-api:1.6.2")

    // liquibase
    implementation("org.liquibase:liquibase-core:4.20.0")
    liquibaseRuntime("org.liquibase:liquibase-core:4.20.0")
    liquibaseRuntime("org.postgresql:postgresql:42.5.3")
    liquibaseRuntime("info.picocli:picocli:4.6.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

var props = Properties()
props.load(file("src/main/resources/liquibase.properties").inputStream())

liquibase {
    activities.register("main") {
        arguments = mapOf(
                "changeLogFile" to props.get("change-log-file"),
                "url" to props.get("url"),
                "username" to props.get("username"),
                "password" to props.get("password"),
                "driver" to props.get("driver-class-name")
        )
    }
}