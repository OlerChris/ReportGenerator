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
	 * Generates a Workbook based on a Class type and entries corresponding to a Collection of data
	 * @param data - Collection containing entries to populate rows of the report
	 * @param type - The class of the data from which column headers are determined
	 * @return Workbook object in XlSX format 
	 * @throws ReportGenerationException if an issue occured while generating the Workbook
	 */
    public Workbook generateReport(Collection<? extends T> data, Class<T> type) throws ReportGenerationException;
    

}
