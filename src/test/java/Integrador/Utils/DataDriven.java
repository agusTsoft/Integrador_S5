package Integrador.Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
    public ArrayList<String> conexionDataSet(String TestCase) {

        ArrayList<String> data = new ArrayList<>();

        FileInputStream file = null;
        try {
            file = new FileInputStream("C:\\IntegradorS5\\src\\test\\resources\\Data\\DataSet.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook excel = null;
        try{
            excel = new XSSFWorkbook(file);
        }catch (IOException e){
            System.out.println("Archivo no encontrado");
        }
        int cantHojas = 0;
        if (excel != null) {
            cantHojas = excel.getNumberOfSheets();
        }else{
            System.out.println("'Excel' es null");
        }

        for (int i = 0; i<cantHojas; i++){
            if (excel.getSheetName(i).equalsIgnoreCase("DatosCP")){
                XSSFSheet Hoja = excel.getSheetAt(i);

                Iterator<Row> filas = Hoja.iterator();

                Row primeraFila = filas.next();

                Iterator<Cell> celda =  primeraFila.cellIterator();

                int k = 0;
                int columna = 0;

                while(celda.hasNext()){
                    Cell celdaSeleccionada = celda.next();
                    if(celdaSeleccionada.getStringCellValue().equalsIgnoreCase("CasosDePrueba")){
                        columna = k;
                    }
                    k++;
                }
                while (filas.hasNext()){
                    Row r = filas.next();

                    if (r.getCell(columna).getStringCellValue().equalsIgnoreCase(TestCase)){
                        Iterator<Cell> cv = r.cellIterator();

                        while(cv.hasNext()){
                            Cell cell = cv.next();

                            if (cell.getCellType() == CellType.STRING){
                                data.add(cell.getStringCellValue());
                            }else if(cell.getCellType() == CellType.NUMERIC){
                                data.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return data;
    }
}
