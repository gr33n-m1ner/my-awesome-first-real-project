import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class mysource {
    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();
        int id = 227995590;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.vk.com/method/wall.get?owner_id=" + id + "&count=1&access_token=a6304274a6304274a630427449a646a8a8aa630a6304274c6615027e743748aff31bf1d&v=5.52"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
