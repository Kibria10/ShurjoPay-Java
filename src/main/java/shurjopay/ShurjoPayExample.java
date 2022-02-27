package shurjopay;
import org.json.JSONObject;
import java.io.IOException;

public class ShurjoPayExample{
    public static void main(String[]args) throws IOException {
//use-case:1
        JSONObject checkout_info = new JSONObject(); //make a json object and put the necessary checkout data parameters inside it.
        checkout_info.put("prefix", Settings.prefix );
        checkout_info.put("token",ShurjoPay.getToken());
        checkout_info.put("amount", "23" );
        checkout_info.put("order_id", "sp315689");
        checkout_info.put("store_id", "1");
        checkout_info.put("currency", "BDT");
        checkout_info.put("return_url", Settings.merchantReturnUrl);
        checkout_info.put("cancel_url", Settings.merchantCancelUrl);
        checkout_info.put("client_ip", "102.101.1.1");
        checkout_info.put("customer_name", "Maharab Kibria");
        checkout_info.put("customer_phone", "01777777777");
        checkout_info.put("email", "junakkibria56@gmail.com");
        checkout_info.put("customer_address", "21 Baker Street");
        checkout_info.put("customer_city", "Dhaka");
        checkout_info.put("customer_state", "Dhaka");
        checkout_info.put("customer_postcode", "1217");
        checkout_info.put("customer_country", "Bangladesh");
        //System.out.println(checkout_info);
        JSONObject checkoutResponse = ShurjoPay.executeCheckout(checkout_info);//<--- executing
        //System.out.println(checkoutResponse);

//use-case:2
        String orderId = "sp62151bae66535"; //this is a static order_id for demo purpose to check if the verifyOrder() method is working or not.
        //merchant needs to pass the orderId manually after payment.
        JSONObject getVerificationResponse = ShurjoPay.verifyOrder(orderId); //<--- executing
        //System.out.println(getVerificationResponse);
    }
}
