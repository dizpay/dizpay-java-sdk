package com.dizpay.api.common;

import lombok.Data;

@Data
public class RestResult<T> {

    private long code;

    private Object message;

    private T data;

    public RestResult() {
        super();
    }

    public RestResult(long code, T data, Object message) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RestResult success(Object data) {
        return new RestResult(StatusCodeEnum.OK.getCode(), data, StatusCodeEnum.OK.getDesc());
    }

    public static RestResult error(long code, Object message) {
        return new RestResult(code, null, message);
    }
}
