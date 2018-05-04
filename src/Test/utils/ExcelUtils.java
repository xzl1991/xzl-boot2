//package utils;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
///**
// * Created by xzl on 2017/12/5.
// *
// * @author xuzelong
// * @date 2017/12/5  14:36.
// */
//public class ExcelUtils {
//    /**
//      *@Auther : xzl
//      *@Description: 读取Excel类型的文件
//      *@Date : 10:05 2017/12/6
//      */
//    public static JSONArray readXLSXExcelToArray(File file,boolean hasTitle) {
//        JSONArray array = new JSONArray();
//            // 1. 获取 WorkBook
//        try(InputStream  in = new FileInputStream(file);XSSFWorkbook wb =  new XSSFWorkbook(in)){
//            int sheetSize = wb.getNumberOfSheets();
//            Sheet sheet ;
//            JSONArray title ;
//            Row row ;
//            Cell cell ;
//            JSONObject obj;
//            int titleSize = 0;
//            for (int i=0;i<sheetSize;i++){
//                sheet = wb.getSheetAt(i);
//                title = new JSONArray();
//                int rowSize = sheet.getLastRowNum() + 1;
//                for (int j=0;j<rowSize;j++){
//                    row = sheet.getRow(j);
//                    if (row == null) {//略过空行
//                        continue;
//                    }
//                    int cellSize = row.getLastCellNum();
//                    if (j == 0 && hasTitle) {//第一行是标题行
//                        for (int k = 0; k < cellSize; k++) {
//                            cell = row.getCell(k);
//                            title.add(cell.toString());
//                        }
//                    }else{//其他行是数据行
//                        obj = new JSONObject();
//                        if(hasTitle){
//                            titleSize = title.size();
//                        }else {
//                            titleSize = cellSize;
//                        }
//                        for (int k = 0; k < titleSize; k++) {
//                            cell = row.getCell(k);
//                            String key = (hasTitle?(title.getString(k)):"KEY");
//                            String value = null;
//                            if (cell != null) {
//                                value = cell.toString();
//                            }
//                            obj.put(key, value);
//                        }
//                        array.add(obj);
//                    }
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return array;
//    }
//}
