dependencies {
    implementation(project(":cypher-book-api-application"))
    implementation(project(":cypher-book-api-domain"))
    // infra は web には依存しないが CypherBookApiApplication が web に存在するため依存関係に含める必要がある
    implementation(project(":cypher-book-api-infra"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
