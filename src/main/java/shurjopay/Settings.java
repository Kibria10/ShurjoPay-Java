package shurjopay;

import org.json.JSONObject;

//here sandbox credentials are set for test purpose.
public class Settings {
    protected static String username = "sp_sandbox"; //<Live server credential will be provided by shurjoPay team>
    protected static String password = "pyyk97hu&6u6"; //<Live server credential will be provided by shurjoPay team>
    protected static String getApiUrl = "https://sandbox.shurjopayment.com/api/get_token"; //"https://engine.shurjopayment.com/api/get_token";
    protected static String checkoutUrl = "https://sandbox.shurjopayment.com/api/secret-pay"; //"https://engine.shurjopayment.com/api/secret-pay";
    protected static String verificationUrl = "https://www.sandbox.shurjopayment.com/api/verification"; //"https://www.engine.shurjopayment.com/api/verification";
    protected static String prefix = "sp"; //<Live server credential will be provided by shurjoPay team>
    protected static String merchantReturnUrl = "https://www.sandbox.shurjopayment.com/response"; //<YourDomainName.com/Custom_return_url>
    protected static String merchantCancelUrl = "https://www.sandbox.shurjopayment.com/response"; //<YourDomainName.com/Custom_cancel_url>

    public static void configure(JSONObject configCredentials){
        if(configCredentials.keys()==null) {
            Settings.username = configCredentials.getString("username");
            Settings.password = configCredentials.getString("password");
            Settings.getApiUrl = configCredentials.getString("getApiUrl");
            Settings.checkoutUrl = configCredentials.getString("checkoutUrl");
            Settings.verificationUrl = configCredentials.getString("verificationUrl");
            Settings.prefix = configCredentials.getString("prefix");
            Settings.merchantReturnUrl = configCredentials.getString("merchantReturnUrl");
            Settings.merchantCancelUrl = configCredentials.getString("merchantCancelUrl");
        }
        else System.out.println("Please call the configure() method with proper parameters. Executing with Sandbox credentials.");
    }
}
//change the credentials to live credentials after getting on-board with shurjoPay!
