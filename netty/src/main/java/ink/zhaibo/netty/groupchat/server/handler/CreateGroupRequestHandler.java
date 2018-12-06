package ink.zhaibo.netty.groupchat.server.handler;

import ink.zhaibo.netty.groupchat.request.CreateGroupRequestPacket;
import ink.zhaibo.netty.groupchat.response.CreateGroupResponsePacket;
import ink.zhaibo.netty.utils.IDUtil;
import ink.zhaibo.netty.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Netty 在这里的逻辑是：
 * 每次有新连接到来的时候，
 * 都会调用 ChannelInitializer 的 initChannel() 方法，
 * 然后这里 9 个指令相关的 handler 都会被 new 一次。
 * 我们可以看到，其实这里的每一个指令 handler，他们内部都是没有成员变量的，也就是说是无状态的，
 * 我们完全可以使用单例模式，即调用 pipeline().addLast() 方法的时候，都直接使用单例，
 * 不需要每次都 new，提高效率，也避免了创建很多小的对象。
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        List<String> userNameList = new ArrayList<>();
        // 1. 创建一个 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        for (String userId : userIdList) {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtils.getSession(channel).getUserName());
            }
        }

        // 3. 创建群聊创建结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IDUtil.randomId());
        createGroupResponsePacket.setUserNameList(userNameList);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());


        // 5. 保存群组相关的信息
        SessionUtils.bindChannelGroup(createGroupResponsePacket.getGroupId(), channelGroup);
    }
}
