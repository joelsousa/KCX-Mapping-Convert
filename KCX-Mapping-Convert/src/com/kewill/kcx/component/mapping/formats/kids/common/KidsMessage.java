/*
 * Funktion    : KidsMessage.java
 * Titel       :
 * Erstellt    : 27.08.2008
 * Author      : Kewill-CSF GmbH
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 * --------------------
 * Changes 
 * --------------------
 * Author      : Kron
 * Date        : 24.09.2008
 * Label       : CK080924
 * Description : writeElement-method changed (dont write empty tags)
 * 				 new method writeElements (write empty tags as well)
 *
 * 

 * Author      : krzoska
 * Date        : 12.11.2008
 * Label       : AK20081112
 * Description : IntendedOfficeOfExit added
 * 	
 * Author      : EI
 * Date        : 06.03.2009
 * Label       : EI20090306
 * Description : writeAddressSubcontractor => writeCodeElement
 * 
 * Author      : EI
 * Date        : 22.04.2009
 * Label       : EI20090422
 * Description : removed writeCustomsOffices(...) 
 * 			   : new Method writeForwarder(Party party)	
 *             : removed TsADR setAdr(...)		
 *             
 * Author      : E.Iwaniuk
 * Date        : 08.05.2009
 * Label       : EI20090508
 * Description : check if Utils.isStringEmpty(...) for writeDateToString... and writeStringToDate...

 *
 *
 * Author      : krzoska
 * Date        : 14.05.2009
 * Label       : AK20090514
 * Description : writeDateToString( instead of writeDateTimeToString 
 *             : in writeDocument(...)  
 *             : in writeDate... date.trim() before check length of date
 *             
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson extended: city, date    
 * 
 * Author      : E.Iwaniuk
 * Date        : 08.02.2010
 * Label       : EI20100208
 * Description : new Member for UK

 * Author      : krzoska
 * Date        : 10.05.2010
 * Label       : AK20100510
 * Description : to write a single coded element with an attribute 
 *
 * Author      : iwaniuk
 * Date        : 26.05.2010
 * Label       : EI20100526
 * Description : writeDateToString, writeTimeToString for kidsToKids was wrong,
 *             : because Kids Date/Time is not formating, all Tags are Strings
 *
 * Author      : E.Iwaniuk
 * Date        : 11.08.2010
 * Label       : EI20100811
 * Description : several new member (all from xsd for ExpRel) 
 * 
 * Author      : krzoska
 * Date        : 12.08.2010
 * Label       : AK20100812
 * Description : output of new members in ExportRefundItem : writeExportRefundItem 
 *               ApplyFor , InAdvance, CBT, Prefinancing, ApplicationForm, 
 *               ApplicationDate,SimplifiedDeclaration,SimplifiedDeclarationDate
 *               
 * Author      : krzoska
 * Date        : 13.08.2010
 * Label       : AK20100813
 * Description : output of new tags in Ingredients : writeIngredients
 *               ApplyFor, Measure1, Measure2, Beneficiary , Supplier 
 *               
 *
 * Author      : krzoska 
 * Date        : 18.01.2011
 * Label       : AK20110118-2 
 * Description : missing null pointer validation

 *
 *   			
 *
 **
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 

 *
 *   			

 */ 

package com.kewill.kcx.component.mapping.formats.kids.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgUidsPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Cap;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentU;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.GoodsIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TypeOfDeclaration;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.db.KcxNoDataFoundException;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KidsMessage<br>
 * Erstellt		: 04.11.2008<br>
 * Beschreibung	: Fields and methods to write a KidsMessage.
 * 
 * @author ???
 * @version 1.0.00
 */
public class KidsMessage  {
	public 	UidsHeader 	uidsHeader;
	public 	KidsHeader	kidsHeader;
    public  KcxEnvelope kcxHeader;
    
	public String kidsMessageName = "";	// MessageName has to be set in the constructor of the subClasses	
	public String msgID = "";           // MessageID die aus der FSS(fssfilename) kommt, genutzt beim FssToKids
	
    protected XMLStreamWriter writer;    
    protected String encoding = "UTF-8";
   
    private CommonFieldsDTO commonFieldsDTO;

    //iwa 20080924
    protected void writeElement(String tag, String value) throws XMLStreamException {
    	if (value != null) {
    	   	if (!value.trim().equals("")) {
    	   		writer.writeStartElement(tag);
                writer.writeCharacters(value.trim());  //AK20081119
                writer.writeEndElement();                
    	   	}
		} 
    }
  //EI20140121:
    protected void writeElementWithNewline(String tag, String value) throws XMLStreamException {
    	if (value != null) {
    	   	if (!value.trim().equals("")) {
    	   		writer.writeStartElement(tag);
                writer.writeCharacters(value.trim());  //AK20081119
                writer.writeEndElement();               
                writer.writeCharacters(Utils.LF);
    	   	}
		} 
    }
    //EI20140121:
    protected void writeElementAllWithNewline(String tag, String value) throws XMLStreamException {
    	if (value != null) {
    	   	if (!value.trim().equals("")) {
    	   		writer.writeStartElement(tag);    	   	    
    	   		writer.writeCharacters(Utils.LF);
                writer.writeCharacters(value.trim());  //AK20081119              
                writer.writeCharacters(Utils.LF);
                writer.writeEndElement();                
                writer.writeCharacters(Utils.LF);
    	   	}
		} 
    }
    //iwa 20080924
    protected void writeElements(String tag, String value) throws XMLStreamException {
    	Utils.log("(KidsMessage writeElement) tag = " + tag);
        Utils.log("(KidsMessage writeElement) value = " + value);
        if (value == null || value.trim().equals("")) {
            writer.writeEmptyElement(tag);
        } else {
        	writeElement(tag, value);
        }
	} 
        
    public void writeCodeElement(String tag, String value, String kcxCodeID, 
                                             String from, String to) throws XMLStreamException {
   		String tmpval;
   		tmpval = Utils.getKCXCodeFromValueCodeIdFromTo(value, kcxCodeID, from, to);
    	Utils.log("(KidsMessage writeCodeElement) alter Wert" + value + " neuer Wert " + tmpval);
    	writeElement(tag, value);
    }
    
    public void writeCodeElement(String tag, String value, String kcxCodeID) throws XMLStreamException {
        if (value != null) {
            if (!value.trim().equals("")) {
                String targetValue = null;
                //Utils.log("(KidsMessage writeCodeElement) " + tag + " kidsHeader.getMap() = " + kidsHeader.getMap());
                //AK20110118-2 missing null pointer validation  
                if (kidsHeader != null && kidsHeader.getMap() != null && kidsHeader.getMap().equals("1")) {
                    try {
                        targetValue = Utils.getKCXCodeFromValueCodeIdFromTo(value, kcxCodeID, 
                                                                            kidsHeader.getMapFrom(), 
                                                                            kidsHeader.getMapTo());
                    } catch (KcxNoDataFoundException e) {
                        Utils.log("(KidsMessage writeCodeElement) " + e.getMessage());
                        // Wird in der DB nichts gefunden wird ein Leerstring eingetragen
                        // code not found in database so output blank
                        //EI20130719: JIRA-KCX179: 
                        // targetValue = " ";
                        targetValue = value;  //EI20130719
                    }
                } else {
                    targetValue = value;
                }
              //Utils.log("(KidsMessage writeCodeElement) alter Wert " + value + " neuer Wert " + targetValue);
                if (Utils.isStringEmpty(targetValue)) {  //EI20130801: JIRA-KCX179:
                	targetValue = value;
                }
                writeElement(tag, targetValue);
            }
        }
    }
    
    //EI20100506: 
    protected void writeElementWithAttribute(String tag, String value, String attrName, String attrValue) 
    										throws XMLStreamException {
        if (value != null) {
        	if (!value.trim().equals("")) {
        		writer.writeStartElement(tag);
        		if (attrValue != null) {   //EI20110926
        			writer.writeAttribute(attrName, attrValue);
        		}
        		writer.writeCharacters(value);        		
        		writer.writeEndElement();
        	}
        }
    }    

    
    //AK20100510
    public void writeCodeElementWithAttribute(String tag, String value, String kcxCodeID, 
    		                                  String attributeName, String attributeValue) throws XMLStreamException {
        if (value != null) {
            if (!value.trim().equals("")) {
                String targetValue = value;
                String targetAttribute = attributeValue;
                Utils.log("(KidsMessage writeCodeElementWithAttribute) kidsHeader.getMap() = " + kidsHeader.getMap());
                if (kidsHeader.getMap() != null && kidsHeader.getMap().equals("1")) {
                	targetAttribute = kidsHeader.getMapTo();
                    try {
                        //targetValue = Utils.getKCXCodeFromValueCodeIdFromTo(value, kcxCodeID, 
                        //                                                    kidsHeader.getMapFrom(), 
                        //                                                    kidsHeader.getMapTo());
                        targetValue = Utils.getKCXCodeFromValueCodeIdFromTo(value, kcxCodeID, 
                        								attributeValue, kidsHeader.getMapTo());                        
                    } catch (KcxNoDataFoundException e) {
                        Utils.log("(KidsMessage writeCodeElementWithAttribute) " + e.getMessage());
                        targetValue = " ";
                    }
                } 
                
                Utils.log("(KidsMessage writeCodeElementWithAttribute) alter Wert " + value + 
                		" neuer Wert " + targetValue);
                writeElementWithAttribute(tag, targetValue, attributeName,  targetAttribute);
            }
        }
    }


    protected void setAttribute(String tag, String value) throws XMLStreamException {
        writer.writeAttribute(tag, value);
    }
    
    protected void openElement(String tag) throws XMLStreamException {
//        Utils.log("(KidsMessage openElement) tag = " + tag);
        writer.writeStartElement(tag);
    }

    protected void closeElement() throws XMLStreamException {
//        Utils.log("(KidsMessage closeElement)");
        writer.writeEndElement();
    }
    
    public UidsHeader getUidsHeader() {
		return uidsHeader;
	}

	public void setUidsHeader(UidsHeader uidsHeader) {
		this.uidsHeader = uidsHeader;
	}

	public KidsHeader getKidsHeader() {
		return kidsHeader;
	}

	public void setKidsHeader(KidsHeader kidsHeader) {
		this.kidsHeader = kidsHeader;
	}

    
 // from Uids to Kids beginn:	
  
	public void writeAreacodeTypeofpermit(TypeOfDeclaration argument) throws XMLStreamException {
		if (argument != null) {
			writeElement("AreaCode", argument.getRegionCode());
			writeElement("TypeOfPermit", argument.getProcedureCode());
		}
	}
	
    public void writeMeansOfTransport(String tag, TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (Utils.isStringEmpty(tag)) {
    	    return;
    	}
    	
    	String mode = argument.getTransportMode();
    	String type = argument.getTransportationType();   
	    String nr = argument.getTransportationNumber();  
	    String country = argument.getTransportationCountry();
	    String place = argument.getPlaceOfLoading();
	    String placeCode = argument.getPlaceOfLoadingCode();
    	
        if (tag.equals("Inland")) {            	
        	if (!Utils.isStringEmpty(mode) || !Utils.isStringEmpty(type) ||
        		!Utils.isStringEmpty(nr)   || !Utils.isStringEmpty(country) ||
            	!Utils.isStringEmpty(place)) {              	
     	    	openElement("MeansOfTransportInland");   
     	    	// C. K. 28.01.2009 Codierung 
    	    	// writeElement("TransportMode", mode);
//    	    	writeCodeElement("TransportMode", mode, "KCX0027", "DE", "KCX_CODE");
                writeCodeElement("TransportMode", mode, "KCX0027");
                writeCodeElement("TransportationType", type, "KCX0004");
                writeElement("TransportationNumber", nr); 
                writeElement("TransportationCountry", country);         //EI20100811 
                writeElement("PlaceOfLoading", place);                  //EI20100811 
                writeElement("PlaceOfLoadingCode", placeCode);          //EI20100811 
     	    	closeElement();   
            }
        } else if (tag.equals("Departure")) {             	        	
            if (!Utils.isStringEmpty(mode) || !Utils.isStringEmpty(type) ||
                !Utils.isStringEmpty(nr)   || !Utils.isStringEmpty(country) ||
            	!Utils.isStringEmpty(place)) { 
        	    openElement("MeansOfTransportDeparture");	        	    	
        	    	// C. K. 28.01.2009 Codierung         	    	
        	    	writeCodeElement("TransportMode", mode, "KCX0027");        	    	
        	    	writeCodeElement("TransportationType", type, "KCX0004");
        	    	writeElement("TransportationNumber", nr);
        	    	writeElement("TransportationCountry", country);
        	    	writeElement("PlaceOfLoading", place);                  //EI20100811 
        	    	writeElement("PlaceOfLoadingCode", placeCode);          //EI20100811 
        	    closeElement(); 
            }
        } else if (tag.equals("Border")) {        	        	
        	if (!Utils.isStringEmpty(mode) || !Utils.isStringEmpty(type) || 
                !Utils.isStringEmpty(nr)   || !Utils.isStringEmpty(country) ||
        		!Utils.isStringEmpty(place)) { 
             		openElement("MeansOfTransportBorder");	
		             // C. K. 28.01.2009 Codierung 
    	    		// writeElement("TransportMode", mode);
    	    		writeCodeElement("TransportMode", mode, "KCX0027");
    	    		// writeElement("TransportationType", type);
    	    		writeCodeElement("TransportationType", type, "KCX0004");
             	    writeElement("TransportationNumber", nr);
             	    writeElement("TransportationCountry", country);
             	    writeElement("PlaceOfLoading", place);                  //EI20100811 
             	    writeElement("PlaceOfLoadingCode", placeCode);          //EI20100811 
           		closeElement(); 
           }
        } else if (tag.equals("MeansOfTransport")) {
        	if (!Utils.isStringEmpty(mode) || !Utils.isStringEmpty(type) ||
                !Utils.isStringEmpty(nr)   || !Utils.isStringEmpty(country) ||
                !Utils.isStringEmpty(place)) {  
                 	openElement("MeansOfTransport");	
    		             // C. K. 28.01.2009 Codierung 
        	    		// writeElement("TransportMode", mode);
        	    		writeCodeElement("TransportMode", mode, "KCX0027");
        	    		writeElement("PlaceOfLoading", place);
        	    		writeCodeElement("TransportationType", type, "KCX0004");
                 	    writeElement("TransportationNumber", nr);
                 	    writeElement("TransportationCountry", country);
               		closeElement(); 
        	}
        } else {
            return;
        }
    }   	

    
    public void writePlaceOfLoading(PlaceOfLoading argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	    	    
    	String code = argument.getCode();
    	String str = argument.getStreet();
    	String city = argument.getCity();
    	String plz = argument.getPostalCode();
    	
    	if (Utils.isStringEmpty(code) && Utils.isStringEmpty(str) &&
    		Utils.isStringEmpty(city) && Utils.isStringEmpty(plz)) {
    		return; 
    	}
    	
    	openElement("PlaceOfLoading");
    		writeElement("Code", code);    	
    		writeElement("Street", str);
    		writeElement("PostalCode", plz);
    		writeElement("City", city);    	    	    
    	    writeElement("AgreedLocationOfGoods", argument.getAgreedLocationOfGoods());
    	closeElement();
    } 
/*
    public void writeCustomsOffices(CustomsOffices argument, String message) throws XMLStreamException {
    	if (argument == null) {
    	    return;  
    	}
    		
    	if (message.equals("ExtNot")) {
    		writeElement("IntendedOfficeOfExit", argument.getIntendedOfficeOfExit());
    		writeElement("RealOfficeOfExit", argument.getRealOfficeOfExit());
    	} else if (message.equals("ExpDat")) {
    		writeElement("CustomsOfficeExport", argument.getCustomsOfficeExport());
    		writeElement("CustomsOfficeForCompletion", argument.getCustomsOfficeForCompletion());
    		writeElement("IntendedOfficeOfExit", argument.getRealOfficeOfExit());  //AK20081112
    	} else if (message.equals("ExtEam")) {
            writeElement("CustomsOfficeExport", argument.getCustomsOfficeExport());
    		writeElement("CustomsOfficeForCompletion", argument.getCustomsOfficeForCompletion());
    	} else {
    		writeElement("RealOfficeOfExit", argument.getRealOfficeOfExit());
    		writeElement("CustomsOfficeExport", argument.getCustomsOfficeExport());
    		writeElement("CustomsOfficeForCompletion", argument.getCustomsOfficeForCompletion());
    	}
    }
   */
    public void writeTransportationRoute(Route argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;   
    	}
    	if (argument.getCountryList() == null) {
    		return;   
    	}
    	
    	int size = argument.getCountryList().size();
    	if (size > 0) {
    		openElement("TransportationRoute");	
    		for (int i = 0; i < size; i++) {    			     			
     			writeElement("Country", (String) argument.getCountryList().get(i));
    		}
    		closeElement();	
    	}    
    }      
    public void writeBusiness(Business argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	String code  = argument.getBusinessTypeCode(); 
      	String price = argument.getInvoicePrice(); 
     	String curr = argument.getCurrency();  
      	if (Utils.isStringEmpty(code) && Utils.isStringEmpty(price) && Utils.isStringEmpty(curr)) {            
      	    return; 
      	}
      	
    	openElement("Business");    
// 		C. K. 28.01.2009 Codierung 
//    	writeElement("BusinessTypeCode", code);
    	writeCodeElement("BusinessTypeCode", code, "KCX0022");
    	writeElement("InvoicePrice", price);
    	writeElement("Currency", curr);
    	closeElement();
    }
    //public void writeSealsU(SealsUiwa seal, HeaderExtensionsU header, String message) throws XMLStreamException {   
    public void writeSealsU(Seal seal, HeaderExtensions header, String message) throws XMLStreamException {
    	
    	if ((seal == null) && (header == null)) {
    	    return;
    	}
    	
    	String type = ""; 
      	String quantity = ""; 
      	String flag = ""; 
      	String stock = ""; 
      	List list = null;
       	
      	if (seal != null) {
      		type = seal.getKind(); 
      		quantity = seal.getNumber();
      		//list = seal.getSealNumbersList();
      		list = seal.getSealNumberList();
      	}
      	if (header != null) {
      		flag = header.getTydenSealsFlag(); 
      		if (!message.equals("ExpRel")) {
      		    stock = header.getTydenSealsStockFlag(); 
      		}
      	}
      	
      	writeSeal(type, quantity, flag, stock, list);
   } 
   // for FSStoKIDS
    public void writeSeals(Seal argument, String message) throws XMLStreamException { 
    	
    	if (argument == null) {
    	    return;
    	}
    	
    	String kind = argument.getKind();
    	String nr = argument.getNumber();
    	String flag = argument.getUseOfTydenseals();
    	String stock = "";
    	if (!message.equals("ExpRel")) {
    	    stock = argument.getUseOfTydensealStock();
    	}
    	List list = argument.getSealNumbersList();
    	
    	writeSeal(kind, nr, flag, stock, list);    	  
    }
    private void writeSeal(String kind, String nr, String flag, String stock, List list) throws XMLStreamException {

    	int listSize = 0;
    	SealNumber sealNumbers = null;  
    	if (list != null) {
    	    listSize = list.size();
    	}
    	if (Utils.isStringEmpty(kind) && Utils.isStringEmpty(nr) &&
            Utils.isStringEmpty(flag) && Utils.isStringEmpty(stock) &&
           (listSize == 0)) {
    	    return; 
    	}
    	
    	openElement("Seal");  
// 			C. K. 28.01.2009 Codierung 
//			writeElement("Kind", kind);      	
//	   		writeCodeElement("Kind", kind,"KCX0002","DE","KCX_CODE");
            writeCodeElement("Kind", kind, "KCX0002");
			writeElement("Number", nr);                        
			writeElement("UseOfTydenseals", flag);
			writeElement("UseOfTydensealStock", stock);
			
			for (int i = 0; i < listSize; i++) {
				sealNumbers = (SealNumber) list.get(i);
				if (sealNumbers != null) {
					openElement("SealNumbers");
					   writeElement("Number", sealNumbers.getNumber());
					   writeElement("SealingParty", sealNumbers.getSealingParty());
					closeElement(); //SealNumbers 
				 }
			}
	
	//V1.0	AK 	for (int i = 0; i < listSize; i++) {   
	//			writeElement("SealNumber", (String) list.get(i));
	//		}
		closeElement();    	
    }
    
    public void writeExportRefundHeader(ExportRefundHeader argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	String text = argument.getText();
    	String payment = argument.getApplicationType();
    	String bank =  argument.getPaymentUids();
    	String assignee = argument.getAssignee();
    	String guarantie = argument.getGuarantee();    	
    	String code = argument.getReservationCode();
    	String country = argument.getDestinationCountry();
    	
    	if (Utils.isStringEmpty(text) && Utils.isStringEmpty(payment) &&
    	    Utils.isStringEmpty(bank) && Utils.isStringEmpty(assignee) &&
    	    Utils.isStringEmpty(guarantie) && Utils.isStringEmpty(code) &&  
    	    Utils.isStringEmpty(country)) {
    	    return; 
    	}
 			       	   	 
       	openElement("ExportRefundHeader");  
       		writeElement("Text", text);      	
// 			C. K. 28.01.2009 Codierung 
//			writeElement("PaymentType", type);      	
	   		writeCodeElement("PaymentType", payment, "KCX0019");
       		writeElement("BankNumber", bank);    
       		writeElement("Assignee", assignee);    
       		writeElement("Guarantee", guarantie);   
// 			C. K. 28.01.2009 Codierung 
//     		writeElement("ReservationCode", code);    
       		writeCodeElement("ReservationCode", code, "KCX0001");
       		writeElement("DestinationCountry", country);    
       	closeElement();       	
    }    

    public void writeLoadingTime(LoadingTime argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	String begin = argument.getBeginTime();  
     	String end = argument.getEndTime();
     	     	
     	if (Utils.isStringEmpty(begin) && Utils.isStringEmpty(end)) {
     	    return; 
     	}
     	
     	openElement("LoadingTime");
     	if (!Utils.isStringEmpty(begin)) {
     		begin.trim();   //EI20090514	
     		if (begin.length() > 12) {
     		    writeDateTimeToString("BeginTime", begin);
     		} else {
     		    writeElement("BeginTime", begin);
     		}
     	}
     	if (!Utils.isStringEmpty(end)) {
     		end.trim();     //EI20090514
     		if (end.length() > 12) {
     		    writeDateTimeToString("EndTime", end);
     		} else {
     		    writeElement("EndTime", end);
     		}
     	}    	     	
        closeElement();
    }  
    
    public void writeForwarder(Trader party, String message)  throws XMLStreamException  {
    	if (party == null) {
    	    return;
    	}
    	
    	String vatid = party.getVATID();
    	
    	if (Utils.isStringEmpty(vatid)) {
    	    return;    	
    	}
    	
    	if (!Utils.isStringEmpty(vatid) && message.equals("ExpDat-CH")) {
    		openElement("Forwarder");
    			writeElement("VATID", vatid);
    		closeElement();
    	}    	        
    }
    public void writeForwarder(String etn, String crid) throws XMLStreamException  {
    	if (Utils.isStringEmpty(etn) && Utils.isStringEmpty(crid)) {
    	    return; 
    	}
    	
    	if (!Utils.isStringEmpty(etn)) {
    		openElement("Forwarder");
    			writeElement("ETNAddress", etn);
    		closeElement();
    	}
    	if (!Utils.isStringEmpty(crid)) {
    		openElement("ForwarderTIN");
    			writeElement("CustomerIdentifier", crid);
    		closeElement();
    	}       	
    }
    	 
    public void writeForwarder(Trader party) throws XMLStreamException  {
    	if (party == null) {
    	    return;
    	}
    	
    	String etn = party.getETNAddress();    	    	
    	String customer = party.getCustomerID();
    	   	
    	writeForwarder(etn, customer);
    }
    public void writeForwarder(Party party) throws XMLStreamException  {
    	if (party == null) {
    	    return;
    	}
    	if (party.getPartyTIN() == null) {
    	    return;
    	}
    	
    	String tin = party.getPartyTIN().getTIN();	    	
    	String customer = party.getPartyTIN().getCustomerIdentifier();
    	 
    	if (Utils.isStringEmpty(tin) && Utils.isStringEmpty(customer)) {
    		openElement("ForwarderTIN");
		    	writeElement("TIN", tin);
		    	writeElement("CustomerIdentifier", customer);
		    closeElement();
    	}
    }    
 
    public void writeParty(String person, String etn, String vatid, 
    					   String tin, String isTGAN, String customer, 
    		               Address address,  ContactPerson contact) throws XMLStreamException  {
    	if (Utils.isStringEmpty(person)) {
    	    return;
    	}

    	if (!Utils.isStringEmpty(etn) || !Utils.isStringEmpty(vatid) || address != null) {      		 
       
    		openElement(person);  
    			if (person.equals("Subcontractor")) {
	     			writeElement("VATNumber", vatid);   //EI20100811
	     			writeElement("ETNAddress", etn);    
	     		}
	     		if (address != null) {	     			
	     			writeAddress(person, address);
	     		}
	     			    	 	
	 	    closeElement();    		
    	}
    	
    	if (!Utils.isStringEmpty(tin) || !Utils.isStringEmpty(isTGAN) || !Utils.isStringEmpty(customer)) {
       		 
   	    	openElement(person + "TIN"); 
   	    	  	writeElement("TIN", tin);   
   	    	  	if (!person.equals("Consignee")) {
//   	  			C. K. 28.01.2009 Codierung 
// 	    	  		writeElement ("IsTINGermanApprovalNumber", isTGAN);
   	   	 	   		writeCodeElement("IsTINGermanApprovalNumber", isTGAN, "KCX0001"); 
   	   	 	   	}
   	    	 	writeElement("CustomerIdentifier", customer);   
   	    	closeElement(); 
       	}
       	if (!person.equals("Consignee")) {
       		writeContact(person, contact);
       	}
    	
    }
    public void writePartyF2K(String person, Party party, TIN tin, ContactPerson contact) throws XMLStreamException  {
    	//from FSS to Kids
    	String etn = "";
    	String vatid = "";
    	String tnr = "";
    	String isTGAN = "";
    	String customer = "";
    	Address address = null;
    	
    	if (Utils.isStringEmpty(person)) {
    	    return;
    	}
    	if (party == null && tin == null && contact == null) {
    	    return;  
    	}
    	
    	if (party != null) {
    		etn = party.getETNAddress();  
    		//vatid = party.getVATID();  <== VatNumber soll nicht ausgegeben werden     
    		address = party.getAddress();
    	}
    	if (tin != null) {
    		tnr = tin.getTIN();  
    		isTGAN = tin.getIsTINGermanApprovalNumber();
    		customer = tin.getCustomerIdentifier(); 
    	}
       	  
    	writeParty(person, etn, vatid, tnr, isTGAN, customer, address, contact);
    }
    public void writePartyU2K(String person, Trader argument) throws XMLStreamException  {
    	//from Uids to Kids
    	if (argument == null) {
    	    return;  
    	}
    	if (Utils.isStringEmpty(person)) {
    	    return;
    	}
  
     	String etn = "";
     	String vatid = "";
     	//String vatid = argument.getVATID();  <== VatNumber soll nicht ausgegeben werden     	
     	if (person.equals("Subcontractor")) {
            etn = argument.getETNAddress();
     	}
     		
     	String tnr = argument.getTIN();  
     	String isTGAN = argument.getCustomsID();
     	String customer = argument.getCustomerID(); 
     	Address address = argument.getAddress();
     	ContactPerson contact = argument.getContactPerson();
       	  
    	writeParty(person, etn, vatid, tnr, isTGAN, customer, address, contact);
    }
    //EI20090408:
    public void writeParty(String person, Party argument) throws XMLStreamException  {    	
    	if (argument == null) {
    		return;
    	}
    	if (Utils.isStringEmpty(person)) {
    		return;
    	}
     	if (argument.getPartyTIN() != null) {
     		writeTIN(person, argument.getPartyTIN());
     	}
      	
     	if (argument.getAddress() != null) {
     		openElement(person);     
        	    writeElement("VATNumber", argument.getVATNumber());      //EI20100811
         	    writeElement("ETNAddress", argument.getETNAddress());    //EI20100811
     		
     			writeAddress(person, argument.getAddress());     			
     		closeElement();     		
     	}
     	
     	//AK20091221  V10 Consignee without ContactPerson
    	//if (argument.getContactPerson() != null && !(person.equals("Consignee"))) {
     	//AK20091221  V11 Consignee contains ContactPerson
     	if (argument.getContactPerson() != null) {
    		writeContact(person, argument.getContactPerson());
    	}
    } 
    //EI20090408:
    public void writeTIN(String person, TIN argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	if (Utils.isStringEmpty(person)) {
    	    return;  
    	}
    	
    	String tnr = argument.getTIN();  
     	String isTGAN = argument.getIsTINGermanApprovalNumber();
     	String customer = argument.getCustomerIdentifier(); 
     	
     	if (Utils.isStringEmpty(tnr) && Utils.isStringEmpty(isTGAN) && Utils.isStringEmpty(customer)) {
     		return;
     	}
     	
    	openElement(person + "TIN");      			
			writeElement("TIN", tnr);     
			// CK 28.11.2011 writeElement("IsTINGermanApprovalNumber", isTGAN); 
			writeCodeElement("IsTINGermanApprovalNumber", isTGAN, "KCX0001"); 
			writeElement("CustomerIdentifier", customer); 
		closeElement();     	    
    }
    public void writeAddress(Address argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	String name = argument.getName();
    	String street = argument.getStreet();
    	String city = argument.getCity();
    	String poco =  argument.getPostalCode();
    	String country = argument.getCountry();
    	// New in V11
    	String countrySubEntity = argument.getCountrySubEntity();
    	String houseNumber = argument.getHouseNumber();
    	if (Utils.isStringEmpty(name) && Utils.isStringEmpty(street) &&
    	    Utils.isStringEmpty(city) && Utils.isStringEmpty(poco) &&
    		Utils.isStringEmpty(country)) {
    	    return;
    	}
    	
    	openElement("Address"); 
            writeElement("Name", name);
            writeElement("Street", street);
            writeElement("HouseNumber", houseNumber);
            writeElement("City", city);
            writeElement("PostalCode", poco);            
            writeElement("Country", country);
            writeElement("CountrySubEntity", countrySubEntity); 
        closeElement();     
    } 
    public void writeAddress(String person, Address argument) throws XMLStreamException {
    	if (argument == null) { 
    	    return;
    	}
    	if (Utils.isStringEmpty(person)) { 
    	    return;
    	}
    	    	  
    	String name = argument.getName();
    	String street = argument.getStreet();
    	String city = argument.getCity();
    	String poco =  argument.getPostalCode();    	
    	String country = argument.getCountry();
    	String countrySubEntity = argument.getCountrySubEntity();
    	String houseNumber = argument.getHouseNumber();
    	if (Utils.isStringEmpty(name) && Utils.isStringEmpty(street) &&
    	    Utils.isStringEmpty(city) && Utils.isStringEmpty(poco) &&
    		Utils.isStringEmpty(country)) {
    	    return;
    	}
    	
    	openElement("Address"); 
            writeElement("Name", name);
            writeElement("Street", street);
            writeElement("HouseNumber", houseNumber);
            writeElement("City", city);
            writeElement("PostalCode", poco);                        
            if (person.equals("Subcontractor")) { 
            	writeCodeElement("Country", country, "KCX0003"); 
            } else {
            	writeElement("Country", country);
            }
            writeElement("CountrySubEntity", countrySubEntity);
        closeElement();     
    } 
    public void writeContact(String person, ContactPerson argument) throws XMLStreamException { 
    	if (argument == null) {
    	    return;
    	}
    	
    	String tag = person + "ContactPerson";
    	if (person.equals("Contact")) {
    		tag = person;
    	}
    	String position = argument.getPosition();
    	String identity = argument.getIdentity();
    	String name = argument.getName();
    	String email = argument.getEmail();
    	String fax =  argument.getFaxNumber();
    	String phone = argument.getPhoneNumber();
    	String city = argument.getCity();
    	String date = argument.getDate();
    	
    	if (tag.equals("Contact")) {
    		if (Utils.isStringEmpty(position) && Utils.isStringEmpty(name) &&
    			Utils.isStringEmpty(email) && Utils.isStringEmpty(fax) &&
    			Utils.isStringEmpty(phone) && Utils.isStringEmpty(identity) &&
    			Utils.isStringEmpty(city) && Utils.isStringEmpty(date)) 
    			return;
    	} else {
    		if (Utils.isStringEmpty(position) && Utils.isStringEmpty(name) &&
    	    	Utils.isStringEmpty(email) && Utils.isStringEmpty(fax) &&
    	    	Utils.isStringEmpty(phone))     	    		
    	    	return;    	        	
    	}
    	
    	openElement(tag); 
    		writeElement("Position", position);
    		if (tag.equals("Contact")) {
    			writeElement("Identity", identity);
    		}
    		writeElement("Clerk", name);
    		writeElement("Email", email);
    		writeElement("FaxNumber", fax);
    		writeElement("PhoneNumber", phone);   
    		if (tag.equals("Contact")) {
    			writeElement("City", city);    //EI20090608    		   
    			if (!Utils.isStringEmpty(date)) {   //EI20090608		     		
    				date.trim();  
    				if (date.length() == 10) {
    					writeDateToString("Date", date);
    				} else {
    					writeElement("Date", date);  
    				}
    			}
    		}
    	closeElement(); 
    }	
  
    public void writeIncoTerms(IncoTerms argument) throws XMLStreamException {  
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}     	 
    	openElement("IncoTerms"); 
    		writeElement("IncoTerm", argument.getIncoTerm());
    		writeElement("Text", argument.getText());
    		writeElement("Place", argument.getPlace());
    		writeElement("LocationCode", argument.getLocationCode());
    	closeElement();
    }
    
    private void writeCommodityCode(String tarif, String teu, String ta1, 
    		                        String ta2, String add,
    		                        String add2, String add3) throws XMLStreamException {
       	if (Utils.isStringEmpty(tarif) && Utils.isStringEmpty(teu) &&
            Utils.isStringEmpty(ta1) && Utils.isStringEmpty(ta2)  &&
        	Utils.isStringEmpty(add)) {
       	    return; 
       	}
        	
        	openElement("CommodityCode");
        		writeElement("TarifCode", tarif);
        		writeElement("EUTarifCode", teu);
        		writeElement("TarifAddition1", ta1);
        		writeElement("TarifAddition2", ta2);
        		writeElement("Addition", add);
    	    closeElement();
    }
    public void writeCommodityCode(CommodityCode argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	String tarif = argument.getTarifCode(); 
    	String teu = argument.getEUTarifCode();  
    	String ta1 = argument.getTarifAddition1();  
    	String ta2 = argument.getTarifAddition2();  
    	String add = argument.getAddition();  
    	String add2 = argument.getAddition2();  
    	String add3 = argument.getAddition3();  
    	//AK20091221
    	writeCommodityCode(tarif, teu, ta1, ta2, add, add2, add3);      	
    }
    public void writeCommodityCode(MsgUidsPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	String tarif = argument.getCommodityCodeKN8();
    	String teu = argument.getCommodityCodeTARIC();  
	    String ta1 = argument.getCommodityCodeFirstAddition(); 
	    String ta2 = argument.getCommodityCodeSecondAddition();
    	String add = argument.getCommodityCodeNationalAddition(); 
	    
    	writeCommodityCode(tarif, teu, ta1, ta2, add, null, null);         		
    } 
    public void writeExportRefundItem(ExportRefundItem argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
//    	AK20100812
    	String applyFor  = argument.getApplyFor();
    	String inAdvance = argument.getInAdvance();
    	String cBT = argument.getCBT();
    	String prefinancing = argument.getPrefinancing();
    	String applicationForm = argument.getApplicationForm();
    	String applicationDate = argument.getApplicationDate();
    	String simplifiedDeclaration = argument.getSimplifiedDeclaration();
    	String simplifiedDeclarationDate = argument.getSimplifiedDeclarationDate();
    	
    	String add1 = argument.getAddition1();      
    	String add2 = argument.getAddition2();        
    	String origin = argument.getOriginCountry();   
    	String code = argument.getAmountCode();      
    	String amount = argument.getAmount();           
    	String type = argument.getTypeOfRefund();
    	String coefficient = argument.getAmountCoefficient();
    	String pa = argument.getPartA();  
    	String pb = argument.getPartB();   
    	String pc = argument.getPartC();   
    	String pd = argument.getPartD();   
    	String unit = argument.getUnitOfMeasurement();
    	List ingList = argument.getIngredientsList();
    	int sizeList = 0;
    	
    	if (ingList != null) {
    		sizeList = ingList.size();    		    
    	}
    	if (Utils.isStringEmpty(add1) && Utils.isStringEmpty(add2) && 
    		Utils.isStringEmpty(origin) && Utils.isStringEmpty(code) && 
    		Utils.isStringEmpty(amount) && Utils.isStringEmpty(type)  &&    			
    		Utils.isStringEmpty(coefficient) && Utils.isStringEmpty(unit) && 
    		Utils.isStringEmpty(pa) && Utils.isStringEmpty(pb) &&  
    		Utils.isStringEmpty(pc) && Utils.isStringEmpty(pd) && 
    		sizeList == 0 && Utils.isStringEmpty(applyFor) &&
    		Utils.isStringEmpty(inAdvance) && Utils.isStringEmpty(cBT) &&
    		Utils.isStringEmpty(prefinancing) && Utils.isStringEmpty(applicationForm) &&
    		Utils.isStringEmpty(applicationDate) && Utils.isStringEmpty(simplifiedDeclaration) &&
    		Utils.isStringEmpty(simplifiedDeclarationDate)
    		) {
                return;
    	}	
    	openElement("ExportRefundItem");
    		writeElement("Addition1", add1);      	
    		writeElement("Addition2", add2);                        
    		writeElement("OriginCountry", origin);   
    		writeElement("AmountCode", code);      	
    		writeElement("Amount", amount);  
// 			C. K. 28.01.2009 Codierung 
//    		writeElement("TypeOfRefund", type);
	   		writeCodeElement("TypeOfRefund", type, "KCX0018");
    		writeElement("AmountCoefficient", coefficient);      	
    		writeElement("PartA", pa);  
    		writeElement("PartB", pb);   
    		writeElement("PartC", pc);   
    		writeElement("PartD", pd);   
// 			C. K. 28.01.2009 Codierung
//    		writeElement("UnitOfMeasurement", unit);
    		//writeCodeElement("UnitOfMeasurement", unit, "KCX0017");
//			AK20100812 tags added
    		writeElement("ApplyFor" , applyFor);
    		writeElement("InAdvance", inAdvance);
    		writeElement("CBT", cBT);
    		writeElement("Prefinancing", prefinancing); 
    		writeElement("ApplicationForm", applicationForm);
    		writeDateToString("ApplicationDate", applicationDate);
    		writeElement("SimplifiedDeclaration", simplifiedDeclaration); 
    		writeDateToString("SimplifiedDeclarationDate", simplifiedDeclarationDate);
    		if (ingList != null) {    			
    			for (int i = 0; i < sizeList; i++) {
            		writeIngredients((Ingredients) ingList.get(i));
    			} 
    		}
    	closeElement();
    	
    }
    
    public void writeIngredients(Ingredients argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
       	String nr = argument.getSequentialNumber();      
    	String amount1 = argument.getAmount1();        
    	String coefficient = argument.getKindOfCoefficient();   
    	String amount2 = argument.getAmount2();      
    	String rate = argument.getRate();           
    	String percenrt = argument.getWeightPercent();
    	String weight = argument.getWeight();
    	String unique = argument.getUniqueFactoryNumber();  
    	String tarif = argument.getTarifNumber();   
    	String licence = argument.getLicenceNumber();   
    	String text = argument.getText(); 
    	String licenceLineNumber = argument.getLicenceLineNumber(); 
    	String licenceStatus = argument.getLicenceStatus();   
    	String licenceQuantity = argument.getLicenceQuantity();   
    	
    	String applyFor = argument.getApplyFor();
		String measure1 = argument.getMeasure1();
		String measure2 = argument.getMeasure2();
		Party beneficiary = argument.getBeneficiary();
		Party supplier = argument.getSupplier();		
    
    	if (Utils.isStringEmpty(nr) && Utils.isStringEmpty(amount1) && 
        	Utils.isStringEmpty(coefficient) && Utils.isStringEmpty(amount2) && 
        	Utils.isStringEmpty(rate) && Utils.isStringEmpty(percenrt) && 
        	Utils.isStringEmpty(weight) && Utils.isStringEmpty(unique) && 
        	Utils.isStringEmpty(tarif) && Utils.isStringEmpty(licence) && 
        	Utils.isStringEmpty(text) && Utils.isStringEmpty(applyFor) &&
        	Utils.isStringEmpty(measure1) && Utils.isStringEmpty(measure2) &&
        	beneficiary.isEmpty() && supplier.isEmpty()) {
    	    return;        		
    	}
    	    	   
    		openElement("Ingredients");
    			writeElement("SequentialNumber", nr);
    			writeElement("ApplyFor", applyFor);  //AK20100813
    			writeElement("Amount1", amount1);
//     			C. K. 28.01.2009 Codierung
//        		writeElement("KindOfCoefficient", coefficient); 
        		writeCodeElement("KindOfCoefficient", coefficient, "KCX0020");
        		writeElement("Measure1", measure1);   //AK20100813
    			writeElement("Amount2", amount2);      	
    			writeElement("Rate", rate);        
    			writeElement("Measure2", measure2);   //AK20100813 
    			writeElement("WeightPercent", percenrt);   
    			writeElement("Weight", weight);      	
    			writeElement("UniqueFactoryNumber", unique);                     
    			writeElement("TarifNumber", tarif);   
    			writeElement("LicenceNumber", licence);     
    			writeElement("licenceLineNumber", licence); //EI20100208 for UK    
    			writeElement("licenceStatus", licence);     //EI20100208 for UK
    			writeElement("licenceQuantity", licence);   //EI20100208 for UK  
    			writeElement("Text", text);
    			writeParty("Beneficiary", beneficiary);     //AK20100813
    			writeParty("Supplier", supplier);           //AK20100813
    		closeElement(); 
    	
    }
	    
    public void writeApprovedTreatment(ApprovedTreatment argument) throws XMLStreamException {   
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}    	
    	openElement("CustomsApprovedTreatment");
    		writeElement("Declared", argument.getDeclared());
    		writeElement("Previous", argument.getPrevious());    		
    		writeElement("National", argument.getNational());
    		writeElement("National2", argument.getNational2());
    		writeElement("National3", argument.getNational3());
    		writeCodeElement("CodeForRefund", argument.getCodeForRefund(), "KCX0021");  //EI20090306 
    		writeElement("Community", argument.getCommunity());
    		writeElement("Community2", argument.getCommunity2());
    		writeElement("Community3", argument.getCommunity3());
    	closeElement();
     }  
  
    //EI20090515
    public void writeApprovedTreatment(ApprovedTreatment argument, ExportRefundItem expRefItem) 
    									throws XMLStreamException {   
    	if (argument == null) {
    	    return;
    	}
    	
    	String decl = argument.getDeclared();
    	String prev = argument.getPrevious();  
	    String nat = argument.getNational();  
	    String codeRefund = "";
	    if (expRefItem != null) {
	        codeRefund = expRefItem.getRestitutionProcedure();
	    }  
    	if (Utils.isStringEmpty(decl) && Utils.isStringEmpty(prev) && 
            Utils.isStringEmpty(nat) && Utils.isStringEmpty(codeRefund)) {
    	    return; 
    	}
         	
    	openElement("CustomsApprovedTreatment");
    		writeElement("Declared", decl);
    		writeElement("Previous", prev);
    		writeElement("National", nat);
    		writeCodeElement("CodeForRefund", codeRefund, "KCX0021");  //EI20090306    		
    	closeElement();
     }      
    public void writeStatistic(Statistic argument, String message) throws XMLStreamException  {   //EI20081006
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}
    	
    	if (Utils.isStringEmpty(argument.getAdditionalUnit()) && Utils.isStringEmpty(argument.getStatisticalValue()) &&
        	!(message.equals("ExpEam") || message.equals("ExpDat"))) {
    	    return;
    	}                
    	openElement("Statistic");
    		writeElement("AdditionalUnit", argument.getAdditionalUnit());
    		writeElement("AdditionalUnitConfirmation", argument.getAdditionalUnitConfirmation()); //EI20100208 for UK
    		writeElement("AdditionalUnitCode", argument.getAdditionalUnitCode());
    		writeElement("StatisticalValue", argument.getStatisticalValue());
    		writeElement("StatisticalValueConfirmation",  argument.getStatisticalValueConfirmation()); //EI20100208 for UK    		
    		if (message.equals("ExpEam") || message.equals("ExpDat") || message.equals("ExpRel")) {
    			writeElement("Value", argument.getValue());
        		writeElement("Currency", argument.getCurrency());
    		}
	    closeElement();    	
    }
    
    // CK20100813
    public void writeGoodsIdentification(String additionalUnit) throws XMLStreamException {
    	if (Utils.isStringEmpty(additionalUnit)) {
    		return;
    	}
    	openElement("GoodsIdentification");
		writeElement("AdditionalCode", additionalUnit);
    closeElement();
    }
    

    public void writeStatistic(String unit, String statistical, String value, String curr, String message) 
    									throws XMLStreamException  {   //EI20081006
    	
    	if (Utils.isStringEmpty(unit) && Utils.isStringEmpty(statistical) &&
    		Utils.isStringEmpty(value) && Utils.isStringEmpty(curr)) {
    	    return;
    	}
    	
    	if (Utils.isStringEmpty(unit) && Utils.isStringEmpty(statistical) &&
    		!(message.equals("ExpEam") || message.equals("ExpDat"))) {
    	    return;
    	}
   	
    	openElement("Statistic");
    		writeElement("AdditionalUnit", unit);
    		writeElement("StatisticalValue", statistical);
    		if (message.equals("ExpEam")) {
    			writeElement("Value", value);
        		writeElement("Currency", curr);
    		}
	    closeElement();
    	
    }
    
    public void writeSpecialMentionList(List <SpecialMention> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	
    	String typeOfExport = "";
    	String exportFromEU = "";
    	String exportFromCountry = "";
    	String text = "";
    	String language = "";
    	
    	SpecialMention specialMention = null;
    	int size = list.size();
    	
    	for (int i = 0; i < size; i++) {
    		specialMention = (SpecialMention) list.get(i);
    		
    		typeOfExport = specialMention.getTypeOfExport();
        	exportFromEU = specialMention.getExportFromEU();
        	exportFromCountry = specialMention.getExportFromCountry();
        	text = specialMention.getText();
        	language = specialMention.getLanguage();
    		
        	if (!(Utils.isStringEmpty(typeOfExport) && Utils.isStringEmpty(exportFromEU) &&
        		  Utils.isStringEmpty(exportFromCountry) && Utils.isStringEmpty(text) &&
        		  Utils.isStringEmpty(language))) {
        		openElement("SpecialMention");
        			writeElement("TypeOfExport", typeOfExport);
        			writeElement("ExportFromEU", exportFromEU);
        			writeElement("ExportFromCountry", exportFromCountry);
        			writeElement("Text", text);
        			writeElement("Language", language);
        		closeElement();
        	}
    	}
    }
    public void writeNonCustomsLaw(String tag, NonCustomsLaw argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
		List<String> nonCustomsLawTypes = argument.getNonCustomsLawType();
		if (nonCustomsLawTypes != null) {
			openElement(tag);
				for (String type : nonCustomsLawTypes) {
					writeElement("NonCustomsLawType", type);
				}
			closeElement();
		}		
	}
    
  
    public void writePackagesList(List<Packages> list, String message) throws XMLStreamException {
    	if (list == null) {
    	    return;
    	}
    	
    	Packages packages = null;
    	String quantity = "";
    	String nr = "";
    	String type = "";
    	String marks = "";
    	int size = list.size();
    	
    	for (int i = 0; i < size; i++) {
    		packages = list.get(i);
    
    		quantity = packages.getQuantity();          
    		nr = packages.getSequentialNumber();  
    		if (!message.equals("ExitNotification")) {
    			type = packages.getType();  
    			marks = packages.getMarks(); 
    		}
    		if (!(Utils.isStringEmpty(quantity) && Utils.isStringEmpty(nr) &&
    			  Utils.isStringEmpty(type) && Utils.isStringEmpty(marks))) { 
	     
    			openElement("Package");   //AK20081120						
    				writeElement("Quantity", quantity);
    				writeElement("SequentialNumber", nr);
    				// C.K. package type auch ein code-feld !!
    				// writeElement("Type", type);
    				writeCodeElement("Type", type, "KCX0066");
    				writeElement("Marks", marks);
    			closeElement();		
    		}
    	}
    } 	
    
    //EI20090415:argument = Container.getNumberList() <== not used
    public void writeContainerList(List<String> argument) throws XMLStreamException {     	
    	if (argument == null) {
    	    return;
    	}    	
    	int size = argument.size();
    	if (size > 0) {    		
    		openElement("Containers"); 
    			for (int i = 0; i < size; i++) {    
    				String nr = argument.get(i);
    				 if (!Utils.isStringEmpty(nr))  {				
    					openElement("Container"); 
    					writeElement("Number", nr);
    					closeElement();
    				}
    			} 
    		closeElement();
    	}    	                 			 	    
    }
  
    public void writeContainerNumberList(Container argument) throws XMLStreamException {     	
    	if (argument == null) {
    	    return;
    	}
    	if (argument.getNumberList() == null) {
    		return;
    	}  	
    	List<String> list = argument.getNumberList();
    	int size = list.size();
    	if (size > 0) {    		
    		//EI20110628: openElement("Containers"); 
    		openElement("Container"); 
    			for (int i = 0; i < size; i++) {
    				writeElement("Number", list.get(i));
    			} 
    		closeElement();
    	}    	                 			 	    
    }
    
    public void writeGoodsIdentification(GoodsIdentification argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
		List<String> additionalCodes = argument.getAdditionalCodeList();
		if (additionalCodes != null) {
			openElement("GoodsIdentification");
				for (String code : additionalCodes) {
					writeElement("AdditionalCode", code);
				}
			closeElement();
		}
	}
    
    public void writeSensitiveGoodsList(List<SensitiveGoods> argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.size() == 0) {
    		return;
    	}
    	for (SensitiveGoods sg : argument) {
    		openElement("SensitiveGoods");
				writeCodeElement("Type", sg.getType(), "KCX0032");
				writeElement("Weight", sg.getWeight());
			closeElement();
    	}
    }
    public void writePermitList(List<Permit> argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	for (Permit p : argument) {
    		openElement("Permit");
				writeElement("PermitAuthority", p.getPermitAuthority());
				writeElement("PermitNumber", p.getPermitNumber());
				writeElement("IssueDate", p.getIssueDate());
				writeElement("AdditionalInformation", p.getAdditionalInformation());				
			closeElement();
    	}
		
	}
    //from FSS to KIDS:
    public void writeDocumentList(List<Document> argument) throws XMLStreamException {
    	if (argument == null) { 
    	    return;
    	}	
    	int size = argument.size();
    	for (int i = 0; i < size; i++) {    		
    		writeDocument(argument.get(i));
    	}   
    }
    public void writeDocument(Document argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	String argFlag = argument.getMsgFlag();
    	if (argFlag == null) {
    		argFlag = "";
    	}
      	String qualifier = "";
      	if (argFlag.equals("U")) {
      		qualifier = argument.getTypeUids();  
      	} else {
      		qualifier = argument.getQualifier();
      	}
    	String type = "";
    	if (argFlag.equals("U"))  {
    		type = argument.getCategory();
    	} else {
    		type = argument.getTypeKids();
    	}		
    	String referenz = argument.getReference();
    	String information = argument.getAdditionalInformation();
    	String detail = argument.getDetail();
    	String idate = argument.getIssueDate();
    	String edate = argument.getExpirationDate();
    	String office = argument.getOffice();
    	String present = argument.getPresent();
    	String presentLocation = argument.getPresentLocation();    	
    	String value = null; 
    	
    	if (argFlag.equals("U")) {
    		value = "";
    	} else {
    		value = argument.getValue();
    	}
    	boolean doc = false;
    	boolean wda = false;
    	boolean wdq = false;
    	
    	String wqualifier = ""; 
    	String wunit = "";
    	String wvalue = ""; 
    	String wdqvalue = ""; 
    	
    	if (!(Utils.isStringEmpty(qualifier) && Utils.isStringEmpty(type) &&
    		  Utils.isStringEmpty(referenz) && Utils.isStringEmpty(information) &&
    		  Utils.isStringEmpty(detail) && Utils.isStringEmpty(idate) &&
    		  Utils.isStringEmpty(edate) && Utils.isStringEmpty(value) &&
    		  Utils.isStringEmpty(office) && Utils.isStringEmpty(present) &&
    		  Utils.isStringEmpty(presentLocation))) {
    		doc = true;
    	}
    		    		
    	if (argument.getWriteDownAmount() != null) {
    		 //Ei20090312: wqualifier = argument.getWriteDownAmount().getQualifier(); 
        	 wunit = argument.getWriteDownAmount().getUnitOfMeasurement();
        	 if (argFlag.equals("U")) {
        		 value = argument.getWriteDownAmount().getValueUids(); //EI20090430
        	 }
        	 wvalue = argument.getWriteDownAmount().getWriteoffValue(); //EI20090430
        	 
    		 if (!(Utils.isStringEmpty(wqualifier) && Utils.isStringEmpty(wunit) &&
    			   Utils.isStringEmpty(wvalue))) {
    			 wda = true;
    		 }
    	}
    	//WritedownQuantity
    	if (argument.getWriteDownQuantity() != null) {
    		wdqvalue = argument.getWriteDownQuantity().getWriteoffValue();
    		if (!(Utils.isStringEmpty(wdqvalue))) {
    			wdq = true;
    		}
    	}
    	
    	if (doc || wda || wdq) {    	
    	openElement("Document");  
    		if (doc) {
    			writeElement("Qualifier", qualifier);      	
    			writeCodeElement("Type", type, "KCX0035");                        
    			writeElement("Reference", referenz); 
    			writeElement("AdditionalInformation", information);      	
    			writeElement("Detail", detail);    
    			if (!Utils.isStringEmpty(idate)) {   
    				idate.trim();     //EI20090514
    				if (idate.length() == 10) {
            		    writeDateToString("IssueDate", idate);
            		} else {
            			writeElement("IssueDate", idate); 
            		}
    			}
    			if (!Utils.isStringEmpty(edate)) {  
    				edate.trim();     //EI20090514
    				if (edate.length() == 10) {
    					writeDateToString("ExpirationDate", edate);
    				} else {
    					writeElement("ExpirationDate", edate); 
    				}
    			}
    			//TODO-IWA: soll es hierher??? writeElement("Reason", argument.getReason());   //EI20120703 KIDS-V20
    			writeElement("Value", value);
    			writeElement("Office", office);
    			writeElement("Present", present);
    			writeElement("PresentLocation", presentLocation);
    		}    		
    		if (wda) {    			
    			writeWriteDownAmount("WritedownAmount", argument.getWriteDownAmount(), "KCX0016"); 
    		}
    		if (wdq) {    			
    			writeWriteDownAmount("WritedownQuantity", argument.getWriteDownQuantity(), "");
    		}    		
    		
    	closeElement();
    	}
    }    
    public void writeProducedDocumentU(DocumentU argument) throws XMLStreamException { 
    	 if (argument == null) {
    	     return;
    	 }
    	 	
    	 String type = argument.getType();           		
     	 String category = argument.getCategory();  
 	     String referenz = argument.getReference();  
 	     String rem = argument.getRemark();          
   	     String cdate = argument.getDateOfCreation();  
	     String vdate = argument.getDateOfValidity();  
	     
	     if (Utils.isStringEmpty(type) && Utils.isStringEmpty(category)  &&
	    	 Utils.isStringEmpty(referenz) && Utils.isStringEmpty(rem) &&
	    	 Utils.isStringEmpty(cdate) && Utils.isStringEmpty(vdate)) {
	         return; 
	     }
		                	 
    	 openElement("Document");   					
    	    writeElement("Qualifier", type);
          	writeCodeElement("Type", category, "KCX0035");
        	writeElement("Reference", referenz);
        	writeElement("AdditionalInformation", rem); 
        	////erst ab V6: Detail
        	if (!Utils.isStringEmpty(cdate)) {
        		//AK20090514
        		cdate.trim();   //EI20090514
        		if (cdate.length() == 10) {
        		    writeDateToString("IssueDate", cdate);
        		} else {
        		    writeElement("IssueDate", cdate);
        		}
        	}
        	if (!Utils.isStringEmpty(vdate)) {
        		//AK20090514
        		vdate.trim();   //EI20090514
        		if (vdate.length() == 10) {
        			writeDateToString("ExpirationDate", vdate);        		        		
        		} else {
        		    writeElement("ExpirationDate", vdate); 
        		}
        	}
        	////erst ab V6: Value
        	////am 26.08.2008 noch nicht vorhanden: writeDownAmount
        closeElement();									
    }
    public void writePreviousDocumentList(List<PreviousDocument> argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}    	
    	for (PreviousDocument doc : argument) {    		
    		writePreviousDocument(doc);
    	}
    }
    public void writePreviousDocument(PreviousDocument argument) throws XMLStreamException {   
    	if (argument == null) {
    		return;
    	}
    	
    	String type = argument.getType();    	
    	String marks = argument.getMarks();  
    	String additionalInfo = argument.getAdditionalInformation();
    	String date = argument.getDate();
    	String office = argument.getOffice();
    	
		if (Utils.isStringEmpty(type) && Utils.isStringEmpty(marks) &&
		    Utils.isStringEmpty(additionalInfo) &&
		    Utils.isStringEmpty(date) &&
		    Utils.isStringEmpty(office)) {
		    return; 
		}
				
    	openElement("PreviousDocument");   
    		writeElement("Type", type);
    		writeElement("DocumentType", argument.getDocumentType());  //EI20130807 for BE
    	    writeElement("Marks", marks);								
    	    writeElement("Reference", argument.getReference()); 	 //EI20130807 for BE
    	    writeElement("AdditionalInformation", additionalInfo); 
    	    writeElement("Date", date);
    	    writeElement("Office", office);
    	    ////ab V6: BZL- und BAV-Felder
    	closeElement();						
    }   
      
    public void writeHeadDocument(List<MsgUidsPos> itemList) throws XMLStreamException { 
    	if (itemList == null) {
    	    return;
    	}
    //for V6 -- Carine fragen ob es tatsächlich so ist
    	int size = itemList.size();   		     
        for (int i = 0; i < size; i++) {         		
        	MsgUidsPos position = itemList.get(i);
        	List <DocumentU>pList = new Vector<DocumentU>();        		        	
        	pList = position.getProducedDocumentUList();
        	if (pList != null && pList.size() > 0) {        		       			     		
        		writeProducedDocumentU(pList.get(0));
        	}
        }                  
   }
    
    public void writeHeadDocument(List itemList, String msgName) throws XMLStreamException { 
    	if (itemList == null) {
    	    return;
    	}
    //for V6 -- Carine fragen ob es tatsächlich so ist
    	int size = itemList.size();   		     
        for (int i = 0; i < size; i++) {   
        	if (msgName.equals("ExpDat")) {
        		MsgExpDatPos position = (MsgExpDatPos) itemList.get(i);
        		List <Document>dList = new Vector<Document>();        		        	
            	dList = position.getDocumentList();
            	if (dList != null && dList.size() > 0)  {       
            		writeDocument(dList.get(0));
            	}        	
        	} else if (msgName.equals("")) {        	
        		MsgUidsPos position = (MsgUidsPos) itemList.get(i);
        		List <DocumentU>dList = new Vector<DocumentU>();        		        	
            	dList = position.getProducedDocumentUList();
            	if (dList != null && dList.size() > 0) {        		       			     		
            		writeProducedDocumentU(dList.get(0));
            	}
        	}        	        
        }                  
   }
    //EI20090309: tag - "BondedWarehouseCompletion" oder "InwardProcessingCompletio"
    public void writeCompletion(String tag, Completion argument) throws XMLStreamException {   
    	if (argument == null) {
    		return;    	    	  
    	}
    	//String type = argument.getType(); 
    	String checked = argument.getChecked();
    	String totalNumberOfPositions = argument.getTotalNumberOfPositions();
    	String authorizationNumber = argument.getAuthorizationNumber();
    	String referenceNumber = argument.getReferenceNumber();
    	List complItemList = argument.getCompletionItemList();    	
    	
	    if (Utils.isStringEmpty(checked) && Utils.isStringEmpty(totalNumberOfPositions)	&&
	        Utils.isStringEmpty(authorizationNumber) && Utils.isStringEmpty(referenceNumber) &&
	        complItemList == null) {
	        return; 
	    }
 
    	openElement(tag);   
    		writeCodeElement("Checked", checked, "KCX0001");     	
    	    writeElement("TotalNumberOfPositions", totalNumberOfPositions);    	    
    	    writeElement("AuthorizationNumber", authorizationNumber);   
    	    writeElement("ReferenceNumber", referenceNumber); 
    	    if (complItemList != null) {
    	    	int listSize = complItemList.size();
    	    	for (int i = 0; i < listSize; i++) {
    	    	     writeCompletionItem(tag, (CompletionItem) complItemList.get(i));
    	    	}
    	    }
    	closeElement();						
    }
  //EI20090309:
    public void writeCompletionItem(String tag, CompletionItem argument) throws XMLStreamException {
       	if (argument == null) {
    		return;
       	}
       	String sequentialNumber = argument.getSequentialNumber();
    	String positionNumber = argument.getPositionNumber();
    	String uCR = argument.getUCR();
    	String entryInAtlas = argument.getEntryInAtlas();
    	String text = argument.getText();
    	String tarif = argument.getTarifNumber();
    	String form = argument.getUsualFormOfHandling();
    	WriteDownAmount wdAmount = argument.getWriteDownAmount();
    	WriteDownAmount trAmount = argument.getTradeAmount();
        	
	    if (Utils.isStringEmpty(sequentialNumber) && Utils.isStringEmpty(positionNumber) &&	    		  
		    Utils.isStringEmpty(uCR) && Utils.isStringEmpty(entryInAtlas) &&
		    Utils.isStringEmpty(text) && Utils.isStringEmpty(tarif) && Utils.isStringEmpty(form) &&
		    wdAmount == null && wdAmount == null) {
		        return; 
		    }  
	    
	    openElement("CompletionItem");
	    	//writeElement("SequentialNumber", sequentialNumber); 
	    	writeElement("PositionNumber", positionNumber); 
	    	writeElement("UCR", uCR); 
	    	writeCodeElement("EntryInAtlas", entryInAtlas, "KCX0001"); 
	    	writeElement("Text", text); 
	    	writeElement("TarifNumber", tarif); 
	    	writeCodeElement("UsualFormOfHandling", form, "KCX0001");   //EI20090817
	    	if (tag.equals("BondedWarehouseCompletion")) {
	    		writeWriteDownAmount("WritedownAmount", wdAmount, "KCX0025");
	    		writeWriteDownAmount("TradeAmount", trAmount, "");
	    	}
	    closeElement();
    }
    //EI20090309:
    public void writeWriteDownAmount(String tag, WriteDownAmount argument, String kcx) throws XMLStreamException  {
    	if (argument == null) {
    		return;
    	}
    	
    	 //Ei20090312: String qualifier = argument.getQualifier();
    	String unit = argument.getUnitOfMeasurement();    	       	
    	//String value = argument.getValueUids();       //EI20090817
    	String wvalue = argument.getWriteoffValue();  //EI20090817
    	
    	//if (Utils.isStringEmpty(qualifier) && Utils.isStringEmpty(unit) && && Utils.isStringEmpty(value) )
    	if (Utils.isStringEmpty(unit) &&  Utils.isStringEmpty(wvalue))	{	    
    		return;
    	}
		openElement(tag);
		//Ei20090312: writeElement("Qualifier", qualifier);  
			if (kcx.equals("")) {
				writeElement("UnitOfMeasurement", unit);
			} else	{				
				writeCodeElement("UnitOfMeasurement", unit, kcx);
			}
			//writeElement("Value", value);  
			//EI20090818: writeElement("WriteoffValue", wvalue);  //EI20090817
			writeElement("Value", wvalue);  //EI20090818 der Tag heisst Value für KIDS			
		closeElement();
    }
    
    public void writeCancellationInfo(String argument) throws XMLStreamException  {      
    	if (argument == null) {
    	    return;
    	}
    	
    	if (!Utils.isStringEmpty(argument)) {
    		openElement("CancellationInfo");
    		    writeElement("KindOfCancellation", argument);
    		closeElement();
    	}
    }
    public void writePDFInformationList(List<PDFInformation> list) throws XMLStreamException {   //EI20110811        
    	if (list == null) {
    	    return;
    	}
    	if (list.isEmpty()) {
    	    return;
    	}
    	for (PDFInformation pfd : list) {
    		writeOnePDFInformation(pfd);
    	}
    }
    public void writeOnePDFInformation(PDFInformation pdf) throws XMLStreamException {            //EI20110811        
    	if (pdf == null) {
    	    return;
    	}
    	if (pdf.isEmpty()) {
    	    return;
    	}
    	openElement("PDFInformation");    	
			writeElement("Name", pdf.getName());      	
			writeElement("Directory", pdf.getDirectory());   			
			writeElement("NewDirectory", pdf.getNewDirectory()); 
			writeElement("Remark", pdf.getRemark());   		
			
			if (pdf.getPdflist() != null) {
				List <String> pdfList = pdf.getPdflist(); 
				if (!pdfList.isEmpty()) {
					// Wenn der Kunde lt. Eintrag in DB ein tgz-file will
					// dann PDF-Information nicht einbetten
					if (commonFieldsDTO != null && commonFieldsDTO.isPdfTgz()) {
						writePdffile(pdfList);
						Utils.log("PDF nicht einbinden");
						commonFieldsDTO.setPdfExists(true);
					} else {
						for (String base64 : pdfList)  {
							writeElement("base64", base64); 
						}
					}
				} else {
                Utils.log("(KidsMessage one PDFInformation from PDFInformationList) pdfList is empty");
				}
			}   			
		closeElement();  
    }
    
    
    public void writePDFInformation(PDFInformation argument) throws XMLStreamException {
        if (commonFieldsDTO != null) {
            commonFieldsDTO.setPdfExists(false);
        }
        
    	if (argument == null) {
    	    return;
    	}
    	
    	String name = argument.getName();
    	String dir = argument.getDirectory();
    	String newdir = argument.getNewDirectory();
    	List <String> pdfList = argument.getPdflist(); 
        int listSize =  0;

    	if (Utils.isStringEmpty(name) && Utils.isStringEmpty(dir) &&
    	        Utils.isStringEmpty(newdir) && pdfList == null) {
    	    return;
    	}
    		
   		openElement("PDFInformation");    	
   			writeElement("Name", name);      	
   			writeElement("Directory", dir);                        
   			writeElement("NewDirectory", newdir); 
   			writeElement("Remark", argument.getRemark());   
   			
   			if (pdfList != null) {
//   				int listSize = pdflist.size();
//   				for (int i = 0; i < listSize; i++) {
//   					writeElement("base64Text", (String)pdflist.get(i));
//   				}
                listSize = pdfList.size();
                if (listSize > 0) {
                    // Wenn der Kunde lt. Eintrag in DB ein tgz-file will
                    // dann PDF-Information nicht einbetten
                    if (commonFieldsDTO != null && commonFieldsDTO.isPdfTgz()) {
                        writePdffile(pdfList);
                        Utils.log("PDF nicht einbinden");
                        commonFieldsDTO.setPdfExists(true);
                    } else {
                        for (int i = 0; i < pdfList.size(); i++)  {
                            writeElement("base64", pdfList.get(i)); 
                        }
                    }
                } else {
                    Utils.log("(KidsMessage writePDFInformation) pdfList is empty");
                }
   			}   			
   		closeElement();    	
    }
    
    public void writePdffile(List <String> pdfList) {
        StringBuffer buff = new StringBuffer();
        String str = "";
        String name = "";
        
        for (int i = 0; i < pdfList.size(); i++)  {
            str = pdfList.get(i);
            buff.append(str);
        }
        String filename = commonFieldsDTO.getFilename();

        if (filename.lastIndexOf(".xml") > 0) {
            name = filename.substring(0, filename.lastIndexOf(".xml")); 
        } else {
            name = filename;
        }
        
        try {
            byte[] out = Base64.decodeBase64(buff.toString().getBytes());
            FileOutputStream fos = new FileOutputStream(Config.getPdfpath() + "/" + name + ".pdf");
            fos.write(out);
            fos.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public void writePdffileTmp(List <String> pdfList) {
        StringBuffer buff = new StringBuffer();
        String str = "";
        String name = "";
        
        for (int i = 0; i < pdfList.size(); i++)  {
            str = pdfList.get(i);
            buff.append(Base64.decodeBase64(str.getBytes()).toString() + Utils.LF);  
        }
        String filename = commonFieldsDTO.getFilename();

        if (filename.lastIndexOf(".xml") > 0) {
            name = filename.substring(0, filename.lastIndexOf(".xml")); 
        } else {
            name = filename;
        }
        byte[] out = Base64.decodeBase64(buff.toString().getBytes());
        str = new String(out);
        
        try {
            FileOutputStream fos = new FileOutputStream(Config.getPdfpath() + "/" + name + ".TmpPdf");
            fos.write(out);
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    public void writeWareHouse(WareHouse wareHouse) throws XMLStreamException {
    	if (wareHouse == null) {
    		return;
    	}    	
    	String type = wareHouse.getWarehouseType();
    	String identification = wareHouse.getWarehouseIdentification();
    	String country = wareHouse.getWarehouseCountry();
    	
    	if (Utils.isStringEmpty(type) && Utils.isStringEmpty(identification) &&
    	    Utils.isStringEmpty(country)) {
    		return;
    	}
    	openElement("Warehouse");
    		writeElement("WarehouseType", type);      	
    		writeElement("WarehouseIdentification", identification);      	
    		writeElement("WarehouseCountry", country);      	
    	closeElement();
    }
    public void writeCAP(Cap argument) throws XMLStreamException { //EI20100208 for UK     
      	if (argument == null) {
    	    return;
    	}
      	String ibClaimRef = argument.getIBClaimRef();
      	String ibClaimType = argument.getIBClaimType();
      	String ibRegNo = argument.getIBRegNo();
      	String ibGan = argument.getIBGAN();
      	
       	if (Utils.isStringEmpty(ibClaimRef) && Utils.isStringEmpty(ibClaimType) &&
            Utils.isStringEmpty(ibRegNo) && Utils.isStringEmpty(ibGan)) {
        	return;
       	}
       	openElement("CAP");
       		writeElement("IBClaimRef", ibClaimRef);      	
       		writeElement("IBClaimType", ibClaimType);      	
       		writeElement("IBRegNo", ibRegNo); 
       		writeElement("IBGAN", ibGan); 
	    closeElement();    	
    }
    public void writeInvoice(Invoice argument) throws XMLStreamException  { //EI20100208 for UK    
     	if (argument == null) {
    	    return;
    	}
      	String currency = argument.getCurrency();
      	String price = argument.getInvoicePrice();
      	String number = argument.getInvoiceNumber();
      	
      	      	
      	if (Utils.isStringEmpty(currency) && Utils.isStringEmpty(price) && Utils.isStringEmpty(number)) {
      			return;
        }
      	openElement("Invoice");
      	writeElement("InvoiceNumber", number); 
      	writeElement("InvoicePrice", price);
      	writeElement("Currency", currency);
	    closeElement();    	
    	
    }
    public void writeAddInfoStatement(Text argument) throws XMLStreamException {  //EI20100208 for UK   
     	if (argument == null) {
    	    return;
    	}
    	String code = argument.getCode();
     	String text = argument.getText();     	
     	if (Utils.isStringEmpty(code) && Utils.isStringEmpty(text))  {
     		return;
     	}
     	openElement("AddInfoStatement");
     		writeElement("Code", code); 
     		writeElement("Text", text); 
     	closeElement();   
    }
    //EI20110502
    public void writeRefinement(Refinement argument)throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}
    	openElement("Refinement");
			writeElement("Direction", argument.getDirection());      	
			writeElement("RefinementType", argument.getRefinementType());      	
			writeElement("ProcessType", argument.getProcessType()); 
			writeElement("BillingType", argument.getBillingType());      	
			writeElement("TemporaryAdmission", argument.getTemporaryAdmission());      	
			writeElement("PositionType", argument.getPositionType());   
		closeElement();
    }
    //EI20110502
    public void writeDetailList(List<Text> list) throws XMLStreamException {   
    	if (list == null) {
    	    return;
    	}
    	int size = list.size();    	 	    
    	if (size > 0) {    		    		
    		for (int i = 0; i < size; i++) {
    			if (list.get(i) != null) {   //EI20120716
    			openElement("Detail"); 
    				writeElement("Name", list.get(i).getCode());
    				writeElement("Value", list.get(i).getText());
    			closeElement();
    			}
    		}     		
    	}   
    }
    //EI20110502
    public void writeNotificationCodeList(List<String> list)throws XMLStreamException {    	
    	if (list == null) {
    	    return;
    	}
    	int size = list.size();    	 	        	   		    	
    	for (int i = 0; i < size; i++) {     		
    		writeElement("NotificationCode", list.get(i));       	
    	}     		    	
    }
  
 //-----------------------------

    public void writeDateTimeToString(String tag, String datetime) throws XMLStreamException {	 
    	//datetime should be formated as ST_DateTime ==> "2008-10-20 22:02:55 +0200"
	    //formated datetime will be konverted into String ==> YYYYMMDDHHMM (KIDS_DateTime)    	
    	
    	if (datetime == null || Utils.isStringEmpty(datetime)) {
    		return;
    	}
    	//EI20100531: if datetime isn't formated:
    	if (!datetime.contains("-")) {  
    		writeElement(tag, datetime);
    		return;
    	}
    	
	    String dateString = "";
	    KcxDateTime kcx;
	    	   	    
	    datetime.trim();   	    
    	try {					
			kcx = new KcxDateTime(EFormat.ST_DateTime, datetime);
			dateString = kcx.format(EFormat.KIDS_DateTime);
			writeElement(tag, dateString);
		} catch (ParseException e) {
            try {                   
                kcx = new KcxDateTime(EFormat.ST_DateTimeT, datetime);
                dateString = kcx.format(EFormat.KIDS_DateTime);                         
                writeElement(tag, dateString);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
		}
	               
    }    

	public void writeDateToString(String tag, String date) throws XMLStreamException {
		//date should be formated as ST_Date ==> "2008-10-20"
	    //formated date will be converted into String ==> YYYYMMDD (KIDS_Date)		
		
	   	if (date == null || Utils.isStringEmpty(date)) {
    		return;
	   	}
    	//EI20100531: if date isn't formated:
    	if (!date.contains("-")) {  
    		writeElement(tag, date);
    		return;
    	}
    	
	    String dateString = ""; 
	    KcxDateTime kcx;
			    	    	
    	date.trim();              	 	
	    try {					
	    	kcx = new KcxDateTime(EFormat.ST_Date, date);
	    	dateString = kcx.format(EFormat.KIDS_Date);
	    	writeElement(tag, dateString);
	    } catch (ParseException e) {
	    	e.printStackTrace();	    		
	    }		
    }   
	
	public void writeTimeToString(String tag, String time) throws XMLStreamException {
		//time should be formated as ST_Time_Sec ==> "22:02:55"
		//formated time will be converted into String ==> HHmmss (KIDS_Time)
		
	   	if (time == null || Utils.isStringEmpty(time)) {
    		return;
	   	}
    	//EI20100531: if time isn't formated:
    	if (!time.contains(":")) {  
    		writeElement(tag, time);
    		return;
    	}
    	
    	String dateString = ""; 
    	KcxDateTime kcx;
		    	    	
    	time.trim();           	
	    try {					
	    	kcx = new KcxDateTime(EFormat.ST_Time_Sec, time);
			dateString = kcx.format(EFormat.KIDS_Time);
			writeElement(tag, dateString);
	    } catch (ParseException e) {
	    	e.printStackTrace();	    		   
		}
	}		 

	/* //EI20100531: these methods are not in use
	public void writeStringToDateTime(String tag, String dateString)  throws XMLStreamException {
		// dateString should be as "YYYYMMDDHHMM" == KIDS_DateTime
		
		String datetime = ""; //dateString will be konverted to formated date ==> ST_DateTime
		KcxDateTime kcx = null;
		
		if (dateString != null && !Utils.isStringEmpty(dateString)) {
			//length og KIDS_DateTime is 12: YYYYMMDDHHMM
			dateString.trim();      //EI20090514
			if (dateString.length() == 8) {
				dateString = dateString + "0000";
			} else if (dateString.length() == 14) {
				dateString = dateString.substring(0, 11);
			}
					
			try {
				kcx = new KcxDateTime(EFormat.KIDS_DateTime, dateString);
				datetime = kcx.format(EFormat.ST_DateTime);
				//uidsDate = kcx.format(EFormat.ST_Date);
				writeElement(tag, datetime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}	
	  	 public void writeStringToDate(String tag, String dateString)  throws XMLStreamException {
			// dateString should be as "YYYYMMDD" == KIDS_Date
					
			String datetime = ""; //dateString will be converted to formated date ==> ST_Date
			KcxDateTime kcx = null;
					
			if (dateString != null && !Utils.isStringEmpty(dateString)) {
				//length og KIDS_Date is 8: YYYYMMDD
				dateString.trim();      //EI20090514
				 if (dateString.length() > 8) {
					dateString = dateString.substring(0, 7);
				}
						
				try {
					kcx = new KcxDateTime(EFormat.KIDS_Date, dateString);
					datetime = kcx.format(EFormat.ST_Date);
					writeElement(tag, datetime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	public void writeStringToTime(String tag, String timeString) throws XMLStreamException {
		// timeString should be as "HHmmss" == KIDS_Time
				
    	String time = ""; //timeString will be converted to formated time ==> ST_Time_Sec
    	KcxDateTime kcx = null;
				
    	if (timeString != null && !Utils.isStringEmpty(timeString)) {
    		//length og KIDS_Time is 6: HHmmss
    		 timeString.trim();       //EI20090514
    		 if (timeString.length() > 6) {
    			timeString = timeString.substring(0, 5);
    		}
    				
    		try {
    			kcx = new KcxDateTime(EFormat.KIDS_Time, timeString);
    			time = kcx.format(EFormat.ST_Time_Sec);
    			writeElement(tag, time);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    	}
    }		
	  */
 // from Uids to Kids end 

    public KcxEnvelope getKcxHeader() {
        return kcxHeader;
    }

    public void setKcxHeader(KcxEnvelope kcxHeader) {
        this.kcxHeader = kcxHeader;
    }
			 
// from Kids to FSS 	  
    /*
	public TsADR setAdr(String type, Party party, String beznr, String posnr) {
		if (party == null)
			return null;
		
		TsADR tmpAdr = new TsADR();
		Address adr = party.getAddress();
		ContactPerson coper = party.getContactPerson();
					
		if (type.equals("Consignor")) {					    				
                tmpAdr = setAdresse("1", adr, coper, beznr, "0");
		} else if (type.equals("Consignee")) {						
			tmpAdr = setAdresse("2", adr, coper, beznr, "0");
		} else if (type.equals("Agent")) {			
			tmpAdr = setAdresse("4", adr, coper, beznr, "0");
		} else if (type.equals("Declarant")) {
			tmpAdr = setAdresse("3", adr, coper, beznr, "0");
		} else if (type.equals("Subcontractor")) {
			tmpAdr = setAdresse("5", adr, coper, beznr, "0");           
		}	
							
		return tmpAdr;		
	}	
	*/
	public TsADR setAdresse(String type, Address address, ContactPerson contact, String beznr, String posnr) {
		if (address == null && contact == null) {
		    return null;
		}
		
		TsADR tmpAdr = new TsADR();
		
		String name = "";
		String n1 = "";
		String n2 = "";
		String n3 = "";
		
		int len = 0;
		if (address != null) {
			name = address.getName();
			if (name != null) {
			    len = name.length();			
			}
			if (len > 0 && len <= 35) {
			    n1 = name;
			} else if (len > 35 && len <= 70) {
				n1 = name.substring(0, 34);
				n2 = name.substring(35);
			} else if (len > 70) {
				n1 = name.substring(0, 34);
				n2 = name.substring(35, 69);
				n3 = name.substring(70);
			}
						
			tmpAdr.setBeznr(beznr);
			tmpAdr.setPosnr(posnr);    
			tmpAdr.setTyp(type);       
			tmpAdr.setName1(n1);  
			tmpAdr.setName2(n2);      
			tmpAdr.setName3(n3);      
			tmpAdr.setStr(address.getStreet());     
			tmpAdr.setOrt(address.getCity());     
			tmpAdr.setPlz(address.getPostalCode());      
			tmpAdr.setLand(address.getCountry());   
		}
		if (contact != null) {
			tmpAdr.setSbstlg(contact.getPosition());   
			tmpAdr.setSbname(contact.getClerk());   
			tmpAdr.setEmail(contact.getEmail());	
			tmpAdr.setTelnr(contact.getPhoneNumber());   
			tmpAdr.setFaxnr(contact.getFaxNumber());    
		}
		
		return tmpAdr;
	}
	public com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsADR 
		   setAdresse53(String type, Address address, String aBeznr, String aPosnr) {
		if (address == null) {
		    return null;
		}
		
		com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsADR tmpAdr = 
			new com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsADR();
		
		String name = "";
		String n1 = "";
		String n2 = "";
		String n3 = "";
		
		int len = 0;
		if (address != null) {
			name = address.getName();
			if (name != null) {
			    len = name.length();			
			}
			if (len > 0 && len <= 35) {
			    n1 = name;
			} else if (len > 35 && len <= 70) {
				n1 = name.substring(0, 34);
				n2 = name.substring(35);
			} else if (len > 70) {
				n1 = name.substring(0, 34);
				n2 = name.substring(35, 69);
				n3 = name.substring(70);
			}
						
			tmpAdr.setBeznr(aBeznr);
			tmpAdr.setPosnr(aPosnr);    
			tmpAdr.setTyp(type);       
			tmpAdr.setName1(n1);  
			tmpAdr.setName2(n2);      
			tmpAdr.setName3(n3);      
			tmpAdr.setStr(address.getStreet());     
			tmpAdr.setOrt(address.getCity());     
			tmpAdr.setPlz(address.getPostalCode());      
			tmpAdr.setLand(address.getCountry());   
		}	
		
		return tmpAdr;
	} 

    protected StringWriter createKcxHeader(String primary, String secondary, String tertiary, String auditId) {
        StringWriter kcxEnvelopeString = new StringWriter();
      
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter kcxWriter = factory.createXMLStreamWriter(kcxEnvelopeString);
            kcxWriter.writeStartDocument();
            kcxHeader = new KcxEnvelope();
            kcxHeader.setFields(primary, secondary, tertiary, auditId);
            kcxHeader.writeHeader(kcxWriter);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return kcxEnvelopeString;
    }
    /**
     * MessageID die aus der FSS(fssfilename) kommt, genutzt beim FssToKids.
     * @return msgID.
     */
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	/**
     * Schreibt das version und encoding tag einer xml
     * mit doppelten hochkomma anstelle von einfachen hochkomma.
     * @param encoding
     * @param version
     * @throws XMLStreamException
     */
    protected void writeStartDocument(String encoding, String version) throws XMLStreamException {
		writer.writeDTD("<?xml version=\"" + version + "\" encoding=\"" + encoding + "\"?>");
	}
    public CommonFieldsDTO getCommonFieldsDTO() {
        return commonFieldsDTO;
    }
    public void setCommonFieldsDTO(CommonFieldsDTO commonFieldsDTO) {
        this.commonFieldsDTO = commonFieldsDTO;
    }
    
    //EI20120206: JIRA-Ticket KCX-94 von AG/DH: Wenn keine Nettogewichte übertragen werden, dann soll 
    // grundsätzlich 90% des Bruttogewichtes für die Position kalkuliert und an ZABIS übertragen werden  
    public String calculateNetMass(String gros, String net)  {  		
		double netMass = 0;	
		double grosMass = 0;
		String netto = "";
		
		if (Utils.isStringEmpty(gros)) {
			return net;
		}
		
		if (Utils.isStringEmpty(net)) {
			net = "0";
		}		
		try {
			grosMass = Double.parseDouble(gros);   
			netMass = Double.parseDouble(net); 
			if (grosMass > 0 && netMass <= 0) {
				netMass = grosMass * 0.9;
			} 			
		} catch (Exception e) {			
			netMass = 0;			
		}
		
		if (netMass > 0) {			
			BigDecimal newDec = new BigDecimal(netMass);
            newDec = newDec.setScale(0, BigDecimal.ROUND_HALF_DOWN);  //die zahl wird auf integer gerundet
			netto = "" + newDec;
		} else {
			netto = "";
		}
		return netto;
	}
    public boolean writeHead() {                                     //EI20130213
    	boolean head = false;
    	if (this.getCommonFieldsDTO() == null) { 
    		head = false;
        } else {
        	if (this.getCommonFieldsDTO().getCustomerProcedureDTO() == null) {
        		head = false;           	
        	} else {        		
            	String fssVersion = this.getCommonFieldsDTO().getCustomerProcedureDTO().getFssVersion();
            	if (Utils.isStringEmpty(fssVersion)) {
            		head = false;
            	} else {
            		String vers = Utils.removeDots(fssVersion.substring(0, 3));
            		if (vers.startsWith("07") || vers.startsWith("7")) {
            			head = true;
            		} else {
            			head = false;
            		}                	
            	}
        	}
        }
    	return head;
    }
    
}
