package test.com.report.generator.classes;

import com.report.generator.annotations.ReportColumn;

public class StringAndInt {

    @ReportColumn(name="A")
    private String a;
    @ReportColumn(name="B")
    private Integer b;
    @ReportColumn(name="C")
    private int c;
    
	public StringAndInt() {
		super();
	}

	public StringAndInt(String a, Integer b, int c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + c;
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
		StringAndInt other = (StringAndInt) obj;
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
		if (c != other.c)
			return false;
		return true;
	}  

}
