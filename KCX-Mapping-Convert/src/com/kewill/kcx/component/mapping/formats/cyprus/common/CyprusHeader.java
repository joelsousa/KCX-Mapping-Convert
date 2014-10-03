package com.kewill.kcx.component.mapping.formats.cyprus.common;

/*
 * Function    : CyprusHeader.java
 * Titel       : 
 * Date        : 08.06.2011
 * Author      : Kewill GmbH / schmidt
 * Description : Header information of message messsages from/to cyprus.
 *               Reads and writes the header fields of a cyprian message.
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
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

import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Header information of message messsages from/to cyprus.
 * Reads and writes the header fields of a cyprian message.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CyprusHeader extends KidsMessage {
	
//    private XMLStreamWriter writer;                   
	private XMLEventReader 		parser	= null;	
	
	private String mesSenMES3	  = "";				// Message sender; 			Transmitter
	private String mesRecMES6	  = "";				// Message recipient; 		Receiver
	private String datOfPreMES9  = "";				// Date of preparation; 	SentAt/Date/(Year, Month, Say)
	private String timOfPreMES10 = "";				// Date of preparation; 	SentAt/Time
	private String priMES15      = "";				// Priority
	private String tesIndMES18   = "";				// Test indicator
	private String mesIdeMES19   = "";				// Message identification;	MessageID
	private String mesTypMES20   = "";				// Message type;            MessageTP/MessageName
    private String corIdeMES25   = "";              // Correlation identifier;  
	
	private String today = FssUtils.getToday();
	private String date = "";
	private String time = "";
   
    public CyprusHeader() {
    }
    
    public CyprusHeader(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

    public void setHeaderFields() throws Exception {

    	EndElement 		endElement 		= null;
    	StartElement 	startElement 	= null;
    	QName 			qname			= null;
    	String			text			= null;
    	
    	XMLEvent event = null;
    	parserloop:
    	while (parser.hasNext()) {
            event = parser.nextEvent();
//            Utils.log("(CyprusHeader setHeaderFields) event = " + event);
            
            switch(event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement = event.asStartElement();
                    qname = startElement.getName();
                    text = qname.getLocalPart();
                    Utils.log("(CyprusHeader setHeaderFields) START_ELEMENT: " + text);
                    /* EI20110706
                    String stopText = text.substring(0, 6);
                    if (stopText.equalsIgnoreCase("HEAHEA") || stopText.equalsIgnoreCase("XMLERR")) {
                        break parserloop;
                    }
                    */
                    switch(ECyprusHeaderTags.valueOf(text)) {
                    	case MesSenMES3:
                			mesSenMES3 = getElementContent();
                			break;
                    	case MesRecMES6:
                			mesRecMES6 = getElementContent();
                			break;
                    	case DatOfPreMES9:
                			datOfPreMES9 = getElementContent();
                			break;
                    	case TimOfPreMES10:
                			timOfPreMES10 = getElementContent();
                			break;
                    	case PriMES15:
                			priMES15 = getElementContent();
                			break;
                    	case TesIndMES18:
                    		tesIndMES18 = getElementContent();
                    		break;
                    	case MesIdeMES19:
                    		mesIdeMES19 = getElementContent();
                    		break;
                    	case MesTypMES20:
                   			mesTypMES20 = getElementContent();
                    		break;	
                    	case CorIdeMES25:
                    		corIdeMES25 = getElementContent();
                    		break;
                  		default: break;
                    	
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    endElement = event.asEndElement();
//                    Utils.log("(CyprusHeader setHeaderFields) LocalName = /" + endElement.getName());
                    qname = endElement.getName();
                    text = qname.getLocalPart();
                    Utils.log("(CyprusHeader setHeaderFields) END_ELEMENT: " + text);                   
                   if (text.equalsIgnoreCase("MesTypMES20")) {  //EI20110706
                       break parserloop;
                   }
                default: break;
            } // end switch
          } // end while
    }

    private String getElementContent() throws XMLStreamException {
        XMLEvent event = parser.nextEvent();
        if (event.isCharacters()) {
            Characters characters = event.asCharacters();
            return characters.getData();
        } else {
            return "";
        }

    }
	
	public void writeHeader() {
		try {
			writeElement("MesSenMES3",    mesSenMES3);
			writeElement("MesRecMES6",    mesRecMES6);
			writeElement("DatOfPreMES9",  datOfPreMES9);
			writeElement("TimOfPreMES10", timOfPreMES10);
            writeElement("PriMES15",      priMES15);
            writeElement("TesIndMES18",   tesIndMES18);
            writeElement("MesIdeMES19",   mesIdeMES19);
            writeElement("MesTypMES20",   mesTypMES20);
            writeElement("CorIdeMES25",   corIdeMES25);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
    }

	
/* *************************************************************************
 * Getter und Setter
 * *************************************************************************/
	private String checkDate(String dateCY) {   //EI20110714
		String dateKids = "";
		if (!Utils.isStringEmpty(dateCY)) { 					
    		if (dateCY.length() == 8) {
    			dateKids = dateCY;  
    		} else if (dateCY.length() == 6) {
    			dateKids = "20" + dateCY;
    		}
    	}	
		return dateKids;
	}
    public String getDay() {
    	date = checkDate(datOfPreMES9); 		//EI20110714
    	if (Utils.isStringEmpty(date)) {        
            date = today;
        } 
        return date.substring(6, 8);
    }   
    public String getMonth() {
    	date = checkDate(datOfPreMES9);                //EI20110714
    	if (Utils.isStringEmpty(date)) {
            date = today;
        } 
        return date.substring(4, 6);
    }
    public String getYear() {
    	date = checkDate(datOfPreMES9);                //EI20110714
        if (Utils.isStringEmpty(date)) {
            date = today;
        } 
        return this.date.substring(0, 4);
    }
    private String checkTime(String timeCY) {               		  //EI20110714
    	String timeKids = "";
    	if (!Utils.isStringEmpty(timeCY)) { 					
    		if (timeCY.length() == 4) {
    			timeKids = timeCY.substring(0, 2) + ":" + timeCY.substring(2, 4) + ":00";  
    		} else if (timeCY.length() == 6) {
    			timeKids = timeCY.substring(0, 2) + ":" + timeCY.substring(2, 4) + ":" + timeCY.substring(4, 6);
    		}
    	}	
		return timeKids;
    }
    public String getTime() {
    	time = checkTime(timOfPreMES10);               		  //EI20110714
        if (Utils.isStringEmpty(time)) {
            time = "00:00:00";
        } 
        return time;
    }
    public String getTimezone() {
        return "";
    }

    public String getMesSenMES3() {
        return mesSenMES3;
    }

    public void setMesSenMES3(String mesSenMES3) {
        this.mesSenMES3 = Utils.checkNull(mesSenMES3);
    }

    public String getMesRecMES6() {
        return mesRecMES6;
    }

    public void setMesRecMES6(String mesRecMES6) {
        this.mesRecMES6 = Utils.checkNull(mesRecMES6);
    }

    public String getDatOfPreMES9() {
        return datOfPreMES9;
    }

    public void setDatOfPreMES9(String datOfPreMES9) {
        this.datOfPreMES9 = Utils.checkNull(datOfPreMES9);
    }

    public String getTimOfPreMES10() {
        return timOfPreMES10;
    }

    public void setTimOfPreMES10(String timOfPreMES10) {
        this.timOfPreMES10 = timOfPreMES10;
    }

    public String getPriMES15() {
        return priMES15;
    }

    public void setPriMES15(String priMES15) {
        this.priMES15 = priMES15;
    }

    public String getTesIndMES18() {
        return tesIndMES18;
    }

    public void setTesIndMES18(String tesIndMES18) {
        this.tesIndMES18 = tesIndMES18;
    }

    public String getMesIdeMES19() {
        return mesIdeMES19;
    }

    public void setMesIdeMES19(String mesIdeMES19) {
        this.mesIdeMES19 = mesIdeMES19;
    }

    public String getMesTypMES20() {
        return mesTypMES20;
    }

    public void setMesTypMES20(String mesTypMES20) {
        this.mesTypMES20 = mesTypMES20;
    }

    public String getCorIdeMES25() {
        return corIdeMES25;
    }

    public void setCorIdeMES25(String corIdeMES25) {
        this.corIdeMES25 = corIdeMES25;
    }
    
}
