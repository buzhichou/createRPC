package com.buzc.rpc.common;

/**
 * RPC 请求体
 */
public class RpcRequest {

    /**请求id*/
    private String requestId;

    /**接口名称*/
    private String interfaceName;
    /**版本*/
    private String serviceVersion;

    /**方法名*/
    private String method;
    /**参数类型*/
    private Class<?> parameterTypes;
    /**具体参数*/
    private Object[] patameters;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getPatameters() {
        return patameters;
    }

    public void setPatameters(Object[] patameters) {
        this.patameters = patameters;
    }
}
