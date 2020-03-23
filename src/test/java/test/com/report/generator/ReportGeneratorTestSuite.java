package test.com.report.generator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.report.generator.tests.ReportGeneratorTest01;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ReportGeneratorTest01.class, 
})
public class ReportGeneratorTestSuite { }
