package com.dizpay.api.request.payout;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * pay order request parameters
 */
@Data
public class PayOrderRequest {
    /**
     * required,your app_id
     */
    @JSONField(name = "app_id")
    private String appId;
    /**
     * required,order number(global uniqueness)
     */
    private String number;
    /**
     * required,signature
     */
    private String signature;
}
