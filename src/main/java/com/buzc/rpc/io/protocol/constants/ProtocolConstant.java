package com.buzc.rpc.io.protocol.constants;

public class ProtocolConstant {

    /**魔数用于验证私有协议*/
    public static final byte MAGIC = 0X35;

    /**默认协议版本*/
    public static final byte DEFAULT_VERSION = 1;

    /**求类型，0代表请求*/
    public static final byte MSGTYPE_REQUEST = 0;
    /**求类型，1代表响应*/
    public static final byte MSGTYPE_RESPONSE = 1;

    /**response 的 attributekey 值*/
    String ATTRIBUTEKEY_RESPONSE = "response";

}
