package ink.zhaibo.netty.groupchat.request;

import ink.zhaibo.netty.common.protocol.Command;
import ink.zhaibo.netty.common.protocol.Packet;
import lombok.Data;

@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
