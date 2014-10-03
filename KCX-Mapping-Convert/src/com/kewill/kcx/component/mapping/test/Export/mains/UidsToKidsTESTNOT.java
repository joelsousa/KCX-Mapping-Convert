package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : UidsToKidsTESTNOT.java
 * Titel       :
 * Date        : 29.09.2008
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

public class UidsToKidsTESTNOT {

	public static void main(String[] args) {
		UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
		
		String payload = getErrorMessage();
    	Utils.log("uidsExitNotificationUK : " + payload);
    	
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
        Utils.log("(UidsExitNotificationUK main) Converted Message = \n" + xml);
	}
	
	private static String getErrorMessage() {
		StringBuffer xml = new StringBuffer();

		xml.append("<?xml version='1.0' encoding='UTF-8'?>");
		xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">");
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
		xml.append("<ExitNotification>");	
		xml.append("<DocumentReferenceNumber>08DE586600077817E5</DocumentReferenceNumber>");
        xml.append("<Termination>Termination</Termination>");
        xml.append("<DateOfExit>2008-09-10T13:41:00 +0200</DateOfExit>");
        xml.append("<TypeOfShipment>TOFSHIP</TypeOfShipment>");
        xml.append("<ExternalRegistrationNumber>ERN</ExternalRegistrationNumber>");
        xml.append("<ReferenceNumber>CK-080910-001</ReferenceNumber>");
        xml.append("<CustomsOffices>");
            xml.append("<OfficeOfActualExit>OfficeOfActualExit</OfficeOfActualExit>"); 
            xml.append("<OfficeOfExit>OfficeOfActualExit</OfficeOfExit>");         
        xml.append("</CustomsOffices>");        
        xml.append("<Shipper>");
            xml.append("<TIN>TIN1</TIN>");        
            xml.append("<CustomerID>CustomerID2158</CustomerID>");
        xml.append("</Shipper>");
        xml.append("<GoodsItem>");
            xml.append("<ItemNumber>ItemNumber</ItemNumber>");
            xml.append("<ExternalRegistrationNumber>ExternalRegistrationNumber</ExternalRegistrationNumber>");  
            xml.append("<NetMass>NetMass</NetMass>");  
            xml.append("<GrossMass>GrossMass</GrossMass>");   
            xml.append("<ChangedFlag>ChangedFlag</ChangedFlag>");      
        xml.append("</GoodsItem>");
 		xml.append("</ExitNotification>");
		xml.append("</Export>");
		xml.append("</Submit>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");


		return xml.toString();
	}
}

