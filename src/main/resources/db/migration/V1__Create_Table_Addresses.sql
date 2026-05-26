create table if not exists addresses (
    id bigint not null auto_increment,
    postal_code varchar(9) not null,
    street varchar(100) not null,
    number varchar(10),
    complement varchar(100),
    city varchar(100) not null,
    state varchar(2) not null,
    country varchar(50) not null,

    primary key(id)
);