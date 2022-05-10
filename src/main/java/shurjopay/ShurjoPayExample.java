package shurjopay;
import org.json.JSONObject;
import java.io.IOException;

public class ShurjoPayExample{
    public static void main(String[]args) throws IOException {
//use-case:1
        JSONObject configCredentials = new JSONObject();
        configCredentials.put("username", "");
        configCredentials.put("password", "");
        configCredentials.put("getApiUrl", "");
        configCredentials.put("checkoutUrl", "");
        configCredentials.put("verificationUrl", "");
        configCredentials.put("prefix", "");
        configCredentials.put("merchantReturnUrl", "");
        configCredentials.put("merchantCancelUrl", "");

        //Settings.configure(configCredentials); //<--- EXECUTING (Only execute this after getting your live API credentials from ShurjoPay. By default, sandbox credentials will execute.)
//use-case:2
        JSONObject checkoutInfo = new JSONObject(); //make a json object and put the necessary checkout data parameters inside it.
        JSONObject getTokenAndStoreId = ShurjoPay.getToken(); //<---EXECUTING
        checkoutInfo.put("prefix", Settings.prefix );
        checkoutInfo.put("token",getTokenAndStoreId.getString("token")); //needed from get token api
        checkoutInfo.put("amount", "420" );
        checkoutInfo.put("order_id", "sp315689");
        checkoutInfo.put("store_id", getTokenAndStoreId.getInt("store_id")); //needed from get token api
        checkoutInfo.put("currency", "BDT");
        checkoutInfo.put("return_url", Settings.merchantReturnUrl);
        checkoutInfo.put("cancel_url", Settings.merchantCancelUrl);
        checkoutInfo.put("client_ip", "102.101.1.1");
        checkoutInfo.put("customer_name", "Maharab Kibria");
        checkoutInfo.put("customer_phone", "01777777777");
        checkoutInfo.put("customer_email", "junakkibria56@gmail.com");
        checkoutInfo.put("customer_address", "221 Baker Street");
        checkoutInfo.put("customer_city", "Dhaka");
        checkoutInfo.put("customer_state", "Dhaka");
        checkoutInfo.put("customer_postcode", "1217");
        checkoutInfo.put("customer_country", "Bangladesh");
        //System.out.println(checkoutInfo);
        JSONObject checkoutResponse = ShurjoPay.executeCheckout(checkoutInfo);//<--- EXECUTING
        //System.out.println(checkoutResponse);

//use-case:3
        String orderId = checkoutResponse.getString("sp_order_id");
        //merchant needs to pass the orderId manually after payment.
        JSONObject getVerificationResponse = ShurjoPay.verifyOrder(orderId); //<--- EXECUTING
        //System.out.println(getVerificationResponse);
    }
}
