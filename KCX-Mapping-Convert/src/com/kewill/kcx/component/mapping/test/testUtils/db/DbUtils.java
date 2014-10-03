/*
 * Funktion    : DbUtils.java
 * Titel       :
 * Erstellt    : 13.12.2010
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
package com.kewill.kcx.component.mapping.test.testUtils.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.db.KcxNoDataFoundException;
import com.kewill.kcx.component.mapping.util.Utils;

public class DbUtils {
    private static volatile Connection conn = null;
    private static volatile Statement  stmt = null;
    private static volatile ResultSet  rs   = null;

    /**
     * Reads the table TEST_VALUES via key parameters provided via a TestValuesDTO
     * to find the value for the given tag names and test case.
     *  
     * @param testValuesDTO DTO with the parameters of the test case and tag name to find a value for. 
     * @return The value to use for the specified tag names in the specified test case.
     */
    public static synchronized String getTestValues(TestValuesDTO testValuesDTO) {
        String procedure   = testValuesDTO.getProcedure();
        String messageType = testValuesDTO.getMessageType();
        String messageName = testValuesDTO.getMessageName();
        String testCase    = testValuesDTO.getTestCase();
        String tagLevel1   = testValuesDTO.getTagLevel1();
        String tagLevel2   = testValuesDTO.getTagLevel2();
        String tagLevel3   = testValuesDTO.getTagLevel3();
        
        String sql = "select * from test_values where procedure='" + procedure + 
                                               "' and message_type='" + messageType +
                                               "' and message_name='" + messageName +
                                               "' and test_case='" + testCase +
                                               "' and tag_level_1='" + tagLevel1 +
                                               "' and tag_level_2='" + tagLevel2 +
                                               "' and tag_level_3='" + tagLevel3 +
                                               "'";
        
        executeSQL(sql);
        TestValuesDTO testValues = new TestValuesDTO();
        try {
            setTestValuesData(testValues);
        } catch (KcxNoDataFoundException e) {
            Utils.log("(DbUtils getTestValues) No TEST_VALUES data found for sql select statement: " + sql);
//            throw new RuntimeException("No TEST_VALUES data found for sql select statement: " + sql + 
//                    "! Check database entries!");
        }
        return testValues.getValue();
    }
    
    private static synchronized void setTestValuesData(TestValuesDTO testValues) {
        try {
            if (rs.next()) {
                testValues.setValue(rs.getString("value"));
            } else {
//                Utils.log("(DbUtils setTestValuesData) No row found!");
                throw new KcxNoDataFoundException("No TEST_VALUES data found! Check Database entries!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    

    private static ResultSet executeSQL(String sql) {
        if (!Config.isLoaded()) {
            Config.configure("conf", "xsd2xml.ini");
        }
        try {
//            Utils.log("(Db executeSQL) openConnection");
            Db.openConnection();
            conn = Db.getConnection();
//            Utils.log("(Db executeSQL) createStatement");
            stmt = conn.createStatement();
//            Utils.log("(Db executeSQL) executeQuery sql " + sql);
            rs = stmt.executeQuery(sql);
//            Utils.log("(Db executeSQL) executeQuery finished");
        } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
        }
        return rs;
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
    }

}
