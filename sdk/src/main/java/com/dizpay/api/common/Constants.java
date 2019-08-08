package com.dizpay.api.common;

public class Constants {
    public static final String BASE_URI = "https://api.dizpay.com/v2/";
    public static final String CHECK_OUT_BASE_URI = "https://checkout.dizpay.com/v1/";

    /**
     * charge order
     */
    public static final String CREATE_CHARGE_ORDER_ENDPOINT = "member/orders/create_charge_order";
    public static final String QUERY_ORDER_ENDPOINT = "member/orders/query_order";
    public static final String CANCEL_ORDER_ENDPOINT = "member/orders/cancel_order";

    /**
     * payout order
     */
    public static final String CREATE_PAYOUT_ORDER_ENDPOINT = "member/orders/create_payout_order";
    public static final String PAY_ORDER_ENDPOINT = "member/orders/pay_order";

    /**
     * rates
     */
    public static final String CRYPTOCURRENCY_ENDPOINT = "member/rates/cryptocurrency";

    /**
     * checkout api
     */
    public static final String CHECK_OUT_INVOICE_ENDPOINT = "invoice";
}
