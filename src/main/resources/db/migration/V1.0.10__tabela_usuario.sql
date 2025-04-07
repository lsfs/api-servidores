create sequence seq_usuario;

create table usuario
(
    id        bigserial ,
    email     varchar(50) not null,
    senha    varchar(200) not null,

    constraint pk_usuario primary key (id)
);