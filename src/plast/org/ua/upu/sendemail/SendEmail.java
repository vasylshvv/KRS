package plast.org.ua.upu.sendemail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	 private String username;
	    private String password;
	    private Properties props;
	 
	    public SendEmail(String username, String password) {
	        this.username = username;
	        this.password = password;
	 
	        props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    }
	 
	    public void send(String subject, String text, String fromEmail, String toEmail) throws UnsupportedEncodingException{
	        Session session = Session.getDefaultInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });
	 
	        try {
	        	MimeMessage message = new MimeMessage(session);
	        	
	        	String encodedSubject = new String (subject.getBytes("UTF-8"),"UTF-8");
	           
	            //от кого
	            message.setFrom(new InternetAddress(fromEmail));
	            
	            //кому
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
	            //тема сообщения
	            message.setHeader("Content-Type", "text/html; charset=UTF-8");
	            message.setSubject(encodedSubject, "UTF-8");
	            
	                  
	            
	            //текст
	           // message.setText(text);
	            
	            message.setContent(text, "text/html; charset=UTF-8");
	 
//	          
//	            String encodedSubject = new String (subject.getBytes("UTF-8"),"UTF-8");
//	            String encodedBody = new String (body.getBytes("UTF-8"),"UTF-8");
//	            message.setSubject(encodedSubject, "UTF-8");
//	            message.setText(encodedBody, "UTF-8");
	            
	            
	            
	            //отправляем сообщение
	            Transport.send(message);
	            System.out.println("SEND EMAIl");
	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
