package test.com.report.generator.tests;

import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.report.generator.ReportGeneratorImpl;
import com.report.generator.exception.ReportGenerationException;
import com.report.generator.ReportGenerator;

import test.com.report.generator.ReportGeneratorTest;
import test.com.report.generator.classes.StringAndIntFlat;
import test.com.report.generator.classes.StringOnlyFlat;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author OlerC
 *
 */
@RunWith(JUnit4.class)
public class ReportGeneratorTest01 extends ReportGeneratorTest {

    @Test //Generating class with String fields only
    public void test01() throws ReportGenerationException {
    	ReportGenerator<StringOnlyFlat> generator = new ReportGeneratorImpl<StringOnlyFlat>();
    			
        StringOnlyFlat x = new StringOnlyFlat("a","b");
        StringOnlyFlat y = new StringOnlyFlat("c","d");
        List<StringOnlyFlat> data = new ArrayList<>();
        data.add(x);
        data.add(y);

        Workbook report = generator.generateReport(data, StringOnlyFlat.class);

        printWorkbook(report);

        Sheet sheet = report.getSheetAt(0);
        assertValidRow(sheet.getRow(0),"A","B");
        assertValidRow(sheet.getRow(1),"a","b");
        assertValidRow(sheet.getRow(2),"c","d");
    }
    
    @Test //Generating class with String, int, and Integer fields
    public void test02() throws ReportGenerationException {
    	ReportGenerator<StringAndIntFlat> generator = new ReportGeneratorImpl<StringAndIntFlat>();

    	StringAndIntFlat x = new StringAndIntFlat("a", 1, 2);
    	StringAndIntFlat y = new StringAndIntFlat("b", 3, 4);
        List<StringAndIntFlat> data = new ArrayList<>();
        data.add(x);
        data.add(y);

        Workbook report = generator.generateReport(data, StringAndIntFlat.class);

        printWorkbook(report);

        Sheet sheet = report.getSheetAt(0);
        assertValidRow(sheet.getRow(0),"A","B","C");
        assertValidRow(sheet.getRow(1),"a", 1, 2);
        assertValidRow(sheet.getRow(2),"b", 3, 4);
    }
}
