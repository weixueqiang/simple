package me.gacl.main;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail2 {
	public static void main(String[] args){
		for (int i = 0; i < 10; i++) {
			// 收件人电子邮箱
			// String to = "1040813950@qq.com";
			 String to = "903690574@qq.com";
			
//			String to = "1005449628@qq.com";
			
			// 发件人电子邮箱
			String from = "1040813950@qq.com";
			
			// 指定发送邮件的主机为 smtp.qq.com
			String host = "smtp.qq.com"; // QQ 邮件服务器
			
			// 获取系统属性
			Properties properties = System.getProperties();
			
			// 设置邮件服务器
			properties.setProperty("mail.smtp.host", host);
			
			properties.put("mail.smtp.auth", "true");
			// 获取默认session对象
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("1040813950@qq.com", "nqpkgwazgawybefa"); // 发件人邮件用户名、密码
				}
			});
			
			try {
				// 创建默认的 MimeMessage 对象
				MimeMessage message = new MimeMessage(session);
				
				// Set From: 头部头字段
				message.setFrom(new InternetAddress(from));
				
				// Set To: 头部头字段
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				
				// Set Subject: 头部头字段
				message.setSubject("发送测试");
				
				// 设置消息体
				message.setText("I`m kangkang,are you sb!");
				
				// 发送消息
				Transport.send(message);
				
				System.out.println("Sent message successfully....from runoob.com");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
	}
}