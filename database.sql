create database produtodb;
use produtodb;
create table tbproduto(
id int auto_increment primary key,
nomeproduto varchar(50) not null,
descricaoproduto text(100) not null,
fabricante varchar(50) not null,
quantidade int not null,
preco decimal(10,2) not null
);
insert into tbproduto(nomeproduto,descricaoproduto,fabricante,quantidade,preco)
values('trakinas','bolacha com recheio','mondelez',100,2.49);

select * from tbproduto;