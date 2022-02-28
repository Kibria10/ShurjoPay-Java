![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# ShurjoPay-Java Integration Steps:

## Prerequisite
To integrate ShurjoPay you need few credentials to access shurjopay:
```
:param prefix: Any string not more than 5 characters. It distinguishes the stores of a merchant.
:param currency: ISO format,(only BDT and USD are allowed).
:param return_url: Merchant should provide a GET Method return url to verify users initiated transaction status. 
:param cancel_url: Merchant should provide a cancel url to redirect the user if he/she cancels the transaction in midway. 
:param client_ip: User's ip
:param username: Merchant's Username provided by shurjopay.
:param password: Merchant's Password provided by shurjopay.
:param post_address: Live shurjopay version 2 URL.
```


> 📝 **NOTE** For ShurjoPay version 2 live engine integration all necessary credential will be given to merchant after subscription completed on shurjoPay gateway.

---
```
Step 1:
Install the "ShurjoPay Plugin.jar" in your project library.

Step 2:
import shurjopay.Settings;
import shurjopay.ShurjoPay;

Step 3:



```


### Postman Documentations

    This document will illustrate the overall request and response flow.
    URL : https://documenter.getpostman.com/view/6335853/U16dS8ig	
