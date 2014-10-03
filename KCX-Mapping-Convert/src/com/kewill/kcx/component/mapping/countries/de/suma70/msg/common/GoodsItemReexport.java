package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.06.2013<br>
 * Description	: GoodsItem Data Of ReExport.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class GoodsItemReexport extends KCXMessage {

	private String msgName;
	private String itemNumber;
	private String destination;
	private String changeFlag;			
		
	private TIN custodianTIN;
	private ArrayList<Packages>	packageList;
	private ReferencedSpecification referencedSpecification;	
	private ManifestReference reference;
	private CustomsNotification customsNotification;
	
	private enum EGoodsItemReexport {
		//KIDS							//UIDS
		ItemNumber,	
		CustodianTIN,
		DestinationPlace,	DestinationCode, Destination,	
		Packages, Package,	Packaging, 		    		          
		Reference,
		ReferencedSpecification,  
		ChangeFlag,
		CustomsNotification,
	}
	
	public GoodsItemReexport() {
		super();  
	}

    public GoodsItemReexport(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
    public GoodsItemReexport(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EGoodsItemReexport) tag) {
  			case Packages:
  			case Packaging: 
  			case Package:
  				Packages packaging = new Packages(getScanner());
  				packaging.parse(tag.name());
  				addPackagesList(packaging);
  				break;
  			
  			case ReferencedSpecification:
				referencedSpecification = new ReferencedSpecification(getScanner());
				referencedSpecification.parse(tag.name());
				break;
				
  			case Reference:  			
  				reference = new ManifestReference(getScanner());
  				reference.parse(tag.name());
  				break;
  			
  			case CustodianTIN:
  				custodianTIN = new TIN(getScanner());
  				custodianTIN.parse(tag.name());
  				break; 
  				
  			case CustomsNotification:
  				customsNotification = new CustomsNotification(getScanner());
  				customsNotification.parse(tag.name());
  				break;
  				
  			default:
  					return;
  			}
  		} else {
  			switch ((EGoodsItemReexport) tag) {
  				case ItemNumber:  				
  					setItemNumber(value);
  					break;  		
  					
  				case DestinationCode:   
  				case DestinationPlace:
  				case Destination:
  					setDestinationPlace(value);
  					break;
  					
  				case ChangeFlag:
  					setChangeFlag(value);
  					break;
		  				  				 			
   				default:
  					return;  					
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EGoodsItemReexport.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public ArrayList<Packages> getPackagesList() {
		return packageList;
	}
	public void setPackagesList(ArrayList<Packages> list) {
		this.packageList = list;
	}
	public void addPackagesList(Packages packaging) {
		if (packageList == null) {
			packageList = new ArrayList<Packages>();
		}
		packageList.add(packaging);
	}

	public ReferencedSpecification getReferencedSpecification() {
		return referencedSpecification;
	}
	public void setReferencedSpecification(ReferencedSpecification referencedSpecification) {
		this.referencedSpecification = referencedSpecification;
	}

	
	public ManifestReference getReference() {
		return reference;
	}
	public void setReference(ManifestReference ref) {
		this.reference = ref;
	}
	
	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}
	
	public String getDestinationPlace() {
		return destination;
	}
	public void setDestinationPlace(String value) {
		this.destination = value;
	}
			
	public String getChangeFlag() {
		return changeFlag;
	}
	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}

	public TIN getCustodianTIN() {
		return custodianTIN;
	}
	public void setCustodianTIN(TIN custodianTIN) {
		this.custodianTIN = custodianTIN;
	}

	public CustomsNotification getCustomsNotification() {
		return customsNotification;
	}
	public void setCustomsNotification(CustomsNotification exitSuma) {
		this.customsNotification = exitSuma;
	}	
		
}
