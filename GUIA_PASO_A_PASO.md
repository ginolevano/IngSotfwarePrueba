# 🎯 GUÍA PASO A PASO - EVOLUTIVO COMPLETADO

## 🚀 INICIO RÁPIDO (5 minutos)

### Paso 1: Compilar
```bash
cd /Users/ginolevano/Documents/GitHub/IngSotfwarePrueba
mvn clean package -DskipTests
```
✅ Resultado esperado: `BUILD SUCCESS`

### Paso 2: Ejecutar
```bash
java -jar target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```
✅ Se abrirá la interfaz gráfica

---

## 📋 FUNCIONALIDADES - GUÍA DE USO

### 1️⃣ VER LISTA DE PEDIDOS

**Dónde:** Panel izquierdo de la ventana

**Qué ves:**
```
Available Orders
┌─────────────────┐
│ • O001          │
│ • O002          │
│ • O003          │
│ • O004          │
│ • O005          │
└─────────────────┘
```

**Cómo usar:**
1. Abre la aplicación
2. Observa la lista en el panel izquierdo
3. Haz clic en cualquier ID para seleccionarlo

---

### 2️⃣ VER DETALLES DE UN PEDIDO

**Opción A: Desde la lista**
1. Haz clic en un ID del panel izquierdo
2. ✅ Los detalles aparecen en el panel derecho

**Opción B: Usando búsqueda**
1. Ingresa el ID en el campo "Order ID:"
2. Haz clic en botón "Search"
3. ✅ Los detalles aparecen en el panel derecho

**Qué ves:**
```
╔══════════════════════════════════════╗
║      ORDER DETAILS - O001            ║
╚══════════════════════════════════════╝

ITEMS:
────────────────────────────────────
[0] Keyboard x2 => 50.00€ (10% off) = 90.00€
[1] Mouse x1 => 30.00€ (0% off) = 30.00€
[2] Charger x1 => 20.00€ (5% off) = 19.00€

TOTALS:
────────────────────────────────────
Gross Total =>       139.00€ | 163.41$
Discounted Total =>  139.00€ | 163.41$
────────────────────────────────────
Exchange Rate: 1€ = 1.1748$
```

---

### 3️⃣ CREAR UN NUEVO PEDIDO

**Paso 1: Abrir diálogo**
- Haz clic en botón "Create Order"
- Se abrirá un diálogo

**Paso 2: Ingresar ID**
```
Order ID: [O006________________]
```
- Ingresa un ID único (ej: O006, TEST001, etc.)
- El ID NO debe existir ya

**Paso 3: Agregar artículos**
```
Articles

┌─ Article 1 ─────────────────┐
│ Name: [Laptop     ]          │
│ Qty: [2]  Price: [899.99]    │
│ Discount %: [10]             │
└──────────────────────────────┘
```
- Completa los 4 campos
- Cantidad debe ser ≥ 1
- Descuento debe estar 0-100%

**Paso 4: Agregar más artículos (opcional)**
- Haz clic en "+ Add Article"
- Se agregará otra fila
- Repite el Paso 3

**Paso 5: Crear el pedido**
- Haz clic en botón "Create"
- Si todo está bien:
  - ✅ Diálogo cierra
  - ✅ Nuevo ID aparece en la lista
  - ✅ Detalles se muestran en panel derecho
  - ✅ Dato se guardó en orders.json

**Si hay error:**
- ❌ "Order ID cannot be empty!" → Ingresa un ID
- ❌ "Order ID already exists!" → Usa otro ID
- ❌ "At least one article is required!" → Agrega un artículo
- ❌ Cantidad inválida → Usa número ≥ 1

---

### 4️⃣ ELIMINAR UN PEDIDO

**Paso 1: Seleccionar pedido**
- Haz clic en un ID del panel izquierdo
- El ID debe resaltarse

**Paso 2: Eliminar**
- Haz clic en botón "Delete Order"
- El pedido se elimina inmediatamente

**Resultado:**
- ✅ Pedido desaparece de la lista
- ✅ Panel derecho se vacía
- ✅ Datos se borran de orders.json

---

### 5️⃣ EDITAR UN PEDIDO

**Paso 1: Seleccionar pedido**
- Haz clic en un ID del panel izquierdo
- El ID debe resaltarse

**Paso 2: Abrir editor**
- Haz clic en botón "Edit Order"
- Se abrirá diálogo con los artículos actuales

**Paso 3: Modificar valores**
```
Article 1:
Name: Keyboard | Price: 50.00€
Quantity: [2]     Discount %: [10]
      ↓ Cambiar a:
Quantity: [5]     Discount %: [20]
```

Solo puedes editar:
- ✅ Cantidad (qty)
- ✅ Descuento (%)

NO puedes editar:
- ❌ Nombre del artículo
- ❌ Precio unitario

**Paso 4: Guardar cambios**
- Haz clic en "Save Changes"
- Los cambios se guardan automáticamente

**Si hay error:**
- ❌ "Quantity must be greater than 0!" → Usa número ≥ 1
- ❌ "Discount must be between 0 and 100!" → Rango 0-100

---

### 6️⃣ BUSCAR UN PEDIDO

**En la barra superior:**
```
Order ID: [______________]  [Search]
```

**Pasos:**
1. Ingresa el ID que buscas (ej: O001)
2. Haz clic en "Search"
3. Resultado:
   - ✅ Si existe: Se muestran detalles
   - ❌ Si no existe: Mensaje "Order not found, Try again!!"

---

## 🔍 DETALLES TÉCNICOS

### Dónde se guardan los datos

```
/Users/ginolevano/Documents/GitHub/IngSotfwarePrueba/
  └── src/main/resources/
      └── orders.json ← Aquí
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

### Cálculos
```
Bruto = Cantidad × Precio Unitario
Descuento = Bruto × (% / 100)
Neto = Bruto - Descuento

Ejemplo:
Quantity: 2
Unit Price: 50€
Discount: 10%

Bruto = 2 × 50 = 100€
Desc = 100 × 0.10 = 10€
Neto = 100 - 10 = 90€
```

---

## 🆘 SOLUCIÓN DE PROBLEMAS

### Problema: "No se puede crear pedido"
**Solución:**
- Verifica que el ID sea único
- Verifica que hayas agregado mínimo 1 artículo
- Verifica que los valores sean válidos

### Problema: "Los cambios no se guardan"
**Solución:**
- Reinicia la aplicación
- Verifica que orders.json exista
- Verifica los permisos del archivo

### Problema: "Error al compilar"
**Solución:**
```bash
mvn clean
mvn compile
```

### Problema: "Tipo de cambio no se actualiza"
**Solución:**
- Es normal, usa el valor mostrado
- Usa descuento 0 para valor exacto

---

## 🎓 EJEMPLOS PRÁCTICOS

### Ejemplo 1: Crear Pedido Simple
1. Click [Create Order]
2. ID: O_DEMO_1
3. Artículo: Notebook, 1, 899.99, 0
4. Click [Create]
5. ✅ Listo

### Ejemplo 2: Crear Pedido Complejo
1. Click [Create Order]
2. ID: O_DEMO_2
3. Art 1: Mouse, 5, 30, 10
4. [+ Add Article]
5. Art 2: Teclado, 2, 80, 5
6. [+ Add Article]
7. Art 3: Monitor, 1, 300, 15
8. Click [Create]
9. ✅ Listo

### Ejemplo 3: Editar Descuentos
1. Seleccionar O_DEMO_2
2. Click [Edit Order]
3. Cambiar descuentos:
   - Mouse: 10% → 20%
   - Teclado: 5% → 15%
   - Monitor: 15% → 25%
4. Click [Save Changes]
5. ✅ Totales se recalculan

---

## 📊 ATAJOS ÚTILES

| Acción | Método |
|--------|--------|
| Crear | [Create Order] o [Enter] en diálogo |
| Buscar | [Search] o [Enter] en campo |
| Seleccionar | Click en ID de lista |
| Eliminar | Seleccionar + [Delete Order] |
| Editar | Seleccionar + [Edit Order] |
| Cancelar | [Cancel] o cerrar diálogo |

---

## ✅ VERIFICACIÓN

**Para verificar que todo funciona:**

1. ✅ Puedo ver la lista de pedidos
2. ✅ Puedo crear un nuevo pedido
3. ✅ Puedo ver detalles de un pedido
4. ✅ Puedo editar cantidad y descuento
5. ✅ Puedo eliminar un pedido
6. ✅ Puedo buscar un pedido
7. ✅ Los datos persisten al reiniciar

Si todos los items están ✅, ¡el sistema funciona perfectamente!

---

## 📞 SOPORTE

- 📖 Documentación: Ver archivos .md en carpeta raíz
- 🔍 Logs: Ver carpeta `logs/`
- 💾 Datos: Ver `orders.json`
- 💻 Código: Ver `src/main/java/`

---

**¡Disfruta usando el Sistema de Gestión de Pedidos! 🎉**

Versión: 2.0 - Evolutivo
Estado: ✅ Completado y Funcional
