package com.dizpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * query order request parameters
 */
@Data
public class QueryOrderRequest {
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
