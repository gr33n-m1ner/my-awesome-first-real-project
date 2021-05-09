import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Source {
    public static HttpClient client = HttpClient.newHttpClient();
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static String token = "a6304274a6304274a630427449a646a8a8aa630a6304274c6615027e743748aff31bf1d";

    public static boolean isInt(String input) {
        boolean checkInt = true;
        char [] charArrayInput = input.toCharArray();
        for (int i = 0; i < input.length(); ++i) {
            checkInt = checkInt && charArrayInput[i] >= '0' && charArrayInput[i] <= '9';
        }
        return checkInt;
    }

    public static String checkInput() throws IOException {
        String input = in.readLine();
        boolean correctInput = isInt(input);
        if (correctInput)
            return input;
        System.out.println("Входные данные должны состоять из цифр");
        return checkInput();
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

        System.out.println("ID страницы, с которой вы хотите взять посты. ID состоит из 9 цифр");
        String id = "";
        boolean ifIncorrect = false;

        while (id.length() != 9) {
            if (ifIncorrect)
                System.out.println("Id должен состоять из 9 цифр");
            id = checkInput();
            ifIncorrect = true;
        }
        System.out.println("Количество постов");
        String count;

        count = checkInput();
        
        String response;
        response = getWallPosts(id, count);
        System.out.println(response);

    }
}
