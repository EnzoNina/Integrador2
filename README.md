
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

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Requiere**. Id de la categoria |


#### Listar todas las Categorias

```http
  GET /categoria/
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

#### Borrar Categoria por ID

```http
  DELETE /categoria/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Requiere**. Id de la categoria |

## Presupuesto

#### Agregar presupuesto

```http
  POST /presupuesto/guardar
```

Datos de entrada
```json
{
  "id_categoria": "<id_categoria>",
  "nombre": "<nombre>",
  "descripcion": "<descripcion>",
  "monto": "<monto>"
}
```

## Divisa

#### Buscar Divisa por ID

```http
  GET /divisa/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Requiere**. Id de la Divisa |


#### Listar Divisas
```http
  GET /divisa/listar
```

## Concepto

#### Buscar Concepto por ID

```http
  GET /concepto/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Requiere**. Id del Concepto |


#### Listar Conceptos
```http
  GET /concepto/listar
```

## Frecuencia

#### Buscar Frecuencia por ID

```http
  GET /frecuencia/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Requiere**. Id de la Frecuencia |


#### Listar Frecuencia
```http
  GET /frecuencia/listar
```

## Transacciones

#### Buscar transaccion por ID

```http
  GET /transaccion/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Requiere**. Id de la transaccion |


#### Listar Transacciones
```http
  GET /transaccion/listar
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