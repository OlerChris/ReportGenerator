package com.report.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;

import com.report.generator.exception.ReportGenerationException;
import com.report.generator.settings.ReportGeneratorSettings;
import com.report.generator.type.ReportType;

/**
 * Main functionality of Report Generator tool
 * 
 * @author Christopher Oler
 *
 */
public interface ReportGenerator<T> {

	/**
	 * Generates a Workbook based on a Class type and entries corresponding to a Collection of data
	 * @param sheetName - Name of the Sheet
	 * @param data - Collection containing entries to populate rows of the report
	 * @param type - The class of the data from which column headers are determined
	 * @return Workbook object in XlSX format 
	 * @throws ReportGenerationException if an issue occured while generating the Workbook
	 */
    public Workbook generateReport(String sheetName, Collection<? extends T> data, Class<T> type) throws ReportGenerationException;

	/**
	 * Generates a Workbook based on a Class type and entries corresponding to a Collection of data
	 * @param sheetName - Name of the Sheet
	 * @param data - Collection containing entries to populate rows of the report
	 * @param type - The class of the data from which column headers are determined
	 * @return Workbook object in the specified reportType  format
	 * @throws ReportGenerationException if an issue occured while generating the Workbook
	 */
    public Workbook generateReport(String sheetName, Collection<? extends T> data, Class<T> type, ReportType reportType) throws ReportGenerationException;
    
    /**
     * Reads the rows of a workbook and return a Collection of Data of the specified type
     * @param wb - the Workbook to be Read
     * @param type - The Class the individual rows are mapped to
     * @return Collection of instances of the specified class
     * @throws ReportGenerationException if Workbook could not be read
     */
    public Collection<T> readWorkbook(Workbook wb, Class<T> type) throws ReportGenerationException;
    
    /**
     * Reads the rows of a workbook and return a Collection of Data of the specified type
     * @param wb - the Workbook to be Read
     * @param sheetName - the name of the sheet in the Workbook to be parsed
     * @param type - The Class the individual rows are mapped to
     * @return Collection of instances of the specified class
     * @throws ReportGenerationException if Workbook could not be read
     */
	public Collection<T> readWorkbook(Workbook wb, String sheetName, Class<T> type) throws ReportGenerationException;

	/**
	 * Returns settings for report generation
	 * @return ReportGenerationSettings instance
	 */
	public ReportGeneratorSettings getSettings();
    
    /**
     * Helper method to write a Workbook to file system
     * @param wb - Workbook to be generated
     * @param pathName - location and name of the file to be created
     * @throws IOException
     */
    public static void writeWorkbook(Workbook wb, String pathName) throws IOException {
	    	FileOutputStream fos = new FileOutputStream(new File(pathName));
	    	wb.write(fos);
	    	fos.flush();
	    	wb.close();
	    	fos.close();
    }    

}
