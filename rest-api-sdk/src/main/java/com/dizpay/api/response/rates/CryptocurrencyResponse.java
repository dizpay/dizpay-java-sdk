package com.dizpay.api.response.rates;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * cryptocurrency response
 */
@Data
public class CryptocurrencyResponse {
    /**
     * usd price
     */
    @JSONField(name = "usd_price")
    private String usdPrice;
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
     * percent change 24h(%)
     */
    @JSONField(name = "percent_change_24h")
    private Integer percentChange24h;
}
