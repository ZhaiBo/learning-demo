package ink.zhaibo.netty.groupchat.command;

import ink.zhaibo.netty.groupchat.console.ConsoleCommand;
import ink.zhaibo.netty.groupchat.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
