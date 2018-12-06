package ink.zhaibo.netty.groupchat.server.handler;

import ink.zhaibo.netty.common.protocol.Command;
import ink.zhaibo.netty.common.protocol.Packet;
import ink.zhaibo.netty.practice.server.MessageRequestHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {
    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();
        /**
         * 对我们这个应用程序来说，每次 decode 出来一个指令对象之后，
         * 其实只会在一个指令 handler 上进行处理，
         * 因此，我们其实可以把这么多的指令 handler 压缩为一个 handler
         */
        handlerMap.put(Command.MESSAGE_REQUEST, new MessageRequestHandler());
        handlerMap.put(Command.CREATE_GROUP_REQUEST, new CreateGroupRequestHandler());
        handlerMap.put(Command.JOIN_GROUP_REQUEST, new JoinGroupRequestHandler());
        handlerMap.put(Command.QUIT_GROUP_REQUEST, new QuitGroupRequestHandler());
        handlerMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, new ListGroupMembersRequestHandler());
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST, new GroupMessageRequestHandler());
        handlerMap.put(Command.LOGOUT_REQUEST, new LogoutRequestHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}