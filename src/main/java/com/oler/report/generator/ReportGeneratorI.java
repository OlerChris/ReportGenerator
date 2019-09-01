package com.oler.report.generator;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;

public interface ReportGeneratorI {

    public Workbook generateReport(Collection<?> data, Class<?> type) throws NoSuchFieldException, IllegalAccessException;
}
