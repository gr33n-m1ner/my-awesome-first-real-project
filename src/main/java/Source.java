
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Source {
    public static void main(String[] args) throws IOException {

        // 227995590

        BufferedReader In = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(In.readLine());
        int count = Integer.parseInt(In.readLine());

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
