package com.unifs.behavioranalysis.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张恭雨
 *Excel操作工具
 */
public class ExcelUtil {
	private final static String excel2003L=".xls";		//2003版本的excel
	private final static String excel2007U=".xlsx";		//2007版本的excel
	//日志记录
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	
	/*
	 * Excel导入
	 */
	public static List<List<Object>> getListByExcel(InputStream in, String fileName) throws IOException{
		List<List<Object>> list;
		//创建Excel工作簿
		
		Workbook work=getWorkbook(in,fileName);
		System.out.println(in+":"+work+":"+fileName);
		if(null==work){
			logger.error("Excel工作簿为空！");
			return null;			
		}
		Sheet sheet;
		Row row;
		Cell cell;
		list=new ArrayList<List<Object>>();
		//遍历Excel中所有的sheet
		for(int i=0;i<work.getNumberOfSheets();i++){
			sheet=work.getSheetAt(i);
			if(sheet==null)
				continue;
			//遍历当前sheet中的所有行
			//去掉头部，初始值+1
			for(int j=sheet.getFirstRowNum()+1;j<=sheet.getLastRowNum();j++){
				//读取一行
				row=sheet.getRow(j);
				//去掉空行
				if(row==null)
					continue;
				//遍历所有的列
				List<Object> li=new ArrayList<Object>();
				//行数
				for(int k=row.getFirstCellNum();k<row.getLastCellNum();k++){
					cell=row.getCell(k);
 					li.add(getCellValue(cell));
				}
				list.add(li);
			}
		}
		return list;
	}
	/*
	 * 格式转换，获取单元格的值
	 */
	private static Object getCellValue(Cell cell) {
		Object value =null;
		//格式化字符类型的数字
		DecimalFormat df=new DecimalFormat("0");
		//格式化日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化数字
		DecimalFormat df2=new DecimalFormat("0.00");
		 
		if(StringUtils.isEmpty(cell)){
			return "";
		}

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value=cell.getRichStringCellValue().getString();
		
			//value=cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if("General".equals(cell.getCellStyle().getDataFormatString())){
				value=df.format(cell.getNumericCellValue());
			}else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())||
					"m/d/yy h:mm".equals(cell.getCellStyle().getDataFormatString())||
					"m/d/yy h".equals(cell.getCellStyle().getDataFormatString())||
					"m/d/yy h:mm:ss".equals(cell.getCellStyle().getDataFormatString())
					){
				value=sdf.format(cell.getDateCellValue());
			}else{
				value=df2.format(cell.getNumericCellValue());
			}
			//System.out.println(cell.getCellStyle().getDataFormatString());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value=cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value="";
			break;
		default:
			break;
		}
		return value;
	}
	/*
	 * 根据Excel文件后缀名，自适应读取文件
	 */
	private static Workbook getWorkbook(InputStream in, String fileName) throws IOException {
		Workbook wb=null;
		String fileType=fileName.substring(fileName.lastIndexOf("."));
		//判断文件版本
		if(excel2003L.equals(fileType)){
			wb=new HSSFWorkbook(in);
		}else if(excel2007U.equals(fileType)){
			wb=new XSSFWorkbook(in);
		}else{
			logger.error("文件格式不正确！");
		}
		return wb;
	}

	/**
	 * 导出Excel
	 * @param sheetName sheet名称
	 * @param title 标题
	 * @param values 内容
	 * @param wb HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName,List<String> title,String [][]values, HSSFWorkbook wb){

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if(wb == null){
			wb = new HSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

		//声明列对象
		HSSFCell cell = null;

		//创建标题
		for(int i=0;i<title.size();i++){
			cell = row.createCell(i);
			cell.setCellValue(title.get(i));
			cell.setCellStyle(style);
		}

		//创建内容
		for(int i=0;i<values.length;i++){
			row = sheet.createRow(i + 1);
			for(int j=0;j<values[i].length;j++){
				//将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
			}
		}

		//让列宽随着导出的列长自动适应
		for (int colNum = 0; colNum < title.size(); colNum++) {
			int columnWidth = sheet.getColumnWidth(colNum) / 256;
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				HSSFRow currentRow;
				//当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}
				if (currentRow.getCell(colNum) != null) {
					HSSFCell currentCell = currentRow.getCell(colNum);
					if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						int length = currentCell.getStringCellValue().getBytes().length;
						if (columnWidth < length) {
							columnWidth = length;
						}
					}
				}
			}
			if(colNum == 0){
				sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
			}else{
				sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
			}
		}

		return wb;
	}
}
