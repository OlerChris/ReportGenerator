package test.com.report.generator.classes;

import com.report.generator.annotations.ReportColumn;

public class StringOnly {
	
	public static final String TO_STRING = "string rep of StringOnlyFlat object";

    @ReportColumn(name="A")
    private String a;
    @ReportColumn(name="B")
    private String b;

    public StringOnly(){}
    
    public StringOnly(String x){this.a = x;}

    public StringOnly(String a, String b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public String toString() {
    	return TO_STRING;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringOnly other = (StringOnly) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
    
    
}
