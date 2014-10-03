package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: EAD Export A... Document.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class EADocument extends KCXMessage {
					
	 private String eaNumber;	             // Nummer der AE (Masterticket)
	 private String dispatchMrn;       	     // veomrn MRN des Versandscheines
	 private String customsProcedure;	 // artzol Art der Anmeldung: ZAPP: DOK,AUS... BHT:1000G/1000N, 555G....
	 private String simplificationFlag;	         // kzbef Kennzeichen Befreiung 	
	 private String simplificationCode;
	 private String customsOfficeExport;     // extdst
	 private String destinationCountry;      // ldbe    
	 private String dispatchCountry;         // ldve 
	 private String valueFlag;	             // kz100 Kz. Wert > 1000 EUR 	
	 private String marketOrderFlag;	     // kzmow Kennzeichen Marktordnungswaren 	
	 private String previousDocument; 	     // vorpap
	 private String authorizationNumber;	 // bewnr
	 
	 private String sumaReferenceNumber;	 // regmow Registrier-/Bezugsnr (DOK); ATB-Nr. der summar. Anmeldung (MIT)
	 private String reference30A;	         // esuma	
	 private String sumaSimplificationReason;	 // asuma
	 private String officialStatement;	             // pruef
	 
	 private Party    declarant;	     // adr, zbanm, kdnran, anmid  
	 private Party    consignor;	     // adr,  kdnrve 
	 private Party    consignee;	     // adr, kdnrem, empid 		
	 private Party    notify;	     // adr, kdnrmi   	
	 
	 private List<EAPosition> eaPositionList;	 // aep in FSS: nur 1 pos erlaubt
	
    public EADocument() {
		super();  
    }

	public EADocument(XmlMsgScanner scanner) {
	  	super(scanner);
	}
	 
		 private enum EEADocument {	
				// Kids-TagNames, 			UIDS-TagNames;
			 EANumber,
			 DispatchMrn,  DispatchMRN,    
			 CustomsProcedure, SimplificationFlag,  SimplificationCode,
			 Declarant, //DeclarantCustomsIdentifier, DeclarantCustomerIdentifier, DeclarantAdditionalIdentification,	 
			 Consignor, //ConsignorCustomerIdentifier,    
			 Consignee, //ConsigneeCustomerIdentifier,  ConsigneeAdditionalIdentification,
			 Notify, //NotifyCustomerIdentifier,
			 CustomsOfficeExport, 
			 AuthorizationNumber,
			 PreviousDocument,
			 DestinationCountry,
			 DispatchCountry,  
			 ValueGreater1000Flag,			 
			 MarketOrderFlag, 			 
						 	   		
			 SumaReferenceNumber,Reference30A, SumaSimplificationReason,
			 OfficialStatement,		 
			 EAPosition, Position;
		 }	 
		 
		 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {		
				switch ((EEADocument) tag) {
				case Declarant:
					declarant = new Party(getScanner());
					declarant.parse(tag.name());						
					break;		
				case Consignor:
					consignor = new Party(getScanner());
					consignor.parse(tag.name());						
					break;	
				case Consignee:
					consignee = new Party(getScanner());
					consignee.parse(tag.name());						
					break;
				case Notify:
					notify = new Party(getScanner());
					notify.parse(tag.name());						
					break;	
				case Position:
				case EAPosition:
					EAPosition position = new EAPosition(getScanner());
					position.parse(tag.name());	
					addPositionList(position);
					break;	
					
					default: break;			
				}
			} else {
				   						
				switch ((EEADocument) tag) {			
					case EANumber:
						setEaNumber(value);
						break;
					case DispatchMrn:
					case DispatchMRN:
						setDispatchMrn(value);
						break;
					case CustomsProcedure:
						setCustomsProcedure(value);
						break;
					case SimplificationFlag:
						setSimplificationFlag(value);
						break;
					case SimplificationCode:
						setSimplificationCode(value);
						break;
					case DestinationCountry:
						setDestinationCountry(value);
						break;
					case DispatchCountry:
						setDispatchCountry(value);
						break;
					case ValueGreater1000Flag:
						setValueGreater1000Flag(value);
						break;
										
					case MarketOrderFlag:
						setMarketOrderFlag(value);
						break;
					case SumaReferenceNumber:
						setSumaReferenceNumber(value);
						break;					
					case SumaSimplificationReason:
						setSumaSimplificationReason(value);
						break;
					case Reference30A:						
						setReference30A(value);
						break;
					case PreviousDocument:
						setPreviousDocument(value);
						break;
					case AuthorizationNumber:
						setAuthorizationNumber(value);
						break;
					case OfficialStatement:
						setOfficialStatement(value);
						break;
					case CustomsOfficeExport:
						setCustomsOfficeExport(value);
						break;
					default:
						return;
				}								 				
			}
		}

		 public void stoppElement(Enum tag) {
		 }
		
		 public Enum translate(String token) {
			 try {
				return EEADocument.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
		 }
 
	    public String getEaNumber() {
			return eaNumber;
		}
		public void setEaNumber(String argument) {
			this.eaNumber = argument;
		}					
		
		public String getDispatchMrn() {
			return dispatchMrn;
		}
		public void setDispatchMrn(String argument) {
			this.dispatchMrn = argument;
		}
		 
	    public String getCustomsProcedure() {
			return customsProcedure;
		}
		public void setCustomsProcedure(String argument) {
			this.customsProcedure = argument;
		}					
	 
		public String getCustomsOfficeExport() {
			return customsOfficeExport;
		}
		public void setCustomsOfficeExport(String argument) {
			this.customsOfficeExport = argument;
		}	
		
	    public String getDestinationCountry() {
			return destinationCountry;
		}
		public void setDestinationCountry(String argument) {
			this.destinationCountry = argument;
		}					
			 
		public String getDispatchCountry() {
			return dispatchCountry;
		}
		public void setDispatchCountry(String argument) {
			this.dispatchCountry = argument;
		}	
		
		public String getValueGreater1000Flag() {
			return valueFlag;
		}
		public void setValueGreater1000Flag(String argument) {
			this.valueFlag = argument;
		}	
				
		public String getSimplificationFlag() {
			return simplificationFlag;
		}
		public void setSimplificationFlag(String argument) {
			this.simplificationFlag = argument;
		}
		
		
		
		public String getMarketOrderFlag() {
			return marketOrderFlag;
		}
		public void setMarketOrderFlag(String argument) {
			this.marketOrderFlag = argument;
		}	
		
		public String getSumaReferenceNumber() {
			return sumaReferenceNumber;
		}
		public void setSumaReferenceNumber(String argument) {
			this.sumaReferenceNumber = argument;
		}	
		
		public String getSimplificationCode() {
			return simplificationCode;
		}
		public void setSimplificationCode(String argument) {
			this.simplificationCode = argument;
		}	
		
		public String getSumaSimplificationReason() {
			return sumaSimplificationReason;
		}
		public void setSumaSimplificationReason(String argument) {
			this.sumaSimplificationReason = argument;
		}	
		
	    public String getReference30A() {
			return reference30A;
		}
		public void setReference30A(String argument) {
			this.reference30A = argument;
		}							
		
		public String getPreviousDocument() {
			return previousDocument;
		}
		public void setPreviousDocument(String argument) {
			this.previousDocument = argument;
		}	
		
		public String getAuthorizationNumber() {
			return authorizationNumber;
		}
		public void setAuthorizationNumber(String argument) {
			this.authorizationNumber = argument;
		}	
		
		public String getOfficialStatement() {
			return officialStatement;
		}
		public void setOfficialStatement(String argument) {
			this.officialStatement = argument;
		}	
				
		public Party getDeclarant() {
			return declarant;
		}
		public void setDeclarant(Party party) {
			this.declarant = party;
		}			
		
		public Party getConsignor() {
			return consignor;
		}
		public void setConsignor(Party party) {
			this.consignor = party;
		}
		
		public Party getConsignee() {
			return consignee;
		}
		public void setConsignee(Party party) {
			this.consignee = party;
		}	
		
		public Party getNotify() {
			return notify;
		}
		public void setNotify(Party party) {
			this.notify = party;
		}	
		
		public List <EAPosition> getPositionList() {
			return eaPositionList;
		}
		public void setPositionList(List <EAPosition> list) {
			this.eaPositionList = list;
		}	
		public void addPositionList(EAPosition position) {
			if (eaPositionList == null) {
				eaPositionList = new Vector<EAPosition>();
			}
			this.eaPositionList.add(position);
		}	
		
		
		public boolean isEmpty() {
			return (Utils.isStringEmpty(this.eaNumber) && Utils.isStringEmpty(this.dispatchMrn) && 
			        Utils.isStringEmpty(this.customsProcedure));
		}
}


