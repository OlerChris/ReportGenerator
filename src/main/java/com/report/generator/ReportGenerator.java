package com.report.generator;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;

public interface ReportGenerator {

    public Workbook generateReport(Collection<?> data, Class<?> type) throws NoSuchFieldException, IllegalAccessException;
}
