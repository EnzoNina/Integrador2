# Integrador 2

Proyecto del curso Integrador 2, que consta de la creación de una aplicación web para las finanzas personalse.

## Tecnologías utilizadas

**Frontend:** Angular.

**Backend:** Spring boot, Java.

**Seguridad:** JWT Tokens.

**Base de datos:** PostgreSQL.

**Otros:** Jpa, DevTools, Lombok.

## Referencias de API

## AUTH

#### Registrar

```http
  POST /auth/register
```

Datos de entrada

```json
{
  "nombres": "<nombres>",
  "dni": "<dni>",
  "apellido_paterno": "<apellido_paterno>",
  "apellido_materno": "<apellido_materno>",
  "celular": "<celular>",
  "direccion": "<direccion>",
  "username": "<username>",
  "password": "<password>"
}
```

#### Login

```http
  POST /auth/login
```

Datos de entrada

```json
{
  "email": "<email>",
  "password": "<password>"
}
```

## Categorias

#### Buscar Categoria por ID

```http
  GET /categoria/{id}
```

| Parameter | Type      | Description                      |
|:----------|:----------|:---------------------------------|
| `id`      | `Integer` | **Requiere**. Id de la categoria |

Ejemplo de respuesta

```json
{
  "id": "1",
  "transaccion": "Ingreso",
  "descripcion": "Sueldo"
}
```

#### Listar todas las Categorias

```http
  GET /categoria/
```

Ejemplo de respuesta

```json
[
  {
    "id": "1",
    "transaccion": "Ingreso",
    "descripcion": "Sueldo"
  },
  {
    "id": "2",
    "transaccion": "Ingreso",
    "descripcion": "Negocio"
  }
]
```

#### Agregar una categoria

```http
  POST /categoria/
```

Datos de entrada

```json
{
  "id_tipo_transaccion": "<id_tipo_transaccion>",
  "descripcion": "<descripcion>"
}
```

Ejemplo de respuesta

```json
{
  "id": 42,
  "idTipoTra": {
    "id": 1,
    "descripcion": "Ingreso"
  },
  "descripcion": "Nueva transaccion"
}
```

#### Borrar Categoria por ID

```http
  DELETE /categoria/{id}
```

| Parameter | Type      | Description                      |
|:----------|:----------|:---------------------------------|
| `id`      | `Integer` | **Requiere**. Id de la categoria |

Ejemplo de respuesta

```json
{
  Categoria
  eliminada
  correctamente
}
```

## Presupuesto

#### Listar presupuestos por Usuario

```http
  POST /presupuesto/listar/usuario
```

Datos de entrada

```json
{
  "id_usuario": "<id_usuario>"
}
```

Ejemplo de repuesta

```json
{
  "presupuestos": [
    {
      "id": 2,
      "nombre": "Comida",
      "descripcion": "Ahorro de comida",
      "monto": 1200.00
    }
  ]
}
```

#### Buscar presupuesto

```http
    POST /presupuesto/buscar
```

Datos de entrada

```json
{
  "id_usuario": "",
  "id_categoria": ""
}
```

Ejemplo de repuesta

```json
{
  "presupuesto": {
    "nombre": "Comida",
    "descripcion": "Ahorro de comida",
    "monto": "1200.00",
    "categoria": "Sueldo",
    "usuario": "adminadmin"
  },
  "mensaje": "Presupuesto encontrado"
}
```

#### Agregar presupuesto

```http
  POST /presupuesto/guardar
```

Datos de entrada

```json
{
  "id_usuario": "<id_usuario>",
  "id_categoria": "<id_categoria>",
  "nombre": "<nombre>",
  "descripcion": "<descripcion>",
  "monto": "<monto>"
}
```

Ejemplo de respuesta

```json
{
  "mensaje": "Presupuesto guardado correctamente",
  "status": "OK"
}
```

#### Actualizar presupuesto

```http
  PUT /presupuesto/actualizar/{id}
```

Datos de entrada

```json
{
  "nombre": "",
  "descripcion": "",
  "monto": {}
}
```

Ejemlo de respuesta

```json
{
  "mensaje": "Presupuesto actualizado correctamente",
  "status": "OK"
}
```

## Divisa

#### Buscar Divisa por ID

```http
  GET /divisa/{id}
```

| Parameter | Type      | Description                   |
|:----------|:----------|:------------------------------|
| `id`      | `Integer` | **Requiere**. Id de la Divisa |

Ejemplo de respuesta

```json
{
  "id": 1,
  "codigo": "USD",
  "nombre": "Dolar estadounidense",
  "simbolo": "$"
}
```

#### Listar Divisas

```http
  GET /divisa/listar
```

Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "codigo": "USD",
    "nombre": "Dolar estadounidense",
    "simbolo": "$"
  },
  {
    "id": 2,
    "codigo": "EUR",
    "nombre": "Euro",
    "simbolo": "€"
  }
]
```
## Concepto

#### Buscar Concepto por ID

```http
  GET /concepto/{id}
```

| Parameter | Type      | Description                   |
|:----------|:----------|:------------------------------|
| `id`      | `Integer` | **Requiere**. Id del Concepto |

Ejemplo de respuesta

```json
{
  "id": 1,
  "descripcion": "Sueldo Mensual"
}
```

#### Listar Conceptos

```http
  GET /concepto/listar
```
Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "descripcion": "Sueldo Mensual"
  },
  {
    "id": 2,
    "descripcion": "Ganancia Personal"
  },
  {
    "id": 3,
    "descripcion": "Ventas propias"
  }
]
```

## Frecuencia

#### Buscar Frecuencia por ID

```http
  GET /frecuencia/{id}
```

| Parameter | Type      | Description                       |
|:----------|:----------|:----------------------------------|
| `id`      | `Integer` | **Requiere**. Id de la Frecuencia |

Ejemplo de respuesta

```json
{
  "id": 1,
  "descripcion": "Diario"
}
```

#### Listar Frecuencia

```http
  GET /frecuencia/listar
```

Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "descripcion": "Diario"
  },
  {
    "id": 2,
    "descripcion": "Semanal"
  },
  {
    "id": 3,
    "descripcion": "Quincenal"
  }
]
```

## Transacciones

#### Listar Transacciones por Usuario

```http
  POST /transaccion/listar
```

Datos de entrada

```json
{
  "id_usuario": "<id_usuario>"
}
```

Ejemplo de respuesta

```json
"transacciones": [
{
"id": 2,
"usuario": "adminadmin",
"categoria": "Sueldo",
"tipo_transaccion": "Ingreso",
"tipo_concepto": "Sueldo Mensual",
"frecuencia": "Diario",
"divisa": "$",
"monto": "1000.00",
"descripcion": "Gasto",
"fecha": "2024-09-22"
},
{
"id": 3,
"usuario": "adminadmin",
"categoria": "Sueldo",
"tipo_transaccion": "Ingreso",
"tipo_concepto": "Sueldo Mensual",
"frecuencia": "Diario",
"divisa": "$",
"monto": "100.00",
"descripcion": "Gasto",
"fecha": "2024-09-22"
}
]
```

#### Buscar transaccion por ID

```http
  GET /transaccion/{id}
```

| Parameter | Type      | Description                        |
|:----------|:----------|:-----------------------------------|
| `id`      | `Integer` | **Requiere**. Id de la transaccion |

Ejemplo de respuesta

```json
{
  "transaccion": {
    "id": 2,
    "usuario": "adminadmin",
    "categoria": "Sueldo",
    "tipo_transaccion": "Ingreso",
    "tipo_concepto": "Sueldo Mensual",
    "frecuencia": "Diario",
    "divisa": "$",
    "monto": "1000.00",
    "descripcion": "Gasto",
    "fecha": "2024-09-22"
  },
  "mensaje": "Transacción encontrada.",
  "status": "OK"
}
```

#### Guardar transaccion

```http
  POST /transaccion/guardar
```

Datos de entrada

```json
{
  "id_usuario": "<id_usuario>",
  "id_tipo_categoria": "<id_tipo_categoria>",
  "id_tipo_concepto": "<id_tipo_concepto>",
  "id_tipo_frecuencia": "<id_tipo_frecuencia>",
  "id_tipo_divisa": "<id_tipo_divisa>",
  "monto": "<monto>",
  "descripcion": "<descripcion>"
}
```
Ejemplo de respuesta

```json
{
  "mensaje": "Transacción guardada correctamente",
  "status": "OK"
}
``