package com.dizpay.sample.sample;

import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.impl.DefaultDizpayClient;
import com.dizpay.api.request.rates.CryptocurrencyRequest;
import com.dizpay.sample.config.DizPayConfig;

/**
 * Cryptocurrency sample
 */
public class CryptocurrencySample {
    public static void main(String[] args) {
        DizpayClient dizpayClient = new DefaultDizpayClient(DizPayConfig.APP_ID, DizPayConfig.APP_KEY);

        CryptocurrencyRequest cryptocurrencyRequest = new CryptocurrencyRequest();
        cryptocurrencyRequest.setCurrencyList("BTC,USDT");

        try {
            RestResult restResult = dizpayClient.cryptocurrency(cryptocurrencyRequest);
            if (restResult != null && restResult.getData() != null) {
                Object restResultData = restResult.getData();
                System.out.println(restResultData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
