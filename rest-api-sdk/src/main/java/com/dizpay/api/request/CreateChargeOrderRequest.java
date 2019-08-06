package com.dizpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * create charge order request parameters
 */
@Data
public class CreateChargeOrderRequest {
    /**
     * your app_id
     */
    @JSONField(name = "app_id")
    private String appId;
    /**
     * order number(global uniqueness)
     */
    private String number;
    /**
     * signature
     */
    private String signature;
    /**
     * currency code
     */
    @JSONField(name = "currency_code")
    private String currencyCode;
    /**
     * optional（0 or 1），default value is 0，0:not token currency，1:token
     */
    @JSONField(name = "erc20_token")
    private int erc20Token = 0;
    /**
     * currency amount
     */
    private String amount;
    /**
     * optional,extra info
     */
    private String extra;
    /**
     * optional,order status notify url
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;
}
