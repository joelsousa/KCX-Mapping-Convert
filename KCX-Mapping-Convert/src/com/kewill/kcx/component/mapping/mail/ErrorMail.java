/*
 * Funktion    : ErrorMail.java
 * Titel       :
 * Erstellt    : 27.10.2006
 * Author      : CSF GmbH / schmidt
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: ErrorMail<br>
 * Erstellt		: 27.10.2006<br>
 * Beschreibung	: Mailen von Fehlern, die einen Alarm auslösen sollen.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class ErrorMail {
	private Logger logger = null;
    private Logger mailLogger = null;
    private File   anhang = null;
	
	public static void main(String[] args) {
	    Utils.log("(ErrorMail main) Enter");
        if (Config.configure("conf", "kcx.ini")) {
        	
        	ErrorMail errorMail = new ErrorMail();
//            errorMail.send(null, "Fehlertext 1", "Testdatei");
//            errorMail.send(new KcxMappingException("KcxMappingException Fehlertext2"), "Fehlertext 2", "Testdatei2", "inFile", "error/dir");
//            errorMail.send(new KcxMappingException("KcxMappingException Fehlertext3"), "Fehlertext 3");
        	try {
        	    throw new IOException("File not found.");
        	} catch (Exception e) {
        	    try {
                    throw new KcxMappingException("KcxMappingException Fehlertext4/5", e);
                } catch (Exception e1) {
                    errorMail.send(null, "Fehlertext 0", getMessage(), null);
                    errorMail.send(null, "Fehlertext 1", getMessage2(), null);
                    errorMail.send(e1, "Fehlertext 2", getMessage(), null);
                    errorMail.send(e1, "Fehlertext 3", getMessage2(), null);
                    errorMail.send(e1, "Fehlertext 4", getMessage(), "filename");
                    errorMail.send(e1, "Fehlertext 5", getMessage2(), "filename");
                }
        	}
            System.exit(0);
        } else {
            Utils.log("(ErrorMail main) Fehler bei Config.configure");
            System.exit(1);
        }

	}
	
	public ErrorMail() {
        mailLogger = Logger.getLogger("mail");
        mailLogger.setAdditivity(false);
	    logger = Logger.getLogger("ErrorMail");
	}
	
    public void send(Throwable throwable, String errorText, String fileName) {
        send(throwable, errorText, fileName, null, null);
    }
    
	public void send(Throwable throwable, String errorText, String fileName, String inFile, String errorDir) {
	    String logLine = "File: \"" + fileName + "\"";
		
        String text = Utils.LF + "Hello support team," + Utils.LF + 
                      Utils.LF +
                      "this is an automaticly generated mail." + Utils.LF + 
                      Utils.LF;
		if (!Utils.isStringEmpty(fileName)) {
            text = text + "An error occured while processing file \"" + fileName + "\"." + Utils.LF;
		}
		
		if (throwable != null) {
			text = text + Utils.LF;
			text = text + "Exception: " + throwable.toString() + Utils.LF;
			logLine = logLine + "; Exception: " + throwable.toString();
			
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            throwable.printStackTrace(new PrintStream(bos));
            String stackTrace = bos.toString();
            text = text + Utils.LF;
            text = text + "Stack Trace: " + stackTrace.toString() + Utils.LF;
		}
		
		if (errorText != null && !errorText.trim().equals("")) {
			text = text + Utils.LF;
			text = text + "Error text: " + errorText + Utils.LF;
            logLine = logLine + "; Error text: " + errorText;
		}

        if (errorDir != null && !errorDir.trim().equals("")) {
            text = text + Utils.LF;
            if (inFile != null && !inFile.trim().equals("")) {
                text = text + "The incoming file " + inFile + " was moved to " + errorDir + "." + Utils.LF;
                logLine = logLine + "; The incoming file " + inFile + " was moved to " + errorDir + ".";
            } else {
                text = text + "The incoming file was moved to " + errorDir + "." + Utils.LF;
                logLine = logLine + "; The incoming file was moved to " + errorDir + ".";
            }
        }

		text = text + Utils.LF;
//		text = text + "Bitte die Logs prüfen." + Utils.LF;
 
        SendMail errorMail = new SendMail();
        try {
            errorMail.send(Config.getMailHost(),
                           Config.getMailFrom(),
                           Config.getMailFromName(),
                           Config.getMailTo().split("[ \t]+"),
            			   Config.getMailCc().split("[ \t]+"),
                           Config.getMailBcc().split("[ \t]+"),
                           Config.getMailSubject(),
                           text,
            			   anhang);
          	  logger.info("Fehler-Mail zu Datei \"" + fileName + "\" erfolgreich gesendet.");
              mailLogger.info(logLine);
        } catch (MailException e) {
            logger.fatal("Fehler beim Mail-Versand:");
            e.printStackTrace();
        }

		
	}


    public void send(Throwable throwable, String errorText) {
        String logLine = "Error mail ";
        String text = Utils.LF + "Hello support team," + Utils.LF +
                        "this is an automaticly generated mail." + Utils.LF + 
                        Utils.LF +
                        "An error occured while processing a message." + Utils.LF;
        
        if (throwable != null) {
            text = text + Utils.LF;
            text = text + "Exception: " + throwable.toString() + Utils.LF;
            logLine = logLine + "; Exception: " + throwable.toString();
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            throwable.printStackTrace(new PrintStream(bos));
            String stackTrace = bos.toString();
            text = text + Utils.LF;
            text = text + "Stack Trace: " + stackTrace.toString() + Utils.LF;
        }
        
        if (errorText != null && !errorText.trim().equals("")) {
            text = text + Utils.LF;
            text = text + "Error text: " + errorText + Utils.LF;
            logLine = logLine + "; Error text: " + errorText;
        }

        text = text + Utils.LF;
//        text = text + "Bitte die Logs prüfen." + Utils.LF;
 
        SendMail errorMail = new SendMail();
        try {
            errorMail.send(Config.getMailHost(),
                           Config.getMailFrom(),
                           Config.getMailFromName(),
                           Config.getMailTo().split("[ \t]+"),
                           Config.getMailCc().split("[ \t]+"),
                           Config.getMailBcc().split("[ \t]+"),
                           Config.getMailSubject(),
                           text,
                           anhang);
              logger.info("Fehler-Mail erfolgreich gesendet.");
              mailLogger.info(logLine);
        } catch (MailException e) {
            logger.fatal("Fehler beim Mail-Versand:");
            e.printStackTrace();
        }
    }
    
    // MS20120227 Begin
    public void send(Throwable throwable, String errorText, String message, String fileName) {
        String    logLine    = "File: \"" + fileName + "\"";
        String    stackTrace = null;
        Throwable rootCause  = null;
        
        String text = Utils.LF + "Hello support team," + Utils.LF + 
                      Utils.LF +
                      "this is an automaticly generated mail." + Utils.LF + 
                      Utils.LF;
        if (!Utils.isStringEmpty(fileName)) {
            text = text + "An error occured while processing file \"" + fileName + "\"." + Utils.LF;
        }
        
        if (errorText != null && !errorText.trim().equals("")) {
            text = text + Utils.LF;
            text = text + "Error text: " + errorText + Utils.LF;
            logLine = logLine + "; Error text: " + errorText;
        }

        if (throwable != null) {
            text = text + Utils.LF;
            text = text + "Cause type: " + throwable.getClass().getName() + Utils.LF;
            text = text + "Cause message: " + throwable.getMessage() + Utils.LF;
            logLine = logLine + "; Exception: " + throwable.toString();
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            throwable.printStackTrace(new PrintStream(bos));
            stackTrace = bos.toString();
            Throwable cause  = throwable.getCause();
            while (cause != null) {
                rootCause  = cause;
                throwable.printStackTrace(new PrintStream(bos));
                cause  = cause.getCause();
            }
            if (rootCause != null) {
                text = text + Utils.LF;
                text = text + "Root cause type: " + rootCause.getClass().getName() + Utils.LF;
                text = text + "Root cause message: " + rootCause.getMessage() + Utils.LF;
            }
        }

        if (message != null) {
            text = text + Utils.LF;
            if (message.length() < 1000) {
                text = text + "Original message:" + Utils.LF + message.substring(0, message.length()) + Utils.LF;
            } else {
                text = text + "Original message (truncated):" + Utils.LF + message.substring(0, 1000) + Utils.LF;
            }
        }
        
        if (throwable != null) {
            text = text + Utils.LF;
            text = text + "Stack Trace: " + stackTrace.toString() + Utils.LF;
        }
        
        text = text + Utils.LF;
 
        SendMail errorMail = new SendMail();
        try {
            errorMail.send(Config.getMailHost(),
                           Config.getMailFrom(),
                           Config.getMailFromName(),
                           Config.getMailTo().split("[ \t]+"),
                           Config.getMailCc().split("[ \t]+"),
                           Config.getMailBcc().split("[ \t]+"),
                           Config.getMailSubject(),
                           text,
                           anhang);
              logger.info("Fehler-Mail zu Datei \"" + fileName + "\" erfolgreich gesendet.");
              mailLogger.info(logLine);
        } catch (MailException e) {
            logger.fatal("Fehler beim Mail-Versand:");
            e.printStackTrace();
        }
    }
    // MS20120227 End


//    public void send(Throwable throwable, String errorText, String fileName, String inFile, String errorDir) {
//        String logLine = "Datei: \"" + fileName + "\"";
//        String text = Utils.LF + "Hallo CSF-Support," + Utils.LF +
//                     "dies ist eine automatisch erzeugte Nachricht." + Utils.LF + 
//                     Utils.LF +
//                     "Beim Verarbeiten der Datei \"" + fileName + "\" ist ein schwerer Fehler aufgetreten!" + Utils.LF;
//        
//        if (throwable != null) {
//            text = text + Utils.LF;
//            text = text + "Exception: " + throwable.toString() + Utils.LF;
//            logLine = logLine + "; Exception: " + throwable.toString();
//            
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            throwable.printStackTrace(new PrintStream(bos));
//            String stackTrace = bos.toString();
//            text = text + Utils.LF;
//            text = text + "Stack Trace: " + stackTrace.toString() + Utils.LF;
//        }
//        
//        if (errorText != null && !errorText.trim().equals("")) {
//            text = text + Utils.LF;
//            text = text + "Fehlertext: " + errorText + Utils.LF;
//            logLine = logLine + "; Fehlertext: " + errorText;
//        }
//
//        if (errorDir != null && !errorDir.trim().equals("")) {
//            text = text + Utils.LF;
//            if (inFile != null && !inFile.trim().equals("")) {
//                text = text + "Die Eingangsdatei " + inFile + " wurde nach " + errorDir + " verschoben." + Utils.LF;
//                logLine = logLine + "; Die Eingangsdatei " + inFile + " wurde nach " + errorDir + " verschoben.";
//            } else {
//                text = text + "Die Eingangsdatei wurde nach " + errorDir + " verschoben." + Utils.LF;
//                logLine = logLine + "; Die Eingangsdatei wurde nach " + errorDir + " verschoben.";
//            }
//        }
//
//        text = text + Utils.LF;
//        text = text + "Bitte die Logs prüfen." + Utils.LF;
// 
//        SendMail errorMail = new SendMail();
//        try {
//            errorMail.send(Config.getMailHost(),
//                           Config.getMailFrom(),
//                           Config.getMailFromName(),
//                           Config.getMailTo().split("[ \t]+"),
//                           Config.getMailCc().split("[ \t]+"),
//                           Config.getMailBcc().split("[ \t]+"),
//                           Config.getMailSubject(),
//                           text,
//                           anhang);
//              logger.info("Fehler-Mail zu Datei \"" + fileName + "\" erfolgreich gesendet.");
//              mailLogger.info(logLine);
//        } catch (MailException e) {
//            logger.fatal("Fehler beim Mail-Versand:");
//            e.printStackTrace();
//        }
//
//        
//    }
//
//
//    public void send(Throwable throwable, String errorText) {
//        String logLine = "Fehler-Mail ";
//        String text = Utils.LF + "Hallo CSF-Support," + Utils.LF +
//                     "dies ist eine automatisch erzeugte Nachricht." + Utils.LF + 
//                     Utils.LF +
//                     "Beim der Verarbeitung ist ein schwerer Fehler aufgetreten!" + Utils.LF;
//        
//        if (throwable != null) {
//            text = text + Utils.LF;
//            text = text + "Exception: " + throwable.toString() + Utils.LF;
//            logLine = logLine + "; Exception: " + throwable.toString();
//        }
//        
//        if (errorText != null && !errorText.trim().equals("")) {
//            text = text + Utils.LF;
//            text = text + "Fehlertext: " + errorText + Utils.LF;
//            logLine = logLine + "; Fehlertext: " + errorText;
//        }
//
//        text = text + Utils.LF;
//        text = text + "Bitte die Logs prï¿½fen." + Utils.LF;
// 
//        SendMail errorMail = new SendMail();
//        try {
//            errorMail.send(Config.getMailHost(),
//                           Config.getMailFrom(),
//                           Config.getMailFromName(),
//                           Config.getMailTo().split("[ \t]+"),
//                           Config.getMailCc().split("[ \t]+"),
//                           Config.getMailBcc().split("[ \t]+"),
//                           Config.getMailSubject(),
//                           text,
//                           anhang);
//              logger.info("Fehler-Mail erfolgreich gesendet.");
//              mailLogger.info(logLine);
//        } catch (MailException e) {
//            logger.fatal("Fehler beim Mail-Versand:");
//            e.printStackTrace();
//        }
//    }

    private static String getMessage() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<Result><Header xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<SentAt><Date><Year>2012</Year><Month>2</Month><Day>14</Day></Date><Time>142030</Time><TimeZone>+00:00</TimeZone></SentAt>" +
                "<Transmitter>DE.KEX.TST</Transmitter><Receiver>DE.KEX.TST</Receiver>" +
                "<MessageTP><CountryCode>GB</CountryCode><Procedure>Export</Procedure><MessageName>Confirmation</MessageName></MessageTP>" +
                "<MessageID>1981</MessageID></Header>" +
                "<Confirmation xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<GoodsDeclaration><KindOfAnswer>0</KindOfAnswer><UCRNumber>12GB02X32684156014</UCRNumber>" +
                "<StatusOfCompletion>H </StatusOfCompletion><StatusOfCompletion2>1</StatusOfCompletion2>" +
                "<StatusOfCompletion3>A1</StatusOfCompletion3><ReferenceNumber>2GB209571166000-3-KCXT-14</ReferenceNumber>" +
                "<PDFInformation><Name>ESS_Copy_3-22939-20120214-1419.pdf</Name>" +
                "</PDFInformation></GoodsDeclaration></Confirmation></Result>";
        return xml;
    }

    private static String getMessage2() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<Result><Header xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<SentAt><Date><Year>2012</Year><Month>2</Month><Day>14</Day></Date><Time>142030</Time><TimeZone>+00:00</TimeZone></SentAt>" +
                "<Transmitter>DE.KEX.TST</Transmitter><Receiver>DE.KEX.TST</Receiver>" +
                "<MessageTP><CountryCode>GB</CountryCode><Procedure>Export</Procedure><MessageName>Confirmation</MessageName></MessageTP>" +
                "<MessageID>1981</MessageID></Header>" +
                "<Confirmation xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<GoodsDeclaration><KindOfAnswer>0</KindOfAnswer><UCRNumber>12GB02X32684156014</UCRNumber>" +
                "<StatusOfCompletion>H </StatusOfCompletion><ReferenceNumber>2GB209571166000-3-KCXT-14</ReferenceNumber>" +
                "<PDFInformation><Name>ESS_Copy_3-22939-20120214-1419.pdf</Name>" +
                "</PDFInformation></GoodsDeclaration></Confirmation></Result>";
        return xml;
    }

}
