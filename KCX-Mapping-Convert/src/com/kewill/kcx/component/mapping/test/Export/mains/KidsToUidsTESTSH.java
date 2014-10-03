/*
 * Function    : KidsToUidsTESTSH.java
 * Titel       :
 * Date        : 27.08.2008
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

package com.kewill.kcx.component.mapping.test.Export.mains;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.common.start.customer.out.KidsToUidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToUidsTESTSH {

	public static void main(String[] args) {
		KidsToUidsExtern kidsToUids = new KidsToUidsExtern();
		String payload = getErrorMessage();
    	Utils.log("kidsErrorMessage : " + payload);
    	
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
	
	private static String getErrorMessage() {
		StringBuffer xml = new StringBuffer();
		
/*		<?xml version="1.0" encoding="UTF-8"?>
		<xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema">
		    <xsd:include schemaLocation="kiff_export_types.xsd"/>
			<xsd:element name="Completion">
						<xsd:element  name="GoodsDeclaration" minOccurs="1" maxOccurs="1">
						x			<xsd:element name="KindOfAnswer" type="xsd:integer" minOccurs="0"/>
						x			<xsd:element name="UniqueConsignmentReferenceNumber" type="xsd:string" minOccurs="0"/>
						x			<xsd:element name="CompletionTime" type="ST_DateTime" minOccurs="0"/>
						x			<xsd:element name="ReferenceNumber" type="xsd:string" />
						x			<xsd:element name="OrderNumber" type="xsd:string" minOccurs="0"/>
						x			<xsd:element name="PreviousProcedure" type="xsd:string" minOccurs="0"/>
						x			<xsd:element name="ReceiverCustomerNumber" type="xsd:string" minOccurs="0"/>
						x			<xsd:element name="Clerk" type="xsd:string" minOccurs="0"/>
						x			<xsd:element name="MeansOfTransportInland" type="CT_TransportMeansType" minOccurs="0"/>
						x			<xsd:element name="MeansOfTransportBorder" type="CT_TransportMeansType" minOccurs="0"/>
						x			<xsd:element name="CustomsOfficeExport" type="CT_CustomsOffice" minOccurs="0"/>
						x			<xsd:element name="CustomsOfficeForCompletion" type="CT_CustomsOffice" minOccurs="0"/>
						x			<xsd:element name="Business" type="CT_Business" minOccurs="0"/>
						x			<xsd:element name="Forwarder" type="CT_AdressType" minOccurs="0"/>
						x			<xsd:element name="ForwarderContactPerson" type="CT_ContactPerson" minOccurs="0"/>
									<xsd:element name="Declarant" type="CT_AdressType" minOccurs="0"/>
									<xsd:element name="DeclarantTIN" type="CT_TINType" minOccurs="0"/>
									<xsd:element name="DeclarantContactPerson" type="CT_ContactPerson" minOccurs="0"/>
									<xsd:element name="Consignee" type="CT_AdressType" minOccurs="0"/>
									<xsd:element name="ConsigneeTIN" type="CT_TINType" minOccurs="0"/>
									<xsd:element name="IncoTerms" type="CT_IncoTerm" minOccurs="0"/>
									<xsd:element name="GoodsItem" maxOccurs="999">
												<xsd:element name="ItemNumber" type="xsd:integer"/>
												<xsd:element name="ArticleNumber" type="xsd:string" minOccurs="0" />
												<xsd:element name="Description" type="xsd:string"/> 
												<xsd:element name="UCROtherSystem" type="xsd:string" minOccurs="0"/>
												<xsd:element name="Annotation" type="xsd:string" minOccurs="0"/>
												<xsd:element name="ShipmentNumber" type="xsd:string"/>
												<xsd:element name="OriginFederalState" type="xsd:string" />
												<xsd:element name="NetMass" type="xsd:decimal" />
												<xsd:element name="NetMassConfirmation" type="ST_ConfirmationCode" minOccurs="0"/>
												<xsd:element name="GrossMass" type="xsd:decimal" minOccurs="0"/>
												<xsd:element name="CommodityCode" type="CT_CommodityCodeType" minOccurs="0"/>
												<xsd:element name="CustomsApprovedTreatment" type="CT_ApprovedTreatment" minOccurs="0"/>
												<xsd:element name="Statistic" type="CT_StatisticType" minOccurs="0"/>
												<xsd:element name="Consignee" type="CT_AdressType" minOccurs="0"/>
												<xsd:element name="ConsigneeTIN" type="CT_TINType" minOccurs="0"/>
												<xsd:element name="PreviousDocument" type="CT_PreviousDocumentType" minOccurs="0"/>
*/
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
				xml.append("<Method>Error Message</Method>");				
				xml.append("<MessageTP>");					
					xml.append("<CountryCode>DE</CountryCode>");					
					xml.append("<Procedure>EXPORT</Procedure>");					
					xml.append("<ProcedureSpecification>PS</ProcedureSpecification>");				
					xml.append("<MessageName>Completion</MessageName>");				
					xml.append("<Release>1.0.00</Release>");				
				xml.append("</MessageTP>");			
				xml.append("<MessageID>MsgID20080902</MessageID>");			
				xml.append("<InReplyTo>IRT</InReplyTo>");			
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<Completion>");				
				xml.append("<GoodsDeclaration>");				
					xml.append("<KindOfAnswer>1</KindOfAnswer>");
					xml.append("<UniqueConsignmentReferenceNumber>08CH12345678912345</UniqueConsignmentReferenceNumber>");
					xml.append("<CompletionTime>2008-09-2313:41:57 +0200</CompletionTime>");
					xml.append("<ReferenceNumber>Bezugsnummer</ReferenceNumber>");			
					xml.append("<OrderNumber>Export</OrderNumber>");			
					xml.append("<PreviousProcedure>AES</PreviousProcedure>");
					xml.append("<ReceiverCustomerNumber>345345345</ReceiverCustomerNumber>");
					xml.append("<Clerk>Heise</Clerk>");
					xml.append("<MeansOfTransportInland>");
						xml.append("<TransportMode>30</TransportMode>");
					xml.append("</MeansOfTransportInland>");
					xml.append("<MeansOfTransportBorder>");
						xml.append("<TransportMode>31</TransportMode>");
						xml.append("<TransportationType>40</TransportationType>");
						xml.append("<TransportationNumber>BS 123456</TransportationNumber>");
						xml.append("<TransportationCountry>40</TransportationCountry>");
					xml.append("</MeansOfTransportBorder>");
					xml.append("<CustomsOfficeExport>DE123456</CustomsOfficeExport>");
					xml.append("<CustomsOfficeForCompletion>CH987654</CustomsOfficeForCompletion>");
					xml.append("<Business>");
						xml.append("<BusinessTypeCode>CH987654</BusinessTypeCode>");
						xml.append("<InvoicePrice>123456.78</InvoicePrice>");
						xml.append("<Currency>CHF</Currency>");
					xml.append("</Business>");
					xml.append("<Forwarder>");
						xml.append("<CustomerIdentifier>KNR123123</CustomerIdentifier>");
						xml.append("<ETNAdress>ETN134134342</ETNAdress>");
					xml.append("</Forwarder>");
					xml.append("<ForwarderContactPerson>");
						xml.append("<Clerk>Peter Pan</Clerk>");
						xml.append("<Email>dings@bums.de</Email>");
						xml.append("<FaxNumber>0987654</FaxNumber>");
						xml.append("<PhoneNumber>01234567</PhoneNumber>");
					xml.append("</ForwarderContactPerson>");
					xml.append("<DeclarantTIN>");
						xml.append("<TIN>KNR123123</TIN>");
						xml.append("<IsTINGermanApprovalNumber>ETN134134342</IsTINGermanApprovalNumber>");
					xml.append("</DeclarantTIN>");
					xml.append("<Declarant>");
						xml.append("<CustomerIdentifier>KNR123123</CustomerIdentifier>");
						xml.append("<ETNAdress>ETN234532</ETNAdress>");
						xml.append("<Name>TransTrans</Name>");
						xml.append("<Street>Koenigsallee 1</Street>");
						xml.append("<City>Duesseldorf</City>");
						xml.append("<PostalCode>12345</PostalCode>");
						xml.append("<Country>DE</Country>");
					xml.append("</Declarant>");
					xml.append("<DeclarantContactPerson>");
						xml.append("<Position>sitzende</Position>");
						xml.append("<Clerk>Peter Gedoens</Clerk>");
						xml.append("<Email>peter@gedoens.de</Email>");
						xml.append("<FaxNumber>4654564</FaxNumber>");
						xml.append("<PhoneNumber>4654654</PhoneNumber>");
					xml.append("</DeclarantContactPerson>");
					xml.append("<Consignee>");
						xml.append("<CustomerIdentifier>KNR24423</CustomerIdentifier>");
						xml.append("<Name>ImportExport</Name>");
						xml.append("<Street>Gurkenstr. 3</Street>");
						xml.append("<City>Magdeburg</City>");
						xml.append("<PostalCode>54567</PostalCode>");
						xml.append("<Country>DE</Country>");
					xml.append("</Consignee>");
					xml.append("<ConsigneeTIN>");
						xml.append("<TIN>KNR324324</TIN>");
					xml.append("</ConsigneeTIN>");
					xml.append("<IncoTerms>");
						xml.append("<IncoTerm>XYZ</IncoTerm>");
						xml.append("<Text>IncoTermXYZ</Text>");
						xml.append("<Place>Platz</Place>");
					xml.append("</IncoTerms>");
					xml.append("<GoodsItem>");
						xml.append("<ItemNumber>1</ItemNumber>");
						xml.append("<ArticleNumber>100200300</ArticleNumber>");
						xml.append("<Description>Unnuetzer Kram</Description>");
						xml.append("<UCROtherSystem>300100200</UCROtherSystem>");
						xml.append("<Annotation>nicht schuetteln, ruehren</Annotation>");
						xml.append("<ShipmentNumber>300200100</ShipmentNumber>");
						xml.append("<OriginFederalState>NW</OriginFederalState>");
						xml.append("<NetMass>1000.123</NetMass>");
						xml.append("<NetMassConfirmation>1</NetMassConfirmation>");
						xml.append("<GrossMass>2000.321</GrossMass>");
						xml.append("<CommodityCode>");
							xml.append("<TarifCode>4321.1234</TarifCode>");
							xml.append("<EUTarifCode>1234.4321</EUTarifCode>");
							xml.append("<TarifAddition1>1111</TarifAddition1>");
							xml.append("<TarifAddition2>2222</TarifAddition2>");
							xml.append("<Addition>999</Addition>");
						xml.append("</CommodityCode>");
						xml.append("<CustomsApprovedTreatment>");
							xml.append("<Declared>12</Declared>");
							xml.append("<Previous>23</Previous>");
							xml.append("<National>EUR</National>");
							xml.append("<CodeForRefund>CHF</CodeForRefund>");
						xml.append("</CustomsApprovedTreatment>");
						xml.append("<Statistic>");
							xml.append("<AdditionalUnit>123123.321</AdditionalUnit>");
							xml.append("<AdditionalUnitConfirmation>0</AdditionalUnitConfirmation>");
							xml.append("<StatisticalValue>321321</StatisticalValue>");
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
						xml.append("<PreviousDocument>");
							xml.append("<Type>380</Type>");
							xml.append("<AdditionalInformation>Zusatzgedoens Vordok</AdditionalInformation>");
							xml.append("<Marks>AndSpencer</Marks>");
						xml.append("</PreviousDocument>");
						xml.append("<PreviousDocument>");
							xml.append("<Type>WA</Type>");
							xml.append("<AdditionalInformation>Wa add info</AdditionalInformation>");
							xml.append("<Marks>wa marks</Marks>");
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
			xml.append("</Completion>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");

		return xml.toString();
	}
}

/*	INTERNAL STATUS INFORMATION
 *
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
				xml.append("<Method>Error Message</Method>");				
				xml.append("<MessageTP>");					
					xml.append("<CountryCode>DE</CountryCode>");					
					xml.append("<Procedure>EXPORT</Procedure>");					
					xml.append("<ProcedureSpecification>PS</ProcedureSpecification>");				
					xml.append("<MessageName>InternalStatusInformation</MessageName>");				
					xml.append("<Release>1.0.00</Release>");				
				xml.append("</MessageTP>");			
				xml.append("<MessageID>MsgID20080902</MessageID>");			
				xml.append("<InReplyTo>IRT</InReplyTo>");			
			xml.append("</Header>");			
		xml.append("</soap:Header>");			
		xml.append("<soap:Body>");				
			xml.append("<InternalStatusInformation>");				
				xml.append("<GoodsDeclaration>");				
					xml.append("<ReferenceNumber>Bezugsnummer</ReferenceNumber>");			
					xml.append("<OrderNumber>Export</OrderNumber>");			
					xml.append("<CorrectionNumber>1</CorrectionNumber>");
					xml.append("<TemporaryUCR>MX5</TemporaryUCR>");
					xml.append("<DateNewStatus>2008-09-11</DateNewStatus>");
					xml.append("<TimeNewStatus>12:34</TimeNewStatus>");
					xml.append("<NewStatus>30</NewStatus>");
				xml.append("</GoodsDeclaration>");
			xml.append("</InternalStatusInformation>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");

CANCELLATION

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
				xml.append("<Receiver>CSFFRA</Receiver>");			
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


 */		
