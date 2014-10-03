package com.kewill.kcx.component.mapping.countries.nl.aes;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.StringInputStream;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.RemoveKcxEnvelope;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpCanKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpEntKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpErlKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpExtKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpIndKK;
import com.kewill.kcx.component.mapping.countries.nl.aes.kids2kidsNl.MapExpDatToExpdatNl;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Modul : KidsToKids<br>
 * Erstellt : 06.03.2009<br>
 * Beschreibung : Transformer called by Mule to convert KIDS-Format to KIDS
 * messages.
 * 
 * 				  This mapping is necessary for i-customs online because of the following 
 * 				  variations of KIDS:
 * 				  1. CustomsOfficeExport in KIDS 8 chars, in i-customs online the last 3 chars used
 * 				  2. HouseNumber in Address has to be filled if Street is filled, default "0"  
 * @author messer
 * @version 1.0.00
 * 
 * * Changes 
 * ------------
 * Author      : Christine Kron
 * Date        : Juli 2010
 * Label       :
 * Description : setting CommonFieldDTO - used for writing Header in correct Version deposited in DB
 * 				 keep CustomsOfficeExport unchanged 
 */
public class KidsToKidsNl implements Callable {
	private static Logger logger = null; // Logger
	private MuleEventContext muleEventContext = null;
	private MuleMessage muleMessage = null;
    private CommonFieldsDTO commonFieldsDTO = null;

	public String onCall(MuleEventContext muleEventContext) throws Exception {
        logger = Logger.getLogger("kcx");
        Config.configure("conf", "kcx.ini");
//      reconfigureLog();
//      Config.showConfiguration();
        getConfiguration();
        
        muleEventContext.transformMessageToString();
        MuleMessage message = muleEventContext.getMessage();
        this.muleEventContext = muleEventContext;
//        this.muleMessage      = message;
        
		String payload = message.getPayloadAsString();
        if (Config.getLogXML()) {
            Utils.log("(KidsToKidsNl onCall) payload = \n" + payload);
        }
		Utils.log("(KidsToKidsNl onCall) message.getEncoding() = " + message.getEncoding());
		String auditId = AuditUtils.getAuditId(message);
        String xml = readKids(payload, message.getEncoding(), auditId);
		
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        Utils.log("(KidsToKidsNl onCall) message conversion successfull for correlation id " + 
                                                                            message.getCorrelationId());
        MuleUtils.writeFileMessage(message, xml, commonFieldsDTO);

        return xml;
	}

    public String readKids(String payload, String encoding, String auditId) throws Exception {
        // MS20110930 Begin
//        String content = removeKcxEnvelope(payload, encoding);
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToKidsNl readKids) content = \n" + content);
//        }
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, encoding);
        // MS20110930 End
        
        InputStream ins = new StringInputStream(content);
        InputStreamReader is = new InputStreamReader(ins, encoding);
//        InputStreamReader is = new InputStreamReader(ins);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return readKids(parser, content, auditId);
    }
    

	public String readKids(XMLEventReader parser, String payload, String auditId)
			throws Exception {
		
		KidsHeader kidsHeader = getKidsHeader(parser);
//		KcxEnvelope kcxEnvelope = new KcxEnvelope();

		String xml = payload;

		String msg = kidsHeader.getMessageName();
		EDirections direction = getDirection(msg);
		kidsHeader.setMapping(direction);
        
		//		if (kidsHeader.getMap().equalsIgnoreCase("1")) {

		Utils.log("(KidsToKidsNl.readKids) kidsHeader.getMessageName() = " + msg + " wird kodiert!");

		String localId = null;

		localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "KIDS", 
		                                        kidsHeader.getCountryCode()).trim();

		Utils.log("(KidsToKidsNl readKids) localId = " + localId);
		kidsHeader.setReceiver(localId);
		
//        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
        commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
        commonFieldsDTO.setDirection(direction);
        
        // MS20110105 Begin
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
        // MS20110105 End
        

        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        
		switch (EKidsMessages.valueOf(msg)) {
		case Cancellation:
			MapExpCanKK mapExpCanKK = new MapExpCanKK(parser, "UTF-8");
			mapExpCanKK.setKidsHeader(kidsHeader);
			xml = mapExpCanKK.getMessage();
			break;
		case ExportDeclaration:
			MapExpDatToExpdatNl mapExpDatKK = new MapExpDatToExpdatNl(parser, "UTF-8");
			mapExpDatKK.setKidsHeader(kidsHeader);
			xml = mapExpDatKK.getMessage();
			break;
		case Completion:
			MapExpEntKK mapExpEntKK = new MapExpEntKK(parser, "UTF-8");
			mapExpEntKK.setKidsHeader(kidsHeader);
			xml = mapExpEntKK.getMessage();
			break;
		case ManualTermination:
			MapExpErlKK mapExpErlKK = new MapExpErlKK(parser, "UTF-8");
			mapExpErlKK.setKidsHeader(kidsHeader);
			xml = mapExpErlKK.getMessage();
			break;
		case ConfirmInvestigation:
			MapExpExtKK mapExpExtKK = new MapExpExtKK(parser, "UTF-8");
			mapExpExtKK.setKidsHeader(kidsHeader);
			xml = mapExpExtKK.getMessage();
			break;
		case PreNotification:
			MapExpIndKK mapExpIndKK = new MapExpIndKK(parser, "UTF-8");
			mapExpIndKK.setKidsHeader(kidsHeader);
			xml = mapExpIndKK.getMessage();
			break;
		default:
			throw new FssException("Unknown message type " + msg);
		}
		String country = kidsHeader.getCountryCode();
		String kewillId = kidsHeader.getReceiver();
		
		return xml;

	}

	private static void getConfiguration() {
		logger.debug("Konfiguration wird geholt.");
		Utils.setDebug(Config.getDebug());
		Utils.setLogLevel(Config.getLogLevel());
	}

	private KidsHeader getKidsHeader(XMLEventReader parser) throws Exception {
		KidsHeader kidsHeader = new KidsHeader();
		kidsHeader.setParser(parser);
		kidsHeader.setHeaderFields();
		return kidsHeader;
	}

	
	private EDirections getDirection(String msg) throws Exception {
		switch (EKidsMessages.valueOf(msg)) {
		// to customs
		case PreNotification: // V6
		case ExportDeclaration:
		case Amendment: // V6
		case Completion:
		case ConfirmInvestigation: // V6
		case Cancellation:
		case ManualTermination:
			return EDirections.CustomerToCountry;
			// from customs
		case ReverseDeclaration:
		case DeclarationResponse:
		case Confirmation:
		case Investigation: // V6
		case InternalStatusInformation:
		case CancelationResponse: // Schweiz
		case ErrorMessage:
		case Confirm:
		case Failure:
		case localAppResult:
			return EDirections.CountryToCustomer;
		default:
			throw new FssException("Unknown message type " + msg);
		}
	}

// MS20110930 Begin	
//	public String removeKcxEnvelope(String payload, String encoding)
//			throws Exception {
//		String content = null;
//
//		InputStream ins = new StringInputStream(payload);
//		//        InputStreamReader is = new InputStreamReader(ins, encoding);
//		InputStreamReader is = new InputStreamReader(ins);
//		XMLInputFactory factory = XMLInputFactory.newInstance();
//		XMLEventReader parser = factory.createXMLEventReader(is);
//
//		XmlMsgScanner scanner = new XmlMsgScanner(parser);
//		scanner.skipTo(Token.START_TAG, "Content", 0);
//		scanner.next();
//		content = scanner.getLexem();
//		if (Config.getLogXML()) {
//			Utils.log("(KidsToKidsNl removeKcxEnvelope) content = \n" + content);
//		}
//
//		return content.trim();
//	}
// MS20110930 End	
}
