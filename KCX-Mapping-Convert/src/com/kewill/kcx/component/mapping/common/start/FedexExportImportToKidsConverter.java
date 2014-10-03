/*
 * Function    : UnisysToKidsConverter.java
 * Titel       :
 * Date        : 14.12.2008
 * Author      : krzoska
 * Description : transformer to convert UNISYS-Format to KIDS messages
 *             : 
 * Parameters  : 

 * Changes 
 * ------------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.common.start;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.FedexToKids;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.MsgDeclnInput;
import com.kewill.kcx.component.mapping.db.CustomerDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : FEDEX-ExportImportNcts.<br>
 * Created : 29.10.2013<br>
 * Description : Converter for FEDEX-EXPRESS (FEDEX ICS is a separate BOB)
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public abstract class FedexExportImportToKidsConverter {
	/**
	 * Structure to pass common values.
	 */
	protected CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();

	public String readFedexDecln(String message, String encoding,
			EDirections direction) throws Exception {
		InputStream ins = new ByteArrayInputStream(message.getBytes());
		InputStreamReader is = new InputStreamReader(ins, encoding);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader parser = factory.createXMLEventReader(is);
		MsgDeclnInput msgDecln = parseFedexMessage(parser);
		KidsHeader kidsHeader = createKidsHeader(msgDecln, encoding, direction);
		String kidsXML = createKidsXMLFromFedexDeclaration(msgDecln,
				kidsHeader, encoding, direction);
		return kidsXML;
	}

	public MsgDeclnInput parseFedexMessage(XMLEventReader parser)
			throws XMLStreamException {
		MsgDeclnInput msgDecln = new MsgDeclnInput(parser);
		msgDecln.parse(HeaderType.DECLN);

		if (msgDecln == null || msgDecln.getDeclSecInput() == null) {
			Utils.log("(FedexExportImportNctsConverter) MsgDeclnInput.parse liefert null!");
			return null;
		}

		return msgDecln;
	}

	public KidsHeader createKidsHeader(MsgDeclnInput msgDecln, String encoding,
			EDirections direction) throws Exception {
		KidsHeader kidsHeader = new KidsHeader();
		String kcxId = "";
		String procedure = "";
		String version = "";
		String countryCode = "";

		String declnTypeCd = msgDecln.getDeclSecInput().getDeclnTypeCd();
		if (Utils.isStringEmpty(declnTypeCd) || declnTypeCd.length() < 3) {
			Utils.log("(FedexExportImportNctsConverter) Procedure is not provided!");
			return null;
		}
		countryCode = msgDecln.getDeclSecInput().getCountryCd();

		// TODO: LOCAL_ID should be established dynamically!
		boolean testmode = true; // EI20140611 in kcx.ini aufnehmen?
		if (testmode) {
			kcxId = Utils.getKewillIdFromCustomer("DE.FEDEX.TST", "FEDEX");
		} else {
			kcxId = Utils.getKewillIdFromCustomer("DE.FEDEX.LIVE", "FEDEX");
		}
		kidsHeader.setReceiver(kcxId);
		kidsHeader.setTransmitter(kcxId);
		kidsHeader.setCountryCode(countryCode);

		if (direction == EDirections.CustomerToCountry) {
			kidsHeader.setDirection("FROM_CUSTOMER");
		} else {
			kidsHeader.setDirection("TO_CUSTOMER");
		}

		String eori = "";
		String text = "";
		CustomerDTO customerDTO = Db.getCustomerFromKidsId(kcxId, "KIDS",
				countryCode);

		if (customerDTO != null && customerDTO.getBranchName() != null) {
			String branch = customerDTO.getBranchName().trim();
			if (branch.length() > 9) {
				text = branch.substring(0, 9).trim();
				if (text.equalsIgnoreCase("EX/EORI:")) {
					eori = branch.substring(8).trim();
				}
			}
		}

		String declnSubTypeCd = declnTypeCd.substring(2, 3);
		declnTypeCd = declnTypeCd.substring(0, 2);
		
		if (declnTypeCd.equalsIgnoreCase("IM")) {
			procedure = "IMPORT";
			version = "2.0.00";
			if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("SE")) { // EI20140528
				kidsHeader.setProcedureSpecification("TNU");
			} else if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("DK")) {
				kidsHeader.setProcedureSpecification("04");
			} else if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("FI")) {
				kidsHeader.setProcedureSpecification("A");
			} else if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("IE")) {
				/*
				 * Joel
				 * 
				 * Version 27 : ID 65
				 * 
				 * Import the <ProcedureSpecification> Tag is missing in the
				 * Declaration Response/Confirmation customer
				 */

				kidsHeader.setProcedureSpecification(msgDecln.getDeclSecInput()
						.getDeclnSubtypeCd());
			}
		} else if (declnTypeCd.equalsIgnoreCase("EX")) {
			procedure = "EXPORT";
			version = "2.1.00";
			commonFieldsDTO.setEoriNumber(eori); // EI20140603 JIRA KCX-277
			if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("SE")) { // EI20140528
				kidsHeader.setProcedureSpecification("UNU");
				// commonFieldsDTO.setEoriNumber("SE5560168642");
			} else if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("DK")) {
				kidsHeader.setProcedureSpecification("20");
				// commonFieldsDTO.setEoriNumber("27043100"); //EI20140603 JIRA
				// KCX-277
			} else if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("FI")) {
				kidsHeader.setProcedureSpecification("A");
				// commonFieldsDTO.setEoriNumber("FI1882507-8"); //EI20140603
				// JIRA KCX-277
				// commonFieldsDTO.setEoriNumber("FI1198462-3#T0001");
				// //EI20140611 JIRA KCX-277 reopen
			} else if (countryCode != null
					&& countryCode.trim().equalsIgnoreCase("IE")) {
				/*
				 * Joel
				 * */
				// TODO : Check!
				kidsHeader.setProcedureSpecification(msgDecln.getDeclSecInput()
						.getDeclnSubtypeCd());
				// commonFieldsDTO.setEoriNumber("IE8273384R"); //EI20140603
				// JIRA KCX-277
			}
		} else if (declnTypeCd.equalsIgnoreCase("TT")) { // TODO-Max
			procedure = "NCTS";
			version = "4.1.00";
		} else {
			Utils.log("(FedexExportImportNctsConverter) unknown Procedure: "
					+ msgDecln.getDeclSecInput().getDeclnTypeCd());
			return null;
		}

		kidsHeader.setProcedure(procedure);
		// TODO: <Method> should be specified further for IMPORT procedure
		kidsHeader.setMethod(procedure);

		// KB ignore ProcedureSpecification as it is unused anyway
		// TODO: only for import, probably based on declnSubTypeCd:
		// if DECLN_TYPE_CODE ends in A or D then <ProcedureSpecification> = NP
		// and if DECLN_TYPE_CODE ends in C or F then <ProcedureSpecification> =
		// SP ?
		// kidsHeader.setProcedureSpecification(msgDecln.getDeclSecInput().getDeclnSubtypeCd());

		// Message ID is set to original filename from start class
		kidsHeader.setMessageID(commonFieldsDTO.getMessageReferenceNumber());

		msgDecln.getDeclSecInput().mapSentAt(kidsHeader,
				msgDecln.getDeclSecInput().getDeclnRouteDt());
		// kidsHeader.setMapping(direction);

		kidsHeader.setMap("0");
		kidsHeader.setMapFrom(countryCode);
		kidsHeader.setMapTo(countryCode);

		kidsHeader.setRelease(version);

		commonFieldsDTO.setKcxId(kcxId);
		commonFieldsDTO.setCountryCode(countryCode);
		commonFieldsDTO.setDirection(direction);
		commonFieldsDTO.setProcedure(procedure);

		kidsHeader.setCommonFieldsDTO(commonFieldsDTO);

		return kidsHeader;
	}

	public String createKidsXMLFromFedexDeclaration(MsgDeclnInput msgDecln,
			KidsHeader kidsHeader, String encoding, EDirections direction)
			throws Exception {
		String procedure = kidsHeader.getProcedure();
		String version = kidsHeader.getRelease();
		if (version == null) {
			version = "0.0.00";
		} else {
			version = Utils.removeDots(version.substring(0, 3));
		}

		String mappingResult = null;
		String procedureVersion = "K" + version + procedure;

		switch (EKidsProcedureVersions.valueOf(procedureVersion)) {
		case K21EXPORT:
		case K20IMPORT:
		case K41NCTS: // EI20140122
			FedexToKids declnToKids = new FedexToKids();
			mappingResult = declnToKids.readFedexDecln(msgDecln, encoding,
					kidsHeader, procedureVersion);
			break;

		default:
			throw new FssException("Unknown procedure " + procedure);
		}

		return mappingResult;
	}

}
