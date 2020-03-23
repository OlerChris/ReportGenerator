package com.report.generator;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.report.generator.annotations.ReportColumn;
import com.report.generator.exception.ReportGenerationException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

public class ReportGeneratorImpl<T> implements ReportGenerator<T>{

    @Override
    public Workbook generateReport(Collection<? extends T> data, Class<T> type) throws ReportGenerationException {
    	try {
    		XSSFWorkbook workbook = new XSSFWorkbook();
    		formSheet(workbook, "data", data, type);
    		return workbook;
    	} catch (ReportGenerationException rge) {
    		throw rge;
    	} catch (Exception e) {
    		throw new ReportGenerationException(e);
    	}
    }

    private void  formSheet(XSSFWorkbook workbook, String sheetName, Collection<?> data, Class<?> type) throws NoSuchFieldException, IllegalAccessException, ReportGenerationException {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        Map<Integer, ColumnDefinition> columnDefinitions = determineColumns(type);
        createColumns(sheet, columnDefinitions);
        populateColumns(sheet, data, columnDefinitions, type);
    }

    private void populateColumns(XSSFSheet sheet, Collection<?> data, Map<Integer, ColumnDefinition> columnDefinitions, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
        int dataStart=1;
        for(Object item : data){
            XSSFRow row = sheet.createRow(dataStart);
            for(int i =0; i<columnDefinitions.size(); i++){
                ColumnDefinition def = columnDefinitions.get(i);
                Field f = type.getDeclaredField(def.fieldName);
                f.setAccessible(true);
                String value = f.get(item).toString();
                row.createCell(i).setCellValue(value);
            }
            dataStart++;
        }
    }

    private void createColumns(XSSFSheet sheet, Map<Integer, ColumnDefinition> columnDefinitions) {
        XSSFRow row = sheet.createRow(0);
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
                defs.add(x);
            }
        }
        for(int i = 0; i < defs.size(); i++){
            defMap.put(i,defs.get(i));
        }
        return defMap;
    }

    private class ColumnDefinition {
        String columnName;
        String fieldName;
    }
}
