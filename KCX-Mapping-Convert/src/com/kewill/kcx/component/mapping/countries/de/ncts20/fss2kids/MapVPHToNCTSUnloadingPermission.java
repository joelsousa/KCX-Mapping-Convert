package com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingPermissionPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVPHPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVPP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPV;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts20.BodyNCTSUnloadingPermissionKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS<br>
 * Created		: 06.02.2013<br>
 * Description	: Mapping of FSS-VPH to KIDS-NCTSUnloadingPermissionH.
 * 
 * @author iwaniuk
 * @version 4.1.00
 *
 */
public class MapVPHToNCTSUnloadingPermission extends KidsMessage {
	private MsgVPH msgVPH;
	private MsgNCTSUnloadingPermission msgNCTSUnloadingPermission;
	private String subversion = "";       //EO20130510
	
	public MapVPHToNCTSUnloadingPermission() {
		msgVPH = new MsgVPH();
		msgNCTSUnloadingPermission = new MsgNCTSUnloadingPermission();
	}
	
	public MapVPHToNCTSUnloadingPermission(CommonFieldsDTO commonFieldsDTO) {  //EI20130523
		msgVPH = new MsgVPH();
		msgNCTSUnloadingPermission = new MsgNCTSUnloadingPermission();
		this.setCommonFieldsDTO(commonFieldsDTO);   //EI20130523
		if (commonFieldsDTO != null && !Utils.isStringEmpty(commonFieldsDTO.getKidsRelease())) {
        	subversion = Utils.removeDots(commonFieldsDTO.getKidsRelease());        	
        }	
	}
	
	public void setMsgVPH(MsgVPH argument) {
		this.msgVPH = argument;
		this.setMsgFields();
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			KidsHeader header = new KidsHeader(writer);
			//header.setHeaderFields(msgVPH.getVorSubset());
			header.setHeaderFieldsFromHead(msgVPH.getVorSubset(), msgVPH.getHeadSubset());  //EI20130711
			header.setMessageName("UnloadingPermission");
			header.setMessageID(getMsgID());
			CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
	        if (this.getCommonFieldsDTO() != null && !Utils.isStringEmpty(this.getCommonFieldsDTO().getKidsRelease())) {
	        	subversion = Utils.removeDots(this.getCommonFieldsDTO().getKidsRelease());
	        	if (subversion.equals("4101")) {
	            	header.setRelease(this.getCommonFieldsDTO().getKidsRelease());
	            }
	        }
	        
	        header.writeHeader();
	        
	        BodyNCTSUnloadingPermissionKids body = new BodyNCTSUnloadingPermissionKids(writer);
	        body.setMsgNCTSUnloadingPermission(msgNCTSUnloadingPermission);
	        
	        getCommonFieldsDTO().setReferenceNumber(msgNCTSUnloadingPermission.getReferenceNumber());
	        body.writeBody();
	        
	        closeElement();	//soap:Envolope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	        	Utils.log("(MapVPHToNCTSUnloadingPermission getMessage) Msg = " + xmlOutputString.toString());
	        }
	} catch (XMLStreamException e) {
		e.printStackTrace();
	}
	
	return xmlOutputString.toString();
	}
	
	public void setMsgFields() {
		
		this.mapVPH(msgVPH);
		this.mapVPV(msgVPH);
		this.mapVPAConsignor(msgVPH);
		this.mapVPAConsignee(msgVPH);
		this.mapVPAPrincipal(msgVPH);
				
		if (msgVPH.getPosList() != null) {
			List<MsgNCTSUnloadingPermissionPos> list = new ArrayList<MsgNCTSUnloadingPermissionPos>();
			for (MsgVPHPos msgVPPPos : msgVPH.getPosList()) {			
				if (msgVPPPos != null) {
					MsgNCTSUnloadingPermissionPos pos = new MsgNCTSUnloadingPermissionPos();
					TsVPP tsVpp = msgVPPPos.getVppSubset();
					if (tsVpp != null)  {	 											
						pos.setItemNumber(tsVpp.getPosnr());
						if (tsVpp.getTnr() != null) {
							CommodityCode code = new CommodityCode();
							code.setTarifCode(tsVpp.getTnr());
							pos.setCommodityCode(code);
						}
						pos.setDescription(tsVpp.getWabsch());
						pos.setGrossMass(Utils.removeZabisDecimalPlaceV7(tsVpp.getRohmas(), 3)); //V70 as decimal
						pos.setNetMass(Utils.removeZabisDecimalPlaceV7(tsVpp.getEigmas(), 3)); 	//V70 as decimal
						if (subversion.equals("4101")) {     //EI20130510
							pos.setGrossMass(tsVpp.getRohmas());
							pos.setNetMass(tsVpp.getEigmas());
			            }
						pos.setTypeOfDeclaration(tsVpp.getAnmart());  //V70 new
						pos.setDestinationCountry(tsVpp.getBeld());   //V70 new	
						pos.setDispatchCountry(tsVpp.getVeld());      //V70 new
					}			
					pos.setSpecialMentionList(this.mapVPP(msgVPPPos));
					pos.setConsignor(this.mapVPDConsignor(msgVPPPos, tsVpp));				
					pos.setConsignee(this.mapVPDConsignee(msgVPPPos, tsVpp));				
					pos.setPackageList(this.mapVPL(msgVPPPos));
					pos.setDocumentList(this.mapVPU(msgVPPPos));
					pos.setContainers(this.mapVPC(msgVPPPos));
					pos.setSensitiveGoodsList(this.mapVPE(msgVPPPos));
			
					list.add(pos);
				}
			}
		 
			msgNCTSUnloadingPermission.setGoodsItemList(list);
		}
	}

	private void mapVPH(MsgVPH msgVPH) {
		if (msgVPH.getVphSubset() != null) {
			TsVPH vph = msgVPH.getVphSubset(); 
			msgNCTSUnloadingPermission.setReferenceNumber(vph.getBeznr());
			msgNCTSUnloadingPermission.setUcrNumber(vph.getMrn());
			msgNCTSUnloadingPermission.setTypeOfDeclaration(vph.getAnmart());
			msgNCTSUnloadingPermission.setPlaceOfTransfer(vph.getUbgort());
			msgNCTSUnloadingPermission.setDispatchCountry(vph.getLdvs());
			msgNCTSUnloadingPermission.setDestinationCountry(vph.getLdbe());
			msgNCTSUnloadingPermission.setOfficeOfDestination(vph.getBedst());
			msgNCTSUnloadingPermission.setOfficeOfDeparture(vph.getAbgdst());
			TransportMeans transportMeans = new TransportMeans();
			transportMeans.setTransportationNumber(vph.getBfabkz());
			transportMeans.setTransportationCountry(vph.getBfabld());
			msgNCTSUnloadingPermission.setMeansOfTransportDeparture(transportMeans);
			TIN tinve = new TIN();
			tinve.setTin(vph.getTinve());
			tinve.setBO(vph.getNlve());                  //V70 new
			tinve.setIdentificationType(vph.getIdve());  //V70 new
			if (!tinve.isEmpty()) {				
				if (msgNCTSUnloadingPermission.getConsignor() == null) {
					PartyNCTS consignor = new PartyNCTS();
					msgNCTSUnloadingPermission.setConsignor(consignor);
				}
				msgNCTSUnloadingPermission.getConsignor().setPartyTIN(tinve);				
			}
			TIN tinem = new TIN();
			tinem.setTin(vph.getTinem());			
			tinem.setBO(vph.getNlem());                  //V70 new
			tinem.setIdentificationType(vph.getIdem());  //V70 new			
			if (!tinem.isEmpty()) {				
				if (msgNCTSUnloadingPermission.getConsignee() == null) {
					PartyNCTS consignee = new PartyNCTS();					
					msgNCTSUnloadingPermission.setConsignee(consignee);
				}
				msgNCTSUnloadingPermission.getConsignee().setPartyTIN(tinem);
			}
			TIN tinhp = new TIN();
			tinhp.setTin(vph.getTinhp());			
			tinhp.setBO(vph.getNlhp());                             //V70 new
			tinhp.setIdentificationType(vph.getIdhp());             //V70 new				
			if (!tinhp.isEmpty()) {				
				if (msgNCTSUnloadingPermission.getPrincipal() == null) {
					PartyNCTS principal = new PartyNCTS();					
					msgNCTSUnloadingPermission.setPrincipal(principal);
				}
				msgNCTSUnloadingPermission.getPrincipal().setPartyTIN(tinhp);
			}
			msgNCTSUnloadingPermission.setCarnetID(vph.getHp());            //EI20130325
			
			msgNCTSUnloadingPermission.setTotalGrossMass(Utils.removeZabisDecimalPlaceV7(vph.getGsroh(), 3)); //V70 as decimal
			if (subversion.equals("4101")) {     //EI20130510
				msgNCTSUnloadingPermission.setTotalGrossMass(vph.getGsroh());
            }
			msgNCTSUnloadingPermission.setTotalNumberPositions(vph.getAnzpos());
			msgNCTSUnloadingPermission.setTotalNumberPackages(vph.getAnzcol());
			msgNCTSUnloadingPermission.setTotalNumberSeals(vph.getAnzve());
			msgNCTSUnloadingPermission.setEndOfPresentationDate(vph.getWgfri());
			//EI20110527:
			//msgNCTSUnloadingPermission.setEndOfPresentationDateFormat(EFormat.ST_Date);
			msgNCTSUnloadingPermission.setEndOfPresentationDateFormat(EFormat.KIDS_Date);
		}
	}
	
	private void mapVPV(MsgVPH msgVPH) {
		// CK-2011-03-03 if null...
		if (msgVPH.getVpvSubsetList() == null) {
			return;
		}
		if (!msgVPH.getVpvSubsetList().isEmpty()) {
			List<SealNumber> list = new ArrayList<SealNumber>();
			for (TsVPV tsVpv : msgVPH.getVpvSubsetList()) {
				SealNumber sealNumber = new SealNumber();
				
				sealNumber.setNumber(tsVpv.getSeal());
				list.add(sealNumber);
			}
			msgNCTSUnloadingPermission.setSealNumbersList(list);
		} else {
			return;
		}
	}
	
	private void mapVPAConsignor(MsgVPH msgVPH) {
		if (msgVPH.getVpaConsignor() != null) {							
			AddressNCTS address = new AddressNCTS();			
			address.setName(msgVPH.getVpaConsignor().getName());			
			address.setStreet(msgVPH.getVpaConsignor().getStr()); //EI20111027
			address.setCity(msgVPH.getVpaConsignor().getOrt());
			address.setPostalCode(msgVPH.getVpaConsignor().getPlz());
			address.setCountry(msgVPH.getVpaConsignor().getLand());
			if (msgNCTSUnloadingPermission.getConsignor() == null) {
				PartyNCTS consignor = new PartyNCTS();
				msgNCTSUnloadingPermission.setConsignor(consignor);
			}
			msgNCTSUnloadingPermission.getConsignor().setAddress(address);
			msgNCTSUnloadingPermission.getConsignor().setETNAddress(msgVPH.getVphSubset().getEtnabs());			
		} else {
			return;
		}
	}
	
	private void mapVPAConsignee(MsgVPH msgVPH) {
		if (msgVPH.getVpaConsignee() != null) {			
			AddressNCTS address = new AddressNCTS();			
			address.setName(msgVPH.getVpaConsignee().getName());			
			address.setStreet(msgVPH.getVpaConsignee().getStr()); 
			address.setCity(msgVPH.getVpaConsignee().getOrt());
			address.setPostalCode(msgVPH.getVpaConsignee().getPlz());
			address.setCountry(msgVPH.getVpaConsignee().getLand());			
			if (msgNCTSUnloadingPermission.getConsignee() == null) {
				PartyNCTS consignee = new PartyNCTS();
				msgNCTSUnloadingPermission.setConsignee(consignee);
			}
			msgNCTSUnloadingPermission.getConsignee().setAddress(address);
		} else {
			return;
		}
	}
	
	private void mapVPAPrincipal(MsgVPH msgVPH) {
		if (msgVPH.getVpaPrincipal() != null) {			
			AddressNCTS address = new AddressNCTS();					
			address.setName(msgVPH.getVpaPrincipal().getName());			
			address.setStreet(msgVPH.getVpaPrincipal().getStr()); 
			address.setCity(msgVPH.getVpaPrincipal().getOrt());
			address.setPostalCode(msgVPH.getVpaPrincipal().getPlz());
			address.setCountry(msgVPH.getVpaPrincipal().getLand());
			if (msgNCTSUnloadingPermission.getPrincipal() == null) {
				PartyNCTS principal = new PartyNCTS();
				msgNCTSUnloadingPermission.setPrincipal(principal);
			}
			msgNCTSUnloadingPermission.getPrincipal().setAddress(address);
		} else {
			return;
		}
	}
	
	private List<SpecialMention> mapVPP(MsgVPHPos msgVPPPos) {
		List<SpecialMention> list = null;
		if (msgVPPPos.getVppSubset() != null) {
			list = new ArrayList<SpecialMention>();
			
			SpecialMention specialMention = new SpecialMention();
			//specialMention.setText(msgVPPPos.getVppSubset()());
			specialMention.setExportFromEU(msgVPPPos.getVppSubset().getKzexeu());
			specialMention.setExportFromCountry(msgVPPPos.getVppSubset().getLdexp());
				
			list.add(specialMention);
		}
		return list;		
	}
	
	private PartyNCTS mapVPDConsignor(MsgVPHPos msgVPPPos, TsVPP vpp) {
		PartyNCTS consignor = null;
		
		if (msgVPPPos.getVpdConsignor() != null && !msgVPPPos.getVpdConsignor().isEmpty()) {					
			consignor = new PartyNCTS();
			AddressNCTS address = new AddressNCTS();
			address.setName(msgVPPPos.getVpdConsignor().getName());			
			address.setStreet(msgVPPPos.getVpdConsignor().getStr());  
			address.setCity(msgVPPPos.getVpdConsignor().getOrt());
			address.setPostalCode(msgVPPPos.getVpdConsignor().getPlz());
			address.setCountry(msgVPPPos.getVpdConsignor().getLand());
			consignor.setAddress(address);		
		}
		if (vpp != null) {
			TIN tin = new TIN();
			tin.setTIN(vpp.getEorive());   //V70 new
			tin.setBO(vpp.getNlve());      //V70 new
			tin.setIdentificationType(vpp.getIdve());   //V70 new
			if (!tin.isEmpty()) {
				if (msgVPPPos.getVpdConsignor() == null) {		
				consignor = new PartyNCTS();				
				}
				consignor.setPartyTIN(tin);
			}
		}
		
		return consignor;
	}	
	
	private PartyNCTS mapVPDConsignee(MsgVPHPos msgVPPPos, TsVPP vpp) {
		PartyNCTS consignee = null;
		
		if (msgVPPPos.getVpdConsignee() != null && !msgVPPPos.getVpdConsignee().isEmpty()) {		
			consignee = new PartyNCTS();
			AddressNCTS address = new AddressNCTS();
			address.setName(msgVPPPos.getVpdConsignee().getName());			
			address.setStreet(msgVPPPos.getVpdConsignee().getStr());  
			address.setCity(msgVPPPos.getVpdConsignee().getOrt());
			address.setPostalCode(msgVPPPos.getVpdConsignee().getPlz());
			address.setCountry(msgVPPPos.getVpdConsignee().getLand());
			consignee.setAddress(address);
		}
		if (vpp != null) {
			TIN tin = new TIN();
			tin.setTIN(vpp.getEoriem());   //V70 new
			tin.setBO(vpp.getNlem());      //V70 new
			tin.setIdentificationType(vpp.getIdem());   //V70 new
			if (!tin.isEmpty()) {
				if (msgVPPPos.getVpdConsignee() == null) {		
				consignee = new PartyNCTS();				
				}
				consignee.setPartyTIN(tin);
			}
		}
		return consignee;		
	}	
	
	private List<Packages> mapVPL(MsgVPHPos msgVPPPos) {
		if (msgVPPPos.getVplList() != null) {
			List<Packages> list = new ArrayList<Packages>();
			for (TsVPL tsVpl : msgVPPPos.getVplList()) {
				if (tsVpl !=  null) {
					Packages packages = new Packages();
					packages.setQuantity(tsVpl.getColanz());
					packages.setType(tsVpl.getColart());
					packages.setMarks(tsVpl.getCollz());				
					list.add(packages);
				}
			}
			return list;
		} else {
			return null;
		}
	}
	
	private List<Document> mapVPU(MsgVPHPos msgVPPPos) {
		// CK-2011-03-03 if null...
		if (msgVPPPos.getVpuList() == null) {
			return null;
		}
		if (!msgVPPPos.getVpuList().isEmpty()) {
			List<Document> list = new ArrayList<Document>();
			for (TsVPU tsVpu : msgVPPPos.getVpuList()) {
				if (tsVpu != null) {
					Document document = new Document();
					document.setQualifier(tsVpu.getUntart());
					document.setReference(tsVpu.getUntref());
					document.setAdditionalInformation(tsVpu.getUntzus());				
					list.add(document);
				}
			}
			return list;
		} else {
			return null;
		}
	}

	private Container mapVPC(MsgVPHPos msgVPPPos) {
		// CK-2011-03-03 if null...
		if (msgVPPPos.getVpcList() == null) {
			return null;
		}
		if (!msgVPPPos.getVpcList().isEmpty()) {
			List<String> list = new ArrayList<String>();
			Container container = new Container();
			for (TsVPC tsVpc : msgVPPPos.getVpcList()) {
				list.add(tsVpc.getConnr());
			}
			container.setNumberList(list);
			return container;
		} else {
			return null;
		}
	}

	private List<SensitiveGoods> mapVPE(MsgVPHPos msgVPPPos) {
		if (msgVPPPos == null) {
			return null;
		}
		if (msgVPPPos.getVpeList() == null) {
			return null;
		}
		if (!msgVPPPos.getVpeList().isEmpty()) {
			List<SensitiveGoods> list = new ArrayList<SensitiveGoods>();
			for (TsVPE tsVpe : msgVPPPos.getVpeList()) {
				if (tsVpe != null) {
					SensitiveGoods sensitiveGoods = new SensitiveGoods();
					sensitiveGoods.setType(tsVpe.getCode());
					sensitiveGoods.setWeight(Utils.removeZabisDecimalPlaceV7(tsVpe.getMenge(), 3));	//V70 as decimal
					if (subversion.equals("4101")) {     //EI20130510
						sensitiveGoods.setWeight(tsVpe.getMenge());
		            }
					list.add(sensitiveGoods);
				}
			}
			return list;
		} else {
			return null;
		}
	}
}
