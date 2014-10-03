package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module : SealID<br>
 * Date created : 11.10.2010<br>
 * Description : Contains the Seal Data with all Fields used in KIDS.
 * 
 * @author Paul
 * @version 1.0.00
 */
public class SealID_unused extends KCXMessage {

	private List<String> sealsIdentityList;
	private List<String> sealsIdentityLngList;

	private int sealsIdIndex = 0;
	private boolean debug = false;

	public SealID_unused() {
		super();
	}

	public SealID_unused(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ESealID {
		// Kids 				//UIDS
		SealsIdentity, 			// SealsIdentity
		SealsIdentityLng;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ESealID) tag) {
			default:
				return;
			}
		} else {

			switch ((ESealID) tag) {
			case SealsIdentity:
				if (sealsIdentityList == null) {
					sealsIdentityList = new ArrayList<String>();
					setSealsIdIndex(0);
				}
				sealsIdentityList.add(value);
				break;

			case SealsIdentityLng:
				if (sealsIdentityLngList == null) {
					sealsIdentityLngList = new ArrayList<String>();
				}
				sealsIdentityLngList.add(getSealsIdIndex(), value);
				setSealsIdIndex(getSealsIdIndex() + 1);
				break;
				
			default:
				break;

			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return ESealID.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public int getSealsIdIndex() {
		return sealsIdIndex;
	}

	public void setSealsIdIndex(int sealsIdIndex) {
		this.sealsIdIndex = sealsIdIndex;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public List<String> getSealsIdentityList() {
		return sealsIdentityList;
	}

	public void setSealsIdentityList(List<String> sealsIdentityList) {
		this.sealsIdentityList = sealsIdentityList;
	}

	public List<String> getSealsIdentityLngList() {
		return sealsIdentityLngList;
	}

	public void setSealsIdentityLngList(List<String> sealsIdentityLngList) {
		this.sealsIdentityLngList = sealsIdentityLngList;
	}

	public boolean isEmpty() {
		return ((this.sealsIdentityList == null) &&
				(this.sealsIdentityLngList == null));
	}
}
