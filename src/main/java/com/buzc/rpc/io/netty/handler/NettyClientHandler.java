package com.buzc.rpc.io.netty.handler;

import com.buzc.rpc.common.RpcResponse;
import com.buzc.rpc.io.common.Constants;
import com.buzc.rpc.io.protocol.ProtocolMsg;
import com.buzc.rpc.io.protocol.ProtocolUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

public class NettyClientHandler extends SimpleChannelInboundHandler<ProtocolMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtocolMsg msg) {
        // 解析出响应对象
        RpcResponse response = ProtocolUtil.convert2Response(msg);
        // 将响应对象存入 attribute中，以便在nettyclient中取出
        AttributeKey<RpcResponse> key = AttributeKey.valueOf(Constants.ATTRIBUTEKEY_RESPONSE);
        channelHandlerContext.channel().attr(key).set(response);
        channelHandlerContext.channel().close();
    }
}
