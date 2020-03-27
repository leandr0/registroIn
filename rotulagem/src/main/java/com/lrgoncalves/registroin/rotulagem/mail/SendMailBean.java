/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.mail;

import java.io.Serializable;

import com.lrgoncalves.registroin.rotulagem.mail.exception.SendRotuloMailExcpetion;

/**
 * @author digitallam
 *
 */
public interface SendMailBean extends Serializable {
	
	final static String subject = "RegistroIn - Análise de Rótulo - ";
	final static String contentMessage =  "<!DOCTYPE html>\n" + 
			"<html>\n" + 
			"<head>\n" + 
			"<style>\n" + 
			"body {\n" + 
			"	margin-left: 1;\n" + 
			"	padding: 0;\n" + 
			"	text-align: justify;\n" + 
			"	font-family: Verdana, sans-serif;\n" + 
			"    vertical-align: middle;\n" + 
			"	font-size: 10px;\n" + 
			"}\n" + 
			"table, th, td {\n" + 
			"  border: none;\n" + 
			"  font-family: Verdana, sans-serif;\n" + 
			"  vertical-align: middle;\n" + 
			"  font-size: 10px;\n" + 
			"  align:\"center\";\n" + 
			"}\n" + 
			"\n" + 
			"a:link, a:visited {\n" + 
			"  background-color:none;\n" + 
			"  color: blue;\n" + 
			"  text-align: justify;\n" + 
			"  vertical-align: middle;\n" + 
			"  text-decoration: none;\n" + 
			"  display: inline-block;\n" + 
			"}\n" + 
			"\n" + 
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"<p> Prezad@(s) $cliente</p>\n" + 
			"\n" + 
			"<p>Anexo análise de rótulo para o produto \"$produto\".</p>\n" + 
			"\n" + 
			"<p>Obrigada pela confiança,</p>\n" + 
			"\n" + 
			"<strong>Vanessa Tacchi</strong>\n" + 
			"   \n" + 
			"<table >\n" + 
			"	<tbody>\n" + 
			"		<tr >\n" + 
			"			<td  style=\"padding-right:5px;\" rowspan=\"4\" align=\"center\" >\n" + 
			"              		<img src=\"http://www.registroin.com.br/img/LOGO.png\" width=\"50\" height=\"40\" />\n" + 
			"          </td>\n" + 
			"		</tr>\n" + 
			"      <tr valign=\"middle\">\n" + 
			"		<td  align=\"center\">\n" + 
			" 				<img src=\"https://www.freepnglogos.com/uploads/logo-telepon-png/telephone-icon-download-28.png\" width=\"10\" height=\"10\" />      \n" + 
			"        </td >\n" + 
			"        <td valign=\"center\" style=\"font-family:Arial\">\n" + 
			"        	(55 11) 96450-0234 \n" + 
			"        </td>\n" + 
			"       </tr>\n" + 
			"       <tr valign=\"middle\">\n" + 
			"       		<td colspan=\"2\" style=\"font-size: 8px;\">\n" + 
			"            	<a href=\"http://www.registroin.com.br\">http://www.registroin.com.br\n" + 
			"         </a>\n" + 
			"            </td>\n" + 
			"       </tr>\n" + 
			"       \n" + 
			"       <tr>\n" + 
			"        <td align=\"center\" colspan=\"2\">\n" + 
			"        		\n" + 
			"        	  <a href=\"http://www.registroin.com.br\">\n" + 
			"              	<img src=\" https://www.freepnglogos.com/uploads/logo-internet-png/logo-internet-chemiphase-updated-website-goes-live-chemiphase-ltd-12.png\" width=\"10\" height=\"10\"/>\n" + 
			"              </a>  		\n" + 
			"      \n" + 
			"        	<a href=\"mailto:vanessa@registroin.com.br?subject=Contato\">\n" + 
			"        		<img src=\"https://www.freepnglogos.com/uploads/email-logo-png-20.png\" width=\"10\" height=\"10\"/>			  \n" + 
			"            </a>\n" + 
			"\n" + 
			"         <a href=\"https://www.linkedin.com/company/registro-in/\" >\n" + 
			"        	<img src=\"https://www.freepnglogos.com/uploads/linkedin-basic-round-social-logo-png-13.png\"width=\"10\" height=\"10\"/>\n" + 
			"         </a>   \n" + 
			"        </td>\n" + 
			"     </tr>\n" + 
			"</tbody>\n" + 
			"</table>\n" + 
			"\n" + 
			"</body>\n" + 
			"</html>\n" + 
			"";
	
	public void sendRotuloMail(RotuloMailMessage message) throws SendRotuloMailExcpetion;
	
}
