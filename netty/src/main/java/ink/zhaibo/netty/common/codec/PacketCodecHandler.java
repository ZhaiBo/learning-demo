package ink.zhaibo.netty.common.codec;

import ink.zhaibo.netty.common.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {
    /**
     * 无状态的 handler，因此，同样可以使用单例模式来实现。
     * 如果 handler 里有与 channel 相关成员变量，
     * 那就不要写成单例的，不过，其实所有的状态都可以绑定在 channel 的属性上，
     * 依然是可以改造成单例模式。
     */
    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    private PacketCodecHandler() {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) {
        out.add(PacketCodec.INSTANCE.decode(byteBuf));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodec.INSTANCE.autoEncode(byteBuf, packet);
        out.add(byteBuf);
    }
}