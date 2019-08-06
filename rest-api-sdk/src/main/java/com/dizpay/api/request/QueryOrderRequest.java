package com.dizpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * query order request parameters
 */
@Data
public class QueryOrderRequest {
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
