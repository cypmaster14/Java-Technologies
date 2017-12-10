import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String INSERT = "http://tenant2.local.com:8080/application?operation=insert&user=%s&email=%s&country=%s";
    private static final String DELETE = "http://tenant2.local.com:8080/application?operation=delete&id=%s";

    private static String SESSION_ID;

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        String user = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString();
        String country = UUID.randomUUID().toString();
        String urlString = String.format(INSERT, user, email, country);
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        initializeSessionIfNotSet(connection);

        for (int i = 0; i < 200; i++) {
            executorService.execute(() -> {
                String userName = UUID.randomUUID().toString();
                String emailString = UUID.randomUUID().toString();
                String countryString = UUID.randomUUID().toString();
                String urlStringg = String.format(INSERT, userName, emailString, countryString);
                try {
                    URL executorURL = new URL(urlStringg);
                    HttpURLConnection con = (HttpURLConnection) executorURL.openConnection();
                    if (SESSION_ID != null) {
                        con.setRequestProperty("Cookie", "JSESSIONID=" + SESSION_ID);
                    }
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code : " + responseCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    private static void initializeSessionIfNotSet(HttpURLConnection con) {
        if (SESSION_ID == null) {
            for (int j = 0; ; j++) {
                String headerName = con.getHeaderFieldKey(j);
                String headerValue = con.getHeaderField(j);

                if (headerName == null && headerValue == null) {
                    break;
                }
                if ("Set-Cookie".equalsIgnoreCase(headerName)) {
                    String[] fields = headerValue.split("=");
                    if (fields[0].equals("JSESSIONID")) {
                        if (SESSION_ID == null)
                            SESSION_ID = fields[1];
                    }
                }
            }
        }
    }
}
