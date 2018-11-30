package com.jo.dy.ot.nio;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

class ClientHanlder extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		super.messageReceived(ctx, e);
		System.out.println("收到服务端..." + e.getMessage());
	}

}

public class NettyClient {
	public static void main(String[] args) {
		ClientBootstrap client = new ClientBootstrap();
		Executor workerExecutor = Executors.newCachedThreadPool();
		Executor bossExecutor = Executors.newCachedThreadPool();
		client.setFactory(new NioClientSocketChannelFactory(bossExecutor, workerExecutor));
		client.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				DefaultChannelPipeline channel = new DefaultChannelPipeline();
				channel.addLast("encoder", new StringEncoder());
				channel.addLast("decoder", new StringDecoder());
				channel.addLast("clientHandler", new ClientHanlder());
				return channel;
			}
		});
		ChannelFuture connect = client.connect(new InetSocketAddress("127.0.0.1", 8080));
		System.out.println("启动....");
		Channel channel = connect.getChannel();
		Scanner scan = new Scanner(System.in);
		while (true) {
			channel.write(scan.nextLine());
		}

	}
}
