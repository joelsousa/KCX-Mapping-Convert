package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSEntrySummaryDeclarationKG<br>
 * Created : 08.06.2011<br>
 * Description : Mapping of KIDS-Format into Greece-Format of
 * ICSEntrySummaryDeclaration message (IE315).
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class MapICSEntrySummaryDeclarationKG extends GreeceMessage {
private MsgEntrySummaryDeclaration msgKids;
	
	public MapICSEntrySummaryDeclarationKG(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids			= new MsgEntrySummaryDeclaration(parser);
		this.encoding	= encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		
		XMLOutputFactory	factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			
            writeStartDocument(encoding, "1.0");    

            msgKids.parse(HeaderType.KIDS);         
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   

            //getCommonFieldsDTO().setTargetMessageType("IE315");   
            getCommonFieldsDTO().setTargetMessageType("DCL_IE_315");    // MS20110720   

            openElement("CC315A");
                writeHeaderFields();    
                //EI20110803: writeESDBody()
				writeBody();
			closeElement();
			
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
	private void writeBody() {
		if (msgKids == null) {
			return;
		}
		try {				
			writeHeahea();					
			writeTraderConco(msgKids.getConsignor(), "TRACONCO1");  
			writeTraderConce(msgKids.getConsignee(), "TRACONCE1");  
			writeTraderNotpar(msgKids.getNotifyParty(), "NOTPAR670");  
			String kopfMode = "";  
			if (msgKids.getMeansOfTransportBorder() != null) {
				kopfMode = msgKids.getMeansOfTransportBorder().getTransportMode();
				if (Utils.isStringEmpty(kopfMode)) {
					kopfMode = "";
				}
			}
			writeGooIteGds(msgKids.getGoodsItemList(), "", msgKids.getMeansOfTransportBorder());
			writeIti(msgKids.getCountryOfRoutingList());        
			writeCusOffLon(msgKids.getCustomsOfficeOfLodgment());		   	
			writeTraderRep(msgKids.getRepresentative(), "");  		
			writeTraderPer(msgKids.getPersonLodgingSuma(), "PERLODSUMDEC");  
			writeSeaId(msgKids.getSealIDList()); 
			writeCusOffEntry(msgKids.getCustomsOfficeFirstEntry(), msgKids.getDeclaredDateOfArrival(), "");  
			writeCusoffSent740(msgKids.getCustomsOfficeOfSubsequentEntryList());			
			writeTraderCar(msgKids.getCarrier(), "");  
			//EI20110707-end
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	private void writeHeahea() {
		if (msgKids == null) {
			return;
		}
		try {			
			openElement("HEAHEA");
			writeElement("RefNumHEA4", msgKids.getReferenceNumber());			
			writeTransportBorder(msgKids.getMeansOfTransportBorder());  			
			writeElement("TotNumOfIteHEA305", msgKids.getTotalNumberPositions());
			writeElement("TotNumOfPacHEA306", msgKids.getTotalNumberPackages());
			writeElement("TotGroMasHEA307", msgKids.getTotalGrossMass());
			writeElement("DecPlaHEA394", msgKids.getDeclarationPlace());			
			writeElement("SpeCirIndHEA1", msgKids.getSituationCode());                
			writeElement("TraChaMetOfPayHEA1", msgKids.getPaymentType());
			writeElement("ComRefNumHEA", msgKids.getShipmentNumber());
			writeElement("ConRefNumHEA", msgKids.getConveyanceReference());
			writeElement("PlaLoaGOOITE334", msgKids.getLoadingPlace());
			writeElement("PlaLoaGOOITE334LNG", msgKids.getLoadingPlaceLng());
			writeElement("PlaUnlGOOITE334", msgKids.getUnloadingPlace());
			writeElement("CodPlUnHEA357LNG", msgKids.getUnloadingPlaceLng());
			writeElement("DecDatTimHEA114", msgKids.getDeclarationTime());
			closeElement();			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeCusOffLon(String office) {
			if (Utils.isStringEmpty(office)) {     
				return;
			}
			try {
				openElement("CUSOFFLON");
	            	writeElement("RefNumCOL1", office);
	            closeElement();	
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
	   }   
	
	/*
	private void writeESDBody() {
			writeHeahea(msgKids);
			writeTraconco(msgKids);
			writeTraconce(msgKids);
			writeNotPar(msgKids);
			writeGooIteGds(msgKids);
			writeITI(msgKids);
			writeCusOffLon(msgKids);
			writeTrarep(msgKids);
			writePerLodSumDec(msgKids);
			writeSeaId(msgKids);
			writeCusOfffEnt(msgKids);
			writeCusOffSent(msgKids);
			writeTraCaRent(msgKids);
// MS20110720 Begin         
//		} catch (XMLStreamException e) {
//			e.printStackTrace();
//		}
// MS20110720 End         
	}
	
	private void writeHeahea(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg != null) {
				openElement("HEAHEA");
				writeElement("RefNumHEA4", msg.getReferenceNumber());
				writeElement("TraModAtBorHEA76", msg.getMeansOfTransportBorder().getTransportMode());
				writeElement("IdeOfMeaOfTraCroHEA85", msg.getMeansOfTransportBorder().getTransportationNumber());
				//writeElement("IdeOfMeaOfTraCroHEA85LNG", value)
				//needs to be added, not needed for DE but for LUX / CY
				writeElement("NatOfMeaOfTraCroHEA87", msg.getMeansOfTransportBorder().getTransportationCountry());
				writeElement("TotNumOfIteHEA305", msg.getTotalNumberPositions());
				writeElement("TotNumOfPacHEA306", msg.getTotalNumberPackages());
				writeElement("TotGroMasHEA307", msg.getTotalGrossMass());
				writeElement("DecPlaHEA394", msg.getDeclarationPlace());
				//writeElement("DecPlaHEA394LNG", value)
				//needs to be added, not needed for DE but for LUX / CY
				writeElement("SpeCirInHEA1", msg.getSituationCode());
				writeElement("TraChaMetOfPayHEA1", msg.getPaymentType());
				writeElement("ComRefNumHEA", msg.getShipmentNumber());
				writeElement("ConRefNumHEA", msg.getConveyanceReference());
				writeElement("PlaLoaGOOITE334", msg.getLoadingPlace());
				writeElement("PlaLoaGOOITE334LNG", msg.getLoadingPlaceLng());
				writeElement("PlaUnlGOOITE334", msg.getUnloadingPlace());
				writeElement("CodPlUnHEA357LNG", msg.getUnloadingPlaceLng());
				writeElement("DecDatTimHEA114", msg.getDeclarationTime());
			closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTraconco(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg != null) {
			    if (msg.getConsignor() != null && !msg.getConsignor().isEmpty()) {
	                openElement("TRACONCO1");
	                if (msg.getConsignor().getAddress() != null) {
	                    writeElement("NamCO17", msg.getConsignor().getAddress().getName());
	                    if (msg.getConsignor().getAddress().getHouseNumber() != null) {
	                        writeElement("StrAndNumCO122", msg.getConsignor().getAddress().getHouseNumber() + 
                                                    " " + msg.getConsignor().getAddress().getStreet());
	                    } else {
	                        writeElement("StrAndNumCO122", msg.getConsignor().getAddress().getStreet());
	                    }
	                    writeElement("PosCodCO123", msg.getConsignor().getAddress().getPostalCode());
	                    writeElement("CitCO124", msg.getConsignor().getAddress().getCity());
	                    writeElement("CouCO125", msg.getConsignor().getAddress().getCountry());
	                }
                    if (msg.getConsignor().getPartyTIN() != null) {
                        writeElement("TINCO159", msg.getConsignor().getPartyTIN().getTIN());
                    }
                closeElement();
			    }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTraconce(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg != null) {
			    if (msg.getConsignee() != null && !msg.getConsignee().isEmpty()) {
			        openElement("TRACONCE1");
	                if (msg.getConsignee().getAddress() != null) {
	                    writeElement("NamCE17", msg.getConsignee().getAddress().getName());
    	                if (msg.getConsignee().getAddress().getHouseNumber() != null) {
    	                    writeElement("StrAndNumCE122", msg.getConsignee().getAddress().getHouseNumber() + 
                                                    " " + msg.getConsignee().getAddress().getStreet());
    	                } else {
    	                    writeElement("StrAndNumCE122", msg.getConsignee().getAddress().getStreet());
    	                }
    	                writeElement("PosCodCE123", msg.getConsignee().getAddress().getPostalCode());
    	                writeElement("CitCE124", msg.getConsignee().getAddress().getCity());
    	                writeElement("CouCE125", msg.getConsignee().getAddress().getCountry());
	                }
                    if (msg.getConsignee().getPartyTIN() != null) {
                        writeElement("TINCE159", msg.getConsignee().getPartyTIN().getTIN());
                    }
                    closeElement();
			    }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
    private void writeNotPar(MsgEntrySummaryDeclaration msg) {
	    if (msg == null) {
			return;
		}
		try {
		    if (msg != null) {
		        if (msg.getNotifyParty() != null && !msg.getNotifyParty().isEmpty()) {
                    openElement("NOTPAR670");
                       if (msg.getNotifyParty().getAddress() != null) {
                           writeElement("NamNOTPAR672", msg.getNotifyParty().getAddress().getName());
                            if (msg.getConsignee().getAddress().getHouseNumber() != null) {
                                writeElement("StrAndNumNOTPAR673", msg.getNotifyParty().getAddress().getHouseNumber() + 
                                                            " " + msg.getNotifyParty().getAddress().getStreet());
                            } else {
                                writeElement("StrAndNumNOTPAR673", msg.getNotifyParty().getAddress().getStreet());
                            }
                           writeElement("PosCodNOTPAR676", msg.getNotifyParty().getAddress().getPostalCode());
                           writeElement("CitNOTPAR674", msg.getNotifyParty().getAddress().getCity());
                           writeElement("CouCodNOTPAR675", msg.getNotifyParty().getAddress().getCountry());
                       }
                       if (msg.getNotifyParty().getPartyTIN() != null) {
                           writeElement("TINNOTPAR671", msg.getNotifyParty().getPartyTIN().getTIN());
                       }
                closeElement();
		        }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeGooIteGds(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getGoodsItemList() != null) {
                for (GoodsItemLong gil : msg.getGoodsItemList()) {
                    openElement("GOOITEGDS");
						writeElement("IteNumGDS7", gil.getItemNumber());
						writeElement("GooDesGDS23", gil.getDescription());
						writeElement("GooDesGDS23LNG", gil.getDescriptionLng());
						writeElement("GroMasGDS46", gil.getGrossMass());
						writeElement("MetOfPayGDI12", gil.getPaymentType());
						writeElement("ComRefNumGIM1", gil.getShipmentNumber());
						writeElement("UNDanGooCodGDI1", gil.getDangerousGoodsNumber());
						writeElement("PlaLoaGOOITE333", gil.getLoadingPlace());
						writeElement("PlaLoaGOOITE333LNG", gil.getLoadingPlaceLng());
						writeElement("PlaUnlGOOITE333", gil.getUnloadingPlace());
						writeElement("PlaUnlGOOITE333LNG", gil.getUnloadingPlaceLng());
						
						if (gil.getDocumentList() != null) {
							openElement("PRODOCDC2");
							for (IcsDocument icsd : gil.getDocumentList()) {
								writeElement("DocTypDC21", icsd.getType());
								writeElement("DocRefDC23", icsd.getReference());
								writeElement("DocRefDCLNG", icsd.getReferenceLng());
							}
							closeElement();
						}
						
						if (gil.getSpecialMentionList() != null) {
							openElement("SPEMENMT2");
							for (SpecialMention sm : gil.getSpecialMentionList()) {
								writeElement("AddInfCodMT23", sm.getCode());
							}
							closeElement();
						}
						
						if (gil.getConsignor() != null) {
							openElement("TRACONCO2");
								writeElement("NamCO27", gil.getConsignor().getAddress().getName());
								writeElement("StrAndNumCO222", gil.getConsignor().getAddress().getHouseNumber() + 
										" " + gil.getConsignor().getAddress().getStreet());
								writeElement("PosCodCO223", gil.getConsignor().getAddress().getPostalCode());
								writeElement("CitCO224", gil.getConsignor().getAddress().getCity());
								writeElement("CouCO225", gil.getConsignor().getAddress().getCountry());
                                if (gil.getConsignor().getPartyTIN() != null) {
                                    writeElement("TINCO259", gil.getConsignor().getPartyTIN().getTIN());
                                }
							closeElement();
						}
						
						writeElement("ComNomCMD1", gil.getCommodityCode());

						if (gil.getConsignee() != null) {
							openElement("TRACONCE2");
								writeElement("NamCE27", gil.getConsignee().getAddress().getName());
								writeElement("StrAndNumCE222", gil.getConsignee().getAddress().getHouseNumber() + 
										" " + gil.getConsignee().getAddress().getStreet());
								writeElement("PosCodCE223", gil.getConsignee().getAddress().getPostalCode());
								writeElement("CitCE224", gil.getConsignee().getAddress().getCity());
								writeElement("CouCE225", gil.getConsignee().getAddress().getCountry());
								//writeElement("NADLNGGICE", value)
								//needs to be added, not needed for DE but for 
								//other countries i.e. Sweden
                                if (gil.getNotifyParty().getPartyTIN() != null) {
                                    writeElement("TINCE259", gil.getNotifyParty().getPartyTIN().getTIN());
                                }
							closeElement();
						}
						
						if (gil.getContainersList() != null) {
							openElement("CONNR2");
							for (String cl : gil.getContainersList()) {
								writeElement("ConNumNR21", cl);
							}
							closeElement();
						}
						
						if (gil.getMeansOfTransportBorderList() != null) {
							openElement("IDEMEATRAGI970");
							for (TransportMeans tm : gil.getMeansOfTransportBorderList()) {
								writeElement("NatIDEMEATRAGI973", tm.getTransportationCountry());
								writeElement("IdeMeaTraGIMEATRA971", tm.getTransportationNumber());
								//writeElement("IdeMeaTraGIMEATRA972LNG", value)
								//needs to be added, not needed for DE but for LUX / CY
							}
							closeElement();
						}
						
						if (gil.getPackagesList() != null) {
							openElement("PACGS2");
							for (Packages pack : gil.getPackagesList()) {
								writeElement("KinOfPacGS23", pack.getType());
								writeElement("NumOfPacGS24", pack.getSequentialNumber());
								writeElement("NumOFPieGS25", pack.getQuantity());
								writeElement("MarNumOfPacGSL21", pack.getMarks());
								writeElement("MarNumOfPacGSL21LNG", pack.getMarksLng());
							}
							closeElement();
						}
						
						if (gil.getNotifyParty() != null) {
							openElement("PRTNOT640");
								writeElement("NamPRTNOT648", gil.getNotifyParty() .getAddress().getName());
								writeElement("StrNumPRTNOT646", gil.getNotifyParty().getAddress().getHouseNumber() + 
										" " + gil.getNotifyParty().getAddress().getStreet());
								writeElement("PstCodPRTNOT644", gil.getNotifyParty().getAddress().getPostalCode());
								writeElement("CtyPRTNOT643", gil.getNotifyParty().getAddress().getCity());
								writeElement("CouCodGINOT647", gil.getNotifyParty().getAddress().getCountry());
								//writeElement("PRTNOT640LNG", value)
								//needs to be added,
								//not needed for DE but for other countries i.e. Sweden
								if (gil.getNotifyParty().getPartyTIN() != null) {
	                                writeElement("TINPRTNOT641", gil.getNotifyParty().getPartyTIN().getTIN());
								}
							closeElement();
						}
		            closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeITI(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getCountryOfRoutingList() != null) {
				for (String cr : msg.getCountryOfRoutingList()) {
		            openElement("ITI");
					    writeElement("CouOfRouCodITI1", cr);
		            closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusOffLon(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getCustomsOfficeOfLodgment() != null) {
				openElement("CUSOFFLON");
					writeElement("RefNumCOL1", msg.getCustomsOfficeOfLodgment());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTrarep(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg != null) {
			    if (msg.getRepresentative() != null && !msg.getRepresentative().isEmpty()) {
	                openElement("TRAREP");
	                    if (msg.getRepresentative().getAddress() != null) {
	                        writeElement("NamTRE1", msg.getRepresentative().getAddress().getName());
	                        if (msg.getRepresentative().getAddress().getHouseNumber() != null) {
	                            writeElement("StrAndNumTRE1", msg.getRepresentative().getAddress().getHouseNumber() + 
	                                                    " " + msg.getRepresentative().getAddress().getStreet());
	                        } else {
	                            writeElement("StrAndNumTRE1", msg.getRepresentative().getAddress().getStreet());
	                        }
	                        writeElement("PosCodTRE1", msg.getRepresentative().getAddress().getPostalCode());
	                        writeElement("CitTRE1", msg.getRepresentative().getAddress().getCity());
	                        writeElement("CouCodTRE1", msg.getRepresentative().getAddress().getCountry());
	                        //writeElement("TRAREPLNG", value)
	                        //needs to be added,
	                        //not needed for DE but for other countries i.e. Sweden
	                    }
	                    if (msg.getRepresentative().getPartyTIN() != null) {
	                        writeElement("TINTRE1", msg.getRepresentative().getPartyTIN().getTIN());
	                    }
                    closeElement();
			    }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writePerLodSumDec(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg != null) {
				openElement("PERLODSUMDEC");
					writeElement("NamPLD1", msg.getPersonLodgingSuma().getAddress().getName());
					writeElement("StrAndNumPLD1", msg.getPersonLodgingSuma()
							.getAddress().getHouseNumber() + 
							" " + msg.getPersonLodgingSuma()
							.getAddress().getStreet());
					writeElement("PosCodPLD1", msg.getPersonLodgingSuma()
							.getAddress().getPostalCode());
					writeElement("CitPLD1", msg.getPersonLodgingSuma().getAddress().getCity());
					writeElement("CouCodPLD1", msg.getPersonLodgingSuma()
							.getAddress().getCountry());
					//writeElement("PERLODSUMDECLNG", value)
					//needs to be added,
					//not needed for DE but for other countries i.e. Sweden
					writeElement("TINPLD1", msg.getPersonLodgingSuma().getPartyTIN().getTIN());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeSeaId(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getSealIDList() != null) {
				openElement("SEAID529");
					for (SealNumber sn : msg.getSealIDList()) {
						writeElement("SeaIdSEAID530", sn.getNumber());
						writeElement("SeaIdSEAID530LNG", sn.getLanguage());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusOfffEnt(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg != null) {
				openElement("CUSOFFFENT730");
					writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
					writeElement("ExpDatOfArrFIRENT733", msg.getDeclaredDateOfArrival());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusOffSent(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getCustomsOfficeOfSubsequentEntryList() != null) {
				openElement("CUSOFFSENT740");
					for (String cose : msg.getCustomsOfficeOfSubsequentEntryList()) {
						writeElement("RefNumSUBENR909", cose);
					}
					
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTraCaRent(MsgEntrySummaryDeclaration msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg != null) {
			    if (msg.getCarrier() != null && !msg.getCarrier().isEmpty()) {
	                openElement("TRACARENT601");
	                    if (msg.getCarrier().getAddress() != null) {
	                        writeElement("NamTRACARENT604", msg.getCarrier().getAddress().getName());
	                        if (msg.getCarrier().getAddress().getHouseNumber() != null) {
	                            writeElement("StrNumTRACARENT607", msg.getCarrier().getAddress().getHouseNumber() + 
                                                             " " + msg.getCarrier().getAddress().getStreet());
	                        } else {
                                writeElement("StrNumTRACARENT607", msg.getCarrier().getAddress().getStreet());
	                        }
	                        writeElement("PstCodTRACARENT606", msg.getCarrier().getAddress().getPostalCode());
	                        writeElement("CtyTRACARENT603", msg.getCarrier().getAddress().getCity());
	                        writeElement("CouCodTRACARENT605", msg.getCarrier().getAddress().getCountry());
	                        //writeElement("TRACARENT601LNG", value)
	                        //needs to be added,
	                        //not needed for DE but for other countries i.e. Sweden
	                    }
	                    if (msg.getCarrier().getPartyTIN() != null) {
	                        writeElement("TINTRACARENT602", msg.getCarrier().getPartyTIN().getTIN());
	                    }
                    closeElement();
			    }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	*/
	
}
