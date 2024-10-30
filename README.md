Parcial de Desarrollo de Software
-

- Alumno: Ignacio Exequiel Sanchez
- Legajo: 50163





BREVE DESCRIPCION DEL EXAMEN
-
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens. Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN. Para eso te ha pedido crear un programa con un método o función con la siguiente firma:

boolean isMutant(String[] dna);

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

OBJETIVO
-
La aplicación tiene como objetivo identificar si una matriz de ADN pertenece a un humano o a un mutante. Para ello, analiza la estructura de la secuencia de ADN y determina si presenta ciertas características específicas.

Una matriz de ADN de ejemplo puede verse así:


```
CGTG
GTAG
GGTT
ATTT
```
Se considera que el ADN es de un mutante si contiene más de una secuencia de 4 caracteres consecutivos idénticos (A, C, G, o T) en cualquier dirección (horizontal, vertical o diagonal). A continuación, se presenta un diagrama ilustrativo de las posibles búsquedas de secuencias en la matriz:

Búsqueda horizontal y vertical.
Búsqueda diagonal en ambas direcciones (ascendente y descendente). Para realizar esta búsqueda, se descompone en dos variantes: una que empieza desde el borde izquierdo y otra desde el borde superior. Es importante gestionar los índices adecuadamente para evitar contar la misma secuencia dos veces.

Descripción del Servidor REST
-

https://parcialdesarrollo-htzs.onrender.com

El proyecto incluye un servidor web REST desarrollado con Spring Boot, que expone dos endpoints principales:

DETECTAR MUTANTE(POST)
-
https://parcialdesarrollo-htzs.onrender.com/mutant

Para detectar si un humano es mutante debe enviar la secuencia de ADN mediante un HTTP POST con un JSON el cual tenga el siguiente formato:

```
POST → /mutant
{
   "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
En caso de verificar un mutante, devuelve HTTP 200-OK, en caso contrario 403-Forbidden.

ESTADISTICAS(GET)
-
https://parcialdesarrollo-htzs.onrender.com/stats

Todas las muestras obtenidas mediante el servicio web se almacenan en una base de datos (1 registro por ADN).
Puede obtener estadísticas de las cadenas de ADN verificadas mediante un HTTP GET. Se retorna un JSON con el siguiente formato:

```
GET → /stats
{
    "count_human_dna": 40,
    "count_mutant_dna": 100,
    "ratio": 0.4
}
```
Donde count_mutant_dna y count_human_dna representan la cantidad de análisis de ADN realizados para mutantes y humanos, respectivamente, y ratio es la relación entre ellos

CONSIDERACIONES
-
. Se validaron las diferentes secuencias de una matriz de DNA.

. Se validó la estructura respetara la uniformidad NxN.

. Se colocó como condición mínima para que el DNA sea de mutante que se obtuvieron al menos 2 secuencias de 4 caracteres seguidos en la matriz de DNA.

. Habran mensajes de advertencia cada vez que se ingrese una matriz de orden NxM, de numeros, vacia o que o contenga las letras correctas.
