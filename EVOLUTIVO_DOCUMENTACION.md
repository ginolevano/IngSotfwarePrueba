# Evolutivo del Sistema de Gestión de Pedidos - Documentación

## 🎯 Objetivos Completados

Se han implementado exitosamente todos los requisitos del evolutivo:

### 1. ✅ Mostrar Lista de IDs de Pedidos en Primera Pantalla
- Implementado un panel izquierdo en la interfaz que muestra todos los IDs disponibles
- Los usuarios pueden hacer clic en un ID para visualizar los detalles del pedido
- La lista se actualiza automáticamente cuando se crean o eliminan pedidos

### 2. ✅ Crear Pedidos mediante Formulario
- Nuevo diálogo `CreateOrderDialog` que permite:
  - Ingresar un ID único para el pedido
  - Agregar múltiples artículos dinámicamente
  - Especificar nombre, cantidad, precio y descuento para cada artículo
  - Validar que el ID sea único antes de guardar
- Los pedidos se guardan inmediatamente en el archivo JSON
- Los nuevos pedidos aparecen en la lista y están disponibles en ejecuciones futuras

### 3. ✅ Eliminar Pedidos
- Botón "Delete Order" en la interfaz
- Selecciona el pedido de la lista y presiona el botón para eliminarlo
- Confirma la eliminación en el archivo JSON
- La lista se actualiza automáticamente

### 4. ✅ Editar Pedidos (Funcionalidad Opcional)
- Nuevo diálogo `EditOrderDialog` que permite:
  - Modificar solo la cantidad y descuento de artículos (como se pidió)
  - Los campos nombre, precio unitario son solo lectura
  - Validar que los valores estén en rangos válidos (cantidad > 0, descuento 0-100%)
- Los cambios se guardan en el archivo JSON

### 5. ✅ Diagrama de Casos de Uso Actualizado
- Agregados nuevos casos de uso:
  - UC0: Ver Lista de Pedidos
  - UC9: Editar Pedido
  - UC10: Modificar Cantidad Artículo
  - UC11: Modificar Descuento Artículo
  - UC12: Guardar en JSON
- Actualizado todas las relaciones entre casos de uso

---

## 📁 Cambios en la Estructura del Proyecto

### Nuevos Archivos Creados

1. **`src/main/java/com/example/service/OrderService.java`**
   - Servicio centralizado para la persistencia de datos
   - Métodos: `loadOrders()`, `saveOrders()`, `isUniqueOrderId()`
   - Maneja la lectura/escritura del archivo JSON

2. **`src/main/java/com/example/view/CreateOrderDialog.java`**
   - Diálogo modal para crear nuevos pedidos
   - Incluye clase interna `ArticlePanel` para gestionar artículos
   - Valida IDs únicos y datos requeridos

3. **`src/main/java/com/example/view/EditOrderDialog.java`**
   - Diálogo modal para editar pedidos existentes
   - Incluye clase interna `EditArticlePanel` para editar artículos
   - Solo permite editar cantidad y descuento

### Archivos Modificados

1. **`src/main/java/com/example/controller/Main.java`**
   - Refactorizado para usar `OrderService`
   - Simplificado el código principal
   - Inicializa la GUI pasando los pedidos y el servicio

2. **`src/main/java/com/example/view/OrderView.java`**
   - Completa rediseño de la interfaz
   - Agregado panel izquierdo con lista de IDs
   - Agregados botones de acción (Create, Delete, Edit)
   - Mejorado layout usando BorderLayout y GridLayout
   - Método `refreshOrderIdsList()` para actualizar la lista dinámicamente

3. **`src/main/java/com/example/controller/OrderController.java`**
   - Agregados listeners para nuevos botones
   - Implementados métodos: `createOrder()`, `deleteOrder()`, `editOrder()`
   - Gestión de persistencia integrada

4. **`src/main/resources/plantuml/diagramasDeCasos.puml`**
   - Actualizado diagrama de casos de uso
   - Agregados nuevos casos de uso para los requisitos
   - Mejoradas las relaciones entre casos

---

## 🔧 Características Técnicas

### Validaciones Implementadas

- ✅ IDs únicos para pedidos (evita duplicados)
- ✅ Cantidad de artículos > 0
- ✅ Descuento entre 0 y 100%
- ✅ Artículos requeridos (mínimo 1)
- ✅ Manejo robusto de excepciones

### Persistencia de Datos

- El archivo `orders.json` se actualiza automáticamente
- Soporta guardar en `target/classes/` o en la carpeta de trabajo
- Formato JSON con sangría para mejor legibilidad
- Logging detallado de operaciones

### Interfaz de Usuario

- Diseño moderno con panels organizados
- Actualización dinámica de listas
- Diálogos modales para operaciones complejas
- Mensajes de error informativos
- Conversión automática EUR/USD en detalles

---

## 🚀 Cómo Usar la Aplicación

### Ejecutar la Aplicación
```bash
cd /Users/ginolevano/Documents/GitHub/IngSotfwarePrueba
mvn clean package
java -jar target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Funcionalidades

1. **Ver Lista de Pedidos**
   - Los IDs aparecen en el panel izquierdo
   - Haz clic en uno para ver detalles

2. **Crear Nuevo Pedido**
   - Click en botón "Create Order"
   - Ingresa ID único
   - Agrega artículos con "+Add Article"
   - Click en "Create"

3. **Eliminar Pedido**
   - Selecciona pedido de la lista
   - Click en "Delete Order"
   - Se elimina del JSON automáticamente

4. **Editar Pedido**
   - Selecciona pedido de la lista
   - Click en "Edit Order"
   - Modifica cantidad/descuento
   - Click en "Save Changes"

5. **Buscar Pedido**
   - Ingresa ID en campo de búsqueda
   - Click en "Search"
   - Ver detalles con conversión a USD

---

## 📊 Diagrama de Casos de Uso - Actualizado

```
Actor: Usuario
Casos de Uso:
- Ver Lista de Pedidos (UC0)
  └─ include: Ver Pedido
- Crear Pedido (UC1)
  ├─ include: Agregar Artículo
  ├─ include: Calcular Total
  └─ include: Guardar en JSON
- Eliminar Pedido (UC8)
  └─ include: Guardar en JSON
- Editar Pedido (UC9)
  ├─ include: Modificar Cantidad
  ├─ include: Modificar Descuento
  ├─ include: Calcular Total
  └─ include: Guardar en JSON
```

---

## ✅ Checklist de Requisitos

- [x] Mostrar lista de IDs en primera pantalla
- [x] Buscar y ver detalles de pedidos
- [x] Crear pedidos mediante formulario
- [x] Guardar en archivo JSON
- [x] Validar IDs únicos
- [x] Eliminar pedidos
- [x] Actualizar JSON al eliminar
- [x] Actualizar diagrama de casos de uso (SOLO casos de uso)
- [x] Editar pedidos (cantidad y descuento)
- [x] Persistencia de datos entre ejecuciones

---

## 🔍 Notas Importantes

- El archivo `orders.json` se guarda con formato legible (pretty-printed)
- Los logs detallados facilitan el debugging
- La aplicación es totalmente funcional en modo standalone
- Soporta múltiples artículos por pedido
- Conversión EUR/USD automática en detalles

---

## 📦 Dependencias Utilizadas

- Jackson (JSON parsing/serialization)
- SLF4J con Logback (logging)
- Swing (GUI)
- Java 21+

---

**Estado: ✅ COMPLETO - Todos los requisitos implementados exitosamente**
