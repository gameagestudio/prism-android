package com.shopex.android.prism.exception;

public class PrismException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496587560248757069L;

	public PrismException() {
		
	}

	public PrismException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public PrismException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public PrismException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}
	
	

}
