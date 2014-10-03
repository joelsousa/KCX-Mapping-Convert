package com.kewill.kcx.component.mapping.test.Export.mains.V60;

/*
 * Function    : KidsToFssTESTAXT.java
 * Titel       :
 * Date        : 06.10.2008
 * Author      : Kewill CSF / Houdek
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

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToFssExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToFssTESTAXT {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		OutputStreamWriter fssOut = null;
		try {
			fos = new FileOutputStream("data/fss/out/MapExpExtToAXT.dat");
			bos = new BufferedOutputStream(fos);
			fssOut = new OutputStreamWriter(bos, "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
         
		
		KidsToFssExtern kidsToFss = new KidsToFssExtern();
		String payload = getCompletion();
    	Utils.log("Completion : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String str = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			str = kidsToFss.readKids(parser, EDirections.CustomerToCountry);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToFss main) Converted Message = \n" + str);
        
        try { 
        	fssOut.write(str); 
        	fssOut.close();
        	bos.close();
        	fos.close();
        } catch (Exception e) {
				e.printStackTrace();
		}
	}

	private static String getCompletion() {
		StringBuffer xml = new StringBuffer();
		
		// ConfirmInvestigation
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
					xml.append("<MessageName>ConfirmInvestigation</MessageName>");				
					xml.append("<Release>1.0.00</Release>");				
				xml.append("</MessageTP>");			
				xml.append("<MessageID>MsgID20080916</MessageID>");			
				xml.append("<InReplyTo>IRTSH080916</InReplyTo>");			
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<ConfirmInvestigation>");				
				xml.append("<GoodsDeclaration>");	
			    	xml.append("<Annotation>Annotation</Annotation>");							
					xml.append("<ReferenceNumber>Reference11111</ReferenceNumber>");			
					xml.append("<ExitType>1</ExitType>");
					xml.append("<TerminationTime>20080916161225</TerminationTime>");	
					xml.append("<DateOfExit>20081006101010</DateOfExit>");						
					xml.append("<RealOfficeOfExit>DE005872</RealOfficeOfExit>");
				xml.append("</GoodsDeclaration>");
			xml.append("</ConfirmInvestigation>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");

		return xml.toString();
	}
}
