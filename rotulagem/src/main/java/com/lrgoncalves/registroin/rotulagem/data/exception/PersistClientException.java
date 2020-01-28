/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.exception;

/**
 * @author digitallam
 *
 */
public class PersistClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3146959777882350698L;

	/**
	 * 
	 */
	public PersistClientException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public PersistClientException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public PersistClientException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PersistClientException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PersistClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
