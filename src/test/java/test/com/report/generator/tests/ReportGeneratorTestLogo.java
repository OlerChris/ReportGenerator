package test.com.report.generator.tests;

import java.awt.Color;
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

import test.com.report.generator.ReportGeneratorTest;
import test.com.report.generator.classes.Mixed;
import test.com.report.generator.classes.StringOnly;

/**
 * Tests Basic Functionality without any styling against a data type with only one layer of fields
 * 
 * @author Christopher Oler
 *
 */
@RunWith(JUnit4.class)
public class ReportGeneratorTestLogo extends ReportGeneratorTest {
    
    @Test //Generating class with String, all Primitives, all Wrappers, and a Class
    public void test03() throws ReportGenerationException, IOException {
    	
    	ReportGenerator<Mixed> generator = new ReportGeneratorImpl<>();
    	generator.getSettings().getStyling()
    	.setLogo("src/test/resources/Logo.png", Workbook.PICTURE_TYPE_PNG,256,256)
    	.setLogoBackground(Color.BLUE);

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

        Workbook report = generator.generateReport(null, data, Mixed.class);

        printWorkbook(report);

        Sheet sheet = report.getSheetAt(0);
        assertValidRow(sheet.getRow(1),"String","Character","char","Byte","byte","Short","short","Integer","int","Long","long","Boolean","boolean","Float","float","Double","double","Object");
        assertValidRow(sheet.getRow(2),
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
        assertCellType(sheet,2,0,CellType.STRING);
        assertCellType(sheet,2,1,CellType.STRING);
        assertCellType(sheet,2,2,CellType.STRING);
        assertCellType(sheet,2,3,CellType.NUMERIC);
        assertCellType(sheet,2,4,CellType.NUMERIC);
        assertCellType(sheet,2,5,CellType.NUMERIC);
        assertCellType(sheet,2,6,CellType.NUMERIC);
        assertCellType(sheet,2,7,CellType.NUMERIC);
        assertCellType(sheet,2,8,CellType.NUMERIC);
        assertCellType(sheet,2,9,CellType.NUMERIC);
        assertCellType(sheet,2,10,CellType.NUMERIC);
        assertCellType(sheet,2,11,CellType.BOOLEAN);
        assertCellType(sheet,2,12,CellType.BOOLEAN);
        assertCellType(sheet,2,13,CellType.NUMERIC);
        assertCellType(sheet,2,14,CellType.NUMERIC);
        assertCellType(sheet,2,15,CellType.NUMERIC);
        assertCellType(sheet,2,16,CellType.NUMERIC);
        assertCellType(sheet,2,17,CellType.STRING);

        writeWorkbook(report, "Logo.xlsx");
    }

}
