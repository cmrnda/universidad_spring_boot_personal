# Sistema de Gestión Universitaria
## Práctica Nro. 2
### ✍️ Estudiante
- **Carlos Manuel Miranda Aguirre**
- - **CI:** 1379486
### Descripción del Proyecto
Este proyecto es una **API REST** desarrollada con **Spring Boot 3.x** que permite la gestión de diferentes aspectos de una universidad, tales como estudiantes, docentes, materias e inscripciones. Incluye un sistema de autenticación basado en **JSON Web Token (JWT)** y almacenamiento en caché con **Redis**.
## 🚀 **Tecnologías Utilizadas**
- **Java 20**
- **Spring Boot 3.4.4**
- **Spring Security** (Autenticación y Autorización basada en JWT)
- **Spring Data JPA + Hibernate** (ORM)
- **PostgreSQL** (Base de datos relacional)
- **Redis** (Caché para mejorar el rendimiento)
- **Swagger OpenAPI** (Documentación de la API)
- **Lombok** (Reducción de código repetitivo)
- **Maven** (Gestor de dependencias)

## 🗂️ **Estructura del Proyecto**
``` 
RegistroUniversitario
├── .mvn
│ └── wrapper
│ └── maven-wrapper.properties
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.universidad
│ │ │ ├── controller
│ │ │ │ ├── EstudianteController.java
│ │ │ │ ├── EvaluacionDocenteController.java
│ │ │ │ └── MateriaController.java
│ │ │ ├── dto
│ │ │ ├── model
│ │ │ │ ├── Docente.java
│ │ │ │ ├── Estudiante.java
│ │ │ │ ├── EvaluacionDocente.java
│ │ │ │ ├── Materia.java
│ │ │ │ └── Persona.java
│ │ │ ├── registro
│ │ │ │ ├── config
│ │ │ │ ├── controller
│ │ │ │ ├── dto
│ │ │ │ ├── model
│ │ │ │ ├── repository
│ │ │ │ ├── security
│ │ │ │ └── service
│ │ │ ├── repository
│ │ │ ├── service
│ │ │ │ ├── impl
│ │ │ │ │ ├── EstudianteServiceImpl.java
│ │ │ │ │ ├── EvaluacionDocenteServiceImpl.java
│ │ │ │ │ └── MateriaServiceImpl.java
│ │ │ │ ├── IEstudianteService.java
│ │ │ │ ├── IEvaluacionDocenteService.java
│ │ │ │ └── IMateriaService.java
│ │ │ ├── validation
│ │ │ └── UniversidadApplication.java
│ │ └── resources
│ │ └── application.properties
├── target
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
## 📖 **Módulos y Endpoints**
**1. Materias**
- `GET /api/materias` → Consultar todas las materias.
- `POST /api/materias` → Crear una nueva materia.
- `PUT /api/materias/{id}` → Actualizar los datos de una materia específica.
- `DELETE /api/materias/{id}` → Eliminar una materia.

**2. Estudiantes**
- `GET /api/estudiantes` → Consultar todos los estudiantes.
- `POST /api/estudiantes` → Registrar un nuevo estudiante.
- `PUT /api/estudiantes/{id}` → Actualizar la información de un estudiante.
- `POST /api/estudiantes/{id}/inscribir` → Inscribir materias a un estudiante.
- `GET /api/estudiantes/{id}/materias` → Obtener las materias de un estudiante específico.
- `PUT /api/estudiantes/{id}/baja` → Dar de baja a un estudiante.

**3. Inscripciones**
- `GET /api/inscripciones` → Consultar el estado de inscripciones.
- `POST /api/inscripciones` → Crear una inscripción.
- `PUT /api/inscripciones/{id}/abandonar` → Abandonar una inscripción de materia.

**4. Evaluación de Docentes**
- `POST /api/evaluaciones-docente` → Crear una evaluación para un docente específico.
- `GET /api/evaluaciones-docente/docente/{id}` → Listar todas las evaluaciones de un docente determinado.
- `GET /api/evaluaciones-docente/{id}` → Obtener la información de una evaluación específica.
- `DELETE /api/evaluaciones-docente/{id}` → Eliminar una evaluación.

**5. Autenticación y Seguridad**
- **Autenticación con JWT**:
    - `POST /api/auth/signup` → Registro de usuarios.
    - `POST /api/auth/login` → Iniciar sesión.
    - `POST /api/auth/logout` → Cerrar sesión.

- **Roles definidos**:
    - **ADMIN**: Acceso completo al sistema.
    - **DOCENTE**: Acceso limitado a evaluaciones y asignaciones de materias.
    - **ESTUDIANTE**: Acceso a inscripción, materias y su estado académico.

## 🌐 **Swagger UI**
La documentación completa de los endpoints del **Sistema de Gestión Universitaria** está disponible en la interfaz visual de Swagger:
**URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
## 🛡 **Seguridad**
- Autenticación y autorización basada en JWT.
- Acceso segmentado según roles: **ADMIN**, **DOCENTE** y **ESTUDIANTE**.
- Endpoints sensibles protegidos y personalizados con **`@PreAuthorize`** y **`@RolesAllowed`**.

## 📦 **Ejecución del Proyecto**
1. **Clonar el Repositorio:**
1. **Configurar la Base de Datos:**
   En el archivo , define las propiedades para conectar tu base de datos **PostgreSQL**. Ejemplo: `src/main/resources/application.properties`
``` 
   spring.datasource.url=jdbc:postgresql://localhost:5432/mi_base
   spring.datasource.username=mi_usuario
   spring.datasource.password=mi_contraseña
```
1. **Ejecutar la Aplicación:**
``` bash
   mvn clean install
   java -jar target/universidad_spring_boot-0.0.1-SNAPSHOT.jar
```
1. **Accede a la Aplicación y Swagger UI:**
    - **URL Principal:** [http://localhost:8080](http://localhost:8080)
    - **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
