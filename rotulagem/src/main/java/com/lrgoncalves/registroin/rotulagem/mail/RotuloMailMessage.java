/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.mail;

import java.io.Serializable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author digitallam
 *
 */
public class RotuloMailMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4113497218499778501L;

	private String[] recipientList;
	
	private String subject;

	private String attachmentFilePath;
	
	private String productName;
	
	private String clientName;
	
	public String buildMessage(String contentMessage) {
		return contentMessage.replace("$cliente", this.clientName)
							.replace("$produto", this.productName);
	}
	
	public InternetAddress[] builRecipientList() throws AddressException {
		
		InternetAddress[] recipientAddress = new InternetAddress[this.getRecipientList().length];

		int counter = 0;

		for (String recip : this.getRecipientList()) {
			recipientAddress[counter] = new InternetAddress(recip.trim());
			counter++;
		}
		
		return recipientAddress;
	}

	public String[] getRecipientList() {
		return recipientList;
	}

	public void setRecipientList(String... recipientList) {
		this.recipientList = recipientList;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}

	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}