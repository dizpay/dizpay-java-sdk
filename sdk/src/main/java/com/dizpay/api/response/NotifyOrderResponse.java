package com.dizpay.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * query order response parameters
 */
@Data
public class NotifyOrderResponse {
    /**
     * order number
     */
    private String number;
    /**
     * currency code
     */
    @JSONField(name = "currency_code")
    private String currencyCode;
    /**
     * 0:not token currency，1:token
     */
    @JSONField(name = "erc20_token")
    private Integer erc20Token;
    /**
     * currency amount
     */
    private String amount;
    /**
     * paid currency amount
     */
    @JSONField(name = "paid_amount")
    private String paidAmount;
    /**
     * extra info
     */
    private String extra;
    /**
     * order status: 1 means in progress, 2 means completed, 4 means cancelled
     */
    private Integer status;
    /**
     * signature
     */
    private String signature;
}
