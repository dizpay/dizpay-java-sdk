package com.dizpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * cancel order request parameters
 */
@Data
public class CancelOrderRequest {
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
