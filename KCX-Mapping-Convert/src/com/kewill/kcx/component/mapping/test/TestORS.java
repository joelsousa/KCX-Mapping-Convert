/*
 * Funktion    : TestORS.java
 * Titel       :
 * Erstellt    : 30.10.2008
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.test;

import junit.framework.TestCase;

import org.mule.MuleServer;
import org.mule.api.MuleContext;


public class TestORS extends TestCase {

    public TestORS(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testORS() {
//      new DefaultInboundEndpoint(Connector connector,
//      EndpointURI endpointUri,
//      List transformers,
//      List responseTransformers,
//      String name,
//      Map properties,
//      TransactionConfig transactionConfig,
//      Filter filter,
//      boolean deleteUnacceptedMessage,
//      EndpointSecurityFilter securityFilter,
//      boolean synchronous,
//      boolean remoteSync,
//      int remoteSyncTimeout,
//      String initialState,
//      String endpointEncoding,
//      MuleContext muleContext,
//      ConnectionStrategy connectionStrategy), 

        try {
//            MuleMessage message = new DefaultMuleMessage("Message");
//            MuleSession session = new DefaultMuleSession(new DirectService(), null); 
//            MuleEvent   event   = new DefaultMuleEvent(message, null, session, false);
//            Utils.getCustomer(new DefaultMuleEventContext(event));
            MuleServer server = new MuleServer("conf/BOB-ACME/kcx-bob-to-client-config.xml");
            server.start(true, false);
            MuleContext muleContext = MuleServer.getMuleContext();
//            Utils.getCustomer(muleContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
