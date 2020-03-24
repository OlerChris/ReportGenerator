package test.com.report.generator.classes;

import com.report.generator.annotations.ReportColumn;

public class StringOnly {
	
	public static final String TO_STRING = "string rep of StringOnlyFlat object";

    @ReportColumn(name="A")
    private String a;
    @ReportColumn(name="B")
    private String b;

    public StringOnly(){}

    public StringOnly(String a, String b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public String toString() {
    	return TO_STRING;
    }
}
