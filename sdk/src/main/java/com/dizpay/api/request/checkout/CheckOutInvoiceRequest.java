package com.dizpay.api.request.checkout;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * checkout model
 */
@Data
public class CheckOutInvoiceRequest implements Serializable {
    /**
     * required,app_id
     * Merchant's app id. You can find it in your merchant dashboard developer section.
     */
    @JSONField(name = "app_id")
    private String appId;
    /**
     * required,order_number,limits: 36 characters or less
     * Merchant's custom unique order number. Example: 20190714224342023
     */
    @JSONField(name = "order_number")
    private String orderNumber;
    /**
     * required,Checkout name, Example: DizPay Inc.
     */
    private String name;
    /**
     * optional, default : Add crypto to the {{ name }} account.
     * More details about the order. It can be cart items or product details. Example: Apple iPhoneX.
     */
    private String description;
    /**
     * optional, If logo_url does not exist, a default logo will show in the icon box.
     */
    @JSONField(name = "logo_url")
    private String logoUrl;
    /**
     * optional, options : email | mobile
     * Specify what information the merchants wants to collect from the buyers. if payer_info is not specified, the buyer page will skip.
     */
    @JSONField(name = "payer_info")
    private String payerInfo;
    /**
     * required,options : fixed_price | no_price
     * If pricing_type is fixed_price, the crypto payment based on the currency code and fiat price. Otherwise, the crypto payment is
     * specified by the cryptocurrency code and amount.
     */
    @JSONField(name = "pricing_type")
    private String pricingType;
    /**
     * required,currency.
     * options: USD | CNY | GBP | BTC | ETH | LTC | DASH | USDT | TUSD | GUSD | PAX | USDC
     * If pricing_type is fixed_price, then this field will specify the price in fiat currency code. Example: USD
     * If pricing_type is no_price, the field will specify the price in cryptocurrency code. Example: BTC
     */
    private String currency;
    /**
     * optional,options: okex | binance | huobi | kraken
     * default:huobi
     * If pricing_type is fixed_price, then this field will specify the amount by the exchange pricing conversion.
     */
    private String rate;

    /**
     * required,amount.eg: "100.0"
     * The price set by the merchant. Example: 108.99. If pricing_type is fixed_price, the amount specifies
     * the price in the currency price, and currency conversions are done by DizPay.
     * If pricing_type is no_price, the amount specifies the cryptocurrency value.
     */
    private String amount;
    /**
     * required,options: BTC | ETH | LTC | DASH | USDT | TUSD | GUSD | PAX | USDC
     * default : “BTC, ETH, USDT”
     * Array of strings specifying what cryptocurrencies the merchants wants to collect from the buyers.
     * The order of the crypto list will follow the same sequence in the array. Example: “BTC, ETH, USDT”.
     */
    private String crypto;
    /**
     * optional,language
     * options: auto | en | cn | ru | ko | jp
     * default : auto
     * If locale is auto, the checkout language can be detected automatically by the browser's settings.
     */
    private String locale;
    /**
     * required,success url
     * Redirect to the merchant URL after successful payment.
     */
    @JSONField(name = "success_url")
    private String successUrl;
    /**
     * required, Redirect to a failure URL when the charge failed to complete. The buyer cancels the order or the payment expired.
     */
    @JSONField(name = "cancel_url")
    private String cancelUrl;
    /**
     * required,notify url。eg："https://charge/callback"
     * Send information to the callback URL when charge has been confirmed and the associated payment is completed.
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;
    /**
     * optional, another-params
     * You may pass additional data for later use in notify_url callback.
     */
    private String extra;
    /**
     * just for signature, No external incoming
     */
    private String signature;
}
