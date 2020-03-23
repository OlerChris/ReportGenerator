package test.com.report.generator.classes;

import com.report.generator.annotations.ReportColumn;

public class StringAndIntFlat {

    @ReportColumn(name="A")
    private String a;
    @ReportColumn(name="B")
    private Integer b;
    @ReportColumn(name="C")
    private int c;
    
	public StringAndIntFlat() {
		super();
	}

	public StringAndIntFlat(String a, Integer b, int c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}  

}
