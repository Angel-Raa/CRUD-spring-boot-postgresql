# CRUD Básico con Spring Boot y PostgreSQL

Este proyecto es un ejemplo de una aplicación CRUD (Crear, Leer, Actualizar y Eliminar) básica desarrollada utilizando Java 17, Spring Boot 3 y PostgreSQL. La aplicación se encuentra en un entorno de contenedorizado utilizando Docker Compose.

## Tecnologías Utilizadas

- JDK 17
- Spring Boot 3
- PostgreSQL 15
- Docker Compose

## Configuración del Entorno

Asegúrate de tener instalados los siguientes componentes en tu entorno de desarrollo antes de comenzar:

1. [Docker Compose](https://docs.docker.com/compose/install/)

## Instrucciones de Uso

Sigue estos pasos para ejecutar la aplicación en tu entorno local:

```bash
# Clonar el repositorio en tu máquina local
git clone https://github.com/Angel-Raa/CRUD-basico.git

# Navegar al directorio del proyecto
cd CRUD-basico

# Construir y ejecutar los contenedores de Docker Compose
docker compose up -d

```
## Endpoints de la API

Una vez que los contenedores estén en funcionamiento, podrás acceder a los siguientes endpoints de la API:

- **Obtener todos los productos:** [http://localhost:9000/product/](http://localhost:9000/product/)

- **Obtener un solo producto:** [http://localhost:9000/product/{id}](http://localhost:9000/product/{id})

- **Guardar un nuevo producto:** [http://localhost:9000/product/save](http://localhost:9000/product/save)

- **Actualizar un producto existente:** [http://localhost:9000/product/update/{id}](http://localhost:9000/product/update/{id})

- **Eliminar un producto:** [http://localhost:9000/product/delete/{id}](http://localhost:9000/product/delete/{id})

