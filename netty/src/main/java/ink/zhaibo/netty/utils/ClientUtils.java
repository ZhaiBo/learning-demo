package ink.zhaibo.netty.utils;

import ink.zhaibo.netty.common.protocol.LoginRequestPacket;
import ink.zhaibo.netty.common.protocol.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientUtils {
    private static final int MAX_RETRY = 5;

    /**
     * 客户端连接
     */
    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功,启动控制台线程!");
                Channel channel = ((ChannelFuture) future).channel();
                // 连接成功之后，启动控制台线程
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    /**
     * 启动控制台线程
     */
    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtils.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    String username = sc.nextLine();
                    loginRequestPacket.setUserName(username);

                    // 密码使用默认的
                    loginRequestPacket.setPassword("pwd");

                    // 发送登录数据包
                    channel.writeAndFlush(loginRequestPacket);

                    waitForLoginResponse();
                } else {
                    String toUserId = sc.next();
                    String message = sc.next();
                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
 /*new Thread(() -> {
         while (!Thread.interrupted()) {
         if (LoginUtils.hasLogin(channel)) {
         //登录成功,输入消息发送至服务端
         Scanner sc = new Scanner(System.in);
         String line = sc.nextLine();
//                  MessageRequestPacket packet = new MessageRequestPacket();
//                  packet.setMessage(line);
//                  ByteBuf byteBuf = PacketCodec.INSTANCE.encode(channel.alloc(), packet);
         channel.writeAndFlush(new MessageRequestPacket(line));
         }
         }
         }).start();*/