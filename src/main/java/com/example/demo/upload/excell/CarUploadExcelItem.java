package com.example.demo.upload.excell;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import com.example.demo.upload.AbstractUploadExcel;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringComponent
public class CarUploadExcelItem extends AbstractUploadExcel<Car> {


    public CarUploadExcelItem(CarService itemService) {
        super(itemService);
    }

    @Override
    public List<Car> parseWorkbook(Workbook workbook) {
        List<Car> list = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            if (row.getRowNum() == 0) {
                row = it.next();
            }

            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                String stringCellValue = cell.getStringCellValue();

                if (stringCellValue != null) {
                    //  Date date = new Date(stringCellValue);
                    //   System.out.println(date.toString());
                    System.out.println(stringCellValue);
                }
            }


        }
//            row.createCell(0).setCellValue(car.getGeneralData().getDateOfTakeToBalanse() == null ? "" : dateFormat(car.getGeneralData().getDateOfTakeToBalanse()));
//            row.createCell(1).setCellValue(car.getGeneralData().isDecommissioned() ? "Да" : "Нет");
//            row.createCell(2).setCellValue(car.getGeneralData().getDateOfdecommissioned() == null ? "" : dateFormat(car.getGeneralData().getDateOfdecommissioned()));
//            row.createCell(3).setCellValue(car.getGeneralData().isFauly());
//            row.createCell(4).setCellValue(car.getGeneralData().getPodrazdelenieOrGarage());
//            row.createCell(5).setCellValue(car.getGeneralData().getColonna());
//            row.createCell(6).setCellValue(car.getGeneralData().getNumberOfGarage());
//            row.createCell(7).setCellValue(car.getGeneralData().getNumberOfInventar());
//            row.createCell(8).setCellValue(car.getGeneralData().getTypeOfFuel() == null ? "" : car.getGeneralData().getTypeOfFuel().toString());
//            row.createCell(9).setCellValue(car.getGeneralData().getMileage());
//            row.createCell(10).setCellValue(car.getGeneralData().getDateOfMileage() == null ? "" : dateFormat(car.getGeneralData().getDateOfMileage()));
//            row.createCell(11).setCellValue(car.getGeneralData().getMashineHours());
//            row.createCell(12).setCellValue(car.getPassportData().getVin());
//            row.createCell(13).setCellValue(car.getPassportData().getModelTS());
//            row.createCell(14).setCellValue(car.getPassportData().getTypeOfBody() == null ? "" : car.getPassportData().getTypeOfBody().toString());
//            row.createCell(15).setCellValue(car.getPassportData().getCategory());
//            row.createCell(16).setCellValue(car.getPassportData().getYearOfBuild() == null ? "" : dateFormat(car.getPassportData().getYearOfBuild()));
//            row.createCell(17).setCellValue(car.getPassportData().getModelOfEngine());
//            row.createCell(18).setCellValue(car.getPassportData().getEccoClass());
//            row.createCell(19).setCellValue(car.getPassportData().getNumberOfEngine());
//            row.createCell(20).setCellValue(car.getPassportData().getNumberOfChassis());
//            row.createCell(21).setCellValue(car.getPassportData().getNumberOfBody());
//            row.createCell(22).setCellValue(car.getPassportData().getColor());
//            row.createCell(23).setCellValue(car.getPassportData().getPowerOfEngine());
//            row.createCell(24).setCellValue(car.getPassportData().getVolumeOfEngine());
//            row.createCell(25).setCellValue(car.getPassportData().getMaxMass());
//            row.createCell(26).setCellValue(car.getPassportData().getMaxMassWithout());
//            row.createCell(27).setCellValue(car.getPassportData().getBuilder());
//            row.createCell(28).setCellValue(car.getPassportData().getNumberOfPassportTS());
//            row.createCell(29).setCellValue(car.getPassportData().getDateOfPassportTS() == null ? "" : dateFormat(car.getPassportData().getDateOfPassportTS()));
//            row.createCell(30).setCellValue(car.getPassportData().getPlaceOfIssuanceOfPassportTS());
//            row.createCell(31).setCellValue(car.getPassportData().getCost() == null ? "" : car.getPassportData().getCost().toString());
//            row.createCell(32).setCellValue(car.getPassportData().getRegNumber());
//            row.createCell(33).setCellValue(car.getPassportData().getOldregNumber());
//            row.createCell(34).setCellValue(car.getPassportData().getCertificateOfRegistration());
//            row.createCell(35).setCellValue(car.getPassportData().getPlaceOfregistration());
//            row.createCell(36).setCellValue(car.getPassportData().getDateOfRegistration() == null ? "" : dateFormat(car.getPassportData().getDateOfRegistration()));
//            row.createCell(37).setCellValue(car.getPassportData().getDateTempRegistration() == null ? "" : dateFormat(car.getPassportData().getDateTempRegistration()));
//            row.createCell(38).setCellValue(car.getPassportData().getQuantityOfPallet());
//            row.createCell(39).setCellValue(car.getPassportData().getLenghtOfBody());
//            row.createCell(40).setCellValue(car.getPassportData().getWidhtOfBody());
//            row.createCell(41).setCellValue(car.getPassportData().getHeightOfBody());
//            row.createCell(42).setCellValue(car.getPassportData().getVolumeOfBody());
//            row.createCell(43).setCellValue(car.getGeneralData().getNumberOfTahograf());
//            row.createCell(44).setCellValue(car.getGeneralData().getModelTahograf());
//            row.createCell(45).setCellValue(car.getGeneralData().getDateOfPoverkaTahograf() == null ? "" : dateFormat(car.getGeneralData().getDateOfPoverkaTahograf()));
//            row.createCell(46).setCellValue(car.getGeneralData().getDateCalibrOfTahograf() == null ? "" : dateFormat(car.getGeneralData().getDateCalibrOfTahograf()));
//            row.createCell(47).setCellValue(car.getGeneralData().getPlaton());
//            row.createCell(48).setCellValue(car.isTrack() ? "Прицеп" : "Техника");
//
//
//            return null;

        return list;
    }

}
