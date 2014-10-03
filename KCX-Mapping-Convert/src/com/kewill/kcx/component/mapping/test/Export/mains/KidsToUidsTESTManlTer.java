/*
 * Function    : KidsToUidsTEST.java
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

package com.kewill.kcx.component.mapping.test.Export.mains;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToUidsTESTManlTer {

	public static void main(String[] args) {
	    KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
		String payload = getManualTermination();
    	Utils.log("kidsManualTermination : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			xml = (String) kidsToUids.convert(payload, new File("dummy.txt"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToUids main) Converted Message = \n" + xml);
	}
	
	private static String getManualTermination() {
		StringBuffer xml = new StringBuffer();
		
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">");
		xml.append("<soap:Header>");
			xml.append("<Header xmlns=\"http://www.eBiz.com/schemas/header/200310\" soap:mustUnderstand=\"true\">");
				xml.append("<SentAt>");
					xml.append("<Date>");
						xml.append("<Year>2008</Year>");
						xml.append("<Month>9</Month>");
						xml.append("<Day>10</Day>");
					xml.append("</Date>");
					xml.append("<Time>15:00:00</Time>");	
					xml.append("<TimeZone>+0200</TimeZone>");
				xml.append("</SentAt>");	
				xml.append("<Transmitter>DE</Transmitter>");		
				xml.append("<Receiver>CSFFRA</Receiver>");			
				xml.append("<Method>Manual Termination</Method>");				
				xml.append("<MessageTP>");					
					xml.append("<CountryCode>DE</CountryCode>");					
					xml.append("<Procedure>EXPORT</Procedure>");					
					xml.append("<ProcedureSpecification>PS</ProcedureSpecification>");				
					xml.append("<MessageName>ManualTermination</MessageName>");				
					xml.append("<Release>1.0.00</Release>");				
				xml.append("</MessageTP>");			
				xml.append("<MessageID>MsgID20080902</MessageID>");			
				xml.append("<InReplyTo>IRT</InReplyTo>");			
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<ManualTermination xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi=\"noNamespaceSchemaLocation\">");				
				xml.append("<GoodsDeclaration>");	
					xml.append("<TerminationTime>TerminationTime</TerminationTime>");	
					xml.append("<Annotation>Annotation</Annotation>");	
					xml.append("<ReferenceNumber>ReferenceNumber</ReferenceNumber>");	
					xml.append("<Clerk>Clerk</Clerk>");														
					xml.append("<Seal>");			
						xml.append("<UseOfTydenseals>1</UseOfTydenseals>");		
						xml.append("<UseOfTydensealStock>Stock</UseOfTydensealStock>");										
					xml.append("</Seal>");					
				xml.append("</GoodsDeclaration>");
			xml.append("</ManualTermination>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");

		return xml.toString();
	}
}
