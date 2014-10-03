package com.kewill.kcx.component.mapping.formats.fss.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Oft gebrauchte, allgemein gültige Methoden.
 * Datum 15.03.2004
 * 
 * @author Michael Schmidt
 * @version 1.0.00 
 */
public final class FssUtils {
    /**
     * Zeilenvorschub (LF, CR+LF).
     */
	public static final String LF = System.getProperty("line.separator", "\n");	// Zeilentrenner
	/**
	 * Trennzeichen zwischen Dateipfadelementen ("/", "\").
	 */
    public static final String FS = System.getProperty("file.separator", "/");   // Dateitrenner
    /**
     * Zeitstempelformat (dd.MM.yyyy_HH:mm:ss).
     */
	public static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");
	/**
	 * FSS Feldtrenner (0x1D).
	 */
	public  static final char FSS_FS  	= 0x1D;
	/**
	 *  FSS Feldtrenner in VORINF Teil 2: das Ausrufezeichen!
	 *  CK090505
	 */
	public static final char FSS_FS2 = 0x21;

	
	private FssUtils() { 
	}
	
	
	/**
	 * Alle leeren Felder des StringBuffer-Arrays mit Leerzeichen 
	 * in der definierten Länge des jeweiligen StringBuffer füllen.
	 *   
	 * @param sb StringBuffer-Array mit den vorzubelegenden Feldern 
	 */
	public static void padLine(StringBuffer[] sb) {
		for (int i = 0; i < sb.length; i++) {
			if (sb[i].length() == 0) {
				for (int j = 0; j < sb[i].capacity(); j++) {
					sb[i].append(' ');
				}
			}
		}
	}

	public static String createLine(StringBuffer[] line, String[] fields) {
		setLine(line, fields);
		return getLine(line);
	}

	/**
	 * Die Felder eines StringBuffer-Arrays mit Werten aus einem String-Array füllen.
	 * 
	 * Ist die Länge des Quellfeldes größer als die Länge des Zielfeldes, so wird
	 * das Quellfeld nur in der Länge des Zielfeldes übertragen, also abgeschnitten.
	 * 
	 * @param dest	StringBuffer-Array in dessen Elemente die Quellstrings geschrieben werden. 
	 * @param src	String-Array aus denen die Zielelemente des StringBuffer-Array generiert werden.
	 */
	public static void setLine(StringBuffer[] dest, String[] src) {
		for (int i = 0; i < src.length; i++) {
			if (src[i] != null) {
				if (src[i].length() > dest[i].length()) { 
					dest[i].replace(0, dest[i].length(), src[i].substring(0, dest[i].length()));
				} else {
					dest[i].replace(0, src[i].length(), src[i]);
				}
			}
		}
	}
	
	/**
	 * Aus einem StringBuffer-Array wird ein String gemacht.
	 * 
	 * @param line Das zu konvertierende StringBuffer-Array
	 * @return Ein aus den Elementen des StringBuffer-Array zusammengesetzer String
	 */
	public static String getLine(StringBuffer[] line) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < line.length; i++) {
			sb.append(line[i]);
		}
		sb.append(LF);
		return sb.toString();
	}
	
	/**
	 * Generiert ein Tagesdatum im Format "YYYYMMDD".
	 * @return Tagesdatum als String im Format "YYYYMMDD"
	 */
	public static String getToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}
	
    /**
     * Generiert eine Tageszeit im Format "HHMMSS".
     * 
     * @return Aktuelle Zeit als String im Format "HHMMSS"
     */
    public static String getNow() {
        // Das Formatierzeichen 'h' liefert die Stunden 1-12
        // Das Formatierzeichen 'H' liefert die Stunden 0-23
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        return formatter.format(new Date());
    }
    
    /**
     * Generiert eine Tagesdatum + Zeit im Format "YYYYMMDDHHmmss".
     * 
     * @return Aktuelles Datum+Zeit als String im Format "YYYYMMDDHHmmss"
     * 
     * @author Christine Kron
     * 
     * benötigt für Feld datzei im neuen HEADER
     */
    public static String getDateTime() {
        // Das Formatierzeichen 'h' liefert die Stunden 1-12
        // Das Formatierzeichen 'H' liefert die Stunden 0-23
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date());
    }

	/**
	 * Eine Zeile mit Zeitstempel auf stdout ausgeben.
	 * 
	 * @param msg     Nachricht die ausgegeben werden soll.
	 * @param omitCR Nachricht ohne Zeilevorschub ausgeben
	 */
	public static void log(String msg, boolean omitCR) {
		String dat = SDF.format(new Date());
		if (omitCR) {
			System.out.print(dat + ": " + msg);
		} else {
			System.out.println(dat + ": " + msg);
		}
	}
	
    /*****************************************************************************
     * Methode     : getDebug
     * Beschreibung: Property <code>debug</code> ermitteln.
     * Autor       : MS
     * Erstellt:   : 10.10.2004
     * 
     * @return boolean Ist Debugging ein oder aus?
     *****************************************************************************/
    public static boolean getDebug() {

    	return (System.getProperty("debug", "false").equals("true")) ? true : false;
    }
    	
    /*****************************************************************************
     * Methode     : reformateCSFDateTime
     * Beschreibung: Umwandlung  eines Datums.  
     *               CSF-Format: YYYYMMDDHHMM oder YYYYMMDD in
     *               XML-Format: YYYY-MM-DDTHH:MM:SS(+HH:MM|Z) oder YYYY-MM-DD               
     * Autor        : Kilian Braune
     * @param        csfdate - Das Datum im Format der CSF-Datei
     * @return       String xmldate - XML Datumsformat
     *****************************************************************************/
       public static String reformateCSFDateTime(String csfdate) {
           String xmldate        = "";              
           if (csfdate.length() == 12) {
               SimpleDateFormat sdfCSF = new SimpleDateFormat("yyyyMMddHHmm");
               SimpleDateFormat sdfXML = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss' 'Z");
               try {
                   xmldate = sdfXML.format(sdfCSF.parse(csfdate));
               } catch (Exception e) {
                   return "";
               }
           }
           if (csfdate.length() == 8) {
               SimpleDateFormat sdfCSF = new SimpleDateFormat("yyyyMMdd");
               SimpleDateFormat sdfXML = new SimpleDateFormat("yyyy-MM-dd");
               try {
                   xmldate = sdfXML.format(sdfCSF.parse(csfdate));
               } catch (Exception e) {
                   return "";
               }
           }
           
           return xmldate;                                     
       } 
       
       /*****************************************************************************
        * Methode     : reformateCSFDate
        * Beschreibung: Umwandlung  eines Datums.  
        *               CSF-Format: DD.MM.YYYY in
        *               XML-Format: YYYY-MM-DD               
        * Autor        : Kilian Braune
        * @param        csfdate - Das Datum im Format der CSF-Datei
        * @return       String xmldate - XML Datumsformat
        *****************************************************************************/
       
       public static String reformateCSFDate(String csfdate) {

           String xmldate        = ""; 
       
           if (csfdate.length() == 10 && csfdate.substring(2, 3).equals(".") && csfdate.substring(5, 6).equals(".")) {
               
               SimpleDateFormat sdfCSF = new SimpleDateFormat("dd.MM.yyyy");
               SimpleDateFormat sdfXML = new SimpleDateFormat("yyyy-MM-dd");
               
               try {
                   xmldate = sdfXML.format(sdfCSF.parse(csfdate));
               } catch (Exception e) {
                   return "";
               }
               
           }
           
           if (csfdate.length() == 8) {
        	   StringBuffer tmp = new StringBuffer();
        	   tmp.append(csfdate.substring(0, 4));
        	   tmp.append("-");
        	   tmp.append(csfdate.substring(4, 6));
        	   tmp.append("-");
        	   tmp.append(csfdate.substring(6));
        	   xmldate = tmp.toString();
           }
           
           return xmldate;                                     
       } 
       
       /*****************************************************************************
        * Methode     : isOsName
        * Beschreibung: Property <code>os.name</code> ermitteln.
        * Autor       : KB
        * Erstellt:   : 28.07.2005
        * 
        * @param osName Das OS, auf das abgefragt werden soll
        * @param i      Bis zu welchem char des ausgelesenen Wertes wird verglichen 
        * @return boolean Handelt es sich um das gefragte Betriebssystem?
        *****************************************************************************/
       public static boolean isOsName(String osName, int i) {
           String osPart = System.getProperty("os.name").toLowerCase().substring(0, i);
           return (osPart.equals(osName.toLowerCase()));
       }
       

       /*****************************************************************************
        * Methode     : log
        * Beschreibung: Eine Zeile mit Zeitstemplel auf Stdout ausgeben.
        * Autor       : MS
        * Erstellt:   : 10.10.2004
        * 
        * @param msg   Nachricht die ausgegeben werden soll.
        *****************************************************************************/
       public static void log(String msg) {
           
           String dat = SDF.format(new Date());
           System.out.println(dat + ": " + msg);
       }
       
       /*****************************************************************************
        * Methode     : getNumber
        * Beschreibung: Einen String auf "(alpha)numeric" prüfen und bei 
        *               "numerisch" fuehrende Nullen loeschen.
        * Autor       : KB
        * Erstellt:   : 24.11.2004
        * 
        * @param string Zu bearbeitender String.  
        * @return String String ohne führenden Nullen.
        *****************************************************************************/
       public static String getNumer(String string) {
           try {
               return String.valueOf(Integer.parseInt(string));
           } catch (NumberFormatException e) {
               return string;
           }
       } 
       
       /*****************************************************************************
        * Methode     : splitLine
        * Beschreibung: Aufsplitten eines Strings anhand eines Delimiter-Zeichens.
        * Christine Kron
        * 
        * @author kron
        * Erstellt:   : 15.12.2004
        * 
        * @param line Der aufzuteilende String 
        * @param delim Das Feldbegrenzungszeichen an dem der String aufgeteilt wird.
        * @return String[] String-Array mit den aufgeteilten Feldern
        *****************************************************************************/
       public static String[] splitLine(String line, char delim) {
           int numdelim = 0;
           for (int i = 0; i < line.length(); i++) {
               if (line.charAt(i) == delim) {
                   numdelim++;
               }
           }

           int numFields = numdelim + 1;
           String[] fields = new String[numFields];
           int position = 0;
           for (int i = 0; i < numFields; i++) {
               StringBuffer field = new StringBuffer();
               while (position < line.length()
               && line.charAt(position++) != delim) {
                   field.append(line.charAt(position - 1));
               }
               fields[i] = field.toString();
           }
           return fields;
//           return line.split(String.valueOf(delim));
       }

       /*****************************************************************************
        * Methode     : parseInt
        * Beschreibung: Einen <code>String</code> in einen <code>int</code> konvertieren. 
        *               Kann der String nicht konvertiert werden, wird eine Null (<code>0</code>  
        *               zurückgeliefert.
        * Autor       : Michael Schmidt
        * Erstellt:   : 10.03.2004
        * @param        s      der zu konvertierende <code>String</code>.
        * @return       Konvertierter String oder 0.
        *****************************************************************************/
       public static int parseInt(String s) {
           try {
               return Integer.parseInt(s);
           } catch (NumberFormatException e) {
               return 0;
           }
       }
       /*****************************************************************************
        * Methode     : nformat
        * Beschreibung: Konvertiert eine Zahl vom Typ <code>long</code> in einen String 
        *               der übergebenen Länge mit führenden Nullen. 
        * Autor       : Michael Schmidt
        * Erstellt:   : 16.03.2004
        * @param        number Zu formatiernde Zahl
        * @param        flen   Länge des Zielstrings
        * @return       Mit führenden Nullen aufgefüllte Zahl als String
        *****************************************************************************/
       public static String nformat(long number, int flen) {
           StringBuffer sb   = new StringBuffer();
           String       num  = Long.toString(number);  // In String wandeln um Länge zu ermitteln
           int          nlen = num.length();           // Länge ermitteln
           int          dif  = flen - nlen;            // Benötigte Anzahl Vornullen ermitteln

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
        * Methode     : checkNull
        * Beschreibung: Einen <code>String</code> auf null prüfen
        *               und Rückgabe Leerstring sonst den String-Parameter selbst.
        *               
        * Autor       : CK/MS
        * Erstellt:   : 01.04.2005
        * @param        s      der String
        * @return       String oder Leerstring.
        *****************************************************************************/
       public static String checkNull(String s) {
           if (s == null) {
               return "";
           } else {
               return s;
           }
       }
       
       /*****************************************************************************
        * Methode     : getCsfDate
        * Beschreibung: Umwandlung  eines Datums.  
        *               XML-Format: YYYY-MM-DD
        *               CSF-Format: YYYYMMDD 
        * Autor        : Christine Kron
        * @param        xmldatum Das Datum im Format der XML-Datei
        * @return       String CSF-Datumsformat
        *****************************************************************************/
       public static String getCsfDate(String xmldatum) {

           if (xmldatum == null) {
               return "";
           }

           if (xmldatum.length() != 10) {
               return "";
           }

           return xmldatum.substring(0, 4)
               + xmldatum.substring(5, 7)
               + xmldatum.substring(8, 10);
       }
       
       /*****************************************************************************
        * Methode     : getStringWithoutDot
        * Beschreibung: Entfernung eines(!) Punktes in einer Zeichenkette 
        *               wird verwendet wenn Zahlenwerte mit Komma (= Punkt) ohne
        *               Komma dargestellt werden sollen. 
        * Autor       : Christine Kron
        * Erstellt:   : 24.03.2005
        * @param        text Zeichenkette mit Punkt
        * @return       Zeichenkette ohne Punkt
        *****************************************************************************/
          public static String getStringWithoutDot(String text) {
               String  str1    = "";
               String  str2    = "";
               String  retStr  = "";
               int     len     = -1;
               int     pos     = -1;

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
        * Methode     : reformateXMLDate
        * Beschreibung: Umwandlung  eines Datums.  
        *               XML-Format: YYYY-MM-DDTHH:MM:SS(+HH:MM|Z) in
        *               CSF-Format: YYYYMMDD
        * Autor        : Kilian Braune
        * @param        xmldate - Das Datum im Format der XML-Datei
        * @return       String csfDate - CSF Datumsformat
        *****************************************************************************/

          public static String reformateXMLDate(String xmldate) {
              
              String csfDate  = null;
              
              if (xmldate == null || xmldate.length() < 10) {
                  return "";
              }
              
              try {
                  csfDate = xmldate.substring(0, 4) + xmldate.substring(5, 7) + xmldate.substring(8, 10);
                  return csfDate;
               
              } catch (Exception e) {
               return "";
              }
           }
          
      /*****************************************************************************
       * Methode     : reformateXMLDateTime
       * Beschreibung: Umwandlung  eines Datums.  
       *               XML-Format: YYYY-MM-DDTHH:MM:SS(+HH:MM|Z)in
       *               CSF-Format: YYYYMMDDHHMM
       * Autor        : Kilian Braune
       * @param        xmldate - Das Datum im Format der XML-Datei
       * @return       String csfDateTime - CSF Datumsformat
       *****************************************************************************/

          public static String reformateXMLDateTime(String xmldate) {

             String csfDate        = null;
             String csfTime        = null;
             String csfDateTime    = null;
             
             if (xmldate == null || xmldate.length() < 16) {
                 return "";
             }
              
             try {
                 csfDate = xmldate.substring(0, 4) + xmldate.substring(5, 7) + xmldate.substring(8, 10);
                 csfTime = xmldate.substring(11, 13) + xmldate.substring(14, 16);
                 
                 csfDateTime = csfDate + csfTime;
                 return csfDateTime;
              
             } catch (Exception e) {
              return "";
             }
          }  
         
      
       /*****************************************************************************
        * Methode     : removeDecimalPoint
        * Beschreibung: Entfernung des Dezimaltrenners aus einem String. Des weiteren
        *               werden Nachkommastellen hinzugefügt, wenn der Kunde nicht 
        *               ausreichend (oder keine) liefert ODER es werden 
        *               Nachkommastellen entfernt, wenn zu viele geliefert werden (ohne
        *               Rundung)
        *               Es wird sowohl der Punkt als auch das Komma als Dezimaltrenner
        *               verarbeitet. 
        * Autor       : JS
        * Erstellt:   : 21.09.2006
        * @param        s Zeichenkette mit Dezimaltrenner 
        * @param        nachkomma Anzahl der Nachkommastellen 
        * @return       Zeichenkette ohne Dezimaltrenner
        *****************************************************************************/
       
       public static String removeDecimalPoint(String s, int nachkomma) {
           String dp = "";                 // decimal point
           int i = 0;
           int j = 0;
             
           if (s.indexOf(",") > 0) {
               dp = "[,]";
           } else {
               if (s.indexOf(".") > 0) {
                   dp = "[.]";
               } else {
                   dp = "";
               }
           }
           
//           if (!(dp == "")) {
           if (!(dp.equals(""))) {
               String[] s1 = s.split(dp);
               j = s1[1].length();
               if (j < nachkomma) {        // zu wenig Nachkommastellen -> 0en anhängen
                   for (i = 0; i < (nachkomma - j); i++) {
                       s = s.concat("0");
                   } 
               } else {
                   if (j > nachkomma) {    // zu viele Nachkommastellen -> abschneiden
                       s = s.substring(0, s.length() - (j - nachkomma));
                   }
               }
               s = s.replace(dp.substring(1, 2), "");  // Dezimaltrenner immer entfernen
           } else {                        // ist kein Dezimaltrenner im String, müssen 0en angehängt werden
               for (i = 0; i < nachkomma; i++) {
                   s = s.concat("0");
               }
           }
           return s;

       }
       
}
