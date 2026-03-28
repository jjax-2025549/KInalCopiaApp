# KinalApp - Sistema de Gestión de Ventas

KinalApp es una API REST desarrollada con Spring Boot para gestionar ventas, productos, clientes y usuarios. Permite realizar operaciones CRUD completas sobre cada entidad del sistema.

## Tecnologías Utilizadas

* **Java 21**
* **Spring Boot 4.0.2**
* **Maven** (Gestor de dependencias)
* **MySQL 8.0** (Sistema Gestor de Base de Datos)
* **Hibernate / JPA** (ORM para mapeo de entidades)
* **Postman** (Pruebas de endpoints)

## Requisitos Previos

Antes de comenzar con la instalación, asegúrate de cumplir con los siguientes requisitos:

* **JDK 21** o superior instalado.
* **Maven** instalado en tu sistema.
* **IntelliJ IDEA** o cualquier IDE compatible con Spring Boot.
* Una instancia activa de **MySQL** (local o remota).

## Cómo se Instala y se Ejecuta

1. Clona el repositorio:
```bash
git clone https://github.com/jjax-2025549/KinalApp.git
cd KinalApp
git checkout jjax-2025549
```

2. Configura la base de datos en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_kinal_app?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tucontraseña
```

3. Ejecuta el proyecto desde IntelliJ o con Maven:
```bash
mvn spring-boot:run
```

4. La API estará disponible en: `http://localhost:8999`

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/com/julianjax/kinalapp/
│   │   ├── controller/    # Controladores REST
│   │   ├── entity/        # Entidades JPA
│   │   ├── repository/    # Repositorios JPA
│   │   └── service/       # Lógica del negocio
│   └── resources/
│       └── application.properties
```

## Entidades del Sistema

* **Cliente** - Gestión de clientes con DPI, nombre, apellido, dirección y estado
* **Usuario** - Gestión de usuarios con username, password, email, rol y estado
* **Venta** - Registro de ventas con fecha, total y relaciones con Cliente y Usuario
* **Producto** - Catálogo de productos con nombre, precio y stock
* **DetalleVenta** - Detalle de cada venta con cantidad, precio unitario y subtotal

## Endpoints Disponibles

### Clientes
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /clientes | Listar todos |
| GET | /clientes/activos | Listar activos |
| GET | /clientes/{dpi} | Buscar por DPI |
| POST | /clientes | Crear cliente |
| PUT | /clientes/{dpi} | Actualizar cliente |
| DELETE | /clientes/{dpi} | Eliminar cliente |

### Usuarios
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /usuarios | Listar todos |
| GET | /usuarios/activos | Listar activos |
| GET | /usuarios/{id} | Buscar por ID |
| POST | /usuarios | Crear usuario |
| PUT | /usuarios/{id} | Actualizar usuario |
| DELETE | /usuarios/{id} | Eliminar usuario |

### Ventas
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /ventas | Listar todas |
| GET | /ventas/activos | Listar activas |
| GET | /ventas/{id} | Buscar por ID |
| POST | /ventas | Crear venta |
| PUT | /ventas/{id} | Actualizar venta |
| DELETE | /ventas/{id} | Eliminar venta |

### Productos
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /productos | Listar todos |
| GET | /productos/activos | Listar activos |
| GET | /productos/{id} | Buscar por ID |
| POST | /productos | Crear producto |
| PUT | /productos/{id} | Actualizar producto |
| DELETE | /productos/{id} | Eliminar producto |

### DetalleVenta
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /detalles | Listar todos |
| GET | /detalles/{id} | Buscar por ID |
| POST | /detalles | Crear detalle |
| PUT | /detalles/{id} | Actualizar detalle |
| DELETE | /detalles/{id} | Eliminar detalle |

## Autor

**Julian Jax** - jjax-2025549
Kinal - IN5AV   