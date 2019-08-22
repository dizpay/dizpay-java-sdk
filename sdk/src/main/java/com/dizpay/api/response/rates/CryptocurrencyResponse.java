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
     * huobipro usd price
     */
    @JSONField(name = "huobipro_usd_price")
    private String huobiproUsdPrice;
    /**
     * okex usd price
     */
    @JSONField(name = "okex_usd_price")
    private String okexUsdPrice;
    /**
     * binance usd price
     */
    @JSONField(name = "binance_usd_price")
    private String binanceUsdPrice;
    /**
     * percent change 24h(%)
     */
    @JSONField(name = "percent_change_24h")
    private String percentChange24h;
    /**
     * country
     */
    @JSONField(name = "country")
    private String country;
    /**
     * country code
     */
    @JSONField(name = "country_code")
    private String countryCode;
    /**
     * currency
     */
    @JSONField(name = "currency")
    private String currency;
    /**
     * usd rate
     */
    @JSONField(name = "usd_rate")
    private String usdRate;
}
