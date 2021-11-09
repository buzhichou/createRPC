package com.buzc.rpc.common;

/**
 * rpc 请求状态枚举
 */
public enum RpcStatusEnum {
    NOT_FIND(404), SUCESS(200);

    private int code;

    RpcStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
