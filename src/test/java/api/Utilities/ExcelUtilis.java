package api.Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtilis {

public static Object[][] readDataFromExcel(String filePath, String sheetName) throws IOException {
	DataFormatter format = new DataFormatter();
    FileInputStream file = new FileInputStream(filePath);
    XSSFWorkbook workbook = new XSSFWorkbook(file);
    XSSFSheet sheet = workbook.getSheet(sheetName);

    int rowCount = sheet.getLastRowNum();
    int colCount = sheet.getRow(0).getLastCellNum();

    Object[][] data = new Object[rowCount][colCount];

    for (int i = 1; i <= rowCount; i++) {
        Row row = sheet.getRow(i);
        for (int j = 0; j < colCount; j++) {
            data[i - 1][j] = row.getCell(j)!= null? format.formatCellValue(row.getCell(j)): "";
        }
    }

    workbook.close();
    file.close();

    return data;
}
	
	

}
