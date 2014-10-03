package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : UidsToKidsTESBH.java
 * Titel       :
 * Date        : 13.09.2008
 * Author      : Kewill CSF / houdek
 * Description : Test class specially used by Boris
 * 			   : 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class UidsToKidsTESTiwaExpRel {

	public static void main(String[] args) {
		UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
		
		/*String payload = getFileMessage(args[0]);
    	Utils.log("uidsMessage : " + payload);*/
		String payload = getErrorMessage();
    	Utils.log("uidsReverseDeclaration : " + payload);
    	
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
        Utils.log("(UidsToKidsTESTiwa main) Converted Message = \n" + xml);
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
				xml.append("<SentAt>2008-09-13 14:29:00 +0200</SentAt>");
				xml.append("<Act>inform</Act>");						
			xml.append("</Header>");
		xml.append("</soap:Header>");
		xml.append("<soap:Body>");		
		xml.append("<Submit xmlns='http://www.eurtradenet.com/schemas/uids/export/body/200601'>");
		xml.append("<Export>");
		xml.append("<ReverseDeclaration>");	
         xml.append("<Exporter>");
          xml.append("<CustomerID>2158</CustomerID>");
          xml.append("<CustomsID>1</CustomsID>");
          xml.append("<ETNAddress>9000119</ETNAddress>");
          xml.append("<TIN>9000119</TIN>");
          xml.append("<Address>");
            xml.append("<Name>Kewill CSF GmbH</Name>");
            xml.append("<StreetAndNumber>Norsk-Data-Str. 1</StreetAndNumber>");
            xml.append("<PostalCode>61352</PostalCode>");
            xml.append("<City>Bad Homburg</City>");
            xml.append("<CountryCodeISO>DE</CountryCodeISO>");
          xml.append("</Address>");
        xml.append("</Exporter>");
        xml.append("<Declarant>");
          xml.append("<CustomsID>1</CustomsID>");
          xml.append("<TIN>9000119</TIN>");
          xml.append("<Address>");
            xml.append("<Name>Kewill CSF GmbH</Name>");
            xml.append("<StreetAndNumber>Norsk-Data-Str. 1</StreetAndNumber>");
            xml.append("<PostalCode>61352</PostalCode>");
            xml.append("<City>Bad Homburg</City>");
            xml.append("<CountryCodeISO>DE</CountryCodeISO>");
          xml.append("</Address>");
        xml.append("</Declarant>");
        xml.append("<DeclarantRepresentative>");
          xml.append("<CustomsID>0</CustomsID>");
        xml.append("</DeclarantRepresentative>");
        xml.append("<Subcontractor>");
          xml.append("<CustomsID>0</CustomsID>");
        xml.append("</Subcontractor>");
        xml.append("<Consignee>");
        xml.append("<CustomerID>0</CustomerID>");
      xml.append("</Consignee>");        
        xml.append("<AuthorisationID>DE5864ZA0002</AuthorisationID>");
        xml.append("<ContainerIndicator>0</ContainerIndicator>");
        xml.append("<CustomsOffices>");
          xml.append("<OfficeOfExport>DE005866</OfficeOfExport>");
          xml.append("<OfficeOfExit>DE005872</OfficeOfExit>");
        xml.append("</CustomsOffices>");
        xml.append("<DateOfAcceptance>2008-09-10 13:41:00 +0200</DateOfAcceptance>");                                      
        xml.append("<DateOfDeclaration>2008-09-10 13:37:00 +0200</DateOfDeclaration>");
        xml.append("<DateOfReceipt>2008-09-10 13:41:00 +0200</DateOfReceipt>");
        xml.append("<DateOfRelease>2008-09-10 13:41:00 +0200</DateOfRelease>");
        xml.append("<DestinationCountry>CH</DestinationCountry>");
        xml.append("<DocumentReferenceNumber>08DE586600077817E5</DocumentReferenceNumber>");
        xml.append("<ExportRefund>0</ExportRefund>");
        xml.append("<GrossMass>00000210000</GrossMass>");
        xml.append("<Incoterms>");
           xml.append("<Code>CFR</Code>");
           xml.append("<City>zuerich</City>");
        xml.append("</Incoterms>");
        xml.append("<ItemsQuantity>001</ItemsQuantity>");
        xml.append("<MeansOfTransport TransportType='Inland'>");
          xml.append("<Type>01</Type>");
        xml.append("</MeansOfTransport>");
        xml.append("<MeansOfTransport TransportType='Departure'>");
          xml.append("<Mode>22</Mode>");
          xml.append("<Nationality>TD</Nationality>");
          xml.append("<Type>02</Type>");
        xml.append("</MeansOfTransport>");
        xml.append("<MeansOfTransport TransportType='Border'>");
        xml.append("<Mode>33</Mode>");
        xml.append("<Nationality>TB</Nationality>");
        xml.append("<Type>03</Type>");
      xml.append("</MeansOfTransport>");
        xml.append("<PackagesQuantity>0000001</PackagesQuantity>");
        xml.append("<ParticipantsCombination>1</ParticipantsCombination>");
        xml.append("<PDF>");
          xml.append("<Name>CB58008935</Name>");
          xml.append("<Directory>/home2/atlas/pdfdir</Directory>");
          xml.append("<Subdirectory>20080910</Subdirectory>");
        xml.append("</PDF>");
        xml.append("<PlaceOfLoading>");
          xml.append("<Code>AA01</Code>");
        xml.append("</PlaceOfLoading>");
        xml.append("<Procedure>");
          xml.append("<Previous>FSS</Previous>");
        xml.append("</Procedure>");
        xml.append("<ReferenceNumber>CK-080910-001</ReferenceNumber>");
        xml.append("<Seals>");
          xml.append("<Quantity>0000</Quantity>");
        xml.append("</Seals>");
        xml.append("<ShortageIndicator>0</ShortageIndicator>");
        xml.append("<Transaction>");
          xml.append("<Type>11</Type>");
          xml.append("<Amount>000000000020278</Amount>");
          xml.append("<Currency>EUR</Currency>");
        xml.append("</Transaction>");
        xml.append("<TypeOfDeclaration>");
          xml.append("<RegionCode>EU</RegionCode>");
          xml.append("<ProcedureCode>e</ProcedureCode>");
        xml.append("</TypeOfDeclaration>");
        xml.append("<TypeOfRelease>1</TypeOfRelease>");
        xml.append("<HeaderExtensions>");
          xml.append("<TydenSealsFlag>0</TydenSealsFlag>");
          xml.append("<TydenSealsStockFlag>0</TydenSealsStockFlag>");
        xml.append("</HeaderExtensions>");
        xml.append("<GoodsItem>");
        xml.append("<ItemNumber>001</ItemNumber>");
        xml.append("<CommodityCodeKN8>85013100</CommodityCodeKN8>");
        xml.append("<GoodsDescription>Gleichstrommotoren; Gleichstromgeneratoren mit einer Leistung von 750Woder wenigerxml.append</GoodsDescription>");
        xml.append("<GrossMass>00000210000</GrossMass>");
        xml.append("<NetMass>00000200000</NetMass>");
        xml.append("<OriginRegion>06</OriginRegion>");
        xml.append("<Packaging>");
          xml.append("<Number>01</Number>");
          xml.append("<Quantity>00001</Quantity>");
          xml.append("<Type>PX</Type>");
          xml.append("<MarksAndNumbers>00000000614069565001</MarksAndNumbers>");
        xml.append("</Packaging>");
        xml.append("<Procedure>");
          xml.append("<Declared>10</Declared>");
          xml.append("<Previous>00</Previous>");
        xml.append("</Procedure>");
        xml.append("<PreviousDocument>");
          xml.append("<Type>Previous-1</Type>");
          xml.append("<Reference>Previous-1-m</Reference>");
        xml.append("</PreviousDocument>");
        xml.append("<PreviousDocument>");
        xml.append("<Type>Previous-2</Type>");
        xml.append("<Reference>Previous-2-m</Reference>");
      xml.append("</PreviousDocument>");
        xml.append("<ProducedDocument>");
        xml.append("<Type>1.1-IWANIUK</Type>");
        xml.append("</ProducedDocument>");
        xml.append("<ProducedDocument>");
        xml.append("<Type>1.2-IWA</Type>");
        xml.append("</ProducedDocument>");
        xml.append("<ProducedDocument>");
        xml.append("<Type>1.3-IWA</Type>");
        xml.append("</ProducedDocument>");
      xml.append("<ContainerRegistration>Container1</ContainerRegistration>");
      xml.append("<ContainerRegistration>Container2</ContainerRegistration>");     
        xml.append("<StatisticalValue>000000006</StatisticalValue>");
        xml.append("<StatisticalQuantity>000000034000</StatisticalQuantity>");
      xml.append("</GoodsItem>");
      xml.append("<GoodsItem>");
      xml.append("<ItemNumber>002</ItemNumber>");
      xml.append("<CommodityCodeKN8>85013100</CommodityCodeKN8>");
      xml.append("<GoodsDescription>Gleichstrommotoren; Gleichstromgeneratoren mit einer Leistung von 750Woder wenigerxml.append</GoodsDescription>");
      xml.append("<GrossMass>00000210000</GrossMass>");
      xml.append("<NetMass>00000200000</NetMass>");
      xml.append("<OriginRegion>06</OriginRegion>");
      xml.append("<Packaging>");
        xml.append("<Number>02</Number>");
        xml.append("<Quantity>00001</Quantity>");
        xml.append("<Type>PX</Type>");
        xml.append("<MarksAndNumbers>00000000614069565001</MarksAndNumbers>"); 
        xml.append("</Packaging>"); 
        xml.append("<ProducedDocument>");
         xml.append("<Type>2.1-MIT</Type>");
         xml.append("</ProducedDocument>");
         xml.append("<ProducedDocument>");
         xml.append("<Type>2.2-MIT</Type>");
         xml.append("</ProducedDocument>");
         xml.append("</GoodsItem>"); 
        xml.append("<GoodsItem>");
        xml.append("<ItemNumber>003</ItemNumber>");   
        xml.append("<ProducedDocument>");
        xml.append("<Type>3.1-MIT</Type>");
        xml.append("</ProducedDocument>");
        xml.append("<ProducedDocument>");
        xml.append("<Type>3.2-MIT</Type>");
        xml.append("</ProducedDocument>");
        xml.append("</GoodsItem>");       
 		xml.append("</ReverseDeclaration>");
		xml.append("</Export>");
		xml.append("</Submit>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");


		return xml.toString();
	}
	private static String getFileMessage(String fileName){
		File inFile = new File(fileName);
		StringBuffer payload = new StringBuffer();
		
		try {
			FileReader fr = new FileReader(inFile);
			BufferedReader in = new BufferedReader(fr);
			String line = null;
			line = in.readLine();
			while(line != null){
				payload.append(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return payload.toString();
	}
}

