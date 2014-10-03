package com.kewill.kcx.component.mapping.countries.fr.ncts.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Iabo;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.MES;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FR-NCTS.<br>
 * Created		: 11.11.2013<br>
 * Description	: MsgIE13 = KIDS-NCTSDeclaration
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgENV extends KCXMessage {

	private Iabo iabo; 
	private String datesys; 
	private String sender; 
	private String recipient; 
	private String typemes; 
	private String lg; 
	private MES mes; 
	public MsgENV() {
		super();
	}

	public MsgENV(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgENV(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	private enum EMsgEnv {
		//ENV:     	//Enveloppe (Noeud principal)
		IABO,      	//Informations sur l'abonné NSTI
		DATESYS,	//Date système (AAAAMMJJ)
		SENDER,		//Coordonnées de l'envoyeur (Adresse EDI)
		RECIPIENT,	//Nom du receveur
		TYPEMES,	//Type de message
		LG,		   //Language
		MES;	//Message	
	}
	
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgEnv) tag) {
			//case DECLN_INPUT:
			//	break;
			case IABO: 	
				iabo = new Iabo(getScanner());  	
				iabo.parse(tag.name());
				break;
			
			case MES:	
				mes = new MES(getScanner());  	
				mes.parse(tag.name());
				break;
		 	
			default:
					return;
			}
		} else {
			switch ((EMsgEnv) tag) {			
				case DATESYS:
					setDateSys(value);
					break;
				case SENDER:
					setSender(value);
					break;
				case RECIPIENT:
					setRecipient(value);
					break;
				case TYPEMES:
					setTypeMes(value);
					break;
				case LG:
					setLg(value);
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
			return EMsgEnv.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Iabo getIABO() {
		return iabo;
	}
	public void setIABO(Iabo iabo) {
		this.iabo = iabo;
	}

	public MES getMes() {
		return mes;
	}
	public void setMes(MES mes) {
		this.mes = mes;
	}

	
	public String getDateSys() {
		return datesys;
	}
	public void setDateSys(String datesys) {
		this.datesys = datesys;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTypeMes() {
		return typemes;
	}
	public void setTypeMes(String typemes) {
		this.typemes = typemes;
	}

	public String getLg() {
		return lg;
	}
	public void setLg(String lg) {
		this.lg = lg;
	}

	public boolean isEmpty() {
		return 	(iabo == null || iabo.isEmpty()) &&
				(mes == null || mes.isEmpty()) &&
				 Utils.isStringEmpty(datesys) &&	
				 Utils.isStringEmpty(sender) &&	
				 Utils.isStringEmpty(recipient) &&	
				 Utils.isStringEmpty(typemes) &&	
				 Utils.isStringEmpty(lg); 
	}
}
