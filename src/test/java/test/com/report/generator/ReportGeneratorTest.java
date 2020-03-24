package test.com.report.generator;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class ReportGeneratorTest {
	
	private static final String CELL_SIZE = "%8s";
	private static final boolean PRINT = true;
	private static final boolean WRITE = true;


    protected void assertValidRow(Row row, Object ... expected) {

        for(int i = 0; i < expected.length; i++){
            Cell cell = row.getCell(i);
            switch(cell.getCellType()) {
			case BLANK:
			case STRING:
				assertEquals(expected[i].toString(), cell.getStringCellValue());
				break;
			case BOOLEAN:
				assertEquals(expected[i], cell.getBooleanCellValue());
				break;
			case NUMERIC:
				assertEquals(Double.valueOf(expected[i].toString()), Double.valueOf(cell.getNumericCellValue()));
				break;
			case ERROR:
			case FORMULA:
			case _NONE:
			default:
				assertTrue(false);
				break;
            
            }
        }
    }
    
	protected void assertCellType(Sheet sheet, int row, int column, CellType type) {
		Cell cell = sheet.getRow(row).getCell(column);
		assertEquals(type, cell.getCellType());
		
	}

    protected void printWorkbook(Workbook wb){
    	if(!PRINT) {
    		return;
    	}
    	
        Sheet datatypeSheet = wb.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();

        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {

                Cell currentCell = cellIterator.next();
                String cell = null;
                if (currentCell.getCellType() == CellType.STRING) {
                	cell = currentCell.getStringCellValue();
                } else if (currentCell.getCellType() == CellType.NUMERIC) {
                    cell = String.valueOf(currentCell.getNumericCellValue());
                }
                System.out.print(String.format(CELL_SIZE, cell));

            }
            System.out.println();
        }
        System.out.println();
    }
    
    protected void writeWorkbook(Workbook wb, String fileName) throws IOException {
    	if(!WRITE) {
    		return;
    	}
    	FileOutputStream fos = new FileOutputStream(new File(fileName));
    	wb.write(fos);
    	fos.flush();
    	wb.close();
    	fos.close();
    }
    
}
