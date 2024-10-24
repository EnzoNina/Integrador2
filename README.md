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

#### Listar Categorias

```http
  GET /categorias/listarCategorias
```

Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "descripcion": "Sueldo"
  },
  {
    "id": 2,
    "descripcion": "Negocio"
  },
  {
    "id": 3,
    "descripcion": "Inversiones"
  }
]
```

### Crear Categoria

```http
  POST /categorias/crearCategoria
```

Datos de entrada

```json
{
  "descripcion": "<descripcion>"
}
```

Ejemplo de respuesta

```json
{
  "categoria": {
    "id": 21,
    "descripcion": "Compras fin de mes"
  },
  "mensaje": "Categoria creada correctamente"
}
```
## CategoriasUsuarios

#### Buscar Categoria Usuario por ID

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
  "tipo_transaccion": "Ingreso",
  "categoria": "Sueldo",
  "descripcion": "Sueldo de trabajo 1"
}
```

#### Listar todas las Categorias Usuarios

```http
  GET /categoria/listar/usuario/{id}
```

Ejemplo de respuesta

```json
[
  {
    "id": "1",
    "tipo_transaccion": "Ingreso",
    "categoria": "Sueldo",
    "descripcion": "Sueldo de trabajo 1"
  },
  {
    "id": "3",
    "tipo_transaccion": "Ingreso",
    "categoria": "Sueldo",
    "descripcion": "Sueldo de trabajo 3"
  },
  {
    "id": "5",
    "tipo_transaccion": "Ingreso",
    "categoria": "Sueldo",
    "descripcion": "Sueldo de trabajo 4"
  },
  {
    "id": "7",
    "tipo_transaccion": "Egreso",
    "categoria": "Gastos Personales",
    "descripcion": "Gastos personales pequeños"
  }
]
```

#### Agregar una categoria usuario

```http
  POST /categoria/crearCategoriaUsuario
```

Datos de entrada

```json
{
  "id_usuario": "1",
  "id_tipo_transaccion": "2",
  "id_categoria": "10",
  "descripcion": "Gastos personales pequeños"
}
```

Ejemplo de respuesta

```json
{
  "id": "7",
  "tipo_transaccion": "Egreso",
  "categoria": "Gastos Personales",
  "descripcion": "Gastos personales pequeños"
}
```

#### Borrar Categoria Usuario por ID

```http
  DELETE /categoria/{id}
```

| Parameter | Type      | Description                      |
|:----------|:----------|:---------------------------------|
| `id`      | `Integer` | **Requiere**. Id de la categoria |

Ejemplo de respuesta

```json
{
  "mensaje": "Categoria eliminada correctamente"
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
      "id": "4",
      "categoria": "Gastos Personales",
      "usuario": "adminadmin",
      "nombre": "Presupuesto de gastos personales",
      "descripcion": "Maximo de 1000",
      "monto": "1000.00"
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
  "id_categoria_usuario": ""
}
```

Ejemplo de repuesta

```json
{
  "presupuesto": {
    "id": "4",
    "categoria": "Gastos Personales",
    "usuario": "adminadmin",
    "nombre": "Presupuesto de gastos personales",
    "descripcion": "Maximo de 1000",
    "monto": "1000.00"
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
  "id_categoria_usuario": "<id_categoria>",
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
{
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
}
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
```

# Dashboard
## Obtener el Balance Total


```http
  GET /dashboard/balance/{id}
```

| Parameter | Type      | Description                  |
|:----------|:----------|:-----------------------------|
| `id`      | `Integer` | **Requiere**. Id del usuario |

Ejemplo de respuesta

```json
{
  1000.00
}
```

## Ingresos por mes

```http
  GET /dashboard/ingresos_por_mes/{id}
```

| Parameter | Type      | Description                  |
|:----------|:----------|:-----------------------------|
| `id`      | `Integer` | **Requiere**. Id del usuario |

Ejemplo de respuesta

```json
{
  "status": "success",
  "ingresos": {
    "2024-09": 1000.00,
    "2024-10": 2000.00
  }
}
```

## Egresos por mes

```http
  GET /dashboard/egresos_por_mes/{id}
```

| Parameter | Type      | Description                  |
|:----------|:----------|:-----------------------------|
| `id`      | `Integer` | **Requiere**. Id del usuario |

Ejemplo de respuesta

```json
{
  "status": "gastos",
  "egresos": {
    "2024-09": 1000.00,
    "2024-10": 2000.00
  }
}
```

## Transacciones recientes
```http
  GET /dashboard/transacciones_recientes/{id}
```

| Parameter | Type      | Description                  |
|:----------|:----------|:-----------------------------|
| `id`      | `Integer` | **Requiere**. Id del usuario |

Ejemplo de respuesta

```json
[
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
## Gastos por categoria

```http
  GET /dashboard/gastos_por_categoria/{id}
```

| Parameter | Type      | Description                  |
|:----------|:----------|:-----------------------------|
| `id`      | `Integer` | **Requiere**. Id del usuario |

Ejemplo de respuesta

```json
{
    "Gastos Personales": 1000.00,
    "Inversiones": 2000.00
}
```
