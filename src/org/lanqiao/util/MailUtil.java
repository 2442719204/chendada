package org.lanqiao.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailUtil {
	static final String SMTPHOST="smtp.163.com";
	static final String AUTHPASSWORD="cjw1560500";//授权码
	static final String FROM = "15605006753@163.com";//发送邮件的帐号公司固定帐号
	public static void sendMail(String mailAddress,String subject,String content){
		//建立与邮件服务器的会话---session
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", SMTPHOST);//设置邮件服务器
		props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件的协议
		props.setProperty("mail.smtp.auth", "true");//设置验证
		//建立邮件服务器会话；---Session
		Session session = Session.getDefaultInstance(props);
		//2、创建一封邮件；---MimeMessage
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(FROM,"按键","utf-8"));
			message.setSubject("这是一封激活注册账号的邮件");
			message.setSentDate(new Date());//设置发送日期；
			message.setContent(content,"text/html;charset=utf-8");
			//设置收件人
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailAddress, "哈哈哈", "utf-8"));//发件
//			message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("2442719204@qq.com", "呼呼呼", "utf-8"));//抄送
			//message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("952208659@qq.com", "yct", "utf-8"));//密送
			//3、发送邮件 -- Transport
			Transport transport = session.getTransport();
			transport.connect(FROM,AUTHPASSWORD);//通过授权码以发送账号身份链接
			transport.sendMessage(message, message.getAllRecipients());//发送邮件
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static void sendMail(String subject,String content,String...MailAddress){
		
	}
}

