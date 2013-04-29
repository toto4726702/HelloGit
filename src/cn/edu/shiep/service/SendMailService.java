package cn.edu.shiep.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailService {
	
	String SMTPHost = "169.254.192.223";
	String user = "";
	String password = "";
	String from = "";
	String to = "";
	String subject = "";
	String content = "";

	public void sendMail(String username){
		Properties props = new Properties();
		
		props.put("mail.smtp.host", SMTPHost);
		
		props.put("mail.smtp.auth", "true");
		
		try{
			
			SmtpAuth auth = new SmtpAuth();
			auth.setUserInfo(user, password);
			
			Session mailSession = Session.getDefaultInstance(props,auth);
			
			mailSession.setDebug(true);
			
			Message message = new MimeMessage(mailSession);
			
			message.setFrom(new InternetAddress(from));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(subject);
			message.setText(content);
			
			message.setSentDate(new Date());
			
			
			message.setHeader("X-Priority", "1");
			
			message.saveChanges();
			
			Transport transport = mailSession.getTransport("smtp");
			
			
			transport.connect(SMTPHost, user,password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		
			}catch(Exception x){
			x.printStackTrace();
		}
	}
	
}
