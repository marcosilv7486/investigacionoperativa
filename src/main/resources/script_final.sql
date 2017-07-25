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
create table CategoriaProducto(
id int identity(1,1) primary key  not null ,
nombre varchar(100) not null
)
go
create table Periodos(
id int identity(1,1) primary key  not null ,
mes varchar(100) not null
)
go
create table Proveedor(
id int identity(1,1) primary key  not null ,
nombre varchar(100) not null
)
go
create table Productos(
id int identity(1,1) primary key  not null ,
codigo varchar(100) not null,
productos varchar(100) not null,
concentracion varchar(100) not null,
presentacion varchar(100) not null,
costoAlmacenaje decimal(10,2) not null,
costoCompra decimal(10,2) not null,
costoPreparacion decimal(10,2) not null,
costoUnidadPerdida decimal(10,2) not null,
inventarioInicial int  not null,
stockMinimoPorPeriodo decimal(10,2) not null,
cantidadMinimaPorPedido int not null,
categoriaProducto_id int references CategoriaProducto,
proveedor_id int references Proveedor
)
go
create table Demanda(
id int identity(1,1) primary key  not null ,
periodos varchar(100) not null,
producto varchar(100) not null,
demanda int not null,
periodo_id int references periodos,
producto_id int references Productos
)
go
create table ResultadoCompra(
id int identity(1,1) primary key  not null ,
compra int not null,
periodo varchar(100) not null,
producto varchar(100) not null
);
create table ResultadoInventario(
id int identity(1,1) primary key  not null ,
inventario int not null,
periodo varchar(100) not null,
producto varchar(100) not null
);
-- data inicial
insert into CategoriaProducto(nombre) values ('Farmaceuticos');
insert into CategoriaProducto(nombre) values ('Alimenticios');
insert into CategoriaProducto(nombre) values ('Comesticos');
insert into CategoriaProducto(nombre) values ('Dispositivos medicos');
insert into CategoriaProducto(nombre) values ('Bebidas Energeticas');
-- data Proveedor
insert into Proveedor(nombre) values ('FARMINDUSTRIA');
insert into Proveedor(nombre) values ('PROQUIDENT S.A.');
insert into Proveedor(nombre) values ('KOREA UNITED PHARM');
insert into Proveedor(nombre) values ('PRIME HEALTH');
insert into Proveedor(nombre) values ('PBM NUTRITIONALS LLC');
insert into Proveedor(nombre) values ('APIPHARMA DOO');
insert into Proveedor(nombre) values ('WOO SHIN MEDICS');
-- data periodos
insert into periodos(mes) values ('I cuatrimestre');
insert into periodos(mes) values ('II cuatrimestre');
insert into periodos(mes) values ('III cuatrimestre');
-- productos
insert into Productos(
codigo,
productos,
concentracion,
presentacion,
costoAlmacenaje,
costoCompra,
costoPreparacion,
costoUnidadPerdida,
inventarioInicial,
stockMinimoPorPeriodo,
cantidadMinimaPorPedido,
categoriaProducto_id,
proveedor_id)
values (
'0000001',
'ORAMIN DRINK',
'150ML',
'LATA',
2.135466667,
1.15,
0,
0,
53693,
27816,
0,
5,
3
);
insert into Productos(
codigo,
productos,
concentracion,
presentacion,
costoAlmacenaje,
costoCompra,
costoPreparacion,
costoUnidadPerdida,
inventarioInicial,
stockMinimoPorPeriodo,
cantidadMinimaPorPedido,
categoriaProducto_id,
proveedor_id)
values (
'0000004',
'U.S MILK GOLD 1 ',
' ',
'LATAX400GR',
8.1536,
15.14,
0,
0,
9114,
0,
2000,
2,
5
);
insert into Productos(
codigo,
productos,
concentracion,
presentacion,
costoAlmacenaje,
costoCompra,
costoPreparacion,
costoUnidadPerdida,
inventarioInicial,
stockMinimoPorPeriodo,
cantidadMinimaPorPedido,
categoriaProducto_id,
proveedor_id)
values (
'0000005',
'SUPRAXOM 1 ',
' ',
'LATAX400GR',
8.1536,
15.14,
0,
0,
5066,
0,
2000,
2,
5
);

insert into Productos(
codigo,
productos,
concentracion,
presentacion,
costoAlmacenaje,
costoCompra,
costoPreparacion,
costoUnidadPerdida,
inventarioInicial,
stockMinimoPorPeriodo,
cantidadMinimaPorPedido,
categoriaProducto_id,
proveedor_id)
values (
'0000007',
'B-VAT',
'2ML',
'APOLLA',
0.8246,
5.96,
0,
0,
6341,
0,
1000,
1,
3
);

insert into Productos(
codigo,
productos,
concentracion,
presentacion,
costoAlmacenaje,
costoCompra,
costoPreparacion,
costoUnidadPerdida,
inventarioInicial,
stockMinimoPorPeriodo,
cantidadMinimaPorPedido,
categoriaProducto_id,
proveedor_id)
values (
'0000007',
'ORAMIN CAPS',
'',
'CAPS',
0.8246,
5.96,
0,
0,
6341,
0,
1000,
1,
3
);
--Demanda
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (1,'ORAMIN DRINK',1,'I cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (1,'ORAMIN DRINK',2,'II cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (1,'ORAMIN DRINK',3,'III cuatrimestre',10000);



insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (2,'ORAMIN DRINK',1,'I cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (2,'ORAMIN DRINK',2,'II cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (2,'ORAMIN DRINK',3,'III cuatrimestre',10000);


insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (3,'ORAMIN DRINK',1,'I cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (3,'ORAMIN DRINK',2,'II cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (3,'ORAMIN DRINK',3,'III cuatrimestre',10000);



insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (4,'ORAMIN DRINK',1,'I cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (4,'ORAMIN DRINK',2,'II cuatrimestre',12000);
insert into Demanda(producto_id,producto,periodo_id,periodos,demanda)
values (4,'ORAMIN DRINK',3,'III cuatrimestre',10000);









