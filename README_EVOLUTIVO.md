# Sistema de Gestión de Pedidos - Evolutivo Completado ✅

## 📋 Descripción

Sistema de gestión de pedidos mejorado que ahora incluye:

- ✅ Visualización de lista de pedidos disponibles
- ✅ Creación de nuevos pedidos mediante formulario
- ✅ Eliminación de pedidos existentes
- ✅ Edición de cantidad y descuento en artículos
- ✅ Persistencia automática en JSON
- ✅ Búsqueda y visualización de detalles
- ✅ Conversión automática EUR/USD

---

## 🚀 Cómo Ejecutar

### Compilar
```bash
cd /Users/ginolevano/Documents/GitHub/IngSotfwarePrueba
mvn clean package -DskipTests
```

### Ejecutar
```bash
java -jar target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

## 📖 Cómo Usar

### 1️⃣ Ver Lista de Pedidos
- Los IDs aparecen en el panel izquierdo de la interfaz
- Haz clic en un ID para seleccionarlo

### 2️⃣ Ver Detalles de un Pedido
- **Opción 1:** Haz clic en un ID del panel izquierdo
- **Opción 2:** Ingresa el ID en el campo de búsqueda y haz clic "Search"
- Se mostrarán todos los artículos, totales en EUR y USD

### 3️⃣ Crear Nuevo Pedido
1. Haz clic en botón "Create Order"
2. Ingresa un **ID único** para el pedido
3. Completa el primer artículo (nombre, cantidad, precio, descuento)
4. Haz clic en "Add Article" para agregar más
5. Haz clic en "Create" para guardar

**Validaciones:**
- El ID debe ser único (no puede existir otro pedido con el mismo ID)
- Se requiere mínimo 1 artículo
- Cantidad debe ser > 0

### 4️⃣ Eliminar Pedido
1. Selecciona un pedido del panel izquierdo
2. Haz clic en botón "Delete Order"
3. El pedido se eliminará del JSON automáticamente

### 5️⃣ Editar Pedido
1. Selecciona un pedido del panel izquierdo
2. Haz clic en botón "Edit Order"
3. Modifica la **cantidad** o **descuento** de los artículos
   - ⚠️ No puedes editar nombre o precio unitario
4. Haz clic en "Save Changes"
5. Los cambios se guardarán en el JSON

**Validaciones:**
- Cantidad debe ser > 0
- Descuento debe estar entre 0-100%

---

## 📁 Estructura del Proyecto

```
src/main/java/com/example/
├── controller/
│   ├── Main.java                    (Punto de entrada)
│   └── OrderController.java         (Controlador MVC)
├── model/
│   ├── Article.java                 (Modelo de artículo)
│   ├── Order.java                   (Modelo de pedido)
│   ├── Calculator.java              (Cálculos)
│   ├── ExchangeRateService.java     (Tipo de cambio)
│   └── Searcher.java               (Búsqueda)
├── service/
│   └── OrderService.java            ⭐ NUEVO (Persistencia)
└── view/
    ├── OrderView.java               (Vista principal - MEJORADA)
    ├── CreateOrderDialog.java       ⭐ NUEVO (Crear)
    └── EditOrderDialog.java         ⭐ NUEVO (Editar)

src/main/resources/
├── orders.json                      (Base de datos)
└── plantuml/
    ├── diagramasDeCasos.puml        (ACTUALIZADO ✅)
    ├── diagramasDeClases.puml
    └── diagramasDeSecuencia.puml
```

---

## 🔄 Flujo de Datos

```
User Interface (Swing)
        ↓
OrderController (Listeners)
        ↓
OrderService (Persistencia)
        ↓
orders.json (Base de datos)
```

---

## 💾 Persistencia de Datos

- El archivo `orders.json` se ubica en `src/main/resources/`
- Se actualiza automáticamente en cada operación
- Formato JSON con sangría para legibilidad
- Los datos persisten entre ejecuciones

---

## 🎯 Casos de Uso Implementados

| Caso de Uso | Actor | Acción |
|---|---|---|
| UC0 | Usuario | Ver Lista de Pedidos |
| UC1 | Usuario | Crear Pedido |
| UC2 | Sistema | Agregar Artículo |
| UC4 | Usuario | Ver Pedido |
| UC6 | Sistema | Calcular Total |
| UC8 | Usuario | Eliminar Pedido |
| UC9 | Usuario | Editar Pedido ⭐ NUEVO |
| UC10 | Sistema | Modificar Cantidad ⭐ NUEVO |
| UC11 | Sistema | Modificar Descuento ⭐ NUEVO |
| UC12 | Sistema | Guardar en JSON ⭐ NUEVO |

---

## 🛠️ Tecnologías

- **Java 21**
- **Maven 3.9+**
- **Jackson** (JSON)
- **Swing** (GUI)
- **SLF4J + Logback** (Logging)

---

## ✅ Requisitos Cumplidos

- [x] Mostrar lista de IDs en primera pantalla
- [x] Crear pedido mediante formulario
- [x] Guardar en archivo JSON
- [x] Validar IDs únicos
- [x] Eliminar pedidos
- [x] Actualizar JSON
- [x] Diagrama de casos de uso actualizado
- [x] Editar pedido (cantidad y descuento)
- [x] Persistencia entre ejecuciones

---

## 📝 Archivos Nuevos

1. **OrderService.java** - Servicio de persistencia
2. **CreateOrderDialog.java** - Diálogo para crear
3. **EditOrderDialog.java** - Diálogo para editar

---

## 🔍 Logs

Los logs se guardan en `logs/` y muestran:
- Pedidos cargados
- Operaciones de guardado
- Búsquedas realizadas
- Errores y excepciones

---

## 📞 Soporte

Para cualquier pregunta o error, revisa los logs en la carpeta `logs/`.

---

**Estado: ✅ COMPLETO Y FUNCIONAL**

*Última actualización: 30/12/2024*
