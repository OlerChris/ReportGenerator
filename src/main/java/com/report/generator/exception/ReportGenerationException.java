package com.report.generator.exception;

/**
 * Generic exception class for all ReportGenerator issues
 * 
 * @author Christopher Oler
 *
 */
public class ReportGenerationException extends Exception {

	private static final long serialVersionUID = -7727666750885157633L;

	public ReportGenerationException() {
		super();
	}

	public ReportGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportGenerationException(String message) {
		super(message);
	}

	public ReportGenerationException(Throwable cause) {
		super(cause);
	}

}
