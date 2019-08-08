package com.dizpay.sample.sample;

import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.impl.DefaultDizpayClient;
import com.dizpay.api.request.QueryOrderRequest;
import com.dizpay.api.response.QueryOrderResponse;
import com.dizpay.sample.config.DizPayConfig;

/**
 * Query order sample
 */
public class QueryOrderSample {
    public static void main(String[] args) {
        DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_KEY);

        String number = "6887623c-e756-4a20-b202-609b5a5b4a5e";
        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setNumber(number);

        try {
            RestResult<QueryOrderResponse> queryOrderResponseRestResult = dizpayClient.queryOrder(queryOrderRequest);
            if (queryOrderResponseRestResult != null && queryOrderResponseRestResult.getData() != null) {
                QueryOrderResponse queryOrderResponse = queryOrderResponseRestResult.getData();
                System.out.println(queryOrderResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
