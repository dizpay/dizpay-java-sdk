package com.dizpay.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.dizpay.demo.config.DizPayConfig;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * checkout model
 */
@Data
public class CheckOutRequest implements Serializable {
    /**
     * app_id
     */
    @JSONField(name = "app_id")
    private String appId;
    /**
     * order_number
     */
    @JSONField(name = "order_number")
    private String orderNumber;
    /**
     * Your Merchant Name, eg: DizPay Inc.',
     */
    private String name;
    /**
     * optional, default is: Add crypto to your {{ Domain or App Name }} account.
     */
    private String description;
    /**
     * for logo. 128x128px, gif, .jpeg, and .png。eg："https://commerce.merchant.com/charges/logo.png",
     */
    @JSONField(name = "logo_url")
    private String logoUrl;
    /**
     * one of in ['', 'email', 'mobile']
     */
    @JSONField(name = "payer_info")
    private String payerInfo;
    /**
     * one of in ['fixed_price', 'no_price']
     */
    @JSONField(name = "pricing_type")
    private String pricingType;
    /**
     * currency。eg："USD"
     */
    private String currency;
    /**
     * amount。eg: "100.0",
     */
    private String amount;
    /**
     * only work when pricing_type => fixed_price
     */
    private String crypto;
    /**
     * language
     */
    private String locale;
    /**
     * one of in ['light', 'dark', 'standard'],
     */
    private String theme;
    /**
     * notify url。eg："https://charge/callback"
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;
    /**
     * on success.eg： “https://commerce.merchant.com/success_url.html”
     */
    @JSONField(name = "redirect_url")
    private String redirectUrl;
    /**
     * on failures.eg： "https://charge/canceled/page"
     */
    @JSONField(name = "cancel_url")
    private String cancelUrl;
    /**
     * another-params
     */
    private String extra;
    /**
     * signature
     */
    private String signature;
}
