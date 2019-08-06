
# DizPay JAVA SDK


This repo contains the open source JAVA SDK that allows you to access [DizPay](https://www.dizpay.com/) API from your JAVA app.

Here are few type api in the SDK:

+ Checkout API
+ Orders API
+ Rates API


# Prerequisites

+ JDK 1.8 or above
+ tomcat 8.0 or above


# Usage

The [sample](./rest-api-sample) are good place to start.

Modify the configuration information of app_id and app_key in com.dizpay.demo.config.DizPayConfig.java
```java
    public static final String APP_ID = "YOUR_APP_ID";
    public static final String APP_KEY = "YOUR_APP_KEY";
    public static final String CHECK_OUT_URL = "https://checkout.dizpay.com/api/invoice";
```

To make [API](https://www.dizpay.com/en/docs) calls:

```java

    package com.dizpay.demo.servlet;

    import com.alibaba.fastjson.JSONObject;
    import com.dizpay.api.common.StatusCodeEnum;
    import com.dizpay.api.util.DizpaySignature;
    import com.dizpay.api.util.HttpClientUtil;
    import com.dizpay.demo.config.DizPayConfig;
    import com.dizpay.demo.model.CheckOutRequest;
    import com.dizpay.demo.model.CheckOutResponse;
    import okhttp3.Response;
    import org.apache.commons.lang3.StringUtils;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.UUID;

    /**
     * check out servlet
     */
    public class CheckOutServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // accept amount
            String amount = request.getParameter("amount");
            if (StringUtils.isBlank(amount)) {
                amount = "0.1";
            }
            // assemble checkoutModel parameters
            CheckOutRequest checkOutModel = new CheckOutRequest();
            checkOutModel.setOrderNumber("CHECKOUT_" + UUID.randomUUID());
            checkOutModel.setName("DizPay Inc.");
            checkOutModel.setDescription("Add crypto to your account.");
            checkOutModel.setLogoUrl("https://cdn.shopifycloud.com/hatchful-web/assets/c3a241ae6d1e03513dfed6f5061f4a4b.png");
            checkOutModel.setPayerInfo("email");
            checkOutModel.setPricingType("fixed_price");
            checkOutModel.setCurrency("USD");
            checkOutModel.setAmount(amount);
            checkOutModel.setCrypto("USDC,TUSD,PAX,GUSD,USDT,ETH,BTC,LTC,DASH");
            checkOutModel.setLocale("auto");
            checkOutModel.setTheme("dark");
            checkOutModel.setRedirectUrl("https://example.com/diz-pay-result?type=success");
            checkOutModel.setCancelUrl("https://example.com/diz-pay-result?type=failed");
            checkOutModel.setNotifyUrl("https://demo.dizpay.com/webhook");
            checkOutModel.setExtra("another-params");

            // signature
            String signature = DizpaySignature.signature(checkOutModel, DizPayConfig.APP_ID, DizPayConfig.APP_KEY);
            checkOutModel.setSignature(signature);
            checkOutModel.setAppId(DizPayConfig.APP_ID);

            // call checkout api
            Response checkOutRsp = HttpClientUtil.httpPost(DizPayConfig.CHECK_OUT_URL, JSONObject.toJSONString(checkOutModel));
            if (checkOutRsp != null && StatusCodeEnum.OK.getCode() == checkOutRsp.code()) {
                CheckOutResponse checkOutResponse = JSONObject.parseObject(checkOutRsp.body().string(), CheckOutResponse.class);
                response.sendRedirect(checkOutResponse.getPaymentUrl());
            }
        }
    }


```

the package 'com.dizpay.demo.sample' contains most of the api calls,
you will learn how to call api via sdk.

eg(create charge order example):

```java
    public static void main(String[] args) {
        DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_KEY);

        CreateChargeOrderRequest createChargeOrderRequest = new CreateChargeOrderRequest();
        createChargeOrderRequest.setNumber(UUID.randomUUID().toString());
        createChargeOrderRequest.setAmount("0.1");
        createChargeOrderRequest.setCurrencyCode("USDT");
        try {
            RestResult<CreateChargeOrderResponse> chargeOrderResponseRestResult = dizpayClient.createChargeOrder(createChargeOrderRequest);
            if (chargeOrderResponseRestResult != null && chargeOrderResponseRestResult.getData() != null) {
                CreateChargeOrderResponse createChargeOrderResponse = chargeOrderResponseRestResult.getData();
                System.out.println(createChargeOrderResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

# SDK Documents

You can create an api instance in the following way

### SDK import(by maven or jar)
```java
    <dependency>
        <groupId>com.dizpay.api</groupId>
        <artifactId>rest-api-sdk</artifactId>
        <version>1.0.0</version>
    </dependency>
```

```java
    rest-api-sdk.jar
```


### Start by DizpayClient
```java
    DizpayClient dizpayClient = new DefaultDizpayClient("YOUR_APP_ID", "YOUR_APP_KEY");
```
### signature
```java
    String signature = DizpaySignature.signature(requestModel, "YOUR_APP_ID", "YOUR_APP_KEY");
```

The signature method has performed non-empty judgment, field sorting, and md5 encryption on the request parameters.

`tip:`

        1.Request parameters are underlined fields, non-hump field requests.

        2.The fields participating in the signature are underlined fields, and the non-hump fields are signed.

        3.Fields with null values do not participate in the signature and need to be filtered.

<br/>

### create charge order
```java
    CreateChargeOrderRequest createChargeOrderRequest = new CreateChargeOrderRequest();
    createChargeOrderRequest.setNumber(UUID.randomUUID().toString());
    createChargeOrderRequest.setAmount("0.1");
    createChargeOrderRequest.setCurrencyCode("USDT");

    RestResult<CreateChargeOrderResponse> chargeOrderResponseRestResult = dizpayClient.createChargeOrder(createChargeOrderRequest);

```

### query order
```java
    String number = "6887623c-e756-4a20-b202-609b5a5b4a5e";
    QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
    queryOrderRequest.setNumber(number);

    RestResult<QueryOrderResponse> queryOrderResponseRestResult = dizpayClient.queryOrder(queryOrderRequest);

```

### cancel order
```java
    String number = "6887623c-e756-4a20-b202-609b5a5b4a5e";
    CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
    cancelOrderRequest.setNumber(number);

    RestResult<CancelOrderResponse> cancelOrderResponseRestResult = dizpayClient.cancelOrder(cancelOrderRequest);
```

### create payout order
```java
    CreatePayoutOrderRequest createPayoutOrderRequest = new CreatePayoutOrderRequest();
    createPayoutOrderRequest.setNumber("payout_"+UUID.randomUUID().toString());
    createPayoutOrderRequest.setAmount("0.1");
    createPayoutOrderRequest.setCurrencyCode("USDT");
    createPayoutOrderRequest.setToAddress("1u1dAwvBcF92sAwBPgNDK3ysbWm3aTE8U");

    RestResult<CreatePayoutOrderResponse> payoutOrderResponseRestResult = dizpayClient.createPayoutOrder(createPayoutOrderRequest);
```

### pay payout order
```java
    String number = "payout_347dfad9-5873-4b43-be4e-24011";
    PayOrderRequest payOrderRequest = new PayOrderRequest();
    payOrderRequest.setNumber(number);

    RestResult<PayOrderResponse> payOrderResponseRestResult = dizpayClient.payOrder(payOrderRequest);
```

### cryptocurrency
```java
    CryptocurrencyRequest cryptocurrencyRequest = new CryptocurrencyRequest();
    cryptocurrencyRequest.setCurrencyList("BTC,USDT");

    RestResult restResult = dizpayClient.cryptocurrency(cryptocurrencyRequest);
```


# More Help

+ [API Reference](https://www.dizpay.com/en/docs)
