
---
## Resumen

Esta release incluye la implementación completa del **Sistema de Gestión de Pedidos** con todas las funcionalidades CRUD solicitadas, arquitectura mejorada y documentación completa.

---

## Nuevas Características

### Funcionalidades Principales (6 UCs)

[OK] **UC1 - Ver Lista de Pedidos**
- Visualiza todos los pedidos disponibles en una lista interactiva
- Carga automática en el inicio

[OK] **UC2 - Buscar Pedido**
- Búsqueda rápida por ID de pedido
- Campo de búsqueda con validación

[OK] **UC3 - Crear Nuevo Pedido**
- Creación de pedidos con artículos múltiples
- Validación de ID único
- Diálogo modal intuitivo
- Persistencia inmediata en orders.json

[OK] **UC4 - Eliminar Pedido**
- Eliminación con un clic
- Requiere selección previa
- Se guarda inmediatamente

[OK] **UC5 - Editar Pedido**
- Modificación de cantidad y descuento
- Recálculo automático de totales
- Persistencia instantánea

[OK] **UC6 - Ver Detalles**
- Visualización completa de pedidos
- Cálculo de totales en EUR y USD
- Tipo de cambio actualizado

### Funcionalidades Secundarias (7 UCs)

[OK] **UC7** - Validación de ID único  
[OK] **UC8** - Agregar artículos a pedidos  
[OK] **UC9** - Modificar cantidad de artículos  
[OK] **UC10** - Modificar descuento de artículos  
[OK] **UC11** - Cálculo automático de totales  
[OK] **UC12** - Guardado en JSON  
[OK] **UC13** - Carga desde JSON  

---

## Cambios en la Arquitectura

### Persistencia Mejorada
- **Antes:** Múltiples archivos JSON con sincronización compleja
- **Ahora:** Fuente única de verdad en `orders.json` (raíz)
- Primera ejecución: Copia desde `src/main/resources/orders.json`
- Subsecuentes: Carga y guarda en raíz automáticamente

### Patrón MVC Robusto
```
Model (Order, Article, Calculator)
  |
Controller (OrderController) <- UI Events
  |
Service (OrderService) <- Persistence
  |
View (OrderView, Dialogs)
```

### Validaciones Agregadas
- [OK] ID de pedido único
- [OK] Selección requerida para Delete/Edit
- [OK] Manejo de errores en JSON
- [OK] @JsonIgnoreProperties para compatibilidad

---

## Diagrama de Casos de Uso

El diagrama actualizado está disponible en `output/diagramasDeCasos.png`

Incluye:
- 6 casos de uso principales
- 7 casos de uso secundarios
- Relaciones de inclusión y extensión
- Flujos de negocio claros

---

## Archivos de Release

```
/output/
  ├── demo-1.0-SNAPSHOT-jar-with-dependencies.jar  <- JAR ejecutable
  ├── diagramasDeCasos.png                          <- Diagrama UC
  └── ... (otros archivos)
```

---

## Cómo Usar la Release

### 1. Descargar
```bash
git checkout v2.0.0
# O descargar el JAR desde output/
```

### 2. Requisitos
- Java 21+ instalado
- Maven 3.9+ (solo si compilas)

### 3. Ejecutar
```bash
java -jar output/demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### 4. Primera Ejecución
- Se crea automáticamente `orders.json` en la raíz
- Se cargan 5 pedidos de ejemplo (O001-O005)
- Puedes crear más con el botón "Create Order"

---

## Mejoras Técnicas

### Código
- Optimización de imports
- Mejor separación de responsabilidades
- Validación consistente
- Manejo de excepciones robusto

### Dependencias
- Jackson 2.x para JSON
- Logback para logging
- Swing nativo de Java

### Testing
- Pruebas unitarias incluidas
- Validación manual completada
- Casos de prueba proporcionados

---

## Datos de Ejemplo

Al ejecutar por primera vez, se cargan 5 pedidos:

| ID | Artículos | Total |
|----|-----------|-------|
| O001 | Keyboard, Mouse, Charger | 150.00 EUR |
| O002 | Monitor, Headset | 280.00 EUR |
| O003 | Call of Duty, Gaming Chair | 490.00 EUR |
| O004 | Laptop (x3) | 2,670.00 EUR |
| O005 | iPhone X, Nvidia Card, AirPods, Garmin Watch | 5,215.00 EUR |

---

## Problemas Conocidos

PROBLEMA A LA HORA DE CONECTAR EL .JSON / PERO SOLUCIONADO 
---

## Documentación

### Incluida
- **README.md** - Guía completa de usuario
- **RELEASE_NOTES.md** - Este archivo
- **diagramasDeCasos.puml** - Diagrama UML fuente
- **output/diagramasDeCasos.png** - Diagrama PNG generado

### Código Comentado
Todas las clases principales tienen comentarios explicativos:
- `OrderService.java` - Persistencia
- `OrderController.java` - Control de eventos
- `OrderView.java` - Interfaz de usuario
- `CreateOrderDialog.java` - Diálogo de creación
- `EditOrderDialog.java` - Diálogo de edición

---

## Próximas Mejoras (v3.0+)

- [ ] Base de datos SQL
- [ ] API REST
- [ ] Validación avanzada
- [ ] Reportes PDF
- [ ] Autenticación de usuarios
- [ ] Interfaz web
- [ ] Sincronización en la nube

---

## Licencia

MIT License - Libre para usar y modificar

---

## Contribuciones

Para reportar bugs o sugerir mejoras, abre un issue en el repositorio.

---

**Versión:** v2.0.0  
**Compilada:** 2024-12-30  
**Build:** demo-1.0-SNAPSHOT-jar-with-dependencies.jar (3.2 MB)  
**Java:** 21+  
**Status:** Producción
