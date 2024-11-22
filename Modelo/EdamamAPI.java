package Modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EdamamAPI {
    private static final String API_URL = "https://api.edamam.com/api/nutrition-data";
    private static final String API_KEY = "c03e0be8529bab94076a8bbff8917e31";
    private static final String APP_ID = "96cf4bfc";

    public static String obtenerInformacionNutricional(String ingrediente) {
        try {
            String encodedIngredient = URLEncoder.encode(ingrediente, StandardCharsets.UTF_8.toString());
            URI uri = new URI(API_URL + "?app_id=" + APP_ID + "&app_key=" + API_KEY + "&ingr=" + encodedIngredient);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se pudo obtener la información nutricional.";
    }
}
