package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : RemoveSoap.java
 * Titel       :
 * Date        : 19.01.2012
 * Author      : Kewill GmbH / Alfred Krzoska
 * Description : Remvoe a soap:Header and soap:Body from a message. 
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

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : RemoveSoap<br>
 * Created 		: 19.01.2012<br>
 * Description  : Remove Soap:Envelope,Soap:Header from message and enclose message with encloseTag
 * 
 * @author Krzoska
 * @version 1.0.00
 */
public class RemoveSoap {    //EI20120124
    
    public String removeSoap(String message, String encloseTag) throws Exception {
        if (Config.getLogXML()) {
            Utils.log("(RemoveSoap removeSoap) message = \n" + message);
        }
        StringBuffer content = null;        
        int from = 0;
        int to = 0;
       
        try {
        	//1.
        	to = message.indexOf("<soap:Envelope");
        	if (to == -1) {
        		return message;
        	} 
        	content = new StringBuffer(message.substring(from, to) + "<" + encloseTag + ">");
            //2.
			from = message.indexOf("<Header");
			if (from == -1) {
        		return message;
        	} 
			to = message.indexOf("</Header>");
			if (to == -1) {
        		return message;
        	} 			
			content.append(message.substring(from, to));
			StringBuffer endTag = new StringBuffer("</Header>");
			content.append(endTag);
			//3.
			String tempSoapBody = "<soap:Body>";
			String tempBody = "";
			from = message.indexOf(tempSoapBody);
			if (from == -1) {
				tempBody = "<Body>";
				from = message.indexOf(tempBody);
				if (from == -1) {
					return message;	
				}  else {
					from = from + tempBody.length();
				}
        	} else {
        		from = from + tempSoapBody.length();
        	}
			
			to = message.indexOf("</soap:Body>");
			if (to == -1) {				
				to = message.indexOf("</soap:Body>");
				if (from == -1) {
					return message;
				} 
        	} 			
			content.append(message.substring(from, to));
			
			//4.
			content.append("</" + encloseTag + ">");
		} catch (Exception e) {
			Utils.log("(RemoveSoap removeSoap) xml = \n" + message);
		}
        
        return content.toString();
    }
    public String removeSoapOLD(String message, String encloseTag) throws Exception {
        if (Config.getLogXML()) {
            Utils.log("(RemoveSoap removeSoap) message = \n" + message);
        }
        StringBuffer content = null;
        int from = 0;
        int to = 0;
       
        try {
        	to = message.indexOf("<soap:Envelope ");

        	if (to == -1) {
        		return message;
        	} 
        	content = new StringBuffer(message.substring(from, to) + "<" + encloseTag + ">");

			from = message.indexOf("<Header xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
			to = message.indexOf("</soap:Header>");
			content.append(message.substring(from, to));
			
			from = message.indexOf("<localAppResult xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
			to = message.indexOf("</soap:Body>");
			content.append(message.substring(from, to));

			content.append("</" + encloseTag + ">");
		} catch (Exception e) {
			Utils.log("(RemoveSoap removeSoap) xml = \n" + message);
		}
        
        return content.toString();
    }
}
