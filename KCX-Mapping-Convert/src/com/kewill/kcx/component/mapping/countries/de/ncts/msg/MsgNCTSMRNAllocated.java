package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: MsgNCTSMRNAllocated<br>
 * Created		: 08.25.2010<br>
 * Description	: Contains Tag Mapping for fields used in NCTSMRNAllocated message. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */
public class MsgNCTSMRNAllocated extends KCXMessage {

	private String msgName = "NCTSMRNAllocated";
	private String referenceNumber;
	private String ucrNumber;
	private String acceptanceDate;
	private TIN principalTIN;
	private PartyNCTS principal;
	private String officeOfDeparture;
	private PDFInformation pdfInformation;
	private List<String> tydenSealNumberList = new ArrayList<String>();
	private EFormat acceptanceDateFormat;

	public MsgNCTSMRNAllocated() {
		super();
	}
	
	public MsgNCTSMRNAllocated(XMLEventReader parser) throws XMLStreamException {
		super(parser);						
	}
	
	private enum ENCTSMRNAllocated {
		//KIDS:					UIDS:
		ReferenceNumber,        LocalReferenceNumber,
		UCRNumber, 				MRN,
		AcceptanceDate, 		DateOfAcceptance,
		PrincipalTIN,
		Principal,				//same
		OfficeOfDeparture, 		//same
								//EnRouteEvent
		PDFInformation, 		PDF,
		TydenSealNumber, 		Tydensealnumber;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ENCTSMRNAllocated) tag) {
			case Principal:
				PartyNCTS wrkPrincipal = new PartyNCTS(getScanner());
				wrkPrincipal.parse(tag.name());
				setPrincipal(wrkPrincipal);
				if (wrkPrincipal.getPartyTIN() != null) {
					setPrincipalTIN(wrkPrincipal.getPartyTIN());
				}
				break;
			case PrincipalTIN:
				TIN wrkPrincipalTIN = new TIN(getScanner());
				wrkPrincipalTIN.parse(tag.name());
				setPrincipalTIN(wrkPrincipalTIN);
				break;
			case PDFInformation:
			case PDF:
				PDFInformation wrkPDFInformation = new PDFInformation(getScanner());
				wrkPDFInformation.parse(tag.name());
				setPdfInformation(wrkPDFInformation);
				break;
			default:
				return;
			}
		} else {
			switch ((ENCTSMRNAllocated) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			case MRN:
			case UCRNumber:
				setUcrNumber(value);
				break;
			case DateOfAcceptance:
			case AcceptanceDate:
				setAcceptanceDate(value);
				 //EI20111006: if (tag == ENCTSMRNAllocated.DateOfAcceptance) {
				 if (tag == ENCTSMRNAllocated.AcceptanceDate) {    //EI20111006
					 //setAcceptanceDateFormat(EFormat.KIDS_DateTime);
					 setAcceptanceDateFormat(EFormat.KIDS_Date);     //EI20110815
				 } else {
					 //setAcceptanceDateFormat(EFormat.ST_DateTimeT);
					 setAcceptanceDateFormat(EFormat.ST_Date);     //EI20110815
				 }
				 break;
			case OfficeOfDeparture:
				setOfficeOfDeparture(value);
				break;
			//case EnRouteEvent:
			case Tydensealnumber:
			case TydenSealNumber:
				if (tydenSealNumberList == null) {
					tydenSealNumberList = new Vector <String>();
				}
				tydenSealNumberList.add(value);
			    break;			
			default:
				break;
			}
		}
	}
	
	public void stoppElement(Enum tag) {

	}
	
	public Enum translate(String token) {
 		try {
  			return ENCTSMRNAllocated.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getUcrNumber() {
		return ucrNumber;
	}

	public void setUcrNumber(String ucrNumber) {
		this.ucrNumber = ucrNumber;
	}

	public String getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public TIN getPrincipalTIN() {
		return principalTIN;
	}

	public void setPrincipalTIN(TIN principalTIN) {
		this.principalTIN = principalTIN;
	}

	public PartyNCTS getPrincipal() {
		return principal;
	}

	public void setPrincipal(PartyNCTS principal) {
		this.principal = principal;
	}

	public String getOfficeOfDeparture() {
		return officeOfDeparture;
	}

	public void setOfficeOfDeparture(String officeOfDeparture) {
		this.officeOfDeparture = officeOfDeparture;
	}

	public PDFInformation getPdfInformation() {
		return pdfInformation;
	}

	public void setPdfInformation(PDFInformation pdfInformation) {
		this.pdfInformation = pdfInformation;
	}

	public List<String> getTydenSealNumberList() {
		return tydenSealNumberList;
	}

	public void setTydenSealNumberList(List<String> tydenSealNumberList) {
		this.tydenSealNumberList = tydenSealNumberList;
	}

	public EFormat getAcceptanceDateFormat() {
		return acceptanceDateFormat;
	}

	public void setAcceptanceDateFormat(EFormat acceptanceDateFormat) {
		this.acceptanceDateFormat = acceptanceDateFormat;
	}

}
