package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : UidsToKidsTESTERL.java
 * Titel       :
 * Date        : 26.09.2008
 * Author      : Kewill CSF / houdek
 * Description : Test class specially used by Boris
 * 			   : 
 */

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class UidsToKidsTESTERL {

	public static void main(String[] args) {
		UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
		
		String payload = getMessage();
    	Utils.log("uidsManualTermination : " + payload);
    	
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
        Utils.log("(UidsToKidsTESTERL main) Converted Message = \n" + xml);
	}
	
	private static String getMessage() {
		StringBuffer xml = new StringBuffer();

		xml.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
		xml.append("<soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope'>");
				xml.append("<soap:Header>");						
				xml.append("<Header xmlns=\"http://www.eurtradenet.com/schemas/header/200310\" soap:mustUnderstand=\"true\">");
					xml.append("<To>DE01.CSFTEST.2158</To>");
					xml.append("<From>DE01.CSFTEST.2158</From>");
					xml.append("<MessageID>DE01DE01215820080913142900123456</MessageID>");
					xml.append("<SentAt>2008-09-13T14:29:00 +0200</SentAt>");
					xml.append("<Act>inform</Act>");						
				xml.append("</Header>");
			xml.append("</soap:Header>");
			xml.append("<soap:Body>");		
			xml.append("<Submit xmlns='http://www.eurtradenet.com/schemas/uids/export/body/200601'>");
			xml.append("<Export>");
		xml.append("<ManualTermination>");	
        xml.append("<DateOfTermination>2008-09-10T13:41:00 +0200</DateOfTermination>");
        xml.append("<Remark>Remark</Remark>");
        xml.append("<ReferenceNumber>CK-080910-001</ReferenceNumber>");
        xml.append("<Contact>");
           xml.append("<Identity>0</Identity>");
        xml.append("</Contact>");
        xml.append("<HeaderExtensions>");
          xml.append("<TydenSealsFlag>0</TydenSealsFlag>");
        xml.append("</HeaderExtensions>");
 		xml.append("</ManualTermination>");
		xml.append("</Export>");
		xml.append("</Submit>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");


		return xml.toString();
	}
}

