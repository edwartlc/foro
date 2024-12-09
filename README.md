<h1>Foro Hub</h1>

![Badge en Desarollo](https://img.shields.io/badge/status-terminado-green)
![Badge forks](https://img.shields.io/badge/forks-1-blue)

Foro Hub es una replica a nivel de backend de la gestión de temas del foro de una plataforma de educación virtual, 
mediante una API REST usando Spring Boot.

> [!IMPORTANT]
> ## Entorno de desarrollo:
> Java JDK versión 21,
> Spring Boot 3.3.5,
> Maven 4,
> Dependencias: Java jwt, Lombok, Spring Web, Spring Boot DevTools, Spring Data JPA, Flyway Migration, MySQL Driver, Validation, 
> Spring Security, Springdoc Openapi
> IDE IntelliJ IDEA,
> MySQL versión 8

## Utilizando el proyecto
1. Para utilizar la aplicación clone el código fuente:
```
git clone https://github.com/edwartlc/foro.git
```
2. Configure el entorno de desarrollo.
3. Explore la API web Gutendex:
```
https://gutendex.com
```
4. Ejecute el código.
5. Cree la base de datos en PostgreSQL.
    
> [!NOTE]
> Visite mi [Sitio GitHub](https://github.com/edwartlc)

## Funcionalidades
La API está enfocada en la implementación de las temáticas de un foro, siguiendo las mejores prácticas del modelo REST y permitiendo 
a los usuarios las siguientes funcionalidades: 
1. Crear un nuevo tema.
2. Mostrar todos los temas creados.
3. Mostrar un tema específico.
4. Actualizar un tema.
5. Eliminar un tema.

Además, permite la persistencia de la información en una base de datos y cuenta con restricción del acceso a través del servicio 
de autenticación de usarios.

![Screenshot 2024-12-09 135235](https://github.com/user-attachments/assets/dc61a2b0-c9b1-4d00-8862-fe82898e8c7b)
