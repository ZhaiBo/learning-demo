package ink.zhaibo.netty.groupchat.request;

import ink.zhaibo.netty.common.protocol.Command;
import ink.zhaibo.netty.common.protocol.Packet;

public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return  Command.LOGOUT_REQUEST;
    }
}
