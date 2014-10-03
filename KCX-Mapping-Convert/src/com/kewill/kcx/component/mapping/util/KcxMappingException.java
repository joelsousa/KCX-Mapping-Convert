package com.kewill.kcx.component.mapping.util;

/**
 * Exception which should be thrown when a unrecoverable mapping error occurs.
 * 
 * @author  schmidt
 * @version 1.0.00
 */
public class KcxMappingException  extends RuntimeException {
    /**
     * Needed because RuntimeException is serializable.
     * No internal use yet.
     */
    static final long serialVersionUID = 1L;

    public KcxMappingException() {
        super();
    }

    public KcxMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public KcxMappingException(String message) {
        super(message);
    }

    public KcxMappingException(Throwable cause) {
        super(cause);
    }

}

