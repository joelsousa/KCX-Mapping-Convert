package com.kewill.kcx.component.mapping.common.start.customer.out;


/*
 * Function    : KidsToJobExtern.java
 * Titel       :
 * Date        : 03.05.2012
 * Author      : Kewill / Christine Kron
 * Description : KidsToJob ohne MULE-Abhängigkeiten. 
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

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToKffJobConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToJobExtern<br>
 * Created 		: 03.05.2012<br>
 * Description  : KidsToJob ohne MULE-Abhängigkeiten.
 * 
 * @author kron
 * @version 1.0.00
 */
public class KidsToJobExtern extends KidsToKffJobConverter {
    
    public Object convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToJobExtern convert) KIDS message = \n" + message);
        }
        Object uids = readKids(message, encoding, outFile.getName(), EDirections.CountryToCustomer);
        
//        Utils.log("(KidsToJobExtern convert) commonFieldsDTO.isPdfTgz() = " + commonFieldsDTO.isPdfTgz());
        
        MuleUtils.writeFile(outFile.getName(), encoding, uids, commonFieldsDTO);

        return uids;
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
}

