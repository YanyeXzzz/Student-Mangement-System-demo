create table if not exists student
(
    id          varchar(10) not null
        primary key,
    username    varchar(50) null,
    create_time datetime    not null,
    update_time datetime    not null,
    age         int         null,
    sex         varchar(2)  null,
    class_name  varchar(30) null
);

