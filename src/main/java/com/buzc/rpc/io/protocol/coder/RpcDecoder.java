package com.buzc.rpc.io.protocol.coder;

import com.buzc.rpc.io.protocol.ProtocolMsg;
import com.buzc.rpc.io.protocol.constants.ProtocolConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * rpc 通信 netty 消息解码器
 */
public class RpcDecoder extends ByteToMessageDecoder {

    /**
     * 协议开始的标志 magic + version + type + length 占据7个字节
     */
    public final int BASE_LENGTH = 7;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        // 可读字节小于基本长度，返回
        if (byteBuf.readableBytes() < BASE_LENGTH) {
            return;
        }
        // 记录包头开始index
        int beginIndex;
        // 准备拆包
        while (true) {
            // 记录包头
            beginIndex = byteBuf.readerIndex();
            //
            byteBuf.markReaderIndex();
            // 读到协议头魔数，结束循环
            if (byteBuf.readByte() == ProtocolConstant.MAGIC) {
                break;
            }
            // 未找到包头，略过一个字节
            byteBuf.resetReaderIndex();
            byteBuf.readByte();

            /*略过一个字节之后，数据包的长度可能又变得不满足条件，结束，等待后面的数据到达*/
            if (byteBuf.readableBytes() < BASE_LENGTH) {
                return;
            }
        }
        // 读取版本号
        byte version = byteBuf.readByte();
        // 读取消息类型
        byte type = byteBuf.readByte();
        // 读取消息长度
        int length = byteBuf.readInt();
        // 判断本包是否完整
        if (byteBuf.readableBytes() < length){
            // 还原读指针
            byteBuf.readerIndex(beginIndex);
            return;
        }
        byte[] data = new byte[length];
        byteBuf.readBytes(data);

        ProtocolMsg msg = new ProtocolMsg();
        msg.setVersion(version);
        msg.setMsgType(type);
        msg.setContent(data);
        list.add(msg);
    }
}
