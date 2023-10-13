# Description

**News Porta**l system. The user can read News. An authorized User can create and edit their own News, as well as leave Comments on other news. The moderator can delete and edit News and Comments.

# User Service

Made using [Spring Security](https://spring.io/projects/spring-security) with [Spring Session JDBC](https://www.baeldung.com/spring-session-jdbc). Passwords are encoded using BCrypt.

| Allowed actions | Unauthorised | User | Admin |
| --- | --- | --- | --- |
| Read news / comments | ✅ | ✅ | ✅ |
| Create news / comments | ❌ | ✅ | ✅ |
| Modify and delete news / comments | ❌ | ❌ | ✅ |

# DB Schema

Made using [PostgreSQL](https://www.postgresql.org/) and [Spring Data JPA](https://spring.io/projects/spring-data-jpa). Using [Flyway](https://flywaydb.org/) for DB migrations.

## user

| id | uuid | primary key |
| --- | --- | --- |
| username | varchar(32) | unique not null |
| password | varchar(64) | not null |
| enabled | boolean |  |
| last_login_timestamp | timestamp |  |

## authority

Both fields are composite primary key

| user | foreign key user.id |
| --- | --- |
| authority | varchar(64) |

## news

| id | uuid | primary key |
| --- | --- | --- |
| content | varchar(1024) |  |
| author_id | uuid | foreign key user.id |
| create_timestamp | timestamp |  |
| update_timestamp | timestamp |  |

## comment

| id | uuid | primary key |
| --- | --- | --- |
| content | varchar(1024) |  |
| user_id | uuid | foreign key user.id |
| news_id | uuid | foreign key news.id |
| create_timestamp | timestamp |  |
| update_timestamp | timestamp |  |
