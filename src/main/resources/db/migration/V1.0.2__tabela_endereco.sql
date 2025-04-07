create sequence seq_endereco start 1 increment 1;

create table endereco (
    end_id         bigserial,
    end_tipo_logradouro varchar(50) not null,
    end_logradouro  varchar(200) not null,
    end_numero      numeric,
    end_bairro      varchar(100) not null,
    cid_id          bigserial not null ,

    constraint pk_endereco primary key (end_id),
    constraint fk_endereco_cidade foreign key (cid_id) references cidade (cid_id)
)