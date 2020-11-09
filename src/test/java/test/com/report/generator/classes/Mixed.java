package test.com.report.generator.classes;

import com.report.generator.annotations.ReportColumn;

public class Mixed {

    @ReportColumn(name="String")
    private String stringObj;
    
    @ReportColumn(name="Character")
    private Character charObj;
    
    @ReportColumn(name="char")
    private char charPrim;
    
    @ReportColumn(name="Byte")
    private Byte byteObj;
    
    @ReportColumn(name="byte")
    private byte bytePrim;
    
    @ReportColumn(name="Short")
    private Short shortObj;
    
    @ReportColumn(name="short")
    private short shortPrim;
    
    @ReportColumn(name="Integer")
    private Integer intObj;
    
    @ReportColumn(name="int")
    private int intPrim;
    
    @ReportColumn(name="Long")
    private Long longObj;
    
    @ReportColumn(name="long")
    private long longPrim;
    
    @ReportColumn(name="Boolean")
    private Boolean boolObj;
    
    @ReportColumn(name="boolean")
    private boolean boolPrim;
    
    @ReportColumn(name="Float")
    private Float fltObj;
    
    @ReportColumn(name="float")
    private float fltPrim;
    
    @ReportColumn(name="Double")
    private Double doubleObj;
    
    @ReportColumn(name="double")
    private double doublePrim;
    
    @ReportColumn(name="Object")
    private StringOnly stringOnly;

    public Mixed(){}

	public Mixed(String stringObj, Character charObj, char charPrim, Byte byteObj, byte bytePrim, Short shortObj,
			short shortPrim, Integer intObj, int intPrim, Long longObj, long longPrim, Boolean boolObj,
			boolean boolPrim, Float fltObj, float fltPrim, Double doubleObj, double doublePrim, StringOnly stringOnly) {
		super();
		this.stringObj = stringObj;
		this.charObj = charObj;
		this.charPrim = charPrim;
		this.byteObj = byteObj;
		this.bytePrim = bytePrim;
		this.shortObj = shortObj;
		this.shortPrim = shortPrim;
		this.intObj = intObj;
		this.intPrim = intPrim;
		this.longObj = longObj;
		this.longPrim = longPrim;
		this.boolObj = boolObj;
		this.boolPrim = boolPrim;
		this.fltObj = fltObj;
		this.fltPrim = fltPrim;
		this.doubleObj = doubleObj;
		this.doublePrim = doublePrim;
		this.stringOnly = stringOnly;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boolObj == null) ? 0 : boolObj.hashCode());
		result = prime * result + (boolPrim ? 1231 : 1237);
		result = prime * result + ((byteObj == null) ? 0 : byteObj.hashCode());
		result = prime * result + bytePrim;
		result = prime * result + ((charObj == null) ? 0 : charObj.hashCode());
		result = prime * result + charPrim;
		result = prime * result + ((doubleObj == null) ? 0 : doubleObj.hashCode());
		long temp;
		temp = Double.doubleToLongBits(doublePrim);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fltObj == null) ? 0 : fltObj.hashCode());
		result = prime * result + Float.floatToIntBits(fltPrim);
		result = prime * result + ((intObj == null) ? 0 : intObj.hashCode());
		result = prime * result + intPrim;
		result = prime * result + ((longObj == null) ? 0 : longObj.hashCode());
		result = prime * result + (int) (longPrim ^ (longPrim >>> 32));
		result = prime * result + ((shortObj == null) ? 0 : shortObj.hashCode());
		result = prime * result + shortPrim;
		result = prime * result + ((stringObj == null) ? 0 : stringObj.hashCode());
		result = prime * result + ((stringOnly == null) ? 0 : stringOnly.hashCode());
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
		Mixed other = (Mixed) obj;
		if (boolObj == null) {
			if (other.boolObj != null)
				return false;
		} else if (!boolObj.equals(other.boolObj))
			return false;
		if (boolPrim != other.boolPrim)
			return false;
		if (byteObj == null) {
			if (other.byteObj != null)
				return false;
		} else if (!byteObj.equals(other.byteObj))
			return false;
		if (bytePrim != other.bytePrim)
			return false;
		if (charObj == null) {
			if (other.charObj != null)
				return false;
		} else if (!charObj.equals(other.charObj))
			return false;
		if (charPrim != other.charPrim)
			return false;
		if (doubleObj == null) {
			if (other.doubleObj != null)
				return false;
		} else if (!doubleObj.equals(other.doubleObj))
			return false;
		if (Double.doubleToLongBits(doublePrim) != Double.doubleToLongBits(other.doublePrim))
			return false;
		if (fltObj == null) {
			if (other.fltObj != null)
				return false;
		} else if (!fltObj.equals(other.fltObj))
			return false;
		if (Float.floatToIntBits(fltPrim) != Float.floatToIntBits(other.fltPrim))
			return false;
		if (intObj == null) {
			if (other.intObj != null)
				return false;
		} else if (!intObj.equals(other.intObj))
			return false;
		if (intPrim != other.intPrim)
			return false;
		if (longObj == null) {
			if (other.longObj != null)
				return false;
		} else if (!longObj.equals(other.longObj))
			return false;
		if (longPrim != other.longPrim)
			return false;
		if (shortObj == null) {
			if (other.shortObj != null)
				return false;
		} else if (!shortObj.equals(other.shortObj))
			return false;
		if (shortPrim != other.shortPrim)
			return false;
		if (stringObj == null) {
			if (other.stringObj != null)
				return false;
		} else if (!stringObj.equals(other.stringObj))
			return false;
		if (stringOnly == null) {
			if (other.stringOnly != null)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Mixed [stringObj=" + stringObj + ", charObj=" + charObj + ", charPrim=" + charPrim + ", byteObj="
				+ byteObj + ", bytePrim=" + bytePrim + ", shortObj=" + shortObj + ", shortPrim=" + shortPrim
				+ ", intObj=" + intObj + ", intPrim=" + intPrim + ", longObj=" + longObj + ", longPrim=" + longPrim
				+ ", boolObj=" + boolObj + ", boolPrim=" + boolPrim + ", fltObj=" + fltObj + ", fltPrim=" + fltPrim
				+ ", doubleObj=" + doubleObj + ", doublePrim=" + doublePrim + ", stringOnly=" + stringOnly + "]";
	}
       
}
