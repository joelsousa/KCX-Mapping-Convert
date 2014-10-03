package com.kewill.kcx.component.mapping.common.start.customs.out;

/*
 * Function    : KidsToUidsExtern.java
 * Titel       :
 * Date        : 08.09.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : KidsToUids ohne MULE-Abhängigkeiten. 
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

import java.io.File;
import java.sql.Timestamp;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToUidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToUidsExtern<br>
 * Created 		: 08.09.2009<br>
 * Description  : KidsToUids ohne MULE-Abhängigkeiten.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToUidsExtern extends KidsToUidsConverter {
    
    public Object convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToUidsExtern convert) KIDS message = \n" + message);
        }
     
        
        // CK20110803
        // Object uids = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        Object uids = null;
        try {
        	uids = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToUidsExtern convert) Exception ", e, outFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KidsToUidsExtern convert) Error ", e, outFile.getName(), null);
        }
        
        Utils.log("(KidsToUidsExtern convert) createOutName = " + Config.isCreateOutName());
        if (Config.isCreateOutName()) {
            try {
                String toCountry     = commonFieldsDTO.getCountryCode().toUpperCase();
                String kcxId         = commonFieldsDTO.getKcxId().toUpperCase();
                String[] tokens      = kcxId.split("[.]");
                String fromCountry   = tokens[0];
                String company       = tokens[1];
                String branch        = "";
                if (tokens.length > 2) {
                    branch = tokens[2];
                }
                String timestamp     = Utils.timestampToString(new Timestamp(System.currentTimeMillis()));
                long lastnum = Db.getRunningNumberForKcxId(kcxId);
                String runningNumber = Utils.nFormat(lastnum, 9);
                String extension     = ".xml";
                if (commonFieldsDTO.isPdfTgz()) {
                    extension     = ".pdf";
                }
                
                String newName = fromCountry + "01" + toCountry + "01_" + company + "_" + branch + "_" + 
                                                                                 timestamp + runningNumber + extension;
                outFile = new File(outFile.getParent(), newName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        MuleUtils.writeFile(outFile.getName(), encoding, uids, commonFieldsDTO);

        return uids;
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
