package com.kewill.kcx.component.mapping.test.Export.mains;
/*
 * Function    : UidsToKidsTESTCK.java
 * Titel       :
 * Date        : 13.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Test class specially used by Christine Kron...
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

import com.kewill.kcx.component.mapping.common.start.customer.in.UidsToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class UidsToKidsTESTAK {

	public static void main(String[] args) {
		
		UidsToKidsExtern uidsToKids = new UidsToKidsExtern();
		
//		String payload = getErrorMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("uidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			xml = uidsToKids.convert(new File(args[0]), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(UidsToKidsTESTCK main) Converted Message = \n" + xml);
	}
	
	private static String getErrorMessage() {
		StringBuffer xml = new StringBuffer();
		
/*	Beispiel UIDS NAchricht DeclarationResponse
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
			<Submit xmlns="http://www.eurtradenet.com/schemas/uids/export/body/200601">
      			<Export>
        			<ExportDeclarationResponse>
          				<Justification>
            				<Code>14</Code>
          				</Justification>
          				<ReferenceNumber>0000001518/0090602237</ReferenceNumber>
          				<DateOfAcceptance>200801251242</DateOfAcceptance>
          				<DateOfCancellation />
          				<DateOfExit />
          				<DateOfLoadingBegin />
          				<DateOfLoadingEnd />
          				<DateOfProcessing />
          				<DateOfReceipt>200801251242</DateOfReceipt>
          				<DateOfRejection />
          				<DateOfRelease />
        			</ExportDeclarationResponse>
      			</Export>
    		</Submit>
		</soap:Body>
	</soap:Envelope>
*/		
		
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
		
		
		
		
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
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
		xml.append("<Confirmation>");
		xml.append("<DateOfReceipt>2008-09-02T15:23:00 +0200</DateOfReceipt>");
		xml.append("<DocumentReferenceNumber>08DE655102276486E5</DocumentReferenceNumber>");
		//xml.append("<DocumentReferenceNumber />");
		xml.append("<FunctionCode>1</FunctionCode>");
//		xml.append("<ReferenceNumber />");
		xml.append("<ReferenceNumber>ChristinesBezugsnummer</ReferenceNumber>");
		xml.append("<PDF>");
		xml.append("<Name>CB81007225.pdf</Name>");
		xml.append("<Directory>/home2/atlas/pdfdir</Directory>");
		xml.append("<SubDirectory>20080916</SubDirectory>");
		xml.append("</PDF>");
		xml.append("</Confirmation>");
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

