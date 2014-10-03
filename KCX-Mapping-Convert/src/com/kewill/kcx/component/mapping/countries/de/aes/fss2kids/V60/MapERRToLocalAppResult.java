/*
 * Function    : MapERRToLocalAppResult.java
 * Titel       :
 * Date        : 04.12.2008
 * Author      : Kewill CSF / houdek
 * Description : Mapping of FSS-Format ERR into KIDS-Format of LocalAppResult.
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 23.04.2009
 * Description : MsgKids replaced with MsgFailure
 *
 * Author      : EI
 * Date        : 04.05.2009
 * Description : copied from MapERRToLocalAppResult for V53 (no changes)
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgERR;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsERR;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyLocalAppResultKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapERRToLocalAppResult<br>
 * Erstellt		: 04.12.2008<br>
 * Beschreibung	: Mapping of FSS-Format ERR into KIDS-Format of LocalAppResult..
 * 
 * @author houdek
 * @version 5.0.00
 */
public class MapERRToLocalAppResult extends KidsMessage {
	
	private MsgERR msgERR;
	private MsgFailure msgFailure;
	private List<TsERR> errList = null;
	private String uidsName = "";

	
	public MapERRToLocalAppResult() {
		msgFailure = new MsgFailure();
	}

	public void setMsgERR(MsgERR msgERR) {
    	this.msgERR = msgERR;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {
		TsERR err = null;
		errList = msgERR.getErrList(); 
		if (errList  != null)  {
			for (int i = 0; i < errList.size(); i++) {
				err = errList.get(i);
				if (i == 0)  {  
					setHeadLocalAppResult(err);

				}
				// CK 24.04.2012
				// Falls die Bezugsnummer in dem ersten TS ERR leer war 
				// (bei H: Textfeld wurde auf 4 Zeichen gekürzt) wird sie aus nachfolgenden Sätzen genommen
				if (msgFailure.getReferenceNumber() == null || msgFailure.getReferenceNumber().trim().equals("")) {
					msgFailure.setReferenceNumber(err.getBeznr());
				}
				msgFailure.addErrorList(setItemsLocalAppResult(err));
			}
		}
    }
	
    private PositionError setItemsLocalAppResult(TsERR err) {
    	PositionError error = new PositionError();
    	
    	error.setErrorNumber(err.getPosnr());
    	error.setKindOfError(err.getFehart());
    	error.setOriginOfError(err.getFehent());
    	error.setModul(err.getObjart());
    	error.setErrorCode(err.getFehcd());
    	error.setErrorText(err.getFehbes());
    	error.setDateOfErrorMessage(err.getGendat());
    	error.setTimeOfErrorMessage(err.getGenzei());

    	return error;
    }
	
	private void setHeadLocalAppResult(TsERR err) {
		msgFailure.setUCRNumber(err.getRegnr());
		msgFailure.setReferenceNumber(err.getBeznr());
		msgFailure.setCorrectionNumber(err.getKorant());
		msgFailure.setTemporaryUCR(err.getArbnr());
		msgFailure.setFileName(err.getDatei());
		msgFailure.setProcedureType(msgERR.getVorSubset().getNatyp());
	}
	
	
	
	public String getMessage() {
	    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(osw);
    
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            KidsHeader             header = new KidsHeader(writer);
            header.setHeaderFields(msgERR.getVorSubset());
            header.setMessageName("localAppResult");
            header.setMessageID(getMsgID());
            CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
            header.writeHeader();
            
            BodyLocalAppResultKids body  = new BodyLocalAppResultKids(writer);
            body.setMessage(msgFailure);            
            body.setKidsHeader(header);

            getCommonFieldsDTO().setReferenceNumber(msgFailure.getReferenceNumber());
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
      
        String xml = bos.toString();
        Utils.log("(MapERRToLocalAppResult getMessage) xml = \n" + xml.replaceAll("><", ">\r\n  <"));
        return xml;
    }

	public String getUidsName() {
		return uidsName;
	}

	public void setUidsName(String uidsName) {
		this.uidsName = uidsName;
	}

}
