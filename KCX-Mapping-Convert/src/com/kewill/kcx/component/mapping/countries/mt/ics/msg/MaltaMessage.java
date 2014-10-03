package com.kewill.kcx.component.mapping.countries.mt.ics.msg;

import java.sql.Time;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: ICS MALTA
 * Created		: 14.08.2013
 * Description	: common methods for Kids2Mt. 
 * @author krzoska
 * @version 1.0.00
 */
public class MaltaMessage extends CyprusMessage {

    private MaltaHeader maltaHeader = null;
	
	public void writeTagsFromKidsHeader(KidsHeader kidsHeader, String nmsp) throws XMLStreamException {
    	if (kidsHeader != null) {
		        writeElement(nmsp + "MesSenMES3", kidsHeader.getTransmitter());
		        writeElement(nmsp + "MesRecMES6", kidsHeader.getReceiver());
		        //writeElement(nmsp + "DatOfPreMES9", kidsHeader.getDay());
		        writeElement(nmsp + "DatOfPreMES9", getDate(kidsHeader.getYear(), 
		        										         kidsHeader.getMonth(), 
		        										         kidsHeader.getDay()));

		        writeElement(nmsp + "TimOfPreMES10", getTimeHHMM(kidsHeader.getTime()));
		        
		        
		        if (kidsHeader.getReceiver() != null && kidsHeader.getReceiver().toUpperCase().endsWith(".TST")) {
		        	writeElement(nmsp + "TesIndMES18", "1");
		        }
		        if (getCommonFieldsDTO() != null) {
			        writeElement(nmsp + "MesIdeMES19", getCommonFieldsDTO().getReferenceNumber());
			        writeElement(nmsp + "MesTypMES20", getCommonFieldsDTO().getTargetMessageType());
		        }
    	}
    }
	
	  public void writeHeaderFields(String namespace) throws XMLStreamException {
		String nmsp = namespace == null ? "" : namespace;
	    if (maltaHeader != null) {
	        writeElement(nmsp + "MesSenMES3", maltaHeader.getMesSenMES3());
	        writeElement(nmsp + "MesRecMES6", maltaHeader.getMesRecMES6());
	        writeElement(nmsp + "DatOfPreMES9", maltaHeader.getDatOfPreMES9());
	        writeElement(nmsp + "TimOfPreMES10", maltaHeader.getTimOfPreMES10());
	        if (getCommonFieldsDTO() != null && getCommonFieldsDTO().getKcxId() != null &&
	        	getCommonFieldsDTO().getKcxId().toUpperCase().endsWith(".TST")) {
	        	writeElement(nmsp + "TesIndMES18", "1");
	        }
	        writeElement(nmsp + "MesIdeMES19", maltaHeader.getMesIdeMES19());
	        writeElement(nmsp + "MesTypMES20", getCommonFieldsDTO().getTargetMessageType());
	    	}
	    }

    public void writeTransportBorder(TransportMeans border, String nmsp)  throws XMLStreamException {
	    	if (border == null) {
	    		return;
	    	}    	
	    	if (Utils.isStringEmpty(border.getTransportMode())) {
	    		return;   //is required
	    	}
	    	if (border.getTransportMode().equals("4")) {
	    		writeElement(nmsp + "TraModAtBorHEA76", border.getTransportMode());
	    		//the other fields shouldn't be send
	    	} else {
	            writeElement(nmsp + "TraModAtBorHEA76", border.getTransportMode());
	            writeElement(nmsp + "IdeOfMeaOfTraCroHEA85", border.getTransportationNumber());
	            writeElement(nmsp + "NatOfMeaOfTraCroHEA87", border.getTransportationCountry());
			}
	}
    
    public void writeTraderConco(Party party, String partyName, String nmsp) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement(nmsp + partyName);    	
        if (address != null) {
            writeElement(nmsp + "NamCO17", address.getName());   
            if (Utils.isStringEmpty(address.getHouseNumber())) {
            	writeElement(nmsp + "StrAndNumCO122", address.getStreet());
            } else {
            	writeElement(nmsp + "StrAndNumCO122", address.getStreet() + " " + address.getHouseNumber());
            }
            writeElement(nmsp + "PosCodCO123", address.getPostalCode());
            writeElement(nmsp + "CitCO124", address.getCity());
            writeElement(nmsp + "CouCO125", address.getCountry());
            writeElement(nmsp + "NADLNGCO", address.getLanguage());
        }

		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINCO159", party.getPartyTIN().getTIN());
		}
		closeElement(); //     	openElement(partyName);
    }
	 
    public void writeTraderConce(Party party, String partyName, String nmsp) throws XMLStreamException {
    	if (party == null) {
    		return;
    	} 
    	Address address = party.getAddress();
    	
    	openElement(nmsp + partyName);    	
        if (address != null) {
            writeElement(nmsp + "NamCE17", address.getName());  
            if (Utils.isStringEmpty(address.getHouseNumber())) {
            	writeElement(nmsp + "StrAndNumCE122", address.getStreet());     
            } else {
            	writeElement(nmsp + "StrAndNumCE122", address.getStreet() + " " + address.getHouseNumber());
            }
            writeElement(nmsp + "PosCodCE123", address.getPostalCode());
            writeElement(nmsp + "CitCE124", address.getCity());
            writeElement(nmsp + "NADLNGCE", address.getLanguage());
            writeElement(nmsp + "CouCE125", address.getCountry());
        }

		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINCE159", party.getPartyTIN().getTIN());			
		}
		closeElement();  //     	openElement(nmsp + partyName);
    }
    
    public void writeGooIteGds(List<GoodsItemLong> list, String msgNumber, TransportMeans borderKopf, String nmsp) 
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
				openElement(nmsp + "GOOITEGDS" + msgNumber);
					writeElement(nmsp + "IteNumGDS7", goodsItemLong.getItemNumber());
					writeElement(nmsp + "GooDesGDS23", goodsItemLong.getDescription());
					writeElement(nmsp + "GooDesGDS23LNG", goodsItemLong.getDescriptionLng());
					writeElement(nmsp + "GroMasGDS46", goodsItemLong.getGrossMass());
					writeElement(nmsp + "MetOfPayGDI12", goodsItemLong.getPaymentType());
					writeElement(nmsp + "ComRefNumGIM1", goodsItemLong.getShipmentNumber());
					writeElement(nmsp + "UNDanGooCodGDI1", goodsItemLong.getDangerousGoodsNumber());
					writeElement(nmsp + "PlaLoaGOOITE333", goodsItemLong.getLoadingPlace());
					writeElement(nmsp + "PlaLoaGOOITE333LNG", goodsItemLong.getLoadingPlaceLng());
					writeElement(nmsp + "PlaUnlGOOITE333", goodsItemLong.getUnloadingPlace());
					writeElement(nmsp + "PlaUnlGOOITE333LNG", goodsItemLong.getUnloadingPlaceLng());						
					writeDocList(goodsItemLong.getDocumentList(), msgNumber, nmsp);
					writeSpemenmt(goodsItemLong.getSpecialMentionList(), nmsp);
					writeTraconco(goodsItemLong.getConsignor(), nmsp);					
					//	writeElement("ComNomCMD1", goodsItemLong.getCommodityCode());	
					writeComCod(goodsItemLong.getCommodityCode(), nmsp);
					writeTraconce(goodsItemLong.getConsignee(), nmsp);
					writeConnr(goodsItemLong.getContainersList(), msgNumber);
					if (!kopfMode.equals("4")) {  //EI20110714
						writeIdemeatragi(goodsItemLong.getMeansOfTransportBorderList(), msgNumber, nmsp);		
					}
					writePacgs(goodsItemLong.getPackagesList(), nmsp);
					writePrtnot(goodsItemLong.getNotifyParty(), nmsp);
				closeElement();   // openElement("GOOITEGDS" + msgNumber);
			}
		}
    }	

    public void writeDocList(List<IcsDocument> list, String msgNumber, String nmsp)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
				
		for (IcsDocument document : list) {
			if (document != null && !document.isEmpty())  {
				openElement(nmsp + "PRODOCDC2" + msgNumber);
					writeElement(nmsp + "DocTypDC21", document.getType());
					writeElement(nmsp + "DocRefDC23", document.getReference());
					writeElement(nmsp + "DocRefDCLNG", document.getReferenceLng());
				closeElement();
			}
		}				
	}

    public void writeTraconco(Party consignor, String nmsp)  throws XMLStreamException {
		if (consignor == null) {
			return;
		}
		if (consignor.isEmpty()) {
			return;
		}
		Address address = consignor.getAddress();
				
		if (address != null) {
			openElement(nmsp + "TRACONCO2");
				writeElement(nmsp + "NamCO27", address.getName());
				if (Utils.isStringEmpty(address.getHouseNumber())) {
					writeElement(nmsp + "StrAndNumCO222", address.getStreet());
				} else {
					writeElement(nmsp + "StrAndNumCO222", address.getStreet() + " " + address.getHouseNumber());
				}
				writeElement(nmsp + "PosCodCO223", address.getPostalCode());
				writeElement(nmsp + "CitCO224", address.getCity());
				writeElement(nmsp + "CouCO225", address.getCountry());
				writeElement(nmsp + "NADLNGTCO", address.getLanguage());
				if (consignor.getPartyTIN() != null) {
					writeElement(nmsp + "TINCO259", consignor.getPartyTIN().getTIN());
				}
			closeElement();
		}		
	}

    
    public void writeSpemenmt(List<SpecialMention> list, String nmsp)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
					
		for (SpecialMention specialMention : list) {
			if (specialMention != null && !specialMention.isEmpty()) {
				openElement(nmsp + "SPEMENMT2");
					writeElement(nmsp + "AddInfCodMT23", specialMention.getCode());
				closeElement();	
			}
		}		
	}

    public void writeTraderNotpar(Party party, String partyName, String nmsp) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}    	
    	Address address = party.getAddress();
    	
    	if (address != null && (address.isEmpty()) || 
    		(party.getPartyTIN() != null && Utils.isStringEmpty(party.getPartyTIN().getTIN()))) {
    		return;
    	}
    	
    	openElement(nmsp + partyName);    	
    	if (address != null && !address.isEmpty()) { 
			writeElement(nmsp + "NamNOTPAR672", address.getName());			
			//EI20110712:writeElement("StrAndNumNOTPAR673", address.getStreet() +  " " + address.getHouseNumber());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement(nmsp + "StrNumNOTPAR673", address.getStreet());
			} else {
				writeElement(nmsp + "StrNumNOTPAR673", address.getStreet() +  " " + address.getHouseNumber());
			}
			writeElement(nmsp + "PosCodNOTPAR676", address.getPostalCode());
			writeElement(nmsp + "CitNOTPAR674", address.getCity());
			writeElement(nmsp + "CouCodNOTPAR675", address.getCountry());
			writeElement(nmsp + "NOTPAR675LNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINNOTPAR671", party.getPartyTIN().getTIN());
		}
		closeElement();   // openElement(nmsp + partyName);
    }

    public void writeTraconce(Party consignee, String nmsp)   throws XMLStreamException {
		if (consignee == null) {
			return;
		}
		if (consignee.isEmpty()) {
			return;
		}
		Address address = consignee.getAddress();
		
		if (address != null) {
			openElement(nmsp + "TRACONCE2");
				writeElement(nmsp + "NamCE27", address.getName());
				if (Utils.isStringEmpty(address.getHouseNumber())) {
					writeElement(nmsp + "StrAndNumCE222", address.getStreet());
				} else {
					writeElement(nmsp + "StrAndNumCE222", address.getStreet() + " " + address.getHouseNumber());
				}
				writeElement(nmsp + "PosCodCE223", address.getPostalCode());
				writeElement(nmsp + "CitCE224", address.getCity());
				writeElement(nmsp + "CouCE225", address.getCountry());
				writeElement(nmsp + "NADLNGGICE", address.getLanguage());
				if (consignee.getPartyTIN() != null) {
					writeElement(nmsp + "TINCE259", consignee.getPartyTIN().getTIN());
				}
			closeElement();  // openElement(nmsp + "TRACONCE2");
		}
		
	}

    public void writeConnr(List<String> list, String msgNumber, String nmsp)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
					
		for (String container : list) {
			if (!Utils.isStringEmpty(container)) {
				openElement(nmsp + "CONNR2" + msgNumber);
					writeElement(nmsp + "ConNumNR21", container);
				closeElement();
			}
		}		
	}

    public void writeIdemeatragi(List<TransportMeans> list, String msgNumber, String nmsp)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
				
		for (TransportMeans transportMeans : list) {
			if (transportMeans != null && !transportMeans.isEmpty()) {
				openElement(nmsp + "IDEMEATRAGI970" + msgNumber);
					writeElement(nmsp + "NatIDEMEATRAGI973", transportMeans.getTransportationCountry());
					writeElement(nmsp + "IdeMeaTraGIMEATRA971", transportMeans.getTransportationNumber());
				closeElement();
			}
		}		
	}
	
    public void writePacgs(List<Packages> list, String nmsp)   throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
					
		for (Packages packages : list) {
			if (packages != null && !packages.isEmpty()) {
				openElement(nmsp + "PACGS2");
					writeElement(nmsp + "KinOfPacGS23", packages.getType());
					writeElement(nmsp + "NumOfPacGS24", packages.getSequentialNumber());
					writeElement(nmsp + "NumOFPieGS25", packages.getQuantity());
					writeElement(nmsp + "MarNumOfPacGSL21", packages.getMarks());
					writeElement(nmsp + "MarNumOfPacGSL21LNG", packages.getMarksLng());
				closeElement();
			}
		}		
	}

    public void writePrtnot(Party notifyParty, String nameSpace)   throws XMLStreamException {
		if (notifyParty == null) {
			return;
		}
		Address address = notifyParty.getAddress();
				
		if (address != null) {
			openElement(nameSpace + "PRTNOT640");
				writeElement(nameSpace + "NamPRTNOT642", address.getName());
				if (Utils.isStringEmpty(address.getHouseNumber())) {
					writeElement(nameSpace + "StrNumPRTNOT646", address.getStreet());
				} else {
					writeElement(nameSpace + "StrNumPRTNOT646", address.getStreet() + " " + address.getHouseNumber());
				}
				writeElement(nameSpace + "PstCodPRTNOT644", address.getPostalCode());
				writeElement(nameSpace + "CtyPRTNOT643", address.getCity());
				writeElement(nameSpace + "CouCodGINOT647", address.getCountry());
				writeElement(nameSpace + "PRTNOT640LNG", address.getLanguage());
				if (notifyParty.getPartyTIN() != null) {
					writeElement(nameSpace + "TINPRTNOT641", notifyParty.getPartyTIN().getTIN());
				}
			closeElement();
		}		
	}

    public void writeIti(List<String> list, String nmsp)  throws XMLStreamException {
		if (list == null) {
			return;
		} 
		if (list.isEmpty()) {
			return;
		}
		for (String country : list) {
			if (!Utils.isStringEmpty(country)) {
				openElement(nmsp + "ITI");                              
	        		writeElement(nmsp + "CouOfRouCodITI1", country);				
	        	closeElement();  
			}
		}		
	}

    
    public void  writeComCod(String comcod, String nmsp)  throws XMLStreamException {
    	if (Utils.isStringEmpty(comcod)) {
			return;
		}
    	openElement(nmsp + "COMCODGODITM");
    		writeElement(nmsp + "ComNomCMD1", comcod);	
    	closeElement();
    }

    public void writeTraderRep(Party party, String msgNumber, String nmsp) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement(nmsp + "TRAREP" + msgNumber);
    	writeElement(nmsp + "NamTRE1", address.getName());
    	if (Utils.isStringEmpty(address.getHouseNumber())) {
    		writeElement(nmsp + "StrAndNumTRE1", address.getStreet());
    	} else {
    		writeElement(nmsp + "StrAndNumTRE1", address.getStreet() + " " + address.getHouseNumber());
    		//writeElement("StrNumTRACARENT607", address.getStreet()+" "+address.getHouseNumber());
    	}
		writeElement(nmsp + "PosCodTRE1", address.getPostalCode());
		writeElement(nmsp + "CitTRE1", address.getCity());
		writeElement(nmsp + "CouCodTRE1", address.getCountry());	
		writeElement(nmsp + "TRAREPLNG", address.getLanguage());
	
		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINTRE1", party.getPartyTIN().getTIN());
		} 
		closeElement();
	}

    public void writeTraderPer(Party party, String partyName, String nmsp) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement(nmsp + partyName);
    	if (party.getAddress() != null) {
			writeElement(nmsp + "NamPLD1", address.getName());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement(nmsp + "StrAndNumPLD1", address.getStreet());
			} else {
				writeElement(nmsp + "StrAndNumPLD1", address.getStreet() + " " + address.getHouseNumber());
			}
			writeElement(nmsp + "PosCodPLD1", address.getPostalCode());
			writeElement(nmsp + "CitPLD1", address.getCity());
			writeElement(nmsp + "CouCodPLD1", address.getCountry());
			writeElement(nmsp + "PERLODSUMDECLNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			//EI: writeElement("TINTRE1", party.getPartyTIN().getTIN());
			writeElement(nmsp + "TINPLD1", party.getPartyTIN().getTIN());   //EI20110713
		}
		closeElement();
    }

    public void writeSeaId(List<SealNumber> list, String nmsp)   throws XMLStreamException {
		if (list == null) {
			return;
		} 
		if (list.isEmpty()) {
			return;
		}
		for (SealNumber sn : list) {
			if (sn != null && !sn.isEmpty()) {
				openElement(nmsp + "SEAID529");
					writeElement(nmsp + "SeaIdSEAID530", sn.getNumber());
					writeElement(nmsp + "SeaIdSEAID530LNG", sn.getLanguage());
				closeElement();	
			}
		}						
	}

    public void writeCusOffEntry(String office, String date, String msgNumber, String nmsp)  throws XMLStreamException {
		if (Utils.isStringEmpty(office) && Utils.isStringEmpty(date)) {
			return;
		}			
		openElement(nmsp + "CUSOFFFENT730" + msgNumber);
			writeElement(nmsp + "RefNumCUSOFFFENT731", office);				
            writeElement(nmsp + "ExpDatOfArrFIRENT733", date);      
		closeElement();				
	}                                                    

    public void writeCusoffSent740(List<String> list, String nmsp) throws XMLStreamException {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
    		return;
    	}
				
		for (String entry : list) {
			if (!Utils.isStringEmpty(entry)) {
				openElement(nmsp + "CUSOFFSENT740");
					writeElement(nmsp + "RefNumSUBENR909", entry);
				closeElement();
			}
		}		
	}
    
    public void writeTraderCar(Party party, String msgNumber, String nmsp) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}    
    	Address address = party.getAddress();
    	
    	openElement(nmsp + "TRACARENT601" + msgNumber);
    	if (address != null) {
			writeElement(nmsp + "NamTRACARENT604", address.getName());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement(nmsp + "StrNumTRACARENT607", address.getStreet());
			} else {
				writeElement(nmsp + "StrNumTRACARENT607", address.getStreet() + " " + address.getHouseNumber());
			}
			writeElement(nmsp + "PstCodTRACARENT606", address.getPostalCode());
			writeElement(nmsp + "CtyTRACARENT603", address.getCity());
			writeElement(nmsp + "CouCodTRACARENT605", address.getCountry());
			writeElement(nmsp + "TRACARENT601LNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINTRACARENT602", party.getPartyTIN().getTIN());
		}
		closeElement();
    }

	public MaltaHeader getMaltaHeader() {
		return maltaHeader;
	}

	public void setMaltaHeader(MaltaHeader maltaHeader) {
		this.maltaHeader = maltaHeader;
	}
	  
	protected String getDate(String year, String month, String day) {
	        String   retYear  = "";
	        String   retMonth = "";
	        String   retDay   = "";
	        if (year != null) {
	            if (year.length() > 2) {
	                retYear = year.substring(year.length() - 2);
	            } else {
	                retYear = Utils.nFormat(year, 2);
	            }
	        }
	        
	        if (month != null) {
	            retMonth = Utils.nFormat(month, 2);
	        }
	        
	        if (day != null) {
	            retDay = Utils.nFormat(day, 2);
	        }
	        
	        return retYear + retMonth + retDay;
	}
}
