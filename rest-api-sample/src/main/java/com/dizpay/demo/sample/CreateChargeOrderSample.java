package com.dizpay.demo.sample;

import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.impl.DefaultDizpayClient;
import com.dizpay.api.request.CreateChargeOrderRequest;
import com.dizpay.api.response.CreateChargeOrderResponse;
import com.dizpay.demo.config.DizPayConfig;

import java.util.UUID;

/**
 * Create charge order sample
 */
public class CreateChargeOrderSample {
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
}
