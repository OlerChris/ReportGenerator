package com.report.generator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.report.generator.annotations.ReportColumn;
import com.report.generator.exception.CellTypeNotSupportedException;
import com.report.generator.exception.ReportGenerationException;
import com.report.generator.type.ReportType;

/**
 * Implementation of Report Generator
 * 
 * @author Christopher Oler
 *
 * @param <T> - Type that maps to column headers
 */
public class ReportGeneratorImpl<T> implements ReportGenerator<T>{

    @Override
    public Workbook generateReport(Collection<? extends T> data, Class<T> type) throws ReportGenerationException {
    	return generateReport(data, type, ReportType.XLSX);
    }
    
    @Override
    public Workbook generateReport(Collection<? extends T> data, Class<T> type, ReportType reportType) throws ReportGenerationException {
    	Workbook workbook;
    	try {
	    	switch(reportType) {
			case CSV:
				throw new ReportGenerationException("Workbooks can not be in CSV format. Try using generateCsv or writeReport methods.");
			case XLS:
				workbook = new HSSFWorkbook();
	    		formSheet(workbook, "sheet1", data, type);
	    		return workbook;
			case XLSX:
	    		workbook = new XSSFWorkbook();
	    		formSheet(workbook, "sheet1", data, type);
	    		return workbook;
			default:
				break;
	    	}
    	} catch (ReportGenerationException rge) {
    		throw rge;
    	} catch (Exception e) {
    		throw new ReportGenerationException(e);
    	}
    	return null;
    }

	private void formSheet(Workbook workbook, String sheetName, Collection<?> data, Class<?> type) throws NoSuchFieldException, IllegalAccessException, ReportGenerationException {
        Sheet sheet = workbook.createSheet(sheetName);
        Map<Integer, ColumnDefinition> columnDefinitions = determineColumns(type);
        createColumns(sheet, columnDefinitions);
        populateColumns(sheet, data, columnDefinitions, type);
    }

    private void populateColumns(Sheet sheet, Collection<?> data, Map<Integer, ColumnDefinition> columnDefinitions, Class<?> type) throws ReportGenerationException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int dataStart=1;
        for(Object item : data){
            Row row = sheet.createRow(dataStart);
            for(int i =0; i<columnDefinitions.size(); i++){
                ColumnDefinition def = columnDefinitions.get(i);
                Field f = type.getDeclaredField(def.fieldName);
                f.setAccessible(true);
                Cell cell = row.createCell(i, def.celltype);
                String value = f.get(item).toString();
                switch(def.celltype) {
				case BLANK:
				case STRING:
					cell.setCellValue(value);
					break;
				case BOOLEAN:
					cell.setCellValue(Boolean.valueOf(value));
					break;
				case NUMERIC:
					cell.setCellValue(Double.valueOf(value).doubleValue());
					break;
				case FORMULA:
				case ERROR:
				default:
					throw new CellTypeNotSupportedException(def.celltype, f.getName());
                }
            }
            dataStart++;
        }
    }

    private void createColumns(Sheet sheet, Map<Integer, ColumnDefinition> columnDefinitions) {
        Row row = sheet.createRow(0);
        for(Entry<Integer, ColumnDefinition> e : columnDefinitions.entrySet()) {
            row.createCell(e.getKey()).setCellValue(e.getValue().columnName);
        }
    }

    private Map<Integer, ColumnDefinition> determineColumns(Class<?> type) {
        Map<Integer, ColumnDefinition> defMap = new HashMap<>();
        List<ColumnDefinition> defs = new ArrayList<>();
        for(Field f : type.getDeclaredFields()){
        	f.setAccessible(true);
            if(f.isAnnotationPresent(ReportColumn.class)){
                ReportColumn info = f.getAnnotation(ReportColumn.class);
                ColumnDefinition x = new ColumnDefinition();
                x.columnName = info.name();
                x.fieldName = f.getName();
                if(info.cellType() == CellType._NONE) {
                	determineCellTypeAndFormat(f, x);
                } else {
                	x.celltype = info.cellType();
                }
                defs.add(x);
            }
        }
        for(int i = 0; i < defs.size(); i++){
            defMap.put(i,defs.get(i));
        }
        return defMap;
    }

    private void determineCellTypeAndFormat(Field f, ColumnDefinition x) {
		f.setAccessible(true);
		if (Number.class.isAssignableFrom(f.getType())
				|| int.class.isAssignableFrom(f.getType())
				|| long.class.isAssignableFrom(f.getType())
				|| short.class.isAssignableFrom(f.getType())
				|| byte.class.isAssignableFrom(f.getType())
				|| double.class.isAssignableFrom(f.getType())
				|| float.class.isAssignableFrom(f.getType())
		) {
			x.celltype = CellType.NUMERIC;
		} 
		else if (CharSequence.class.isAssignableFrom(f.getType())) {
			x.celltype = CellType.STRING;
		}
		else if (Boolean.class.isAssignableFrom(f.getType()) 
				|| boolean.class.isAssignableFrom(f.getType())
		) {
			x.celltype = CellType.BOOLEAN;
		} 
		else {
			x.celltype = CellType.BLANK;
		}
	}

	private class ColumnDefinition {
        String columnName;
        String fieldName;
        CellType celltype;
    }
	
}
