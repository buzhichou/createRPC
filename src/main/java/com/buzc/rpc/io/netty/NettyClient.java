package com.buzc.rpc.io.netty;

import com.buzc.rpc.common.RpcRequest;
import com.buzc.rpc.common.RpcResponse;
import com.buzc.rpc.io.common.Constants;
import com.buzc.rpc.io.netty.handler.NettyClientChannelRequestHandler;
import com.buzc.rpc.io.protocol.ProtocolUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * netty请求客户端
 */
public class NettyClient {

    private String host;
    private int port;

    public NettyClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    /**
     * rpc 请求发送方法
     * @param request rpc 请求对象
     * @return rpc响应对象
     */
    public RpcResponse send(RpcRequest request) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new NettyClientChannelRequestHandler());
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            // 发起连接，并将异步方法转换为同步
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            // 获取连接 channel
            Channel channel = channelFuture.channel();
            // 写入并立即刷新管道
            channel.writeAndFlush(ProtocolUtil.convert2Msg(request)).sync();
            // 关闭连接
            channel.closeFuture().sync();
            // 从会话级缓存中取出响应数据
            AttributeKey<RpcResponse> key = AttributeKey.valueOf(Constants.ATTRIBUTEKEY_RESPONSE);
            return channel.attr(key).get();
        } finally {
            group.shutdownGracefully();
        }
    }


}
