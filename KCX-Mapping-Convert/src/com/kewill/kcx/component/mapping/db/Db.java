package com.kewill.kcx.component.mapping.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: Db<br>
 * Erstellt		: 28.10.2008<br>
 * Beschreibung	: Datenbankroutinen. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class Db {
	private static volatile Connection conn = null;
	private static volatile Statement stmt = null;
	private static volatile PreparedStatement updateStmt = null;
	private static volatile ResultSet rs = null;
//	private static volatile boolean connected = false;
    private static boolean connected = false;

	private Db() {
	}
	
	/**
     * Reads the customer table via local_id and local_id_type="UIDS".
	 * Returns the customer data for a local_id of type "UIDS" via ETN-Address. 
	 * @param etnAddress An ETN-Address
	 * @return The customer data as a CustomerDTO
	 */
	public static synchronized CustomerDTO getUidsCustomerFromEtnAddress(String etnAddress) {
		String sql = "select * from customer where local_id='" + etnAddress + "' and local_id_type='UIDS'";
		executeSQL(sql);
		CustomerDTO customer = new CustomerDTO();
        try {
            setCustomerData(customer);
        } catch (KcxNoDataFoundException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            throw new RuntimeException("No customer data found for local_id " 
                    + etnAddress 
                    + " and local_id_type UIDS! Check database entries!");
        }
		return customer;
	}
	/**
     * Reads the customer table via local_id and local_id_type.
	 * Returns the customer data for a local_id of type "UIDS" via ETN-Address. 
	 * @param address A local (country or message format specific) customer number
	 * @param type    Type of the local ID 
	 * @return The customer data as a CustomerDTO
	 */
	public static synchronized CustomerDTO getCustomerFromAddress(String address, String type) { //EI20110112
		String sql = "select * from customer where local_id= '" + address + "' and local_id_type='" + type + "'";
		executeSQL(sql);
		CustomerDTO customer = new CustomerDTO();
        try {
            setCustomerData(customer);
        } catch (KcxNoDataFoundException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            throw new RuntimeException("No customer data found for local_id " 
                    + address 
                    + " and local_id_type " + type + " Check database entries!");
        }
		return customer;
	}
	
	
    /**
     * Reads the customer table via country.
     * Returns a list of customers which communicate with the given country and
     * use the local_id_type given.
     *  
     * @param countryCode The country code to look up in the CUSTOMER table.
     * @param type        Format of the local id (field local_id_type)    
     * @return The customer data as a CustomerDTO
     */
    public static synchronized ArrayList<CustomerDTO> getCustomersForCountry(String countryCode, String type) {
        String sql = "select * from customer where country= '" + countryCode + "' and local_id_type='" + type + "'";
        executeSQL(sql);
        return getCustomerDataDTOs();
    }
    
    
	/**
     * Reads the fedex_completion table via kcx_id and key.
	 * Returns the fedex_completion data for a kcx_id and specify key. 
	 * @param kcxId KCX customer number
	 * @param key   Name of Key. Specifies where the value of field <code>value</code> comes from. 
	 * @return The fedex_completion data as a FedexCompletionDTO
	 */
	public static synchronized FedexCompletionDTO getFedexCompletion(String kcxId, String key) { //EI20110330		
		String sql = "select * from fedex_completion where kcx_id= '" + kcxId + "' and key='" + key + "'";
		executeSQL(sql);
		FedexCompletionDTO completion = new FedexCompletionDTO();
        try {
            setFedexCompletion(completion);
        } catch (KcxNoDataFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            throw new RuntimeException("No fedex_completion data found for kcx_id " 
                    + kcxId 
                    + " and key " + key + " Check database entries!");
        }
		return completion;
	}
    /**
     * Reads the customer table via local_id and local_id_type="FSS".
     * Returns the customer data for a local_id of type "UIDS" via ETN-Address. 
     * @param fssAddress An FSS-Address (man + nl)
     * @return The customer data as a CustomerDTO
     */
    public static synchronized CustomerDTO getCustomerFromFssAddress(String fssAddress) {
        String sql = "select * from customer where local_id='" + fssAddress + "' and local_id_type='FSS'";
        executeSQL(sql);
        CustomerDTO customer = new CustomerDTO();
        try {
            setCustomerData(customer);
        } catch (KcxNoDataFoundException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            throw new RuntimeException("No customer data found for local_id " 
                    + fssAddress 
                    + " and local_id_type FSS! Check database entries!");
        }
        return customer;
    }
    
    /**
     * Reads the customer table via kcx_id and local_id_type="UIDS".
     * Returns the customer data for a local_id of type "KIDS" via a KIDS-ID. 
     * @param receiver A KIDS-ID
     * @return The customer data as a CustomerDTO
     */
    public static synchronized CustomerDTO getUidsCustomerFromKidsId(String receiver) {
        String sql = "select * from customer where kcx_id='" + receiver + "' and local_id_type='KIDS'";
        executeSQL(sql);
        CustomerDTO customer = new CustomerDTO();
        try {
            setCustomerData(customer);
        } catch (KcxNoDataFoundException e) {
//            e.printStackTrace();
            throw new RuntimeException("No customer data found for kcx_id " 
                                     + receiver 
                                     + " and local_id_type KIDS! Check database entries!");
        }
        return customer;
    }
    
    /**
     * Reads the customer table via kcx_id, local_id_type and target country.
     * Returns the customer data from the customer table. 
     * @param id      KCX-ID
     * @param type    Message format (UIDS or FSS)
     * @param country Country code of the target country
     * @return The customer data as a CustomerDTO
     */
    public static synchronized CustomerDTO getCustomerFromKidsId(String id, String type, String country) {
        String sql = "select * from customer where kcx_id='" + id 
                                          + "' and local_id_type='" + type 
                                          + "' and country='" + country + "'";
        executeSQL(sql);
        CustomerDTO customer = new CustomerDTO();       
        try {
        	Utils.log("(DB getCustomerIdFromKewill) Customer data for kcx_id = " + id + " local_id_type " + type);
            setCustomerData(customer);
        } catch (KcxNoDataFoundException e) {        	
            throw new RuntimeException("No customer data found for kcx_id " 
                                     + id
                                     + " local_id_type "
                                     + type
                                     + " and country "
                                     + country
                                     + " ! Check database entries!");
        }
        return customer;
    }
    
    /**
     * Reads the customer_procedures table via kcx_id.
     * Returns the customer data for a local_id of type "FSS" via a KIDS-ID. 
     * @param id A KIDS-ID
     * @param procedure A customs procedure type (EXPORT, ...)
     * @return The customer_procedures data as a CustomerProcedureDTO
     */
    public static synchronized CustomerProcedureDTO getCustomerProceduresFromKidsId(String id, String procedure) {
        String sql = "select * from customer_procedures where kcx_id='" + id + "' and procedure='" + procedure + "'";
        executeSQL(sql);
        CustomerProcedureDTO customerProcedureDTO = new CustomerProcedureDTO();
        try {
            setCustomerProcedureData(customerProcedureDTO);
        } catch (KcxNoDataFoundException e) {
//            e.printStackTrace();
            throw new RuntimeException("No customer_procedures data found for kcx_id " 
                                     + id
                                     + " and procedure "
                                     + procedure
                                     + " ! Check database entries!");
        }
        return customerProcedureDTO;
    }
    
 // EI20130426:
    public static synchronized CustomerProcedureDTO getCustomerProceduresFromFormat(String id, String procedure, String format) {
        String sql = "select * from customer_procedures where kcx_id='" + id + "' and procedure='" + procedure + "' and msg_format='" + format + "'";
        executeSQL(sql);
        CustomerProcedureDTO customerProcedureDTO = new CustomerProcedureDTO();
        try {
            setCustomerProcedureData(customerProcedureDTO);
        } catch (KcxNoDataFoundException e) {
//            e.printStackTrace();
            throw new RuntimeException("No customer_procedures data found for kcx_id " + id
                                     + " and procedure " + procedure
                                     + " and msg_format " + format
                                     + " ! Check database entries!");
        }
        return customerProcedureDTO;
    }
    
    /**
     * Reads the customer_headers table via kcx_id.
     * Returns the customer_headers data for a KIDS_ID_id and PROCEDURE.
     * 
     * @param id A KIDS-ID
     * @param procedure A customs procedure type (EXPORT, ...)
     * @return The customer_headers data as a CustomerHeadersDTO
     */
    public static synchronized CustomerHeadersDTO getCustomerHeaders(String id, String procedure) {
        String sql = "select * from customer_headers where kcx_id='" + id + 
                                                "' and procedure='" + procedure.toUpperCase() + "'";
        executeSQL(sql);
        CustomerHeadersDTO customerHeadersDTO = new CustomerHeadersDTO();
        try {
            setCustomerHeadersData(customerHeadersDTO);
        } catch (KcxNoDataFoundException e) {
//            e.printStackTrace();
            Utils.log("(Db getCustomerHeaders) No customer_headers data found for kcx_id " 
                                     + id
                                     + " and procedure "
                                     + procedure
                                     + " ! Check database entries!");
            return null;
        }
        return customerHeadersDTO;
    }
    
    /**
     * Reads the customer_data table via kcx_id.
     * Returns the customer data record for a KIDS-ID. 
     * @param id A KIDS-ID
     * @return The customer_data record as a CustomerDataDTO
     */
    public static synchronized CustomerDataDTO getCustomerDataFromKidsId(String id) {
    	String sql = "select * from customer_data where kcx_id='" + id + "'";
        executeSQL(sql);
        CustomerDataDTO customerDataDTO = new CustomerDataDTO();
        try {
            setCustomerDataData(customerDataDTO);
        } catch (KcxNoDataFoundException e) {
            Utils.log(Utils.LOG_DEBUG, "(Db getCustomerDataFromKidsId) No CUSTOMER_DATA data found " +
            		                   "for kcx_id " + id + ".");
        }
        return customerDataDTO;
    }
    
    /**
     * Reads the customer_procedures table via kcx_id.
     * Returns the customer data for a local_id of type "FSS" via a KIDS-ID. 
     * @param id A KIDS-ID
     * @param procedure A customs procedure type (EXPORT, ...)
     * @param country A country code
     * @return The customer_licenses data as a CustomerLicenseDTO
     */
    public static synchronized CustomerLicenseDTO getCustomerLicenseFromKidsId(String id, 
                                                                               String procedure, 
                                                                               String country) {
        String sql = "select * from customer_licenses where kcx_id='" + id + 
                                                     "' and procedure='" + procedure + 
                                                     "' and country='" + country + "'";
        executeSQL(sql);
        CustomerLicenseDTO customerLicenseDTO = new CustomerLicenseDTO();
        try {
            setCustomerLicenseData(customerLicenseDTO);
        } catch (KcxNoDataFoundException e) {
//            e.printStackTrace();
            throw new RuntimeException("No customer_licenses data found for" +
            		                   " kcx_id " + id + 
            		                   " procedure " + procedure + 
            		                   " and country " + country + 
            		                   " ! Check database entries!");
        }
        return customerLicenseDTO;
    }
    
    /**
     * Reads the country_config table via a country code.
     * Returns the customer data record for a country code. 
     * @param countryCode A 2-letter country code
     * @return The country_config record as a CountryConfigDTO
     */
    public static synchronized CountryConfigDTO getCountryConfig(String countryCode) {
        String sql = "select * from country_config where country='" + countryCode + "'";
        executeSQL(sql);
        CountryConfigDTO countryConfigDTO = new CountryConfigDTO();
        try {
            setCountryConfigData(countryConfigDTO);
        } catch (KcxNoDataFoundException e) {
            Utils.log(Utils.LOG_DEBUG, "(Db getCountryConfig) No COUNTRY_CONFIG data found " +
                                       "for country code " + countryCode + ".");
            return null;
        }
        return countryConfigDTO;
    }
    
    public static synchronized KCXCodeDTO getKCXCodeFromValueCodeIdFromTo(
		String value, String kcxCodeID, String from, String to) {
		String sql = "select " + to + " from kcx_codes where kcxcode_Id='"
				+ kcxCodeID + "' and " + from + "='" + value + "'";
		Utils.log("**********************" + sql);
		executeSQL(sql);
		KCXCodeDTO kcxDTO = new KCXCodeDTO();
		try {
			setKCXCodeData(kcxDTO, to);
		} catch (KcxNoDataFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			if (value.startsWith("0") && value.matches("[0-9]+")) {
				Utils.log("(Db setKCXCodeData) Removing prepending Zero's and trying again!");
				sql = "select " + to + " from kcx_codes where kcxcode_Id='"
						+ kcxCodeID + "' and " + from + "='"
						+ Utils.removePrependingZeros(value) + "'";
				executeSQL(sql);
				try {
					setKCXCodeData(kcxDTO, to);
				} catch (KcxNoDataFoundException e1) {
	                throw new KcxNoDataFoundException("No kcx_code data found for kcx_codeID " + 
	                                                  kcxCodeID + " from " + from + "=" + value + 
	                                                  " to " + to + "! Check database entries!");
				}
			} else {
				throw new KcxNoDataFoundException("No kcx_code data found for kcx_codeID " + 
				                                  kcxCodeID + " from " + from + "=" + value + 
				                                  " to " + to + "! Check database entries!");
			}
		} finally {
			close();
		}
		
		return kcxDTO;
	}
    
    public static synchronized boolean isKCXCodeInDb(String kcxCode) {
    	String sql = "select * from kcx_codes where kcx_code='" + kcxCode + "'";
    	executeSQL(sql);
    	try {
				return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return false;
    }
    public static synchronized boolean isKCXValueEmpty(String kcxCode, String field) {
    	String sql = "select " + field + " from kcx_codes where kcx_code='" + kcxCode + "'";
    	executeSQL(sql);
    	try {
    		if (rs.next()) {
    			String val = rs.getString(field);
    			return val.trim().equals("");
    		} else {
    			return true;
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return false;
    }
    //EI20130114
    public static synchronized String getKCXCodeXmlTag(String kcxcodeId, String from, String value) {
    	String sql = "select * from kcx_codes where kcxcode_Id='"
		+ kcxcodeId + "' and " + from + "='" + value + "'";
    	executeSQL(sql);   
    	String xml = "";
    	try {
    		if (rs.next()) {
    			xml = rs.getString("xml_tag");    			
    		} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}		
		return xml;
    }
    
    public static synchronized int getDeclNum(String kewillId, String refnr) {
        int             lastnum;
        DeclNumsDTO     declNumsDTO     = null; 
        LastDeclNumDTO  lastnumDTO      = null;
        
        declNumsDTO = Db.getDeclNums(kewillId, refnr);
        
        if (declNumsDTO != null) {
                Utils.log("(Utils getDeclNum) existing declnum " + declNumsDTO.getDeclnum() + 
                        " to refnr " + refnr);
                return declNumsDTO.getDeclnum();
        } else {
            lastnumDTO = Db.getLastDeclNum();
            if (lastnumDTO == null) {
                lastnumDTO = new LastDeclNumDTO();
                lastnum = 1;
            } else {
                lastnum = lastnumDTO.getLastnum();
                lastnum++;
                if (lastnum > 99999999) {
                    lastnum = 1;
                }
            }
            Db.setLastDeclNum(lastnum);
            declNumsDTO = new DeclNumsDTO();
            declNumsDTO.setDeclnum(lastnum);
            declNumsDTO.setKcxId(kewillId);
            declNumsDTO.setRefnr(refnr);
            // CK 18.08.2010 format for insert: dd.mm.yyyy
            declNumsDTO.setDate(Utils.dateToString(new Date(System.currentTimeMillis())));
            Db.addDeclNum(declNumsDTO);
        }
        Utils.log("(Utils getDeclNo) new entry declnum " + lastnum + " to refnr " + refnr);
        return lastnum;
    }

    public static synchronized int getExistingDeclNum(String kewillId, String refnr) {
        int             num;
        DeclNumsDTO     declNumsDTO     = null; 
        
        declNumsDTO = Db.getDeclNums(kewillId, refnr);
        
        if (declNumsDTO != null) {
                Utils.log("(Utils getDeclNum) existing declnum " + declNumsDTO.getDeclnum() + 
                        " to refnr " + refnr);
                num = declNumsDTO.getDeclnum();
        } else {
            num = 0;
        }
        return num;
    }

    /**
     * Reads the declnums table via kcx_id and refnr.
     * Returns the declnums if existing for this customer with this refnr. 
     * @param id A KIDS-ID
     * @param refnr a ReferencNumber from a ExportDeclaration
     * @return The declnum as a DeclNumsDTO
     */
    public static synchronized DeclNumsDTO getDeclNums(String id, 
                                                       String refnr) {
        String sql = "select * from declnums where kcx_id='" + id + 
                                                     "' and refnr='" + refnr + "'";
        executeSQL(sql);
        DeclNumsDTO declNumsDTO = new DeclNumsDTO();
        try {
            setDeclnumsData(declNumsDTO);
        } catch (KcxNoDataFoundException e) {
            // no row found is not an error, row has to be added
            declNumsDTO = null;
            Utils.log(e.getMessage());
        } finally {
            close();
        }
        return declNumsDTO;
    }

    /**
     * Reads the lastdeclnum table without seach key because only one row is existing.
     * Returns the lastnum if existing. 
     * @return The lastnum as a LastDeclNumDTO
     */
    public static synchronized LastDeclNumDTO getLastDeclNum() { 
        String sql = "select * from lastdeclnum"; 
                                                     
        executeSQL(sql);
        LastDeclNumDTO lastDeclNumDTO = new LastDeclNumDTO();
        try {
            setLastDeclNumData(lastDeclNumDTO);
        } catch (KcxNoDataFoundException e) {
            // no row found is not an error, row has to be added
            Utils.log(e.getMessage());
            lastDeclNumDTO = null;
        } finally {
            close();
        }
        return lastDeclNumDTO;
    }

    /**
     * insert ReferenceNumber and DeclarationNumber combination for one KCX customer.
     * Returns the true/false. 
     * @param declNumsDTO DTO for table DECLNUMS.
     * @return The result as a Boolean.
     */
    public static synchronized Boolean addDeclNum(DeclNumsDTO declNumsDTO) { 
        String sql = "insert into declnums (kcx_id, declnum, refnr, mdat) VALUES ('" 
            + declNumsDTO.getKcxId() + "', '" + declNumsDTO.getDeclnum() + "', '"
            + declNumsDTO.getRefnr() + "', '" + declNumsDTO.getDate() + "')";
        if (executeSQLupdate(sql)) {
            close();    
        } else {
            close();
            throw new RuntimeException("Adding declnum and refnr into declnums failed for kcxid " 
                    + declNumsDTO.getKcxId() 
                    + " and refnr " + declNumsDTO.getRefnr()
                    + " and declnum " + declNumsDTO.getDeclnum());
        }
        return true;
    }

    /**
     * insert/update lastDeclnum.
     * Returns true/false. 
     * @param lastnum The number to set as last used declaration number.
     * @return The result as a Boolean.
     */
    public static synchronized Boolean setLastDeclNum(int lastnum) { 
        String sql = "update lastdeclnum set lastnum=" + lastnum + " where 1=1";
        if (executeSQLupdate(sql)) {
            close();
            return true;
        } else {
            // MS 20101202
            close();
            sql = "insert into lastdeclnum (lastnum) VALUES (" + lastnum + ")";
            if (executeSQLupdate(sql)) {
                close();
                return true;
            } else {
                close();
                throw new RuntimeException("inserting lastnum failed for " + lastnum); 
            }
        }
    }
    
    /**
     * delete records from lastDeclnum older than 30 days or reodays from config
     * Returns true/false. 
     * @param days The number to set as last used declaration number.
     * @return The result as a Boolean.
     */
    public static synchronized Boolean deleteDeclNums(int days) { 
    	String sql = "delete from declnums where declnums.mdat < sysdate-" + days;
    	
        if (executeSQLupdate(sql)) {
            close();
            return true;
        } else {
                close();
                throw new RuntimeException("deleting records older than " + days + " from declNums failed"); 
        }
    }
    
    /**
     * Read the message key data for a message from message_response_history table.
     * 
     * @param  kidsHeader KIDS header of the message.
     * @param  greeceHeader Greece header of the message.
     * @return true if a record for this message exists in the database, false if not.
     */
    public static synchronized boolean readMessageResponse(KidsHeader kidsHeader, GreeceHeader greeceHeader) {
        int year = Utils.stringToInt(kidsHeader.getYear());
        int month = Utils.stringToInt(kidsHeader.getMonth());
        int day = Utils.stringToInt(kidsHeader.getDay());
        int hour = Utils.stringToInt(kidsHeader.getTime().substring(0, 2));
        int minute = Utils.stringToInt(kidsHeader.getTime().substring(3, 5));
        GregorianCalendar g = new GregorianCalendar(year, month - 1, day, hour, minute);
        Timestamp timestamp = new Timestamp(g.getTimeInMillis());
        String ts = Utils.timestampToOracleString(timestamp);
        Utils.log("(Db readMessageResponse) ts = " + ts);
        
        String sql = "select * from message_response_history where " +
        		                                          "kcx_id='" + kidsHeader.getReceiver() + "' and " +
        		                                          "message_date=timestamp'" + ts + "' and " +
        		                                          "message_id='" + kidsHeader.getMessageID() + "' and " +
        		                                          "message_type='" + greeceHeader.getMesTypMES20() + "' and " +
        		                                          "in_reply_to='" + kidsHeader.getInReplyTo() + "' and " +
        		                                          "country_code='" + kidsHeader.getCountryCode() + "'";
        executeSQL(sql);
        boolean result = false;
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * Insert message key data plus a hash code of the message into message_response_history table.
     * 
     * @param  kidsHeader KIDS header of the message.
     * @param  greeceHeader Greece header of the message.
     */
    public static synchronized void addMessageResponseHistory(KidsHeader kidsHeader, GreeceHeader greeceHeader) {
        int year = Utils.stringToInt(kidsHeader.getYear());
        int month = Utils.stringToInt(kidsHeader.getMonth());
        int day = Utils.stringToInt(kidsHeader.getDay());
        int hour = Utils.stringToInt(kidsHeader.getTime().substring(0, 2));
        int minute = Utils.stringToInt(kidsHeader.getTime().substring(3, 5));
        GregorianCalendar g = new GregorianCalendar(year, month - 1, day, hour, minute);
        Timestamp messageDate = new Timestamp(g.getTimeInMillis());
        String messageDateTs = Utils.timestampToOracleString(messageDate);
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts = Utils.timestampToOracleString(timestamp);

        String receiver = kidsHeader.getReceiver();
        String messageId = kidsHeader.getMessageID();
        String messageType = greeceHeader.getMesTypMES20();
        String inReplyTo = kidsHeader.getInReplyTo();
        String countryCode = kidsHeader.getCountryCode();
        
        String insert = "insert into message_response_history VALUES ('" + receiver + "', " + 
                                                                           "timestamp'" + messageDateTs + "', '" + 
                                                                           messageId  + "', '" + 
                                                                           messageType  + "', '" +
                                                                           inReplyTo + "', '" +
                                                                           countryCode + "', " +
                                                                           "timestamp'" + ts + "')";
        if (executeSQLupdate(insert)) {
            close();    
        } else {
            throw new RuntimeException("Adding record into message_response_history failed for " 
                  + " receiver = " + receiver + ", messageId = " + messageId + ", messageType = " + messageType + ".");
        }
    }


    
    
//    public static synchronized void addMessageIdHistory(String messageIdOrg, String messageIdGen, 
//                                                        String receiver, String countryCode) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        // 2007-08-08 09:00:00.123456789
//        String ts = Utils.timestampToOracleString(timestamp);
//        
//        String insert = "insert into message_id_history VALUES ('" + messageIdOrg + "', '" + 
//                                                                     messageIdGen + "', '" + 
//                                                                     receiver   + "', '" + 
//                                                                     countryCode  + "', timestamp'" + ts + "')";
//        String update = "update message_id_history " +
//        	  	           "set message_id_org='" + messageIdOrg + "', '" + 
//        	  	               "message_receiver = '" + receiver + "', '" +
//                               "country_code = '" + countryCode  + "', '" +
//        	  	               "message_id_ts=timestamp'" + ts   + "', '" + 
//        	  	               "refnr_org = ' ', refnr_gen = ' '" +
//        	  	         "where message_id_gen='" + messageIdGen + "'";
//        if (executeSQLupdate(insert)) {
//            close();    
//        } else if (executeSQLupdate(update)) {
//            close();
//        } else {
//            throw new RuntimeException("Adding record into message_id_history failed for " 
//                    + " messageIdOrg = " + messageIdOrg + " and messageIdGen = " + messageIdGen + ".");
//        }
//    }

    /**
     * Insert original and generated message ID plus a timestamp into message_id_history table.
     * 
     * @param messageIdOrg Original message id.
     * @param messageIdGen Generated message id.
     * @param receiver     Message receiver.
     * @param countryCode  ISO 2 code of receiving country.
     */
    public static synchronized void addMessageIdHistory(String messageIdOrg, String messageIdGen, 
    																		 String receiver, String countryCode) {
    	addMessageIdHistory(messageIdOrg, messageIdGen, receiver, countryCode, null, null);
    }
    
    /**
     * Insert original and generated message ID plus a timestamp into message_id_history table.
     * 
     * @param messageIdOrg Original message id.
     * @param messageIdGen Generated message id.
     * @param receiver     Message receiver.
     * @param countryCode  ISO 2 code of receiving country.
     * @param refnrOrg  Original ReferenceNumber.
     * @param refnrGen  Generated ReferenceNumber.
     */
    public static synchronized void addMessageIdHistory(String messageIdOrg, String messageIdGen, 
                                                        String receiver, String countryCode, 
                                                        String refnrOrg, String refnrGen) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // 2007-08-08 09:00:00.123456789
        String ts = Utils.timestampToOracleString(timestamp);
        
        String insert = "insert into message_id_history VALUES ('" + messageIdOrg + "', '" + 
                                                                     messageIdGen + "', '" + 
                                                                     receiver   + "', '" + 
                                                                     // countryCode  + "', timestamp'" + ts + "')";
                                                                     countryCode  + "', timestamp'" + ts + "', '" + 
                                                                     refnrOrg   + "', '" +
                                                                     refnrGen   + "')";
        String update = "update message_id_history " +
        	  	           "set message_id_org='" + messageIdOrg 	+ "', " + 
        	  	               "message_receiver = '" + receiver 	+ "', " +
                               "country_code = '" + countryCode 	+ "', " +
        	  	               "message_id_ts=timestamp'" + ts 		+ "', " + 
        	  	               "refnr_org = '" + refnrOrg 			+ "', " +
        	  	               "refnr_gen = '" + refnrGen 			+ "' " +
        	  	         //EI20140408: "where message_id_gen='" + messageIdGen + "'";
        	  	             "where message_id_gen = '" + messageIdGen + "'" +
        	  	             " and countryCode = '" + countryCode + "'"; //EI20140408
        if (executeSQLupdate(insert)) {
            close();    
        } else if (executeSQLupdate(update)) {
            close();
        } else {
            throw new RuntimeException("Adding record into message_id_history failed for " 
                    + " messageIdOrg = " + messageIdOrg + " and messageIdGen = " + messageIdGen + ":  " + 
                    update + " / " + insert);
        }
    }
    /**
     * Read the original message ID for a generated message ID from message_id_history table.
     * 
     * @param messageIdGen Generated message id.
     * @param countryCode sender CountryCode.
     * @return The original message ID which belongs to the given generated message ID.
     */
    //public static synchronized MessageIdHistoryDTO readMessageIdHistory(String messageIdGen) {
    public static synchronized MessageIdHistoryDTO readMessageIdHistory(String messageIdGen, String countryCode) {	
        //EI20140408: String sql = "select * from message_id_history where message_id_gen='" + messageIdGen + "'";
    	String sql = "select * from message_id_history where message_id_gen='" + messageIdGen + "'" +
    												" and country_code='" + countryCode + "'";
        executeSQL(sql); 
        MessageIdHistoryDTO dto = new MessageIdHistoryDTO();
        try {
            setMessageIdHistoryData(dto);
        } catch (KcxNoDataFoundException e) {
            throw new RuntimeException("No messageIdHistory data found for generated message-id " + messageIdGen +
            		" and CountryCode " + countryCode + "!");
        }
        return dto;
    }

    /**
     * Reads the last running number for a Message-ID from table <code>message_id_numbers</code>.
     * Increments it by 1.
     * Writes the new value back into the database.
     * And finally returns the new value.
     *  
     * @return Last running number icremented by one.
     */
    public static synchronized long getMessageIdRunningNumber() {
        String sql    = "select lastnum from message_id_numbers";
        long lastnum = 0;
        executeSQL(sql);
        try {
            if (rs.next()) {
                lastnum = rs.getLong("lastnum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        
        if (lastnum == 0) {
            lastnum = 1;
            sql = "insert into message_id_numbers VALUES (" + lastnum + ")";
            if (!executeSQLupdate(sql)) {
                Utils.log("(Db getMessageIdRunningNumber) Inserting into message_id_numbers failed."); 
            }
            close();
        } else {
            lastnum = lastnum + 1;
            if (lastnum > 999999999) {
                lastnum = 1;
            }
            sql = "update message_id_numbers set lastnum=" + lastnum;
            if (!executeSQLupdate(sql)) {
                Utils.log("(Db getMessageIdRunningNumber) Updating message_id_numbers failed."); 
            }
            close();
        }
        return lastnum;
    }
    
    /**
     * Reads the last running number for a KCX-ID from table <code>customer_numbers</code>.
     * Increments it by 1.
     * Writes the new value back into the database.
     * And finally returns the new value.
     *  
     * @param kcxId KcxId of the customer
     * @return Last running number of customer incremented by one.
     */
    public static synchronized long getRunningNumberForKcxId(String kcxId) {
        String sql    = "select * from customer_numbers where kcx_id='" + kcxId + "'";
        long lastnum = 0;
        executeSQL(sql);
        try {
            if (rs.next()) {
                lastnum = rs.getLong("lastnum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        
        if (lastnum == 0) {
            lastnum = 1;
            sql = "insert into customer_numbers VALUES ('" + kcxId + "', " + lastnum + ")";
            if (!executeSQLupdate(sql)) {
                Utils.log("(Db getRunningNumberForKcxId) Inserting into customer_numbers failed for " + kcxId); 
            }
            close();
        } else {
            lastnum = lastnum + 1;
            if (lastnum > 999999999) {
                lastnum = 1;
            }
            sql = "update customer_numbers set lastnum=" + lastnum + " where kcx_id='" + kcxId + "'";
            if (!executeSQLupdate(sql)) {
                Utils.log("(Db getRunningNumberForKcxId) Updating customer_numbers failed for " + kcxId); 
            }
            close();
        }
        return lastnum;
    }
    
    /**
     * Sets the CustomerDTO from the first entry of the result set.
     * 
     * @param customer An empty CustomerDTO.
     */
    private static synchronized void setCustomerData(CustomerDTO customer) {
        try {
            // MS20120716 Begin
            if (rs == null) {
                Utils.log("(Db setCustomerData) Resultset is null! Cannot find out customer data!");
                throw new KcxNoDataFoundException("Resultset is null! Cannot find out customer data. Check the log file!");
            }
            // MS20120716 End
            if (rs.next()) {
                customer.setCorporateId(rs.getString("kcx_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCorporateIdentity(rs.getString("corporate_identity"));
                customer.setProtocol(rs.getString("protocol"));
                customer.setTargetDomain(rs.getString("target_domain"));
                customer.setBranchName(rs.getString("branch_name"));
                customer.setCountry(rs.getString("country"));
                customer.setLocalId(rs.getString("local_id"));
                customer.setLocalIdType(rs.getString("local_id_type"));
            } else {
                Utils.log("(Db setCustomerData) No row found!");
                throw new KcxNoDataFoundException("No customer data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
    /**
     * Returns a list of CustomerDTOs from a result set.
     * Does not throw an exception if no data is available from the result set.
     * 
     * @return A list of CustomerDTOs or null if no data was available from the result set.
     */
    private static synchronized ArrayList<CustomerDTO> getCustomerDataDTOs() {
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
        CustomerDTO customer = null;
        try {
            while (rs.next()) {
                customer = new CustomerDTO();
                customer.setCorporateId(rs.getString("kcx_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCorporateIdentity(rs.getString("corporate_identity"));
                customer.setProtocol(rs.getString("protocol"));
                customer.setTargetDomain(rs.getString("target_domain"));
                customer.setBranchName(rs.getString("branch_name"));
                customer.setCountry(rs.getString("country"));
                customer.setLocalId(rs.getString("local_id"));
                customer.setLocalIdType(rs.getString("local_id_type"));
                customerDTOs.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return customerDTOs;
    }
    private static synchronized void setFedexCompletion(FedexCompletionDTO completion) {  //EI20110330
        try {
            if (rs.next()) {
            	completion.setKcxId(rs.getString("kcx_id"));
            	completion.setKey(rs.getString("key"));
            	completion.setValue(rs.getString("value"));                
            } else {
                Utils.log("(Db setFedexCompletion) No row found!");
                throw new KcxNoDataFoundException("No fedex_completion data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
    private static synchronized void setDeclnumsData(DeclNumsDTO declnums) {
        try {
            if (rs.next()) {
            	declnums.setKcxId(rs.getString("kcx_id"));
            	declnums.setDeclnum(rs.getInt("declnum"));
            	declnums.setRefnr(rs.getString("refnr"));
            } else {
                Utils.log("(Db setDeclnumsData) No row found!");
                throw new KcxNoDataFoundException("No declnums data found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
    private static synchronized void setLastDeclNumData(LastDeclNumDTO lastnum) {
        try {
            if (rs.next()) {
            	lastnum.setLastnum(rs.getInt("lastnum"));
            } else {
                Utils.log("(Db setLastDeclNumData) No row found!");
                throw new KcxNoDataFoundException("No lastdeclnum data found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
    private static synchronized void setCustomerProcedureData(CustomerProcedureDTO customerProcedureDTO) {
        try {
            if (rs.next()) {
                customerProcedureDTO.setKcxId(rs.getString("kcx_id"));
                customerProcedureDTO.setProcedure(rs.getString("procedure"));
                customerProcedureDTO.setMsgFormat(rs.getString("msg_format"));
                customerProcedureDTO.setMappingCode(rs.getString("mapping_code"));
                customerProcedureDTO.setFssVersion(rs.getString("fss_version"));
                customerProcedureDTO.setUidsVersion(rs.getString("uids_version"));
                customerProcedureDTO.setKidsRelease(rs.getString("kids_release"));
            } else {
                Utils.log("(Db setCustomerProcedureData) No row found!");
                throw new KcxNoDataFoundException("No customer_procedure data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private static synchronized void setCustomerHeadersData(CustomerHeadersDTO customerHeaderDTO) {
        try {
            if (rs.next()) {
                customerHeaderDTO.setKcxId(rs.getString("kcx_id"));
                customerHeaderDTO.setProcedure(rs.getString("procedure"));
                customerHeaderDTO.setFssVersion(rs.getString("fss_version"));
                customerHeaderDTO.setKidsVersion(rs.getString("kids_version"));
                customerHeaderDTO.setUidsVersion(rs.getString("uids_version"));
            } else {
                Utils.log("(Db setCustomerHeadersData) No row found!");
                throw new KcxNoDataFoundException("No customer_headers data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private static synchronized void setCustomerDataData(CustomerDataDTO customerDataDTO) {
        try {
            if (rs.next()) {
                customerDataDTO.setKcxId(rs.getString("kcx_id"));
                customerDataDTO.setPdfOutFormat(rs.getString("pdf_out_format"));
                customerDataDTO.setBobName(rs.getString("bob_name"));     //EI20130215
            } else {
                Utils.log("(Db setCustomerDataData) No row found!");
                throw new KcxNoDataFoundException("No customer_data data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private static synchronized void setCustomerLicenseData(CustomerLicenseDTO customerLicenseDTO) {
        try {
            if (rs.next()) {
                customerLicenseDTO.setKcxId(rs.getString("kcx_id"));
                customerLicenseDTO.setCountry(rs.getString("country"));
                customerLicenseDTO.setProcedure(rs.getString("procedure"));
            } else {
                Utils.log("(Db setCustomerLicenseData) No row found!");
                throw new KcxNoDataFoundException("No customer_license data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    
    private static synchronized void setCountryConfigData(CountryConfigDTO countryConfigDTO) {
        try {
            if (rs.next()) {
                countryConfigDTO.setCountry(rs.getString("country"));
                countryConfigDTO.setXmlDir(rs.getString("xml_dir"));
                countryConfigDTO.setXmlSaveDir(rs.getString("xml_savedir"));
                countryConfigDTO.setPdfDir(rs.getString("pdf_dir"));
                countryConfigDTO.setPdfWaitTime(rs.getInt("pdf_waittime"));
                countryConfigDTO.setPdfWaitInterval(rs.getInt("pdf_waitinterval"));
                countryConfigDTO.setPdfMethod(rs.getString("pdf_method"));
            } else {
                Utils.log("(Db setCountryConfigData) No row found!");
                throw new KcxNoDataFoundException("No CountryConfig data found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    
    private static synchronized void setKCXCodeData(KCXCodeDTO kcxCode, String to) {
        try {
            if (rs.next()) {
            	 kcxCode.setKcxCode(rs.getString(to));
            } else {
                Utils.log("(Db setKCXCodeData) No row found!");
                throw new KcxNoDataFoundException("No KCXCode data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
    /**
     * Sets the MessageIdHistoryDTO from the first entry of the result set.
     * 
     * @param messageIdHistory An empty MessageIdHistoryDTO.
     */
    private static synchronized void setMessageIdHistoryData(MessageIdHistoryDTO messageIdHistory) {
        try {
            if (rs.next()) {
                messageIdHistory.setMessageIdOrg(rs.getString("message_id_org"));
                messageIdHistory.setMessageIdGen(rs.getString("message_id_gen"));
                messageIdHistory.setMessageReceiver(rs.getString("message_receiver"));
                messageIdHistory.setCountryCode(rs.getString("country_code"));
                messageIdHistory.setMessageIdTs(rs.getTimestamp("message_id_ts"));
            } else {
                Utils.log("(Db setMessageIdHistoryData) No row found!");
                throw new KcxNoDataFoundException("No messageIdHistory data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
	private static ResultSet executeSQL(String sql) {
	    rs = null; // MS20110817
		try {
            //EI20110322: Utils.log("(Db executeSQL) openConnection");
            openConnection();
          //EI20110322: Utils.log("(Db executeSQL) createStatement");
			stmt = conn.createStatement();
			Utils.log("(Db executeSQL) executeQuery sql " + sql);
			// MS20110817 Begin
			if (Config.isDbSetQueryTimeout()) {
	            stmt.setQueryTimeout(Config.getDbQueryTimeout());
			}
			// MS20110817 End
			rs = stmt.executeQuery(sql);			
			Utils.log("(Db executeSQL) executeQuery finished");
    	} catch (SQLException ex) {
    			// handle any errors
    			System.out.println("SQLException: " + ex.getMessage());
    			System.out.println("SQLState: " + ex.getSQLState());
    			System.out.println("VendorError: " + ex.getErrorCode());
    			ex.printStackTrace();
    	}
		return rs;
	}

	private static Boolean executeSQLupdate(String sql) {
		int i = 0;
//		Boolean result = false;
		try {
            Utils.log("(Db executeSQLupdate) openConnection");
            openConnection();
            Utils.log("(Db executeSQLupdate) createStatement");
            updateStmt = conn.prepareStatement(sql);
			Utils.log("(Db executeSQLupdate) prepareStatement sql " + sql);
            // MS20110817 Begin
            if (Config.isDbSetQueryTimeout()) {
                // MS20120427 Begin
                // stmt.setQueryTimeout(Config.getDbQueryTimeout());        
                updateStmt.setQueryTimeout(Config.getDbQueryTimeout());
                // MS20120427 End
            }
            // MS20110817 End
			i = updateStmt.executeUpdate(sql);
            Utils.log("(Db executeSQLupdate) prepareStatement finished");
    	} catch (SQLException ex) {
    			// handle any errors
    			System.out.println("SQLException: " + ex.getMessage());
    			System.out.println("SQLState: " + ex.getSQLState());
    			System.out.println("VendorError: " + ex.getErrorCode());
    			ex.printStackTrace();
    			i = -1;
    	}
    	
  		return i > 0;
	}
	
	
    public static void openConnection() {
        Utils.log("(Db openConnection) connected = " + connected);
	    if (connected) {
	        return;
	    } else {
	        if (!Config.isLoaded()) {
	            Config.configure("conf", "kcx.ini");
	        }
	        try {
	            DriverManager.registerDriver(new OracleDriver());
	            Properties info = new Properties();
//	            info.put("user", "KCX");
//	            info.put("password", "kcx");
//	            info.put("defaultPrefetchSize", "1");
//	            conn = DriverManager.getConnection("jdbc:oracle:thin:@pilot-db:1521:KCX", info);
                info.put("user", Config.getDbuser());
                info.put("password", Config.getDbpasswd());
                info.put("defaultPrefetchSize", Config.getDbPrefetchSize());
//                Utils.log("(Db openConnection) Config.getDbconnect() = " + Config.getDbconnect());
                conn = DriverManager.getConnection(Config.getDbconnect(), info);
                connected = true;
	        } catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	            ex.printStackTrace();
	        }
	    }
	}
	
	public static void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			rs = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
                e.printStackTrace();
			}
			stmt = null;
		}
        if (updateStmt != null) {
            try {
                updateStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            updateStmt = null;
        }
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//                e.printStackTrace();
//			}
//			conn = null;
//			connected = false;
//		}
	}

    public static void closeConnection() {
        Utils.log("(Db closeConnection) Closing connection. (connected = " + connected + ")");
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
            connected = false;
        }
    }

    public static boolean isConnected() {
        return connected;
    }

    public static Connection getConnection() {
        return conn;
    }

    /* EI2011103-b:   
    public static boolean replaceRefnr(String kcxId, String refnrOrg, String refnrNew) {
    	boolean ret = false;
        if (Utils.isStringEmpty(refnrOrg)) {
        	return ret;
        }
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());  // 2007-08-08 09:00:00.123456789
        String ts = Utils.timestampToOracleString(timestamp);
        
        String insert = "insert into refnrcy VALUES ('" + kcxId + "', '" + 
        												  refnrOrg + "', '" +     
        												  refnrNew + "', timestamp'" + ts + "')";  
        												
        String update = "update refnrcy " +
        					"set refnrNew='" + refnrNew 	+ "', '" + 
        					//"mdat = timestamp'" + ts + "'  +     					
        					" where kcxId='" + kcxId + "'" +
        					" and refnr = '" + refnrOrg + "'";
        
        if (executeSQLupdate(insert)) {
        	close();    
        	ret = true;
        //} else if (executeSQLupdate(update)) {
        //	close();
        } else {
        	throw new RuntimeException("Adding record into refnrcy failed for " 
        			+ " kcxId = " + kcxId + " and refnr = " + refnrOrg);
        }
        
        return ret;
    }
   
    public static String getOriginalRefnr(String kcxId, String refnrCY) {
    	String refnr = "";
    		String sql = "select * from refnrcy where kcx_id = '" + kcxId + "'" +
    										" and newrefnr = '" + refnrCY  + "'";
    		executeSQL(sql);    		
    		try {
    			refnr = setRefnr();
    		} catch (KcxNoDataFoundException e) {             
                throw new RuntimeException("select on refnrcy failed for " 
                		+ " kcxId = " + kcxId + " and refnrCY = " + refnrCY);
            }
    		return refnr;
    	} 
   
    private static synchronized String setRefnr() {
    	String refnr = "";
        try {
            if (rs.next()) {
            	refnr = rs.getString("refnr");               
            } else {
            	refnr = "";
                Utils.log("(Db setRefnr:  No row found!");
                throw new KcxNoDataFoundException("No data found in refnrCy ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return refnr;
    }    
    EI20111103-e */
   
    //EI20111104-b:    
    public static synchronized boolean updateMessageIdHistory(String refnrOrg, String messageIdGen, String countryCode) {        
         boolean ret = false;       
         String update = "update message_id_history " +
         	  	           "set refnr_org = '" + refnrOrg + "', " + 
         	  	               "refnr_gen = '" + messageIdGen + "' " +  
         	  	          //EI20140408:  "where message_id_gen='" + messageIdGen + "'";
         	  	         "where message_id_gen='" + messageIdGen + "'" +
         	  	         " and country_code = '" + countryCode + "'";  //EI20140520 fehlte " " vor and
 	   				     //"and message_receiver = '" + receiver + "'";
         if (executeSQLupdate(update)) {
             close(); 
             ret = true;
         } else {
             throw new RuntimeException("Update record in message_id_history failed for " 
                     + " refNr = " + refnrOrg + " and messageIdGen = " + messageIdGen + ".");            
         }
         return ret;
     }
    public static synchronized String getOriginalRefnr(String refnrCY, String countryCode) { //EI: all DB-access-methods have to be synchronized!!!
    	String refnr = "";
    	//EI20140408: String sql = "select * from message_id_history where message_Id_Gen = '" + refnrCY + "'";
    	String sql = "select * from message_id_history where message_Id_Gen = '" + refnrCY + 
    	"' and country_code = '" + countryCode + "'";   //EI20140520 fehlte ' am anfang direkt nach "
    	
    		executeSQL(sql);    		
    		try {
    			refnr = setRefnrOrg();    			
    		} catch (KcxNoDataFoundException e) {             
                throw new RuntimeException("select on message_id_history failed for " 
                		 + "refnrCY = " + refnrCY + " and CountryCode " + countryCode);
            }
    		return refnr;
    } 
    private static synchronized String setRefnrOrg() {
    	String refnr = "";
        try {
            if (rs.next()) {
            	refnr = rs.getString("refnr_org");               
            } else {
            	refnr = "";
                Utils.log("(Db setRefnr:  No row found in message_id_history!");
                throw new KcxNoDataFoundException("No data found in message_id_history ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return refnr;
    } 
    //EI20111104-e

    //AK20130418
    /**
     * 1. retrieves protocol which is counter field from customer data for BL mapping
     * 2. updates protocol with next number to that was read
     * 3. if protocol contains maximum possible value 99999 
     *    or protocol is not set: it will be set to 1
     * 
     * @param kcxId 	customer kcxId (corporate Id)
     * @param country 	customer country
     * 
     * @return retrieved value
     * 		 
     */
	public static synchronized String getProtocolForBL(String kcxId, String country) {
        String             	lastNum		= "1";
        int 				nextLastnum =  1;
        String				sql			= null;
        
        CustomerDTO customerDTO = Db.getCustomerFromKidsId(kcxId, "BL", country);
        
        //AK20130510
        if (customerDTO != null && customerDTO.getProtocol() != null && 
        	!Utils.isStringEmpty(customerDTO.getProtocol())) {
        		lastNum	= customerDTO.getProtocol().trim();
        }
        if (lastNum != null) {
                Utils.log("(Db getProtocol) existing protocol for local_id_type BL " + customerDTO.getProtocol() + 
                        " to lastnum ");
                nextLastnum = Integer.parseInt(lastNum) + 1;
        } 
        
        if (nextLastnum > 999999) {
               	nextLastnum = 1;
        }
        
       	sql = "update customer set protocol = '" + nextLastnum + 
    		   "' where kcx_id = '" + kcxId + 
    		   "' and local_id_type = 'BL'";
            
        executeSQLupdate(sql);

        return lastNum;
	}

    /**
     * 1. retrieves Entry for exiting test qualifier should be set only for testing environment
     * 
     * @param kcxId 	customer kcxId (corporate Id)
     * @param localIdType 	customer country
     * @param country 	customer country
     * 
     * @return retrieved value
     */
	public static synchronized String getCountryTestQualifier(String kcxId, String localIdType, String country) {
        CustomerDTO customerDTO = Db.getCustomerFromKidsId(kcxId, localIdType, country);
        
        if (customerDTO != null) {
        	return "T";
        }
        
        return "";
	}

	
	 public static synchronized AirportLandDTO getLandFromAirport(String airport) {   //EI20130628
	        String sql = "select * from airport_land where airport_code='" + airport  + "'";
	        executeSQL(sql);
	        AirportLandDTO airportLandDTO = new AirportLandDTO();
	        try {
	            setAirportLandData(airportLandDTO);
	        } catch (KcxNoDataFoundException e) {
//	            e.printStackTrace();
	            throw new RuntimeException("No airport_land data found for airport " 
	                                     + airport	                                    
	                                     + " ! Check database entries!");
	        }
	        return airportLandDTO;
	 }
	 public static synchronized AirportLandDTO getUncodeFromAirport(String airport_name) {   //EI20130628
	        String sql = "select * from airport_land where airport_bez='" + airport_name  + "'";
	        executeSQL(sql);
	        AirportLandDTO airportLandDTO = new AirportLandDTO();
	        try {
	            setAirportLandData(airportLandDTO);
	        } catch (KcxNoDataFoundException e) {
//	            e.printStackTrace();
	            throw new RuntimeException("No airport data found for airport " 
	                                     + airport_name	                                    
	                                     + " ! Check database entries!");
	        }
	        return airportLandDTO;
	 }
	 
	 private static synchronized void setAirportLandData(AirportLandDTO airportLandDTO) {  //EI20130628
	        try {
	            if (rs.next()) {
	            	airportLandDTO.setAirportLand(rs.getString("airport_code"));
	            	airportLandDTO.setAirportBez(rs.getString("airport_bez"));
	            	airportLandDTO.setAirportLand(rs.getString("airport_land"));
	            	airportLandDTO.setAirportDate(rs.getString("aiport_date"));	                
	            } else {
	                Utils.log("(Db setAirportLandData) No row found!");
	                throw new KcxNoDataFoundException("No airport_land data found! Check Database entries!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close();
	        }
	    }
	 
	//EI20140319:
	 //public static synchronized CustomerPackagecodesDTO getColartFromBDPPackageCode(String packageCode, String portSystem,
	 //							String declarationType, String qualifier) {   //EI20140319
	 public static synchronized CustomerPackagecodesDTO getColartFromBDPPackageCode(String packageCode) {		
	    String sql = "select * from customer_packagecodes where bdp='" + packageCode  + "'";
	    executeSQL(sql);
	    CustomerPackagecodesDTO customerPackageDTO = new CustomerPackagecodesDTO();
	    try {
	    	setCustomerPackageData(customerPackageDTO);
	    } catch (KcxNoDataFoundException e) {
//	            e.printStackTrace();
	    	throw new RuntimeException("No CustomerPackagecodes data found for PackageCode " 
	                                     + packageCode	                                    
	                                     + " ! Check database entries!");
	    }
	    return customerPackageDTO;
	 }
	 private static synchronized void setCustomerPackageData(CustomerPackagecodesDTO customerPackageDTO) {  //EI20140319
		 try {
			 if (rs.next()) {
				 customerPackageDTO.setUnRec21(rs.getString("un_rec21"));
				 customerPackageDTO.setDescription(rs.getString("description"));
				 customerPackageDTO.setZabis(rs.getString("zabis"));
				 customerPackageDTO.setZabisGpo(rs.getString("zabis_gpo"));	
				 customerPackageDTO.setZabisHds(rs.getString("zabis_hds"));
				 customerPackageDTO.setZabisBht(rs.getString("zabis_bht"));
	         } else {
	                Utils.log("(Db setCustomerPackageData) No row found!");
	                //EI20140319: throw new KcxNoDataFoundException("No setCustomerPackage data found! Check Database entries!");
	         }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	}
}
