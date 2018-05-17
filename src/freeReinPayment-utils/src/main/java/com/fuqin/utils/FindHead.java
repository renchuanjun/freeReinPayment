package com.fuqin.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

public class FindHead {

	public static void main(String[] args) throws IOException {
	
	   String	fileurl="C:/Users/lenovo/Desktop/项目/海航管控项目/成员公司/成员公司_（不需更改的）20170911(加密)/渤海金控.xlsx";
		File file = new File(fileurl);
		FileInputStream fileInputStream = new FileInputStream(file);
		
		 	// 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)
				Workbook book = null;
				book = new XSSFWorkbook(fileInputStream);
				// 读取表格的第一个sheet页
				Sheet sheet = book.getSheetAt(0);
				// 定义 row、cell
				Row row;
				String cell ="";
				String cells="";
				// 总共有多少行,从0开始
				int totalRows = sheet.getLastRowNum();
				int colNum = sheet.getRow(0).getPhysicalNumberOfCells();
				Row firstrow = sheet.getRow(0);
				String heads="";
				for (int i = 0; i < colNum; i++) {
					//循环第一列，获取表头-即属性名
					Cell ce = firstrow.getCell(i);
					 if (ce.toString().equals("指标名称")) {
						 for (int j =1; j < totalRows+1; j++) {
							String	 head = sheet.getRow(j).getCell(i).toString()+"|";
							heads += head;
						 }
						 System.out.println(fileurl);
						 System.out.println(heads.substring(0, heads.length()-1));
					 }}
	}

}
