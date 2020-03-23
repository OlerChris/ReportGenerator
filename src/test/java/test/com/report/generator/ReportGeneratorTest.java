package test.com.report.generator;

import org.apache.poi.ss.usermodel.*;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class ReportGeneratorTest {
	
	private static String CELL_SIZE = "%8s";

    protected void assertValidRow(Row row, Object ... expected) {

        for(int i = 0; i < expected.length; i++){
            Cell cell = row.getCell(i);
            CellType cellType = cell.getCellType();
            if(expected[i] instanceof String){
                assertTrue(cellType == CellType.STRING);
                assertEquals(expected[i], cell.getStringCellValue());
            }
            else {
                assertEquals(expected[i].toString(), cell.getStringCellValue());
            }
        }
    }

    protected void printWorkbook(Workbook x){
        Sheet datatypeSheet = x.getSheetAt(0);
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
    
}
