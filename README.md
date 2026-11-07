# 3Link Backend

3Link es una aplicación web diseñada para ejercitar la asociación de ideas y reforzar el aprendizaje mediante la conexión de conceptos. Permite crear tarjetas de palabras, organizarlas por categorías y seleccionar tres términos aleatorios para construir un discurso que los relacione, incluso cuando pertenecen a temas diferentes. Esta propuesta busca desarrollar la creatividad, el razonamiento y la capacidad de expresar conocimientos a partir de asociaciones.
## Tecnologías

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring MVC](https://img.shields.io/badge/Spring_MVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

## Features

- Registro de palabras con uno o varios filtros.
- Consulta de todas las palabras y de una palabra por su identificador.
- Selección aleatoria de cartas según los filtros recibidos.
- Coincidencia con cualquiera de los filtros seleccionados, sin repetir palabras en una misma búsqueda.
- Validación de palabras de hasta 100 caracteres y entre 1 y 10 filtros por registro.
- Persistencia de palabras y filtros relacionados en PostgreSQL.

## Ejecución

Se necesita **JDK 21**, **PostgreSQL** y una base de datos creada previamente. Para el entorno local, se utiliza una base llamada `threelink` en el puerto `5432`.

El archivo `src/main/resources/application.properties` obtiene los datos de conexión a partir de las variables `DB_URL`, `DB_USERNAME` y `DB_PASSWORD`. No se deben escribir contraseñas reales en este archivo.

**Windows (PowerShell):**

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/threelink"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = Read-Host "Contraseña local de PostgreSQL"
.\mvnw.cmd spring-boot:run
```

**Linux y macOS:**

```bash
export DB_URL="jdbc:postgresql://localhost:5432/threelink"
export DB_USERNAME="postgres"
read -rs -p "Contraseña local de PostgreSQL: " DB_PASSWORD; echo
export DB_PASSWORD
./mvnw spring-boot:run
```

Con PostgreSQL en ejecución, la API se inicia por defecto en `http://localhost:8080`. Hibernate utiliza `ddl-auto=update` para crear o actualizar las tablas del proyecto en la base existente.

Los endpoints principales son:

| Método | Ruta | Descripción |
| --- | --- | --- |
| `GET` | `/api/words` | Lista todas las palabras. |
| `GET` | `/api/words/{wordId}` | Consulta una palabra por ID. |
| `POST` | `/api/words` | Registra una palabra con filtros. |
| `GET` | `/api/words/random?filters=animal,comida&limit=3` | Obtiene cartas aleatorias que coincidan con los filtros indicados. |

Ejemplo de solicitud para registrar una palabra:

```json
{
  "value": "Axolote",
  "filters": ["animal", "acuático"]
}
```

Para ejecutar las pruebas con la misma configuración de PostgreSQL:

```powershell
.\mvnw.cmd test
```

## Video de demostración

<a href="https://youtu.be/9wYBw4R6RB4">
  <img src="https://img.youtube.com/vi/9wYBw4R6RB4/hqdefault.jpg" width="900" alt="Video de demostración de 3Link">
</a>

[Ver video en YouTube](https://youtu.be/9wYBw4R6RB4)
