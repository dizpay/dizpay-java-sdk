package com.dizpay.api;

import com.dizpay.api.common.RestResult;
import com.dizpay.api.request.CancelOrderRequest;
import com.dizpay.api.request.CreateChargeOrderRequest;
import com.dizpay.api.request.QueryOrderRequest;
import com.dizpay.api.request.payout.CreatePayoutOrderRequest;
import com.dizpay.api.request.payout.PayOrderRequest;
import com.dizpay.api.request.rates.CryptocurrencyRequest;
import com.dizpay.api.response.CancelOrderResponse;
import com.dizpay.api.response.CreateChargeOrderResponse;
import com.dizpay.api.response.QueryOrderResponse;
import com.dizpay.api.response.payout.CreatePayoutOrderResponse;
import com.dizpay.api.response.payout.PayOrderResponse;
import com.dizpay.api.response.rates.CryptocurrencyResponse;

/**
 * dizpay api
 */
public interface DizpayClient {

    /**
     * create charge order
     * @param createChargeOrderRequest
     * @return
     * @throws Exception
     */
    RestResult<CreateChargeOrderResponse> createChargeOrder(CreateChargeOrderRequest createChargeOrderRequest) throws Exception;

    /**
     * query order
     * @param queryOrderRequest
     * @return
     * @throws Exception
     */
    RestResult<QueryOrderResponse> queryOrder(QueryOrderRequest queryOrderRequest) throws Exception;

    /**
     * cancel order
     * @param cancelOrderRequest
     * @return
     * @throws Exception
     */
    RestResult<CancelOrderResponse> cancelOrder(CancelOrderRequest cancelOrderRequest) throws Exception;

    /**
     * create payout order
     * @return
     * @throws Exception
     */
    RestResult<CreatePayoutOrderResponse> createPayoutOrder(CreatePayoutOrderRequest createPayoutOrderRequest) throws Exception;

    /**
     * pay payout order
     * @param payOrderRequest
     * @return
     * @throws Exception
     */
    RestResult<PayOrderResponse> payOrder(PayOrderRequest payOrderRequest) throws Exception;

    /**
     * cryptocurrency rates info
     * @param cryptocurrencyRequest
     * @return
     * @throws Exception
     */
    RestResult cryptocurrency(CryptocurrencyRequest cryptocurrencyRequest) throws Exception;
}
