package shurjopay;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ShurjoPay {

    public static String getToken() throws IOException{
        JSONObject credential = new JSONObject();
        credential.put("username", Settings.username );
        credential.put("password", Settings.password);
        String credentialToString = credential.toString();

        URL obj = new URL(Settings.getApiUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json;");
        connection.setDoOutput(true);
        connection.setConnectTimeout(30000);
        //send request
        OutputStream os = connection.getOutputStream();
        os.write(credentialToString.getBytes());
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code of Get Token API: " + responseCode);

        //get response
        String responseDataOfGetToken = "";
        if (responseCode==200) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responseDataOfGetToken = response.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Something went wrong!");
        }
        JSONObject JSONResponseData = new JSONObject(responseDataOfGetToken);
        String token =JSONResponseData.getString("token");
        return token;
    }


    public static JSONObject executeCheckout(JSONObject checkoutInfo) throws IOException{
        int flag = 0;
        String order_id = (String) checkoutInfo.get("order_id");
        if (order_id.equals(null) || order_id.equals("")){
            flag =1;
            System.out.println("Please Provide Order ID");
        }

        String amount = (String) checkoutInfo.get("amount");
        int amountInt = Integer.parseInt(amount); //need to implement a try catch on this later
        if (amountInt < 0 || amount.equals(null) || amount.equals("")) {
            flag = 2;
            System.out.println("Please Provide Valid Amount");
        }

        String return_url = (String) checkoutInfo.get("return_url");
        if(return_url.equals(null) || return_url.equals("")){
            flag =3;
            System.out.println("Please Provide Valid Return URL");
        }

        String cancel_url = (String) checkoutInfo.get("return_url");
        if(cancel_url.equals(null) || cancel_url.equals("")){
            flag =4;
            System.out.println("Please Provide Valid Cancel URL");
        }

        ///NOW FOR THE ACTUAL PART///
        String responseDataOfCheckOut = "";
        if (flag == 0){
            URL obj = new URL(Settings.checkoutUrl);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;");
            connection.setDoOutput(true);
            connection.setConnectTimeout(30000);
            //send request
            OutputStream os = connection.getOutputStream();
            String checkoutInfoString = checkoutInfo.toString();
            os.write(checkoutInfoString.getBytes());
            os.flush();
            os.close();
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code of Checkout API : " + responseCode);

            //get response
            if (responseCode==200) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    responseDataOfCheckOut = response.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Something went wrong!");
            }
        }
        JSONObject JSONResponseData = new JSONObject(responseDataOfCheckOut);
        String executeCheckoutUrl = JSONResponseData.get("checkout_url").toString();
        if (Settings.checkoutUrl!=null){
            try {
                URI uri = new URI(executeCheckoutUrl);
                java.awt.Desktop.getDesktop().browse(uri);
                System.out.println("Payment Portal Opened in The Browser.");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return JSONResponseData;
    }

    public static JSONObject verifyOrder(String orderId) throws IOException{
        JSONObject jsonOrderId = new JSONObject(); //creating json object of order id
        jsonOrderId.put("order_id", orderId);
        String jsonOrderIdToString = jsonOrderId.toString(); // turning the json object into string to pass it on the os.write()
        URL obj = new URL(Settings.verificationUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(30000);
        connection.setDoOutput(true);
        //send request
        OutputStream os = connection.getOutputStream();
        os.write(jsonOrderIdToString.getBytes());
        os.flush();
        os.close();
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code of Verifying Order API: " + responseCode);

        //get response
        String responseDataOfVerification = "";
        if (responseCode==200) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responseDataOfVerification = response.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else { System.out.println("Something went wrong!");
        }
        //next 3 lines for removing the first "[" and last "]" characters from responseDataOfVerification.
        //so that the string can be put in as a JSONObject() and then returned.
        StringBuffer responseRefining = new StringBuffer(responseDataOfVerification);
        responseRefining.delete(responseDataOfVerification.length() - 1, responseDataOfVerification.length());
        responseRefining.delete(0, 1);

        JSONObject JsonResponseData = new JSONObject(responseRefining.toString());
        return JsonResponseData;
    }
}
