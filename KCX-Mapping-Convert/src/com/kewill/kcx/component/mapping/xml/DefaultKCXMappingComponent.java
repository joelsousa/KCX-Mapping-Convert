package com.kewill.kcx.component.mapping.xml;

import java.sql.Timestamp;
import java.util.Calendar;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.util.Base64;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.service.LogService;
import com.kewill.kcx.util.AuditUtils;
import com.kewill.kcx.util.KCXProperties;

/**
 * Module       : DefaultKCXMappingComponent<br>
 * Created 		: Nov 2008<br>
 * Description 	: this component should be used when a message doesnt require mapping, it will simply take the payload
 * and embed it within a new KCX message.
 * 
 * @author richard.holt
 * @version 1.0.00
 */

public class DefaultKCXMappingComponent extends LogService implements Callable {
	
	private String primary = null;
	private String secondary = null;
	private String tertiary = null;

	public Object onCall(MuleEventContext muleEventContext) throws Exception {
//		AuditUtils.dispatchAuditEventPayload(muleEventContext, 
//		                                     "Default KCX Mapping Start : " + 
//		                                     muleEventContext.getService().getName(), 
//                                                 (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());
        Utils.addAuditEvent(muleEventContext, 
                            "Default KCX Mapping Start : " + muleEventContext.getService().getName(), 
                            (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());

		
	    //String payload = muleEventContext.transformMessageToString();
	    byte[] payload = muleEventContext.transformMessageToBytes();
        MuleMessage muleMessage = muleEventContext.getMessage();

		Document document = DocumentHelper.createDocument(DocumentHelper.createElement(KCXProperties.KCX_ROOT));
		Element root = document.getRootElement();
		Element temp;
		if (primary != null) {
			temp = root.addElement(KCXProperties.PRIMARY);
			temp.setText(primary);
		}
		if (secondary != null) {
			temp = root.addElement(KCXProperties.SECONDARY);
			temp.setText(secondary);
		}
		if (tertiary != null) {
			temp = root.addElement(KCXProperties.TERTIARY);
			temp.setText(tertiary);
		}
		
//		AuditUtils.dispatchCreateAuditPayload(muleEventContext, document.asXML());
        Utils.createAuditContext(muleEventContext, document.asXML());

		Element content = root.addElement(KCXProperties.CONTENT);
		content.addCDATA(Base64.encodeBytes(payload));
		
		//Document sourceDocument = DocumentHelper.parseText(payload);
		//content.add((Element)sourceDocument.getRootElement().clone());
		
		
//		AuditUtils.dispatchAuditEventPayload(muleEventContext, 
//		                                    "Default KCX Mapping Stop : " + 
//		                                    muleEventContext.getService().getName(), 
//                                                (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());
        Utils.addAuditEvent(muleEventContext, 
                            "Default KCX Mapping Stop : " + muleEventContext.getService().getName(), 
                            (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());
		
		muleMessage.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);

		return document.asXML();
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

	public String getTertiary() {
		return tertiary;
	}

	public void setTertiary(String tertiary) {
		this.tertiary = tertiary;
	}

}
