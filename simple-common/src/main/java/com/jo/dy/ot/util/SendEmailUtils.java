package com.jo.dy.ot.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

public class SendEmailUtils {

	private static Logger logger = Logger.getLogger(SendEmailUtils.class);

	private static String from;

	private static String host;
	private static String password;

	private static Session session;

	static {
		Properties prop = new Properties();
		try {
			// SendEmailUtils.class.getClassLoader().getSystemResourceAsStream("email.properties");
			prop.load(SendEmailUtils.class.getClassLoader().getResourceAsStream("email.properties"));
			from = prop.getProperty("from");
			password = prop.getProperty("password");
			host = prop.getProperty("host");
			prop.setProperty("mail.smtp.host", host);
			prop.put("mail.smtp.auth", "true");

			session = Session.getDefaultInstance(prop, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password); // 发件人邮件用户名、密码
				}
			});
		} catch (Exception e) {
			logger.error("加载email.properties文件出错!");
		}

	}

	public static Session getSession() {
		if (session == null) {
			throw new RuntimeException("Session获取异常!");
		}
		return session;
	}

	public static MimeMessage getMimeMessage() {
		return new MimeMessage(SendEmailUtils.getSession());
	}

	public static void send(MimeMessage message) throws Exception {
		message.setFrom(new InternetAddress(from));
		Transport.send(message);
		logger.info("邮件发送成功!");
	}

	public static void send(MimeMessage message, String... address) throws Exception {
		send(message, RecipientType.TO, address);
	}

	public static void send(MimeMessage message, RecipientType recipientType, String... address) throws Exception {
		if (ArrayUtils.isEmpty(address)) {
			logger.info("收件者为空!");
			return;
		}
		List<InternetAddress> addresses = new ArrayList<>();
		for (String add : address) {
			addresses.add(new InternetAddress(add));
		}
		// 发送给谁
		message.addRecipients(recipientType, addresses.toArray(new InternetAddress[address.length]));
		send(message);
	}

	public static void send(MimeMessage message, List<String> address) throws Exception {
		send(message, RecipientType.TO, address);
	}

	public static void send(MimeMessage message, RecipientType recipientType, List<String> address) throws Exception {
		if (CollectionUtils.isEmpty(address)) {
			logger.info("收件者为空!");
			return;
		}
		List<InternetAddress> addresses = new ArrayList<>();
		for (String add : address) {
			addresses.add(new InternetAddress(add));
		}
		// 发送给谁
		message.addRecipients(recipientType, addresses.toArray(new InternetAddress[address.size()]));
		send(message);

	}

	private static void sendTextDemo() throws Exception {
		MimeMessage message = SendEmailUtils.getMimeMessage();
		// 标题
		message.setSubject("仅发送文字!");
		message.setText("内容....");
		SendEmailUtils.send(message, "903690574@qq.com");

	}

	private static void sendHtmlDemo() throws Exception {
		MimeMessage message = SendEmailUtils.getMimeMessage();
		// 标题
		message.setSubject("仅发送文字!", "utf-8");
		message.setContent("<h1 style='color:red'>中文呀</h1>", "text/html;charset=utf-8");

		SendEmailUtils.send(message, "903690574@qq.com", "1040813950@qq.com");
	}

	private static void sendFileDemo() throws Exception {
		MimeMessage message = SendEmailUtils.getMimeMessage();
		// 创建多重消息
		Multipart multipart = new MimeMultipart();
		// 创建消息部分
		BodyPart messageBodyPart = new MimeBodyPart();

		// 消息
		messageBodyPart.setText("这个是内容");
		// 设置文本消息部分
		multipart.addBodyPart(messageBodyPart);

		// 附件部分
		messageBodyPart = new MimeBodyPart();
		String filename = "file.txt";
		DataSource source = new FileDataSource(filename);
		
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);
		// 发送完整消息
		message.setContent(multipart);
		SendEmailUtils.send(message, "903690574@qq.com,","1005449628@qq.com");
	}

	public static void main(String[] args) throws Exception {
//		sendTextDemo();
//		sendHtmlDemo();
//		sendFileDemo();
	}

}