create schema if not exists chat_schema;

create table if not exists chat_schema.chat_user (
    id serial primary key not null,
    login varchar not null,
    password varchar not null
);

create table if not exists chat_schema.chat_room (
    id serial primary key not null,
    name varchar not null,
    owner integer not null references chat_schema.chat_user(id)
);

create table if not exists chat_schema.message (
    id serial primary key not null,
    author integer not null references chat_schema.chat_user(id),
    room integer not null references chat_schema.chat_room(id),
    content text not null,
    message_date_time timestamp
);


