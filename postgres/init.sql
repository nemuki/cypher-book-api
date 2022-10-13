CREATE SCHEMA IF NOT EXISTS cypher;

CREATE TABLE IF NOT EXISTS cypher.book(
    isbn       varchar(13)  NOT NULL,
    title      varchar(100) NOT NULL,
    author     varchar(100) NOT NULL,
    publisher  varchar(100) NOT NULL,
    price      integer      NOT NULL,
    created_at timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (isbn)
);

INSERT INTO cypher.book VALUES
('9784798142470', 'Spring徹底入門 Spring FrameworkによるJavaアプリケーション開発', '株式会社NTTデータ', '翔泳社', 4400),
('9784297118594', 'Kotlinサーバーサイドプログラミング実践開発', '竹端尚人', '技術評論社', 3608);