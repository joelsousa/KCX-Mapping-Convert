package com.kewill.kcx.tools.XLSReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.Utils;

public class DbKCXReader {
	

	/**
	 * Modul		: Db<br>
	 * Erstellt		: 28.10.2008<br>
	 * Beschreibung	: Datenbankroutinen. 
	 * 
	 * @author schmidt
	 * @version 1.0.00
	 */
	
		private static volatile Connection conn = null;
		private static volatile Statement stmt = null;
		private static volatile ResultSet rs = null;
		private static volatile boolean connected = false;

		
		
		
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
	    
	   
		private static ResultSet executeSQL(String sql) {
			try {
	            openConnection();
				stmt = conn.createStatement();
				Utils.log("(Db executeSQL) sql = " + sql);
				rs = stmt.executeQuery(sql);
	    	} catch (SQLException ex) {
	    			// handle any errors
	    			System.out.println("SQLException: " + ex.getMessage());
	    			System.out.println("SQLState: " + ex.getSQLState());
	    			System.out.println("VendorError: " + ex.getErrorCode());
	    			ex.printStackTrace();
	    	}
			return rs;
		}
		
	    public static void openConnection() {
		    if (connected) {
		        return;
		    } else {
		        if (!Config.isLoaded()) {
		            Config.configure("conf", "kcx.ini");
		        }
		        try {
		            DriverManager.registerDriver(new OracleDriver());
		            Properties info = new Properties();
//		            info.put("user", "KCX");
//		            info.put("password", "kcx");
//		            info.put("defaultPrefetchSize", "1");
//		            conn = DriverManager.getConnection("jdbc:oracle:thin:@pilot-db:1521:KCX", info);
	                info.put("user", Config.getDbuser());
	                info.put("password", Config.getDbpasswd());
	                info.put("defaultPrefetchSize", Config.getDbPrefetchSize());
//	                Utils.log("(Db openConnection) Config.getDbconnect() = " + Config.getDbconnect());
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

	

	
	public static synchronized ResultSet getKCXValues() {
	    	String sql = "select * from kcx_codes";
	    	executeSQL(sql);
	    	 
			
			return rs;
	    }
}
