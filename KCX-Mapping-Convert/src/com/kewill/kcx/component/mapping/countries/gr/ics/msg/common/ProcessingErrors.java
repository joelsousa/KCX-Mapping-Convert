package com.kewill.kcx.component.mapping.countries.gr.ics.msg.common;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
  * Module		: ICS GREECE.<br>
 * Created		: 09.08.2011<br>
 * Description	: ProcessingError
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ProcessingErrors extends KCXMessage {
	private XMLEventReader parser = null;
	
	private List<Error> processingErrorList = null;
	
	public ProcessingErrors() {
		super();
	}
	
	public ProcessingErrors(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
	
	public ProcessingErrors(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EProcessingErrors {
		Error;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EProcessingErrors) tag) {
			
				case Error:
					Error error = new Error(getScanner());
					error.parse(tag.name());
					addProcessingErrorList(error);
					break;
			default:
				break;
			}
		} else {
			switch ((EProcessingErrors) tag) {
			

			default:
				break;
			}
		}
	}
	
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}
	
	public Enum translate(String token) {
		try {
			return EProcessingErrors.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public List<Error> getProcessingErrorList() {
		return processingErrorList;
	}

	public void setProcessingErrorList(List<Error> list) {
		this.processingErrorList = list;
	}

	private void addProcessingErrorList(Error error) {
		if (processingErrorList == null) {
			processingErrorList = new Vector<Error>();
		}
		processingErrorList.add(error);
	}
	
	public boolean isEmpty() {
		//EI20131211: return processingErrorList.isEmpty()
		return processingErrorList == null || processingErrorList.isEmpty();
	}
}
