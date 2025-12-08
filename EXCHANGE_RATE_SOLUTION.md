# Solución: Servicio de Tipo de Cambio EUR/USD

## Descripción del Problema

La aplicación necesita mostrar el total de los pedidos en **euros** y **dólares**. Dado que el tipo de cambio EUR/USD varía constantemente, no es viable almacenarlo como una constante en la aplicación.

## Solución Implementada

Se creó la clase **`ExchangeRateService`** que obtiene el tipo de cambio actual desde una **API externa gratuita**.

### API Utilizada

- **Proveedor**: exchangerate-api.com
- **URL**: `https://api.exchangerate-api.com/v4/latest/EUR`
- **Autenticación**: No requiere clave API
- **Límites**: 1500 peticiones/mes (plan gratuito)
- **Formato de respuesta**: JSON

### Ejemplo de Respuesta

```json
{
  "result": "success",
  "base_code": "EUR",
  "target_code": "USD",
  "conversion_rate": 1.08,
  "rates": {
    "USD": 1.0854,
    "GBP": 0.8350,
    "JPY": 162.82,
    ...
  }
}
```

## Características de la Implementación

### Métodos Principales

1. **`getEURtoUSDRate(): double`**
   - Obtiene el tipo de cambio actual EUR → USD
   - Realiza una petición HTTP GET a la API
   - Retorna un `double` con el tipo de cambio
   - Gestiona excepciones de conexión

2. **`convertEURtoUSD(double amountEUR): double`**
   - Convierte una cantidad en EUR a USD
   - Usa el tipo de cambio actual obtenido de la API
   - Retorna el monto convertido

3. **`parseExchangeRate(String jsonResponse): double`**
   - Parsea la respuesta JSON
   - Extrae el valor de cambio USD
   - Maneja errores de parsing

### Características de Seguridad

- **Timeout**: 5 segundos máximo para la petición HTTP
- **Validación**: Verifica que la respuesta contenga los campos esperados
- **Manejo de excepciones**: Captura `IOException` e `InterruptedException`
- **HttpClient**: Usa Java 11+ HttpClient (no requiere librerías externas)

## Integración en la Aplicación

### En `OrderView.java`

El servicio se utiliza para mostrar el total en ambas monedas:

```java
ExchangeRateService exchangeService = new ExchangeRateService();
try {
    double rate = exchangeService.getEURtoUSDRate();
    
    // Mostrar totales
    sb.append("Total EUR: ").append(totalEUR).append("€\n");
    sb.append("Total USD: ").append(totalEUR * rate).append("$\n");
} catch (IOException | InterruptedException e) {
    // Fallback: mostrar solo EUR si hay error
    sb.append("Total EUR: ").append(totalEUR).append("€\n");
    sb.append("(No se pudo obtener tipo de cambio)\n");
}
```

## Ventajas de esta Solución

✅ **Gratuita**: No requiere clave API  
✅ **Simple**: Usa HTTP nativo de Java  
✅ **Rápida**: Respuesta en milisegundos  
✅ **Confiable**: API establecida y estable  
✅ **Sin dependencias externas**: Solo usa Jackson (ya presente)  
✅ **Documentada**: Javadoc incluido  

## Alternativas Consideradas

| Alternativa | Ventajas | Desventajas |
|---|---|---|
| **exchangerate-api.com** ✓ | Gratuita, sin autenticación, rápida | Límite de peticiones |
| **open.er-api.com** | Gratuita, sin autenticación | Menos documentada |
| **fixer.io** | Completa, histórico | Requiere API key |
| **openexchangerates.org** | Histórico detallado | Requiere API key |
| **constante hardcodeada** | Rápida | Inexacta y poco realista |

## Manejo de Errores

Si la API falla o no está disponible:
1. Se lanza una excepción `IOException`
2. En `OrderView`, se captura y se muestra solo el precio en EUR
3. Se registra el error en logs
4. La aplicación continúa funcionando normalmente

## Testing

Para probar el servicio:

```java
ExchangeRateService service = new ExchangeRateService();
double rate = service.getEURtoUSDRate();
System.out.println("1 EUR = " + rate + " USD");

double converted = service.convertEURtoUSD(100);
System.out.println("100 EUR = " + converted + " USD");
```

## Dependencias Necesarias

El proyecto ya tiene **Jackson** para parsing JSON:
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.20.0</version>
</dependency>
```

## Referencias

- [exchangerate-api.com Documentation](https://www.exchangerate-api.com/docs)
- [Java HttpClient](https://docs.oracle.com/en/java/javase/21/docs/api/java.net.http/java/net/http/HttpClient.html)
- [Jackson Documentation](https://github.com/FasterXML/jackson)
