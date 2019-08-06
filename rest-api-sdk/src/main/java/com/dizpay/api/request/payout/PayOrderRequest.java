package com.dizpay.api.request.payout;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * pay order request parameters
 */
@Data
public class PayOrderRequest {
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
}
