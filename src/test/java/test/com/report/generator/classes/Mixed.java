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
       
}
