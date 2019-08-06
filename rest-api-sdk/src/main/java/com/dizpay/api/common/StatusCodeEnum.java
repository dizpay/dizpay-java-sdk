package com.dizpay.api.common;

/**
 * status code constants
 */
public enum StatusCodeEnum {

    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    Forbidden(403, "Forbidden"),
    INTERNAL(500, "Internal"),
    ;

    private int code;
    private String desc;

    StatusCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * find enum by enumName
     */
    public static StatusCodeEnum findByName(String enumName) {
        for (StatusCodeEnum statusCodeEnum: StatusCodeEnum.values()) {
            if (statusCodeEnum.name().equals(enumName)) {
                return statusCodeEnum;
            }
        }
        return null;
    }

    /**
     * find value by enumName
     * @param val
     * @return
     */
    public static StatusCodeEnum findByCode(Integer val) {
        for (StatusCodeEnum statusCodeEnum : StatusCodeEnum.values()) {
            if (statusCodeEnum.code == val) {
                return statusCodeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
