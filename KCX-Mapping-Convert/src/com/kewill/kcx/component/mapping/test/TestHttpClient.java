/*
 * Funktion    : TestHttpClient.java
 * Titel       :
 * Erstellt    : 23.12.2008
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

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

/**
 * Modul		: TestHttpClient<br>
 * Erstellt		: 23.12.2008<br>
 * Beschreibung	: Send test message to a HTTP address. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class TestHttpClient {

    private TestHttpClient() { }
    
    public static void main(String[] args) throws Exception {
        // Target URL
        String url = "http://172.16.8.105:1234/kids";
        // Get file to be posted
        String strXMLFilename = "data/kids/test/DE01DE01215820081130230000035.xml";
        File input = new File(strXMLFilename);        
        
        // Prepare HTTP post
        PostMethod post = new PostMethod(url);
        // Request content will be retrieved directly
        // from the input stream
        RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=ISO-8859-1");
        post.setRequestEntity(entity);
        // Get HTTP client
        HttpClient httpclient = new HttpClient();
        // Execute request
//        try {
            int result = httpclient.executeMethod(post);
            // Display status code
            System.out.println("Response status code: " + result);
            // Display response
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
            // Warten damit durch das Programmende die Connection nicht vorzeitig geschlossen wird.
            Thread.sleep(5);
//        } finally {
//            // Release current connection to the connection pool once you are done
//            post.releaseConnection();
//        }        
        
    }

}
