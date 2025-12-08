package com.example.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Servicio para obtener el tipo de cambio EUR/USD desde una API externa.
 */
public class ExchangeRateService {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/EUR";
    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public ExchangeRateService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Obtiene el tipo de cambio actual EUR/USD
     * @return El tipo de cambio EUR a USD
     * @throws IOException Si hay error en la conexión
     * @throws InterruptedException Si se interrumpe la petición
     */
    public double getEURtoUSDRate() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return parseExchangeRate(response.body());
        } else {
            throw new IOException("Error en la API: Status " + response.statusCode());
        }
    }

    /**
     *   parseamos la respuesta JSON para obtener el tipo de cambio
     * @param jsonResponse Respuesta JSON de la API
     * @return El tipo de cambio EUR a USD
     * @throws IOException Si hay error al parsear
     */
    private double parseExchangeRate(String jsonResponse) throws IOException {
        JsonNode ratesNode = objectMapper.readTree(jsonResponse).get("rates");
        if (ratesNode == null || ratesNode.get("USD") == null) {
            throw new IOException("No se encontró el tipo de cambio USD en la respuesta");
        }
        return ratesNode.get("USD").asDouble();
    }

    /**
     * Convierte un monto en EUR a USD
     * @param amountEUR Monto en euros
     * @return Monto equivalente en dólares
     * @throws IOException Si hay error en la conexión
     * @throws InterruptedException Si se interrumpe la petición
     */
    public double convertEURtoUSD(double amountEUR) throws IOException, InterruptedException {
        return amountEUR * getEURtoUSDRate();
    }
}
