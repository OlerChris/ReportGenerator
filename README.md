# ReportGenerator
Annotation based report generation and parsing

Writing Reports
		generate appache POI Workbook instances given a collection of data. CellTypes are implied based on the Java types or can be explicitly 
	specified. toString() is used to determine value of non-primitive/Wrapper types.
	
Reading Reports
		map appache POI Workbook instances to a Collection of data of a given Type. The specified type must have a noArg constructor. 
	Non-primitive/wrapper type fields can be mapped to but require a constructor that takes a single String as an argument.
	