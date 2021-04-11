
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;


public class Source {

    public static HttpClient client = HttpClient.newHttpClient();
    public static String token = "a6304274a6304274a630427449a646a8a8aa630a6304274c6615027e743748aff31bf1d";

    public static boolean isInt(String a) {
        boolean prov = true;
        char [] q = a.toCharArray();
        for (int i = 0; i < a.length(); ++i) {
            prov = prov && (int) q[i] >= (int) '0' && (int) q[i] <= (int) '9';
        }
        return prov;
    }

    private static String getWallPosts(String ownerId, int count) throws ExecutionException, InterruptedException {
        String response;

        String url = "https://api.vk.com/method/wall.get?owner_id=" + ownerId + "&count=" + count + "&access_token=" + token + "&v=5.52";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        return "aaa";
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // 227995590
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ID страницы, с которой вы хотите взять посты");
        String id;
        while (true) {
            String prov = in.readLine();
            if (!isInt(prov) || prov.length() != 9) {
                System.out.println("Нормально введи");
                continue;
            }
            id = prov;
            break;
        }
        System.out.println("Количество постов");
        int count;
        while (true) {
            String prov = in.readLine();
            if (!isInt(prov)) {
                System.out.println("Нормально введи");
                continue;
            }
            count = Integer.parseInt(prov);
            break;
        }
        String response;

        response = getWallPosts(id, count);
        System.out.println(response);

    }
}
