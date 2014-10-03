package com.kewill.kcx.component.mapping.countries.cy.ics.msg;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS CYPRUS
 * Description	: common methods for Kids2Cy. 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CyprusMessage extends KCXMessage {
	
	private KidsHeader kidsHeader = null;
    private CyprusHeader cyprusHeader = null;
	
	protected String encoding = "UTF-8";
	protected XMLStreamWriter writer;
	
	private CommonFieldsDTO commonFieldsDTO = null;
	
	private enum ECMessages {
		EnveloppeMessage,
		MessageBody;
	}
	
	public CyprusMessage() {
		super();
	}
	
	public CyprusMessage(XmlMsgScanner scanner) {
		super(scanner);
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return ECMessages.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public KidsHeader getKidsHeader() {
		return kidsHeader;
	}

	public void setKidsHeader(KidsHeader kidsHeader) {
		this.kidsHeader = kidsHeader;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public XMLStreamWriter getWriter() {
		return writer;
	}

	public void setWriter(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public CommonFieldsDTO getCommonFieldsDTO() {
		return commonFieldsDTO;
	}

	public void setCommonFieldsDTO(CommonFieldsDTO commonFieldsDTO) {
		this.commonFieldsDTO = commonFieldsDTO;
	}
	
	protected void openElement(String tag) throws XMLStreamException {
		  writer.writeStartElement(tag);
	}
		 
	protected void openElement(String namespaceURI, String tag) throws XMLStreamException {
		writer.setDefaultNamespace(namespaceURI);
		writer.writeStartElement(tag);
		writer.writeDefaultNamespace(namespaceURI);
	}
	
	protected void closeElement() throws XMLStreamException {
	      writer.writeEndElement();
	}
	
	protected void writeElement(String tag, String value) throws XMLStreamException {
	    if (value != null) {
	    	if (!value.trim().equals("")) {
	    		writer.writeStartElement(tag);
	    		writer.writeCharacters(value);
	    		writer.writeEndElement();
    		}
    	}
	}
	
    protected void setAttribute(String tag, String value) throws XMLStreamException {
        writer.writeAttribute(tag, value);
    }

    protected String getTime(String time) {
		String retTime = "";
		
		if (time != null) {
			if (time.length() > 3) {
				retTime = time.substring(0, 4);
			}
		}
		return retTime;
	}
	
    protected void writeStartDocument(String encoding, String version) throws XMLStreamException {
        writer.writeDTD("<?xml version=\"" + version + "\" encoding=\"" + encoding + "\"?>");
    }

    public CyprusHeader getCyprusHeader() {
        return cyprusHeader;
    }

    public void setCyprusHeader(CyprusHeader cyprusHeader) {
        this.cyprusHeader = cyprusHeader;
    }
    
    public void writeHeaderFields() throws XMLStreamException {
    	if (cyprusHeader != null) {
        writeElement("MesSenMES3", cyprusHeader.getMesSenMES3());
        writeElement("MesRecMES6", cyprusHeader.getMesRecMES6());
        writeElement("DatOfPreMES9", cyprusHeader.getDatOfPreMES9());
        writeElement("TimOfPreMES10", cyprusHeader.getTimOfPreMES10());
        writeElement("MesIdeMES19", cyprusHeader.getMesIdeMES19());
        writeElement("MesTypMES20", getCommonFieldsDTO().getTargetMessageType());
    	}
    }
    
    //EI20110714:
    public void writeTransportBorder(TransportMeans border)  throws XMLStreamException {
    	if (border == null) {
    		return;
    	}    	
    	if (Utils.isStringEmpty(border.getTransportMode())) {
    		return;   //is required
    	}
    	if (border.getTransportMode().equals("4")) {
    		writeElement("TraModAtBorHEA76", border.getTransportMode());
    		//the other fields shouldn't be send
    	} else {
            writeElement("TraModAtBorHEA76", border.getTransportMode());
            writeElement("IdeOfMeaOfTraCroHEA85", border.getTransportationNumber());
            writeElement("NatOfMeaOfTraCroHEA87", border.getTransportationCountry());
		}
    }
  //EI20110707:
    public void writeTraderConco(Party party, String partyName) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement(partyName);    	
        if (address != null) {
            writeElement("NamCO17", address.getName());   
            if (Utils.isStringEmpty(address.getHouseNumber())) {
            	writeElement("StrAndNumCO122", address.getStreet());
            } else {
            	writeElement("StrAndNumCO122", address.getStreet() + " " + address.getHouseNumber());
            }
            writeElement("PosCodCO123", address.getPostalCode());
            writeElement("CitCO124", address.getCity());
            writeElement("CouCO125", address.getCountry());
            writeElement("NADLNGCO", address.getLanguage());
        }

		if (party.getPartyTIN() != null) {
			writeElement("TINCO159", party.getPartyTIN().getTIN());
		}
		closeElement();
    }
    
  //EI20110707:
    public void writeTraderConce(Party party, String partyName) throws XMLStreamException {
    	if (party == null) {
    		return;
    	} 
    	Address address = party.getAddress();
    	
    	openElement(partyName);    	
        if (address != null) {
            writeElement("NamCE17", address.getName());  
            if (Utils.isStringEmpty(address.getHouseNumber())) {
            	writeElement("StrAndNumCE122", address.getStreet());     
            } else {
            	writeElement("StrAndNumCE122", address.getStreet() + " " + address.getHouseNumber());
            }
            writeElement("PosCodCE123", address.getPostalCode());
            writeElement("CitCE124", address.getCity());
            writeElement("NADLNGCE", address.getLanguage());
            writeElement("CouCE125", address.getCountry());
        }

		if (party.getPartyTIN() != null) {
			writeElement("TINCE159", party.getPartyTIN().getTIN());			
		}
		closeElement();
    }
    
  //EI20110707:
    public void writeTraderNotpar(Party party, String partyName) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}    	
    	Address address = party.getAddress();
    	
    	openElement(partyName);    	
    	if (address != null) { 
			writeElement("NamNOTPAR672", address.getName());			
			//EI20110712:writeElement("StrAndNumNOTPAR673", address.getStreet() +  " " + address.getHouseNumber());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement("StrNumNOTPAR673", address.getStreet());
			} else {
				writeElement("StrNumNOTPAR673", address.getStreet() +  " " + address.getHouseNumber());
			}
			writeElement("PosCodNOTPAR676", address.getPostalCode());
			writeElement("CitNOTPAR674", address.getCity());
			writeElement("CouCodNOTPAR675", address.getCountry());
			writeElement("NOTPAR675LNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			writeElement("TINNOTPAR671", party.getPartyTIN().getTIN());
		}
		closeElement();
    }
    
  //EI20110707:
    public void writeTraderRep(Party party, String msgNumber) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement("TRAREP" + msgNumber);
    	writeElement("NamTRE1", address.getName());
    	if (Utils.isStringEmpty(address.getHouseNumber())) {
    		writeElement("StrAndNumTRE1", address.getStreet());
    	} else {
    		writeElement("StrAndNumTRE1", address.getStreet() + " " + address.getHouseNumber());
    		//writeElement("StrNumTRACARENT607", address.getStreet()+" "+address.getHouseNumber());
    	}
		writeElement("PosCodTRE1", address.getPostalCode());
		writeElement("CitTRE1", address.getCity());
		writeElement("CouCodTRE1", address.getCountry());	
		writeElement("TRAREPLNG", address.getLanguage());
	
		if (party.getPartyTIN() != null) {
			writeElement("TINTRE1", party.getPartyTIN().getTIN());
		} 
		closeElement();
	}
	
  //EI20110707:
    public void writeTraderPer(Party party, String partyName) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement(partyName);
    	if (party.getAddress() != null) {
			writeElement("NamPLD1", address.getName());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement("StrAndNumPLD1", address.getStreet());
			} else {
				writeElement("StrAndNumPLD1", address.getStreet() + " " + address.getHouseNumber());
			}
			writeElement("PosCodPLD1", address.getPostalCode());
			writeElement("CitPLD1", address.getCity());
			writeElement("CouCodPLD1", address.getCountry());
			writeElement("PERLODSUMDECLNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			//EI: writeElement("TINTRE1", party.getPartyTIN().getTIN());
			writeElement("TINPLD1", party.getPartyTIN().getTIN());   //EI20110713
		}
		closeElement();
    }
  //EI20110707:
    public void writeTraderCar(Party party, String msgNumber) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}    
    	Address address = party.getAddress();
    	
    	openElement("TRACARENT601" + msgNumber);
    	if (address != null) {
			writeElement("NamTRACARENT604", address.getName());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement("StrNumTRACARENT607", address.getStreet());
			} else {
				writeElement("StrNumTRACARENT607", address.getStreet() + " " + address.getHouseNumber());
			}
			writeElement("PstCodTRACARENT606", address.getPostalCode());
			writeElement("CtyTRACARENT603", address.getCity());
			writeElement("CouCodTRACARENT605", address.getCountry());
			writeElement("TRACARENT601LNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			writeElement("TINTRACARENT602", party.getPartyTIN().getTIN());
		}
		closeElement();
    }
    
    public void writeIti(List<String> list)  throws XMLStreamException {
		if (list == null) {
			return;
		} 
		if (list.isEmpty()) {
			return;
		}
		for (String country : list) {
			if (!Utils.isStringEmpty(country)) {
				openElement("ITI");                              
	        		writeElement("CouOfRouCodITI1", country);				
	        	closeElement();  
			}
		}		
	}
    
    public void writeCusOffEntry(String office, String date, String msgNumber)  throws XMLStreamException {
		if (Utils.isStringEmpty(office) && Utils.isStringEmpty(date)) {
			return;
		}			
		openElement("CUSOFFFENT730" + msgNumber);
			writeElement("RefNumCUSOFFFENT731", office);				
            writeElement("ExpDatOfArrFIRENT733", date);      
		closeElement();				
	}                                                    
	
    public void writeCusOffEntry323(String declaredOfficeOfFirstEntry, String msgNumber) {
		if (Utils.isStringEmpty(declaredOfficeOfFirstEntry)) {
			return;
		}
		try {
			openElement("CUSOFFFENT730" + msgNumber);
				writeElement("RefNumCUSOFFFENT731", declaredOfficeOfFirstEntry);
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
    public void writeSeaId(List<SealNumber> list)   throws XMLStreamException {
		if (list == null) {
			return;
		} 
		if (list.isEmpty()) {
			return;
		}
		for (SealNumber sn : list) {
			if (sn != null && !sn.isEmpty()) {
				openElement("SEAID529");
					writeElement("SeaIdSEAID530", sn.getNumber());
					writeElement("SeaIdSEAID530LNG", sn.getLanguage());
				closeElement();	
			}
		}						
	}
    public void writeGooIteGds(List<GoodsItemLong> list, String msgNumber, TransportMeans borderKopf) 
    													throws XMLStreamException {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		String kopfMode = "";
		if (borderKopf != null) {
			kopfMode = borderKopf.getTransportMode();
			if (Utils.isStringEmpty(kopfMode)) {
				kopfMode = "";
			}
		}
		
		for (GoodsItemLong goodsItemLong : list) {
			if (goodsItemLong != null) {
				openElement("GOOITEGDS" + msgNumber);
					writeElement("IteNumGDS7", goodsItemLong.getItemNumber());
					writeElement("GooDesGDS23", goodsItemLong.getDescription());
					writeElement("GooDesGDS23LNG", goodsItemLong.getDescriptionLng());
					writeElement("GroMasGDS46", goodsItemLong.getGrossMass());
					writeElement("MetOfPayGDI12", goodsItemLong.getPaymentType());
					writeElement("ComRefNumGIM1", goodsItemLong.getShipmentNumber());
					writeElement("UNDanGooCodGDI1", goodsItemLong.getDangerousGoodsNumber());
					writeElement("PlaLoaGOOITE333", goodsItemLong.getLoadingPlace());
					writeElement("PlaLoaGOOITE333LNG", goodsItemLong.getLoadingPlaceLng());
					writeElement("PlaUnlGOOITE333", goodsItemLong.getUnloadingPlace());
					writeElement("PlaUnlGOOITE333LNG", goodsItemLong.getUnloadingPlaceLng());						
					writeDocList(goodsItemLong.getDocumentList(), msgNumber);
					writeSpemenmt(goodsItemLong.getSpecialMentionList());
					writeTraconco(goodsItemLong.getConsignor());					
					//writeElement("ComNomCMD1", goodsItemLong.getCommodityCode());	
					writeComCod(goodsItemLong.getCommodityCode()); //EI20110803
					writeTraconce(goodsItemLong.getConsignee());
					writeConnr(goodsItemLong.getContainersList(), msgNumber);
					if (!kopfMode.equals("4")) {  //EI20110714
						writeIdemeatragi(goodsItemLong.getMeansOfTransportBorderList(), msgNumber);		
					}
					writePacgs(goodsItemLong.getPackagesList());
					writePrtnot(goodsItemLong.getNotifyParty());
				closeElement();
			}
		}
	}	
    
    public void  writeComCod(String comcod)  throws XMLStreamException {
    	if (Utils.isStringEmpty(comcod)) {
			return;
		}
    	openElement("COMCODGODITM");
    		writeElement("ComNomCMD1", comcod);	
    	closeElement();
    }
    public void writeTraconco(Party consignor)  throws XMLStreamException {
		if (consignor == null) {
			return;
		}
		if (consignor.isEmpty()) {
			return;
		}
		Address address = consignor.getAddress();
				
		if (address != null) {
			openElement("TRACONCO2");
				writeElement("NamCO27", address.getName());
				if (Utils.isStringEmpty(address.getHouseNumber())) {
					writeElement("StrAndNumCO222", address.getStreet());
				} else {
					writeElement("StrAndNumCO222", address.getStreet() + " " + address.getHouseNumber());
				}
				writeElement("PosCodCO223", address.getPostalCode());
				writeElement("CitCO224", address.getCity());
				writeElement("CouCO225", address.getCountry());
				writeElement("NADLNGTCO", address.getLanguage());
				if (consignor.getPartyTIN() != null) {
					writeElement("TINCO259", consignor.getPartyTIN().getTIN());
				}
			closeElement();
		}		
	}
    		
    public void writeTraconce(Party consignee)   throws XMLStreamException {
		if (consignee == null) {
			return;
		}
		if (consignee.isEmpty()) {
			return;
		}
		Address address = consignee.getAddress();
		
		if (address != null) {
			openElement("TRACONCE2");
				writeElement("NamCE27", address.getName());
				if (Utils.isStringEmpty(address.getHouseNumber())) {
					writeElement("StrAndNumCE222", address.getStreet());
				} else {
					writeElement("StrAndNumCE222", address.getStreet() + " " + address.getHouseNumber());
				}
				writeElement("PosCodCE223", address.getPostalCode());
				writeElement("CitCE224", address.getCity());
				writeElement("CouCE225", address.getCountry());
				writeElement("NADLNGGICE", address.getLanguage());
				if (consignee.getPartyTIN() != null) {
					writeElement("TINCE259", consignee.getPartyTIN().getTIN());
				}
			closeElement();
		}
		
	}
	
    public void writePrtnot(Party notifyParty)   throws XMLStreamException {
		if (notifyParty == null) {
			return;
		}
		Address address = notifyParty.getAddress();
				
		if (address != null) {
			openElement("PRTNOT640");
				writeElement("NamPRTNOT642", address.getName());
				if (Utils.isStringEmpty(address.getHouseNumber())) {
					writeElement("StrNumPRTNOT646", address.getStreet());
				} else {
					writeElement("StrNumPRTNOT646", address.getStreet() + " " + address.getHouseNumber());
				}
				writeElement("PstCodPRTNOT644", address.getPostalCode());
				writeElement("CtyPRTNOT643", address.getCity());
				writeElement("CouCodGINOT647", address.getCountry());
				writeElement("PRTNOT640LNG", address.getLanguage());
				if (notifyParty.getPartyTIN() != null) {
					writeElement("TINPRTNOT641", notifyParty.getPartyTIN().getTIN());
				}
			closeElement();
		}		
	}
    public void writeDocList(List<IcsDocument> list, String msgNumber)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
				
		for (IcsDocument document : list) {
			if (document != null && !document.isEmpty())  {
				openElement("PRODOCDC2" + msgNumber);
					writeElement("DocTypDC21", document.getType());
					writeElement("DocRefDC23", document.getReference());
					writeElement("DocRefDCLNG", document.getReferenceLng());
				closeElement();
			}
		}				
	}
       
    public void writeSpemenmt(List<SpecialMention> list)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
					
		for (SpecialMention specialMention : list) {
			if (specialMention != null && !specialMention.isEmpty()) {
				openElement("SPEMENMT2");
					writeElement("AddInfCodMT23", specialMention.getCode());
				closeElement();	
			}
		}		
	}
    
    public void writeConnr(List<String> list, String msgNumber)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
					
		for (String container : list) {
			if (!Utils.isStringEmpty(container)) {
				openElement("CONNR2" + msgNumber);
					writeElement("ConNumNR21", container);
				closeElement();
			}
		}		
	}
	
    public void writeIdemeatragi(List<TransportMeans> list, String msgNumber)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
				
		for (TransportMeans transportMeans : list) {
			if (transportMeans != null && !transportMeans.isEmpty()) {
				openElement("IDEMEATRAGI970" + msgNumber);
					writeElement("NatIDEMEATRAGI973", transportMeans.getTransportationCountry());
					writeElement("IdeMeaTraGIMEATRA971", transportMeans.getTransportationNumber());
				closeElement();
			}
		}		
	}
	
    public void writePacgs(List<Packages> list)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
					
		for (Packages packages : list) {
			if (packages != null && !packages.isEmpty()) {
				openElement("PACGS2");
					writeElement("KinOfPacGS23", packages.getType());
					writeElement("NumOfPacGS24", packages.getSequentialNumber());
					writeElement("NumOFPieGS25", packages.getQuantity());
					writeElement("MarNumOfPacGSL21", packages.getMarks());
					writeElement("MarNumOfPacGSL21LNG", packages.getMarksLng());
				closeElement();
			}
		}		
	}
    
    public void writeErrorList(List<PositionError> list) throws XMLStreamException {    			
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
    		return;
    	}
		
		for (PositionError error : list) {			
			if (error != null) {
				openElement("XMLERR805");							
					writeElement("ErrCodXMLER806", error.getErrorCode());
					writeElement("ErrLocXMLER803", error.getModul());
					writeElement("ErrReaXMLER802", error.getErrorText());
					writeElement("OriAttValXMLER804", error.getOriginOfError());					
					//writeElement("??", error.getDateOfErrorMessage + error.getTimeOfErrorMessage);
				closeElement();
			}
		}						
	}
    
    public void writeCusoffSent740(List<String> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
    		return;
    	}
				
		for (String entry : list) {
			if (!Utils.isStringEmpty(entry)) {
				openElement("CUSOFFSENT740");
					writeElement("RefNumSUBENR909", entry);
				closeElement();
			}
		}		
	}
    
    public void writeCusoffentactoff700(String actualOfficeOfFirstEntry) {
		if (Utils.isStringEmpty(actualOfficeOfFirstEntry)) {
			return;
		}
		try {
			openElement("CUSOFFENTACTOFF700");
				writeElement("RefNumCUSOFFENTACTOFF701", actualOfficeOfFirstEntry);				
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
    
    public void writeTrareqdic456(Party submitter) {		
		if (submitter == null || submitter.isEmpty()) {
			return;
		}
		try {
			if (submitter.getAddress() != null) {
				Address address = submitter.getAddress();
				openElement("TRAREQDIV456");
					writeElement("NamTRAREQDIV457", address.getName());					
					if (Utils.isStringEmpty(address.getHouseNumber())) {
						writeElement("StrAndNumTRAREQDIV458", address.getStreet());
					} else {
						writeElement("StrAndNumTRAREQDIV458", address.getStreet() + " " + address.getHouseNumber());
					}
					writeElement("CouTRAREQDIV459", address.getCountry());
					writeElement("PosCodTRAREQDIV460", address.getPostalCode());
					writeElement("CitTRAREQDIV461", address.getCity());
					writeElement("TRAREQDIV456LNG", address.getLanguage());
					if (submitter.getPartyTIN() != null) {
						writeElement("TINTRAREQDIV463", submitter.getPartyTIN().getTIN());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

    public void writeImpope200(List<ImportOperation> list, String msgNumber) {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		try {						
			for (ImportOperation importOperation : list) {
				if (importOperation != null) {
					openElement("IMPOPE200" + msgNumber);
						writeElement("DocRefNumIMPOPE201", importOperation.getMRN());						
						writeGooitegds323(importOperation.getItemNumberList(), msgNumber);						
					closeElement();
				}				
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	
    private void writeGooitegds323(List<String> list, String msgNumber) {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		try {
			openElement("GOOITEIMP248" + msgNumber); 
				for (String itemNumber : list) {
					writeElement("IteNumGIIMP297", itemNumber);
				}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
    /*
     * @in: time string  
     * @return time without colon 
     */
    protected String getTimeHHMM(String time) {
    	if (time == null || time.length() < 4) {
    		return "";
    	} else {
    		return time.replace(":", "").substring(0, 4);
    	}
    }
    
}

