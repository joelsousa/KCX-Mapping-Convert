/*
 * Funktion    : PdfDecode.java
 * Titel       :
 * Erstellt    : 08.05.2009
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
package com.kewill.kcx.tools.XLSReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		: PdfDecode<br>
 * Erstellt		: 08.05.2009<br>
 * Beschreibung	: Sammelt alle base64-Tags einer XML-Datei, dekodiert sie
 *                und schreibt das Ergebnis in eine Datei. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class PdfDecode {

    public static void main(String[] args) {
        PdfDecode decode = new PdfDecode();
        decode.run(args);
    }
    
    public void run(String[] args) {
        String pdfFileName = null;
        String xmlFileName = null;
        if (args.length == 1) {
            pdfFileName = args[0] + ".pdf";
            xmlFileName = args[0] + ".xml";
        } else {
            if (args.length == 2) {
                pdfFileName = args[1];
                pdfFileName = args[0] + ".xml";
            } else {
                pdfFileName = args[1];
                xmlFileName = args[2];
            }
        }
        
        try {
            FileInputStream   fis = new FileInputStream(args[0]);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader    br  = new BufferedReader(isr); 
            
            StringBuffer pdfBuffer = new StringBuffer();
            StringBuffer xmlBuffer = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                String[] tokens = line.split("[<>]");
                for (int i = 0; i < tokens.length; i++) {
                    Utils.log("(PdfDecode run) tokens[" + i + "] = " + tokens[i]);
                    if (tokens[i].equalsIgnoreCase("base64")) {
                        pdfBuffer.append(tokens[i + 1]);
                    } else {
                        xmlBuffer.append(tokens[i]);
                    }
                }
                line = br.readLine();
            }
            br.close();
            
            byte[] out = Base64.decodeBase64(pdfBuffer.toString().getBytes());
            FileOutputStream fos = new FileOutputStream(pdfFileName);
            fos.write(out);
            fos.close();
            
            out = xmlBuffer.toString().getBytes();
            fos = new FileOutputStream(xmlFileName);
            fos.write(out);
            fos.close();
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
