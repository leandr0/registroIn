/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author digitallam
 *
 */
public class SendMail {

	static String emailDestinatario = "vanessa@registroin.com,leandro1604@gmail.com";
	static String nomeDestinatario = "Nome Destinatario";
	static String emailRemetente = "consultoria@registroin.com.br";
	static String nomeRemetente = "Nome Remetente";
	static String assunto = "RegistroIn - email retulagem";
	static String body = "Tipo assim ... ";

	static String protocolo = "smtp";
	static String servidor = "email-ssl.com.br";// "smtplw.com.br"; // do painel de controle do SMTP
	static String username = "leandro@registroin.com.br"; // do painel de controle do SMTP
	static String senha = "Le@ndr#2019"; // do painel de controle do SMTP
	static String porta = "587"; // do painel de controle do SMTP

	public static void main(String[] args) {
		
		
		String[] recipientList = emailDestinatario.split(",");
		
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", protocolo);
		props.put("mail.smtp.host", servidor);
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.port", porta);

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(false);

		try {
			InternetAddress iaFrom = new InternetAddress(emailRemetente, nomeRemetente);
			
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient : recipientList) {
			    recipientAddress[counter] = new InternetAddress(recipient.trim());
			    counter++;
			}
			
			//InternetAddress[] iaTo = new InternetAddress[1];
			InternetAddress[] iaReplyTo = new InternetAddress[1];

			iaReplyTo[0] = new InternetAddress(emailDestinatario, nomeDestinatario);
			//iaTo[0] = new InternetAddress(emailDestinatario, nomeDestinatario);

			MimeMessage msg = new MimeMessage(session);

			if (iaReplyTo != null)
				msg.setReplyTo(iaReplyTo);
			if (iaFrom != null)
				msg.setFrom(iaFrom);
			if (recipientAddress.length > 0)
				msg.setRecipients(Message.RecipientType.TO, recipientAddress);
			msg.setSubject(assunto);
			msg.setSentDate(new Date());

			msg.setContent(body, "text/html");

			Transport tr = session.getTransport(protocolo);
			tr.connect(servidor, username, senha);

			msg.saveChanges();

			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}