package com.kewill.kcx.component.mapping.companies.fedex.ics.msg;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class FedexMessage extends KCXMessage {

	private FedexMessageHeader msgHeader = null;
	private FedexMessageBody msgBody = null;
	private KidsHeader kidsHeader = null;
	
	protected String encoding = "UTF-8";
	protected XMLStreamWriter writer;
	
	private CommonFieldsDTO commonFieldsDTO = null;
	
	protected enum EFMessages {
		EnveloppeMessage,
		MessageBody;
	}
	
	public FedexMessage() {
		super();
	}

	public FedexMessage(XmlMsgScanner scanner) {
  		super(scanner);
  	}	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EFMessages) tag) {
			case EnveloppeMessage: 	
				msgHeader = new FedexMessageHeader(getScanner());  	
				msgHeader.parse(tag.name());				
				break;	
			case MessageBody:
				msgBody = new FedexMessageBody(getScanner());  	
				msgBody.parse(tag.name());				
				break;
			default:
					return;
			}
		} 		
	}

	@Override
	public void stoppElement(Enum tag) {
	}

	@Override
	public Enum translate(String token) {
		try {
			return EFMessages.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public void setMsgHeader(FedexMessageHeader argument) {
		this.msgHeader = argument;
	}
	public FedexMessageHeader getMessageHeader() {
		return msgHeader;
	}
		
	public void setMessageBody(FedexMessageBody argument) {
		this.msgBody = argument;
	}
	public FedexMessageBody getMessageBody() {
		return msgBody;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getEncoding() {
		return encoding;
	}
	
	/**
     * Schreibt das version und encoding tag einer xml
     * mit doppelten hochkomma anstelle von einfachen hochkomma.
     * @param encoding
     * @param version
     * @throws XMLStreamException
     */
	protected void writeStartDocument(String encoding, String version) throws XMLStreamException {
		writer.writeDTD("<?xml version=\"" + version + "\" encoding=\"" + encoding + "\"?>");
	}
	
	public void setKidsHeader(KidsHeader kidsHeader) {
		this.kidsHeader = kidsHeader;
	}
	
	public KidsHeader getKidsHeader() {
		return kidsHeader;
	}
	
	protected void openElement(String tag) throws XMLStreamException {
	  writer.writeStartElement(tag);
	}
	  	
	protected void closeElement() throws XMLStreamException {
	      writer.writeEndElement();
	}
	  
	protected void setAttribute(String tag, String value) throws XMLStreamException {
	      writer.writeAttribute(tag, value);
	}

	public CommonFieldsDTO getCommonFieldsDTO() {
		return commonFieldsDTO;
	}
	
	public void setCommonFieldsDTO(CommonFieldsDTO commonFieldsDTO) {
		this.commonFieldsDTO = commonFieldsDTO;
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
	
	protected String getTime(String time) {
		String retTime = "";
		
		if (time != null) {
			if (time.length() > 3) {
				retTime = time.substring(0, 4);
			}
		}
		return retTime;
	}
}
