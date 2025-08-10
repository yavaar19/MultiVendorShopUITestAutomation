package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private final String filePath;
    private final String sheetName;

    public ExcelReader(String filePath, String sheetName) {

        this.filePath = filePath;
        this.sheetName = sheetName;

    }

    public Object[][] getData() throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheet(sheetName);

        int col = sheet.getRow(0).getPhysicalNumberOfCells();
        int row = sheet.getPhysicalNumberOfRows();

        Object[][] data = new Object[row - 1][col];

        for (int r = 1; r < row; r++) {

            Row currentRow = sheet.getRow(r);

            for (int c = 0; c < col; c++) {

                try {

                    Cell cell = currentRow.getCell(c);
                    cell.setCellType(CellType.STRING);
                    data[r - 1][c] = currentRow.getCell(c).getStringCellValue();


                } catch (Exception e) {

                    data[r - 1][c] = "";

                }

            }

        }

        workbook.close();
        fis.close();

        return data;

    }

}
