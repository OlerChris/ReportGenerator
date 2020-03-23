package test.com.report.generator.classes;

import com.report.generator.annotations.ReportColumn;

public class StringOnlyFlat {

    @ReportColumn(name="A")
    private String a;
    @ReportColumn(name="B")
    private String b;

    public StringOnlyFlat(){}

    public StringOnlyFlat(String a, String b) {
        this.a = a;
        this.b = b;
    }
}
