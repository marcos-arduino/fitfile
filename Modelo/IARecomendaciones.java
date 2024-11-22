package Modelo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;

public class IARecomendaciones {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/engines/davinci/completions";

    public static String obtenerReceta(String prompt) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            String inputJson = "{ \"prompt\": \"" + prompt + "\", \"max_tokens\": 150 }";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = inputJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (InputStream is = connection.getInputStream(); Scanner scanner = new Scanner(is)) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                return response.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No se pudo obtener una receta.";
    }
}
