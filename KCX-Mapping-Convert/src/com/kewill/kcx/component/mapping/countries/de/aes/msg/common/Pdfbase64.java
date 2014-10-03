/*
 * Funktion    : PdfBase64.java
 * Titel       :
 * Erstellt    : 18.06.2008
 * Author      : Christine Kron
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;

import org.apache.commons.codec.binary.Base64;

/**
 * Modul		: Pdfbase64<br>
 * Erstellt		: 18.06.2008<br>
 * Beschreibung	: -.
 * 
 * @author Kron
 * @version 1.0.00
 */
public class Pdfbase64 {
	
	public static void main(String[] args)  {
		try {
			if (args.length != 1) {
				System.err.println("Usage: java PdfBase64 [Path to the PDF Filename]");
				System.exit(-1);
			}
			new Pdfbase64().base64(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	public void base64(String pPdfFilename) throws Exception {
	    FileInputStream fis = null;
	    File filein = new File(pPdfFilename);
	    // Try to load the file and ecode it in base64
	    try {
	      if (!filein.exists()) {
	        throw new Exception("The pdf file " + filein.getAbsolutePath() + " is not available!");
	      }
	      fis = new FileInputStream(filein);
	      long l = filein.length();
	      byte[] buf = null;
	      if (l < Integer.MAX_VALUE) {
	        int i = new Long(l).intValue();
	        buf = new byte[i];
	      } else {
	        buf = new byte[Integer.MAX_VALUE];
	      }
	      int size = 0;
	      while ((size = fis.read(buf)) != -1) {
	        byte[] out = Base64.encodeBase64Chunked(buf);
	        String str = new String(out);
	        BufferedReader br = new BufferedReader(new StringReader(str));
	        String line76 = null;
		    while ((line76 = br.readLine()) != null) {
		    	// Hier in die XML datei schreiben
	          System.out.println(line76);	    
		    }
	      }
	    } catch (Exception e) {
	    	throw e;
	    } finally {
	       try {
	         if (fis != null) {
	             fis.close();
	         }
	       } catch (Exception e1) {
	       }
	   }
	}
	
}
