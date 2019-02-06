package com.example.demo.upload.excell;

import com.example.demo.entity.cars.car.*;
import com.example.demo.services.CarService;
import com.example.demo.upload.AbstractUploadExcel;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@SpringComponent
public class CarUploadExcelItem extends AbstractUploadExcel<Car> {


    public CarUploadExcelItem(CarService itemService) {
        super(itemService);
    }

    @Override
    public List<Car> parseWorkbook(Workbook workbook) {
        List<Car> list = new ArrayList<>();
        String username = loginService.getAuth().getUsername();
        System.out.println(username);
        try {
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
                date = dateFromString(cell);
                generalData.setDateOfTakeToBalanse(date);


                boolean decomisioned;
                cell = row.getCell(1);
                if (cell == null) {
                    decomisioned = false;
                } else {
                    decomisioned = cell.getStringCellValue().toLowerCase().equals("да");
                }
                generalData.setDecommissioned(decomisioned);

                cell = row.getCell(2);
                date = dateFromString(cell);
                generalData.setDateOfdecommissioned(date);

                boolean fauly;
                cell = row.getCell(3);
                if (cell == null) {
                    fauly = false;
                } else {
                    fauly = cell.getStringCellValue().toLowerCase().equals("да");
                }
                generalData.setFauly(fauly);

                cell = row.getCell(4);
                generalData.setPodrazdelenieOrGarage(getStringFromCell(cell));

                cell = row.getCell(5);
                generalData.setColonna(getStringFromCell(cell));

                cell = row.getCell(6);
                generalData.setNumberOfGarage(getStringFromCell(cell));

                cell = row.getCell(7);
                generalData.setNumberOfInventar(getStringFromCell(cell));

                cell = row.getCell(8);
                EnumTypeFuel enumTypeFuel = null;
                if (cell != null) {
                    enumTypeFuel = EnumTypeFuel.lookByName(cell.getStringCellValue());
                }
                generalData.setTypeOfFuel(enumTypeFuel);

                cell = row.getCell(9);
                double mileage = getDoubleFromString(cell);
                generalData.setMileage(mileage);

                cell = row.getCell(10);
                date = dateFromString(cell);
                generalData.setDateOfMileage(date);

                cell = row.getCell(11);
                int mashineHours = getIntFromString(cell);
                generalData.setMashineHours(mashineHours);

                cell = row.getCell(12);
                passportData.setVin(getStringFromCell(cell));

                cell = row.getCell(13);
                passportData.setModelTS(getStringFromCell(cell));

                cell = row.getCell(14);
                EnumTypeOfBody enumTypeOfBody = null;
                if (cell != null) {
                    enumTypeOfBody = EnumTypeOfBody.lookByName(cell.getStringCellValue());
                }
                passportData.setTypeOfBody(enumTypeOfBody);

                cell = row.getCell(15);
                passportData.setCategory(getStringFromCell(cell));

                cell = row.getCell(16);
                date = dateFromString(cell);
                passportData.setYearOfBuild(date);

                cell = row.getCell(17);
                passportData.setModelOfEngine(getStringFromCell(cell));

                cell = row.getCell(18);
                int eccoClass = 0;
                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        eccoClass = (int) cell.getNumericCellValue();
                    } else {
                        try {
                            eccoClass = Integer.parseInt(cell.getStringCellValue());

                            if (eccoClass < 0 || eccoClass > 5) {
                                eccoClass = 0;
                            }

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            eccoClass = 0;
                        }
                    }
                }
                passportData.setEccoClass(eccoClass);

                cell = row.getCell(19);
                passportData.setNumberOfEngine(getStringFromCell(cell));


                cell = row.getCell(20);
                passportData.setNumberOfChassis(getStringFromCell(cell));

                cell = row.getCell(21);
                passportData.setNumberOfBody(getStringFromCell(cell));

                cell = row.getCell(22);
                passportData.setColor(getStringFromCell(cell));

                cell = row.getCell(23);
                int powerOfEngine = getIntFromString(cell);
                passportData.setPowerOfEngine(powerOfEngine);

                cell = row.getCell(24);
                int volumeOfEngine = getIntFromString(cell);
                passportData.setVolumeOfEngine(volumeOfEngine);

                cell = row.getCell(25);
                int maxMass = getIntFromString(cell);
                passportData.setMaxMass(maxMass);

                cell = row.getCell(26);
                int maxMassWithout = getIntFromString(cell);
                passportData.setMaxMassWithout(maxMassWithout);

                cell = row.getCell(27);
                passportData.setBuilder(getStringFromCell(cell));

                cell = row.getCell(28);
                passportData.setNumberOfPassportTS(getStringFromCell(cell));

                cell = row.getCell(29);
                date = dateFromString(cell);
                passportData.setDateOfPassportTS(date);

                cell = row.getCell(30);
                passportData.setPlaceOfIssuanceOfPassportTS(getStringFromCell(cell));

                cell = row.getCell(31);
                BigDecimal bigDecimal = null;
                if (cell != null) {
                    try {
                        bigDecimal = new BigDecimal(cell.getStringCellValue());

                    } catch (NumberFormatException e) {
                        bigDecimal = null;
                    }
                }
                passportData.setCost(bigDecimal);

                cell = row.getCell(32);
                passportData.setRegNumber(getStringFromCell(cell));

                cell = row.getCell(33);
                passportData.setOldregNumber(getStringFromCell(cell));

                cell = row.getCell(34);
                passportData.setCertificateOfRegistration(getStringFromCell(cell));

                cell = row.getCell(35);
                passportData.setPlaceOfregistration(getStringFromCell(cell));

                cell = row.getCell(36);
                date = dateFromString(cell);
                passportData.setDateOfRegistration(date);

                cell = row.getCell(37);
                date = dateFromString(cell);
                passportData.setTempRegistration(date);

                cell = row.getCell(38);
                int quantityOfPallets = getIntFromString(cell);
                passportData.setQuantityOfPallet(quantityOfPallets);

                cell = row.getCell(39);
                double lengthOfBody = getDoubleFromString(cell);
                passportData.setLenghtOfBody(lengthOfBody);

                cell = row.getCell(40);
                double widhtOfBody = getDoubleFromString(cell);
                passportData.setWidhtOfBody(lengthOfBody);

                cell = row.getCell(41);
                double heightOfBody = getDoubleFromString(cell);
                passportData.setHeightOfBody(lengthOfBody);

                cell = row.getCell(42);
                double volumeOfBody = getDoubleFromString(cell);
                passportData.setVolumeOfBody(volumeOfBody);

                cell = row.getCell(43);
                generalData.setNumberOfTahograf(getStringFromCell(cell));

                cell = row.getCell(44);
                generalData.setModelTahograf(getStringFromCell(cell));

                cell = row.getCell(45);
                date = dateFromString(cell);
                generalData.setDateOfPoverkaTahograf(date);

                cell = row.getCell(46);
                date = dateFromString(cell);
                generalData.setDateCalibrOfTahograf(date);

                cell = row.getCell(47);
                generalData.setPlaton(getStringFromCell(cell));

                cell = row.getCell(44);
                boolean track = false;
                if (cell != null) {
                    track = cell.getStringCellValue().toLowerCase().equals("прицеп");
                }
                car.setTrack(track);

                list.add(car);

            }
        } catch (IllegalArgumentException e) {
            list = Collections.EMPTY_LIST;
        }

        System.out.println(list.size());
        return list;
    }

    private String getStringFromCell(Cell cell) {
        String value;
// TODO
//        if (cell == null) return null;
//        cell.setCellType(CellType.STRING);
//        if (cell.getCellType() == CellType.NUMERIC) {
//            value = String.valueOf(cell.getNumericCellValue());
//            System.out.println(value + "  numer");
//        } else {
//            if (cell.getCellType() == CellType.STRING) {
//                value = cell.getStringCellValue();
//                System.out.println(value + " string");
//            } else value = null;
//        }
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        value = cell.getStringCellValue();
        if (value.trim().isEmpty()) {
            value = null;
        }
        return value;
    }

    private double getDoubleFromString(Cell cell) {
        double value = 0;
        if (cell == null) return value;
        if (cell.getCellType() == CellType.NUMERIC) {
            value = cell.getNumericCellValue();
        } else {
            try {
                value = Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException | NullPointerException e) {
                //  e.printStackTrace();
                value = 0;
            }
        }
        return value;
    }

    private int getIntFromString(Cell cell) {
        int value = 0;

        if (cell == null) return value;

        if (cell.getCellType() == CellType.NUMERIC) {
            value = (int) cell.getNumericCellValue();
        } else {
            try {
                value = Integer.parseInt(cell.getStringCellValue());

            } catch (NumberFormatException | NullPointerException e) {
                // e.printStackTrace();
                value = 0;
            }
        }
        return value;
    }

    private Date dateFromString(Cell cell) {
        Date date = null;
        if (cell == null) return date;
        try {
            date = format.parse(cell.getStringCellValue());
        } catch (ParseException | NullPointerException e) {
            //e.printStackTrace();
            date = null;
        }

        return date;
    }

}
