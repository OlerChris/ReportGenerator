package com.report.generator.exception;

/**
 * Exception encountered while reading report
 * 
 * @author Christopher Oler
 *
 */
public class ReportReadingException extends ReportGenerationException {

	private static final long serialVersionUID = 4507005108290916959L;

	public ReportReadingException() {
		super();
	}

	public ReportReadingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportReadingException(String message) {
		super(message);
	}

	public ReportReadingException(Throwable cause) {
		super(cause);
	}

}
