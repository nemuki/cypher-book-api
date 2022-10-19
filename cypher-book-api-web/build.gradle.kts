dependencies {
    implementation(project(":cypher-book-api-application"))
    implementation(project(":cypher-book-api-domain"))

    // CypherBookApiApplication が infra を DI できないので追加している
    // Cypher では許容されているそう
    // https://github.com/MxShun/cypher-book/pull/21#discussion_r744343872
    implementation(project(":cypher-book-api-infra"))
}
