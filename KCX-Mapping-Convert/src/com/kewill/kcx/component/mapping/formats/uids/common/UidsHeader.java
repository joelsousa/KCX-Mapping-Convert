package com.kewill.kcx.component.mapping.formats.uids.common;
/*
 * Funktion    : UidsHeader.java
 * Titel       :
 * Erstellt    : 02.09.2008
 * Author      : CSF GmbH / kron
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Changes:
 * -----------
 * Author      : Sven Heise
 * Datum       : 08.09.2008
 * Kennzeichen : SH080908
 * Beschreibung: new header field AdditionalInformation
 *
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 */
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
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.db.CustomerHeadersDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

//SH080916 for date/time-formatting
import java.text.ParseException;

/**
 * Modul		: UidsHeader<br>
 * Erstellt     : 02.09.2008<br>
 * Beschreibung : Field and methods to read and write a UIDS-Header.
 * 
 * @author kron
 * @version 1.0.00
 * --------------
 *  Changes:
 *---------------  
 * Author      : iwaniuk
 * Datum       :
 * Kennzeichen : EI20100629
 * Beschreibung: new Tag TestIndicator (optional)
 */
public class UidsHeader extends UidsMessage {
 
	private String to			= null;
	private String from			= null;
	private String msgid		= null;
	private String inreplyto 	= null;
	private String sentat		= null;
	private String act 			= null;
	private String charmap		= null;
	private String messageName	= "unknown";
	private String additionalInformation = null;
	private String messageType	  = null;
    private String direction      = null;
	private String messageVersion = null;
	private String procedure      = null;
	private String method    	  = null;
	private String testIndicator  = null;   //EI20100629
	
	private String timeZone  = "";   //EI20110519
	
	//SH090916 for date/time-formatting
	private KcxDateTime kcx  = null;
	
	private XMLEventReader 		parser	= null;
	
	private String uidsNamespaceVersion = null;
	
	public UidsHeader(XMLStreamWriter writer) {
        this.writer = writer;
    }
	public UidsHeader() {
    }
	
	public void setHeaderFields() throws Exception {
		EndElement 		endElement 		= null;
    	Characters 		characters 		= null;
    	StartElement 	startElement 	= null;
    	QName 			qname			= null;
    	String			text			= null;
    	
    	XMLEvent event = null;
    	parserloop:
    	while (parser.hasNext()) {
            event = parser.nextEvent();

            switch(event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement = event.asStartElement();
                    qname = startElement.getName();
                    text = qname.getLocalPart();
                    
                    switch(EUidsHeaderTags.valueOf(text)) {
                        case To:
                        	event = parser.nextEvent();
                        	characters = event.asCharacters();
                        	this.to = characters.getData();
                        	break;
                        case From:
                        	event = parser.nextEvent();
                        	characters = event.asCharacters();
                        	this.from = characters.getData();
                        	break;
                        case MessageID:
                        	event = parser.nextEvent();
                        	characters = event.asCharacters();
                        	this.msgid = characters.getData();
                        	break;
                        case InReplyTo:
                        	event = parser.nextEvent();
                        	//SH080915 empty tag -> don't throw an exception, leave empty
                        	try {
                        		characters = event.asCharacters();
                        		this.inreplyto = characters.getData();
                        	} catch (Exception e) {
                        		Utils.log("(UidsHeader setHeaderFields) InReplyTo empty = " + e.toString());
                        	}
                        	break;
                        case SentAt:
                        	event = parser.nextEvent();
                        	characters = event.asCharacters();
                        	//this.sentat = characters.getData();
                        	//SH080916 use setter here to initialize kcx-Date var
                        	setSentat(characters.getData());
                        	break;
                    	case Act:
                    		event = parser.nextEvent();
                    		characters = event.asCharacters();
                    		this.act = characters.getData();
                        	//SH080911 if act is "status" or "failure" set message name to InternalStatusInformation
                        	//		   because there is no message body following
                        	if (act.equalsIgnoreCase("status") || act.equalsIgnoreCase("failure")) {
                        		setMessageName("InternalStatusInformation");
                        	}
                        	if (act.equalsIgnoreCase("confirm")) {
                        		setMessageName("Confirm");
                        	}
                    		break;
                    	case TestIndicator:
                        	event = parser.nextEvent();
                        	characters = event.asCharacters();
                        	this.testIndicator = characters.getData();
                        	break;
                    	case AdditionalInformation:
                    		//SH080908
                    		event = parser.nextEvent();
                    		characters = event.asCharacters();
                    		this.additionalInformation = characters.getData();
                    		break;
                        case Procedure:
                            //20100629MS
                            this.procedure = getValue();
                            break;
                        case Method:
                            //CK100622
                            event = parser.nextEvent();
                            characters = event.asCharacters();
                            this.method = characters.getData();
                            break; 
                    	case MessageType:
                    		//CK090428
                    		event = parser.nextEvent();
                    		characters = event.asCharacters();
                    		this.messageType = characters.getData();
                    		break;
                        case Direction:
                            //20100629MS
                            this.direction = getValue();
                            break;
                    	case MessageVersion:
                    		//CK090428
                    		event = parser.nextEvent();
                    		characters = event.asCharacters();
                    		this.messageVersion = characters.getData();
                    		break;    
                    		
                    	default: break;
                    }
                    break;
                
                case XMLStreamConstants.END_ELEMENT:
                    endElement = event.asEndElement();
                    // if EndTag of "Header" is found: break because 
                    // the Header is complete
                    qname = endElement.getName();
                    text  = qname.getLocalPart();
                    if (text.equals("Header")) {
                    	break parserloop;
                    }
                
                default: break;
            } // end switch
          } // end while
    	
    	/*
    	* Reading out Body to extract XML-Tag under Export
    	* this Tag is the Messagename !!!
    	* 
    	*/
// 20100511MS boolean exportFound = false;
        boolean procedureFound = false;
    	while (parser.hasNext()) {
            event = parser.nextEvent();
            //Utils.log("(UidsHeader readXMLEvents2 ) event = " + event);
            
            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
            	startElement = event.asStartElement();
                qname = startElement.getName();
                text = qname.getLocalPart();
//                Utils.log("(UidsHeader setHeaderFields) START_ELEMENT: " + text);
                
// 20100511MS if (exportFound) {
                if (procedureFound) {
                	this.messageName = text;
                	break;
                }
                
                // CK080925 looking for failure - messages 
                if ("soap:Fault".equals(text) || "Fault".equals(text)) {
                	setMessageName("Failure");
                	break;
                }

                if ("Export".equals(text)) {
                    setProcedure("Export");             // 20100507MS
// 20100511MS exportFound = true;
                    procedureFound = true;
                }
                
                // 20100507MS
                if ("EMCS".equals(text)) {
                    setProcedure("EMCS");
                    procedureFound = true;
                }
                
                // 20100611PT
                if ("ICS".equals(text)) {
                    setProcedure("ICS");
                    procedureFound = true;
                }
                
                //20100824EB
                if ("NCTS".equals(text)) {
                    setProcedure("NCTS");
                    procedureFound = true;
                }
                
              //EI20130104
                if ("Manifest".equals(text)) {
                    setProcedure("MANIFEST");
                    procedureFound = true;
                }
            } 
    	}
    }

	// 20100629MS
    private String getValue() throws Exception {
        XMLEvent event = parser.nextEvent();
//        Utils.log("(KidsHeader getValue) nextEvent = " + event);
        if (event.isCharacters()) {
//            String chars = event.asCharacters().getData();
//            Utils.log("(KidsHeader getValue) characters = " + chars);
            return event.asCharacters().getData();
        } else {
            return "";
        }
    }
    
    // 20100629MS
    public void writeHeader(CommonFieldsDTO commonFieldsDTO) {
        String kcxId = null;
        if (commonFieldsDTO.getDirection() == EDirections.CountryToCustomer) {
            kcxId = commonFieldsDTO.getKcxId();
        } else {
            kcxId = commonFieldsDTO.getCountryCode();
        }
        //EI20121207: diese sonderlösung soll nur für MANIFEST gelten:
        if (this.procedure != null && this.procedure.equalsIgnoreCase("MANIFEST")) {
        // Sonderlösung für JPMorgan DE.FORD.TST und DE.FORD.CGN
        // CK 15.11.2012        
        if (kcxId.equals("DE.FORD.TST") || kcxId.equals("DE.FORD.CGN")) {
        	writeHeaderJPMorgan();
        	return;
        }
        }
        String procedure = commonFieldsDTO.getProcedure().toUpperCase();
        CustomerHeadersDTO customerHeadersDTO = Db.getCustomerHeaders(kcxId, procedure);
        Utils.log("(KidsHeader writeHeader) customerHeadersDTO = " + customerHeadersDTO);
        if (customerHeadersDTO == null) {
            writeHeaderV10();
        } else {
            String headerVersion = customerHeadersDTO.getUidsVersion();
            Utils.log("(KidsHeader writeHeader) UIDS headerVersion = " + headerVersion);
            if (headerVersion.equals("1.1")) {
                writeHeaderV11();
            } else {
                if (headerVersion.equals("1.2")) {
                    writeHeaderV12();
                } else {
                    writeHeaderV10();
                }
            }
        }
    }
    
    
    // 20100629MS
//    public void writeHeader() {
	public void writeHeaderV10() {
        Utils.log("(UidsHeader writeHeaderV10) writing UIDS header version 1.0");
        try {
            openElement("soap:Header");
                openElement("Header");
                	setAttribute("xmlns", "http://www.eurtradenet.com/schemas/header/200310");
                	setAttribute("soap:mustUnderstand", "true");  //EI20110519: wieder aktiviert
                	writeElement("To", to);
                	writeElement("From", from);
                	writeElement("MessageID", msgid);
                	writeElement("InReplyTo", inreplyto);
                	// +"Z" necessary because SimpleDateFormat returns " +0100" without colon
                    // so we leave the timezone in simpleDateFormat out and add "Z" 
                    // this is valid with the xml schema
                    // CK 2010-12-23
                	//EI20110519: writeElement("SentAt", sentat + "Z");                	
                	if (sentat.length() > 11 && sentat.substring(10, 11).equals("T")) {                  		
                		sentat = sentat.substring(0, 10) + " " + sentat.substring(11);
                		sentat = sentat + " " + timeZone;                		
                		writeElement("SentAt", sentat);
                	} else {
                		sentat = sentat + " " + timeZone;
                		writeElement("SentAt", sentat);
                	} //EI20110519-end
                	writeElement("Act", act);
                	//SH080908
                	// CK080919 writeElement checks null ....
                	// if (additionalInformation != null && !additionalInformation.trim().equals("")) {
                		writeElement("AdditionalInformation", additionalInformation);
                	// }
                	//CK090428 neue Felder immer schreiben 
                	// if (messageVersion.equals("2.0")) {
                	writeElement("Method", method);
                	writeElement("MessageVersion", messageVersion);
                	writeElement("MessageType", messageType);
                	// }
                	writeElement("CharMap", charmap);
               	closeElement(); // Header
            closeElement(); // soap:Header
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
	
    // 20100629MS
//  public void writeHeader() {
  public void writeHeaderV11() {
      Utils.log("(UidsHeader writeHeaderV11) writing UIDS header version 1.1");
      try {
          openElement("soap:Header");
              openElement("Header");
                  setAttribute("xmlns", "http://www.eurtradenet.com/schemas/header/200310");
                  // setAttribute("soap:mustUnderstand", "true");
                  writeElement("To", to);
                  writeElement("From", from);
                  writeElement("MessageID", msgid);
                  writeElement("InReplyTo", inreplyto);
                  // +"Z" necessary because SimpleDateFormat returns " +0100" without colon
                  // so we leave the timezone in simpleDateFormat out and add "Z" 
                  // this is valid with the xml schema
                  // CK 2010-12-23
                  writeElement("SentAt", sentat + "Z");
                  writeElement("Act", act);
                  writeElement("TestIndicator", testIndicator);
                  writeElement("AdditionalInformation", additionalInformation);
                  writeElement("Procedure", procedure);
                  writeElement("Method", method);
                  writeElement("MessageType", messageType);
                  writeElement("Direction", direction);
                  writeElement("MessageVersion", messageVersion);
              closeElement(); // Header
          closeElement(); // soap:Header
      } catch (XMLStreamException e) {
          
          e.printStackTrace();
      }
  }
  public void writeHeaderV12() {
      Utils.log("(UidsHeader writeHeaderV12) writing UIDS header version 1.2");
      try {
          openElement("soap:Header");
              openElement("Header");
                  setAttribute("xmlns", "http://www.eurtradenet.com/schemas/header/200809");
                  // setAttribute("soap:mustUnderstand", "true");
                  writeElement("To", to);
                  writeElement("From", from);
                  writeElement("MessageID", msgid);
                  writeElement("InReplyTo", inreplyto);
                  // +"Z" necessary because SimpleDateFormat returns " +0100" without colon
                  // so we leave the timezone in simpleDateFormat out and add "Z" 
                  // this is valid with the xml schema
                  // CK 2010-12-23
                  writeElement("SentAt", sentat + "Z");
                  writeElement("Act", act);
                  writeElement("TestIndicator", testIndicator);
                  writeElement("AdditionalInformation", additionalInformation);
                  writeElement("Procedure", procedure);
                  writeElement("Method", method);
                  writeElement("MessageType", messageType);
                  writeElement("Direction", direction);
                  writeElement("MessageVersion", messageVersion);
              closeElement(); // Header
          closeElement(); // soap:Header
      } catch (XMLStreamException e) {
          
          e.printStackTrace();
      }
  }
  
  public void writeHeaderV21() {            //EI20120803
      Utils.log("(UidsHeader writeHeaderV21) writing UIDS header version 2.1");
      try {
          openElement("soap:Header");
              openElement("Header");
                  setAttribute("xmlns", "http://www.eurtradenet.com/schemas/header/200809");
                  // setAttribute("soap:mustUnderstand", "true");
                  writeElement("To", to);
                  writeElement("From", from);
                  writeElement("MessageID", msgid);
                  writeElement("InReplyTo", inreplyto);
                  // +"Z" necessary because SimpleDateFormat returns " +0100" without colon
                  // so we leave the timezone in simpleDateFormat out and add "Z" 
                  // this is valid with the xml schema
                  // CK 2010-12-23
                  writeElement("SentAt", sentat + "Z");
                  writeElement("Act", act);
                  writeElement("TestIndicator", testIndicator);
                  writeElement("AdditionalInformation", additionalInformation);
                  writeElement("Procedure", procedure);
                  writeElement("Method", method);
                  writeElement("MessageType", messageType);
                  writeElement("Direction", direction);
                  writeElement("MessageVersion", messageVersion);
              closeElement(); // Header
          closeElement(); // soap:Header
      } catch (XMLStreamException e) {
          
          e.printStackTrace();
      }
  }
  public void writeHeaderJPMorgan() {
      Utils.log("(UidsHeader writeHeaderV10) writing UIDS header version 1.0");
      try {
          openElement("soap:Header");
              openElement("Header");
              	setAttribute("xmlns", "http://www.eurtradenet.com/schemas/header/200310");
              	setAttribute("soap:mustUnderstand", "true");  
              	writeElement("To", to);
              	// Ford erhält im xml-tag "From" auch die ETN-Adresse - nicht das Land "DE"
              	// writeElement("From", from);
              	writeElement("From", to);
              	writeElement("MessageID", msgid);
              	writeElement("InReplyTo", inreplyto);
              	if (sentat.length() > 19 && sentat.substring(10, 11).equals("T")) {                  		
            		sentat = sentat.substring(0, 19) + " " + timeZone;              
            		writeElement("SentAt", sentat);
            	} else {
            		sentat = sentat + " " + timeZone;
            		writeElement("SentAt", sentat);
            	} 
              	writeElement("Act", act);
              	writeElement("CharMap", charmap);
             	closeElement(); // Header
          closeElement(); // soap:Header
      } catch (XMLStreamException e) {
          
          e.printStackTrace();
      }
  }
	
  
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCountryCode() {
		if (this.to != null) {
			return this.to.substring(0, 2);
		}
		return "";
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getInreplyto() {
		return inreplyto;
	}
	public void setInreplyto(String inreplyto) {
		this.inreplyto = inreplyto;
	}
	public String getSentat() {
		return sentat;
	}
	public void setSentat(String sentat) {
		this.sentat = sentat;
		try {
			kcx = new KcxDateTime(EFormat.ST_DateTime, sentat);
		} catch (ParseException e) {
			try {
				// kcx = new KcxDateTime(EFormat.ST_DateTimeT, sentat);
				kcx = new KcxDateTime(EFormat.ST_DateTimeTNoZ, sentat);
				
			} catch (ParseException ex) {
				Utils.log("UidsHeader.setSentAt wrong DateTime-formatting: " + ex.toString());
			}
		}
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	//SH080908
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public String getCharmap() {
		return charmap;
	}
	public void setCharmap(String charmap) {
		this.charmap = charmap;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getDay() {
		return kcx.format(EFormat.G_Day2);
		//return "25";
	}
	public String getMonth() {
		return kcx.format(EFormat.G_Month2);
		//return "09";
	}
	public String getYear() {
		return kcx.format(EFormat.G_Year4);
		//return "1966";
	}
	public String getTime() {
		// return kcx.format(EFormat.G_Time_Sec);
		// return "08:01:00";
		
		// Christine Kron 2010-12-17 
		// should return format of field "Time" in KIDS-Header without colon 
		return kcx.format(EFormat.KIDS_Time);
		// return "080100";
	}
	
	public String getTimezone() {
		return kcx.format(EFormat.G_Timezone);
		//return "+0200";
	}
	
	public void setSampleFields() {
		setTo("PL01.CSFALT");
		setFrom("DE01.CSFALT.2150");
		setMsgid("ChristinesMsgID");
		setSentat("2008-09-02T15:23:00 +0200");
		setAct("inform");
		
	}
	public String getUidsNamespaceVersion() {
		return uidsNamespaceVersion;
	}
	public void setUidsNamespaceVersion(String uidsNamespaceVersion) {
		this.uidsNamespaceVersion = uidsNamespaceVersion;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageVersion() {
		return messageVersion;
	}
	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTestIndicator() {
		return testIndicator;
	}
	public void setTestIndicator(String argument) {
		this.testIndicator = argument;
	}
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }	
    
    
    public String getTimeZone() {
        return timeZone;
    }
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
