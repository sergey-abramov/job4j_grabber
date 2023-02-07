create table post (
    id serial primary key,
    post_name text,
    link varchar(255) unique,
    description text,
    created_date timestamp
);