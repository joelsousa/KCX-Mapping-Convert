package com.kewill.kcx.component.mapping.test;

import javax.xml.stream.XMLStreamException;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportADPtoReverseDeclarationKids_notInUse;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class TestMEBodyExportADPtoReverseDeclarationKids {

public static void main(String[] args) {
//	BodyExportADPtoReverseDeclarationKids adp=new BodyExportADPtoReverseDeclarationKids();
	KidsMessage msg=new KidsMessage();
	
	try {
		msg.writeCodeElement("", "3", "KCX0027", "DE", "CH");
	} catch (XMLStreamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
