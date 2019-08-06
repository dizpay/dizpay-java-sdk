package com.dizpay.demo.sample;

import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.impl.DefaultDizpayClient;
import com.dizpay.api.request.CreateChargeOrderRequest;
import com.dizpay.api.request.payout.CreatePayoutOrderRequest;
import com.dizpay.api.response.CreateChargeOrderResponse;
import com.dizpay.api.response.payout.CreatePayoutOrderResponse;
import com.dizpay.demo.config.DizPayConfig;

import java.util.UUID;

/**
 * Create charge order sample
 */
public class CreatePayoutOrderSample {
    public static void main(String[] args) {
        DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_KEY);

        CreatePayoutOrderRequest createPayoutOrderRequest = new CreatePayoutOrderRequest();
        createPayoutOrderRequest.setNumber("payout_"+UUID.randomUUID().toString());
        createPayoutOrderRequest.setAmount("0.1");
        createPayoutOrderRequest.setCurrencyCode("USDT");
        createPayoutOrderRequest.setToAddress("1u1dAwvBcF92sAwBPgNDK3ysbWm3aTE8U");

        try {
            RestResult<CreatePayoutOrderResponse> payoutOrderResponseRestResult = dizpayClient.createPayoutOrder(createPayoutOrderRequest);
            if (payoutOrderResponseRestResult != null && payoutOrderResponseRestResult.getData() != null) {
                CreatePayoutOrderResponse createPayoutOrderResponse = payoutOrderResponseRestResult.getData();
                System.out.println(createPayoutOrderResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
