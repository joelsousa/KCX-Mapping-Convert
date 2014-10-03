package com.kewill.kcx.component.mapping.common.start.customer.out;

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

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToUidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
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
        Object uids = readKids(message, encoding, outFile.getName(), EDirections.CountryToCustomer);
        
//        Utils.log("(KidsToUidsExtern convert) commonFieldsDTO.isPdfTgz() = " + commonFieldsDTO.isPdfTgz());
        
        MuleUtils.writeFile(outFile.getName(), encoding, uids, commonFieldsDTO);

        return uids;
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
}
