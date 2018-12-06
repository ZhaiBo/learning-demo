package ink.zhaibo.netty.groupchat.request;

import ink.zhaibo.netty.common.protocol.Command;
import ink.zhaibo.netty.common.protocol.Packet;
import lombok.Data;

@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
