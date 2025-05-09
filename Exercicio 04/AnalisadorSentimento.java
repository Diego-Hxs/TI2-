import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnalisadorSentimento {
    public static void main(String[] args) throws Exception {
        String endpoint = "https://<SEU-ENDPOINT>.cognitiveservices.azure.com/";
        String chave = "<SUA-CHAVE>";

        String url = endpoint + "text/analytics/v3.0/sentiment";
        String jsonBody = "{ \"documents\": [ { \"id\": \"1\", \"language\": \"pt\", \"text\": \"Estou muito feliz com o resultado!\" } ] }";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Ocp-Apim-Subscription-Key", chave)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Resposta da API:");
        System.out.println(response.body());
    }
}
