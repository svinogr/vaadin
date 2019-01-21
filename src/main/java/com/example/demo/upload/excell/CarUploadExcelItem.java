package com.example.demo.upload.excell;

import com.example.demo.entity.cars.car.*;
import com.example.demo.services.CarService;
import com.example.demo.upload.AbstractUploadExcel;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
        Cell cell = null;
        Car car;
        while (it.hasNext()) {
            Row row = it.next();
            if (row.getRowNum() == 0) {
                row = it.next();
            }

            car = new Car();
            GeneralData generalData = new GeneralData();
            PassportData passportData = new PassportData();
            car.setGeneralData(generalData);
            car.setPassportData(passportData);

            Date date;
            cell = row.getCell(0);
            date = dateFromString(cell.getStringCellValue());
            generalData.setDateOfTakeToBalanse(date);

            boolean decomisioned = row.getCell(1).getStringCellValue().toLowerCase().equals("да");
            generalData.setDecommissioned(decomisioned);

            cell = row.getCell(2);
            date = dateFromString(cell.getStringCellValue());
            generalData.setDateOfdecommissioned(date);

            boolean fauly = row.getCell(3).getStringCellValue().toLowerCase().equals("да");
            generalData.setFauly(fauly);

            cell = row.getCell(4);
            generalData.setPodrazdelenieOrGarage(cell.getStringCellValue());

            cell = row.getCell(5);
            generalData.setColonna(cell.getStringCellValue());

            cell = row.getCell(6);
            generalData.setNumberOfGarage(cell.getStringCellValue());

            cell = row.getCell(7);
            generalData.setNumberOfInventar(cell.getStringCellValue());

            cell = row.getCell(8);
            EnumTypeFuel enumTypeFuel = EnumTypeFuel.lookByName(cell.getStringCellValue());
            generalData.setTypeOfFuel(enumTypeFuel);

            cell = row.getCell(9);
            double mileage = getDoubleFromString(cell.getStringCellValue());
            generalData.setMileage(mileage);

            cell = row.getCell(10);
            date = dateFromString(cell.getStringCellValue());
            generalData.setDateOfMileage(date);

            cell = row.getCell(11);
            int mashineHours = getIntFromString(cell.getStringCellValue());
            generalData.setMashineHours(mashineHours);

            cell = row.getCell(12);
            passportData.setVin(cell.getStringCellValue());

            cell = row.getCell(13);
            passportData.setModelTS(cell.getStringCellValue());

            cell = row.getCell(14);
            EnumTypeOfBody enumTypeOfBody = EnumTypeOfBody.lookByName(cell.getStringCellValue());
            passportData.setTypeOfBody(enumTypeOfBody);

            cell = row.getCell(15);
            passportData.setCategory(cell.getStringCellValue());

            cell = row.getCell(16);
            date = dateFromString(cell.getStringCellValue());
            passportData.setYearOfBuild(date);

            cell = row.getCell(17);
            passportData.setModelOfEngine(cell.getStringCellValue());

            cell = row.getCell(18);
            int eccoClass;
            try {
                eccoClass = Integer.parseInt(cell.getStringCellValue());

                if (eccoClass < 0 || eccoClass > 5) {
                    eccoClass = 0;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                eccoClass = 0;
            }
            passportData.setEccoClass(eccoClass);

            cell = row.getCell(19);
            passportData.setNumberOfEngine(cell.getStringCellValue());


            cell = row.getCell(20);
            passportData.setNumberOfChassis(cell.getStringCellValue());

            cell = row.getCell(21);
            passportData.setNumberOfBody(cell.getStringCellValue());


            cell = row.getCell(22);
            passportData.setColor(cell.getStringCellValue());


            cell = row.getCell(23);
            int powerOfEngine = getIntFromString(cell.getStringCellValue());
            passportData.setPowerOfEngine(powerOfEngine);

            cell = row.getCell(24);
            int volumeOfEngine = getIntFromString(cell.getStringCellValue());
            passportData.setVolumeOfEngine(volumeOfEngine);


            cell = row.getCell(25);
            int maxMass = getIntFromString(cell.getStringCellValue());
            passportData.setMaxMass(maxMass);

            cell = row.getCell(26);
            int maxMassWithout = getIntFromString(cell.getStringCellValue());
            passportData.setMaxMassWithout(maxMassWithout);

            cell = row.getCell(27);
            passportData.setBuilder(cell.getStringCellValue());

            cell = row.getCell(28);
            passportData.setNumberOfPassportTS(cell.getStringCellValue());

            cell = row.getCell(29);
            date = dateFromString(cell.getStringCellValue());
            passportData.setDateOfPassportTS(date);


            cell = row.getCell(30);
            passportData.setPlaceOfIssuanceOfPassportTS(cell.getStringCellValue());

            cell = row.getCell(31);
            BigDecimal bigDecimal;
            try {
                bigDecimal = new BigDecimal(cell.getStringCellValue());

            } catch (NumberFormatException e) {
                bigDecimal = null;
            }
            passportData.setCost(bigDecimal);

            cell = row.getCell(32);
            passportData.setRegNumber(cell.getStringCellValue());


            cell = row.getCell(33);
            passportData.setOldregNumber(cell.getStringCellValue());

            cell = row.getCell(34);
            passportData.setCertificateOfRegistration(cell.getStringCellValue());


            cell = row.getCell(35);
            passportData.setPlaceOfregistration(cell.getStringCellValue());


            cell = row.getCell(36);
            date = dateFromString(cell.getStringCellValue());
            passportData.setDateOfRegistration(date);

            cell = row.getCell(37);
            date = dateFromString(cell.getStringCellValue());
            passportData.setTempRegistration(date);

            cell = row.getCell(38);
            int quantityOfPallets = getIntFromString(cell.getStringCellValue());
            passportData.setQuantityOfPallet(quantityOfPallets);

            cell = row.getCell(39);
            double lengthOfBody = getDoubleFromString(cell.getStringCellValue());
            passportData.setLenghtOfBody(lengthOfBody);

            cell = row.getCell(40);
            double widhtOfBody = getDoubleFromString(cell.getStringCellValue());
            passportData.setWidhtOfBody(lengthOfBody);

            cell = row.getCell(41);
            double heightOfBody = getDoubleFromString(cell.getStringCellValue());
            passportData.setHeightOfBody(lengthOfBody);

            cell = row.getCell(42);
            double volumeOfBody = getDoubleFromString(cell.getStringCellValue());
            passportData.setVolumeOfBody(volumeOfBody);

            cell = row.getCell(43);
            generalData.setNumberOfTahograf(cell.getStringCellValue());

            cell = row.getCell(44);
            generalData.setModelTahograf(cell.getStringCellValue());

            cell = row.getCell(45);
            date = dateFromString(cell.getStringCellValue());
            generalData.setDateOfPoverkaTahograf(date);

            cell = row.getCell(46);
            date = dateFromString(cell.getStringCellValue());
            generalData.setDateCalibrOfTahograf(date);

            cell = row.getCell(47);
            generalData.setPlaton(cell.getStringCellValue());

            cell = row.getCell(44);
            boolean track = cell.getStringCellValue().toLowerCase().equals("прицеп");
            car.setTrack(track);

            list.add(car);

        }
        return list;
    }

    private double getDoubleFromString(String stringCellValue) {
        double value;
        try {
            value = Double.parseDouble(stringCellValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = 0;
        }
        return value;
    }

    private int getIntFromString(String stringCellValue) {
        int value;
        try {
            value = Integer.parseInt(stringCellValue);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = 0;
        }
        return value;
    }

    private Date dateFromString(String stringCellValue) {
        Date date;
        try {
            date = format.parse(stringCellValue);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

}
