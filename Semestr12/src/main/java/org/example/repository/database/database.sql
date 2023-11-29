drop table if exists user_feedback;
drop table if exists task;
drop table if exists feedback;
drop table if exists users;
drop table if exists examples;


create table users(
    id bigserial primary key,
    email varchar unique not null,
    first_name varchar not null,
    second_name varchar not null,
    hash_password varchar not null,
    role varchar not null
);

create table task(
    id bigserial primary key,
    user_id bigint not null ,
    description varchar not null,
    foreign key (user_id) references users(id)
);

create table feedback(
    id bigserial primary key,
    description varchar not null,
    author_id bigint not null,
    foreign key (author_id) references users(id)
);

create table user_feedback(
    user_id bigint not null,
    feedback_id bigint not null,
    foreign key (user_id) references users(id),
    foreign key (feedback_id) references feedback(id)
);

create table examples(
    id bigserial primary key,
    title varchar not null,
    description varchar not null,
    git_url varchar
);