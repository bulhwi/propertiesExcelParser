import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CreateExcelFile {

    public void createWorkbook(List<String> messageTagNameList, Map<String, String> enMap, Map<String, String> tonMap) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

/*        XSSFCellStyle style = ((XSSFWorkbook) workbook).createCellStyle();
        //style.setFillBackgroundColor(new XSSFColor(new java.awt.Color(255, 225, 221)));
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);*/

        //------------------------- top
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("messageTagName");
        row.createCell(1).setCellValue("영어");
        row.createCell(2).setCellValue("통가어");
        //-------------------------

        for (int i = 0; i < messageTagNameList.size(); i++) {
            row = sheet.createRow(i + 1);
            if ((messageTagNameList.indexOf("#") == 0) && messageTagNameList.equals("")) { //#pagename.jsp 일때
                Cell pageCell = row.createCell(0);
                pageCell.setCellValue(messageTagNameList.get(i));
                //pageCell.setCellStyle(style);
            } else { // tagName=lang 일때
                Cell tagNameCell = row.createCell(0);
                Cell enCell = row.createCell(1);
                Cell tonCell = row.createCell(2);

                tagNameCell.setCellValue(messageTagNameList.get(i));
                enCell.setCellValue(enMap.get(messageTagNameList.get(i)));
                tonCell.setCellValue(tonMap.get(messageTagNameList.get(i)));
            }
        }

        saveFile(workbook);
        System.out.println("***   SAVE FILE COMPLETE   ***");
    }

    public void saveFile(Workbook workbook) {

        String savePath = "C:/Users/bulhwi/Desktop"; //pc 마다 바꿔줘야됨 ;;

        try {

            File file = new File(savePath + "/messageTagNameExcel.xlsx");
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
