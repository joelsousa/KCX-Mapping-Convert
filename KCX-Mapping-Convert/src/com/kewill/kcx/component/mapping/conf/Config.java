/*
 * Funktion    : Config.java
 * Titel       :
 * Erstellt    : 30.09.2008
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      : Christine Kron
 * Datum       : 17.06.2009
 * Kennzeichen :
 * Beschreibung: pdfdir aufgenommen
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.conf;

import org.apache.log4j.Logger;

import com.kewill.kcx.component.mapping.util.Prop;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: Config<br>
 * Erstellt		: 18.12.2008<br>
 * Beschreibung	: Manage configuration data. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class Config {
    private static Logger   logger          = null;           // Logger

    private static boolean  configured      = false;          // Konfiguration eingelesen?
    
    private static String   iniPath         = "conf";         // Pfad zur INI-Datei
    private static String   iniFile         = "kcx.ini";      // Name der INI-Datei
    private static boolean  loaded          = false;          // Konfiguration eingelesen?
    
    // Parameter aus kcx.ini
    private static boolean  showConfig      = false;           // Konfiguration anzeigen?
    private static boolean  wrapMessages    = true;            // Nachrichten in einen KIDS-HEader einpacken 
                                                               // und ohne Konvertierung durchrouten

    private static boolean  debug           = true;            // Debug-Schalter
    private static int      logLevel        = 6;               // Log-Level
    private static String   logConfig       = "log.config";    // Log-Konfigurationsdatei
    private static boolean  logXML          = true;            // Konvertierte Daten ausgeben
    private static String   errorLog        = "log/error.log"; // Name der Error-Log-Datei
  
    private static boolean  mailSendFlag    = true;             // Sollen Fehlermails gesendet werden?
    private static String   mailHost        = "mail01";         // Mail-Host                    
    private static String   mailFrom        = "kcx@kewill.de";  // Absender-Adresse
    private static String   mailFromName    = "KCX";            // Name des Absenders
    private static String   mailSubject     = null;             // Subject für Fehlermails
    private static String   mailTo          = "";               // Empfänger-Adressen
    private static String   mailCc          = "";               // CC-Adressen
    private static String   mailBcc         = "";               // BCC-Adressen
    private static boolean  mailDebug       = true;             // Mail-API Debug-Ausgaben?

    private static String   dbUser          = null;         // Datenbank-User
    private static String   dbPasswd        = null;         // Datenbank-User-Passwort
    private static String   dbConnect       = null;         // Datenbank-Connectstring
    private static String   dbPrefetchSize  = null;         // Datenbank-Prefetchgröße
    private static boolean  dbSetQueryTimeout = false;      // Soll ein Timeout für Datenbankstatements gesetzt werden?
    private static int      dbQueryTimeout  = 0;            // Timeout für Datenbankstatements in Sekunden
    
    private static String   pdfpath			= null;         // Absoluter Pfad für temporäre PDFs
    
    private static boolean  writeOutFiles   = true;         // Ausgabedateien uf Platte schreiben?
    private static String   countryOutPath  = null;         // Absoluter Pfad für Ausgangsdateien Richtung Land
    private static String   customerOutPath = null;         // Absoluter Pfad für Ausgangsdateien Richtung Kunde
    private static boolean  writeInFiles    = true;         // Ausgabedateien uf Platte schreiben?
    private static String   countryInPath  = null;          // Absoluter Pfad für Eingangsdateien aus Richtung Land
    private static String   customerInPath = null;          // Absoluter Pfad für Eingangsdateien aus Richtung Kunde
    
    private static boolean  auditEnabled    = true;         // Sollen Auditeinträge geschrieben werden.
    
    private static int      reodays     	= 30;			// Zum Löschen declnums die älter sind
    
    private static boolean  createOutName   = false;        // Create new (file) name for outgoing messages 
                                                            // conforming to the (KIDS, UIDS, ...) filename conventions
                                                            // e.g. make a UIDS file name for countries expecting UIDS 
                                                            // mesages instead of a KIDS name which probably was put in 
                                                            // by the customer. 
    private static boolean  createInName   = false;         // Create new (file) name for incomming messages 
                                                            // Messages received by JMS or web services do not hold a 
                                                            // file name. On other occasions it might be desired to 
                                                            // create a uniform file name instead of the incoming one.
                                                            // This flag indicates if a new name should be created. 
    private static boolean  returnConfirm   = true;         // Return Confirm Messages 
    														// set to "false" when used with UIDS from DK for ICS-Pool
    private static boolean  writeToCustomerDir = false;     // Write outgoing messages into a directory named after the KCX-ID. 

    private static boolean  replaceReceiver = false;     	// Replace localID in field "Receiver" by KCX-ID used with KidsToKidsConverter
    														// on customsBOBs with incoming messages named after the KCX-ID. 
    private static boolean timeZoneColon	= true;			// Insert the colon into the TZ: +01:00 instead of +0100
    														// Default is "true" - because only Diehl gets TZ without colon
    private static boolean removeTZ			= false;		// remove Timezone "Z" in datetime fields UIDS: Default is "false"
    
    private static String   sslKeystore      = "~/.keystore";    // Name of the keystore file containing trusted server certificates 
    private static String   sslAlias         = "www2.gsis.gr";   // Alias of the certificate stored in the keystore  
    
    private Config() {
    }

    /**
     * Lädt die Konfigurationsdaten aus einer *.ini-Datei.
     * Prüft die Daten auf Mussangaben, vorhandene Dateien und Pfade.
     * Legt Pfade ggfls. neu an.
     * Weist alle Parameter internen Variablen zu.
     * 
     * @param path Pfad zur *.ini-Datei
     * @param name Name der *.ini-Datei
     * @return true, wenn die Datei gelesen werden konnte und alle Prüfungen sind OK, sonst false
     */
    public static boolean configure(String path, String name) {
        boolean rc = true;
        
        logger = Logger.getLogger("kcx");

        if (path != null) {
            iniPath = path;
        }
        if (name != null) {
            iniFile = name;
        }
        if (loadConfiguration()) {
            loaded = true;
            rc = checkConfiguration();
            showConfiguration();
        } else {
            rc =  false;
        }
        configured = true;
        return rc;
    }
    
    public static boolean configure() {
        return configure(iniPath, iniFile);
    }
    
    private static boolean loadConfiguration() {
        int error = Prop.load(iniPath, iniFile);
        return error == 0;
    }
    
    /**
     * Lädt die Konfigurationsdaten erneut aus der *.ini-Datei.
     * Prüft die Daten auf Mussangaben, vorhandene Dateien und Pfade.
     * Legt Pfade ggfls. neu an.
     * Weist alle Parameter internen Variablen zu.
     * 
     * @param path Pfad zur *.ini-Datei
     * @param name Name der *.ini-Datei
     * @return true, wenn die Datei gelesen werden konnte und alle Prüfungen sind OK, sonst false
     */
    public static boolean reconfigure(String path, String name) {
        logger = Logger.getLogger("kcx");
        if (path != null) {
            iniPath = path;
        }
        if (name != null) {
            iniFile = name;
        }
        if (reloadConfiguration()) {
            loaded = true;
            return checkConfiguration();
        } else {
            return false;
        }
    }
    
    private static boolean reloadConfiguration() {
        int error = Prop.reload(iniPath, iniFile);
        return error == 0;
    }


    private static boolean checkConfiguration() {
        boolean rc = true;
        
        showConfig        = Prop.getBooleanProperty("config.show", "false");
        wrapMessages      = Prop.getBooleanProperty("wrap.messages", "true");
        debug             = Prop.getBooleanProperty("log.debug", "true");
        logLevel          = Prop.getIntProperty("log.level", "6");
        logConfig         = Prop.getProperty("log.config");
        logXML            = Prop.getBooleanProperty("log.logXML", "true");
        errorLog          = Prop.getProperty("error.log", "log/error.log");
        mailSendFlag      = Prop.getBooleanProperty("mail.sendMail", "true");
        mailHost          = Prop.getProperty("mail.host", "mail01");
        mailFrom          = Prop.getProperty("mail.from", "kcx@kewill.de");
        mailFromName      = Prop.getProperty("mail.fromName", "KCX");
        mailSubject       =  Prop.getProperty("mail.subject");
        mailTo            = Prop.getProperty("mail.to", "");
        mailCc            = Prop.getProperty("mail.cc", "");
        mailBcc           = Prop.getProperty("mail.bcc", "");
        mailDebug         = Prop.getBooleanProperty("mail.debug", "true");
        dbUser            = Prop.getProperty("db.user", "kcx");
        dbPasswd          = Prop.getProperty("db.passwd", "kcx");
        dbConnect         = Prop.getProperty("db.connect");
        dbPrefetchSize    = Prop.getProperty("db.prefetchsize", "1");
        dbSetQueryTimeout = Prop.getBooleanProperty("db.setQueryTimeout", "false");
        dbQueryTimeout    = Prop.getIntProperty("db.queryTimeout", "0");
        pdfpath		      = Prop.getProperty("pdf.path", "pdfpath");
        writeOutFiles     = Prop.getBooleanProperty("out.writeFiles", "false");
        countryOutPath    = Prop.getProperty("out.countryPath");
        customerOutPath   = Prop.getProperty("out.customerPath");
        writeInFiles      = Prop.getBooleanProperty("in.writeFiles", "false");
        countryInPath     = Prop.getProperty("in.countryPath");
        customerInPath    = Prop.getProperty("in.customerPath");
        createOutName     = Prop.getBooleanProperty("out.createName", "false");
        createInName      = Prop.getBooleanProperty("in.createName", "false");
        returnConfirm     = Prop.getBooleanProperty("message.returnConfirm", "true");
        writeToCustomerDir     = Prop.getBooleanProperty("out.writeToCustomerDir", "false");
        replaceReceiver	  = Prop.getBooleanProperty("replaceReceiver", "false");
        auditEnabled      = Prop.getBooleanProperty("audit.enabled", "true");
        reodays    		  = Prop.getIntProperty("reodays", "30");
        timeZoneColon	  = Prop.getBooleanProperty("timeZoneColon", "true");
        removeTZ		  = Prop.getBooleanProperty("removeTZ", "false");
        sslKeystore       = Prop.getProperty("ssl.keystore", "~/.keystore");
        sslAlias          = Prop.getProperty("ssl.alias", "www2.gsis.gr");
        
        
        Utils.checkDir(pdfpath, "PDF-Zwischenverzeichnis");
        Utils.checkDir(countryOutPath, "Basis-Ausgabepfad fuer Laender");
        Utils.checkDir(customerOutPath, "Basis-Ausgabepfad fuer Kunden");

        return rc;
    }
    
    /**
     * Loggt die geladenen Konfigurationsparameter.
     */
    public static void showConfiguration() {
        if (!showConfig) {
            return;
        }
        String configFile = iniPath.trim() + System.getProperty("file.separator") + iniFile.trim();
        logger.info("configuration file = " + configFile);
        logger.info("showConfig         = " + showConfig);
        logger.info("wrapMessages       = " + wrapMessages);
        logger.info("debug              = " + debug);
        logger.info("logLevel           = " + logLevel);
        logger.info("logConfig          = " + logConfig);
        logger.info("logXML             = " + logXML);
        logger.info("errorLog           = " + errorLog);
        logger.info("mailSendMail       = " + mailSendFlag);
        logger.info("mailHost           = " + mailHost);
        logger.info("mailFrom           = " + mailFrom);
        logger.info("mailFromName       = " + mailFromName);
        logger.info("mailSubject        = " + mailSubject);
        logger.info("mailTo             = " + mailTo);
        logger.info("mailCc             = " + mailCc);
        logger.info("mailBcc            = " + mailBcc);
        logger.info("mailDebug          = " + mailDebug);
        logger.info("dbUser             = " + dbUser);
        logger.info("dbPasswd           = " + dbPasswd);
        logger.info("dbConnect          = " + dbConnect);
        logger.info("dbPrefetchSize     = " + dbPrefetchSize);
        logger.info("dbSetQueryTimeout  = " + dbSetQueryTimeout);
        logger.info("dbQueryTimeout     = " + dbQueryTimeout);
        logger.info("pdfpath     		= " + pdfpath);
        logger.info("writeOutFiles      = " + writeOutFiles);
        logger.info("countryOutPath     = " + countryOutPath);
        logger.info("customerOutPath    = " + customerOutPath);
        logger.info("writeInFiles       = " + writeInFiles);
        logger.info("countryInPath      = " + countryInPath);
        logger.info("customerInPath     = " + customerInPath);
        logger.info("createOutName      = " + createOutName);
        logger.info("createInName       = " + createInName);
        logger.info("returnConfirm      = " + returnConfirm);
        logger.info("writeToCustomerDir = " + writeToCustomerDir);
        logger.info("replaceReceiver    = " + replaceReceiver);
        logger.info("auditEnabled       = " + auditEnabled);
        logger.info("reodays			= " + reodays);
        logger.info("timeZoneColon      = " + timeZoneColon);
        logger.info("removeTZ      		= " + removeTZ);
        logger.info("sslKeystore        = " + sslKeystore);
        logger.info("sslAlias           = " + sslAlias);
        
    }
    
    
    public static boolean isAuditEnabled() {
        return auditEnabled;
    }
    public static boolean isWrapMessages() {
        return wrapMessages;
    }
    public static void setWrapMessages(boolean doWrapMessages) {
        wrapMessages = doWrapMessages;
    }
    public static boolean getDebug() {
        return debug;
    }
    public static int getLogLevel() {
        return logLevel;
    }
    public static String getLogConfig() {
        return logConfig;
    }
    public static boolean getLogXML() {
        return logXML;
    }
    public static String getErrorLog() {
        return errorLog;
    }

    public static boolean getMailSendFlag() {
        return mailSendFlag;
    }
    public static String getMailBcc() {
        return mailBcc.trim();
    }
    public static String getMailCc() {
        return mailCc.trim();
    }
    public static String getMailFrom() {
        return mailFrom;
    }
    public static String getMailFromName() {
        return mailFromName;
    }
    public static String getMailSubject() {
        return mailSubject;
    }
    public static String getMailHost() {
        return mailHost;
    }
    public static String getMailTo() {
        return mailTo.trim();
    }
    public static boolean getMailDebug() {
        return mailDebug;
    }

    public static String getDbconnect() {
        return dbConnect;
    }

    public static String getDbpasswd() {
        return dbPasswd;
    }
    
    public static String getDbuser() {
        return dbUser;
    }

    public static String getDbPrefetchSize() {
        return dbPrefetchSize;
    }

    public static boolean isDbSetQueryTimeout() {
        return dbSetQueryTimeout;
    }

    public static int getDbQueryTimeout() {
        return dbQueryTimeout;
    }

    public static String getPdfpath() {
        return pdfpath;
    }

    public static boolean isLoaded() {
        return loaded;
    }

    public static boolean isConfigured() {
        return configured;
    }

	public static int getReodays() {
		return reodays;
	}

	public static void setReodays(int reodays) {
		Config.reodays = reodays;
	}

    public static String getCountryOutPath() {
        return countryOutPath;
    }

    public static String getCustomerOutPath() {
        return customerOutPath;
    }

    public static boolean isWriteOutFiles() {
        return writeOutFiles;
    }
    public static boolean isCreateOutName() {
        return createOutName;
    }

    public static boolean isWriteInFiles() {
        return writeInFiles;
    }
    public static boolean isCreateInName() {
        return createInName;
    }

    public static String getCountryInPath() {
        return countryInPath;
    }

    public static String getCustomerInPath() {
        return customerInPath;
    }

	public static boolean isReturnConfirm() {
		return returnConfirm;
	}

    public static boolean isWriteToCustomerDir() {
        return writeToCustomerDir;
    }
    public static boolean isReplaceReceiver() {
        return replaceReceiver;
    }
    public static boolean isTimeZoneColon() {
    	return timeZoneColon;
    }
    public static boolean isRemoveTZ() {
    	return removeTZ;
    }
    
    public static String getSslKeystore() {
        return sslKeystore;
    }
    public static String getSslAlias() {
        return sslAlias;
    }

}
