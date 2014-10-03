package com.kewill.kcx.component.mapping.countries.mt.ics.msg;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustFltInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Cusoffentactoff;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgCC348A
 * Created		: 21.08.2013
 * Description	: ICSArrivalNotificationValidation message.
 * 
 * @author	krzoska
 * @version	1.0.00
 *
 */

public class MsgCC348A extends KCXMessage {
	private HashMap<String, String> enumMap = null;
	private HashMap<String, String> enumBack = null;
	private String corIdeMES25;
	private ArrivalOperation arrivalOperation;

	private Funerrer1 fcnError;
	private ArrayList <Funerrer1>	fcnErrorList;
	private CyprusAddress tracarent;
	private Cusoffentactoff cusoffentactoff700;
	
	public MsgCC348A() {
		super();
		initEnumMap(); 
	}
	
	public MsgCC348A(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		initEnumMap();
		initEnumBack();
	}
	
	public MsgCC348A(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap();
		initEnumBack();
	}
	
	private void initEnumMap() {
	    enumMap = new HashMap<String, String>();
	    enumMap.put("arrival-operation", "ArrivalOperation");
	    enumMap.put("FUNERRER1", "FUNERRER1");
	    enumMap.put("TRACARENT601", "TRACARENT601"); 
	    enumMap.put("CUSOFFENTACTOFF700", "CUSOFFENTACTOFF700"); 
	}
	
	private void initEnumBack() {
		enumBack = new HashMap<String, String>();
		enumBack.put("ArrivalOperation", "arrival-operation");
		enumBack.put("FUNERRER1", "FUNERRER1");
		enumBack.put("TRACARENT601", "TRACARENT601"); 
		enumBack.put("CUSOFFENTACTOFF700", "CUSOFFENTACTOFF700"); 
	}
	
	private enum ECC348A {
		//MT
		ArrivalOperation,
		CorIdeMES25,
		FUNERRER1,
		TRACARENT601,
		CUSOFFENTACTOFF700;
	}
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		String text;
		if (value == null) {

			switch((ECC348A) tag) {
				case FUNERRER1:	
					fcnError = new Funerrer1(getScanner());
					text = enumBack.get(tag.name());
					fcnError.parse(text);
					addFunErrerList(fcnError);
					break;
				case ArrivalOperation:
					arrivalOperation = new ArrivalOperation(getScanner());
					text = enumBack.get(tag.name());
					arrivalOperation.parse(text);
					break;
					
				case TRACARENT601:
					tracarent	= new CyprusAddress(getScanner());
					text = enumBack.get(tag.name());
					tracarent.parse(text);
					break;
					
				case CUSOFFENTACTOFF700:
					cusoffentactoff700 = new Cusoffentactoff(getScanner());
					text = enumBack.get(tag.name());
					cusoffentactoff700.parse(text);
					break;
					
				default:
					return;
			}
		} else {
			switch((ECC348A) tag) {
				case CorIdeMES25:
					setCorIdeMES25(value);
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
		String text = enumMap.get(token);
		if (text != null) {
			token = text;
		}

		try {
			return ECC348A.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public Funerrer1 getFcnError() {
		return fcnError;
	}

	public void addFunErrerList(Funerrer1 error) {   //EI20110706
		if (fcnErrorList == null) {
			fcnErrorList = new ArrayList<Funerrer1>();
		}
		fcnErrorList.add(error);
	}
	
	public void setCorIdeMES25(String value) {
		corIdeMES25 = value;
	}
	public String getCorIdeMES25() {
		return corIdeMES25;
	}

	public ArrivalOperation getArrivalOperation() {
		return arrivalOperation;
	}

	public void setArrivalOperation(ArrivalOperation arrivalOperation) {
		this.arrivalOperation = arrivalOperation;
	}

	public ArrayList<Funerrer1> getFcnErrorList() {
		return fcnErrorList;
	}

	public void setFcnErrorList(ArrayList<Funerrer1> fcnErrorList) {
		this.fcnErrorList = fcnErrorList;
	}

	public CyprusAddress getTracarent() {
		return tracarent;
	}

	public void setTracarent(CyprusAddress tracarent) {
		this.tracarent = tracarent;
	}

	public Cusoffentactoff getCusoffentactoff700() {
		return cusoffentactoff700;
	}

	public void setCusoffentactoff700(Cusoffentactoff cusoffentactoff700) {
		this.cusoffentactoff700 = cusoffentactoff700;
	}
}