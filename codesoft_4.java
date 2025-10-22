import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverterPro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("💰 Welcome to Currency Converter Pro 💱");
        System.out.println("-------------------------------------------");

        System.out.print("Enter base currency (e.g., USD, EUR, INR): ");
        String baseCurrency = sc.next().toUpperCase();

        try {
            // Show chart for popular currencies
            System.out.println("\n📊 Currency Chart (Base: " + baseCurrency + ")");
            String[] topCurrencies = {"INR", "EUR", "GBP", "JPY", "AUD"};
            for (String target : topCurrencies) {
                double rate = getRate(baseCurrency, target);
                System.out.printf("1 %s = %.4f %s%n", baseCurrency, rate, target);
            }

            // Convert user input amount
            System.out.println("\n-------------------------------------------");
            System.out.print("Enter target currency: ");
            String targetCurrency = sc.next().toUpperCase();
            System.out.print("Enter amount to convert: ");
            double amount = sc.nextDouble();

            double result = convertAmount(baseCurrency, targetCurrency, amount);
            double rate = getRate(baseCurrency, targetCurrency);

            System.out.println("\n💹 Exchange Rate: 1 " + baseCurrency + " = " + rate + " " + targetCurrency);
            System.out.println("✅ Converted Amount: " + amount + " " + baseCurrency + " = " + result + " " + targetCurrency);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------");
        System.out.println("🙏 Thank you for using Currency Converter Pro!");
        System.out.println("Stay updated. Stay global 🌍💱");
        System.out.println("-------------------------------------------");

        sc.close();
    }

    // Function to get exchange rate between two currencies
    private static double getRate(String from, String to) {
        try {
            String urlStr = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to;
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

            String json = response.toString();
            double rate = 0.0;

            if (json.contains("\"rate\":")) {
                int start = json.indexOf("\"rate\":") + 7;
                int end = json.indexOf("}", start);
                String rateStr = json.substring(start, end).replaceAll("[^0-9.]", "");
                if (!rateStr.isEmpty()) {
                    rate = Double.parseDouble(rateStr);
                }
            }

            return rate;
        } catch (Exception e) {
            return 0.0;
        }
    }

    // Function to convert amount directly
    private static double convertAmount(String from, String to, double amount) {
        try {
            String urlStr = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to + "&amount=" + amount;
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

            String json = response.toString();
            double result = 0.0;

            if (json.contains("\"result\":")) {
                int start = json.indexOf("\"result\":") + 9;
                int end = json.indexOf("}", start);
                String resultStr = json.substring(start, end).replaceAll("[^0-9.]", "");
                if (!resultStr.isEmpty()) {
                    result = Double.parseDouble(resultStr);
                }
            }

            return result;
        } catch (Exception e) {
            return 0.0;
        }
    }
}
