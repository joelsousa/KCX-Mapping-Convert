/*
 * Funktion    : PdfReader.java
 * Titel       :
 * Erstellt    : 06.08.2010
 * Author      : CSF GmbH / schmidt
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
package com.kewill.kcx.component.mapping.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.conf.Config;


/**
 * Modul		: PdfReader<br>
 * Erstellt		: 06.08.2010<br>
 * Beschreibung	: Read a PDF file from disk. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class PdfReader {

    /**
     * Read PDF file, encode BASE64 and return as a <code>List</code> of encoded lines.
     * 
     * @param path      Path to PDF file
     * @param filename  Name of PDF file
     * @return PDF as list of BASE64 encoded lines
     */
    public List<String> readPdf(String path, String filename) {
        File pdfFile = new File(path, filename);
        List<String> pdfList = new ArrayList<String>(); 
        try {
            
            // PDF in byte[] einlesen
            RandomAccessFile r = new RandomAccessFile(pdfFile, "r");
            byte[] in = new byte[(int) pdfFile.length()]; 
            r.read(in);
            
            // byte[] Base64 codieren
            byte[] out = Base64.encodeBase64Chunked(in);

            // Codierten String in die pdfList schreiben. 
            String str = new String(out);
            BufferedReader br = new BufferedReader(new StringReader(str));
            String line76 = null;
            while ((line76 = br.readLine()) != null)  {
                if (Config.getLogXML()) {
                    System.out.println(line76); 
                }
                pdfList.add(line76);
            }
        } catch (FileNotFoundException e) {
            Utils.log("(PdfReader readPdf) PDF file " + pdfFile.getPath() + " does not exist.");
        } catch (IOException e) {
            Utils.log("(PdfReader readPdf) IOException! Can not read PDF file " + pdfFile.getPath() + ".");
        }
        return pdfList;
    }
}
