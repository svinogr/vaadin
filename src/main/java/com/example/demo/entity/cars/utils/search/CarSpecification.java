package com.example.demo.entity.cars.utils.search;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.GeneralData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CarSpecification {
    private static  final String GENERAL_DATA_FIELD_OF_CAR ="generalData";
    private static  final String PASSPORT_DATA_FIELD_OF_CAR ="passportData";

    private CarSpecification() {
    }

    private static Predicate getCheckablePredicate(MyFilterItem myFilterItem, Root<Car> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        Join<Car, GeneralData> join = root.join(generalDataFieldOfCar);
        return criteriaBuilder.equal(
                join.get(myFilterItem.getEnumColumnNames().getColumnSearchName()),
                myFilterItem.isChecked());
    }
    private static Predicate getDatablePredicate(MyFilterItem myFilterItem, Root<Car> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        Join<Car, GeneralData> join = root.join(generalDataFieldOfCar);
        return criteriaBuilder.between(
                join.get(myFilterItem.getEnumColumnNames().getColumnSearchName()),
                myFilterItem.getDateForSearch()[0], myFilterItem.getDateForSearch()[1]);
    }
    private static Predicate getTextablelePredicate(MyFilterItem myFilterItem, Root<Car> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        Join<Car, GeneralData> join = root.join(generalDataFieldOfCar);
        return criteriaBuilder.equal(
                join.get(myFilterItem.getEnumColumnNames().getColumnSearchName()),
                myFilterItem.getTexForSearch()[0]);
    }

    private static Predicate getDoubleTwoTextablelePredicate(MyFilterItem myFilterItem, Root<Car> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        Join<Car, GeneralData> join = root.join(generalDataFieldOfCar);
        return criteriaBuilder.between(
                join.get(myFilterItem.getEnumColumnNames().getColumnSearchName()),
                Double.parseDouble(myFilterItem.getTexForSearch()[0]), Double.parseDouble(myFilterItem.getTexForSearch()[1]));}

    private static Predicate getIntTwoTextablelePredicate(MyFilterItem myFilterItem, Root<Car> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        Join<Car, GeneralData> join = root.join(generalDataFieldOfCar);
        return criteriaBuilder.between(
                join.get(myFilterItem.getEnumColumnNames().getColumnSearchName()),
                Integer.parseInt(myFilterItem.getTexForSearch()[0]),  Integer.parseInt(myFilterItem.getTexForSearch()[1]));}

    public static Specification<Car> byId(int id){
        return (root, query, criteriaBuilder) -> {
            int idS = id;
            Join<Car, GeneralData>  join = root.join("generalData");
            return criteriaBuilder.equal(join.get("id"),idS);
        };
    }

    public static Specification<Car> generalByFauly(MyFilterItem myFilterItem){
        return ((root, query, criteriaBuilder) -> getCheckablePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByDateTaleToBalance(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getDatablePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByDecomissionned(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getCheckablePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByGarageOrPodrazdelenie(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByColonna(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByNumberGarage(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByNumberInventar(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByTypeOfFuel(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByMeleage(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getDoubleTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> generalByMashineHours(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, GENERAL_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByVin(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByTypeTS(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByYearOfBuild(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getDatablePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByEcoOfEngine(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByNumberEngine(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByNumberOfChassis(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByNumberOfBody(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByPowerOfEngine(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByVolumeOfEngine(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByMass(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));

    }

    public static Specification<Car> passportByMassWithout(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));

    }

    public static Specification<Car> passportByNumberOfPassport(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByRegNumber(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByQuantityOfPallete(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByWidhtOfBody(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByHeightOfBody(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByLenghtOfBody(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getIntTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }

    public static Specification<Car> passportByVolumetOfBody(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getDoubleTwoTextablelePredicate(myFilterItem, root, criteriaBuilder, PASSPORT_DATA_FIELD_OF_CAR));
    }
}
