package ink.zhaibo.netty.groupchat.server;

import ink.zhaibo.netty.common.codec.PacketCodecHandler;
import ink.zhaibo.netty.common.codec.PacketDecoder;
import ink.zhaibo.netty.common.codec.PacketEncoder;
import ink.zhaibo.netty.common.codec.Spliter;
import ink.zhaibo.netty.groupchat.server.handler.*;
import ink.zhaibo.netty.practice.server.AuthHandler;
import ink.zhaibo.netty.practice.server.LoginRequestHandler;
import ink.zhaibo.netty.practice.server.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class GroupServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        // 登录请求处理器
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                      /*  // 单聊消息请求处理器
                        ch.pipeline().addLast(new MessageRequestHandler());
                        // 创建群请求处理器
                        //共享handler
                        ch.pipeline().addLast(CreateGroupRequestHandler.INSTANCE);
                        // 加群请求处理器
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        // 退群请求处理器
                        ch.pipeline().addLast(new QuitGroupRequestHandler());
                        // 获取群成员请求处理器
                        ch.pipeline().addLast(new ListGroupMembersRequestHandler());
                        // 群发消息
                        ch.pipeline().addLast(new GroupMessageRequestHandler());
                        // 登出请求处理器
                        ch.pipeline().addLast(new LogoutRequestHandler());*/
                    }
                });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
