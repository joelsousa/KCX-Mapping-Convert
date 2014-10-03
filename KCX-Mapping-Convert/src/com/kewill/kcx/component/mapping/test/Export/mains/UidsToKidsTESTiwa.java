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

public class UidsToKidsTESTiwa {

	public static void main(String[] args) {
	
		UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
		
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("uidsErrorInformation : " + payload);
    	
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
	
	private static String getMessage() {
		StringBuffer xml = new StringBuffer();
		

		
/*	Beispiel UIDS NAchricht ExportConfirmation
 * 	
 * <?xml version='1.0' encoding='UTF-8'?>
		<soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope'>
			<soap:Header>
				<Header xmlns='http://www.eurtradenet.com/schemas/header/200310' soap:mustUnderstand='true'>
					<To>CK01.1234.ende</To>
					<From>absendergeheim</From>
					<MessageID>DE01DE01346120080902152500234</MessageID>
					<SentAt>2008-09-02T15:25:03 +0200</SentAt>
					<Act>inform</Act>
				</Header>
			</soap:Header>
			<soap:Body>
				<Submit xmlns='http://www.eurtradenet.com/schemas/uids/export/body/200601'>
					<Export>
						<Confirmation>
							<DateOfReceipt>2008-09-02T15:23:00 +0200</DateOfReceipt>
							<DocumentReferenceNumber>08DE655102276486E5</DocumentReferenceNumber>
							<FunctionCode>1</FunctionCode>
							<ReferenceNumber>ChristinesBezugsnummer</ReferenceNumber>
						</Confirmation>
					</Export>
				</Submit>
			</soap:Body>
		</soap:Envelope>
*/		
		
/*
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
          xml.append("<CustomsID>0</CustomsID>");
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
        xml.append("<DateOfAcceptance>2008-09-10T13:41:00 +0200</DateOfAcceptance>");
        xml.append("<DateOfDeclaration>2008-09-10T13:37:00 +0200</DateOfDeclaration>");
        xml.append("<DateOfReceipt>2008-09-10T13:41:00 +0200</DateOfReceipt>");
        xml.append("<DateOfRelease>2008-09-10T13:41:00 +0200</DateOfRelease>");
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
          xml.append("<Type>03</Type>");
        xml.append("</MeansOfTransport>");
        xml.append("<MeansOfTransport TransportType='Departure'>");
          xml.append("<Mode>33</Mode>");
          xml.append("<Nationality>QU</Nationality>");
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
 		xml.append("</ReverseDeclaration>");
		xml.append("</Export>");
		xml.append("</Submit>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");
*/
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
						xml.append("<ErrorInformation>");	
							xml.append("<MRN>MRN</MRN>");					
									xml.append("<DocumentReferenceNumber>08DE655102276486E5</DocumentReferenceNumber>");
									xml.append("<ReferenceNumber>beznr</ReferenceNumber>");
									xml.append("<RegistrationNumber>1</RegistrationNumber>");
									xml.append("<Error>");
										xml.append("<Type>Type-1</Type>");
										xml.append("<Reason>Reason-1</Reason>");
										xml.append("<Pointer>Pointer-1</Pointer>");							
									xml.append("</Error>");
						xml.append("</ErrorInformation>");
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

