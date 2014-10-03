package com.kewill.kcx.component.mapping.test.Export.mains;

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

import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToUidsTESTiwa {
	
	public static void main(String[] args) {
		KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
		//String payload = getMessage();
		String payload = getFileMessage(args[0]);
    	Utils.log("uidsMessage : " + payload);
    	
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
	
	private static String getMessage() {
		
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
				xml.append("<Method>ExpRel Message</Method>");				
				xml.append("<MessageTP>");					
					xml.append("<CountryCode>DE</CountryCode>");					
					xml.append("<Procedure>EXPORT</Procedure>");					
					xml.append("<ProcedureSpecification>PS</ProcedureSpecification>");				
					xml.append("<MessageName>ReverseDeclaration</MessageName>");				
					xml.append("<Release>1.0.00</Release>");				
				xml.append("</MessageTP>");			
				xml.append("<MessageID>MsgID10000000</MessageID>");			
				xml.append("<InReplyTo>IRT</InReplyTo>");			
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<ReverseDeclaration>");				
				xml.append("<GoodsDeclaration>");
					xml.append("<AreaCode>TofD-RC</AreaCode>");
					xml.append("<TypeOfPermit>TofD-PC</TypeOfPermit>");
					xml.append("<KindOfAnswer>TofR</KindOfAnswer>");
					xml.append("<DeclarationKind>EDR-AR</DeclarationKind>");
					xml.append("<UniqueConsignmentReferenceNumber>DRNr</UniqueConsignmentReferenceNumber>");
					xml.append("<CompletionTime>20081001134157</CompletionTime>");
					xml.append("<CancellationTime>20081002134157</CancellationTime>");
					xml.append("<DeclarationTime>20081003134157</DeclarationTime>");
					xml.append("<DestinationCountry>DK</DestinationCountry>");
					xml.append("<DateOfExit>20081004134157</DateOfExit>");
					xml.append("<ReleaseTime>20081005134157</ReleaseTime>");
					xml.append("<AcceptanceTime>20081006134157</AcceptanceTime>");
					xml.append("<ReceiveTime>20081007134157</ReceiveTime>");
					xml.append("<TimeOfRejection>20081008134157</TimeOfRejection>");
					xml.append("<BeginTimeOfProcessing>20081009134157</BeginTimeOfProcessing>");
					xml.append("<ShortageInQuantity>ShIn</ShortageInQuantity>");
					xml.append("<TransportInContainer>CI</TransportInContainer>");
					xml.append("<GrossMass>GrossMass</GrossMass>");
					xml.append("<UCROtherSystem>ExRegNr</UCROtherSystem>");
					xml.append("<Annotation>Remark</Annotation>");	
					xml.append("<ReferenceNumber>RefNr</ReferenceNumber>");
					xml.append("<OrderNumber>LocalReferenceNumber</OrderNumber>");	
					xml.append("<TotalNumberPackages>PackagesQuantity</TotalNumberPackages>");	
					xml.append("<TotalNumberPositions>ItemsQuantity</TotalNumberPositions>");	
					xml.append("<AuthorizationNumber>AuthorizationID</AuthorizationNumber>");	
					xml.append("<PreviousProcedure>Procedure-Previous</PreviousProcedure>");	
					xml.append("<DeclarantIsConsignor>ParComb</DeclarantIsConsignor>");						
					xml.append("<Clerk>contact-identity</Clerk>");
					xml.append("<MeansOfTransportInland>");
						xml.append("<TransportMode>Inland-Type</TransportMode>");
					xml.append("</MeansOfTransportInland>");
					xml.append("<MeansOfTransportBorder>");
						xml.append("<TransportMode>Border-Type</TransportMode>");
						xml.append("<TransportationType>Mode</TransportationType>");
						xml.append("<TransportationNumber>Identity 123456</TransportationNumber>");
						xml.append("<TransportationCountry>Nationality</TransportationCountry>");
					xml.append("</MeansOfTransportBorder>");					
					xml.append("<PlaceOfLoading>");
						xml.append("<Code>code</Code>");
						xml.append("<Street>Adr-StreetAndNr</Street>");
						xml.append("<PostalCode>Adr-PostalCode</PostalCode>");
						xml.append("<City>Adr-City</City>");
					xml.append("</PlaceOfLoading>");
					xml.append("<CustomsOffices>");
						xml.append("<CustomsOfficeExport>CustomsOfficeExport</CustomsOfficeExport>");
						xml.append("<CustomsOfficeForCompletion>CustomsOfficeForCompletion</CustomsOfficeForCompletion>");					
						xml.append("<RealOfficeOfExit>RealOfficeOfExit</RealOfficeOfExit>");
					xml.append("</CustomsOffices>");
					xml.append("<Seal>");
						xml.append("<Kind>Type</Kind>");
						xml.append("<Number>Qu</Number>");
						xml.append("<UseOfTydenseals>HE-UseOfTydenseals</UseOfTydenseals>");
						xml.append("<UseOfTydensealStock>HE-UseOfTydensealStock</UseOfTydensealStock>");
					xml.append("<SealNumber>SealsID-1Identity</SealNumber>");
					xml.append("<SealNumber>SealsID-2Identity</SealNumber>");
					xml.append("<SealNumber>SealsID-3Identity</SealNumber>");
		            xml.append("</Seal>");
					xml.append("<Business>");
						xml.append("<BusinessTypeCode>business</BusinessTypeCode>");
						xml.append("<InvoicePrice>123456.78</InvoicePrice>");
						xml.append("<Currency>WKZ</Currency>");
			        xml.append("</Business>");
			        xml.append("<LoadingTime>");
			        	xml.append("<BeginTime>20081010134:57</BeginTime>");
			        	xml.append("<EndTime>20081011134157</EndTime>");
			        xml.append("</LoadingTime>");
					xml.append("<Forwarder>");						
						xml.append("<ETNAdress>ETN134134342</ETNAdress>");
					xml.append("</Forwarder>");
					xml.append("<ForwarderTIN>");
					    xml.append("<CustomerIdentifier>KNR123123</CustomerIdentifier>");					
				    xml.append("</ForwarderTIN>");
					xml.append("<ForwarderContactPerson>");
						xml.append("<Clerk>For clerk</Clerk>");
						xml.append("<Email>e-maile</Email>");
						xml.append("<FaxNumber>0987654</FaxNumber>");
						xml.append("<PhoneNumber>01234567</PhoneNumber>");
					xml.append("</ForwarderContactPerson>");					
					xml.append("<Declarant>");						
						xml.append("<ETNAdress>ETN234532</ETNAdress>");
						xml.append("<Address>");
						xml.append("<Name>TransTrans</Name>");
						xml.append("<Street>Koenigsallee 1</Street>");
						xml.append("<City>Duesseldorf</City>");
						xml.append("<PostalCode>12345</PostalCode>");
						xml.append("<Country>DE</Country>");
						xml.append("</Address>");
					xml.append("</Declarant>");
						xml.append("<DeclarantTIN>");
						xml.append("<TIN>KNR123123</TIN>");
						xml.append("<IsTINGermanApprovalNumber>ETN134134342</IsTINGermanApprovalNumber>");
						xml.append("<CustomerIdentifier>CustomerID</CustomerIdentifier>");
				    xml.append("</DeclarantTIN>");					
					xml.append("<DeclarantContactPerson>");
						xml.append("<Position>sitzende</Position>");
						xml.append("<Clerk>Peter Gedoens</Clerk>");
						xml.append("<Email>petergedoens.de</Email>");
						xml.append("<FaxNumber>4654564</FaxNumber>");
						xml.append("<PhoneNumber>4654654</PhoneNumber>");
					xml.append("</DeclarantContactPerson>");
					xml.append("<Consignee>");	
					xml.append("<Address>");
						xml.append("<Name>ImportExport</Name>");
						xml.append("<Street>Gurkenstr. 3</Street>");
						xml.append("<City>Magdeburg</City>");
						xml.append("<PostalCode>54567</PostalCode>");
						xml.append("<Country>DE</Country>");
						xml.append("</Address>");
					xml.append("</Consignee>");
					xml.append("<ConsigneeTIN>");
						xml.append("<TIN>tinNr</TIN>");
						xml.append("<IsTINGermanApprovalNumber>customsID</IsTINGermanApprovalNumber>");
						xml.append("<CustomerIdentifier>CustomerID</CustomerIdentifier>");						
					xml.append("</ConsigneeTIN>");
					xml.append("<IncoTerms>");
						xml.append("<IncoTerm>Code</IncoTerm>");
						xml.append("<Text>Description</Text>");
						xml.append("<Place>City</Place>");
					xml.append("</IncoTerms>");
					xml.append("<GoodsItem>");
						xml.append("<ItemNumber>1</ItemNumber>");
						xml.append("<ArticleNumber>ArtNr</ArticleNumber>");
						xml.append("<Description>Descriptionm</Description>");
						xml.append("<UCROtherSystem>ExtRegNr</UCROtherSystem>");
						xml.append("<Annotation>Remark</Annotation>");
						xml.append("<ShipmentNumber>3ShNr</ShipmentNumber>");
						xml.append("<OriginFederalState>OrigReg</OriginFederalState>");
						xml.append("<NetMass>NetMass</NetMass>");
						xml.append("<NetMassConfirmation>1</NetMassConfirmation>");
						xml.append("<GrossMass>GrossMass</GrossMass>");
						xml.append("<CommodityCode>");
							xml.append("<TarifCode>KN8</TarifCode>");
							xml.append("<EUTarifCode>TARIC</EUTarifCode>");
							xml.append("<TarifAddition1>1111</TarifAddition1>");
							xml.append("<TarifAddition2>2222</TarifAddition2>");
							xml.append("<Addition>Add</Addition>");
						xml.append("</CommodityCode>");
						xml.append("<CustomsApprovedTreatment>");
							xml.append("<Declared>Dec2</Declared>");
							xml.append("<Previous>Pr</Previous>");
							xml.append("<National>Add</National>");
							xml.append("<CodeForRefund>CHF</CodeForRefund>");
						xml.append("</CustomsApprovedTreatment>");
						xml.append("<Statistic>");
							xml.append("<AdditionalUnit>SQ</AdditionalUnit>");
							xml.append("<AdditionalUnitConfirmation>0</AdditionalUnitConfirmation>");
							xml.append("<StatisticalValue>SV</StatisticalValue>");
							xml.append("<StatisticalValueConfirmation>1</StatisticalValueConfirmation>");
							xml.append("<Value>11.2</Value>");
							xml.append("<Currency>EUR</Currency>");							
						xml.append("</Statistic>");
						xml.append("<Consignee>");
							xml.append("<CustomerIdentifier>KNR24423P1</CustomerIdentifier>");
							xml.append("<Name>ImportExport1</Name>");
							xml.append("<Street>Gurkenstr. 2</Street>");
							xml.append("<City>Eggenstein</City>");
							xml.append("<PostalCode>13457</PostalCode>");
							xml.append("<Country>DE</Country>");
						xml.append("</Consignee>");
						xml.append("<ConsigneeTIN>");
							xml.append("<TIN>KNR324324P1</TIN>");
						xml.append("</ConsigneeTIN>");
						xml.append("<Packages>");
						xml.append("<Quantity>Quantity</Quantity>");
						xml.append("<SequentialNumber>Nr</SequentialNumber>");
						xml.append("<Type>type</Type>");
						xml.append("<Marks>MaNr</Marks>");
						xml.append("</Packages>");
						xml.append("<Container>");
							xml.append("<ContainerNumber>CoNr1</ContainerNumber>");						
						xml.append("</Container>");
						xml.append("<Container>");
							xml.append("<ContainerNumber>CoNr2</ContainerNumber>");						
						xml.append("</Container>");
						xml.append("<Container>");
							xml.append("<ContainerNumber>CoNr3</ContainerNumber>");
						xml.append("</Container>");
						xml.append("<Document>");
							xml.append("<Qualifier>Type</Qualifier>");
							xml.append("<Type>Category</Type>");
							xml.append("<Reference>Reference</Reference>");
						xml.append("</Document>");
						xml.append("<PreviousDocument>");
							xml.append("<Type>WA</Type>");
							xml.append("<Marks>Reference</Marks>");
							xml.append("<AdditionalInformation>Remark</AdditionalInformation>");							
						xml.append("</PreviousDocument>");
						xml.append("<PreviousDocument>");
							xml.append("<Type>WA2</Type>");
							xml.append("<AdditionalInformation>Wa2 add info</AdditionalInformation>");
							xml.append("<Marks>wa2 marks</Marks>");
						xml.append("</PreviousDocument>");
					
					xml.append("</GoodsItem>");
					xml.append("<GoodsItem>");
						xml.append("<ItemNumber>2</ItemNumber>");
						xml.append("<ArticleNumber>220200300</ArticleNumber>");
						xml.append("<Description>2Unnuetzer Kram</Description>");
						xml.append("<UCROtherSystem>220100200</UCROtherSystem>");
						xml.append("<Annotation>22nicht schuetteln, ruehren</Annotation>");
						xml.append("<ShipmentNumber>220200100</ShipmentNumber>");
						xml.append("<OriginFederalState>22</OriginFederalState>");
						xml.append("<NetMass>2200.123</NetMass>");
						xml.append("<NetMassConfirmation>2</NetMassConfirmation>");
						xml.append("<GrossMass>2200.321</GrossMass>");
						xml.append("<CommodityCode>");
							xml.append("<TarifCode>2221.1234</TarifCode>");
							xml.append("<EUTarifCode>2234.4321</EUTarifCode>");
							xml.append("<TarifAddition1>2211</TarifAddition1>");
							xml.append("<TarifAddition2>2233</TarifAddition2>");
							xml.append("<Addition>222</Addition>");
						xml.append("</CommodityCode>");
						xml.append("<CustomsApprovedTreatment>");
							xml.append("<Declared>23</Declared>");
							xml.append("<Previous>34</Previous>");
							xml.append("<National>CHF</National>");
							xml.append("<CodeForRefund>EUR</CodeForRefund>");
						xml.append("</CustomsApprovedTreatment>");
						xml.append("<Statistic>");
							xml.append("<AdditionalUnit>223123.321</AdditionalUnit>");
							xml.append("<AdditionalUnitConfirmation>0</AdditionalUnitConfirmation>");
							xml.append("<StatisticalValue>221321</StatisticalValue>");
							xml.append("<StatisticalValueConfirmation>0</StatisticalValueConfirmation>");
							xml.append("<Value>22.2</Value>");
							xml.append("<Currency>EUR</Currency>");							
						xml.append("</Statistic>");
						xml.append("<Consignee>");
							xml.append("<CustomerIdentifier>KNR22423P1</CustomerIdentifier>");
							xml.append("<Name>ImportExport22</Name>");
							xml.append("<Street>Gurkenstr. 22</Street>");
							xml.append("<City>Eggenstein2</City>");
							xml.append("<PostalCode>22222</PostalCode>");
							xml.append("<Country>FR</Country>");
						xml.append("</Consignee>");
						xml.append("<ConsigneeTIN>");
							xml.append("<TIN>KNR224324P1</TIN>");
						xml.append("</ConsigneeTIN>");
						xml.append("<PreviousDocument>");
							xml.append("<Type>280</Type>");
							xml.append("<AdditionalInformation>Zusatzgedoens Vordok</AdditionalInformation>");
							xml.append("<Marks>AndSpencer</Marks>");
						xml.append("</PreviousDocument>");
						xml.append("<PreviousDocument>");
							xml.append("<Type>2WA</Type>");
							xml.append("<AdditionalInformation>2Wa add info</AdditionalInformation>");
							xml.append("<Marks>2wa marks</Marks>");
						xml.append("</PreviousDocument>");
						xml.append("<PreviousDocument>");
							xml.append("<Type>2WA2</Type>");
							xml.append("<AdditionalInformation>2Wa2 add info</AdditionalInformation>");
							xml.append("<Marks>2wa2 marks</Marks>");
						xml.append("</PreviousDocument>");
				
						xml.append("</GoodsItem>");
					
				xml.append("</GoodsDeclaration>");
			xml.append("</ReverseDeclaration>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");
		
		return xml.toString();
	}
}
