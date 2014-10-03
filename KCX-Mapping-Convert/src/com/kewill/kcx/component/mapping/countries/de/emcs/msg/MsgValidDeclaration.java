package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ComplementConsignee;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.DocumentCertificate;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EaadDraft;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.HeaderEaadDraft;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.MovementGuarantee;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.TransportDetails;

/**
 * Module		: EMCS<br>
 * Created		: 30.04.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSMsgValidDeclaration.
 *              : IE801 / C_EAD_VAL  
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgValidDeclaration extends KCXMessage {

	private String msgName = "EMCSValidDeclaration";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;
	private String dateAndTimeOfValidation;
	private String dispatchImportOffice;
	private String deliveryPlaceCustomsOffice;
	private String competentAuthorityDispatchOffice;
	private String dateAndTimeOfUpdateValidation;
	private String journeyTime;
	private String destinationTypeCode;
	private String transportArrangement;
	private String sequenceNumber;
	private String dateOfDispatch;
	private String timeOfDispatch;
	private String originTypeCode;
	private String invoiceNumber;
	private String invoiceDate;	
	private List <String>importSadList;
	private String transportModeCode;
	private String guarantorTypeCode;	
	private List <EmcsTrader> guarantorList;
	private List<DocumentCertificate> documentCertificateList;
	private EmcsTrader consignee; 
	private ComplementConsignee complementConsignee;
	private EmcsTrader consignor;
	private EmcsTrader placeOfDispatch;
	private EmcsTrader deliveryPlace; 	
	private EmcsTrader transportArranger;
	private EmcsTrader firstTransporter; 
	private List <TransportDetails> transportDetailsList;	
	private PDFInformation pdfInformation;	
	private List <MsgValidDeclarationPos> goodsItemList;
	private Text complementaryInformation;            //EI20110711
	private String upstreamARC;                       //EI20110711
	
	public MsgValidDeclaration() {
		super();				
	}

	public MsgValidDeclaration(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
 
	private enum EValidDeclaration {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,  
		CustomerOrderNumber,     		//not defined
		Clerk,                   		//not defined  
		AadReferenceCode,				ExciseMovementEaad, //ExciseMovementEaad.AadReferenceCode
		DateAndTimeOfValidation, 								//ExciseMovementEaad.DateAndTimeOfValidationEaad
		DispatchImportOffice,    		  //same
		DeliveryPlaceCustomsOffice,		  //same
		CompetentAuthorityDispatchOffice, //same
		DateAndTimeOfUpdateValidation,	  //an EMCS-V20: HeaderEaad.DateOfUpdateValidation
		JourneyTime,					HeaderEaad, //HeaderEaad.JourneyTime
		DestinationTypeCode,					    //HeaderEaad.DestinationTypeCode
		TransportArrangement,					    //HeaderEaad.TransportArrangement		
		SequenceNumber,                             //HeaderEaad.SequenceNumber
		DateOfDispatch,					EaadDraft,  //EaadDraft.DateOfDispatch
		TimeOfDispatch,								//EaadDraft.TimeOfDispatch
		OriginTypeCode,                             //EaadDraft.OriginTypeCode
		InvoiceNumber,                       		//EaadDraft.InvoiceNumber
		InvoiceDate,                          		//EaadDraft.InvoiceDate
		UpstreamARC,								//same                         //EI20110711 
		ImportSad,									//EaadDraft.ImportSad <== Liste!
		TransportModeCode,              TransportMode,
		ComplementaryInformation,       //same 										//EI20110711
		GuarantorTypeCode, 				MovementGuarantee, //MovementGuarantee.GuarantorTypeCode
		Guarantor,									//MovementGuarantee.GuarantorTrader
		DocumentCertificate,			//same
		Consignee, 						ConsigneeTrader,
		ComplementConsignee,			ComplementConsigneeTrader,
		Consignor,						ConsignorTrader,
		PlaceOfDispatch,				PlaceOfDispatchTrader,
		DeliveryPlace, 					DeliveryPlaceTrader, 							
		TransportArranger,				TransportArrangerTrader,
		FirstTransporter, 				FirstTransporterTrader,
		TransportDetails,				//same
		GoodsItem,						BodyEaad,
		PDFInformation,	PdfInformation, PDF;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EValidDeclaration) tag) {
			case ExciseMovementEaad:				
				ExciseMovementEaad exciseMovementEaad = new ExciseMovementEaad(getScanner());
				exciseMovementEaad.parse(tag.name());
				setExciseMovementEaad(exciseMovementEaad);
				break;
			case HeaderEaad:
				HeaderEaadDraft headerEaad = new HeaderEaadDraft(getScanner());
				headerEaad.parse(tag.name());
				setHeaderEaad(headerEaad);
				break;
			case EaadDraft:
				EaadDraft eaadDraft = new EaadDraft(getScanner());
				eaadDraft.parse(tag.name());
				setEaadDraft(eaadDraft);
				break;
				/*
			case Transport:
				Transport transport = new Transport(getScanner());
				transport.parse(tag.name());
				setTransport(transport);
				break;
				*/
			case TransportDetails:
				TransportDetails transportDetails = new TransportDetails(getScanner());
				transportDetails.parse(tag.name());
				if (transportDetailsList == null) {
					transportDetailsList = new Vector<TransportDetails>();
				}
				transportDetailsList.add(transportDetails);
				break;				
			case MovementGuarantee:
				MovementGuarantee movementGuarantee = new MovementGuarantee(getScanner());
				movementGuarantee.parse(tag.name());
				setMovementGuarantee(movementGuarantee);
				break;		
			case DocumentCertificate:
				DocumentCertificate documentCertificate = new DocumentCertificate(getScanner());
				documentCertificate.parse(tag.name());	
				if (documentCertificateList == null) {
					documentCertificateList = new Vector<DocumentCertificate>();
				}
				documentCertificateList.add(documentCertificate);
				break;				
			case Consignee:	
			case ConsigneeTrader:
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());
				break;	
			case ComplementConsignee:	
			case ComplementConsigneeTrader:
				complementConsignee = new ComplementConsignee(getScanner());
				complementConsignee.parse(tag.name());
				break;
			case Consignor:
			case ConsignorTrader:
				consignor = new EmcsTrader(getScanner());
				consignor.parse(tag.name());
				break;				
			case DeliveryPlace:	
			case DeliveryPlaceTrader:
				deliveryPlace = new EmcsTrader(getScanner());
				deliveryPlace.parse(tag.name());
				break;	
			case PlaceOfDispatch:
			case PlaceOfDispatchTrader:
				placeOfDispatch = new EmcsTrader(getScanner());
				placeOfDispatch.parse(tag.name());
				break;
			case TransportArranger:
			case TransportArrangerTrader:
				transportArranger = new EmcsTrader(getScanner());
				transportArranger.parse(tag.name());
				break;
			case Guarantor:
				EmcsTrader guarantor = new EmcsTrader(getScanner());
				guarantor.parse(tag.name());
				if (guarantorList == null) {
					guarantorList = new Vector<EmcsTrader>();
				}
				guarantorList.add(guarantor);
				break;
				
			case FirstTransporter:
			case FirstTransporterTrader:
				firstTransporter = new EmcsTrader(getScanner());
				firstTransporter.parse(tag.name());				
				break;
			case PdfInformation:
			case PDFInformation:
			case PDF:				
				pdfInformation = new PDFInformation(getScanner());
				pdfInformation.parse(tag.name());				
				break;
			case GoodsItem:
			case BodyEaad:				
				MsgValidDeclarationPos goodsItem = new MsgValidDeclarationPos(getScanner());
				goodsItem.parse(tag.name());
				if (goodsItemList == null) {
					goodsItemList = new Vector<MsgValidDeclarationPos>();
				}
				goodsItemList.add(goodsItem);				
				break;				
			default:
				return;
			}
	    } else {
	    	switch ((EValidDeclaration) tag) {
    			case ReferenceNumber:
    			case LocalReferenceNumber:
    				 setReferenceNumber(value);
    				 break;	
    			case CustomerOrderNumber:
    				 setCustomerOrderNumber(value);
    				 break;		
    			case Clerk:
    				 setClerk(value);
    				 break;	
    			case SequenceNumber:
    				 setSequenceNumber(value);
    				 break;
    			case AadReferenceCode:
    				 setAadReferenceCode(value);
    				 break;					 
    			case JourneyTime:
    				 setJourneyTime(value);
    				 break;					 
    			case TransportArrangement:
    				 setTransportArrangement(value);
    				 break;					 
    			case TransportModeCode:
    			case TransportMode:
    				 setTransportModeCode(value);
    				 break;					 
    			case InvoiceNumber:
    				 setInvoiceNumber(value);
    				 break;					 
    			case InvoiceDate:
    				 setInvoiceDate(value);
    				 break;					 
    			case DateAndTimeOfValidation:
    				 setDateAndTimeOfValidation(value);
    				 break;					 
    			case DestinationTypeCode:
    				 setDestinationTypeCode(value);
    				 break;					 
    			case DeliveryPlaceCustomsOffice:
    				 setDeliveryPlaceCustomsOffice(value);
    				 break;					 
    			case DispatchImportOffice:
    				setDispatchImportOffice(value);
    				break;
    			case CompetentAuthorityDispatchOffice:
    				setCompetentAuthorityDispatchOffice(value);
    				break;
    			case DateAndTimeOfUpdateValidation:
    				setDateAndTimeOfUpdateValidation(value);
    				break;
    			case DateOfDispatch:
    				setDateOfDispatch(value);
    				break;
    			case TimeOfDispatch:
    				setTimeOfDispatch(value);
    				break;
    			case OriginTypeCode:
    				setOriginTypeCode(value);
    				break;
    			case ImportSad:
    				if (importSadList == null) {
    					importSadList = new Vector<String>();
    				}
    				importSadList.add(value);
    				break;
    			case GuarantorTypeCode:
    				setGuarantorTypeCode(value);
    				break;
    			case UpstreamARC:
    				setUpstreamARC(value);
    				break;
    			case ComplementaryInformation:
    				//complementaryInformation = new Text(value, attr.getValue("language"));
    				complementaryInformation = new Text(value, attr);  //EI20110926
    				break;
    			default: break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {		
	}

	public Enum translate(String token) {
 		try {
  			return EValidDeclaration.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getCustomerOrderNumber() {
		return this.customerOrderNumber;
	}
	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}	

	public String getClerk() {
		return this.clerk;
	}
	public void setClerk(String argument) {
		this.clerk = argument;
	}
	
	public String getDispatchImportOffice() {
		return this.dispatchImportOffice;
	}
	public void setDispatchImportOffice(String argument) {
		this.dispatchImportOffice = argument;
	}
	public String getCompetentAuthorityDispatchOffice() {
		return this.competentAuthorityDispatchOffice;
	}
	public void setCompetentAuthorityDispatchOffice(String argument) {
		this.competentAuthorityDispatchOffice = argument;
	}
	public String getDateAndTimeOfUpdateValidation() {
		return this.dateAndTimeOfUpdateValidation;
	}
	public void setDateAndTimeOfUpdateValidation(String argument) {
		this.dateAndTimeOfUpdateValidation = argument;
	}
	public String getDateOfDispatch() {
		return this.dateOfDispatch;
	}
	public void setDateOfDispatch(String argument) {
		this.dateOfDispatch = argument;
	}
	
	public String getTimeOfDispatch() {
		return this.timeOfDispatch;
	}
	public void setTimeOfDispatch(String argument) {
		this.timeOfDispatch = argument;
	}	
	public String getOriginTypeCode() {
		return this.originTypeCode;
	}
	public void setOriginTypeCode(String argument) {
		this.originTypeCode = argument;
	}
	public List<String> getImportSadList() {
		return this.importSadList;
	}
	public void setImportSadList(List<String> argument) {
		this.importSadList = argument;
	}
	public void addImportSadList(String argument) {
		if (this.importSadList == null) {
			importSadList = new Vector<String>();
		}
		this.importSadList.add(argument);
	}
	
	public String getGuarantorTypeCode() {
		return this.guarantorTypeCode;
	}
	public void setGuarantorTypeCode(String argument) {
		this.guarantorTypeCode = argument;
	}
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}	

	public String getJourneyTime() {
		return this.journeyTime;
	}
	public void setJourneyTime(String argument) {
		this.journeyTime = argument;
	}

	public String getTransportArrangement() {
		return this.transportArrangement;
	}
	public void setTransportArrangement(String argument) {
		this.transportArrangement = argument;
	}	

	public String getTransportModeCode() {
		return this.transportModeCode;
	}
	public void setTransportModeCode(String argument) {
		this.transportModeCode = argument;
	}	

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public void setInvoiceNumber(String argument) {
		this.invoiceNumber = argument;
	}	

	public String getInvoiceDate() {
		return this.invoiceDate;
	}
	public void setInvoiceDate(String argument) {
		this.invoiceDate = argument;
	}	

	public String getDateAndTimeOfValidation() {
		return this.dateAndTimeOfValidation;
	}
	public void setDateAndTimeOfValidation(String argument) {
		this.dateAndTimeOfValidation = argument;
	}	

	public String getDestinationTypeCode() {
		return this.destinationTypeCode;
	}
	public void setDestinationTypeCode(String argument) {
		this.destinationTypeCode = argument;
	}	

	public String getDeliveryPlaceCustomsOffice() {
		return this.deliveryPlaceCustomsOffice;
	}
	public void setDeliveryPlaceCustomsOffice(String argument) {
		this.deliveryPlaceCustomsOffice = argument;
	}	
	
	public List<DocumentCertificate> getDocumentCertificateList() {
		return this.documentCertificateList;
	}
	public void setDocumentCertificateList(List<DocumentCertificate> list) {
		this.documentCertificateList = list;
	}		
	public List<EmcsTrader> getGuarantorList() {
		return this.guarantorList;
	}
	public void setGuarantorList(List<EmcsTrader> argument) {
		this.guarantorList = argument;
	}	
	public void addGuarantorList(EmcsTrader argument) {
		if (guarantorList == null) {
			guarantorList = new Vector<EmcsTrader>();
		}
		this.guarantorList.add(argument);
	}		
	public EmcsTrader getConsignee() {
		return this.consignee;
	}
	public void setConsignee(EmcsTrader argument) {
		this.consignee = argument;
	}
	public EmcsTrader getConsignor() {
		return this.consignor;
	}
	public void setConsignor(EmcsTrader argument) {
		this.consignor = argument;
	}
	public ComplementConsignee getComplementConsignee() {
		return this.complementConsignee;
	}
	public void setComplementConsignee(ComplementConsignee argument) {
		this.complementConsignee = argument;
	}	
				
	public EmcsTrader getPlaceOfDispatch() {
		return this.placeOfDispatch;
	}
	public void setPlaceOfDispatch(EmcsTrader argument) {
		this.placeOfDispatch = argument;
	}	
	public EmcsTrader getDeliveryPlace() {
		return this.deliveryPlace;
	}
	public void setDeliveryPlace(EmcsTrader argument) {
		this.deliveryPlace = argument;
	}	
	public EmcsTrader getTransportArranger() {
		return this.transportArranger;
	}
	public void seTransportArranger(EmcsTrader argument) {
		this.transportArranger = argument;
	}
	public EmcsTrader getFirstTransporter() {
		return this.firstTransporter;
	}
	public void setFirstTransporter(EmcsTrader argument) {
		this.firstTransporter = argument;
	}
		
	public List <TransportDetails> getTransportDetailsList() {
		return this.transportDetailsList;
	}
	public void setTransportDetails(List <TransportDetails> argument) {
		this.transportDetailsList = argument;
	}
	public PDFInformation getPdfInformation() {
		return this.pdfInformation;
	}
	public void setPdfInformation(PDFInformation argument) {
		this.pdfInformation = argument;
	}
	public List<MsgValidDeclarationPos> getGoodsItemList() {
		return this.goodsItemList;
	}
	public void setGoodsItem(List<MsgValidDeclarationPos> argument) {
		this.goodsItemList = argument;
	}	
	public void addGoodsItem(MsgValidDeclarationPos argument) {
		if (goodsItemList == null) {
			goodsItemList = new Vector<MsgValidDeclarationPos>();
		}
		goodsItemList.add(argument);
	}

	public void setExciseMovementEaad(ExciseMovementEaad argument) {		
		if (argument == null) {
			return;
		}
	
		this.aadReferenceCode =	argument.getAadReferenceCode();
		this.dateAndTimeOfValidation =	argument.getDateAndTimeOfValidationOfEaad();
	}
	
	public void setHeaderEaad(HeaderEaadDraft argument) {
		if (argument == null) {
			return;
		}
	
		this.journeyTime =	argument.getJourneyTime();
		this.destinationTypeCode =	argument.getDestinationTypeCode();
		this.transportArrangement =	argument.getTransportArrangement();
		this.sequenceNumber =	argument.getSequenceNumber();	
		this.dateAndTimeOfUpdateValidation = argument.getDateOfUpdateValidation();   //EI20110927 new for EMCS V20
	}                                              
	
	public void setEaadDraft(EaadDraft argument) {
		if (argument == null) {
			return;
		}
	
		this.dateOfDispatch =	argument.getDateOfDispatch();
		this.timeOfDispatch =	argument.getTimeOfDispatch();
		this.originTypeCode =	argument.getOriginTypeCode();
		this.invoiceNumber =	argument.getInvoiceNumber();
		this.invoiceDate =	argument.getInvoiceDate();	
		this.importSadList = argument.getImportSadList();
	}  
	public void setTransport(Transport argument) {
		if (argument == null) {
			return;
		}
	
		this.transportModeCode =	argument.getMode();
	}  
	
	public void setMovementGuarantee(MovementGuarantee argument) {
		if (argument == null) {
			return;
	    }
	
		this.guarantorTypeCode = argument.getGuarantorTypeCode();
		this.guarantorList = argument.getGuarantorList();
	}  	
	
	public Text getComplementaryInformation() {
		return complementaryInformation;	
	}
	public void setComplementaryInformation(Text complementaryInformation) {
		this.complementaryInformation = complementaryInformation;
	}
	
	public String getUpstreamARC() {
		return upstreamARC;	
	}
	public void setUpstreamARC(String value) {
		this.upstreamARC = value;
	}
}
