package com.jo.dy.ot.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 基于通道的NIO--面向缓冲区,非阻塞,选择器<br>
 * FileChannel--SocketChannel--ServerSocketChannel--DatagramChannel
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月28日 上午10:36:38
 */
public class FileChannelDemo {

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();
		FileChannel read = FileChannel.open(Paths.get("D://a.mp4"), StandardOpenOption.READ);
		FileChannel writer = FileChannel.open(Paths.get("D://b.mp4"), StandardOpenOption.READ, StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);
		ByteBuffer dst = ByteBuffer.allocate((int) read.size());
		read.read(dst);
		System.out.println("position1:" + dst.position());
		System.out.println("limit1:" + dst.limit());
		dst.flip();// 从起始开始读取,将position设置为0
		System.out.println("position2:" + dst.position());
		System.out.println("limit2:" + dst.limit());
		writer.write(dst);
		read.close();
		writer.close();
		System.out.println("耗时...:" + (System.currentTimeMillis() - start));
	}

}
