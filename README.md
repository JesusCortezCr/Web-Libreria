# 📚 Plataforma de Publicaciones y Foro para Escritores Independientes

> Aplicación web desarrollada con Spring Boot, Thymeleaf y MySQL que permite a escritores independientes compartir documentos, archivos y participar en foros mediante reseñas y comentarios. Ideal para fomentar la lectura, el aprendizaje colaborativo y la escritura libre.


---

## 📚 Tabla de Contenidos

- [🧠 Descripción del Proyecto](#-descripción-del-proyecto)
- [🛠️ Tecnologías Utilizadas](#️-tecnologías-utilizadas)
- [🧩 Arquitectura del Software](#-arquitectura-del-software)
- [🔑 Funcionalidades Principales](#-funcionalidades-principales)
- [⚙️ Requisitos del Sistema](#️-requisitos-del-sistema)
- [💡 Lo que Aprendí](#-lo-que-aprendí)


---

## 🧠 Descripción del Proyecto

Este sistema fue creado para brindar a escritores independientes y lectores una **plataforma libre** donde puedan:

- Subir documentos o archivos personales
- Leer publicaciones de otros autores
- Comentar y dejar reseñas constructivas
- Marcar publicaciones como favoritas

Es un espacio abierto, intuitivo y colaborativo para fomentar el intercambio de conocimientos escritos entre personas de todo nivel.

---

## 🛠️ Tecnologías Utilizadas

- ☕ **Spring Boot** (con JPA, Security)
- 🐬 **MySQL** (Base de datos relacional)
- 🧠 **Thymeleaf** (Plantillas dinámicas HTML)
- 🎨 **Bootstrap** (Estilo responsivo)
- 🌐 HTML, CSS, JavaScript

---

## 🧩 Arquitectura del Software

La arquitectura del sistema sigue el patrón **MVC (Modelo - Vista - Controlador)** para mantener una estructura clara, escalable y mantenible:


### Flujo general:
- El usuario inicia sesión y accede según su rol.
- Las vistas se adaptan a cada tipo de usuario.
- El backend se comunica con MySQL mediante JPA/Hibernate.
- Se asegura la protección de rutas y formularios mediante Spring Security.

---

## 🔑 Funcionalidades Principales

### 👤 ROLE_CLIENTE

- [x] Crear cuenta y autenticarse
- [x] Publicar archivos o documentos personales
- [x] Leer archivos de otros usuarios
- [x] Comentar publicaciones
- [x] Marcar publicaciones como favoritas
- [x] Ver sus favoritos y publicaciones anteriores

### 🧑‍💼 ROLE_ADMIN (Administrador del sistema)

- [x] Login administrativo
- [x] Mantenimiento completo de usuarios (CRUD)
- [x] Acceso al historial de publicaciones

---

## ⚙️ Requisitos del Sistema

- ☕ Java 17 o superior
- 🐬 MySQL o XAMPP
- 📦 Maven con dependencias de Spring Boot
- 🌐 Navegador actualizado

---
💡 Lo que Aprendí
Este proyecto me permitió experimentar con el desarrollo de software libre para cualquier tipo de usuario, reforzando habilidades clave como:

Implementación de roles y seguridad

Uso de plantillas dinámicas con Thymeleaf

Creación de relaciones en base de datos

Interacción entre usuarios de forma segura y ordenada

Buenas prácticas de desarrollo web backend + frontend

