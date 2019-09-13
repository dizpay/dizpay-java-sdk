
# DizPay JAVA SDK

This open source JAVA SDK allows you to access the [DizPay](https://www.dizpay.com/) API from your JAVA app.

## Introduction

DizPay is a blockchain payment processor with a simple and powerful RESTful API for integrating cryptocurrency payments into your website. Currently, DizPay supports payments of Bitcoin \(BTC\), Ethereum \(ETH\), Litecoin \(LTC\), DASH, Tether \(USDT\), Gemini Dollar \(GUSD\), Paxos \(PAX\), TrueUSD \(TUSD\), and USD Coin \(USDC\).

This API reference details the endpoints and information you need to use the DizPay API in your JAVA applications.

Before you start using the API, you must obtain an App ID and App key for your applications from the DizPay server. You can get this information by signing up with DizPay our website.

This SDK contains the following API:

- Checkout API
- Orders API
- Rates API

## Prerequisites

- JDK 1.8 or above
- tomcat 8.0 or above

## Usage

The DizPay API requires the following configuration changes before you can use it. These changes will insert your app ID and key into your application as well as the perquisite namespaces. Use this [sample](https://github.com/dizpay/dizpay-java-sdk/blob/master/sample) to quickly get started if needed.

Set the App ID and key parameters in `com.dizpay.demo.config.DizPayConfig.java` before your first API call.  Just make sure you you do not accidently reveal this information in any user vieable request or response. :

```java
    public static final String APP_ID = "YOUR_APP_ID";
    public static final String APP_key = "YOUR_APP_KEY";
```

You should then add the following package to your application. It includes most of the API calls in the SDK.

```java
    package com.dizpay.demo.servlet;
    import com.alibaba.fastjson.JSONObject;
    import com.dizpay.api.DizpayClient;
    import com.dizpay.api.common.RestResult;
    import com.dizpay.api.impl.DefaultDizpayClient;
    import com.dizpay.api.request.checkout.CheckOutInvoiceRequest;
    import com.dizpay.api.response.checkout.CheckOutInvoiceResponse;
    import com.dizpay.api.response.checkout.CheckOutInvoiceResponse;
    import com.dizpay.api.response.checkout.CheckOutInvoiceResponse;
    import com.dizpay.demo.config.DizPayConfig;
    import org.apache.commons.lang3.StringUtils;
    import org.apache.commons.lang3.time.DateFormatUtils;
    import org.apache.commons.lang3.time.DateUtils;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.Date;
    import java.util.UUID;
    // check out servlet     
  
    public class CheckOutServlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
      }
    
      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // accept amount
        String amount = request.getParameter("amount";
        if (StringUtils.isBlank(amount)) {
          amount ="0.1";
        }
        // assemble checkoutModel parameters
        
        CheckOutInvoiceRequest checkOutInvoiceRequest = new CheckOutInvoiceRequest();
        checkOutInvoiceRequest.setOrderNumber(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        checkOutInvoiceRequest.setName("DizPay Inc.");
        checkOutInvoiceRequest.setDescription("Add crypto to your account.");
        checkOutInvoiceRequest.setLogoUrl("https://cdn.shopifycloud.com/hatchful-web/assets/c3a241ae6d1e03513dfed6f5061f4a4b.png");
        checkOutInvoiceRequest.setPayerInfo("email");
        checkOutInvoiceRequest.setPricingType("fixed_price");
        checkOutInvoiceRequest.setCurrency("USD");
        checkOutInvoiceRequest.setRate("huobi");
        checkOutInvoiceRequest.setAmount(amount);
        checkOutInvoiceRequest.setCrypto("USDC,TUSD,PAX,GUSD,USDT,ETH,BTC,LTC,DASH");
        checkOutInvoiceRequest.setLocale("auto");
        checkOutInvoiceRequest.setSuccessUrl("https://example.com/diz-pay-result?type=success");
        checkOutInvoiceRequest.setCancelUrl("https://example.com/diz-pay-result?type=failed");
        checkOutInvoiceRequest.setNotifyUrl("https://demo.dizpay.com/webhook")
        checkOutInvoiceRequest.setExtra("another-params");

      // DizpayClient instance

      DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_key);

      try {
        RestResult<CheckOutInvoiceResponse> responseRestResult = dizpayClient.checkoutInvoice(checkOutInvoiceRequest);
        if (responseRestResult !=null && responseRestResult.getData() !=null) {
          CheckOutInvoiceResponse checkOutInvoiceResponse = responseRestResult.getData();
          response.sendRedirect(checkOutInvoiceResponse.getPaymentUrl());
        } else {
          response.getWriter().write(JSONObject.toJSONString(responseRestResult));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
```

This sample package has most of the API calls you will need to use DizPay. To use them, just include them in your code like in the following example.

### Example (_Create change order_)

```java
    public static void main(String[] args) {
      DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_key);
      
      CreateChargeOrderRequest createChargeOrderRequest = new CreateChargeOrderRequest();
      createChargeOrderRequest.setNumber(UUID.randomUUID().toString());
      createChargeOrderRequest.setAmount("0.1");
      createChargeOrderRequest.setCurrencyCode("USDT");
      
      try {
        RestResult<CreateChargeOrderResponse> chargeOrderResponseRestResult = dizpayClient.createChargeOrder(createChargeOrderRequest)   
        if (chargeOrderResponseRestResult !=null && chargeOrderResponseRestResult.getData() !=null) {
          CreateChargeOrderResponse createChargeOrderResponse = chargeOrderResponseRestResult.getData();
          System.out.println(createChargeOrderResponse);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
```

### Rate limiting

To prevent continuous API requests, DizPay limits how many API requests you can send per minute to our servers. You will find your limit in your application details on our website. If you exceed this number of requests, our server will return a 1005 error code. We do this to maintain a consistent service to all of our users.

## Responses

The DizPay API returns one of the following status codes with every response to your requests.

### Status Codes

| Status Code | Status | Description |
| --- | --- | --- |
| 200 | OK | Request succeeded |
| 400 | Bad Request | Request failed. Check the Error code in the response for further details |
| 401 | Unauthorized | The app\_id is invalid or blocked |
| 403 | Forbidden | Signing a failure |
| 500 | Internal Server Error | An internal error occurred in the server |

### Errors

The API will also return one of the following error codes, along with an error `message`, if it returns a &quot;400&quot; status code.

| **Error Code** | **Description** |
| --- | --- |
| 1000 | Field Syntax Error _(e.g. Required, Range, Type)_ |
| 1001 | Entity does not exist |
| 1002 | Entity does not match |
| 1003 | Invalid value |
| 1004 | SMS failed |
| 1005 | Requests sent too frequently |
| 1006 | Entity has expired |
| 1008 | Not enough balance |
| 1010 | Email failed |
| 1051 | Error with the wallet server |



## SDK Documents

To make API calls, you must first create an API instance, and to do that, you must run an SDK instance.

### SDK import (_by maven or jar_)

The following code will load the necessary SDK dependencies into your application.

```java
    <dependency>
      <groupId>com.dizpay.api</groupId>
      <artifactId>sdk</artifactId>
      <version>1.0.0</version>
    </dependency>
```

```java
    sdk.jar
```

### Starting the DisPay Client

`DizpayClient` serves are your interface to the DizPay API. Create it by passing your app ID and key to the `DefaultDizpayClient` constructor as arguments.

```java
    DizpayClient dizpayClient = new DefaultDizpayClient(APP_ID, APP_key>);
```

### Signature

A **signature** lets you perform non-empty judgements, sort through fields, and MD% encrypt request parameters.

```java
    String signature = DizpaySignature.signature(requestModel, APP_ID, APP_key);
```

#### Tips

- Use underlined and non-hump fields as request parameters
- Only use signed fields in the signature
- Filter out any fields with null values

### Notification on Payment Completion

If set, DizPay sends the results of each transaction to the address set by the `notify_url` parameter. It sends this notification if the transaction either goes through or after each reattempt. The payment gateway will reattempt a failed transaction every 10 minutes for up to 24 hours.

## Payment/Charge Orders

A **payment (charge) order** handles outside payment requests. It adds the received amount to your merchant address. If the transaction is more than or equal to your pre-set target, the server will send a notification to your configured `notify_url` address.

### Create a Payment Order

```java
    CreateChargeOrderRequest createChargeOrderRequest = new CreateChargeOrderRequest();
    createChargeOrderRequest.setNumber(UUID.randomUUID().toString()); //order number
    createChargeOrderRequest.setAmount("0.1"); // Sets amount
    createChargeOrderRequest.setCurrencyCode("USDT"); //Sets currency code
    
    RestResult<CreateChargeOrderResponse> chargeOrderResponseRestResult = dizpayClient.createChargeOrder(createChargeOrderRequest);
```

#### Parameters

You can set the following order parameters. You can use them through their set methods as seen above.

| Parameter | Required | Description | Remarks |
| --- | --- | --- | --- |
| app\_id | No | Your app\_id |   |
| number | Yes | Order number |   |
| currency\_code | Yes | Digital currency name | Example: USDT |
| amount | Yes | Amount of currency |   |
| signature | Yes | signature |   |
| erc20\_token | No | 0 for Non-token currency<br />1 for Token currency | Default: 0 |
| extra | No | Extra information |   |
| notify\_url | No | Callback URL | Must contain the protocol (http) as well. |

#### Responses

Creating a charge order will return the following data

| Field | Description |
| --- | --- |
| number | Order number |
| currency\_code | Code of the used currency |
| erc20\_token | Either 0 or 1 |
| amount | Amount paid |
| paid\_amount | Amount actually received |
| extra | Extra information |
| fee | Fee set by the merchant. This field is currently unused. |
| to\_address | Address of the target recipient |
| txid | The txid of the transaction |
| status | Order status:  <br />1 for In progress  <br />2 for Completed  <br />4 for Cancelled |

### Querying an Order

Return information on an order based on its order number

```java
    String number = "6887623c-e756-4a20-b202-609b5a5b4a5e";  // order number
    QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
    queryOrderRequest.setNumber(number);

    RestResult<QueryOrderResponse> queryOrderResponseRestResult = dizpayClient.queryOrder(queryOrderRequest);
```

#### Parameters

| Parameter | Required | Description |
| --- | --- | --- |
| app\_id | No | Your app\_id |
| number | Yes | The order number to query |
| signature | No | Signature |

#### Response

| Field | Description |
| --- | --- |
| number | Order number |
| currency\_code | Code of the used currency |
| erc20\_token | 0 for Non-token currency  <br />1 for Token |
| amount | Amount paid |
| paid\_amount | Amount actually received |
| extra | extra information |
| fee | Fee set by the merchant. This field is currently unused. |
| to\_address | Address of the target recipient |
| address | Address of the money source |
| txid | The txid for the transaction |
| status | Order status:  <br />1 for In progress  <br />2 for Completed  <br />4 for Cancelled |

### Cancel Order

Cancels the selected order.

```java
    String number = "6887623c-e756-4a20-b202-609b5a5b4a5e";
    CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
    cancelOrderRequest.setNumber(number);

    RestResult<CancelOrderResponse> cancelOrderResponseRestResult = dizpayClient.cancelOrder(cancelOrderRequest);
```

#### Parameters

| Parameter | Required | Description |
| --- | --- | --- |
| app\_id | No | Your app\_id |
| number | No | The order number to cancel |
| signature | Yes | Signature |

#### Response Fields

| Field | Description |
| --- | --- |
| number | Order number |
| currency\_code | Code of the used currency |
| erc20\_token | 0 for Non-token currency  <br />1 for Token |
| amount | Amount paid |
| paid\_amount | Amount actually received |
| extra | Extra information |
| fee | Fee set by the merchant. This field is currently unused. |
| to\_address | Address of the target recipient |
| address | Address of the money source |
| txid | The txid for the transaction |
| status | Order status:  <br />1 for In progress  <br />2 for Completed  <br />4 for Cancelled |

## Payout Order

A **payout order** processes payouts and withdrawal transactions. Also called a **substitute order**, the process deducts the corresponding digital currency from your merchant&#39;s bulk shipping wallet.

### Creating a Payout Order

Before you create a payout order, make sure you have enough balance in your wallet to cover the transaction.

```java
    CreatePayoutOrderRequest createPayoutOrderRequest = new CreatePayoutOrderRequest();
    createPayoutOrderRequest.setNumber("payout_"+UUID.randomUUID().toString());
    createPayoutOrderRequest.setAmount("0.1";
    createPayoutOrderRequest.setCurrencyCode("USDT");
    createPayoutOrderRequest.setToAddress("1u1dAwvBcF92sAwBPgNDK3ysbWm3aTE8U");

    RestResult<CreatePayoutOrderResponse> payoutOrderResponseRestResult = dizpayClient.createPayoutOrder(createPayoutOrderRequest);
```

#### Parameters

| Parameter | Required | Description | Remarks |
| --- | --- | --- | --- |
| app\_id | Yes | Your app\_id |   |
| number | Yes | Order number |   |
| currency\_code | Yes | Digital currency name | Example: USDT |
| amount | Yes | Amount of currency |   |
| signature | Yes | signature |   |
| erc20\_token | No | 0 for Non-token currency<br />1 for Token currency | Default: 0 |
| extra | No | Extra information |   |
| to\_address | No | The address to withdraw the funds in |   |

#### Response

| Field | Description |
| --- | --- |
| number | Order number |
| currency\_code | Code of the used currency |
| erc20\_token | Either 0 or 1 |
| amount | Amount paid |
| paid\_amount | Amount actually received |
| extra | Extra information |
| fee | Fee set by the merchant. This field is currently unused. |
| to\_address | Address of the target recipient |
| address | Address of the money source |
| txid | The txid of the transaction |
| status | Order status:  <br />1 for In progress  <br />2 for Completed  <br />4 for Cancelled |

### Paying a Payout Order

Use this procedure to pay a payout

```java
    String number = "payout\_347dfad9-5873-4b43-be4e-24011";  
    PayOrderRequest payOrderRequest = new PayOrderRequest();
    payOrderRequest.setNumber(number);

    RestResult<PayOrderResponse> payOrderResponseRestResult = dizpayClient.payOrder(payOrderRequest);
```

#### Parameters

| Parameter | Required | Description |
| --- | --- | --- |
| app\_id | Yes | Your app\_id |
| number | Yes | The order number to query |
| signature | Yes | Signature |

#### Response

| Field | Description |
| --- | --- |
| number | Order number |
| currency\_code | Code of the used currency |
| erc20\_token | 0 for Non-token currency  <br />1 for Token |
| amount | Amount paid |
| paid\_amount | Amount actually received |
| extra | extra information |
| fee | Fee set by the merchant. This field is currently unused. |
| to\_address | Address of the target recipient |
| address | Address of the money source |
| txid | The txid for the transaction |
| status | Order status:  <br />1 for In progress  <br />2 for Completed  <br />4 for Cancelled |

### Cryptocurrency

This command retrieves the current transfer rate between two currencies.

```java
    CryptocurrencyRequest cryptocurrencyRequest = new CryptocurrencyRequest();     
    cryptocurrencyRequest.setCurrencyList("BTC,USDT");
    
    RestResult restResult = dizpayClient.cryptocurrency(cryptocurrencyRequest);
```

#### Parameters

| Parameter | Required | Description |
| --- | --- | --- |
| currency\_list | No | Comma-separated currency codes. Returns all the rates if this parameter is empty. |

#### Return Fields

| Field | Description |
| --- | --- |
| usd\_price | Current USD if querying cryptocurrencies |
| usd\_rate | Current USD if querying fiat currencies |
| currency | Name of currency |
| currency\_code | Currency code |
| erc20\_token | No |
| percent\_change\_24h | Cryptocurrency Increase percent in the last 24 hours. This can be negative. |
| country | Name of country, if fiat currency query |
| country\_code | Country code |


## More Help

[API Reference](https://www.dizpay.com/en/docs)

