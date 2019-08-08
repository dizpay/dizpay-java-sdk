package com.dizpay.sample.sample;

import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.impl.DefaultDizpayClient;
import com.dizpay.api.request.payout.PayOrderRequest;
import com.dizpay.api.response.payout.PayOrderResponse;
import com.dizpay.sample.config.DizPayConfig;

/**
 * Pay payout order sample
 */
public class PayOrderSample {
    public static void main(String[] args) {
        DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_KEY);

        String number = "payout_347dfad9-5873-4b43-be4e-24011";
        PayOrderRequest payOrderRequest = new PayOrderRequest();
        payOrderRequest.setNumber(number);

        try {
            RestResult<PayOrderResponse> payOrderResponseRestResult = dizpayClient.payOrder(payOrderRequest);
            if (payOrderResponseRestResult != null && payOrderResponseRestResult.getData() != null) {
                PayOrderResponse payOrderResponse = payOrderResponseRestResult.getData();
                System.out.println(payOrderResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
