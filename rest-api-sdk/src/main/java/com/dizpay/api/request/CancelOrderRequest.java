package com.dizpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * cancel order request parameters
 */
@Data
public class CancelOrderRequest {
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
