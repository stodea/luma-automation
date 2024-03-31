package resources;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProviders {

    public static Object[][] excelDataProvider(String dataProviderSheetName) throws IOException {
        FileInputStream workbookLocation = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);

        int sheetIndex = workbook.getSheetIndex(dataProviderSheetName);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        int noOfRows = sheet.getPhysicalNumberOfRows() - 1;
        int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[noOfRows][noOfColumns];
        for (int i = 1; i <= noOfRows; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df = new DataFormatter();
                data[i - 1][j] = df.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }
        workbook.close();
        workbookLocation.close();
        return data;
    }

    @DataProvider
    public static Object[][] dataForOrderProduct() throws IOException {
        String dataProviderSheetName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        return excelDataProvider(dataProviderSheetName);
    }
}
