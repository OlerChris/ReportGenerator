package com.report.generator.exception;

import org.apache.poi.ss.usermodel.CellType;

/**
 * Indicates a CellType was not supported
 * 
 * @author Christopher Oler
 *
 */
public class CellTypeNotSupportedException extends ReportGenerationException {
	
	private static final long serialVersionUID = 1060525950256161366L;

	public CellTypeNotSupportedException(CellType type, String fieldName){
		super("CellType of " + type + " was encountered but not supported. On field " + fieldName);
	}
	
}
