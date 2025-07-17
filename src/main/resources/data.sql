--Tabla rol insercion
insert into rol(id_rol,nombre) values (1, 'cliente'),(2,'autor');

--Tabla usuario insercion
insert into usuario(id_usuario, nombre, apellido, correo, contrasenia, telefono, id_rol)values
(1, 'daniel', 'martinez', 'dani@gmail.com', '111', '999999999', 1),
(2, 'juana', 'perez', 'jua@gmail.com', '222','999999998' , 2);