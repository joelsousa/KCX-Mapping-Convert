package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgCC351APos<br>
 * Date		: 06.22.2011<br>
 * Beschreibung	: -.
 * 
 * @author Lassiter
 * @version 1.0.00
 */

public class Gooitegds extends KCXMessage {
	private String 				itemNumber; 
	private String				comRefNumGIM1;	
	//GOOITEGDS313
	private String				description;
	private String				descriptionLng;
	private String				grossMass;
	private String				paymentType;
	private String				dangerousGoodsNumber;
	private String				loadingPlace;
	private String				loadingPlaceLng;
	private String				unloadingPlace;
	private String				unloadingPlaceLng;
	private List<Prodocd2>		prodocdc2351List = null;       //Dcouments	
	private List<Connr2>		connr2351List = null;          //Containers	  
	private List<Idemeatragi970> idemeatragi970351List = null; //Means Of Transport Border	
	private List<Spemenmt2>		spemenmt2List = null;          //Special Mentions	
	private CyprusAddress		traconco2;                     //Consignor Address
	private Comcodgoditm		comcodgoditm;
	private CyprusAddress		traconce2;
	private Pacgs2				pacgs2;
	private List<Pacgs2>		pacgs2List;
	private CyprusAddress		prtnot640;
	
	public Gooitegds() {
      	super();
	}

	public Gooitegds(XMLEventReader parser) {
		super(parser);
	}      

	public Gooitegds(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EGoodsItemTags {
		//CY				//GR
		IteNumGDS7,
		ComRefNumGIM1,
		PRODOCDC2351,       PRODOCDC2,
		CONNR2351,			CONNR2,
		IDEMEATRAGI970351,  IDEMEATRAGI970,
		
		//GOOITEGDS313
		GooDesGDS23,
		GooDesGDS23LNG,
		GroMasGDS46,
		MetOfPayGDI12,
		UNDanGooCodGDI1,
		PlaLoaGOOITE333,
		PlaLoaGOOITE333LNG,
		PlaUnlGOOITE333,
		PlaUnlGOOITE333LNG,
		
		SPEMENMT2,
		
		//GOOITEGDS2304
		PRODOCDC2304,
		CONNR2304,
		IDEMEATRAGI970304;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			
			switch ((EGoodsItemTags) tag) {
			case PRODOCDC2:
			case PRODOCDC2304:
			case PRODOCDC2351: 
				Prodocd2 prodocdc2351 = new Prodocd2(getScanner());
				prodocdc2351.parse(tag.name());
				addProdocdc2List(prodocdc2351);
				break;
			case CONNR2:
			case CONNR2304:
			case CONNR2351: 
				Connr2 connr2351 = new Connr2(getScanner());
				connr2351.parse(tag.name());
				addConnr2List(connr2351);
				break;
			case IDEMEATRAGI970:
			case IDEMEATRAGI970304:
			case IDEMEATRAGI970351: 
				Idemeatragi970 idemeatragi970351 = new Idemeatragi970(getScanner());
				idemeatragi970351.parse(tag.name());
				addIdemeatragi970List(idemeatragi970351);
				break;
			case SPEMENMT2: 
				Spemenmt2 spemenmt2 = new Spemenmt2(getScanner());
				spemenmt2.parse(tag.name());
				addSpemenmt2List(spemenmt2);
				break;
			default:
				return;
			}
		} else {
			switch ((EGoodsItemTags) tag) {
			case IteNumGDS7: 	
				setItemNumber(value);
				break;
			case ComRefNumGIM1: 
				setShipmentNumber(value);
				break;
			case GooDesGDS23: 
				setDescription(value);
				break;
			case GooDesGDS23LNG: 
				setDescriptionLng(value);
				break;
			case GroMasGDS46: 
				setGrossMass(value);
				break;
			case MetOfPayGDI12: 
				setPaymentType(value);
				break;
			case UNDanGooCodGDI1: 
				setDangerousGoodsNumber(value);
				break;
			case PlaLoaGOOITE333: 
				setLoadingPlace(value);
				break;
			case PlaLoaGOOITE333LNG: 
				setLoadingPlaceLng(value);
				break;
			case PlaUnlGOOITE333: 
				setUnloadingPlace(value);
				break;
			case PlaUnlGOOITE333LNG: 
				setUnloadingPlaceLng(value);
				break;
			default:
				return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return EGoodsItemTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	private void addConnr2List(Connr2 connr2) {
		if (connr2351List == null) {
			connr2351List = new Vector<Connr2>();
		}
		connr2351List.add(connr2);
	}
	
	private void addProdocdc2List(Prodocd2 prodocdc2) {
		if (prodocdc2351List == null) {
			prodocdc2351List = new Vector<Prodocd2>();
		}		
		prodocdc2351List.add(prodocdc2);
	}
	
	private void addIdemeatragi970List(Idemeatragi970 idemeatragi970) {
		if (idemeatragi970351List == null) {
			idemeatragi970351List = new Vector<Idemeatragi970>();
		}
		idemeatragi970351List.add(idemeatragi970);
	}
	
	private void addSpemenmt2List(Spemenmt2 spemenmt2) {
		if (spemenmt2List == null) {
			spemenmt2List = new Vector<Spemenmt2>();
		}
		spemenmt2List.add(spemenmt2);
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getShipmentNumber() {
		return comRefNumGIM1;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.comRefNumGIM1 = Utils.checkNull(shipmentNumber);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = Utils.checkNull(description);
	}

	public String getDescriptionLng() {
		return descriptionLng;
	}

	public void setDescriptionLng(String descriptionLng) {
		this.descriptionLng = Utils.checkNull(descriptionLng);
	}

	public String getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(String grossMass) {
		this.grossMass = Utils.checkNull(grossMass);
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = Utils.checkNull(paymentType);
	}

	public String getDangerousGoodsNumber() {
		return dangerousGoodsNumber;
	}

	public void setDangerousGoodsNumber(String dangerousGoodsNumber) {
		this.dangerousGoodsNumber = Utils.checkNull(dangerousGoodsNumber);
	}

	public String getLoadingPlace() {
		return loadingPlace;
	}

	public void setLoadingPlace(String loadingPlace) {
		this.loadingPlace = Utils.checkNull(loadingPlace);
	}

	public String getLoadingPlaceLng() {
		return loadingPlaceLng;
	}

	public void setLoadingPlaceLng(String loadingPlaceLng) {
		this.loadingPlaceLng = Utils.checkNull(loadingPlaceLng);
	}

	public String getUnloadingPlace() {
		return unloadingPlace;
	}

	public void setUnloadingPlace(String unloadingPlace) {
		this.unloadingPlace = Utils.checkNull(unloadingPlace);
	}

	public String getUnloadingPlaceLng() {
		return unloadingPlaceLng;
	}

	public void setUnloadingPlaceLng(String unloadingPlaceLng) {
		this.unloadingPlaceLng = Utils.checkNull(unloadingPlaceLng);
	}


	public List<Prodocd2> getProdocdc2List() {
		return prodocdc2351List;
	}

	public void setProdocdc2List(List<Prodocd2> prodocdc2351List) {
		this.prodocdc2351List = prodocdc2351List;
	}

	public List<Connr2> getConnr2List() {
		return connr2351List;
	}

	public void setConnr2List(List<Connr2> connr2351List) {
		this.connr2351List = connr2351List;
	}

	public List<Idemeatragi970> getIdemeatragi970List() {
		return idemeatragi970351List;
	}

	public void setIdemeatragi970List(List<Idemeatragi970> idemeatragi970351List) {
		this.idemeatragi970351List = idemeatragi970351List;
	}

	public List<Spemenmt2> getSpemenmt2List() {
		return spemenmt2List;
	}

	public void setSpemenmt2List(List<Spemenmt2> spemenmt2List) {
		this.spemenmt2List = spemenmt2List;
	}

	public CyprusAddress getTraconco2() {
		return traconco2;
	}

	public void setTraconco2(CyprusAddress traconco2) {
		this.traconco2 = traconco2;
	}

	public Comcodgoditm getComcodgoditm() {
		return comcodgoditm;
	}

	public void setComcodgoditm(Comcodgoditm comcodgoditm) {
		this.comcodgoditm = comcodgoditm;
	}

	public CyprusAddress getTraconce2() {
		return traconce2;
	}

	public void setTraconce2(CyprusAddress traconce2) {
		this.traconce2 = traconce2;
	}

	public Pacgs2 getPacgs2() {
		return pacgs2;
	}

	public void setPacgs2(Pacgs2 pacgs2) {
		this.pacgs2 = pacgs2;
	}

	public List<Pacgs2> getPacgs2List() {
		return pacgs2List;
	}
	public void setPacgs2List(List<Pacgs2> pacgs2List) {
		this.pacgs2List = pacgs2List;
	}
	
	public CyprusAddress getPrtnot640() {
		return prtnot640;
	}

	public void setPrtnot640(CyprusAddress prtnot640) {
		this.prtnot640 = prtnot640;
	}
	
}
