drop database if exists cine;
create database cinesa default character set utf8 collate utf8_general_ci;
use cinesa;

create table ciudades (
id int not null auto_increment, nombre varchar(25) null, num_centros int null, activo int null, borrado int null,
primary key (id));

create table centros (
id int not null auto_increment, id_ciudad int null, nombre varchar(25) null, num_salas int null, aforo int null, activo int null, borrado int null,
primary key (id),
foreign key (id_ciudad) references ciudades (id));

create table tipo_roles (
id int not null auto_increment, nombre varchar(20),
primary key (id));

create table usuarios (
id int not null auto_increment, dni varchar(10), nombre varchar(25) null, apellidos varchar(50) null, 
CP varchar(10) null, mail varchar(50) null, movil int null, rol int not null, clave varchar(8) null, activo int null, borrado int null,
primary key (id),
foreign key (rol) references tipo_roles (id));

create table salas (
id int not null auto_increment, id_centro int null, aforo int null,
primary key (id),
foreign key (id_centro) references centros (id));

create table asientos (
id int not null auto_increment, fila int null, columna int null, id_sala int null, id_centro int null,
primary key (id),
foreign key (id_sala) references salas (id),
foreign key (id_centro) references centros (id));

create table peliculas (
id int not null auto_increment, titulo varchar(50) null, duracion int null, idioma varchar(20) null, edad int null, id_sala int null, id_centro int null,
primary key (id),
foreign key (id_sala) references salas(id));

create table premios (
id int not null auto_increment, fecha timestamp default current_timestamp, premiado int not null,
primary key (id),
foreign key (premiado) references usuarios (id));

create table reservas (
id int not null auto_increment, ciudad int not null, centro int not null, sala int not null, fecha date, hora time, fila int null, asiento int null, estado varchar(2), usuario int not null,
primary key (id),
foreign key (ciudad) references ciudades(id),
foreign key (centro) references centros(id),
foreign key(sala) references salas(id),
foreign key(usuario) references usuarios(id));

create table compras (
id int not null auto_increment, fecha date, id_usuario int not null, importe int, id_sala int not null, id_centro int not null, sesion time,
primary key (id),
foreign key (id_usuario) references usuarios(id),
foreign key (id_sala) references salas(id),
foreign key (id_centro) references centros(id));

create table bancos (
id int not null auto_increment, nombre varchar(15),
primary key (id));

create table cuentas (
id int not null auto_increment, id_usuario int not null, id_banco int not null, numCuenta int not null, saldo int not null,
primary key (id),
foreign key (id_usuario) references usuarios (id),
foreign key (id_banco) references bancos (id));

create table movimientos (
id int not null auto_increment, cuenta int not null, saldo int,
primary key (id),
foreign key (cuenta) references cuentas (id));

insert into tipo_roles (id, nombre) values (3, "Cliente");
insert into tipo_roles (id, nombre) values (1, "Administrador");
insert into ciudades (nombre, num_centros, activo, borrado) values ("Getafe",1,1,0);
insert into ciudades (nombre, num_centros, activo, borrado) values ("Leganés",1,1,0);
insert into ciudades (nombre, num_centros, activo, borrado) values ("Arroyomolinos",1,1,0);
insert into ciudades (nombre, num_centros, activo, borrado) values ("Fuenlabrada",1,1,0);
insert into centros (id_ciudad, nombre, num_salas, aforo, activo, borrado) values (1,"Nassica",6,350,1,0);
insert into centros (id_ciudad, nombre, num_salas, aforo, activo, borrado) values (2,"Parquesur",8,550,1,0);
insert into centros (id_ciudad, nombre, num_salas, aforo, activo, borrado) values (3,"Xanadu",10,750,1,0);
insert into centros (id_ciudad, nombre, num_salas, aforo, activo, borrado) values (4,"Isla Azul",6,420,1,0);
insert into usuarios (dni,nombre,apellidos,CP,mail,movil,rol,clave,activo,borrado) values ("789456","Lola","Cruz","12345","dfhs",123456789,3,"pepe",1,0);
insert into usuarios (dni,nombre,apellidos,CP,mail,movil,rol,clave,activo,borrado) values ("123456","Pepe","Franco","12389","fdffsd",789456123,3,"luis",1,0);
insert into usuarios (dni,nombre,apellidos,CP,mail,movil,rol,clave,activo,borrado) values ("456953","Carmen","Pérez","12589","ewttwe",789456123,3,"rosa",1,0);
insert into usuarios (dni,nombre,apellidos,CP,mail,movil,rol,clave,activo,borrado) values ("741852","Luis","Torre","87596","fdrwer",789456123,3,"meme",1,0);
insert into usuarios (dni,nombre,apellidos,CP,mail,movil,rol,clave,activo,borrado) values ("963852","Nere","Moreno","748512","sferre",751986328,1,"lola",1,0);
insert into usuarios (id,dni,nombre,apellidos,CP,mail,movil,rol,clave,activo,borrado) values (10000,"159753","Monica","Montero","1597845","erwefsd",485962589,3,"lola",1,0);
insert into salas (id_centro,aforo) values (1,75);
insert into salas (id_centro,aforo) values (1,75);
insert into salas (id_centro,aforo) values (1,50);
insert into salas (id_centro,aforo) values (1,50);
insert into salas (id_centro,aforo) values (1,50);
insert into salas (id_centro,aforo) values (1,50);
insert into salas (id_centro,aforo) values (2,100);
insert into salas (id_centro,aforo) values (2,50);
insert into salas (id_centro,aforo) values (2,50);
insert into salas (id_centro,aforo) values (2,50);
insert into salas (id_centro,aforo) values (2,50);
insert into salas (id_centro,aforo) values (2,50);
insert into salas (id_centro,aforo) values (2,50);
insert into salas (id_centro,aforo) values (2,150);
insert into salas (id_centro,aforo) values (3,75);
insert into salas (id_centro,aforo) values (3,75);
insert into salas (id_centro,aforo) values (3,100);
insert into salas (id_centro,aforo) values (3,50);
insert into salas (id_centro,aforo) values (3,50);
insert into salas (id_centro,aforo) values (3,150);
insert into salas (id_centro,aforo) values (3,50);
insert into salas (id_centro,aforo) values (3,75);
insert into salas (id_centro,aforo) values (3,50);
insert into salas (id_centro,aforo) values (3,75);
insert into salas (id_centro,aforo) values (4,80);
insert into salas (id_centro,aforo) values (4,60);
insert into salas (id_centro,aforo) values (4,60);
insert into salas (id_centro,aforo) values (4,80);
insert into salas (id_centro,aforo) values (4,70);
insert into salas (id_centro,aforo) values (4,70);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Pocahontas",102,"Castellano",4,1,1);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Lolita",130,"Francés",18,2,1);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Terminator",115,"Castellano",10,3,1);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("In time",140,"Castellano",16,4,1);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("El Rey León",105,"Castellano",0,5,1);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("El último Samurai",115,"Castellano",12,6,1);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Los Simpsons",120,"Inglés",14,1,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Avatar",150,"Castellano",6,2,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("V de vendetta",110,"Subtitulada",18,3,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("El hombre sin sombra",112,"Castellano",18,4,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("It",120,"Castellano",18,5,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Saw",118,"Castellano",18,6,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("WoW",132,"Inglés",12,7,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Celda 211",105,"Castellano",16,8,2);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Dando la nota",110,"Castellano",12,1,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Dando la nota 2",125,"Castellano",12,2,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Dando la nota 3",120,"Subtitulada",12,3,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("El lobo",104,"Castellano",18,4,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Fiesta de las salchichas",101,"Castellano",18,5,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Resident Evil",113,"Castellano",16,6,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Predator",115,"Inglés",16,7,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Mar adentro",130,"Castellano",15,8,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Tiburón",109,"Castellano",18,9,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("La brújula dorada",122,"Castellano",6,10,3);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("300",136,"Castellano",12,1,4);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Frozen",106,"Castellano",2,2,4);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Scream",114,"Castellano",18,3,4);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Las tortugas Ninja",118,"Castellano",6,4,4);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Dos policías rebeldes",110,"Castellano",16,5,4);
insert into peliculas (titulo,duracion,idioma,edad,id_sala, id_centro) values ("Hostage",121,"Inglés",16,6,4);
insert into bancos (nombre) values ("BBVA");
insert into bancos (nombre) values ("BSCH");
insert into cuentas (id_usuario,id_banco,numCuenta,saldo) values (1,1,123456789,100);
insert into cuentas (id_usuario,id_banco,numCuenta,saldo) values (2,1,456789123,100);
insert into cuentas (id_usuario,id_banco,numCuenta,saldo) values (3,2,159753468,100);
insert into cuentas (id_usuario,id_banco,numCuenta,saldo) values (4,2,785958215,100);
