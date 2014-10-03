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
package com.kewill.kcx.component.mapping.test.testUtils;

import java.io.File;

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
    private static String   iniFile         = "xsd2xml.ini";      // Name der INI-Datei
    private static boolean  loaded          = false;          // Konfiguration eingelesen?

    private static boolean  showConfig      = false;          // Konfiguration ausgeben?
    
    private static boolean  debug           = true;         // Debug-Schalter
    private static int      logLevel        = 6;            // Log-Level
    private static String   logConfig       = "log.config"; // Log-Konfigurationsdatei
    private static boolean  logXML          = true;        // Konvertierte Daten ausgeben
    private static String   errorLog        = "log/error.log"; // Name der Error-Log-Datei
  
    private static String   mailHost = "mail01";            // Mail-Host                    
    private static String   mailFrom = "kcx@kewill.de";     // Absender-Adresse
    private static String   mailFromName = "KCX";           // Name des Absenders
    private static String   mailTo = "";                    // Empfänger-Adressen
    private static String   mailCc = "";                    // CC-Adressen
    private static String   mailBcc = "";                   // BCC-Adressen
    private static boolean  mailDebug = true;               // Mail-API Debug-Ausgaben?

    private static String   dbUser          = null;         // Datenbank-User
    private static String   dbPasswd        = null;         // Datenbank-User-Passwort
    private static String   dbConnect       = null;         // Datenbank-Connectstring
    private static String   dbPrefetchSize  = null;         // Datenbank-Prefetchgröße

    private static boolean  useFixedValues  = true;         // Generierte Werte in die Tags schreiben
    private static String   targetDirectory = null;         // Verzeichnis der generierten XML-Dateien
    private static File     targetDir       = null;         // Verzeichnis der generierten XML-Dateien
    
    
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
        
        debug           = Prop.getBooleanProperty("log.debug", "true");
        logLevel        = Prop.getIntProperty("log.level", "6");
        logConfig       = Prop.getProperty("log.config");
        logXML          = Prop.getBooleanProperty("log.logXML", "true");
        errorLog        = Prop.getProperty("error.log", "log/error.log");
        showConfig      = Prop.getBooleanProperty("log.showConfig", "false");
        mailHost        = Prop.getProperty("mail.host", "mail01");
        mailFrom        = Prop.getProperty("mail.from", "kcx@kewill.de");
        mailFromName    = Prop.getProperty("mail.fromName", "KCX");
        mailTo          = Prop.getProperty("mail.to", "");
        mailCc          = Prop.getProperty("mail.cc", "");
        mailBcc         = Prop.getProperty("mail.bcc", "");
        mailDebug       = Prop.getBooleanProperty("mail.debug", "true");
        dbUser          = Prop.getProperty("db.user", "kcx");
        dbPasswd        = Prop.getProperty("db.passwd", "kcx");
        dbConnect       = Prop.getProperty("db.connect");
        dbPrefetchSize  = Prop.getProperty("db.prefetchsize", "1");
        useFixedValues  = Prop.getBooleanProperty("xsd2xml.useFixedValues", "true");
        targetDirectory = Prop.getProperty("xsd2xml.targetDirectory");
        
        if (!Utils.isStringEmpty(targetDirectory)) {
            targetDir = Utils.checkDir(targetDirectory, "XML-Zielverzeichnis");
        }

        return rc;
    }
    
    /**
     * Loggt die geladenen Konfigurationsparameter.
     */
    public static void showConfiguration() {
        if (Config.showConfig) {
            String configFile = iniPath.trim() + System.getProperty("file.separator") + iniFile.trim();
            logger.debug("configuration file = " + configFile);
            logger.debug("debug              = " + debug);
            logger.debug("logLevel           = " + logLevel);
            logger.debug("logConfig          = " + logConfig);
            logger.debug("logXML             = " + logXML);
            logger.debug("errorLog           = " + errorLog);
            logger.debug("showConfig         = " + showConfig);
            logger.debug("mailHost           = " + mailHost);
            logger.debug("mailFrom           = " + mailFrom);
            logger.debug("mailFromName       = " + mailFromName);
            logger.debug("mailTo             = " + mailTo);
            logger.debug("mailCc             = " + mailCc);
            logger.debug("mailBcc            = " + mailBcc);
            logger.debug("mailDebug          = " + mailDebug);
            logger.debug("dbUser             = " + dbUser);
            logger.debug("dbPasswd           = " + dbPasswd);
            logger.debug("dbConnect          = " + dbConnect);
            logger.debug("dbPrefetchSize     = " + dbPrefetchSize);
            logger.debug("targetDirectory    = " + targetDirectory);
            logger.debug("useFixedValues     = " + useFixedValues);
        }
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
    public static boolean isLoaded() {
        return loaded;
    }
    public static boolean isConfigured() {
        return configured;
    }

    public static boolean isUseFixedValues() {
        return useFixedValues;
    }

    public static String getTargetDirectory() {
        return targetDirectory;
    }

    public static File getTargetDir() {
        return targetDir;
    }
}
