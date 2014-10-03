/*
 * Funktion    : GenUidsExpDat.java
 * Titel       :
 * Erstellt    : 16.12.2008
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
package com.kewill.kcx.component.mapping.xml;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.uids.aes.BodyExportDeclarationUids;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: GenUidsExpDat<br>
 * Erstellt		: 16.12.2008<br>
 * Beschreibung	: Genriert ExportDeclarations.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class GenUidsExpDat {
    private static GenUidsExpDat generator        = null;
    
    private UidsHeader      uidsHeader      = null;
    
    private MsgExpDat       msgExpDat       = new MsgExpDat();;

    private XMLStreamWriter writer          = null;
    private StringWriter    xmlOutputString = new StringWriter();
    private XmlUtils        xmlUtils        = null;
    
    public static void main(String[] args) {
        generator = new GenUidsExpDat();
        generator.setUp();
        generator.generateExportDeclaration();
    }
    
    private void setUp() {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        xmlOutputString = new StringWriter();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            xmlUtils = new XmlUtils(writer);
            writer.writeStartDocument();
            xmlUtils.openElement("soap:Envelope");
            xmlUtils.setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
        } catch (XMLStreamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        uidsHeader = new UidsHeader(writer);
        setUidsHeader();
        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setDirection(EDirections.CustomerToCountry);
        commonFieldsDTO.setProcedure("Export");
        uidsHeader.writeHeader(commonFieldsDTO);
        msgExpDat = new MsgExpDat();
        setKidsMessage();
    }
    
    private void generateExportDeclaration() {
        BodyExportDeclarationUids body = null;
        try {
            body   = new BodyExportDeclarationUids(writer);
            body.setMsgKids(msgExpDat);
            body.writeBody();
            xmlUtils.closeElement();  // soap:Envelope
            writer.writeEndDocument();
            writer.flush();
            writer.close();
            Utils.log("(MsgExportDeclaration getMessage) Msg = " + xmlOutputString.toString());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void setUidsHeader() {
        uidsHeader.setTo("-");
        uidsHeader.setFrom("-");
        uidsHeader.setMsgid("-");
        uidsHeader.setSentat("-");
        uidsHeader.setInreplyto("-");
        uidsHeader.setAct("-");
    }
    
    private void setKidsMessage() {
        msgExpDat.setMsgName("-");     
        msgExpDat.setAnnotation("-");        
        msgExpDat.setAreaCode("-");
        msgExpDat.setAuthorizationNumber("-");                 
        //msgExpDat.setClerk("-");     
        msgExpDat.setDeclarantIsConsignor("-");       
        msgExpDat.setDispatchCountry("-");              
        msgExpDat.setDestinationCountry("-");               
        msgExpDat.setGrossMass("-");
        msgExpDat.setIntendedOfficeOfExit("-");       
        msgExpDat.setOrderNumber("-");
        msgExpDat.setCustomsOfficeExport("-");
        msgExpDat.setCustomsOfficeForCompletion("-"); 
        msgExpDat.setPaymentType("-");                         
        msgExpDat.setPreviousProcedure("-");               
        msgExpDat.setReferenceNumber("-");         
        msgExpDat.setShipmentNumber("-");                      
        msgExpDat.setSituationCode("-");                       
        msgExpDat.setTransportInContainer("-");
        msgExpDat.setTypeOfPermit("-");       
        msgExpDat.setCorrectionCode("-");
  
        PlaceOfLoading  loading = new PlaceOfLoading();
        msgExpDat.setPlaceOfLoading(loading);
        
        TransportMeans inland = new TransportMeans();
        TransportMeans border = new TransportMeans();
        TransportMeans departure = new TransportMeans();
        msgExpDat.setTransportMeansInland(inland);
        msgExpDat.setTransportMeansBorder(border);
        msgExpDat.setTransportMeansDeparture(departure);       
      
        Container container = new Container();        
        msgExpDat.setContainer(container);

        msgExpDat.setUCRNumber("-");
        msgExpDat.setUCROtherSystem("-");
                
        Business business = new Business();
        msgExpDat.setBusiness(business);
        Route trRoute;     
        Seal  seal;
        ExportRefundHeader expRefHeader;
        LoadingTime loadingTime;
               
        Party consignor;
        Party consignee;
        Party declarant;
        Party agent;
        Party subcontractor;
        ContactPerson contact;
              
        IncoTerms incoTerms;
        Document document; 
        List <Document>documentList;
        PreviousDocument prDocument;
        List <PreviousDocument>previousDocumentList;         
        
        MsgExpDatPos goodsItem;
        List <MsgExpDatPos>goodsItemList;
        
    }
}
