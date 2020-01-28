/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.inject.Inject;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import com.lrgoncalves.registroin.rotulagem.data.entity.Contact;
import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.mail.ManagerSendMailBean;
import com.lrgoncalves.registroin.rotulagem.mail.RotuloMailMessage;
import com.lrgoncalves.registroin.rotulagem.mail.exception.SendRotuloMailExcpetion;

/**
 * @author digitallam
 *
 */
public class ManagerReportBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006495284764146044L;

	private static final String ROTULO_PATH = "/tmp/registroIn/rotulos/"; 
	
	@Inject
	protected ManagerSendMailBean sendMail;
	
	
	public String saveReport(final String rotulo) throws IOException {
		
		String outputFile = ROTULO_PATH+rotulo+".pdf";
		OutputStream os = new FileOutputStream(outputFile);

		manipulatePdf(downloadHTML(rotulo).replace("/rotulagem/", "http://localhost/rotulagem/"), outputFile,
				PageSize.A4, PageSize.A4.getWidth());

		os.close();
		
		
		return outputFile;
	}
	
	
	private String downloadHTML(final String rotulo) throws IOException {
		
		String url = "http://localhost:8080/rotulagem/report-partner.jsf?rotulo=" + rotulo;
		
		URL u = new URL(url);

		URLConnection conn = u.openConnection();
		InputStream is = conn.getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer inputLine = new StringBuffer();

		while (in.ready())
			inputLine.append(in.readLine());

		in.close();
		is.close();
		
		return inputLine.toString();
		
	}
	
	
	private void manipulatePdf(String htmlSource, String pdfDest, PageSize pageSize, float screenWidth)
			throws IOException {

		PdfWriter writer = new PdfWriter(pdfDest);
		PdfDocument pdfDoc = new PdfDocument(writer);

		pdfDoc.setTagged();
		pdfDoc.setDefaultPageSize(pageSize);

		ConverterProperties converterProperties = new ConverterProperties();

		MediaDeviceDescription mediaDescription = new MediaDeviceDescription(MediaType.SCREEN);
		mediaDescription.setWidth(screenWidth);
		converterProperties.setMediaDeviceDescription(mediaDescription);

		FontProvider fp = new DefaultFontProvider();

		converterProperties.setFontProvider(fp);
		converterProperties.setCreateAcroForm(true);

		HtmlConverter.convertToPdf(htmlSource, pdfDoc, converterProperties);
		pdfDoc.close();
		
	}
	
	@Asynchronous
	public Future<Void> sendReportByEmail(final Rotulo rotulo) throws IOException, SendRotuloMailExcpetion{
		

		RotuloMailMessage message = new RotuloMailMessage();

		String clientName = "";
		
		if(rotulo.getClient().getContactList() == null)
			throw new IllegalArgumentException("Email list is empty !");
		
		String recipients[] = new String[rotulo.getClient().getContactList().size()];

		int index = 0;

		for (Contact contactMail : rotulo.getClient().getContactList()) {

			clientName = clientName.concat(contactMail.getName() + ", ");

			recipients[index] = contactMail.getEmail();
			index++;
		}
	
		message.setRecipientList(recipients);
		message.setAttachmentFilePath(saveReport(rotulo.getId()));
		message.setProductName(rotulo.getProduto());
		message.setClientName(clientName);
		sendMail.sendRotuloMail(message);
		
		return null;
	}

}
