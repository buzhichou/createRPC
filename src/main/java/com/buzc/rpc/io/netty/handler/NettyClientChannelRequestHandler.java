package com.buzc.rpc.io.netty.handler;

import com.buzc.rpc.io.protocol.coder.RpcDecoder;
import com.buzc.rpc.io.protocol.coder.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 客户端响应数据处理
 */
public class NettyClientChannelRequestHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new RpcEncoder());
        pipeline.addLast(new RpcDecoder());
        pipeline.addLast(new NettyClientHandler());
    }
}
