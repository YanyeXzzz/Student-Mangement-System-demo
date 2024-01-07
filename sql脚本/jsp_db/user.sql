create table if not exists user
(
    username    varchar(20) null,
    password    varchar(20) null,
    create_time datetime    null,
    update_time datetime    null,
    id          int auto_increment
        primary key
);

