package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : KidsToFssTESTADN.java
 * Titel       :
 * Date        : 09.10.2008
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
import com.kewill.kcx.component.mapping.common.start.customs.out.KidsToFssExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToFssTESTiwa extends KidsToFssExtern {

	public static void main(String[] args) {

		KidsToFssExtern kidsToFss = new KidsToFssExtern();
		//String payload = getADIMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			xml = kidsToFss.readKids(parser, EDirections.CustomerToCountry);
			String dummy = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToFss main) Converted Message = \n" + xml);
     
       /*
    	FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		OutputStreamWriter fssOut = null;
		try {
			fos = new FileOutputStream("data/fss/out/MapExpIndToADI.dat");
			bos = new BufferedOutputStream(fos);
			fssOut = new OutputStreamWriter(bos, "ISO-8859-1");
		}
	 	 catch (Exception e) {
			e.printStackTrace();
		}
         
		
		KidsToFss kidsToFss = new KidsToFss();
		//String payload = getADIMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("kidsAdiMessage : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        //StringBuffer str = new StringBuffer();
        String str = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			str = kidsToFss.readKids(parser);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToFSS main) Converted Message = \n" + str);
        
        try { 
        	fssOut.write(str); 
        	fssOut.close();
        	bos.close();
        	fos.close();
        }
        catch (Exception e) {
				e.printStackTrace();
		} 
       */ 
	}
	
	private static String getADIMessage() {
		StringBuffer xml = new StringBuffer();
		
		
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
				xml.append("<Method>Export</Method>");				
				xml.append("<MessageTP>");					
					xml.append("<CountryCode>DE</CountryCode>");					
					xml.append("<Procedure>EXPORT</Procedure>");					
					xml.append("<ProcedureSpecification>PS</ProcedureSpecification>");				
					xml.append("<MessageName>Prenotification</MessageName>");				
					xml.append("<Release>1.0.00</Release>");				
				xml.append("</MessageTP>");			
				xml.append("<MessageID>MsgID20080916</MessageID>");			
				xml.append("<InReplyTo>IRTSH080916</InReplyTo>");			
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<Prenotification>");				
				xml.append("<GoodsDeclaration>");				
					xml.append("<ReferenceNumber>Reference11111</ReferenceNumber>");
					xml.append("<UCRNumber>08DE586600077817E5</UCRNumber>");
					xml.append("<ExitType>1</ExitType>");
					xml.append("<TerminationTime>20080916161225</TerminationTime>");	
					xml.append("<DateOfExit>20081006101010</DateOfExit>");						
					xml.append("<CustomsOfficeExport>CustOffExport</CustomsOfficeExport>");
					xml.append("<RealOfficeOfExit>DE005872</RealOfficeOfExit>");					
					xml.append("<GoodsItem>");
			        xml.append("<ItemNumber>001</ItemNumber>");
			        xml.append("<CommodityCodeKN8>85013100</CommodityCodeKN8>");
			        xml.append("<GoodsDescription>Gleichstrommotoren; Gleichstromgeneratoren mit einer Leistung von 750Woder wenigerxml.append</GoodsDescription>");
			        xml.append("<GrossMass>00000210000</GrossMass>");
			        xml.append("<NetMass>00000200000</NetMass>");
			        xml.append("<OriginRegion>06</OriginRegion>");
			        xml.append("<Packages>");
			          xml.append("<SequentialNumber>01</SequentialNumber>");
			          xml.append("<Quantity>00001</Quantity>");
			          xml.append("<Type>PX</Type>");
			          xml.append("<Marks>00000000614069565001</Marks>");
			        xml.append("</Packages>");
			        xml.append("<Packages>");
			          xml.append("<SequentialNumber>02</SequentialNumber>");
			          xml.append("<Quantity>00001</Quantity>");
			          xml.append("<Type>PX</Type>");
			          xml.append("<Marks>00000000614069565001</Marks>");
			        xml.append("</Packages>");
			        xml.append("<Packages>");
			          xml.append("<SequentialNumber>03</SequentialNumber>");
			          xml.append("<Quantity>00001</Quantity>");
			          xml.append("<Type>PX</Type>");
			          xml.append("<Marks>00000000614069565001</Marks>");
			        xml.append("</Packages>");
			        xml.append("<Procedure>");
			          xml.append("<Declared>10</Declared>");
			          xml.append("<Previous>00</Previous>");
			        xml.append("</Procedure>");
			        xml.append("<PreviousDocument>");
			          xml.append("<Document>");
			             xml.append("<Type>OHNE</Type>");
			          xml.append("</Document>");
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
				xml.append("</GoodsDeclaration>");
			xml.append("</Prenotification>");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return payload.toString();
	}	
}
