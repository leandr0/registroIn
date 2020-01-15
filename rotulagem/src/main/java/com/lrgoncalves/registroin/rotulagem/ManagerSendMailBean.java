/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;

/**
 * @author digitallam
 *
 */
public class ManagerSendMailBean implements SendMailBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7961099230230203947L;

	private static Properties mailProperties = null;

	private static Session mailSession;
	
	private static Transport mailTransport;

	private Session getSession() {

		if (mailProperties == null) {
			mailProperties = new Properties();
			mailProperties.put("mail.transport.protocol", protocol);
			mailProperties.put("mail.smtp.host", host);
			mailProperties.put("mail.smtp.auth", "false");
			mailProperties.put("mail.smtp.port", port);
		}

		if(mailSession == null) {
			mailSession = Session.getDefaultInstance(mailProperties, null);
			mailSession.setDebug(false);
		}
		
		return mailSession;
	}
	
	private Transport getTransport() throws MessagingException {
		
		if(mailTransport == null) {
			mailTransport = getSession().getTransport(protocol);
			mailTransport.connect(host, username, passwd);
		}
		return mailTransport;
	}

	public  void email(final String pdfFilePath, final String clientName, final String productName) {

		String[] recipientList = null;// emailTo.split(",");

		ByteArrayOutputStream outputStream = null;

		try {

			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];

			int counter = 0;

			for (String recip : recipientList) {
				recipientAddress[counter] = new InternetAddress(recip.trim());
				counter++;
			}

			MimeBodyPart contentMessageBody = new MimeBodyPart();
			contentMessageBody.setHeader("Content-Type", "text/html");
			contentMessageBody.setContent(contentMessage, "text/html; charset=utf-8");

			outputStream = new ByteArrayOutputStream();

			byte[] bytes = FileUtils.readFileToByteArray(new File(pdfFilePath));

			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("RegitroIn-Rotulo-.pdf");


			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(contentMessageBody);
			mimeMultipart.addBodyPart(pdfBodyPart);
			
			InternetAddress iaSender = new InternetAddress("");

			MimeMessage mimeMessage = new MimeMessage(getSession());
			mimeMessage.setSender(iaSender);
			mimeMessage.setSubject(subject.concat(productName));

			if (recipientAddress.length > 0)
				mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddress);

			mimeMessage.setContent(mimeMultipart, "text/html; charset=utf-8");
			mimeMessage.saveChanges();

			getTransport().sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
			mailTransport.close();
			}catch (Exception e) {}
			
			if (null != outputStream) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception ex) {
				}
			}
		}
	}

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
	
	
	@Override
	public void sendRotuloMail(RotuloMailMessage message) throws SendRotuloMailExcpetion {
		

		try {

			InternetAddress[] recipientAddress = message.builRecipientList();

			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(buildContentMessageBody(message));
			mimeMultipart.addBodyPart(buildAttachmentMessageBody(message));
			

			MimeMessage mimeMessage = new MimeMessage(getSession());
			mimeMessage.setSender(new InternetAddress(message.getEmailSenderAddress()));
			mimeMessage.setSubject(subject.concat(message.getProductName()));

			if (recipientAddress.length > 0)
				mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddress);

			mimeMessage.setContent(mimeMultipart, "text/html; charset=utf-8");
			mimeMessage.saveChanges();

			getTransport().sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
}