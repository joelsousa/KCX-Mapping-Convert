package com.kewill.kcx.component.mapping.countries.de.aes21;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.ch.aus.uids2kids.MapCHReverseDeclarationUK;
import com.kewill.kcx.component.mapping.countries.de.aes.EUidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapConfirmUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapErrInfUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapExpConUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapExpErlUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapExpNckUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapExtNotUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapFailureUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpAmdUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpCanUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpDatUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpEntUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpExtUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpFupUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpIndUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpRelUK;
import com.kewill.kcx.component.mapping.countries.de.aes21.uids2kids.MapExpStaUK;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      : Export/aes<br>
 * Created     : 24.07.2012<br>
 * Description : V21: Transformer called to convert UIDS-Format to KIDS messages.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
 
public class UidsToKidsExportV21 {

	public String readUids(XMLEventReader parser, String encoding, UidsHeader uidsHeader, 
	                                                    CommonFieldsDTO commonFieldsDTO) throws Exception {
        
        String      countryCode = commonFieldsDTO.getCountryCode();
        String      kcxId       = commonFieldsDTO.getKcxId();
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        KidsHeader kidsHeader     = new KidsHeader(writer);
        kidsHeader.setReceiver(kcxId);
        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }		
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);		
        String msg = uidsHeader.getMessageName();
        
        switch (EUidsMessages.valueOf(msg)) {        	
        	case ExportPreAdvice: 
   				Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
            	MapExpIndUK mapExpIndUK = new MapExpIndUK(parser, kidsHeader, encoding);
            	prepareMessage(uidsHeader, kidsHeader, mapExpIndUK, commonFieldsDTO, "PreNotification", direction);
            	mapExpIndUK.getMessage(writer);
   				break;	   			
       		case ExportDeclaration:  
       	        Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
       			MapExpDatUK mapExpDatUK = new MapExpDatUK(parser, kidsHeader, encoding);
       	        prepareMessage(uidsHeader, kidsHeader, mapExpDatUK, commonFieldsDTO, "ExportDeclaration", direction);
                mapExpDatUK.getMessage(writer);                        	        
       			break;
       		case ExportAmendment: 
       			Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
                MapExpAmdUK mapExpAmdUK = new MapExpAmdUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpAmdUK, commonFieldsDTO, "Amendment", direction);
                mapExpAmdUK.getMessage(writer);
       			break;
       		case Completion:
       	        Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
                MapExpEntUK mapExpEntUK = new MapExpEntUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpEntUK, commonFieldsDTO, "Completion", direction);
                mapExpEntUK.getMessage(writer);
                break;
       		case Cancelation:  
       	        Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
       			MapExpCanUK mapExpCanUK = new MapExpCanUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpCanUK, commonFieldsDTO, "Cancellation", direction);
       			mapExpCanUK.getMessage(writer);
       			break;
       		case ManualTerminationExport: 
       	        Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
       			MapExpErlUK mapExpErlUK = new MapExpErlUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpErlUK, commonFieldsDTO, "ManualTermination", direction);
       			mapExpErlUK.getMessage(writer);
       			break;           			
       		case ExportRelease: 
       			if (countryCode.equals("CH")) {  
       			    MapCHReverseDeclarationUK mapCHReverseDeclarationUK = 
       			            new MapCHReverseDeclarationUK(parser, kidsHeader, encoding);
                    prepareMessage(uidsHeader, kidsHeader, mapCHReverseDeclarationUK, commonFieldsDTO, 
                                                                          "ReverseDeclaration", direction);
       				mapCHReverseDeclarationUK.getMessage(writer);
       			} else {       	
       			    MapExpRelUK mapReverseDeclarationUK = 
       			            new MapExpRelUK(parser, kidsHeader, encoding);
                    prepareMessage(uidsHeader, kidsHeader, mapReverseDeclarationUK, commonFieldsDTO, 
                                                                          "ReverseDeclaration", direction);
       				mapReverseDeclarationUK.getMessage(writer);  
       			}
       			break;
       		case ExportDeclarationResponse:  
       			MapExpStaUK mapExpStaUK = new MapExpStaUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpStaUK, commonFieldsDTO, "DeclarationResponse", direction);
       			mapExpStaUK.getMessage(writer);
       			break;  
       		case Confirmation:  
       			MapExpConUK mapExpConUK = new MapExpConUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpConUK, commonFieldsDTO, "Confirmation", direction);
       			mapExpConUK.getMessage(writer);
       			break;       		
           	case NonExitedExportRequest:
           		Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
                MapExpFupUK mapExpFupUK = new MapExpFupUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpFupUK, commonFieldsDTO, "Investigation", direction);
                mapExpFupUK.getMessage(writer);
           		break;
           	case NonExitedExportResponse: 
           		Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
                MapExpExtUK mapExpExtUK = new MapExpExtUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpExtUK, commonFieldsDTO, "ConfirmInvestigation", direction);
                mapExpExtUK.getMessage(writer);
           		break;	                  
            case InternalStatusInformation:
              	MapExpNckUK mapExpNckUK = new MapExpNckUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExpNckUK, commonFieldsDTO, "InternalStatusInformation", direction);
                mapExpNckUK.getMessage(writer);
                break;                 
            case ExitNotification:  
       	        Utils.isProcedureLicensed(kcxId, "EXPORT", countryCode);
       			MapExtNotUK mapExtNotUK = new MapExtNotUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapExtNotUK, commonFieldsDTO, "ExitNotification", direction);
       			mapExtNotUK.getMessage(writer);
       			break;            			 		
       		case ErrorInformation:
       			MapErrInfUK mapErrInfUK = new MapErrInfUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapErrInfUK, commonFieldsDTO, "ErrorMessage", direction);
         	    mapErrInfUK.getMessage(writer);
         	    break;      		
       		case Confirm:  
       			MapConfirmUK mapConfirmUK = new MapConfirmUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapConfirmUK, commonFieldsDTO, "localAppResult", direction);
       			mapConfirmUK.getMessage(writer);
       			break;
       		case Failure:  
       			MapFailureUK mapFailureUK = new MapFailureUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapFailureUK, commonFieldsDTO, "localAppResult", direction);
       			mapFailureUK.getMessage(writer);
       			break;
       		     			       		       			       
       		default: throw new FssException("Unknown message type " + msg);
        }

        String xml = bos.toString();
        return xml;
    }

	private void prepareMessage(UidsHeader uidsHeader, KidsHeader kidsHeader, KidsMessage message,
	                            CommonFieldsDTO commonFieldsDTO, String messageType, EDirections direction) 
	                                                                            throws XMLStreamException {
        mapUidsToKidsHeader(uidsHeader, kidsHeader, messageType);
        setKidsHeaderMappingFields(uidsHeader, kidsHeader, direction);
        message.setUidsHeader(uidsHeader);
        message.setCommonFieldsDTO(commonFieldsDTO);
	}
	
	
	private XMLStreamWriter getWriter(ByteArrayOutputStream bos, String encoding) throws Exception {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer       = null;
        writer     = factory.createXMLStreamWriter(osw);
        
	    return writer;
	}

	
    private KidsHeader mapUidsToKidsHeader(UidsHeader uidsHeader, KidsHeader kidsHeader, String messageName) {
    	kidsHeader.setDay(uidsHeader.getDay());
    	kidsHeader.setMonth(uidsHeader.getMonth());
    	kidsHeader.setYear(uidsHeader.getYear());
    	kidsHeader.setTime(uidsHeader.getTime());
    	kidsHeader.setTimeZone(uidsHeader.getTimezone());
    	kidsHeader.setTransmitter(uidsHeader.getFrom());
    	// Wird bereits vor dem Aufruf  mit der KewillId besetzt.
//    	kidsHeader.setReceiver(uidsHeader.getTo());
    	kidsHeader.setMethod(uidsHeader.getMethod());
    	kidsHeader.setCountryCode(uidsHeader.getCountryCode());
    	kidsHeader.setProcedure("Export");
  		kidsHeader.setMessageName(messageName);
        // CK090428 Kids Release in Abh. von Uids-Version setzen
  		// Uids 1.0 entspricht Kids 1.0.00 entspricht Zabis 5.3
  	    // Uids 2.0 entspricht Kids 2.0.00 entspricht Zabis 6.0
  		// CK120821
  		// UIDS 2.1 entspricht KIDS 2.1.00 = Zabis 7.0
  		if (uidsHeader.getMessageVersion() == null) {
  			Utils.log("(UidsToKidsConverter mapUidsToKidsHeader) getMessageVersion liefert null!");
  			kidsHeader.setRelease("1.0.00");
  		} else if (uidsHeader.getMessageVersion().equals("2.0")) {
  			kidsHeader.setRelease("1.1.00");
  			Utils.log("(UidsToKidsConverter mapUidsToKidsHeader) getMessageVersion ist 2.0");
  			
  		} else if (uidsHeader.getMessageVersion().equals("2.1")) {
  			kidsHeader.setRelease("2.1.00");
  			Utils.log("(UidsToKidsConverter mapUidsToKidsHeader) getMessageVersion ist 2.1");
  		} else if  (uidsHeader.getMessageVersion().equals("2.2")) {
  			//kidsHeader.setRelease("2.1.00");
  			kidsHeader.setRelease("2.1.00");
  			Utils.log("(UidsToKidsConverter mapUidsToKidsHeader) getMessageVersion ist 2.1");
  		} else  { kidsHeader.setRelease("1.0.00");
  		Utils.log("(UidsToKidsConverter mapUidsToKidsHeader) getMessageVersion NICHT 2.0, daher" +
  				"wird KIDS Release auf 1 gesetzt");
  		}
    	// kidsHeader.setRelease("1.0.00");
  		// Ende CK090428
    	kidsHeader.setMessageID(uidsHeader.getMsgid());
    	kidsHeader.setInReplyTo(uidsHeader.getInreplyto());
    
    	return kidsHeader;
    }
    
    private void setKidsHeaderMappingFields(UidsHeader uidsHeader, KidsHeader kidsHeader, EDirections direction) {
        CustomerProcedureDTO customerProcedureDTO = 
            Utils.getCustomerProceduresFromKcxId(kidsHeader.getReceiver(), kidsHeader.getProcedure().toUpperCase());
        String mappingCode = customerProcedureDTO.getMappingCode();

        Utils.log("(UidsToKidsConverter setKidsHeaderMappingFields) mappingCode                 = " + mappingCode);
        Utils.log("(UidsToKidsConverter setKidsHeaderMappingFields) uidsHeader.getCountryCode() = " + 
                                                           uidsHeader.getCountryCode());
        Utils.log("(UidsToKidsConverter setKidsHeaderMappingFields) kidsHeader.getCountryCode() = " + 
                                                           kidsHeader.getCountryCode());
        
        
        // Wenn Nachricht von Kunde Richtung Zoll,  dann ist mapFrom = mappingCode
        // und mapTo = countryCode.
        // Wenn Nachricht von Zoll  Richtung Kunde, dann ist mapFrom = countryCode
        // und mapto = mappingCode.
        
		// CK 2011-01-20 mapping code "EU" with the actual procedure means "no code mapping"
        
        if (mappingCode.equalsIgnoreCase("EU")) {
        	kidsHeader.setMap("0");
        } else {        
        	if (direction == EDirections.CustomerToCountry) {
	            kidsHeader.setMapFrom(mappingCode);
            	kidsHeader.setMapTo(uidsHeader.getCountryCode());
        	} else {
	            kidsHeader.setMapFrom(uidsHeader.getCountryCode());
            	kidsHeader.setMapTo(mappingCode);
        	}
	        
        	Utils.log("(UidsToKidsConverter setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapFrom() = " + kidsHeader.getMapFrom());
        	Utils.log("(UidsToKidsConverter setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapTo()   = " + kidsHeader.getMapTo());
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
	            kidsHeader.setMap("0");
        	} else {
	            kidsHeader.setMap("1");
        	}
        }
    }

}
