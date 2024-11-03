


# **DETECCIÓN DE MUTANTES MEDIANTE ANÁLISIS DE ADN - PARCIAL DE DESARROLLO DE SOFTWARE 2024**


- Alumno: Ignacio Exequiel Sanchez
- Legajo: 50163
- Comisión: 3k09
-------------------------------------------------------------------------------------------------
## **Pre-requisitos**

JDK (17)

IntelliJ IDEA (u otro IDE de tu preferencia)

Gradle (Gestor de Dependencias)

H2 (Base de Datos)

## **Instrucciones de ejecución?**

1- Descargar el proyecto o clonarlo:
`https://github.com/ignacioesanchez1/parcial3k9.git`

2- Esperar a que se descarguen las dependencias (El IDE lo suele hacer automático)

3- Ejecutar el proyecto

4- Levantar la base de datos H2: `http://localhost:8080/h2-inicial1/`

Probá la API con Postman o cualquier otro de tu preferencia:
`https://parcialdesarrolloutn.onrender.com`
- POST (guardar local): `http://localhost:8080/mutant`
- POST (guardar render): `https://parcialdesarrolloutn.onrender.com/mutant`
- GET (recuperar estadisticas): `https://parcialdesarrolloutn.onrender.com/stats`


Descripción del Proyecto
-

Magneto necesita reclutar la mayor cantidad de mutantes posible para su lucha contra los X-Men. Para esto, te ha contratado para desarrollar un programa que identifique mutantes basándose en sus secuencias de ADN. Este programa, mediante un análisis de la estructura genética, podrá determinar si una secuencia de ADN pertenece a un mutante o a un humano.

El método principal tiene la siguiente firma:
```
boolean isMutant(String[] dna);
```
Este método recibe un arreglo de strings que representa una tabla de (NxN) con la secuencia de ADN. Las letras en cada string solo pueden ser: A, T, C, G, que representan las bases nitrogenadas del ADN.

Objetivo
-
El objetivo de esta aplicación es analizar la estructura de una secuencia de ADN y determinar si corresponde a un mutante. Esto se logra identificando más de una secuencia de cuatro caracteres consecutivos idénticos en cualquier dirección: horizontal, vertical o diagonal.

**Ejemplo de Matriz de ADN:**

```
 String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
```
En este ejemplo, la matriz de ADN contiene una secuencia mutante válida, con secuencias de cuatro letras idénticas en direcciones horizontal, vertical o diagonal.

**Diagrama de búsqueda en la matriz:**

<div align="center">
<img src="https://github.com/user-attachments/assets/1aff182e-4a1e-48f1-8dc1-07e7a16c794b" alt="Estrategia De Busqueda" width="500" height="500"/>
</div>


Endpoints de la API REST
-
**URL Base del Proyecto**

LINK → https://parcialdesarrolloutn.onrender.com

**1. Detectar Mutante (POST)**

**Endpoint:** `/mutant`

**Método:** `POST`

**Descripción:** Este endpoint verifica si una secuencia de ADN pertenece a un mutante. Debe enviarse un JSON con el siguiente formato:
```
{
   "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
**Respuestas:**

`200 OK:` La secuencia corresponde a un mutante.

`403 Forbidden:` La secuencia corresponde a un humano.

**Ejemplo de Uso:**

POST → https://parcialdesarrolloutn.onrender.com/mutant

**2. Obtener Estadísticas (GET)**

**Endpoint:** `/stats`

**Método:** `GET`

**Descripción:** Este endpoint devuelve estadísticas de las secuencias de ADN verificadas. La respuesta es un JSON con el siguiente formato:

```
{
    "count_human_dna": 40,
    "count_mutant_dna": 100,
    "ratio": 0.4
}
````
**Campo de Respuesta:**

- `count_human_dna:` Número de secuencias de ADN humano analizadas.
- `count_mutant_dna:` Número de secuencias de ADN mutante analizadas.
- `ratio:` Proporción de mutantes respecto a humanos analizados.
  
**Ejemplo de Uso:**
  
GET → https://parcialdesarrolloutn.onrender.com/stats


Code Coverage
---

![CodeCoverage](https://github.com/user-attachments/assets/74786819-4cf9-4983-9cbc-2ff508dc973b)


Este proyecto mantiene una cobertura de código superior al 80%, con buenos resultados en cobertura de líneas, ramas y clases. Las pruebas cubren la mayoría de los escenarios, aunque existen áreas específicas que podrían mejorarse para optimizar aún más la cobertura.

Diagramas de Secuencia y Pruebas de Stress (JMeter)
-
Se realizaron pruebas de estrés para los endpoints `/mutant` (POST) y `/stats` (GET), además de generar los correspondientes diagramas de secuencia. Los detalles de estas pruebas se encuentran en el documento adjunto <a href="documents/NIVEL 3.pdf">PDF-NIVEL 3</a>, junto con una explicación de cada columna de cobertura.


Consideraciones
-

- Validación de secuencias en la matriz de ADN.
- Verificación de la estructura NxN para asegurar uniformidad en las secuencias de ADN.
- Condición de mutante: la matriz debe contener al menos dos secuencias de 4 caracteres idénticos consecutivos.
- Mensajes de advertencia para matrices de dimensiones incorrectas (NxM), vacías o que contengan caracteres no válidos.
