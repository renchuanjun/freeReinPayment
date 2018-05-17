package com.fuqin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	/**
	 * 导入Excel
	 * @param execelFile excel文件的绝对路径
	 * @param index	从第几行开始读取
	 * @param impOrExpExcel 导入进库的接口
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	
	public static List<List<String>> impExcel(String execelFile,int index) throws FileNotFoundException, IOException {

		List<List<String>> alllist = new ArrayList<List<String>>();
		
		// 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)
		Workbook book = null;
		try {
			// Excel 2007获取方法
			book = new XSSFWorkbook(new FileInputStream(execelFile));
		} catch (Exception ex) {
			// Excel 2003获取方法
			book = new HSSFWorkbook(new FileInputStream(execelFile));
		}

		// 读取表格的第一个sheet页
		Sheet sheet = book.getSheetAt(0);
		// 定义 row、cell
		Row row;
		String cell;
		// 总共有多少行,从0开始
		int totalRows = sheet.getLastRowNum();

		// 循环输出表格中的内容,首先循环取出行,再根据行循环取出列
		for (int i = index; i <= totalRows; i++) {
			row = sheet.getRow(i);
			// 处理空行
			if (row == null) {
				continue;
			}
			//每一行的值的集合
			List<String> arrList = new ArrayList<String>();
			
			// 总共有多少列,从0开始
			int totalCells = row.getLastCellNum();
			for (int j = row.getFirstCellNum(); j < totalCells; j++) {
				// 处理空列
				if (row.getCell(j) == null) {
					arrList.add("");
					continue;
				}
				// 通过 row.getCell(j).toString() 获取单元格内容
				cell = row.getCell(j).toString();
				arrList.add(cell);
			}

			alllist.add(arrList);
		}
		
		return alllist;

	}

	/**
	 * 导出Excel表
	 * @param expFilePath 导出的路径
	 * @param sheetName Sheet名称
	 * @param list 数据
	 * @throws IOException 
	 */
	public static String expExcel(String expFilePath,String sheetName,List<List<String>> list) throws IOException {

		OutputStream os = null;
		Workbook book = null;
		String suffix = null;
		try {
			// 创建工作区(97-2003,2007)
			try {
				// Excel 2007获取方法
				book = new XSSFWorkbook();
				suffix = ".xlsx";
				
			} catch (Exception ex) {
				// Excel 2003获取方法
				book = new HSSFWorkbook();
				suffix = ".xls";
			}
			expFilePath = expFilePath + suffix;
			
			//创建相应的文件
			File file = new File(expFilePath);
			if (!file.isDirectory()) 
				file.createNewFile();
			
			// 输出流
			os = new FileOutputStream(expFilePath);
			
			// 创建第一个sheet页
			Sheet sheet = book.createSheet(sheetName);
			int count = list.size();
			if (list == null || count == 0) 
				return null;
			
			for (int i = 0; i < count; i++) {
				List<String> subList = list.get(i);
				int columnCount = subList.size();//列数
				Row row = sheet.createRow(i);// 生成新行
				for (int j = 0; j < columnCount; j++) {
					// 给每一行每一列赋值
					row.createCell(j).setCellValue(subList.get(j));
				}
			}
			// 写文件
			book.write(os);
			
			return suffix;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 关闭输出流
			if (os != null) {
				try {
					os.close();
					os = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}


