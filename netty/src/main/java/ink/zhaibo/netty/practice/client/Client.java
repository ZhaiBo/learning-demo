package ink.zhaibo.netty.practice.client;

import ink.zhaibo.config.Constants;
import ink.zhaibo.netty.practice.codec.PacketDecoder;
import ink.zhaibo.netty.practice.codec.PacketEncoder;
import ink.zhaibo.netty.practice.codec.Spliter;
import ink.zhaibo.netty.utils.ClientUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new ClientHandler());
//                        生命周期ch.pipeline().addLast(new LifeCycleTestHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });

        ClientUtils.connect(bootstrap, Constants.LOCAL_HOST, Constants.SERVER_PORT, MAX_RETRY);
    }
}
