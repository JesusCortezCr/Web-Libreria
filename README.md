# 📚 Plataforma de Publicaciones y Foro para Escritores Independientes

> Aplicación web desarrollada con Spring Boot, Thymeleaf y MySQL que permite a escritores independientes compartir documentos, archivos y participar en foros mediante reseñas y comentarios. Ideal para fomentar la lectura, el aprendizaje colaborativo y la escritura libre.

![Portada del Proyecto](./screenshots/cover.png)

---

## 📚 Tabla de Contenidos

- [📸 Capturas / Demo](#-capturas--demo)
- [🧠 Descripción del Proyecto](#-descripción-del-proyecto)
- [🛠️ Tecnologías Utilizadas](#️-tecnologías-utilizadas)
- [🧩 Arquitectura del Software](#-arquitectura-del-software)
- [🔑 Funcionalidades Principales](#-funcionalidades-principales)
- [⚙️ Requisitos del Sistema](#️-requisitos-del-sistema)
- [🚀 Instalación Local](#-instalación-local)
- [📦 Estructura de Carpetas](#-estructura-de-carpetas)
- [📈 Futuras Mejoras](#-futuras-mejoras)
- [💡 Lo que Aprendí](#-lo-que-aprendí)
- [📬 Contacto](#-contacto)
- [📄 Licencia](#-licencia)

---

## 📸 Capturas / Demo

🔗 **Demo en construcción**

| Página Principal | Vista de Publicación | Reseñas |
|------------------|----------------------|---------|
| ![](./screenshots/home.png) | ![](./screenshots/publicacion.png) | ![](./screenshots/comentarios.png) |

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

## 🚀 Instalación Local

### Paso 1: Clonar el repositorio

```bash
git clone https://github.com/jesuseduardocortezramos/plataforma-publicaciones.git
cd plataforma-publicaciones
