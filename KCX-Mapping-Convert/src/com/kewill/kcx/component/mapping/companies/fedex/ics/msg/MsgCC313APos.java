package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

/*
 * Function    : MsgCC315APos 
 * Titel       :
 * Date        : 25.11.2010
 * Author      : Kewill CSF / krzoska
 * Description : Positions of MsgCC315A of IE315
 * Parameters  :

 * Changes
 * ------------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Comcodgoditm;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Connr2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Idemeatragi970;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Pacgs2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Spemenmt2;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgCC315APos<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: -.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MsgCC313APos extends KCXMessage {

	private String 				itemNumber; 
	private String				shipmentNumber;
	private String				description;
	private String				descriptionLng;
	private String				grossMass;
	private String				dangerousGoodsNumber;
	private String				paymentType;
	private String				loadingPlace;
	private String				loadingPlaceLng;
	private String				unloadingPlace;
	private String				unloadingPlaceLng;
	
	//Documents
	private Prodocd2			prodocdc2;
	private List<Prodocd2>		prodocdc2List = null;

	//SpecialMention
	private Spemenmt2			spemenmt2;						
	private List<Spemenmt2>		spemenmt2List = null;
	
	private FedexAddress		traconco2;
	private FedexAddress		traconce2;

	// CODE COMMODITY
	private Comcodgoditm		comcodgoditm;
	
	//Container
	private Connr2				connr2;
	private List<Connr2>  		connr2List = null;
	
	//Means Of Transport Border
	private Idemeatragi970		 idemeatragi970;
	private List<Idemeatragi970> idemeatragi970List = null; //999 
	
	//Packages
	private Pacgs2				pacgs2;
	private List<Pacgs2>		pacgs2List = null;
	
	private FedexAddress 		notpar640;
    	

	public MsgCC313APos() {
      	super();
	}

	public MsgCC313APos(XMLEventReader parser) {
		super(parser);
	}      

	public MsgCC313APos(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EGoodsItemTags {
		IteNumGDS7,
		GooDesGDS23,
		GooDesGDS23LNG,
		GroMasGDS46,
		MetOfPayGDI12,
		ComRefNumGIM1,
		UNDanGooCodGDI1,
		PlaLoaGOOITE333,
		PlaLoaGOOITE333LNG,
		PlaUnlGOOITE333,
		PlaUnlGOOITE333LNG,
		PRODOCDC2,
		SPEMENMT2,
		TRACONCO2,
		TRACONCE2,
		COMCODGODITM,
		CONNR2,
		IDEMEATRAGI970,
		PACGS2,
		NOTPAR640


		;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EGoodsItemTags) tag) {
		case PRODOCDC2:	prodocdc2 = new Prodocd2(getScanner());
						prodocdc2.parse(tag.name());
						addProdocdc2List(prodocdc2);
						break;
		case SPEMENMT2:	spemenmt2 = new Spemenmt2(getScanner());
						spemenmt2.parse(tag.name());
						addSpemenmt2List(spemenmt2);
						break;
		case TRACONCO2:	traconco2 = new FedexAddress(getScanner());
						traconco2.parse(tag.name());
						break;
		case TRACONCE2: traconce2 = new FedexAddress(getScanner());
						traconce2.parse(tag.name());
						break;
		case COMCODGODITM: comcodgoditm = new Comcodgoditm(getScanner());
						   comcodgoditm.parse(tag.name()); 
						break;
		case CONNR2:	connr2 = new Connr2(getScanner());
						connr2.parse(tag.name());
						addConnr2List(connr2);
						break;
		case IDEMEATRAGI970: idemeatragi970 = new Idemeatragi970(getScanner());
						idemeatragi970.parse(tag.name());
						addIdemeatragi970List(idemeatragi970);
						break;
		case PACGS2:	pacgs2 = new Pacgs2(getScanner());
						pacgs2.parse(tag.name());
						addPacgs2List(pacgs2);
						break;
		case NOTPAR640: notpar640 = new FedexAddress(getScanner());
						notpar640.parse(tag.name());
						break;

		default:
				return;
		}
	  } else {
		switch ((EGoodsItemTags) tag) {
		case IteNumGDS7:		setItemNumber(value);
			break;
		case GooDesGDS23: 		setDescription(value);
			break;
		case GooDesGDS23LNG: 	setDescriptionLng(value);
			break;
		case GroMasGDS46: 		setGrossMass(value);
			break;
		case MetOfPayGDI12: 	setPaymentType(value);
			break;
		case ComRefNumGIM1: 	setShipmentNumber(value);
			break;

		case PlaLoaGOOITE333: 	setLoadingPlace(value);
		break;
		case PlaLoaGOOITE333LNG:setLoadingPlaceLng(value);
			break;
		case PlaUnlGOOITE333: 	setUnloadingPlace(value);
			break;
		case PlaUnlGOOITE333LNG:setLoadingPlaceLng(value);
			break;
		case UNDanGooCodGDI1: 	setDangerousGoodsNumber(value);
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

	public Enum translate(String token) {
		try {
			return EGoodsItemTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getItemNumber() {
		return itemNumber;
	
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
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

	public String getDangerousGoodsNumber() {
		return dangerousGoodsNumber;
	
	}

	public void setDangerousGoodsNumber(String dangerousGoodsNumber) {
		this.dangerousGoodsNumber = Utils.checkNull(dangerousGoodsNumber);
	}

	public String getPaymentType() {
		return paymentType;
	
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = Utils.checkNull(paymentType);
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

	private void addProdocdc2List(Prodocd2 prodocdc2) {
		if (prodocdc2List == null) {
			prodocdc2List = new Vector<Prodocd2>();
		}
		
		prodocdc2List.add(prodocdc2);
	}


	
	public List<Spemenmt2> getSpecialMentionList() {
		return spemenmt2List;
	}


	private void addSpemenmt2List(Spemenmt2 spemenmt2) {
		if (spemenmt2List == null) {
			spemenmt2List = new Vector<Spemenmt2>();
		}

		spemenmt2List.add(spemenmt2);
	}
	
	private void addConnr2List(Connr2 connr2) {
		if (connr2List == null) {
			connr2List = new Vector<Connr2>();
		}
		connr2List.add(connr2);
	}


	private void addIdemeatragi970List(Idemeatragi970 idemeatragi970) {
		if (idemeatragi970List == null) {
			idemeatragi970List = new Vector<Idemeatragi970>();
		}
		idemeatragi970List.add(idemeatragi970);
	}

	
	private void addPacgs2List(Pacgs2 pacgs2) {
		if (pacgs2List == null) {
			pacgs2List = new Vector<Pacgs2>();
		}
		pacgs2List.add(pacgs2);
	}

	public List<Prodocd2> getProdocdc2List() {
		return prodocdc2List;
	
	}

	public void setProdocdc2List(List<Prodocd2> prodocdc2List) {
		this.prodocdc2List = prodocdc2List;
	}

	public FedexAddress getTraconco2() {
		return traconco2;
	
	}

	public void setTraconco2(FedexAddress traconco2) {
		this.traconco2 = traconco2;
	}

	public FedexAddress getTraconce2() {
		return traconce2;
	
	}

	public void setTraconce2(FedexAddress traconce2) {
		this.traconce2 = traconce2;
	}

	public Comcodgoditm getComcodgoditm() {
		return comcodgoditm;
	}

	public List<Connr2> getConnr2List() {
		return connr2List;
	
	}

	public List<Idemeatragi970> getIdemeatragi970List() {
		return idemeatragi970List;
	
	}

	public List<Pacgs2> getPacgs2List() {
		return pacgs2List;
	
	}

	public FedexAddress getNotpar640() {
		return notpar640;
	
	}

}
