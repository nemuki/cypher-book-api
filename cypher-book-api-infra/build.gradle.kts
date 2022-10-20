dependencies {
    implementation(project(":cypher-book-api-application"))
    implementation(project(":cypher-book-api-domain"))

    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("org.postgresql:postgresql")
}
