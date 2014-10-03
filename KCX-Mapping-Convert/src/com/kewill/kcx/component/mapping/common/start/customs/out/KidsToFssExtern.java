/*
 * Funktion    : KidsToFssExtern.java
 * Titel       :
 * Erstellt    : 01.09.2009
 * Author      : CSF GmbH / schmidt
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
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : UidsToKidsExtern<br>
 * Erstellt     : 01.09.2009<br>
 * Beschreibung : KidsToFss ohne MULE-Abhängigkeiten. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToFssExtern extends KidsToFssConverter {

    public String convert(String message, File outFile, String encoding) throws Exception {
        Utils.log("(KidsToFssExtern convert) outFile = " + outFile);
        Utils.log("(KidsToFssExtern convert) encoding = " + encoding);  //EI20130404
        if (!Config.isConfigured()) {
            Config.configure();
        }
        message = message.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(KidsToFssExtern convert) KIDS message = \n" + message);
        }
        
        // CK20110803
        // String fss = readKids(message, encoding, EDirections.CustomerToCountry);
        String fss = null;
        try {
        	fss = readKids(message, encoding, EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToFssExtern convert) Exception ", e, outFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KidsToFssExtern convert) Error ", e, outFile.getName(), null);
        }
        
        if (Config.getLogXML()) {
            Utils.log("(KidsToFssExtern convert) FSS Message = \n" + fss);
        }
        
        writeFileMessage(fss, outFile, encoding);

        MuleUtils.writeFile(outFile.getName(), encoding, fss, commonFieldsDTO);


        return fss;
    }

    private void writeFileMessage(String message, File outFile, String encoding) throws Exception {
        Utils.log("(KidsToFssExtern writeFileMessage) fileName = " + outFile.getName());
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
