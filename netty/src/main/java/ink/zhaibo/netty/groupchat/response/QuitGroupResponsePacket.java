package ink.zhaibo.netty.groupchat.response;

import ink.zhaibo.netty.common.protocol.Command;
import ink.zhaibo.netty.common.protocol.Packet;
import lombok.Data;

@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}