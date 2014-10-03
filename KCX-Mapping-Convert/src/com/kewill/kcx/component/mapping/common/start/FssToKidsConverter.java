package com.kewill.kcx.component.mapping.common.start;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.Import.Fss64ToKidsImport;
import com.kewill.kcx.component.mapping.countries.de.Import20.Fss70ToKidsImport;
import com.kewill.kcx.component.mapping.countries.de.Port.FssToKidsPort;
import com.kewill.kcx.component.mapping.countries.de.Port20.Fss70ToKidsPort;
import com.kewill.kcx.component.mapping.countries.de.aes.Fss53ToKidsExport;
import com.kewill.kcx.component.mapping.countries.de.aes.Fss60ToKidsExport;
import com.kewill.kcx.component.mapping.countries.de.aes21.Fss70ToKidsExport;
import com.kewill.kcx.component.mapping.countries.de.ncts.Fss62ToKidsNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.Fss70ToKidsNCTS;
import com.kewill.kcx.component.mapping.countries.de.suma62.Fss62ToKidsSuma;
import com.kewill.kcx.component.mapping.countries.de.suma70.Fss70ToKidsSuma;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module	 	: FssToKids<br>
 * Created	 	: 04.03.2009<br>
 * Description	: transformer to convert ZABIS-FSS format to KIDS format.
 * 
 * @author kron
 * @version 1.0.00
 */

public abstract class FssToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;

    public String readFss(String message, String fssFilename, String encoding, EDirections direction) throws Exception {
        InputStream ins = new ByteArrayInputStream(message.getBytes());
        InputStreamReader is = new InputStreamReader(ins);
        BufferedReader reader = new BufferedReader(is);
        
        String msgID = "";
        if (fssFilename.lastIndexOf(".xml") > 0) {
            msgID = fssFilename.substring(0, fssFilename.lastIndexOf(".xml"));  
        } else {
            msgID = fssFilename;
        }
        Utils.log("(FssToKidsConverter readFss) msgID = " + msgID);
        
        return readFss(reader, "No Mule Call", msgID, direction);
    }
    
    public String readFss(BufferedReader in, String auditId, String msgID, EDirections direction) throws FssException {    
    	
        TsVOR  tsVor = null;
        TsHead tsHead = null;
        String xml = null;
        String kcxId = null;
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        String procedure = null;
        String version = null;
        
        String secondary = "EXPORT";   //EI20130528
        
        commonFieldsDTO = new CommonFieldsDTO();       
        commonFieldsDTO.setDirection(direction);
        
        tsVor  = new TsVOR("");         //EI20121204 verschoben von if... 
        tsHead = new TsHead("");        //EI20121204 verschoben von else if... 
               
        try {
            String line = in.readLine();
            if (Config.getLogXML()) {
            	Utils.log("(FssToKidsConverter readFss) line = " + line);	
            }            
            
            String lineType = line.substring(0, 3);
            if (lineType.equalsIgnoreCase("VOR")) {  
            	 //EI20121204 verschoben nach oben: tsVor = new TsVOR("E");
                String[] vorArray = line.split("" + FssUtils.FSS_FS);
                tsVor.setFields(vorArray);
                               
                if (Utils.isStringEmpty(tsVor.getMsgid())) {
                	// C.K. 08.10.2012 MsgID aus Dateinamen in tsVor setzen weil dieser
                    // später benutzt wird um den KIDS-Header zu schreiben    
                    commonFieldsDTO.setMessageReferenceNumber(msgID);
                } else {
                    commonFieldsDTO.setMessageReferenceNumber(tsVor.getMsgid());
                }   
                //EI30130711: ab Auslieferung nach 20130711 wird wohl endlich richtig:
                if (!Utils.isStringEmpty(tsVor.getVersnr())) {
                	String vnr = Utils.getStringWithoutDot(tsVor.getVersnr());  
                	if (vnr.length() > 1) {                		
                		if (vnr.startsWith("0")) {
                			vnr = vnr.substring(1);   // ab 1. stelle bis ende
                		}
                		if (vnr.startsWith("7")) {
                			tsVor.setInReplyTo(tsVor.getMsgid()); //damit kein Dreh entsteht zw. VOR und HEAD!!!
                			tsVor.setMsgid(msgID);   	//dateiname
                		}    
                	}
                }
            } else if (lineType.equalsIgnoreCase("HEA")) {
                //EI20121204 verschoben nach oben: tsHead = new TsHead("HEAD");  
                String[] headArray = line.split("" + FssUtils.FSS_FS);
                tsHead.setFields(headArray);
                
                if (Utils.isStringEmpty(tsHead.getMsgid())) {
                    commonFieldsDTO.setMessageReferenceNumber(msgID);                                 
                    tsHead.setMsgid(msgID);
                } else {
                    commonFieldsDTO.setMessageReferenceNumber(tsHead.getMsgid());
                }  
                
                // Felder des VOR-Satzes aus dem HEAD-Satz bestücken und wie gewohnt verwenden.
                // Damit sparen wir uns eine Menge Anpassungen.                 
                tsVor.mapHead2Vor(tsHead, "A");  //EI20121005     in fss2kids ist doch "A" (???)    
                                
            } else {
                throw new FssException("Wrong header line. Expected VOR/HEAD but found " + lineType);
            }
           
            String messageType = tsVor.getNatyp();
            if (messageType.equalsIgnoreCase("CON") || messageType.equalsIgnoreCase("ERR") || messageType.equalsIgnoreCase("STI")) {
                commonFieldsDTO.setFunctionalAcknowledgement(true);
            }  
            
            kcxId = null;
            kcxId = Utils.getKewillIdFromFssCustomer(tsVor.getKey()); 
            if (kcxId != null) 
            	kcxId = kcxId.trim();
            	
            Utils.log("(FssToKidsConverter readFss) kewillId = " + kcxId);
            if (kcxId != null) {
            	tsVor.setKewillId(kcxId.trim());
            	tsHead.setKewillId(kcxId.trim());
            }
            procedure = tsVor.getModul().trim();
            version = Utils.getStringWithoutDot(tsVor.getVersnr());   
            
            // EI20121129: IMxxx umbenannt in enum in ZBxxx, so wie alle andere Verfahren
            //if (procedure.equals("ZB")) {   //EI20110923
            //	procedure = "IM";
            //}
            
            //KFF-SonderrRlease: n.n.01  EI20130426:
            String release = "";   
            //EI20130523: release = Utils.getReleaseFromCustomerProcedure(kcxId, procedure, "KIDS"); 
            release = Utils.getReleaseFromCustomerProcedure(kcxId, procedure);
            commonFieldsDTO.setKidsRelease(release);
            
            CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kcxId);  //EI20140428
            commonFieldsDTO.setCustomerDataDTO(customerDataDTO);					  //EI20140428
  
            switch (EFssProcedureVersions.valueOf(procedure + version)) {
            	// EXPORT
                case EX0500:	
                case EXP0500:                
                    Fss53ToKidsExport fss53ToKids = new Fss53ToKidsExport(in, auditId, tsVor, msgID, commonFieldsDTO);
                    xml = fss53ToKids.readFss();
                    break;
                case EX0600:	case EX0620:                                
                case EXP0600:
                case VCH0600:	case VCH0620: 	case VCH0640:               
                    Fss60ToKidsExport fss60ToKids = new Fss60ToKidsExport(in, auditId, tsVor, msgID, commonFieldsDTO);
					xml = fss60ToKids.readFss();
                    break;                    
                case EX0700:	case EX07000:      			 //EI20120802                                
                case EXP0700:	case EXP07000:  
                //case VCH00700: case VCH007000:     //EI20130417 doku che-fss-70.doc ist noch nicht fertig
                								     //           Absprache wg. Dezimalzahlen notwendig!                 	
                	Fss70ToKidsExport fss70ToKids = new Fss70ToKidsExport(in, auditId, tsVor, tsHead, msgID, commonFieldsDTO);
                    xml = fss70ToKids.readFss();
                    break; 
                     
                // NCTS                    
                case VE0500:  
                case VE0600:	case VE0620:                                        	                           
                	Fss62ToKidsNCTS fss62ToKidsNCTS = new Fss62ToKidsNCTS(in, auditId, tsVor, msgID, commonFieldsDTO);
                    xml = fss62ToKidsNCTS.readFss();
                    secondary = "NCTS";
                    break;                  
                case VE0700:	case VE07000:    			    
                	//Utils.log("(FssToKidsConverter VE-V70) nicht definiert ");
                	//EI20140128: Fss70ToKidsNCTS fss70ToKidsNCTS = new Fss70ToKidsNCTS(in, auditId, tsVor, msgID, commonFieldsDTO);
                	Fss70ToKidsNCTS fss70ToKidsNCTS = new Fss70ToKidsNCTS(in, auditId, tsVor, tsHead, msgID, commonFieldsDTO);
                    xml = fss70ToKidsNCTS.readFss();
                    secondary = "NCTS";
                    break;
                
                // IMPORT == ZB
                case ZB0600:   case ZB0620:   case ZB0640:                                        
                	Fss64ToKidsImport fss64ToKidsImport = new Fss64ToKidsImport(in, auditId, tsVor, msgID, commonFieldsDTO);
                    xml = fss64ToKidsImport.readFss();
                    secondary = "IMPORT";
                    break;	                   
                case ZB0700:    case ZB07000:                     //EI20120808                             
                	Fss70ToKidsImport fss70ToKidsImport = new Fss70ToKidsImport(in, auditId, tsVor, tsHead, msgID, commonFieldsDTO);
                    xml = fss70ToKidsImport.readFss();
                    secondary = "IMPORT";
                    break;  
                    
                //PORT    
                case ZP0600: 	case ZP0640:	case ZP0650:                
                	FssToKidsPort fssToKidsPort = new FssToKidsPort(in, auditId, tsVor, msgID, commonFieldsDTO);
                    xml = fssToKidsPort.readFss();   
                    secondary = "PORT";
                    break;  
                case ZP0700: 	case ZP07000:    //EI20130529: FSS_Aantworten haben sich nicht geaendert, aber fuer ERR
                	// inReplyTo und messageId soll aus HEAD gelesen werden
                	Fss70ToKidsPort fss70ToKidsPort = new Fss70ToKidsPort(in, auditId, tsVor, tsHead, msgID, commonFieldsDTO);
                    xml = fss70ToKidsPort.readFss();   
                    secondary = "PORT";
                    break; 
                    
                // SUMA    
                case SU0620:	case SU0600:	case SU0640:	case SU0650:   //C.K. 2012-11                
                	Fss62ToKidsSuma fss62ToKidsSuma = new Fss62ToKidsSuma(in, auditId, tsVor, msgID, commonFieldsDTO);
                    xml = fss62ToKidsSuma.readFss();
                    secondary = "MANIFEST";
                    break;	                
                case SU0700:    case SU07000:   //EI20130701
                    Fss70ToKidsSuma fss70ToKidsSuma = new Fss70ToKidsSuma(in, auditId, tsVor, tsHead, msgID, commonFieldsDTO);
                    xml = fss70ToKidsSuma.readFss();
                    secondary = "MANIFEST";
                    break;
                   
                default:
                    throw new FssException("Unknown procedure and version " + version);
            }           
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        kcxEnvelope.setPrimary(Utils.removeDots(kcxId.trim()));
        //EI20130528:kcxEnvelope.setSecondary(EProcedures.EXPORT.toString().toUpperCase());
        //EI20130528: sollte hier statt EXPORT die richtige Procedur stehen?
        kcxEnvelope.setSecondary(secondary.toUpperCase());  //EI20130528
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(xml);
        Utils.log("(FssToKidsConverter readFss)  kcxHeader.toString() = " + kcxEnvelope.toString());
        
        logAudit(tsVor, kcxEnvelope, commonFieldsDTO);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);
        return kcx;
    }

    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    // Nicht abstract, da nicht zwingend.
    public abstract void logAudit(TsVOR tsVor, KcxEnvelope kcxEnvelope, 
                                  CommonFieldsDTO commonFieldsDTO) throws FssException;


    private String wrapMessageInKcxEnvelope(KcxEnvelope kcxEnvelope) {
        StringWriter kcxEnvelopeString = new StringWriter();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter kcxWriter = factory.createXMLStreamWriter(kcxEnvelopeString);
            kcxEnvelope.writeHeader(kcxWriter);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return kcxEnvelopeString.toString();
    }

}
