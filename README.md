# MoveSync API 🚀

Este proyecto es la **API backend de MoveSync**, una aplicación construida con **Spring Boot**, **PostgreSQL** y **Flyway** para gestionar información de movimientos y sincronización de datos.

## 📌 Tecnologías principales

- [Spring Boot 3.5.5](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/) (vía Docker)
- [Flyway](https://flywaydb.org/) para migraciones automáticas de base de datos
- Gradle como sistema de construcción

## 📁 Estructura general

- `src/main/java`: Código fuente Java (controllers, services, repositories, models)
- `src/main/resources/db/migration`: Migraciones SQL controladas por Flyway
- `application.properties`: Configuración principal (base de datos, JPA, etc.)
- `docker-compose.yml`: Configuración para levantar la base de datos PostgreSQL

---

## ⚙️ ¿Cómo ejecutar el proyecto?

### 1. Clonar el repositorio

```bash
git clone https://github.com/ATOMOSX/move_sync_api.git
cd move_sync_api
```

### 2. Levantar la base de datos con Docker

Asegúrate de tener Docker instalado. Luego ejecuta:

```bash
docker compose up -d
```

Esto levantará una instancia de PostgreSQL en `localhost:5432` con:

- **Base de datos:**
- **Usuario:** 
- **Contraseña:** `postgres`

> ⚠️ Puedes modificar estos valores en `docker-compose.yml` si lo deseas.

### 3. Ejecutar la aplicación

Desde la raíz del proyecto:

```bash
./gradlew bootRun
```

O bien, si usas IntelliJ IDEA, puedes correr directamente la clase `MoveSyncApiApplication`.

---

## 🗄️ Migraciones con Flyway

Cada vez que inicies la aplicación, Flyway aplicará automáticamente los scripts SQL que encuentre en:

```
src/main/resources/db/migration
```

Por ejemplo:

- `V1__crear_tabla_usuarios.sql`
- `V2__insertar_datos_iniciales.sql`

---

## 🧪 Endpoints disponibles

A medida que avances en el desarrollo, aquí puedes documentar tus endpoints. Por ejemplo:

```http
GET /api/usuarios
POST /api/sincronizar
```

---

## 👥 Equipo

Este proyecto es desarrollado como parte del curso de **Bases de Datos 1** en el programa de Ingeniería de Sistemas.

---

