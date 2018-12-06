package ink.zhaibo.netty.groupchat.server.handler;

import ink.zhaibo.netty.groupchat.request.GroupMessageRequestPacket;
import ink.zhaibo.netty.groupchat.response.GroupMessageResponsePacket;
import ink.zhaibo.netty.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setFromUser(SessionUtils.getSession(ctx.channel()));

        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}