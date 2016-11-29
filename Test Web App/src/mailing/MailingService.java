package mailing;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailingService {
	

	public void sendMail(String fromAddr, String toAddr, String replyToAddr, String ccAddr, String messageContent)
	{
		//validating fromAddr and msg length 
		if(!isValidEmail(fromAddr) || messageContent.isEmpty())
		{
			System.out.println("message length="+messageContent.length());
			return;
		}
		
		Properties props 		= new Properties();
		Session session 		= Session.getDefaultInstance(props);
		MimeMessage message		= new MimeMessage(session);

		try
		{
			//setting addresses,subject,..
			String to = toAddr != null ? toAddr : "pandiyanp14@gmail.com";
			String subject = "TEST MAIL";
			message.setFrom( new InternetAddress(fromAddr,"pandiyan","UTF-8") );
			message.setRecipient( Message.RecipientType.TO, new InternetAddress(to) );
			message.setSubject( subject , "utf-8" );
			
			//setting content type and msg
			Multipart multipart			= new MimeMultipart("alternative");
			MimeBodyPart messagePart	= new MimeBodyPart();
			messagePart.setContent(messageContent, "text/html");
			multipart.addBodyPart(messagePart);
			message.setContent(multipart);
			
			//sending mail
			Transport.send(message);
			System.out.println("Email Sent");
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
		}
		
	}
	
	public boolean isValidEmail(String mailAddr)
	{
		boolean status = false;
		
		if(mailAddr == null || mailAddr.isEmpty())
		{
			System.out.println("To address is EMPTY. Unable to send email from="+mailAddr);
			return status;
		}
		
		String mail[] = mailAddr.split("@");
		if(mail.length == 2 && mail[0] != null && !mail[0].trim().equals("") && mail[1] != null && !mail[1].trim().equals("") && mail[1].contains("."))
		{
			status = true;
			System.out.println("To address is VALID. Gonna to send email from="+mailAddr);
		}
		else
		{
			System.out.println("To address is INVALID. Unable to send email from="+mailAddr);
		}
		
		return status;
	}
	
	
	public static void main(String args[])
	{
		//MailingService ms = new MailingService();
		//ms.sendMail("pandiyan.selvam@a-cti.com", null, null, null, "hi this is test mail");
	}
	
}
