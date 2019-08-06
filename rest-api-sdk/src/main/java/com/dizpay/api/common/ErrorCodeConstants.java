package com.dizpay.api.common;

/**
 * error code constants
 */
public class ErrorCodeConstants {
    /**
     * Field format error (required, range, type)
     */
    public static final int FIELD_FORMAT = 1000;
    /**
     * Field does not exist
     */
    public static final int FIELD_NOT_EXIST = 1001;
    /**
     * Field does not match
     */
    public static final int FIELD_NOT_MATCH = 1002;
    /**
     * Field illegal
     */
    public static final int FIELD_ILLEGAL = 1003;
    /**
     * message failed to send
     */
    public static final int MESSAGE_FAILED_SEND = 1004;
    /**
     * try too many times
     */
    public static final int TOO_MANY_TIMES = 1005;
    /**
     * field is expired
     */
    public static final int FIELD_IS_EXPIRED = 1006;
    /**
     * Insufficient balance
     */
    public static final int INSUFFICIENT_BALANCE = 1008;
    /**
     * email failed to send
     */
    public static final int EMAIL_FAILED_SEND = 1010;
    /**
     * invalid address
     */
    public static final int INVALID_ADDRESS = 1051;
}
