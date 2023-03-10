
import com.kobylynskyi.graphql.codegen.model.GeneratedLanguage
import io.github.kobylynskyi.graphql.codegen.gradle.GraphQLCodegenGradleTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.4"
    id("io.spring.dependency-management") version "1.1.0"

    kotlin("jvm") version "1.8.10"
    kotlin("plugin.spring") version "1.8.10"

    id("io.github.kobylynskyi.graphql.codegen") version "5.6.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/snapshot")
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.graphql:spring-graphql:1.2.0-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
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

tasks.named<GraphQLCodegenGradleTask>("graphqlCodegen") {
    // all config options:
    // https://github.com/kobylynskyi/graphql-java-codegen/blob/master/docs/codegen-options.md
    outputDir = File("$buildDir/generated")

    apiPackageName = "com.example.graphql.api"
    modelPackageName = "com.example.graphql.model"

//    parentInterfaces {
//        queryResolver = "graphql.kickstart.tools.GraphQLQueryResolver"
//        mutationResolver = "graphql.kickstart.tools.GraphQLMutationResolver"
//        subscriptionResolver = "graphql.kickstart.tools.GraphQLSubscriptionResolver"
//        resolver = "graphql.kickstart.tools.GraphQLResolver<{{TYPE}}>"
//    }

    modelNameSuffix = "GQL"

    addGeneratedAnnotation = true

    fieldsWithResolvers = setOf("User.comments")

    generatedLanguage = GeneratedLanguage.KOTLIN
    generateApis = true
    generateEqualsAndHashCode = true
    subscriptionReturnType = "org.reactivestreams.Publisher"
//    supportUnknownFields = true
    unknownFieldsPropertyName = "additionalFields"
}

// Automatically generate GraphQL code on project build:
sourceSets {
    getByName("main").java.srcDirs("$buildDir/generated")
}

// Add generated sources to your project source sets:
tasks.named<KotlinCompile>("compileKotlin") {
    dependsOn("graphqlCodegen")
}
