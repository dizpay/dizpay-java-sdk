package com.dizpay.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dizpay.api.DizpayClient;
import com.dizpay.api.common.Constants;
import com.dizpay.api.common.ErrorCodeConstants;
import com.dizpay.api.common.RestResult;
import com.dizpay.api.common.StatusCodeEnum;
import com.dizpay.api.request.CancelOrderRequest;
import com.dizpay.api.request.CreateChargeOrderRequest;
import com.dizpay.api.request.QueryOrderRequest;
import com.dizpay.api.request.checkout.CheckOutInvoiceRequest;
import com.dizpay.api.request.payout.CreatePayoutOrderRequest;
import com.dizpay.api.request.payout.PayOrderRequest;
import com.dizpay.api.request.rates.CryptocurrencyRequest;
import com.dizpay.api.response.CancelOrderResponse;
import com.dizpay.api.response.CreateChargeOrderResponse;
import com.dizpay.api.response.QueryOrderResponse;
import com.dizpay.api.response.checkout.CheckOutInvoiceResponse;
import com.dizpay.api.response.payout.CreatePayoutOrderResponse;
import com.dizpay.api.response.payout.PayOrderResponse;
import com.dizpay.api.util.DizpaySignature;
import com.dizpay.api.util.HttpClientUtil;
import okhttp3.Response;

import java.io.IOException;

public class DefaultDizpayClient implements DizpayClient {

    /**
     * app_id
     */
    private String appId;
    /**
     * app_key
     */
    private String appKey;

    /**
     * create DefaultDizpayClient method
     * @param appId
     * @param appKey
     */
    public DefaultDizpayClient(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    @Override
    public RestResult<CreateChargeOrderResponse> createChargeOrder(CreateChargeOrderRequest createChargeOrderRequest) throws Exception {
        // 1.verify request object
        JSONObject errorObj = new JSONObject();
        if (createChargeOrderRequest == null) {
            errorObj.put("createChargeOrderRequest", "createChargeOrderRequest does not exist");
            return RestResult.error(ErrorCodeConstants.FIELD_NOT_EXIST, errorObj);
        }
        // 2.get the signature value
        String signature = DizpaySignature.signature(createChargeOrderRequest, this.appId, this.appKey);
        createChargeOrderRequest.setAppId(this.appId);
        createChargeOrderRequest.setSignature(signature);

        // 3.send request to dizpay
        Response response = HttpClientUtil.httpPost(Constants.BASE_URI + Constants.CREATE_CHARGE_ORDER_ENDPOINT, JSONObject.toJSONString(createChargeOrderRequest));
        return assembleRsp(response, CreateChargeOrderResponse.class);
    }

    @Override
    public RestResult<QueryOrderResponse> queryOrder(QueryOrderRequest queryOrderRequest) throws Exception {
        // 1.verify request object
        JSONObject errorObj = new JSONObject();
        if (queryOrderRequest == null) {
            errorObj.put("queryOrderRequest", "queryOrderRequest does not exist");
            return RestResult.error(ErrorCodeConstants.FIELD_NOT_EXIST, errorObj);
        }
        // 2.get the signature value
        String signature = DizpaySignature.signature(queryOrderRequest, this.appId, this.appKey);
        queryOrderRequest.setAppId(this.appId);
        queryOrderRequest.setSignature(signature);

        // 3.send request to dizpay
        Response response = HttpClientUtil.httpPost(Constants.BASE_URI + Constants.QUERY_ORDER_ENDPOINT, JSONObject.toJSONString(queryOrderRequest));
        return assembleRsp(response, QueryOrderResponse.class);
    }

    @Override
    public RestResult<CancelOrderResponse> cancelOrder(CancelOrderRequest cancelOrderRequest) throws Exception {
        // 1.verify request object
        JSONObject errorObj = new JSONObject();
        if (cancelOrderRequest == null) {
            errorObj.put("cancelOrderRequest", "cancelOrderRequest does not exist");
            return RestResult.error(ErrorCodeConstants.FIELD_NOT_EXIST, errorObj);
        }
        // 2.get the signature value
        String signature = DizpaySignature.signature(cancelOrderRequest, this.appId, this.appKey);
        cancelOrderRequest.setAppId(this.appId);
        cancelOrderRequest.setSignature(signature);

        // 3.send request to dizpay
        Response response = HttpClientUtil.httpPost(Constants.BASE_URI + Constants.CANCEL_ORDER_ENDPOINT, JSONObject.toJSONString(cancelOrderRequest));
        return assembleRsp(response, CancelOrderResponse.class);
    }

    @Override
    public RestResult<CreatePayoutOrderResponse> createPayoutOrder(CreatePayoutOrderRequest createPayoutOrderRequest) throws Exception {
        // 1.verify request object
        JSONObject errorObj = new JSONObject();
        if (createPayoutOrderRequest == null) {
            errorObj.put("createPayoutOrderRequest", "createPayoutOrderRequest does not exist");
            return RestResult.error(ErrorCodeConstants.FIELD_NOT_EXIST, errorObj);
        }
        // 2.get the signature value
        String signature = DizpaySignature.signature(createPayoutOrderRequest, this.appId, this.appKey);
        createPayoutOrderRequest.setAppId(this.appId);
        createPayoutOrderRequest.setSignature(signature);

        // 3.send request to dizpay
        Response response = HttpClientUtil.httpPost(Constants.BASE_URI + Constants.CREATE_PAYOUT_ORDER_ENDPOINT, JSONObject.toJSONString(createPayoutOrderRequest));
        return assembleRsp(response, CreatePayoutOrderResponse.class);
    }

    @Override
    public RestResult<PayOrderResponse> payOrder(PayOrderRequest payOrderRequest) throws Exception {
        // 1.verify request object
        JSONObject errorObj = new JSONObject();
        if (payOrderRequest == null) {
            errorObj.put("payOrderRequest", "payOrderRequest does not exist");
            return RestResult.error(ErrorCodeConstants.FIELD_NOT_EXIST, errorObj);
        }
        // 2.get the signature value
        String signature = DizpaySignature.signature(payOrderRequest, this.appId, this.appKey);
        payOrderRequest.setAppId(this.appId);
        payOrderRequest.setSignature(signature);

        // 3.send request to dizpay
        Response response = HttpClientUtil.httpPost(Constants.BASE_URI + Constants.PAY_ORDER_ENDPOINT, JSONObject.toJSONString(payOrderRequest));
        return assembleRsp(response, PayOrderResponse.class);
    }

    @Override
    public RestResult<CheckOutInvoiceResponse> checkoutInvoice(CheckOutInvoiceRequest checkOutRequest) throws Exception {
        // 1.verify request object
        JSONObject errorObj = new JSONObject();
        if (checkOutRequest == null) {
            errorObj.put("checkOutRequest", "checkOutRequest does not exist");
            return RestResult.error(ErrorCodeConstants.FIELD_NOT_EXIST, errorObj);
        }
        // 2.get the signature value
        String signature = DizpaySignature.signature(checkOutRequest, this.appId, this.appKey);
        checkOutRequest.setAppId(this.appId);
        checkOutRequest.setSignature(signature);

        // 3.send request to dizpay
        Response response = HttpClientUtil.httpPost(Constants.CHECK_OUT_BASE_URI + Constants.CHECK_OUT_INVOICE_ENDPOINT, JSONObject.toJSONString(checkOutRequest));
        return assembleRsp(response, CheckOutInvoiceResponse.class);
    }

    @Override
    public RestResult cryptocurrency(CryptocurrencyRequest cryptocurrencyRequest) throws Exception {
        // 1.verify request object
        JSONObject errorObj = new JSONObject();
        if (cryptocurrencyRequest == null) {
            errorObj.put("cryptocurrencyRequest", "cryptocurrencyRequest does not exist");
            return RestResult.error(ErrorCodeConstants.FIELD_NOT_EXIST, errorObj);
        }

        // 2.send request to dizpay
        Response response = HttpClientUtil.httpPost(Constants.BASE_URI + Constants.CRYPTOCURRENCY_ENDPOINT, JSONObject.toJSONString(cryptocurrencyRequest));
        if (response != null) {
            int statusCode = response.code();
            String rspStr = response.body().string();
            if (StatusCodeEnum.OK.getCode() == statusCode) {
                return RestResult.success(rspStr);
            } else {
                return RestResult.error(statusCode, rspStr);
            }
        }
        return null;
    }

    /**
     * assemble response
     * @param response
     * @param cls
     * @return
     * @throws IOException
     */
    private RestResult assembleRsp(Response response, Class cls) throws IOException {
        if (response != null) {
            int statusCode = response.code();
            String rspStr = response.body().string();
            // according to statusCode do next step
            if (StatusCodeEnum.OK.getCode() == statusCode) {
                Object createChargeOrderResponse = JSONObject.parseObject(rspStr, cls);
                return RestResult.success(createChargeOrderResponse);
            } else if (StatusCodeEnum.BAD_REQUEST.getCode() == statusCode) {
                RestResult<CreateChargeOrderResponse> errorRsp = JSONObject.parseObject(rspStr, RestResult.class);
                return errorRsp;
            } else {
                StatusCodeEnum statusCodeEnum = StatusCodeEnum.findByCode(statusCode);
                if (statusCodeEnum != null) {
                    return RestResult.error(statusCode, rspStr);
                }
            }
        }
        return RestResult.error(StatusCodeEnum.BAD_REQUEST.getCode(), StatusCodeEnum.BAD_REQUEST.getDesc());
    }
}
