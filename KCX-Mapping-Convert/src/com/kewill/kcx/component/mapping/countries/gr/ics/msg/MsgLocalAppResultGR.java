package com.kewill.kcx.component.mapping.countries.gr.ics.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.common.ProcessingErrors;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.common.ResultState;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ICS Greece<br>
 * Created		: 09.08.2011
 * Description	: ProcessIncomingMessageResponse == MsgLocalAppResult.
 * 
 * @author	iwaniuk
 * @version	1.0.00
 *
 */

public class MsgLocalAppResultGR extends KCXMessage {	
	
	private ResultState			resultState;	
	private ProcessingErrors	processingErrors;
	
	public MsgLocalAppResultGR() {
		super();
	}
	
	public MsgLocalAppResultGR(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgLocalAppResultGR(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EApplResult {
		resultState,
		ProcessingErrors;
	}
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EApplResult) tag) {
				case resultState:		
					resultState = new ResultState(getScanner());									
					resultState.parse(tag.name());
					break;
								
				case ProcessingErrors:
					processingErrors = new ProcessingErrors(getScanner());
					processingErrors.parse(tag.name());					
					break;
								
				default:
					return;
			}
		} else {
			switch ((EApplResult) tag) {
				default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return EApplResult.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public ResultState getResultState() {
		return this.resultState;
	}
	public void setResultState(ResultState argument) {
		this.resultState = argument;
	}

	public ProcessingErrors getProcessingErrors() {
		return processingErrors;
	}
	public void setProcessingErrors(ProcessingErrors argument) {
		this.processingErrors = argument;
	}
	

}
