package com.testNG.hybrid.automation.framework.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtil {
	FileInputStream fis;
	Workbook wb;
	FileOutputStream fos;

	public String readDataFromPropertiesFile(String key) throws Exception {
		try {
			String path = System.getProperty("user.dir") + Constants.propertiesFilePath;
			fis = new FileInputStream(path);
			Properties properties = new Properties();
			properties.load(fis);
			String value = properties.getProperty(key);
			return value;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

	}

	public String readDataFromExcel(String sheetName, int rowNo, int cellNo) throws Exception {
		try {
			String path = System.getProperty("user.dir") + Constants.excelFilePath;
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			if (sh == null) {
				throw new RuntimeException(sheetName + " Sheet not found");
			}

			Row ro = sh.getRow(rowNo);

			if (ro == null) {
				throw new RuntimeException("Row " + rowNo + " not found in sheet: " + sheetName);
			}

			Cell ce = ro.getCell(cellNo);
			if (ce == null) {
				throw new RuntimeException("Cell " + cellNo + " not found in row " + rowNo);
			}
			DataFormatter formatter = new DataFormatter();
			String data = formatter.formatCellValue(ce);

			return data;
		} finally {
			if (wb != null) {
				wb.close();
			}
			if (fis != null) {
				fis.close();
			}

		}
	}

	// for getting data form excel using data provider

	public Object[][] getDataFromExcelToDataProvider(String sheetName) throws Exception {
		try {
			String path = System.getProperty("user.dir") + Constants.excelFilePath;
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			if (sh == null) {
				throw new RuntimeException(sheetName + " Sheet not found");
			}
			int lastrownum = sh.getLastRowNum();
			int lastcellnum = sh.getRow(0).getLastCellNum();

			Object[][] data = new Object[lastrownum][lastcellnum];
			DataFormatter formatter = new DataFormatter();
			for (int i = 0; i < lastrownum; i++) {
				Row ro = sh.getRow(i + 1);
				for (int j = 0; j < lastcellnum; j++) {
					Cell ce = ro.getCell(j);
					data[i][j] = formatter.formatCellValue(ce);

				}
			}

			return data;
		} finally {
			if (wb != null) {
				wb.close();
			}
			if (fis != null) {
				fis.close();
			}

		}

	}

	public void writeDataIntoExcelSheet(String sheetName, int rowNo, int cellNo, String value) throws Exception {
		try {
			String path = System.getProperty("user.dir") + Constants.excelFilePath;
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			if (sh == null) {
				sh = wb.createSheet(sheetName);
			}
			Row ro = sh.getRow(rowNo);
			if (ro == null) {
				ro = sh.createRow(rowNo);
			}
			Cell ce = ro.getCell(cellNo);
			if (ce == null) {
				ce = ro.createCell(cellNo);
			}

			ce.setCellValue(value);

			fos = new FileOutputStream(path);
			wb.write(fos);
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (wb != null) {
				wb.close();
			}
			if (fis != null) {
				fis.close();
			}

		}

	}

}
