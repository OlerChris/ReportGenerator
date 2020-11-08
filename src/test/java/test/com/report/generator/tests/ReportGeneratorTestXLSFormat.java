package test.com.report.generator.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.report.generator.ReportGenerator;
import com.report.generator.ReportGeneratorImpl;
import com.report.generator.exception.ReportGenerationException;
import com.report.generator.type.ReportType;

import test.com.report.generator.ReportGeneratorTest;
import test.com.report.generator.classes.Mixed;
import test.com.report.generator.classes.StringAndInt;
import test.com.report.generator.classes.StringOnly;

/**
 * Tests Basic Functionality without any styling against a data type with only one layer of fields
 * 
 * @author Christopher Oler
 *
 */
@RunWith(JUnit4.class)
public class ReportGeneratorTestXLSFormat extends ReportGeneratorTest {	

    @Test //Generating class with String fields only
    public void test01() throws ReportGenerationException, IOException {
    	
    	ReportGenerator<StringOnly> generator = new ReportGeneratorImpl<>();
    	
        StringOnly x = new StringOnly("a","b");
        StringOnly y = new StringOnly("c","d");
        List<StringOnly> data = new ArrayList<>();
        data.add(x);
        data.add(y);

        Workbook report = generator.generateReport(data, StringOnly.class, ReportType.XLS);

        printWorkbook(report);

        Sheet sheet = report.getSheetAt(0);
        assertValidRow(sheet.getRow(0),"A","B");
        assertValidRow(sheet.getRow(1),"a","b");
        assertValidRow(sheet.getRow(2),"c","d");
        
        writeWorkbook(report, "StringOnlyFlat.xlsx");
    }
    
    @Test //Generating class with String, int, and Integer fields
    public void test02() throws ReportGenerationException, IOException {
    	
    	ReportGenerator<StringAndInt> generator = new ReportGeneratorImpl<>();

    	StringAndInt x = new StringAndInt("a", 1, 2);
    	StringAndInt y = new StringAndInt("b", 3, 4);
        List<StringAndInt> data = new ArrayList<>();
        data.add(x);
        data.add(y);

        Workbook report = generator.generateReport(data, StringAndInt.class, ReportType.XLS);

        printWorkbook(report);

        Sheet sheet = report.getSheetAt(0);
        assertValidRow(sheet.getRow(0),"A","B","C");
        assertValidRow(sheet.getRow(1),"a", 1, 2);
        assertValidRow(sheet.getRow(2),"b", 3, 4);
        assertCellType(sheet,1,0,CellType.STRING);
        assertCellType(sheet,1,1,CellType.NUMERIC);
        assertCellType(sheet,1,2,CellType.NUMERIC);
        writeWorkbook(report, "StringAndIntFlat.xlsx");
    }
    
    @Test //Generating class with String, all Primitives, all Wrappers, and a Class
    public void test03() throws ReportGenerationException, IOException {
    	
    	ReportGenerator<Mixed> generator = new ReportGeneratorImpl<>();

    	Mixed x = new Mixed(
    			"MY STRING",
    			'a',
    			'b',
    			(byte)4, 
    			(byte)6,
    			(short) 18,
    			(short) 19,
    			13, 
    			16, 
    			2l, 
    			1232445423l, 
    			true, 
    			false, 
    			1.4f, 
    			5.9f, 
    			21.2412, 
    			21324.241412, 
    			new StringOnly("", "")
    	);
        List<Mixed> data = new ArrayList<>();
        data.add(x);

        Workbook report = generator.generateReport(data, Mixed.class, ReportType.XLS);

        printWorkbook(report);

        Sheet sheet = report.getSheetAt(0);
        assertValidRow(sheet.getRow(0),"String","Character","char","Byte","byte","Short","short","Integer","int","Long","long","Boolean","boolean","Float","float","Double","double","Object");
        assertValidRow(sheet.getRow(1),
        		"MY STRING",
    			'a',
    			'b',
    			(byte)4, 
    			(byte)6,
    			(short) 18,
    			(short) 19,
    			13, 
    			16, 
    			2l, 
    			1232445423l, 
    			true, 
    			false, 
    			1.4f, 
    			5.9f, 
    			21.2412, 
    			21324.241412, 
    			StringOnly.TO_STRING
    	);
        assertCellType(sheet,1,0,CellType.STRING);
        assertCellType(sheet,1,1,CellType.STRING);
        assertCellType(sheet,1,2,CellType.STRING);
        assertCellType(sheet,1,3,CellType.NUMERIC);
        assertCellType(sheet,1,4,CellType.NUMERIC);
        assertCellType(sheet,1,5,CellType.NUMERIC);
        assertCellType(sheet,1,6,CellType.NUMERIC);
        assertCellType(sheet,1,7,CellType.NUMERIC);
        assertCellType(sheet,1,8,CellType.NUMERIC);
        assertCellType(sheet,1,9,CellType.NUMERIC);
        assertCellType(sheet,1,10,CellType.NUMERIC);
        assertCellType(sheet,1,11,CellType.BOOLEAN);
        assertCellType(sheet,1,12,CellType.BOOLEAN);
        assertCellType(sheet,1,13,CellType.NUMERIC);
        assertCellType(sheet,1,14,CellType.NUMERIC);
        assertCellType(sheet,1,15,CellType.NUMERIC);
        assertCellType(sheet,1,16,CellType.NUMERIC);
        assertCellType(sheet,1,17,CellType.STRING);

        writeWorkbook(report, "Mixed.xlsx");
    }

    @Test(expected = ReportGenerationException.class) // generateReport should throw exception for CSV type as it is not a workbook
    public void test04() throws ReportGenerationException {
    	
    	ReportGenerator<StringOnly> generator = new ReportGeneratorImpl<>();
    	generator.generateReport(null, StringOnly.class, ReportType.CSV);
    	
    }
}
