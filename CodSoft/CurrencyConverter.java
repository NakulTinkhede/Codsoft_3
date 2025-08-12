import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_KEY = "3deaeec30af6db7c0a886d53"; // Your API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().trim().toUpperCase().replaceAll("[^A-Z]", "");

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.nextLine().trim().toUpperCase().replaceAll("[^A-Z]", "");

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(baseCurrency, targetCurrency);
        if (rate != -1) {
            double convertedAmount = amount * rate;
            System.out.println("Exchange Rate: 1 " + baseCurrency + " = " + rate + " " + targetCurrency);
            System.out.println("Converted Amount = " + convertedAmount + " " + targetCurrency);
        } else {
            System.out.println("Invalid currency code or not supported.");
        }

        scanner.close(); // Resource leak warning fix
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String urlStr = API_URL + API_KEY + "/latest/" + baseCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            if (json.getString("result").equals("success")) {
                return json.getJSONObject("conversion_rates").getDouble(targetCurrency);
            }
        } catch (Exception e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
        }
        return -1;
    }
}



// javac -cp ".;lib/json-20230618.jar" CurrencyConverter.java

// java -cp ".;lib/json-20230618.jar" CurrencyConverter