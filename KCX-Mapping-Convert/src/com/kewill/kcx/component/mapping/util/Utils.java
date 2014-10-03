package com.kewill.kcx.component.mapping.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.mule.api.MuleEventContext;

import com.kewill.kcx.component.mapping.EProcedures;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.emcs.EKidsEmcsMessages;
import com.kewill.kcx.component.mapping.countries.de.ics.EKidsICSMessages;
import com.kewill.kcx.component.mapping.countries.de.ncts.EKidsNCTSMessages;
import com.kewill.kcx.component.mapping.countries.de.suma62.EKidsManifestMessages;
import com.kewill.kcx.component.mapping.db.AirportLandDTO;
import com.kewill.kcx.component.mapping.db.CountryConfigDTO;
import com.kewill.kcx.component.mapping.db.CustomerDTO;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerPackagecodesDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.db.FedexCompletionDTO;
import com.kewill.kcx.component.mapping.db.KCXCodeDTO;
import com.kewill.kcx.component.mapping.db.KcxLicenseException;
import com.kewill.kcx.component.mapping.db.KcxNoDataFoundException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.mail.ErrorMail;
import com.kewill.kcx.util.AuditUtils;

/**
 * Modul		: Utils<br>
 * Erstellt		: 27.11.2004<br>
 * Beschreibung	: Eine Sammlung Utility Methoden.<br>
 *
 * @author 	MS
 * @version 1.0.00
 */
public final class Utils {
	/**
	 * Date format YYYYMMDD.
	 */
    public static final DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    /**
     * Zeitformat HHMMSS.
     */
    public static final DateFormat HHMMSS   = new SimpleDateFormat("HHmmss");

    /**
     * Date format YYYYMMDDHHMMSS.
     */
    public static final DateFormat YYYYMMDDHHMISS = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * Date format YYYYMMDDhhmmssSSS.
     */
    public static final DateFormat YYYYMMDDHHMISSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");    

    /**
     * Date format YYYY-MM-DD HH:MM:SS.
     */
    public static final DateFormat ORACLE_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
	 * Das OS-spezifische Zeilenendezeichen.
	 */
    public static final String LF = System.getProperty("line.separator", "\n");

    /**
     * Log-Level NONE.
     * Wert = 0
     */
    private static final int LOG_NONE    = 0;

    /**
     * Log-Level SEVERE.
     * Wert = 1
     */
    public  static final int LOG_SEVERE  = 1;

    /**
     * Log-Level ERROR.
     * Wert = 2
     */
    public  static final int LOG_ERROR   = 2;

    /**
     * Log-Level WARNING.
     * Wert = 3
     */
    public  static final int LOG_WARNING = 3;

    /**
     * Log-LevelINFO.
     * Wert = 4
     */
    public  static final int LOG_INFO    = 4;

    /**
     * Log-Level DEBUG.
     * Wert = 5
     */
    public  static final int LOG_DEBUG   = 5;

    /**
     * Log-Level ALL.
     * Wert = 6
     */
    public  static final int LOG_ALL     = 6;

    /**
     * Default-Log-ID-Länge.
     * Wert = 8
     */
    private static final int DEFAULT_LOG_ID_LENGTH = 8;

    private static boolean debug      = true;			// Debug für Programmstart einschalten
    private static int     logLevel   = LOG_ALL;		// Loglevel auf ALL für Programmstart
    private static String  logID      = "        ";     // ID zum Kennzeichnen zusammengehöriger Zeilen

    private static DateFormat   df           = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss,SSS");
    private static NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("de"));

    private static String       timestamp    = null;


    private Utils() {
    }

    public static synchronized void log(String msg) {
        if (debug) {
        	processId();
		    timestamp = df.format(new Date(System.currentTimeMillis()));
		    System.out.println(timestamp + " " + logID + " [   ] DBG   " + msg);
    		System.out.flush();
        }
    }
    public static synchronized String getSystemTimestamp() {  //EI20130621
    	return timestamp = df.format(new Date(System.currentTimeMillis()));
    }
    public static synchronized void log(int level, String msg) {
        if (logLevel != LOG_NONE && level <= logLevel) {
        	processId();
		    timestamp = df.format(new Date(System.currentTimeMillis()));
		    System.out.println(timestamp + " " + logID + " [   ] LOG   " + msg);
    		System.out.flush();
        }
    }

    private static synchronized void processId() {
	    String tmpID = "" + Utils.nFormat(Thread.currentThread().getId(), DEFAULT_LOG_ID_LENGTH);
		if (logID != null) {
			if (!logID.equalsIgnoreCase(tmpID)) {
        		System.out.println("=======================");
        		System.out.flush();
    		}
    	}
		logID = tmpID;
    }

    public static void setDebug(boolean dbg) {
        debug = dbg;
    }

    public static void setLogLevel(int level) {
//        log(LOG_INFO, "(Utils setLogLevel) Setting logLevel to " + level);
        logLevel = level;
    }

    /**
	 * Wandelt ein Datum vom Typ <code>java.sql.Date</code> in einen String um.
	 * Ist der Datum-Parameter <code>null</code> wird ein leerer String zurück
	 * geliefert.
	 *
	 * @param date Das umzuwandelnde Datum
	 * @return Das Datum als String
	 */
	public static String dateToString(java.sql.Date date) {
	    if (date == null) {
	        return "";
	    } else {
		    return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN).format(date);
	    }
	}

    /**
	 * Wandelt ein Zeitobjekt vom Typ <code>java.sql.Time</code> in einen String um.
	 * Ist der Zeit-Parameter <code>null</code> wird ein leerer String zurück
	 * geliefert.
	 *
	 * @param time Die umzuwandelnde Zeit
	 * @return Die Zeit als String
	 */
	public static String timeToString(java.sql.Time time) {
	    if (time == null) {
	        return "";
	    } else {
		    return DateFormat.getTimeInstance().format(time);
	    }
	}

	/**
	 * Wandelt einen Zeitstempel vom Typ <code>java.sql.Timestamp</code> in
	 * einen String um. Ist der Zeitstempel-Parameter <code>null</code> wird
	 * ein leerer String zurück geliefert.
	 *
	 * @param date Der umzuwandelnde Timestamp
	 * @return Der Timestamp als String
	 */
	public static String dateTimeToString(java.sql.Timestamp date) {
	    if (date == null) {
	        return "";
	    } else {
		    return DateFormat.getDateTimeInstance().format(date);
	    }
	}

    /**
     * Wandelt einen Zeitstempel vom Typ <code>java.sql.Timestamp</code> in
     * einen String im Format YYYYMMDDHHMMSS um. 
     * Ist der Zeitstempel-Parameter <code>null</code> wird ein leerer String zurück geliefert.
     *
     * @param timestamp Der umzuwandelnde Timestamp
     * @return Der Timestamp als String
     */
    public static String timestampToString(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        } else {
            return YYYYMMDDHHMISS.format(timestamp);
        }
    }
    
    /**
     * Wandelt einen Zeitstempel vom Typ <code>java.sql.Timestamp</code> in
     * einen String im Format YYYYMMDDHHMMSSSSS um. 
     * Ist der Zeitstempel-Parameter <code>null</code> wird ein leerer String zurück geliefert.
     *
     * @param timestamp Der umzuwandelnde Timestamp
     * @return Der Timestamp als String mit Millisekunden
     */
    public static String timestampMillisToString(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        } else {
            return YYYYMMDDHHMISSSSS.format(timestamp);
        }
    }

    /**
     * Wandelt einen Zeitstempel vom Typ <code>java.sql.Timestamp</code> in
     * einen String im Oracle Timestamp Format YYYY-MM-DD HH:MM:SS um. 
     * Ist der Zeitstempel-Parameter <code>null</code> wird ein leerer String zurück geliefert.
     *
     * @param timestamp Der umzuwandelnde Timestamp
     * @return Der Timestamp als String
     */
    public static String timestampToOracleString(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        } else {
            return ORACLE_TIMESTAMP.format(timestamp);
        }
    }

	/**
	 * Wandelt einen String in ein Datum vom Typ
	 * <code>java.sql.Date</code> um. Ist der String leer wird
	 * <code>null</code> zurück geliefert.
	 *
	 * @param date Das umzuwandelnde Datum im String-Format
	 * @return Der String als <code>java.sql.Date</code>
	 */
	public static java.sql.Date stringToDate(String date) {
	    if (date.trim().equals("")) {
	        return null;
	    } else {
			try {
				DateFormat df = DateFormat.getDateInstance();
				return new java.sql.Date(df.parse(date).getTime());
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return null;
			}
	    }
	}

	/**
	 * Wandelt einen String in ein Datum vom Typ
	 * <code>java.sql.Time</code> um. Ist der String leer wird
	 * <code>null</code> zurück geliefert.
	 *
	 * @param time Das umzuwandelnde Datum im String-Format
	 * @return Der String als <code>java.sql.Time</code>
	 */
	public static java.sql.Time stringToTime(String time) {
	    if (time.trim().equals("")) {
	        return null;
	    } else {
			try {
				DateFormat df = DateFormat.getTimeInstance();
				return new java.sql.Time(df.parse(time).getTime());
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return null;
			}
	    }
	}

	/**
	 * Wandelt einen String in einen Zeitstempel vom Typ
	 * <code>java.sql.Timestamp</code> um. Ist der String leer wird
	 * <code>null</code> zurück geliefert.
	 *
	 * @param date Der umzuwandelnde Timestamp im String-Format
	 * @return Der String als <code>java.sql.Timestamp</code>
	 */
	public static java.sql.Timestamp stringToDateTime(String date) {
	    if (date.trim().equals("")) {
	        return null;
	    } else {
			try {
				DateFormat df = DateFormat.getDateTimeInstance();
				return new java.sql.Timestamp(df.parse(date).getTime());
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return null;
			}
	    }
	}

	/**
	 * Wandelt einen String in einen <code>int</code>.
	 * Repräsentiert der String keine Zahl wird <code>0</code>
	 * zurück geliefert.
	 *
	 * @param string Der umzuwandelnde Wert im String-Format
	 * @return Der String als <code>int</code>
	 */
	public static int stringToInt(String string) {
	    Utils.log("(Utils stringToInt) string = " + string);
        int value = 0;
        try {
			value = Integer.parseInt(string);
			Utils.log("(Utils stringToInt) int value = " + value);
		} catch (NumberFormatException e) {
		    Utils.log("(Utils stringToInt) NumberFormatException occured");
		    e.printStackTrace();
		    value = 0;
		}
		return value;
	}

	/*****************************************************************************
	 * Methode		:removePrependingZeros
	 * Beschreibung	:Entfernt führende nullen bei einem String,
	 * 				 der einer Zahl entspricht.
	 * 				 Entspricht der String nicht einer Zahl,
	 * 				 wird der orginal String zurückgeliefert.
	 * Author		:Marcus Meßer
	 * Erstellt		:29.01.2009
	 * @param str String von dem führende nullen entfernt werden sollen
	 * @return String ohne führende nullen
	 */
	public static String removePrependingZeros(String str) {
		String s = "";
		try {
		s = Integer.parseInt(str) + "";
		} catch (NumberFormatException e) {
		s = str;
		}
		return s;
	}

	/*****************************************************************************
	 * Methode     : nFormat
	 * Beschreibung: Konvertiert eine Zahl vom Typ <code>long</code> in einen String
	 * 				 der übergebenen Länge mit führenden Nullen.
	 * Autor 	   : Michael Schmidt
	 * Erstellt:   : 11.07.2005
	 * @param 		 number Zu formatiernde Zahl
	 * @param 		 tlen   Länge des Zielstrings (target length)
	 * @return 		 Mit führenden Nullen aufgefüllte Zahl als String
	 *****************************************************************************/
	public static String nFormat(long number, int tlen)	{
		StringBuffer sb   = new StringBuffer();
		String       num  = Long.toString(number);	// In String wandeln um Länge zu ermitteln
		int          nlen = num.length();			// Länge ermitteln
		int          dif  = tlen - nlen;			// Benötigte Anzahl Vornullen ermitteln

		if (dif > 0) {
			for (int i = 0; i < dif; i++) {
				sb.append("0");
			}
			sb.append(num);
		} else {
			sb.append(num);
		}
		return sb.toString();
	}

    /*****************************************************************************
     * Methode     : nFormat
     * Beschreibung: Konvertiert eine Zahl vom Typ <code>String</code> in einen String
     *               der übergebenen Länge mit führenden Nullen.
     * Autor       : Michael Schmidt
     * Erstellt:   : 01.07.2011
     * @param        num    Zu formatiernde Zahl
     * @param        tlen   Länge des Zielstrings (target length)
     * @return       Mit führenden Nullen aufgefüllte Zahl als String
     *****************************************************************************/
    public static String nFormat(String num, int tlen) {
        StringBuffer sb   = new StringBuffer();
        int          nlen = num.length();           // Länge ermitteln
        int          dif  = tlen - nlen;            // Benötigte Anzahl Vornullen ermitteln

        if (dif > 0) {
            for (int i = 0; i < dif; i++) {
                sb.append("0");
            }
            sb.append(num);
        } else {
            sb.append(num);
        }
        return sb.toString();
    }

	/**
	 * Prüft ob ein Verzeichnis existiert und legt es gegebenenfalls neu an.
	 * Legt auch alle fehlenden übergeordneten Verzeichnisse an.
	 *
	 * @param dirName Pfad des Verzeichnisses
	 * @param dirDescr Beschreibung des Verzeichnisses für Fehlerausgaben.
	 * @return Das Verzeichnis als <code>File</code>.
	 */
    public static File checkDir(String dirName, String dirDescr) {
        if (Utils.isStringEmpty(dirName)) {
            return null;
        }
        
        File dir = null;

		dir = new File(dirName);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Utils.log("(Utils checkDir) Das " + dirDescr + " " + dirName +
                             " existiert nicht und konnte auch nicht erstellt werden.");
                dir = null;
            }
        } else if (!dir.isDirectory()) {
            Utils.log("(Utils checkDir) \"" + dirName + "\" ist kein Dateiverzeichnis.");
            dir = null;
        }

        return dir;
    }

	/**
	 * Verschiebt eine Datei in ein anderes Verzeichnis.
	 * Ist die Datei im Zielverzeichnis bereits vorhanden, wird solange eine
	 * Laufnummer angehängt, bis ein freier Dateiname gefunden wurde.
	 *
	 * @param targetDir Zielverzeichnis
	 * @param inFile    Zu verschiebenden Datei.
	 * @return File Die verschobene und evtl. umbenannte Datei.
	 */
    public static File moveToDir(File targetDir, File inFile) {
    	return moveToDir(targetDir, inFile, false);
    }

   	/**
	 * Verschiebt eine Datei in ein anderes Verzeichnis.
	 * Ist der Parameter "overwrite"<code>false</code> und die Datei
	 * im Zielverzeichnis bereits vorhanden, wird solange eine neue
	 * Laufnummer angehängt, bis ein freier Dateiname gefunden wurde.
	 * Ist der Parameter "overwrite"<code>true</code> wird eine vorhandene Datei überschrieben.
	 *
	 * @param targetDir Zielverzeichnis
	 * @param inFile    Zu verschiebenden Datei.
	 * @param overwrite Datei überschreiben (true) oder neue Datei mit Laufnummer schreiben (false)
	 * @return File Die verschobene und evtl. umbenannte Datei.
	 */
    public static File moveToDir(File targetDir, File inFile, boolean overwrite) {
    	checkDir(targetDir.getPath(), "Verzeichnis");
        int laufnr = 0;
        boolean ret = true;
        File targetFile = null;
        String fileName = null;
        String fileBody = null;
        String fileExt = null;
        fileName = inFile.getName();
        String[] tokens = fileName.split("\\.");
        fileBody = tokens[0];
        if (tokens.length > 1) {
            fileExt  = "." + tokens[1].toLowerCase();
        } else {
            fileExt  = "";
        }

        targetFile = new File(targetDir, fileName);
        laufnr = 0;
        ret = targetFile.exists();
        if (!overwrite) {
        	while (ret) {
        		laufnr++;
        		targetFile = new File(targetDir, fileBody + "_" + laufnr + fileExt);
        		ret = targetFile.exists();
        	}
        }

        moveFile(targetFile, inFile, overwrite);

        return targetFile;
    }


   	/**
	 * Verschiebt eine Datei.
	 * Ist der Parameter "overwrite"<code>true</code> und die Datei
	 * im Zielverzeichnis bereits vorhanden, wird sie überschrieben.
	 * Ist der Parameter "overwrite"<code>false</code> wird eine Fehlermeldung ausgegeben.
	 *
	 * @param targetFile Zieldatei
	 * @param inFile     Zu verschiebenden Datei.
	 * @param overwrite  Datei überschreiben (true) oder neue Datei mit Laufnummer schreiben (false)
	 */
    public static void moveFile(File targetFile, File inFile, boolean overwrite) {
    	checkDir(targetFile.getParent(), "Verzeichnis");
        boolean fileExists = true;

        try {
        	fileExists = targetFile.exists();
        	if (fileExists) {
                if (overwrite) {
            		targetFile.delete();
                } else {
                    Utils.log("(Utils moveToDir) Datei " + inFile
                    		+ " konnte nicht nach " + targetFile + " verschoben werden," +
                    		  " da " + targetFile + " bereits existiert.");
                    return;
                }
        	}

            boolean ret = inFile.renameTo(targetFile);
            if (!ret) {
                Utils.log("(Utils moveFile) Datei " + inFile
                		+ " konnte nicht nach " + targetFile + " verschoben werden.");
            } else {
                Utils.log("(Utils moveFile) Datei " +  inFile + " verschoben nach " + targetFile + ".");
            }
        } catch (SecurityException e) {
            Utils.log(Utils.LOG_SEVERE, "(Utils moveFile) SecurityException beim Verschieben von Datei "
                    + inFile + " nach " + targetFile + "\n" + e);
        }
    }

    /**
     * Kopierte eine Datei in ein anderes Verzeichnis.
     * Ist die Datei im Zielverzeichnis bereits vorhanden, wird solange eine
     * Laufnummer angehängt, bis ein freier Dateiname gefunden wurde.
     *
     * @param targetDir Zielverzeichnis
     * @param inFile    Zu kopierende Datei.
     * @param newName   Neuer Name der zu kopierenden Datei
     * @return File Die kopierte und evtl. umbenannte Datei.
     */
    public static File copyToDir(File targetDir, File inFile, String newName) {
    	checkDir(targetDir.getPath(), "Verzeichnis");
        int laufnr = 0;
        boolean ret = true;
        File targetFile = null;
        String fileName = null;
        String fileBody = null;
        String fileExt = null;
        if (newName == null) {
            fileName = inFile.getName();
        } else {
            fileName = newName;
        }
        try {
            String[] tokens = fileName.split("\\.");
            fileBody = tokens[0];
            if (tokens.length > 1) {
                fileExt  = "." + tokens[1].toLowerCase();
            } else {
                fileExt  = "";
            }

            targetFile = new File(targetDir, fileName);
            laufnr = 0;
            ret = targetFile.exists();
            while (ret) {
//                Utils.log("(Utils copyToDir) " + targetFile + " existiert bereits.");
                laufnr++;
                targetFile = new File(targetDir, fileBody + "_" + laufnr + fileExt);
//                Utils.log("(Utils copyToDir) Zielname geändert nach " + targetFile + ".");
                ret = targetFile.exists();
            }

//            Utils.log("(Utils copyToDir) Kopieren von " + inFile + " nach " + targetFile + ".");

            try {
                BufferedReader in  = new BufferedReader(new FileReader(inFile));
                BufferedWriter out = new BufferedWriter(new FileWriter(targetFile));
                char[] ca = new char[(int) inFile.length()];
                while (in.read(ca) != -1) {
                    out.write(ca);
                }
                in.close();
                out.close();
                ret = true;
            } catch (IOException e) {
                e.printStackTrace();
                ret = false;
            }

            if (!ret) {
                Utils.log("(Utils copyToDir) Datei " + inFile
                		+ " konnte nicht nach " + targetFile + " kopiert werden.");
            } else {
                Utils.log("(Utils copyToDir) Datei " +  inFile + " kopiert nach " + targetFile + ".");
            }
        } catch (SecurityException e) {
            Utils.log(Utils.LOG_SEVERE, "(Utils copyToDir) SecurityException beim Kopieren von Datei "
                    + inFile + " nach " + targetFile + "\n" + e);
        }
        return targetFile;
    }

	/**
	 * Returns the logID.
	 * @return Returns the logID.
	 */
	public static String getLogID() {
		return logID;
	}

	/**
	 * Sets the logID.
	 * @param logID The logID to set.
	 */
	public static void setLogID(String logID) {
		Utils.logID = logID;
	}

	/**
	 * Prüft anhand der Dateiendung, ob die Datei eine XML-Datei ist.
	 *
	 * @param fileName Zu prüfender Dateiname.
	 * @return true, wenn die Dateiendung XML ist, sonst false.
	 */
	public static boolean isXMLFile(String fileName) {
	    String[] tokens = fileName.split("\\.");
	    if (tokens.length > 1) {
	        return tokens[1].toLowerCase().equalsIgnoreCase("XML") ? true : false;
	    } else {
	        return false;
	    }
	}

    public static void showMemory() {
        MemoryUsage mu  = getMemoryUsage();
        long committed  = mu.getCommitted();
        long init       = mu.getInit();
        long used       = mu.getUsed();
        long max        = mu.getMax();

        Utils.log("(Utils showMemory) Memory: init      = " + numberFormat.format(init));
        Utils.log("(Utils showMemory) Memory: committed = " + numberFormat.format(committed));
        Utils.log("(Utils showMemory) Memory: used      = " + numberFormat.format(used));
        Utils.log("(Utils showMemory) Memory: max       = " + numberFormat.format(max));
    }

    public static String getUsedMemory() {
        MemoryUsage mu  = getMemoryUsage();
        long used       = mu.getUsed();
        String usedString = numberFormat.format(used);
        return usedString;
    }

    private static MemoryUsage getMemoryUsage() {
        MemoryMXBean m  = ManagementFactory.getMemoryMXBean();
        MemoryUsage mu  = m.getHeapMemoryUsage();
        return mu;
    }

    public static void showRuntimeMemory() {
        Utils.log("(Utils showRuntimeMemory) FreeMemory : " + numberFormat.format(Runtime.getRuntime().freeMemory()));
//        Utils.log("(Utils showRuntimeMemory) MaxMemory  : " + Runtime.getRuntime().maxMemory());
    }

    public static boolean isStringEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static String checkNull(String s) {
		if (s == null) {
		    return "";
		} else {
		    return s;
		}
	}

    public static void createAuditContext(MuleEventContext muleEventContext, String value) throws Exception {
        if (!Config.isAuditEnabled()) {
            return;
        }
        if (value == null) {
            value = " ";
        }
        AuditUtils.dispatchCreateAuditPayload(muleEventContext, value);
    }


    public static void addAuditEvent(MuleEventContext muleEventContext, String key, String value) throws Exception {
        if (!Config.isAuditEnabled()) {
            return;
        }
        if (value == null) {
            value = " ";
        }
        AuditUtils.dispatchAuditEventPayload(muleEventContext, key, value);
    }

//    public static String getKewillIdFromCustomer(MuleEventContext muleEventContext) {
//        BorrowedObject<OrganisationResolutionService, MuleEventContext> borrowedObject =
//            new BorrowedObject<OrganisationResolutionService, MuleEventContext>(
//                "organisationResolutionClientPool", muleEventContext) {
//                    public void doWork(MuleEventContext muleEventContext) throws Exception {
//                        MuleMessage muleMessage = muleEventContext.getMessage();
//                        String name    = muleMessage.getStringProperty("CustomerName", null);
//                        Utils.log("(Utils getKewillIdFromCustomer) name = " + name);
//                        setResult(borrowedObject.resolveKewillFromCustomer(name));
//                    }
//        };
//        String result = (String) borrowedObject.getResult();
//        Utils.log("(Utils getKewillIdFromCustomer) result = " + result);
//        if (result == null) {
//            MuleMessage muleMessage = muleEventContext.getMessage();
//            String name    = muleMessage.getStringProperty("CustomerName", null);
//            throw new KcxMappingException("Unable to resolve kewill id from customer id (" + name + ").\n" +
//            		                      "Check database entries.");
//        }
//        return result;
//    }

//    public static String getCustomerIdFromKewillId(MuleEventContext muleEventContext) {
//        BorrowedObject<OrganisationResolutionService, MuleEventContext> borrowedObject =
//            new BorrowedObject<OrganisationResolutionService, MuleEventContext>(
//                "organisationResolutionClientPool", muleEventContext) {
//                    public void doWork(MuleEventContext muleEventContext) throws Exception {
//                        MuleMessage muleMessage = muleEventContext.getMessage();
//                        String name    = muleMessage.getStringProperty("KewillId", null);
//                        String country = muleMessage.getStringProperty("Country", null);
//                        String system  = muleMessage.getStringProperty("System", null);
//                        Utils.log("(Utils getCustomerIdFromKewillId) name    = " + name);
//                        Utils.log("(Utils getCustomerIdFromKewillId) country = " + country);
//                        Utils.log("(Utils getCustomerIdFromKewillId) system  = " + system);
//                        setResult(borrowedObject.resolveCustomsFromKewill(name, country, system));
//                    }
//        };
//        String result = (String) borrowedObject.getResult();
//        Utils.log("(Utils getCustomerIdFromKewillId) result = " + result);
//        if (result == null) {
//            MuleMessage muleMessage = muleEventContext.getMessage();
//            String name    = muleMessage.getStringProperty("KewillId", null);
//            String country = muleMessage.getStringProperty("Country", null);
//            String system  = muleMessage.getStringProperty("System", null);
//            String kewillIdKey = name + " / " + country + " / " + system;
//            throw new KcxMappingException("Unable to resolve customer id from kewill id (" + kewillIdKey + ").\n" +
//                                          "Check database entries.");
//        }
//        return result;
//    }

//    public static CustomerProcedureDTO getCustomerProceduresFromKewillId(MuleEventContext muleEventContext) {
//        BorrowedObject<OrganisationResolutionService, MuleEventContext> borrowedObject =
//            new BorrowedObject<OrganisationResolutionService, MuleEventContext>(
//                "organisationResolutionClientPool", muleEventContext) {
//                    public void doWork(MuleEventContext muleEventContext) throws Exception {
//                        MuleMessage muleMessage = muleEventContext.getMessage();
//                        String kcxId     = muleMessage.getStringProperty("KewillId", null);
//                        String procedure = muleMessage.getStringProperty("Procedure", null);
//                        Utils.log("(Utils getCustomerIdFromKewillId) kcxId     = " + kcxId);
//                        Utils.log("(Utils getCustomerIdFromKewillId) procedure = " + procedure);
//// TODO                        setResult(borrowedObject.resolveProcedureFromKewill(kcxId, procedure));
//                    }
//        };
//        CustomerProcedureDTO result = (CustomerProcedureDTO) borrowedObject.getResult();
//        Utils.log("(Utils getCustomerProceduresFromKewillId) result = " + result);
//        if (result == null) {
//            MuleMessage muleMessage = muleEventContext.getMessage();
//            String name    = muleMessage.getStringProperty("KewillId", null);
//            throw new KcxMappingException("Unable to resolve procedure id from kewill id (" + name + ").\n" +
//                                          "Check database entries.");
//        }
//        return result;
//    }

    public static String getKCXCodeFromValueCodeIdFromTo(String value, String kcxCodeID, String from, String to) {
    	String result;
        KCXCodeDTO kcxDTO = Db.getKCXCodeFromValueCodeIdFromTo(value, kcxCodeID, from, to);
        if (kcxDTO == null) {
        	result = "";
        } else {
        	result = kcxDTO.getKcxCode();	
        }
        
        Utils.log("(Utils getKCXCodeFromFromValueCodeIdFromTo) result = " + result);
        return result;
    }
    public static String getKewillIdFromCustomer(String customerName) {
        CustomerDTO customerDTO = Db.getUidsCustomerFromEtnAddress(customerName);
        String result = customerDTO.getCorporateId();
        if (result != null) {
            result = result.trim();
        }
        Utils.log("(Utils getKewillIdFromCustomer) result = " + result);
        return result;
    }
    //EI20130114:
    public static String getKCXCodeXmlTag(String kcxcodeId, String from, String value) {  
        String xml = Db.getKCXCodeXmlTag(kcxcodeId, from, value);                
        Utils.log("(Utils getKCXCodeXmlTag) result = " + xml);
        return xml;
    }
    
    public static String getKewillIdFromCustomer(String customerName, String type) {  //EI20110112
        CustomerDTO customerDTO = Db.getCustomerFromAddress(customerName, type);
        String result = customerDTO.getCorporateId();
        if (result != null) {
            result = result.trim();
        }
        Utils.log("(Utils getKewillIdFromCustomer) result = " + result);
        return result;
    }
    public static String getFedexCompletion(String kcxId, String key) {  //EI20110329
    	FedexCompletionDTO completionDTO = Db.getFedexCompletion(kcxId, key);
    	String result = completionDTO.getValue();
        if (result != null) {
            result = result.trim();
        }
        Utils.log("(Utils getValueFromFedexCompletion) result = " + result);
        return result;
    }
    
    public static String getKewillIdFromFssCustomer(String customerName) {
        CustomerDTO customerDTO = Db.getCustomerFromFssAddress(customerName);
        String result = customerDTO.getCorporateId();
        Utils.log("(Utils getKewillIdFromFssCustomer) result = " + result);
        return result;
    }

//    public static String getUidsCustomerIdFromKewill(String kewillId) {
//        CustomerDTO customerDTO = Db.getUidsCustomerFromKidsId(kewillId);
//        String result = customerDTO.getLocalId();
//        Utils.log("(Utils getCustomerIdFromKewill) result = " + result);
//        return result;
//    }
    public static String getCustomerIdFromKewill(String kewillId, String type, String country) {
        CustomerDTO customerDTO = Db.getCustomerFromKidsId(kewillId, type, country);
        String result = customerDTO.getLocalId();
        Utils.log("(Utils getCustomerIdFromKewill) result = " + result);
        return result;
    }

    //AK20120424_2
    public static String getCorporateIdentityFromCustomer(String kcxId, String type, String country) {
        CustomerDTO customerDTO = Db.getCustomerFromKidsId(kcxId, type, country);
        String result = customerDTO.getCorporateIdentity();
        Utils.log("(Utils getCorporateIdFromCustomer) result = " + result);
        return result;
    }   
    
    public static String getProtocolForBL(String kcxId, String country) {
    	return Db.getProtocolForBL(kcxId, country);
    }
    
    public static int getDeclNum(String kewillId, String refnr) {
        return Db.getDeclNum(kewillId, refnr);
    }

	public static int getExistingDeclNum(String kewillId, String refnr) {
	    return Db.getExistingDeclNum(kewillId, refnr);
    }


    public static CustomerProcedureDTO getCustomerProceduresFromKcxId(String kcxId, String procedure) {
        CustomerProcedureDTO customerProceduresDTO = Db.getCustomerProceduresFromKidsId(kcxId, procedure);
        return customerProceduresDTO;
    }
    public static AirportLandDTO getAirportLand(String airport) {   //EI20130628    	
    	AirportLandDTO airportLandDTO = Db.getLandFromAirport(airport);        
        return airportLandDTO;
    }
    public static String getLandFromAirport(String airport) {   //EI20130628  
    	String land = "";
    	if (Utils.isStringEmpty(airport)) {    		
    		return land;
    	}    	
    	AirportLandDTO airportLandDTO = Db.getLandFromAirport(airport);      
    	if (airportLandDTO != null) {
    		land = airportLandDTO.getAirportLand();
    	}
        return land;
    }
    public static String getUnlocodeFromAirport(String airport_name) {   //EI20130628    
    	String code = "";
    	if (Utils.isStringEmpty(airport_name)) {    		
    		return code;
    	} 
    	AirportLandDTO airportLandDTO = Db.getUncodeFromAirport(airport_name);      
    	if (airportLandDTO != null) {
    		code = airportLandDTO.getUnlocode();
    	}
        return code;
    }
    
    public static String getColartFromBDPPackageCode(String packageCode, String portSystem, 
			 								String declarationType, String qualifier) {   //EI20140319
    	String colart = packageCode;
    	if (Utils.isStringEmpty(packageCode)) {    		
    		return "";
    	} 
    	if (Utils.isStringEmpty(portSystem)) {    		
    		portSystem = "";
    	} 
    	if (Utils.isStringEmpty(declarationType)) {    		
    		declarationType = "";
    	}  
    	if (Utils.isStringEmpty(qualifier)) {    		
    		qualifier = "";
    	} 
    	
    	CustomerPackagecodesDTO customerPackageDTO = Db.getColartFromBDPPackageCode(packageCode);       	
    	if (customerPackageDTO != null) {
    		if (portSystem.equals("BHT")) {  //&& declarationType=LAS && qualifier = 001
    			colart = customerPackageDTO.getZabisBht();    			
    		}
    		if (portSystem.equals("ZAPP")) {
    			if (declarationType.equals("HDS")) {
    				if (qualifier.equals("1")) {
    					colart = customerPackageDTO.getZabisHds();
    				} else {
    					colart = customerPackageDTO.getZabisGpo();
    				}
    			}
    		}    		
    	}
    	if (colart == null || colart.equals("x")) {
    		colart = "";
    	}
        return colart;
    }
    
    
    // EI20130426:
    //public static String getReleaseFromCustomerProcedure(String kcxId, String procedure, String format) {
    public static String getReleaseFromCustomerProcedure(String kcxId, String procedure) {
    	String result = "";
    	String proc = "";
    	
    	if (Utils.isStringEmpty(procedure)) {
    		return result;	
    	}
    	
    	if (procedure.equalsIgnoreCase("ex") || procedure.equalsIgnoreCase("exp")) {
    		proc = "EXPORT";
    	} else if (procedure.equalsIgnoreCase("ve")) {
    		proc = "NCTS";
    	} else if (procedure.equalsIgnoreCase("zb")) {
    		proc = "IMPORT";
    	} else if (procedure.equalsIgnoreCase("zp")) {
    		proc = "PORT";
    	} else if (procedure.equalsIgnoreCase("su")) {
    		proc = "MANIFEST";
    	} else {
    		proc = "";
    	}
    	
    	//CustomerProcedureDTO customerProceduresDTO = Db.getCustomerProceduresFromFormat(kcxId, proc, format);
    	//if (format.equalsIgnoreCase("KIDS")) {  //z.Z nur fuer Kids - wg.KFF SonderRelease
    	CustomerProcedureDTO customerProceduresDTO = Db.getCustomerProceduresFromKidsId(kcxId, proc);
    	
    	if (customerProceduresDTO != null) {
    		result = customerProceduresDTO.getKidsRelease();    		
    	}
    	Utils.log("(Utils getReleaseFromCustomerProcedure) result = " + result);
    	
        return result;
    }
    
    public static CustomerDataDTO getCustomerDataFromKcxId(String kcxId) {
        CustomerDataDTO customerDataDTO = Db.getCustomerDataFromKidsId(kcxId);
        return customerDataDTO;
    }
    public static String getBobNameFromKcxId(String kcxId) {   //EI20130215
    	String bob = "";
        CustomerDataDTO customerDataDTO = Db.getCustomerDataFromKidsId(kcxId);
        if (customerDataDTO != null) {
        	bob = customerDataDTO.getBobName();
        }
        return bob;
    }
    public static boolean isProcedureLicensed(String kcxId, String procedure, String country) {
        Utils.log("(Utils isProcedureLicensed) kcxId = " + kcxId +
                                            ", Procedure = " + procedure +
                                            ", country = " + country);
        try {
            Db.getCustomerLicenseFromKidsId(kcxId, procedure, country);
        } catch (KcxNoDataFoundException e) {
            throw new KcxLicenseException("KCX-ID " + kcxId + " is not licensed for procedure " +
                                          procedure + " in country " + country + "!");
        }
        return true;
    }

    public static CountryConfigDTO getCountryConfig(String countryCode) {
        CountryConfigDTO countryConfigDTO = Db.getCountryConfig(countryCode);
        return countryConfigDTO;
    }   
    
    /*****************************************************************************
	 * Methode     : getStringWithoutDot
	 * Beschreibung: Entfernung eines(!) Punktes in einer Zeichenkette
	 * 				 wird verwendet wenn Zahlenwerte mit Komma (= Punkt) ohne
	 * 				 Komma dargestellt werden sollen, oder beim Umwandeln Versionsnummer
	 * 				 Zabis 05.00 in 0500.
	 * Autor 	   : Christine Kron
	 * Erstellt:   : 04.03.2009
	 * @param 		 text Zeichenkette mit Punkt
	 * @return 		 Zeichenkette ohne Punkt
	 *****************************************************************************/
	public static String getStringWithoutDot(String text) {
		String	str1	= "";
		String	str2	= "";
		String	retStr	= "";
		int 	len		= -1;
		int		pos		= -1;

        if (text == null) {
            return "";
        }

        len = text.length();
		if (len == 0) {
            return "";
        }

		pos = text.indexOf(".");

        if (pos <= 0) {
            return text;
        }

		str1 = text.substring(0, pos);
		str2 = text.substring(pos + 1, len);

		retStr = str1 + str2;

        return retStr;
    }

    /*****************************************************************************
     * Methode     : removeDots
     * Beschreibung: Entfernung aller Punkte in einer Zeichenkette.
     * Autor       : Michael Schmidt
     * Erstellt:   : 02.06..2009
     * @param        text Zeichenkette mit Punkt
     * @return       Zeichenkette ohne Punkt
     *****************************************************************************/
    public static String removeDots(String text) {
        if (text == null) {
            return "";
        } else {
            return text.replaceAll("\\.", "");
        }
    }

	/**
	 * Liefert den UIDS-MessageName der einem KIDS-Namen entspricht.
	 *
	 * @param nameKids UIDS MessageName
	 * @param procedure customs procedure
	 * @return String KIDS MessageName
	 *
	 * @author 	CK
	 */
	public static String getUidsMsgFromKidsMsg(String nameKids, String procedure) {
		String nameUids = "";

/*  EI20100706:
   ist in EKidsMessages definiert -- fehlt in switch
	CancelationResponse (only CH)
	VerificationResult (not in use)
	new for V21 kids2uids: ExitNotification, ExportReminder, ExitAuthorization(ExitAuthorisation) //EI20131209: eingefuegt in den switch
	new for V22 kids2uids: ExportControlNotification //EI20131204: eingefuegt in den switch
 */
		switch (EProcedures.valueOf(procedure)) {
		case EXPORT:
			switch (EKidsMessages.valueOf(nameKids)) {
            case PreNotification:
            	nameUids = "ExportPreAdvice";
                 break;
            case ExportDeclaration:
            	nameUids = "ExportDeclaration";
                 break;
            case Amendment:
            	nameUids = "ExportAmendment";
                 break;
            case Completion:
            	nameUids = "Completion";
                 break;
            case ConfirmInvestigation:
            	nameUids = "NonExitedExportResponse";
                 break;
            case Cancellation:
            	nameUids = "Cancelation";
                 break;
            case ManualTermination:
            	nameUids = "ManualTerminationExport";
                 break;
            case ReverseDeclaration:
            	nameUids = "ExportRelease";
                 break;
            case DeclarationResponse:
            	nameUids = "ExportDeclarationResponse";
                 break;
            case Confirmation:
            	nameUids = "Confirmation";
                 break;
            case Investigation:
                 nameUids = "NonExitedExportRequest";
                 break;
            case InternalStatusInformation:
            	nameUids = "InternalStatusInformation";
                 break;
            case ErrorMessage:
            	nameUids = "ErrorInformation";
                 break;
            case localAppResult:
            	nameUids = "Failure";
            	// important note: in mapping the message localAppResult from Kids to Uids
            	// in the class MapLocalAppKU the UIDS-Name is overwritten  depending on
            	// the content (error existing or not)
            break;
            case CancelationResponse:
            	nameUids = "CancelationResponse";
            	break;
            case ExitNotification:
            	nameUids = "ExitNotification";
            	break;
            
            case ExportReminder:
            	nameUids = "RemindCompletion";
            	break;
            case ExitAuthorization:
            case ExitAuthorisation:
            	nameUids = "ExitAuthorisation";
            	break;
            case ExportControlNotification:
            	nameUids = "ExportControlNotification";
            	break;
            default: nameUids = "";
     	}
		break;

		case EMCS:
			switch (EKidsEmcsMessages.valueOf(nameKids)) {
            case EMCSDeclaration:
                nameUids = "EMCSDeclaration";
                 break;
            case EMCSValidDeclaration:
                nameUids = "EMCSValidDeclaration";
                 break;
            case EMCSCancellation:
                nameUids = "EMCSCancellationEAAD";
                 break;
            case EMCSReminderMessage:
                nameUids = "EMCSReminderMessageForExciseMovement";
                 break;
            case EMCSDiversionNotification:
                nameUids = "EMCSNotificationDivertedEAAD";
                 break;
            case EMCSChangeOfDestination:
                nameUids = "EMCSChgOfDestinationInfo";
                 break;
            case EMCSReportOfReceipt:
                nameUids = "EMCSReportOfReceipt";
                 break;
            case EMCSAcceptedExport:
                nameUids = "EMCSNotificationOfAcceptedExport";
                 break;
            case EMCSExplanationOnDelay:
                nameUids = "EMCSExplanationOnDelayForDelivery";
                 break;
            case EMCSExportRejection:
                nameUids = "EMCSRejectionOfEAADForExport";
                 break;
            case EMCSGenericRefusalMessage:
                nameUids = "EMCSDeclarationRejected";
                 break;
            case InternalStatusInformation:
            	nameUids = "InternalStatusInformation";
                 break;
            case localAppResult:
            	nameUids = "Failure";
            	// important note: in mapping the message localAppResult from Kids to Uids
            	// in the class MapLocalAppKU the UIDS-Name is overwritten  depending on
            	// the content (error existing or not)
                 break;
            case EMCSAlertOrRejection:                   	//EI20110927
            	nameUids = "EMCSAlertOrRejection";
                break;
            case EMCSInterruptionOfMovement:             	//EI20110927 
            	nameUids = "EMCSInterruptionOfMovement";
                break;
            case EMCSExplanationOnReasonForShortage:    	 //EI20110927
            	nameUids = "EMCSExplanationOnReasonForShortage";
                break;
            case EMCSStatusResponse:      					//EI20110927 
            	nameUids = "EMCSStatusResponse";
                break;
            case EMCSEventReport:      						//EI20110927
            	nameUids = "EMCSEventReport";
                break;
            case EMCSSubmittedDraftOfSplittingOperation:    //EI20110927
            	nameUids = "EMCSSubmittedDraftOfSplittingOperation";
                break;
           
            default: nameUids = "";
     	}

			break;
		case ICS:
			switch (EKidsICSMessages.valueOf(nameKids)) {
			case ICSAdvancedInterventionNot:                      //IE351 
				nameUids = "ICSDeclarationProhibited";
				break;
			case ICSArrivalItemRejection:					      //IE349 
				nameUids = "ICSArrivalItemRejection";
				break;
			case ICSArrivalNotification:						  //IE347 
				nameUids = "ICSArrivalNotification";
				break;
			case ICSArrivalNotificationValidation:
				nameUids = "ICSArrivalNotificationValidation";	  //IE348 
				break;			
			case ICSDiversionRequest:							  //IE323 
				nameUids = "ICSDiversionRequest";
				break;
			case ICSDiversionRequestRejected:					  //IE324 
				nameUids = "ICSDiversionRequestRejected";
				break;
			case ICSDiversionRequestAcknowledgment:				  //IE325 
				nameUids = "ICSDiversionRequestAccepted";
				break;
			case ICSEntrySummaryDeclaration:					  //IE315				
				nameUids = "ICSDeclaration";
				break;
			case ICSEntrySummaryDeclarationAcknowledgment:		  //IE328 
				nameUids = "ICSDeclarationAccepted";
				break;
			case ICSEntrySummaryDeclarationRejected:			  //IE316 
				nameUids = "ICSDeclarationRejected";
				break;
			case ICSDeclarationAmendment:						  //IE313 - neu EI20010120 
				nameUids = "ICSDeclarationAmendment";
				break;
			case ICSEntrySummaryDeclarationAmendmentAcceptance:   //IE304 
				//EI20110120: nameUids = "ICSDeclarationAmendment";
				nameUids = "ICSDeclarationAmendmentAccepted";
				break;			
			case ICSEntrySummaryDeclarationAmendmentRejection:	  //IE305 
				nameUids = "ICSDeclarationAmendmentRejected";
				break;															
			case InternalStatusInformation:
				nameUids = "InternalStatusInformation";
				break;
			case localAppResult:
				nameUids = "Failure";
            	// important note: in mapping the message localAppResult from Kids to Uids
            	// in the class MapLocalAppKU the UIDS-Name is overwritten  depending on
            	// the content (error existing or not)
				break;    //EI20110318 added missing break
			default: nameUids = "";
			}
			//EI20110318:Utils.log("Utils.getUidsMsgFromKidsMsg() : Procedure " + procedure + " has to be defined here");
			break;

		case NCTS:
			switch (EKidsNCTSMessages.valueOf(nameKids)) {
            case NCTSDeclaration:
            	nameUids = "NCTSDeclaration";
                 break;
            case ArrivalNotification:
            	nameUids = "NCTSArrivalNotification";
                 break;
            case UnloadingRemarks:
            	nameUids = "NCTSUnloadingRemarks";
                 break;
            case MRNAllocated:
            	nameUids = "NCTSMRNAllocated";
                 break;
            case UnloadingPermission:
            	nameUids = "NCTSUnloadingPermission";
                 break;
            case UnloadingRemarksRejection:
            	nameUids = "NCTSUnloadingRemarksRejection";
                 break;
            case ArrivalRejection:
            	nameUids = "NCTSArrivalRejection";
                 break;
            case NCTSDeclarationRejected:
            	nameUids = "NCTSDeclarationRejected";
                 break;
//            // MS20110512 Begin
//            case NCTSWriteOffNotification:
//            	 nameUids = "NCTSWriteOffNotification";
//                 break;
//            // MS20110512 End
            case InternalStatusInformation:
            	nameUids = "InternalStatusInformation";
                 break;
            case localAppResult:
            	nameUids = "Failure";
            	// important note: in mapping the message localAppResult from Kids to Uids
            	// in the class MapLocalAppKU the UIDS-Name is overwritten  depending on
            	// the content (error existing or not)
            	break;
            // MS20110512 Begin
            // default: nameUids = "";
            default: nameUids = nameKids;
            // MS20110512 End
			}
		break;
		
		case MANIFEST:
			switch (EKidsManifestMessages.valueOf(nameKids)) {
				case GoodsReleasedExternal: 
					nameUids = "GoodsReleasedExternal";
					break;
				case GoodsReleasedInternal: 
					nameUids = "GoodsReleasedInternal";
					break;
				case localAppResult:
					nameUids = "Failure";
					break;
				default: nameUids = nameKids;
			}
		break;

		
		default: Utils.log("Utils.getUidsMsgFromKidsMsg() : Procedure " + procedure + " not defined here");

		}
		return nameUids;
	}

	/**
     * Gibt den Namen einer als File übergebenen Datei ohne Pfad und Endung zurück.
     *
     * @param file Ein File-Objekt.
     * @return Der Dateiname aus dem File-Objekt ohne Pfad und Extension.
     */
    public static String getFileBasename(File file) {
        String filename = file.getName();
        String basename = filename;
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            basename = filename.substring(0, lastDot);
        }
        return basename;
    }
    /**
     * Entfernt einen/mehrere Leerzeichen in einem String.
     *
     * @param str die Zeichenkette die zu bearbeiten ist.
     * @return String die Ergebnis-Zeichenkette.
     */
    public static String getTrimmed(String str) {
    	if (str == null) {    //EI20120124: because of NullPointerException
    		return null;
    	}
        StringBuffer buf = new StringBuffer();

    	for (int i = 0; i < str.length(); i++) {
    		if (str.charAt(i) != ' ') {
    			buf.append(str.charAt(i));
    		}
    	}
    	return buf.toString();
    }
    
    public static void processException(String errorMessage, Throwable throwable, String fileName, String correlationID) {
        if (!Config.getMailSendFlag()) {
            throw new KcxMappingException("(Utils processException) Error processing file " + fileName, throwable);
        }
        if (fileName != null) {
            new ErrorMail().send(throwable, errorMessage + throwable, fileName);
            throw new KcxMappingException("(Utils processException) Error processing file " + fileName, throwable);
        } else if (correlationID != null) {
            new ErrorMail().send(throwable, errorMessage + throwable, correlationID);
            throw new KcxMappingException("(Utils processException) Error processing message " + correlationID, throwable);
        } else {
            new ErrorMail().send(throwable, errorMessage + throwable);
            throw new KcxMappingException("(Utils processException) Error processing message.", throwable);
        }
    }
    
    
    public static void processException(String errorMessage, Throwable throwable, String message, String fileName, String correlationID) {
        if (!Config.getMailSendFlag()) {
            throw new KcxMappingException("(Utils processException) Error processing file " + fileName, throwable);
        }
        if (fileName != null) {
            new ErrorMail().send(throwable, errorMessage + throwable, message, fileName);
            throw new KcxMappingException("(Utils processException) Error processing file " + fileName, throwable);
        } else if (correlationID != null) {
            new ErrorMail().send(throwable, errorMessage + throwable, message, correlationID);
            throw new KcxMappingException("(Utils processException) Error processing message " + correlationID, throwable);
        } else {
            new ErrorMail().send(throwable, errorMessage + throwable, message);
            throw new KcxMappingException("(Utils processException) Error processing message.", throwable);
        }
    }
    
    
    /**
     * Generate a message ID with a defined length.
     * The message ID alsway has a prefix of "KWL".
     * The prefix ("KWL") is followed by a running number.
     * The running number has a maximum length of 9 digits.
     * This means that the minimum length of the generated message ID is 3 plus length of the running number.
     * The running number is prefixed by leading zeroes until maxLen - 3 is reached.
     * This means that (provided the running number reaches its maximum length of 9 bytes) the message id cannot be
     * less that 12 bytes.
     * 
     * @param maxLen Message ID length.
     * @return The generated message ID.
     */
    public static String generateMessageId(int maxLen) {
        long runningNumber = Db.getMessageIdRunningNumber();
        String runningString = Utils.nFormat(runningNumber, maxLen - 3);
        String messageIdGen = "KWL" + runningString;
        
        return messageIdGen;
    }
    
    /**
     * 
     * Method that checks if the message belonging to this header should be refused by KCX.
     * This can be necessary if a special messagetype cannot be processed by a partner
     * 
     * At the moment this is the case with
     *  
     * MessageName ArrivalNotification
     * Procedure ICS
     * Country IT
     * Directin FROM_CUSTOMER
     * 
     * 19.10.2011
     * @param header Header der Nachricht.
     * @return true/false.
     */
    public static boolean refuseMessage(KidsHeader header) {
        
        return refuseMessage(header.getMessageName(), header.getCountryCode(), header.getDirection());
        
    }  
    /**
     * 
     * Method that checks if the message belonging to this header should be refused by KCX.
     * This can be necessary if a special messagetype cannot be processed by a partner
     * 
     * At the moment this is the case with
     *  
     * MessageName ArrivalNotification
     * Procedure ICS
     * Country IT
     * Directin FROM_CUSTOMER
     * 
     * 19.10.2011
     * @param header Header der Nachricht.
     * @return true/false.
     */
    public static boolean refuseMessage(UidsHeader header) {
        
        return refuseMessage(header.getMessageName(), header.getCountryCode(), header.getDirection());
        
    }    
    /**
     * 
     * Method that checks if the message belonging to this header should be refused by KCX.
     * This can be necessary if a special messagetype cannot be processed by a partner
     * 
     * At the moment this is the case with
     *  
     * MessageName ArrivalNotification
     * Procedure ICS
     * Country IT
     * Directin FROM_CUSTOMER
     * 
     * 19.10.2011
     * @param  msg Messagename.
     * @param  country Länderkennzeichen.
     * @param  direction Richtung.
     * @return true/false.
     */
    public static boolean refuseMessage(String msg, String country, String direction) {
        // the definitions about which messages shuld not be processed have to be defined
    	// in a ini-file or in the DB if there will be more combinations in future !!
    	// Christine Kron 19.10.2011
    	// Arrivals and Diversions nach GR abklemmen CK 19.12.2011
        if (msg.equalsIgnoreCase("ICSArrivalNotification") &&
        		country.equalsIgnoreCase("IT") && 
        		direction.equalsIgnoreCase("FROM_CUSTOMER")) {
        	return true;
        }
        if (msg.equalsIgnoreCase("ICSArrivalNotification") &&
        		country.equalsIgnoreCase("GR") && 
        		direction.equalsIgnoreCase("FROM_CUSTOMER")) {
        	return true;
        }
        if (msg.equalsIgnoreCase("ICSArrivalNotification") &&
        		country.equalsIgnoreCase("ES") && 
        		direction.equalsIgnoreCase("FROM_CUSTOMER")) {
        	return true;
        }
        if (msg.equalsIgnoreCase("ICSDiversionRequest") &&
        		country.equalsIgnoreCase("GR") && 
        		direction.equalsIgnoreCase("FROM_CUSTOMER")) {
        	return true;
        }
        // 27.06.2012
        if (msg.equalsIgnoreCase("ICSArrivalNotification") &&
        		country.equalsIgnoreCase("PT") && 
        		direction.equalsIgnoreCase("FROM_CUSTOMER")) {
        	return true;
        }
        //EI20130830:
        if (msg.equalsIgnoreCase("ICSArrivalNotification") &&
        		country.equalsIgnoreCase("SE") && 
        		direction.equalsIgnoreCase("FROM_CUSTOMER")) {
        	return true;
        }
        // default 
       	return false;	
        
        
    }    

    /*****************************************************************************
     * Methode     : addZabisDecimalPlace.
     * Beschreibung: deaktiviert !! Kunden sollen NK-stellen bereits angehängt liefern
     * 				 ohne Dezimaltrenner - wie in Zabis gefordert
     * 				 Umwandlung eines Betrages im Format Decimal in ein ZABIS-FSS-Format 
     * 				 mit vorgegebenen Nachkommastellen ohne Decimaltrenner 
     * Autor       : Christine Kron
     * Erstellt:   : 26.10.2011
     * deaktiviert : 02.12.2011
     * @param        amount 		Betrag im Format "decimal" also ggfs. mit Decimalpunkt
     * @param        nk 			Anzahl der Nachkommastellen
     * @return       Zeichenkette 	die den Betrag darstellt
     *****************************************************************************/
    public static String addZabisDecimalPlace(String amount, int nk) {
    	return amount;
//    	double d = 0;
//        if (amount == null) {
//            return "";
//        } 
//        if (amount == "") {
//        	return "";
//        } else {
//        	
//			try {
//				d = Double.parseDouble(amount);
//			} catch (NumberFormatException e) {
//				log("amount " + amount);
//				e.printStackTrace();
//			}
//
//            while (nk != 0) {
//                    d = d * 10;              
//                    nk--;
//                }
//            BigDecimal newDec = new BigDecimal(d);
//            newDec = newDec.setScale(0, BigDecimal.ROUND_HALF_UP);
//            return removeDots(newDec.toString());
//        }
    }
    /*****************************************************************************
     * Methode     : addZabisDecimalPlace.
     * Beschreibung: Umwandlung eines Betrages im Format Decimal in ein ZABIS-FSS-Format 
     * 				 mit vorgegebenen Nachkommastellen ohne Decimaltrenner 
     * Autor       : Christine Kron
     * Erstellt:   : 26.10.2011
     * @param        amount 		Betrag im Format "decimal" also ggfs. mit Decimalpunkt
     * @param        nk 			Anzahl der Nachkommastellen
     * @return       Zeichenkette 	die den Betrag darstellt
     *****************************************************************************/
    public static String addZabisDecimalPlaceV7(String amount, int nk) {

    	double d = 0;
        if (amount == null) {
            return "";
        } 
        if (amount == "") {
        	return "";
        } else {
        	
			try {
				d = Double.parseDouble(amount);
			} catch (NumberFormatException e) {
				log("amount " + amount);
				e.printStackTrace();
			}

            while (nk != 0) {
                    d = d * 10;              
                    nk--;
                }
            BigDecimal newDec = new BigDecimal(d);
            newDec = newDec.setScale(0, BigDecimal.ROUND_HALF_UP);
            return removeDots(newDec.toString());
        }
    }
    /*****************************************************************************
     * Methode     : removeZabisDecimalPlace.
     * Beschreibung: Umwandlung eines Betrages im ZABIS-FSS-Format mit vorgegebenen 
     * 				 Nachkommastellen (virtuellem Komma) in ein Decimalformat mit dem 
     * 				 Punkt als Decimaltrenner. 
     * Autor       : Christine Kron
     * Erstellt:   : 27.08.2012
     * @param        amount 		Betrag im Zabis-Decimal-Format 
     * @param        nk 			Anzahl der Nachkommastellen
     * @return       Zeichenkette 	die den Betrag mit Decimalpunkt darstellt
     *****************************************************************************/
    public static String removeZabisDecimalPlaceV7(String amount, int nk) {

    	double zabiswert;
    	double xmlwert;
    	int nkorg = nk;
    	
        if (amount == null) {
            return "";
        } 
        if (amount == "") {
        	return "";
        } else {
        	
			try {
				zabiswert = Double.parseDouble(amount);
				
			} catch (NumberFormatException e) {
				log("amount " + amount);
				e.printStackTrace();
				return "";
			}

			xmlwert = zabiswert;
            while (nk != 0) {
                    xmlwert = (xmlwert / 10);
                    nk--;
                }
            log("Zabiswert: " + amount + " NK " + nkorg + " decimal Wert " + xmlwert);
            
            DecimalFormat df1 =   new DecimalFormat("##0.0", new DecimalFormatSymbols(Locale.US));
            DecimalFormat df2 =   new DecimalFormat("##0.0#", new DecimalFormatSymbols(Locale.US));
            DecimalFormat df3 =   new DecimalFormat("##0.0##", new DecimalFormatSymbols(Locale.US));
            DecimalFormat df4 =   new DecimalFormat("##0.0###", new DecimalFormatSymbols(Locale.US));
            
            // System.out.println(   df.format(xmlwert));
            if (nkorg == 1) {
            	log("DecimalFormat ##0.0    " + df1.format(xmlwert));
            	return df1.format(xmlwert);
            }  
            if (nkorg == 2) {
            	log("DecimalFormat ##0.0#    " + df2.format(xmlwert));
            	return df2.format(xmlwert);
            }  
            if (nkorg == 3) {
            	log("DecimalFormat ##0.0##    " + df3.format(xmlwert));
            	return df4.format(xmlwert);
            }  
            if (nkorg == 4) {
            	log("DecimalFormat ##0.0###    " + df4.format(xmlwert));
            	return df4.format(xmlwert);
            }  
            
            
         return "" + xmlwert;
            
        }
    }
    
    /*****************************************************************************
     * Methode     : getTimeZone.
     * Beschreibung: gibt die TimeZone des aktuellen host in Stunden zurück zu einem angegebenen Datum 
     * Autor       : Christine Kron
     * Erstellt:   : 30.11.2011
     * @param        date 		Datum zu dem man die TZ erfragt im Format YYYYMMDDhhmmss
     * @return       TZ in Stunden
     *****************************************************************************/
    
    public static int getTimeZone(String date) {
       
    DateFormat YYYYMMDDHHMISS = new SimpleDateFormat("yyyyMMddHHmmss");
    
    long datelong = 0;
    
	try {
		datelong = YYYYMMDDHHMISS.parse(date).getTime();
	} catch (ParseException e) {
		log("Utils.getTimeZone() : Date " + date + " not valid!");
		e.printStackTrace();
	} 
	
	// Definiert die TimeZone des aktuellen Computers
	TimeZone timeZone = TimeZone.getDefault();

	// timezone offset wird in millisekunden zurückgegeben: hier Umrechnung in Stunden
	return timeZone.getOffset(datelong) / 3600000;

    }    
/* EI20111103: 
    public static String replaceRefnr(String refnrOrg, String kcxId) {
        if (isStringEmpty(refnrOrg)) {
        	return "";
        }    	
        String newRefnr = "";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());  // 2007-08-08 09:00:00.123456789              
        String ts = Utils.timestampToString(timestamp);
        
        String app = "";
        if (ts != null && ts.length() > 13) {
        	app = ts.substring(6, 14);
        } 
        	
        if (refnrOrg.length() > 13) {
        	newRefnr = refnrOrg.substring(0, 13) + "-" + app;
        } else {
        	newRefnr = refnrOrg + "-" + app;
        }
        	
        if (Db.replaceRefnr(kcxId, refnrOrg, newRefnr)) {
        	return newRefnr;
        } else {
        	return refnrOrg;
        }
    }               
*/  
    
    public static EFormat getKidsDateAndTimeFormat(String value) {  //EI20121022
		EFormat eFormat = null;
		 if (Utils.isStringEmpty(value)) {
			return null; 
		 }
		 int len = value.length();
		 if (len == 8) {
			 eFormat = EFormat.KIDS_Date;
		 } else if (len == 12) {
			 eFormat = EFormat.KIDS_DateTime;
		 } else {
			 eFormat = EFormat.KIDS_DateTime; 
		 }
		return eFormat;
	}
    public static EFormat getUidsDateAndTimeFormat(String value) {  //EI20121022
		EFormat eFormat = null;
		if (Utils.isStringEmpty(value)) {
				return null; 
		}
		int len = value.length();
		 
		if (len == 10) {
			 eFormat = EFormat.ST_Date;
		} else if (len > 11) {
			 if (!value.substring(10, 11).equals("T")) {
				 eFormat = EFormat.ST_DateTime;
			 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
				 eFormat = EFormat.ST_DateTimeT;
			 } else {
				 eFormat = EFormat.ST_DateTimeTZ;
			 }
		}
		 
		return eFormat;
	}  
    public static boolean isSignNumeric(String sign) {   //EI20131017
    	boolean ret = false;
    	if (Utils.isStringEmpty(sign)) {
    		return false;
    	}
    	if (sign.equals("0") || sign.equals("1") || sign.equals("2") || sign.equals("3") || sign.equals("4") ||
    		sign.equals("5") || sign.equals("6") || sign.equals("7") || sign.equals("8") || sign.equals("9")) {
    		ret = true;
    	}  
    	return ret;
    }
    public static boolean isNumeric(String nr) {                    //I20140527
    	return !isStringEmpty(nr) && nr.matches("[0-9]+");
    }
    public static boolean isStringNumeric(String nr) {                    //I20140704
    	return !isStringEmpty(nr) && nr.matches("[0-9]+");
    }
    public static boolean isStringAlpha(String str) { //I20140704
    	boolean ret = true;
    	if (isStringEmpty(str)) {
    		return false;
    	}
    	int len = str.length();
    	for (int i = 0; i < len; i++) {
			if (isSignNumeric(str.substring(i, i + 1))) {
				ret = false;
				break;
			}
		}
    	return ret;
    }
}
