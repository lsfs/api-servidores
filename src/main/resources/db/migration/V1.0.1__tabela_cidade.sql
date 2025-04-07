create sequence seq_cidade start 1 increment 1;

create table cidade
(
    cid_id         bigserial,
    cid_nome       varchar(200) not null,
    cid_uf         char(2) not null,
    constraint pk_cidade primary key (cid_id)
)