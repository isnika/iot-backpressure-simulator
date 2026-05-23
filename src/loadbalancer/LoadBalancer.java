package loadbalancer;

import model.SensorData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoadBalancer {

    public void route(SensorData data) throws Exception {

        // phân node
        int port = (data.sensorId <= 100) ? 8001 :
                (data.sensorId <= 200) ? 8002 : 8003;

        //  convert object → JSON thủ công (KHÔNG CẦN JACKSON)
        String json = "{"
                + "\"sensorId\":" + data.sensorId + ","
                + "\"timestamp\":" + data.timestamp + ","
                + "\"temperature\":" + data.temperature + ","
                + "\"humidity\":" + data.humidity + ","
                + "\"pressure\":" + data.pressure
                + "}";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/sensor-data"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}