package com.kewill.kcx.component.mapping.formats.kids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSDeclarationRejectedKids<br>
 * Created		: 08.26.2010<br>
 * Description	: Contains message structure for NCTSDeclarationRejected KIDS. 
 * 
 * @author Lassiter Caviles
 * @version 1.0.00
 */
public class BodyNCTSDeclarationRejectedKids extends KidsMessageNCTS {
	
	private MsgNCTSDeclarationRejected message;
	
	public BodyNCTSDeclarationRejectedKids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgNCTSDeclarationRejected getMessage() {
		return message;
	}

	public void setMessage(MsgNCTSDeclarationRejected argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("NCTSDeclarationRejected");
			openElement("GoodsDeclaration");
					writeElement("ReferenceNumber", message.getReferenceNumber());
					writeElement("UCRNumber", message.getUcrNumber());
					writeElement("TypeOfDeclaration", message.getTypeOfDeclaration());						
					writeFormattedDateOrTime("ArrivalRejectionDate", message.getArrivalRejectionDate(),									
							         message.getArrivalRejectionDateFormat(), EFormat.KIDS_Date);						
					writeElement("Reason", message.getReason());
						
					if (message.getErrorList() != null) {
						for (FunctionalErrorNcts error : message.getErrorList()) {
							writeFunctionalErrorNcts("Error", error);
						}
					}					
			closeElement();
			closeElement();
			closeElement();
					
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
}
