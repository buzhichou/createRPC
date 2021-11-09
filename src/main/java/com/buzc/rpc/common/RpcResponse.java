package com.buzc.rpc.common;

/**
 * rpc 响应对象
 */
public class RpcResponse {
    /**请求id,标识响应的是哪个请求*/
    private String requestId;
    /**返回数据*/
    private Object data;
    /**状态信息*/
    private RpcStatusEnum status;
    /**异常信息*/
    private Exception exception;

    /**
     * 构造函数
     */
    public RpcResponse(RpcStatusEnum status){
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RpcStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RpcStatusEnum status) {
        this.status = status;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
