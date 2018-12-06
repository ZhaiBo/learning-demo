package ink.zhaibo.netty.groupchat.client.handler;

import ink.zhaibo.netty.groupchat.response.LogoutResponsePacket;
import ink.zhaibo.netty.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
        SessionUtils.unBindSession(ctx.channel());
    }
}
