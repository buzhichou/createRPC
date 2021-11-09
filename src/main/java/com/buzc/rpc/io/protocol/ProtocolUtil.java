package com.buzc.rpc.io.protocol;

import com.buzc.rpc.common.RpcResponse;
import com.buzc.rpc.io.protocol.constants.ProtocolConstant;
import com.buzc.rpc.io.seralizer.RpcSerializer;

/**
 * rpc 请求和响应序列化、反序列话工具
 */
public class ProtocolUtil {

    /**
     * 将对象转换为 rpc 协议消息
     * @param o 要转化的对象
     */
    public static <T> ProtocolMsg convert2Msg(T o){
        ProtocolMsg protocolMsg = new ProtocolMsg();
        protocolMsg.setVersion(ProtocolConstant.DEFAULT_VERSION);
        protocolMsg.setMagic(ProtocolConstant.MAGIC);
        byte[] buf = RpcSerializer.serialize(o);
        protocolMsg.setContent(buf);
        protocolMsg.setMsgType(ProtocolConstant.MSGTYPE_REQUEST);
        return protocolMsg;
    }

    /**
     * 将 rpc 协议消息转换为响应对象
     * @param msg 序列话的消息对象
     */
    public static RpcResponse convert2Response(ProtocolMsg msg){
        RpcResponse response = RpcSerializer.deserialize(msg.getContent(), RpcResponse.class);
        return response;
    }
}
