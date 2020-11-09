package test.com.report.generator.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.report.generator.ReportGenerator;
import com.report.generator.ReportGeneratorImpl;
import com.report.generator.exception.ReportGenerationException;

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
public class ReportGeneratorTestBasicReading extends ReportGeneratorTest {

    @Test //Read data with String fields only
    public void test01() throws ReportGenerationException, IOException {
    	
    	ReportGenerator<StringOnly> generator = new ReportGeneratorImpl<>();
    			
        StringOnly x = new StringOnly("a","b");
        StringOnly y = new StringOnly("c","d");
        List<StringOnly> data = new ArrayList<>();
        data.add(x);
        data.add(y);

        Workbook report = generator.generateReport(null, data, StringOnly.class);

        Collection<StringOnly> read = generator.readWorkbook(report, StringOnly.class);
        
        assertEquals(2, read.size());
        assertEquals(x, ((List<?>)read).get(0));
        assertEquals(y, ((List<?>)read).get(1));
    }
    
    @Test //Read data with String, int, and Integer fields
    public void test02() throws ReportGenerationException, IOException {
    	
    	ReportGenerator<StringAndInt> generator = new ReportGeneratorImpl<>();

    	StringAndInt x = new StringAndInt("a", 1, 2);
    	StringAndInt y = new StringAndInt("b", 3, 4);
        List<StringAndInt> data = new ArrayList<>();
        data.add(x);
        data.add(y);

        Workbook report = generator.generateReport(null, data, StringAndInt.class);

        Collection<StringAndInt> read = generator.readWorkbook(report, StringAndInt.class);
        assertEquals(2, read.size());
        assertEquals(x, ((List<?>)read).get(0));
        assertEquals(y, ((List<?>)read).get(1));
    }
    
    @Test //Read data with String, all Primitives, all Wrappers, and a Class
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

        Workbook report = generator.generateReport(null, data, Mixed.class);

        Collection<Mixed> read = generator.readWorkbook(report, Mixed.class);
        assertEquals(1, read.size());
        assertEquals(x, ((List<?>)read).get(0));
    }

}
