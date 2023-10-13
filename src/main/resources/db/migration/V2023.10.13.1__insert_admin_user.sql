insert into news_portal.public.user (id, username, password, enabled)
values ('57353db7-abe1-4ae3-b983-dda243b03ee3', 'admin', 'admin', 'true');

insert into news_portal.public.authority (user_id, authority)
values ('57353db7-abe1-4ae3-b983-dda243b03ee3', 'USER'),
       ('57353db7-abe1-4ae3-b983-dda243b03ee3', 'ADMIN');

create extension if not exists pgcrypto;

update news_portal.public.user
set password = crypt(news_portal.public.user.password, gen_salt('bf'));