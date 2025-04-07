create sequence seq_servidor_efetivo start 1 increment 1;

create table servidor_efetivo
(
    se_matricula    varchar(20) not null,
    pes_id         bigserial not null,
    constraint fk_servidor_efetivo foreign key (pes_id) references pessoa (pes_id)
);

create sequence seq_lotacao start 1 increment 1;


create table lotacao (
                         lot_id         bigserial,
                         pes_id         bigserial not null,
                         unid_id       bigserial not null,
                         lot_data_lotacao date not null,
                         lot_data_remocao date,
                         lot_portaria varchar(100),

                         constraint pk_lotacao primary key (lot_id)
    ,constraint fk_lotacao_pessoa foreign key (pes_id) references pessoa (pes_id)
    ,constraint fk_lotacao_unidade foreign key (unid_id) references unidade (unid_id)
)