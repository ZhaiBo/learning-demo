package ink.zhaibo.netty.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    /**
     * 对于 Netty 里面的 ByteBuf，我们使用 4.1.6.Final 版本，默认情况下用的是堆外内存，
     * 堆外内存我们需要自行释放，随着程序运行越来越久，内存泄露的问题就慢慢暴露出来了，
     * 而这里使用 ByteToMessageDecoder，Netty 会自动进行内存的释放，就不用操心太多的内存管理方面的逻辑
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodec.INSTANCE.decode(in));
    }
}
