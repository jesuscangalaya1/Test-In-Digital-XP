
# Sistema para administrar clientes.

"Clone el repositorio y ejecute el proyecto en su IDE de preferencia.
 La base de datos MYSQL se creará automáticamente al ejecutar el proyecto,
 No olvide configurar las credenciales de la base de datos en el archivo `application.properties` ubicado en `src/main/resources/application.properties`."

![Credentials db](src/main/resources/static/img_db_credentials.png)

## Tecnologias

- **Java Development Kit (JDK) 17:** Asegúrese de tener instalado JDK 17 en su sistema. Puede descargarlo desde [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) o [OpenJDK](https://adoptopenjdk.net/).

- **Spring Boot: 2.7.15**.

- **Base de datos MYSQL**

- **Swagger: http://localhost:8080/swagger-ui/index.html**


## Requerimientos

## Endpoints

- Endpoint de Entrada POST /creacliente
    - Nombre
    - Apellido
    - Edad
    - Fecha de nacimiento

- Endpoint de salida GET /kpideclientes
    - Promedio edad entre todos los clientes
    - Desviación estándar entre las edades de todos los clientes

- Endpoint de salida GET /listclientes
    - Lista de personas con todos los datos


## Extras

- Eliminación lógica.
- Uso de DTOs.
- @RestControllerAdvice para manejar excepciones globales en mi proyecto.
- Uso de Constantes para Mejor Legibilidad.
- Utilización de Genéricos para Flexibilidad.
- Campos validados.
- Documentación con Swagger.

## Screenshots
- **Swagger:**
http://localhost:8080/swagger-ui/index.html
![Swagger](src/main/resources/static/img_1.png)

- **POST /createClient:**
![POST /createClient](src/main/resources/static/img_create_client_post.png)

- **POST /Validacion de campos:**
![POST /createClient](src/main/resources/static/img_campos_validados_post.png)
![POST /createClient](src/main/resources/static/img_fecha_validada_post.png)

- **GET / Lista paginada de clientes:**
![GET / Lista paginada de clientes](src/main/resources/static/img_paginations_clients_get.png)

- **GET / KPI de clientes:**
![GET / KPI de clientes](src/main/resources/static/img_kpi_clients_get.png)

- **DELETE / Delete client:**
![DELETE / Delete client](src/main/resources/static/img_deletee_logic.png)









