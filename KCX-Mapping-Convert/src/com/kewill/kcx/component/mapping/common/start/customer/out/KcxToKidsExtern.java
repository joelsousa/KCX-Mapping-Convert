/*
 * Funktion    : KcxToKids.java
 * Titel       :
 * Erstellt    : 11.12.2008
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
package com.kewill.kcx.component.mapping.common.start.customer.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KcxToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KcxToKidsExtern<br>
 * Erstellt		: 18.05.2010<br>
 * Beschreibung	: KcxToKids ohne MULE-Abhängigkeiten. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KcxToKidsExtern extends KcxToKidsConverter {
    
    public String convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KcxToKidsExtern convert) KIDS message = \n" + message);
        }
        String xml = removeKcxEnvelope(message, encoding, EDirections.CountryToCustomer);
        
        // MS20111208 Begin
        // writeFileMessage(xml, outFile, encoding);
        if (Config.isWriteToCustomerDir()) {
            MuleUtils.writeCustomerFile(outFile.getName(), encoding, xml, commonFieldsDTO);
        } else {
            writeFileMessage(xml, outFile, encoding);
        }
        // MS20111208 End
        
        return xml;
    }

    private void writeFileMessage(String message, File outFile, String encoding) throws Exception {
        Utils.log("(KcxToKidsExtern writeFileMessage) fileName = " + outFile.getName());
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

