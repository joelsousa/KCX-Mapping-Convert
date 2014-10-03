package com.kewill.kcx.component.mapping.common.start;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.Import.KidsToKids64Import;
import com.kewill.kcx.component.mapping.countries.de.Import20.KidsToKidsImport20;
import com.kewill.kcx.component.mapping.countries.de.Port.KidsToKidsPort;
import com.kewill.kcx.component.mapping.countries.de.Port20.KidsToKidsPort20;
import com.kewill.kcx.component.mapping.countries.de.aes.KidsToKidsExport;
import com.kewill.kcx.component.mapping.countries.de.aes21.KidsToKidsExportV21;
import com.kewill.kcx.component.mapping.countries.de.emcs.KidsToKidsEmcs;
import com.kewill.kcx.component.mapping.countries.de.emcs21.KidsToKidsEmcs21;
import com.kewill.kcx.component.mapping.countries.de.ics.KidsToKidsICS;
import com.kewill.kcx.component.mapping.countries.de.ics20.KidsToKidsICS20;
import com.kewill.kcx.component.mapping.countries.de.ncts.KidsToKidsNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.KidsToKidsNCTS41;
import com.kewill.kcx.component.mapping.countries.nl.aes.KidsToKidsExportNl;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      : KidsToKidsConverter<br>
 * Created     : 06.03.2009<br>
 * Description : Transformer convert KIDS-Format to KIDS
 * messages.
 * 
 * @author messer
 * @version 1.0.00
 */
public abstract class KidsToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO = null;
    /**
     * EI20120801 relevant only for KFF.
     */
    protected String bob = "";                  //EI20120801 relevant only for KFF

    public String readKids(String message, String encoding, EDirections direction) throws Exception {
        InputStream ins = new ByteArrayInputStream(message.getBytes());
        InputStreamReader is = new InputStreamReader(ins, encoding);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return readKids(parser, message, "No Mule Call", direction);
    }
    
	public String readKids(XMLEventReader parser, String payload, String auditId, EDirections direction)
			                                                                        throws Exception {
		KidsHeader kidsHeader = getKidsHeader(parser);	
		
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        String kcxId = kidsHeader.getReceiver();
        Utils.log("(KidsToKidsConverter readKids) kcxId = " + kcxId);
        //Utils.log("(KidsToKidsConverter readKids) bob = " + bob);      //EI20120806
        String countryCode = kidsHeader.getCountryCode();
		
		
        // MS20101018
//        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        //commonFieldsDTO.setBOB(bob);     				//EI20120802        
        
        // MS20101018
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }

        // some specified Messages should not be processed
		// e.g. if a country cannot process these messages
		if (Utils.refuseMessage(kidsHeader)) {
			Utils.log("(KidsToKidsConverter readKids) Processing refused !!! Msg " + 
					kidsHeader.getMessageName() + " MsgID " + kidsHeader.getMessageID());
			return null;
		}        
        
        // MS20101129
        String messageName = kidsHeader.getMessageName();
        if (messageName.equalsIgnoreCase("localAppresult") || 
                                                        messageName.equalsIgnoreCase("InternalStatusInformation")) {
            commonFieldsDTO.setFunctionalAcknowledgement(true);
        }
        
        // CK20100805
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        
        String kcxIdWithoutDots = Utils.removeDots(kcxId);

        if (direction == EDirections.CustomerToCountry) {
            kcxEnvelope.setPrimary(countryCode);
        } else {
            kcxEnvelope.setPrimary(kcxIdWithoutDots);
        }

        
        String mappingResult = payload;

		// Code-Mapping durchführen heißt, zuerst die Nachricht einlesen
		// (parsen)
		// ist kein Code-Mapping erforderlich wird die Nachricht nur in den
		// KCX-Umschlag gepackt
		String msg = kidsHeader.getMessageName();
		// CK15032012 kidsHeader.setMapping(direction);

        String procedure = kidsHeader.getProcedure();
        if (procedure == null) {
            throw new KcxMappingException("No procedure found in KIDS data! Cannot determine mapping class!");
        } else {
            procedure = procedure.toUpperCase();
        }
        kcxEnvelope.setSecondary(procedure.toUpperCase());

		if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
            Utils.isProcedureLicensed(kcxId, procedure, countryCode);
            // CK29022012
            // hier den receiver NICHT durch die local-ID ersetzen, das passiert bei  Richtung "CustomerToCountry"
            // am Ausgangs-BOB im KcxToKidsMule
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
            // CK29022012
            // hier den receiver durch die local-ID ersetzen, FALLS in der kcx.ini gesetzt 
            if (Config.isReplaceReceiver()) {
            	String kcxiddb = Utils.getKewillIdFromCustomer(kcxId, "KIDS"); 
            	Utils.log("(KidsToKidsConverter readKids) localId = " + kcxId + " replaced by " + kcxiddb);
            	kidsHeader.setReceiver(kcxiddb);
            	commonFieldsDTO.setKcxId(kcxiddb);
            	if (direction != EDirections.CustomerToCountry) {
                     kcxEnvelope.setPrimary(Utils.removeDots(kcxiddb));
                }
            }
        }
		
		// CK15032012 hierher verschoben, da ggfs erst der local Receiver durch die kcx-id ersetzt werden muss 
		kidsHeader.setMapping(direction);
		
		if (kcxId.equals("DE.TOLL.TST")) {  //EI20130819: für KFF nur zur DEMO-zwecke
	        if (procedure.equalsIgnoreCase("Export")) {
	        	kidsHeader.setRelease("2.1.01");
	        }
	        if (procedure.equalsIgnoreCase("Import")) {
	        	kidsHeader.setRelease("2.0.01");
	        }
	    }
	        
        String version = kidsHeader.getRelease();
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }       
        //EI20130417: fuer BDP muss konvertierung immer angestossen werden:
        //boolean isBDP = this.isBDP(kcxId);  
        boolean isBDP = this.isBDP(kcxId, version); //EI20131204 version hinzugefuegt
        if (isBDP) {
        	//String release = getBdpRelease(isBDP, procedure, version);//EI20130425: for BDP we set/overwrite the right version
        	String release = getBdpRelease(isBDP, procedure, kidsHeader.getRelease()); //EI20131204: jetzt mit kidsHeader.getRelease() statt version
        	version = Utils.removeDots(release.substring(0, 3));
        	kidsHeader.setRelease(release);
        	kidsHeader.setMap("0");             //EI20130513
        	kidsHeader.setMapFrom("DE");
        	kidsHeader.setMapTo("DE");
        }       
        
        // NL hat eine Sonderkonvertierung für die Hausnummer und muss daher immer aufgerufen werden!
        // ReverseDeclaration immer konvertieren um PDF einzubetten.
        // EMCSValidDeclaration muss wegen tgz-Erstellung auch immer gemapped werden. 20101215MS
        
               
        //for NL muss Loading-, UnloadingPlace umgewandelt werden == neu geschrieben werden:
        //von ICS-Pool wird LoadingPlace:AE Dubai Intl, UnloadingPlace=NL Schiphol soll DXB, AMS
        if (kidsHeader.getCountryCode() != null && kidsHeader.getCountryCode().equalsIgnoreCase("NL")) {
        	if (procedure.equalsIgnoreCase("ICS") && kidsHeader.getCommonFieldsDTO().getDirection() == EDirections.CustomerToCountry) {        		
        		kidsHeader.setMap("1");
        		kidsHeader.setMapFrom("KCX_CODE");
            	kidsHeader.setMapTo("NL");
        	}
        }
       
        if (kidsHeader.getMap().equals("1") || msg.equalsIgnoreCase("ReverseDeclaration") || 
				msg.equalsIgnoreCase("EMCSValidDeclaration") || 
				isBDP) {
        	
            Utils.log("(KidsToKidsConverter.readKids) kidsHeader.getMessageName() = " + msg + " wird kodiert!");
            switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
                case K10EXPORT:
                case K11EXPORT:
                case K12EXPORT:
                case K20EXPORT: 
                    if (kidsHeader.getCountryCode().equalsIgnoreCase("NL") && 
                        kidsHeader.getCommonFieldsDTO().getDirection() == EDirections.CustomerToCountry) {
                        KidsToKidsExportNl kidsToKidsExportNl = new KidsToKidsExportNl();
                        mappingResult = kidsToKidsExportNl.readKids(parser, kidsHeader, commonFieldsDTO);
                    } else {
                        KidsToKidsExport kidsToKidsExport = new KidsToKidsExport();
                        mappingResult = kidsToKidsExport.readKids(parser, kidsHeader, commonFieldsDTO);
                    }
                    break;
                case K21EXPORT:
                	 if (kidsHeader.getCountryCode().equalsIgnoreCase("NL") && 
                         kidsHeader.getCommonFieldsDTO().getDirection() == EDirections.CustomerToCountry) {
                         KidsToKidsExportNl kidsToKidsExportNl = new KidsToKidsExportNl();
                         mappingResult = kidsToKidsExportNl.readKids(parser, kidsHeader, commonFieldsDTO);
                     } else {
                         KidsToKidsExportV21 kidsToKidsExport21 = new KidsToKidsExportV21();
                         mappingResult = kidsToKidsExport21.readKids(parser, kidsHeader, commonFieldsDTO);
                     }
                	 break;
                	 
                case K10EMCS:                  
                    KidsToKidsEmcs kidsToKidsEmcs = new KidsToKidsEmcs();
                    mappingResult = kidsToKidsEmcs.readKids(parser, kidsHeader, commonFieldsDTO);
                    break;
                case K20EMCS:   
                    KidsToKidsEmcs kidsToKidsEmcs20 = new KidsToKidsEmcs();
                    mappingResult = kidsToKidsEmcs20.readKids(parser, kidsHeader, commonFieldsDTO);
                    break;
                case K21EMCS:
                	KidsToKidsEmcs21 kidsToKidsEmcs21 = new KidsToKidsEmcs21();
                    mappingResult = kidsToKidsEmcs21.readKids(parser, kidsHeader, commonFieldsDTO);
                    break;
                    
                case K10ICS:                 	
                    KidsToKidsICS kidsToKidsICS = new KidsToKidsICS();
                    mappingResult = kidsToKidsICS.readKids(parser, kidsHeader, commonFieldsDTO);                    
                    break;
                case K20ICS:                	 
                    KidsToKidsICS20 kidsToKidsICS20 = new KidsToKidsICS20();
                    mappingResult = kidsToKidsICS20.readKids(parser, kidsHeader, commonFieldsDTO);                     
                    break;           
                    
                case K40NCTS:	 //FT23082010
                	KidsToKidsNCTS kidsToKidsNCTS = new KidsToKidsNCTS();
                    mappingResult = kidsToKidsNCTS.readKids(parser, kidsHeader, commonFieldsDTO);
                    break;
                case K41NCTS:	 //EI20130204                	
                    KidsToKidsNCTS41 kidsToKidsNCTS41 = new KidsToKidsNCTS41();
                    mappingResult = kidsToKidsNCTS41.readKids(parser, kidsHeader, commonFieldsDTO);
                    break;               
                    
                case K10PORT:    //EI20111109                
                	 KidsToKidsPort kidsToKidsPort = new KidsToKidsPort();
                	 mappingResult = kidsToKidsPort.readKids(parser, kidsHeader, commonFieldsDTO);
                	 break; 
                case K20PORT:    //EI20130508
                	KidsToKidsPort20 kidsToKidsPort20 = new KidsToKidsPort20();
               	 	mappingResult = kidsToKidsPort20.readKids(parser, kidsHeader, commonFieldsDTO, isBDP);
               	 	break; 
                	 
                case K10IMPORT:   //EI20120223 
                	KidsToKids64Import kidsToKidsImport = new KidsToKids64Import();
                	mappingResult = kidsToKidsImport.readKids(parser, kidsHeader, commonFieldsDTO);
                	break;
                case K20IMPORT:   //EI20121220                 	
                	KidsToKidsImport20 kidsToKidsImport20 = new KidsToKidsImport20();
                	mappingResult = kidsToKidsImport20.readKids(parser, kidsHeader, commonFieldsDTO);
                	break;
                	
                default:
                    throw new FssException("Unknown KIDS version and procedure " + "K" + version + procedure);
            }
		} else {
		    Utils.log("(KidsToKidsConverter.readKids) kidsHeader.getMessageName() = " + msg + 
		                                                                    " wird NICHT kodiert!");
		}
       
        if (mappingResult == null) {
            return null;
        }
		kcxEnvelope.setTertiary(null);
		kcxEnvelope.setAuditId(auditId);
		kcxEnvelope.setContent(mappingResult);
		
        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, kidsHeader, commonFieldsDTO);
        
        return kcx;

	}

    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception;

	private String wrapMessageInKcxEnvelope(KcxEnvelope kcxHeader) {
		StringWriter kcxEnvelopeString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			XMLStreamWriter kcxWriter = factory.createXMLStreamWriter(kcxEnvelopeString);
			kcxHeader.writeHeader(kcxWriter);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return kcxEnvelopeString.toString();
	}
	
    private KidsHeader getKidsHeader(XMLEventReader parser) throws Exception {
        KidsHeader kidsHeader = new KidsHeader();
        kidsHeader.setParser(parser);
        kidsHeader.setHeaderFields();
        return kidsHeader;
    }
    
    //private boolean isBDP(String kcxId, String version) {   //EI20130417
    private boolean isBDP(String kcxId, String version) {   //EI20131204 version hinzugefuegt
    	boolean isBDP = false;
    	if (!Utils.isStringEmpty(kcxId)) {
    		isBDP = kcxId.contains("BDP");    	
    		if (kcxId.equals("DE.KCX.TST") && version.equals("10")) {   //EI20131204: NUR FUER TESTs von Manija
    			isBDP = true;                   //damit die Nachrichten nicht an BDP gehen
    			Utils.log("(KidsToKidsConverter  BDP-TEST-kcxId = " + kcxId);
    		}
    	}
    	return isBDP;
    }
   
    private String getBdpRelease(boolean isBDP, String procedure, String version) {   //EI20130425
    	String release = "0.0.00";
    									//for BDP we set (overwrite) the right Versions:    	
    	if (isBDP && procedure != null && version != null && version.equals("1.0.00")) {
    									//&& version.startsWith("1")) {    			
    		if (procedure.equalsIgnoreCase("EXPORT")) {
    			release = "2.1.00";
    		}
    		if (procedure.equalsIgnoreCase("IMPORT")) {
    			release = "2.0.00";
    		}
    		if (procedure.equalsIgnoreCase("NCTS")) {
    			release = "4.1.00";
    		}
    		if (procedure.equalsIgnoreCase("PORT")) {
    			//release = "1.0.00";
    			release = "2.0.00";    			
    		}
    		if (procedure.equalsIgnoreCase("ICS")) {
    			release = "2.0.00";
    		}
    		if (procedure.equalsIgnoreCase("EMCS")) {
    			release = "2.0.00";
    		}
    		if (procedure.equalsIgnoreCase("MANIFEST")) {
    			release = "1.0.00";
    		}
    	} else {
    		Utils.log("(KidsToKidsConverter isBDP falsche Release/Porcedure = " + version + "/" + procedure);    		
    	}
    	return release;
    }
    
}
