package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : KidsToUidsTESTSH.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF / Koschara
 * Description : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class UidsToKidsTESTSH {

	public static void main(String[] args) {
		UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
		String payload = getErrorMessage();
    	Utils.log("uidsErrorMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			xml = uidsToKids.readUids(parser, "TestAuditId", "UTF-8", EDirections.CustomerToCountry);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(UidsToKids main) Converted Message = \n" + xml);
	}
	
	private static String getErrorMessage() {
		StringBuffer xml = new StringBuffer();
		
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">");
		xml.append("<soap:Header>");
			xml.append("<Header xmlns=\"http://www.eurtradenet.com/schemas/header/200310\" soap:mustUnderstand=\"true\">");
				xml.append("<To>DE01.CSFTEST.2158</To>");
				xml.append("<From>DE</From>");
				xml.append("<MessageID>MsgID20080902</MessageID>");			
				xml.append("<InReplyTo>IRT</InReplyTo>");			
				xml.append("<SentAt>2008-09-10T15:00:00 +0200</SentAt>");
				//xml.append("<Act>status</Act>");
				//xml.append("<AdditionalInformation>42</AdditionalInformation>");
				xml.append("<Act>failure</Act>");
				//xml.append("<AdditionalInformation>92-EdifactCreationError</AdditionalInformation>");
				xml.append("<AdditionalInformation>91-InhouseCreationError</AdditionalInformation>");
				
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body />");				
		//xml.append("<AdditionalInformation>42</AdditionalInformation>");
		//xml.append("</soap:Body>");				
		xml.append("</soap:Envelope>");
		

		return xml.toString();
	}
}

/* EXPNCK
xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">");
xml.append("<soap:Header>");
	xml.append("<Header xmlns=\"http://www.eurtradenet.com/schemas/header/200310\" soap:mustUnderstand=\"true\">");
		xml.append("<To>DE01CSFTESTUKEXPNCK</To>");
		xml.append("<From>DE</From>");
		xml.append("<MessageID>MsgID20080902</MessageID>");			
		xml.append("<InReplyTo>IRT</InReplyTo>");			
		xml.append("<SentAt>2008-09-10T15:00:00 +0200</SentAt>");
		//xml.append("<Act>status</Act>");
		//xml.append("<AdditionalInformation>42</AdditionalInformation>");
		xml.append("<Act>failure</Act>");
		//xml.append("<AdditionalInformation>92-EdifactCreationError</AdditionalInformation>");
		xml.append("<AdditionalInformation>91-InhouseCreationError</AdditionalInformation>");
		
	xml.append("</Header>");			
xml.append("</soap:Header>");			
xml.append("<soap:Body />");				
//xml.append("<AdditionalInformation>42</AdditionalInformation>");
//xml.append("</soap:Body>");				
xml.append("</soap:Envelope>");

CANCELLATION

		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">");
		xml.append("<soap:Header>");
			xml.append("<Header xmlns=\"http://www.eurtradenet.com/schemas/header/200310\" soap:mustUnderstand=\"true\">");
				xml.append("<To>DE01.CSFTEST.2158</To>");
				xml.append("<From>DE</From>");
				xml.append("<MessageID>MsgID20080916</MessageID>");			
				xml.append("<InReplyTo>IRTCANCEL</InReplyTo>");			
				xml.append("<SentAt>2008-09-16T17:37:20 +0200</SentAt>");
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<Submit xmlns='http://www.eurtradenet.com/schemas/uids/export/body/200601'>");
				xml.append("<Export>");
					xml.append("<Cancelation>");
						xml.append("<Contact>");
							xml.append("<Identity>Sven</Identity>");
						xml.append("</Contact>");
						xml.append("<TypeOfDocument>TypOfDocABCDEFG</TypeOfDocument>");
						xml.append("<DateOfAnnulment>2008-09-16T16:54:43 +0200</DateOfAnnulment>");
						xml.append("<ReasonOfAnnulment>verpennt</ReasonOfAnnulment>");
						xml.append("<ReferenceNumber>SvensBezugsnummer</ReferenceNumber>");
						xml.append("<TypeOfAnnulment>TypOfAnulUVWXYZ</TypeOfAnnulment>");
					xml.append("</Cancelation>");
				xml.append("</Export>");
			xml.append("</Submit>");
		xml.append("</soap:Body>");				
		xml.append("</soap:Envelope>");

*/