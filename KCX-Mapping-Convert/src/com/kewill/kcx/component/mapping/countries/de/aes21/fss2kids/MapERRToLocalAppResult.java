package com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids;

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
 * Module		: Export/AES21<br>
 * Created		: 05.10.2012<br>
 * Description	: Mapping of FSS-Format ERR into KIDS-Format of LocalAppResult..
 *               : New in AES21:  Header MessageID and InReplyTo will be filled from TsVOR (and TsVOR from TsHEAD)
 *               
 * @author iwaniuk
 * @version 2.1.00
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
		//EI20140403:msgFailure.setProcedureType(msgERR.getVorSubset().getNatyp());
		msgFailure.setProcedureType("ERR"); //so war es bisher, als es noch kein HEAD gab,
											//jetzt steht in HEAD.natyo der name der urspruenglicher Nachricht
											//also nicht ERR sondern ex. SUK, 
											//keine Ahnung was besser ist bzw. was der Kunde erwartet
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
            
            KidsHeader header = new KidsHeader(writer);
            //header.setHeaderFields(msgERR.getVorSubset());
            header.setHeaderFieldsFromHead(msgERR.getVorSubset(), msgERR.getHeadSubset());     //EI20121005
            header.setMessageName("localAppResult");
            
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
