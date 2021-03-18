
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class source {
    public static void main(String[] args) {

        int id = 227995590, count = 1;
        String token = "a6304274a6304274a630427449a646a8a8aa630a6304274c6615027e743748aff31bf1d";

        HttpClient client = HttpClient.newHttpClient();
        String url = "https://api.vk.com/method/wall.get?owner_id=" + id + "&count=" + count + "&access_token=" + token + "&v=5.52";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
