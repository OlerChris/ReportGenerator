package test.com.report.generator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.report.generator.tests.ReportGeneratorTestBasics;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ReportGeneratorTestBasics.class, 
})
public class ReportGeneratorTestSuite { }
