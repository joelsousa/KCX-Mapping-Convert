package com.kewill.kcx.component.mapping.util;

/**
 * Exception which should be thrown when a unrecoverable FSS error occurs.
 * 
 * @author  ?
 * @version 1.0.00
 */
public class FssRuntimeException extends RuntimeException {
    /**
     * Needed because RuntimeException is serializable.
     * No internal use yet.
     */
    static final long serialVersionUID = 1L;

	public FssRuntimeException() {
		super();
	}

	public FssRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FssRuntimeException(String message) {
		super(message);
	}

	public FssRuntimeException(Throwable cause) {
		super(cause);
	}
	
}
