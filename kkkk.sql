USE master
go
IF EXISTS(select * from sys.databases where name='proyecto_iop')
alter database proyecto_iop set single_user with rollback immediate
alter database proyecto_iop set multi_user
DROP DATABASE proyecto_iop
go
create database proyecto_iop
go
use proyecto_iop
go
create table productos(
id int identity(1,1) not null primary key,
codigoProducto varchar(100) not null,
productos varchar(100) not null,
c_costo decimal(10,2) not null,
c_costoPreparacion decimal(10,2) not null default 0,
c_costoAlmacenaje decimal(10,2) not null default 0,
c_costoUnidadPerdida decimal(10,2) not null default 0,
cantidadMinimaPorPedido int not null default 0,
inventarioInicial int not null default 0
)
go
create table periodos(
id int identity(1,1) not null primary key ,
periodos varchar(100) not null
)
go
create table Demanda (
id int identity primary key ,
periodo_id int references periodos,
producto_id int references productos,
productos varchar(100) not null,
periodos varchar(100) not null,
demanda int not null default 0
)
go
create table compra(
id int identity(1,1) not null primary key,
producto varchar(100) not null,
periodo varchar(100) not null,
compra int not null
)
go
create table inventario(
id int identity(1,1) not null primary key,
producto varchar(100) not null,
periodo varchar(100) not null,
inventario int not null
);
insert into productos(codigoProducto,productos,c_costo,c_costoAlmacenaje,inventarioInicial,cantidadMinimaPorPedido) 
values ('000001','PRODUCTO 1',1.15,2.14,4000,1000);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000002','ORAMIN-F CAPS',6.73,0.80);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000003','NEONYPOL CAPS BLANDAS',7.87,0.82);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000004','3-GEL SUSP. ORAL SACHETX10 ML',6.32,2.03);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000005','U.S MILK GOLD 1 LATAX400GR',15.14,8.1);

--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000006','MEGESTROL SOBRE 200MG/5ML',2.59,1.60);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000007','AUGMEX DUO SUSP 200MG/28.5MG',7.08,0.68);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000008','B-VAT 2ML AMPX10 ',5.96,0.68);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000009','SUPRAXOM LATA X400GR',15.87,8.16);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000010','SUPRAXOM  LATAX900GR',32.3,13.14);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000011','KEFDYL 500MG CAPSX100',29.6,1.31);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000012','KELEXYN 500MG CAPSX100',29.6,1.31);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000013','MEGESTROL 240ML FCO',30.54,1.32);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000014','SUPLEMENTO PRENATAL CAPS',10.42,3.69);
--insert into producto(codigoProducto,nombre,c_costo,c_costoAlmacenaje) 
--values ('000015','ZODYLEX AMP',2.73,3.22);


insert into Periodos(periodos) values ('Enero');
insert into Periodos(periodos) values ('Febrero');
insert into Demanda(producto_id,periodo_id,demanda,productos,periodos) 
values (1,1,1000,'PRODUCTO 1','Enero');
insert into Demanda(producto_id,periodo_id,demanda,productos,periodos) 
values (1,2,4000,'PRODUCTO 1','Febrero');
insert into compra(producto,periodo,compra)
values ('PRODUCTO 1','Enero',0);
insert into compra(producto,periodo,compra)
values ('PRODUCTO 1','Febrero',0);
insert into inventario(producto,periodo,inventario)
values ('PRODUCTO 1','Enero',0);
insert into inventario(producto,periodo,inventario)
values ('PRODUCTO 1','Febrero',0);
select *from demanda;

select *from compra;
select *from inventario;