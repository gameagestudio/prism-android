package com.shopex.android.prism.exception;

public class PrismDialogException extends PrismException {
	private static final long serialVersionUID = 1L;
	private int mErrorCode;
	private String mFailingUrl;

	public PrismDialogException(String message, int errorCode, String failingUrl) {
		super(message);
		this.mErrorCode = errorCode;
		this.mFailingUrl = failingUrl;
	}

	public int getErrorCode() {
		return this.mErrorCode;
	}

	public String getFailingUrl() {
		return this.mFailingUrl;
	}
}
