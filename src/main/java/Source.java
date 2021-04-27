import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Source {
    public static HttpClient client = HttpClient.newHttpClient();
    public static String token = "a6304274a6304274a630427449a646a8a8aa630a6304274c6615027e743748aff31bf1d";

    public static boolean isInt(String input) {
        boolean checkInt = true;
        char [] q = input.toCharArray();
        for (int i = 0; i < input.length(); ++i) {
            checkInt = checkInt && (int) q[i] >= (int) '0' && (int) q[i] <= (int) '9';
        }
        return checkInt;
    }

    private static String getWallPosts(String ownerId, String count) {

        String url = "https://api.vk.com/method/wall.get?owner_id=" + ownerId + "&count=" + count + "&access_token=" + token + "&v=5.52";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }

    public static void main(String[] args) throws IOException {
        // 227995590
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ID страницы, с которой вы хотите взять посты");
        String id = null;

        boolean correctInput = false;
        boolean ifIncorrect = false;
        while (!correctInput) {
            if (ifIncorrect) System.out.println("Id должен состоять из 9 цифр. Попробуйте еще раз");
            String input = in.readLine();
            correctInput = isInt(input) && input.length() == 9;
            ifIncorrect = true;
            id = input;
        }

        System.out.println("Количество постов");
        String count = null;

        correctInput = false;
        ifIncorrect = false;
        while (!correctInput) {
            if (ifIncorrect) System.out.println("Количество постов нужно задавать числом. Попробуйте еще раз");
            String input = in.readLine();
            correctInput = isInt(input);
            ifIncorrect = true;
            count = input;
        }
        
        String response;
        response = getWallPosts(id, count);
        System.out.println(response);

    }
}
