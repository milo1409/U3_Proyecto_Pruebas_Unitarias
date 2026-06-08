# Gestión de defectos

Este archivo documenta los defectos identificados o simulados durante el desarrollo del taller de pruebas unitarias.
La documentación de defectos permite mantener trazabilidad entre las pruebas fallidas, la causa probable, la corrección aplicada y el estado final del defecto.

---

## Defecto 01

* **Caso:** edad negativa.
* **Entrada:** persona viva con edad = -1.
* **Resultado esperado:** `INVALID_AGE`.
* **Resultado obtenido inicialmente:** `VALID`.
* **Causa probable:** el método `registerVoter` no tenía validación para edades menores que cero.
* **Solución aplicada:** se agregó una condición para retornar `INVALID_AGE` cuando la edad es menor que cero.
* **Estado:** Cerrado.

---

## Defecto 02

* **Caso:** edad superior al máximo permitido.
* **Entrada:** persona viva con edad = 121.
* **Resultado esperado:** `INVALID_AGE`.
* **Resultado obtenido inicialmente:** `VALID`.
* **Causa probable:** no existía validación del límite superior de edad.
* **Solución aplicada:** se agregó la constante `MAX_VALID_AGE = 120` y una condición para rechazar edades mayores a 120.
* **Estado:** Cerrado.

---

## Defecto 03

* **Caso:** persona menor de edad.
* **Entrada:** persona viva con edad = 17.
* **Resultado esperado:** `UNDERAGE`.
* **Resultado obtenido inicialmente:** `VALID`.
* **Causa probable:** el método permitía registrar cualquier persona viva sin validar la mayoría de edad.
* **Solución aplicada:** se agregó la constante `MIN_VALID_AGE = 18` y una condición para retornar `UNDERAGE` cuando la edad es menor a 18.
* **Estado:** Cerrado.

---

## Defecto 04

* **Caso:** persona muerta.
* **Entrada:** persona con `alive = false`.
* **Resultado esperado:** `DEAD`.
* **Resultado obtenido inicialmente:** `VALID`.
* **Causa probable:** la primera implementación del método `registerVoter` retornaba siempre `VALID`.
* **Solución aplicada:** se agregó una condición para validar el estado de vida y retornar `DEAD` cuando la persona no está viva.
* **Estado:** Cerrado.

---

## Defecto 05

* **Caso:** persona duplicada.
* **Entrada:** dos personas registradas con el mismo identificador.
* **Resultado esperado:** `DUPLICATED`.
* **Resultado obtenido inicialmente:** `VALID`.
* **Causa probable:** no existía almacenamiento de identificadores registrados.
* **Solución aplicada:** se agregó un `Set<Integer>` llamado `registeredIds` para controlar los identificadores ya registrados.
* **Estado:** Cerrado.

---

## Defecto 06

* **Caso:** persona nula.
* **Entrada:** `person = null`.
* **Resultado esperado:** `INVALID`.
* **Resultado obtenido inicialmente:** error por `NullPointerException` o comportamiento no controlado.
* **Causa probable:** no existía validación defensiva para objetos nulos.
* **Solución aplicada:** se agregó una condición inicial para retornar `INVALID` cuando la persona es `null`.
* **Estado:** Cerrado.

---

## Defecto 07

* **Caso:** identificador inválido.
* **Entrada:** persona viva, mayor de edad, con `id = 0`.
* **Resultado esperado:** `INVALID`.
* **Resultado obtenido inicialmente:** `VALID`.
* **Causa probable:** el método no validaba identificadores menores o iguales a cero.
* **Solución aplicada:** se agregó una condición para retornar `INVALID` cuando `id <= 0`.
* **Estado:** Cerrado.
