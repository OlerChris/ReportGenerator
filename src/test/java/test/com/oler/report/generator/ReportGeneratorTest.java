package test.com.oler.report.generator;

import org.apache.poi.ss.usermodel.*;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class ReportGeneratorTest {

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
                //getCellTypeEnum shown as deprecated for version 3.15
                //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                if (currentCell.getCellTypeEnum() == CellType.STRING) {
                    System.out.print(currentCell.getStringCellValue() + "--");
                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                    System.out.print(currentCell.getNumericCellValue() + "--");
                }

            }
            System.out.println();

        }
    }
}
