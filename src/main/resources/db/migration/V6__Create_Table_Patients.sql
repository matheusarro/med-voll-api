create table if not exists patients (
    id bigint not null auto_increment,
    name varchar(100) not null,
    cpf varchar(11) not null unique,
    email varchar(100) not null unique,
    phone varchar(15) not null,
    address_id bigint not null,
    active boolean not null default true,

    primary key(id),
    foreign key (address_id) references addresses(id)
);