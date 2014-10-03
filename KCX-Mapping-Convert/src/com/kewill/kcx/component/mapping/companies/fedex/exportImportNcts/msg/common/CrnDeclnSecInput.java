package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: FEDEX-Import.<br>
 * Created		: 15.07.2014<br>
 * Description	: Common class for MsgDeclnInput: Body tags of Declaration
 * 
 * @author joel
 * @version 1.0.00
 */
public class CrnDeclnSecInput extends KCXMessage {
	private String dummy;
	private String crn_nbr;
	private String dest_location_cd;
	private String arrival_tmstp;

	public CrnDeclnSecInput() {
		super();
	}

	public CrnDeclnSecInput(XMLEventReader parser) {
		super(parser);
	}

	public CrnDeclnSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ECrnDeclnSecInput {
		CRN_NBR, DEST_LOCATION_CD, ARRIVAL_TMSTP;
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {

			switch ((ECrnDeclnSecInput) tag) {

			default:
				return;
			}
		} else {
			switch ((ECrnDeclnSecInput) tag) {
			case CRN_NBR:
				setCrn_nbr(value);
				break;
			case DEST_LOCATION_CD:
				setDest_location_cd(value);
				break;
			case ARRIVAL_TMSTP:
				setArrival_tmstp(value);
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
			return  ECrnDeclnSecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCrn_nbr() {
		return crn_nbr;
	}

	public void setCrn_nbr(String crn_nbr) {
		this.crn_nbr = crn_nbr;
	}

	public String getDest_location_cd() {
		return dest_location_cd;
	}

	public void setDest_location_cd(String dest_location_cd) {
		this.dest_location_cd = dest_location_cd;
	}

	public String getArrival_tmstp() {
		return arrival_tmstp;
	}

	public void setArrival_tmstp(String arrival_tmstp) {
		this.arrival_tmstp = arrival_tmstp;
	}


	public boolean isEmpty() {
		return 	(Utils.isStringEmpty(dummy) &&
				Utils.isStringEmpty(dummy)); 
	}
}
