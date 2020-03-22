package test.com.report.generator.set01.classes;

import com.report.generator.annotations.ReportColumn;

public class Basic {

    @ReportColumn(name="A")
    private String a;
    @ReportColumn(name="B")
    private String b;

    public Basic(){}

    public Basic(String a, String b) {
        this.a = a;
        this.b = b;
    }
}
