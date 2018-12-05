package ink.zhaibo.netty.practice.protocol;

import lombok.Data;

@Data
public class MessageResponsePacket extends Packet {
    private String message;

    private String fromUserId;

    private String fromUserName;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
