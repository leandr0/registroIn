/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.mail;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Future;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lrgoncalves.registroin.rotulagem.mail.exception.SendRotuloMailExcpetion;

/**
 * @author digitallam
 *
 */
public class ManagerSendMailBean implements SendMailBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5000394900565933544L;


	private static final Log  LOGGER = LogFactory.getLog(ManagerSendMailBean.class);
	
	
	@Resource(name = "java:jboss/mail/RegistroIn")
    private Session session;	

	private MimeBodyPart buildContentMessageBody(RotuloMailMessage message) throws MessagingException {
		
		MimeBodyPart contentMessageBody = new MimeBodyPart();
		contentMessageBody.setHeader("Content-Type", "text/html");
		contentMessageBody.setContent(message.buildMessage(contentMessage), "text/html; charset=utf-8");

		return contentMessageBody;
	}
	
	private MimeBodyPart buildAttachmentMessageBody(RotuloMailMessage message) throws MessagingException, IOException {
		
		byte[] bytes = FileUtils.readFileToByteArray(new File(message.getAttachmentFilePath()));

		DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		
		MimeBodyPart attachmentMessageBody = new MimeBodyPart();
		attachmentMessageBody.setDataHandler(new DataHandler(dataSource));
		attachmentMessageBody.setFileName("RegistroIn-Rotulo-Alimentos-"+message.getProductName().replace(" ", "_")+".pdf");

		return attachmentMessageBody;
		
	}
	
	@Asynchronous
	@Override
	public Future<Void> sendRotuloMail(RotuloMailMessage message) throws SendRotuloMailExcpetion {
		

		try {

			InternetAddress[] recipientAddress = message.builRecipientList();

			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(buildContentMessageBody(message));
			mimeMultipart.addBodyPart(buildAttachmentMessageBody(message));
			

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setSender(new InternetAddress(session.getProperty("mail.smtp.user")));
			mimeMessage.setSubject(subject.concat(message.getProductName()));

			if (recipientAddress.length > 0)
				mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddress);

			mimeMessage.setContent(mimeMultipart, "text/html; charset=utf-8");
			mimeMessage.saveChanges();
			
			Transport.send(mimeMessage, mimeMessage.getAllRecipients());
			
			return null;
			
		} catch (Exception ex) {
			LOGGER.error("sendRotuloMail", ex.getCause());
			throw new SendRotuloMailExcpetion(ex.getMessage());
		} 
	}
}