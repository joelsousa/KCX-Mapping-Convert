package com.kewill.kcx.component.mapping.countries.fr.ncts.KidsToMsgEnv;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Amount;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.OfficeOfTransit;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Reference;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.MsgENV;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Caution;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Cdt;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Cps;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Ctr;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Dm;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.FrDocument;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.FrParty;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Gar;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Gbp;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.IE13;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.IE29;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Ico;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Iga;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.It;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Itinerary;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Itot;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Lap;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Lig;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Loc;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.MES;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Msp;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Scl;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.formats.fr.ncts.BodyIE13;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module  : FR-NCTS<br>
 * Created : 12.11.2013<br>
 * Description : Mapping of KIDS-Format to FR-NCTS-IE13.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapNCTSDeclarationToIE13 extends KidsMessage {	
		
		private BodyIE13 body = null;
		private MsgENV msgEnv = null;
		private MsgNCTSDeclaration message = null;
		
		
		public MapNCTSDeclarationToIE13(XMLEventReader parser, String encoding) throws XMLStreamException {
			message = new MsgNCTSDeclaration(parser);
			msgEnv = new MsgENV();
			this.encoding = encoding;
		}
		
		public String getMessage(KidsHeader kidsHeader) {
	        StringWriter xmlOutputString = new StringWriter();
	        
	        XMLOutputFactory factory = XMLOutputFactory.newInstance();
	        try {
	            writer = factory.createXMLStreamWriter(xmlOutputString);
	            body   = new BodyIE13(writer);
	           
	            writeStartDocument(encoding, "1.0");
	            
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
	            message.parse(HeaderType.KIDS); 
	            mapKidsToIE13(kidsHeader);           
	           
	            body.setMessage(msgEnv);
	            body.writeBody();
	            
	            closeElement();  // soap:Envelope
	            writer.writeEndDocument();
	            
	            writer.flush();
	            writer.close();
	            
	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }
	        return xmlOutputString.toString();
	    }
		
		private void mapKidsToIE13(KidsHeader kidsHeader) {
			if (kidsHeader != null) {
				String date = kidsHeader.getSentAtDate();				
				msgEnv.setDateSys(date);
				msgEnv.setSender(kidsHeader.getTransmitter());
				msgEnv.setRecipient(kidsHeader.getReceiver());
				msgEnv.setTypeMes(kidsHeader.getMessageName());
				msgEnv.setLg(kidsHeader.getCountryCode());				
			}
			MES mes = new MES();
			IE29 ie29 = new IE29();
			IE13 msg = new IE13();
			ie29.setIE13(msg);
			mes.setIE29(ie29);			
			msgEnv.setMes(mes);
			
			msg.setTyd(message.getTypeOfDeclaration());
			msg.setLrn(message.getReferenceNumber());
			msg.setTirId(message.getCarnetID());
			if (message.getPlaceOfLoading() != null) {
				Loc loc = new Loc();
				loc.setChgt(message.getPlaceOfLoading().getCode());
				loc.setCod(message.getPlaceOfLoading().getAgreedLocationOfGoodsCode());
				loc.setMcon(message.getPlaceOfLoading().getAgreedLocationOfGoods());
				loc.setMaut(message.getPlaceOfLoading().getAuthorisedLocationOfGoodsCode());
			}
			msg.setLid(message.getDeclarationPlace());
			msg.setPae(message.getDispatchCountry());
			msg.setPad(message.getDestinationCountry());
			msg.setBud(message.getOfficeOfDestination());
			if (message.getOfficeOfTransitList() != null) {
				for (OfficeOfTransit office : message.getOfficeOfTransitList()) {
					if (office != null) {
						Gbp gbp = new Gbp();
						gbp.addCodList(office.getCode());
					}
				}		
			}
			msg.setBue(message.getOfficeOfDeparture());
			if (message.getDeclarationDate() != null && message.getDeclarationDate().length() == 12) {
				msg.setDtd(message.getDeclarationDate().substring(0, 8));
			} else {
				msg.setDtd(message.getDeclarationDate());
			}
			msg.setCtn(message.getTransportInContainer());
			msg.setCtl(message.getControlResultCode());
			if (message.getGuaranteeListH() != null) {
				for (Guarantee guarantee : message.getGuaranteeListH()) {
					if (guarantee != null) {
						Gar gar = new Gar();
						gar.setTyp(guarantee.getTypeOfGuarantee());		
						if (guarantee.getReferenceList() != null) {
							for (Reference ref : guarantee.getReferenceList()) {
								if (ref != null) {
									Iga iga = new Iga();
									iga.setNum(ref.getGrn());
									iga.setCod(ref.getAccessCode());
									iga.setAut(ref.getOtherNumber());
									iga.setLue(ref.getNotValidForEC());
									if (ref.getAmount() != null) {
										iga.setCautionMt(ref.getAmount().getAmount());
										iga.setCautionDev(ref.getAmount().getCurrency());
									}
									if (ref.getNotValidForCountryList() != null) {
										Lap lap = new Lap();
										for (String not : ref.getNotValidForCountryList()) {
											if (!Utils.isStringEmpty(not)) {
												lap.addCpaList(not);
											}
										}
									}																	
									gar.addIgaList(iga);
								}
							}
						}
						msg.addGarList(gar);
					}
				}
			}
			if (message.getMeansOfTransportDeparture() != null) {
				It itd = new It();
				itd.setMod(message.getMeansOfTransportDeparture().getTransportMode());
				itd.setIdt(message.getMeansOfTransportDeparture().getTransportationNumber());
				itd.setNat(message.getMeansOfTransportDeparture().getTransportationCountry());
				msg.setItd(itd);
			}
			if (message.getMeansOfTransportBorder() != null) {
				It itf = new It();
				itf.setMod(message.getMeansOfTransportBorder().getTransportMode());
				itf.setTyp(message.getMeansOfTransportBorder().getTransportationType());
				itf.setIdt(message.getMeansOfTransportBorder().getTransportationNumber());
				itf.setNat(message.getMeansOfTransportBorder().getTransportationCountry());
				msg.setItf(itf);
			}			
			msg.setIexp(mapFrParty(message.getConsignor()));				
			msg.setIdes(mapFrParty(message.getConsignee()));	
			if (message.getAuthorisedConsignee() != null && message.getAuthorisedConsignee().getPartyTIN() != null) {	
				msg.setDesa(message.getAuthorisedConsignee().getPartyTIN().getTIN());
			}			
			msg.setIpo(mapFrParty(message.getPrincipal()));							
			if (!Utils.isStringEmpty(message.getRepresentativeName()) || 
				!Utils.isStringEmpty(message.getRepresentativeCapacity())) {
				FrParty ire = new FrParty();
				ire.setNom(message.getRepresentativeName());
				ire.setPvr(message.getRepresentativeCapacity());
				msg.setIre(ire);
			}
			Itot itot = new Itot();
			itot.setNbu(message.getTotalNumberPackages());
			itot.setPdb(message.getTotalGrossMass());			
			itot.setNbc(message.getTotalNumberPositions());		
			if (message.getSeals() != null) {
				Scl scl = new Scl();
				scl.setNbs(message.getSeals().getNumber());
				if (message.getSeals().getSealNumbersList() != null) {
					for (SealNumber sn : message.getSeals().getSealNumbersList()) {
						if (sn != null) {
							scl.addNusList(sn.getNumber());
						}
					}
				}
				msg.setScl(scl);
			}			
			msg.setSecurity(this.mapSecurity());
			
			if (message.getGoodsItemList() != null) {
				for (MsgNCTSDeclarationPos item : message.getGoodsItemList()) {							
					msg.addLigList(this.mapLig(item));					
				}
			}			
		}
		
		private FrParty mapFrParty(PartyNCTS partyKids) {
			if (partyKids == null) {
				return null;
			}
			FrParty party = new FrParty();
			if (partyKids.getPartyTIN() != null) {
				party.setSir(partyKids.getPartyTIN().getTIN());
			}
			if (partyKids.getAddress() != null) {
				AddressNCTS address = new AddressNCTS();
				party.setNom(address.getName());				
				if (!Utils.isStringEmpty(address.getStreet())) {
					String str = address.getStreet();					
					if (!Utils.isStringEmpty(address.getHouseNumber())) {
						str = str + " " + address.getHouseNumber();
					}
					party.setRue(str);
				}
				party.setVil(address.getCity());
				party.setCdp(address.getPostalCode());
				party.setPay(address.getCountry());		
			}
			return party;
		}
		private Security mapSecurity() {
			Security security = new Security();
			
			security.setEnable(message.getSecurityIndicator());
			security.setCircSpecInd(message.getSituationCode());
			security.setModPaiTrp(message.getPaymentType());
			security.setConvRef(message.getConveyanceNumber());
			security.setComRef(message.getShipmentNumber());
			security.setUnloadCode(message.getPlaceOfUnloading());
			if (message.getTransRoute() != null && message.getTransRoute().getCountryList() != null) {
				for (String country : message.getTransRoute().getCountryList()) {
					if (!Utils.isStringEmpty(country)) {
						Itinerary iti = new Itinerary();
						iti.setCountry(country);
						security.addItineraryList(iti);
					}
				}
			}			
			security.setCarrier(this.mapFrParty(message.getCarrier()));					
			security.setConsignor(this.mapFrParty(message.getConsignor()));			
			security.setConsignee(this.mapFrParty(message.getConsignee()));
			
			return security;
		}
		private Lig mapLig(MsgNCTSDeclarationPos item) {
			if (item == null) {
				return null;
			}
			Lig lig = new Lig();
			Ico ico = new Ico();
			Dm dm = new Dm();
			ico.setDm(dm);
			
			lig.setNoa(item.getItemNumber());
			lig.setTyd(item.getTypeOfDeclaration());
			lig.setPex(item.getDispatchCountry());
			lig.setPde(item.getDestinationCountry());			
			lig.setIexp(this.mapFrParty(item.getConsignor()));			
			lig.setIdes(this.mapFrParty(item.getConsignee()));				
			dm.setLi1(item.getDescription());
			if (item.getCommodityCode() != null) {				
				ico.setNdp(item.getCommodityCode().getTarifCode());
				dm.setLi2(item.getCommodityCode().getAddition());
				dm.setLi3(item.getCommodityCode().getAddition2());
				dm.setLi4(item.getCommodityCode().getAddition3());
			}
			if (item.getPackagesList() != null) {
				for (Packages pack : item.getPackagesList()) {
					if (pack != null) {
						Cdt cdt = new Cdt();
						cdt.setTco(pack.getType());
						cdt.setNbc(pack.getQuantity());
						cdt.setNbp(pack.getQuantityCH());
						cdt.setTxt(pack.getMarks());
						lig.addCdtList(cdt);
					}
				}
			}
			if (item.getContainer() != null && item.getContainer().getNumberList() != null) {
				for (String nr : item.getContainer().getNumberList()) {
					if (!Utils.isStringEmpty(nr)) {
						Ctr ctr = new Ctr();
						ctr.setNct(nr);
						lig.addCtrList(ctr);
					}					
				}
			}
			if (item.getGuaranteeList() != null && item.getGuaranteeList().get(0) != null) {
				Guarantee gar = item.getGuaranteeList().get(0);
				if (gar.getReferenceList() != null && gar.getReferenceList().get(0) != null) {
					Amount amount = gar.getReferenceList().get(0).getAmount();
					if (amount != null && !Utils.isStringEmpty(amount.getAmount())) {
						Caution caution = new Caution();
						caution.setMarchVal(amount.getAmount());
						lig.setCaution(caution);
					}
				}				
			}
			if (item.getPreviousDocumentList() != null) {
				for (PreviousDocument prev : item.getPreviousDocumentList()) {
					if (prev != null) {
						FrDocument idan = new FrDocument();
						idan.setTyd(prev.getType());
						idan.setRef(prev.getMarks());
						idan.setCpl(prev.getAdditionalInformation());
						lig.addIdanList(idan);
					}
				}
			}
			if (item.getDocumentList() != null) {
				for (Document doc : item.getDocumentList()) {
					if (doc != null) {
						FrDocument idpr = new FrDocument();
						idpr.setTyd(doc.getType());
						idpr.setRef(doc.getReference());
						idpr.setCpl(doc.getAdditionalInformation());
						lig.addIdprList(idpr);
					}
				}
			}
			if (item.getSensitiveGoodsList() != null) {
				for (SensitiveGoods dgs : item.getSensitiveGoodsList()) {
					if (dgs != null) {
						Cps cps = new Cps();
						cps.setCod(dgs.getType());
						cps.setQte(dgs.getWeight());
						lig.addCpsList(cps);
					}
				}
			}
			if (item.getSpecialMentionList() != null) {
				for (SpecialMention sm : item.getSpecialMentionList()) {
					if (sm != null) {
						Msp msp = new Msp();
						msp.setEue(sm.getExportFromEU());
						msp.setEap(sm.getExportFromCountry());
						msp.setCod(sm.getExport());
						msp.setInf(sm.getText());
						lig.addMspList(msp);
					}
				}
			}
			if (item.getConsignor() != null || item.getConsignee() != null) {
				Security security = new Security();
				security.setConsignor(this.mapFrParty(item.getConsignor()));
				security.setConsignee(this.mapFrParty(item.getConsignee()));
				lig.addSecurityList(security);
			}
			
			return lig;
		}
	
}