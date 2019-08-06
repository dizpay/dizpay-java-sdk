package com.dizpay.api.request.rates;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * cryptocurrency request
 */
@Data
public class CryptocurrencyRequest {
    @JSONField(name = "currency_list")
    private String currencyList;
}
