package ink.zhaibo.netty.groupchat.response;

import ink.zhaibo.netty.common.protocol.Command;
import ink.zhaibo.netty.common.protocol.Packet;
import ink.zhaibo.netty.common.protocol.Session;
import lombok.Data;

@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return Command.GROUP_MESSAGE_RESPONSE;
    }
}