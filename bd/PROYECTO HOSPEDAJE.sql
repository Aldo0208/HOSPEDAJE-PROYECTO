-- borra la bd si existe
DROP DATABASE IF EXISTS CiberHospedaje;
-- creamos la bd
CREATE DATABASE CiberHospedaje;
-- activamos la bd
USE CiberHospedaje;

create table tb_tipos(
id_tipo  int not null primary key,
des_tipo varchar(20)
);

create table tb_categoria(
id_categoria int not null primary key,
pre_categoria varchar(20)
);

create table tb_estado(
id_estado  int not null primary key,
des_estado varchar(20)
);

CREATE TABLE tb_cliente(
codigo  int auto_increment,
nombre varchar(15),
apellido varchar(25),
telefono  int,
dni    int,
primary key (codigo)
);

create table tb_categorias(
idtipo		int not null primary key,
descripcion varchar(45)
);


create table tb_habitaciones(
cod_habi      char(5) not null,
categoria int DEFAULT 1,
precio decimal(8,2),
estado int DEFAULT 1,
primary key (idhabi), 
foreign key (categoria) references tb_categorias(idcategoria),
foreign key (estado) references tb_estado(idestado)
);

create table tb_productos(
cod_producto      char(5) not null,
nomprod  varchar(45),
descripcion varchar(45),
stock		int,
precio		decimal(8,2),
primary key (cod_producto)
);

create table tb_reg_atencion(
cod_ate char(5) not null,
fch_ate    date,
cod_cliente  int not null,
cod_producto int not null,
total_bol decimal(8,2),
primary key (cod_ate),
foreign key (cod_cliente) references tb_cliente(codigo),
foreign key (cod_producto) references tb_producto(cod_producto)
);

create table tb_reg_hospedaje(
cod_hos char(5) not null,
fch_hos    date,
cod_cliente  int not null,
cod_habitacion int not null,
primary key (cod_hos),
foreign key (cod_cliente) references tb_cliente(codigo),
foreign key (cod_habi) references tb_habitaciones(cod__habi)
);












create table tb_det_boleta(
num_bol     char(5) not null,
idprod      char(5) not null,
cantidad    int,
preciovta   decimal(8,2),
importe		decimal(8,2),
primary key (num_bol,idprod),
foreign key (num_bol) references tb_cab_boleta(num_bol),
foreign key (idprod) references tb_habitaciones(idprod)
);

-- inserts
insert into tb_tipos values (1, 'Administrador');
insert into tb_tipos values (2, 'Cliente');
insert into tb_tipos values (3, 'Cajero');

insert into tb_usuarios values (1,'Tito', 'Siber','U001', '10001', curdate(),2,1);
insert into tb_usuarios values (2,'Zoila', 'Baca','U002', '10002', curdate(),2,1);
insert into tb_usuarios values (null,'Pedro','Navaja','C001', '10001', curdate(),3,1);
insert into tb_usuarios values (10,'Jose', 'Atuncar','ADMI', 'ADMIN', curdate(),1,1);
insert into tb_usuarios values (null,'Marce', 'Odebrech','CAJA', 'CAJA2', curdate(),3,2);

insert into tb_categorias values (1, 'Pastillas');
insert into tb_categorias values (2, 'Jarabe');
insert into tb_categorias values (3, 'Cremas');
insert into tb_categorias values (4, 'Tocador');
insert into tb_categorias values (5, 'Cuidado');
insert into tb_categorias values (6, 'Otros');

insert into tb_productos values ('P0001','Panadol cj 10',20,1.85,1,1);
insert into tb_productos values ('P0002','Curitas unidad',100,1.0,3,1);
insert into tb_productos values ('P0003','Kita tos',80,15.0,2,1);
insert into tb_productos values ('P0004','Achiz',120,1.0,1,1);
insert into tb_productos values ('P0005','Jaboncillo cj',120,1.0,3,1);
insert into tb_productos values ('P0006','Termometro',80,2.8,5,1);
insert into tb_productos values ('P0007','Panadol jarabe pq',40,10.5,2,1);
insert into tb_productos values ('P0008','Antalgina',55,1.8,1,1);
insert into tb_productos values ('P0009','Ibuprofeno',60,15.0,4,1);
insert into tb_productos values ('P0010','Mejoralito NiÃ±os',10,1.5,1,1);
insert into tb_productos values ('P0011','Panadol jarabe',10,1.5,2,1);
insert into tb_productos values ('P0012','Jabon Neko',40,8.5,4,1);
insert into tb_productos values ('P0013','Pañales x 24u',10,1.5,5,1);
insert into tb_productos values ('P0014','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0015','Champu Amigo',50,0.99,5,1);
insert into tb_productos values ('P0016','Mejoralito',10,1.5,4,1);
insert into tb_productos values ('P0017','SinDolor',23,1.5,6,1);
insert into tb_productos values ('P0018','Mejoralito Forte',10,1.5,2,1);
insert into tb_productos values ('P0019','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0020','Mejoralito Forte',10,1.5,3,1);
insert into tb_productos values ('P0021','Mejoralito Forte',10,1.5,2,1);
insert into tb_productos values ('P0022','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0023','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0024','Mejoralito Forte',10,1.5,2,1);
insert into tb_productos values ('P0025','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0026','Mejoralito Forte',10,1.5,3,1);
insert into tb_productos values ('P0027','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0028','Mejoralito Forte',10,1.5,4,1);
insert into tb_productos values ('P0029','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0030','Mejoralito Forte',10,1.5,5,1);
insert into tb_productos values ('P0031','Mejoralito UForte',10,0.99,5,1);
