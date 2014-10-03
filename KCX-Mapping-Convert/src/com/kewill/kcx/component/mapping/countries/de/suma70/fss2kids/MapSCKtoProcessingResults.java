package com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids;


import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.CustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Notification;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSCK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSCP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyProcessingResultsKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 27.06.2013<br>
 * Description	: Mapping of FSS-SCK to KIDS-ProcessingResults.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class MapSCKtoProcessingResults extends KidsMessageManifest20 {
	
	private MsgSCK msgSCK;	
	private MsgProcessingResults message;
	
	public MapSCKtoProcessingResults() {
		msgSCK = new MsgSCK();	
		message = new MsgProcessingResults();
	}

	public void setMsgSCK(MsgSCK argument) {
		this.msgSCK = argument;						
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
	        header.setHeaderFieldsFromHead(msgSCK.getVorSubset(), msgSCK.getHeadSubset());     //EI20121005
	        
	        header.setMessageName("ProcessingResults");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyProcessingResultsKids body = new BodyProcessingResultsKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapSCKToProcessingResults getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
			
	public void setMsgFields() {	
		if (msgSCK.getSckSubset() != null) {
			message.setRegistrationNumber(msgSCK.getSckSubset().getRegnr());
			message.setReferenceNumber(msgSCK.getSckSubset().getBeznr());
			message.setRegistrationDate(msgSCK.getSckSubset().getRegdat());
			message.setTypeOfTransaction(msgSCK.getSckSubset().getNagru());
			if (!Utils.isStringEmpty(msgSCK.getSckSubset().getVgref())) {
				HeaderExtensions he = new HeaderExtensions();
				he.setHeadPosID(msgSCK.getSckSubset().getVgref());				
				message.setHeaderExtensions(he);
			}
			if (!Utils.isStringEmpty(msgSCK.getSckSubset().getBefnum())) {
				Transport trans = new Transport();
				trans.setTransportationNumber(msgSCK.getSckSubset().getBefnum());
				message.setTransport(trans);
			}
			message.setDateOfArrival(msgSCK.getSckSubset().getAnkdat());
			message.setEdifactNumber(msgSCK.getSckSubset().getEdinr());
			if (!Utils.isStringEmpty(msgSCK.getSckSubset().getEmpdat())) {	
				String datetime = msgSCK.getSckSubset().getEmpdat();				
				if (datetime.length() == 14) {
					message.setDateTimeOfReceipt(datetime.substring(0, 12));
				} else if (datetime.length() == 12) {
					message.setDateTimeOfReceipt(datetime);
				}
			}
		}
		
		/*
		 ntyp
		 */
			
			ArrayList<TsSCP> scpKopfList = msgSCK.getScpKopfList();     //EI20130704
			if (scpKopfList != null) {						
				for (TsSCP scp : scpKopfList) {
					if (scp != null) {
						int pos = 0; 
						if (scp.getPosnr() != null) {
							pos = Integer.parseInt(scp.getPosnr());
						}
						if (pos == 0)  {							
							message.addNotificationList(this.mapNotification(scp));
						} else {	
							this.mapGoodsItem(scp);
							message.addGoodsItemList(this.mapGoodsItem(scp));
						}
					}
				}
			}
 /*
wakbez
vwzeit
frzonekz
vland

			
  */
		if (msgSCK.getScpPosList() != null) {						
			for (TsSCP scp :  msgSCK.getScpPosList()) {
				if (scp != null) {
						message.addGoodsItemList(this.mapGoodsItem(scp));
				}
			}
		}
		//EI20140314:message.setLocalApplication(mapLocalApplication(msgSCK.getHeadSubset(), msgSCK.getKunSubset(), "CUSREC"));				
		message.setLocalApplication(mapLocalApplication("CUSREC", msgSCK.getHeadSubset(), msgSCK.getKunSubset(), msgSCK.getKupPosList()));			
    }
	
	private ItemProcessingResults mapGoodsItem(TsSCP scp) {
		if (scp == null) { 
			return null;
		}
		if (scp.isEmpty()) { 
			return null;
		}
		ItemProcessingResults item = new ItemProcessingResults();		
		item.setItemNumber(scp.getPosnr());
		item.setRegistrationNumber(scp.getRegnr());
		
		item.setCustodianTIN(mapTIN(scp));
		item.setCustomsResponse(mapCustomsResponse(scp));			
		item.setReferencedSpecification(getReferencedSpecification(scp));			
		item.setNotification(this.mapNotification(scp));
		return item;
	}
	private Notification mapNotification(TsSCP scp) {
		Notification noti = new Notification();
		if (scp == null) {
			return null;
		}
		noti.setNotificationType(scp.getMelgew());
		noti.setNotificationSubType(scp.getMelPref());
		noti.setNotificationCode(scp.getMelnr());
		noti.setNotificationDescription(scp.getFehtxt());
		
		return noti;
	}
	private TIN mapTIN(TsSCP scp) {
		TIN tin = null;
		if (scp == null) {
			return tin;
		}
		if (!Utils.isStringEmpty(scp.getVrweori()) || !Utils.isStringEmpty(scp.getVrwnl())) {
			tin = new TIN();
			tin.setTIN(scp.getVrweori());
			tin.setBO(scp.getVrwnl());			
		}
		
		return tin;
	}

	private CustomsResponse mapCustomsResponse(TsSCP scp) {
		if (scp == null) {
			return null;
		}
		CustomsResponse cr = null;
		if (!Utils.isStringEmpty(scp.getMelgew()) ||
			!Utils.isStringEmpty(scp.getMelnr()) ||
			!Utils.isStringEmpty(scp.getFehtxt())) {
			
			cr = new CustomsResponse();			
			cr.setType(scp.getMelgew());
			cr.setPointer(scp.getMelnr());
			cr.setReason(scp.getFehtxt());
		}		
		return cr;
	}

	private ReferencedSpecification getReferencedSpecification(TsSCP scp) {
		if (scp == null) {
			return null;
		}
		ReferencedSpecification rs = null;
	
		if (!Utils.isStringEmpty(scp.getAwbzzz()) ||
			!Utils.isStringEmpty(scp.getKzawb())) {
			
			rs = new ReferencedSpecification();
			rs.setTypeOfSpecificationID(scp.getKzawb());
			rs.setSpecificationID(scp.getAwbzzz());
		}	
		return rs;
	}	
}
