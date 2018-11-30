package com.jo.dy.ot.nio;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

class ServerHanlder extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		super.messageReceived(ctx, e);
		System.out.println("收到客户端信息:" + e.getMessage());
		ctx.getChannel().write("are you ok");
	}

}

public class NettyServer {

	public static void main(String[] args) {
		ServerBootstrap server = new ServerBootstrap();
		Executor workerExecutor = Executors.newCachedThreadPool();
		Executor bossExecutor = Executors.newCachedThreadPool();
		server.setFactory(new NioServerSocketChannelFactory(bossExecutor, workerExecutor));
		server.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				DefaultChannelPipeline channel = new DefaultChannelPipeline();
				channel.addLast("encoder", new StringEncoder());
				channel.addLast("decoder", new StringDecoder());
				channel.addLast("serverHandler", new ServerHanlder());
				return channel;
			}
		});
		server.bind(new InetSocketAddress(8080));
		System.out.println("启动....");
	}

}
