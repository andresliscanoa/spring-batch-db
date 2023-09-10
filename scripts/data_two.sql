--- PUBLIC SCHEMA

create table if not exists public.users
(
    id bigserial primary key,
    fullName varchar(100) not null,
    gender   varchar(100) not null
);