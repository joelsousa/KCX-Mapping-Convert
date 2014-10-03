package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: PreviousDocument
 * Created		: 08.09.2010
 * Description	: contains the PreviousDocument data with all fields used in KIDS/UIDS.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class PreviousDocument extends KCXMessage {
	private String	type;
	private String	marks;
	private String	additionalInformation;
	private String 	date;
	private EFormat dateFormat;    //EI20110524
	
	private XMLEventReader	parser	= null;
	
	/// CONSTRUCTOR(s)
		public PreviousDocument() {
			super();
		}
		
		public PreviousDocument(XMLEventReader parser) {
			super(parser);
			this.parser	= parser;
		}
		
		public PreviousDocument(XmlMsgScanner scanner) {
			super(scanner);
		}
	
	/// SETTER & GETTER Methods
		public void setType(String type) {
			this.type	= type;
		}
		
		public String getType() {
			return this.type;
		}
		
		public void setMarks(String marks) {
			this.marks	= marks;
		}
		
		public String getMarks() {
			return this.marks;
		}
		
		public void setAdditionalInformation(String addtnlInfo) {
			this.additionalInformation	= addtnlInfo;
		}
		
		public String getAdditionalInformation() {
			return this.additionalInformation;
		}
		
		public void setParser(XMLEventReader parser) {
			this.parser	= parser;
		}
		
		public XMLEventReader getParser() {
			return this.parser;
		}
	
	/// ENUM(s)
		private enum EPreviousDocument {
			// KIDS					// UIDS
			Type,					// same
			Marks,					Reference,
			AdditionalInformation,	Remark,
			Date,					DateOfCreation
		}
	
	/// METHODS
		@Override
		public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch((EPreviousDocument) tag) {
					default:
						return;
				}
			} else {
				switch((EPreviousDocument) tag) {
					case Type:
						setType(value);
						break;
					
					case Marks:
					case Reference:
						setMarks(value);
						break;
					
					case AdditionalInformation:
					case Remark:
						setAdditionalInformation(value);
						break;
					
					case Date:
						setDate(value);
						setDateFormat(EFormat.KIDS_Date);
						break;
					case DateOfCreation:
						setDate(value);
						setDateFormat(EFormat.ST_Date);
						break;
					
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
				return EPreviousDocument.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
		
		public boolean isEmpty() {
			if (Utils.isStringEmpty(this.type) && 
					Utils.isStringEmpty(this.marks) && 
					Utils.isStringEmpty(this.additionalInformation)) {
				return true;
			} else {
				return false;
			}
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}
		
		public EFormat getDateFormat() {
			return dateFormat;
		}
		public void setDateFormat(EFormat format) {
			this.dateFormat = format;
		}
}
