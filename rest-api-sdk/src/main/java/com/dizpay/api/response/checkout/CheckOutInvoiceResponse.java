package com.dizpay.api.response.checkout;

import lombok.Data;

import java.io.Serializable;

/**
 * checkout res
 */
@Data
public class CheckOutInvoiceResponse implements Serializable {
    /**
     * invoiceId
     */
    private String invoiceId;
    /**
     * paymentUrl
     */
    private String paymentUrl;
}
