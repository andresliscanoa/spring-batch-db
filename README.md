# Spring Batch

Procesamiento en lote de información de una base de datos a otra base de datos

## Stack

- SpringBoot 3.1.3
- JDK17
- Maven
- Postgres
- Docker | Docker Compose

## Run
```shell
docker-compose -f docker-compose.yaml -p spring-batch-db up -d --build
``` 
## Down
```shell
docker-compose -f docker-compose.yaml -p spring-batch-db down --remove-orphans --volumes
```