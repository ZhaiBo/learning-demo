package ink.zhaibo.netty.utils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class ServerUtils {

    /**
     * 服务端启动
     */
    public static void bindPort(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bindPort(serverBootstrap, port + 1);
                }
            }
        });
    }

}
