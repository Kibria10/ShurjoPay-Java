package shurjopay;
//here sandbox credentials are set for test purpose.
class Settings {
    protected static final String username = "sp_sandbox"; //<Live server credential will be provided by shurjoPay team>
    protected static final String password = "pyyk97hu&6u6"; //<Live server credential will be provided by shurjoPay team>
    protected static final String getApiUrl = "https://sandbox.shurjopayment.com/api/get_token"; //"https://engine.shurjopayment.com/api/get_token";
    protected static final String checkoutUrl = "https://sandbox.shurjopayment.com/api/secret-pay"; //"https://engine.shurjopayment.com/api/secret-pay";
    protected static final String verificationUrl = "https://www.sandbox.shurjopayment.com/api/verification"; //"https://www.engine.shurjopayment.com/api/verification";
    protected static final String prefix = "sp"; //<Live server credential will be provided by shurjoPay team>
    protected static final String merchantReturnUrl = "https://www.sandbox.shurjopayment.com/response"; //<YourDomainName/Custom_return_url>
    protected static final String merchantCancelUrl = "https://www.sandbox.shurjopayment.com/response"; //<YourDomainName/Custom_cancel_url>
}
//change the credentials to live credentials after getting on-board with shurjoPay!
