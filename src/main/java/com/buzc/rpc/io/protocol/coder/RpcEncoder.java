package com.buzc.rpc.io.protocol.coder;

import com.buzc.rpc.io.protocol.ProtocolMsg;
import com.buzc.rpc.io.protocol.constants.ProtocolConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * rpc 消息编码器
 */
public class RpcEncoder extends MessageToByteEncoder<ProtocolMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtocolMsg protocolMsg, ByteBuf byteBuf) {
        /*
        按自定义协议格式： magic + version + type + length + data
        的顺序写入
         */
        // 协议头魔数
        byteBuf.writeByte(ProtocolConstant.MAGIC);
        // 写入版本
        byteBuf.writeByte(ProtocolConstant.DEFAULT_VERSION);
        // 写入消息类型
        byteBuf.writeByte(protocolMsg.getMsgType());
        // 写入消息长度
        byteBuf.writeByte(protocolMsg.getContent().length);
        // 写入消息内容
        byteBuf.writeBytes(protocolMsg.getContent());
    }
}
