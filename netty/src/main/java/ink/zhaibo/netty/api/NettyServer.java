package ink.zhaibo.netty.api;

import ink.zhaibo.config.Constants;
import ink.zhaibo.netty.utils.NettyUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap
                //boss主要负责创建新连接，worker主要用于读取数据以及业务逻辑处理
                .group(boss, worker)

                //.channel(OioServerSocketChannel.class)配置线程模型
                .channel(NioServerSocketChannel.class)

                //handler()用于指定在服务端启动过程中的一些逻辑
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) {
                        System.out.println("服务端启动中");
                    }
                })

                //childHandler()用于指定处理新连接数据的读写处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                });

        //从8000开始绑定,绑定失败则端口+1
        NettyUtils.bindPort(serverBootstrap, Constants.SERVER_PORT);
    }
}
