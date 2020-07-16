create table city_info
(
    id      bigserial not null
        constraint city_info_pk
            primary key,
    country varchar(30),
    capital varchar(30),
    info    varchar(350)
);

alter table city_info
    owner to test;

create unique index city_info_id_uindex
    on city_info (id);