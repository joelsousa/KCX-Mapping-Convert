/*
 * Funktion    : ErrorMail.java
 * Titel       :
 * Erstellt    : 01.02.2006
 * Author      : CSF GmbH / kron
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rï¿½ckgabe    : keine
 * Aufruf      : 
 *
 * ï¿½nderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.mail;

 
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul        : ZabisMail<br>
 * Erstellt     : 29.10.2006<br>
 * Beschreibung : Modul zum Versenden von Mails mit und ohne Anhang.<br>
 * 
 * @author  schmidt
 * @version 1.0.00
 */
public class SendMail {

    /**
     * Bevor man Mails verschicken kann braucht man eine Mail-Session.
     */
	private Session 			session;
    
    /**
     * Die zu sendende Mail-Message.
     */
	private MimeMessage         message;
    
	private BodyPart 			msgBodyPart;
	
	private String				transport = "mail.smtp.host";
	
	public static void main(String[] args) throws Exception {
		String iniPath = "conf";

		if (args.length > 0) {
			iniPath = args[0];
		}

		Utils.log("iniPath = " + iniPath);
		if (Config.configure(iniPath, "compliance.ini")) {
            SendMail errorMail = new SendMail();
            File anhang = new File("c:/temp/MailAnhang.txt");

            errorMail.send("mailto.btx.dtag.de",
                           "mschmidt2@t-online.de",
                           "Compliance",
                           new String[] {"mschmidt2@t-online.de"}, 
                           null, 
                           null, 
                           "test-subject",
                           "\nHallo CSF-Support,\n\ndies ist eine von Compliance automatisch erzeugte Nachricht\n"
                         + "mit einem Fehlerreport im Anhang!\n\n",
                           anhang);
        }
	}

	public void send(String host,
                     String from, 
                     String fromName,
                     String[] recipients,
					 String[] recipientsCc,  
                     String[] recipientsBcc,  
	  				 String subject, 
	  				 String text, 
	  				 File anhang) throws MailException {
		  
          // ***???*** besser Exception werfen so dass aufrufendes Programm 
          // 1. weiss dass die mail nicht gesendet wurde und
          // 2. der Fehlertext übergeben wird!?
          
          if (from == null || from.trim().equals(""))  {
              throw new MailException("Mail nicht gesendet da Absender nicht gesetzt!");
          }
		  if (recipients == null || recipients[0].trim().equals(""))  {
			  throw new MailException("Mail nicht gesendet da Empfänger nicht gesetzt!");
		  }
		  if (subject == null || subject.trim().equals(""))  {
              throw new MailException("Mail nicht gesendet da Betreff nicht gesetzt!");
		  }
		  if (text == null || text.trim().equals(""))  {
              throw new MailException("Mail nicht gesendet da kein Nachrichtentext gesetzt!");
		  }
		  if (host == null) {
              throw new MailException("Mail nicht gesendet da mail.host nicht gesetzt!");
		  }
		  
	  	try {

	  		// Zeichensatz definieren
			System.setProperty("file.encoding", "ISO-8859-1");
			
			// Mail Transport und Server muss in compliance.ini definiert sein
			// z.B.: props.put( "mail.smtp.host", "mail01" );
            Properties props = new Properties();
			props.put(transport, host);

			session = Session.getDefaultInstance(props);
		    if (Config.getMailDebug()) {
		    	session.setDebug(true);
		    }

		    message = new MimeMessage(session);
			
			message.setSentDate(new Date());
			
			message.setFrom(new InternetAddress(from, fromName));
            for (String recipient : recipients) {
                Utils.log("recipient = >" + recipient + "<");
                if (!recipient.trim().equals("")) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.trim()));
                }
            }

            for (String recipient : recipientsCc) {
                Utils.log("recipientCc = >" + recipient + "<");
                if (!recipient.trim().equals("")) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient.trim()));
                }
            }
            for (String recipient : recipientsBcc) {
                Utils.log("recipientBcc = >" + recipient + "<");
                if (!recipient.trim().equals("")) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(recipient.trim()));
                }
            }
		    
			message.setSubject(subject);
			
			// Mailbody(part1) und Attachment(part2) definieren		    
			Multipart multipart = new MimeMultipart();
						
			msgBodyPart = new MimeBodyPart();
			msgBodyPart.setText(text);
			multipart.addBodyPart(msgBodyPart);
			
		    if (anhang != null) {
		    	msgBodyPart = new MimeBodyPart();
		    	DataSource source = new FileDataSource(anhang);
		    	msgBodyPart.setDataHandler(new DataHandler(source));
		    	// 	Attachment Name wird hier "neu" definiert
		    	msgBodyPart.setFileName(anhang.getName());
		    	// 	hinzufuegen des zweiten mailparts
		    	multipart.addBodyPart(msgBodyPart);
		    }		    
			
			message.setContent(multipart);
			
			Transport.send(message);
			
	  	} catch (Exception e) {
			e.printStackTrace();
            throw new MailException(e.getMessage());
		} 
	  	
	  }
}
