package com.report.generator;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.report.generator.annotations.ReportColumn;
import com.report.generator.exception.CellTypeNotSupportedException;
import com.report.generator.exception.ReportGenerationException;
import com.report.generator.exception.ReportReadingException;
import com.report.generator.settings.ReportGeneratorSettings;
import com.report.generator.settings.ReportStyling;
import com.report.generator.type.ReportType;

/*TODO LIST
 * Inverse
 * Color
 * Header
 * Title bar
 * Multiple sheets
 * indent
 * ColumnDefinition cache
 * allow specifying exact CellAddress for reading files
 * CSV
 */
/**
 * Implementation of Report Generator
 * 
 * @author Christopher Oler
 *
 * @param <T> - Type that maps to column headers
 */
public class ReportGeneratorImpl<T> implements ReportGenerator<T>{
	
	private ReportGeneratorSettings settings;
		
	public ReportGeneratorImpl() {
		this.settings = new ReportGeneratorSettings();
	}
	
	public ReportGeneratorImpl(ReportGeneratorSettings settings) {
		this.settings = settings;
	}
	
	public ReportGeneratorSettings getSettings() {
		return settings;
	}

    @Override
    public Workbook generateReport(String sheetName, Collection<? extends T> data, Class<T> type) throws ReportGenerationException {
    	return generateReport(sheetName, data, type, ReportType.XLSX);
    }
    
    @Override
    public Workbook generateReport(String sheetName, Collection<? extends T> data, Class<T> type, ReportType reportType) throws ReportGenerationException {
    	Workbook workbook = null;
    	if(sheetName == null) {
    		sheetName = "sheet1";
    	}
    	try {
	    	switch(reportType) {
			case CSV:
				throw new ReportGenerationException("Workbooks can not be in CSV format. Try using generateCsv or writeReport methods.");
			case XLS:
				workbook = new HSSFWorkbook();
//				initWorkbook(workbook);
	    		formSheet(workbook, sheetName, data, type);
	    		break;
			case XLSX:
	    		workbook = new XSSFWorkbook();
//	    		initWorkbook(workbook);
	    		formSheet(workbook, sheetName, data, type);
	    		break;
			default:
				throw new ReportGenerationException("ReportType not supported for generateReport");
	    	}
    	} catch (ReportGenerationException rge) {
    		throw rge;
    	} catch (Exception e) {
    		throw new ReportGenerationException(e);
    	}
    	return workbook;
    }

//	private Workbook initWorkbook(Workbook workbook) throws ReportGenerationException {
//		ReportStyling style = settings.getStyling();
//		if(style.hasLogo()) {
//			try {
//				workbook.addPicture(style.getLogoImage(), style.getLogoPictureType());
//			} catch (IOException e) {
//				throw new ReportGenerationException("exception encountered trying to initialize logo", e);
//			}
//		}
//		return workbook;
//	}

	private void formSheet(Workbook workbook, String sheetName, Collection<?> data, Class<?> type) throws NoSuchFieldException, IllegalAccessException, ReportGenerationException {
		ReportStyling style = settings.getStyling();
		Sheet sheet = workbook.createSheet(sheetName);
        Map<Integer, ColumnDefinition> columnDefinitions = determineColumns(type);
        int headerRow = style.getHeaderRow();
        createColumns(sheet, columnDefinitions, headerRow);
        populateColumns(sheet, data, columnDefinitions, type, headerRow+1);
        int maxColumn = columnDefinitions.keySet().stream().mapToInt(x -> x).max().orElse(0);
        if(settings.isFiltersEnabled()) {
        	sheet.setAutoFilter(new CellRangeAddress(headerRow, headerRow+1+data.size(), style.getStartColumn(), maxColumn + style.getStartColumn()));
        }
        if(style.hasLogo()) {
        	formLogo(workbook, sheet, style, maxColumn);
        }
    }

    private void formLogo(Workbook workbook, Sheet sheet, ReportStyling style, int maxColumn) throws ReportGenerationException {
    	try {
			int pictureIndex = workbook.addPicture(style.getLogoImage(), style.getLogoPictureType());
			sheet.createRow(0);
			sheet.getRow(0).setHeight((short) (15*style.getLogoHeight()));
			CellRangeAddress logoRange= new CellRangeAddress(0,0,style.getStartColumn(),maxColumn + style.getStartColumn());
			if(workbook instanceof XSSFWorkbook) {
				XSSFCellStyle backgroundStyle = (XSSFCellStyle) workbook.createCellStyle();
				backgroundStyle.setFillForegroundColor(new XSSFColor(style.getLogoBackgroundColor()));//TODO color map index
				backgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				CellUtil.getCell(sheet.getRow(logoRange.getFirstRow()), logoRange.getFirstColumn()).setCellStyle(backgroundStyle);
			}
			sheet.addMergedRegion(logoRange);
			CreationHelper helper = workbook.getCreationHelper();
			Drawing<?> drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setAnchorType( ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE );
			anchor.setCol1( style.getStartColumn() );
			anchor.setRow1( 0 );
			anchor.setRow2( 0 );
			anchor.setCol2( style.getStartColumn() );
			Picture pict = drawing.createPicture( anchor, pictureIndex );
			Dimension dimension = pict.getImageDimension();
			double originalHeight = dimension.getHeight();
			double originalWidth = dimension.getWidth();
			pict.resize();
			pict.resize(style.getLogoWidth()/originalWidth, style.getLogoHeight()/originalHeight);

		} catch (IOException e) {
			throw new ReportGenerationException("exception encountered trying to initialize logo", e);
		}
    }

	private void populateColumns(Sheet sheet, Collection<?> data, Map<Integer, ColumnDefinition> columnDefinitions, Class<?> type, int dataStart) throws ReportGenerationException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        for(Object item : data){
            Row row = sheet.createRow(dataStart);
            for(int i =0; i<columnDefinitions.size(); i++){
                ColumnDefinition def = columnDefinitions.get(i);
                Field f = def.field;
                f.setAccessible(true);
                Cell cell = row.createCell(i, def.celltype);
                String value = f.get(item).toString();
                switch(def.celltype) {
				case BLANK:
				case STRING:
					cell.setCellValue(value);
					break;
				case BOOLEAN:
					cell.setCellValue(Boolean.valueOf(value));
					break;
				case NUMERIC:
					cell.setCellValue(Double.valueOf(value).doubleValue());
					break;
				case FORMULA:
				case ERROR:
				default:
					throw new CellTypeNotSupportedException(def.celltype, f.getName());
                }
            }
            dataStart++;
        }
    }

    private void createColumns(Sheet sheet, Map<Integer, ColumnDefinition> columnDefinitions, int headerRow) {
        Row row = sheet.createRow(headerRow);
        for(Entry<Integer, ColumnDefinition> e : columnDefinitions.entrySet()) {
            Cell cell = row.createCell(e.getKey());
            cell.setCellValue(e.getValue().columnName);
        }
    }

    private Map<Integer, ColumnDefinition> determineColumns(Class<?> type) {
        Map<Integer, ColumnDefinition> defMap = new HashMap<>();
        List<ColumnDefinition> defs = new ArrayList<>();
        for(Field f : type.getDeclaredFields()){
        	f.setAccessible(true);
            if(f.isAnnotationPresent(ReportColumn.class)){
                ReportColumn info = f.getAnnotation(ReportColumn.class);
                ColumnDefinition x = new ColumnDefinition();
                x.columnName = info.name();
                x.field = f;
                if(info.cellType() == CellType._NONE) {
                	x.celltype = determineCellType(f);
                } else {
                	x.celltype = info.cellType();
                }
                defs.add(x);
            }
        }
        for(int i = 0; i < defs.size(); i++){
            defMap.put(i,defs.get(i));
        }
        return defMap;
    }

    private CellType determineCellType(Field f) {
		f.setAccessible(true);
		if (Number.class.isAssignableFrom(f.getType())
				|| int.class.isAssignableFrom(f.getType())
				|| long.class.isAssignableFrom(f.getType())
				|| short.class.isAssignableFrom(f.getType())
				|| byte.class.isAssignableFrom(f.getType())
				|| double.class.isAssignableFrom(f.getType())
				|| float.class.isAssignableFrom(f.getType())
		) {
			return CellType.NUMERIC;
		} 
		else if (CharSequence.class.isAssignableFrom(f.getType())) {
			return CellType.STRING;
		}
		else if (Boolean.class.isAssignableFrom(f.getType()) 
				|| boolean.class.isAssignableFrom(f.getType())
		) {
			return CellType.BOOLEAN;
		} 
		else {
			return CellType.BLANK;
		}
	}

	private class ColumnDefinition {
        Field field;
		String columnName;
        CellType celltype;
    }

	@Override
	public Collection<T> readWorkbook(Workbook wb, Class<T> type) throws ReportGenerationException {
		Sheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
		Map<Integer, ColumnDefinition> columns = determineColumns(type);
		return readSheet(sheet, columns, type);

	}
	
	@Override
	public Collection<T> readWorkbook(Workbook wb, String sheetName, Class<T> type) throws ReportGenerationException {
		Sheet sheet = wb.getSheet(sheetName);
		Map<Integer, ColumnDefinition> columns = determineColumns(type);
		return readSheet(sheet, columns, type);

	}

	private Collection<T> readSheet(Sheet sheet, Map<Integer, ColumnDefinition> columns, Class<T> type) throws ReportGenerationException {
		CellAddress ad = findDataStart(sheet, columns);
		int currentRow = ad.getRow();
		Collection<T> data = new ArrayList<>();
		while(currentRow < settings.getMaxRowNum()) {
			boolean isEmptyRow = true;
			T object;
			try {
				Constructor<T> contructor = type.getConstructor();
				contructor.setAccessible(true);
				object = type.getConstructor().newInstance();
			} catch (Exception e) {
				throw new ReportReadingException("could not instantiate type "+type.getName()+" while reading sheet. Ensure type has a noArgs constructor.", e);
			}
			Row row = sheet.getRow(currentRow);
			if(row == null) {
				return data;
			}
			isEmptyRow = populateData(object, columns, row, ad.getColumn());
			if(isEmptyRow) {
				return data;
			} else {
				data.add(object);
			}
			currentRow++;
		}
		//TODO log that maxRowNum was exceeded
		return data;
	}

	private boolean populateData(T object, Map<Integer, ReportGeneratorImpl<T>.ColumnDefinition> columns, Row row, int columnOffset) throws ReportGenerationException {
		boolean isEmptyRow = true;
		for(Integer headerIndex : columns.keySet()) {
			try {
				Field field = columns.get(headerIndex).field;
				field.setAccessible(true);
				Cell cell = row.getCell(columnOffset+headerIndex);
				if(cell == null) {
					continue;
				}
				String cellValue = getCellValue(cell).toString();
				Object value;
				if(cellValue == null || cellValue.length()==0) {
					continue;
				}
				isEmptyRow = false;
				if(field.getType().equals(Integer.class) || field.getType().equals(Integer.TYPE)) {
					value = Double.valueOf(cellValue).intValue();
				} else if(field.getType().equals(Double.class) || field.getType().equals(Double.TYPE)) {
					value = Double.valueOf(cellValue);
				} else if(field.getType().equals(Float.class) || field.getType().equals(Float.TYPE)) {
					value = Float.valueOf(cellValue);
				} else if(field.getType().equals(Short.class) || field.getType().equals(Short.TYPE)) {
					value = (short)Double.valueOf(cellValue).intValue();
				} else if(field.getType().equals(Byte.class) || field.getType().equals(Byte.TYPE)) {
					value = (byte)Double.valueOf(cellValue).intValue();
				} else if(field.getType().equals(Boolean.class) || field.getType().equals(Boolean.TYPE)) {
					value = Boolean.valueOf(cellValue);
				} else if(field.getType().equals(Long.class) || field.getType().equals(Long.TYPE)) {
					value = Double.valueOf(cellValue).longValue();
				} else if(field.getType().equals(Character.class) || field.getType().equals(Character.TYPE)) {
					if(cellValue.length()>1) {
						throw new ReportReadingException("Character field "+field.getName()+" must be of size one. Cell value was "+cellValue+" at row number "+row.getRowNum());
					}
					value = cellValue.charAt(0);
				} else if(field.getType().equals(String.class)) {
					value = cellValue;
				} else {
					Constructor<?> constructor = field.getType().getConstructor(String.class);
					constructor.setAccessible(true);
					value = constructor.newInstance(cellValue);
				}
				field.set(object, value);
			} catch (ReportGenerationException rge) {
				if(settings.isOptimisticParsing()) {
					continue;
				}
	    		throw rge;
	    	} catch (Exception e) {
	    		if(settings.isOptimisticParsing()) {
					continue;
				}
	    		throw new ReportGenerationException(e);
	    	}
		}
		return isEmptyRow;
	}

	private CellAddress findDataStart(Sheet sheet, Map<Integer, ColumnDefinition> columns) throws ReportGenerationException {
		int radius = settings.getSearchRadius();
		for(int rowNum = 0; rowNum < radius; rowNum++) {
			for(int colNum = 0; colNum+rowNum < radius; colNum++) {
				if(isHeaderStart(sheet,rowNum,colNum,columns)) {
					return new CellAddress(rowNum+1, colNum);
				}
			}
		}
		throw new ReportReadingException("Could not find Headers for data. Ensure the type is correct or try increasing searchRadius in settings");	
	}

	private boolean isHeaderStart(Sheet sheet, int rowNum, int colNum, Map<Integer, ColumnDefinition> columns) {
		for(Integer headerIndex : columns.keySet()) {
			Cell cell = sheet.getRow(rowNum).getCell(colNum+headerIndex);
			if(!getCellValue(cell).toString().equalsIgnoreCase(columns.get(headerIndex).columnName)) {
				return false;
			}
		}
		return true;
	}
	
	private Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		if(cellType.equals(CellType.FORMULA)) {
			cellType.equals(cell.getCachedFormulaResultType());
		}
		switch(cellType) {
			case NUMERIC:
				return cell.getNumericCellValue();
			case BOOLEAN:
				return cell.getBooleanCellValue();
			case STRING:
				return cell.getStringCellValue();
			case BLANK:
			case FORMULA:
			case _NONE:
			case ERROR:
			default:
				return null;
		}
	}
	
}
