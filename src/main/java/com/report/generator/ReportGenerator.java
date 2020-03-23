package com.report.generator;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;

import com.report.generator.exception.ReportGenerationException;

/**
 * Defines main functionality of Report Generator tool
 * 
 * @author Christopher Oler
 *
 */
public interface ReportGenerator<T> {

	/**
	 * 
	 * @param data
	 * @param type
	 * @return Workbook with 
	 * @throws ReportGenerationException
	 */
    public Workbook generateReport(Collection<? extends T> data, Class<T> type) throws ReportGenerationException;
    

}
