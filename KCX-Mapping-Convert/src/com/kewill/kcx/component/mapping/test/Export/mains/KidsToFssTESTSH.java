package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : KidsToFssTESTSH.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / Heise
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
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToFssExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToFssTESTSH extends KidsToFssExtern {

	public static void main(String[] args) {
		KidsToFssExtern kidsToFss = new KidsToFssExtern();
		String payload = getErrorMessage();
    	Utils.log("kidsErrorMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			xml = kidsToFss.readKids(parser, EDirections.CustomerToCountry);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToUids main) Converted Message = \n" + xml);
	}
	
	private static String getErrorMessage() {
		StringBuffer xml = new StringBuffer();
		
		// Cancellation
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">");
		xml.append("<soap:Header>");
			xml.append("<Header xmlns=\"http://www.eBiz.com/schemas/header/200310\" soap:mustUnderstand=\"true\">");
				xml.append("<SentAt>");
					xml.append("<Date>");
						xml.append("<Year>2008</Year>");
						xml.append("<Month>9</Month>");
						xml.append("<Day>16</Day>");
					xml.append("</Date>");
					xml.append("<Time>16:24:30</Time>");	
					xml.append("<TimeZone>+0200</TimeZone>");
				xml.append("</SentAt>");	
				xml.append("<Transmitter>DE</Transmitter>");		
				xml.append("<Receiver>DE01CSFTEST</Receiver>");			
				xml.append("<Method>ExportCancellation</Method>");				
				xml.append("<MessageTP>");					
					xml.append("<CountryCode>DE</CountryCode>");					
					xml.append("<Procedure>EXPORT</Procedure>");					
					xml.append("<ProcedureSpecification>PS</ProcedureSpecification>");				
					xml.append("<MessageName>Cancellation</MessageName>");				
					xml.append("<Release>1.0.00</Release>");				
				xml.append("</MessageTP>");			
				xml.append("<MessageID>MsgID20080916</MessageID>");			
				xml.append("<InReplyTo>IRTSH080916</InReplyTo>");			
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<Cancellation>");				
				xml.append("<GoodsDeclaration>");				
					xml.append("<KindOfDeclaration>KindOfDeclarationXYZ</KindOfDeclaration>");			
					xml.append("<CancellationTime>2008-09-16T16:12:34 +0200</CancellationTime>");
					xml.append("<Cancellation>");
						xml.append("<KindOfCancellation>KindOfCancelABC</KindOfCancellation>");
						xml.append("<Reason>Verschlampt</Reason>");
					xml.append("</Cancellation>");
					xml.append("<DeclarationNumberCustoms>08CH12345678901238</DeclarationNumberCustoms>");
					xml.append("<ReferenceNumber>Ref47110815123</ReferenceNumber>");
					xml.append("<Clerk>Heise</Clerk>");
				xml.append("</GoodsDeclaration>");
			xml.append("</Cancellation>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");

		return xml.toString();
	}
}
