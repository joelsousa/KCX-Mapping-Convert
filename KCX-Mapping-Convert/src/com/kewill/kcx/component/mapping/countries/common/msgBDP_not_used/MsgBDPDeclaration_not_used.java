package com.kewill.kcx.component.mapping.countries.common.msgBDP_not_used;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Cap;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.MeansOfIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.OutwardProcessing;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Product;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Reentry;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;

/**
 * Module       : Export/aes.<br>
 * Created      : 2.07.2012<br>
 * Description	: Kids Version 2.0.00
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MsgBDPDeclaration_not_used extends KCXMessage {
	
	private String msgName = "ExportDeclaration";
	private String fromFormat = "";              //EI20120913: used for PreviousDocument: "UIDS" or "KIDS"
	private String areaCode;
	private String typeOfPermit; 	
	private String typeOfPermitCH;	
	private String kindOfDeclaration;                   
	private String statusCode;                         					
	private String nctsType;							
	private String ucrNumber;
	private String correctionReason;                    
	private String finalCode;                          
	private String declarationTime;
	private String acceptanceDateTime;
	private String departureDate; 
	private String dispatchCountry; 
	private String destinationCountry;
	private String situationCode;  
    private String paymentType;    
    private String transportInContainer;
    private String netMass;
    private String grossMass;	    
    private String uCROtherSystem;
    private String annotation;
    private String shipmentNumber;
    private String referenceNumber;
    private String orderNumber;
    private String totalNumberPositions; 
    private String totalNumberOfPackages;  		
    private String authorizationNumber;   
    private String previousProcedure;  
     
    private String declarantIsConsignor;
    private String typeOfRepresentation;    
    private String correctionCode;
    private String bunchNumber;	
	private String language;	
	private String transferToTransitSystem;
	private ContactPerson contact;             
	private String validity;                 
	private WareHouse wareHouse;            
	private TransportMeans meansOfTransport;
	private TransportMeans trmInland;
	private TransportMeans trmDeparture;
	private TransportMeans trmBorder;
	private PlaceOfLoading placeOfLoading;	
	private String customsOfficeExport;		    	
	private String customsOfficeForCompletion;	
	private String intendedOfficeOfExit;			
	private TIN supervisingCustomsOfficeTIN;
	private Party supervisingCustomsOffice;               
	private String placeOfDeclaration;
	private Business business;
	private Route transportationRoute;               
    private Seal  seal;    
    private ExportRefundHeader exportRefundHeader;
    private HeaderExtensions headerExtensions;       //only UIDS
    private LoadingTime loadingTime;	
    private Party forwarder;
    private TIN   forwarderTIN;
    private TIN messageReceiverTIN;   
	private Party consignor;  
    private TIN consignorTIN;
    private ContactPerson consignorContactPerson;      
	private Party consignorSecurity;      
	private TIN consignorSecurityTIN;     
	private Party declarant; 
    private TIN declarantTIN;
    private ContactPerson declarantContactPerson;
    private String declarantNumber;      	
	private Party agent; 
    private TIN agentTIN; 
    private ContactPerson agentContactPerson; 
    private Party subcontractor; 
    private TIN subcontractorTIN;
    private ContactPerson subcontractorContactPerson;
    private Party consignee; 
    private TIN consigneeTIN;
    private ContactPerson consigneeContactPerson;
    private Party consigneeSecurity;         
	private TIN consigneeSecurityTIN; 
	private TIN customsDocumentsReceiverTIN;
	private Party customsDocumentsReceiver;
	private ContactPerson customsDocumentsReceiverContactPerson;
	private Party represented;       
	private TIN representedTIN;       
	private Party warehousekeeper; 
    private TIN warehousekeeperTIN;
    private ContactPerson warehousekeeperContactPerson;
    private Party beneficiary; 
    private TIN beneficiaryTIN;
    private ContactPerson beneficiaryContactPerson;
    private TIN initialSenderTIN;
	private Party initialSender;
	private ContactPerson initialSenderContactPerson;
	private Party carrier;	
	private TIN   carrierTIN;	
	private String descriptionLanguage;
	private String accountNumber;
	private String repertoriumNumber;
	private String controlResultCode;  
    private IncoTerms incoTerms;
    private DocumentV20 document;
    private List <DocumentV20>documentList;   
    private Container container;	
	private List <PreviousDocumentV20>previousDocumentList; 
	private List <Text>addInfoStatementList;    
	private String loadingLists;
	private String securityIndicator;           
	private String injunctionType;
	private String companyNumberTaxPayer;	
	private SpecialMention specialMention;                   
	private List <SpecialMention> specialMentionList = null;  
//new for V20 begin:
	private String invoiceCurrencyType; 
	private String authorizationTrustedExporter; 
	private String procedure; 
	private String relevantDate; 
	private String flagOfStatistic; 
	private String realOfficeOfExit; 
	private String addressCombination; 	
	private TIN    finalRecipientTIN;
	private Party  finalRecipient;
	private OutwardProcessing outwardProcessing;
	private List <Reentry> reentryList;
	private List <MeansOfIdentification> meansOfIdentificationList;
	private List <Product> productList;
	private String dateOfExit; 		
//new for V20 end
    	
	private List <MsgBDPDeclarationPos_not_used>goodsItemList;	
	private Invoice invoice;        
	private Cap cap; 				
	private String temporaryReason;             
			
	private String advanceNoticeTime;                 //EI20090422 used only in Pre-Notification

	//CK20100721 needed for NL, filled with content only if referenced ExportDeclaration
	// has a DeclarationNumber in the DB table DeclNums
	private int declarationNumber;	
	
	
	public MsgBDPDeclaration_not_used() {
			super();
	}	
	public MsgBDPDeclaration_not_used(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	 
	private enum EBdpDeclaration { 	
		//KIDS-Name								
		ReferenceNumber,				//Export, Import, NCTS, Port		
		ShipmentNumber, 				//Export, ------, NCTS			
		AuthorizationNumber,			//Export, ------, NCTS	  			
		PlaceOfLoading,					//Export, ------, NCTS					
		PlaceOfUnLoading,				//------, ------, NCTS				
        AreaCode,         				//Export, ------, ----        
        Procedure,						//Export, ------, ---- 
		TypeOfPermit,     				//Export, ------, ----                                               
		UCRNumber,						//Export, ------, ---- 						
		DateOfDeparture, 				//Export, ------, ----						
		Warehouse,        				//Export, ------, ----
		DestinationCountry,				//Export, ------, NCTS
		DeclarantIsConsignor,			//Export,Import(DeclarantIsConsignee), ----		
		CustomsOfficeExport,			//Export, ------, ----, Port
		CustomsOfficeForCompletion,		//Export, ------, ----	    
		IntendedOfficeOfExit,			//Export, ------, ----
		TransportationRoute,			//Export, ------, NCTS
		LoadingTime,					//Export, ------, ----
		ForwarderTIN, Forwarder,		//Export, ------, ----
		Subcontractor, SubcontractorTIN, SubcontractorContactPerson, //Export, -, -
		CarrierTIN, Carrier,			//Export, ------, NCTS
		PrincipalTIN, Principal, 		//------, ------, NCTS		
		FinalRecipientTIN,				//Export, ------, ----
		FinalRecipient,					//Export, ------, ----
		AddressCombination,				//Export, ------, ----
		CarnetID,                 		//------, ------, NCTS		
		Guarantee,                		//------, ------, NCTS		
		SituationCode,					//Export, ------, NCTS
		PaymentType,				  	//Export, Import, NCTS	
		DeclarationTime,				//Export, ------, NCTS(DeclarationDate)
		DeclarationPriorPresentation, 	//------, Import, ----
		AgentRepresentationCode,      	//------, Import, ----
		PreTaxDeductionCode,          	//------, Import, ----
		TransportInContainer,		  	//Export, Import, NCTS	
		Business,					 	//Export, Import, ----		
		PlaceOfDeclaration,			  	//Export, Import, NCTS(DeclarationPlace)	
		CustomsOfficeOfDeclaration,	  	//------, Import, OfficeOfDeparture
		Traders,		              	//-(neu), Import, -(neu)
		DispatchCountry,				//Export, Import, NCTS	
		CustomsOfficeEntry,        		//------, Import, OfficeOfDestination
		ImporterLocation,           	//------, Import, ----
		GoodsLocation,             		//------, Import, ----
		DestinationFederalState,    	//------, Import, ----
		TaxOffice,                  	//------, Import, ----
		CustomsStatus,             		//------, Import, ----
		StatisticalStatus,         		//------, Import, ----
		TotalNumberPositions,			//Export, Import, NCTS    	
		NetMass,						//Export, Import, ----				
		GrossMass,						//Export, Import, NCTS(TotalGrossMass)	
		PreviousDocument,				//Export, Import, ----
		      Number,					//Reference, Number, ----
		MeansOfTransportBorder,			//Export, Import, NCTS
		MeansOfTransportCrossingBorder, //------, ------, NCTS		
		MeansOfTransportInland,  		//Export, Import, NCTS
		MeansOfTransportArrival,      	//------, Import, ----
		MeansOfTransportDeparture,    	//------, Import, NCTS		
		ContactPerson,					//Export, Import, NCTS(Clerk)
		IncoTerms,   					//Export, Import, ----
		     Details,					//Text, Details, ----
		Invoice,						//Export, Import, ----
		      InvoicePrice,				//Amount, InvoicePrice, ----
		WriteOffSumAType,     			//------, Import, ----
		WriteOffBonWarAvuvAuthNum,  	//------, Import, ----
		WriteOffBonWarRefNum,   		//------, Import, ----
		CustomerOrderNumber,   			//------, Import, ----
		Document,						//Export, Import, ----	
		      //Number,					//Reference, Number, ----
		Container,          			//Export, Import, ----               
		Deferment,     					//------, Import, ----
		AdditionalInformation,     		//------, Import, ----
		DV1,     				    	//------, Import, ----		
		PORT,     						//------, ------, ----, Port aber auch 	GoodsItem!		
		GoodsItem,						//Export, Import, NCTS, ----
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EBdpDeclaration) tag) {
			default:
					return;
			}
		} else {				
			switch ((EBdpDeclaration) tag) {
			default:
				return;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EBdpDeclaration.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
	
}