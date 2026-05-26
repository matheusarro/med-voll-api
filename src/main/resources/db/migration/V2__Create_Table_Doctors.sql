create table if not exists doctors (
    id bigint not null auto_increment,
    name varchar(100) not null,
    crm varchar(6) not null unique,
    email varchar(100) not null unique,
    speciality varchar(100) not null,
    address_id bigint not null,

    primary key(id),
    foreign key (address_id) references addresses(id)
);