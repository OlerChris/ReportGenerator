package test.com.oler.report.generator.set01;

import com.oler.report.generator.ReportGenerator;
import com.oler.report.generator.ReportGeneratorI;
import org.apache.poi.ss.usermodel.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.com.oler.report.generator.ReportGeneratorTest;
import test.com.oler.report.generator.set01.classes.Basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit4.class)
public class ReportGeneratorTest01 extends ReportGeneratorTest {

    private static ReportGeneratorI generator;

    @Before
    public void initEach(){
        generator = new ReportGenerator();
    }

    @Test //Generating class with String fields only
    public void test01() throws NoSuchFieldException, IllegalAccessException {
        Basic x = new Basic("a","b");
        Basic y = new Basic("c","d");
        List<Basic> data = new ArrayList<>();
        data.add(x);
        data.add(y);

        Workbook report = generator.generateReport(data, Basic.class);

        //printWorkbook(report);

        Sheet sheet = report.getSheetAt(0);
        assertValidRow(sheet.getRow(0),"A","B");
        assertValidRow(sheet.getRow(1),"a","b");
        assertValidRow(sheet.getRow(2),"c","d");
    }
}
