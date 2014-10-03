package com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids;


import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfCompletion;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemCompletion;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSEK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSEK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSEP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyNotificationOfCompletionKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 03.07.2013<br>
 * Description	: Mapping of FSS SEK to KIDS NotificationOfCompletion Mitteilung der Erledigung.
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 */
public class MapSEKtoNotificationOfCompletion extends KidsMessageManifest20 {
	
	private MsgSEK msgSEK;	
	private MsgNotificationOfCompletion message;
	
	public MapSEKtoNotificationOfCompletion() {
		msgSEK = new MsgSEK();	
		message = new MsgNotificationOfCompletion();
	}

	public void setMsgSEK(MsgSEK argument) {
		this.msgSEK = argument;						
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
	        
	        KidsHeader  header = new KidsHeader(writer);
	        header.setHeaderFieldsFromHead(msgSEK.getVorSubset(), msgSEK.getHeadSubset());
	        
	        header.setMessageName("NotificationOfCompletion");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyNotificationOfCompletionKids body   = new BodyNotificationOfCompletionKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapSEKToNotificationOfCompletionKids getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
			
	private void setMsgFields() {
		if (msgSEK.getSekSubset() != null) {
			message.setReferenceNumber(msgSEK.getSekSubset().getBeznr());
			message.setRegistrationNumber(msgSEK.getSekSubset().getRegnr());			
			message.setTypeOfTransaction(msgSEK.getSekSubset().getErlart());			
			//EI20131022: message.setReference(mapReference(msgSEK.getSekSubset()));
			message.setRegistrationNumberOfCompletion(msgSEK.getSekSubset().getErlreg()); //EI20131022				
			if (Utils.isStringEmpty(msgSEK.getSekSubset().getBefnum())) {
				Transport trans = new Transport();
				trans.setTransportationNumber(msgSEK.getSekSubset().getBefnum());
				message.setTransport(trans);
			}			
			if (!Utils.isStringEmpty(msgSEK.getSekSubset().getEmpdat())) {	
				String datetime = msgSEK.getSekSubset().getEmpdat();				
				if (datetime.length() == 14) {
					message.setDateTimeOfReceipt(datetime.substring(0, 12));
				} else if (datetime.length() == 12) {
					message.setDateTimeOfReceipt(datetime);
				}
			}
			message.setDateOfArrival(msgSEK.getSekSubset().getAnkdat());
			message.setEdifactNumber(msgSEK.getSekSubset().getEdinr());
		}
		
		if (msgSEK.getSepList() != null) {			
			for (TsSEP sep : msgSEK.getSepList()) {
				if (sep != null) {
					message.addGoodsItemList(getGoodsItem(sep));
				}
			}
		}
			
		//EI20140314: message.setLocalApplication(mapLocalApplication(msgSEK.getHeadSubset(), msgSEK.getKunSubset(), "CUSFIN"));	
		message.setLocalApplication(mapLocalApplication("CUSFIN", msgSEK.getHeadSubset(), msgSEK.getKunSubset(), msgSEK.getKupPosList()));	
		
	
		if (message.getLocalApplication() != null) {				
			message.setRegistrationDate(message.getLocalApplication().getRegistrationDate());				
		}				
    }
	/*
	an3	Verarbeitungszustand der SumA-Nachricht, möglicher Inhalt z.B.: VSA VSM ESA ESV WAV 
	an3	Höchstes Fehlergewicht lt. CUSREC (nur bei SCK)
	an2	Kopfstatus in ZABIS
	datetime(14)	Empfangsdatum der ATB-Nummer
	n2	Positionsstatus (nur CUSFIN/SEK)
	an211	leer
	*/
	private ManifestReference mapReference(TsSEK sekSubset) {
		ManifestReference 		ms	= null;
		
		if (!Utils.isStringEmpty(sekSubset.getErlreg())) {
			ms = new ManifestReference();
			ms.setRegistrationNumber(sekSubset.getErlreg());										
		}
		return ms;
	}

	private GoodsItemCompletion getGoodsItem(TsSEP sep) {
		GoodsItemCompletion item = new GoodsItemCompletion();
		
		item.setItemNumber(sep.getPosnr());
		item.setRegistrationNumber(sep.getRegnr());
		item.setDateOfCompletion(sep.getErldat());
		item.setContact(mapContactPerson(sep.getErlsb()));
		item.setCustodianTIN(mapCustodianTIN(sep));		
		item.setPackagesList(mapPackageList(sep));		
		item.setReferenceNumber(sep.getBeznr());			
		item.setReferencedSpecification(mapReferencedSpecification(sep));
		item.setCancellationCode(sep.getKzstor());
		
		return item;
	}
	
	private TIN mapCustodianTIN(TsSEP sep) {
		TIN custodianTIN = null;

		if (!Utils.isStringEmpty(sep.getVrweori()) || 
			!Utils.isStringEmpty(sep.getVrwnl())) {
				custodianTIN = new TIN();
				custodianTIN.setTIN(sep.getVrweori());
				custodianTIN.setBO(sep.getVrwnl());
		}
		return custodianTIN;
	}
	
	private ArrayList<Packages> mapPackageList(TsSEP sep) {
		ArrayList<Packages> list = null;
		if (!Utils.isStringEmpty(sep.getStkerl())) {
			list = new ArrayList<Packages>();
			Packages packages = new Packages();			
			packages.setQuantity(sep.getStkerl());
			list.add(packages);                   //EI20130705
		}
		return list;
	}

	private ReferencedSpecification mapReferencedSpecification(TsSEP sep) {
		
		ReferencedSpecification rs = null;
		
		if (!Utils.isStringEmpty(sep.getKzawb()) || 
			!Utils.isStringEmpty(sep.getAwbzzz())) {
				rs = new ReferencedSpecification();				
				rs.setTypeOfSpecificationID(sep.getKzawb());
				rs.setSpecificationID(sep.getAwbzzz());
		}
		return rs;
	}

	private ContactPerson mapContactPerson(String sb) {
		ContactPerson cp = null;
		if (!Utils.isStringEmpty(sb)) {
			cp = new ContactPerson();			
			cp.setName(sb);
		}
				
		return cp;
	}
	
}
