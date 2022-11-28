dependencies {
    implementation(project(":cypher-book-api-application"))
    implementation(project(":cypher-book-api-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
    runtimeOnly("org.postgresql:postgresql")
}
