package com.dizpay.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * checkout model
 */
@Data
public class CheckOutResponse implements Serializable {
    /**
     * invoiceId
     */
    private String invoiceId;
    /**
     * paymentUrl
     */
    private String paymentUrl;
}
