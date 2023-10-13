-- create the user table
create table if not exists "user"
(
    id                   uuid primary key,
    username             varchar(32) not null,
    password             varchar(64) not null,
    enabled              boolean,
    last_login_timestamp timestamp
);

-- create the authority table
create table authority
(
    user_id   uuid not null references "user" (id),
    authority varchar(64),
    primary key (user_id, authority)
);

-- create the news table
create table if not exists news
(
    id               uuid primary key,
    title            varchar(255),
    content          text,
    author_id        uuid references "user" (id),
    create_timestamp timestamp default now(),
    update_timestamp timestamp default now()
);

-- create the comment table
create table if not exists comment
(
    id               uuid primary key,
    content          text,
    user_id          uuid references "user" (id),
    news_id          uuid references news (id),
    create_timestamp timestamp default now(),
    update_timestamp timestamp default now()
);

-- indexes for optimization
create index if not exists idx_news_author_id on news (author_id);
create index if not exists idx_comment_user_id on comment (user_id);
create index if not exists idx_comment_news_id on comment (news_id);
