/*
 * Funktion    : Prop.java
 * Titel       :
 * Erstellt    : 30.09.2008
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
package com.kewill.kcx.component.mapping.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * Modul		: Prop<br>
 * Erstellt		: 16.06.2009<br>
 * Beschreibung	: Lädt eine Propertiesdatei.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class Prop {
    private static final int ERR_NO_ERROR       = 0;
    private static final int ERR_FILE_NOT_EXIST = 1;
    
    private static Properties        prop          = new Properties(System.getProperties());
    private static List<String>      filesLoaded   = new Vector<String>();
    private static List<InputStream> streamsLoaded = new Vector<InputStream>();
    private static long              fileDate      = 0L;
    private static boolean           reloadConfig  = false;
    private static boolean           loaded        = false;
    
    private Prop() {
    }
    
    public static int load(final String path, final String file) {
        loaded = false;
        FileInputStream is = null;
        String loadFile = path.trim() + System.getProperty("file.separator") + file.trim();
//        Utils.log("(Prop load) Loading \"" + loadFile + "\"");
        
        File inFile = new File(loadFile);
        long lastModified = inFile.lastModified();
        if (fileDate == 0L) {
            fileDate = lastModified;
        }
        if (fileDate < lastModified) {
            reloadConfig = true;
            fileDate = lastModified;
        } else {
            reloadConfig = false;
        }
        
        if (isLoaded(loadFile) && !reloadConfig) {
            Utils.log("(Prop load) File " + loadFile + " already loaded.");
            return ERR_NO_ERROR;
        } 
        try {
//            File inFile = new File(loadFile);
            if (!inFile.exists()) {
                Utils.log("(Prop load) File " + loadFile + " does not exist.");
                return ERR_FILE_NOT_EXIST;
            }
            is = new FileInputStream(inFile);
            prop.load(is);
            filesLoaded.add(loadFile);
            loaded = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try { 
                    is.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ERR_NO_ERROR;
    }
    
    public static void load(InputStream is) {
        Utils.log("(Prop load) InputStream: " + is);
        if (isLoaded(is))   {
            Utils.log("(Prop load) Stream " + is + " already loaded.");
            return;
        } 
        try {
            prop.load(is);
            streamsLoaded.add(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try { 
                    is.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int reload(final String path, final String file) {
        Utils.log("(Prop reload) Reloading " + file);
        FileInputStream is = null;
        String loadFile = path.trim() + System.getProperty("file.separator") + file.trim();
        try {
            File inFile = new File(loadFile);
            if (!inFile.exists()) {
                Utils.log("(Prop reload) File " + loadFile + " does not exist.");
                return ERR_FILE_NOT_EXIST;
            }
            is = new FileInputStream(inFile);
            prop.load(is);
            filesLoaded.add(loadFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try { 
                    is.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ERR_NO_ERROR;
    }

    public static void reload(InputStream is) {
        Utils.log("(Prop reload) InputStream: " + is);
        try {
            prop.load(is);
            streamsLoaded.add(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try { 
                    is.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isLoaded(final String file) {
        return filesLoaded.contains(file);
    }
    
    private static boolean isLoaded(final InputStream is) {
        return streamsLoaded.contains(is);
    }
    
    public static String getProperty(final String key) {
        String value = prop.getProperty(key);
        return (value == null) ? null : value.trim();
    }

    public static String getProperty(final String key, final String def) {
        return prop.getProperty(key, def).trim();
    }

    public static int getIntProperty(final String key) {
        int ret = 0;
        try {
            String value = prop.getProperty(key);
            ret = (value == null) ? 0 : Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            ret = 0;
        }
        return ret;
    }

    public static int getIntProperty(final String key, final String def) {
        int ret = 0;
        try {
            ret = Integer.parseInt(prop.getProperty(key, def).trim());
        } catch (NumberFormatException e) {
            Utils.log(Utils.LOG_SEVERE, "(Prop getIntProperty) " + e + ": key = " +  key + ", default = " + def);
            ret = 0;
        }
        return ret;
    }

    public static boolean getBooleanProperty(final String key) {
        boolean ret = false;
        String value = prop.getProperty(key);
        ret = (value == null) ? false : Boolean.parseBoolean(value.trim());
        return ret;
    }

    public static boolean getBooleanProperty(final String key, final String def) {
        boolean ret = false;
        ret = Boolean.parseBoolean(prop.getProperty(key, def).trim());
        return ret;
    }

    public static Class getClassProperty(final String key) {
        return getClassProperty(key, null);
    }

    public static Class getClassProperty(final String key, final String def) {
        try {
            String value = prop.getProperty(key);
            return (value == null) ? null : Class.forName(value.trim());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isLoaded() {
        return loaded;
    }

}
