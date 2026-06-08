Proyecto de Pruebas Unitarias - Registraduría
1. Descripción del proyecto

Este proyecto corresponde al taller de automatización de pruebas unitarias de la Unidad 3.
El objetivo es implementar pruebas unitarias sobre un caso de uso de una registraduría, aplicando buenas prácticas de desarrollo dirigido por pruebas TDD, patrón AAA y diseño de pruebas mediante clases de equivalencia y valores límite.

El sistema permite validar el registro de una persona como votante a partir de reglas de negocio relacionadas con:

Edad de la persona.
Estado de vida.
Identificador único.
Duplicidad del registro.
Validaciones defensivas frente a datos nulos o inválidos.
2. Tecnologías utilizadas
Java
Maven
JUnit
Visual Studio Code
Git / GitHub
3. Estructura del proyecto

El proyecto fue creado con Maven usando el arquetipo maven-archetype-quickstart.

pruebasunitarias/
│
├── pom.xml
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── edu/
│   │           └── unisabana/
│   │               └── tyvs/
│   │                   └── domain/
│   │                       ├── model/
│   │                       │   ├── Gender.java
│   │                       │   ├── Person.java
│   │                       │   └── RegisterResult.java
│   │                       │
│   │                       └── service/
│   │                           └── Registry.java
│   │
│   └── test/
│       └── java/
│           └── edu/
│               └── unisabana/
│                   └── tyvs/
│                       └── domain/
│                           └── service/
│                               └── RegistryTest.java

## Matriz de clases de equivalencia y valores límite

| Caso | Entrada | Clase de equivalencia | Resultado esperado | Prueba |
|---|---|---|---|---|
| Persona válida | edad=30, alive=true, id=12345 | Edad válida, persona viva, id único | VALID | shouldReturnValidWhenPersonIsAliveAdultAndNotDuplicated |
| Persona muerta | edad=40, alive=false, id=54321 | Estado de vida inválido | DEAD | shouldReturnDeadWhenPersonIsNotAlive |
| Menor de edad | edad=17, alive=true | Límite superior menor de edad | UNDERAGE | shouldReturnUnderageWhenPersonIs17YearsOld |
| Mayoría de edad | edad=18, alive=true | Límite inferior edad válida | VALID | shouldReturnValidWhenPersonIs18YearsOld |
| Edad máxima válida | edad=120, alive=true | Límite superior edad válida | VALID | shouldReturnValidWhenPersonIs120YearsOld |
| Edad negativa | edad=-1, alive=true | Edad inválida inferior | INVALID_AGE | shouldReturnInvalidAgeWhenPersonHasNegativeAge |
| Edad superior a 120 | edad=121, alive=true | Edad inválida superior | INVALID_AGE | shouldReturnInvalidAgeWhenPersonIsOlderThan120 |
| Persona duplicada | id=777 dos veces | Identificador duplicado | DUPLICATED | shouldReturnDuplicatedWhenPersonIdWasAlreadyRegistered |
| Persona nula | person=null | Nulidad | INVALID | shouldReturnInvalidWhenPersonIsNull |
| ID inválido | id=0 | Identificador inválido | INVALID | shouldReturnInvalidWhenPersonIdIsZero |