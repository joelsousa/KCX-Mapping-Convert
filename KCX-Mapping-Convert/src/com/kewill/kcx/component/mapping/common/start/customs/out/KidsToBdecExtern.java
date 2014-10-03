/*
 * Funktion    : KidsToBdecExtern.java
 * Titel       :
 * Erstellt    : 03.11.2009
 * Author      : CSF GmbH / iwaniuk
 * Beschreibung: KidsToFss ohne MULE-Abhängigkeiten.
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
package com.kewill.kcx.component.mapping.common.start.customs.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToBdecConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : KidsToBdecExtern<br>
 * Erstellt     : 03.11.2009<br>
 * Beschreibung : KidsToBdec ohne MULE-Abhängigkeiten. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToBdecExtern extends KidsToBdecConverter {

    public String convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        message = message.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(KidsToBdecExtern convert) KIDS message = \n" + message);
        }
        // CK20110803
        // String bdec = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        String bdec = null;
        try {
        	bdec = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToBdecExtern convert) Exception ", e, outFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KidsToBdecExtern convert) Error ", e, outFile.getName(), null);
        }
        
        if (Config.getLogXML()) {
            Utils.log("(KidsToBdecExtern convert) ECustoms Message = \n" + bdec);
        }
        
        writeFileMessage(bdec, outFile, encoding);
        
        return bdec;
    }

    private void writeFileMessage(String message, File outFile, String encoding) throws Exception {
        Utils.log("(KidsToBdecExtern writeFileMessage) fileName = " + outFile.getName());
        try {
            FileOutputStream fos = new FileOutputStream(outFile);
            OutputStreamWriter out = new OutputStreamWriter(fos, encoding);
            out.write(message);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
}
