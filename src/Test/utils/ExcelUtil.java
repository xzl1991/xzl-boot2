package utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ExcelUtil {
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String ALL = "全部";

	
 	

	/**
	 * 合并单元格后给合并后的单元格加边框
	 * 
	 * @param region
	 * @param cs
	 */
	public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs, XSSFSheet sheet)
	{

		int toprowNum = region.getFirstRow();
		for (int i = toprowNum; i <= region.getLastRow(); i++)
		{
			XSSFRow row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++)
			{
				XSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
												// (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	/**
	 * 设置表头的单元格样式
	 * 
	 * @return
	 */
	public static HSSFCellStyle getHeadStyle(HSSFWorkbook workBook)
	{
		// 创建单元格样式
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		HSSFFont font = workBook.createFont();
		// 设置字体加粗
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("黑体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	/**
	 * 设置表体的单元格样式
	 * 
	 * @return
	 */
	public static HSSFCellStyle getBodyStyle(HSSFWorkbook workBook)
	{
		// 创建单元格样式
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		HSSFFont font = workBook.createFont();
		// 设置字体加粗
//		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("黑体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	/**
	 * 设置表体的标题样式
	 * 
	 * @return
	 */
	public static HSSFCellStyle getTitleStyle(HSSFWorkbook workBook)
	{
		// 创建单元格样式
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		HSSFFont font = workBook.createFont();
		// 设置字体加粗
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("黑体");
		font.setFontHeight((short) 300);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	public static CellStyle getHeadStyle(SXSSFWorkbook workBook)
	{
		
		CellStyle cellStyle = workBook.createCellStyle();
		
		cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		cellStyle.setWrapText(false);
		
		Font font = workBook.createFont();
		
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("榛戜綋");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	public static CellStyle getBodyStyle(SXSSFWorkbook workBook)
	{
		
		CellStyle cellStyle = workBook.createCellStyle();
		
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		cellStyle.setWrapText(false);
		
		Font font = workBook.createFont();
		
//		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("榛戜綋");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	public static CellStyle getTitleStyle(SXSSFWorkbook workBook)
	{
		
		CellStyle cellStyle = workBook.createCellStyle();
		
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		cellStyle.setWrapText(false);
		
		Font font = workBook.createFont();
		
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("榛戜綋");
		font.setFontHeight((short) 300);
		cellStyle.setFont(font);
		
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	public static SXSSFWorkbook mapToExcel(Map<String,String> titles,List<Map<String,Object>> list){
		SXSSFWorkbook wb = null;
		try{
			wb = new SXSSFWorkbook();
			Sheet sheet = wb.createSheet();
			CellStyle headStyle = ExcelUtil.getHeadStyle(wb);
			CellStyle bodyStyle = ExcelUtil.getBodyStyle(wb);

			Row titelRow = sheet.createRow(0);
			Iterator<String> titlesIterator = titles.keySet().iterator();
			for(int t = 0;t<titles.size();t++)
			{
				Cell cell = titelRow.createCell(t);
				cell.setCellValue(titlesIterator.next());
				cell.setCellStyle(headStyle);
			}
			addSheetByMap(sheet,bodyStyle,titles,list);
			return wb;
		}catch (Exception e){
			e.printStackTrace();
		}
		return wb;
	}
	/**
	  *@Auther : xzl
	  *@return :
	  *@Description: Map 泛型导致的导出方法不通用问题
	  *@Date : 15:04 2017/11/22
	  */
	public static SXSSFWorkbook mapToExcelWithNoType(Map<String,String> titles,List<Map> list){
		SXSSFWorkbook wb = null;
		try{
			wb = new SXSSFWorkbook();
			Sheet sheet = wb.createSheet();
			CellStyle headStyle = ExcelUtil.getHeadStyle(wb);
			CellStyle bodyStyle = ExcelUtil.getBodyStyle(wb);

			Row titelRow = sheet.createRow(0);
			Iterator<String> titlesIterator = titles.keySet().iterator();
			for(int t = 0;t<titles.size();t++)
			{
				Cell cell = titelRow.createCell(t);
				cell.setCellValue(titlesIterator.next());
				cell.setCellStyle(headStyle);
			}
			addSheetByMapNoType(sheet,bodyStyle,titles,list);
			return wb;
		}catch (Exception e){
			e.printStackTrace();
		}
		return wb;
	}
	private static void addSheetByMapNoType(Sheet sheet,CellStyle bodyStyle,Map<String,String> titles,List<Map> list ){
		String key ;
		String value = "";
		Iterator iterator ;
		Row row ;
		Map map ;
		Cell cell;
		for(int i = 0;i<list.size();i++)
		{
			map = list.get(i);
			iterator = titles.values().iterator();
			row = sheet.createRow(i+1);
			for(int j = 0;j<titles.size();j++)
			{
				key = iterator.next().toString();
				if(map.containsKey(key) && map.get(key) != null)
				{
					value = map.get(key).toString();
				}
				cell = row.createCell(j);
				cell.setCellValue(value);
				cell.setCellStyle(bodyStyle);
			}
		}
	}
	private static void addSheetByMap(Sheet sheet,CellStyle bodyStyle,Map<String,String> titles,List<Map<String,Object>>
			list ){
		String key ;
		Iterator iterator ;
		Row row ;
		Map<String,Object> map ;
		Cell cell;
		for(int i = 0;i<list.size();i++)
		{
			map = list.get(i);
			iterator = titles.values().iterator();
			row = sheet.createRow(i+1);
			for(int j = 0;j<titles.size();j++)
			{
				String value = "";
				key = iterator.next().toString();
				if(map.containsKey(key) && map.get(key) != null)
				{
					value = map.get(key).toString();
				}
				cell = row.createCell(j);
				cell.setCellValue(value);
				cell.setCellStyle(bodyStyle);
			}
		}
	}
	/**
	 * 读取简单excel
	 * */
	public static JSONArray readXLSXExcelToArray(File file) throws IOException {
		JSONArray array = new JSONArray();
		InputStream is = null;
		Workbook wb = null;
		try {
			is = new FileInputStream(file);
			wb = new XSSFWorkbook(is);
			int sheetSize = wb.getNumberOfSheets();
			for (int i = 0; i < sheetSize; i++) {
				Sheet sheet = wb.getSheetAt(i);
				JSONArray title = new JSONArray();
				int rowSize = sheet.getLastRowNum() + 1;
				for (int i1 = 0; i1 < rowSize; i1++) {
					Row row = sheet.getRow(i1);
					if (row == null) {//略过空行
						continue;
					}
					int cellSize = row.getLastCellNum();
					if (i1 == 0) {//第一行是标题行
						for (int k = 0; k < cellSize; k++) {
							Cell cell = row.getCell(k);
							title.add(cell.toString());
						}
					}else{//其他行是数据行
						JSONObject obj = new JSONObject();
						for (int k = 0; k < title.size(); k++) {
							Cell cell = row.getCell(k);
							String key = title.getString(k);
							String value = null;
							if (cell != null) {
								value = cell.toString();
							}
							obj.put(key, value);
						}
						array.add(obj);
					}
				}
			}

		} finally {
			if (wb != null) {
				wb.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return array;
	}
	/**
	 * <P>自定义文件导出</P>
	 * @auth xzl
	 * */
	public static  void exportFileByName(Map<String,String> titles,List<Map<String,Object>> list,String sufName,HttpServletResponse response) throws Exception {
		OutputStream ouputStream = null;
		try (SXSSFWorkbook wb = ExcelUtil.mapToExcel(titles, list)){
			dealWithresponse(sufName,response);
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
		} finally {
			if (ouputStream != null) {
				ouputStream.flush();
				ouputStream.close();
			}
		}
	}
	/**
	 * <P>自定义文件导出,无泛型的map</P>
	 * @auth xzl
	 * */
	public static  void exportFileByNameMap(Map<String,String> titles,List<Map> list,String sufName,HttpServletResponse response) throws Exception {
		OutputStream ouputStream = null;
		try (SXSSFWorkbook wb = ExcelUtil.mapToExcelWithNoType(titles, list)){
			dealWithresponse(sufName,response);
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if (ouputStream != null) {
				ouputStream.flush();
				ouputStream.close();
			}
		}
	}
	private static void dealWithresponse(String sufName,HttpServletResponse response) throws Exception{
		String filename = sufName + new DateUtils() + ".xlsx";
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));
	}
	public static String getCellStringValue(Cell cell){
		String value;
		if(cell == null)
		{
			return "";
		}
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_NUMERIC :
				DecimalFormat format = new DecimalFormat("#.00");
				value = format.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING :
				value = cell.getStringCellValue();
				break;
			default :
				value = "";
		}
		return value;
		
	}
}
