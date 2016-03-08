package com.pantau.core


import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
/**
 * Created by GE62 on 3/8/2016.
 */
class PasarExcelImporter {


    PasarExcelImporter(InputStream is) {
        XSSFWorkbook wb = new XSSFWorkbook(is)
        XSSFSheet ws = wb.getSheetAt(0)

        for (int i = 3; i < ws.getLastRowNum(); i++) {
            XSSFRow row = ws.getRow(i)
            if (row) {
                XSSFCell c0 = row.getCell(3)
                XSSFCell c1 = row.getCell(4)
                XSSFCell c2 = row.getCell(5)
                def tmp = new Pasar(nama: c0.toString(), lng: Double.parseDouble(c1.toString()), lat: Double.parseDouble(c2.toString()))
                if(!tmp.save(flush: true)){
                    tmp.errors.each {
                        println it
                    }
                }

            }
        }
    }
}
