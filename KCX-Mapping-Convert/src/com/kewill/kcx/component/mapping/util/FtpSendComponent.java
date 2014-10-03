package com.kewill.kcx.component.mapping.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.transport.file.ExpressionFilenameParser;
import org.mule.transport.ftp.FtpConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FtpSendComponent implements Callable {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String host;
    private int port = 21;
    private boolean passive = true;
    private String path;
    private String user;
    private String password;
    private String tempPattern;
    private String outputPattern;
    private int timeout = 180000; //3 minute socket timeouts
    private boolean binary = true;
    private ExpressionFilenameParser efnp = new ExpressionFilenameParser();
    //pool properties, all have default values, see here for more detail http://commons.apache.org/pool/apidocs/org/apache/commons/pool/impl/GenericObjectPool.html
    private int maxActive = GenericObjectPool.DEFAULT_MAX_ACTIVE;
    private byte whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
    private long maxWait = GenericObjectPool.DEFAULT_MAX_WAIT;
    private int maxIdle = GenericObjectPool.DEFAULT_MAX_IDLE;
    private int minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;
    //change from default of false to true
    private boolean testOnBorrow = true;
    private boolean testOnReturn = GenericObjectPool.DEFAULT_TEST_ON_RETURN;
    //change from default of -1 (disabled) to 10 minutes i.e. the pool is scanned every 10 minutes to see if anyone has been idle for a while
    private long timeBetweenEvictionRunsMillis = 600000;
    private int numTestsPerEvictionRun = GenericObjectPool.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
    //change from default of 30 minutes to 4 minutes i.e. if an object has been idle for 4 minutes then it will be removed from the pool
    private long minEvictableIdleTimeMillis = 240000;
    //change from default of false to true i.e. the validateconnection call failure will result in the object being dropped even on idle objects, this stops as many stale and open connections
    private boolean testWhenIdle = GenericObjectPool.DEFAULT_TEST_WHILE_IDLE;
    private long softMinEvictableIdleTimeMillis = GenericObjectPool.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
    private boolean lifo = GenericObjectPool.DEFAULT_LIFO;

    public Object onCall(MuleEventContext muleEventContext) throws Exception {
        MuleMessage muleMessage = muleEventContext.getMessage();
        efnp.setMuleContext(muleEventContext.getMuleContext());
        String key = host + user + path;

        Utils.log("(FtpSendComponent onCall) key = " + key);

        InputStream messageToSend = simpleTransformToStream(muleEventContext.transformMessage());

        //work out the temporary file name if one is used
        String tempFilename = null;
        if (tempPattern != null) {
            tempFilename = efnp.getFilename(muleMessage, tempPattern);
        }

        //work out final file name
        String filename = muleMessage.getStringProperty(FtpConnector.PROPERTY_FILENAME, null);
        if (filename == null && outputPattern == null) {
            filename = UUID.randomUUID().toString();
            logger.info(key + " : FTP no filename (as a property) or output pattern given so defaulting output file name to a guid " + filename);
        } else if (outputPattern != null) {
            filename = efnp.getFilename(muleMessage, outputPattern);
        }

        FTPClient client = new FTPClient();
        try {
            Utils.log("(FtpSendComponent onCall) messageToSend = " + messageToSend);
            Utils.log("(FtpSendComponent onCall) tempFilename = " + tempFilename);
            Utils.log("(FtpSendComponent onCall) filename     = " + filename);
            Utils.log("(FtpSendComponent onCall) client       = " + client);
            Utils.log("(FtpSendComponent onCall) path         = " + path);
            
//            FtpConnectionFactory(host, port, user, password, passive, binary, path, timeout);
            
            client.connect(host, port);
            client.login(user, password);
            if (!client.setFileType(FTP.BINARY_FILE_TYPE)) {
                Utils.log("(FtpSendComponent onCall) Failed to set ftpClient object to BINARY_FILE_TYPE");
            }
//            if (!client.setFileType(FTP.ASCII_FILE_TYPE)) {
//                Utils.log("(FtpSendComponent onCall) Failed to set ftpClient object to ASCII_FILE_TYPE");
//            }
            client.changeWorkingDirectory(path);
            Utils.log("(FtpSendComponent onCall) directory   = " + client.printWorkingDirectory());
            client.setDataTimeout(timeout);
            
            
            if (!client.storeFile(tempFilename == null ? filename : tempFilename, messageToSend)) {
                String msg = "(FtpSendComponent onCall) " + key + ": FTP save failed to complete for " + host + ", " + user + ", " + path + ". FTPClient reply code = " + client.getReplyCode();
                Utils.log(msg);
                throw new Exception(msg);
            }
            logger.info(key + " : FTP saved file to " + (tempFilename == null ? filename : tempFilename));

            if (tempFilename != null) {
                if (!client.rename(tempFilename, filename)) {
                    String msg = "(FtpSendComponent onCall) " + key + ": FTP renaming failed " + host + ", " + user + ", " + path + ". FTPClient reply code = " + client.getReplyCode();
                    throw new Exception(msg);
                }
                logger.info(key + " : FTP renamed file from " + tempFilename + " to " + filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
            client = null;
            throw e;
        } finally {
            client.disconnect();
        }
        return muleMessage;
    }

    private InputStream simpleTransformToStream(Object object) throws Exception {
        if (object instanceof String) {
            return IOUtils.toInputStream((String) object);
        } else if (object instanceof byte[]) {
            return new ByteArrayInputStream((byte []) object);
            //return IOUtils.toInputStream(new String((byte[]) object));
        } else if (object instanceof InputStream) {
            return (InputStream) object;
        } else {
            throw new Exception(this.getClass().getName() + " expects String or byte[] data");
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host.trim();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempPattern() {
        return tempPattern;
    }

    public void setTempPattern(String tempPattern) {
        this.tempPattern = tempPattern;
    }

    public String getOutputPattern() {
        return outputPattern;
    }

    public void setOutputPattern(String outputPattern) {
        this.outputPattern = outputPattern;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isBinary() {
        return binary;
    }

    public void setBinary(boolean binary) {
        this.binary = binary;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public byte getWhenExhaustedAction() {
        return whenExhaustedAction;
    }

    public void setWhenExhaustedAction(byte whenExhaustedAction) {
        this.whenExhaustedAction = whenExhaustedAction;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public boolean isTestWhenIdle() {
        return testWhenIdle;
    }

    public void setTestWhenIdle(boolean testWhenIdle) {
        this.testWhenIdle = testWhenIdle;
    }

    public long getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    public void setSoftMinEvictableIdleTimeMillis(
            long softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public boolean isLifo() {
        return lifo;
    }

    public void setLifo(boolean lifo) {
        this.lifo = lifo;
    }
}
