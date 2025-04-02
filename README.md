# 🎓 Universidad Spring Boot

### ✍️ Autor
**cmrnda**

### 📌 Descripción
Este proyecto es una API REST desarrollada con **Spring Boot** para gestionar la información de estudiantes de una universidad. Proporciona endpoints para realizar operaciones CRUD sobre los datos de los estudiantes.

---

## 🚀 Instalación y Configuración

### 🔹 1. Clonar el Repositorio
Ejecuta el siguiente comando en tu terminal para descargar el proyecto:
```sh
git clone https://github.com/Genesixd/universidad_spring_boot.git
cd universidad_spring_boot
```

### 🔹 2. Configurar el Puerto
El servidor utiliza el puerto **8082** por defecto. Si deseas modificarlo, cambia el valor en `application.properties`:
```properties
server.port=8082
```

### 🔹 3. Ejecutar la Aplicación
Para iniciar la API con **Maven**, usa el siguiente comando:
```sh
mvn spring-boot:run
```

---

## 👀 Endpoints Disponibles

| Método  | Endpoint                   | Descripción                  |
|---------|----------------------------|------------------------------|
| **GET**     | `/api/estudiantes`         | Obtener lista de estudiantes |
| **POST**    | `/api/estudiantes`         | Crear un nuevo estudiante    |
| **GET**     | `/api/estudiantes/{id}`    | Consultar un estudiante por ID |
| **PUT**     | `/api/estudiantes/{id}`    | Actualizar un estudiante     |
| **DELETE**  | `/api/estudiantes/{id}`    | Eliminar un estudiante       |

### 🔹 Prueba los Endpoints
Puedes probar los endpoints usando **Postman** o `cURL` en la terminal:
```sh
curl -X GET http://localhost:8080/api/estudiantes -H "Accept: application/json"
```


