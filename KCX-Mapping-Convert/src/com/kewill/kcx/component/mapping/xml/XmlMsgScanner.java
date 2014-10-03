package com.kewill.kcx.component.mapping.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

import com.kewill.kcx.component.mapping.util.FssRuntimeException;

/**
 * Module    : XmlMsgScanner.java
 * Created        : 09.09.2008
 * Description : XML-Scanner.  
 * 			    
 * @author  Kewill CSF / Christine Kron / Thorsten Rausch
 * @version 1.0.00
 */

public class XmlMsgScanner {

	public static enum Token {
		START_DOC,
		STOPP_DOC,
		START_TAG, 
		STOPP_TAG, 
		VALUE,
		EXCEPTION; 
	}
	
	public static enum HeaderType {
		KIDS, UIDS, FEDEX, UNISYS, MISSING, UNKNOWN, FAULTY,
		CYPRUS,
		KFF,
		KEX, 
		DECLN;
	}
	
	// Anzahl gelesener Nachrichten (wird von der Nachricht selbst inkrementiert)
	private int msgno = 0;
	// Anzahl der Verarbeitungsfehler (wie oben)
	private int errors = 0;
	// aktuelle Zeilennummer
	private int lnno = 0;
	// Werte zum aktuellen Token
	private TokenInfo tokenInfo = null;
	private Queue <TokenInfo> tokenQueue = new LinkedList();
	// StAX-Parser
	private XMLEventReader parser;
	// Nachrichtenquelle (File od. Socket)
	private InputStream stream;
	// Zwischenspeicher für Textelemente
	private StringBuffer textbuf;
	
	private class TokenInfo {
		public int lnno;
		public Token token;
		public String value;
		public Attributes attribs;
		public Exception exception;
		
		public TokenInfo(int lnno, Token token, String value, Attributes attribs) {
			super();
			this.token = token;
			this.value = value;
			this.lnno = lnno;
			this.attribs = attribs;
		}
		
		public TokenInfo(int lnno, Exception e) {
			super();
			this.lnno = lnno;
			this.token = Token.EXCEPTION;
			this.exception = e;
		}
		
		public String toString() {
			StringBuffer sb = new StringBuffer();

			sb.append("Zeile ");
			sb.append(lnno);
			sb.append(" ");
			sb.append(token);
			if (value != null) {
				sb.append(" Wert:");
				sb.append(value);
			}
			if (attribs != null) {
				for (int i = 0; i < attribs.getLength(); i++) {
					sb.append("\nAttribut Nr. ");
					sb.append(i);
					sb.append(": ");
					sb.append(attribs.getQName(i));
					sb.append(" = ");
					sb.append(attribs.getValue(i));
				}
			}
			if (exception != null) {
				sb.append("Exception: ");
				sb.append(exception.getMessage());
			}
			return sb.toString();
		}
	}
	
	public XmlMsgScanner(XMLEventReader parser)	{
		this.parser = parser;
	}
	
	protected void finalize() {
		close();
	}

	public boolean hasNext() {
		if (!tokenQueue.isEmpty()) {
			return true;
		} else if (parser == null) {
			return false;
		} else {
			return parser.hasNext();
		} 
	}
	
	public Token next()	{
		try {
			if (tokenQueue.isEmpty()
			&& 	parser != null
			&&  parser.hasNext()) {
				TokenInfo tok  = nexttok();
				if (tok != null) {
					tokenQueue.add(tok);
				}
			}
		} catch (XMLStreamException e) {
			tokenQueue.add(new TokenInfo(lnno, e));
		}
		
		if (!tokenQueue.isEmpty()) {
			tokenInfo = tokenQueue.remove();
		} else if (tokenInfo == null) {
			return Token.STOPP_DOC;
		}

		if (tokenInfo.token != Token.EXCEPTION) {
			return tokenInfo.token;
		} else {
			throw new FssRuntimeException(tokenInfo.exception);
		}
	}

	private TokenInfo nexttok() throws XMLStreamException {
		while (true) {
			XMLEvent event = parser.nextEvent();
			switch (event.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					return startDocument();
				case XMLStreamConstants.END_DOCUMENT:
					return endDocument();
				case XMLStreamConstants.START_ELEMENT:
					return startElement(event);
				case XMLStreamConstants.END_ELEMENT:
					return endElement(event);
				case XMLStreamConstants.CHARACTERS:
					characters(event.asCharacters().getData());
					break;
                case XMLStreamConstants.COMMENT:
                    // Kommentare überlesen. Skip comments. 20101207MS
                    break;
				default: throw new FssRuntimeException(
						"unbekanntes Event " + event);
			}
		}
	}
	
	// Start des Dokuments
	private TokenInfo startDocument() {
		resetTextbuf();
		return new TokenInfo(lnno, Token.START_DOC, null, null);
	}
	// Ende des Dokuments
	private TokenInfo endDocument() {
		resetTextbuf();
		return new TokenInfo(lnno, Token.STOPP_DOC, null, null);
	}
	// Anfang eines Elements
	private TokenInfo startElement(XMLEvent event) {
		resetTextbuf();
		return new TokenInfo(lnno, 
				Token.START_TAG, 
				event.asStartElement().getName().getLocalPart(), 
				getAttributes(event));
	}
	// Ende eines Elements
	private TokenInfo endElement(XMLEvent event) {
		if (tokenInfo == null) {
			return null;
		}
		if (tokenInfo.token == Token.START_TAG) {
			endTextbuf();
		}
		resetTextbuf();
		return new TokenInfo(lnno, 
				Token.STOPP_TAG, 
				event.asEndElement().getName().getLocalPart(), 
				null);
	}
	// Kommentar
	private void comment(String text) {
		lnno += cntnls(text);
	}
	// White-Spaces
	private void spaces(String text) {
		lnno += cntnls(text);
	}
	// Textelement
	private void characters(String text) {
		lnno += cntnls(text);
		addTextbuf(text);
	}
	// Textpuffer zurücksetzen
	private void resetTextbuf() {
		textbuf = null;
	}
	// Ende eines Textfeldes erreicht
	private void endTextbuf() {
		if (textbuf != null && textbuf.length() > 0) {
			tokenQueue.add(new TokenInfo(lnno, Token.VALUE, textbuf.toString(), null));
		}
		resetTextbuf();
	}
	// Textelement in Puffer einfügen
	private void addTextbuf(String text) {
		if (textbuf == null) {
			textbuf = new StringBuffer();
		}
		textbuf.append(text);
	}
	// Zeilenumbrüche mitzählen
	private int cntnls(String text) {
		int n = 0;
		if (text != null) {
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (c == '\n' || c == '\r') { n++; }
			}
		}
		return n;
	}
	// Attribute zum aktuellen Element zusammenstellen
	private Attributes getAttributes(XMLEvent event) {
		if (!event.isStartElement()) {
			return null;
		}
		Iterator<?> ia = event.asStartElement().getAttributes();
		AttributesImpl attr = null;
		while (ia.hasNext()) { 
			Attribute a = (Attribute) ia.next();
			if (attr == null) {
				attr = new AttributesImpl();
			}
			attr.addAttribute(
					a.getName().getNamespaceURI(),			// uri,
					a.getName().getLocalPart(),				// localName
					a.getName().toString(),					// qName
					null, //TODO was soll denn da rein?	// type
					a.getValue()							// value
					);
		}
		return attr;
	}
	
	public String getLexem() {
		if (tokenInfo == null) {
			return null;
		} else {
			switch (tokenInfo.token) {
				case START_TAG:	
				case STOPP_TAG:
				case VALUE: 
					return tokenInfo.value;
				default: 
					return null;	
			}
		}
	}
	
	public Token getToken() {
		if (tokenInfo == null) {
			return null;
		} else {
			return tokenInfo.token;
		}
	}
	
	public Attributes getAttr() {
		if (tokenInfo == null) {
			return null;
		} else {
			return tokenInfo.attribs;
		}
	}
	
	/**
	 * überspringt alle Token, bis ein entsprechendes mit dem Wert
	 * 'value' gefunden wird. 
	 * 
	 * @param token Token, das gesucht werden soll
	 * @param value Wert des Tokens
	 * @param offset mindestens offset Token erst vorangehen
	 * @return true, falls ein passender Wert gefunden wurde.
	 */
	public boolean skipTo(Token token, String value, int offset) {
		while (offset-- > 0 && hasNext()) {
			next();
		}
		do {
			try {
				if (getToken() == token 
				&& (value == null || getLexem().equals(value))) {
					return true;
				} else {
					next();
				}
			} catch (Exception e) {
				return false;
			}
		} while (hasNext());		
		return false;
	}

	/**
	 * überspringt alle Token, bis ein entsprechendes gefunden wird. 
	 * 
	 * @param token Token, das gesucht werden soll
	 * @param offset mindestens offset Token erst vorangehen
	 * @return true, falls ein passender Wert gefunden wurde.
	 */
	public boolean skipTo(Token token, int offset) {
		return skipTo(token, null, offset);
	}

	/**
	 * überspringt alle Token, bis ein entsprechendes mit dem Wert
	 * 'value' gefunden wird. Begonnen wird die Suche erst hinter
	 * dem aktuellen Token.
	 * 
	 * @param token Token, das gesucht werden soll
	 * @param value Wert des Tokens
	 * @return true, falls ein passender Wert gefunden wurde.
	 */
	public boolean skipTo(Token token, String value) {
		return skipTo(token, value, 1);
	}
	
	public void close() {
		try {
			if (stream != null) {
				stream.close();
			}
			if (parser != null) {
				parser.close();
			}
		} catch (XMLStreamException e) {
			throw new FssRuntimeException(e);
		} catch (IOException e) {
			throw new FssRuntimeException(e);
		}
		stream = null;
		parser = null;
	}

	// aktuelle Zeilennummer
	public int getLnno() {
		if (tokenInfo == null) {
			return 0;
		} else {
			return tokenInfo.lnno;
		}
	}

	/**
	 * bestimmt den Typ des Vorlaufs: KIDS oder UIDS.
	 * Die Routine darf nur aufgerufen werden, wenn aus der Nachricht noch
	 * nichts gelesen wurde!  
	 * 
	 * @return 
	 * FAULTY, bei fehlerhaftem Vorlauf
	 * UNKNOWN, falls keine Zuordnung möglich ist
	 * sonst KIDS oder UIDS
	 * @throws XMLStreamException bei Syntaxfehlern
	 */
	public HeaderType getHeaderType() throws XMLStreamException {
		if (lnno != 0) {
			throw new FssRuntimeException(
			"getHeaderType/XmlMsgScanner darf nur aufgerufen werden," 
			+ " wenn vorher aus der Nachricht nicht gelesen wurde!");
		}
		HeaderType type = null;
		boolean inHeader = false;
		while (type == null) {
			tokenInfo = nexttok();
			tokenQueue.add(tokenInfo);

			switch (tokenInfo.token) {
				case STOPP_TAG:
					if ("Header".equals(tokenInfo.value)) {
						type = 
							inHeader ? HeaderType.UNKNOWN : HeaderType.FAULTY;
					}
					break;
				case START_TAG:
					if ("Header".equals(tokenInfo.value)) {
						inHeader = true;
					} else if (inHeader) {
						// zur Unterscheidung von KIDS und UIDS wird der
						// Receiver (alias To) benutzt, da dieser immer
						// angegeben sein muss!
						if ("To".equals(tokenInfo.value)) {
							type = HeaderType.UIDS;
						} else if ("Receiver".equals(tokenInfo.value)) {
							type = HeaderType.KIDS;
						}
					}
					break;
				case STOPP_DOC:
					type = inHeader ? HeaderType.UNKNOWN : HeaderType.MISSING;
					break;
				default: continue;	
			}
		}
		tokenInfo = null;
		return type;
	}
	
	public int getMsgno() {
		return msgno;
	}
	public void incMsgno() {
		msgno++;
	}
	public int getErrors() {
		return errors;
	}
	public void incErrors(int errors) {
		this.errors += errors;
	}
	public void incErrors() {
		incErrors(1);
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (TokenInfo info : tokenQueue) {
			sb.append(info);
			sb.append('\n');
		}
		return sb.toString();
	}
}



