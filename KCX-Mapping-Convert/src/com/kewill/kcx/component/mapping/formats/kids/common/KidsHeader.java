/*
 * Funktion    : KidsHeader.java
 * Titel       :
 * Erstellt    : 11.08.2008
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      : Sven Heise
 * Datum       : 12.09.2008
 * Kennzeichen : SH080912
 * Beschreibung: SendAt-Datum komplett ausgeben
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.formats.kids.common;

import java.text.ParseException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.CustomerHeadersDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul : KidsHeader<br>
 * Erstellt : 17.11.2008<br>
 * Beschreibung : Structure and methods of the KIDS message header.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsHeader extends KidsMessage {

	private String year = null;
	private String month = null;
	private String day = null;
	private String time = null; // HH:MM:SS EI20140317: eigentlich in xsd als
								// hhmmss definiert
	private String unformatedTime = null; // EI20140317
	private String timeZone = null; // +/-HHMM
	private String transmitter = null;
	private String receiver = null;
	private String method = null;
	private String countryCode = null;
	private String procedure = null;
	private String procedureSpecification = null;
	private String messageName = null;
	private String direction = null;
	private String release = null;
	private String map = null;
	private String mapFrom = null;
	private String mapTo = null;
	private String autoTransmission = null; // CK20120228
	private String validationCheck = null; // CK20120228
	private String testIndicator = null; // CK20120228 Werte: T = Test,
											// leer/keinTag = LIVE

	private String messageID = null;
	private String inReplyTo = null;
	private String customerNumber = null; // EI20130419
	private String justCode = null; // EI20130606

	private XMLEventReader parser = null;

	public KidsHeader() {
	}

	public KidsHeader(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public void setWriter(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public void setHeaderFields() throws Exception {

		EndElement endElement = null;
		Characters characters = null;
		StartElement startElement = null;
		QName qname = null;
		String text = null;

		int type = -1; // heute
		boolean b = false; // heute
		String value = ""; // heute

		XMLEvent event = null;
		parserloop: while (parser.hasNext()) {
			event = parser.nextEvent();
			// Utils.log("(KidsHeader setHeaderFields) event = " + event);
			type = event.getEventType(); // heute
			switch (event.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				startElement = event.asStartElement();
				qname = startElement.getName();
				text = qname.getLocalPart();
				// Utils.log("(KidsHeader setHeaderFields) START_ELEMENT: " +
				// text);
				switch (EKidsHeaderTags.valueOf(text)) {
				case Year:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.year = characters.getData();
					break;
				case Month:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.month = characters.getData();
					break;
				case Day:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.day = characters.getData();
					break;
				case Time:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.time = characters.getData();
					break;
				case TimeZone:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.timeZone = characters.getData();
					break;
				case Transmitter:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.transmitter = characters.getData();
					break;
				case Receiver:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.receiver = characters.getData();
					break;
				case Method:
					// event = parser.nextEvent();
					// characters = event.asCharacters();
					// this.method = characters.getData();
					this.method = getValue(); // EI20130711
					break;
				case CountryCode:
					event = parser.nextEvent();
					characters = event.asCharacters();
					// this.countryCode = characters.getData(); // MS20101207
					this.countryCode = characters.getData().toUpperCase(); // MS20101207
					break;
				case Procedure:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.procedure = characters.getData();
					break;
				case ProcedureSpecification:
					this.procedureSpecification = getValue();
					break;
				case MessageName:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.messageName = characters.getData();
					break;
				case Direction:
					this.direction = getValue();
					break;
				case Release:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.release = characters.getData();
					break;
				case Map:
					/*
					 * EI20130711: generell müsste man die getValue() hier
					 * nutzen, bei mussFelder ist die exception OK, bei allen
					 * optionalen Tags sollte keine exception ausgelöst werden
					 * event = parser.nextEvent(); characters =
					 * event.asCharacters(); this.map = characters.getData();
					 */
					this.map = getValue(); // EI20130711
					break;

				case MapFrom:
					if (!Utils.isStringEmpty(this.map) && this.map.equals("1")) {
						event = parser.nextEvent();
						characters = event.asCharacters();
						this.mapFrom = characters.getData();
					} else {
						this.mapFrom = getValue();
					}
					break;

				case MapTo:
					if (!Utils.isStringEmpty(this.map) && this.map.equals("1")) {
						event = parser.nextEvent();
						characters = event.asCharacters();
						this.mapTo = characters.getData();
					} else {
						this.mapTo = getValue();
					}
					break;

				case AutoTransmission: // CK20120228
					// event = parser.nextEvent();
					// characters = event.asCharacters();
					this.autoTransmission = getValue(); // EI20130711
					break;

				case ValidationCheck: // CK20120228
					// event = parser.nextEvent();
					// characters = event.asCharacters();
					this.validationCheck = getValue(); // EI20130711
					break;

				case TestIndicator: // CK20120228
					// event = parser.nextEvent();
					// characters = event.asCharacters();
					this.testIndicator = getValue(); // EI20130711
					break;

				case MessageID:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.messageID = characters.getData();
					break;

				case InReplyTo:
					event = parser.nextEvent();
					characters = event.asCharacters();
					this.inReplyTo = characters.getData();
					// this.inReplyTo = getValue();
					break;

				case CustomerNumber:
					this.customerNumber = getValue(); // EI20130711
					break;

				case JustCode:
					this.justCode = getValue(); // EI20130711
					break;

				default:
					break;

				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				endElement = event.asEndElement();
				// Utils.log("(KidsHeader setHeaderFields) LocalName = /" +
				// endElement.getName());
				// if EndTag of "Header" is found: break because
				// the Header is complete
				qname = endElement.getName();
				// Utils.log("(KidsHeader setHeaderFields qname : " + qname);

				text = qname.getLocalPart();
				// Utils.log("(KidsHeader setHeaderFields) END_ELEMENT: " +
				// text);
				if (text.equals("Header")) {
					break parserloop;
				}
			default:
				break;
			} // end switch
		} // end while
	}

	public void setMapping(EDirections direction) {
		// C. Kron
		// Fehlen die Angaben zum Encoding wird folgendermaßen kodiert (analog
		// UidsToKids)
		// Wenn Nachricht von Kunde Richtung Zoll, dann ist mapFrom =
		// mappingCode aus der DB
		// und mapTo = countryCode.
		// Wenn Nachricht von Zoll Richtung Kunde, dann ist mapFrom =
		// countryCode
		// und mapto = mappingCode aus der DB

		// ******************************************************************************************
		// mapping code "EU" in db table customer_procedures used with ICS means
		// "no mapping" because
		// codes are valid in all countries of EU
		// ******************************************************************************************

		if (map == null) {

			CustomerProcedureDTO customerProcedureDTO = Utils
					.getCustomerProceduresFromKcxId(this.getReceiver(), this
							.getProcedure().toUpperCase());
			// CustomerProcedureDTO customerProcedureDTO =
			// Utils.getCustomerProceduresFromKcxId(receiver,
			// procedure.toUpperCase());
			String mappingCode = customerProcedureDTO.getMappingCode();

			// C.K. 10.02.2012 check if null for
			if (mappingCode == null || this.getCountryCode() == null) {
				return;
			}

			Utils.log("(KidsHeader setMapping) mappingCode                 = "
					+ mappingCode);
			Utils.log("(KidsHeader setMapping) kidsHeader.getCountryCode() = "
					+ countryCode);
			if (mappingCode.trim().equalsIgnoreCase("EU")) {
				this.setMap("0");
			} else {
				if (direction == EDirections.CustomerToCountry) {
					this.setMapFrom(mappingCode);
					this.setMapTo(this.getCountryCode());
				} else {
					this.setMapFrom(this.getCountryCode());
					this.setMapTo(mappingCode);
				}

				Utils.log("(KidsHeader setMapping) kidsHeader.getMapFrom() = "
						+ this.getMapFrom());
				Utils.log("(KidsHeader setMapping) kidsHeader.getMapTo()   = "
						+ this.getMapTo());
				if (this.getMapFrom().trim()
						.equalsIgnoreCase(this.getMapTo().trim())) {
					this.setMap("0");
				} else {
					this.setMap("1");
				}
			}

		} else if (!map.equals("0")) { // EI20110513
			if (mapFrom != null) { // EI20110513
				// Sind in den Encoding-Angaben "mapFrom" und "mapTo" gleich
				// d.h. das Sender- und Empfängerland identisch,
				// wird kein Mapping durchgeführt und die Angabe im Tag "Map"
				// entsprechend
				// auf "0" gesetzt!
				if (mapFrom.equalsIgnoreCase(mapTo)) {
					map = "0";
				}
			}
		}
	}

	private String getValue() throws Exception {
		XMLEvent event = parser.nextEvent();
		// Utils.log("(KidsHeader getValue) nextEvent = " + event);
		if (event.isCharacters()) {
			// String chars = event.asCharacters().getData();
			// Utils.log("(KidsHeader getValue) characters = " + chars);
			return event.asCharacters().getData();
		} else {
			return "";
		}
	}

	public void setHeaderFields(TsVOR tsVor) {

		year = tsVor.getGendat().substring(0, 4);
		month = tsVor.getGendat().substring(4, 6);
		day = tsVor.getGendat().substring(6, 8);
		String hh, mm, ss = null;
		hh = tsVor.getGenzei().substring(0, 2);
		mm = tsVor.getGenzei().substring(2, 4);
		ss = tsVor.getGenzei().substring(4, 6);
		time = hh + ":" + mm + ":" + ss;

		// CK 30.11.2011
		// timeZone = "+0200";
		timeZone = "+0" + Utils.getTimeZone(year + month + day + hh + mm + ss)
				+ "00";

		transmitter = tsVor.getCountry();
		receiver = tsVor.getKewillId();
		countryCode = tsVor.getCountry();

		// 20100728 aus VOR besetzen
		this.setProcedureFromModule(tsVor.getModul()); // EI20121129 new method
		/*
		 * EI20121129 moved in the new method if
		 * (tsVor.getModul().trim().equalsIgnoreCase("EX")) { procedure =
		 * "EXPORT"; } else if (tsVor.getModul().trim().equalsIgnoreCase("VE"))
		 * { procedure = "NCTS"; } else if
		 * (tsVor.getModul().trim().equalsIgnoreCase("ZB")) { //EI20110923
		 * procedure = "IMPORT"; } else if
		 * (tsVor.getModul().trim().equalsIgnoreCase("ZP")) { //EI20120120
		 * procedure = "PORT"; } else if
		 * (tsVor.getModul().trim().equalsIgnoreCase("SU")) { //CK20121112
		 * procedure = "MANIFEST"; } else { procedure = null; }
		 */

		// wird im aufrufenden MapFSSToKids gesetzt!
		// C.K. 26.11.2008 MessageName = "ExportConfirmation";

		// 20100618MS
		if (tsVor.getRicht().equalsIgnoreCase("A")) {
			direction = "TO_CUSTOMER";
		} else {
			direction = "FROM_CUSTOMER";
		}

		// CK090428 Kids Release in Abh. der FSS-Version setzen
		// Export
		// Uids 1.0 entspricht Kids 1.0.00 entspricht Zabis 5.3
		// Uids 2.0 entspricht Kids 2.0.00 entspricht Zabis 6.0
		// NCTS
		// Uids 4.0 entspricht Kids 4.0.00 enstpricht Zabis 5-6
		Utils.log("(KidsHeader setHeaderFields) tsVor.getVersnr() = "
				+ tsVor.getVersnr());

		this.setReleaseFromVersion(tsVor.getVersnr()); // EI20121129 new method
		/*
		 * EI20121129 moved in the new method if (tsVor.getVersnr() == null) {
		 * release = "1.0.00"; } else if (procedure.equals("EXPORT")) { if
		 * (tsVor.getVersnr().substring(0, 2).equals("06")) { release =
		 * "2.0.00"; } else if (tsVor.getVersnr().substring(0, 2).equals("07"))
		 * { release = "2.1.00"; } else { release = "1.0.00"; } } else if
		 * (procedure.equals("NCTS")) { //release = "4.0.00"; if
		 * (tsVor.getVersnr().substring(0, 2).equals("06")) { release =
		 * "4.0.00"; } else { release = "4.2.00"; } } else if
		 * (procedure.equals("IMPORT")) { //EI20110923 if
		 * (tsVor.getVersnr().substring(0, 2).equals("06")) { release =
		 * "1.0.00"; } else { release = "2.0.00"; } } else if
		 * (procedure.equals("PORT")) { //EI20121123 if
		 * (tsVor.getVersnr().substring(0, 2).equals("06")) { release =
		 * "1.0.00"; } else { release = "2.0.00"; } } else if
		 * (procedure.equals("MANIFEST")) { // CK 20121120 release = "1.0.00"; }
		 * else if (tsVor.getVersnr().substring(0, 2).equals("06")) { release =
		 * "2.0.00"; } else if (tsVor.getVersnr().substring(0, 2).equals("07"))
		 * { //EI20120803 release = "2.1.00"; } else { release = "1.0.00"; }
		 */

		// Muss abhängig von der Nachricht (DE/CH) gesetzt werden.
		// countryCode wird aus tsVor.country genommen.
		// tsVor.country wird in fssToKids abhängig vom Nachrichtentyp besetzt.
		mapFrom = countryCode;

		// Muss abhängig von Kunde (KcxId) und Verfahren (EXPORT, ...) auf den
		// Wert von
		// MAP_TO aus Tabelle Customer_Procedures gesetzt werden.

		CustomerProcedureDTO customerProcedureDTO = Utils
				.getCustomerProceduresFromKcxId(receiver, procedure);
		mapTo = customerProcedureDTO.getMappingCode();

		// Muss abhängig von der Nachricht (DE/CH) und dem Zielcode
		// (Feld MAP_TO in Tabelle Customer_Procedures) gesetzt werden.
		// Wenn Ein- und Ausgangsländercode gleich sind, dann "0" sonst "1"
		if (mapFrom.equalsIgnoreCase(mapTo)) {
			map = "0";
		} else {
			map = "1";
		}
		// CK090505
		// this.inReplyTo = tsVor.getInReplyToMsgID();
		if (direction.equals("TO_CUSTOMER")) { // EI2013: diese methode wird NUR
												// richtung TO_CUSTOMER
												// verwendet!
			this.inReplyTo = tsVor.getMsgid();
		} else {
			this.messageID = tsVor.getMsgid();
		}

		if (this.procedure.equals("NCTS")) {
			this.method = this.procedure;
		}
	}

	public void setHeaderFieldsNEU(TsVOR tsVor) {
		year = tsVor.getGendat().substring(0, 4);
		month = tsVor.getGendat().substring(4, 6);
		day = tsVor.getGendat().substring(6, 8);
		String hh, mm, ss = null;
		hh = tsVor.getGenzei().substring(0, 2);
		mm = tsVor.getGenzei().substring(2, 4);
		ss = tsVor.getGenzei().substring(4, 6);
		time = hh + ":" + mm + ":" + ss;
		unformatedTime = hh + mm + ss; // EI20140307

		timeZone = "+0" + Utils.getTimeZone(year + month + day + hh + mm + ss)
				+ "00";

		transmitter = tsVor.getCountry();
		receiver = tsVor.getKewillId();
		countryCode = tsVor.getCountry();

		this.setProcedureFromModule(tsVor.getModul()); // EI20121129 new method

		if (tsVor.getRicht().equalsIgnoreCase("A")) {
			direction = "TO_CUSTOMER";
		} else {
			direction = "FROM_CUSTOMER";
		}

		Utils.log("(KidsHeader setHeaderFieldsNEU) tsVor.getVersnr() = "
				+ tsVor.getVersnr());

		this.setReleaseFromVersion(tsVor.getVersnr()); // EI20121129 new method

		mapFrom = countryCode;

		CustomerProcedureDTO customerProcedureDTO = Utils
				.getCustomerProceduresFromKcxId(receiver, procedure);
		mapTo = customerProcedureDTO.getMappingCode();
		if (mapFrom.equalsIgnoreCase(mapTo)) {
			map = "0";
		} else {
			map = "1";
		}

		if (this.procedure.equals("NCTS")) {
			this.method = this.procedure;
		}
		this.messageID = tsVor.getMsgid();
		this.inReplyTo = tsVor.getInReplyTo();
	}

	private void setProcedureFromModule(String module) {
		if (Utils.isStringEmpty(module)) {
			procedure = null;
		} else if (module.trim().equalsIgnoreCase("EX")) {
			procedure = "EXPORT";
		} else if (module.trim().equalsIgnoreCase("VE")) {
			procedure = "NCTS";
		} else if (module.trim().equalsIgnoreCase("ZB")) { // EI20110923
			procedure = "IMPORT";
		} else if (module.trim().equalsIgnoreCase("ZP")) { // EI20120120
			procedure = "PORT";
		} else if (module.trim().equalsIgnoreCase("SU")) { // CK20121112
			procedure = "MANIFEST";
		} else {
			procedure = null;
		}
	}

	private void setReleaseFromVersion(String version) {
		if (version == null) {
			release = "1.0.00";
		} else if (procedure.equals("EXPORT")) {
			if (version.substring(0, 2).equals("06")) {
				release = "2.0.00";
			} else if (version.substring(0, 2).equals("07")) {
				release = "2.1.00";
			} else {
				release = "1.0.00";
			}
		} else if (procedure.equals("NCTS")) {
			// EI20130204: release = "4.0.00";
			if (version.substring(0, 2).equals("07")) {
				release = "4.1.00"; // EI20130204
			} else {
				release = "4.0.00";
			}
		} else if (procedure.equals("IMPORT")) { // EI20110923
			if (version.substring(0, 2).equals("07")) {
				release = "2.0.00";
			} else {
				release = "1.0.00";
			}
		} else if (procedure.equals("PORT")) { // EI20121123
			if (version.substring(0, 2).equals("07")) {
				release = "2.0.00";
			} else {
				release = "1.0.00";
			}
		} else if (procedure.equals("MANIFEST")) { // CK 20121120
			// release = "1.0.00";
			if (version.substring(0, 2).equals("07")) {
				release = "2.0.00";
			} else {
				release = "1.0.00";
			}

		} else if (version.substring(0, 2).equals("06")) {
			release = "2.0.00";
		} else if (version.substring(0, 2).equals("07")) { // EI20120803
			release = "2.0.00";
		} else {
			release = "1.0.00";
		}
	}

	// public void setHeaderFieldsFromHead(TsVOR tsVor) { //EI20121128: ab V70
	// wird TsVOR mit TsHead ersetzt
	public void setHeaderFieldsFromHead(TsVOR tsVor, TsHead tsHead) { // EI201210105:

		if (tsVor != null) {
			/*
			 * setHeaderFields(tsVor); //EI20130110: hat gefehlt nach
			 * auskommentieren von dem unteren teil //bei V60 stand in Ts.msgid
			 * das, was in eigentlich inReplyTo ist - das wurde in
			 * setHeaderFields //umgedreht, ab V70 wird TsVor aus TsHead
			 * gefüllt, daher muss noch mal umgerdeht werden: this.messageID =
			 * tsVor.getMsgid(); this.inReplyTo = tsVor.getInReplyTo();
			 */
			setHeaderFieldsNEU(tsVor);
			return;
		}

		// EI20140128: hier kann Header direkt von TsHead belegt werden:
		// EI20140128: getestet: beide KidsHeader waren identisch (von TsVor und
		// von TsHead)
		if (tsHead != null) {
			year = tsHead.getDateTime().substring(0, 4);
			month = tsHead.getDateTime().substring(4, 6);
			day = tsHead.getDateTime().substring(6, 8);
			String hh, mm, ss = null;
			hh = tsHead.getDateTime().substring(8, 10);
			mm = tsHead.getDateTime().substring(10, 12);
			ss = tsHead.getDateTime().substring(12, 14);
			time = hh + ":" + mm + ":" + ss;

			timeZone = "+0"
					+ Utils.getTimeZone(year + month + day + hh + mm + ss)
					+ "00";

			transmitter = tsHead.getCountry();
			receiver = tsHead.getKewillId();
			countryCode = tsHead.getCountry();

			String richtung = tsHead.getRichtung();
			if (Utils.isStringEmpty(richtung) || richtung.contains("_KD")) {
				direction = "TO_CUSTOMER";
			} else {
				direction = "FROM_CUSTOMER";
			}

			this.setProcedureFromModule(tsHead.getModul());
			if (procedure.equals("NCTS")) {
				method = this.procedure;
			}
			this.setReleaseFromVersion(tsHead.getVersion());

			mapFrom = countryCode;
			CustomerProcedureDTO customerProcedureDTO = Utils
					.getCustomerProceduresFromKcxId(receiver, procedure);
			if (customerProcedureDTO != null) {
				mapTo = customerProcedureDTO.getMappingCode();
				if (mapFrom.equalsIgnoreCase(mapTo)) {
					map = "0";
				} else {
					map = "1";
				}
			} else {
				mapTo = "";
				map = "0";
			}

			messageID = tsHead.getMsgid();
			inReplyTo = tsHead.getInReplyTo();
		}

	}

	// 20100618MS
	// public void writeHeader(CommonFieldsDTO commonFieldsDTOPars) {
	public void writeHeader() {

		// Depending on the direction Header Version has to be defined
		// Reason: a customer can use older versions than the customs
		// application uses
		// if customerDTO empty or no entry in DB the old version 1.0 is used

		// KIDS V 1.1 with Direction
		// KIDS V 1.2 namespace not applicable
		// KIDS V 1.3 new fields ApplicationControl...
		// Header/CustomsExchange/ApplicationControl/AutoTransmission
		// Header/CustomsExchange/ApplicationControl/ValidationCheck
		// Header/CustomsExchange/ApplicationControl/TestIndicator

		CustomerHeadersDTO customerHeadersDTO = null;

		// CK 3.5.2012 Default Header von 1.0 auf 1.2 gesetzt!
		// ###################################################

		if (this.getCommonFieldsDTO() == null) {
			Utils.log("(KidsHeader writeHeader) commonFieldsDTO null => KIDS headerVersion = 1.2");
			writeHeaderV12();
		} else {
			if (this.getCommonFieldsDTO().getDirection() == EDirections.CustomerToCountry) {
				customerHeadersDTO = Db.getCustomerHeaders(countryCode,
						procedure);
			} else {
				customerHeadersDTO = Db.getCustomerHeaders(receiver, procedure);
			}
			if (customerHeadersDTO == null) {
				Utils.log("(KidsHeader writeHeader) customerHeadersDTO null => KIDS headerVersion = 1.2");
				if (this.getMethod() != null && this.getMethod().equals("CMP")) { // EI20140123:
					writeHeaderV13();
				} else {
					writeHeaderV12();
				}
			} else {
				String headerVersion = customerHeadersDTO.getKidsVersion();

				if (headerVersion.equals("1.3")) {
					writeHeaderV13();
				} else if (headerVersion.equals("1.2")) {
					writeHeaderV12();
				} else if (headerVersion.equals("1.1")) {
					writeHeaderV11();
				} else {
					writeHeaderV12();
				}
			}
		}
	}

	// 20100618MS
	// public void writeHeader() {
	public void writeHeaderV10() {
		Utils.log("(KidsHeader writeHeaderV10) writing KIDS header version 1.0");
		try {
			openElement("soap:Header");
			openElement("Header");
			setAttribute("xmlns", "http://www.eBiz.com/schemas/header/200310");
			setAttribute("soap:mustUnderstand", "true");
			openElement("SentAt");
			openElement("Date");
			writeElement("Year", year);
			writeElement("Month", month);
			writeElement("Day", day);
			closeElement(); // Date
			writeElement("Time", time);
			writeElement("TimeZone", timeZone);
			closeElement(); // SentAt
			writeElement("Transmitter", transmitter);
			writeElement("Receiver", receiver);
			writeElement("Method", method);
			openElement("MessageTP");
			writeElement("CountryCode", countryCode);
			writeElement("Procedure", procedure);
			writeElement("ProcedureSpecification", procedureSpecification);
			writeElement("MessageName", messageName);
			writeElement("Release", release);
			closeElement(); // MessageTP
			openElement("CustomsExchange");
			openElement("Codemapping");
			// writeElement("Map", map);
			// C.K. must be "0" because mapping is done in KCX before
			// KIDS message is written
			writeElement("Map", "0");
			writeElement("MapFrom", mapFrom);
			writeElement("MapTo", mapTo);
			closeElement(); // CodeMapping
			closeElement(); // CustomsExchange
			writeElement("MessageID", messageID);
			writeElement("InReplyTo", inReplyTo);
			closeElement(); // Header
			closeElement(); // soap:Header
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
	}

	// 20100618MS
	public void writeHeaderV11() {
		Utils.log("(KidsHeader writeHeaderV11) writing KIDS header version 1.1");
		try {
			openElement("soap:Header");
			openElement("Header");
			setAttribute("xmlns", "http://www.eBiz.com/schemas/header/200310");
			setAttribute("soap:mustUnderstand", "true");
			openElement("SentAt");
			openElement("Date");
			writeElement("Year", year);
			writeElement("Month", month);
			writeElement("Day", day);
			closeElement(); // Date
			writeElement("Time", time);
			writeElement("TimeZone", timeZone);
			closeElement(); // SentAt
			writeElement("Transmitter", transmitter);
			writeElement("Receiver", receiver);
			writeElement("Method", method);
			openElement("MessageTP");
			writeElement("CountryCode", countryCode);
			writeElement("Procedure", procedure);
			writeElement("ProcedureSpecification", procedureSpecification);
			writeElement("MessageName", messageName);
			Utils.log("(KidsHeader writeHeaderV11) direction = " + direction);
			// EI20100810: writeElement("Direction", direction);
			writeElement("Release", release);
			writeElement("Direction", direction); // EI20100810
			closeElement(); // MessageTP
			openElement("CustomsExchange");
			openElement("Codemapping");
			// writeElement("Map", map);
			// C.K. must be "0" because mapping is done in KCX before
			// KIDS message is written
			writeElement("Map", "0");
			writeElement("MapFrom", mapFrom);
			writeElement("MapTo", mapTo);
			closeElement(); // CodeMapping
			closeElement(); // CustomsExchange
			writeElement("MessageID", messageID);
			writeElement("InReplyTo", inReplyTo);
			closeElement(); // Header
			closeElement(); // soap:Header
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
	}

	// 20100721CK
	public void writeHeaderV12() {
		Utils.log("(KidsHeader writeHeaderV12) writing KIDS header version 1.2");
		boolean isBDP = this.isBDP(receiver); // EI20130527
		try {
			openElement("soap:Header");
			if (isBDP) { // EI20130527
				openElement("KIDSHeader");
				if (this.direction != null
						&& this.direction.equalsIgnoreCase("TO_CUSTOMER")) {
					this.release = "1.0.00";
				}
			} else {
				openElement("Header");
			}
			// Christine Kron 21.07.2010
			// n/a not applicable because NS is not valid and Minihouse had a
			// problem to read
			// the Header with this NS
			// setAttribute("xmlns",
			// "http://www.eBiz.com/schemas/header/200310");
			// setAttribute("soap:mustUnderstand", "true");
			openElement("SentAt");
			openElement("Date");
			writeElement("Year", year);
			writeElement("Month", month);
			writeElement("Day", day);
			closeElement(); // Date
			writeElement("Time", time);
			writeElement("TimeZone", timeZone);
			closeElement(); // SentAt
			writeElement("Transmitter", transmitter);
			writeElement("Receiver", receiver);
			writeElement("Method", method);
			openElement("MessageTP");
			writeElement("CountryCode", countryCode);
			writeElement("Procedure", procedure);
			writeElement("ProcedureSpecification", procedureSpecification);
			writeElement("MessageName", messageName);
			writeElement("Release", release);
			writeElement("JustCode", justCode); // EI20130606
			writeElement("Direction", direction); // EI20100810
			Utils.log("(KidsHeader writeHeaderV12) direction = " + direction);
			closeElement(); // MessageTP
			openElement("CustomsExchange");
			openElement("Codemapping");
			// writeElement("Map", map);
			// C.K. must be "0" because mapping is done in KCX before
			// KIDS message is written

			writeElement("Map", "0");
			writeElement("MapFrom", mapFrom);
			writeElement("MapTo", mapTo);
			closeElement(); // CodeMapping
			closeElement(); // CustomsExchange
			writeElement("MessageID", messageID);
			writeElement("InReplyTo", inReplyTo);
			writeElement("CustomerNumber", customerNumber); // EI20130419
			closeElement(); // Header
			closeElement(); // soap:Header
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
	}

	// 20120228CK
	// EI20140327: ab Header-Version 1.3 wird in writeHeader13 Time ohne ":"
	// geschrieben == unformatedTime
	// (eigentlich so, wie es in xsd definiert ist (auch für fruehere Versionen)
	public void writeHeaderV13() {
		Utils.log("(KidsHeader writeHeaderV13) writing KIDS header version 1.3");
		boolean isBDP = this.isBDP(receiver); // EI20130527
		try {
			openElement("soap:Header");
			if (isBDP) { // EI20130527
				openElement("KIDSHeader");
				if (this.direction != null
						&& this.direction.equalsIgnoreCase("TO_CUSTOMER")) {
					this.release = "1.0.00";
				}
			} else {
				openElement("Header");
			}
			openElement("SentAt");
			openElement("Date");
			writeElement("Year", year);
			writeElement("Month", month);
			writeElement("Day", day);
			closeElement(); // Date
			// EI20140317: writeElement("Time", time);
			writeElement("Time", unformatedTime); // EI20140317
			writeElement("TimeZone", timeZone);
			closeElement(); // SentAt
			writeElement("Transmitter", transmitter);
			writeElement("Receiver", receiver);
			writeElement("Method", method);
			openElement("MessageTP");
			writeElement("CountryCode", countryCode);
			writeElement("Procedure", procedure);
			writeElement("ProcedureSpecification", procedureSpecification);
			writeElement("MessageName", messageName);
			writeElement("Release", release);
			writeElement("JustCode", justCode); // EI20130606
			writeElement("Direction", direction); // EI20100810
			Utils.log("(KidsHeader writeHeaderV12) direction = " + direction);
			closeElement(); // MessageTP
			openElement("CustomsExchange");
			openElement("Codemapping");
			writeElement("Map", "0");
			writeElement("MapFrom", mapFrom);
			writeElement("MapTo", mapTo);
			closeElement(); // CodeMapping
			if (this.getAutoTransmission() != null
					|| this.getValidationCheck() != null
					|| this.getTestIndicator() != null) {
				openElement("ApplicationControl");
				writeElement("AutoTransmission", this.getAutoTransmission());
				writeElement("ValidationCheck", this.getValidationCheck());
				writeElement("TestIndicator", this.getTestIndicator());
				closeElement(); // ApplicationControl
			}
			closeElement(); // CustomsExchange
			writeElement("MessageID", messageID);
			writeElement("InReplyTo", inReplyTo);
			writeElement("CustomerNumber", customerNumber); // EI20130419
			closeElement(); // Header
			closeElement(); // soap:Header
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
	}

	public void writeHeader13WithNewlines() {
		Utils.log("(KidsHeader writeHeaderWithNewlines) writing KIDS header version 1.2 mit \n");
		boolean isBDP = this.isBDP(receiver); // EI20130527
		try {
			openElement("soap:Header");
			writer.writeCharacters("\n");
			if (isBDP) { // EI20130527
				openElement("KIDSHeader");
				if (this.direction != null
						&& this.direction.equalsIgnoreCase("TO_CUSTOMER")) {
					this.release = "1.0.00";
				}
			} else {
				openElement("Header");
				writer.writeCharacters("\n");
			}
			// Christine Kron 21.07.2010
			// n/a not applicable because NS is not valid and Minihouse had a
			// problem to read
			// the Header with this NS
			// setAttribute("xmlns",
			// "http://www.eBiz.com/schemas/header/200310");
			// setAttribute("soap:mustUnderstand", "true");
			openElement("SentAt");
			writer.writeCharacters("\n");
			openElement("Date");
			writer.writeCharacters("\n");
			writeElementWithNewline("Year", year);
			writeElementWithNewline("Month", month);
			writeElementWithNewline("Day", day);
			closeElement(); // Date
			writer.writeCharacters("\n");
			writeElementWithNewline("Time", time);
			writeElementWithNewline("TimeZone", timeZone);
			closeElement(); // SentAt
			writer.writeCharacters("\n");
			writeElementWithNewline("Transmitter", transmitter);
			writeElementWithNewline("Receiver", receiver);
			writeElementWithNewline("Method", method);
			openElement("MessageTP");
			writer.writeCharacters("\n");
			writeElementWithNewline("CountryCode", countryCode);
			writeElementWithNewline("Procedure", procedure);
			writeElementWithNewline("ProcedureSpecification",
					procedureSpecification);
			writeElementWithNewline("MessageName", messageName);
			writeElementWithNewline("Release", release);
			writeElementWithNewline("JustCode", justCode); // EI20130606
			writeElementWithNewline("Direction", direction); // EI20100810
			closeElement(); // MessageTP
			writer.writeCharacters("\n");
			openElement("CustomsExchange");
			writer.writeCharacters("\n");
			openElement("Codemapping");
			writeElementWithNewline("Map", "0");
			writeElementWithNewline("MapFrom", mapFrom);
			writeElementWithNewline("MapTo", mapTo);
			closeElement(); // CodeMapping
			writer.writeCharacters("\n");
			if (this.getAutoTransmission() != null
					|| this.getValidationCheck() != null
					|| this.getTestIndicator() != null) {
				openElement("ApplicationControl");
				writer.writeCharacters(Utils.LF);
				writeElementWithNewline("AutoTransmission",
						this.getAutoTransmission());
				writeElementWithNewline("ValidationCheck",
						this.getValidationCheck());
				writeElementWithNewline("TestIndicator",
						this.getTestIndicator());
				closeElement(); // ApplicationControl
				writer.writeCharacters(Utils.LF);
			}
			closeElement(); // CustomsExchange
			writer.writeCharacters("\n");
			writeElementWithNewline("MessageID", messageID);
			writeElementWithNewline("InReplyTo", inReplyTo);
			writeElementWithNewline("CustomerNumber", customerNumber); // EI20130419
			closeElement(); // Header
			writer.writeCharacters("\n");
			closeElement(); // soap:Header
			writer.writeCharacters("\n");
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}
	}

	/* ***************************************
	 * Getters and Setters **************************************
	 */
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getSentAt() {
		// SH080912 rest of date + time
		try {
			KcxDateTime kcxd = new KcxDateTime(year, month, day, time, timeZone);
			// CK 2010-12-22 use format with "T"
			// return kcxd.format(EFormat.ST_DateTime);
			// CK 8.6.2012
			// return kcxd.format(EFormat.ST_DateTimeTNoZ);
			if (Config.isRemoveTZ()) {
				return kcxd.format(EFormat.ST_DateTimeT);
			} else {
				return kcxd.format(EFormat.ST_DateTimeTNoZ);
			}
		} catch (ParseException e) {
			Utils.log(e.toString());
			return "";
		}
	}

	public String getSentAtDate() { // EI20131114
		String date = "";
		if (!Utils.isStringEmpty(this.year)) {
			date = this.year;
			if (!Utils.isStringEmpty(this.month)) {
				date = date + this.month;
				if (!Utils.isStringEmpty(this.day)) {
					date = date + this.day;
				}
			}
		}
		return date;
	}

	public String getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = transmitter;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCountryCode() {
		if (countryCode == null) {
			return "";
		} else {
			return countryCode;
		}
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getProcedureSpecification() {
		return procedureSpecification;
	}

	public void setProcedureSpecification(String procedureSpecification) {
		this.procedureSpecification = procedureSpecification;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getRelease() {
		if (release != null) {
			release = release.trim();
		}
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(String inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getMapFrom() {
		return mapFrom;
	}

	public void setMapFrom(String mapFrom) {
		this.mapFrom = mapFrom;
	}

	public String getMapTo() {
		return mapTo;
	}

	public void setMapTo(String mapTo) {
		this.mapTo = mapTo;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAutoTransmission() {
		return autoTransmission;
	}

	public void setAutoTransmission(String autoTransmission) {
		this.autoTransmission = autoTransmission;
	}

	public String getValidationCheck() {
		return validationCheck;
	}

	public void setValidationCheck(String validationCheck) {
		this.validationCheck = validationCheck;
	}

	public String getTestIndicator() {
		return testIndicator;
	}

	public void setTestIndicator(String testIndicator) {
		this.testIndicator = testIndicator;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String value) {
		this.customerNumber = value;
	}

	public String getJustCode() {
		return justCode;
	}

	public void setJustCode(String value) {
		this.justCode = value;
	}

	private boolean isBDP(String kcxId) { // EI20130417
		boolean isBDP = false;
		if (!Utils.isStringEmpty(kcxId)) {
			isBDP = kcxId.contains("BDP");
			if (kcxId.equals("DE.KCX.TST")) { // EI20131204: NUR FUER TESTs von
												// Manija
				isBDP = true; // damit die Nachrichten nicht an BDP gehen
				Utils.log("(FSSToKids  BDP-TEST-kcxId = " + kcxId);
			}
		}
		return isBDP;
	}
}
