package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;
import com.google.common.collect.Table.Cell;

public class Utils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static int rowCount;
	static int cellCount;

	public static final int IMPLICIT_WAIT_TIME = 25;
	public static final int PAGE_LOAD_TIME = 20;

	public static String GenerateEmailWithTimeStamp() {
 
		Date date = new Date();
		String Timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "Testpokemon"+Timestamp+"@gmail.com";

	}
	

	public static Object[][] getTestDataFromExcel() {
	    File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorial\\qa\\testdata\\TEST_Data_crt.xlsx");
	    XSSFWorkbook workbook = null;
	    XSSFSheet sheet = null;
	    try {
	        FileInputStream fisExcel = new FileInputStream(excelFile);
	        workbook = new XSSFWorkbook(fisExcel);
	        sheet = workbook.getSheet("Login");
	    } catch (Throwable e) {
	        e.printStackTrace();
	    }

	    int rowCount = sheet.getPhysicalNumberOfRows(); // Number of rows with data
	    int cellCount = sheet.getRow(0).getPhysicalNumberOfCells(); // Number of columns in the first row

	    System.out.println("Number of rows: " + rowCount + " | Number of cells: " + cellCount);

	    Object dataset[][] = new Object[rowCount - 1][cellCount]; // Skip the header row

	    for (int i = 1; i < rowCount; i++) { // Start from 1 to skip the header row
	        XSSFRow row = sheet.getRow(i);
	        for (int j = 0; j < cellCount; j++) {
	            XSSFCell cell = row.getCell(j);
	            if (cell != null) {
	                CellType cellType = cell.getCellType();
	                switch (cellType) {
	                    case STRING:
	                        dataset[i - 1][j] = cell.getStringCellValue();
	                        break;
	                    case NUMERIC:
	                        dataset[i - 1][j] = String.valueOf((int) cell.getNumericCellValue());
	                        break;
	                    case BOOLEAN:
	                        dataset[i - 1][j] = cell.getBooleanCellValue();
	                        break;
	                    case FORMULA:
	                        dataset[i - 1][j] = cell.getCellFormula(); // Handle formulas if necessary
	                        break;
	                    default:
	                        dataset[i - 1][j] = null;
	                }
	            }
	        }
	    }
	    return dataset;
	}
	
	public static String captureScreenshots(WebDriver driver,String testname) {
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testname+".png";
		
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return destinationScreenshotPath;
			
	}
	
	}

