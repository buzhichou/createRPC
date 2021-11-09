package com.buzc.rpc.io.protocol;


/**
 * 自定义通信协议
 *
 *
 +--------+--------+--------+--------+--------+--------+--------+--------+--------+--------+--------+--------+--------+--------+--------+--------+
 |  BYTE  |        |        |        |        |        |        |             ........
 +--------------------------------------------+--------+-----------------+--------+--------+--------+--------+--------+--------+-----------------+
 |  magic | version|  type  |           content lenth           |                   content byte[]                                        |        |
 +--------+-----------------------------------------------------------------------------------------+--------------------------------------------+
 */
public class ProtocolMsg {

    /**第一个字节是魔法数定义为*/
    private byte magic;

    /**第二个字节代表协议版本号*/
    private byte version;

    /**第三个字节是请求类型，0代表请求1代表响应*/
    private byte msgType;

    /*第四个字节表示消息长度，即此四个字节后面此长度的内容是消息content*/
    //    private byte contentLenth;

    /**消息内容*/
    private byte[] content;

    public byte getMagic() {
        return magic;
    }

    public void setMagic(byte magic) {
        this.magic = magic;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
