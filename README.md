# 📦 Sistema de Gestión de Pedidos - Order Management System

Sistema de gestión integral de pedidos desarrollado en **Java 21** con **Maven**, **Swing** para GUI y **JSON** para persistencia de datos.

## 🎯 Funcionalidades Implementadas

 **Ver Lista de Pedidos** - Visualizar todos los pedidos disponibles  
 **Buscar Pedido** - Búsqueda rápida por ID de pedido  
 **Crear Nuevo Pedido** - Añadir pedidos con artículos (ID único validado)  
 **Eliminar Pedido** - Borrar pedidos existentes con persistencia  
 **Editar Pedido** - Modificar cantidad y descuento de artículos  
 **Ver Detalles** - Visualizar detalles completos con cálculo de totales en EUR/USD  
---

## 📋 Tabla de Contenidos

- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Cómo Usar](#cómo-usar)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Arquitectura](#arquitectura)
- [Casos de Uso](#casos-de-uso)
- [Tecnologías](#tecnologías)

---

## 🔧 Requisitos

- **Java 21+** (JDK) - Descarga desde [Oracle](https://www.oracle.com/java/technologies/downloads/) o [OpenJDK](https://openjdk.java.net/)
- **Maven 3.9+** - Instala desde [maven.apache.org](https://maven.apache.org/install.html)
- **Opcional:** PlantUML para generar diagramas

**Verificar instalación:**
```bash
java -version
mvn -version
```

---

##  Instalación

### 1. Clonar/Descargar el Proyecto
```bash
cd /ruta/del/proyecto
```

### 2. Compilar el Proyecto
```bash
mvn clean package -DskipTests
```

### 3. Ejecutar la Aplicación
```bash
java -jar target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

##  Cómo Usar

### Pantalla Principal

La interfaz contiene 4 secciones:

```
┌─────────────────────────────────────────────┐
│ Search Order ID: [______] [Search]          │
├──────────────────┬──────────────────────────┤
│ Available Orders │ Order Details            │
│                  │                          │
│ O001             │ (Mostrar detalles aquí) │
│ O002             │                          │
│ O003             │                          │
│                  │                          │
├──────────────────┴──────────────────────────┤
│ [Create Order] [Delete Order] [Edit Order]  │
└──────────────────────────────────────────────┘
```

### 📖 Paso a Paso

#### 1️⃣ Ver Lista de Pedidos
- Los pedidos (O001-O005) aparecen automáticamente en la lista izquierda al iniciar
- Haz clic en uno para ver sus detalles en la derecha

#### 2️⃣ Buscar Pedido
- Escribe el ID (ej: "O002") en el campo de búsqueda
- Haz clic en "Search"
- Se mostrará el pedido con todos sus detalles

#### 3️⃣ Crear Nuevo Pedido
1. Haz clic en **"Create Order"**
2. Se abrirá un formulario modal
3. Ingresa:
   - **Order ID**: Ej: "O006" (debe ser único)
   - **Article Name**: Ej: "Teclado"
   - **Quantity**: Ej: "1"
   - **Unit Price**: Ej: "150"
   - **Discount %**: Ej: "10"
4. Haz clic en "Create"
5. ✅ El nuevo pedido se guardará en `orders.json` y aparecerá en la lista

#### 4️⃣ Editar Pedido
1. **Selecciona** un pedido de la lista (debe verse azul/resaltado)
2. Haz clic en **"Edit Order"**
3. Se abrirá un formulario con los artículos
4. Modifica:
   - **Quantity** - Cantidad del artículo
   - **Discount** - Porcentaje de descuento
5. Haz clic en "Confirm"
6. ✅ Los cambios se guardarán en `orders.json`

#### 5️⃣ Eliminar Pedido
1. **Selecciona** un pedido de la lista
2. Haz clic en **"Delete Order"**
3. El pedido se eliminará
4. ✅ Se guardará en `orders.json` sin ese pedido

#### 6️⃣ Ver Detalles
- Haz clic en cualquier pedido de la lista
- La derecha mostrará:
  - Lista de artículos con sus precios
  - Cantidad y descuento por artículo
  - **Total Bruto** en EUR y USD
  - **Total con Descuento** en EUR y USD
  - Tipo de cambio EUR/USD actual

---

## 📁 Estructura del Proyecto

```
IngSotfwarePrueba/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── controller/
│   │   │   │   ├── Main.java              ← Punto de entrada
│   │   │   │   └── OrderController.java   ← Lógica de eventos
│   │   │   ├── model/
│   │   │   │   ├── Order.java             ← Modelo de pedido
│   │   │   │   ├── Article.java           ← Modelo de artículo
│   │   │   │   ├── Calculator.java        ← Cálculos
│   │   │   │   ├── Searcher.java          ← Búsquedas
│   │   │   │   └── ExchangeRateService.java ← Conversión EUR/USD
│   │   │   ├── service/
│   │   │   │   └── OrderService.java      ← Persistencia JSON
│   │   │   └── view/
│   │   │       ├── OrderView.java         ← GUI principal
│   │   │       ├── CreateOrderDialog.java ← Diálogo crear
│   │   │       └── EditOrderDialog.java   ← Diálogo editar
│   │   └── resources/
│   │       ├── orders.json               ← Datos iniciales
│   │       ├── logback.xml               ← Configuración logs
│   │       └── plantuml/
│   │           └── diagramasDeCasos.puml ← Diagrama UC
│   └── test/
│       ├── ArticleTestCase.java
│       ├── CalculatorTestCase.java
│       ├── OrderTestCase.java
│       └── SearcherTest.java
├── output/
│   └── diagramasDeCasos.png              ← Diagrama generado
├── pom.xml                               ← Configuración Maven
├── orders.json                           ← Datos en runtime (gitignored)
└── README.md                             ← Este archivo
```

---

## 🏗️ Arquitectura

### Patrón MVC
- **Model**: `Order`, `Article`, `Calculator`
- **View**: `OrderView`, `CreateOrderDialog`, `EditOrderDialog`
- **Controller**: `OrderController`
- **Service**: `OrderService` (persistencia)

### Persistencia de Datos

```
┌─────────────────────────────────────────────────┐
│ Primera Ejecución                               │
├─────────────────────────────────────────────────┤
│ src/main/resources/orders.json (plantilla)      │
│           ↓ (copia a)                           │
│ orders.json (raíz) ← FUENTE ÚNICA DE VERDAD    │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│ Próximas Ejecuciones                            │
├─────────────────────────────────────────────────┤
│ orders.json (raíz) ← Carga automáticamente      │
│           ↓ (cambios)                           │
│ Se actualiza con cada CRUD                      │
└─────────────────────────────────────────────────┘
```

### Estructura JSON

```json
[
  {
    "id": "O001",
    "articles": [
      {
        "name": "Keyboard",
        "quantity": 2,
        "unitPrice": 50.0,
        "discount": 10.0
      }
    ]
  }
]
```

---

## 🎯 Casos de Uso Implementados

| UC | Caso de Uso | Estado |
|----|------------|--------|
| UC1 | Ver Lista de Pedidos | ✅ Implementado |
| UC2 | Buscar Pedido | ✅ Implementado |
| UC3 | Crear Nuevo Pedido | ✅ Implementado |
| UC4 | Eliminar Pedido | ✅ Implementado |
| UC5 | Editar Pedido | ✅ Implementado |
| UC6 | Ver Detalles | ✅ Implementado |
| UC7 | Validar ID Único | ✅ Implementado |
| UC8 | Agregar Artículo | ✅ Implementado |
| UC9 | Modificar Cantidad | ✅ Implementado |
| UC10 | Modificar Descuento | ✅ Implementado |
| UC11 | Calcular Totales | ✅ Implementado |
| UC12 | Guardar en JSON | ✅ Implementado |
| UC13 | Cargar desde JSON | ✅ Implementado |

📊 **Ver diagrama visual**: `output/diagramasDeCasos.png`

---

## 🔧 Tecnologías

- **Java 21** - Lenguaje de programación
- **Maven 3.9+** - Gestor de dependencias y build
- **Swing** - Framework GUI
- **Jackson** - Serialización JSON
- **SLF4J + Logback** - Logging
- **PlantUML** - Diagramas UML

---

## 📝 Datos de Ejemplo

La aplicación viene con 5 pedidos de ejemplo:

| ID | Artículos | Total |
|----|-----------|-------|
| O001 | Keyboard, Mouse, Charger | €150.00 |
| O002 | Monitor, Headset | €280.00 |
| O003 | Call of Duty, Gaming Chair | €490.00 |
| O004 | Laptop (x3) | €2,670.00 |
| O005 | iPhone X, Nvidia Card, AirPods, Garmin Watch | €5,215.00 |

---

## 🐛 Troubleshooting

**P: Los botones Edit/Delete no responden**  
R: Debes **seleccionar** un pedido de la lista primero (debe verse azul)

**P: No veo los cambios en la app después de crear/editar**  
R: Reinicia la aplicación. Los datos se cargan al inicio desde `orders.json`

**P: ¿Dónde se guardan mis cambios?**  
R: En el archivo `orders.json` en la raíz del proyecto (gitignored por seguridad)

**P: ¿Puedo perder datos?**  
R: No, cada operación guarda en `orders.json` inmediatamente

---

## 📄 Licencia

Este proyecto es de código abierto bajo licencia MIT.

---

**Desarrollado como proyecto de Ingeniería del Software - 2024**
