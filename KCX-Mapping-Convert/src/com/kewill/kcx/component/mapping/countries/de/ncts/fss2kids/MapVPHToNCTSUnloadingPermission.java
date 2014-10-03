package com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermissionPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVPPPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPV;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSUnloadingPermissionKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MapVIAToNCTSUnloadingPermission<br>
 * Created		: 2010.09.06<br>
 * Description	: Mapping of FSS to KIDS format of VPH.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class MapVPHToNCTSUnloadingPermission extends KidsMessage {
	private MsgVPH msgVPH;
	private MsgNCTSUnloadingPermission msgNCTSUnloadingPermission;
	
	public MapVPHToNCTSUnloadingPermission() {
		msgVPH = new MsgVPH();
		msgNCTSUnloadingPermission = new MsgNCTSUnloadingPermission();
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
			header.setHeaderFields(msgVPH.getVorSubset());
			header.setMessageName("UnloadingPermission");
			header.setMessageID(getMsgID());
			CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
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
		
		setVPH(msgVPH);
		setVPV(msgVPH);
		setVPAConsignor(msgVPH);
		setVPAConsignee(msgVPH);
		setVPAPrincipal(msgVPH);
		
		List<MsgNCTSUnloadingPermissionPos> list = new ArrayList<MsgNCTSUnloadingPermissionPos>();
		if (msgVPH.getPosList() != null) {
		 for (MsgVPPPos msgVPPPos : msgVPH.getPosList()) {			
			if (msgVPPPos != null) {
				MsgNCTSUnloadingPermissionPos pos = new MsgNCTSUnloadingPermissionPos();
				if (msgVPPPos.getVppList() != null) {	//VPP 
					TsVPP tsVpp = msgVPPPos.getVppList().get(0);
					if (tsVpp != null) {
						msgNCTSUnloadingPermission.setReferenceNumber(tsVpp.getBeznr());
						pos.setItemNumber(tsVpp.getPosnr());
						if (tsVpp.getTnr() != null) {
							CommodityCode code = new CommodityCode();
							code.setTarifCode(tsVpp.getTnr());
							pos.setCommodityCode(code);
						}
						pos.setDescription(tsVpp.getWabsch());
						pos.setGrossMass(tsVpp.getRohmas());
						pos.setNetMass(tsVpp.getEigmas());
						pos.setMissingLineIndicator(tsVpp.getPfehlt());
					}
				}			
				pos.setSpecialMentionList(setVPP(msgVPPPos));
				pos.setConsignor(setVPDConsignor(msgVPPPos));
				pos.setConsignee(setVPDConsignee(msgVPPPos));
				pos.setPackageList(setVPL(msgVPPPos));
				pos.setDocumentList(setVPU(msgVPPPos));
				pos.setContainers(setVPC(msgVPPPos));
				pos.setSensitiveGoodsList(setVPE(msgVPPPos));
			
				list.add(pos);
			}
		 }
		 
		 msgNCTSUnloadingPermission.setGoodsItemList(list);
		}
	}

	private void setVPH(MsgVPH msgVPH) {
		if (msgVPH.getVphSubset() != null) {
			msgNCTSUnloadingPermission.setReferenceNumber(msgVPH.getVphSubset().getBeznr());
			msgNCTSUnloadingPermission.setUcrNumber(msgVPH.getVphSubset().getMrn());
			msgNCTSUnloadingPermission.setTypeOfDeclaration(msgVPH.getVphSubset().getAnmart());
			msgNCTSUnloadingPermission.setPlaceOfTransfer(msgVPH.getVphSubset().getUbgort());
			msgNCTSUnloadingPermission.setDispatchCountry(msgVPH.getVphSubset().getLdvs());
			msgNCTSUnloadingPermission.setDestinationCountry(msgVPH.getVphSubset().getLdbe());
			msgNCTSUnloadingPermission.setOfficeOfDestination(msgVPH.getVphSubset().getBedst());
			msgNCTSUnloadingPermission.setOfficeOfDeparture(msgVPH.getVphSubset().getAbgdst());
			TransportMeans transportMeans = new TransportMeans();
			transportMeans.setTransportationNumber(msgVPH.getVphSubset().getBfabkz());
			transportMeans.setTransportationCountry(msgVPH.getVphSubset().getBfabld());
			msgNCTSUnloadingPermission.setMeansOfTransportDeparture(transportMeans);
			TIN tin = new TIN();
			tin.setTin(msgVPH.getVphSubset().getTinve());
			msgNCTSUnloadingPermission.setConsignorTIN(tin);
			tin = new TIN();
			tin.setTin(msgVPH.getVphSubset().getTinem());
			msgNCTSUnloadingPermission.setConsigneeTIN(tin);
			tin = new TIN();
			tin.setTin(msgVPH.getVphSubset().getTinhp());
			msgNCTSUnloadingPermission.setPrincipalTIN(tin);
			msgNCTSUnloadingPermission.setTotalGrossMass(msgVPH.getVphSubset().getGsroh());
			msgNCTSUnloadingPermission.setTotalNumberPositions(msgVPH.getVphSubset().getAnzpos());
			msgNCTSUnloadingPermission.setTotalNumberPackages(msgVPH.getVphSubset().getAnzcol());
			msgNCTSUnloadingPermission.setTotalNumberSeals(msgVPH.getVphSubset().getAnzve());
			msgNCTSUnloadingPermission.setEndOfPresentationDate(msgVPH.getVphSubset().getWgfri());
			//EI20110527:
			//msgNCTSUnloadingPermission.setEndOfPresentationDateFormat(EFormat.ST_Date);
			msgNCTSUnloadingPermission.setEndOfPresentationDateFormat(EFormat.KIDS_Date);
		}
	}
	
	private void setVPV(MsgVPH msgVPH) {
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
	
	private void setVPAConsignor(MsgVPH msgVPH) {
		if (msgVPH.getVpaConsignor() != null) {
			PartyNCTS consignor = new PartyNCTS();
			AddressNCTS address = new AddressNCTS();
			String[] stAndHouseNum;
			
			address.setName(msgVPH.getVpaConsignor().getName());
			/* EI20111027
			if (msgVPH.getVpaConsignor().getStr() != null) {
				stAndHouseNum = msgVPH.getVpaConsignor().getStr().split(" ");
				address.setStreet(stAndHouseNum[0]);
				if (stAndHouseNum.length > 1) {
					address.setHouseNumber(stAndHouseNum[1]);
				}
			}
			*/
			address.setStreet(msgVPH.getVpaConsignor().getStr()); //EI20111027
			address.setCity(msgVPH.getVpaConsignor().getOrt());
			address.setPostalCode(msgVPH.getVpaConsignor().getPlz());
			address.setCountry(msgVPH.getVpaConsignor().getLand());
			
			consignor.setAddress(address);
			consignor.setETNAddress(msgVPH.getVphSubset().getEtnabs());
			msgNCTSUnloadingPermission.setConsignor(consignor);
		} else {
			return;
		}
	}
	
	private void setVPAConsignee(MsgVPH msgVPH) {
		if (msgVPH.getVpaConsignee() != null) {
			PartyNCTS consignee = new PartyNCTS();
			AddressNCTS address = new AddressNCTS();
			String[] stAndHouseNum;
			
			address.setName(msgVPH.getVpaConsignee().getName());
			/* EI20111027
			if (msgVPH.getVpaConsignee().getStr() != null) {
				stAndHouseNum = msgVPH.getVpaConsignee().getStr().split(" ");
				address.setStreet(stAndHouseNum[0]);
				if (stAndHouseNum.length > 1) {
					address.setHouseNumber(stAndHouseNum[1]);
				}
			}
			*/
			address.setStreet(msgVPH.getVpaConsignee().getStr()); //EI20111027
			address.setCity(msgVPH.getVpaConsignee().getOrt());
			address.setPostalCode(msgVPH.getVpaConsignee().getPlz());
			address.setCountry(msgVPH.getVpaConsignee().getLand());
			consignee.setAddress(address);
			msgNCTSUnloadingPermission.setConsignee(consignee);
		} else {
			return;
		}
	}
	
	private void setVPAPrincipal(MsgVPH msgVPH) {
		if (msgVPH.getVpaPrincipal() != null) {
			PartyNCTS principal = new PartyNCTS();
			AddressNCTS address = new AddressNCTS();
			String[] stAndHouseNum;
			
			address.setName(msgVPH.getVpaPrincipal().getName());
			/*
			if (msgVPH.getVpaPrincipal().getStr() != null) {
				stAndHouseNum = msgVPH.getVpaPrincipal().getStr().split(" ");
				address.setStreet(stAndHouseNum[0]);
				if (stAndHouseNum.length > 1) {
					address.setHouseNumber(stAndHouseNum[1]);
				}
			}
			*/
			address.setStreet(msgVPH.getVpaPrincipal().getStr()); //EI20111027
			address.setCity(msgVPH.getVpaPrincipal().getOrt());
			address.setPostalCode(msgVPH.getVpaPrincipal().getPlz());
			address.setCountry(msgVPH.getVpaPrincipal().getLand());
			principal.setAddress(address);
			msgNCTSUnloadingPermission.setPrincipal(principal);
		} else {
			return;
		}
	}
	
	private List<SpecialMention> setVPP(MsgVPPPos msgVPPPos) {
		if (msgVPPPos.getVppList() != null) {
			List<SpecialMention> list = new ArrayList<SpecialMention>();
			
			for (TsVPP tsVpp : msgVPPPos.getVppList()) {
				SpecialMention specialMention = new SpecialMention();
				specialMention.setText(tsVpp.getUnstm());
				specialMention.setExportFromEU(tsVpp.getKzexeu());
				specialMention.setExportFromCountry(tsVpp.getLdexp());
				
				list.add(specialMention);
			}
			return list;
		} else {
			return null;
		}
	}
	
	private PartyNCTS setVPDConsignor(MsgVPPPos msgVPPPos) {
		// CK-2011-03-03 if null...
		if (msgVPPPos.getVpdConsignor() == null) {
			return null;
		}
		if (!msgVPPPos.getVpdConsignor().isEmpty()) {
			PartyNCTS consignor = new PartyNCTS();
			AddressNCTS address = new AddressNCTS();
			address.setName(msgVPPPos.getVpdConsignor().getName());
			/* EI20111027
			String[] stAndHouseNum = msgVPPPos.getVpdConsignor().getStr().split(" ");
			address.setStreet(stAndHouseNum[0]);
			if (stAndHouseNum.length > 1) {
				address.setHouseNumber(stAndHouseNum[1]);
			}			
			*/
			address.setStreet(msgVPPPos.getVpdConsignor().getStr());  //EI20111027
			address.setCity(msgVPPPos.getVpdConsignor().getOrt());
			address.setPostalCode(msgVPPPos.getVpdConsignor().getPlz());
			address.setCountry(msgVPPPos.getVpdConsignor().getLand());
			consignor.setAddress(address);
			
			return consignor;
		} else {
			return null;
		}
		
	}
	
	private PartyNCTS setVPDConsignee(MsgVPPPos msgVPPPos) {
		// CK-2011-03-03 if null...
		if (msgVPPPos.getVpdConsignee() == null) {
			return null;
		}
		if (!msgVPPPos.getVpdConsignee().isEmpty()) {
			PartyNCTS consignee = new PartyNCTS();
			AddressNCTS address = new AddressNCTS();
			address.setName(msgVPPPos.getVpdConsignee().getName());
			/*
			String[] stAndHouseNum = msgVPPPos.getVpdConsignee().getStr().split(" ");
			address.setStreet(stAndHouseNum[0]);
			if (stAndHouseNum.length > 1) {
				address.setHouseNumber(stAndHouseNum[1]);
			}
			*/
			address.setStreet(msgVPPPos.getVpdConsignee().getStr());  //EI20111027
			address.setCity(msgVPPPos.getVpdConsignee().getOrt());
			address.setPostalCode(msgVPPPos.getVpdConsignee().getPlz());
			address.setCountry(msgVPPPos.getVpdConsignee().getLand());
			consignee.setAddress(address);
			
			return consignee;
		} else {
			return null;
		}
	}
	
	private List<Packages> setVPL(MsgVPPPos msgVPPPos) {
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
	
	private List<Document> setVPU(MsgVPPPos msgVPPPos) {
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

	private Container setVPC(MsgVPPPos msgVPPPos) {
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

	private List<SensitiveGoods> setVPE(MsgVPPPos msgVPPPos) {
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
					sensitiveGoods.setWeight(tsVpe.getMenge());				
					list.add(sensitiveGoods);
				}
			}
			return list;
		} else {
			return null;
		}
	}
}
