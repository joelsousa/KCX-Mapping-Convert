package com.kewill.kcx.component.mapping.common.start.customer.out;

/*
 * Function    : KidsToKexExtern.java
 * Titel       :
 * Date        : 15.11.2011
 * Author      : Kewill CSF / Schmidt
 * Description : KidsToKex ohne MULE-Abhängigkeiten. 
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
import com.kewill.kcx.component.mapping.common.start.KidsToKexConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToKexExtern<br>
 * Created 		: 15.11.2011<br>
 * Description  : KidsToKex ohne MULE-Abhängigkeiten.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToKexExtern extends KidsToKexConverter {
    
    public Object convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToKexExtern convert) KIDS message = \n" + message);
        }
        Object result = readKids(message, encoding, outFile.getName(), EDirections.CountryToCustomer);
        
        MuleUtils.writeCustomerFile(outFile.getName(), encoding, result, commonFieldsDTO);

        return result;
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
}
