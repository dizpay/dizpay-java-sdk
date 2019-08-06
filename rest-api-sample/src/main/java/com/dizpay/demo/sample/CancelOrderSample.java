package com.dizpay.demo.sample;

import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.impl.DefaultDizpayClient;
import com.dizpay.api.request.CancelOrderRequest;
import com.dizpay.api.request.QueryOrderRequest;
import com.dizpay.api.response.CancelOrderResponse;
import com.dizpay.api.response.QueryOrderResponse;
import com.dizpay.demo.config.DizPayConfig;

/**
 * Cancel order sample
 */
public class CancelOrderSample {
    public static void main(String[] args) {
        DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_KEY);

        String number = "6887623c-e756-4a20-b202-609b5a5b4a5e";
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        cancelOrderRequest.setNumber(number);

        try {
            RestResult<CancelOrderResponse> cancelOrderResponseRestResult = dizpayClient.cancelOrder(cancelOrderRequest);
            if (cancelOrderResponseRestResult != null && cancelOrderResponseRestResult.getData() != null) {
                CancelOrderResponse cancelOrderResponse = cancelOrderResponseRestResult.getData();
                System.out.println(cancelOrderResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
