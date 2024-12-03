
use mysql;

create table member (
    id bigint not null auto_increment,
    name varchar(50),
    age integer,
    primary key(id)
);
  --int, bigint, 등..

insert into member (name, age) values ('홍길동', 20);
