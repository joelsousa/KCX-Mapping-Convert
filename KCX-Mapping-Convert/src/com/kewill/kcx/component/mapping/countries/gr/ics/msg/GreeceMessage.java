package com.kewill.kcx.component.mapping.countries.gr.ics.msg;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

//EI20110803: public class GreeceMessage extends KCXMessage {
public class GreeceMessage extends CyprusMessage {   //EI20110803:

	//private KidsHeader kidsHeader = null;
    private GreeceHeader greeceHeader = null;
	
	//protected String encoding = "UTF-8";
	//protected XMLStreamWriter writer;	
	//private CommonFieldsDTO commonFieldsDTO = null;
	
	protected enum EGMessages {
		EnveloppeMessage,
		MessageBody;
	}
	
	public GreeceMessage() {
		super();
	}
	
	public GreeceMessage(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return EGMessages.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public GreeceHeader getGreeceHeader() {
        return greeceHeader;
    }

    public void setGreeceHeader(GreeceHeader greeceHeader) {
        this.greeceHeader = greeceHeader;
    }
    
    public void writeHeaderFields() throws XMLStreamException {
        if (greeceHeader != null) {
            writeElement("MesSenMES3", greeceHeader.getMesSenMES3());
            writeElement("MesRecMES6", greeceHeader.getMesRecMES6());
            writeElement("DatOfPreMES9", greeceHeader.getDatOfPreMES9());
            writeElement("TimOfPreMES10", greeceHeader.getTimOfPreMES10());
            writeElement("MesIdeMES19", greeceHeader.getMesIdeMES19());
            // MS20110805 Begin
//            writeElement("MesTypMES20", getCommonFieldsDTO().getTargetMessageType());
            writeElement("MesTypMES20", greeceHeader.getMesTypMES20());
            // MS20110805 End
        }
    }
    
	/*
	public KidsHeader getKidsHeader() {
		return kidsHeader;
	}

	public void setKidsHeader(KidsHeader kidsHeader) {
		this.kidsHeader = kidsHeader;
	}
    
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public XMLStreamWriter getWriter() {
		return writer;
	}

	public void setWriter(XMLStreamWriter writer) {
		this.writer = writer;
	}
/*
	public CommonFieldsDTO getCommonFieldsDTO() {
		return commonFieldsDTO;
	}

	public void setCommonFieldsDTO(CommonFieldsDTO commonFieldsDTO) {
		this.commonFieldsDTO = commonFieldsDTO;
	}
	*/
	/*
	protected void openElement(String tag) throws XMLStreamException {
		  writer.writeStartElement(tag);
	}
		  	
	protected void closeElement() throws XMLStreamException {
	      writer.writeEndElement();
	}

	protected void writeElement(String tag, String value) throws XMLStreamException {
	    if (value != null) {
	    	if (!value.trim().equals("")) {
	    		writer.writeStartElement(tag);
	    		writer.writeCharacters(value);
	    		writer.writeEndElement();
    		}
    	}
	}
	
    protected void setAttribute(String tag, String value) throws XMLStreamException {
        writer.writeAttribute(tag, value);
    }

	protected String getTime(String time) {
		String retTime = "";
		
		if (time != null) {
			if (time.length() > 3) {
				retTime = time.substring(0, 4);
			}
		}
		return retTime;
	}
	
    protected void writeStartDocument(String encoding, String version) throws XMLStreamException {
        writer.writeDTD("<?xml version=\"" + version + "\" encoding=\"" + encoding + "\"?>");
    }
*/

}
