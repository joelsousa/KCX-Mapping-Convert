package com.kewill.kcx.component.mapping.xml;

import java.sql.Timestamp;
import java.util.Calendar;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.service.LogService;
import com.kewill.kcx.util.KCXProperties;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Module       : UnpackContentComponent<br>
 * Created      : Dec 2008<br>
 * Description 	: this component unpacks data from a KCX content node and converts it back from base64. 
 * 
 * @author richard.holt
 * @version 1.0.00
 */
public class UnpackContentComponent extends LogService implements Callable {

	public Object onCall(MuleEventContext muleEventContext) throws Exception {
//		AuditUtils.dispatchAuditEventPayload(muleEventContext,
//		        "Unpack Content Start : " + muleEventContext.getService().getName(),
//		        (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());
        Utils.addAuditEvent(muleEventContext,
                "Unpack Content Start : " + muleEventContext.getService().getName(),
                (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());

		Document document = DocumentHelper.parseText(muleEventContext.transformMessageToString());

		Node content = (Node) document.getRootElement().selectSingleNode(KCXProperties.CONTENT);
		Object result = null;
		if (content != null) {
			result = Base64.decode(content.getText());
		} else {
			logger.warn("No content node to unpack returning original payload");
			result = muleEventContext.transformMessage();
		}
//		AuditUtils.dispatchAuditEventPayload(muleEventContext,
//		        "Unpack Content Stop : " + muleEventContext.getService().getName(),
//		        (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());
        Utils.addAuditEvent(muleEventContext,
                "Unpack Content Stop : " + muleEventContext.getService().getName(),
                (new Timestamp(Calendar.getInstance().getTimeInMillis())).toString());

		return result;
	}
}
