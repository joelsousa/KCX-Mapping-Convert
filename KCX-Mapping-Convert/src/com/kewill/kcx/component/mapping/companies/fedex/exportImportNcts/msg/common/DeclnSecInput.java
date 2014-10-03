package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module : FEDEX-Import.<br>
 * Created : 29.10.2013<br>
 * Description : Common class for MsgDeclnInput: Header/Body tags of Declaration
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class DeclnSecInput extends KCXMessage {

	private String oidNbr;
	private String subtypeCd;
	private String routeDt;
	private String declnRouteArrTmstp;
	private String typeCd;
	private String routeNbr;
	private String empNm;
	private String empId;
	private String countryCd;
	private String systemMessageReferenceNbr; // Export
	private String entryTmstp; // Export
	private String decltRep; // Export
	private String declnUcr; // ?
	private String locationCd;

	public DeclnSecInput() {
		super();
	}

	public DeclnSecInput(XMLEventReader parser) {
		super(parser);
	}

	public DeclnSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EDeclnSecInput {
		DECLN_OID_NBR, // Import: Header/Header/MessageID: 58700758
		SYSTEM_MESSAGE_REFERENCE_NBR, // Export: Header/Header/MessageID:
										// 58700758
		DECLN_STATUS_CD, DECLN_IN_OUT, DECLN_SUBTYPE_CD, // Header/Header/MessageTP/ProcedureSpecification

		DECLN_ROUTE_DT, // Header/Header/SentAt 27/11/2012
		DECLN_ROUTE_ARR_TMSTP, // DECLN_ROUTE_DT 27/11/2012 10:20:30
		DECLN_TYP_CD, // Header/Header/MessageTP/Procedure: IMx, EXx
		COUNTRY_CD, // map?: Header.CountryCode
		EMP_NM, // Export: ContactPerson.Name
		EMP_ID, // Export: ContactPerson.Id
		DECLN_UCR, // ?
		ROUTE_NBR, // ImportDeclaration/MeansOfTransportBorder/TransportationNumber:
					// AU8019, auch in Export vorhanden map?
		ENTRY_TMSTP, // Export/Import: DeclarationTime
		DECLT_REP, // Export: TypeOfRepresentation
		DECLN_ROUTE_ORIG_CNTRY_CD, // Export: map?
		LOCATION_CD, // Export: map?
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {

			switch ((EDeclnSecInput) tag) {

			default:
				return;
			}
		} else {
			switch ((EDeclnSecInput) tag) {
			case DECLN_OID_NBR:
				setDeclnOidNbr(value);
				break;
			case DECLN_STATUS_CD:
				break;
			case DECLN_IN_OUT:
				break;
			case DECLN_SUBTYPE_CD:
				setDeclnSubtypeCd(value);
				break;
			case DECLN_ROUTE_DT:
				setDeclnRouteDt(value);
				break;
			case DECLN_ROUTE_ARR_TMSTP:
				setDeclnRouteArrTmstp(value);
				break;
			case DECLN_TYP_CD:
				setDeclnTypeCd(value);
				break;
			case COUNTRY_CD:
				setCountryCd(value);
				break;

			case EMP_NM:
				setEmpNm(value);
				break;
			case EMP_ID:
				setEmpId(value);
				break;

			case ROUTE_NBR:
				setRouteNbr(value);
				break;
			case DECLT_REP:
				setDecltRep(value);
				break;
			case SYSTEM_MESSAGE_REFERENCE_NBR:
				setSystemMessageReferenceNbr(value);
				break;
			case ENTRY_TMSTP:
				setEntryTmstp(value);
				break;
			case DECLN_UCR:
				setDeclnUcr(value);
				break;
			case LOCATION_CD:
				setLocationCd(value);
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
			return EDeclnSecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getDeclnOidNbr() {
		return oidNbr;
	}

	public void setDeclnOidNbr(String value) {
		this.oidNbr = Utils.checkNull(value);
	}

	public String getDeclnSubtypeCd() {
		return subtypeCd;
	}

	public void setDeclnSubtypeCd(String subtypeCd) {
		this.subtypeCd = subtypeCd;
	}

	public String getDeclnRouteDt() {
		return routeDt;
	}

	public void setDeclnRouteDt(String routeDt) {
		this.routeDt = routeDt;
	}

	public String getDeclnTypeCd() {
		return typeCd;
	}

	public void setDeclnTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getEmpNm() {
		return empNm;
	}

	public void setEmpNm(String emmpNm) {
		this.empNm = emmpNm;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getRouteNbr() {
		return routeNbr;
	}

	public void setRouteNbr(String routeNbr) {
		this.routeNbr = routeNbr;
	}

	public String getSystemMessageReferenceNbr() {
		return systemMessageReferenceNbr;
	}

	public void setSystemMessageReferenceNbr(String value) {
		this.systemMessageReferenceNbr = value;
	}

	public String getEntryTmstp() {
		return entryTmstp;
	}

	public void setEntryTmstp(String value) {
		this.entryTmstp = value;
	}

	public String getDecltRep() {
		return decltRep;
	}

	public void setDecltRep(String value) {
		this.decltRep = value;
	}

	public String getDeclnUcr() {
		return declnUcr;
	}

	public void setDeclnUcr(String declnUcr) {
		this.declnUcr = declnUcr;
	}

	private boolean isDateTime(String routeDt) {
		if (Utils.isStringEmpty(routeDt)) {
			return false;
		}
		if (routeDt.length() == 19 && routeDt.contains("/")
				&& routeDt.contains(":")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean mapSentAt(KidsHeader kidsHeader, String routeDt) {
		// Format von sentAt: 27/11/2012 10:00:00

		if (routeDt == null) {
			Calendar calendar = Calendar.getInstance();

			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			kidsHeader.setDay(day.toString());
			Integer month = calendar.get(Calendar.MONTH) + 1 ;
			kidsHeader.setMonth(month.toString());
			Integer year = calendar.get(Calendar.YEAR);
			kidsHeader.setYear( year.toString());
			
			String auxTime = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
			kidsHeader.setTime(auxTime);
			return true;
		}
		
		if (!routeDt.contains("/") && !routeDt.contains(":")) {
			return false;
		}
		int len = routeDt.length();
		if (len > 9 && routeDt.substring(2, 3).equals("/")
				&& routeDt.substring(5, 6).equals("/")) {
			kidsHeader.setDay(routeDt.substring(0, 2));
			kidsHeader.setMonth(routeDt.substring(3, 5));
			kidsHeader.setYear(routeDt.substring(6, 10));
		}
		if (len == 19 && routeDt.substring(13, 14).equals(":")
				&& routeDt.substring(16, 17).equals(":")) {
			kidsHeader.setTime(routeDt.substring(11, 19));
			kidsHeader.setTimeZone(routeDt.substring(10, 11));
		}
		return true;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(oidNbr) && Utils.isStringEmpty(typeCd)
				&& Utils.isStringEmpty(subtypeCd)
				&& Utils.isStringEmpty(routeDt) && Utils
					.isStringEmpty(routeNbr));
	}

	public String getDeclnRouteArrTmstp() {
		return declnRouteArrTmstp;
	}

	public void setDeclnRouteArrTmstp(String declnRouteArrTmstp) {
		this.declnRouteArrTmstp = declnRouteArrTmstp;
	}

	public String getLocationCd() {
		return locationCd;
	}

	public void setLocationCd(String locationCd) {
		this.locationCd = locationCd;
	}
}
